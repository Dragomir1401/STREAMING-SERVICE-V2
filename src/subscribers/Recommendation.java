package subscribers;

import input.Input;
import input.MovieInput;
import input.NotificationInput;
import input.UserInput;
import momentary.PageNow;
import output.CommandOutput;
import output.Output;
import sorters.RecommendationSort;
import sorters.SortByLikes;
import sorters.SortRecommendationsByLikes;

import java.util.ArrayList;
import java.util.List;

import static constants.Constants.*;

public class Recommendation {
    public static void generateRecommendations(Input input, PageNow pageNow, Output output) {
        if (pageNow.getUser().getUser().getCredentials().getAccountType().equals(PREMIUM))
            generateRecommendation(pageNow.getUser().getUser(), input, output);
    }

    private static void generateRecommendation(UserInput user, Input input, Output output) {
        List<RecommendationElement> likedGenres = new ArrayList<>();

        // make a top of liked genres for the user
        for (MovieInput movie : user.getLikedMovies()) {
            for (String genre : movie.getGenres()) {
                RecommendationElement recommendationElement = findRecommendation(likedGenres, genre);
                if (recommendationElement == null) {
                    RecommendationElement newElement = new RecommendationElement(genre);
                    likedGenres.add(newElement);
                } else {
                    recommendationElement.increaseLikeCount();
                }
            }
        }

        // sort recommendations decreasingly
        SortRecommendationsByLikes sort = new SortRecommendationsByLikes();
        sort.run(likedGenres, DECREASING);

        List<MovieInput> databaseMovies = new ArrayList<>(input.getMovies());

        // sort database movies decreasingly
        SortByLikes sortByLikes = new SortByLikes();
        sortByLikes.run(databaseMovies, DECREASING);

        boolean doneRecommending = false;
        for (RecommendationElement recommendationElement : likedGenres) {
            for (MovieInput movie : databaseMovies) {
                if (!user.getWatchedMovies().contains(movie)
                        && movie.getGenres().contains(recommendationElement.getGenre()) && !doneRecommending) {
                    user.getNotifications().add(new NotificationInput(movie.getName(), RECOMMENDATION));
                    output.getOutput().add(new CommandOutput(user));
                    doneRecommending = true;
                }
            }
        }

        if (!doneRecommending) {
            user.getNotifications().add(new NotificationInput(NO_RECOMMENDATION, RECOMMENDATION));
            output.getOutput().add(new CommandOutput(user));
        }
    }

    private static RecommendationElement findRecommendation(List<RecommendationElement> likedGenres, String genre) {
        for (RecommendationElement recommendationElement : likedGenres) {
            if (recommendationElement.getGenre().equals(genre)) {
                return recommendationElement;
            }
        }
        return null;
    }
}
