package com.zt.inspection.contract;


import com.zt.inspection.model.entity.response.SectionBean;

import java.util.List;

public class HistoryRouteFragmentContract {
    public interface View {

        void loadFail(String message);

        void loadSuccess(List<SectionBean> datas);
    }

    public interface Presenter {

        void loadDatas(int page,String departid);

    }

    public interface Model {
    }
}
