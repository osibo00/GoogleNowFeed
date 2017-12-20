package productions.darthplagueis.googlenowfeed.controller;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import productions.darthplagueis.googlenowfeed.fragments.BookmarksFragment;
import productions.darthplagueis.googlenowfeed.fragments.MovieReviewsFragment;
import productions.darthplagueis.googlenowfeed.fragments.TimeswireFragment;
import productions.darthplagueis.googlenowfeed.fragments.TopStoriesFragment;

/**
 * Created by oleg on 12/14/17.
 */

public class FragmentAdapter extends FragmentStatePagerAdapter {
    private int numberOfTabs;

    public FragmentAdapter(FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numberOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new TopStoriesFragment();
            case 1:
                return new TimeswireFragment();
            case 2:
                return new MovieReviewsFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numberOfTabs;
    }
}
