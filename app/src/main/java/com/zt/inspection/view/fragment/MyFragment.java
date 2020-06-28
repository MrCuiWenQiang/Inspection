package com.zt.inspection.view.fragment;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.widget.QMUIFloatLayout;
import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView;
import com.qmuiteam.qmui.widget.grouplist.QMUIGroupListView;
import com.zt.inspection.R;
import com.zt.inspection.contract.MyFragmentContract;
import com.zt.inspection.presenter.MyFragmentPresenter;
import com.zt.inspection.view.BranchModelActivity;
import com.zt.inspection.view.LeaveActivity;
import com.zt.inspection.view.UserInfoActivity;

import cn.faker.repaymodel.mvp.BaseMVPFragment;
import cn.faker.repaymodel.util.UiTools;


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

    private String[] item_one_name = new String[]{ "修改密码","通讯录"};
    private int[] one_ids = new int[]{ R.id.one_2, R.id.one_3};
    private String[] item_two_name = new String[]{"版本更新"};
    private int[] two_ids = new int[]{R.id.two_1};
    private String[] item_three_name = new String[]{"意见反馈", "使用说明"};
    private int[] three_ids = new int[]{R.id.three_1, R.id.three_2};


//    private String[] top_name = new String[]{"请假"};
//    private int[] top_ids = new int[]{R.id.top_1};
//    private int[] top_images = new int[]{R.mipmap.leave};

    private QMUIGroupListView qmui_gl;
    private QMUIFloatLayout mFloatLayout;

    private int tvSize;
    private int imSize;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.one_1: {
//                toAcitvity(UserInfoActivity.class);
                break;
            }
            case R.id.one_2: {
                break;
            } case R.id.one_3: {
                toAcitvity(BranchModelActivity.class);
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
            case R.id.top_1: {//请假
                toAcitvity(LeaveActivity.class);
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
        mFloatLayout = findViewById(R.id.qmui_floatlayout);
        mFloatLayout.setGravity(Gravity.LEFT);
        mFloatLayout.setMaxNumber(3);
        mFloatLayout.setChildHorizontalSpacing(12);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        tvSize = QMUIDisplayHelper.sp2px(getContext(), 4);
//        imSize =  QMUIDisplayHelper.dp2px(getContext(), 45);
/*        for (int i = 0; i < top_name.length; i++) {
            setTOP(top_name[i], top_images[i], top_ids[i]);
        }*/
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

    private void setTOP(String name, int image, int id) {
        LinearLayout ll = new LinearLayout(getContext());
        ll.setId(id);
        ll.setOnClickListener(this);
        ll.setOrientation(LinearLayout.VERTICAL);
//        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(imSize, imSize);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.CENTER;
        ll.setLayoutParams(layoutParams);
        ImageView imageView = new ImageView(getContext());
        TextView tv = new TextView(getContext());
        LinearLayout.LayoutParams tvParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        tv.setLayoutParams(tvParams);
        tv.setGravity(Gravity.CENTER);
        imageView.setBackground(ContextCompat.getDrawable(getContext(), image));
        tv.setText(name);
        tv.setTextSize(tvSize);
        ll.addView(imageView);
        ll.addView(tv);
        mFloatLayout.addView(ll);
    }
}
