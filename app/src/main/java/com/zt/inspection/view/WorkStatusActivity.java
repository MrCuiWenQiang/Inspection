package com.zt.inspection.view;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zt.inspection.R;
import com.zt.inspection.contract.WorkStatusContract;
import com.zt.inspection.model.entity.response.CaseInfoBean;
import com.zt.inspection.presenter.WorkStatusPresenter;
import com.zt.inspection.util.RoleIdUtil;
import com.zt.inspection.view.adapter.ImageAdapter;
import com.zt.inspection.view.adapter.OnLongListener;
import com.zt.inspection.view.adapter.VideoImageAdapter;
import com.zt.inspection.view.adapter.WorkAdapter;
import com.zt.inspection.view.dialog.DownLoadViewDialog;
import com.zt.inspection.view.dialog.PhotoDialog;

import java.util.List;

import cn.faker.repaymodel.mvp.BaseMVPAcivity;
import cn.faker.repaymodel.util.ToastUtility;
import cn.faker.repaymodel.widget.view.BaseRecycleView;

/**
 * 不同案件状态列表
 */
public class WorkStatusActivity extends BaseMVPAcivity<WorkStatusContract.View, WorkStatusPresenter> implements WorkStatusContract.View {

    public static final String INTENT_KEY_STATUS = "INTENT_KEY_STATUS";
    public static final String INTENT_KEY_TITLE = "INTENT_KEY_TITLE";

    public static Intent newInstance(Context context, String status, String name) {
        Intent intent = new Intent(context, WorkStatusActivity.class);
        intent.putExtra(INTENT_KEY_STATUS, status);
        intent.putExtra(INTENT_KEY_TITLE, name);
        return intent;
    }

    private RefreshLayout mRefreshLayout;
    private RecyclerView rv_list;
    private WorkAdapter adapter;
    private int page = 1;
    private String status;

    @Override
    protected int getLayoutContentId() {
        return R.layout.ac_workstatus;
    }

    @Override
    protected void initContentView() {
        rv_list = findViewById(R.id.rv_list);
        rv_list.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new WorkAdapter();
        rv_list.setAdapter(adapter);
        mRefreshLayout = findViewById(R.id.refreshLayout);
        mRefreshLayout.setEnableRefresh(true);//启用刷新
        mRefreshLayout.setEnableLoadmore(false);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        status = getIntent().getStringExtra(INTENT_KEY_STATUS);
        String name = getIntent().getStringExtra(INTENT_KEY_TITLE);

        setStatusBar(R.color.select_color);
        setTitle(name, R.color.white);
        setToolBarBackgroundColor(R.color.select_color);
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
        adapter.setOnItemClickListener(new BaseRecycleView.OnItemClickListener<CaseInfoBean>() {
            @Override
            public void onItemClick(View view, CaseInfoBean data, int position) {
                Intent intent = WorkInfoActivity.newInstance(getContext(),data.getCID(),data.getCSTATE(),data.getCASENUMBER());
                startActivity(intent);
            }
        });
        adapter.setOnLongListener(new OnLongListener<CaseInfoBean>() {
            @Override
            public void onlongListerer(int i, CaseInfoBean data) {
                if (status.equals("0")&&RoleIdUtil.isXUNJIAN()){
                    showListDialog(null, new String[]{"删除"}, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            showLoading();
                            mPresenter.delete(data.getCID());
                        }
                    });
                }
            }
        });

        adapter.setOnPhotoListener(new ImageAdapter.OnPhotoListener() {
            @Override
            public void onClick(int type, int postoin, Object data) {
                String videoPaths = (String) data;
                PhotoDialog videoDialog = new PhotoDialog();
                videoDialog.setUrl(videoPaths);
                videoDialog.show(getSupportFragmentManager(), "s");
            }
        });
        adapter.setOnVideoPhotoListener(new VideoImageAdapter.OnVideoPhotoListener() {
            @Override
            public void onClick(int type, int postoin, Object data) {
                String videoPaths = (String) data;
                DownLoadViewDialog downLoadViewDialog = new DownLoadViewDialog();
                downLoadViewDialog.setUrl(videoPaths);
                downLoadViewDialog.show(getSupportFragmentManager(),"v");
            }
        });
    }

    private void loadData() {
        mPresenter.loaddata(page, null, status);
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
    public void deleteSuccess() {
        page = 1;
        loadData();
    }

    @Override
    public void deleteFail(String message) {
        ToastUtility.showToast(message);
    }
}
