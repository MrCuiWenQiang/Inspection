package com.zt.inspection.view.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.baidu.location.BDLocation;
import com.zt.inspection.MyApplication;
import com.zt.inspection.Urls;
import com.zt.inspection.model.entity.request.UploadLocalBean;
import com.zt.inspection.util.LBS.LBSUtil;
import com.zt.inspection.util.LBS.LocationListener;

import cn.faker.repaymodel.net.okhttp3.HttpHelper;
import cn.faker.repaymodel.net.okhttp3.callback.HttpResponseCallback;

public class UploadLocalService extends Service implements LocationListener {

    public static final String KEY_ROLEID = "KEY_ROLEID";

    private String roleId = null;

    @Override
    public IBinder onBind(Intent intent) {
        roleId = intent.getStringExtra(KEY_ROLEID);
        return null;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init() {
        LBSUtil.addListener(this);
    }

    @Override
    public void onReceiveLocation(BDLocation location, int errorCode, double latitude, double longitude) {
        double lat = location.getLatitude();//纬度
        double lon = location.getLongitude();//经度
        uploadLocal(lat, lon, roleId);
    }

    public void uploadLocal(double lat, double lon, String id) {
        // TODO: 2020/6/18 因为后台数据库将坐标 x y倒置问题 故反过来
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

    @Override
    public boolean onUnbind(Intent intent) {
        LBSUtil.removeListener(this);
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
