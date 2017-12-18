package productions.darthplagueis.googlenowfeed.fragments;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

import productions.darthplagueis.googlenowfeed.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BookmarksFragment extends Fragment {
    private SharedPreferences sharedPrefs;
    private TextView savedSection, savedAuthor, savedDate, savedTitle, savedAbstract;
    private View rootView;
    private JSONObject jsonObject;
    private ImageView thumbnail;
    private Button browser;


    public BookmarksFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_bookmarks, container, false);
        savedSection = rootView.findViewById(R.id.saved_article_section);
        savedAuthor = rootView.findViewById(R.id.saved_article_author);
        savedDate = rootView.findViewById(R.id.saved_article_date);
        savedTitle = rootView.findViewById(R.id.saved_article_title);
        savedAbstract = rootView.findViewById(R.id.saved_article_abstract);
        thumbnail = rootView.findViewById(R.id.saved_article_image);
        browser = rootView.findViewById(R.id.saved_browser);


        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        try {
            String jsonString = sharedPrefs.getString("saved", null);
            jsonObject = new JSONObject(jsonString);
            final JSONObject results = (JSONObject) jsonObject.get("object");

            savedSection.setText(results.getString("section"));
            savedAuthor.setText(results.getString("author"));
            savedDate.setText(results.getString("date"));
            savedTitle.setText(results.getString("title"));
            savedAbstract.setText(results.getString("articleAbstract"));
            try {

                Glide.with(view)
                        .load(results.getString("thumbnail"))
                        .into(thumbnail);
            } catch (IndexOutOfBoundsException e) {
                e.printStackTrace();
            }
            browser.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(rootView.getContext(), "Opening Browser", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    try {
                        intent.setData(Uri.parse(results.getString("browser")));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    rootView.getContext().startActivity(intent);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPrefs = getContext().getSharedPreferences("bookmarked_articles", Context.MODE_PRIVATE);
    }
}
