package com.zt.inspection.view.dialog;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.zt.inspection.R;

import cn.faker.repaymodel.util.ScreenUtil;
import cn.faker.repaymodel.widget.view.dialog.BasicDialog;

public class RoleDialog extends BasicDialog {
    private onRegisListener regisListener;

    @Override
    public int getLayoutId() {
        return R.layout.dg_role;
    }

    private EditText tvName;
    private TextView tvClean;
    private TextView tvNice;

    public RoleDialog setRegisListener(onRegisListener regisListener) {
        this.regisListener = regisListener;
        return this;
    }

    @Override
    public void initview(View v) {


        tvName = v.findViewById(R.id.tv_name);
        tvClean = v.findViewById(R.id.tv_clean);
        tvNice = v.findViewById(R.id.tv_nice);

    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }


    @Override
    protected void initListener() {
        super.initListener();
        tvClean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        tvNice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                regisListener.onRegistInfo(getValue(tvName));
            }
        });
    }
    protected String getValue(EditText text) {
        if (text == null) return null;
        if (TextUtils.isEmpty(text.getText())) {
            return null;
        } else {
            return text.getText().toString();
        }
    }

    public interface onRegisListener {
        void onRegistInfo(String info);
    }

    protected int getDialogWidth() {
        return ScreenUtil.getWindowWidth(getContext()) -10;
    }

    protected int getDialogHeght() {
        return ScreenUtil.getWindowHeight(getContext())* 3/11;

    }
}
