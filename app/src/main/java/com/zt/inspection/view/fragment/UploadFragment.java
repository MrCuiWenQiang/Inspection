package com.zt.inspection.view.fragment;

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
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.dmcbig.mediapicker.PickerActivity;
import com.dmcbig.mediapicker.PickerConfig;
import com.dmcbig.mediapicker.entity.Media;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;
import com.qmuiteam.qmui.widget.popup.QMUIListPopup;
import com.qmuiteam.qmui.widget.popup.QMUIPopup;
import com.zt.inspection.R;
import com.zt.inspection.contract.UploadActivityContract;
import com.zt.inspection.model.entity.request.WorkUpdateBean;
import com.zt.inspection.model.entity.response.CtypeBean;
import com.zt.inspection.presenter.UploadPresenter;
import com.zt.inspection.util.ImageUtil;
import com.zt.inspection.view.CameraActivity;
import com.zt.inspection.view.adapter.ResourceAdapter;
import com.zt.inspection.view.dialog.PhotoDialog;
import com.zt.inspection.view.dialog.VideoDialog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.faker.repaymodel.mvp.BaseMVPAcivity;
import cn.faker.repaymodel.mvp.BaseMVPFragment;
import cn.faker.repaymodel.util.LocImageUtility;
import cn.faker.repaymodel.util.ToastUtility;

import static android.os.Looper.getMainLooper;
import static com.zt.inspection.view.adapter.ResourceAdapter.ADAPTER_TYPR_SHOW_PHOTO;
import static com.zt.inspection.view.adapter.ResourceAdapter.ADAPTER_TYPR_SHOW_VIDEO;
import static com.zt.inspection.view.adapter.ResourceAdapter.TYPE_ADD;
import static com.zt.inspection.view.adapter.ResourceAdapter.TYPE_PHOTO;

/**
 * 案件上报
 */
