package com.zt.inspection.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.zt.inspection.R;
import com.zt.inspection.contract.LeaveInfoContract;
import com.zt.inspection.model.entity.response.LeaveInfoBean;
import com.zt.inspection.presenter.LeaveInfoPresenter;
import com.zt.inspection.util.WidgetUtil;
import com.zt.inspection.view.dialog.RegistDialog;

import cn.faker.repaymodel.mvp.BaseMVPAcivity;
import cn.faker.repaymodel.util.ToastUtility;

/**
 * 假条详情
 */
public class LeaveInfoActivity extends BaseMVPAcivity<LeaveInfoContract.View, LeaveInfoPresenter> implements LeaveInfoContract.View, View.OnClickListener {

    protected static final String KEY_ID = "KEY_ID";

    public static Bundle newInstance(String id) {
        Bundle args = new Bundle();
        args.putString(KEY_ID, id);
        return args;
    }

    private String leavenId = null;

    private TextView tvUserName;
    private TextView tvLeaveType;
    private TextView tvLeaveReason;
    private TextView tvStartDate;
    private TextView tvEndDate;
    private TextView tvState;
    private TextView tvUserNametla;
    private TextView tvAuditInfo;
    private TextView tvAuditDate;

    private Button bt_examine;
    private Button bt_reject;
    private Button bt_adopt;

    private WidgetUtil widgetUtil;

    @Override
    protected int getLayoutContentId() {
        return R.layout.ac_leaveinfo;
    }

    @Override
    protected void initContentView() {
        setStatusBar(R.color.select_color);
        setTitle("记录详情", R.color.white);
        setToolBarBackgroundColor(R.color.select_color);


        bt_adopt = findViewById(R.id.bt_adopt);
        bt_examine = findViewById(R.id.bt_examine);
        bt_reject = findViewById(R.id.bt_reject);

        tvUserName = findViewById(R.id.tv_user_name);
        tvLeaveType = findViewById(R.id.tv_leave_type);
        tvLeaveReason = findViewById(R.id.tv_leave_reason);
        tvStartDate = findViewById(R.id.tv_start_date);
        tvEndDate = findViewById(R.id.tv_end_date);
        tvState = findViewById(R.id.tv_state);
        tvUserNametla = findViewById(R.id.tv_user_nametla);
        tvAuditInfo = findViewById(R.id.tv_audit_info);
        tvAuditDate = findViewById(R.id.tv_audit_date);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        leavenId = getIntent().getStringExtra(KEY_ID);
        showLoading();
        mPresenter.loadData(leavenId);
    }


    @Override
    protected void initListener() {
        super.initListener();
        bt_examine.setOnClickListener(this);
        bt_reject.setOnClickListener(this);
        bt_adopt.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_examine: {
                break;
            }
            case R.id.bt_reject: {
                new RegistDialog().setRegisListener(new RegistDialog.onRegisListener() {
                    @Override
                    public void onRegistInfo(String info) {
                        showLoading();
                        mPresenter.reject(data.getAUDIT_ID(), data.getLEAVE_ID(), info);
                    }
                }).show(getSupportFragmentManager(), "1");
                break;
            }
            case R.id.bt_adopt: {
                new RegistDialog().setRegisListener(new RegistDialog.onRegisListener() {
                    @Override
                    public void onRegistInfo(String info) {
                        showLoading();
                        mPresenter.adopt(data.getAUDIT_ID(), data.getLEAVE_ID(), info);
                    }
                }).show(getSupportFragmentManager(), "2");
                break;
            }
        }
    }

    @Override
    public void loadDatas_Fail(String msg) {
        dimiss();
        ToastUtility.showToast(msg);
    }

    private LeaveInfoBean data;

    @Override
    public void loadDatas_Success(LeaveInfoBean bean) {
        dimiss();
        data = bean;
        // TODO: 2020/5/25 未测试 需谨慎
        widgetUtil.bindData(this, bean);
    }

    @Override
    public void adopt_fail(String message) {
        dimiss();
        ToastUtility.showToast(message);
    }

    @Override
    public void reject_fail(String message) {
        dimiss();
        ToastUtility.showToast(message);
    }
}
