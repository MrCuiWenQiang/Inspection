package com.zt.inspection.presenter;


import com.zt.inspection.Urls;
import com.zt.inspection.contract.UploadActivityContract;

import java.util.List;

import cn.faker.repaymodel.mvp.BaseMVPPresenter;
import cn.faker.repaymodel.net.okhttp3.HttpHelper;
import cn.faker.repaymodel.net.okhttp3.callback.HttpCallback;
import cn.faker.repaymodel.util.LogUtil;

public class UploadPresenter extends BaseMVPPresenter<UploadActivityContract.View> implements UploadActivityContract.Presenter {


    @Override
    public void uploadFile(List<String> photos, List<String> videos) {

    }

    public void uploadFile(String filePath){
        HttpHelper.post_file(Urls.UPLOAD, filePath, new HttpCallback() {
            @Override
            public void onSuccess(String result) {
                LogUtil.e("dddid",result);
            }

            @Override
            public void onFailed(int status, String message) {
                LogUtil.e("ddd",message);
            }
        });
    }

}
