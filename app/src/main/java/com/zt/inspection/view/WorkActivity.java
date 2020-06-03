package com.zt.inspection.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.qmuiteam.qmui.widget.QMUITabSegment;
import com.zt.inspection.R;
import com.zt.inspection.contract.WorkContract;
import com.zt.inspection.model.entity.view.MainTableBean;
import com.zt.inspection.presenter.WorkPresenter;
import com.zt.inspection.view.adapter.MainPageAdapter;

import java.util.ArrayList;
import java.util.List;

import cn.faker.repaymodel.mvp.BaseMVPAcivity;
import cn.faker.repaymodel.widget.viewgroup.CurrentViewPager;

/**
 * 考勤打卡
 */
public class WorkActivity extends BaseMVPAcivity<WorkContract.View, WorkPresenter> implements WorkContract.View, View.OnClickListener {

    private CurrentViewPager mContentViewPager;
    private QMUITabSegment mTabSegment;

    @Override
    protected int getLayoutContentId() {
        return R.layout.ac_work;
    }

    @Override
    protected void initContentView() {
        setStatusBar(R.color.select_color);
        setTitle("考勤打卡", R.color.white);
        setToolBarBackgroundColor(R.color.select_color);

        mContentViewPager = findViewById(R.id.contentViewPager);
        mTabSegment = findViewById(R.id.tabs);

        mTabSegment.setDefaultNormalColor(ContextCompat.getColor(this, R.color.black));
        mTabSegment.setDefaultSelectedColor(ContextCompat.getColor(this, R.color.select_color));
        mTabSegment.setHasIndicator(true);
        mTabSegment.setIndicatorPosition(false);
        mTabSegment.setIndicatorWidthAdjustContent(true);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        mPresenter.giveTable();
        mPresenter.giveFragments();
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void settingTabs(List<MainTableBean> datas) {
        for (MainTableBean tab : datas) {
            mTabSegment.addTab(new QMUITabSegment.Tab(ContextCompat.getDrawable(this, tab.getIcon()), ContextCompat.getDrawable(this, tab.getSelectIcon()), tab.getName(), false));
        }
    }

    @Override
    public void settingFragments(ArrayList<Fragment> fragments) {
        MainPageAdapter pageAdapter = new MainPageAdapter(getSupportFragmentManager(), fragments);
        mContentViewPager.setAdapter(pageAdapter);
        mTabSegment.setupWithViewPager(mContentViewPager, false);
        mTabSegment.notifyDataChanged();
        mContentViewPager.setCurrentItem(0);
    }
}
