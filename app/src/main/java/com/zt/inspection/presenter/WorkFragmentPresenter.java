package com.zt.inspection.presenter;


import com.alibaba.fastjson.JSONObject;
import com.zt.inspection.MyApplication;
import com.zt.inspection.Urls;
import com.zt.inspection.contract.WorkFragmentContract;
import com.zt.inspection.model.entity.request.AddClockinEntity;
import com.zt.inspection.model.entity.request.UserIdEntity;
import com.zt.inspection.model.entity.response.ClockInBean;
import com.zt.inspection.model.entity.response.TodayBean;
import com.zt.inspection.util.MyMathUtil;
import com.zt.inspection.util.WorkUtil;

import java.util.HashMap;
import java.util.List;

import cn.faker.repaymodel.mvp.BaseMVPPresenter;
import cn.faker.repaymodel.net.json.JsonUtil;
import cn.faker.repaymodel.net.okhttp3.HttpHelper;
import cn.faker.repaymodel.net.okhttp3.callback.HttpResponseCallback;

public class WorkFragmentPresenter extends BaseMVPPresenter<WorkFragmentContract.View> implements WorkFragmentContract.Presenter {
    private ClockInBean workData;

    @Override
    public void GetCLOCKINList() {
        HashMap<String, Object> params = new HashMap<>();
        params.put("DEPARTID", MyApplication.loginUser.getDEPARTID());
        HttpHelper.get(Urls.GETCLOCKINLIST, params, new HttpResponseCallback() {
            @Override
            public void onSuccess(String data) {
                workData = JsonUtil.convertJsonToObject(data, ClockInBean.class);
                if (workData != null) {
                    todayInfo();
                } else {
                    getView().GetCLOCKINList_Fail("获取不到打卡所属数据，请重试");
                }
            }

            @Override
            public void onFailed(int status, String message) {
                getView().GetCLOCKINList_Fail("获取不到打卡所属数据，请重试");
            }
        });
    }

    @Override
    public void AddCLOCKIN(String remarks) {
        AddClockinEntity entity = new AddClockinEntity(MyApplication.loginUser.getPATROLCODE(), remarks,MyApplication.loginUser.getDEPARTID());
        HttpHelper.post(Urls.ADDCLOCKIN, entity, new HttpResponseCallback() {
            @Override
            public void onSuccess(String data) {
                JSONObject jsonObject = JSONObject.parseObject(data);
                String statusValue = jsonObject.getString("result");
                if ("成功".equals(statusValue)) {
                    int status = jsonObject.getInteger("state");
                    String v =WorkUtil.typeName(status);
                    getView().AddCLOCKIN_success(status, v);
                } else if ("失败".equals(statusValue)) {
                    getView().AddCLOCKIN_fail("打卡失败");
                }
            }

            @Override
            public void onFailed(int status, String message) {
                getView().AddCLOCKIN_fail(message);
            }
        });

    }

    @Override
    public boolean isInArea(double x, double y) {
        if (workData != null) {
            double point_x = Double.valueOf(workData.getX());
            double point_Y = Double.valueOf(workData.getX());
            return MyMathUtil.isHave(point_x,point_Y,x,y,Double.valueOf(workData.getRADIUS()));
        } else {
            return false;
        }
    }

    @Override
    public void todayInfo() {
//        UserIdEntity querydata = new UserIdEntity(MyApplication.loginUser.getUserId());
        HashMap<String, Object> params = new HashMap<>();
        params.put("userId", MyApplication.loginUser.getDEPARTID());
        HttpHelper.get(Urls.GETUSERSTATE, params, new HttpResponseCallback() {
            @Override
            public void onSuccess(String data) {
                List<TodayBean> datas = JsonUtil.fromList(data, TodayBean.class);
                if (datas == null || datas.size() <= 0) {
                    getView().todayinfo_visible();
                } else {
                    getView().todayinfo_gone(datas);
                }
            }

            @Override
            public void onFailed(int status, String message) {
                getView().todayinfo_final(message);
            }
        });
    }


}
