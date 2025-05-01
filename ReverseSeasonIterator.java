import java.util.List;

/**
 * Iterator for traversing episodes in reverse order.
 */
public class ReverseSeasonIterator implements EpisodeIterator {
    private final List<Episode> episodes;
    private int currentIndex;

    public ReverseSeasonIterator(Season season) {
        this.episodes = season.getEpisodes();
        this.currentIndex = episodes.size() - 1;
        //start from the end
    }

    @Override
    public boolean hasNext() {
        return currentIndex >= 0;
        //still got episodes?
    }

    @Override
    public Episode next() {
        if (!hasNext()) {
            throw new IllegalStateException("No more episodes");
        }
        return episodes.get(currentIndex--);
        //grab current and go backward
    }
}