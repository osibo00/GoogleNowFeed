package productions.darthplagueis.googlenowfeed.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import productions.darthplagueis.googlenowfeed.R;
import productions.darthplagueis.googlenowfeed.model.Results;

/**
 * Created by oleg on 12/13/17.
 */

public class ArticlesViewHolder extends RecyclerView.ViewHolder {
    private TextView section, author, nyt, title, articleAbstract, date;
    private ImageView thumbnail;

    public ArticlesViewHolder(View itemView) {
        super(itemView);
        section = (TextView) itemView.findViewById(R.id.article_section);
        author = (TextView) itemView.findViewById(R.id.article_author);
        nyt = (TextView) itemView.findViewById(R.id.article_nyt);
        title = (TextView) itemView.findViewById(R.id.article_title);
        articleAbstract = (TextView) itemView.findViewById(R.id.article_abstract);
        date = (TextView) itemView.findViewById(R.id.article_date);
        thumbnail = (ImageView) itemView.findViewById(R.id.article_image);
    }

    public void onBind(Results results) {
        section.setText(results.getSection());
        author.setText(results.getByline());
        nyt.setText(results.getSource());
        title.setText(results.getTitle());
        articleAbstract.setText(results.getAbstract_string());
        date.setText(results.getPublished_date());

        Glide.with(itemView)
                .load(results.getThumbnail_standard())
                .into(thumbnail);
    }
}
