package com.zt.inspection.contract;


import com.zt.inspection.model.entity.response.PatrolSectionListBean;

import java.util.List;

public class PatrolSectionListContract {
    public interface View {
        void loadLoncalSuccess(List<PatrolSectionListBean> datas);
        void loadLoncalFail(String msg);
    }

    public interface Presenter {
        void loadLoncal();
    }

    public interface Model {
    }
}
