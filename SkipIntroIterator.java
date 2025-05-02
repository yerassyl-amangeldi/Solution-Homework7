/**
 * Decorator iterator that skips the intro of each episode.
 */
public class SkipIntroIterator implements EpisodeIterator {
    private final EpisodeIterator iterator;
    private final int introLengthSec;

    public SkipIntroIterator(EpisodeIterator iterator, int introLengthSec) {
        this.iterator = iterator;
        this.introLengthSec = introLengthSec;
        //wrap iterator to skip intros
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
        //just pass through
    }

    @Override
    public Episode next() {
        return iterator.next();
        //pass episode through, client handles view
    }

    public int getIntroLengthSec() {
        return introLengthSec;
        //give intro length for view
    }
}