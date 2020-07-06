package com.zt.inspection.view;

import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;


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
import com.zt.inspection.R;
import com.zt.inspection.Urls;
import com.zt.inspection.contract.PatrolSectionListContract;
import com.zt.inspection.model.entity.response.PatrikRouteBean;
import com.zt.inspection.model.entity.response.PatrolRlistBean;
import com.zt.inspection.model.entity.response.PatrolSectionListBean;
import com.zt.inspection.presenter.PatrolSectionListPresenter;
import com.zt.inspection.view.adapter.PatrolSectionListAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import cn.faker.repaymodel.mvp.BaseMVPAcivity;
import cn.faker.repaymodel.util.ToastUtility;
import cn.faker.repaymodel.widget.view.BaseRecycleView;

public class PatrolSectionListActivity extends BaseMVPAcivity<PatrolSectionListContract.View, PatrolSectionListPresenter> implements PatrolSectionListContract.View {

    private RecyclerView rv_data;
    /*   private MapView mapview;
       private GraphicsLayer hiddenSegmentsLayer;*/
    private PatrolSectionListAdapter adapter;
    private static final int PERIOD = 1 * 1000;
    private static final int DELAY = 100;

    private MapView bmapView;
    private BaiduMap mBaiduMap;
    BitmapDescriptor bbitmap;

    @Override
    protected int getLayoutContentId() {
        return R.layout.ac_patrolsectionlist;
    }

    @Override
    protected void initContentView() {
        setStatusBar(R.color.select_color);
        setTitle("当日巡检路线", R.color.white);
        setToolBarBackgroundColor(R.color.select_color);

//        mapview = findViewById(R.id.mapview);
        rv_data = findViewById(R.id.rv_data);
        rv_data.setLayoutManager(new LinearLayoutManager(getContext()));
        bmapView = findViewById(R.id.bmapView);
        mBaiduMap = bmapView.getMap();
        initMap();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        showLoading();
        adapter = new PatrolSectionListAdapter();
        rv_data.setAdapter(adapter);
 /*       bitmap = ContextCompat.getDrawable(getContext(), R.mipmap.runman);
        pictureMarkerSymbol = new PictureMarkerSymbol(bitmap);*/
        mPresenter.loadLoncal();

        bbitmap = BitmapDescriptorFactory.fromResource(R.mipmap.runman);
    }

    @Override
    protected void initListener() {
        super.initListener();
        adapter.setOnItemClickListener(new BaseRecycleView.OnItemClickListener<PatrolSectionListBean>() {
            @Override
            public void onItemClick(View view, PatrolSectionListBean data, int position) {
                toDraw(data);
            }
        });
    }

    PatrolSectionListBean data;
    List<PatrolRlistBean> datas;
    List<LatLng> points;
    public void toDraw(PatrolSectionListBean data) {
        mBaiduMap.clear();
        this.data = data;
        datas = data.getRList();

       /* hiddenSegmentsLayer.removeAll();
        Polyline polyline = new Polyline();
        for (int i = 0; i < datas.size(); i++) {
            PatrolRlistBean item = datas.get(i);
            if (i == 0) {
                polyline.startPath(Double.valueOf(item.getY()), Double.valueOf(item.getX()));
            } else {
                polyline.lineTo(Double.valueOf(item.getY()), Double.valueOf(item.getX()));
            }
        }
        mapview.setExtent(polyline);
        SimpleLineSymbol simpleLineSymbol = new SimpleLineSymbol(Color.BLUE, 3);//创建线要素对象
        hiddenSegmentsLayer.addGraphic(new Graphic(polyline, simpleLineSymbol));
   */
        points = new ArrayList<LatLng>();
        for (int i = 0; i < datas.size(); i++) {
            PatrolRlistBean item = datas.get(i);
            LatLng p1 = new LatLng(Double.valueOf(item.getY()), Double.valueOf(item.getX()));
            points.add(p1);
        }
        if (points.size() > 0) {
            BitmapDescriptor bitmap = BitmapDescriptorFactory
                    .fromResource(R.mipmap.qd);
            OverlayOptions option = new MarkerOptions()
                    .position(points.get(0))
                    .icon(bitmap);
            mBaiduMap.addOverlay(option);

            BitmapDescriptor bitmape = BitmapDescriptorFactory
                    .fromResource(R.mipmap.zd);
            OverlayOptions optione = new MarkerOptions()
                    .position(points.get(points.size() - 1))
                    .icon(bitmape);
            mBaiduMap.addOverlay(optione);
        }
        if (points.size()>1){
            //设置折线的属性
            OverlayOptions mOverlayOptions = new PolylineOptions()
                    .width(10)
                    .color(0xAAFF0000)
                    .points(points);
            Overlay mPolyline = mBaiduMap.addOverlay(mOverlayOptions);
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
        mTransforma.setRepeatCount(0);

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
    int pointId = -1;
    /*    Drawable bitmap;
        PictureMarkerSymbol pictureMarkerSymbol;*/
    private Timer mTimer;
    private TimerTask mTimerTask;

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


                    mTimerTask.cancel();
                    mTimer.cancel();
                } else {

                    PatrolRlistBean data = datas.get(index);
//                    toPoint(Double.valueOf(data.getY()), Double.valueOf(data.getX()));
                    toPoint(Double.valueOf(data.getX()), Double.valueOf(data.getY()));
                    ++index;
                }
            }
        };
        mTimer.schedule(mTimerTask, DELAY, PERIOD);
    }
    Overlay overlay;
    private void toPoint(double x, double y) {

*//*        if (pointId != -1) {
            hiddenSegmentsLayer.removeGraphic(pointId);
            pointId = -1;
        }
        Point point = new Point(x, y);
        Graphic graphic = new Graphic(point, pictureMarkerSymbol);
        pointId = hiddenSegmentsLayer.addGraphic(graphic);
        mapview.setExtent(point);*//*

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


    private void initMap() {
/*        ArcGISDynamicMapServiceLayer arcGISTiledMapServiceLayer = new ArcGISDynamicMapServiceLayer(Urls.mapUrl);
        mapview.addLayer(arcGISTiledMapServiceLayer);
        hiddenSegmentsLayer = new GraphicsLayer();
        mapview.addLayer(hiddenSegmentsLayer);
        mapview.setMaxScale(10000);*/

        bmapView.showZoomControls(false);
        MapStatus.Builder builder = new MapStatus.Builder();
        builder.zoom(21);
        LatLng GEO_BEIJING = new LatLng(36.657828, 117.115476);
        MapStatusUpdate status1 = MapStatusUpdateFactory.newLatLng(GEO_BEIJING);
        mBaiduMap.setMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
        mBaiduMap.setMapStatus(status1);
    }

    @Override
    public void loadLoncalSuccess(List<PatrolSectionListBean> datas) {
        dimiss();
        adapter.setDatas(datas);
    }

    @Override
    public void loadLoncalFail(String msg) {
        dimiss();
        ToastUtility.showToast(msg);
    }
}
