package com.example.shareinentcallback;

import android.graphics.drawable.Drawable;

public class ShareApps {
	
	String appName;
	
	String appPackageName;
	
	Drawable appIcon;

	public ShareApps(String appName, String appPackageName, Drawable appIcon) {
		super();
		this.appName = appName;
		this.appPackageName = appPackageName;
		this.appIcon = appIcon;
	}

}
