package com.zt.inspection.contract;


import android.support.v4.app.Fragment;

import com.zt.inspection.model.entity.db.UserDBEntity;
import com.zt.inspection.model.entity.response.ClockInBean;
import com.zt.inspection.model.entity.view.MainTableBean;

import java.util.ArrayList;
import java.util.List;

public class WorkContract {
    public interface View {
        void settingTabs(List<MainTableBean> datas);
        void settingFragments(ArrayList<Fragment> fragments);
    }

    public interface Presenter {
        void giveTable();

        void giveFragments();

    }

    public interface Model {

    }
}
