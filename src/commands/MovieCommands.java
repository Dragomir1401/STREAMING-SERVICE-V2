package commands;

import filters.FilterByCountry;
import input.ActionInput;
import input.Input;
import input.MovieInput;
import input.UserInput;
import momentary.PageNow;
import output.CommandOutput;
import output.Output;
import java.text.DecimalFormat;
import java.util.List;

import static constants.Constants.UPGRADES;
import static constants.Constants.SEE_DETAILS;
import static constants.Constants.PREMIUM;
import static constants.Constants.STANDARD;
import static constants.Constants.RATE;
import static constants.Constants.MAX_RATE;
import static constants.Constants.LIKE;

public class MovieCommands {
    private static MovieCommands instance;

    /**
     * singleton getter for instance
     * @return - singleton instance with lazy instantiation
     */
    public static MovieCommands getInstance() {
        if (instance == null) {
            instance = new MovieCommands();
        }

        return instance;
    }

    /**
     * finds instance of a movie in list
     * @param movies - list of movies
     * @param movieName - name of the movie
     * @return - found instance
     */
    public MovieInput findMovieInstance(final List<MovieInput> movies, final String movieName) {
        for (MovieInput movie : movies) {
            if (movie.getName().equals(movieName)) {
                return movie;
            }
        }
        return null;
    }

    /**
     * finds index of the movie
     * @param movies - list of movies where we search
     * @param movieName - name of the movie
     * @return - the found index
     */
    public int findMovieIndex(final List<MovieInput> movies, final String movieName) {
        int index = 0;
        for (MovieInput movie : movies) {
            if (movie.getName().equals(movieName)) {
                return index;
            }
            index++;
        }
        return -1;
    }

    /**
     * find movie details and writes to output
     * @param movieInput - movie input
     * @param output - output structure
     * @param user - given user
     * @param pageNow - current page
     */
    public void getMovieDetails(final MovieInput movieInput, final Output output,
                                final UserInput user, final PageNow pageNow) {

        if (movieInput != null && pageNow.getUserCommands().userCanSeeMovie(user, movieInput)) {
            output.getOutput().add(new CommandOutput(new MovieInput(movieInput), user));
            return;
        }

        output.getOutput().add(new CommandOutput());
    }

    /**
     * purchase a movie action
     * @param input - input structure
     * @param output - output structure
     * @param pageNow - current page
     * @param actionInput - action input
     */
    public void purchaseMovie(final Input input, final Output output, final PageNow pageNow,
                              final ActionInput actionInput) {
        // check to see if user has already purchased movie
        for (MovieInput movieInput : pageNow.getUser().getUser().getPurchasedMovies()) {
            if (movieInput.getName().equals(pageNow.getMovie().getName())) {
                output.getOutput().add(new CommandOutput());
                return;
            }
        }

        // initialise filter
        FilterByCountry filterByCountry = new FilterByCountry();

        // get permitted movies in that country
        List<MovieInput> permittedMovies = filterByCountry.filter(input.getMovies(),
                pageNow.getUser().getUser());

        // find movie instance
        MovieInput movie = null;
        if (pageNow.getName().equals(UPGRADES)) {
            movie = findMovieInstance(permittedMovies, actionInput.getMovie());
        } else if (pageNow.getName().equals(SEE_DETAILS)) {
            movie = findMovieInstance(permittedMovies, pageNow.getMovie().getName());
        }

        if (movie == null) {
            // movie does not exist or is not visible for the user country
            output.getOutput().add(new CommandOutput());
            return;
        }

        // case for premium account take from free movies
        if (pageNow.getUser().getUser().getCredentials().getAccountType().equals(PREMIUM)) {
            // check to see if user has free movies left
            if (pageNow.getUser().getUser().getNumFreePremiumMovies() > 0) {
                pageNow.getUser().getUser().setNumFreePremiumMovies(
                        pageNow.getUser().getUser().getNumFreePremiumMovies() - 1);

                // add movie to purchased list
                pageNow.getUser().getUser().getPurchasedMovies().add(new MovieInput(movie));

                // create output
                output.getOutput().add(new CommandOutput(new MovieInput(movie),
                        pageNow.getUser().getUser()));
                return;
            }

            // else purchase as normal user
            purchaseMovieStandardAccount(pageNow, output, movie);
            return;
        }
        // case for standard user
        if (pageNow.getUser().getUser().getCredentials().getAccountType().equals(STANDARD)) {
            purchaseMovieStandardAccount(pageNow, output, movie);
        }
    }

