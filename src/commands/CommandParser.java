package commands;

import input.ActionInput;
import input.Input;
import momentary.PageNow;
import output.Output;


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
        for (ActionInput action : input.getActions()) {
            switch (action.getType()) {
                case "change page" -> ChangePage.run(input, pageNow, action, output);
                case "on page" -> OnPage.run(input, pageNow, action, output);
                default -> System.out.println("Default command case!");
            }
        }

    }
}
