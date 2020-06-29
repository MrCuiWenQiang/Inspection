package com.zt.inspection.view;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.zt.inspection.R;
import com.zt.inspection.Urls;
import com.zt.inspection.model.entity.response.NoticeBean;

import cn.faker.repaymodel.activity.BaseToolBarActivity;

public class WebActivity extends BaseToolBarActivity {
    private WebView wb_content;
    private static final String KEY_WEB = "KEY_WEB";
    private static final String KEY_WEB_ISURL = "KEY_WEB_ISURL";
    public static Intent newInstance(Context context, NoticeBean data) {
        return newInstance(context, data, false);
    }

    public static Intent newInstance(Context context, NoticeBean data, boolean isUrl) {

        Intent intent = new Intent(context, WebActivity.class);
        intent.putExtra(KEY_WEB, data);
        intent.putExtra(KEY_WEB_ISURL, isUrl);
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

//        Boolean isUrl =  getIntent().getBooleanExtra(KEY_WEB_ISURL,false);
        wb_content.getSettings().setJavaScriptEnabled(true); // 启用js
        wb_content.getSettings().setBlockNetworkImage(false); // 解决图片不显示
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            wb_content.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        wb_content.loadUrl(Urls.NOTICEDETAILS+data.getNID());
//        wb_content.loadData(Html.fromHtml(data.getCONTENT()).toString(), "text/html", "UTF-8");
    }
}
