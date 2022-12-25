package actions;

import input.ActionInput;
import input.Input;
import momentary.PageNow;
import output.Output;

public abstract class Command {
    private final Input input;
    private final PageNow pageNow;
    private final ActionInput actionInput;
    private final Output output;

    public Command(final Input input, final PageNow pageNow, final ActionInput actionInput,
                   final Output output) {
        this.input = input;
        this.pageNow = pageNow;
        this.actionInput = actionInput;
        this.output = output;
    }

    /**
     * getter for input
     * @return - input
     */
    public Input getInput() {
        return input;
    }

    /**
     * getter for current page
     * @return - current page
     */
    public PageNow getPageNow() {
        return pageNow;
    }


    /**
     * getter for action input
     * @return - action input
     */
    public ActionInput getActionInput() {
        return actionInput;
    }


    /**
     * getter for output
     * @return - output
     */
    public Output getOutput() {
        return output;
    }


    /**
     * run command
     */
    public void run() {

    }
}
