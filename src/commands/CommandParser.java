package commands;

import database.Operations;
import input.ActionInput;
import input.Input;
import momentary.PageNow;
import navigators.Receiver;
import output.Output;
import recommendation.Recommendation;

import static constants.Constants.DEFAULT_COMMAND;
import static constants.Constants.CHANGE_PAGE;
import static constants.Constants.BACK;
import static constants.Constants.ON_PAGE;
import static constants.Constants.DATABASE;


public final class CommandParser {
    private CommandParser() {

    }

    /**
     * parses type of commands in change page and on page
     * @param input - given input
     * @param pageNow - current page
     * @param output - output structure
     */
    public static void parse(final Input input, final PageNow pageNow, final Output output) {
        Receiver receiver = new Receiver(input, pageNow, output);
        for (ActionInput action : input.getActions()) {
            // specify action for change page to see details
            receiver.specifyAction(action);

            switch (action.getType()) {
                case CHANGE_PAGE -> ChangePage.next(action, receiver);
                case ON_PAGE -> OnPage.run(input, pageNow, action, output);
                case BACK -> ChangePage.back(pageNow, output, receiver);
                case DATABASE -> Operations.parseOperation(input, output, action);
                default -> System.out.println(DEFAULT_COMMAND);
            }
        }
        // generate final recommendation if there is a current user logged
        if (pageNow.getUser().getUser() != null) {
            Recommendation.generateRecommendations(input, pageNow, output);
        }
    }
}
