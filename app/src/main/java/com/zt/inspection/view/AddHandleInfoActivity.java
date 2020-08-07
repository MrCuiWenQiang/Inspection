package com.zt.inspection.view;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.dmcbig.mediapicker.PickerActivity;
import com.dmcbig.mediapicker.PickerConfig;
import com.dmcbig.mediapicker.entity.Media;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;
import com.qmuiteam.qmui.widget.popup.QMUIListPopup;
import com.qmuiteam.qmui.widget.popup.QMUIPopup;
import com.zt.inspection.R;
import com.zt.inspection.contract.AddHandleInfoContract;
import com.zt.inspection.model.entity.request.AddHandleInfoEntity;
import com.zt.inspection.presenter.AddHandleInfoPresenter;
import com.zt.inspection.util.ImageUtil;
import com.zt.inspection.view.adapter.ResourceAdapter;
import com.zt.inspection.view.dialog.PhotoDialog;
import com.zt.inspection.view.dialog.VideoDialog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.faker.repaymodel.mvp.BaseMVPAcivity;
import cn.faker.repaymodel.util.LocImageUtility;

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
    private final int REQUESTCODE_LOCAL_PHOTO = 63;
    private final int REQUESTCODE_LOCAL_VIDEO = 64;
    private List<String> photo_paths = new ArrayList<>();
    private List<String> video_photo_paths = new ArrayList<>();//视频第一帧地址
    private List<String> video_paths = new ArrayList<>();//视频地址

    private String[] levels = new String[]{"未完成", "已完成"};
    private String[] levelids = new String[]{"2", "3"};
    private final String[] pts = new String[]{"图库", "拍照"};
    private final String[] vds = new String[]{"图库", "拍视频"};
    @Override
    protected int getLayoutContentId() {
        return R.layout.ac_addhandleinfo;
    }

    @Override
    protected void initContentView() {
        isShowCut(false);
        setStatusBar(R.color.stdf_color);
        setTitle("上报记录", R.color.white);
        setToolBarBackgroundColor(R.drawable.ll_top_b);

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
                    showListDialog("", pts, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (which == 0) {
                                selectLocal(REQUESTCODE_LOCAL_PHOTO,PickerConfig.PICKER_IMAGE);
                            } else {
                                intent.setClass(getContext(), CameraActivity.class);
                                intent.putExtra(CameraActivity.CAMERA_TAG, CameraActivity.CAMERA_TAG_PHOTO);
                                startActivityForResult(intent, REQUESTCODE);
                            }
                            dialog.dismiss();
                        }
                    });
                } else if (type == TYPE_PHOTO) {
                    String videoPaths = photo_paths.get(postoin);
                    PhotoDialog videoDialog = new PhotoDialog();
                    videoDialog.setUrl(videoPaths);
                    videoDialog.show(getSupportFragmentManager(), "s");
                }
            }

            @Override
            public void onLongClick(int type, int postoin, Object data) {
                new QMUIDialog.MessageDialogBuilder(getContext()).setMessage("是否删除该图片?")
                        .addAction("删除", new QMUIDialogAction.ActionListener() {
                            @Override
                            public void onClick(QMUIDialog dialog, int index) {
                                dialog.dismiss();
//                                photo_paths.remove(postoin);
                                adapter_photo.removeItem(postoin);
                            }
                        }).addAction("取消", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        dialog.dismiss();
                    }
                }).show();
            }
        });
        adapter_video.setOnPhotoListener(new ResourceAdapter.OnPhotoListener() {
            @Override
            public void onClick(int type, int postoin, Object data) {
                Intent intent = new Intent();
                if (type == TYPE_ADD) {
                    showListDialog("", vds, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (which == 0) {
                                selectLocal(REQUESTCODE_LOCAL_VIDEO,PickerConfig.PICKER_VIDEO);
                            } else {
                                intent.setClass(getContext(), CameraActivity.class);
                                intent.putExtra(CameraActivity.CAMERA_TAG, CameraActivity.CAMERA_TAG_VIDEO);
                                startActivityForResult(intent, REQUESTCODE);
                            }
                            dialog.dismiss();
                        }
                    });
                } else if (type == TYPE_PHOTO) {

                    String videoPaths = video_paths.get(postoin);
                    VideoDialog videoDialog = new VideoDialog();
                    videoDialog.setUrl(videoPaths);
                    videoDialog.show(getSupportFragmentManager(), "s");
                }
            }

            @Override
            public void onLongClick(int type, int postoin, Object data) {
                new QMUIDialog.MessageDialogBuilder(getContext()).setMessage("是否删除该视频?")
                        .addAction("删除", new QMUIDialogAction.ActionListener() {
                            @Override
                            public void onClick(QMUIDialog dialog, int index) {
                                dialog.dismiss();
//                                video_photo_paths.remove(postoin);
                                video_paths.remove(postoin);
                                adapter_video.removeItem(postoin);
                            }
                        }).addAction("取消", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        dialog.dismiss();
                    }
                }).show();
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
    void selectLocal(int result,int mondel) {
        Intent intent = new Intent(getContext(), PickerActivity.class);
//        intent.putExtra(PickerConfig.SELECT_MODE,PickerConfig.PICKER_IMAGE_VIDEO);//设置选择类型，默认是图片和视频可一起选择(非必填参数)
        intent.putExtra(PickerConfig.SELECT_MODE, mondel);//设置选择类型，默认是图片和视频可一起选择(非必填参数)
        long maxSize = 188743680L;//long long long long类型
        intent.putExtra(PickerConfig.MAX_SELECT_SIZE, maxSize); //最大选择大小，默认180M（非必填参数）
        intent.putExtra(PickerConfig.MAX_SELECT_COUNT, 5);  //最大选择数量，默认40（非必填参数）
        startActivityForResult(intent, result);
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
    ArrayList<Media> select;
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
        } else if (requestCode == REQUESTCODE_LOCAL_PHOTO && resultCode == PickerConfig.RESULT_CODE) {//本地选择
            select = data.getParcelableArrayListExtra(PickerConfig.EXTRA_RESULT);//选择完后返回的list
            for (Media item : select) {
                photo_paths.add(item.path);
            }
            adapter_photo.setPhotoPaths(photo_paths);
        } else if (requestCode == REQUESTCODE_LOCAL_VIDEO && resultCode == PickerConfig.RESULT_CODE) {//本地选择
            select = data.getParcelableArrayListExtra(PickerConfig.EXTRA_RESULT);//选择完后返回的list
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (Media item : select) {
                        Bitmap bitmap = ImageUtil.getVideoThumbnail(item.path,MediaStore.Images.Thumbnails.MINI_KIND,400,400);
                        String path = LocImageUtility.saveBitmap(getBaseContext(),bitmap);//第一帧
                        video_photo_paths.add(path);
                        video_paths.add(item.path);
                    }
                    new Handler(getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            adapter_video.setPhotoPaths(video_photo_paths);
                        }
                    });
                }
            }).start();
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
