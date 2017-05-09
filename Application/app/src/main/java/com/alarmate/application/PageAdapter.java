package com.alarmate.application;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.astuetz.PagerSlidingTabStrip;

/**
 * Created by seongjinlee on 2017. 5. 1..
 */

//public class PageAdapter extends FragmentStatePagerAdapter implements PagerSlidingTabStrip.IconTabProvider {
public class PageAdapter extends FragmentStatePagerAdapter {
    private String[] pageTitle = {"알람목록", "TAB2", "TAB3"};
//    private int[] icnos = {R.mipmap.ic_tab_clock_on, R.mipmap.ic_tab_clock_off};



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

/*    @Override
    public int getPageIconResId(int position) {
        return 0;
    }*/
}
