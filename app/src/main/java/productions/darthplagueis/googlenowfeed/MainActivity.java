package productions.darthplagueis.googlenowfeed;

import android.content.res.ColorStateList;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.List;

import productions.darthplagueis.googlenowfeed.api.NewYorkTimesApi;
import productions.darthplagueis.googlenowfeed.controller.ArticlesAdapter;
import productions.darthplagueis.googlenowfeed.model.Articles;
import productions.darthplagueis.googlenowfeed.model.Results;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private final static String TAG = "RESULTS";
    private RecyclerView articleRecycler;
    private ArticlesAdapter articlesAdapter;
    private LinearLayoutManager linearLayoutManager;
    private Retrofit retrofit;
    private boolean loadMoreArticles;
    private boolean loadNational;
    private boolean loadNyRegion;
    private boolean loadWorld;
    private String apiKey = "39d64cbc2574413981aa95276470b20d";
    private FloatingActionButton scrollToTop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        articleRecycler = (RecyclerView) findViewById(R.id.main_recycler);
        articlesAdapter = new ArticlesAdapter(getApplicationContext());
        linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        articleRecycler.setAdapter(articlesAdapter);
        articleRecycler.setHasFixedSize(true);
        articleRecycler.setLayoutManager(linearLayoutManager);

        scrollToTop();

        loadArticles();

        unlimitedPower();
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

    private void getTechTimesData() {
        NewYorkTimesApi newYorkTimesApi = retrofit.create(NewYorkTimesApi.class);
        Call<Articles> articlesCall = newYorkTimesApi.getTechArticles(apiKey);
        articlesCall.enqueue(new Callback<Articles>() {
            @Override
            public void onResponse(Call<Articles> call, retrofit2.Response<Articles> response) {
                loadMoreArticles = true;
                if (response.isSuccessful()) {
                    Articles articles = response.body();
                    List<Results> resultsList = articles.getResults();
                    articlesAdapter.listResults(resultsList);
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
        Call<Articles> articlesCall = newYorkTimesApi.getNationalArticles(apiKey);
        articlesCall.enqueue(new Callback<Articles>() {
            @Override
            public void onResponse(Call<Articles> call, retrofit2.Response<Articles> response) {
                loadMoreArticles = true;
                loadNational = false;
                if (response.isSuccessful()) {
                    Articles articles = response.body();
                    List<Results> resultsList = articles.getResults();
                    articlesAdapter.listResults(resultsList);
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
        Call<Articles> articlesCall = newYorkTimesApi.getNyRegionArticles(apiKey);
        articlesCall.enqueue(new Callback<Articles>() {
            @Override
            public void onResponse(Call<Articles> call, retrofit2.Response<Articles> response) {
                loadMoreArticles = true;
                loadNyRegion = false;
                if (response.isSuccessful()) {
                    Articles articles = response.body();
                    List<Results> resultsList = articles.getResults();
                    articlesAdapter.listResults(resultsList);
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
        Call<Articles> articlesCall = newYorkTimesApi.getWorldArticles(apiKey);
        articlesCall.enqueue(new Callback<Articles>() {
            @Override
            public void onResponse(Call<Articles> call, retrofit2.Response<Articles> response) {
                loadWorld = false;
                if (response.isSuccessful()) {
                    Articles articles = response.body();
                    List<Results> resultsList = articles.getResults();
                    articlesAdapter.listResults(resultsList);
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

    private void scrollToTop() {
        scrollToTop = findViewById(R.id.scroll_to_top);
        scrollToTop.setSize(FloatingActionButton.SIZE_AUTO);
        scrollToTop.setVisibility(View.GONE);

        scrollToTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                articleRecycler.smoothScrollToPosition(0);
            }
        });
    }
}
