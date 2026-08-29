package snufkin.harp.dto;

public class TrackDTO {
    private Long id;
    private String title;
    private Integer trackNumber;
    private Integer durationSeconds;
    private Long albumId;

    // Constructors
    public TrackDTO() {
    }

    public TrackDTO(Long id, String title, Integer trackNumber, Integer durationSeconds, Long albumId) {
        this.id = id;
        this.title = title;
        this.trackNumber = trackNumber;
        this.durationSeconds = durationSeconds;
        this.albumId = albumId;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getTrackNumber() {
        return trackNumber;
    }

    public void setTrackNumber(Integer trackNumber) {
        this.trackNumber = trackNumber;
    }

    public Integer getDurationSeconds() {
        return durationSeconds;
    }

    public void setDurationSeconds(Integer durationSeconds) {
        this.durationSeconds = durationSeconds;
    }

    public Long getAlbumId() {
        return albumId;
    }

}
