package com.zt.inspection.view;

import android.os.Bundle;
import android.view.View;

import com.zt.inspection.R;
import com.zt.inspection.contract.BranchModelContract;
import com.zt.inspection.presenter.BranchModelPresenter;

import cn.faker.repaymodel.mvp.BaseMVPAcivity;

/**
 * 通讯录
 */
public class BranchModelActivity extends BaseMVPAcivity<BranchModelContract.View, BranchModelPresenter> implements BranchModelContract.View, View.OnClickListener {

    @Override
    public void onClick(View v) {

    }

    @Override
    protected int getLayoutContentId() {
        return R.layout.ac_branchmodel;
    }

    @Override
    protected void initContentView() {

    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }
}
