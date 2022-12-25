package sorters;

import input.MovieInput;

import java.util.Comparator;
import java.util.List;

public class SortByDuration implements Sort {
    /**
     * sorts by duration
     * @param movies - movies list
     * @param parameter - increasing/decreasing
     */
    @Override
    public void run(final List<MovieInput> movies, final String parameter) {

        if (parameter.equals("increasing")) {
            movies.sort(Comparator.comparingInt(MovieInput::getDuration));
            return;
        }
        if (parameter.equals("decreasing")) {
            movies.sort(Comparator.comparingInt(MovieInput::getDuration).reversed());
        }
    }
}
