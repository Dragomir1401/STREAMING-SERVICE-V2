package navigators;

import input.MovieInput;
import momentary.PageNow;
import output.CommandOutput;
import output.Output;

import java.util.ArrayList;
import java.util.List;

import static constants.Constants.SUCCESS;
import static constants.Constants.UPGRADES;
import static constants.Constants.FAILURE;


public class GoToUpgrades implements NavigateCommand {
    private final PageNow pageNow;
    private final Output output;
    private MovieInput previousMovie;
    private List<MovieInput> previousMovieList;
    private String previousName;


    public GoToUpgrades(final PageNow pageNow, final Output output) {
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
            if (pageNow.getMovie() != null) {
                previousMovie = new MovieInput(pageNow.getMovie());
            }
            previousMovieList = new ArrayList<>();
            if (pageNow.getMovieList() != null) {
                previousMovieList.addAll(pageNow.getMovieList());
            }


            // move to upgrades page
            pageNow.setName(UPGRADES);
            pageNow.setMovie(null);
            pageNow.setMovieList(null);

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
        List<MovieInput> list = new ArrayList<>(previousMovieList);
        pageNow.setMovieList(list);

        return previousName;
    }
}
