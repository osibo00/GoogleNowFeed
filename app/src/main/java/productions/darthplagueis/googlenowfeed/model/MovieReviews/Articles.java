package productions.darthplagueis.googlenowfeed.model.MovieReviews;

import java.util.List;

/**
 * Created by c4q on 12/18/17.
 */

public class Articles {
    private String status;
    private String copyright;
    private String has_more;
    private int num_results;
    private List<Results> results;

    public String getStatus() {
        return status;
    }

    public String getCopyright() {
        return copyright;
    }

    public String getHas_more() {
        return has_more;
    }

    public int getNum_results() {
        return num_results;
    }

    public List<Results> getResults() {
        return results;
    }
}
