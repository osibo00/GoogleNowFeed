package productions.darthplagueis.googlenowfeed.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by oleg on 12/12/17.
 */

public class Results {
    private String section;
    private String subsection;
    private String title;
    private String url;
    private String short_url;
    private String byline;
    private String thumbnail_standard;
    @SerializedName("abstract")
    private String abstract_string;
    private String source;
    private String published_date;
//    private Multimedia[] multimedia;

    public String getSection() {
        return section;
    }

    public String getSubsection() {
        return subsection;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getShort_url() {
        return short_url;
    }

    public String getByline() {
        return byline;
    }

    public String getThumbnail_standard() {
        return thumbnail_standard;
    }

    public String getAbstract_string() {
        return abstract_string;
    }

    public String getSource() {
        return source;
    }

    public String getPublished_date() {
        return published_date;
    }

    //    public List<Multimedia> getMultimedia() {
//        return multimedia;
//    }
}
