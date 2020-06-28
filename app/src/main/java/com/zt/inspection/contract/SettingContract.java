package com.zt.inspection.contract;



public class SettingContract {
    public interface View {
        void getHttpSettingSuccess(String ip,String port);
        void fail(String msg);
        void settingSetting_success(String msg);
    }

    public interface Presenter {
        void getHttpSetting();
        void settingHttpSetting(String ip_address, String port);
    }

    public interface Model {

    }
}
