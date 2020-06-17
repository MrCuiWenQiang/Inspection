package com.zt.inspection.presenter;

import com.zt.inspection.MyApplication;
import com.zt.inspection.Urls;
import com.zt.inspection.contract.PatrolRouteContract;
import com.zt.inspection.model.entity.response.PatrikRouteBean;

import java.util.HashMap;
import java.util.List;

import cn.faker.repaymodel.mvp.BaseMVPPresenter;
import cn.faker.repaymodel.net.json.JsonUtil;
import cn.faker.repaymodel.net.okhttp3.HttpHelper;
import cn.faker.repaymodel.net.okhttp3.callback.HttpResponseCallback;

public class PatrolRoutePresenter extends BaseMVPPresenter<PatrolRouteContract.View> implements PatrolRouteContract.Presenter {
    @Override
    public void queryData(String id) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("userId", MyApplication.loginUser.getPATROLCODE());
        params.put("PATROLSECTIONID", id);
        params.put("Start", "");
        params.put("End", "");
        HttpHelper.get(Urls.GETPATROLROUTE, params, new HttpResponseCallback() {
            @Override
            public void onSuccess(String datajson) {
                List<PatrikRouteBean> datas = JsonUtil.fromList(datajson, PatrikRouteBean.class);
                if (datas != null && datas.size() > 0) {
                    if (getView() != null) {
                        getView().queryDataSuccess(datas);
                    }
                } else {
                    if (getView() != null) {
                        getView().queryDataFinsh("未查询到路线信息");
                    }
                }
            }

            @Override
            public void onFailed(int status, String message) {
                if (getView() != null) {
                    getView().queryDataFinsh(message);
                }
            }
        });
    }
}
