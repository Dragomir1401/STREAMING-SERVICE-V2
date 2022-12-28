package navigators;

import constants.PageNames;
import input.ActionInput;
import input.Input;
import momentary.PageNow;
import output.Output;

public class Receiver {

    private final Input input;
    private final PageNow pageNow;
    private final Output output;
    private ActionInput action = new ActionInput();
    private final Invoker invoker;


    public Receiver(final Input input, final PageNow pageNow, final Output output) {
        this.input = input;
        this.pageNow = pageNow;
        this.output = output;
        this.invoker = new Invoker();
    }


    /**
     * specifies what action to be done
     * @param actionInput  action
     */
    public void specifyAction(final ActionInput actionInput) {
        this.action = actionInput;
    }


    /**
     * getter for invoker
     * @return  invoker
     */
    public Invoker getInvoker() {
        return invoker;
    }


    /**
     * receiver of execute action
     * @param pageName  name of page
     */
    public void executeAction(final String pageName) {
        NavigateCommand command;
        try {
            PageNames pageType = PageNames.fromString(pageName);
            if (pageType == null) {
                throw new IllegalArgumentException();
            }
            command = getCommand(pageType);

        } catch (IllegalArgumentException ex) {
            System.out.println("Invalid page: " + pageName);
            System.out.println("Available pages to change to:");
            for (PageNames type : PageNames.values()) {
                System.out.println("\t- " + type.getText());
            }
            return;
        }

        assert command != null;
        invoker.execute(command);
    }

    /**
     * gets the needed navigation command to run it
     * @param page  name of page
     * @return  command instance
     * @throws IllegalArgumentException  exception for unknown page
     */
    private NavigateCommand getCommand(final PageNames page) throws IllegalArgumentException {
        try {
            return switch (page) {
                case REGISTER -> new GoToRegister(pageNow, output);
                case LOGIN -> new GoToLogin(pageNow, output);
                case LOGOUT -> new GoToLogout(pageNow, output);
                case UPGRADES -> new GoToUpgrades(pageNow, output);
                case MOVIES -> new GoToMovies(input, pageNow, output);
                case SEE_DETAILS -> new GoToSeeDetails(action, pageNow, output);
                default -> null;
            };
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException();
        }
    }


    /**
     * undo action
     * @return  calls invoker for undo
     */
    public String undo() {
        return invoker.undo();
    }

}
