package actions;

import filters.FilterByCountry;
import filters.StartsWithStrategy;
import filters.Strategy;
import filters.UserFilter;
import input.ActionInput;
import input.Input;
import input.MovieInput;
import momentary.PageNow;
import output.CommandOutput;
import output.Output;

import java.util.ArrayList;
import java.util.List;

import static constants.Constants.MOVIES;

public class Search extends Command {

    public Search(final Input input, final PageNow pageNow, final ActionInput actionInput,
                  final Output output) {
        super(input, pageNow, actionInput, output);
    }

    /**
     * run command
     */
    @Override
    public void run() {
        // check to see if we are on movies page
        if (super.getPageNow().getName().equals(MOVIES)) {

            // initialise filters
            Strategy strategy = new StartsWithStrategy();
            UserFilter filterByCountry = new FilterByCountry();

            // set current movie filtered list on permitted movies for that country
            List<MovieInput> permittedMovies = filterByCountry.filter(super.getInput().getMovies(),
                    super.getPageNow().getUser().getUser());

            // create content for filtering
            List<String> content = new ArrayList<>();
            content.add(super.getActionInput().getStartsWith());

            // set list to filtered movies
            super.getPageNow().setMovieList(strategy.meetCriteria(permittedMovies, content));

            // set output
            super.getOutput().getOutput().add(new CommandOutput(super.getPageNow().getMovieList(),
                    super.getPageNow().getUser().getUser()));

            // exit
            return;
        }

        // error case
        super.getOutput().getOutput().add(new CommandOutput());

    }

}
