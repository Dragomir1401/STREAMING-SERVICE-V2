package subscribers;

import input.ActionInput;
import momentary.PageNow;
import output.CommandOutput;
import output.Output;

import static constants.Constants.SEE_DETAILS;

public final class Subscribe {
    private Subscribe() {
    }

    /**
     * subscribes given user to a genre
     * @param pageNow  current page
     * @param output  output structure
     * @param action  action
     */
    public static void subscribe(final PageNow pageNow, final Output output,
                                 final ActionInput action) {

        // check to see if user is already subscribed to this genre
        if (pageNow.getUser().getUser().getSubscribedGenre().contains(
                action.getSubscribedGenre())) {

            output.getOutput().add(new CommandOutput());
            return;
        }

        // check to see if we are on correct page and subscribing to correct genres
        if (!pageNow.getName().equals(SEE_DETAILS)
                || !pageNow.getMovie().getGenres().contains(action.getSubscribedGenre())) {

            output.getOutput().add(new CommandOutput());
            return;
        }


        // generate subscription
        pageNow.getUser().getUser().getSubscribedGenre().add(action.getSubscribedGenre());
    }
}
