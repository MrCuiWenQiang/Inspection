package com.zt.inspection.util.LBS;


import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;

import java.util.List;

import cn.faker.repaymodel.util.LogUtil;

public class BDLocationListener extends BDAbstractLocationListener {

    private List<LocationListener> locationListeners;
    double latitude = -1;
    double longitude = -1;
    BDLocation location = null;
    @Override
    public void onReceiveLocation(BDLocation location) {
        this.location = location;
         latitude = location.getLatitude();    //获取纬度信息
         longitude = location.getLongitude();    //获取经度信息
        float radius = location.getRadius();    //获取定位精度，默认值为0.0f

        String coorType = location.getCoorType();

        int errorCode = location.getLocType();
        for (LocationListener listener : locationListeners) {//或许用while ->set
            listener.onReceiveLocation(location, errorCode,location.getLatitude(), location.getLongitude());
        }
    }

    public void setListeners(List<LocationListener> locationListeners) {
        this.locationListeners = locationListeners;
    }


}
