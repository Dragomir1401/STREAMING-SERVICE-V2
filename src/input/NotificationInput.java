package input;

public class NotificationInput {
    private String movieName;
    private String message;

    public NotificationInput() {

    }

    public NotificationInput(final String movieName, final String message) {
        this.movieName = movieName;
        this.message = message;
    }

    /**
     * getter for movie name
     * @return  movie name
     */
    public String getMovieName() {
        return movieName;
    }

    /**
     * setter for movie name
     * @param movieName  movie name
     */
    public void setMovieName(final String movieName) {
        this.movieName = movieName;
    }

    /**
     * getter for message
     * @return  message
     */
    public String getMessage() {
        return message;
    }

    /**
     * setter for message
     * @param message  message
     */
    public void setMessage(final String message) {
        this.message = message;
    }
}
