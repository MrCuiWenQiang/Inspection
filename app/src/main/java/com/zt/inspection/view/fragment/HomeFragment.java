package com.zt.inspection.view.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

/*import com.esri.android.map.GraphicsLayer;
import com.esri.android.map.MapView;
import com.esri.android.map.ags.ArcGISDynamicMapServiceLayer;
import com.esri.android.map.event.OnSingleTapListener;
import com.esri.core.geometry.Polyline;
import com.esri.core.map.Graphic;
import com.esri.core.symbol.SimpleLineSymbol;*/
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Overlay;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.PolylineOptions;
import com.baidu.mapapi.map.TextureMapView;
import com.baidu.mapapi.model.LatLng;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zt.inspection.R;
import com.zt.inspection.Urls;
import com.zt.inspection.contract.HomeFragmentContract;
import com.zt.inspection.model.entity.response.CaseInfoBean;
import com.zt.inspection.model.entity.response.PatrikRouteBean;
import com.zt.inspection.model.entity.response.PatrolRlistBean;
import com.zt.inspection.model.entity.response.PatrolSectionListBean;
import com.zt.inspection.model.entity.view.HomeWorkBean;
import com.zt.inspection.presenter.HomeFragmentPresenter;
import com.zt.inspection.view.NoticeActivity;
import com.zt.inspection.view.PatrolSectionListActivity;
import com.zt.inspection.view.WorkActivity;
import com.zt.inspection.view.WorkInfoActivity;
import com.zt.inspection.view.WorkStatusActivity;
import com.zt.inspection.view.adapter.HomeWorkAdapter;
import com.zt.inspection.view.adapter.WorksAdapter;

import java.util.ArrayList;
import java.util.List;

import cn.faker.repaymodel.mvp.BaseMVPFragment;
import cn.faker.repaymodel.widget.view.BaseRecycleView;
import cn.faker.repaymodel.widget.view.SpaceGridItemDecoration;

/**
 * 主要fragment
 */
public class HomeFragment extends BaseMVPFragment<HomeFragmentContract.View, HomeFragmentPresenter> implements HomeFragmentContract.View, View.OnClickListener {

    public static HomeFragment newInstance() {
        Bundle args = new Bundle();
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

//    private MapView mapview;
//    private GraphicsLayer hiddenSegmentsLayer;

    private TextureMapView bmapView;
    private BaiduMap mBaiduMap;

    private LinearLayout ll_notice;
    private LinearLayout ll_work;
    private RecyclerView rv_works;
    private RecyclerView rv_tabs;
    private HomeWorkAdapter adapter;
    private ImageView im_top;
    private RefreshLayout mRefreshLayout;
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_notice: {
                toAcitvity(NoticeActivity.class);
                break;
            }
            case R.id.ll_work: {
                toAcitvity(WorkActivity.class);
                break;
            }
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.fg_home;
    }

    @Override
    public void initview(View v) {
        rv_tabs = findViewById(R.id.rv_tabs);
        im_top = findViewById(R.id.im_top);
//        mapview = findViewById(R.id.mapview);
        bmapView = findViewById(R.id.bmapView);
        mBaiduMap = bmapView.getMap();
        ll_notice = findViewById(R.id.ll_notice);
        ll_work = findViewById(R.id.ll_work);
        rv_works = findViewById(R.id.rv_works);
        rv_works.setLayoutManager(new GridLayoutManager(getContext(), 2));
        adapter = new HomeWorkAdapter();
        rv_works.setAdapter(adapter);
        rv_works.addItemDecoration(new SpaceGridItemDecoration(4, 2));//靠间隔背景色做分割线也算另外一个思路
        rv_tabs.setLayoutManager(new LinearLayoutManager(getContext()));

        mRefreshLayout = findViewById(R.id.refreshLayout);
        mRefreshLayout.setEnableRefresh(true);//启用刷新
        mRefreshLayout.setEnableLoadmore(false);
    }

    @Override
    protected void initListener() {
        super.initListener();
        ll_notice.setOnClickListener(this);
        ll_work.setOnClickListener(this);
        adapter.setOnItemClickListener(new BaseRecycleView.OnItemClickListener<HomeWorkBean>() {
            @Override
            public void onItemClick(View view, HomeWorkBean data, int position) {
                startActivity(WorkStatusActivity.newInstance(getContext(), data.getStatus(), data.getTitle()));
            }
        });
/*        mapview.setOnSingleTapListener(new OnSingleTapListener() {
            @Override
            public void onSingleTap(float v, float v1) {
                toAcitvity(PatrolSectionListActivity.class);

            }
        });*/
        mBaiduMap.setOnMapClickListener(new BaiduMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                toAcitvity(PatrolSectionListActivity.class);
            }

            @Override
            public void onMapPoiClick(MapPoi mapPoi) {

            }
        });

        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mPresenter.loadHomeDatas();
            }
        });
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        initMap();
        mPresenter.loadShowDatas();
    }

