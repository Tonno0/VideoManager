package com.example.video;

import org.xutils.x;

import android.app.Application;

public class MyApplication extends Application{

	@Override
	public void onCreate() {
		super.onCreate();
		x.Ext.init(this);
	    x.Ext.setDebug(BuildConfig.DEBUG); 
	}
	

}
