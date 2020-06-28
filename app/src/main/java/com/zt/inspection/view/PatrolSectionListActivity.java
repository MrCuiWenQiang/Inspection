package com.zt.inspection.view;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.esri.android.map.GraphicsLayer;
import com.esri.android.map.MapView;
import com.esri.android.map.ags.ArcGISDynamicMapServiceLayer;
import com.esri.core.geometry.Point;
import com.esri.core.geometry.Polyline;
import com.esri.core.map.Graphic;
import com.esri.core.symbol.PictureMarkerSymbol;
import com.esri.core.symbol.SimpleLineSymbol;
import com.zt.inspection.R;
import com.zt.inspection.Urls;
import com.zt.inspection.contract.PatrolSectionListContract;
import com.zt.inspection.model.entity.response.PatrolRlistBean;
import com.zt.inspection.model.entity.response.PatrolSectionListBean;
import com.zt.inspection.presenter.PatrolSectionListPresenter;
import com.zt.inspection.view.adapter.PatrolSectionListAdapter;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import cn.faker.repaymodel.mvp.BaseMVPAcivity;
import cn.faker.repaymodel.util.ToastUtility;
import cn.faker.repaymodel.widget.view.BaseRecycleView;

public class PatrolSectionListActivity extends BaseMVPAcivity<PatrolSectionListContract.View, PatrolSectionListPresenter> implements PatrolSectionListContract.View {

    private RecyclerView rv_data;
    private MapView mapview;
    private GraphicsLayer hiddenSegmentsLayer;
    private PatrolSectionListAdapter adapter;
    private static final int PERIOD = 1 * 1000;
    private static final int DELAY = 100;

    @Override
    protected int getLayoutContentId() {
        return R.layout.ac_patrolsectionlist;
    }

    @Override
    protected void initContentView() {
        setStatusBar(R.color.select_color);
        setTitle("当日巡查路线", R.color.white);
        setToolBarBackgroundColor(R.color.select_color);

        mapview = findViewById(R.id.mapview);
        rv_data = findViewById(R.id.rv_data);
        rv_data.setLayoutManager(new LinearLayoutManager(getContext()));
        initMap();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        showLoading();
        adapter = new PatrolSectionListAdapter();
        rv_data.setAdapter(adapter);
        bitmap = ContextCompat.getDrawable(getContext(), R.mipmap.runman);
        pictureMarkerSymbol = new PictureMarkerSymbol(bitmap);
        mPresenter.loadLoncal();
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

    public void toDraw(PatrolSectionListBean data) {
        hiddenSegmentsLayer.removeAll();
        this.data = data;
        Polyline polyline = new Polyline();
        List<PatrolRlistBean> datas = data.getRList();
        for (int i = 0; i < datas.size(); i++) {
            PatrolRlistBean item = datas.get(i);
            if (i == 0) {
//                polyline.startPath(Double.valueOf(item.getX()), Double.valueOf(item.getY()));
                polyline.startPath(Double.valueOf(item.getY()), Double.valueOf(item.getX()));
            } else {
//                polyline.lineTo(Double.valueOf(item.getX()), Double.valueOf(item.getY()));
                polyline.lineTo(Double.valueOf(item.getY()), Double.valueOf(item.getX()));
            }
        }
        mapview.setExtent(polyline);
        SimpleLineSymbol simpleLineSymbol = new SimpleLineSymbol(Color.BLUE, 3);//创建线要素对象
        hiddenSegmentsLayer.addGraphic(new Graphic(polyline, simpleLineSymbol));
        toPoint();
    }

    int index = 0;
    int pointId = -1;
    Drawable bitmap;
    PictureMarkerSymbol pictureMarkerSymbol;
    private Timer mTimer;
    private TimerTask mTimerTask;

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

    private void toPoint(double x, double y) {

        if (pointId != -1) {
            hiddenSegmentsLayer.removeGraphic(pointId);
            pointId = -1;
        }
        Point point = new Point(x, y);
        Graphic graphic = new Graphic(point, pictureMarkerSymbol);
        pointId = hiddenSegmentsLayer.addGraphic(graphic);
        mapview.setExtent(point, 120);
    }

    private void initMap() {
        ArcGISDynamicMapServiceLayer arcGISTiledMapServiceLayer = new ArcGISDynamicMapServiceLayer(Urls.mapUrl);
        mapview.addLayer(arcGISTiledMapServiceLayer);
        hiddenSegmentsLayer = new GraphicsLayer();
        mapview.addLayer(hiddenSegmentsLayer);
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
