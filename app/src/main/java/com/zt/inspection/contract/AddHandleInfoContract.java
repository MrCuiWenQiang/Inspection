package com.zt.inspection.contract;



import com.zt.inspection.model.entity.request.AddHandleInfoEntity;

import java.util.List;

public class AddHandleInfoContract {
    public interface View {

        void uploadFileFail(String message);

        void uploadFileSuccess(String message);

        void uploadSuccess(String message);

        void uploadFail(String message);
    }

    public interface Presenter {
        void uploadFile(AddHandleInfoEntity entity, List<String> photos, List<String> videos);
    }

    public interface Model {
    }
}
