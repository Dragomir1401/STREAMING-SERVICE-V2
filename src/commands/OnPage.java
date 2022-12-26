package commands;

import actions.BuyTokens;
import actions.BuyPremium;
import actions.Command;
import actions.Filter;
import actions.Login;
import actions.Like;
import actions.Purchase;
import actions.Register;
import actions.Rate;
import actions.Watch;
import actions.Search;
import input.Input;
import input.ActionInput;
import momentary.PageNow;
import output.Output;

import static constants.Constants.*;


public final class OnPage {
    private OnPage() {

    }

    /**
     * runs on page type commands
     * @param input - input structure
     * @param pageNow - current page
     * @param action - action input
     * @param output - output structure
     */
    public static Command factoryCommand(final Input input, final PageNow pageNow,
                                         final ActionInput action, final Output output) {
        if (action.getFeature() != null) {
            // select command type and factory its class
            switch (action.getFeature()) {
                case REGISTER -> {
                    return new Register(input, pageNow, action, output);
                }
                case LOGIN -> {
                    return new Login(input, pageNow, action, output);
                }
                case FILTER -> {
                    return new Filter(input, pageNow, action, output);
                }
                case BUY_TOKENS -> {
                    return new BuyTokens(input, pageNow, action, output);
                }
                case LIKE -> {
                    return new Like(input, pageNow, action, output);
                }
                case RATE -> {
                    return new Rate(input, pageNow, action, output);
                }
                case PURCHASE -> {
                    return new Purchase(input, pageNow, action, output);
                }
                case WATCH -> {
                    return new Watch(input, pageNow, action, output);
                }
                case SEARCH -> {
                    return new Search(input, pageNow, action, output);
                }
                case BUY_PREMIUM_ACCOUNT -> {
                    return new BuyPremium(input, pageNow, action,
                                output);
                }
                default -> throw new IllegalArgumentException("The command type "
                        + action.getFeature() + " is not recognized.");
            }

        }
        return null;
    }


    /**
     * run command
     * @param input - input
     * @param pageNow - current page
     * @param action - action input
     * @param output - output structure
     */
    public static void run(final Input input, final PageNow pageNow, final ActionInput action,
                           final Output output) {
        // select command
        Command command = factoryCommand(input, pageNow, action, output);
        // run command
        assert command != null;
        command.run();
    }
}
