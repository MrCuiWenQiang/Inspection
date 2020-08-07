package com.zt.inspection.presenter;


import android.os.Handler;
import android.os.Looper;

import com.esri.core.geometry.Point;
import com.esri.core.tasks.geocode.Locator;
import com.esri.core.tasks.geocode.LocatorReverseGeocodeResult;
import com.zt.inspection.MyApplication;
import com.zt.inspection.Urls;
import com.zt.inspection.contract.UploadActivityContract;
import com.zt.inspection.model.Params;
import com.zt.inspection.model.entity.request.WorkUpdateBean;
import com.zt.inspection.model.entity.response.BaiDuLocalBean;
import com.zt.inspection.model.entity.response.CtypeBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.faker.repaymodel.mvp.BaseMVPPresenter;
import cn.faker.repaymodel.net.json.JsonUtil;
import cn.faker.repaymodel.net.okhttp3.HttpHelper;
import cn.faker.repaymodel.net.okhttp3.callback.HttpCallback;
import cn.faker.repaymodel.net.okhttp3.callback.HttpResponseCallback;
import cn.faker.repaymodel.util.db.DBThreadHelper;
import cn.faker.repaymodel.util.error.ErrorUtil;


public class UploadPresenter extends BaseMVPPresenter<UploadActivityContract.View> implements UploadActivityContract.Presenter {
    private  boolean isLoading = false;
    private boolean isError = false;


    private List<String> updateUrl = new ArrayList<>();
    private List<String> updateVideoUrl = new ArrayList<>();

    @Override
    public void getCtype() {
        HttpHelper.get(Urls.GETLEIXIN, null, new HttpResponseCallback() {
            @Override
            public void onSuccess(String datajson) {
                List<CtypeBean> datas = JsonUtil.fromList(datajson, CtypeBean.class);
                if (datas == null || datas.size() <= 0) {
                    getView().getCtype_Fail("未获取到问题类型,请联系后台");
                } else {
                    List<String> ids = new ArrayList<>();
                    List<String> names = new ArrayList<>();
                    for (CtypeBean item : datas) {
                        ids.add(item.getCID());
                        names.add(item.getCNAME());
                    }
                    getView().getCtype_Success(ids.toArray(new String[]{}), names.toArray(new String[]{}), datas);
                }
            }

            @Override
            public void onFailed(int status, String message) {
                if (getView() != null) {
                    getView().getCtype_Fail(message);
                }
            }
        });
    }

    @Override
    public void uploadFile(WorkUpdateBean workUpdateBean, List<String> photos, List<String> videos) {
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
                    clearUrl();
                    sendMessage(400,"上传图片失败,请检查网络连接");
                }else {
                    uploadTxt(workUpdateBean);
                }
            }
        }).start();

    }


    private void clearUrl(){
        updateUrl.clear();
        updateVideoUrl.clear();
    }

    private void uploadTxt(WorkUpdateBean workUpdateBean){
        workUpdateBean.setCSTATE("0");
        workUpdateBean.setUPUID(MyApplication.loginUser.getPATROLCODE());
        workUpdateBean.setDEPTID(MyApplication.loginUser.getDEPARTID());
        workUpdateBean.setUPUNAME(MyApplication.loginUser.getPATROLNAME());
        workUpdateBean.setSGQIMAGES(listtoString(updateUrl));
        workUpdateBean.setSGQVIDEO(listtoString(updateVideoUrl));
        HttpHelper.post(Urls.ADDINFO, workUpdateBean, new HttpResponseCallback() {
            @Override
            public void onSuccess(String datajson) {
                clearUrl();
                getView().uploadSuccess("上报成功");
            }

            @Override
            public void onFailed(int status, String message) {
                clearUrl();
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

    public void queryAddress(double x,double y)  {
        HashMap<String, Object> params = new HashMap<>();
        params.put("ak", Params.BAIDU_AK);
        params.put("output", "json");
        params.put("coordtype", "wgs84ll");
        params.put("location", x+","+y);
        HttpHelper.get(Urls.BAIDUREVERSE_GEOCODING, params, new HttpCallback() {

            @Override
            public void onSuccess(String result) {
                BaiDuLocalBean bean = JsonUtil.convertJsonToObject(result,BaiDuLocalBean.class);
                if (bean!=null){
                    if (bean.getStatus()==0){
                        getView().getAddress_Success(bean.getResult().getFormatted_address());
                    }else {
                        getView().getAddress_Fail("暂时没有地址描述:地址反编码服务错误");
                    }
                }else {
                    getView().getAddress_Fail("暂时没有地址描述：地址反编码服务出现问题");
                }
            }

            @Override
            public void onFailed(int status, String message) {
            getView().getAddress_Fail("暂时没有地址描述");

            }
        });



      /*  DBThreadHelper.startThreadInPool(new DBThreadHelper.ThreadCallback< Map<String, String>>() {
            @Override
            protected  Map<String, String> jobContent() throws Exception {
                Map<String, String>  mvl = null;
                try {
                    Locator al = Locator.createOnlineLocator(Urls.addressURL);
                    LocatorReverseGeocodeResult result =al.reverseGeocode(new Point(x,y),500);
                    mvl = result.getAddressFields();

                }catch (Exception e){
                    ErrorUtil.showError(e);
                    return  null;
                }
                return mvl;
            }

            @Override
            protected void jobEnd( Map<String, String> o) {
                if (o == null){
                    getView().getAddress_Fail("暂时没有地址描述");
                }else {
                    getView().getAddress_Success("....");
                }
            }
        });*/
    }
}
