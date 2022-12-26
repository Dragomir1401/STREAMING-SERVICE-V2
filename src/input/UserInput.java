package input;

import java.util.List;
import java.util.ArrayList;

import static constants.Constants.NUM_FREE_PREMIUM_MOVIES;

public class UserInput {
    private CredentialsInput credentials;
    private List<MovieInput> purchasedMovies = new ArrayList<>();
    private List<MovieInput> watchedMovies = new ArrayList<>();
    private List<MovieInput> likedMovies = new ArrayList<>();
    private List<MovieInput> ratedMovies = new ArrayList<>();
    private int tokensCount;
    private int numFreePremiumMovies = NUM_FREE_PREMIUM_MOVIES;
    private List<NotificationInput> notifications = new ArrayList<>();

    public UserInput() {

    }

    public UserInput(final UserInput userInput) {
        this.credentials = new CredentialsInput();
        this.credentials.setAccountType(userInput.getCredentials().getAccountType());
        this.credentials.setPassword(userInput.getCredentials().getPassword());
        this.credentials.setBalance(userInput.getCredentials().getBalance());
        this.credentials.setCountry(userInput.getCredentials().getCountry());
        this.numFreePremiumMovies = userInput.getNumFreePremiumMovies();
        this.credentials.setName(userInput.getCredentials().getName());
        this.watchedMovies = new ArrayList<>();
        this.purchasedMovies = new ArrayList<>();
        this.likedMovies = new ArrayList<>();
        this.ratedMovies = new ArrayList<>();
        this.notifications = new ArrayList<>();
        this.notifications.addAll(userInput.getNotifications());
        this.purchasedMovies.addAll(userInput.getPurchasedMovies());
        this.watchedMovies.addAll(userInput.getWatchedMovies());
        this.likedMovies.addAll(userInput.getLikedMovies());
        this.ratedMovies.addAll(userInput.getRatedMovies());
        this.tokensCount = userInput.getTokensCount();
    }

    public UserInput(final CredentialsInput credentialsInput) {
        this.credentials = new CredentialsInput();
        this.credentials.setName(credentialsInput.getName());
        this.credentials.setAccountType(credentialsInput.getAccountType());
        this.credentials.setBalance(credentialsInput.getBalance());
        this.credentials.setCountry(credentialsInput.getCountry());
        this.credentials.setPassword(credentialsInput.getPassword());
        this.purchasedMovies = new ArrayList<>();
        this.watchedMovies = new ArrayList<>();
        this.likedMovies = new ArrayList<>();
        this.ratedMovies = new ArrayList<>();
        this.notifications = new ArrayList<>();
        this.numFreePremiumMovies = NUM_FREE_PREMIUM_MOVIES;
        this.tokensCount = 0;
    }


    /**
     * rests user instance
     */
    public void resetUser() {
        this.credentials = new CredentialsInput();
        this.purchasedMovies = new ArrayList<>();
        this.watchedMovies = new ArrayList<>();
        this.likedMovies = new ArrayList<>();
        this.ratedMovies = new ArrayList<>();
        this.notifications = new ArrayList<>();
        this.tokensCount = 0;
        this.numFreePremiumMovies = NUM_FREE_PREMIUM_MOVIES;
    }


    /**
     * getter for credentials
     * @return  credentials
     */
    public CredentialsInput getCredentials() {
        return credentials;
    }


    /**
     * setter for credentials
     * @param credentials  credentials
     */
    public void setCredentials(final CredentialsInput credentials) {
        this.credentials = credentials;
    }


    /**
     * getter for purchased movies
     * @return  purchased movies
     */
    public List<MovieInput> getPurchasedMovies() {
        return purchasedMovies;
    }


    /**
     * setter for purchased movies
     * @param purchasedMovies  purchased movies
     */
    public void setPurchasedMovies(final List<MovieInput> purchasedMovies) {
        this.purchasedMovies = purchasedMovies;
    }


    /**
     * getter for watched movies
     * @return  movies
     */
    public List<MovieInput> getWatchedMovies() {
        return watchedMovies;
    }


    /**
     * setter for watched movies
     * @param watchedMovies  watched movies
     */
    public void setWatchedMovies(final List<MovieInput> watchedMovies) {
        this.watchedMovies = watchedMovies;
    }


    /**
     * getter for liked movies
     * @return  liked movies
     */
    public List<MovieInput> getLikedMovies() {
        return likedMovies;
    }


    /**
     * setter for liked movies
     * @param likedMovies  liked movies
     */
    public void setLikedMovies(final List<MovieInput> likedMovies) {
        this.likedMovies = likedMovies;
    }


    /**
     * getter for rated movies
     * @return  rated movies
     */
    public List<MovieInput> getRatedMovies() {
        return ratedMovies;
    }


    /**
     * setter for rated movies
     * @param ratedMovies  rated movies
     */
    public void setRatedMovies(final List<MovieInput> ratedMovies) {
        this.ratedMovies = ratedMovies;
    }


    /**
     * getter for tokens count
     * @return  tokens count
     */
    public int getTokensCount() {
        return tokensCount;
    }


    /**
     * setter for tokens count
     * @param tokensCount  tokens count
     */
    public void setTokensCount(final int tokensCount) {
        this.tokensCount = tokensCount;
    }


    /**
     * getter for num free premium movies
     * @return  free premium movies
     */
    public int getNumFreePremiumMovies() {
        return numFreePremiumMovies;
    }


    /**
     * setter for num of free premium movies
     * @param numFreePremiumMovies  num of free movies
     */
    public void setNumFreePremiumMovies(final int numFreePremiumMovies) {
        this.numFreePremiumMovies = numFreePremiumMovies;
    }

    /**
     * getter for notifications list
     * @return  notifiactions list
     */
    public List<NotificationInput> getNotifications() {
        return notifications;
    }

    /**
     * setter for notifications list
     * @param notifications  notifications
     */
    public void setNotifications(List<NotificationInput> notifications) {
        this.notifications = notifications;
    }
}
