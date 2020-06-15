package com.zt.inspection.presenter;


import android.support.v4.app.Fragment;

import com.zt.inspection.R;
import com.zt.inspection.contract.MainContract;
import com.zt.inspection.model.entity.view.MainTableBean;
import com.zt.inspection.view.fragment.HomeFragment;
import com.zt.inspection.view.fragment.MapFragment;
import com.zt.inspection.view.fragment.MyFragment;
import com.zt.inspection.view.fragment.UploadFragment;

import java.util.ArrayList;
import java.util.List;

import cn.faker.repaymodel.mvp.BaseMVPPresenter;

public class MainPresenter extends BaseMVPPresenter<MainContract.View> implements MainContract.Presenter {


    @Override
    public void giveTable() {
        List<MainTableBean> datas = new ArrayList<>();
        datas.add(new MainTableBean( R.mipmap.select_false_map,R.mipmap.select_true_map,"首页"));
        datas.add(new MainTableBean( R.mipmap.select_false_tak,R.mipmap.select_true_tak,"巡检"));
        datas.add(new MainTableBean( R.mipmap.select_false_ls,R.mipmap.select_true_ls,"上报"));
        datas.add(new MainTableBean( R.mipmap.bottom_user_gray,R.mipmap.bottom_user_green,"我的"));
        getView().settingTabs(datas);
    }

    @Override
    public void giveFragments() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(HomeFragment.newInstance());
        fragments.add(MapFragment.newInstance());
        fragments.add(UploadFragment.newInstance());
        fragments.add(MyFragment.newInstance());
        getView().settingFragments(fragments);
    }
}
