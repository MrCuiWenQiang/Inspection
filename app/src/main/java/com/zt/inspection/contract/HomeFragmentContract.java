package com.zt.inspection.contract;

import com.zt.inspection.model.entity.response.CaseInfoBean;
import com.zt.inspection.model.entity.response.PatrolRlistBean;
import com.zt.inspection.model.entity.response.PatrolSectionListBean;
import com.zt.inspection.model.entity.view.HomeWorkBean;

import java.util.List;

public class HomeFragmentContract {
    public interface View {
        void showWorks(List<HomeWorkBean> datas);

        void showTab(int c);

        void loadWorkDataSuccess(List<CaseInfoBean> datas);

        void loadWorkDataFail(String message);

        void loadLoncal(List<PatrolSectionListBean> itemDatas);
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
