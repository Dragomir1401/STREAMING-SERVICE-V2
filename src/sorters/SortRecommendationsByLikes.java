package sorters;

import subscribers.RecommendationElement;

import java.util.Comparator;
import java.util.List;

import static constants.Constants.DECREASING;
import static constants.Constants.INCREASING;

public class SortRecommendationsByLikes implements RecommendationSort {
    @Override
    public void run(List<RecommendationElement> likedGenres, String parameter) {
        if (parameter.equals(INCREASING)) {
            likedGenres.sort((o1, o2) -> {
                int cmp = o1.getLikeCount() - o2.getLikeCount();
                if (cmp != 0) {
                    return -cmp;
                } else {
                    cmp = o1.getGenre().compareTo(o2.getGenre());
                }
                return cmp;
            });
            return;
        }

        if (parameter.equals(DECREASING)) {
            likedGenres.sort((o1, o2) -> {
                int cmp = o1.getLikeCount() - o2.getLikeCount();
                if (cmp != 0) {
                    return cmp;
                } else {
                    cmp = o1.getGenre().compareTo(o2.getGenre());
                }
                return -cmp;
            });
        }

    }
}
