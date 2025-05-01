import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Season implements Iterable<Episode> {
    private final List<Episode> episodes = new ArrayList<>();

    public void addEpisode(Episode episode) {
        episodes.add(episode);
    }

    @Override
    public Iterator<Episode> iterator() {
        return new SeasonIterator(this);
    }

    //package access for iterators
    List<Episode> getEpisodes() {
        return episodes;
    }
}