package com.zt.inspection.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.webkit.WebView;

import com.zt.inspection.R;
import com.zt.inspection.model.entity.response.NoticeBean;

import cn.faker.repaymodel.activity.BaseToolBarActivity;

public class WebActivity extends BaseToolBarActivity {
    private WebView wb_content;
    private static final String KEY_WEB = "KEY_WEB";

    public static Intent newInstance(Context context, NoticeBean data) {

        Intent intent = new Intent(context,WebActivity.class);
        intent.putExtra(KEY_WEB,data);
        return intent;
    }

    @Override
    protected int getLayoutContentId() {
        return R.layout.ac_web;
    }

    @Override
    protected void initContentView() {
        setStatusBar(R.color.select_color);
        setTitle("消息详情", R.color.white);
        setToolBarBackgroundColor(R.color.select_color);

        wb_content = findViewById(R.id.wb_content);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        NoticeBean data = (NoticeBean) getIntent().getSerializableExtra(KEY_WEB);
        wb_content.loadData(Html.fromHtml(data.getCONTENT()).toString(), "text/html", "UTF-8");
    }
}
