package snufkin.harp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import snufkin.harp.dto.AlbumDTO;
import snufkin.harp.model.Album;
import snufkin.harp.repository.AlbumRepository;
import snufkin.harp.repository.ArtistRepository;
import snufkin.harp.request.CreateAlbumRequest;
import snufkin.harp.request.UpdateAlbumRequest;

@Service
public class AlbumService {
    
    private final AlbumRepository albumRepository;
    private final ArtistRepository artistRepository;

    public AlbumService(AlbumRepository albumRepository, ArtistRepository artistRepository) {
        this.albumRepository = albumRepository;
        this.artistRepository = artistRepository;
    }

    @Transactional
    public AlbumDTO createAlbum(CreateAlbumRequest albumRequest) {
        Album album = new Album();
        album.setTitle(albumRequest.getTitle());
        album.setDescription(albumRequest.getDescription());
        album.setImageUrl(albumRequest.getImageUrl());
        album.setArtist(artistRepository.getArtistById(albumRequest.getArtistId()));
        albumRepository.save(album);
        return new AlbumDTO(album.getId(), album.getTitle(), album.getDescription(), album.getImageUrl(), album.getArtist().getId());
    }


    public AlbumDTO getAlbumById(Long id) {
        Album album = albumRepository.getAlbumById(id);
        return new AlbumDTO(album.getId(), album.getTitle(), album.getDescription(), album.getImageUrl(), album.getArtist().getId());
    }

    public List<AlbumDTO> getAllAlbums() {
        return albumRepository.findAll().stream()
                .map(album -> new AlbumDTO(album.getId(), album.getTitle(), album.getDescription(), album.getImageUrl(), album.getArtist().getId()))
                .toList();
    }
    
    @Transactional
    public AlbumDTO updateAlbum(Long id, UpdateAlbumRequest albumRequest) {
        Album album = albumRepository.getAlbumById(id);
        album.setTitle(albumRequest.getTitle());
        album.setDescription(albumRequest.getDescription());
        album.setImageUrl(albumRequest.getImageUrl());
        albumRepository.save(album);
        return new AlbumDTO(album.getId(), album.getTitle(), album.getDescription(), album.getImageUrl(), album.getArtist().getId());
    }

    @Transactional
    public void deleteAlbum(Long id) {
        Album album = albumRepository.getAlbumById(id);
        albumRepository.delete(album);
    }

}
