package filters;

import input.MovieInput;

import java.util.List;

public interface Strategy {
    /**
     * checks criteria for Filter design pattern
     * @param movies  movie list
     * @param content - content it has to contain
     * @return - list of filtered by criteria
     */
    List<MovieInput> meetCriteria(List<MovieInput> movies, List<String> content);
}
