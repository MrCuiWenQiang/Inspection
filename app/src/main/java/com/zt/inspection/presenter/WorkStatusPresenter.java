package com.zt.inspection.presenter;


import android.text.TextUtils;

import com.zt.inspection.MyApplication;
import com.zt.inspection.Urls;
import com.zt.inspection.contract.WorkStatusContract;
import com.zt.inspection.model.entity.response.CaseInfoBean;

import java.util.HashMap;
import java.util.List;

import cn.faker.repaymodel.mvp.BaseMVPPresenter;
import cn.faker.repaymodel.net.json.JsonUtil;
import cn.faker.repaymodel.net.okhttp3.HttpHelper;
import cn.faker.repaymodel.net.okhttp3.callback.HttpResponseCallback;

public class WorkStatusPresenter extends BaseMVPPresenter<WorkStatusContract.View> implements WorkStatusContract.Presenter {

    @Override
    public void loaddata(int page, String bumenid,String status) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("DEPARTLEADER", MyApplication.loginUser.getDEPARTLEADER());
        params.put("RoleId", MyApplication.loginUser.getRoleids());
        params.put("userId", MyApplication.loginUser.getPATROLCODE());
        if (TextUtils.isEmpty(bumenid)) {
            params.put("bumenid", MyApplication.loginUser.getDEPARTID());

        } else {
            params.put("bumenid", bumenid);
        }
        params.put("page", page);
        params.put("limit", 10);
        params.put("CSTATE", status);
        params.put("strWhere", "");
        params.put("CDateDateTime", "");
        params.put("CloseDateTime", "");
        HttpHelper.get(Urls.GETCASEINFOLIST, params, new HttpResponseCallback() {
            @Override
            public void onSuccess(String datajson) {
                List<CaseInfoBean> datas = JsonUtil.fromList(datajson,CaseInfoBean.class);
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
