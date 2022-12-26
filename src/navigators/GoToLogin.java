package navigators;

import input.MovieInput;
import momentary.PageNow;
import output.CommandOutput;
import output.Output;
import java.util.ArrayList;
import java.util.List;
import static constants.Constants.LOGIN;
import static constants.Constants.HOMEPAGE;


public class GoToLogin implements NavigateCommand {
    private final PageNow pageNow;
    private final Output output;
    private MovieInput previousMovie;
    private List<MovieInput> previousMovieList;
    private String previousName;

    public GoToLogin(PageNow pageNow, Output output) {
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
        if (!pageNow.getName().equals(HOMEPAGE) || pageNow.getUser().getUser() != null) {
            output.getOutput().add(new CommandOutput());
        } else {

            // save previous state for undo
            previousName = pageNow.getName();
            if (pageNow.getMovie() != null)
                previousMovie = new MovieInput(pageNow.getMovie());
            previousMovieList = new ArrayList<>();
            if (pageNow.getMovieList() != null)
                previousMovieList.addAll(pageNow.getMovieList());

            // set login start
            pageNow.setName(LOGIN);
            pageNow.setMovie(null);
            pageNow.setMovieList(null);
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
