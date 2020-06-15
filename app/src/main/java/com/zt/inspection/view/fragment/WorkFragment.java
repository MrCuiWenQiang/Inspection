package com.zt.inspection.view.fragment;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;
import com.zt.inspection.R;
import com.zt.inspection.contract.WorkFragmentContract;
import com.zt.inspection.model.entity.response.ClockInBean;
import com.zt.inspection.model.entity.response.TodayBean;
import com.zt.inspection.presenter.WorkFragmentPresenter;
import com.zt.inspection.util.WorkUtil;

import java.util.List;

import cn.faker.repaymodel.mvp.BaseMVPFragment;
import cn.faker.repaymodel.util.LogUtil;

/**
 * 考勤打卡
 */
public class WorkFragment extends BaseMVPFragment<WorkFragmentContract.View, WorkFragmentPresenter> implements WorkFragmentContract.View, View.OnClickListener {
    private String TAG = getClass().getName();

    public static WorkFragment newInstance() {
        Bundle args = new Bundle();
        WorkFragment fragment = new WorkFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private TextView tv_up_hint;
    private TextView tv_down_hint;

    private ViewGroup ll_up;
    private ViewGroup ll_down;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_up: {
                showLoading();
                mPresenter.AddCLOCKIN(null);
                break;
            }
            case R.id.ll_down: {
                showLoading();
                mPresenter.AddCLOCKIN(null);
                break;
            }
        }
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
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        showLoading();
        mPresenter.GetCLOCKINList();
    }

    @Override
    protected void initListener() {
        super.initListener();
        ll_up.setOnClickListener(this);
        ll_down.setOnClickListener(this);
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
        tv_up_hint.setVisibility(View.VISIBLE);
        tv_down_hint.setVisibility(View.GONE);
        ll_down.setVisibility(View.GONE);
    }

    @Override
    public void todayinfo_gone(List<TodayBean> datas) {
        TodayBean todayBean1 = datas.get(0);
        //今日已打卡界面显示
        tv_up_hint.setText(WorkUtil.typeName(todayBean1.getType()));
        tv_up_hint.setVisibility(View.VISIBLE);

        if (datas.size() == 1) {
            ll_down.setVisibility(View.VISIBLE);
            ll_up.setVisibility(View.GONE);

        } else if (datas.size() == 2) {
            TodayBean todayBean2 = datas.get(1);
            tv_up_hint.setText(WorkUtil.typeName(todayBean2.getType()));
            tv_down_hint.setVisibility(View.VISIBLE);
//            tv_up_hint.setVisibility(View.VISIBLE);
            ll_down.setVisibility(View.GONE);
            ll_up.setVisibility(View.GONE);
        }
        dimiss();
    }

    @Override
    public void todayinfo_final(String message) {
        dimiss();
        LogUtil.e(TAG, message);
    }

}
