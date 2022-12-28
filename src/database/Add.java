package database;

import input.Input;
import input.ActionInput;
import input.MovieInput;
import input.UserInput;
import input.NotificationInput;
import output.CommandOutput;
import output.Output;

import static constants.Constants.ADD_MESSAGE;

public final class Add {

    private Add() {

    }


    /**
     * adds movie in database
     * @param input  input structure
     * @param output  output structure
     * @param action  action structure
     */
    public static void addMovie(final Input input, final Output output, final ActionInput action) {
        // check to see if movie already exists in database
        for (MovieInput movie : input.getMovies()) {
            if (movie.getName().equals(action.getAddedMovie().getName())) {
                output.getOutput().add(new CommandOutput());
                return;
            }
        }

        // add movie in database
        input.getMovies().add(new MovieInput(action.getAddedMovie()));

        // notify all conform users
        for (UserInput user : input.getUsers()) {
            if (!action.getAddedMovie().getCountriesBanned().contains(
                    user.getCredentials().getCountry())) {
                for (String genre : action.getAddedMovie().getGenres()) {
                    if (user.getSubscribedGenre().contains(genre)) {
                        user.getNotifications().add(new NotificationInput(
                                action.getAddedMovie().getName(), ADD_MESSAGE));
                        break;
                    }
                }
            }
        }



    }

}
