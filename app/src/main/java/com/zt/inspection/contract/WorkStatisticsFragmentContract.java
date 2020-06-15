package com.zt.inspection.contract;


import com.zt.inspection.model.entity.response.WorkDeptBean;
import com.zt.inspection.model.entity.response.WorkUserBean;

import java.util.List;

public class WorkStatisticsFragmentContract {
    public interface View {
        void getListDataSuccess(List<WorkUserBean> data);

        void getListDataFail(String message);

        void getManualDataSuccess(WorkDeptBean data);

        void getManualDataFail(String message);
    }

    public interface Presenter {
        void search(String startDate,String endDate);//判断统计应该是部门还是个人
        void getListData(String startDate,String endDate);//个人统计
        void getManualData(String startDate,String endDate);//部门统计
    }

    public interface Model {

    }
}
