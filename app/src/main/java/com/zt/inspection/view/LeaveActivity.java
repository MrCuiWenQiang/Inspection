package com.zt.inspection.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.qmuiteam.qmui.widget.QMUITabSegment;
import com.zt.inspection.R;
import com.zt.inspection.contract.LeaveContract;
import com.zt.inspection.model.entity.view.MainTableBean;
import com.zt.inspection.presenter.LeavePresenter;
import com.zt.inspection.view.adapter.MainPageAdapter;
import com.zt.inspection.view.fragment.ApplyLeaveFragment;
import com.zt.inspection.view.fragment.LeaveFragment;

import java.util.ArrayList;
import java.util.List;

import cn.faker.repaymodel.mvp.BaseMVPAcivity;
import cn.faker.repaymodel.widget.viewgroup.CurrentViewPager;

public class LeaveActivity extends BaseMVPAcivity<LeaveContract.View, LeavePresenter> implements LeaveContract.View, View.OnClickListener {

    private CurrentViewPager mContentViewPager;
    private QMUITabSegment mTabSegment;

    @Override
    protected int getLayoutContentId() {
        return R.layout.ac_leave;
    }

    @Override
    protected void initContentView() {
        isShowToolView(false);
        setStatusBar(R.color.select_color);
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
        List<MainTableBean> datas = new ArrayList<>();
        datas.add(new MainTableBean( R.mipmap.apply_false,R.mipmap.apply_true,"申请"));
        datas.add(new MainTableBean( R.mipmap.select_false_tak,R.mipmap.select_true_tak,"记录"));
        for (MainTableBean tab : datas) {
            mTabSegment.addTab(new QMUITabSegment.Tab(ContextCompat.getDrawable(this, tab.getIcon()), ContextCompat.getDrawable(this, tab.getSelectIcon()), tab.getName(), false));
        }
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(ApplyLeaveFragment.newInstance());
        fragments.add(LeaveFragment.newInstance());
        MainPageAdapter pageAdapter = new MainPageAdapter(getSupportFragmentManager(), fragments);
        mContentViewPager.setAdapter(pageAdapter);
        mTabSegment.setupWithViewPager(mContentViewPager, false);
        mTabSegment.notifyDataChanged();
        mContentViewPager.setCurrentItem(0);
    }


    @Override
    protected void initListener() {
        super.initListener();
    }

    @Override
    public void onClick(View v) {

    }


}
