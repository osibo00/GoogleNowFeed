package productions.darthplagueis.googlenowfeed;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import productions.darthplagueis.googlenowfeed.fragments.BookmarksFragment;

public class BookmarksActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmarks);

        Toolbar toolbar = (Toolbar) findViewById(R.id.bookmark_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        initializesBookmarksFrag();
    }

    private void initializesBookmarksFrag() {
        BookmarksFragment bookmarksFragment = new BookmarksFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.bookmarks_frame, bookmarksFragment);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
