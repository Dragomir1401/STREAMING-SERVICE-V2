package subscribers;

import input.Input;
import input.MovieInput;
import input.NotificationInput;
import input.UserInput;
import momentary.PageNow;
import output.CommandOutput;
import output.Output;
import sorters.SortByLikes;
import sorters.SortRecommendationsByLikes;
import java.util.ArrayList;
import java.util.List;
import static constants.Constants.PREMIUM;
import static constants.Constants.DECREASING;
import static constants.Constants.RECOMMENDATION;
import static constants.Constants.NO_RECOMMENDATION;


public final class Recommendation {

    private Recommendation() {

    }


    /**
     * generates recommendation for current user
     * @param input  input structure
     * @param pageNow  current page
     * @param output  output structure
     */
    public static void generateRecommendations(final Input input, final PageNow pageNow,
                                               final Output output) {
        if (pageNow.getUser().getUser().getCredentials().getAccountType().equals(PREMIUM)) {
            generateRecommendation(pageNow.getUser().getUser(), input, output);
        }
    }


    /**
     * generates recommendation for given user
     * @param input  input structure
     * @param user  given user
     * @param output  output structure
     */
    private static void generateRecommendation(final UserInput user, final Input input,
                                               final Output output) {

        // create criteria for notification
        List<RecommendationElement> likedGenres = new ArrayList<>();
        List<MovieInput> databaseMovies = createTop(user, likedGenres, input);


        // add recommendation notification
        boolean doneRecommending = false;
        for (RecommendationElement recommendationElement : likedGenres) {
            for (MovieInput movie : databaseMovies) {
                if (!containsMovie(user.getWatchedMovies(), movie)
                        && movie.getGenres().contains(recommendationElement.getGenre())
                        && !doneRecommending) {

                    user.getNotifications().add(new NotificationInput(movie.getName(),
                            RECOMMENDATION));
                    output.getOutput().add(new CommandOutput(user));
                    doneRecommending = true;
                }
            }
        }


        // case for no recommendation
        if (!doneRecommending) {
            user.getNotifications().add(new NotificationInput(NO_RECOMMENDATION, RECOMMENDATION));
            output.getOutput().add(new CommandOutput(user));
        }
    }


    /**
     * finds recommendation structure
     * @param likedGenres  user top liked genres
     * @param genre  genre
     * @return  recommendation
     */
    private static RecommendationElement findRecommendation(
            final List<RecommendationElement> likedGenres, final String genre) {

        for (RecommendationElement recommendationElement : likedGenres) {
            if (recommendationElement.getGenre().equals(genre)) {
                return recommendationElement;
            }
        }

        return null;
    }


    /**
     * checks if list contains given movie
     * @param list  list of movies
     * @param movie  movie
     * @return  true/false
     */
    private static boolean containsMovie(final List<MovieInput> list, final MovieInput movie) {
        for (MovieInput movieInput : list) {
            if (movieInput.getName().equals(movie.getName())) {
                return true;
            }
        }
        return false;
    }


    /**
     * creates a top of like movies and liked genres
     * @param user  given user
     * @param likedGenres  genres like by user
     * @param input  input structure
     * @return  movies sorted by preference
     */
    private static List<MovieInput> createTop(final UserInput user,
                                              final List<RecommendationElement> likedGenres,
                                              final Input input) {

        // make a top of liked genres for the user
        for (MovieInput movie : user.getLikedMovies()) {
            for (String genre : movie.getGenres()) {
                RecommendationElement recommendationElement = findRecommendation(likedGenres,
                        genre);

                if (recommendationElement == null) {
                    RecommendationElement newElement = new RecommendationElement(genre);
                    newElement.increaseLikeCount();
                    likedGenres.add(newElement);
                } else {
                    recommendationElement.increaseLikeCount();
                }
            }
        }


        // sort recommendations decreasingly
        SortRecommendationsByLikes sort = new SortRecommendationsByLikes();
        sort.run(likedGenres, DECREASING);


        // see if database is uniform in number of likes
        List<MovieInput> databaseMovies = new ArrayList<>(input.getMovies());
        boolean sameNumLikes = true;
        for (int i = 0; i < databaseMovies.size() - 1; i++) {
            for (int j = i + 1; j < databaseMovies.size(); j++) {
                if (databaseMovies.get(i).getNumLikes() != databaseMovies.get(j).getNumLikes()) {
                    sameNumLikes = false;
                    break;
                }
            }
        }


        // sort database movies decreasingly
        if (!sameNumLikes) {
            SortByLikes sortByLikes = new SortByLikes();
            sortByLikes.run(databaseMovies, DECREASING);
        }


        return databaseMovies;
    }
}
