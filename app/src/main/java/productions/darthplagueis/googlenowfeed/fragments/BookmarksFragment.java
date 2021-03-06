package productions.darthplagueis.googlenowfeed.fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import productions.darthplagueis.googlenowfeed.R;
import productions.darthplagueis.googlenowfeed.controller.BookmarkAdapter;
import productions.darthplagueis.googlenowfeed.model.Bookmark;

/**
 * A simple {@link Fragment} subclass.
 */

public class BookmarksFragment extends Fragment {
    private SharedPreferences sharedPrefs;
    private final static String PREFS_NAME = "bookmarked_articles";
    private View rootView;

    public BookmarksFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragments_bookmarks, container, false);

        List<Bookmark> bookmarks = new ArrayList<>();

        getBookmarkedArticles(bookmarks);

        RecyclerView bookmarkRecycler = rootView.findViewById(R.id.bookmark_recycler);
        BookmarkAdapter bookmarkAdapter = new BookmarkAdapter(bookmarks);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(rootView.getContext(), LinearLayoutManager.VERTICAL, false);
        bookmarkRecycler.setAdapter(bookmarkAdapter);
        bookmarkRecycler.setLayoutManager(linearLayoutManager);

        return rootView;
    }

    private void getBookmarkedArticles(List<Bookmark> bookmarks) {
        Map<String, ?> keys = sharedPrefs.getAll();
        for (Map.Entry<String, ?> entry : keys.entrySet()) {
            Log.d("map values", entry.getKey() + ": " + entry.getValue().toString());
            String jsonString = sharedPrefs.getString(entry.getKey(), null);
            try {
                JSONObject jsonObject = new JSONObject(jsonString);
                final JSONObject results = (JSONObject) jsonObject.get("object");

                bookmarks.add(new Bookmark(
                        results.getString("section"),
                        results.getString("title"),
                        results.getString("articleAbstract"),
                        results.getString("author"),
                        results.getString("date"),
                        results.getString("browser"),
                        results.getString("thumbnail")
                ));

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPrefs = getActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }
}