/*    private void initMap() {
        ArcGISDynamicMapServiceLayer arcGISTiledMapServiceLayer = new ArcGISDynamicMapServiceLayer(Urls.mapUrl);
        mapview.addLayer(arcGISTiledMapServiceLayer);
        hiddenSegmentsLayer = new GraphicsLayer();
        mapview.addLayer(hiddenSegmentsLayer);
        mapview.setMaxScale(10000);
    }*/
    private void initMap() {
        LatLng GEO_BEIJING = new LatLng(36.657828,117.115476);
        MapStatusUpdate status1 = MapStatusUpdateFactory.newLatLng(GEO_BEIJING);
        mBaiduMap.setMapStatus(status1);
    }

    /**
     * fragment显示或者隐藏时调用的方法
     */


    @Override
    public void onPause() {
        super.onPause();
//        mapview.pause();
        bmapView.onPause();
    }
    @Override
    public void onResume() {
        super.onResume();
//        mapview.unpause();
        bmapView.onResume();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
//        mapview.destroyDrawingCache();
        bmapView.onDestroy();
    }

    @Override
    public void showWorks(List<HomeWorkBean> datas) {
        adapter.setDatas(datas);
    }

    @Override
    public void showTab(int c) {
        im_top.setBackgroundResource(c);
    }

    WorksAdapter wadapter ;

    @Override
    public void loadWorkDataSuccess(List<CaseInfoBean> datas) {
        dimiss();
        rv_tabs.setVisibility(View.VISIBLE);
        if (wadapter==null){
            wadapter = new WorksAdapter();
            rv_tabs.setAdapter(wadapter);
            wadapter.setOnItemClickListener(new BaseRecycleView.OnItemClickListener<CaseInfoBean>() {
                @Override
                public void onItemClick(View view, CaseInfoBean data, int position) {
                    Intent intent = WorkInfoActivity.newInstance(getContext(),data.getCID(),data.getCSTATE(),data.getCASENUMBER(),data);
                    startActivity(intent);
                }
            });
        }
        wadapter.setDatas(datas);
    }

    @Override
    public void loadWorkDataFail(String message) {

    }

/*    @Override
    public void loadLoncal(List<PatrolRlistBean> datas) {
        mapview.setVisibility(View.VISIBLE);
        hiddenSegmentsLayer.removeAll();
        if (datas!=null&&datas.size()>0){
            Polyline polyline = new Polyline();
            for (int i = 0; i < datas.size(); i++) {
                PatrolRlistBean item = datas.get(i);
                if (i == 0) {
                    polyline.startPath(Double.valueOf(item.getX()), Double.valueOf(item.getY()));
                } else {
                    polyline.lineTo(Double.valueOf(item.getX()), Double.valueOf(item.getY()));
                }
            }
            mapview.setExtent(polyline);
            SimpleLineSymbol simpleLineSymbol = new SimpleLineSymbol(Color.BLUE, 3);//创建线要素对象
            hiddenSegmentsLayer.addGraphic(new Graphic(polyline, simpleLineSymbol));
        }
    }*/

    @Override
    public void loadLoncal(List<PatrolSectionListBean> fdatas) {
        dimiss();
        bmapView.setVisibility(View.VISIBLE);
        mBaiduMap.clear();
        int si = 0;
        for (PatrolSectionListBean bean:fdatas) {
            List<PatrolRlistBean> datas = bean.getRList();
            if (datas!=null&&datas.size()>1){
                List<LatLng> points = new ArrayList<LatLng>();
                for (int i = 0; i < datas.size(); i++) {
                    PatrolRlistBean item = datas.get(i);
                    LatLng p1 = new LatLng(Double.valueOf(item.getY()), Double.valueOf(item.getX()));
                    points.add(p1);
                }
                //设置折线的属性
                OverlayOptions mOverlayOptions = new PolylineOptions()
                        .width(10)
                        .color(0xAAFF0000)
                        .points(points);
                Overlay mPolyline = mBaiduMap.addOverlay(mOverlayOptions);
                if (si==0){
                    si+=1;
                    MapStatus mMapStatus = new MapStatus.Builder()
                            .target(points.get(0))
                            .zoom(19)
                            .build();
                    MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
                    mBaiduMap.setMapStatus(mMapStatusUpdate);
                }

            }
        }
    }
    @Override
    protected void dimiss() {
        super.dimiss();
        mRefreshLayout.finishRefresh();//完成刷新
        mRefreshLayout.finishLoadmore();//完成刷新
    }

}
