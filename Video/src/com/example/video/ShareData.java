package com.example.video;

import android.content.Context;
import android.content.SharedPreferences;

public class ShareData {
	private static final String dataName = "data.xml";
	private static SharedPreferences sp = null;

	public ShareData(Context context) {
		if (sp == null) {
			sp = context.getApplicationContext().getSharedPreferences(dataName,
					Context.MODE_PRIVATE);
		}
	}

	public void putString(String key, String value) {
		sp.edit().putString(key, value).commit();
	}
	
	public String getString(String key, String def) {
		return sp.getString(key, def);
	}
	
	public void putBoolean(String key, boolean value) {
		sp.edit().putBoolean(key, value).commit();
	}
	
	public boolean getBoolean(String key, boolean def) {
		return sp.getBoolean(key, def);
	}
	
	public void putLong(String key, long value) {
		sp.edit().putLong(key, value).commit();
	}
	
	public long getLong(String key, long def) {
		return sp.getLong(key, def);
	}
}
