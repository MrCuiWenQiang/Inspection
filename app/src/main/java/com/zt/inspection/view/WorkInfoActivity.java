package com.zt.inspection.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/*import com.esri.android.map.GraphicsLayer;
import com.esri.android.map.MapView;
import com.esri.android.map.ags.ArcGISDynamicMapServiceLayer;
import com.esri.core.geometry.Point;
import com.esri.core.map.Graphic;
import com.esri.core.symbol.PictureMarkerSymbol;*/
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.zt.inspection.R;
import com.zt.inspection.Urls;
import com.zt.inspection.contract.WorkInfoContract;
import com.zt.inspection.model.entity.response.CaseInfoBean;
import com.zt.inspection.model.entity.response.WordInfoBean;
import com.zt.inspection.presenter.WorkInfoPresenter;
import com.zt.inspection.util.RoleIdUtil;
import com.zt.inspection.util.StatusUtil;
import com.zt.inspection.view.adapter.ImageAdapter;
import com.zt.inspection.view.adapter.VideoImageAdapter;
import com.zt.inspection.view.adapter.WorkInfoAdapter;
import com.zt.inspection.view.dialog.DownLoadViewDialog;
import com.zt.inspection.view.dialog.PhotoDialog;

import java.util.List;

import cn.faker.repaymodel.mvp.BaseMVPAcivity;
import cn.faker.repaymodel.util.ToastUtility;

/**
 * 案件记录
 */
