package com.zt.inspection.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import com.esri.android.map.GraphicsLayer;
import com.esri.android.map.MapView;
import com.esri.android.map.ags.ArcGISDynamicMapServiceLayer;
import com.esri.core.geometry.Point;
import com.esri.core.geometry.Polyline;
import com.esri.core.map.Graphic;
import com.esri.core.symbol.PictureMarkerSymbol;
import com.esri.core.symbol.SimpleLineSymbol;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;
import com.zt.inspection.R;
import com.zt.inspection.Urls;
import com.zt.inspection.contract.PatrolRouteContract;
import com.zt.inspection.model.entity.response.PatrikRouteBean;
import com.zt.inspection.presenter.PatrolRoutePresenter;

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
    private MapView mMapView;
    private GraphicsLayer hiddenSegmentsLayer;
    private GraphicsLayer graphicsLayer;
    private String id;

    private List<PatrikRouteBean> datas;
    private final static String INTENT_KEY_ID = "INTENT_KEY_ID";

    int pointId = -1;
    Drawable bitmap;
    PictureMarkerSymbol pictureMarkerSymbol;

    private static final int PERIOD = 1 * 1000;
    private static final int DELAY = 100;
    private Timer mTimer;
    private TimerTask mTimerTask;

    @Override
    protected int getLayoutContentId() {
        return R.layout.ac_patrolroute;
    }

    @Override
    protected void initContentView() {
        setStatusBar(R.color.select_color);
        setTitle("轨迹回放", R.color.white);
        setToolBarBackgroundColor(R.color.select_color);

        mMapView = findViewById(R.id.mapview);
        tv_rf = findViewById(R.id.tv_rf);
        initMap();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        id = getIntent().getStringExtra(INTENT_KEY_ID);
        bitmap = ContextCompat.getDrawable(getContext(), R.mipmap.runman);
        pictureMarkerSymbol = new PictureMarkerSymbol(bitmap);

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
        ArcGISDynamicMapServiceLayer arcGISTiledMapServiceLayer = new ArcGISDynamicMapServiceLayer(Urls.mapUrl);
        mMapView.addLayer(arcGISTiledMapServiceLayer);
        hiddenSegmentsLayer = new GraphicsLayer();
        graphicsLayer = new GraphicsLayer();
        mMapView.addLayer(hiddenSegmentsLayer);
        mMapView.addLayer(graphicsLayer);
        mMapView.setMaxScale(10000);
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.pause();
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.unpause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.destroyDrawingCache();
        if (mTimerTask != null) {
            mTimerTask.cancel();
        }
        if (mTimer != null) {
            mTimer.cancel();
        }
    }

    @Override
    public void queryDataSuccess(List<PatrikRouteBean> datas) {
        dimiss();
        this.datas = datas;
        Polyline polyline = new Polyline();
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
        graphicsLayer.addGraphic(new Graphic(polyline, simpleLineSymbol));
        toPoint();
    }

    int index = 0;

    private void toPoint() {
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

        if (pointId != -1) {
            hiddenSegmentsLayer.removeGraphic(pointId);
            pointId = -1;
        }
        Point point = new Point(x,y);
        Graphic graphic = new Graphic(point, pictureMarkerSymbol);
        pointId = hiddenSegmentsLayer.addGraphic(graphic);
        mMapView.setExtent(point,120);
    }

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
