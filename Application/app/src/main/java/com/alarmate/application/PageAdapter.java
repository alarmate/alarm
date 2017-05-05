package com.alarmate.application;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by seongjinlee on 2017. 5. 1..
 */

public class PageAdapter extends FragmentStatePagerAdapter {
    private String[] pageTitle = {"TAB1", "TAB2", "TAB3"};


    public PageAdapter(FragmentManager fm) {
        super(fm);
    }




    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0 :
                return new FragmentOne();
            case 1 :
                return new FragmentTwo();
            case 2 :
                return new FragmentThree();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return pageTitle[position];
    }
}
