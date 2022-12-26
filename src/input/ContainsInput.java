package input;
import java.util.List;

public class ContainsInput {
    private List<String> actors;
    private List<String> genre;
    private List<String> country;



    /**
     * getter for actors
     * @return  actors
     */
    public List<String> getActors() {
        return actors;
    }



    /**
     * setter for actors
     * @param actors  actors
     */
    public void setActors(final List<String> actors) {
        this.actors = actors;
    }



    /**
     * getter for genre
     * @return  genre
     */
    public List<String> getGenre() {
        return genre;
    }



    /**
     * getter for country
     * @return  country
     */
    public List<String> getCountry() {
        return country;
    }



    /**
     * setter for country
     * @param country  country
     */
    public void setCountry(final List<String> country) {
        this.country = country;
    }
}
