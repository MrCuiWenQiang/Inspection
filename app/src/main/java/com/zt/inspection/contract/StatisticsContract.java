package com.zt.inspection.contract;


import com.zt.inspection.model.entity.response.HomeBean;

public class StatisticsContract {
    public interface View {
        void loadSuccess(int year,int month,HomeBean data);

        void loadFail(String message);
    }

    public interface Presenter {
        void loadData(int year,int month);
    }

    public interface Model {
    }
}
