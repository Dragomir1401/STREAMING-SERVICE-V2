package input;

public class ActionInput {
    private String type;
    private String page;
    private String feature;
    private CredentialsInput credentials;
    private String startsWith;
    private FilterInput filters;
    private int rate;
    private String movie;
    private  int count;
    private String subscribedGenre;
    private MovieInput addedMovie;
    private String deletedMovie;

    /**
     * getter for type
     * @return  type
     */
    public String getType() {
        return type;
    }



    /**
     * setter for type
     * @param type  type
     */
    public void setType(final String type) {
        this.type = type;
    }



    /**
     * getter for page
     * @return  page
     */
    public String getPage() {
        return page;
    }



    /**
     * setter for page
     * @param page  page
     */
    public void setPage(final String page) {
        this.page = page;
    }



    /**
     * getter for feature
     * @return  feature
     */
    public String getFeature() {
        return feature;
    }



    /**
     * setter for feature
     * @param feature feature
     */
    public void setFeature(final String feature) {
        this.feature = feature;
    }



    /**
     * getter for credentials
     * @return  credentials
     */
    public CredentialsInput getCredentials() {
        return credentials;
    }



    /**
     * setter for credentials
     * @param credentials  credentials
     */
    public void setCredentials(final CredentialsInput credentials) {
        this.credentials = credentials;
    }



    /**
     * getter for starts with
     * @return  starts with
     */
    public String getStartsWith() {
        return startsWith;
    }



    /**
     * getter for filters
     * @return  filters
     */
    public FilterInput getFilters() {
        return filters;
    }



    /**
     * setter for filters
     * @param filters  filters
     */
    public void setFilters(final FilterInput filters) {
        this.filters = filters;
    }



    /**
     * getter for rate
     * @return  rate
     */
    public int getRate() {
        return rate;
    }



    /**
     * getter for movie
     * @return  movie
     */
    public String getMovie() {
        return movie;
    }



    /**
     * setter for movie
     * @param movie  movie
     */
    public void setMovie(final String movie) {
        this.movie = movie;
    }



    /**
     * getter for count
     * @return  count
     */
    public int getCount() {
        return count;
    }

    /**
     * getter for subscribed genre
     * @return  genre
     */
    public String getSubscribedGenre() {
        return subscribedGenre;
    }


    /**
     * setter for subscribed genre
     * @param subscribedGenre  genre
     */
    public void setSubscribedGenre(String subscribedGenre) {
        this.subscribedGenre = subscribedGenre;
    }

    /**
     * getter for added movie
     * @return  added movie
     */
    public MovieInput getAddedMovie() {
        return addedMovie;
    }


    /**
     * setter for added movie
     * @param addedMovie  added movie
     */
    public void setAddedMovie(MovieInput addedMovie) {
        this.addedMovie = addedMovie;
    }

    /**
     * getter for deleted movie
     * @return  deleted movie
     */
    public String getDeletedMovie() {
        return deletedMovie;
    }


    /**
     * setter for deleted movie
     * @param deletedMovie  deleted movie
     */
    public void setDeletedMovie(String deletedMovie) {
        this.deletedMovie = deletedMovie;
    }
}