public class UploadFragment extends BaseMVPFragment<UploadActivityContract.View, UploadPresenter>
        implements UploadActivityContract.View {

    private RecyclerView rv_photo;
    private RecyclerView rv_video;
    private ResourceAdapter adapter_photo;
    private ResourceAdapter adapter_video;

    private Button bt_update;
    private TextView tvCtype;
    private EditText etTitle;
    private TextView tvWorklevel;
    private EditText tvCaddress;
    private ImageView imCaddress;
    private EditText tvFeedbackcontent;

    private final int REQUESTCODE = 52;
    private final int REQUESTCODE_LOCAL_PHOTO = 63;
    private final int REQUESTCODE_LOCAL_VIDEO = 64;

    private List<String> photo_paths = new ArrayList<>();
    private List<String> video_photo_paths = new ArrayList<>();//视频第一帧地址
    private List<String> video_paths = new ArrayList<>();//视频地址


    private String[] typeIds;
    private String[] typeNames;
    private String[] levels = new String[]{"A", "B", "C", "D"};


    public static UploadFragment newInstance() {
        Bundle args = new Bundle();
        UploadFragment fragment = new UploadFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public int getLayoutId() {
        return R.layout.fg_upload;
    }

    @Override
    public void initview(View v) {

        rv_photo = findViewById(R.id.rv_photo);
        rv_video = findViewById(R.id.rv_video);


        bt_update = findViewById(R.id.bt_update);
        tvCtype = findViewById(R.id.tv_ctype);
        etTitle = findViewById(R.id.et_title);
        tvWorklevel = findViewById(R.id.tv_worklevel);
        tvCaddress = findViewById(R.id.et_caddress);
        imCaddress = findViewById(R.id.im_caddress);
        tvFeedbackcontent = findViewById(R.id.tv_feedbackcontent);


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
        showLoading();
        mPresenter.getCtype();
    }

    private final String[] pts = new String[]{"图库", "拍照"};
    private final String[] vds = new String[]{"图库", "拍视频"};

    @Override
    protected void initListener() {
        adapter_photo.setOnPhotoListener(new ResourceAdapter.OnPhotoListener() {
            @Override
            public void onClick(int type, int postoin, Object data) {
                Intent intent = new Intent();
                if (type == TYPE_ADD) {
                    showListDialog("", pts, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (which == 0) {
                                selectLocal(REQUESTCODE_LOCAL_PHOTO, PickerConfig.PICKER_IMAGE);
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
                    videoDialog.show(getChildFragmentManager(), "s");
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
                                selectLocal(REQUESTCODE_LOCAL_VIDEO, PickerConfig.PICKER_VIDEO);
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
                    videoDialog.show(getChildFragmentManager(), "s");
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
        imCaddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//map point ,let select
                // TODO: 2020/6/17 地图选点
            }
        });
        tvCtype.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSelectType(tvCtype);
            }
        });
        tvWorklevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSelectLevel(tvWorklevel);
            }
        });
        bt_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update();
            }
        });
    }


    private void update() {
        WorkUpdateBean data = new WorkUpdateBean();
        if (TextUtils.isEmpty(tvCtype.getText())) {
            ToastUtility.showToast("请选择事件类型");
            return;
        }
  /*      if (TextUtils.isEmpty(etTitle.getText())) {
            ToastUtility.showToast("请填写事件标题");
            return;
        }*/
        if (TextUtils.isEmpty(tvWorklevel.getText())) {
            ToastUtility.showToast("请选择事件等级");
            return;
        }
        data.setCTYPE(getValue(tvCtype));
        data.setCTID(tvCtype.getTag() == null ? null : String.valueOf(tvCtype.getTag()));
        data.setTITLE(getValue(etTitle));
        data.setWORKLEVEL(getValue(tvWorklevel));
        data.setCADDRESS(getValue(tvCaddress));
        data.setFeedbackContent(getValue(tvFeedbackcontent));
/*        data.setX(String.valueOf(x));
        data.setY(String.valueOf(y));*/
// TODO: 2020/6/18 因为后台数据库将坐标 x y倒置问题 故反过来
        data.setX(String.valueOf(0));
        data.setY(String.valueOf(0));
//        data.setPID(id);

        mPresenter.uploadFile(data, photo_paths, video_paths);
    }

    @Override
    public void getCtype_Success(String[] ids, String[] names, List<CtypeBean> datas) {
        typeIds = ids;
        typeNames = names;
        dimiss();

    }

    @Override
    public void getCtype_Fail(String s) {
        dimiss();
        showDialog(s, new QMUIDialogAction.ActionListener() {
            @Override
            public void onClick(QMUIDialog dialog, int index) {
                dialog.dismiss();
            }
        });
    }

    @Override
    public void uploadFileFail(String message) {
        showDialog(message);
    }

    @Override
    public void uploadFileSuccess(String message) {
        showLoading();
    }

    @Override
    public void uploadSuccess(String message) {
        dimiss();
        showDialog(message);
        video_photo_paths.clear();
        video_paths.clear();
        photo_paths.clear();
        adapter_photo.setPhotoPaths(photo_paths);
        adapter_video.setPhotoPaths(video_photo_paths);
        tvFeedbackcontent.setText(null);
    }

    @Override
    public void uploadFail(String message) {
        dimiss();
        showDialog(message);
    }

    @Override
    public void getAddress_Success(String address) {
        dimiss();
        tvCaddress.setText(address);
    }

    @Override
    public void getAddress_Fail(String message) {
        dimiss();
        showDialog(message);
    }

    private void showSelectType(TextView tv) {
        ArrayAdapter adapter = new ArrayAdapter<>(getContext(), R.layout.v_tablist, new ArrayList<>(Arrays.asList(typeNames)));
        final QMUIListPopup mListPopup = new QMUIListPopup(getContext(), QMUIPopup.DIRECTION_BOTTOM, adapter);
        mListPopup.create(tv.getWidth(), QMUIDisplayHelper.dp2px(getContext(), 200),
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        tv.setText(typeNames[position]);
                        tv.setTag(typeIds[position]);
                        mListPopup.dismiss();
                    }
                });
        mListPopup.setAnimStyle(QMUIPopup.ANIM_GROW_FROM_CENTER);
        mListPopup.show(tv);
    }


    // TODO: 2020/6/17 代码冗余  完全可以通过类型去控制
    private void showSelectLevel(TextView tv) {
        ArrayAdapter adapter = new ArrayAdapter<>(getContext(), R.layout.v_tablist, new ArrayList<>(Arrays.asList(levels)));
        final QMUIListPopup mListPopup = new QMUIListPopup(getContext(), QMUIPopup.DIRECTION_BOTTOM, adapter);
        mListPopup.create(tv.getWidth(), QMUIDisplayHelper.dp2px(getContext(), 200),
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        tv.setText(levels[position]);
                        mListPopup.dismiss();
                    }
                });
        mListPopup.setAnimStyle(QMUIPopup.ANIM_GROW_FROM_CENTER);
        mListPopup.show(tv);
    }

    ArrayList<Media> select;

    void selectLocal(int result, int mondel) {
        Intent intent = new Intent(getContext(), PickerActivity.class);
//        intent.putExtra(PickerConfig.SELECT_MODE,PickerConfig.PICKER_IMAGE_VIDEO);//设置选择类型，默认是图片和视频可一起选择(非必填参数)
        intent.putExtra(PickerConfig.SELECT_MODE, mondel);//设置选择类型，默认是图片和视频可一起选择(非必填参数)
        long maxSize = 188743680L;//long long long long类型
        intent.putExtra(PickerConfig.MAX_SELECT_SIZE, maxSize); //最大选择大小，默认180M（非必填参数）
        intent.putExtra(PickerConfig.MAX_SELECT_COUNT, 5);  //最大选择数量，默认40（非必填参数）
        startActivityForResult(intent, result);
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
                        Bitmap bitmap = ImageUtil.getVideoThumbnail(item.path, MediaStore.Images.Thumbnails.MINI_KIND, 400, 400);
                        String path = LocImageUtility.saveBitmap(getContext(), bitmap);//第一帧
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
}
