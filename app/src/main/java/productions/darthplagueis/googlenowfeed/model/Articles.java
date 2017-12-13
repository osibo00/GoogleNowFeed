package productions.darthplagueis.googlenowfeed.model;

/**
 * Created by oleg on 12/12/17.
 */

public class Articles {
    private String status;
    private String copyright;
    private int num_results;
    private Results[] results;

    public String getStatus() {
        return status;
    }

    public String getCopyright() {
        return copyright;
    }

    public int getNum_results() {
        return num_results;
    }

    public Results[] getResults() {
        return results;
    }
}
