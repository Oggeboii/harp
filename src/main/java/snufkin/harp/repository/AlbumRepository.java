package snufkin.harp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import jakarta.persistence.EntityNotFoundException;
import snufkin.harp.model.Album;

public interface AlbumRepository extends JpaRepository<Album, Long> {
    
    default Album getAlbumById(Long id) {
        return findById(id).orElseThrow(() -> new EntityNotFoundException("Album not found with id: " + id));
    }
}
