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

import org.json.JSONException;
import org.json.JSONObject;

import productions.darthplagueis.googlenowfeed.R;
import productions.darthplagueis.googlenowfeed.model.TopStories.Results;

/**
 * Created by oleg on 12/13/17.
 */

public class TopStoriesViewHolder extends RecyclerView.ViewHolder {
    private TextView section, author, title, articleAbstract, date;
    private ImageView thumbnail;
    private Button bookmark, browser;
    private SharedPreferences sharedPrefs;

    public TopStoriesViewHolder(View itemView) {
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
        section.setText(results.getSection());
        author.setText(results.getByline());
        title.setText(results.getTitle());
        articleAbstract.setText(results.getAbstract_string());

        String dateString = results.getPublished_date();
        StringBuilder sb = new StringBuilder();
        sb.append(dateString);
        sb.delete(10, dateString.length());
        date.setText(sb);

        try {
            Glide.with(itemView)
                    .load(results.getMultimedia()[4].getUrl())
                    .into(thumbnail);
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }

        sharedPrefs = itemView.getContext().getSharedPreferences("bookmarked_articles", Context.MODE_PRIVATE);

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
                            .put("thumbnail", results.getMultimedia()[4].getUrl())
                            .put("browser",results.getUrl());
                    bookmarkObjects.put("object", object);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                editor.putString("saved", bookmarkObjects.toString()).commit();

            }
        });

        browser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(itemView.getContext(), "Opening Browser", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(results.getUrl()));
                v.getContext().startActivity(intent);            }
        });
    }
}
