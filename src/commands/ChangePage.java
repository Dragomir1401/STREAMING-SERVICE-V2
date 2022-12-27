package commands;

import input.ActionInput;
import input.MovieInput;
import momentary.PageNow;
import navigators.*;
import output.CommandOutput;
import output.Output;
import static constants.Constants.*;


public final class ChangePage {
    private ChangePage() {

    }

    /**
     * runs change page type commands
     * @param action - action input
     */
    public static void next(ActionInput action, Reciever reciever) {

        // execute action using receiver
        reciever.executeAction(action.getPage());

        // clear stack of actions in case of logout
        if (action.getType().equals(LOGOUT)) {
            reciever.getInvoker().restart();
        }
    }

    public static void back(final PageNow pageNow, final Output output, Reciever reciever) {
        // check for logged users
        if (pageNow.getUser().getUser() == null) {
            output.getOutput().add(new CommandOutput());
            return;
        }

        String previousPage = reciever.undo();

        if (previousPage != null) {
            if (previousPage.equals(MOVIES)) {
                output.getOutput().add(new CommandOutput(pageNow.getMovieList(),
                        pageNow.getUser().getUser()));
            }

            // check for empty stack of actions to undo
            if (previousPage.equals(EMPTY_COMMAND_STACK)) {
                output.getOutput().add(new CommandOutput());
            }
        }

    }
}

