package navigators;

import input.MovieInput;
import momentary.PageNow;
import output.CommandOutput;
import output.Output;

import java.util.ArrayList;
import java.util.List;

import static constants.Constants.*;

public class GoToRegister implements NavigateCommand {
    private final PageNow pageNow;
    private final Output output;
    private String previousName;

    public GoToRegister(PageNow pageNow, Output output) {
        this.pageNow = pageNow;
        this.output = output;
        this.previousName = null;
    }



    /**
     * execute named navigation command
     */
    @Override
    public String execute() {
        if (pageNow.getName().equals(HOMEPAGE) && pageNow.getUser().getUser() == null) {

            // save previous state for undo
            previousName = pageNow.getName();

            // set register start
            pageNow.setName(REGISTER);
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
        output.getOutput().add(new CommandOutput());

        return previousName;
    }

}
