/**
 * Wrapper for Episode with an offset for playback.
 */
public class EpisodeView {
    private final Episode episode;
    private final int startOffsetSec;

    public EpisodeView(Episode episode, int startOffsetSec) {
        this.episode = episode;
        this.startOffsetSec = startOffsetSec;
        //wrap episode with start time
    }

    public void play() {
        System.out.println("Playing '" + episode.getTitle() + "' from " + startOffsetSec + " seconds");
        //play from offset
    }
}