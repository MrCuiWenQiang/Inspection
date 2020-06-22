package com.zt.inspection.view.dialog;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.VideoView;

import com.zt.inspection.R;
import com.zt.inspection.util.DownloadVideoUtils;

import java.io.File;

import cn.faker.repaymodel.net.okhttp3.callback.DownLoadFileCallBack;
import cn.faker.repaymodel.util.ScreenUtil;
import cn.faker.repaymodel.util.ToastUtility;
import cn.faker.repaymodel.widget.view.dialog.BasicDialog;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

public class DownLoadViewDialog extends BasicDialog {

    private LinearLayout ll_status;
    private VideoView mVideoView;
    private String url;
    private DownloadVideoUtils videoUtils;

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int getLayoutId() {
        return R.layout.dg_downloadview;
    }

    @Override
    public void initview(View v) {
        mVideoView = v.findViewById(R.id.vd_model);
        ll_status = v.findViewById(R.id.ll_status);
        mVideoView.setMediaController(new MediaController(getContext()));
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        videoUtils = new DownloadVideoUtils();
        videoUtils.loadFile(url, new DownLoadFileCallBack() {
            @Override
            public void onComplete(File file) {
                if (ll_status!=null){
                    ll_status.setVisibility(View.GONE);
                    String path = file.getPath();
                    playVideo(path);
                }
            }

            @Override
            public void onDownloading(int progress) {

            }

            @Override
            public void onDownloadFail(int code) {
                dismiss();
                ToastUtility.showToast("加载视频失败!");
            }
        });
    }

    private void playVideo(final String path) {
        Uri uri = Uri.parse(path);
        mVideoView.setVideoURI(uri);
        mVideoView.start();

        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();// 播放
            }
        });

        mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                dismiss();
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    protected int getDialogWidth() {
//        return ScreenUtil.getWindowWidth(getContext()) - 10;
        return WRAP_CONTENT;
    }

    protected int getDialogHeght() {
//        return ScreenUtil.getWindowHeight(getContext()) - 10;
        return WRAP_CONTENT;

    }

}
