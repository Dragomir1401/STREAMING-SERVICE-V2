package database;

import input.MovieInput;
import input.Input;
import input.ActionInput;
import input.UserInput;
import input.NotificationInput;
import output.CommandOutput;
import output.Output;
import static constants.Constants.DELETE_MESSAGE;
import static constants.Constants.PREMIUM;
import static constants.Constants.STANDARD;


public final class Delete {
    private Delete() {

    }

    /**
     * delets movie from database
     * @param input  input structure
     * @param output  output structure
     * @param action  action input
     */
    public static void deleteMovie(final Input input, final Output output,
                                   final ActionInput action) {
        // check to see if movie exists in database
        boolean movieExists = false;
        for (MovieInput movie : input.getMovies()) {
            if (movie.getName().equals(action.getDeletedMovie())) {
                movieExists = true;
                break;
            }
        }
        if (!movieExists) {
            output.getOutput().add(new CommandOutput());
            return;
        }

        // remove movie in database
        input.getMovies().removeIf(movie -> movie.getName().equals(action.getDeletedMovie()));


        // notify all conform users
        for (UserInput user : input.getUsers()) {
            for (MovieInput movie : user.getPurchasedMovies()) {
                if (movie.getName().equals(action.getDeletedMovie())) {
                    // notify the deletion to the user
                    user.getNotifications().add(new NotificationInput(action.getDeletedMovie(),
                            DELETE_MESSAGE));

                    // delete movie from its lists
                    user.getPurchasedMovies().removeIf(movieInput -> movieInput.getName().equals(
                            action.getDeletedMovie()));
                    user.getWatchedMovies().removeIf(movieInput -> movieInput.getName().equals(
                            action.getDeletedMovie()));
                    user.getLikedMovies().removeIf(movieInput -> movieInput.getName().equals(
                            action.getDeletedMovie()));
                    user.getRatedMovies().removeIf(movieInput -> movieInput.getName().equals(
                            action.getDeletedMovie()));

                    // give back credits or free movie to user
                    if (user.getCredentials().getAccountType().equals(PREMIUM)) {
                        user.setNumFreePremiumMovies(user.getNumFreePremiumMovies() + 1);
                    } else if (user.getCredentials().getAccountType().equals(STANDARD)) {
                        user.setTokensCount(user.getTokensCount() + 2);
                    }

                    break;
                }
            }
        }


    }
}
