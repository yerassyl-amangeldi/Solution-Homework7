public class Main {
    public static void main(String[] args) {
        //set up a series with one season
        Series series = new Series();
        Season season1 = new Season();
        season1.addEpisode(new Episode("Pilot", 1800));
        season1.addEpisode(new Episode("Episode 2", 1700));
        season1.addEpisode(new Episode("Episode 3", 1750));
        series.addSeason(season1);

        //normal order with for-each
        System.out.println("Normal order:");
        for (Episode episode : season1) {
            System.out.println(" - " + episode.getTitle());
        }

        //reverse order
        System.out.println("\nReverse order: ");
        EpisodeIterator reverseIterator = season1.reverseIterator();
        while (reverseIterator.hasNext()) {
            System.out.println(" - " + reverseIterator.next().getTitle());
        }

        //shuffle order with seed
        System.out.println("\nShuffle order (seed=42): ");
        EpisodeIterator shuffleIterator = season1.shuffleIterator(42);
        while (shuffleIterator.hasNext()) {
            System.out.println(" - " + shuffleIterator.next().getTitle());
        }
    }
}