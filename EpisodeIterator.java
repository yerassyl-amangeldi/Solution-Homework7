import java.util.Iterator;

public interface EpisodeIterator extends Iterator<Episode> {
    boolean hasNext();
    Episode next();
    //bare minimum for iterator
}