package com.zt.inspection.contract;


import com.zt.inspection.model.entity.response.BranchBean;

import java.util.HashMap;
import java.util.List;

public class BranchModelContract {
    public interface View {

        void queryDataFail(String msg);

        void queryDataSuccess(List<BranchBean> orderDatas, HashMap map);
    }

    public interface Presenter {
        void queryData();
    }

    public interface Model {
    }
}
