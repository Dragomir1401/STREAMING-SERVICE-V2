package input;

import java.util.List;

public class Input {
    private List<UserInput> users;
    private List<MovieInput> movies;
    private List<ActionInput> actions;

    public Input() {

    }

    public Input(final List<UserInput> users, final List<MovieInput> movies,
                 final List<ActionInput> actions) {
        this.users = users;
        this.movies = movies;
        this.actions = actions;
    }


    /**
     * getter for users
     * @return - users
     */
    public List<UserInput> getUsers() {
        return users;
    }


    /**
     * setter for users
     * @param users - users
     */
    public void setUsers(final List<UserInput> users) {
        this.users = users;
    }


    /**
     * getter for movies
     * @return - movies
     */
    public List<MovieInput> getMovies() {
        return movies;
    }


    /**
     * setter for movies
     * @param movies - movies
     */
    public void setMovies(final List<MovieInput> movies) {
        this.movies = movies;
    }


    /**
     * getter for action
     * @return - action
     */
    public List<ActionInput> getActions() {
        return actions;
    }


    /**
     * setter for action
     * @param actions -actions
     */
    public void setActions(final List<ActionInput> actions) {
        this.actions = actions;
    }

}
