package actions;

import input.ActionInput;
import input.Input;
import momentary.PageNow;
import output.CommandOutput;
import output.Output;

import static constants.Constants.SEE_DETAILS;

public final class Subscribe extends Command {

    public Subscribe(final Input input, final PageNow pageNow, final ActionInput actionInput,
                     final Output output) {
        super(input, pageNow, actionInput, output);
    }

    /**
     * subscribes given user to a genre
     */
    public void run() {

        // check to see if user is already subscribed to this genre
        if (super.getPageNow().getUser().getUser().getSubscribedGenre().contains(
                super.getActionInput().getSubscribedGenre())) {

            super.getOutput().getOutput().add(new CommandOutput());
            return;
        }

        // check to see if we are on correct page and subscribing to correct genres
        if (!super.getPageNow().getName().equals(SEE_DETAILS)
                || !super.getPageNow().getMovie().getGenres().contains(super.getActionInput().
                getSubscribedGenre())) {

            super.getOutput().getOutput().add(new CommandOutput());
            return;
        }


        // generate subscription
        super.getPageNow().getUser().getUser().getSubscribedGenre().add(super.getActionInput().
                getSubscribedGenre());
    }
}