public class WorkInfoActivity extends BaseMVPAcivity<WorkInfoContract.View, WorkInfoPresenter>
        implements WorkInfoContract.View {

    private static final String INTENT_KEY_WORK = "INTENT_KEY_WORK";
    private static final String INTENT_KEY_CSTATE = "INTENT_KEY_CSTATE";
    private static final String INTENT_KEY_CASENUMBER = "INTENT_KEY_CASENUMBER";
    private static final String INTENT_KEY_CASEINFOBEAN = "INTENT_KEY_CASEINFOBEAN";

    public static Intent newInstance(Context context, String id, String cstate, String casenumber, CaseInfoBean caseinfo) {
        Intent intent = new Intent(context, WorkInfoActivity.class);
        intent.putExtra(INTENT_KEY_WORK, id);
        intent.putExtra(INTENT_KEY_CSTATE, cstate);
        intent.putExtra(INTENT_KEY_CASENUMBER, casenumber);
        Bundle bundle = new Bundle();
        bundle.putSerializable(INTENT_KEY_CASEINFOBEAN, caseinfo);
        intent.putExtras(bundle);
        return intent;
    }

    private String id;
    private String casenumber;
    private String cstate;

    private Button bt_addinfo;
    private RecyclerView rv_list;
    private WorkInfoAdapter adapter;

    private RecyclerView rvSgqPhoto;
    private RecyclerView rvSgqVideo;
    private RecyclerView rvSghPhoto;
    private RecyclerView rvSghVideo;

    private TextView tvCtid;
    private TextView tvCstate;
    private TextView tvWorklevel;
    private TextView tvUpuid;
    private TextView tvCdatetime;
    private TextView tvFeedbackcontent;
    private TextView tv_caddress;
    private TextView tv_pidname;
//    private MapView mMapView;

    private CaseInfoBean caseinfo;

    private MapView bmapView;
    private BaiduMap mBaiduMap;

    @Override
    protected int getLayoutContentId() {
        return R.layout.ac_workinfo;
    }

    @Override
    protected void initContentView() {
        setStatusBar(R.color.select_color);
        setTitle("案件记录", R.color.white);
        setToolBarBackgroundColor(R.color.select_color);

        bmapView = findViewById(R.id.bmapView);
        mBaiduMap = bmapView.getMap();

        rvSgqPhoto = findViewById(R.id.rv_sgq_photo);
        rvSgqVideo = findViewById(R.id.rv_sgq_video);
        rvSghPhoto = findViewById(R.id.rv_sgh_photo);
        rvSghVideo = findViewById(R.id.rv_sgh_video);
        tv_pidname = findViewById(R.id.tv_pidname);
        tv_caddress = findViewById(R.id.tv_caddress);


        tvCtid = findViewById(R.id.tv_ctid);
        tvCstate = findViewById(R.id.tv_cstate);
        tvWorklevel = findViewById(R.id.tv_worklevel);
        tvUpuid = findViewById(R.id.tv_upuid);
        tvCdatetime = findViewById(R.id.tv_cdatetime);
//        mMapView = findViewById(R.id.mapview);
        tvFeedbackcontent = findViewById(R.id.tv_feedbackcontent);


        bt_addinfo = findViewById(R.id.bt_addinfo);
        rv_list = findViewById(R.id.rv_list);
        rv_list.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new WorkInfoAdapter();
        rv_list.setAdapter(adapter);

        rvSgqPhoto.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        rvSgqVideo.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        rvSghPhoto.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        rvSghVideo.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        showLoading();
        id = getIntent().getStringExtra(INTENT_KEY_WORK);
        cstate = getIntent().getStringExtra(INTENT_KEY_CSTATE);
        casenumber = getIntent().getStringExtra(INTENT_KEY_CASENUMBER);
        mPresenter.queryDatas(id);
        caseinfo = (CaseInfoBean) getIntent().getSerializableExtra(INTENT_KEY_CASEINFOBEAN);

        settingData();
        initResource();
        initMap();
        if (RoleIdUtil.isSHIGONG() ) {
            bt_addinfo.setVisibility(View.VISIBLE);
        }
    }
    private void initMap() {
/*        ArcGISDynamicMapServiceLayer arcGISTiledMapServiceLayer = new ArcGISDynamicMapServiceLayer(Urls.mapUrl);
        mMapView.addLayer(arcGISTiledMapServiceLayer);
        GraphicsLayer hiddenSegmentsLayer = new GraphicsLayer();
        mMapView.addLayer(hiddenSegmentsLayer);
        mMapView.setMaxScale(10000);
        Point point = new Point(Double.valueOf(caseinfo.getX()),Double.valueOf(caseinfo.getY()));
        Drawable bitmap = ContextCompat.getDrawable(getContext(), R.mipmap.anfapoint);
        PictureMarkerSymbol pictureMarkerSymbol = new PictureMarkerSymbol(bitmap);
        Graphic graphic = new Graphic(point, pictureMarkerSymbol);
        mMapView.setExtent(point);
        hiddenSegmentsLayer.addGraphic(graphic);*/
        bmapView.showZoomControls(false);
        LatLng point = new LatLng(Double.valueOf(caseinfo.getY()),Double.valueOf(caseinfo.getX()));
        BitmapDescriptor bitmap = BitmapDescriptorFactory
                .fromResource(R.mipmap.anfapoint);
        OverlayOptions option = new MarkerOptions()
                .position(point)
                .icon(bitmap);
        mBaiduMap.addOverlay(option);

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
                Intent intent = MapActivity.newInstance(getContext(),caseinfo);
                startActivity(intent);
            }

            @Override
            public void onMapPoiClick(MapPoi mapPoi) {

            }
        });
    }


    private void initResource() {

        ImageAdapter adapterd = new ImageAdapter();
        adapterd.setPhotoPaths(caseinfo.getUrl(), caseinfo.getSGQIMAGES());
        adapterd.setOnPhotoListener(new ImageAdapter.OnPhotoListener() {
            @Override
            public void onClick(int type, int postoin, Object data) {
                String videoPaths = (String) data;
                PhotoDialog videoDialog = new PhotoDialog();
                videoDialog.setUrl(videoPaths);
                videoDialog.show(getSupportFragmentManager(), "s");
            }
        });
        rvSgqPhoto.setAdapter(adapterd);

        ImageAdapter adaptere = new ImageAdapter();
        adaptere.setPhotoPaths(caseinfo.getUrl(), caseinfo.getSGHIMAGES());
        adaptere.setOnPhotoListener(new ImageAdapter.OnPhotoListener() {
            @Override
            public void onClick(int type, int postoin, Object data) {
                String videoPaths = (String) data;
                PhotoDialog videoDialog = new PhotoDialog();
                videoDialog.setUrl(videoPaths);
                videoDialog.show(getSupportFragmentManager(), "es");
            }
        });
        rvSghPhoto.setAdapter(adaptere);

        VideoImageAdapter videoImageAdapterd = new VideoImageAdapter();
        videoImageAdapterd.setPhotoPaths(caseinfo.getUrl(), caseinfo.getSGQVIDEO());
        videoImageAdapterd.setOnPhotoListener(new VideoImageAdapter.OnVideoPhotoListener() {
            @Override
            public void onClick(int type, int postoin, Object data) {
                String videoPaths = (String) data;
                DownLoadViewDialog downLoadViewDialog = new DownLoadViewDialog();
                downLoadViewDialog.setUrl(videoPaths);
                downLoadViewDialog.show(getSupportFragmentManager(),"v");
            }
        });
        rvSgqVideo.setAdapter(videoImageAdapterd);


        VideoImageAdapter videoImageAdaptere = new VideoImageAdapter();
        videoImageAdaptere.setPhotoPaths(caseinfo.getUrl(), caseinfo.getSGHVIDEO());
        videoImageAdaptere.setOnPhotoListener(new VideoImageAdapter.OnVideoPhotoListener() {
            @Override
            public void onClick(int type, int postoin, Object data) {
                String videoPaths = (String) data;
                DownLoadViewDialog downLoadViewDialog = new DownLoadViewDialog();
                downLoadViewDialog.setUrl(videoPaths);
                downLoadViewDialog.show(getSupportFragmentManager(),"ev");
            }
        });
        rvSghVideo.setAdapter(videoImageAdaptere);

    }

    private void settingData() {
        tvCtid.setText(caseinfo.getTYPENAME());
        tvCstate.setText(StatusUtil.getName(caseinfo.getCSTATE()));
        tvWorklevel.setText(caseinfo.getWORKLEVEL());
        tv_caddress.setText(caseinfo.getCADDRESS());
        tv_pidname.setText(caseinfo.getPIDNAME());
        tvUpuid.setText(caseinfo.getUPUID());
        tvCdatetime.setText(caseinfo.getCDATETIME());
        tvFeedbackcontent.setText(caseinfo.getFEEDBACKCONTENT());
    }

    @Override
    protected void initListener() {
        super.initListener();
        bt_addinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = AddHandleInfoActivity.toIntent(getContext(), id, casenumber);
                startActivityForResult(intent, 200);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 200 && resultCode == 200) {
            showLoading();
            mPresenter.queryDatas(id);
        }
    }

    @Override
    public void queryDatasFail(String message) {
        dimiss();
        ToastUtility.showToast(message);
    }

    @Override
    public void queryDatasSuccess(List<WordInfoBean> datas) {
        dimiss();
        adapter.setData(datas);
        for (WordInfoBean data:datas){
            if (StatusUtil.isFinsh(data.getCSTATE())){
                bt_addinfo.setVisibility(View.GONE);
                break;
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
//        mMapView.pause();
        bmapView.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
//        mMapView.unpause();
        bmapView.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        mMapView.destroyDrawingCache();
        bmapView.onDestroy();
    }
}
