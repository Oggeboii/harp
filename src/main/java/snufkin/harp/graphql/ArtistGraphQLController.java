package snufkin.harp.graphql;

import java.util.List;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import snufkin.harp.dto.ArtistDTO;
import snufkin.harp.service.ArtistService;

@Controller
public class ArtistGraphQLController {

    private final ArtistService artistService;

    public ArtistGraphQLController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @QueryMapping
    public List<ArtistDTO> artists() {
        return artistService.getAllArtists();
    }

    @QueryMapping
    public ArtistDTO artist(@Argument Long id) {
        return artistService.getArtistById(id);
    }
    
}
