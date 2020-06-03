package com.zt.inspection.view.dialog;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

import com.zt.inspection.R;


import cn.faker.repaymodel.util.ScreenUtil;
import cn.faker.repaymodel.widget.view.dialog.BasicDialog;

public class VideoDialog extends BasicDialog {

    private VideoView mVideoView;

    private String url;

    @Override
    public int getLayoutId() {
        return R.layout.dg_dialog;
    }

    @Override
    public void initview(View v) {
        mVideoView = v.findViewById(R.id.vd_model);

        mVideoView.setMediaController(new MediaController(getContext()));
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        playVideo(url);
    }


    public void setUrl(String url) {
        this.url = url;
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


    protected int getDialogWidth() {
        return ScreenUtil.getWindowWidth(getContext()) - 5;
    }

    protected int getDialogHeght() {
        return ScreenUtil.getWindowHeight(getContext()) - 10;

    }
}
