package com.zt.inspection.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.qmuiteam.qmui.widget.QMUITabSegment;
import com.zt.inspection.R;
import com.zt.inspection.view.adapter.MainPageAdapter;

import java.util.ArrayList;

import cn.faker.repaymodel.fragment.BaseViewPagerFragment;

/**
 * 因逻辑简单 不再使用mvp
 */
public class HistoryFragment extends BaseViewPagerFragment {

    private ViewPager mContentViewPager;
    private QMUITabSegment mTabSegment;
    private ImageView im_icon;

    public static HistoryFragment newInstance() {
        Bundle args = new Bundle();
        HistoryFragment fragment = new HistoryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fg_history;
    }

    @Override
    public void initview(View v) {
        mContentViewPager = v.findViewById(R.id.contentViewPager);
        mTabSegment = v.findViewById(R.id.tabs);
        im_icon = v.findViewById(R.id.im_icon);

        mTabSegment.setDefaultNormalColor(ContextCompat.getColor(getContext(), R.color.passw_changing));
        mTabSegment.setDefaultSelectedColor(ContextCompat.getColor(getContext(), R.color.blue_5));
        mTabSegment.setHasIndicator(true);
        mTabSegment.setIndicatorPosition(false);
        mTabSegment.setIndicatorWidthAdjustContent(true);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        mTabSegment.addTab(new QMUITabSegment.Tab("案件历史"));
        mTabSegment.addTab(new QMUITabSegment.Tab("巡检历史"));

        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(HistoryWorkFragment.newInstance());
        fragments.add(HistoryRouteFragment.newInstance());

        MainPageAdapter pageAdapter = new MainPageAdapter(getChildFragmentManager(), fragments);
        mContentViewPager.setAdapter(pageAdapter);
        mTabSegment.setupWithViewPager(mContentViewPager, false);
        mTabSegment.notifyDataChanged();
        mContentViewPager.setCurrentItem(0);
        mContentViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                int n = i+1;
                if (n==1){
                    im_icon.setImageResource(R.mipmap.workicc);
                }else {
                    im_icon.setImageResource(R.mipmap.routrww);
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }
}
