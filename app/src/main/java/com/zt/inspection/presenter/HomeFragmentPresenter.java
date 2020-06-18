package com.zt.inspection.presenter;


import com.zt.inspection.R;
import com.zt.inspection.contract.HomeFragmentContract;
import com.zt.inspection.model.entity.view.HomeWorkBean;
import com.zt.inspection.util.RoleIdUtil;

import java.util.ArrayList;
import java.util.List;

import cn.faker.repaymodel.mvp.BaseMVPPresenter;

public class HomeFragmentPresenter extends BaseMVPPresenter<HomeFragmentContract.View> implements HomeFragmentContract.Presenter {

    @Override
    public void loadShowDatas() {
        List<HomeWorkBean> tabs = new ArrayList<>();
        if (RoleIdUtil.isBUMEN()||RoleIdUtil.isManager()){
            tabs.add(new HomeWorkBean("已上报",null, R.mipmap.main_rw_dzx,"0"));
            tabs.add(new HomeWorkBean("施工中",null, R.mipmap.main_rw_update,"1"));
            tabs.add(new HomeWorkBean("施工完",null, R.mipmap.main_rw_bj,"2"));
            tabs.add(new HomeWorkBean("已完结",null, R.mipmap.main_rw_ybj,"4"));

        }else if (RoleIdUtil.isXUNJIAN()){
            tabs.add(new HomeWorkBean("全 部",null, R.mipmap.main_rw_dzx,""));
            tabs.add(new HomeWorkBean("待执行",null, R.mipmap.main_rw_update,"1"));
            tabs.add(new HomeWorkBean("施工完",null, R.mipmap.main_rw_bj,"2"));
            tabs.add(new HomeWorkBean("已完结",null, R.mipmap.main_rw_ybj,"4"));
        }else {
            tabs.add(new HomeWorkBean("待分派",null, R.mipmap.main_rw_dzx,"0"));
            tabs.add(new HomeWorkBean("施工中",null, R.mipmap.main_rw_update,"1"));
            tabs.add(new HomeWorkBean("施工完",null, R.mipmap.main_rw_bj,"2"));
            tabs.add(new HomeWorkBean("已完结",null, R.mipmap.main_rw_ybj,"4"));
        }
        getView().showWorks(tabs);
    }
}
