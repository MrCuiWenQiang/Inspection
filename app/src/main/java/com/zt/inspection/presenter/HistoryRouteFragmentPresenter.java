package com.zt.inspection.presenter;


import android.text.TextUtils;

import com.zt.inspection.MyApplication;
import com.zt.inspection.Urls;
import com.zt.inspection.contract.HistoryRouteFragmentContract;
import com.zt.inspection.model.entity.response.SectionBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.faker.repaymodel.mvp.BaseMVPPresenter;
import cn.faker.repaymodel.net.json.JsonUtil;
import cn.faker.repaymodel.net.okhttp3.HttpHelper;
import cn.faker.repaymodel.net.okhttp3.callback.HttpResponseCallback;

public class HistoryRouteFragmentPresenter extends BaseMVPPresenter<HistoryRouteFragmentContract.View> implements HistoryRouteFragmentContract.Presenter {


    @Override
    public void loadDatas(int page, String departid) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("page", page);
        params.put("limit", 10);
        params.put("DEPARTLEADER", MyApplication.loginUser.getDEPARTLEADER());
        params.put("RoleId", MyApplication.loginUser.getRoleids());
        params.put("userId", MyApplication.loginUser.getPATROLCODE());
        params.put("Start", "");
        params.put("End", "");
        if (TextUtils.isEmpty(departid)) {
            params.put("DEPARTID", MyApplication.loginUser.getDEPARTID());

        } else {
            params.put("DEPARTID", departid);
        }

        HttpHelper.get(Urls.GETPATROLSECTION, params, new HttpResponseCallback() {
            @Override
            public void onSuccess(String datajson) {
                List<SectionBean> datas = JsonUtil.fromList(datajson, SectionBean.class);
                if (getView()!=null){
                    getView().loadSuccess(datas);
                }
            }

            @Override
            public void onFailed(int status, String message) {
                if (getView()!=null){
                    getView().loadFail(message);
                }
            }
        });
    }
}
