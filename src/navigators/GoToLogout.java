package navigators;

import input.MovieInput;
import input.UserInput;
import momentary.PageNow;
import output.CommandOutput;
import output.Output;

import java.util.ArrayList;
import java.util.List;

import static constants.Constants.*;


public class GoToLogout implements NavigateCommand {
    private final PageNow pageNow;
    private final Output output;
    private MovieInput previousMovie;
    private List<MovieInput> previousMovieList;
    private String previousName;
    private UserInput previousUser;

    public GoToLogout(PageNow pageNow, Output output) {
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
            previousUser = new UserInput(pageNow.getUser().getUser());

            // execute change to log out page
            pageNow.setName(HOMEPAGE);
            pageNow.setMovie(null);
            pageNow.setMovieList(null);
            pageNow.getUser().setUser(null);

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
        output.getOutput().add(new CommandOutput());

        return previousName;
    }
}
