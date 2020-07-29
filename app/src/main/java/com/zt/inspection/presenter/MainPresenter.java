package com.zt.inspection.presenter;


import android.support.v4.app.Fragment;

import com.zt.inspection.MyApplication;
import com.zt.inspection.R;
import com.zt.inspection.contract.MainContract;
import com.zt.inspection.model.entity.view.MainTableBean;
import com.zt.inspection.util.RoleIdUtil;
import com.zt.inspection.view.fragment.HistoryFragment;
import com.zt.inspection.view.fragment.HomeFragment;
import com.zt.inspection.view.fragment.MapFragment;
import com.zt.inspection.view.fragment.MyFragment;
import com.zt.inspection.view.fragment.StatisticsFragment;
import com.zt.inspection.view.fragment.UploadFragment;

import java.util.ArrayList;
import java.util.List;

import cn.faker.repaymodel.mvp.BaseMVPPresenter;

public class MainPresenter extends BaseMVPPresenter<MainContract.View> implements MainContract.Presenter {

    public void initpush(){
    }
    @Override
    public void giveTable() {
        List<MainTableBean> datas = new ArrayList<>();
        datas.add(new MainTableBean( R.mipmap.select_false_map,R.mipmap.select_true_map,"首页"));
        datas.add(new MainTableBean( R.mipmap.select_false_ls,R.mipmap.select_true_ls,"历史"));
        if (RoleIdUtil.isXUNJIAN()||RoleIdUtil.isManager()||RoleIdUtil.isBUMEN()){
            datas.add(new MainTableBean( R.mipmap.select_false_tak,R.mipmap.select_true_tak,"巡检"));
            datas.add(new MainTableBean( R.mipmap.select_false_we,R.mipmap.select_true_we,"上报"));
        }
        datas.add(new MainTableBean( R.mipmap.bottom_user_gray,R.mipmap.bottom_user_green,"我的"));
        getView().settingTabs(datas);
    }

    @Override
    public void giveFragments() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        if (RoleIdUtil.isManager()){
            fragments.add(StatisticsFragment.newInstance());
        }else {
            fragments.add(HomeFragment.newInstance());
        }

        fragments.add(HistoryFragment.newInstance());
        if (RoleIdUtil.isXUNJIAN()||RoleIdUtil.isManager()||RoleIdUtil.isBUMEN()){
            fragments.add(MapFragment.newInstance());
            fragments.add(UploadFragment.newInstance());
        }
        fragments.add(MyFragment.newInstance());
        getView().settingFragments(fragments);
    }
}
