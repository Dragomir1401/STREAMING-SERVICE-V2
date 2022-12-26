package navigators;

import input.ActionInput;
import input.MovieInput;
import momentary.PageNow;
import output.CommandOutput;
import output.Output;

import java.util.ArrayList;
import java.util.List;

import static constants.Constants.MOVIES;
import static constants.Constants.SEE_DETAILS;

public class GoToSeeDetails implements NavigateCommand {
    private final ActionInput action;
    private final PageNow pageNow;
    private final Output output;
    private MovieInput previousMovie;
    private List<MovieInput> previousMovieList;
    private String previousName;

    public GoToSeeDetails(ActionInput action, PageNow pageNow, Output output) {
        this.action = action;
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
    public void execute() {
        if (pageNow.getName().equals(MOVIES)
                && pageNow.getMoviesCommands().findMovieInstance(pageNow.getMovieList(),
                action.getMovie()) != null) {

            // save previous state for undo
            previousName = pageNow.getName();
            if (pageNow.getMovie() != null)
                previousMovie = new MovieInput(pageNow.getMovie());
            previousMovieList = new ArrayList<>();
            if (pageNow.getMovieList() != null)
                previousMovieList.addAll(pageNow.getMovieList());


            // move to see details page
            pageNow.setName(SEE_DETAILS);
            pageNow.setMovie(new MovieInput(pageNow.getMoviesCommands().findMovieInstance(
                    pageNow.getMovieList(), action.getMovie())));

            pageNow.getMoviesCommands().getMovieDetails(pageNow.getMoviesCommands().findMovieInstance(
                            pageNow.getMovieList(), action.getMovie()), output,
                    pageNow.getUser().getUser(), pageNow);

        } else {
            output.getOutput().add(new CommandOutput());
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
