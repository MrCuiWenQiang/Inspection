package com.zt.inspection.util.LBS;

import com.baidu.location.BDLocation;

public interface LocationListener {
    void onReceiveLocation(BDLocation location,int errorCode,double latitude,double longitude);
}
