package com.zt.inspection.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zt.inspection.R;
import com.zt.inspection.contract.NoticeFragmentContract;
import com.zt.inspection.model.entity.response.NoticeBean;
import com.zt.inspection.presenter.NoticeFragmentPresenter;
import com.zt.inspection.view.WebActivity;
import com.zt.inspection.view.adapter.NoticeAdapter;

import java.util.List;

import cn.faker.repaymodel.mvp.BaseMVPFragment;
import cn.faker.repaymodel.util.ToastUtility;

public class NoticeFragment extends BaseMVPFragment<NoticeFragmentContract.View, NoticeFragmentPresenter> implements NoticeFragmentContract.View, View.OnClickListener {

    private static final String KEY_TYPE = "KEY_TYPE";
    private RecyclerView rv_list;
    private NoticeAdapter adapter = new NoticeAdapter();
    private RefreshLayout mRefreshLayout;
    private int page = 1;
    private String type;

    public static NoticeFragment newInstance(String type) {
        Bundle args = new Bundle();
        args.putString(KEY_TYPE, type);
        NoticeFragment fragment = new NoticeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fg_notice;
    }

    @Override
    public void initview(View v) {
        rv_list = findViewById(R.id.rv_list);
        rv_list.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_list.setAdapter(adapter);
        mRefreshLayout = findViewById(R.id.refreshLayout);
        mRefreshLayout.setEnableRefresh(true);//启用刷新
        mRefreshLayout.setEnableLoadmore(false);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        type = getArguments().getString(KEY_TYPE);
        showLoading();
        loadData();
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
        adapter.setListener(new NoticeAdapter.OnItemListener() {
            @Override
            public void onClick(int postoin, Object data) {
                NoticeBean bean = (NoticeBean) data;
                if (TextUtils.isEmpty(bean.getCONTENT())){
                    ToastUtility.showToast("这条消息没有详情");
                    return;
                }
                Intent intent = WebActivity.newInstance(getContext(), (NoticeBean) data);
                startActivity(intent);
            }
        });
    }

    private void loadData() {
        mPresenter.loadData(page, type);
    }
    @Override
    public void loadData_Success(List<NoticeBean> noticeBeans) {
        dimiss();
        if (page==1){
            adapter.setData(null);
        }
        adapter.setData(noticeBeans);
        mRefreshLayout.setEnableLoadmore(true);
    }

    @Override
    public void loadData_Fail(String msg) {
        dimiss();
        ToastUtility.showToast(msg);
        mRefreshLayout.setEnableLoadmore(false);
    }

    @Override
    protected void dimiss() {
        super.dimiss();
        mRefreshLayout.finishRefresh();//完成刷新
        mRefreshLayout.finishLoadmore();//完成刷新
    }
}
