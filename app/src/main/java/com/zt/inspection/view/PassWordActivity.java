package com.zt.inspection.view;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;
import com.zt.inspection.MyApplication;
import com.zt.inspection.R;
import com.zt.inspection.Urls;
import com.zt.inspection.model.entity.request.ModifyPOWBean;

import java.util.HashMap;

import cn.faker.repaymodel.activity.BaseToolBarActivity;
import cn.faker.repaymodel.net.okhttp3.HttpHelper;
import cn.faker.repaymodel.net.okhttp3.callback.HttpResponseCallback;
import cn.faker.repaymodel.util.ToastUtility;

public class PassWordActivity extends BaseToolBarActivity {

    private EditText etPasswordOld;
    private EditText etPasswordNow;
    private EditText etPasswordNowN;
    private Button btSave;

    @Override
    protected int getLayoutContentId() {
        return R.layout.ac_pw;
    }

    @Override
    protected void initContentView() {
        setStatusBar(R.color.select_color);
        setTitle("修改密码", R.color.white);
        setToolBarBackgroundColor(R.color.select_color);

        etPasswordOld = findViewById(R.id.et_password_old);
        etPasswordNow = findViewById(R.id.et_password_now);
        etPasswordNowN = findViewById(R.id.et_password_now_n);
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
                toSave();
            }
        });
    }

    private void toSave() {
        String pwOld = getValue(etPasswordOld);
        String pwNow = getValue(etPasswordNow);
        String pwNowN = getValue(etPasswordNowN);
        if (TextUtils.isEmpty(pwOld)){
            ToastUtility.showToast("请输入原密码!");
            return;
        }
        if (TextUtils.isEmpty(pwNow)){
            ToastUtility.showToast("请输入新密码!");
            return;
        }
        if (TextUtils.isEmpty(pwNowN)){
            ToastUtility.showToast("请确认输入新密码!");
            return;
        }
        if (!pwNow.equals(pwNowN)){
            ToastUtility.showToast("两次输入的新密码不一致!");
            return;
        }
        showLoading();
/*        HashMap<String, Object> params = new HashMap<>();
        params.put("PATROLCODE", MyApplication.loginUser.getPATROLCODE());
        params.put("Password", pwOld);
        params.put("Password1", pwNow);*/
        ModifyPOWBean bean = new ModifyPOWBean(pwNow,pwOld,MyApplication.loginUser.getPATROLCODE());
        HttpHelper.post(Urls.MODIFYPOW, bean, new HttpResponseCallback() {
            @Override
            public void onSuccess(String datajson) {
                dimiss();
                showDialog("修改成功", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        dialog.dismiss();
                        finish();
                    }
                });
            }

            @Override
            public void onFailed(int status, String message) {
                dimiss();
                ToastUtility.showToast(message);
            }
        });
    }
}
