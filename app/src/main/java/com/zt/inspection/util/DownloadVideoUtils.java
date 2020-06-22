package com.zt.inspection.util;

import android.os.Environment;

import java.io.File;

import cn.faker.repaymodel.net.okhttp3.HttpHelper;
import cn.faker.repaymodel.net.okhttp3.callback.DownLoadFileCallBack;

public class DownloadVideoUtils {


    public void loadFile(String url, DownLoadFileCallBack callBack) {
        File file = new File(getOutFilePath(), getFileName(url));
        if (file.exists()) {
//            file.getPath();
            callBack.onComplete(file);
        } else {
            downLoad(url, callBack);
        }
    }


    private void downLoad(String url, DownLoadFileCallBack callBack) {
        HttpHelper.downloadFile(url, new DownLoadFileCallBack(getOutFilePath(), getFileName(url)) {
            @Override
            public void onComplete(File file) {
                callBack.onComplete(file);
            }

            @Override
            public void onDownloading(int progress) {
                callBack.onDownloading(progress);
            }

            @Override
            public void onDownloadFail(int code) {
                callBack.onDownloadFail(code);
            }
        });
    }


    private String getFileName(String url) {
        int endindex = url.lastIndexOf("/");
        String file = url.substring(endindex, url.length());
        return file;
    }

    public static String getOutFilePath() {
        String excelPath = Environment.getExternalStorageDirectory() + "/xjVideos";
        File file = new File(excelPath);
        if (!file.exists())
            file.mkdir();

        return excelPath;
    }
}
