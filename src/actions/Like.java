package actions;

import input.ActionInput;
import input.Input;
import momentary.PageNow;
import output.CommandOutput;
import output.Output;

public class Like extends Command {

    public Like(final Input input, final PageNow pageNow, final ActionInput actionInput,
                final Output output) {
        super(input, pageNow, actionInput, output);
    }

    /**
     * run command
     */
    @Override
    public void run() {
        // check to see if we are on correct page
        if (super.getPageNow().getName().equals("see details")
                || super.getPageNow().getName().equals("upgrades")) {
            super.getPageNow().getMoviesCommands().likeMovie(super.getInput(), super.getPageNow(),
                    super.getOutput(), super.getActionInput());
            return;
        }
        // error case
        super.getOutput().getOutput().add(new CommandOutput());
    }
}
