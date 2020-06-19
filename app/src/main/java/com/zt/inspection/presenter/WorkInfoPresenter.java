package com.zt.inspection.presenter;


import com.zt.inspection.Urls;
import com.zt.inspection.contract.WorkInfoContract;
import com.zt.inspection.model.entity.response.WordInfoBean;

import java.util.HashMap;
import java.util.List;

import cn.faker.repaymodel.mvp.BaseMVPPresenter;
import cn.faker.repaymodel.net.json.JsonUtil;
import cn.faker.repaymodel.net.okhttp3.HttpHelper;
import cn.faker.repaymodel.net.okhttp3.callback.HttpResponseCallback;

public class WorkInfoPresenter extends BaseMVPPresenter<WorkInfoContract.View> implements WorkInfoContract.Presenter {
    @Override
    public void queryDatas(String id) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("id", id);
        HttpHelper.get(Urls.GETQUERYINFO, params, new HttpResponseCallback() {
            @Override
            public void onSuccess(String datajson) {
                List<WordInfoBean> datas = JsonUtil.fromList(datajson,WordInfoBean.class);
                if (datas!=null||datas.size()>0){
                    getView().queryDatasSuccess(datas);
                }else {
                    getView().queryDatasFail("暂无案件记录");
                }
            }

            @Override
            public void onFailed(int status, String message) {
                getView().queryDatasFail(message);
            }
        });
    }
}
