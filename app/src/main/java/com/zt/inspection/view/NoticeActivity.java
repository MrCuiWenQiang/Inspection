package com.zt.inspection.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.qmuiteam.qmui.widget.QMUITabSegment;
import com.zt.inspection.R;
import com.zt.inspection.contract.NoticeContract;
import com.zt.inspection.presenter.NoticePresenter;
import com.zt.inspection.view.adapter.MainPageAdapter;
import com.zt.inspection.view.fragment.NoticeFragment;

import java.util.ArrayList;

import cn.faker.repaymodel.mvp.BaseMVPAcivity;
import cn.faker.repaymodel.widget.viewgroup.CurrentViewPager;

/*
 *公告
 */
public class NoticeActivity extends BaseMVPAcivity<NoticeContract.View, NoticePresenter> implements NoticeContract.View, View.OnClickListener {


    private CurrentViewPager mContentViewPager;
    private QMUITabSegment mTabSegment;

    public static final String TAG_TYPE = "TAG_TYPES";

    public static Intent newInstance(Context context, int type) {
        Intent intent = new Intent(context, NoticeActivity.class);
        intent.putExtra(TAG_TYPE, type);
        return intent;
    }

    @Override
    protected int getLayoutContentId() {
        return R.layout.ac_notice;
    }

    @Override
    protected void initContentView() {
        setStatusBar(R.color.select_color);
        setTitle("通知公告", R.color.white);
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

        mTabSegment.addTab(new QMUITabSegment.Tab("通知公告"));
        mTabSegment.addTab(new QMUITabSegment.Tab("美篇图文"));
        mTabSegment.addTab(new QMUITabSegment.Tab("工作消息"));

        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(NoticeFragment.newInstance("1"));
        fragments.add(NoticeFragment.newInstance("2"));
        fragments.add(NoticeFragment.newInstance("3"));

        MainPageAdapter pageAdapter = new MainPageAdapter(getSupportFragmentManager(), fragments);
        mContentViewPager.setAdapter(pageAdapter);
        mTabSegment.setupWithViewPager(mContentViewPager, false);
        mTabSegment.notifyDataChanged();

        int type = getIntent().getIntExtra(TAG_TYPE, -1);
        if (type == 1) {
            mContentViewPager.setCurrentItem(0);

        } else if (type == 2) {
            mContentViewPager.setCurrentItem(1);

        } else if (type == 3) {
            mContentViewPager.setCurrentItem(2);

        } else {
            mContentViewPager.setCurrentItem(0);
        }

    }


    @Override
    public void onClick(View v) {

    }


}
