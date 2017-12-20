package productions.darthplagueis.googlenowfeed.fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import productions.darthplagueis.googlenowfeed.R;
import productions.darthplagueis.googlenowfeed.controller.BookmarkAdapter;
import productions.darthplagueis.googlenowfeed.model.Bookmark;

/**
 * A simple {@link Fragment} subclass.
 */

public class BookmarksFragment extends Fragment {
    private SharedPreferences sharedPrefs;
    private JSONObject jsonObject;
    private View rootView;

    public BookmarksFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragments_bookmarks, container, false);

        RecyclerView bookmarkRecycler = rootView.findViewById(R.id.bookmark_recycler);

        List<Bookmark> bookmarks = new ArrayList<>();

        String jsonString = sharedPrefs.getString("saved", null);

        try {
            jsonObject = new JSONObject(jsonString);
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

        BookmarkAdapter bookmarkAdapter = new BookmarkAdapter(bookmarks);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(rootView.getContext(), LinearLayoutManager.VERTICAL, false);

        bookmarkRecycler.setAdapter(bookmarkAdapter);

        bookmarkRecycler.setLayoutManager(linearLayoutManager);

        return rootView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPrefs = getContext().getSharedPreferences("bookmarked_articles", Context.MODE_PRIVATE);
    }
}