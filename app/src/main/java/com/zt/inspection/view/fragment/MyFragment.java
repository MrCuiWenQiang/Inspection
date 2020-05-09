package com.zt.inspection.view.fragment;


import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView;
import com.qmuiteam.qmui.widget.grouplist.QMUIGroupListView;
import com.zt.inspection.R;
import com.zt.inspection.contract.MyFragmentContract;
import com.zt.inspection.presenter.MyFragmentPresenter;

import cn.faker.repaymodel.mvp.BaseMVPFragment;


/**
 * Map地图展示Fragment
 */
public class MyFragment extends BaseMVPFragment<MyFragmentContract.View, MyFragmentPresenter> implements MyFragmentContract.View, View.OnClickListener {

    public static MyFragment newInstance() {
        Bundle args = new Bundle();
        MyFragment fragment = new MyFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private String[] item_one_name = new String[]{"用户信息", "修改密码"};
    private int[] one_ids = new int[]{R.id.one_1, R.id.one_2};
    private String[] item_two_name = new String[]{"版本更新"};
    private int[] two_ids = new int[]{R.id.two_1};
    private String[] item_three_name = new String[]{"意见反馈", "使用说明"};
    private int[] three_ids = new int[]{R.id.three_1, R.id.three_2};


    private QMUIGroupListView qmui_gl;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.one_1: {

                break;
            }
            case R.id.one_2: {
                break;
            }
            case R.id.two_1: {
                break;
            }
            case R.id.two_2: {
                break;
            }
            case R.id.two_3: {
                break;
            }

            case R.id.three_1: {
                break;
            }
            case R.id.three_2: {
                break;
            }
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.ac_my;
    }

    @Override
    public void initview(View v) {
        qmui_gl = findViewById(R.id.qmui_gl);

    }

    @Override
    public void initData(Bundle savedInstanceState) {
        settingGroup("用户相关", QMUICommonListItemView.HORIZONTAL, QMUICommonListItemView.ACCESSORY_TYPE_NONE, item_one_name, null, one_ids);
        settingGroup("资源管理", QMUICommonListItemView.VERTICAL, QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON, item_two_name, null, two_ids);
        settingGroup("帮助相关", QMUICommonListItemView.VERTICAL, QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON, item_three_name, null, three_ids);
    }

    private void settingGroup(String title, @QMUICommonListItemView.QMUICommonListItemOrientation int orientation, @QMUICommonListItemView.QMUICommonListItemAccessoryType int type, String[] name, String[] describe, int[] ids) {
        QMUIGroupListView.Section section = QMUIGroupListView.newSection(getContext()).setTitle(title);
        for (int i = 0; i < name.length; i++) {
            QMUICommonListItemView listItemView = qmui_gl.createItemView(name[i]);
            listItemView.setId(ids[i]);
            if (describe != null) {
                listItemView.setDetailText(describe[i]);
            }
            listItemView.setOrientation(orientation);
            listItemView.setAccessoryType(type);
            section.addItemView(listItemView, this);
        }
        section.addTo(qmui_gl);
    }
}
