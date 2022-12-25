package momentary;
import input.UserInput;

public class UserNow {
    private UserInput user;
    private static UserNow instance = null;

    /**
     * singleton getter
     * @return - singleton instance
     */
    public static UserNow getInstance() {
        if (instance == null) {
            instance = new UserNow();
        }
        return instance;
    }


    /**
     * getter for user
     * @return - user
     */
    public UserInput getUser() {
        return user;
    }


    /**
     * setter for use
     * @param user - user
     */
    public void setUser(final UserInput user) {
        this.user = user;
    }

}
