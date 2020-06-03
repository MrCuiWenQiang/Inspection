package com.zt.inspection.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.zt.inspection.R;
import com.zt.inspection.contract.UserInfoContract;
import com.zt.inspection.presenter.UserInfoPresenter;

import cn.faker.repaymodel.mvp.BaseMVPAcivity;

/*
 *用户信息
 */
public class UserInfoActivity extends BaseMVPAcivity<UserInfoContract.View, UserInfoPresenter> implements UserInfoContract.View, View.OnClickListener {

    private EditText[] editS;
    private Button bt_save;
    @Override
    protected int getLayoutContentId() {
        return R.layout.ac_userinfo;
    }

    @Override
    protected void initContentView() {
        setStatusBar(R.color.select_color);
        setTitle("用户信息", R.color.white);
        setToolBarBackgroundColor(R.color.select_color);
        setRightBtn("编辑", R.color.white, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editContent();
            }
        });

        bt_save = findViewById(R.id.bt_save);
    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public void onClick(View v) {

    }

    private void editContent(){
        if (editS[0].isFocusable()){
            setNoEdit();
        }else {
            setYESEdit();
        }
    }

    private void setNoEdit() {
        for (EditText editText : editS) {
            editText.setFocusable(false);
            editText.setFocusableInTouchMode(false);
        }
        bt_save.setVisibility(View.GONE);
    }

    private void setYESEdit() {
        for (EditText editText : editS) {
            editText.setFocusable(true);
            editText.setFocusableInTouchMode(true);
            editText.requestFocus();
        }
        bt_save.setVisibility(View.VISIBLE);
    }

}
