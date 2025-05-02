import java.util.List;

/**
 * Iterator that chains all seasons of a series for continuous playback.
 */
public class BingeIterator implements EpisodeIterator {
    private final List<Season> seasons;
    private int currentSeasonIndex;
    private EpisodeIterator currentSeasonIterator;

    public BingeIterator(Series series) {
        this.seasons = series.getSeasons();
        this.currentSeasonIndex = 0;
        this.currentSeasonIterator = seasons.isEmpty() ? null : new SeasonIterator(seasons.get(0));
        //start with first season or nothing
    }

    @Override
    public boolean hasNext() {
        while (currentSeasonIterator != null && !currentSeasonIterator.hasNext()) {
            currentSeasonIndex++;
            if (currentSeasonIndex < seasons.size()) {
                currentSeasonIterator = new SeasonIterator(seasons.get(currentSeasonIndex));
            } else {
                currentSeasonIterator = null;
            }
        }
        return currentSeasonIterator != null;
        //keep going until no seasons left
    }

    @Override
    public Episode next() {
        if (!hasNext()) {
            throw new IllegalStateException("No more episodes");
        }
        return currentSeasonIterator.next();
        //get next episode from current season
    }
}