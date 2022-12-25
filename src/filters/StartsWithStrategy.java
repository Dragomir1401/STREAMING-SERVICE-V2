package filters;

import input.MovieInput;

import java.util.ArrayList;
import java.util.List;

public class StartsWithStrategy implements Strategy {

    /**
     * criteria of starting with a given string
     * @param movies - movie list
     * @param content - content it has to contain
     * @return - list of movies resulted
     */
    @Override
    public List<MovieInput> meetCriteria(final List<MovieInput> movies,
                                         final List<String> content) {
        List<MovieInput> moviesStartWith = new ArrayList<>();

        // content[0] contains startsWith parameter
        for (MovieInput movie : movies) {
            if (movie.getName().startsWith(content.get(0))) {
                moviesStartWith.add(movie);
            }
        }
        return moviesStartWith;
    }
}
