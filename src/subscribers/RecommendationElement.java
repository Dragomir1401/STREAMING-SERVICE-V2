package subscribers;

public class RecommendationElement {
    private int likeCount;
    private String genre;

    public RecommendationElement(final String genre) {
        this.likeCount = 0;
        this.genre = genre;
    }


    /**
     * increases like count for genre
     */
    public void increaseLikeCount() {
        likeCount++;
    }


    /**
     * getter for like count
     * @return  like count
     */
    public int getLikeCount() {
        return likeCount;
    }


    /**
     * getter for genre
     * @return  genre
     */
    public String getGenre() {
        return genre;
    }


    /**
     * setter for genre
     * @param genre  genre
     */
    public void setGenre(final String genre) {
        this.genre = genre;
    }
}
