package com.zt.inspection.util;

import java.io.File;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;

/*** APK工具类 */
public class VersionUtil {

	/**
	 * 安装APK
	 */
	public static void installApk(String apkFile, Context context) {
		File apkfile = new File(apkFile);
		if (!apkfile.exists()) {
			return;
		}
		File file = new File(apkFile);
		Intent intent = new Intent();
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.setAction(Intent.ACTION_VIEW); // 浏览网页的Action(动作)
		String type = "application/vnd.android.package-archive";
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
			intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
			Uri uri = FileProvider.getUriForFile(context, context.getPackageName() + ".fileprovider", file);
			intent.setDataAndType(uri, type);
		} else {
			intent.setDataAndType(Uri.fromFile(file), type); // 设置数据类型
		}

		context.startActivity(intent);
	}

	/**
	 * 获取程序的版本信息 展示给用户
	 * 
	 * @return
	 */
	public static String getApplicationVersionName(Context context) {
		try {
			// 获取packagemanager的实例
			PackageManager packageManager = context.getPackageManager();
			// getPackageName()是你当前类的包名，0代表是获取版本信息
			PackageInfo packInfo = packageManager.getPackageInfo(
					context.getPackageName(), 0);
			String version = packInfo.versionName;
			return version;
		} catch (Exception e) {
			e.printStackTrace();
			return "0";
		}
	}

	/**
	 * 用于自行判断
	 * @param context
	 * @return
	 */
	public static int getApplicationVersionCode(Context context) {
		try {
			// 获取packagemanager的实例
			PackageManager packageManager = context.getPackageManager();
			// getPackageName()是你当前类的包名，0代表是获取版本信息
			PackageInfo packInfo = packageManager.getPackageInfo(
					context.getPackageName(), 0);
			int version = packInfo.versionCode;
			return version;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	/**
	 * 获取程序的包名
	 * 
	 * @return
	 */
	public static String getApplicationPackageName(Context context) {
		try {
			return context.getPackageName();  
	//		return "com.wewell.sofetypass";//因为接口是安监通的。测试所以需要返回安监通包名
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Result postStreamFromUrl(String path) throws Exception {
		URL url = new URL(path);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setConnectTimeout(5 * 1000);
		conn.setRequestMethod("GET");
		InputStream inStream = null;
		if (conn.getContentType().contains("text")) {
			throw new HeadErrorException("文件不存在");
		}
		// 设置session
		if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
			inStream = conn.getInputStream();
			Result r = new Result();
			r.setInStream(inStream);
			r.setLength((long)conn.getContentLength());
			return r;
		} else {
			throw new HeadErrorException("文件不存在");
		}
	}

	public static class HeadErrorException extends Exception {
		private static final long serialVersionUID = 5921781442803574700L;

		public HeadErrorException(String msg) {
			super(msg);
		}
	}

	public static class Result {
		private long length;
		private InputStream inStream;

		public long getLength() {
			return length;
		}

		public void setLength(long l) {
			this.length = l;
		}

		public InputStream getInStream() {
			return inStream;
		}

		public void setInStream(InputStream inStream) {
			this.inStream = inStream;
		}

	}

}
