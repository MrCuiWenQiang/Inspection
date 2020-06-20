package com.zt.inspection.view.dialog;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.zt.inspection.R;

import cn.faker.repaymodel.net.loadimage.ImageLoadHelper;
import cn.faker.repaymodel.util.ScreenUtil;
import cn.faker.repaymodel.widget.view.dialog.BasicDialog;

public class PhotoDialog extends BasicDialog {
    private ImageView vd_model;
    private String url;
    @Override
    public int getLayoutId() {
        return R.layout.dg_photodialog;
    }

    @Override
    public void initview(View v) {
        vd_model = v.findViewById(R.id.vd_model);
    }
    public void setUrl(String url) {
        this.url = url;
    }
    @Override
    public void initData(Bundle savedInstanceState) {
        ImageLoadHelper.loadImage(getContext(), vd_model, url);
        vd_model.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
