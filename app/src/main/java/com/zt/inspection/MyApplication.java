package com.zt.inspection;


import org.litepal.LitePal;

import cn.faker.repaymodel.BasicApplication;
import cn.faker.repaymodel.net.okhttp3.HttpHelper;
import cn.faker.repaymodel.util.LocImageUtility;
import cn.faker.repaymodel.util.LogUtil;
import cn.faker.repaymodel.util.ToastUtility;


public class MyApplication extends BasicApplication {

    private static MyApplication application;

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
	}


}
