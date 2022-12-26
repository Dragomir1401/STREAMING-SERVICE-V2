package actions;

import filters.FilterByCountry;
import filters.GenresStrategy;
import filters.ActorsStrategy;
import filters.Strategy;
import input.ActionInput;
import input.Input;
import input.MovieInput;
import momentary.PageNow;
import output.CommandOutput;
import output.Output;
import sorters.SortByDuration;
import sorters.SortByRating;

import java.util.ArrayList;
import java.util.List;

import static constants.Constants.MOVIES;

public class Filter extends Command {

    public Filter(final Input input, final PageNow pageNow, final ActionInput actionInput,
                  final Output output) {
        super(input, pageNow, actionInput, output);
    }

    /**
     * run command
     */
    @Override
    public void run() {
        if (super.getPageNow().getName().equals(MOVIES)) {
            // initialise filter
            FilterByCountry filterByCountry = new FilterByCountry();

            // get permitted movies in that country
            List<MovieInput> permittedMovies = filterByCountry.filter(
                    super.getInput().getMovies(), super.getPageNow().getUser().getUser());

            // filter by contains if necessary
            if (super.getActionInput().getFilters().getContains() != null) {

                // filter by contains given genre
                if (super.getActionInput().getFilters().getContains().getGenre() != null) {

                    // create new strategy
                    Strategy strategy = new GenresStrategy();

                    // apply filter strategy
                    if (strategy.meetCriteria(permittedMovies,
                            super.getActionInput().getFilters().getContains().getGenre()) != null) {

                        permittedMovies = new ArrayList<>(strategy.meetCriteria(permittedMovies,
                                super.getActionInput().getFilters().getContains().getGenre()));
                    }
                }

                // filter by contains given actors
                if (super.getActionInput().getFilters().getContains().getActors() != null) {

                    // create new strategy
                    Strategy strategy = new ActorsStrategy();

                    // apply filter strategy
                    if (strategy.meetCriteria(permittedMovies,
                            super.getActionInput().getFilters().getContains().getActors())
                            != null) {

                        permittedMovies = new ArrayList<>(strategy.meetCriteria(permittedMovies,
                                super.getActionInput().getFilters().getContains().getActors()));
                    }
                }
            }

            if (super.getActionInput().getFilters().getSort() != null) {

                boolean sortedByDuration = false;
                // case for sorters
                if (super.getActionInput().getFilters().getSort().getDuration() != null) {
                    // initialise sorter
                    SortByDuration sort = new SortByDuration();

                    // run the sort on permitted movies
                    sort.run(permittedMovies,
                            super.getActionInput().getFilters().getSort().getDuration());
                    sortedByDuration = true;
                }

                // case for sorters
                if (super.getActionInput().getFilters().getSort().getRating() != null) {
                    // initialise sorter
                    SortByRating sort = new SortByRating();

                    // run the sort on permitted movies
                    if (sortedByDuration) {
                        sort.runForEqualCases(permittedMovies,
                                super.getActionInput().getFilters().getSort().getRating());
                    } else {
                        sort.run(permittedMovies,
                                super.getActionInput().getFilters().getSort().getRating());
                    }
                }

            }

            // set current movie list
            super.getPageNow().setMovieList(new ArrayList<>(permittedMovies));

            // manage output
            super.getOutput().getOutput().add(new CommandOutput(super.getPageNow().getMovieList(),
                    super.getPageNow().getUser().getUser()));
            return;
        }

        // error case
        super.getOutput().getOutput().add(new CommandOutput());
    }
}
