package productions.darthplagueis.googlenowfeed.controller;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import productions.darthplagueis.googlenowfeed.R;
import productions.darthplagueis.googlenowfeed.model.TopStories.Results;
import productions.darthplagueis.googlenowfeed.view.TopStoriesViewHolder;

/**
 * Created by oleg on 12/13/17.
 */

public class TopStoriesAdapter extends RecyclerView.Adapter<TopStoriesViewHolder> {
    private List<Results> resultsList;
    private Context context;

    public TopStoriesAdapter(Context context) {
        this.context = context;
        resultsList = new ArrayList<>();
    }

    @Override
    public TopStoriesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.article_itemview, parent, false);
        return new TopStoriesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TopStoriesViewHolder holder, int position) {
        ((TopStoriesViewHolder) holder).onBind(resultsList.get(position));
    }

    @Override
    public int getItemCount() {
        return resultsList.size();
    }

    public void listResults(List<Results> results) {
        resultsList.addAll(results);
        notifyDataSetChanged();
    }
}
