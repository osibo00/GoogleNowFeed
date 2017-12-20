package productions.darthplagueis.googlenowfeed.controller;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import productions.darthplagueis.googlenowfeed.R;
import productions.darthplagueis.googlenowfeed.model.Bookmark;
import productions.darthplagueis.googlenowfeed.view.BookmarksViewHolder;

public class BookmarkAdapter extends RecyclerView.Adapter<BookmarksViewHolder> {
    private List<Bookmark> bookmarkList;

    public BookmarkAdapter(List<Bookmark> bookmarkList) {
        this.bookmarkList = bookmarkList;
    }

    @Override
    public BookmarksViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View childView = LayoutInflater.from(parent.getContext()).inflate(R.layout.bookmarks_itemview, parent, false);
        return new BookmarksViewHolder(childView);
    }

    @Override
    public void onBindViewHolder(BookmarksViewHolder holder, int position) {
        Bookmark bookmark = bookmarkList.get(position);
        holder.onBind(bookmark);
    }

    @Override
    public int getItemCount() {
        return bookmarkList.size();
    }
}