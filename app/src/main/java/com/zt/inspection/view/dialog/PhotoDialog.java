package com.zt.inspection.view.dialog;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.zt.inspection.R;
import com.zt.inspection.view.group.TouchImageView;

import cn.faker.repaymodel.net.loadimage.ImageLoadHelper;
import cn.faker.repaymodel.util.ScreenUtil;
import cn.faker.repaymodel.widget.view.dialog.BasicDialog;

public class PhotoDialog extends BasicDialog {
    private TextView tv_status;
    private TouchImageView vd_model;
    private String url;
    @Override
    public int getLayoutId() {
        return R.layout.dg_photodialog;
    }

    @Override
    public void initview(View v) {
        vd_model = v.findViewById(R.id.vd_model);
        tv_status = v.findViewById(R.id.tv_status);
    }
    public void setUrl(String url) {
        this.url = url;
    }
    @Override
    public void initData(Bundle savedInstanceState) {
        tv_status.setVisibility(View.VISIBLE);
        tv_status.setText("加载中");
        ImageLoadHelper.loadImage(getContext(), vd_model, url, new Callback() {
            @Override
            public void onSuccess() {
                vd_model.initImageView(getDialogWidth(), getDialogHeght());
                tv_status.setVisibility(View.GONE);
            }

            @Override
            public void onError() {
                tv_status.setText("加载失败");
            }
        });

        vd_model.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        vd_model.setOnEventClick(new TouchImageView.OnEventClick() {
            @Override
            public void onClick() {
                dismiss();
            }
        });

    }

    protected int getDialogWidth() {
        return ScreenUtil.getWindowWidth(getContext()) - 5;
    }

    protected int getDialogHeght() {
        return ScreenUtil.getWindowHeight(getContext()) - 10;

    }
}
