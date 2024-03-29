package sorters;

import recommendation.RecommendationElement;
import java.util.List;

public interface RecommendationSort {
    /**
     * runs sorters
     * @param likedGenres  genres list
     * @param parameter  increasing/decreasing
     */
    void run(List<RecommendationElement> likedGenres, String parameter);
}
