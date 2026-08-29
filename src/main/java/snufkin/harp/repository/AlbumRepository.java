package snufkin.harp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import snufkin.harp.model.Album;

public interface AlbumRepository extends JpaRepository<Album, Long> {
    
}
