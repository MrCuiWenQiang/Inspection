package com.zt.inspection.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zt.inspection.R;
import com.zt.inspection.contract.HistoryRouteFragmentContract;
import com.zt.inspection.model.entity.response.SectionBean;
import com.zt.inspection.presenter.HistoryRouteFragmentPresenter;
import com.zt.inspection.view.PatrolRouteActivity;
import com.zt.inspection.view.adapter.HistoryRouteAdapter;

import java.util.List;

import cn.faker.repaymodel.mvp.BaseMVPFragment;
import cn.faker.repaymodel.util.ToastUtility;
import cn.faker.repaymodel.widget.view.BaseRecycleView;

/**
 * 巡检路线列表
 */
public class HistoryRouteFragment extends BaseMVPFragment<HistoryRouteFragmentContract.View, HistoryRouteFragmentPresenter>
        implements HistoryRouteFragmentContract.View {

    public static HistoryRouteFragment newInstance() {
        Bundle args = new Bundle();
        HistoryRouteFragment fragment = new HistoryRouteFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private int page = 1;

    private RefreshLayout mRefreshLayout;
    private RecyclerView rv_list;
    private HistoryRouteAdapter adapter;

    @Override
    public int getLayoutId() {
        return R.layout.fg_historyroute;
    }

    @Override
    public void initview(View v) {
        rv_list = findViewById(R.id.rv_list);
        rv_list.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new HistoryRouteAdapter();
        rv_list.setAdapter(adapter);
        mRefreshLayout = findViewById(R.id.refreshLayout);
        mRefreshLayout.setEnableRefresh(true);//启用刷新
        mRefreshLayout.setEnableLoadmore(false);
    }

    @Override
    protected void initListener() {
        super.initListener();
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                page = 1;
                loadData();
            }
        });
        mRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                ++page;
                loadData();
            }
        });
        adapter.setOnItemClickListener(new BaseRecycleView.OnItemClickListener<SectionBean>() {
            @Override
            public void onItemClick(View view, SectionBean data, int position) {
                Intent intent = PatrolRouteActivity.toIntent(getContext(), data.getPATROLSECTIONID());
                startActivity(intent);
            }
        });
    }


    @Override
    public boolean requestData() {
        showLoading();
        loadData();
        return false;
    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    private void loadData() {
        mPresenter.loadDatas(page, null);
    }

    @Override
    public void loadSuccess(List<SectionBean> datas) {
        dimiss();
        if (datas != null && datas.size() > 0) {
            mRefreshLayout.setEnableLoadmore(true);
            if (page == 1) {
                adapter.setDatas(null);
            }
            adapter.setDatas(datas);
        } else {
            ToastUtility.showToast("没有更多数据了");
            mRefreshLayout.setEnableLoadmore(false);
        }
    }

    @Override
    public void loadFail(String message) {
        dimiss();
        ToastUtility.showToast(message);
        mRefreshLayout.setEnableLoadmore(false);
    }


    @Override
    protected void dimiss() {
        super.dimiss();
        mRefreshLayout.finishRefresh();//完成刷新
        mRefreshLayout.finishLoadmore();//完成刷新
    }

}
