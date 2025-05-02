import java.util.Set;

/**
 * Decorator iterator that skips episodes already watched.
 */
public class WatchHistoryFilterIterator implements EpisodeIterator {
    private final EpisodeIterator iterator;
    private final Set<Episode> watchedEpisodes;
    private Episode nextUnwatched;

    public WatchHistoryFilterIterator(EpisodeIterator iterator, Set<Episode> watchedEpisodes) {
        this.iterator = iterator;
        this.watchedEpisodes = watchedEpisodes;
        this.nextUnwatched = findNextUnwatched();
        //filter out watched episodes
    }

    @Override
    public boolean hasNext() {
        return nextUnwatched != null;
        //got an unwatched episode ready?
    }

    @Override
    public Episode next() {
        if (!hasNext()) {
            throw new IllegalStateException("No unwatched episodes");
        }
        Episode result = nextUnwatched;
        nextUnwatched = findNextUnwatched();
        return result;
        //return unwatched episode and prep next
    }

    private Episode findNextUnwatched() {
        while (iterator.hasNext()) {
            Episode next = iterator.next();
            if (!watchedEpisodes.contains(next)) {
                return next;
            }
        }
        return null;
        //hunt for next unwatched episode
    }
}