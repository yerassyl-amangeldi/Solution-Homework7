import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Iterator for traversing episodes in random order with a fixed seed.
 */
public class ShuffleSeasonIterator implements EpisodeIterator {
    private final List<Episode> shuffledEpisodes;
    private int currentIndex;

    public ShuffleSeasonIterator(Season season, long seed) {
        this.shuffledEpisodes = new ArrayList<>(season.getEpisodes());
        Collections.shuffle(shuffledEpisodes, new Random(seed));
        this.currentIndex = 0;
        //copy list, shuffle it, start at zero
    }

    @Override
    public boolean hasNext() {
        return currentIndex < shuffledEpisodes.size();
        //more to go?
    }

    @Override
    public Episode next() {
        if (!hasNext()) {
            throw new IllegalStateException("No more episodes");
        }
        return shuffledEpisodes.get(currentIndex++);
        //next shuffled episode
    }
}