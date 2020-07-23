package com.zt.inspection.presenter;


import android.os.Environment;

import com.zt.inspection.MyApplication;
import com.zt.inspection.Urls;
import com.zt.inspection.contract.UpdateContract;
import com.zt.inspection.model.entity.response.VersionBean;
import com.zt.inspection.util.VersionUtil;

import java.io.File;
import java.util.UUID;

import cn.faker.repaymodel.mvp.BaseMVPPresenter;
import cn.faker.repaymodel.net.json.JsonUtil;
import cn.faker.repaymodel.net.okhttp3.HttpHelper;
import cn.faker.repaymodel.net.okhttp3.callback.DownLoadFileCallBack;
import cn.faker.repaymodel.net.okhttp3.callback.HttpResponseCallback;
import cn.faker.repaymodel.util.DateUtils;
import cn.faker.repaymodel.widget.view.date.DateUtil;

public class UpdatePresenter extends BaseMVPPresenter<UpdateContract.View> implements UpdateContract.Presenter {

    @Override
    public void initVersion() {
        getView().showVersionName("当前版本:"+VersionUtil.getApplicationVersionName(MyApplication.getContext()));
    }

    @Override
    public void cleckVersion() {
        HttpHelper.get(Urls.EDITION, null, new HttpResponseCallback() {
            @Override
            public void onSuccess(String datajson) {
                VersionBean data = JsonUtil.convertJsonToObject(datajson, VersionBean.class);
                if (data != null) {
                    int code = VersionUtil.getApplicationVersionCode(MyApplication.getContext());
//                    if (data.getVersion()>code){
                    if (true){
                        getView().showUpdateDialog(data.getURL());
                    }else {
                        getView().NOUpdate("你已是最新版本,暂时不需要升级");
                    }
                } else {
                    getView().updateFail("升级信息获取失败");
                }
            }

            @Override
            public void onFailed(int status, String message) {
                if (getView()!=null){
                    getView().updateFail(message);
                }
            }
        });
    }

    @Override
    public void toUpdate(String url) {
        HttpHelper.downloadFile(url, new DownLoadFileCallBack(getOutFilePath(), getFileName(url)) {
            @Override
            public void onComplete(File file) {
                getView().onDownSuccess(file);
            }

            @Override
            public void onDownloading(int progress) {
                getView().onDownStart("正在下载"+progress+"%");
            }

            @Override
            public void onDownloadFail(int code) {
                getView().onDownFail("下载失败");
            }
        });
    }

    public static String getOutFilePath() {
        String excelPath = Environment.getExternalStorageDirectory() + "/xjapks";
        File file = new File(excelPath);
        if (!file.exists())
            file.mkdir();

        return excelPath;
    }


    private String getFileName(String url) {
//        int endindex = url.lastIndexOf("/");
//        String file =UUID.randomUUID()+url.substring(endindex+1, url.length());
        return UUID.randomUUID()+".apk";
    }
}
