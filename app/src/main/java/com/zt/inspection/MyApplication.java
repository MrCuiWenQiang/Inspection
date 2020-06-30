package com.zt.inspection;


import android.support.multidex.MultiDex;

import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
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
        MultiDex.install(this);
		ToastUtility.setToast(this);
		LitePal.initialize(this);
		LogUtil.isShow =true;
		LocImageUtility.setImageUtility(this);
		HttpHelper.init();
		PreferencesUtility.setPreferencesUtility(getApplicationContext(),"testManager");

		//在使用SDK各组件之前初始化context信息，传入ApplicationContext
		SDKInitializer.initialize(this);
		//自4.3.0起，百度地图SDK所有接口均支持百度坐标和国测局坐标，用此方法设置您使用的坐标类型.
		//包括BD09LL和GCJ02两种坐标，默认是BD09LL坐标。
		SDKInitializer.setCoordType(CoordType.BD09LL);
	}


}
