package navigators;

import input.MovieInput;
import momentary.PageNow;
import output.CommandOutput;
import output.Output;

import java.util.ArrayList;
import java.util.List;

public class GoToRegister implements NavigateCommand {
    private final PageNow pageNow;
    private final Output output;
    private MovieInput previousMovie;
    private List<MovieInput> previousMovieList;
    private String previousName;

    public GoToRegister(PageNow pageNow, Output output) {
        this.pageNow = pageNow;
        this.output = output;
        this.previousMovie = new MovieInput();
        this.previousMovieList = new ArrayList<>();
        this.previousName = null;
    }

    @Override
    public void execute() {
        if (pageNow.getName().equals("homepage") && pageNow.getUser().getUser() == null) {

            // save previous state for undo
            previousName = pageNow.getName();
            previousMovie = new MovieInput(pageNow.getMovie());
            previousMovieList = new ArrayList<>();
            previousMovieList.addAll(pageNow.getMovieList());

            // set register start
            pageNow.setName("register");
            pageNow.setMovie(null);
            pageNow.setMovieList(null);
        } else {
            output.getOutput().add(new CommandOutput());
        }
    }

    @Override
    public void undo() {
        // reset actions done
        pageNow.setName(previousName);
        pageNow.setMovie(previousMovie);
        pageNow.setMovieList(previousMovieList);
    }

}
