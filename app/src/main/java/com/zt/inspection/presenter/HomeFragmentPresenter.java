package com.zt.inspection.presenter;


import android.text.TextUtils;

import com.zt.inspection.MyApplication;
import com.zt.inspection.R;
import com.zt.inspection.Urls;
import com.zt.inspection.contract.HomeFragmentContract;
import com.zt.inspection.model.entity.response.CaseInfoBean;
import com.zt.inspection.model.entity.response.PatrolRlistBean;
import com.zt.inspection.model.entity.response.PatrolSectionListBean;
import com.zt.inspection.model.entity.view.HomeWorkBean;
import com.zt.inspection.util.RoleIdUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.faker.repaymodel.mvp.BaseMVPPresenter;
import cn.faker.repaymodel.net.json.JsonUtil;
import cn.faker.repaymodel.net.okhttp3.HttpHelper;
import cn.faker.repaymodel.net.okhttp3.callback.HttpResponseCallback;
import cn.faker.repaymodel.util.DateUtils;

// TODO: 2020/6/23 巡检人员主页展示当日路线地图 
// TODO: 2020/6/23 施工人员展示施工中的任务
public class HomeFragmentPresenter extends BaseMVPPresenter<HomeFragmentContract.View> implements HomeFragmentContract.Presenter {

    @Override
    public void loadShowDatas() {
        List<HomeWorkBean> tabs = new ArrayList<>();
        if (RoleIdUtil.isBUMEN() || RoleIdUtil.isManager()) {
            tabs.add(new HomeWorkBean("已上报", null, R.mipmap.main_rw_dzx, "0"));
            tabs.add(new HomeWorkBean("施工中", null, R.mipmap.main_rw_update, "1"));
            tabs.add(new HomeWorkBean("施工完", null, R.mipmap.main_rw_bj, "2"));
            tabs.add(new HomeWorkBean("已完结", null, R.mipmap.main_rw_ybj, "4"));

        } else if (RoleIdUtil.isXUNJIAN()) {
            tabs.add(new HomeWorkBean("全 部", null, R.mipmap.main_rw_dzx, "-1"));
            tabs.add(new HomeWorkBean("待执行", null, R.mipmap.main_rw_update, "0"));
            tabs.add(new HomeWorkBean("施工完", null, R.mipmap.main_rw_bj, "2"));
            tabs.add(new HomeWorkBean("已完结", null, R.mipmap.main_rw_ybj, "4"));
        } else {
            tabs.add(new HomeWorkBean("全 部", null, R.mipmap.main_rw_dzx, "-1"));
            tabs.add(new HomeWorkBean("施工中", null, R.mipmap.main_rw_update, "1"));
            tabs.add(new HomeWorkBean("施工完", null, R.mipmap.main_rw_bj, "2"));
            tabs.add(new HomeWorkBean("已完结", null, R.mipmap.main_rw_ybj, "4"));
        }
        if (RoleIdUtil.isBUMEN() || RoleIdUtil.isManager()) {
            getView().showTab(R.mipmap.c);
        } else if (RoleIdUtil.isXUNJIAN()) {
            getView().showTab(R.mipmap.c);
        } else if (RoleIdUtil.isSHIGONG()) {
            getView().showTab(R.mipmap.b);
        } else {
            getView().showTab(R.mipmap.a);
        }


        getView().showWorks(tabs);
        loadHomeDatas();
    }

    @Override
    public void loadHomeDatas() {
       if (RoleIdUtil.isSHIGONG()) {
            loadWorkData();
//        }else   if (RoleIdUtil.isXUNJIAN()) {
        }else   {
           loadLoncal();
       }
    }

    //获取当日巡检路线并画图userId，Start，End，DEPARTID，RoleId，DEPARTLEADER
    @Override
    public void loadLoncal() {
        HashMap<String, Object> params = new HashMap<>();
        params.put("DEPARTLEADER", MyApplication.loginUser.getDEPARTLEADER());
        params.put("DEPARTID", MyApplication.loginUser.getDEPARTID());
        params.put("RoleId", MyApplication.loginUser.getRoleids());
        params.put("userId", MyApplication.loginUser.getPATROLCODE());
        params.put("Start", DateUtils.getCurrentDate());
        params.put("End", DateUtils.getCurrentDate());
        HttpHelper.get(Urls.GETPATROLSECTIONLIST, params, new HttpResponseCallback() {
            @Override
            public void onSuccess(String datajson) {
                List<PatrolSectionListBean> datas = JsonUtil.fromList(datajson, PatrolSectionListBean.class);
                if (datas!=null&&datas.size()>0){
                    if (getView() != null) {
                        List<PatrolRlistBean> itemDatas = datas.get(0).getRList();
                        getView().loadLoncal(itemDatas);
                    }
                }else {
                    getView().loadLoncal(null);
                }
            }

            @Override
            public void onFailed(int status, String message) {
                if (getView() != null) {
                    getView().loadLoncal(null);
                }
            }
        });
    }

    @Override
    public void loadWorkData() {
        HashMap<String, Object> params = new HashMap<>();
        params.put("DEPARTLEADER", MyApplication.loginUser.getDEPARTLEADER());
        params.put("RoleId", MyApplication.loginUser.getRoleids());
        params.put("userId", MyApplication.loginUser.getPATROLCODE());
        params.put("bumenid", MyApplication.loginUser.getDEPARTID());
        params.put("page", 1);
        params.put("limit", 3);
        params.put("CSTATE", "1");
        params.put("SFSG", RoleIdUtil.isSHIGONG() ? "1" : "0");
        params.put("strWhere", "");
        params.put("CDateDateTime", "");
        params.put("CloseDateTime", "");
        HttpHelper.get(Urls.GETCASEINFOLIST, params, new HttpResponseCallback() {
            @Override
            public void onSuccess(String datajson) {
                List<CaseInfoBean> datas = JsonUtil.fromList(datajson, CaseInfoBean.class);
                if (datas!=null&&datas.size()>0){
                    if (getView() != null) {
                        getView().loadWorkDataSuccess(datas);
                    }
                }else {
                    getView().loadWorkDataFail("暂无施工中的任务");
                }
            }

            @Override
            public void onFailed(int status, String message) {
                if (getView() != null) {
                    getView().loadWorkDataFail(message);
                }
            }
        });
    }
}
