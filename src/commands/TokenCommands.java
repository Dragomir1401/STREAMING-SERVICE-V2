package commands;

import input.ActionInput;
import input.UserInput;
import output.CommandOutput;
import output.Output;

import static constants.Constants.PREMIUM_COST;

public final class TokenCommands {
    private TokenCommands() {

    }
    private static TokenCommands instance;

    /**
     * singleton getter for token type commands
     * @return - singleton instance
     */
    public static TokenCommands getInstance() {
        if (instance == null) {
            instance = new TokenCommands();
        }

        return instance;
    }

    /**
     * buy tokens command
     * @param userInput - input for user
     * @param actionInput - action input
     * @param output - output structure
     */
    public void buyTokens(final UserInput userInput, final ActionInput actionInput,
                          final Output output) {
        // case for not enough tokens
        if (actionInput.getCount() > Integer.parseInt(userInput.getCredentials().getBalance())) {
            output.getOutput().add(new CommandOutput());
            return;
        }

        // buy tokens action
        int balance = Integer.parseInt(userInput.getCredentials().getBalance());
        balance -= actionInput.getCount();
        userInput.getCredentials().setBalance(Integer.toString(balance));
        userInput.setTokensCount(userInput.getTokensCount() + actionInput.getCount());
    }

    /**
     * buy premium account feature
     * @param userInput - input for user
     * @param output - output structure
     */
    public void buyPremiumAccount(final UserInput userInput, final Output output) {
        // case for not enough tokens
        if (PREMIUM_COST > userInput.getTokensCount()) {
            output.getOutput().add(new CommandOutput());
            return;
        }

        // case for already having premium
        if (userInput.getCredentials().getAccountType().equals("premium")) {
            output.getOutput().add(new CommandOutput());
            return;
        }

        // buy premium account action
        userInput.setTokensCount(userInput.getTokensCount() - PREMIUM_COST);
        userInput.getCredentials().setAccountType("premium");
    }
}
