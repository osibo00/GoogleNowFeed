package productions.darthplagueis.googlenowfeed.api;

import productions.darthplagueis.googlenowfeed.model.TopStories.Articles;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by oleg on 12/12/17.
 */

public interface NewYorkTimesApi {

    @GET("technology.json")
    Call<Articles> getTechArticles(@Query("api-key") String apiKey);

    @GET("national.json")
    Call<Articles> getNationalArticles(@Query("api-key") String apiKey);

    @GET("world.json")
    Call<Articles> getWorldArticles(@Query("api-key") String apiKey);

    @GET("nyregion.json")
    Call<Articles> getNyRegionArticles(@Query("api-key") String apiKey);

    @GET("nyt/all.json")
    Call<productions.darthplagueis.googlenowfeed.model.Timeswire.Articles> getTimeswireArticles(@Query("api-key") String apiKey, @Query("limit") int limit, @Query("offset") int offset);

    @GET("reviews/picks.json")
    Call<productions.darthplagueis.googlenowfeed.model.MovieReviews.Articles> getMovieReviewsArticles(@Query("api-key") String apiKey, @Query("offset") int offset);
}
