package com.zt.inspection.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.baidu.location.BDLocation;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.CircleOptions;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.Overlay;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.Stroke;
import com.baidu.mapapi.model.LatLng;
import com.zt.inspection.R;
import com.zt.inspection.model.entity.response.CaseInfoBean;
import com.zt.inspection.model.entity.response.ClockInBean;
import com.zt.inspection.util.LBS.LBSUtil;

import cn.faker.repaymodel.activity.BaseToolBarActivity;

/**
 * 既作为案件地图  也作为考勤打卡范围显示
 */
public class MapActivity extends BaseToolBarActivity implements
         com.zt.inspection.util.LBS.LocationListener{
    private static final String INTENT_KEY_CASEINFOBEAN = "INTENT_KEY_CASEINFOBEAN";
    private static final String INTENT_KEY_CLOCKINBEAN = "INTENT_KEY_CLOCKINBEAN";

    //案件
    public static Intent newInstance(Context context, CaseInfoBean caseinfo) {
        Intent intent = new Intent(context, MapActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(INTENT_KEY_CASEINFOBEAN, caseinfo);
        intent.putExtras(bundle);
        return intent;
    }

    //考勤
    public static Intent newInstance(Context context, ClockInBean clockInBean) {
        Intent intent = new Intent(context, MapActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(INTENT_KEY_CLOCKINBEAN, clockInBean);
        intent.putExtras(bundle);
        return intent;
    }

    private MapView bmapView;
    private BaiduMap mBaiduMap;

    private CaseInfoBean caseinfo;
    private ClockInBean clockInBean;

    @Override
    protected int getLayoutContentId() {
        return R.layout.ac_map;
    }

    @Override
    protected void initContentView() {
        setStatusBar(R.color.select_color);
        setToolBarBackgroundColor(R.color.select_color);


        bmapView = findViewById(R.id.bmapView);
        mBaiduMap = bmapView.getMap();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        if (getIntent().getSerializableExtra(INTENT_KEY_CASEINFOBEAN) != null) {
            setTitle("案件地图", R.color.white);
            caseinfo = (CaseInfoBean) getIntent().getSerializableExtra(INTENT_KEY_CASEINFOBEAN);
            initCaseinfoMap();
        } else {
            setTitle("考勤范围", R.color.white);
            clockInBean = (ClockInBean) getIntent().getSerializableExtra(INTENT_KEY_CLOCKINBEAN);
            initClockInBeanMap();
        }

    }

    private void initCaseinfoMap() {
        LatLng point;
        if (Double.valueOf(caseinfo.getY())<=0||Double.valueOf(caseinfo.getX())<=0){
            point = new LatLng(36.658576,117.12647);
        }else {
            point = new LatLng(Double.valueOf(caseinfo.getY()), Double.valueOf(caseinfo.getX()));
            BitmapDescriptor bitmap = BitmapDescriptorFactory
                    .fromResource(R.mipmap.anfapoint);
            OverlayOptions option = new MarkerOptions()
                    .position(point)
                    .icon(bitmap);
            mBaiduMap.addOverlay(option);
        }
        MapStatus mMapStatus = new MapStatus.Builder()
                .target(point)
                .zoom(19)
                .build();
        MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
        mBaiduMap.setMapStatus(mMapStatusUpdate);

        mBaiduMap.setOnMapClickListener(new BaiduMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
//                toAcitvity();
            }

            @Override
            public void onMapPoiClick(MapPoi mapPoi) {

            }
        });
    }


    private void initClockInBeanMap() {
        mBaiduMap.setMyLocationEnabled(true);
        LBSUtil.addListener(this);
        LatLng point = new LatLng(Double.valueOf(clockInBean.getY()), Double.valueOf(clockInBean.getX()));
//构造CircleOptions对象
        CircleOptions mCircleOptions = new CircleOptions().center(point)
                .radius(Integer.valueOf(clockInBean.getRADIUS()))
                .fillColor(Color.parseColor("#c1b1d6f3")) //填充颜色
                .stroke(new Stroke(5, Color.parseColor("#c1b1d6f3"))); //边框宽和边框颜色

//在地图上显示圆
        Overlay mCircle = mBaiduMap.addOverlay(mCircleOptions);


        BitmapDescriptor bitmap = BitmapDescriptorFactory
                .fromResource(R.mipmap.localsi);
        OverlayOptions option = new MarkerOptions()
                .position(point)
                .icon(bitmap);
        mBaiduMap.addOverlay(option);

    }
    @Override
    public void onPause() {
        super.onPause();
        bmapView.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        bmapView.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        bmapView.onDestroy();
        LBSUtil.removeListener(this);
    }
    boolean isOne = false;
    @Override
    public void onReceiveLocation(BDLocation location, int errorCode, double latitude, double longitude) {
        //mapView 销毁后不在处理新接收的位置
        if (location == null || bmapView == null) {
            return;
        }
        MyLocationData locData = new MyLocationData.Builder()
                .accuracy(location.getRadius())
                // 此处设置开发者获取到的方向信息，顺时针0-360
                .direction(location.getDirection()).latitude(location.getLatitude())
                .longitude(location.getLongitude()).build();
        mBaiduMap.setMyLocationData(locData);
        double lat = location.getLatitude();//纬度
        double lon = location.getLongitude();//经度
        if (!isOne ) {
            MapStatus mMapStatus = new MapStatus.Builder()
                    .target(new LatLng(lat, lon))
                    .zoom(17)
                    .build();
            MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
            mBaiduMap.setMapStatus(mMapStatusUpdate);
            isOne = true;
        }
    }
}
