package input;

import java.util.ArrayList;

public class MovieInput {
    private String name;
    private int year;
    private int duration;
    private ArrayList<String> genres;
    private ArrayList<String> actors;
    private ArrayList<String> countriesBanned;

    private int numLikes;
    private Double rating;
    private int numRatings;

    public MovieInput() {
        this.rating = 0.00;
        this.numLikes = 0;
    }

    public MovieInput(final MovieInput movieInput) {
        this.name = movieInput.name;
        this.numLikes = movieInput.numLikes;
        this.rating = movieInput.rating;
        this.duration = movieInput.duration;
        this.numRatings = movieInput.numRatings;
        this.year = movieInput.year;
        this.genres = new ArrayList<>();
        this.genres.addAll(movieInput.genres);
        this.actors = new ArrayList<>();
        this.actors.addAll(movieInput.actors);
        this.countriesBanned = new ArrayList<>();
        this.countriesBanned.addAll(movieInput.countriesBanned);
    }


    /**
     * reset movie instance
     */
    public void resetMovie() {
        this.year = 0;
        this.duration = 0;
        this.name = null;
        this.numLikes = 0;
        this.numRatings = 0;
        this.rating = 0.00;
        this.genres = null;
        this.actors = null;
        this.countriesBanned = null;
    }


    /**
     * increases likes
     */
    public void increaseLikes() {
        this.numLikes++;
    }



    /**
     * increases number of ratings
     */
    public void increaseNumRatings() {
        this.numRatings++;
    }



    /**
     * getter for name
     * @return  name
     */
    public String getName() {
        return name;
    }



    /**
     * setter for name
     * @param name  name
     */
    public void setName(final String name) {
        this.name = name;
    }


    /**
     * getter for year
     * @return  year
     */
    public int getYear() {
        return year;
    }



    /**
     * setter for year
     * @param year  year
     */
    public void setYear(final int year) {
        this.year = year;
    }


    /**
     * getter for duration
     * @return  duration
     */
    public int getDuration() {
        return duration;
    }



    /**
     * setter for duration
     * @param duration  duration
     */
    public void setDuration(final int duration) {
        this.duration = duration;
    }


    /**
     * getter for genres
     * @return  genres
     */
    public ArrayList<String> getGenres() {
        return genres;
    }



    /**
     * setter for genres
     * @param genres  genres
     */
    public void setGenres(final ArrayList<String> genres) {
        this.genres = genres;
    }


    /**
     * getter for actors
     * @return  actors
     */
    public ArrayList<String> getActors() {
        return actors;
    }



    /**
     * setter for actors
     * @param actors  actors
     */
    public void setActors(final ArrayList<String> actors) {
        this.actors = actors;
    }


    /**
     * getter for countries banned
     * @return  countries banned
     */
    public ArrayList<String> getCountriesBanned() {
        return countriesBanned;
    }



    /**
     * setter for countries banned
     * @param countriesBanned  countries
     */
    public void setCountriesBanned(final ArrayList<String> countriesBanned) {
        this.countriesBanned = countriesBanned;
    }


    /**
     * getter for num likes
     * @return  num likes
     */
    public int getNumLikes() {
        return numLikes;
    }



    /**
     * setter for num likes
     * @param numLikes  num likes
     */
    public void setNumLikes(final int numLikes) {
        this.numLikes = numLikes;
    }


    /**
     * getter for rating
     * @return  rating
     */
    public double getRating() {
        return rating;
    }



    /**
     * setter for rating
     * @param rating - rating
     */
    public void setRating(final double rating) {
        this.rating = rating;
    }


    /**
     * getter for num ratings
     * @return - num ratings
     */
    public int getNumRatings() {
        return numRatings;
    }


    /**
     * setter for num of ratings
     * @param numRatings - num
     */
    public void setNumRatings(final int numRatings) {
        this.numRatings = numRatings;
    }

}
