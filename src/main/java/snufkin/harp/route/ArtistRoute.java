package snufkin.harp.route;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

import snufkin.harp.request.CreateArtistRequest;
import snufkin.harp.request.UpdateArtistRequest;
import snufkin.harp.service.ArtistService;

@Component
public class ArtistRoute extends RouteBuilder {

    private final ArtistService artistService;

    public ArtistRoute(ArtistService artistService) {
        this.artistService = artistService;
    }
    
    @Override
    public void configure() {
        restConfiguration()
        .component("servlet")
        .bindingMode(RestBindingMode.json);
        rest("/artists")
        
        //GET /artists/{id}
        .get("/{id}")
            .to("direct:getArtist")

        //GET /artists
        .get()
            .to("direct:getAllArtists")

        //POST /artists
        .post()
            .type(CreateArtistRequest.class)
            .consumes("application/json")
            .to("direct:createArtist")

        //PUT /artists/{id}
        .put("/{id}")
            .type(UpdateArtistRequest.class)
            .consumes("application/json")
            .to("direct:updateArtist")

        //DELETE /artists/{id}
        .delete("/{id}")
            .to("direct:deleteArtist");

        //GET ONE
        from("direct:getArtist")
        .process(exchange -> {
            Long id = exchange.getMessage()
                    .getHeader("id", Long.class);
            exchange.getMessage()
                    .setBody(artistService.getArtistById(id));
        });

        //GET ALL
        from("direct:getAllArtists")
        .process(exchange -> {
            exchange.getMessage()
                    .setBody(artistService.getAllArtists());
        });

        //CREATE
        from("direct:createArtist")
        .process(exchange -> {
            CreateArtistRequest request = exchange.getMessage()
                                        .getBody(CreateArtistRequest.class);
            exchange.getMessage()
                    .setBody(artistService.createArtist(request));
        });

        //UPDATE
        from("direct:updateArtist")
        .process(exchange -> {
            Long id = exchange.getMessage()
                    .getHeader("id", Long.class);
            UpdateArtistRequest request = exchange.getMessage()
                                        .getBody(UpdateArtistRequest.class);
            exchange.getMessage()
                    .setBody(artistService.updateArtist(id, request));
        });

        //DELETE
        from("direct:deleteArtist")
        .process(exchange -> {
            Long id = exchange.getMessage()
                    .getHeader("id", Long.class);
            artistService.deleteArtist(id);
            exchange.getMessage()
                    .setBody(null);
            exchange.getMessage()
                    .setHeader("CamelHttpResponseCode", 204);
        });
    }
}
