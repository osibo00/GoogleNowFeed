package productions.darthplagueis.googlenowfeed.model.Timeswire;

import com.google.gson.annotations.SerializedName;

/**
 * Created by oleg on 12/15/17.
 */

public class Results {
    private String section;
    private String title;
    @SerializedName("abstract")
    private String abstract_string;
    private String url;
    private String byline;
    private String published_date;
    private String thumbnail_standard;

    public String getSection() {
        return section;
    }

    public String getTitle() {
        return title;
    }

    public String getAbstract_string() {
        return abstract_string;
    }

    public String getUrl() {
        return url;
    }

    public String getByline() {
        return byline;
    }

    public String getPublished_date() {
        return published_date;
    }

    public String getThumbnail_standard() {
        return thumbnail_standard;
    }
}
