package com.zt.inspection.contract;


import com.zt.inspection.model.entity.response.PatrolSectionBean;

public class MapFragmentContract {
    public interface View {

        void startLine_Success(PatrolSectionBean data);

        void startLine_Fail(String msg);

        void endLine_Fail(String msg);

        void endLine_Success();

        void showEditDialog(String s);
    }

    public interface Presenter {
        /**
         * 巡查路段打卡
         * @param name 巡查名称
         */
        void  startLine(double x,double y);
        void endLine(String id);
        void uploadLocal(double lat,double lon ,String id);
    }

    public interface Model {
    }
}
