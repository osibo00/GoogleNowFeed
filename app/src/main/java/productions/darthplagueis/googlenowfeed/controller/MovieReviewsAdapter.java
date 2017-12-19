package productions.darthplagueis.googlenowfeed.controller;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import productions.darthplagueis.googlenowfeed.R;
import productions.darthplagueis.googlenowfeed.model.MovieReviews.Results;
import productions.darthplagueis.googlenowfeed.view.MovieReviewsViewHolder;

/**
 * Created by c4q on 12/18/17.
 */

public class MovieReviewsAdapter extends RecyclerView.Adapter<MovieReviewsViewHolder> {
    private List<Results> resultsList;
    private Context context;

    public MovieReviewsAdapter(Context context) {
        resultsList = new ArrayList<>();
        this.context = context;
    }

    @Override
    public MovieReviewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.article_itemview, parent, false);
        return new MovieReviewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieReviewsViewHolder holder, int position) {
        ((MovieReviewsViewHolder) holder).onBind(resultsList.get(position));
    }

    @Override
    public int getItemCount() {
        return resultsList.size();
    }

    public void listResults(List<Results> movieResults){
        resultsList.addAll(movieResults);
        notifyDataSetChanged();
    }

}
