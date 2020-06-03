package com.zt.inspection.contract;



public class WorkStatisticsFragmentContract {
    public interface View {
    }

    public interface Presenter {
        void getListData(String startDate,String endDate);//个人统计
        void getManualData(String startDate,String endDate);//部门统计
    }

    public interface Model {

    }
}
