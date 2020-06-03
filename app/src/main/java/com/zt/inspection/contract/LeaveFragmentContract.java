package com.zt.inspection.contract;


import com.zt.inspection.model.entity.response.LeaveListBean;

import java.util.List;

public class LeaveFragmentContract {
    public interface View {
        void loadDatas_Fail(String msg);

        void loadDatas_success(List<LeaveListBean> datas);
    }

    public interface Presenter {
        void loadDatas(String start, String end);

    }

    public interface Model {
    }
}
