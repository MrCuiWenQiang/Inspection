package com.zt.inspection.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Function :
 * Remarks  :
 * Created by Mr.C on 2018/12/21 0021.
 */
public class MainPageAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> fragments;

    public MainPageAdapter(FragmentManager fm, ArrayList<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }


    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }


    @Override
    public int getCount() {
        return fragments == null ? 0 : fragments.size();
    }
}
