import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        //set up series with two seasons
        Series series = new Series();
        Season season1 = new Season();
        season1.addEpisode(new Episode("S1E1: Pilot", 1800));
        season1.addEpisode(new Episode("S1E2: The Quest", 1700));
        season1.addEpisode(new Episode("S1E3: Finale", 1750));
        Season season2 = new Season();
        season2.addEpisode(new Episode("S2E1: Rebirth", 1800));
        season2.addEpisode(new Episode("S2E2: Clash", 1650));
        series.addSeason(season1);
        series.addSeason(season2);

        //normal order
        System.out.println("Normal order (Season 1):");
        for (Episode episode : season1) {
            System.out.println(" - " + episode.getTitle());
        }

        //reverse order
        System.out.println("\nReverse order (Season 1):");
        EpisodeIterator reverseIterator = season1.reverseIterator();
        while (reverseIterator.hasNext()) {
            System.out.println(" - " + reverseIterator.next().getTitle());
        }

        //shuffle order
        System.out.println("\nShuffle order (Season 1, seed=42):");
        EpisodeIterator shuffleIterator = season1.shuffleIterator(42);
        while (shuffleIterator.hasNext()) {
            System.out.println(" - " + shuffleIterator.next().getTitle());
        }

        //binge mode
        System.out.println("\nBinge mode (all seasons):");
        EpisodeIterator bingeIterator = new BingeIterator(series);
        while (bingeIterator.hasNext()) {
            System.out.println(" - " + bingeIterator.next().getTitle());
        }

        //skip intro
        System.out.println("\nBinge mode with Skip Intro (30s):");
        SkipIntroIterator skipIntroIterator = new SkipIntroIterator(new BingeIterator(series), 30);
        while (skipIntroIterator.hasNext()) {
            Episode episode = skipIntroIterator.next();
            EpisodeView view = new EpisodeView(episode, skipIntroIterator.getIntroLengthSec());
            view.play();
        }

        //unwatched episodes
        System.out.println("\nUnwatched episodes only:");
        Set<Episode> watched = new HashSet<>();
        watched.add(season1.getEpisodes().get(0)); //watched S1E1
        EpisodeIterator unwatchedIterator = new WatchHistoryFilterIterator(new BingeIterator(series), watched);
        while (unwatchedIterator.hasNext()) {
            System.out.println(" - " + unwatchedIterator.next().getTitle());
        }

        //performance test
        System.out.println("\nPerformance test with 10,000 episodes:");
        Series largeSeries = generateLargeSeries(10000);
        testIteratorPerformance(largeSeries);
    }

    private static Series generateLargeSeries(int episodeCount) {
        Series series = new Series();
        Season season = new Season();
        for (int i = 1; i <= episodeCount; i++) {
            season.addEpisode(new Episode("Episode " + i, 1800));
        }
        series.addSeason(season);
        return series;
        //make a ton of episodes
    }

    private static void testIteratorPerformance(Series series) {
        long startTime, endTime;

        //normal iterator
        startTime = System.nanoTime();
        EpisodeIterator normal = new SeasonIterator(series.getSeasons().get(0));
        while (normal.hasNext()) normal.next();
        endTime = System.nanoTime();
        System.out.println("Normal iterator: " + (endTime - startTime) / 1_000_000 + " ms");

        //reverse iterator
        startTime = System.nanoTime();
        EpisodeIterator reverse = series.getSeasons().get(0).reverseIterator();
        while (reverse.hasNext()) reverse.next();
        endTime = System.nanoTime();
        System.out.println("Reverse iterator: " + (endTime - startTime) / 1_000_000 + " ms");

        //shuffle iterator
        startTime = System.nanoTime();
        EpisodeIterator shuffle = series.getSeasons().get(0).shuffleIterator(42);
        while (shuffle.hasNext()) shuffle.next();
        endTime = System.nanoTime();
        System.out.println("Shuffle iterator: " + (endTime - startTime) / 1_000_000 + " ms");
        //time each iterator
    }
}