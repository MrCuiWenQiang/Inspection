package com.zt.inspection.presenter;


import android.text.TextUtils;

import com.zt.inspection.Urls;
import com.zt.inspection.contract.NoticeContract;
import com.zt.inspection.model.entity.request.NoticeEntity;
import com.zt.inspection.model.entity.response.NoticeBean;

import java.util.List;

import cn.faker.repaymodel.mvp.BaseMVPPresenter;
import cn.faker.repaymodel.net.json.JsonUtil;
import cn.faker.repaymodel.net.okhttp3.HttpHelper;
import cn.faker.repaymodel.net.okhttp3.callback.HttpResponseCallback;

public class NoticePresenter extends BaseMVPPresenter<NoticeContract.View> implements NoticeContract.Presenter {

    @Override
    public void loadData(String txt_search, String start, String end) {
        if (TextUtils.isEmpty(txt_search)&&(TextUtils.isEmpty(start)||TextUtils.isEmpty(end))){
            getView().loadData_Fail("缺少必要参数");
            return;
        }
        NoticeEntity noticeEntity = new NoticeEntity(txt_search,start,end);
        HttpHelper.post(Urls.GETNOTICELIST, noticeEntity, new HttpResponseCallback() {
            @Override
            public void onSuccess(String data) {
                List<NoticeBean> noticeBeans = JsonUtil.fromList(data,NoticeBean.class);
                if (noticeBeans!=null&&noticeBeans.size()>0){
                    getView().loadData_Success(noticeBeans);
                }else {
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
