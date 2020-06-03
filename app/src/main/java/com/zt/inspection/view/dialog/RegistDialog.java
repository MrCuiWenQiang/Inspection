package com.zt.inspection.view.dialog;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.zt.inspection.R;

import cn.faker.repaymodel.widget.view.dialog.BasicDialog;

public class RegistDialog extends BasicDialog {


    private EditText tv_remarks;
    private TextView tv_clean;
    private TextView tv_nice;




    private onRegisListener regisListener;


    public RegistDialog setRegisListener(onRegisListener regisListener) {
        this.regisListener = regisListener;
        return this;
    }

    @Override
    public int getLayoutId() {
        return R.layout.dg_regist;
    }

    @Override
    public void initview(View v) {

        tv_remarks = v.findViewById(R.id.tv_remarks);
        tv_clean = v.findViewById(R.id.tv_clean);
        tv_nice = v.findViewById(R.id.tv_nice);

    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void initListener() {
        super.initListener();
        tv_clean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        tv_clean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                regisListener.onRegistInfo(getValue(tv_remarks));
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
}
