package com.zt.inspection.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.cjt2325.cameralibrary.CaptureLayout;
import com.cjt2325.cameralibrary.listener.ErrorListener;
import com.cjt2325.cameralibrary.listener.JCameraListener;
import com.cjt2325.cameralibrary.JCameraView;
import com.cjt2325.cameralibrary.listener.ClickListener;
import com.zt.inspection.R;

import java.io.File;
import java.lang.reflect.Field;

import cn.faker.repaymodel.activity.BaseToolBarActivity;
import cn.faker.repaymodel.util.LocImageUtility;

/**
 * 拍照,录制视频
 */
public class CameraActivity extends BaseToolBarActivity {

    private JCameraView jCameraView;

    public final static String CAMERA_TAG = "CAMERA_TAG";
    public final static String FILEVIDEO = "FILEVIDEO";
    public final static int CAMERA_TAG_PHOTO = 1;
    public final static int CAMERA_TAG_VIDEO = 2;

    public static final int SUCCESSCODE= 45;
    public static final String INTENT_TAG_PATH= "INTENT_TAG_PATH";

    @Override
    protected int getLayoutContentId() {
        return R.layout.ac_camera;
    }

    @Override
    protected void initContentView() {
        isShowToolView(false);
        changStatusIconCollor(true);
        if (Build.VERSION.SDK_INT >= 19) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        } else {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(option);
        }

        jCameraView = (JCameraView) findViewById(R.id.jcameraview);
    }

    public static String getCameraPath() {
        return Environment.getExternalStorageDirectory().getPath() + File.separator + "JCamera";
    }

    @Override
    public void initData(Bundle savedInstanceState) {
//设置视频保存路径
        jCameraView.setSaveVideoPath(getCameraPath());
        int type = getIntent().getIntExtra(CAMERA_TAG, 1);
        String text = "";
        if (type == CAMERA_TAG_PHOTO) {
            jCameraView.setFeatures(JCameraView.BUTTON_STATE_ONLY_CAPTURE);
            text= "点击拍照";
        } else if (type == CAMERA_TAG_VIDEO) {
            jCameraView.setFeatures(JCameraView.BUTTON_STATE_ONLY_RECORDER);
            text= "长按拍摄";
        }
        Class cs = jCameraView.getClass();
        Field[] fields = cs.getDeclaredFields();
        for (Field item :fields) {
            item.setAccessible(true);
            if ("mCaptureLayout".equals(item.getName())){
                CaptureLayout captureLayout = null;
                try {
                    captureLayout = (CaptureLayout) item.get(jCameraView);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }finally {
                    cs = null;
                }
                if (captureLayout!=null){
                    captureLayout.setTip(text);
                }
                break;
            }
        }

//设置视频质量
        jCameraView.setMediaQuality(JCameraView.MEDIA_QUALITY_MIDDLE);

//JCameraView监听
        jCameraView.setErrorLisenter(new ErrorListener() {
            @Override
            public void onError() {
                //打开Camera失败回调
                Log.i("CJT", "open camera error");
            }

            @Override
            public void AudioPermissionError() {
                //没有录取权限回调
                Log.i("CJT", "AudioPermissionError");
            }
        });

        jCameraView.setJCameraLisenter(new JCameraListener() {
            @Override
            public void captureSuccess(Bitmap bitmap) {
                String path = LocImageUtility.saveBitmap(getBaseContext(),bitmap);
                Intent intent = new Intent();
                intent.putExtra(INTENT_TAG_PATH,path);
                intent.putExtra(CAMERA_TAG,CAMERA_TAG_PHOTO);
                //获取图片bitmap
                setResult(SUCCESSCODE,intent);
                finish();
            }

            @Override
            public void recordSuccess(String url, Bitmap firstFrame) {
                Intent intent = new Intent();
                String path = LocImageUtility.saveBitmap(getBaseContext(),firstFrame);//第一帧
                intent.putExtra(INTENT_TAG_PATH,path);
                intent.putExtra(FILEVIDEO,url);
                intent.putExtra(CAMERA_TAG,CAMERA_TAG_VIDEO);
                //获取图片bitmap
                setResult(SUCCESSCODE,intent);
                finish();
            }
        });
//左边按钮点击事件
        jCameraView.setLeftClickListener(new ClickListener() {
            @Override
            public void onClick() {
                CameraActivity.this.finish();
            }
        });
//右边按钮点击事件
        jCameraView.setRightClickListener(new ClickListener() {
            @Override
            public void onClick() {
                Toast.makeText(CameraActivity.this, "Right", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        jCameraView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        jCameraView.onPause();
    }
}
