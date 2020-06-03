package com.zt.inspection.view.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.zt.inspection.R;
import com.zt.inspection.contract.ApplyLeaveFragmentContract;
import com.zt.inspection.presenter.ApplyLeaveFragmentPresenter;

import cn.faker.repaymodel.mvp.BaseMVPFragment;

public class ApplyLeaveFragment extends BaseMVPFragment<ApplyLeaveFragmentContract.View, ApplyLeaveFragmentPresenter> implements ApplyLeaveFragmentContract.View {

    public static ApplyLeaveFragment newInstance() {
        Bundle args = new Bundle();
        ApplyLeaveFragment fragment = new ApplyLeaveFragment();
        fragment.setArguments(args);
        return fragment;
    }


    private TextView tvType;
    private TextView tvStartDate;
    private TextView tvEndDate;
    private EditText tvReason;
    private Button btSave;


    @Override
    public int getLayoutId() {
        return R.layout.fg_applyleave;
    }

    @Override
    public void initview(View v) {
        tvType = findViewById(R.id.tv_type);
        tvStartDate = findViewById(R.id.tv_start_date);
        tvEndDate = findViewById(R.id.tv_end_date);
        tvReason = findViewById(R.id.tv_reason);
        btSave = findViewById(R.id.bt_save);
    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void initListener() {
        super.initListener();
        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
