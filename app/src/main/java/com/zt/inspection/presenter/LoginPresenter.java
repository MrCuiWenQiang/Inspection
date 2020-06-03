package com.zt.inspection.presenter;


import android.support.v4.app.Fragment;
import android.text.TextUtils;

import com.zt.inspection.MyApplication;
import com.zt.inspection.R;
import com.zt.inspection.Urls;
import com.zt.inspection.contract.LoginContract;
import com.zt.inspection.model.LoginModel;
import com.zt.inspection.model.entity.db.UserDBEntity;
import com.zt.inspection.model.entity.request.LoginEntity;
import com.zt.inspection.model.entity.response.LoginBean;
import com.zt.inspection.model.entity.view.MainTableBean;
import com.zt.inspection.view.fragment.MapFragment;
import com.zt.inspection.view.fragment.MyFragment;

import java.util.ArrayList;
import java.util.List;

import cn.faker.repaymodel.mvp.BaseMVPPresenter;
import cn.faker.repaymodel.net.json.JsonUtil;
import cn.faker.repaymodel.net.okhttp3.HttpHelper;
import cn.faker.repaymodel.net.okhttp3.callback.HttpResponseCallback;

public class LoginPresenter extends BaseMVPPresenter<LoginContract.View> implements LoginContract.Presenter {
    private LoginModel loginModel = new LoginModel();


    @Override
    public void giveUserInfo() {
        loginModel.findUserByLimit(new LoginContract.Model.OnFindUser() {
            @Override
            public void onSuccess(UserDBEntity data) {
                if (getView() != null && data != null) {
                    getView().settingUser(data.getName(), data.getPassword());
                }
            }

            @Override
            public void onfail() {

            }
        });
    }

    @Override
    public void login(String name, String pw, boolean checked) {
        if (TextUtils.isEmpty(name)) {
            getView().login_Fail("请输入用户名!");
            return;
        }
        if (TextUtils.isEmpty(pw)) {
            getView().login_Fail("请输入密码!");
            return;
        }
        LoginEntity dataForm = new LoginEntity(name,pw);
        HttpHelper.post(Urls.LOGIN, dataForm, new HttpResponseCallback() {
            @Override
            public void onSuccess(String data) {
                LoginBean resultEntity = JsonUtil.convertJsonToObject(data, LoginBean.class);
                if (getView() != null) {
                    if (resultEntity != null) {
                        if (checked){
                            loginModel.saveUser(dataForm.getUserid(), dataForm.getPassword(), resultEntity);
                        }
                        MyApplication.loginUser = resultEntity;
                        getView().login_Success();
                    }else {
                        getView().login_Fail("数据解析失败");
                    }
                }

            }

            @Override
            public void onFailed(int status, String message) {
                if (getView() != null) {
                    getView().login_Fail(message);
                }
            }
        });

    }
}
