package com.zt.inspection.contract;


import com.zt.inspection.model.entity.db.UserDBEntity;
import com.zt.inspection.model.entity.response.LoginBean;

public class UserInfoContract {
    public interface View {
    }

    public interface Presenter {
    }

    public interface Model {

        interface OnFindUser {
            void onSuccess(UserDBEntity data);
            void onfail();
        }
    }
}
