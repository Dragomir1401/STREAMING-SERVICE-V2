package commands;

import input.ActionInput;
import input.Input;
import input.MovieInput;
import input.UserInput;

public class UserCommands {
    private static UserCommands instance;

    /**
     * run user command
     */
    public static void run() {

    }

    /**
     * singleton getter
     * @return - singleton instance
     */
    public static UserCommands getInstance() {
        if (instance == null) {
            instance = new UserCommands();
        }

        return instance;
    }

    /**
     * checks if user exists in input database
     * @param input - input structure
     * @param actionInput - action input
     * @return - true/false
     */
    public boolean userExists(final Input input, final ActionInput actionInput) {
        // user with the same name exists
        for (UserInput userInput : input.getUsers()) {
            if (userInput.getCredentials().getName().equals(
                    actionInput.getCredentials().getName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * verify credentials for login
     * @param input - input structure
     * @param actionInput - action input
     * @return - true for verified login, false for fail login
     */
    public boolean checkCredentials(final Input input, final ActionInput actionInput) {
        // check login credentials
        for (UserInput userInput : input.getUsers()) {
            if (userInput.getCredentials().getName().equals(
                    actionInput.getCredentials().getName())) {
                return userInput.getCredentials().getPassword().equals(
                        actionInput.getCredentials().getPassword());
            }
        }
        return false;
    }

    /**
     * finds user instance in database
     * @param input - input structure
     * @param actionInput - action input
     * @return - user instance
     */
    public UserInput findUserInDatabase(final Input input, final ActionInput actionInput) {
        // finds user instance in input database
        for (UserInput userInput : input.getUsers()) {
            if (userInput.getCredentials().getName().equals(
                    actionInput.getCredentials().getName())) {
                return userInput;
            }
        }
        return null;
    }

    /**
     * checks if user can see a given movie via not banned in his country
     * @param userInput - user
     * @param movieInput - movie
     * @return - true/false
     */
    public boolean userCanSeeMovie(final UserInput userInput, final MovieInput movieInput) {
        for (String country : movieInput.getCountriesBanned()) {
            if (userInput.getCredentials().getCountry().equals(country)) {
                return false;
            }
        }
        return true;
    }
}
