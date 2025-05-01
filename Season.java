import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Season implements Iterable<Episode> {
    private final List<Episode> episodes = new ArrayList<>();

    public void addEpisode(Episode episode) {
        episodes.add(episode);
        //add episode to the list
    }

    @Override
    public Iterator<Episode> iterator() {
        return (Iterator<Episode>) new SeasonIterator(this);
        //default is normal iterator
    }

    public EpisodeIterator reverseIterator() {
        return new ReverseSeasonIterator(this);
        //gimme reverse order
    }

    public EpisodeIterator shuffleIterator(long seed) {
        return new ShuffleSeasonIterator(this, seed);
        //random order with seed
    }

    //package access for iterators
    List<Episode> getEpisodes() {
        return episodes;
    }
}