    /**
     * purchase movie case for standard account
     * @param pageNow - current page
     * @param output - output structure
     * @param movie - movie to be purchased
     */
    public void purchaseMovieStandardAccount(final PageNow pageNow, final Output output,
                                             final MovieInput movie) {
        // case for standard account buy with tokens
        if (pageNow.getUser().getUser().getTokensCount() < 2) {
            // not enough tokens
            output.getOutput().add(new CommandOutput());
            return;
        }
        // decrease tokens
        pageNow.getUser().getUser().setTokensCount(
                pageNow.getUser().getUser().getTokensCount() - 2);
        // add movie to purchased list
        pageNow.getUser().getUser().getPurchasedMovies().add(new MovieInput(movie));
        // create output
        output.getOutput().add(new CommandOutput(new MovieInput(movie),
                pageNow.getUser().getUser()));
    }

    /**
     * watch a movie action
     * @param pageNow - current page
     * @param output - output structure
     * @param actionInput - input structure
     */
    public void watchMovie(final PageNow pageNow, final Output output,
                           final ActionInput actionInput) {
        // check to see if movie is in purchased movies
        MovieInput movieInstance = null;
        if (pageNow.getName().equals(UPGRADES)) {
            movieInstance = findMovieInstance(pageNow.getUser().getUser().getPurchasedMovies(),
                    actionInput.getMovie());
        } else if (pageNow.getName().equals(SEE_DETAILS)) {
            movieInstance = findMovieInstance(pageNow.getUser().getUser().getPurchasedMovies(),
                    pageNow.getMovie().getName());
        }

        if (movieInstance == null) {
            output.getOutput().add(new CommandOutput());
            return;
        }
        MovieInput movie = new MovieInput(movieInstance);

        // watch movie action only adds when movie is not already in watched list
        boolean movieAlreadyWatched = false;
        for (MovieInput movieInput : pageNow.getUser().getUser().getWatchedMovies()) {
            if (movieInput.getName().equals(movie.getName())) {
                movieAlreadyWatched = true;
                break;
            }
        }

        if (!movieAlreadyWatched) {
            pageNow.getUser().getUser().getWatchedMovies().add(new MovieInput(movie));
        } else {
            return;
        }

        // create output
        output.getOutput().add(new CommandOutput(new MovieInput(movie),
                pageNow.getUser().getUser()));
    }

    /**
     * like a movie action
     * @param input - input structure
     * @param pageNow - current page
     * @param output - output structure
     * @param actionInput - action input
     */
    public void likeMovie(final Input input, final PageNow pageNow, final Output output,
                          final ActionInput actionInput) {
        // check to see if movie is in watched movies
        MovieInput movieInstance = findMovieInstanceForEdgeCase(pageNow, actionInput);
        if (movieInstance == null) {
            output.getOutput().add(new CommandOutput());
            return;
        }
        MovieInput movie = new MovieInput(movieInstance);

        // like movie action
        movie.increaseLikes();

        // add movie to liked list
        pageNow.getUser().getUser().getLikedMovies().add(new MovieInput(movie));

        // modify movie in all its appearances
        modifyAppearances(input, pageNow, movie, RATE);

        // create output
        output.getOutput().add(new CommandOutput(new MovieInput(movie),
                pageNow.getUser().getUser()));
    }

