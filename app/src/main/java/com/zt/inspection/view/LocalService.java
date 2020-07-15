package com.zt.inspection.view;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.baidu.location.BDLocation;
import com.zt.inspection.MyApplication;
import com.zt.inspection.Urls;
import com.zt.inspection.model.entity.request.LocationBean;
import com.zt.inspection.util.LBS.LBSUtil;
import com.zt.inspection.util.LBS.LocationListener;

import java.util.Date;

import cn.faker.repaymodel.net.okhttp3.HttpHelper;
import cn.faker.repaymodel.net.okhttp3.callback.HttpResponseCallback;

/**
 * 定位上传服务
 */
public class LocalService extends Service implements LocationListener {
    private Date oldDate;
    private long timer = 1000*60;
    private boolean isFinle = false;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        LBSUtil.addListener(this);
        LBSUtil.start();
    }



    @Override
    public void onDestroy() {
        LBSUtil.removeListener(this);
        uploadInfo(-1, -1, "0");
        super.onDestroy();
    }

    @Override
    public void onReceiveLocation(BDLocation location, int errorCode, double latitude, double longitude) {
        if (errorCode == 61 || errorCode == 161) {
            Date date = new Date();
            if (oldDate == null) {
                oldDate = date;
                uploadInfo(latitude, longitude, "1");
            } else {
                long oldTime = oldDate.getTime();
                long nowTime = date.getTime();
                if (nowTime - oldTime >= timer) {
                    oldDate = date;
                    uploadInfo(latitude, longitude, "1");
                }
            }

        }
    }


    private void uploadInfo(double latitude, double longitude, String isline) {
        if (isFinle) {
            return;
        }
        isFinle = true;
        LocationBean locationBean = new LocationBean();
        locationBean.setUSERID(MyApplication.loginUser.getPATROLCODE());
        locationBean.setUSERNAME(MyApplication.loginUser.getUserName());
        locationBean.setDEPARTID(MyApplication.loginUser.getDEPARTID());
        locationBean.setISLINE(isline);
        locationBean.setX(String.valueOf(latitude));
        locationBean.setY(String.valueOf(longitude));
        HttpHelper.post(Urls.LOCATION, locationBean, new HttpResponseCallback() {
            @Override
            public void onSuccess(String datajson) {


                isFinle = false;
            }

            @Override
            public void onFailed(int status, String message) {
                isFinle = false;
            }
        });
    }
}
