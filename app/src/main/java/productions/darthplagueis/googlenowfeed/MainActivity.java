package productions.darthplagueis.googlenowfeed;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
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
    private LinearLayoutManager linearLayoutManager;
    private Retrofit retrofit;
    private int offset;
    private boolean loadMoreArticles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        articleRecycler = (RecyclerView) findViewById(R.id.main_recycler);
        linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        articleRecycler.setHasFixedSize(true);
        articleRecycler.setLayoutManager(linearLayoutManager);

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

                if (dy > 0) {
                    if (loadMoreArticles) {
                        if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {
                            offset += 19;
                            loadMoreArticles = false;
                            getTimesData(offset);
                        }
                    }
                }
            }
        });
    }

    private void getTimesData(int offset) {
        NewYorkTimesApi newYorkTimesApi = retrofit.create(NewYorkTimesApi.class);
        Call<Articles> articlesCall = newYorkTimesApi.getAllArticles("39d64cbc2574413981aa95276470b20d", 20, offset);
        articlesCall.enqueue(new Callback<Articles>() {
            @Override
            public void onResponse(Call<Articles> call, retrofit2.Response<Articles> response) {
                loadMoreArticles = true;
                if (response.isSuccessful()) {
                    Articles articles = response.body();
                    List<Results> resultsList = new ArrayList<>();
                    Collections.addAll(resultsList, articles.getResults());

                    articleRecycler.setAdapter(new ArticlesAdapter(resultsList));
                    Log.d(TAG, "onResponse: " + response.body());
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

    @NonNull
    private void unlimitedPower() {
        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.nytimes.com/svc/news/v3/content/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        offset = 1;
        loadMoreArticles = false;
        getTimesData(offset);
    }
}
