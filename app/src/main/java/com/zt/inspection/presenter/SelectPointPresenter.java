package com.zt.inspection.presenter;


import com.zt.inspection.Urls;
import com.zt.inspection.contract.SelectPointContract;
import com.zt.inspection.model.Params;
import com.zt.inspection.model.entity.response.BaiDuLocalBean;

import java.util.HashMap;

import cn.faker.repaymodel.mvp.BaseMVPPresenter;
import cn.faker.repaymodel.net.json.JsonUtil;
import cn.faker.repaymodel.net.okhttp3.HttpHelper;
import cn.faker.repaymodel.net.okhttp3.callback.HttpCallback;

public class SelectPointPresenter extends BaseMVPPresenter<SelectPointContract.View> implements SelectPointContract.Presenter {
    public void queryAddress(double x,double y)  {
        HashMap<String, Object> params = new HashMap<>();
        params.put("ak", Params.BAIDU_AK);
        params.put("output", "json");
        params.put("coordtype", "BD09ll");
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
