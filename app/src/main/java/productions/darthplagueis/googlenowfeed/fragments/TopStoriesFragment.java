package productions.darthplagueis.googlenowfeed.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
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
import productions.darthplagueis.googlenowfeed.api.Token;
import productions.darthplagueis.googlenowfeed.controller.TopStoriesAdapter;
import productions.darthplagueis.googlenowfeed.model.TopStories.Articles;
import productions.darthplagueis.googlenowfeed.model.TopStories.Results;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Displays the top stories.
 */
public class TopStoriesFragment extends Fragment {
    private final static String TAG = "RESULTS";
    private RecyclerView articleRecycler;
    private TopStoriesAdapter topStoriesAdapter;
    private LinearLayoutManager linearLayoutManager;
    private Retrofit retrofit;
    private boolean loadMoreArticles;
    private boolean loadNational;
    private boolean loadNyRegion;
    private boolean loadWorld;
    private static final String API_KEY = BuildConfig.API_KEY;
    private FloatingActionButton scrollToTop;

    public TopStoriesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_top_stories, container, false);

        articleRecycler = (RecyclerView) rootView.findViewById(R.id.top_stories_recycler);
        topStoriesAdapter = new TopStoriesAdapter(getContext());
        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        articleRecycler.setAdapter(topStoriesAdapter);
        articleRecycler.setHasFixedSize(true);
        articleRecycler.setLayoutManager(linearLayoutManager);

        scrollToTop(rootView);
        loadArticles();
        unlimitedPower();

        return rootView;
    }

    private void scrollToTop(View rootView) {
        scrollToTop = rootView.findViewById(R.id.scroll_to_top);
        scrollToTop.setSize(FloatingActionButton.SIZE_AUTO);
        scrollToTop.setVisibility(View.GONE);

        scrollToTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                articleRecycler.smoothScrollToPosition(0);
            }
        });
    }

    private void loadArticles() {
        articleRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int visibleItemCount = linearLayoutManager.getChildCount();
                int totalItemCount = linearLayoutManager.getItemCount();
                int pastVisibleItems = linearLayoutManager.findFirstVisibleItemPosition();

                if (dy <= 0) {
                    scrollToTop.setVisibility(View.VISIBLE);
                }

                if (dy > 0) {
                    if (loadMoreArticles) {
                        if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {
                            loadMoreArticles = false;
                            if (loadNational) {
                                getNationalTimesData();
                            } else if (loadNyRegion) {
                                getNyRegionTimesData();
                            } else if (loadWorld) {
                                getWorldTimesData();
                            }
                        }
                    }
                    scrollToTop.setVisibility(View.GONE);
                }
            }
        });
    }

    @NonNull
    private void unlimitedPower() {
        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.nytimes.com/svc/topstories/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        loadMoreArticles = false;
        loadNational = true;
        loadNyRegion = true;
        loadWorld = true;
        getTechTimesData();
    }

    private void getTechTimesData() {
        NewYorkTimesApi newYorkTimesApi = retrofit.create(NewYorkTimesApi.class);
        Call<Articles> articlesCall = newYorkTimesApi.getTechArticles(API_KEY);
        articlesCall.enqueue(new Callback<Articles>() {
            @Override
            public void onResponse(Call<Articles> call, retrofit2.Response<Articles> response) {
                loadMoreArticles = true;
                if (response.isSuccessful()) {
                    Articles articles = response.body();
                    List<Results> resultsList = articles.getResults();
                    topStoriesAdapter.listResults(resultsList);
                    Log.d(TAG, "onResponse: " + resultsList.size());
                }
            }

            @Override
            public void onFailure(Call<Articles> call, Throwable t) {
                loadMoreArticles = true;
                Log.d(TAG, "onFailure: " + call.request());
                t.printStackTrace();
            }
        });
    }

    private void getNationalTimesData() {
        NewYorkTimesApi newYorkTimesApi = retrofit.create(NewYorkTimesApi.class);
        Call<Articles> articlesCall = newYorkTimesApi.getNationalArticles(API_KEY);
        articlesCall.enqueue(new Callback<Articles>() {
            @Override
            public void onResponse(Call<Articles> call, retrofit2.Response<Articles> response) {
                loadMoreArticles = true;
                loadNational = false;
                if (response.isSuccessful()) {
                    Articles articles = response.body();
                    List<Results> resultsList = articles.getResults();
                    topStoriesAdapter.listResults(resultsList);
                    Log.d(TAG, "onResponse: " + resultsList.size());
                }
            }

            @Override
            public void onFailure(Call<Articles> call, Throwable t) {
                loadMoreArticles = true;
                Log.d(TAG, "onFailure: " + call.request());
                t.printStackTrace();
            }
        });
    }

    private void getNyRegionTimesData() {
        NewYorkTimesApi newYorkTimesApi = retrofit.create(NewYorkTimesApi.class);
        Call<Articles> articlesCall = newYorkTimesApi.getNyRegionArticles(API_KEY);
        articlesCall.enqueue(new Callback<Articles>() {
            @Override
            public void onResponse(Call<Articles> call, retrofit2.Response<Articles> response) {
                loadMoreArticles = true;
                loadNyRegion = false;
                if (response.isSuccessful()) {
                    Articles articles = response.body();
                    List<Results> resultsList = articles.getResults();
                    topStoriesAdapter.listResults(resultsList);
                    Log.d(TAG, "onResponse: " + resultsList.size());
                }
            }

            @Override
            public void onFailure(Call<Articles> call, Throwable t) {
                loadMoreArticles = true;
                Log.d(TAG, "onFailure: " + call.request());
                t.printStackTrace();
            }
        });
    }

    private void getWorldTimesData() {
        NewYorkTimesApi newYorkTimesApi = retrofit.create(NewYorkTimesApi.class);
        Call<Articles> articlesCall = newYorkTimesApi.getWorldArticles(API_KEY);
        articlesCall.enqueue(new Callback<Articles>() {
            @Override
            public void onResponse(Call<Articles> call, retrofit2.Response<Articles> response) {
                loadWorld = false;
                if (response.isSuccessful()) {
                    Articles articles = response.body();
                    List<Results> resultsList = articles.getResults();
                    topStoriesAdapter.listResults(resultsList);
                    Log.d(TAG, "onResponse: " + resultsList.size());
                }
            }

            @Override
            public void onFailure(Call<Articles> call, Throwable t) {
                Log.d(TAG, "onFailure: " + call.request());
                t.printStackTrace();
            }
        });
    }

}
