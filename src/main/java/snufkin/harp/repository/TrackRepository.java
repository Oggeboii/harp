package snufkin.harp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import jakarta.persistence.EntityNotFoundException;
import snufkin.harp.model.Track;

public interface TrackRepository extends JpaRepository<Track, Long> {
    
    default Track getTrackById(Long id) {
        return findById(id).orElseThrow(() -> new EntityNotFoundException("Track not found with id: " + id));
    }

}
