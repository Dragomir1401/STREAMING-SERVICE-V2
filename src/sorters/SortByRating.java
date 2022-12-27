package sorters;

import input.MovieInput;
import java.util.Comparator;
import java.util.List;

import static constants.Constants.DECREASING;
import static constants.Constants.INCREASING;

public class SortByRating implements SortMovies {
    /**
     * sorts by rating
     * @param movies  movies list
     * @param parameter  increasing/decreasing
     */
    @Override
    public void run(final List<MovieInput> movies, final String parameter) {

        if (parameter.equals(INCREASING)) {
            movies.sort(Comparator.comparingDouble(MovieInput::getRating));
            return;
        }
        if (parameter.equals(DECREASING)) {
            movies.sort(Comparator.comparingDouble(MovieInput::getRating).reversed());
        }
    }


    /**
     * sorts by duration and for equal cases by rating
     * @param movies  movie list
     * @param parameter  increasing/decreasing
     */
    public void runForEqualCases(final List<MovieInput> movies, final String parameter) {
        if (parameter.equals(INCREASING)) {
            movies.sort((o1, o2) -> {
                int cmp = o1.getDuration() - o2.getDuration();
                if (cmp != 0) {
                    return -cmp;
                } else {
                    cmp = Double.compare(o1.getRating(), o2.getRating());
                }
                return cmp;
            });
            return;
        }

        if (parameter.equals(DECREASING)) {
            movies.sort((o1, o2) -> {
                int cmp = o1.getDuration() - o2.getDuration();
                if (cmp != 0) {
                    return -cmp;
                } else {
                    cmp = Double.compare(o1.getRating(), o2.getRating());
                }
                return -cmp;
            });
        }
    }
}
