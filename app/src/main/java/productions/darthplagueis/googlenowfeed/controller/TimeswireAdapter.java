package productions.darthplagueis.googlenowfeed.controller;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import productions.darthplagueis.googlenowfeed.R;
import productions.darthplagueis.googlenowfeed.model.Timeswire.Results;
import productions.darthplagueis.googlenowfeed.view.TimeswireViewHolder;

/**
 * Created by oleg on 12/15/17.
 */

public class TimeswireAdapter extends RecyclerView.Adapter<TimeswireViewHolder> {
    private List<Results> results;
    private Context context;

    public TimeswireAdapter(Context context) {
        this.context = context;
        results = new ArrayList<>();
    }

    @Override
    public TimeswireViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.article_itemview, parent, false);
        return new TimeswireViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TimeswireViewHolder holder, int position) {
        ((TimeswireViewHolder) holder).onBind(results.get(position));
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public void listResults(List<Results> resultsList) {
        results.addAll(resultsList);
        notifyDataSetChanged();
    }
}
