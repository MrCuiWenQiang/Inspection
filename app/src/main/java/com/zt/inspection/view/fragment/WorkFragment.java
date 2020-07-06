package com.zt.inspection.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.baidu.location.BDLocation;
import com.zt.inspection.R;
import com.zt.inspection.contract.WorkFragmentContract;
import com.zt.inspection.model.entity.response.ClockInBean;
import com.zt.inspection.model.entity.response.TodayBean;
import com.zt.inspection.presenter.WorkFragmentPresenter;
import com.zt.inspection.util.LBS.LBSUtil;
import com.zt.inspection.util.WorkUtil;
import com.zt.inspection.view.MapActivity;

import java.util.List;

import cn.faker.repaymodel.mvp.BaseMVPFragment;
import cn.faker.repaymodel.util.LogUtil;
import cn.faker.repaymodel.util.ToastUtility;

/**
 * 考勤打卡
 */
public class WorkFragment extends BaseMVPFragment<WorkFragmentContract.View, WorkFragmentPresenter>
        implements WorkFragmentContract.View, View.OnClickListener
        , com.zt.inspection.util.LBS.LocationListener{
    private String TAG = getClass().getName();

    public static WorkFragment newInstance() {
        Bundle args = new Bundle();
        WorkFragment fragment = new WorkFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private TextView tv_up_hint;
    private TextView tv_down_hint;
    private TextView tv_up_date;
    private TextView tv_down_date;
    private TextView tv_show_up;
    private TextView tv_show_down;



    private ViewGroup ll_up;
    private ViewGroup ll_down;
    private double lat;
    private double lon;
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_up: {
                showLoading();
                mPresenter.AddCLOCKIN(null,lat,lon);
                break;
            }
            case R.id.ll_down: {
                showLoading();
                mPresenter.AddCLOCKIN(null,lat,lon);
                break;
            }case R.id.tv_show_down: {
                toMap();
                break;
            }case R.id.tv_show_up: {
                toMap();
                break;
            }
        }
    }
    private void toMap(){
        if (workData==null){
            ToastUtility.showToast("没有打卡信息");
            return;
        }
        Intent intent = MapActivity.newInstance(getContext(),workData);
        startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fg_work;
    }

    @Override
    public void initview(View v) {
        tv_up_hint = findViewById(R.id.tv_up_hint);
        tv_down_hint = findViewById(R.id.tv_down_hint);
        ll_up = findViewById(R.id.ll_up);
        ll_down = findViewById(R.id.ll_down);
        tv_up_date = findViewById(R.id.tv_up_date);
        tv_down_date = findViewById(R.id.tv_down_date);
        tv_show_up = findViewById(R.id.tv_show_up);
        tv_show_down = findViewById(R.id.tv_show_down);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        showLoading();
        mPresenter.GetCLOCKINList();
    }

    @Override
    protected void initListener() {
        super.initListener();
        LBSUtil.addListener(this);
        ll_up.setOnClickListener(this);
        ll_down.setOnClickListener(this);
        tv_show_up.setOnClickListener(this);
        tv_show_down.setOnClickListener(this);
    }






    @Override
    public void GetCLOCKINList_Fail(String msg) {
        dimiss();
        showDialog(msg);
    }

    @Override
    public void AddCLOCKIN_success(int status, String v) {
        dimiss();
        showDialog(v);
        mPresenter.todayInfo();
    }

    @Override
    public void AddCLOCKIN_fail(String message) {
        dimiss();
        showDialog(message);
    }


    @Override
    public void todayinfo_visible() {
        dimiss();
        //今日未打卡
        ll_up.setVisibility(View.VISIBLE);
        tv_show_up.setVisibility(View.VISIBLE);
        tv_up_hint.setVisibility(View.GONE);
        tv_down_hint.setVisibility(View.GONE);
        ll_down.setVisibility(View.GONE);
        tv_show_down.setVisibility(View.GONE);
    }

    @Override
    public void todayinfo_gone(List<TodayBean> datas) {
        TodayBean todayBean1 = datas.get(0);
        //今日已打卡界面显示
        tv_up_hint.setText(WorkUtil.typeName(todayBean1.getType()));
        tv_up_date.setText(todayBean1.getSIGNTIME());
        tv_up_hint.setTextColor(ContextCompat.getColor(getContext(),WorkUtil.typeColor(todayBean1.getType())));
        tv_up_hint.setVisibility(View.VISIBLE);
        tv_up_date.setVisibility(View.VISIBLE);

        if (datas.size() == 1) {
            ll_down.setVisibility(View.VISIBLE);
            tv_show_down.setVisibility(View.VISIBLE);
            ll_up.setVisibility(View.GONE);
            tv_show_up.setVisibility(View.GONE);

        } else if (datas.size() == 2) {
            TodayBean todayBean2 = datas.get(1);
            tv_down_date.setText(todayBean2.getSIGNTIME());
            tv_down_hint.setText(WorkUtil.typeName(todayBean2.getType()));
//            tv_down_hint.setTextColor(WorkUtil.typeColor(todayBean2.getType()));
            tv_down_hint.setTextColor(ContextCompat.getColor(getContext(),WorkUtil.typeColor(todayBean2.getType())));

            tv_down_date.setVisibility(View.VISIBLE);
            tv_down_hint.setVisibility(View.VISIBLE);
//            tv_up_hint.setVisibility(View.VISIBLE);
            ll_down.setVisibility(View.GONE);
            tv_show_down.setVisibility(View.GONE);
            ll_up.setVisibility(View.GONE);
            tv_show_up.setVisibility(View.GONE);
        }
        dimiss();
    }

    @Override
    public void todayinfo_final(String message) {
        dimiss();
        LogUtil.e(TAG, message);
    }
    ClockInBean workData;
    @Override
    public void GetCLOCKINList_SUCCESS(ClockInBean workData) {
        this.workData = workData;
    }

    @Override
    public void onReceiveLocation(BDLocation location, int errorCode, double latitude, double longitude) {
        lat = location.getLatitude();//纬度
        lon = location.getLongitude();//经度
    }
}
