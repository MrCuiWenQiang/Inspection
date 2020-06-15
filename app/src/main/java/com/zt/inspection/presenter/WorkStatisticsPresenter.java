package com.zt.inspection.presenter;

import com.zt.inspection.MyApplication;
import com.zt.inspection.Urls;
import com.zt.inspection.contract.WorkStatisticsFragmentContract;
import com.zt.inspection.model.entity.response.WorkDeptBean;
import com.zt.inspection.model.entity.response.WorkUserBean;
import com.zt.inspection.util.RoleIdUtil;

import java.util.HashMap;
import java.util.List;

import cn.faker.repaymodel.mvp.BaseMVPPresenter;
import cn.faker.repaymodel.net.json.JsonUtil;
import cn.faker.repaymodel.net.okhttp3.HttpHelper;
import cn.faker.repaymodel.net.okhttp3.callback.HttpResponseCallback;


public class WorkStatisticsPresenter extends BaseMVPPresenter<WorkStatisticsFragmentContract.View> implements WorkStatisticsFragmentContract.Presenter {


    @Override
    public void search(String startDate, String endDate) {
        if (RoleIdUtil.isBUMEN()) {
            getManualData(startDate, endDate);
        } else {
            getListData(startDate, endDate);
        }
    }

    @Override
    public void getListData(String startDate, String endDate) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("userId", MyApplication.loginUser.getDEPARTID());
        params.put("startDate", endDate);
        params.put("endDate", endDate);
        HttpHelper.get(Urls.GETSIGNTJ, params, new HttpResponseCallback() {
            @Override
            public void onSuccess(String datajson) {
                List<WorkUserBean> data = JsonUtil.fromList(datajson, WorkUserBean.class);
                if (data != null) {
                    if (getView() != null) {
                        getView().getListDataSuccess(data);
                    }
                } else {
                    if (getView() != null) {
                        getView().getListDataFail("暂无数据");
                    }
                }
            }

            @Override
            public void onFailed(int status, String message) {
                if (getView() != null) {
                    getView().getListDataFail(message);
                }
            }
        });
    }

    @Override
    public void getManualData(String startDate, String endDate) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("userid", MyApplication.loginUser.getPATROLCODE());
        params.put("deptid", MyApplication.loginUser.getDEPARTID());
        params.put("startDate", endDate);
        params.put("endDate", endDate);
        HttpHelper.get(Urls.DEPTSIGN, params, new HttpResponseCallback() {
            @Override
            public void onSuccess(String datajson) {
                WorkDeptBean data = JsonUtil.convertJsonToObject(datajson, WorkDeptBean.class);
                if (data != null) {
                    if (getView() != null) {
                        getView().getManualDataSuccess(data);
                    }
                } else {
                    if (getView() != null) {
                        getView().getManualDataFail("暂无数据");
                    }
                }
            }

            @Override
            public void onFailed(int status, String message) {
                if (getView() != null) {
                    getView().getManualDataFail(message);
                }
            }
        });
    }
}
