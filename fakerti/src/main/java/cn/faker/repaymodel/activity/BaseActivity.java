package cn.faker.repaymodel.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;

import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;

import cn.faker.repaymodel.R;
import cn.faker.repaymodel.activity.interface_ac.BasicActivity;

/**
 * Activity的基类
 * Created by Mr.C on 2017/11/1 0001.
 */

public abstract class BaseActivity extends BaseManagerActivity implements BasicActivity {

    private boolean isload = false;
    protected final String TAG = this.getClass().getSimpleName();

    private QMUITipDialog tipDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initWindow();
        setContentView(getLayoutId());
        initView();
        initData(savedInstanceState);
        initListener();
    }
    protected void initWindow(){

    }
    @Override
    protected void onStart() {
        super.onStart();
        if (!isload) {
            initData();
            isload = !isload;
        }
    }

    protected void initData() {

    }

    /**
     * 在此方法内设置控件监听
     */
    protected void initListener() {

    }

    protected void toAcitvity(Class<?> cls,int requestCode) {
        Intent intent = new Intent(this, cls);
        startActivityForResult(intent,requestCode);
    }


    protected void toAcitvity(Class<?> cls) {
        Intent intent = new Intent(this, cls);
        startActivity(intent);
    }
    protected void showListDialog(String title, String[] items, DialogInterface.OnClickListener listener) {
        new QMUIDialog.CheckableDialogBuilder(this).setTitle(title)
                .addItems(items, listener)
                .show();
    }
    protected void showDialog(String title,String msg) {
        new QMUIDialog.MessageDialogBuilder(this).setTitle(title).setMessage(msg).addAction("确定", new QMUIDialogAction.ActionListener() {
            @Override
            public void onClick(QMUIDialog dialog, int index) {
                dialog.dismiss();
            }
        }).show();
    }
    protected void showDialog(String title,String msg, QMUIDialogAction.ActionListener actionListener) {
        new QMUIDialog.MessageDialogBuilder(this).setTitle(title).setMessage(msg).addAction("确定", actionListener).show();
    }
    protected void showDialog(String msg) {
        showDialog(msg,true);
    }
    protected void showDialog(String msg,boolean iscan) {
        new QMUIDialog.MessageDialogBuilder(this).setMessage(msg).setCancelable(iscan).addAction("确定", new QMUIDialogAction.ActionListener() {
            @Override
            public void onClick(QMUIDialog dialog, int index) {
                dialog.dismiss();
            }
        }).show();
    }

    protected void showDialog(String msg,boolean iscan, QMUIDialogAction.ActionListener actionListener) {
        new QMUIDialog.MessageDialogBuilder(this).setMessage(msg).setCancelable(iscan).addAction("确定", actionListener).show();
    }
    protected void showDialog(String msg, QMUIDialogAction.ActionListener actionListener) {
        showDialog(msg,true,actionListener);
    }

    protected String getEditTextValue(EditText editText){
        if (editText==null){
            return null;
        }
        return editText.getText().toString();
    }
    protected String getValue(EditText text) {
        if (TextUtils.isEmpty(text.getText())) {
            return null;
        } else {
            return text.getText().toString();
        }
    }
    protected String getValue(CharSequence text) {
        if (TextUtils.isEmpty(text)) {
            return null;
        } else {
            return text.toString();
        }
    }
    protected String getValue(TextView text) {
        if (text==null||  TextUtils.isEmpty(text.getText())){
            return null;

        }
        return text.getText().toString();
    }
    protected String getValue(Object text) {
        if (text==null||  !(text instanceof String)){
            return null;

        }
        return text.toString();
    }
    protected void showLoading() {
        dimiss();
        showLoading(null);
    }
    protected void showLoading(String message) {
        if (tipDialog==null){
            tipDialog = new QMUITipDialog.CustomBuilder(this)
                    .setContent(R.layout.dialog_loading)
                    .create();
            tipDialog.setCancelable(false);
            tipDialog.show();
        }
        if (message!=null){
            TextView tv = tipDialog.findViewById(R.id.tv_content);
            tv.setText(message);
        }

    }

    protected void dimiss() {
        if (tipDialog != null) {
            tipDialog.dismiss();
            tipDialog = null;
        }
    }
}
