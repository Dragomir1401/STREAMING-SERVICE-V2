package navigators;

import constants.PageNames;
import input.ActionInput;
import input.Input;
import momentary.PageNow;
import output.Output;

public class Reciever {

    private final Input input;
    private final PageNow pageNow;
    private final Output output;
    private ActionInput action = new ActionInput();
    private final Invoker invoker;


    public Reciever(Input input, PageNow pageNow, Output output) {
        this.input = input;
        this.pageNow = pageNow;
        this.output = output;
        this.invoker = new Invoker();
    }

    public void specifyAction(ActionInput action) {
        this.action = action;
    }

    public Invoker getInvoker() {
        return invoker;
    }

    /**
     * receiver of execute action
     * @param pageName  name of page
     */
    public void executeAction(String pageName) {
        NavigateCommand command;
        try {
            PageNames pageType = PageNames.fromString(pageName);
            if (pageType == null) {
                throw new IllegalArgumentException();
            }
            command = getCommand(pageType);

        } catch(IllegalArgumentException ex) {
            System.out.println("Invalid page: " + pageName);
            System.out.println("Available pages to change to:");
            for (PageNames type : PageNames.values()) {
                System.out.println("\t- " + type.text);
            }
            return;
        }

        invoker.execute(command);
    }

    /**
     * gets the needed navigation command to run it
     * @param page  name of page
     * @return  command instance
     * @throws IllegalArgumentException  exception for unknown page
     */
    private NavigateCommand getCommand(PageNames page) throws IllegalArgumentException {
        try {
            switch (page) {
                case REGISTER: return new GoToRegister(pageNow, output);
                case LOGIN: return new GoToLogin(pageNow, output);
                case LOGOUT: return new GoToLogout(pageNow, output);
                case UPGRADES: return new GoToUpgrades(pageNow, output);
                case MOVIES: return new GoToMovies(input, pageNow, output);
                case SEE_DETAILS: return new GoToSeeDetails(action, pageNow, output);
            }
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException();
        }
        return null;
    }

    public String undo() {
        return invoker.undo();
    }

    public void redo() {
        invoker.redo();
    }
}