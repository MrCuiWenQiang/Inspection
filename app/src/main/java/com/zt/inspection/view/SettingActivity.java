package com.zt.inspection.view;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.qmuiteam.qmui.util.QMUIKeyboardHelper;
import com.zt.inspection.R;
import com.zt.inspection.contract.SettingContract;
import com.zt.inspection.presenter.SettingPresenter;


import cn.faker.repaymodel.mvp.BaseMVPAcivity;
import cn.faker.repaymodel.util.ToastUtility;

public class SettingActivity extends BaseMVPAcivity<SettingContract.View, SettingPresenter>
        implements SettingContract.View, View.OnClickListener {


    private Button btnSetHttp;

    private EditText et_ip;
    private EditText et_port;

    @Override
    protected int getLayoutContentId() {
        return R.layout.ac_setting;
    }

    @Override
    protected void initContentView() {
        isShowToolView(true);
        isShowBackButton(true);
        setTitle("系统设置", R.color.white);
        setToolBarBackgroundColor(R.color.win_start);
        setStatusBar(R.color.win_start);


        btnSetHttp = findViewById(R.id.btnSetHttp);
        et_ip = findViewById(R.id.et_ip);
        et_port = findViewById(R.id.et_port);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
    }

    @Override
    protected void initListener() {
        super.initListener();

        btnSetHttp.setOnClickListener(this);
    }





    @Override
    public void fail(String msg) {
        ToastUtility.showToast(msg);
    }

    @Override
    public void getHttpSettingSuccess(String ip, String port) {
        et_ip.setText(ip);
        et_port.setText(port);
    }

    @Override
    public void settingSetting_success(String msg) {
        QMUIKeyboardHelper.hideKeyboard(et_port);
        ToastUtility.showToast(msg);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
          case R.id.btnSetHttp: {
                mPresenter.settingHttpSetting(getEditTextValue(et_ip),getEditTextValue(et_port));
                break;
            }
        }
    }

}
