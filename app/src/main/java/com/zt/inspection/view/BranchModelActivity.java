package com.zt.inspection.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;
import com.zt.inspection.R;
import com.zt.inspection.contract.BranchModelContract;
import com.zt.inspection.model.entity.response.BranchBean;
import com.zt.inspection.presenter.BranchModelPresenter;
import com.zt.inspection.view.adapter.BranchModelAdapter;
import com.zt.inspection.view.group.IndexBar;

import java.util.HashMap;
import java.util.List;

import cn.faker.repaymodel.mvp.BaseMVPAcivity;
import cn.faker.repaymodel.widget.view.BaseRecycleView;

/**
 * 通讯录
 */
public class BranchModelActivity extends BaseMVPAcivity<BranchModelContract.View, BranchModelPresenter>
        implements BranchModelContract.View {

    private RecyclerView rv_list;
    private IndexBar view_indexbar;
    private BranchModelAdapter adapter;
    private LinearLayoutManager layoutManager;

    private HashMap<String, Integer> indexmap;

    @Override
    protected int getLayoutContentId() {
        return R.layout.ac_branchmodel;
    }

    @Override
    protected void initContentView() {
        setStatusBar(R.color.select_color);
        setTitle("通讯录", R.color.white);
        setToolBarBackgroundColor(R.color.select_color);

        rv_list = findViewById(R.id.rv_list);
        view_indexbar = findViewById(R.id.view_indexbar);
        layoutManager = new LinearLayoutManager(getContext());
        rv_list.setLayoutManager(layoutManager);
        adapter = new BranchModelAdapter();
        rv_list.setAdapter(adapter);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        showLoading();
        mPresenter.queryData();
    }

    @Override
    protected void initListener() {
        super.initListener();
        view_indexbar.setOnIndexChangedListener(new IndexBar.OnIndexChangedListener() {
            @Override
            public void onIndexChanged(String index, boolean isDown) {
                if (indexmap.containsKey(index)) {
                    layoutManager.scrollToPositionWithOffset(indexmap.get(index), 0);
                    layoutManager.setStackFromEnd(true);
                }
            }
        });
        adapter.setOnItemClickListener(new BaseRecycleView.OnItemClickListener<BranchBean>() {
            @Override
            public void onItemClick(View view, BranchBean data, int position) {
                Intent intent = new Intent(Intent.ACTION_CALL);
                Uri uri = Uri.parse("tel:" + data.getSIMCODE());
                intent.setData(uri);
                startActivity(intent);
            }
        });
    }

    @Override
    public void queryDataFail(String msg) {
        dimiss();
        showDialog(msg, new QMUIDialogAction.ActionListener() {
            @Override
            public void onClick(QMUIDialog dialog, int index) {
                dialog.dismiss();
                finish();
            }
        });
    }

    @Override
    public void queryDataSuccess(List<BranchBean> orderDatas, HashMap map) {
        dimiss();
        indexmap = map;
        adapter.setDatas(orderDatas);
    }
}
