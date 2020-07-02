package com.zt.inspection.view.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bruce.pickerview.popwindow.DatePickerPopWin;
import com.zt.inspection.R;
import com.zt.inspection.contract.WorkStatisticsFragmentContract;
import com.zt.inspection.model.entity.response.WorkDeptBean;
import com.zt.inspection.model.entity.response.WorkUserBean;
import com.zt.inspection.presenter.WorkStatisticsPresenter;
import com.zt.inspection.view.adapter.WorkManualStatisAdapter;
import com.zt.inspection.view.adapter.WorkStatisAdapter;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import cn.faker.repaymodel.mvp.BaseMVPFragment;
import cn.faker.repaymodel.util.DateUtils;
import cn.faker.repaymodel.util.ToastUtility;
import cn.faker.repaymodel.widget.view.date.DateUtil;
import cn.faker.repaymodel.widget.view.date.MonthUtil;

/**
 * 考勤统计
 */
public class WorkStatisticsFragment extends BaseMVPFragment<WorkStatisticsFragmentContract.View, WorkStatisticsPresenter>
        implements WorkStatisticsFragmentContract.View, View.OnClickListener {

    public static WorkStatisticsFragment newInstance() {
        Bundle args = new Bundle();
        WorkStatisticsFragment fragment = new WorkStatisticsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private RecyclerView rv_data;
    private WorkStatisAdapter adapter_user;
    private WorkManualStatisAdapter adapter_manua;
    private TextView tv_date;
    private TextView tv_date_end;
    private TextView bt_search;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_date: {
                showDate(tv_date, 0);
                break;
            }
            case R.id.tv_date_end: {
                showDate(tv_date_end, 1);
                break;
            }
            case R.id.bt_search: {
                showLoading();
                mPresenter.search(tv_date.getText().toString(), tv_date_end.getText().toString());
                break;
            }
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.ac_workstatis;
    }

    @Override
    public void initview(View v) {
        rv_data = findViewById(R.id.rv_data);
        tv_date = findViewById(R.id.tv_date);
        bt_search = findViewById(R.id.bt_search);
        tv_date_end = findViewById(R.id.tv_date_end);
        rv_data.setLayoutManager(new LinearLayoutManager(getContext()));

    }

    private void showDate(TextView tv, int type) {

        DatePickerPopWin pickerPopWin = new DatePickerPopWin.Builder(getContext(), new DatePickerPopWin.OnDatePickedListener() {
            @Override
            public void onDatePickCompleted(int year, int month, int day, String dateDesc) {
                if (type == 0) {
                    //0为开始时间
                    boolean is = false;
                    try {
                        is = DateUtils.isbeginTime(dateDesc, tv_date_end.getText().toString());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    if (is) {
                        ToastUtility.showToast("开始时间不能大于结束时间");
                    } else {
                        tv.setText(dateDesc);
                    }
                } else {
                    boolean is = false;
                    try {
                        is = DateUtils.isbeginTime(tv_date.getText().toString(),dateDesc);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    if (is) {
                        ToastUtility.showToast("结束时间不能小于开始时间");
                    } else {
                        tv.setText(dateDesc);
                    }
                }

            }
        }).textConfirm("确定") //text of confirm button
                .textCancel("取消") //text of cancel button
                .btnTextSize(16) // button text size
                .viewTextSize(25) // pick view text size
                .colorCancel(Color.parseColor("#999999")) //color of cancel button
                .colorConfirm(Color.parseColor("#4688f8"))//color of confirm button
                .minYear(1990) //min year in loop
                .maxYear(2550) // max year in loop
//                .showDayMonthYear(true) // shows like dd mm yyyy (default is false)
                .dateChose(tv.getText().toString()) // date chose when init popwindow
                .build();
        pickerPopWin.showPopWin(getActivity());
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        String mstart = MonthUtil.getMonthFirstDay(new Date());
        String mend = MonthUtil.getMonthLastDay(new Date());
        tv_date.setText(mstart);
        tv_date_end.setText(mend);
        showLoading();
        mPresenter.search(tv_date.getText().toString(), tv_date_end.getText().toString());
    }

    @Override
    protected void initListener() {
        super.initListener();
        tv_date.setOnClickListener(this);
        tv_date_end.setOnClickListener(this);
        bt_search.setOnClickListener(this);
    }

    @Override
    public void getListDataSuccess(List<WorkUserBean> data) {
        dimiss();
        if (adapter_user == null) {
            adapter_user = new WorkStatisAdapter();
            rv_data.setAdapter(adapter_user);
        }
        adapter_user.setUserData(data);
    }

    @Override
    public void getListDataFail(String message) {
        dimiss();
        showDialog(message);
    }

    @Override
    public void getManualDataSuccess(WorkDeptBean data) {
        dimiss();
        if (adapter_manua == null) {
            adapter_manua = new WorkManualStatisAdapter();
            rv_data.setAdapter(adapter_manua);
        }
        adapter_manua.setUserData(data);
    }

    @Override
    public void getManualDataFail(String message) {
        dimiss();
        showDialog(message);
    }
}
