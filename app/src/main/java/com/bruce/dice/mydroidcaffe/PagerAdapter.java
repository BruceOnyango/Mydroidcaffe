package com.bruce.dice.mydroidcaffe;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PagerAdapter extends FragmentPagerAdapter {
    int myNumberOfTabs;
    public PagerAdapter(@NonNull FragmentManager fm, int numberOfTabs) {

        super(fm, numberOfTabs);
        this.myNumberOfTabs = numberOfTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: return new DesertRecipeFragment();
            case 1: return new PastriesRecipeFragment();
            case 2: return new StoreFragment();

            default:return null;
        }

    }

    @Override
    public int getCount() {
        return myNumberOfTabs;
    }
}
