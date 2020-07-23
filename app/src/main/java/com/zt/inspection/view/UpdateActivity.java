package com.zt.inspection.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;
import com.zt.inspection.R;
import com.zt.inspection.contract.UpdateContract;
import com.zt.inspection.presenter.UpdatePresenter;
import com.zt.inspection.util.VersionUtil;

import java.io.File;

import cn.faker.repaymodel.mvp.BaseMVPAcivity;
import cn.faker.repaymodel.util.ToastUtility;

/**
 * 升级
 */
public class UpdateActivity extends BaseMVPAcivity<UpdateContract.View, UpdatePresenter>
        implements UpdateContract.View {

    private TextView tvHint;
    private Button btUp;


    @Override
    protected int getLayoutContentId() {
        return R.layout.ac_update;
    }

    @Override
    protected void initContentView() {
        isShowToolView(false);
        setStatusBar(R.color.select_color);
        tvHint = findViewById(R.id.tv_hint);
        btUp = findViewById(R.id.bt_up);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        mPresenter.initVersion();
    }

    @Override
    protected void initListener() {
        super.initListener();
        btUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLoading();
                mPresenter.cleckVersion();
            }
        });
    }

    @Override
    public void showVersionName(String applicationVersionName) {
        tvHint.setText(applicationVersionName);
    }

    @Override
    public void showUpdateDialog(String url) {
        dimiss();
        new QMUIDialog.MessageDialogBuilder(this).setMessage("有最新版本,是否需要升级?")
                .addAction("取消", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        dialog.dismiss();
                    }
                })
                .addAction("立即升级", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        dialog.dismiss();
                        mPresenter.toUpdate(url);
                    }
                }).show();
    }

    @Override
    public void NOUpdate(String s) {
        dimiss();
        ToastUtility.showToast(s);
    }

    @Override
    public void updateFail(String msg) {
        dimiss();
        showDialog(msg);
    }

    @Override
    public void onDownStart(String msg) {
        showMyLoading( msg);
    }

    @Override
    public void onDownFail(String msg) {
        dimiss();
        showDialog(msg);
    }

    @Override
    public void onDownSuccess(File file) {
        dimiss();
        VersionUtil.installApk(file.getPath(), getContext());
    }

    @Override
    protected void dimiss() {
        super.dimiss();
        if (tipDialog!=null){
            tipDialog.dismiss();
        }
    }

    QMUITipDialog tipDialog;
    protected void showMyLoading(String message) {
        if (tipDialog==null){
             tipDialog = new QMUITipDialog.CustomBuilder(this)
                    .setContent(R.layout.dg_loading)
                    .create();
            tipDialog.setCancelable(false);
            tipDialog.show();
        }
        if (message!=null){
            TextView tv = tipDialog.findViewById(cn.faker.repaymodel.R.id.tv_content);
            tv.setText(message);
        }

    }
}
