package com.zt.inspection.view.fragment;

import android.os.Bundle;
import android.view.View;

import com.zt.inspection.R;
import com.zt.inspection.contract.WorkStatisticsFragmentContract;
import com.zt.inspection.model.entity.response.ClockInBean;
import com.zt.inspection.presenter.WorkStatisticsPresenter;

import cn.faker.repaymodel.mvp.BaseMVPFragment;

/**
 * 考勤打卡
 */
public class WorkStatisticsFragment extends BaseMVPFragment<WorkStatisticsFragmentContract.View, WorkStatisticsPresenter> implements WorkStatisticsFragmentContract.View, View.OnClickListener {

    public static WorkStatisticsFragment newInstance() {
        Bundle args = new Bundle();
        WorkStatisticsFragment fragment = new WorkStatisticsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.ac_workstatis;
    }

    @Override
    public void initview(View v) {

    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }
}
