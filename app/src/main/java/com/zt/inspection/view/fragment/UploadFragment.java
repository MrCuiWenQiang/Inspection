package com.zt.inspection.view.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zt.inspection.R;
import com.zt.inspection.contract.UploadFragmentContract;
import com.zt.inspection.presenter.UploadPresenter;
import com.zt.inspection.view.CameraActivity;
import com.zt.inspection.view.adapter.ResourceAdapter;

import cn.faker.repaymodel.mvp.BaseMVPFragment;

import static com.zt.inspection.view.adapter.ResourceAdapter.ADAPTER_TYPR_SHOW_PHOTO;
import static com.zt.inspection.view.adapter.ResourceAdapter.ADAPTER_TYPR_SHOW_VIDEO;
import static com.zt.inspection.view.adapter.ResourceAdapter.TYPE_ADD;
import static com.zt.inspection.view.adapter.ResourceAdapter.TYPE_PHOTO;

/**
 * 案件上报
 */
public class UploadFragment extends BaseMVPFragment<UploadFragmentContract.View, UploadPresenter> implements UploadFragmentContract.View, View.OnClickListener {

    public static UploadFragment newInstance() {
        Bundle args = new Bundle();
        UploadFragment fragment = new UploadFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private RecyclerView rv_photo;
    private RecyclerView rv_video;
    private ResourceAdapter adapter_photo;
    private ResourceAdapter adapter_video;

    @Override
    public void onClick(View v) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fg_upload;
    }

    @Override
    public void initview(View v) {
        rv_photo = findViewById(R.id.rv_photo);
        rv_video = findViewById(R.id.rv_video);

        rv_photo.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        rv_video.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        adapter_photo = new ResourceAdapter(ADAPTER_TYPR_SHOW_PHOTO);
        adapter_video = new ResourceAdapter(ADAPTER_TYPR_SHOW_VIDEO);
        rv_photo.setAdapter(adapter_photo);
        rv_video.setAdapter(adapter_video);
    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void initListener() {
        adapter_photo.setOnPhotoListener(new ResourceAdapter.OnPhotoListener() {
            @Override
            public void onClick(int type, int postoin, Object data) {
                if (type == TYPE_ADD) {
                    toAcitvity(CameraActivity.class);
                } else if (type == TYPE_PHOTO) {
                }
            }
        });
        adapter_video.setOnPhotoListener(new ResourceAdapter.OnPhotoListener() {
            @Override
            public void onClick(int type, int postoin, Object data) {
                if (type == TYPE_ADD) {
                    toAcitvity(CameraActivity.class);
                } else if (type == TYPE_PHOTO) {
                }
            }
        });
    }
}
