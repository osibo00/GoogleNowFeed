package productions.darthplagueis.googlenowfeed.fragments;


import android.content.Intent;
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
import android.widget.LinearLayout;

import com.github.rubensousa.floatingtoolbar.FloatingToolbar;

import java.util.List;

import productions.darthplagueis.googlenowfeed.BookmarksActivity;
import productions.darthplagueis.googlenowfeed.BuildConfig;
import productions.darthplagueis.googlenowfeed.R;
import productions.darthplagueis.googlenowfeed.api.NewYorkTimesApi;
import productions.darthplagueis.googlenowfeed.controller.TimeswireAdapter;
import productions.darthplagueis.googlenowfeed.model.Timeswire.Articles;
import productions.darthplagueis.googlenowfeed.model.Timeswire.Results;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class TimeswireFragment extends Fragment {
    private final static String TAG = "RESULTS";
    private RecyclerView articleRecycler;
    private TimeswireAdapter timeswireAdapter;
    private LinearLayoutManager linearLayoutManager;
    private Retrofit retrofit;
    private boolean loadMoreArticles;
    private int offset;
    private static final String API_KEY = BuildConfig.API_KEY;


    public TimeswireFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_timeswire, container, false);
        articleRecycler = (RecyclerView) rootView.findViewById(R.id.timeswire_recycler);
        timeswireAdapter = new TimeswireAdapter(getContext());
        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        articleRecycler.setAdapter(timeswireAdapter);
        articleRecycler.setHasFixedSize(true);
        articleRecycler.setLayoutManager(linearLayoutManager);

        floatingToolBar(rootView);
        loadArticles();
        unlimitedPower();

        return rootView;
    }

    private void floatingToolBar(View rootView) {
        FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
        FloatingToolbar floatingToolbar = (FloatingToolbar) rootView.findViewById(R.id.floating_toolbar);
        floatingToolbar.attachFab(fab);
        floatingToolbar.attachRecyclerView(articleRecycler);

        LinearLayout linearLayout = (LinearLayout) rootView.findViewById(R.id.fab_toolbar);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), BookmarksActivity.class);
                startActivity(intent);
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

                if (dy > 0) {
                    if (loadMoreArticles) {
                        if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {
                            loadMoreArticles = false;
                            offset += 19;
                            getTimeswireData(offset);
                        }
                    }
                }
            }
        });
    }

    @NonNull
    private void unlimitedPower() {
        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.nytimes.com/svc/news/v3/content/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        loadMoreArticles = false;
        offset = 1;
        getTimeswireData(offset);
    }

    private void getTimeswireData(int offset) {
        NewYorkTimesApi newYorkTimesApi = retrofit.create(NewYorkTimesApi.class);
        Call<Articles> articlesCall = newYorkTimesApi.getTimeswireArticles(API_KEY, 20, offset);
        articlesCall.enqueue(new Callback<Articles>() {
            @Override
            public void onResponse(Call<Articles> call, retrofit2.Response<Articles> response) {
                loadMoreArticles = true;
                if (response.isSuccessful()) {
                    Articles articles = response.body();
                    List<Results> resultsList = articles.getResults();
                    timeswireAdapter.listResults(resultsList);
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

}
