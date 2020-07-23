package com.zt.inspection.view;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.qmuiteam.qmui.widget.QMUITabSegment;
import com.zt.inspection.R;
import com.zt.inspection.contract.LoginContract;
import com.zt.inspection.contract.MainContract;
import com.zt.inspection.model.entity.view.MainTableBean;
import com.zt.inspection.presenter.LoginPresenter;
import com.zt.inspection.presenter.MainPresenter;
import com.zt.inspection.util.socket.SocketClient;
import com.zt.inspection.view.adapter.MainPageAdapter;

import java.util.ArrayList;
import java.util.List;

import cn.faker.repaymodel.mvp.BaseMVPAcivity;
import cn.faker.repaymodel.util.permission.PermissionHelper;
import cn.faker.repaymodel.widget.viewgroup.CurrentViewPager;

/*
 *登录
 */
public class LoginActivity extends BaseMVPAcivity<LoginContract.View, LoginPresenter> implements LoginContract.View, View.OnClickListener {

    private EditText et_name;
    private EditText et_password;
    private CheckBox cb_pw;
    private Button bt_login;
    private static final int STORAGE_REQUEST_CODE = 1;

    private final String[] permissions = new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.CALL_PHONE};

    @Override
    protected int getLayoutContentId() {
        return R.layout.ac_login;
    }

    @Override
    protected void initContentView() {
        isShowToolView(false);
        setStatusBar(R.color.logf);
        et_name = findViewById(R.id.et_name);
        et_password = findViewById(R.id.et_password);
        cb_pw = findViewById(R.id.cb_pw);
        bt_login = findViewById(R.id.bt_login);

        et_name.setHintTextColor(ContextCompat.getColor(getContext(),R.color.white));
        et_password.setHintTextColor(ContextCompat.getColor(getContext(),R.color.white));

        findViewById(R.id.tv_setting).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toAcitvity(SettingActivity.class);
            }
        });
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        mPresenter.giveUserInfo();
        if (PermissionHelper.isversion()) {
            for (String p : permissions) {
                if (ContextCompat.checkSelfPermission(this, p)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this, permissions,
                            STORAGE_REQUEST_CODE);
                    break;
                }
            }
        }
    }

    @Override
    protected void initListener() {
        super.initListener();
        bt_login.setOnClickListener(this);

//        SocketClient.startClient("192.168.2.7",8098);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_login: {
                showLoading();
                mPresenter.login(getValue(et_name), getValue(et_password), cb_pw.isChecked());
                break;
            }
        }
    }

    @Override
    public void login_Success() {
        dimiss();
        toAcitvity(MainActivity.class);
        finish();
    }

    @Override
    public void login_Fail(String msg) {
        dimiss();
        showDialog(msg);
    }

    @Override
    public void settingUser(String name, String password) {
        et_name.setText(name);
        et_password.setText(password);
        if (!TextUtils.isEmpty(password)) {
            cb_pw.setChecked(true);
        }
    }
}
