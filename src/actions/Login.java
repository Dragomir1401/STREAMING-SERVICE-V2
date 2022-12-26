package actions;

import input.ActionInput;
import input.Input;
import momentary.PageNow;
import output.CommandOutput;
import output.Output;

import static constants.Constants.HOMEPAGE;
import static constants.Constants.LOGIN;

public class Login extends Command {
    public Login(final Input input, final PageNow pageNow, final ActionInput actionInput,
                 final Output output) {
        super(input, pageNow, actionInput, output);
    }

    /**
     * run command
     */
    @Override
    public void run() {
        // if page is not login or user is already logged or user is not in input base
        if (super.getPageNow().getName().equals(LOGIN)
                && super.getPageNow().getUser().getUser() == null
                && super.getPageNow().getUserCommands().userExists(super.getInput(),
                        super.getActionInput())) {

            if (super.getPageNow().getUserCommands().checkCredentials(super.getInput(),
                    super.getActionInput())) {

                // if credentials are found in database
                super.getPageNow().getUser().setUser(
                        super.getPageNow().getUserCommands().findUserInDatabase(super.getInput(),
                                super.getActionInput()));

                // set page to homepage with authentication
                super.getPageNow().setName(HOMEPAGE);

                // add output
                super.getOutput().getOutput().add(new CommandOutput(
                        super.getPageNow().getMovieList(),
                        super.getPageNow().getUser().getUser()));

                // exit
                return;
            }
        }

        // set page back to homepage in case of error
        super.getPageNow().setName(HOMEPAGE);
        super.getOutput().getOutput().add(new CommandOutput());
    }
}
