package snufkin.harp.request;

public class CreateAlbumRequest {
    private String title;
    private String description;
    private String imageUrl;
    private Long artistId;

    // Constructors
    public CreateAlbumRequest() {
    }

    public CreateAlbumRequest(String title, String description, String imageUrl, Long artistId) {
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
        this.artistId = artistId;
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

    public Long getArtistId() {
        return artistId;
    }

    public void setArtistId(Long artistId) {
        this.artistId = artistId;
    }
}
