package com.zt.inspection.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

/*import com.esri.android.map.GraphicsLayer;
import com.esri.android.map.MapView;
import com.esri.android.map.ags.ArcGISDynamicMapServiceLayer;
import com.esri.core.geometry.Point;
import com.esri.core.geometry.Polyline;
import com.esri.core.map.Graphic;
import com.esri.core.symbol.PictureMarkerSymbol;
import com.esri.core.symbol.SimpleLineSymbol;*/
import com.baidu.mapapi.animation.Animation;
import com.baidu.mapapi.animation.Transformation;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.Overlay;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.PolylineOptions;
import com.baidu.mapapi.model.LatLng;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;
import com.zt.inspection.R;
import com.zt.inspection.Urls;
import com.zt.inspection.contract.PatrolRouteContract;
import com.zt.inspection.model.entity.response.PatrikRouteBean;
import com.zt.inspection.model.entity.response.PatrolRlistBean;
import com.zt.inspection.presenter.PatrolRoutePresenter;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import cn.faker.repaymodel.mvp.BaseMVPAcivity;
import cn.faker.repaymodel.util.ToastUtility;

/**
 * 轨迹回放
 */
public class PatrolRouteActivity extends BaseMVPAcivity<PatrolRouteContract.View, PatrolRoutePresenter> implements PatrolRouteContract.View {

    public static Intent toIntent(Context context, String id) {
        Intent intent = new Intent();
        intent.setClass(context, PatrolRouteActivity.class);
        intent.putExtra(INTENT_KEY_ID, id);
        return intent;
    }




    private TextView tv_rf;
/*    private MapView mMapView;
    private GraphicsLayer hiddenSegmentsLayer;
    private GraphicsLayer graphicsLayer;*/
//    PictureMarkerSymbol pictureMarkerSymbol;

    private String id;

    private List<PatrikRouteBean> datas;
    private final static String INTENT_KEY_ID = "INTENT_KEY_ID";

    int pointId = -1;
    Drawable bitmap;
    BitmapDescriptor bbitmap;
    private static final int PERIOD = 1 * 1000;
    private static final int DELAY = 100;
    private Timer mTimer;
    private TimerTask mTimerTask;


    private MapView bmapView;
    private BaiduMap mBaiduMap;

    @Override
    protected int getLayoutContentId() {
        return R.layout.ac_patrolroute;
    }

    @Override
    protected void initContentView() {
        setStatusBar(R.color.select_color);
        setTitle("轨迹回放", R.color.white);
        setToolBarBackgroundColor(R.color.select_color);

//        mMapView = findViewById(R.id.mapview);
        tv_rf = findViewById(R.id.tv_rf);

        bmapView = findViewById(R.id.bmapView);
        mBaiduMap = bmapView.getMap();
        initMap();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        id = getIntent().getStringExtra(INTENT_KEY_ID);
        bitmap = ContextCompat.getDrawable(getContext(), R.mipmap.runman);
        bbitmap = BitmapDescriptorFactory .fromResource(R.mipmap.runman);
//        pictureMarkerSymbol = new PictureMarkerSymbol(bitmap);

        showLoading();
        mPresenter.queryData(id);
    }

