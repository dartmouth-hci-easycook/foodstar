package edu.dartmouth.cs.hci.foodstar.ui.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import edu.dartmouth.cs.hci.foodstar.R;
import edu.dartmouth.cs.hci.foodstar.ui.HomeScreen;
import edu.dartmouth.cs.hci.foodstar.ui.fragments.PlaceholderFragment;

/**
 * Created by Vishal Gaurav
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    private Context mContext;

    public SectionsPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        return PlaceholderFragment.newInstance(position + 1);
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return HomeScreen.COUNT_TABS_HOME_SCREEN;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return mContext.getString(R.string.tab_label_starred);
            case 1:
                return mContext.getString(R.string.tab_label_recommendation);
            case 2:
                return mContext.getString(R.string.tab_label_trending);
        }
        return null;
    }
}
