package productions.darthplagueis.googlenowfeed.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import productions.darthplagueis.googlenowfeed.BuildConfig;
import productions.darthplagueis.googlenowfeed.R;
import productions.darthplagueis.googlenowfeed.api.NewYorkTimesApi;
import productions.darthplagueis.googlenowfeed.controller.MovieReviewsAdapter;
import productions.darthplagueis.googlenowfeed.model.MovieReviews.Articles;
import productions.darthplagueis.googlenowfeed.model.MovieReviews.Results;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieReviewsFragment extends Fragment {
    private final static String TAG = "RESULTS";
    private RecyclerView movieRecyclerView;
    private LinearLayoutManager linearLayoutManager;
    private MovieReviewsAdapter movieReviewsAdapter;
    private Retrofit retrofit;
    private int offset;
    private Boolean loadMoreArticles;
    private static final String API_KEY = BuildConfig.API_KEY;

    private View rootView;


    public MovieReviewsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_movie_reviews, container, false);
        movieRecyclerView = (RecyclerView) rootView.findViewById(R.id.movie_recycler);
        movieReviewsAdapter = new MovieReviewsAdapter(getContext());
        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        movieRecyclerView.setAdapter(movieReviewsAdapter);
        movieRecyclerView.setHasFixedSize(true);
        movieRecyclerView.setLayoutManager(linearLayoutManager);

        loadArticles();
        getRetroFit();

        return rootView;
    }

    private void loadArticles() {
        movieRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int visibleItemCount = linearLayoutManager.getChildCount();
                int totalItemCount = linearLayoutManager.getItemCount();
                int pastVisibleItems = linearLayoutManager.findFirstVisibleItemPosition();


                if (dy > 0) {
                    if (loadMoreArticles) {
                        if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {
                            loadMoreArticles = false;
                            offset += 20;
                            loadMovieReviews(offset);
                        }
                    }
                }
            }
        });
    }

    private void loadMovieReviews(int offset) {
        NewYorkTimesApi newYorkTimesApi = retrofit.create(NewYorkTimesApi.class);
        Call<Articles> articlesCall = newYorkTimesApi.getMovieReviewsArticles(API_KEY, offset);
        articlesCall.enqueue(new Callback<Articles>() {
            @Override
            public void onResponse(Call<Articles> call, Response<Articles> response) {
                loadMoreArticles = true;
                Articles articles = response.body();
                List<Results> resultsList = articles.getResults();
                movieReviewsAdapter.listResults(resultsList);
                Log.d(TAG, "onResponse: " + resultsList.size());
            }

            @Override
            public void onFailure(Call<Articles> call, Throwable t) {
                loadMoreArticles = true;
                Log.d(TAG, "onFailure: " + call.request());
                t.printStackTrace();
            }
        });
    }

    private void getRetroFit() {
        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.nytimes.com/svc/movies/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        loadMoreArticles = false;

        offset = 0;
        loadMovieReviews(offset);
    }


}
