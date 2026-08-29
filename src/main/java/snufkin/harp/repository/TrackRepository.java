package snufkin.harp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import snufkin.harp.model.Track;

public interface TrackRepository extends JpaRepository<Track, Long> {
    
}
