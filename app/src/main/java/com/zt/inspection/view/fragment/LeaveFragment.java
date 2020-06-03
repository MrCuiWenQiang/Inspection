package com.zt.inspection.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zt.inspection.R;
import com.zt.inspection.contract.LeaveFragmentContract;
import com.zt.inspection.model.entity.response.LeaveListBean;
import com.zt.inspection.presenter.LeaveFragmentPresenter;
import com.zt.inspection.view.LeaveInfoActivity;
import com.zt.inspection.view.adapter.LeaveAdapter;

import java.util.List;

import cn.faker.repaymodel.mvp.BaseMVPAcivity;
import cn.faker.repaymodel.mvp.BaseMVPFragment;
import cn.faker.repaymodel.util.DateUtils;
import cn.faker.repaymodel.util.ToastUtility;

public class LeaveFragment extends BaseMVPFragment<LeaveFragmentContract.View, LeaveFragmentPresenter> implements LeaveFragmentContract.View, View.OnClickListener {
    private RefreshLayout mRefreshLayout;
    private RecyclerView rv_list;

    private LeaveAdapter adapter;

    private String date = DateUtils.getCurrentDate();

    public static LeaveFragment newInstance() {
        Bundle args = new Bundle();
        LeaveFragment fragment = new LeaveFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fg_leave;
    }

    @Override
    public void initview(View v) {
        adapter = new LeaveAdapter();
        rv_list = findViewById(R.id.rv_list);
        rv_list.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_list.setAdapter(adapter);
        mRefreshLayout = findViewById(R.id.refreshLayout);
        mRefreshLayout.setEnableRefresh(true);//启用刷新
        mRefreshLayout.setEnableLoadmore(false);
    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    private void load() {
        mPresenter.loadDatas(date, date);
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
        adapter.setListener(new LeaveAdapter.OnItemListener() {
            @Override
            public void onClick(int postoin, Object data) {
                LeaveListBean bean = (LeaveListBean) data;
                Bundle bundle = LeaveInfoActivity.newInstance(bean.getLEAVE_ID());
                Intent intent = new Intent(getContext(), LeaveFragment.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void loadDatas_Fail(String msg) {
        dimiss();
        ToastUtility.showToast(msg);
    }

    @Override
    public void loadDatas_success(List<LeaveListBean> datas) {
        dimiss();
        adapter.setDatas(datas);
    }

    @Override
    protected void dimiss() {
        super.dimiss();
        mRefreshLayout.finishRefresh();//完成刷新
    }

    @Override
    public boolean requestData() {
        showLoading();
        load();
        return false;
    }
}
