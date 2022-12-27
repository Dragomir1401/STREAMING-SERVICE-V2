package output;

import input.MovieInput;
import input.UserInput;

import java.util.ArrayList;
import java.util.List;

public class CommandOutput {
    private String error;
    private List<MovieInput> moviesList;
    private UserInput user;

    public CommandOutput() {
        this.error = "Error";
        this.moviesList = new ArrayList<>();
    }

    public CommandOutput(final List<MovieInput> moviesList, final UserInput user) {
        this.moviesList = new ArrayList<>();
        if (moviesList != null) {
            for (MovieInput movie : moviesList) {
                this.moviesList.add(new MovieInput(movie));
            }
        }
        this.user = new UserInput(user);
    }

    public CommandOutput(final MovieInput movie, final UserInput user) {
        this.user = new UserInput(user);
        this.moviesList = List.of(new MovieInput(movie));
    }

    public CommandOutput(final UserInput user) {
        this.user = new UserInput(user);
        this.moviesList = null;
    }


    /**
     * getter for error
     * @return  error
     */
    public String getError() {
        return error;
    }


    /**
     * setter for error
     * @param error  error
     */
    public void setError(final String error) {
        this.error = error;
    }


    /**
     * getter for current list
     * @return  list of movies
     */
    public List<MovieInput> getCurrentMoviesList() {
        return moviesList;
    }


    /**
     * setter for current movies
     * @param moviesInputList  list
     */
    public void setCurrentMoviesList(final List<MovieInput> moviesInputList) {
        this.moviesList = moviesInputList;
    }


    /**
     * getter for current user
     * @return  user
     */
    public UserInput getCurrentUser() {
        return user;
    }


    /**
     * setter for current user
     * @param userInput  user
     */
    public void setCurrentUser(final UserInput userInput) {
        this.user = userInput;
    }
}
