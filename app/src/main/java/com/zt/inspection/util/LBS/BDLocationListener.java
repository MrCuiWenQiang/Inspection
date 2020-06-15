package com.zt.inspection.util.LBS;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;

import java.util.List;

import cn.faker.repaymodel.util.LogUtil;

public class BDLocationListener extends BDAbstractLocationListener {

    private List<LocationListener> locationListeners;
    private TransGPS transGPS = null;

    @Override
    public void onReceiveLocation(BDLocation location) {
        if (transGPS == null) {
            transGPS = new TransGPS();
        }
        double latitude = location.getLatitude();    //获取纬度信息
        double longitude = location.getLongitude();    //获取经度信息
        float radius = location.getRadius();    //获取定位精度，默认值为0.0f

        String coorType = location.getCoorType();
        //获取经纬度坐标类型，以LocationClientOption中设置过的坐标类型为准

        int errorCode = location.getLocType();
        //获取定位类型、定位错误返回码，具体信息可参照类参考中BDLocation类中的说明
        LogUtil.e("d*******", errorCode + "--" + latitude + "==" + latitude);
        TransGPS.Location l = new TransGPS.Location(location.getLatitude(), location.getLongitude());
        TransGPS.Location nowl = transGPS.bd_decrypt(l);
        nowl = transGPS.transformFromGCJToWGS(nowl);//方法是BD先转成GCJ再转成WGS
        for (LocationListener listener : locationListeners) {//或许用while ->set
            listener.onReceiveLocation(location, errorCode, nowl.getLat(), nowl.getLng());
        }
    }

    public void setListeners(List<LocationListener> locationListeners) {
        this.locationListeners = locationListeners;
    }
}
