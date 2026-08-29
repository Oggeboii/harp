package snufkin.harp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import snufkin.harp.dto.ArtistDTO;
import snufkin.harp.model.Artist;
import snufkin.harp.repository.ArtistRepository;
import snufkin.harp.request.CreateArtistRequest;
import snufkin.harp.request.UpdateArtistRequest;

@Service
public class ArtistService {
    
    private final ArtistRepository artistRepository;

    public ArtistService(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }


    @Transactional
    public ArtistDTO createArtist(CreateArtistRequest artistRequest) {
        Artist artist = new Artist();
        artist.setName(artistRequest.getName());
        artist.setDescription(artistRequest.getDescription());
        artist.setImageUrl(artistRequest.getImageUrl());
        artistRepository.save(artist);
        return new ArtistDTO(artist.getId(), artist.getName(), artist.getDescription(), artist.getImageUrl());
    }

    public ArtistDTO getArtistById(Long id) {
        Artist artist = artistRepository.getArtistById(id);
        return new ArtistDTO(artist.getId(), artist.getName(), artist.getDescription(), artist.getImageUrl());
    }

    public List<ArtistDTO> getAllArtists() {
        return artistRepository.findAll().stream()
                .map(artist -> new ArtistDTO(artist.getId(), artist.getName(), artist.getDescription(), artist.getImageUrl()))
                .toList();
    }

    @Transactional
    public ArtistDTO updateArtist(Long id, UpdateArtistRequest artistRequest) {
        Artist artist = artistRepository.getArtistById(id);
        artist.setName(artistRequest.getName());
        artist.setDescription(artistRequest.getDescription());
        artist.setImageUrl(artistRequest.getImageUrl());
        artistRepository.save(artist);
        return new ArtistDTO(artist.getId(), artist.getName(), artist.getDescription(), artist.getImageUrl());
    }

    @Transactional
    public void deleteArtist(Long id) {
        Artist artist = artistRepository.getArtistById(id);
        artistRepository.delete(artist);
    }


}
