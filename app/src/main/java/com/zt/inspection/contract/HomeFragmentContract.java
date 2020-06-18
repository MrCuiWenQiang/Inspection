package com.zt.inspection.contract;

import com.zt.inspection.model.entity.view.HomeWorkBean;

import java.util.List;

public class HomeFragmentContract {
    public interface View {
        void showWorks(List<HomeWorkBean> datas);
    }

    public interface Presenter {
        void loadShowDatas();
    }

    public interface Model {
    }
}
