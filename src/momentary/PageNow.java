package momentary;

import commands.MovieCommands;
import commands.TokenCommands;
import commands.UserCommands;
import input.MovieInput;

import java.util.List;

public class PageNow {
    private final UserNow user;
    private String name;
    private final UserCommands userCommands;
    private final TokenCommands tokensCommands;
    private final MovieCommands moviesCommands;
    private List<MovieInput> movieList;
    private MovieInput movie;

    public PageNow(final UserNow user, final String name, final UserCommands userCommands,
                   final TokenCommands tokensCommands, final MovieCommands moviesActions,
                   final List<MovieInput> movieInputList, final MovieInput movieInput) {
        this.user = user;
        this.name = name;
        this.userCommands = userCommands;
        this.tokensCommands = tokensCommands;
        this.moviesCommands = moviesActions;
        this.movieList = movieInputList;
        this.movie = movieInput;
    }

    public PageNow(final String name) {
        this.user = UserNow.getInstance();
        this.name = name;
        this.userCommands = UserCommands.getInstance();
        this.tokensCommands = TokenCommands.getInstance();
        this.moviesCommands = MovieCommands.getInstance();
        this.movieList = null;
        this.movie = null;
    }


    /**
     * getter for user
     * @return - user
     */
    public UserNow getUser() {
        return user;
    }


    /**
     * getter for name
     * @return - name
     */
    public String getName() {
        return name;
    }


    /**
     * setter for name
     * @param name - name
     */
    public void setName(final String name) {
        this.name = name;
    }


    /**
     * getter for user commands
     * @return - user commands
     */
    public UserCommands getUserCommands() {
        return userCommands;
    }


    /**
     * getter for token commands
     * @return - token commands
     */
    public TokenCommands getTokensCommands() {
        return tokensCommands;
    }


    /**
     * getter for movies commands
     * @return - movies commands
     */
    public MovieCommands getMoviesCommands() {
        return moviesCommands;
    }


    /**
     * getter for movie list
     * @return - movie list
     */
    public List<MovieInput> getMovieList() {
        return movieList;
    }


    /**
     * setter for movies list
     * @param movieInputList - movies list
     */
    public void setMovieList(final List<MovieInput> movieInputList) {
        this.movieList = movieInputList;
    }


    /**
     * getter for movie
     * @return - movie
     */
    public MovieInput getMovie() {
        return movie;
    }


    /**
     * setter for movie
     * @param movieInput - movie
     */
    public void setMovie(final MovieInput movieInput) {
        this.movie = movieInput;
    }
}
