package filters;

import input.MovieInput;

import java.util.ArrayList;
import java.util.List;

public class ActorsStrategy implements Strategy {
    /**
     * criteria of having given actors in movie
     * @param movies - movie list
     * @param content - content it has to contain
     * @return - movie list
     */
    @Override
    public List<MovieInput> meetCriteria(final List<MovieInput> movies,
                                         final List<String> content) {
        List<MovieInput> moviesWithActors = new ArrayList<>();

        for (MovieInput movie : movies) {
            boolean containsActors = true;

            for (String actor : content) {
                if (!movie.getActors().contains(actor)) {
                    containsActors = false;
                    break;
                }
            }
            if (containsActors) {
                moviesWithActors.add(new MovieInput(movie));
            }
        }
        return moviesWithActors;
    }
}
