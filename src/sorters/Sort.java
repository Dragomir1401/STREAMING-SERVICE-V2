package sorters;

import input.MovieInput;

import java.util.List;

public interface Sort {
    /**
     * runs sorters
     * @param movies - movies list
     * @param parameter - increasing/decreasing
     */
    void run(List<MovieInput> movies, String parameter);
}
