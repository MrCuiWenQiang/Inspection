package com.zt.inspection.contract;


import com.zt.inspection.model.entity.response.ClockInBean;

public class WorkFragmentContract {
    public interface View {
        void GetCLOCKINList_Success(ClockInBean bean);
        void GetCLOCKINList_Fail(String msg);

        void AddCLOCKIN_fail(String message);

        void AddCLOCKIN_success(int status, String v);
    }

    public interface Presenter {
        void GetCLOCKINList();//获取打卡所需信息
        void AddCLOCKIN(String SIGNDESC, String OUTTIME);//打卡

        void isInArea(double x, double y);//定位位置校验

        void todayInfo();//获取当天的打卡信息
    }

    public interface Model {

    }
}
