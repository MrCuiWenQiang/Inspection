package com.zt.inspection.presenter;


import com.zt.inspection.MyApplication;
import com.zt.inspection.Urls;
import com.zt.inspection.contract.BranchModelContract;
import com.zt.inspection.model.entity.response.BranchBean;

import java.util.HashMap;
import java.util.List;

import cn.faker.repaymodel.mvp.BaseMVPPresenter;
import cn.faker.repaymodel.net.json.JsonUtil;
import cn.faker.repaymodel.net.okhttp3.HttpHelper;
import cn.faker.repaymodel.net.okhttp3.callback.HttpResponseCallback;

public class BranchModelPresenter extends BaseMVPPresenter<BranchModelContract.View> implements BranchModelContract.Presenter {

    @Override
    public void queryData() {
        HashMap<String, Object> params = new HashMap<>();
        params.put("DEPARTID", MyApplication.loginUser.getDEPARTID());
        HttpHelper.get(Urls.GETBRANCHLIST, params, new HttpResponseCallback() {
            @Override
            public void onSuccess(String datajson) {
                List<BranchBean> datas = JsonUtil.fromList(datajson, BranchBean.class);
            }

            @Override
            public void onFailed(int status, String message) {

            }
        });
    }
}
