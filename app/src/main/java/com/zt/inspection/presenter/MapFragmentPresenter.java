package com.zt.inspection.presenter;


import android.text.TextUtils;

import com.zt.inspection.MyApplication;
import com.zt.inspection.Urls;
import com.zt.inspection.contract.MapFragmentContract;
import com.zt.inspection.model.entity.request.EndPatrolSectionBean;
import com.zt.inspection.model.entity.request.PatrolSectionEntity;
import com.zt.inspection.model.entity.request.UploadLocalBean;
import com.zt.inspection.model.entity.response.PatrolSectionBean;


import java.util.HashMap;

import cn.faker.repaymodel.mvp.BaseMVPPresenter;
import cn.faker.repaymodel.net.json.JsonUtil;
import cn.faker.repaymodel.net.okhttp3.HttpHelper;
import cn.faker.repaymodel.net.okhttp3.callback.HttpResponseCallback;

public class MapFragmentPresenter extends BaseMVPPresenter<MapFragmentContract.View> implements MapFragmentContract.Presenter {


    @Override
    public void startLine(String name) {
        PatrolSectionEntity requestEntity = new PatrolSectionEntity(MyApplication.loginUser.getPATROLCODE()
                , MyApplication.loginUser.getDEPARTID(), name);
        HttpHelper.post(Urls.ADDPATROLSECTION, requestEntity, new HttpResponseCallback() {
            @Override
            public void onSuccess(String datajson) {
                PatrolSectionBean data = JsonUtil.convertJsonToObject(datajson, PatrolSectionBean.class);
                if (data != null) {
                    getView().startLine_Success(data);
                } else {
                    getView().startLine_Fail("开始巡检失败");
                }
            }

            @Override
            public void onFailed(int status, String message) {
                if (getView() != null) {
                    getView().startLine_Fail(message);
                }
            }
        });
    }

    @Override
    public void endLine(String id) {
        EndPatrolSectionBean bean = new EndPatrolSectionBean();
        bean.setPATROLSECTIONID(id);
        HttpHelper.post(Urls.EDITPATROLSECTION, bean, new HttpResponseCallback() {
            @Override
            public void onSuccess(String datajson) {
                if ("ok".equals(datajson)) {
                    getView().endLine_Success();

                } else {
                    getView().endLine_Fail("结束巡检失败");
                }
            }

            @Override
            public void onFailed(int status, String message) {
                if (getView() != null) {
                    getView().endLine_Fail(message);
                }
            }
        });
    }

    @Override
    public void uploadLocal(double lat, double lon, String id) {
        // TODO: 2020/6/18 因为后台数据库将坐标 x y倒置问题 故反过来

//        UploadLocalBean requestBean = new UploadLocalBean(String.valueOf(lat), String.valueOf(lon), id, MyApplication.loginUser.getPATROLCODE());
        UploadLocalBean requestBean = new UploadLocalBean(String.valueOf(lon), String.valueOf(lat), id, MyApplication.loginUser.getPATROLCODE());
           HttpHelper.post(Urls.ADDPATROLROUTE, requestBean, new HttpResponseCallback() {
            @Override
            public void onSuccess(String datajson) {

            }

            @Override
            public void onFailed(int status, String message) {

            }
        });
    }


}
