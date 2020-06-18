package com.zt.inspection.presenter;



import com.zt.inspection.Urls;
import com.zt.inspection.contract.NoticeFragmentContract;
import com.zt.inspection.model.entity.response.NoticeBean;

import java.util.HashMap;
import java.util.List;

import cn.faker.repaymodel.mvp.BaseMVPPresenter;
import cn.faker.repaymodel.net.json.JsonUtil;
import cn.faker.repaymodel.net.okhttp3.HttpHelper;
import cn.faker.repaymodel.net.okhttp3.callback.HttpResponseCallback;

public class NoticeFragmentPresenter extends BaseMVPPresenter<NoticeFragmentContract.View> implements NoticeFragmentContract.Presenter {

    @Override
    public void loadData(int page, String type) {

        HashMap<String, Object> params = new HashMap<>();
        params.put("TYPE", type);
        params.put("page", page);
        params.put("limit", 10);
        params.put("strWhere", "");
        params.put("start", "");
        params.put("end", "");
        HttpHelper.get(Urls.GETNOTICELIST, params, new HttpResponseCallback() {
            @Override
            public void onSuccess(String data) {
                List<NoticeBean> noticeBeans = JsonUtil.fromList(data, NoticeBean.class);
                if (noticeBeans != null && noticeBeans.size() > 0) {
                    getView().loadData_Success(noticeBeans);
                } else {
                    getView().loadData_Fail("暂无数据");
                }
            }

            @Override
            public void onFailed(int status, String message) {
                getView().loadData_Fail(message);
            }
        });
    }
}
