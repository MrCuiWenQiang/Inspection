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
import com.zt.inspection.contract.HistoryWorkFragmentContract;
import com.zt.inspection.model.entity.response.CaseInfoBean;
import com.zt.inspection.presenter.HistoryWorkFragmentPresenter;
import com.zt.inspection.view.WorkInfoActivity;
import com.zt.inspection.view.adapter.HistoryWorkAdapter;
import com.zt.inspection.view.adapter.ImageAdapter;
import com.zt.inspection.view.adapter.OnLongListener;
import com.zt.inspection.view.adapter.VideoImageAdapter;
import com.zt.inspection.view.dialog.DownLoadViewDialog;
import com.zt.inspection.view.dialog.PhotoDialog;

import java.util.List;

import cn.faker.repaymodel.mvp.BaseMVPFragment;
import cn.faker.repaymodel.util.ToastUtility;
import cn.faker.repaymodel.widget.view.BaseRecycleView;

/**
 * 案件历史
 */
public class HistoryWorkFragment extends BaseMVPFragment<HistoryWorkFragmentContract.View, HistoryWorkFragmentPresenter> implements HistoryWorkFragmentContract.View {

    public static HistoryWorkFragment newInstance() {
        Bundle args = new Bundle();
        HistoryWorkFragment fragment = new HistoryWorkFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private RefreshLayout mRefreshLayout;
    private RecyclerView rv_list;
    private HistoryWorkAdapter adapter;
    private int page = 1;

    @Override
    public int getLayoutId() {
        return R.layout.fg_historywork;
    }

    @Override
    public void initview(View v) {
        rv_list = findViewById(R.id.rv_list);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setAutoMeasureEnabled(true);
        rv_list.setLayoutManager(manager);
        adapter = new HistoryWorkAdapter();
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
        adapter.setOnItemClickListener(new BaseRecycleView.OnItemClickListener<CaseInfoBean>() {
            @Override
            public void onItemClick(View view, CaseInfoBean data, int position) {
                Intent intent = WorkInfoActivity.newInstance(getContext(),data.getCID(),data.getCSTATE());
                startActivity(intent);
            }
        });
        adapter.setOnPhotoListener(new ImageAdapter.OnPhotoListener() {
            @Override
            public void onClick(int type, int postoin, Object data) {
                String videoPaths = (String) data;
                PhotoDialog videoDialog = new PhotoDialog();
                videoDialog.setUrl(videoPaths);
                videoDialog.show(getChildFragmentManager(), "s");
            }
        });
        adapter.setOnVideoPhotoListener(new VideoImageAdapter.OnVideoPhotoListener() {
            @Override
            public void onClick(int type, int postoin, Object data) {
                String videoPaths = (String) data;
                DownLoadViewDialog downLoadViewDialog = new DownLoadViewDialog();
                downLoadViewDialog.setUrl(videoPaths);
                downLoadViewDialog.show(getChildFragmentManager(),"v");
            }
        });
     /*   adapter.setOnLongListener(new OnLongListener<CaseInfoBean>() {
            @Override
            public void onlongListerer(int i, CaseInfoBean data) {

            }
        });*/
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        showLoading();
        loadData();
    }

/*    @Override
    public boolean requestData() {
        showLoading();
        loadData();
        return false;
    }*/

    private void loadData() {
        mPresenter.loaddata(page, null);
    }


    @Override
    protected void dimiss() {
        super.dimiss();
        mRefreshLayout.finishRefresh();//完成刷新
        mRefreshLayout.finishLoadmore();//完成刷新
    }

    @Override
    public void loadSuccess(List<CaseInfoBean> datas) {
        dimiss();
        if (datas!=null&&datas.size()>0){
            mRefreshLayout.setEnableLoadmore(true);
            if (page==1){
                adapter.setDatas(null);
            }
            adapter.setDatas(datas);
        }else {
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
}
