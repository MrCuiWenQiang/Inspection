package com.zt.inspection.presenter;


import com.zt.inspection.MyApplication;
import com.zt.inspection.Urls;
import com.zt.inspection.contract.WorkStatisticsFragmentContract;
import com.zt.inspection.model.entity.response.ClockInBean;

import java.util.HashMap;

import cn.faker.repaymodel.mvp.BaseMVPPresenter;
import cn.faker.repaymodel.net.json.JsonUtil;
import cn.faker.repaymodel.net.okhttp3.HttpHelper;
import cn.faker.repaymodel.net.okhttp3.callback.HttpResponseCallback;

public class WorkStatisticsPresenter extends BaseMVPPresenter<WorkStatisticsFragmentContract.View> implements WorkStatisticsFragmentContract.Presenter {


    @Override
    public void getListData(String startDate, String endDate) {

    }

    @Override
    public void getManualData(String startDate, String endDate) {

    }
}
