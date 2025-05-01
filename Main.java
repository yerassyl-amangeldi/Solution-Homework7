public class Main {
    public static void main(String[] args) {
        //set up a series with one season
        Series series = new Series();
        Season season1 = new Season();
        season1.addEpisode(new Episode("Pilot", 1800));
        season1.addEpisode(new Episode("Episode 2", 1700));
        season1.addEpisode(new Episode("Episode 3", 1750));
        series.addSeason(season1);

        //watch season in order with for-each
        System.out.println("Watching season 1 in normal order:");
        for (Episode episode : season1) {
            System.out.println(" - " + episode.getTitle());
        }

        //try iterator manually
        System.out.println("\nUsing explicit iterator:");
        EpisodeIterator iterator = new SeasonIterator(season1);
        while (iterator.hasNext()) {
            System.out.println(" - " + iterator.next().getTitle());
        }
    }
}