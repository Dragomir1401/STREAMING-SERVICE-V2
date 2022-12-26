package actions;

import input.ActionInput;
import input.Input;
import momentary.PageNow;
import output.CommandOutput;
import output.Output;

import static constants.Constants.UPGRADES;

public class BuyPremium extends Command {

    public BuyPremium(final Input input, final PageNow pageNow, final ActionInput actionInput,
                      final Output output) {
        super(input, pageNow, actionInput, output);
    }

    /**
     * run command
     */
    @Override
    public void run() {
        // check to see if we are on correct page
        if (super.getPageNow().getName().equals(UPGRADES)) {
            super.getPageNow().getTokensCommands().buyPremiumAccount(
                    super.getPageNow().getUser().getUser(), super.getOutput());
            return;
        }
        // error case
        super.getOutput().getOutput().add(new CommandOutput());
    }
}
