package navigators;

public interface NavigateCommand {
    /**
     * execute named navigation command
     */
    void execute();

    /**
     * undo named navigation command
     */
    String undo();
}
