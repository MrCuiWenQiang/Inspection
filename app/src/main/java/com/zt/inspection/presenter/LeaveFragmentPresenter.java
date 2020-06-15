package com.zt.inspection.presenter;


import android.text.TextUtils;

import com.zt.inspection.MyApplication;
import com.zt.inspection.Urls;
import com.zt.inspection.contract.LeaveFragmentContract;
import com.zt.inspection.model.entity.request.LeaveEntity;
import com.zt.inspection.model.entity.response.LeaveListBean;

import java.util.List;

import cn.faker.repaymodel.mvp.BaseMVPPresenter;
import cn.faker.repaymodel.net.json.JsonUtil;
import cn.faker.repaymodel.net.okhttp3.HttpHelper;
import cn.faker.repaymodel.net.okhttp3.callback.HttpResponseCallback;

public class LeaveFragmentPresenter extends BaseMVPPresenter<LeaveFragmentContract.View> implements LeaveFragmentContract.Presenter {

    @Override
    public void loadDatas(String start, String end) {
        if (TextUtils.isEmpty(start)||TextUtils.isEmpty(end)){
            getView().loadDatas_Fail("缺少必要参数");
            return;
        }

        LeaveEntity entity = new LeaveEntity();
        entity.setStart(start);
        entity.setEnd(end);
        entity.setUserId(MyApplication.loginUser.getPATROLCODE());
        HttpHelper.post(Urls.GETLEAVELIST, entity, new HttpResponseCallback() {
            @Override
            public void onSuccess(String data) {
                List<LeaveListBean> datas = JsonUtil.fromList(data,LeaveListBean.class);
                if (datas==null||datas.size()<=0){
                    if (getView()!=null){
                        getView().loadDatas_Fail("暂无数据");
                    }
                }else {
                    if (getView()!=null){
                        getView().loadDatas_success(datas);
                    }
                }
            }

            @Override
            public void onFailed(int status, String message) {
                if (getView()!=null){
                    getView().loadDatas_Fail(message);
                }
            }
        });
    }
}
