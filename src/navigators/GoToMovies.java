package navigators;

import filters.FilterByCountry;
import input.Input;
import input.MovieInput;
import momentary.PageNow;
import output.CommandOutput;
import output.Output;

import java.util.ArrayList;
import java.util.List;

import static constants.Constants.*;

public class GoToMovies implements NavigateCommand {
    private final Input input;
    private final PageNow pageNow;
    private final Output output;
    private MovieInput previousMovie;
    private List<MovieInput> previousMovieList;
    private String previousName;

    public GoToMovies(Input input, PageNow pageNow, Output output) {
        this.input = input;
        this.pageNow = pageNow;
        this.output = output;
        this.previousMovie = new MovieInput();
        this.previousMovieList = new ArrayList<>();
        this.previousName = null;
    }

    /**
     * execute named navigation command
     */
    @Override
    public String execute() {
        if (pageNow.getUser().getUser() != null) {
            // save previous state for undo
            previousName = pageNow.getName();
            if (pageNow.getMovie() != null)
                previousMovie = new MovieInput(pageNow.getMovie());
            previousMovieList = new ArrayList<>();
            if (pageNow.getMovieList() != null)
                previousMovieList.addAll(pageNow.getMovieList());


            // move to see movies page
            FilterByCountry filterByCountry = new FilterByCountry();
            pageNow.setName(MOVIES);
            pageNow.setMovie(null);
            pageNow.setMovieList(filterByCountry.filter(input.getMovies(), pageNow.getUser().getUser()));
            output.getOutput().add(new CommandOutput(pageNow.getMovieList(),
                    pageNow.getUser().getUser()));

            return SUCCESS;
        } else {
            output.getOutput().add(new CommandOutput());
            return FAILURE;
        }

    }


    /**
     * undo named navigation command
     * @return  previous page name
     */
    @Override
    public String undo() {
        // reset actions done
        pageNow.setName(previousName);
        pageNow.setMovie(previousMovie);
        pageNow.setMovieList(previousMovieList);

        return previousName;
    }
}
