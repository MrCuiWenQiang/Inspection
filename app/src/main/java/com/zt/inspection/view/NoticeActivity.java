package com.zt.inspection.view;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zt.inspection.R;
import com.zt.inspection.contract.NoticeContract;
import com.zt.inspection.model.entity.response.NoticeBean;
import com.zt.inspection.presenter.NoticePresenter;
import com.zt.inspection.view.adapter.NoticeAdapter;

import java.util.List;

import cn.faker.repaymodel.mvp.BaseMVPAcivity;
import cn.faker.repaymodel.util.DateUtils;
import cn.faker.repaymodel.util.ToastUtility;
import cn.qqtheme.framework.picker.DatePicker;
import cn.qqtheme.framework.util.ConvertUtils;

/*
 *公告
 */
public class NoticeActivity extends BaseMVPAcivity<NoticeContract.View, NoticePresenter> implements NoticeContract.View, View.OnClickListener {

    private RecyclerView rv_list;
    private NoticeAdapter adapter = new NoticeAdapter();
    private String date = DateUtils.getCurrentDate();
    private RefreshLayout mRefreshLayout;

    int year = DateUtils.getYear();
    int month = DateUtils.getMonth();
    int day = DateUtils.getDay();

    @Override
    protected int getLayoutContentId() {
        return R.layout.ac_notice;
    }

    @Override
    protected void initContentView() {
        setStatusBar(R.color.select_color);
        setTitle("通知公告", R.color.white);
        setToolBarBackgroundColor(R.color.select_color);

        setRightBtn("选择日期", R.color.white, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onYearMonthDayPicker();
            }
        });

        rv_list = findViewById(R.id.rv_list);
        rv_list.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        rv_list.setAdapter(adapter);
        mRefreshLayout = findViewById(R.id.refreshLayout);
        mRefreshLayout.setEnableRefresh(true);//启用刷新
        mRefreshLayout.setEnableLoadmore(false);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
//        showLoading();
        load();
    }

    @Override
    protected void initListener() {
        super.initListener();
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                load();
            }
        });
    }

    private void load() {
        mPresenter.loadData(null, date, null);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void loadData_Success(List<NoticeBean> noticeBeans) {
        dimiss();
        adapter.setData(noticeBeans);
    }

    @Override
    public void loadData_Fail(String msg) {
        dimiss();
        ToastUtility.showToast(msg);
    }

    public void onYearMonthDayPicker() {

        final DatePicker picker = new DatePicker(this);
        picker.setCanceledOnTouchOutside(true);
        picker.setUseWeight(true);
        picker.setTopPadding(ConvertUtils.toPx(this, 10));
        picker.setRangeEnd( DateUtils.getYear() , DateUtils.getMonth(), DateUtils.getDay());
        picker.setRangeStart(DateUtils.getYear()-10, 1, 1);
        picker.setSelectedItem(year, month, day);
        picker.setResetWhileWheel(false);
        picker.setOnDatePickListener(new DatePicker.OnYearMonthDayPickListener() {
            @Override
            public void onDatePicked(String syear, String smonth, String sday) {
                 year = Integer.valueOf(syear);
                 month = Integer.valueOf(smonth);
                 day =Integer.valueOf(sday);
                date = syear + "-" + smonth + "-" + sday;
                load();
            }
        });
        picker.setOnWheelListener(new DatePicker.OnWheelListener() {
            @Override
            public void onYearWheeled(int index, String year) {
                picker.setTitleText(year + "-" + picker.getSelectedMonth() + "-" + picker.getSelectedDay());
            }

            @Override
            public void onMonthWheeled(int index, String month) {
                picker.setTitleText(picker.getSelectedYear() + "-" + month + "-" + picker.getSelectedDay());
            }

            @Override
            public void onDayWheeled(int index, String day) {
                picker.setTitleText(picker.getSelectedYear() + "-" + picker.getSelectedMonth() + "-" + day);
            }
        });
        picker.show();
    }

    @Override
    protected void dimiss() {
        super.dimiss();
        mRefreshLayout.finishRefresh();//完成刷新
    }
}
