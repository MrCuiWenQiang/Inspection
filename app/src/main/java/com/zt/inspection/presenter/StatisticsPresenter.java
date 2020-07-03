package com.zt.inspection.presenter;

import com.zt.inspection.Urls;
import com.zt.inspection.contract.StatisticsContract;
import com.zt.inspection.model.entity.response.HomeBean;

import java.util.HashMap;

import cn.faker.repaymodel.mvp.BaseMVPPresenter;
import cn.faker.repaymodel.net.json.JsonUtil;
import cn.faker.repaymodel.net.okhttp3.HttpHelper;
import cn.faker.repaymodel.net.okhttp3.callback.HttpResponseCallback;

public class StatisticsPresenter extends BaseMVPPresenter<StatisticsContract.View> implements StatisticsContract.Presenter {

    @Override
    public void loadData(int year, int month) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("year", year);
        params.put("month", month);
        HttpHelper.get(Urls.APPHOME, params, new HttpResponseCallback() {
            @Override
            public void onSuccess(String datajson) {
                HomeBean data = JsonUtil.convertJsonToObject(datajson, HomeBean.class);
                if (data != null) {
                    getView().loadSuccess(year, month, data);
                } else {
                    getView().loadFail("加载失败");
                }
            }

            @Override
            public void onFailed(int status, String message) {
                if (getView() != null) {
                    getView().loadFail(message);
                }
            }
        });
    }
}
