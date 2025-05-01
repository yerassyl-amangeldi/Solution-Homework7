import java.util.ArrayList;
import java.util.List;

public class Series {
    private final List<Season> seasons = new ArrayList<>();

    public void addSeason(Season season) {
        seasons.add(season);
        //toss season into list
    }

    public List<Season> getSeasons() {
        return seasons;
    }
}