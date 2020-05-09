package com.zt.inspection.view.fragment;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.esri.android.map.GraphicsLayer;
import com.esri.android.map.LocationDisplayManager;
import com.esri.android.map.MapView;
import com.esri.android.map.ags.ArcGISDynamicMapServiceLayer;
import com.esri.core.geometry.Point;
import com.zt.inspection.R;
import com.zt.inspection.Urls;
import com.zt.inspection.contract.MapFragmentContract;
import com.zt.inspection.presenter.MapFragmentPresenter;

import cn.faker.repaymodel.mvp.BaseMVPFragment;
import cn.faker.repaymodel.util.ToastUtility;
import cn.faker.repaymodel.util.permission.PermissionHelper;
import cn.faker.repaymodel.util.permission.collocation.Api23;

/**
 * Map地图展示Fragment
 */
public class MapFragment extends BaseMVPFragment<MapFragmentContract.View, MapFragmentPresenter> implements MapFragmentContract.View, View.OnClickListener {
    String[] pers = new String[]{Api23.LOCATION, Api23.STORAGE};

    private MapView mMapView;
    private GraphicsLayer hiddenSegmentsLayer;

    private LocationDisplayManager locationDisplayManager;

    public static MapFragment newInstance() {
        Bundle args = new Bundle();
        MapFragment fragment = new MapFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fg_map;
    }

    @Override
    public void initview(View v) {
        mMapView = v.findViewById(R.id.mapview);
        initMap();

    }

    @Override
    public boolean requestData() {
        if (isHavePM(getContext(), pers)) {
            initLocation();
        }
        return false;
    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    private void initMap() {
        ArcGISDynamicMapServiceLayer arcGISTiledMapServiceLayer = new ArcGISDynamicMapServiceLayer(Urls.mapUrl);
        mMapView.addLayer(arcGISTiledMapServiceLayer);
        hiddenSegmentsLayer = new GraphicsLayer();
        mMapView.addLayer(hiddenSegmentsLayer);
    }

    private void initLocation() {
        locationDisplayManager = mMapView.getLocationDisplayManager();
        locationDisplayManager.setAutoPanMode(LocationDisplayManager.AutoPanMode.LOCATION);
        locationDisplayManager.setLocationListener(new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

                String bdlat = location.getLatitude() + "";
                String bdlon = location.getLongitude() + "";
                if (bdlat.indexOf("E") == -1 || bdlon.indexOf("E") == -1) {
                    //这里做个判断是因为，可能因为gps信号问题，定位出来的经纬度不正常。
                    double lat = location.getLatitude();//纬度
                    double lon = location.getLongitude();//经度
//                    mMapView.setExtent(new Point(lon, lat), 250);
                }
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

            }
        });
        locationDisplayManager.start();
    }

    @Override
    public void onClick(View v) {

    }

    public boolean isHavePM(Context context, String... permissiont) {
        if (PermissionHelper.isversion()) {
            for (String tag : permissiont) {
                if ((ContextCompat.checkSelfPermission(context, tag)) !=
                        PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(permissiont, PermissionHelper.PERMISSION_CODE);
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PermissionHelper.PERMISSION_CODE) {
            if (Api23.LOCATION.equals(permissions[0])) {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                } else {
                    ToastUtility.showToast("权限未给予,部分功能将不可用");
                }
            } else if (Api23.STORAGE.equals(permissions[1])) {
                if (grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    initLocation();
                } else {
                    ToastUtility.showToast("权限未给予,部分功能将不可用");
                }
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.pause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.destroyDrawingCache();
        locationDisplayManager.stop();
    }
}
