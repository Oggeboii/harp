package snufkin.harp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import jakarta.persistence.EntityNotFoundException;
import snufkin.harp.model.Artist;

public interface ArtistRepository extends JpaRepository<Artist, Long> {
    
    default Artist getArtistById(Long id) {
        return findById(id).orElseThrow(() -> new EntityNotFoundException("Artist not found with id: " + id));
    }
}
