package navigators;

import java.util.LinkedList;

import static constants.Constants.*;

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
    public void execute(NavigateCommand command) {
        String result = command.execute();
        if (result.equals(SUCCESS)) {
            historyExecuted.push(command);
        }
    }

    /**
     * Undo the latest command
     */
    public String undo() {
        if (historyExecuted.isEmpty())
            return EMPTY_COMMAND_STACK;
        NavigateCommand navigateCommand = historyExecuted.pop();
        if (navigateCommand != null) {
            historyUndo.push(navigateCommand);
            return navigateCommand.undo();
        }
        return POOR_UNDO;
    }

    /**
     * Redo command previously undone. Cannot perform a redo after an execute, only after at least one undo.
     */
    public void redo() {
        if (historyUndo.isEmpty())
            return;
        NavigateCommand navigateCommand = historyUndo.pop();
        if (navigateCommand != null) {
            historyExecuted.push(navigateCommand);
            navigateCommand.execute();
        }
    }
}
