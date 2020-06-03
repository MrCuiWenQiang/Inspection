package com.zt.inspection.presenter;


import com.zt.inspection.MyApplication;
import com.zt.inspection.Urls;
import com.zt.inspection.contract.LeaveInfoContract;
import com.zt.inspection.model.entity.request.LeaveInfoEntity;
import com.zt.inspection.model.entity.response.AdoptBean;
import com.zt.inspection.model.entity.response.LeaveInfoBean;
import com.zt.inspection.model.entity.response.RejectBean;

import cn.faker.repaymodel.mvp.BaseMVPPresenter;
import cn.faker.repaymodel.net.json.JsonUtil;
import cn.faker.repaymodel.net.okhttp3.HttpHelper;
import cn.faker.repaymodel.net.okhttp3.callback.HttpResponseCallback;

public class LeaveInfoPresenter extends BaseMVPPresenter<LeaveInfoContract.View> implements LeaveInfoContract.Presenter {
    private String id;
    @Override
    public void loadData(String id) {
        this.id = id;
        LeaveInfoEntity entity = new LeaveInfoEntity(id);
        HttpHelper.post(Urls.GETMODEL, entity, new HttpResponseCallback() {
            @Override
            public void onSuccess(String data) {
                LeaveInfoBean bean = JsonUtil.convertJsonToObject(data,LeaveInfoBean.class);
                if (getView()!=null){
                    if (bean==null){
                        getView().loadDatas_Fail("暂无数据");
                    }else {
                        getView().loadDatas_Success(bean);
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

    @Override
    public void showOpen() {
        String type = MyApplication.loginUser.getRoleId();

    }

    @Override
    public void adopt(String audit_id, String leave_id, String AUDIT_INFO) {
        AdoptBean bean = new AdoptBean(audit_id,leave_id,AUDIT_INFO);
        HttpHelper.post(Urls.ADOPT, bean, new HttpResponseCallback() {
            @Override
            public void onSuccess(String data) {
                if (data.equals("ok")){
                    loadData(id);
                }else {
                    getView().adopt_fail("请求失败");
                }
            }

            @Override
            public void onFailed(int status, String message) {
                getView().adopt_fail(message);
            }
        });
    }

    @Override
    public void reject(String audit_id, String leave_id, String AUDIT_INFO) {
        RejectBean bean = new RejectBean(audit_id,leave_id,AUDIT_INFO);
        HttpHelper.post(Urls.REJECT, bean, new HttpResponseCallback() {
            @Override
            public void onSuccess(String data) {
                if (data.equals("ok")){
                    loadData(id);
                }else {
                    getView().reject_fail("请求失败");
                }
            }

            @Override
            public void onFailed(int status, String message) {
                getView().reject_fail(message);
            }
        });
    }
}
