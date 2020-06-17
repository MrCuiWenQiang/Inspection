package com.zt.inspection.contract;

import com.zt.inspection.model.entity.response.PatrikRouteBean;

import java.util.List;

public class PatrolRouteContract {
    public interface View {
        void queryDataSuccess(List<PatrikRouteBean> datas);

        void queryDataFinsh(String message);
    }

    public interface Presenter {
        void queryData(String id);
    }

    public interface Model {

    }
}
