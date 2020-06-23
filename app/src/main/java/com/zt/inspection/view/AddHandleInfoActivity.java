package com.zt.inspection.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;
import com.qmuiteam.qmui.widget.popup.QMUIListPopup;
import com.qmuiteam.qmui.widget.popup.QMUIPopup;
import com.zt.inspection.R;
import com.zt.inspection.contract.AddHandleInfoContract;
import com.zt.inspection.model.entity.request.AddHandleInfoEntity;
import com.zt.inspection.presenter.AddHandleInfoPresenter;
import com.zt.inspection.view.adapter.ResourceAdapter;
import com.zt.inspection.view.dialog.PhotoDialog;
import com.zt.inspection.view.dialog.VideoDialog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.faker.repaymodel.mvp.BaseMVPAcivity;

import static com.zt.inspection.view.adapter.ResourceAdapter.ADAPTER_TYPR_SHOW_PHOTO;
import static com.zt.inspection.view.adapter.ResourceAdapter.ADAPTER_TYPR_SHOW_VIDEO;
import static com.zt.inspection.view.adapter.ResourceAdapter.TYPE_ADD;
import static com.zt.inspection.view.adapter.ResourceAdapter.TYPE_PHOTO;

/**
 * 上传记录
 */
public class AddHandleInfoActivity extends BaseMVPAcivity<AddHandleInfoContract.View, AddHandleInfoPresenter>
        implements AddHandleInfoContract.View {

    private static final String INTENT_KEY_ID= "INTENT_KEY_ID";
    private static final String INTENT_KEY_CASENUMBER = "INTENT_KEY_CASENUMBER";

    public static Intent toIntent(Context context, String id, String casenumber) {
        Intent intent = new Intent();
        intent.setClass(context, AddHandleInfoActivity.class);
        intent.putExtra(INTENT_KEY_ID, id);
        intent.putExtra(INTENT_KEY_CASENUMBER,casenumber);
        return intent;
    }


    private String id;
    private String casenumber;
    private TextView tvCstate;
    private EditText tvFeedbackcontent;
    private Button btUpdate;


    private RecyclerView rv_photo;
    private RecyclerView rv_video;
    private ResourceAdapter adapter_photo;
    private ResourceAdapter adapter_video;
    private final int REQUESTCODE = 52;
    private List<String> photo_paths = new ArrayList<>();
    private List<String> video_photo_paths = new ArrayList<>();//视频第一帧地址
    private List<String> video_paths = new ArrayList<>();//视频地址

    private String[] levels = new String[]{"未完成", "已完成"};
    private String[] levelids = new String[]{"2", "3"};

    @Override
    protected int getLayoutContentId() {
        return R.layout.ac_addhandleinfo;
    }

    @Override
    protected void initContentView() {
        setStatusBar(R.color.select_color);
        setTitle("上报记录", R.color.white);
        setToolBarBackgroundColor(R.color.select_color);

        tvCstate = findViewById(R.id.tv_cstate);
        tvFeedbackcontent = findViewById(R.id.tv_feedbackcontent);
        rv_photo = findViewById(R.id.rv_photo);
        rv_video = findViewById(R.id.rv_video);
        btUpdate = findViewById(R.id.bt_update);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        rv_photo.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        rv_video.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        adapter_photo = new ResourceAdapter(ADAPTER_TYPR_SHOW_PHOTO);
        adapter_video = new ResourceAdapter(ADAPTER_TYPR_SHOW_VIDEO);
        rv_photo.setAdapter(adapter_photo);
        rv_video.setAdapter(adapter_video);
        id = getIntent().getStringExtra(INTENT_KEY_ID);
        casenumber = getIntent().getStringExtra(INTENT_KEY_CASENUMBER);
    }

    @Override
    protected void initListener() {
        super.initListener();
        adapter_photo.setOnPhotoListener(new ResourceAdapter.OnPhotoListener() {
            @Override
            public void onClick(int type, int postoin, Object data) {
                Intent intent = new Intent();
                if (type == TYPE_ADD) {
                    intent.setClass(getContext(), CameraActivity.class);
                    intent.putExtra(CameraActivity.CAMERA_TAG, CameraActivity.CAMERA_TAG_PHOTO);
                    startActivityForResult(intent, REQUESTCODE);
                } else if (type == TYPE_PHOTO) {
                    String videoPaths = photo_paths.get(postoin);
                    PhotoDialog videoDialog = new PhotoDialog();
                    videoDialog.setUrl(videoPaths);
                    videoDialog.show(getSupportFragmentManager(), "s");
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
                    videoDialog.show(getSupportFragmentManager(), "s");
                }
            }
        });
        tvCstate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSelectType(tvCstate);
            }
        });
        btUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(tvCstate.getText())){
                    showDialog("请选择完成状态");
                    return;
                }
                AddHandleInfoEntity entity = new AddHandleInfoEntity();
                // TODO: 2020/6/22 因接口原因 换为案件编号
                entity.setCID(casenumber);
                entity.setFEEDBACKCONTENT(getEditTextValue(tvFeedbackcontent));
                entity.setCSTATE(String.valueOf(tvCstate.getTag()));
                showLoading();
                mPresenter.uploadFile(entity,photo_paths,video_paths);
            }
        });
    }

    private void showSelectType(TextView tv) {
        ArrayAdapter adapter = new ArrayAdapter<>(getContext(), R.layout.v_tablist, new ArrayList<>(Arrays.asList(levels)));
        final QMUIListPopup mListPopup = new QMUIListPopup(getContext(), QMUIPopup.DIRECTION_BOTTOM, adapter);
        mListPopup.create(tv.getWidth(), QMUIDisplayHelper.dp2px(getContext(), 200),
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        tv.setText(levels[position]);
                        tv.setTag(levelids[position]);
                        mListPopup.dismiss();
                    }
                });
        mListPopup.setAnimStyle(QMUIPopup.ANIM_GROW_FROM_CENTER);
        mListPopup.show(tv);
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

    public void uploadFileFail(String message) {
        showDialog(message);
    }

    @Override
    public void uploadFileSuccess(String message) {
        showLoading(message);
    }

    @Override
    public void uploadSuccess(String message) {
        setResult(200);
        dimiss();
        showDialog(message, new QMUIDialogAction.ActionListener() {
            @Override
            public void onClick(QMUIDialog dialog, int index) {
                dialog.dismiss();
                finish();
            }
        });
    }

    @Override
    public void uploadFail(String message) {
        dimiss();
        showDialog(message);
    }

}
