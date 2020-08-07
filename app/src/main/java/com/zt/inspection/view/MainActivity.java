package com.zt.inspection.view;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.qmuiteam.qmui.util.QMUIResHelper;
import com.qmuiteam.qmui.widget.QMUITabSegment;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;
import com.zt.inspection.MyApplication;
import com.zt.inspection.R;
import com.zt.inspection.contract.MainContract;
import com.zt.inspection.model.entity.view.MainTableBean;
import com.zt.inspection.presenter.MainPresenter;
import com.zt.inspection.util.LBS.LocationUtil;
import com.zt.inspection.view.adapter.MainPageAdapter;
import com.zt.inspection.view.service.MessageClientService;

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
        setStatusBar(R.color.stdf_color);
        mContentViewPager = findViewById(R.id.contentViewPager);
        mTabSegment = findViewById(R.id.tabs);

        mTabSegment.setDefaultNormalColor(ContextCompat.getColor(this, R.color.black));
        mTabSegment.setDefaultSelectedColor(ContextCompat.getColor(this, R.color.select_color));
        mTabSegment.setHasIndicator(true);
        mTabSegment.setIndicatorPosition(false);
        mTabSegment.setIndicatorWidthAdjustContent(true);
        Intent intent = new Intent(getContext(),MessageClientService.class);
        startService(intent);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        mPresenter.initpush();
        mPresenter.giveTable();
        mPresenter.giveFragments();
        if ("1".equals(MyApplication.loginUser.getISPUBLICEQUIPMENT())){
            Intent intent = new Intent(getContext(),LocalService.class);
            startService(intent);
//            getApplicationContext().bindService(intent,conn,0);
        }
    }
    private ServiceConnection conn = new ServiceConnection() {

        @Override
        public void onServiceDisconnected(ComponentName name) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            // TODO Auto-generated method stub
        }
    };
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
    public void onResume() {
        super.onResume();
        if(!LocationUtil.isLocationEnabled(this)) {
            LocationUtil.toOpenGPS(this);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        getApplicationContext().unbindService(conn);
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
