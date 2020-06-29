package com.zt.inspection.contract;


import com.zt.inspection.model.entity.response.ClockInBean;
import com.zt.inspection.model.entity.response.TodayBean;

import java.util.List;

public class WorkFragmentContract {
    public interface View {

        void GetCLOCKINList_Fail(String msg);

        void AddCLOCKIN_fail(String message);

        void AddCLOCKIN_success(int status, String v);

        void todayinfo_visible();//今日未打卡界面显示

        void todayinfo_gone(List<TodayBean> datas); //今日已打卡的界面显示

        void todayinfo_final(String message);//信息获取失败界面显示
    }

    public interface Presenter {
        void GetCLOCKINList();//获取打卡所需信息
        void AddCLOCKIN(String remarks,Double x,Double y);//打卡


        boolean isInArea(double x, double y);//定位位置校验

        void todayInfo();//获取当天的打卡信息
    }

    public interface Model {

    }
}
