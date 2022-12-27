package subscribers;

public class RecommendationElement {
    private int likeCount;
    private String genre;

    public RecommendationElement(String genre) {
        this.likeCount = 0;
        this.genre = genre;
    }

    public void increaseLikeCount() {
        likeCount++;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int rate) {
        this.likeCount = rate;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
