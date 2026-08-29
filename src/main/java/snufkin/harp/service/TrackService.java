package snufkin.harp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import snufkin.harp.dto.TrackDTO;
import snufkin.harp.model.Track;
import snufkin.harp.repository.AlbumRepository;
import snufkin.harp.repository.TrackRepository;
import snufkin.harp.request.CreateTrackRequest;
import snufkin.harp.request.UpdateTrackRequest;

@Service
public class TrackService {
    
    private final AlbumRepository albumRepository;
    private final TrackRepository trackRepository;

    public TrackService(TrackRepository trackRepository, AlbumRepository albumRepository) {
        this.trackRepository = trackRepository;
        this.albumRepository = albumRepository;
    }

    @Transactional
    public TrackDTO createTrack(CreateTrackRequest trackRequest) {
        Track track = new Track();
        track.setTitle(trackRequest.getTitle());
        track.setTrackNumber(trackRequest.getTrackNumber());
        track.setDurationSeconds(trackRequest.getDurationSeconds());
        track.setAlbum(albumRepository.getAlbumById(trackRequest.getAlbumId()));
        trackRepository.save(track);
        return new TrackDTO(track.getId(), track.getTitle(), track.getTrackNumber(), track.getDurationSeconds(), track.getAlbum().getId());
    }

    public TrackDTO getTrackById(Long id) {
        Track track = trackRepository.getTrackById(id);
        return new TrackDTO(track.getId(), track.getTitle(), track.getTrackNumber(), track.getDurationSeconds(), track.getAlbum().getId());
    }

    public List<TrackDTO> getAllTracks() {
        return trackRepository.findAll().stream()
                .map(track -> new TrackDTO(track.getId(), track.getTitle(), track.getTrackNumber(), track.getDurationSeconds(), track.getAlbum().getId()))
                .toList();
    }

    @Transactional
    public TrackDTO updateTrack(Long id, UpdateTrackRequest trackRequest) {
        Track track = trackRepository.getTrackById(id);
        track.setTitle(trackRequest.getTitle());
        track.setTrackNumber(trackRequest.getTrackNumber());
        track.setDurationSeconds(trackRequest.getDurationSeconds());
        track.setAlbum(albumRepository.getAlbumById(trackRequest.getAlbumId()));
        trackRepository.save(track);
        return new TrackDTO(track.getId(), track.getTitle(), track.getTrackNumber(), track.getDurationSeconds(), track.getAlbum().getId());
    }

    @Transactional
    public void deleteTrack(Long id) {
        Track track = trackRepository.getTrackById(id);
        trackRepository.delete(track);
    }


}
