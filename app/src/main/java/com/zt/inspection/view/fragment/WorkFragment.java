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
import com.zt.inspection.presenter.WorkFragmentPresenter;

import cn.faker.repaymodel.mvp.BaseMVPFragment;

/**
 * 考勤打卡
 */
public class WorkFragment extends BaseMVPFragment<WorkFragmentContract.View, WorkFragmentPresenter> implements WorkFragmentContract.View, View.OnClickListener {

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
                mPresenter.AddCLOCKIN(null,"1");
                break;
            }
            case R.id.ll_down: {
                showLoading();
                mPresenter.AddCLOCKIN(null,"2");
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
    public void GetCLOCKINList_Success(ClockInBean bean) {
        dimiss();
    }

    @Override
    public void GetCLOCKINList_Fail(String msg) {
        dimiss();
        showDialog(msg);
    }



    @Override
    public void AddCLOCKIN_success(String OUTTIME, int status, String v) {
        dimiss();
        if (OUTTIME.equals("1")) {
            tv_up_hint.setVisibility(View.VISIBLE);
            ll_up.setVisibility(View.GONE);
        } else if (OUTTIME.equals("2")) {
            tv_down_hint.setVisibility(View.VISIBLE);
            ll_down.setVisibility(View.GONE);
        }
    }
    @Override
    public void AddCLOCKIN_fail(String message) {
        dimiss();
        showDialog(message);
    }

}
