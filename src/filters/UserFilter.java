package filters;

import input.MovieInput;
import input.UserInput;

import java.util.List;

public interface UserFilter {
    /**
     * user filter interface
     * @param movies  movie list
     * @param user - user input
     * @return - resulted filtered movie list
     */
    List<MovieInput> filter(List<MovieInput> movies, UserInput user);
}
