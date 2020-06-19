package com.zt.inspection.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.zt.inspection.R;
import com.zt.inspection.contract.WorkInfoContract;
import com.zt.inspection.model.entity.response.WordInfoBean;
import com.zt.inspection.presenter.WorkInfoPresenter;
import com.zt.inspection.view.adapter.WorkInfoAdapter;

import java.util.List;

import cn.faker.repaymodel.mvp.BaseMVPAcivity;
import cn.faker.repaymodel.util.ToastUtility;

/**
 * 案件记录
 */
public class WorkInfoActivity extends BaseMVPAcivity<WorkInfoContract.View, WorkInfoPresenter>
        implements WorkInfoContract.View {

    private static final String INTENT_KEY_WORK = "INTENT_KEY_WORK";

    public static Intent newInstance(Context context,String id) {
        Intent intent = new Intent(context,WorkInfoActivity.class);
        intent.putExtra(INTENT_KEY_WORK,id);
        return intent;
    }



    private RecyclerView rv_list;
    private WorkInfoAdapter adapter;

    @Override
    protected int getLayoutContentId() {
        return R.layout.ac_workinfo;
    }

    @Override
    protected void initContentView() {
        setStatusBar(R.color.select_color);
        setTitle("案件记录", R.color.white);
        setToolBarBackgroundColor(R.color.select_color);

        rv_list = findViewById(R.id.rv_list);
        rv_list.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new WorkInfoAdapter();
        rv_list.setAdapter(adapter);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        showLoading();
        mPresenter.queryDatas(getIntent().getStringExtra(INTENT_KEY_WORK));
    }

    @Override
    public void queryDatasFail(String message) {
        dimiss();
        ToastUtility.showToast(message);
    }

    @Override
    public void queryDatasSuccess(List<WordInfoBean> datas) {
        dimiss();
        adapter.setData(datas);
    }
}
