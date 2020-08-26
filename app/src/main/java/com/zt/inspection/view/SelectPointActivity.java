package com.zt.inspection.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.model.LatLng;
import com.zt.inspection.R;
import com.zt.inspection.contract.SelectPointContract;
import com.zt.inspection.presenter.SelectPointPresenter;

import cn.faker.repaymodel.mvp.BaseMVPAcivity;

public class SelectPointActivity extends BaseMVPAcivity<SelectPointContract.View, SelectPointPresenter>
        implements SelectPointContract.View,View.OnClickListener {
    private BaiduMap mBaiduMap;
    private MapView bmapView;
    private TextView tvStatus;
    private Button btAffirm;

    private static final String INTENT_KEY_LAT = "INTENT_KEY_LAT";
    private static final String INTENT_KEY_LON = "INTENT_KEY_LON";
    public static final String INTENT_RESULT_KEY_ADDRESS = "INTENT_RESULT_KEY_ADDRESS";

    public static Intent toIntent(Context context, double lat, double lon) {
        Intent intent = new Intent();
        intent.setClass(context, SelectPointActivity.class);
        intent.putExtra(INTENT_KEY_LAT, lat);
        intent.putExtra(INTENT_KEY_LON, lon);
        return intent;
    }
    @Override
    protected int getLayoutContentId() {
        return R.layout.ac_selectpoint;
    }

    @Override
    protected void initContentView() {
        setStatusBar(R.color.select_color);
        setTitle("地图选点", R.color.white);
        setToolBarBackgroundColor(R.color.select_color);

        bmapView = findViewById(R.id.bmapView);
        tvStatus = findViewById(R.id.tv_status);
        btAffirm = findViewById(R.id.bt_affirm);
        mBaiduMap = bmapView.getMap();
        initMap();
    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    private void initMap() {
        bmapView.showZoomControls(false);
        double x = getIntent().getDoubleExtra(INTENT_KEY_LAT,-1);
        double y = getIntent().getDoubleExtra(INTENT_KEY_LON,-1);

        LatLng point ;
        if (x>0&&y>0){
            point = new LatLng(x, y);
        }else {
            point = new LatLng(36.658576, 117.12647);
        }
        MapStatus mMapStatus = new MapStatus.Builder()
                .target(point)
                .zoom(19)
                .build();
        MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
        mBaiduMap.setMapStatus(mMapStatusUpdate);
    }

    boolean isMoven = false;

    @Override
    protected void initListener() {
        super.initListener();
        mBaiduMap.setOnMapTouchListener(new BaiduMap.OnMapTouchListener() {
            @Override
            public void onTouch(MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_MOVE: {
                        isMoven = true;
                        break;
                    }
                    case MotionEvent.ACTION_UP: {
                        if (isMoven){
                            toGetCentre();
                        }
                        isMoven = false;
                        break;
                    }
                }
            }
        });
    }

    /**
     * 当用户触摸后 获取地图中心位置
     */
    private void toGetCentre() {
        LatLng latLng = mBaiduMap.getMapStatus().target;
        mPresenter.queryAddress(latLng.latitude,latLng.longitude);
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
    }

    @Override
    public void getAddress_Success(String formatted_address) {
        btAffirm.setOnClickListener(this);
        tvStatus.setText(formatted_address);
    }

    @Override
    public void getAddress_Fail(String msg) {
        btAffirm.setOnClickListener(null);
        tvStatus.setText(msg);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_affirm:{
                String address = getValue(tvStatus);
                if (TextUtils.isEmpty(address)||address.equals("拖动地图选择位置")){
                    finish();
                }else {
                    Intent intent = new Intent();
                    intent.putExtra(INTENT_RESULT_KEY_ADDRESS,address);
                    setResult(200,intent);
                    finish();
                }

            }
        }
    }
}
