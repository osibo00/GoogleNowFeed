package productions.darthplagueis.googlenowfeed.controller;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import productions.darthplagueis.googlenowfeed.R;
import productions.darthplagueis.googlenowfeed.model.Results;
import productions.darthplagueis.googlenowfeed.view.ArticlesViewHolder;

/**
 * Created by oleg on 12/13/17.
 */

public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesViewHolder> {
    private List<Results> resultsList;

    public ArticlesAdapter(List<Results> resultsList) {
        this.resultsList = resultsList;
    }

    @Override
    public ArticlesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.article_itemview, parent, false);
        return new ArticlesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ArticlesViewHolder holder, int position) {
        ((ArticlesViewHolder) holder).onBind(resultsList.get(position));
    }

    @Override
    public int getItemCount() {
        return resultsList.size();
    }

    public void listResults(List<Results> resultsList) {
    }
}
