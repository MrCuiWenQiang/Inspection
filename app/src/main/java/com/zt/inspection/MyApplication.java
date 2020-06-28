package com.zt.inspection;


import com.zt.inspection.model.entity.db.UserDBEntity;
import com.zt.inspection.model.entity.response.LoginBean;

import org.litepal.LitePal;

import cn.faker.repaymodel.BasicApplication;
import cn.faker.repaymodel.net.okhttp3.HttpHelper;
import cn.faker.repaymodel.util.LocImageUtility;
import cn.faker.repaymodel.util.LogUtil;
import cn.faker.repaymodel.util.PreferencesUtility;
import cn.faker.repaymodel.util.ToastUtility;


public class MyApplication extends BasicApplication {

    private static MyApplication application;

	public static LoginBean loginUser=null;//登录后的用户信息，不再提供工具类

    public static MyApplication getInstance() {
        return application;
    }
	@Override
	public void onCreate() {
		super.onCreate();
		init();
	}

	private void init(){

		ToastUtility.setToast(this);
		LitePal.initialize(this);
		LogUtil.isShow =true;
		LocImageUtility.setImageUtility(this);
		HttpHelper.init();
		PreferencesUtility.setPreferencesUtility(getApplicationContext(),"testManager");
	}


}
