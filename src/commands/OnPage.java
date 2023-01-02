package commands;

import actions.Rate;
import actions.Register;
import actions.Login;
import actions.Filter;
import actions.BuyPremium;
import actions.BuyTokens;
import actions.Command;
import actions.Watch;
import actions.Purchase;
import actions.Subscribe;
import actions.Search;
import actions.Like;
import input.Input;
import input.ActionInput;
import momentary.PageNow;
import output.Output;
import static constants.Constants.RATE;
import static constants.Constants.REGISTER;
import static constants.Constants.LOGIN;
import static constants.Constants.FILTER;
import static constants.Constants.PURCHASE;
import static constants.Constants.SEARCH;
import static constants.Constants.BUY_TOKENS;
import static constants.Constants.BUY_PREMIUM_ACCOUNT;
import static constants.Constants.SUBSCRIBE;
import static constants.Constants.WATCH;
import static constants.Constants.LIKE;

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
                case SUBSCRIBE -> {
                    return new Subscribe(input, pageNow, action,
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
