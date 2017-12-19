package productions.darthplagueis.googlenowfeed.model.MovieReviews;

/**
 * Created by c4q on 12/18/17.
 */

public class Results {
    private String display_title;
    private String byline;
    private String headline;
    private String summary_short;
    private String publication_date;
    private Link link;
    private Multimedia multimedia;

    public String getDisplay_title() {
        return display_title;
    }

    public String getByline() {
        return byline;
    }

    public String getHeadline() {
        return headline;
    }

    public String getSummary_short() {
        return summary_short;
    }

    public String getPublication_date() {
        return publication_date;
    }

    public Link getLink() {
        return link;
    }

    public Multimedia getMultimedia() {
        return multimedia;
    }
}
