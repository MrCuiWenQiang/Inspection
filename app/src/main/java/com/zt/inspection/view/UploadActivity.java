package com.zt.inspection.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zt.inspection.R;
import com.zt.inspection.contract.UploadActivityContract;
import com.zt.inspection.presenter.UploadPresenter;
import com.zt.inspection.view.CameraActivity;
import com.zt.inspection.view.adapter.ResourceAdapter;
import com.zt.inspection.view.dialog.VideoDialog;

import java.util.ArrayList;
import java.util.List;

import cn.faker.repaymodel.mvp.BaseMVPAcivity;
import cn.faker.repaymodel.mvp.BaseMVPFragment;

import static com.zt.inspection.view.adapter.ResourceAdapter.ADAPTER_TYPR_SHOW_PHOTO;
import static com.zt.inspection.view.adapter.ResourceAdapter.ADAPTER_TYPR_SHOW_VIDEO;
import static com.zt.inspection.view.adapter.ResourceAdapter.TYPE_ADD;
import static com.zt.inspection.view.adapter.ResourceAdapter.TYPE_PHOTO;

/**
 * 案件上报
 */
public class UploadActivity extends BaseMVPAcivity<UploadActivityContract.View, UploadPresenter> implements UploadActivityContract.View, View.OnClickListener {



    private RecyclerView rv_photo;
    private RecyclerView rv_video;
    private ResourceAdapter adapter_photo;
    private ResourceAdapter adapter_video;
    private List<String> photo_paths = new ArrayList<>();
    private List<String> video_photo_paths = new ArrayList<>();//视频第一帧地址
    private List<String> video_paths = new ArrayList<>();//视频地址
    @Override
    public void onClick(View v) {

    }



    @Override
    protected int getLayoutContentId() {
        return R.layout.fg_upload;
    }

    @Override
    protected void initContentView() {
        rv_photo = findViewById(R.id.rv_photo);
        rv_video = findViewById(R.id.rv_video);

        rv_photo.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        rv_video.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
//        rv_photo.setLayoutManager(new GridLayoutManager(getContext(),2));
//        rv_video.setLayoutManager(new GridLayoutManager(getContext(),2));
        adapter_photo = new ResourceAdapter(ADAPTER_TYPR_SHOW_PHOTO);
        adapter_video = new ResourceAdapter(ADAPTER_TYPR_SHOW_VIDEO);
        rv_photo.setAdapter(adapter_photo);
        rv_video.setAdapter(adapter_video);
    }



    @Override
    public void initData(Bundle savedInstanceState) {
mPresenter.uploadFile("/storage/sdcard0/excel/leave.png");
    }

    private final int REQUESTCODE = 52;

    @Override
    protected void initListener() {
        adapter_photo.setOnPhotoListener(new ResourceAdapter.OnPhotoListener() {
            @Override
            public void onClick(int type, int postoin, Object data) {
                Intent intent = new Intent();
                if (type == TYPE_ADD) {
                    intent.setClass(getContext(), CameraActivity.class);
                    intent.putExtra(CameraActivity.CAMERA_TAG, CameraActivity.CAMERA_TAG_PHOTO);
                    startActivityForResult(intent, REQUESTCODE);
                } else if (type == TYPE_PHOTO) {
                }
            }
        });
        adapter_video.setOnPhotoListener(new ResourceAdapter.OnPhotoListener() {
            @Override
            public void onClick(int type, int postoin, Object data) {
                Intent intent = new Intent();
                if (type == TYPE_ADD) {
                    intent.setClass(getContext(), CameraActivity.class);
                    intent.putExtra(CameraActivity.CAMERA_TAG, CameraActivity.CAMERA_TAG_VIDEO);
                    startActivityForResult(intent, REQUESTCODE);
                } else if (type == TYPE_PHOTO) {

                    String videoPaths = video_paths.get(postoin);
                    VideoDialog videoDialog = new VideoDialog();
                    videoDialog.setUrl(videoPaths);
                    videoDialog.show(getSupportFragmentManager(),"s");
                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUESTCODE && resultCode == CameraActivity.SUCCESSCODE) {
            String path = data.getStringExtra(CameraActivity.INTENT_TAG_PATH);
            int type = data.getIntExtra(CameraActivity.CAMERA_TAG, 1);
            if (type == CameraActivity.CAMERA_TAG_PHOTO) {
                photo_paths.add(path);
                adapter_photo.setPhotoPaths(photo_paths);
            } else if (type == CameraActivity.CAMERA_TAG_VIDEO) {
                video_photo_paths.add(path);
                video_paths.add(data.getStringExtra(CameraActivity.FILEVIDEO));
                adapter_video.setPhotoPaths(video_photo_paths);
            }
        }
    }
}
