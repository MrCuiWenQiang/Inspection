package com.zt.inspection.contract;


import com.zt.inspection.model.entity.response.NoticeBean;

import java.util.List;

public class NoticeFragmentContract {
    public interface View {

        void loadData_Success(List<NoticeBean> noticeBeans);

        void loadData_Fail(String msg);
    }

    public interface Presenter {
        void loadData(int page, String type);
    }

    public interface Model {

    }
}
