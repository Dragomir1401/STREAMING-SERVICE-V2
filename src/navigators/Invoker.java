package navigators;

import java.util.LinkedList;

import static constants.Constants.SUCCESS;
import static constants.Constants.EMPTY_COMMAND_STACK;
import static constants.Constants.POOR_UNDO;

public class Invoker {
    private final LinkedList<NavigateCommand> historyExecuted = new LinkedList<>();
    private final LinkedList<NavigateCommand> historyUndo = new LinkedList<>();

    /**
     * Clear up all the used resources
     */
    public void restart() {
        historyExecuted.clear();
        historyUndo.clear();
    }

    /**
     * Executes a given command
     * @param command  command
     */
    public void execute(final NavigateCommand command) {
        String result = command.execute();
        if (result.equals(SUCCESS)) {
            historyExecuted.push(command);
        }
    }

    /**
     * Undo the latest command
     */
    public String undo() {
        if (historyExecuted.isEmpty()) {
            return EMPTY_COMMAND_STACK;
        }
        NavigateCommand navigateCommand = historyExecuted.pop();
        if (navigateCommand != null) {
            historyUndo.push(navigateCommand);
            return navigateCommand.undo();
        }
        return POOR_UNDO;
    }
}
