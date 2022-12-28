package commands;

import input.ActionInput;
import momentary.PageNow;
import navigators.Receiver;
import output.CommandOutput;
import output.Output;
import static constants.Constants.LOGOUT;
import static constants.Constants.MOVIES;
import static constants.Constants.EMPTY_COMMAND_STACK;


public final class ChangePage {
    private ChangePage() {

    }

    /**
     * runs change page type commands
     * @param action - action input
     */
    public static void next(final ActionInput action, final Receiver receiver) {

        // execute action using receiver
        receiver.executeAction(action.getPage());

        // clear stack of actions in case of logout
        if (action.getType().equals(LOGOUT)) {
            receiver.getInvoker().restart();
        }
    }

    /**
     * runs back navigator command
     * @param pageNow  current page
     * @param output  output structure
     * @param receiver  receiver of commands
     */
    public static void back(final PageNow pageNow, final Output output, final Receiver receiver) {
        // check for logged users
        if (pageNow.getUser().getUser() == null) {
            output.getOutput().add(new CommandOutput());
            return;
        }

        String previousPage = receiver.undo();

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

