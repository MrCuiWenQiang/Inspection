package com.zt.inspection.presenter;


import android.text.TextUtils;


import com.zt.inspection.contract.SettingContract;
import com.zt.inspection.util.HttpUtil;

import cn.faker.repaymodel.mvp.BaseMVPPresenter;
import cn.faker.repaymodel.util.error.ErrorUtil;

public class SettingPresenter extends BaseMVPPresenter<SettingContract.View> implements SettingContract.Presenter {

    @Override
    public void attachView(SettingContract.View view) {
        super.attachView(view);
        getHttpSetting();
    }



    @Override
    public void getHttpSetting() {
        getView().getHttpSettingSuccess(HttpUtil.getHttpSetting_ip(),String.valueOf(HttpUtil.getHttpSetting_port()));
    }



    @Override
    public void settingHttpSetting(String ip_address, String port) {
        if (TextUtils.isEmpty(ip_address)||TextUtils.isEmpty(port)){
            getView().fail("请填写完整信息!");
            return;
        }
        int portNumber = Integer.valueOf(port);
        if (portNumber<=0){
            getView().fail("请填写完整信息!");
            return;
        }
        HttpUtil.settingHttpSetting(ip_address,portNumber);
        getView().settingSetting_success("设置成功");
    }
}
