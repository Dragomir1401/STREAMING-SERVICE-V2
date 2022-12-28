package database;

import input.ActionInput;
import input.Input;
import output.Output;
import static constants.Constants.DEFAULT_DATABASE_OPERATION;
import static constants.Constants.ADD;
import static constants.Constants.DELETE;


public final class Operations {

    private Operations() {

    }

    /**
     * parses database operation
     * @param input  input structure
     * @param output  output structure
     * @param action  action input
     */
    public static void parseOperation(final Input input, final Output output,
                                      final ActionInput action) {
        switch (action.getFeature()) {
            case ADD -> Add.addMovie(input, output, action);
            case DELETE -> Delete.deleteMovie(input, output, action);
            default -> System.out.println(DEFAULT_DATABASE_OPERATION);
        }
    }
}
