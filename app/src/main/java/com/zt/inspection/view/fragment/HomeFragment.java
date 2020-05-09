package com.zt.inspection.view.fragment;

import android.os.Bundle;
import android.view.View;

import com.esri.android.map.GraphicsLayer;
import com.esri.android.map.MapView;
import com.esri.android.map.ags.ArcGISDynamicMapServiceLayer;
import com.zt.inspection.R;
import com.zt.inspection.Urls;
import com.zt.inspection.contract.HomeFragmentContract;
import com.zt.inspection.presenter.HomeFragmentPresenter;

import cn.faker.repaymodel.mvp.BaseMVPFragment;

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

    private MapView mapview;
    private GraphicsLayer hiddenSegmentsLayer;

    @Override
    public void onClick(View v) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fg_home;
    }

    @Override
    public void initview(View v) {
        mapview = findViewById(R.id.mapview);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        initMap();
    }

    private void initMap() {
        ArcGISDynamicMapServiceLayer arcGISTiledMapServiceLayer = new ArcGISDynamicMapServiceLayer(Urls.mapUrl);
        mapview.addLayer(arcGISTiledMapServiceLayer);
        hiddenSegmentsLayer = new GraphicsLayer();
        mapview.addLayer(hiddenSegmentsLayer);
    }

    @Override
    public void onPause() {
        super.onPause();
        mapview.pause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapview.destroyDrawingCache();
    }
}
