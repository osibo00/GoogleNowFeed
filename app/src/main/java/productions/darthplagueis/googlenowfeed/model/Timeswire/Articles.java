package productions.darthplagueis.googlenowfeed.model.Timeswire;

import java.util.List;

/**
 * Created by oleg on 12/15/17.
 */

public class Articles {
    private String status;
    private String copyright;
    private int num_results;
    private List<Results> results;

    public String getStatus() {
        return status;
    }

    public String getCopyright() {
        return copyright;
    }

    public int getNum_results() {
        return num_results;
    }

    public List<Results> getResults() {
        return results;
    }
}
