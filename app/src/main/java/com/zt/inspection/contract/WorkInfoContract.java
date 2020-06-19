package com.zt.inspection.contract;

import com.zt.inspection.model.entity.response.WordInfoBean;

import java.util.List;

public class WorkInfoContract {
    public interface View {
        void queryDatasFail(String message);

        void queryDatasSuccess(List<WordInfoBean> datas);
    }

    public interface Presenter {
        void queryDatas(String id);
    }

    public interface Model {

    }
}
