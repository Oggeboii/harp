package snufkin.harp.route;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

import snufkin.harp.request.CreateAlbumRequest;
import snufkin.harp.request.UpdateAlbumRequest;
import snufkin.harp.service.AlbumService;

@Component
public class AlbumRoute extends RouteBuilder {

    private final AlbumService albumService;

    public AlbumRoute(AlbumService albumService) {
        this.albumService = albumService;
    }
    
    @Override
    public void configure() {
        restConfiguration()
        .component("servlet")
        .bindingMode(RestBindingMode.json);
        rest("/albums")
        
        //GET /albums/{id}
        .get("/{id}")
            .to("direct:getAlbum")

        //GET /albums
        .get()
            .to("direct:getAllAlbums")

        //POST /albums
        .post()
            .type(CreateAlbumRequest.class)
            .consumes("application/json")
            .to("direct:createAlbum")

        //PUT /albums/{id}
        .put("/{id}")
            .type(UpdateAlbumRequest.class)
            .consumes("application/json")
            .to("direct:updateAlbum")

        //DELETE /albums/{id}
        .delete("/{id}")
            .to("direct:deleteAlbum");

        //GET ONE
        from("direct:getAlbum")
        .process(exchange -> {
            Long id = exchange.getMessage()
                    .getHeader("id", Long.class);
            exchange.getMessage()
                    .setBody(albumService.getAlbumById(id));
        });

        //GET ALL
        from("direct:getAllAlbums")
        .process(exchange -> {
            exchange.getMessage()
                    .setBody(albumService.getAllAlbums());
        });

        //CREATE
        from("direct:createAlbum")
        .process(exchange -> {
            CreateAlbumRequest request = exchange.getMessage()
                                        .getBody(CreateAlbumRequest.class);
            exchange.getMessage()
                    .setBody(albumService.createAlbum(request));
        });
    
        //UPDATE
        from("direct:updateAlbum")
        .process(exchange -> {
            Long id = exchange.getMessage()
                    .getHeader("id", Long.class);
            UpdateAlbumRequest request = exchange.getMessage()
                                        .getBody(UpdateAlbumRequest.class);
            exchange.getMessage()
                    .setBody(albumService.updateAlbum(id, request));
        });

        //DELETE
        from("direct:deleteAlbum")
        .process(exchange -> {
            Long id = exchange.getMessage()
                    .getHeader("id", Long.class);
            albumService.deleteAlbum(id);
            exchange.getMessage()
                    .setBody(null);
            exchange.getMessage()
                    .setHeader("CamelHttpResponseCode", 204);
        });
    }
}
