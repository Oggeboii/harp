package snufkin.harp.route;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

import snufkin.harp.request.CreateTrackRequest;
import snufkin.harp.request.UpdateTrackRequest;
import snufkin.harp.service.TrackService;

@Component
public class TrackRoute extends RouteBuilder {

    private final TrackService trackService;

    public TrackRoute(TrackService trackService) {
        this.trackService = trackService;
    }

    @Override
    public void configure() {
        restConfiguration()
        .component("servlet")
        .bindingMode(RestBindingMode.json);
        rest("/tracks")
        
        //GET /tracks/{id}
        .get("/{id}")
            .to("direct:getTrack")

        //GET /tracks
        .get()
            .to("direct:getAllTracks")

        //POST /tracks
        .post()
            .type(CreateTrackRequest.class)
            .consumes("application/json")
            .to("direct:createTrack")

        //PUT /tracks/{id}
        .put("/{id}")
            .type(UpdateTrackRequest.class)
            .consumes("application/json")
            .to("direct:updateTrack")

        //DELETE /tracks/{id}
        .delete("/{id}")
            .to("direct:deleteTrack");

        //GET ONE
        from("direct:getTrack")
        .process(exchange -> {
            Long id = exchange.getMessage()
                    .getHeader("id", Long.class);
            exchange.getMessage()
                    .setBody(trackService.getTrackById(id));
        });

        //GET ALL
        from("direct:getAllTracks")
        .process(exchange -> {
            exchange.getMessage()
                    .setBody(trackService.getAllTracks());
        });

        //CREATE
        from("direct:createTrack")
        .process(exchange -> {
            CreateTrackRequest trackRequest = exchange.getMessage()
                                            .getBody(CreateTrackRequest.class);
            exchange.getMessage()
                    .setBody(trackService.createTrack(trackRequest));
        });

        //UPDATE
        from("direct:updateTrack")
        .process(exchange -> {
            Long id = exchange.getMessage()
                    .getHeader("id", Long.class);
            UpdateTrackRequest trackRequest = exchange.getMessage()
                                            .getBody(UpdateTrackRequest.class);
            exchange.getMessage()
                    .setBody(trackService.updateTrack(id, trackRequest));
        });

        //DELETE
        from("direct:deleteTrack")
        .process(exchange -> {
            Long id = exchange.getMessage()
                    .getHeader("id", Long.class);
            trackService.deleteTrack(id);
            exchange.getMessage()
                    .setBody(null);
            exchange.getMessage()
                    .setHeader("CamelHttpResponseCode", 204);
        });
    }
    
}
