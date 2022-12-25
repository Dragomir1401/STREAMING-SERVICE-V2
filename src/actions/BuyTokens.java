package actions;

import input.ActionInput;
import input.Input;
import momentary.PageNow;
import output.CommandOutput;
import output.Output;

public class BuyTokens extends Command {

    public BuyTokens(final Input input, final PageNow pageNow, final ActionInput actionInput,
                     final Output output) {
        super(input, pageNow, actionInput, output);
    }

    /**
     * run command
     */
    @Override
    public void run() {
        // check to see if we are on correct page
        if (super.getPageNow().getName().equals("upgrades")) {
            super.getPageNow().getTokensCommands().buyTokens(
                    super.getPageNow().getUser().getUser(), super.getActionInput(),
                    super.getOutput());
            return;
        }
        // error case
        super.getOutput().getOutput().add(new CommandOutput());
    }

}
