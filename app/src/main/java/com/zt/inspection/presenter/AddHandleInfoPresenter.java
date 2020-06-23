package com.zt.inspection.presenter;


import android.os.Handler;
import android.os.Looper;

import com.zt.inspection.MyApplication;
import com.zt.inspection.Urls;
import com.zt.inspection.contract.AddHandleInfoContract;
import com.zt.inspection.model.entity.request.AddHandleInfoEntity;

import java.util.ArrayList;
import java.util.List;

import cn.faker.repaymodel.mvp.BaseMVPPresenter;
import cn.faker.repaymodel.net.okhttp3.HttpHelper;
import cn.faker.repaymodel.net.okhttp3.callback.HttpResponseCallback;

public class AddHandleInfoPresenter extends BaseMVPPresenter<AddHandleInfoContract.View> implements AddHandleInfoContract.Presenter {
    private  boolean isLoading = false;
    private boolean isError = false;


    private List<String> updateUrl = new ArrayList<>();
    private List<String> updateVideoUrl = new ArrayList<>();

    @Override
    public void uploadFile(AddHandleInfoEntity entity, List<String> photos, List<String> videos) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                int sum_p = 0;
                int sum_v = 0;
                sum_p = photos.size();
                sum_v = videos.size();
                boolean isUpload = true;
                isError = false;
                while (isUpload) {
                    if (isError) {
                        break;//遇到错误不再上传
                    }
                    if (isLoading) {
                        continue;//单个文件上传完成才继续
                    }
                    if (sum_p - 1 != -1) {
                        sum_p = sum_p - 1;
                        String path = photos.get(sum_p);
                        sendMessage(200,"上传图片文件中");
                        uploadFile(1,path);
                    } else if (sum_v - 1 != -1) {
                        sum_v = sum_v - 1;
                        String path = videos.get(sum_v);
                        sendMessage(200,"上传视频文件中");
                        uploadFile(2,path);
                    }else {
                        break;//图片视频都没有
                    }
                }
                if (isError){
                    sendMessage(400,"上传图片失败,请检查网络连接");
                }else {
                    uploadTxt(entity);
                }
            }
        }).start();
    }

    private void sendMessage(int type,String message){
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                if (type!=200){
                    getView().uploadFileFail(message);
                }else {
                    getView().uploadFileSuccess(message);
                }
            }
        });
    }



    public void uploadFile(int type,String filePath) {
        isLoading = true;
        HttpHelper.post_file(Urls.UPLOAD, filePath, new HttpResponseCallback() {
            @Override
            public void onSuccess(String result) {
                if (type==1){
                    updateUrl.add(result);
                }else {
                    updateVideoUrl.add(result);
                }
                isLoading = false;
            }

            @Override
            public void onFailed(int status, String message) {
                updateUrl.clear();
                isError = true;
                isLoading = false;
            }
        });
    }

    private void uploadTxt(AddHandleInfoEntity workUpdateBean){
        workUpdateBean.setSGHIMAGES(listtoString(updateUrl));
        workUpdateBean.setSGHVIDEO(listtoString(updateVideoUrl));
        workUpdateBean.setHANDLEUID(MyApplication.loginUser.getPATROLCODE());
        workUpdateBean.setDEPTID(MyApplication.loginUser.getDEPARTID());
        HttpHelper.post(Urls.AddHandleInfo, workUpdateBean, new HttpResponseCallback() {
            @Override
            public void onSuccess(String datajson) {
                getView().uploadSuccess("上报成功");
            }

            @Override
            public void onFailed(int status, String message) {
                getView().uploadFail(message);
            }
        });
    }
    private String listtoString(List<String> list){
        if (list==null||list.size()<=0){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (String value:list ) {
            sb.append(value);
            sb.append(",");
        }
        return sb.subSequence(0,sb.length()-1).toString();
    }
}
