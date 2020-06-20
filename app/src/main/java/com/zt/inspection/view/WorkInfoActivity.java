package com.zt.inspection.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.zt.inspection.R;
import com.zt.inspection.contract.WorkInfoContract;
import com.zt.inspection.model.entity.response.WordInfoBean;
import com.zt.inspection.presenter.WorkInfoPresenter;
import com.zt.inspection.util.RoleIdUtil;
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
    private static final String INTENT_KEY_CSTATE = "INTENT_KEY_CSTATE";

    public static Intent newInstance(Context context,String id,String cstate) {
        Intent intent = new Intent(context,WorkInfoActivity.class);
        intent.putExtra(INTENT_KEY_WORK,id);
        intent.putExtra(INTENT_KEY_CSTATE,cstate);
        return intent;
    }

    private String id;
    private String cstate;

    private Button bt_addinfo;
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

        bt_addinfo = findViewById(R.id.bt_addinfo);
        rv_list = findViewById(R.id.rv_list);
        rv_list.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new WorkInfoAdapter();
        rv_list.setAdapter(adapter);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        showLoading();
        id = getIntent().getStringExtra(INTENT_KEY_WORK);
        cstate = getIntent().getStringExtra(INTENT_KEY_CSTATE);
        mPresenter.queryDatas(id);

        if (RoleIdUtil.isSHIGONG()&&cstate.equals("2")){
            bt_addinfo.setVisibility(View.GONE);
        }
    }

    @Override
    protected void initListener() {
        super.initListener();
        bt_addinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = AddHandleInfoActivity.toIntent(getContext(),id);
                startActivity(intent);
            }
        });
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
