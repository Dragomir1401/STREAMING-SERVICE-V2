package actions;

import input.ActionInput;
import input.Input;
import input.UserInput;
import momentary.PageNow;
import output.CommandOutput;
import output.Output;

import static constants.Constants.HOMEPAGE;
import static constants.Constants.REGISTER;

public class Register extends Command {
    public Register(final Input input, final PageNow pageNow, final ActionInput actionInput,
                    final Output output) {
        super(input, pageNow, actionInput, output);
    }

    /**
     * run command
     */
    @Override
    public void run() {
        // if page is register and another user is not registered already
        if (super.getPageNow().getName().equals(REGISTER)
                && super.getPageNow().getUser().getUser() == null
                && !super.getPageNow().getUserCommands().userExists(super.getInput(),
                super.getActionInput())) {

            UserInput newUser = new UserInput(super.getActionInput().getCredentials());

            // add user to input base
            super.getInput().getUsers().add(newUser);

            // set current user to new user
            super.getPageNow().getUser().setUser(newUser);

            // generate output
            super.getOutput().getOutput().add(new CommandOutput(super.getPageNow().getMovieList(),
                    super.getPageNow().getUser().getUser()));

            // set page to homepage with authentication
            super.getPageNow().setName(HOMEPAGE);

            // exit
            return;
        }

        // set page back to homepage in case of error
        super.getPageNow().setName(HOMEPAGE);
        super.getOutput().getOutput().add(new CommandOutput());
    }
}

