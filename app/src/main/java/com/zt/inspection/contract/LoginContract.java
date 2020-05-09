package com.zt.inspection.contract;


import com.zt.inspection.model.entity.db.UserDBEntity;
import com.zt.inspection.model.entity.response.LoginBean;

public class LoginContract {
    public interface View {
        void login_Success();
        void login_Fail(String msg);
        void settingUser(String name, String password);
    }

    public interface Presenter {
        void giveUserInfo();
        void login(String value, String value1, boolean checked);
    }

    public interface Model {
        void saveUser(String userName,String pw, LoginBean data);

        void findUserByLimit( final OnFindUser onFindUser);

        void cleanAll();

        interface OnFindUser {
            void onSuccess(UserDBEntity data);
            void onfail();
        }
    }
}
