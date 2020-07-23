package com.zt.inspection.contract;


import java.io.File;

public class UpdateContract {
    public interface View {

        void showVersionName(String applicationVersionName);

        void showUpdateDialog(String url);

        void NOUpdate(String s);

        void updateFail(String msg);

        void onDownStart(String msg);

        void onDownFail(String msg);

        void onDownSuccess(File file);
    }

    public interface Presenter {
        void initVersion();
        void cleckVersion();

        void toUpdate(String url);
    }

    public interface Model {
    }
}
