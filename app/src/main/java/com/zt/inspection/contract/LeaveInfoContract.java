package com.zt.inspection.contract;


import com.zt.inspection.model.entity.response.LeaveInfoBean;

public class LeaveInfoContract {
    public interface View {

        void loadDatas_Fail(String msg);

        void loadDatas_Success(LeaveInfoBean bean);

        void adopt_fail(String message);

        void reject_fail(String message);
    }

    public interface Presenter {
        void loadData(String id);

        void showOpen();//根据用户类型判断是否显示该界面

        //通过
        void adopt(String audit_id, String leave_id, String AUDIT_INFO);

        //拒接
        void reject(String audit_id, String leave_id, String AUDIT_INFO);
    }

    public interface Model {
    }
}
