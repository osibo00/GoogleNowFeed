package productions.darthplagueis.googlenowfeed.model;

/**
 * Created by ashley on 12/19/17.
 */

public class Bookmark {
    private String section;
    private String title;
    private String abstract_string;
    private String url;
    private String byline;
    private String published_date;
    private String thumbnail;

    public Bookmark(String section, String title, String abstract_string, String byline, String published_date, String url, String thumbnail) {
        this.section = section;
        this.title = title;
        this.abstract_string = abstract_string;
        this.url = url;
        this.byline = byline;
        this.published_date = published_date;
        this.thumbnail = thumbnail;
    }

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

    public String getThumbnail() {
        return thumbnail;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAbstract_string(String abstract_string) {
        this.abstract_string = abstract_string;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setByline(String byline) {
        this.byline = byline;
    }

    public void setPublished_date(String published_date) {
        this.published_date = published_date;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
