package com.zt.inspection.util.LBS;

import com.baidu.location.BDLocation;

import java.util.Date;

public interface LocationListener {
    void onReceiveLocation(BDLocation location,int errorCode,double latitude,double longitude);
}
