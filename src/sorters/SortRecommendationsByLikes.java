package sorters;

import subscribers.RecommendationElement;
import java.util.Comparator;
import java.util.List;
import static constants.Constants.DECREASING;
import static constants.Constants.INCREASING;

public class SortRecommendationsByLikes implements RecommendationSort {

    /**
     * runs sorter
     * @param likedGenres  genres list
     * @param parameter  increasing/decreasing
     */
    @Override
    public void run(final List<RecommendationElement> likedGenres, final String parameter) {
        if (parameter.equals(INCREASING)) {
            likedGenres.sort(Comparator.comparingInt(RecommendationElement::getLikeCount));
            return;
        }

        if (parameter.equals(DECREASING)) {
            likedGenres.sort((o1, o2) -> o2.getLikeCount() - o1.getLikeCount());
        }

    }
}
