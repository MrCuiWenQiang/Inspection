package com.zt.inspection.presenter;


import com.zt.inspection.MyApplication;
import com.zt.inspection.Urls;
import com.zt.inspection.contract.PatrolSectionListContract;
import com.zt.inspection.model.entity.response.PatrolRlistBean;
import com.zt.inspection.model.entity.response.PatrolSectionListBean;

import java.util.HashMap;
import java.util.List;

import cn.faker.repaymodel.mvp.BaseMVPPresenter;
import cn.faker.repaymodel.net.json.JsonUtil;
import cn.faker.repaymodel.net.okhttp3.HttpHelper;
import cn.faker.repaymodel.net.okhttp3.callback.HttpResponseCallback;
import cn.faker.repaymodel.util.DateUtils;

public class PatrolSectionListPresenter extends BaseMVPPresenter<PatrolSectionListContract.View> implements PatrolSectionListContract.Presenter {
    @Override
    public void loadLoncal() {
        HashMap<String, Object> params = new HashMap<>();
        params.put("DEPARTLEADER", MyApplication.loginUser.getDEPARTLEADER());
        params.put("DEPARTID", MyApplication.loginUser.getDEPARTID());
        params.put("RoleId", MyApplication.loginUser.getRoleids());
        params.put("userId", MyApplication.loginUser.getPATROLCODE());
        params.put("Start", DateUtils.getCurrentDate());
        params.put("End", DateUtils.getCurrentDate());
        HttpHelper.get(Urls.GETPATROLSECTIONLIST, params, new HttpResponseCallback() {
            @Override
            public void onSuccess(String datajson) {
                List<PatrolSectionListBean> datas = JsonUtil.fromList(datajson, PatrolSectionListBean.class);
                if (datas!=null&&datas.size()>0){
                    if (getView() != null) {
                        getView().loadLoncalSuccess(datas);
                    }
                }else {
                    getView().loadLoncalFail("暂无数据");
                }
            }

            @Override
            public void onFailed(int status, String message) {
                if (getView() != null) {
                    getView().loadLoncalFail(message);
                }
            }
        });
    }
}