    /**
     * rate a movie action
     * @param input - input structure
     * @param pageNow - current page
     * @param output - output structure
     * @param actionInput - action input
     */
    public void rateMovie(final Input input, final PageNow pageNow, final Output output,
                          final ActionInput actionInput) {
        // check to see if movie is in watched movies
        MovieInput movieInstance = findMovieInstanceForEdgeCase(pageNow, actionInput);
        if (movieInstance == null || actionInput.getRate() > MAX_RATE
                || actionInput.getRate() < 1) {
            output.getOutput().add(new CommandOutput());
            return;
        }
        MovieInput movie = new MovieInput(movieInstance);

        boolean movieAlreadyRated = false;

        // check to see if user already liked this movie and wants to change rating
        for (MovieInput movieInput : pageNow.getUser().getUser().getRatedMovies()) {
            if (movieInput.getName().equals(movie.getName())) {
                movieAlreadyRated = true;
                break;
            }
        }

        // keep user rating in user rated movie class
        movie.setRating(actionInput.getRate());

        double rating;

        // add movie to rated movies for user if not rated already
        if (!movieAlreadyRated) {
            movie.increaseNumRatings();
            pageNow.getUser().getUser().getRatedMovies().add(new MovieInput(movie));
        }

        // create rating for movie

        double sum = 0.00;
        for (UserInput userInput : input.getUsers()) {
            // find rated movie for user
            MovieInput ratedMovie = findMovieInstance(userInput.getRatedMovies(),
                    movie.getName());

            if (movieAlreadyRated) {
                sum -= ratedMovie.getRating() / movie.getNumRatings();
                movieAlreadyRated = false;
            }

            if (userInput.getCredentials().getName().equals(
                    pageNow.getUser().getUser().getCredentials().getName())) {
                sum += actionInput.getRate();
            } else if (ratedMovie != null) {
                sum += ratedMovie.getRating();
            }
        }


        // calculate rating
        rating = sum / (double) movie.getNumRatings();

        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        double rating1 = Double.parseDouble(decimalFormat.format(rating));

        // set rating
        movie.setRating(rating1);

        // modify all appearances
        modifyAppearances(input, pageNow, movie, LIKE);

        // create output
        output.getOutput().add(new CommandOutput(new MovieInput(movie),
                pageNow.getUser().getUser()));
    }

    /**
     * modifies all appereances of movies in liked, watched, rated, etc
     * @param input - input given
     * @param pageNow - current page
     * @param movie - given movie
     * @param rateOrLike - rate or like selector
     */
    public void modifyAppearances(final Input input, final PageNow pageNow, final MovieInput movie,
                                  final String rateOrLike) {
        // modify movie in all its appearances
        int index = findMovieIndex(pageNow.getUser().getUser().getPurchasedMovies(),
                movie.getName());
        if (index >= 0) {
            pageNow.getUser().getUser().getPurchasedMovies().set(index, new MovieInput(movie));
        }

        index = findMovieIndex(pageNow.getUser().getUser().getWatchedMovies(), movie.getName());
        if (index >= 0) {
            pageNow.getUser().getUser().getWatchedMovies().set(index, new MovieInput(movie));
        }

        if (rateOrLike.equals(LIKE)) {
            index = findMovieIndex(pageNow.getUser().getUser().getLikedMovies(), movie.getName());
            if (index >= 0) {
                pageNow.getUser().getUser().getLikedMovies().set(index, new MovieInput(movie));
            }

            index = findMovieIndex(pageNow.getUser().getUser().getRatedMovies(), movie.getName());
            if (index >= 0) {
                pageNow.getUser().getUser().getRatedMovies().set(index, new MovieInput(movie));
            }

        } else if (rateOrLike.equals(RATE)) {
            index = findMovieIndex(pageNow.getUser().getUser().getRatedMovies(), movie.getName());
            if (index >= 0) {
                pageNow.getUser().getUser().getRatedMovies().set(index, new MovieInput(movie));
            }
        }

        index = findMovieIndex(pageNow.getMovieList(), movie.getName());
        if (index >= 0) {
            pageNow.getMovieList().set(index, new MovieInput(movie));
        }
        index = findMovieIndex(input.getMovies(), movie.getName());
        if (index >= 0) {
            input.getMovies().set(index, new MovieInput(movie));
        }
    }

    /**
     * finds movie instance in current page list of movies depending on where the name is given
     * @param pageNow - current page
     * @param actionInput - action input
     * @return - list of movies
     */
    public MovieInput findMovieInstanceForEdgeCase(final PageNow pageNow,
                                                   final ActionInput actionInput) {
        MovieInput movieInstance = null;
        if (pageNow.getName().equals(UPGRADES)) {
            movieInstance = findMovieInstance(pageNow.getUser().getUser().getWatchedMovies(),
                    actionInput.getMovie());
        } else if (pageNow.getName().equals(SEE_DETAILS)) {
            movieInstance = findMovieInstance(pageNow.getUser().getUser().getWatchedMovies(),
                    pageNow.getMovie().getName());
        }
        return movieInstance;
    }
}
