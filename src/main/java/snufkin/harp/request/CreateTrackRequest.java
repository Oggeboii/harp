package snufkin.harp.request;

public class CreateTrackRequest {
    private String title;
    private Integer trackNumber;
    private Integer durationSeconds;
    private String imageUrl;
    private Long albumId;

    // Constructors
    public CreateTrackRequest() {
    }

    public CreateTrackRequest(String title, Integer trackNumber, Integer durationSeconds, String imageUrl, Long albumId) {
        this.title = title;
        this.trackNumber = trackNumber;
        this.durationSeconds = durationSeconds;
        this.imageUrl = imageUrl;
        this.albumId = albumId;
    }

    // Getters and Setters
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Long albumId) {
        this.albumId = albumId;
    }
}
