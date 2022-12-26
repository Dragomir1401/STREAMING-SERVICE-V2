package database;

import input.ActionInput;
import input.Input;
import output.Output;

import static constants.Constants.*;

public final class Operations {
    public static void parseOperation(Input input, Output output, ActionInput action) {
        switch (action.getFeature()) {
            case ADD -> Add.addMovie(input, output, action);
            case DELETE -> Delete.deleteMovie(input, output, action);
            default -> System.out.println(DEFAULT_DATABASE_OPERATION);
        }
    }
}
