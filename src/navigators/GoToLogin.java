package navigators;

import momentary.PageNow;
import output.CommandOutput;
import output.Output;

import static constants.Constants.FAILURE;
import static constants.Constants.LOGIN;
import static constants.Constants.HOMEPAGE;
import static constants.Constants.SUCCESS;

public class GoToLogin implements NavigateCommand {
    private final PageNow pageNow;
    private final Output output;
    private String previousName;

    public GoToLogin(final PageNow pageNow, final Output output) {
        this.pageNow = pageNow;
        this.output = output;
        this.previousName = null;
    }

    /**
     * execute named navigation command
     */
    @Override
    public String execute() {
        if (!pageNow.getName().equals(HOMEPAGE) || pageNow.getUser().getUser() != null) {
            output.getOutput().add(new CommandOutput());
            return FAILURE;
        } else {

            // save previous state for undo
            previousName = pageNow.getName();


            // set login start
            pageNow.setName(LOGIN);
            pageNow.setMovie(null);
            pageNow.setMovieList(null);
            return SUCCESS;
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
