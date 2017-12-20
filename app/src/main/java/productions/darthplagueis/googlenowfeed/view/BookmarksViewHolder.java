package productions.darthplagueis.googlenowfeed.view;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import productions.darthplagueis.googlenowfeed.R;
import productions.darthplagueis.googlenowfeed.model.Bookmark;

public class BookmarksViewHolder extends RecyclerView.ViewHolder {
    private TextView savedSection, savedAuthor, savedDate, savedTitle, savedAbstract;
    private ImageView thumbnail;
    private Button browser;
    public BookmarksViewHolder(View itemView) {
        super(itemView);
        savedSection = itemView.findViewById(R.id.saved_article_section);
        savedAuthor = itemView.findViewById(R.id.saved_article_author);
        savedDate = itemView.findViewById(R.id.saved_article_date);
        savedTitle = itemView.findViewById(R.id.saved_article_title);
        savedAbstract = itemView.findViewById(R.id.saved_article_abstract);
        thumbnail = itemView.findViewById(R.id.saved_article_image);
        browser = itemView.findViewById(R.id.saved_browser);
    }
    public void onBind(final Bookmark bookmark) {
        savedSection.setText(bookmark.getSection());
        savedAuthor.setText(bookmark.getByline());
        savedDate.setText(bookmark.getPublished_date());
        savedTitle.setText(bookmark.getTitle());
        savedAbstract.setText(bookmark.getAbstract_string());
        Glide.with(itemView)
                .load(bookmark.getThumbnail())
                .into(thumbnail);
        browser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(itemView.getContext(), "Opening Browser", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(bookmark.getUrl()));
                v.getContext().startActivity(intent);
            }
        });
    }
}