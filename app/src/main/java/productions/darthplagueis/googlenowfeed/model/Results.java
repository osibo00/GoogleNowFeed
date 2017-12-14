package productions.darthplagueis.googlenowfeed.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by oleg on 12/12/17.
 */

public class Results {
    private String section;
    private String title;
    private String url;
    private String byline;
    @SerializedName("abstract")
    private String abstract_string;
    private String published_date;
    private Multimedia[] multimedia;

    public String getSection() {
        return section;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }


    public String getByline() {
        return byline;
    }

    public String getAbstract_string() {
        return abstract_string;
    }

    public String getPublished_date() {
        return published_date;
    }

    public Multimedia[] getMultimedia() {
        return multimedia;
    }
}
