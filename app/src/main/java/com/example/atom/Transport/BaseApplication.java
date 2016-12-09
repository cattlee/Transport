package com.example.atom.Transport;

import com.example.atom.Transport.config.Constants;
import com.example.atom.Transport.image.ImageLoaderConfig;

import android.app.Application;
import android.content.res.Configuration;

/**
 * @author Tau.Chen 陈涛
 * 
 * @email tauchen1990@gmail.com,1076559197@qq.com
 * 
 * @date 2013年9月12日
 * 
 * @version V_1.0.0
 * 
 * @description  Application和Actovotu,Service一样是Android框架的一个系统组件，
 * 当android程序启动时系统会创建一个 application对象，
 * 用来存储系统的一些信息(比较大的文件，小文件通过intent或者将bundle添加到intent进行传输)。
 * 
 */
public class BaseApplication extends Application {

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		ImageLoaderConfig.initImageLoader(this, Constants.BASE_IMAGE_CACHE);
	}

	@Override
	public void onLowMemory() {
		// TODO Auto-generated method stub
		super.onLowMemory();
	}

	@Override
	public void onTerminate() {
		// TODO Auto-generated method stub
		super.onTerminate();
	}

}
