package com.zt.inspection.contract;

import com.zt.inspection.model.entity.response.CaseInfoBean;

import java.util.List;

public class WorkStatusContract {
    public interface View {

        void loadSuccess(List<CaseInfoBean> datas);

        void loadFail(String message);

        void deleteSuccess();

        void deleteFail(String message);
    }

    public interface Presenter {
        /**
         *
         * @param page
         * @param bumenid 有些领导可以选择部门查看 后期在完善
         */
        void loaddata(int page, String bumenid, String status);

        void delete(String cid);
    }

    public interface Model {
    }
}
