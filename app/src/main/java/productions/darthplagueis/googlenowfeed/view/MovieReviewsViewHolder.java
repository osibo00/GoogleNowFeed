package productions.darthplagueis.googlenowfeed.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import org.json.JSONException;
import org.json.JSONObject;

import productions.darthplagueis.googlenowfeed.R;
import productions.darthplagueis.googlenowfeed.model.MovieReviews.Results;

/**
 * Created by c4q on 12/18/17.
 */

public class MovieReviewsViewHolder extends RecyclerView.ViewHolder {
    private final String TAG = "bookmarked_articles";
    private SharedPreferences sharedPrefs;

    private TextView section, author, title, articleAbstract, date;
    private ImageView thumbnail;
    private Button bookmark, browser;

    public MovieReviewsViewHolder(View itemView) {
        super(itemView);
        section = (TextView) itemView.findViewById(R.id.article_section);
        author = (TextView) itemView.findViewById(R.id.article_author);
        title = (TextView) itemView.findViewById(R.id.article_title);
        articleAbstract = (TextView) itemView.findViewById(R.id.article_abstract);
        date = (TextView) itemView.findViewById(R.id.article_date);
        thumbnail = (ImageView) itemView.findViewById(R.id.article_image);
        bookmark = (Button) itemView.findViewById(R.id.bookmark);
        browser = (Button) itemView.findViewById(R.id.browser);
    }

    public void onBind(final Results results) {
        section.setText(results.getHeadline());
        author.setText(results.getByline());
        title.setText(results.getDisplay_title());
        articleAbstract.setText(results.getSummary_short());

        String dateString = results.getPublication_date();
        StringBuilder sb = new StringBuilder();
        sb.append(dateString);
        sb.delete(10, dateString.length());
        date.setText(sb);


        Glide.with(itemView.getContext())
                .load(results.getMultimedia().getSrc())
                .apply(new RequestOptions().placeholder(R.drawable.nyt_logo).diskCacheStrategy(DiskCacheStrategy.ALL))
                .into(thumbnail);

        sharedPrefs = itemView.getContext().getSharedPreferences(TAG, Context.MODE_PRIVATE);

        bookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPrefs.edit();
                Toast.makeText(itemView.getContext(), "Bookmarked", Toast.LENGTH_SHORT).show();

                JSONObject bookmarkObjects = new JSONObject();

                try {
                    JSONObject object = new JSONObject()
                            .put("section", section.getText().toString())
                            .put("author", author.getText().toString())
                            .put("title", title.getText().toString())
                            .put("articleAbstract", articleAbstract.getText().toString())
                            .put("date", date.getText().toString())
                            .put("thumbnail", results.getMultimedia().getSrc())
                            .put("browser", results.getLink().getUrl());
                    bookmarkObjects.put("object", object);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                editor.putString("saved", bookmarkObjects.toString()).apply();
            }
        });

        browser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(itemView.getContext(), "OPENING BROWSER", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(results.getLink().getUrl()));
                v.getContext().startActivity(intent);
            }
        });
    }
}

