package com.zt.inspection.presenter;


import com.alibaba.fastjson.JSONObject;
import com.zt.inspection.MyApplication;
import com.zt.inspection.Urls;
import com.zt.inspection.contract.WorkFragmentContract;
import com.zt.inspection.model.entity.request.AddClockinEntity;
import com.zt.inspection.model.entity.response.ClockInBean;

import java.util.HashMap;

import cn.faker.repaymodel.mvp.BaseMVPPresenter;
import cn.faker.repaymodel.net.json.JsonUtil;
import cn.faker.repaymodel.net.okhttp3.HttpHelper;
import cn.faker.repaymodel.net.okhttp3.callback.HttpResponseCallback;

public class WorkFragmentPresenter extends BaseMVPPresenter<WorkFragmentContract.View> implements WorkFragmentContract.Presenter {

    @Override
    public void GetCLOCKINList() {
        HashMap<String, Object> params = new HashMap<>();
        params.put("DEPARTID", MyApplication.loginUser.getDEPARTID());
        HttpHelper.get(Urls.GETCLOCKINLIST, params, new HttpResponseCallback() {
            @Override
            public void onSuccess(String data) {
                ClockInBean bean = JsonUtil.convertJsonToObject(data, ClockInBean.class);
                if (bean != null) {
                    getView().GetCLOCKINList_Success(bean);
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
    public void AddCLOCKIN(String SIGNDESC, String OUTTIME) {
        AddClockinEntity entity = new AddClockinEntity(MyApplication.loginUser.getUserId(), SIGNDESC, OUTTIME);
        HttpHelper.post(Urls.ADDCLOCKIN, entity, new HttpResponseCallback() {
            @Override
            public void onSuccess(String data) {
                JSONObject jsonObject = JSONObject.parseObject(data);
                String statusValue = jsonObject.getString("result");
                if ("成功".equals(statusValue)) {
                    int status = jsonObject.getInteger("state");
                    String v = null;
                    switch (status) {
                        case 0: {
                            v = "正常";
                            break;
                        }
                        case 1: {
                            v = "缺卡";
                            break;
                        }
                        case 2: {
                            v = "迟到";
                            break;
                        }
                        case 3: {
                            v = "早退";
                            break;
                        }
                        case 4: {
                            v = "旷工";
                            break;
                        }
                        default: {
                            v = "正常";
                        }
                    }
                    getView().AddCLOCKIN_success( OUTTIME,status,v);
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
    public void isInArea(double x, double y) {

    }

    @Override
    public void todayInfo() {
        
    }


}
