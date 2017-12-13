package productions.darthplagueis.googlenowfeed.api;

import productions.darthplagueis.googlenowfeed.model.Articles;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 * Created by oleg on 12/12/17.
 */

public interface NewYorkTimesApi {
    //https://api.nytimes.com/svc/news/v3/content/all/all.json?api-key=39d64cbc2574413981aa95276470b20d


//    @GET("all.json")
//    Call<Articles> getArticles(@Header("api-key") String apiKey, @Query("limit") int limit, @Query("offset") int offset);

    @GET("nyt/all.json")
    Call<Articles> getAllArticles(@Query("api-key") String apiKey, @Query("limit") int limit, @Query("offset") int offset);
}
