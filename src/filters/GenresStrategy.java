package filters;

import input.MovieInput;

import java.util.ArrayList;
import java.util.List;

public class GenresStrategy implements Strategy {
    /**
     * criteria of containing the given genres
     * @param movies - movie list
     * @param content - content it has to contain
     * @return - resulted movie list
     */
    @Override
    public List<MovieInput> meetCriteria(final List<MovieInput> movies,
                                         final List<String> content) {
        List<MovieInput> moviesWithGenres = new ArrayList<>();
        for (MovieInput movie : movies) {
            boolean containsGenres = true;

            for (String genre : content) {
                if (!movie.getGenres().contains(genre)) {
                    containsGenres = false;
                    break;
                }
            }

            if (containsGenres) {
                moviesWithGenres.add(new MovieInput(movie));
            }
        }
        return moviesWithGenres;
    }
}
