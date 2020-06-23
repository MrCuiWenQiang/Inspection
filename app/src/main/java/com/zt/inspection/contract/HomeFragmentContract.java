package com.zt.inspection.contract;

import com.zt.inspection.model.entity.view.HomeWorkBean;

import java.util.List;

public class HomeFragmentContract {
    public interface View {
        void showWorks(List<HomeWorkBean> datas);

        void showTab(int c);
    }

    public interface Presenter {
        void loadShowDatas();
        void loadHomeDatas();
        void loadLoncal();
        void loadWorkData();
    }

    public interface Model {
    }
}