    @Override
    protected void initListener() {
        super.initListener();
        tv_rf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toPoint();
            }
        });
    }

    private void initMap() {
/*        ArcGISDynamicMapServiceLayer arcGISTiledMapServiceLayer = new ArcGISDynamicMapServiceLayer(Urls.mapUrl);
        mMapView.addLayer(arcGISTiledMapServiceLayer);
        hiddenSegmentsLayer = new GraphicsLayer();
        graphicsLayer = new GraphicsLayer();
        mMapView.addLayer(hiddenSegmentsLayer);
        mMapView.addLayer(graphicsLayer);
        mMapView.setMaxScale(10000);*/
        bmapView.showZoomControls(false);
        MapStatus.Builder builder = new MapStatus.Builder();
        builder.zoom(21);
        mBaiduMap.setMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
    }

    @Override
    public void onPause() {
        super.onPause();
//         mMapView.pause();
        bmapView.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
//         mMapView.unpause();
        bmapView.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        bmapView.onDestroy();
//        mMapView.destroyDrawingCache();
        if (mTimerTask != null) {
            mTimerTask.cancel();
        }
        if (mTimer != null) {
            mTimer.cancel();
        }
    }
    List<LatLng> points;
    @Override
    public void queryDataSuccess(List<PatrikRouteBean> datas) {
        dimiss();
        mBaiduMap.clear();
        this.datas = datas;
/*        Polyline polyline = new Polyline();
        for (int i = 0; i < datas.size(); i++) {
            PatrikRouteBean item = datas.get(i);
            if (i == 0) {
//                polyline.startPath(Double.valueOf(item.getX()), Double.valueOf(item.getY()));
                polyline.startPath(Double.valueOf(item.getY()), Double.valueOf(item.getX()));
            } else {
//                polyline.lineTo(Double.valueOf(item.getX()), Double.valueOf(item.getY()));
                polyline.lineTo(Double.valueOf(item.getY()), Double.valueOf(item.getX()));
            }
        }
        mMapView.setExtent(polyline);
        SimpleLineSymbol simpleLineSymbol = new SimpleLineSymbol(Color.BLUE, 3);//创建线要素对象
        graphicsLayer.addGraphic(new Graphic(polyline, simpleLineSymbol));*/

        points = new ArrayList<LatLng>();
        for (int i = 0; i < datas.size(); i++) {
            PatrikRouteBean item = datas.get(i);
            LatLng p1 = new LatLng(Double.valueOf(item.getY()), Double.valueOf(item.getX()));
            points.add(p1);
        }
        if (points.size()>1){
            //设置折线的属性
            OverlayOptions mOverlayOptions = new PolylineOptions()
                    .width(10)
                    .color(0xAAFF0000)
                    .points(points);
            Overlay mPolyline = mBaiduMap.addOverlay(mOverlayOptions);
        }


        if (points.size()>0){
            BitmapDescriptor bitmap = BitmapDescriptorFactory
                    .fromResource(R.mipmap.qd);
            OverlayOptions option = new MarkerOptions()
                    .position(points.get(0))
                    .icon(bitmap);
            mBaiduMap.addOverlay(option);

            BitmapDescriptor bitmape = BitmapDescriptorFactory
                    .fromResource(R.mipmap.zd);
            OverlayOptions optione = new MarkerOptions()
                    .position(points.get(points.size()-1))
                    .icon(bitmape);
            mBaiduMap.addOverlay(optione);

            MapStatus mMapStatus = new MapStatus.Builder()
                    .target(points.get(0))
                    .zoom(19)
                    .build();
            MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
            mBaiduMap.setMapStatus(mMapStatusUpdate);
        }
        toPoint();
    }
    private void toPoint() {
        //通过LatLng列表构造Transformation对象
        Transformation mTransforma = new Transformation(points.toArray(new LatLng[]{}));

//动画执行时间
        mTransforma.setDuration(5000);

//动画重复模式
        mTransforma.setRepeatMode(Animation.RepeatMode.RESTART);

//动画重复次数
        mTransforma.setRepeatCount(1);

//根据开发需要设置动画监听
        mTransforma.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart() {
            }

            @Override
            public void onAnimationEnd() {
            }

            @Override
            public void onAnimationCancel() {
            }

            @Override
            public void onAnimationRepeat() {

            }
        });

        MarkerOptions option = new MarkerOptions()
                .position(points.get(0))
                .icon(bbitmap);
        MapStatus mMapStatus = new MapStatus.Builder()
                .target(points.get(0))
                .build();
        MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
        mBaiduMap.setMapStatus(mMapStatusUpdate);
//设置动画
        Marker marker= (Marker) mBaiduMap.addOverlay(option);
        marker.setAnimation(mTransforma);
//开启动画
        marker.startAnimation();
    }
    int index = 0;
    Overlay overlay;
/*    private void toPoint() {
        if (index != 0) {
            index = 0;
            mTimerTask.cancel();
            mTimer.cancel();
        }
        mTimer = new Timer();
        mTimerTask = new TimerTask() {
            @Override
            public void run() {
                if (index >= datas.size()) {
                    index = 0;

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tv_rf.setVisibility(View.VISIBLE);
                        }
                    });
                    mTimerTask.cancel();
                    mTimer.cancel();
                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tv_rf.setVisibility(View.GONE);
                        }
                    });
                    PatrikRouteBean data = datas.get(index);
//                    toPoint(Double.valueOf(data.getY()), Double.valueOf(data.getX()));
                    toPoint(Double.valueOf(data.getX()), Double.valueOf(data.getY()));
                    ++index;
                }
            }
        };
        mTimer.schedule(mTimerTask, DELAY, PERIOD);
    }

    private void toPoint(double x, double y) {

*//*        if (pointId != -1) {
            hiddenSegmentsLayer.removeGraphic(pointId);
            pointId = -1;
        }
        Point point = new Point(x,y);
        Graphic graphic = new Graphic(point, pictureMarkerSymbol);
        pointId = hiddenSegmentsLayer.addGraphic(graphic);
        mMapView.setExtent(point,120);*//*
        if (overlay!=null){
            overlay.remove();
            overlay = null;
        }
        LatLng point = new LatLng(y,x);
        OverlayOptions option = new MarkerOptions()
                .position(point)
                .icon(bbitmap);
         overlay=  mBaiduMap.addOverlay(option);

        MapStatus mMapStatus = new MapStatus.Builder()
                .target(point)
                .build();
        MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
        mBaiduMap.setMapStatus(mMapStatusUpdate);
    }*/

    @Override
    public void queryDataFinsh(String message) {
        dimiss();
        showDialog(message, new QMUIDialogAction.ActionListener() {
            @Override
            public void onClick(QMUIDialog dialog, int index) {
                dialog.dismiss();
                finish();
            }
        });
    }
}
