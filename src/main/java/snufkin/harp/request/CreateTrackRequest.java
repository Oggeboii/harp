package snufkin.harp.request;

public class CreateTrackRequest {
    private String title;
    private String description;
    private String imageUrl;
    private Long albumId;

    // Constructors
    public CreateTrackRequest() {
    }

    public CreateTrackRequest(String title, String description, String imageUrl, Long albumId) {
        this.title = title;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
