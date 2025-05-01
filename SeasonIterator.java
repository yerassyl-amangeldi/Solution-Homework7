import java.util.List;

/**
 * Iterator for traversing episodes in normal order.
 */
public class SeasonIterator implements EpisodeIterator {
    private final List<Episode> episodes;
    private int currentIndex;

    public SeasonIterator(Season season) {
        this.episodes = season.getEpisodes();
        this.currentIndex = 0;
        //grab episodes and start at zero
    }

    @Override
    public boolean hasNext() {
        return currentIndex < episodes.size();
        //any episodes left?
    }

    @Override
    public Episode next() {
        if (!hasNext()) {
            throw new IllegalStateException("No more episodes");
        }
        return episodes.get(currentIndex++);
        //give next one and bump index
    }
}