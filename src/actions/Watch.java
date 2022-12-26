package actions;

import input.ActionInput;
import input.Input;
import momentary.PageNow;
import output.CommandOutput;
import output.Output;

import static constants.Constants.SEE_DETAILS;
import static constants.Constants.UPGRADES;

public class Watch extends Command {

    public Watch(final Input input, final PageNow pageNow, final ActionInput actionInput,
                 final Output output) {
        super(input, pageNow, actionInput, output);
    }

    /**
     * run command
     */
    @Override
    public void run() {
        // check to see if we are on correct page
        if (super.getPageNow().getName().equals(SEE_DETAILS)
                || super.getPageNow().getName().equals(UPGRADES)) {

            super.getPageNow().getMoviesCommands().watchMovie(super.getPageNow(),
                    super.getOutput(), super.getActionInput());
            return;
        }

        // error case
        super.getOutput().getOutput().add(new CommandOutput());
    }
}
