package navigators;

public interface NavigateCommand {
    /**
     * execute named navigation command
     */
    String execute();

    /**
     * undo named navigation command
     */
    String undo();
}
