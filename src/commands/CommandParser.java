package commands;

import database.Operations;
import input.ActionInput;
import input.Input;
import momentary.PageNow;
import navigators.Reciever;
import output.Output;
import subscribers.Recommendation;
import subscribers.Subscribe;

import static constants.Constants.*;


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
        Reciever reciever = new Reciever(input, pageNow, output);
        for (ActionInput action : input.getActions()) {
            // specify action for change page to see details
            reciever.specifyAction(action);

            switch (action.getType()) {
                case CHANGE_PAGE -> ChangePage.next(action, reciever);
                case ON_PAGE -> OnPage.run(input, pageNow, action, output);
                case BACK -> ChangePage.back(pageNow, output, reciever);
                case SUBSCRIBE -> Subscribe.subscribe(pageNow, output, action);
                case DATABASE -> Operations.parseOperation(input, output, action);
                default -> System.out.println(DEFAULT_COMMAND);
            }
        }
        // generate final recommendation if there is a current user logged
        if (pageNow.getUser().getUser() != null)
            Recommendation.generateRecommendations(input, pageNow, output);
    }
}
