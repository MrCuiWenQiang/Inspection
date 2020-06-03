package com.zt.inspection.presenter;


import android.support.v4.app.Fragment;

import com.zt.inspection.R;
import com.zt.inspection.contract.WorkContract;
import com.zt.inspection.model.entity.view.MainTableBean;
import com.zt.inspection.view.fragment.WorkFragment;
import com.zt.inspection.view.fragment.WorkStatisticsFragment;

import java.util.ArrayList;
import java.util.List;

import cn.faker.repaymodel.mvp.BaseMVPPresenter;

public class WorkPresenter extends BaseMVPPresenter<WorkContract.View> implements WorkContract.Presenter {

    @Override
    public void giveTable() {
        List<MainTableBean> datas = new ArrayList<>();
        datas.add(new MainTableBean( R.mipmap.select_false_map,R.mipmap.select_true_map,"打卡"));
        datas.add(new MainTableBean( R.mipmap.select_false_tak,R.mipmap.select_true_tak,"统计"));
//        datas.add(new MainTableBean( R.mipmap.select_false_ls,R.mipmap.select_true_ls,"上报"));
//        datas.add(new MainTableBean( R.mipmap.bottom_user_gray,R.mipmap.bottom_user_green,"我的"));
        getView().settingTabs(datas);
    }

    @Override
    public void giveFragments() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(WorkFragment.newInstance());
        fragments.add(WorkStatisticsFragment.newInstance());
        getView().settingFragments(fragments);
    }

}
