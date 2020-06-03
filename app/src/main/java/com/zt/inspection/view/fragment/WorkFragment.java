package com.zt.inspection.view.fragment;

import android.os.Bundle;
import android.view.View;

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

    @Override
    public void onClick(View v) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fg_work;
    }

    @Override
    public void initview(View v) {

    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public void GetCLOCKINList_Success(ClockInBean bean) {

    }

    @Override
    public void GetCLOCKINList_Fail(String msg) {

    }

    @Override
    public void AddCLOCKIN_fail(String message) {

    }

    @Override
    public void AddCLOCKIN_success(int status, String v) {

    }
}
