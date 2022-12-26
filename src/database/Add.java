package database;

import input.*;
import momentary.PageNow;
import output.CommandOutput;
import output.Output;

import static constants.Constants.ADD_MESSAGE;

public final class Add {

    public Add() {

    }
    public static void addMovie(Input input, Output output, ActionInput action) {
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
            if (!action.getAddedMovie().getCountriesBanned().contains(user.getCredentials().getCountry())) {
                for (String genre : action.getAddedMovie().getGenres()) {
                    if (user.getSubscribedGenre().contains(genre)) {
                        user.getNotifications().add(new NotificationInput(action.getAddedMovie().getName(), ADD_MESSAGE));
                        break;
                    }
                }
            }
        }



    }

}
