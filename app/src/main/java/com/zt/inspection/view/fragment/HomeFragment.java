package com.zt.inspection.view.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.esri.android.map.GraphicsLayer;
import com.esri.android.map.MapView;
import com.esri.android.map.ags.ArcGISDynamicMapServiceLayer;
import com.zt.inspection.R;
import com.zt.inspection.Urls;
import com.zt.inspection.contract.HomeFragmentContract;
import com.zt.inspection.model.entity.view.HomeWorkBean;
import com.zt.inspection.presenter.HomeFragmentPresenter;
import com.zt.inspection.view.NoticeActivity;
import com.zt.inspection.view.WorkActivity;
import com.zt.inspection.view.WorkStatusActivity;
import com.zt.inspection.view.adapter.HomeWorkAdapter;

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

    private MapView mapview;
    private GraphicsLayer hiddenSegmentsLayer;

    private LinearLayout ll_notice;
    private LinearLayout ll_work;
    private RecyclerView rv_works;
    private HomeWorkAdapter adapter;
    private ImageView im_top;

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
        im_top = findViewById(R.id.im_top);
        mapview = findViewById(R.id.mapview);
        ll_notice = findViewById(R.id.ll_notice);
        ll_work = findViewById(R.id.ll_work);
        rv_works = findViewById(R.id.rv_works);
        rv_works.setLayoutManager(new GridLayoutManager(getContext(), 2));
        adapter = new HomeWorkAdapter();
        rv_works.setAdapter(adapter);
        rv_works.addItemDecoration(new SpaceGridItemDecoration(4, 2));//靠间隔背景色做分割线也算另外一个思路
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
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        initMap();
        mPresenter.loadShowDatas();
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
    public void onResume() {
        super.onResume();
        mapview.unpause();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        mapview.destroyDrawingCache();
    }

    @Override
    public void showWorks(List<HomeWorkBean> datas) {
        adapter.setDatas(datas);
    }

    @Override
    public void showTab(int c) {
        im_top.setBackgroundResource(c);
    }
}
