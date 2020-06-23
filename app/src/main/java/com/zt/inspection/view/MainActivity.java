package com.zt.inspection.view;

import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.qmuiteam.qmui.util.QMUIResHelper;
import com.qmuiteam.qmui.widget.QMUITabSegment;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;
import com.zt.inspection.R;
import com.zt.inspection.contract.MainContract;
import com.zt.inspection.model.entity.view.MainTableBean;
import com.zt.inspection.presenter.MainPresenter;
import com.zt.inspection.view.adapter.MainPageAdapter;

import java.util.ArrayList;
import java.util.List;

import cn.faker.repaymodel.activity.manager.ActivityManager;
import cn.faker.repaymodel.mvp.BaseMVPAcivity;
import cn.faker.repaymodel.widget.viewgroup.CurrentViewPager;

public class MainActivity extends BaseMVPAcivity<MainContract.View, MainPresenter> implements MainContract.View {

    private CurrentViewPager mContentViewPager;
    private QMUITabSegment mTabSegment;

    @Override
    protected int getLayoutContentId() {
        return R.layout.ac_main;
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
        mPresenter.giveTable();
        mPresenter.giveFragments();
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

    @Override
    public void onBackPressed() {
        new QMUIDialog.MessageDialogBuilder(this).setMessage("是否退出当前应用").setCancelable(false)
                .addAction("退出", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        dialog.dismiss();
                        ActivityManager.exit(getContext());
                    }
                })
                .addAction("取消", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        dialog.dismiss();
                    }
                }).show();
    }
}
