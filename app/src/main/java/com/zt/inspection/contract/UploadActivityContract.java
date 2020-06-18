package com.zt.inspection.contract;


import com.zt.inspection.model.entity.request.WorkUpdateBean;
import com.zt.inspection.model.entity.response.CtypeBean;

import java.util.List;

public class UploadActivityContract {
    public interface View {


        void getCtype_Success(String[] strings, String[] strings1, List<CtypeBean> datas);

        void getCtype_Fail(String s);

        void uploadFileFail(String message);

        void uploadFileSuccess(String message);

        void uploadSuccess(String message);

        void uploadFail(String message);

        void getAddress_Success(String address);
        void getAddress_Fail(String message);
    }

    public interface Presenter {
        void getCtype();
        void queryAddress(double x,double y);
        void uploadFile(WorkUpdateBean workUpdateBean, List<String> photos, List<String> videos);
    }

    public interface Model {
    }
}
