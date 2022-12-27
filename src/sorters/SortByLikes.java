package sorters;

import input.MovieInput;

import java.util.Comparator;
import java.util.List;

import static constants.Constants.DECREASING;
import static constants.Constants.INCREASING;

public class SortByLikes implements SortMovies {
    /**
     * sorts by duration
     *
     * @param movies    movies list
     * @param parameter increasing/decreasing
     */
    @Override
    public void run(final List<MovieInput> movies, final String parameter) {

        if (parameter.equals(INCREASING)) {
            movies.sort(Comparator.comparingInt(MovieInput::getNumLikes));
            return;
        }

        if (parameter.equals(DECREASING)) {
            movies.sort((o1, o2) -> o2.getNumLikes() - o1.getNumLikes());
        }

    }
}
