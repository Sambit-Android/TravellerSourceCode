package com.bcits.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.SystemClock;
import android.preference.PreferenceManager;

public class AmrMethods {/** A M Remith Methods */
	 public static final long MIN_CLICK_INTERVAL = 1000; //in millis
	 public static long lastClickTime = 0;

	
	public static void setDefaults(String key, String value, Context context) {
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
		SharedPreferences.Editor editor = prefs.edit();
		editor.putString(key, value);
		editor.commit();
	}

	public static String getDefaults(String key, Context context) {
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
		return preferences.getString(key, null);
	}
	
	public static boolean doubleClick(){
		long currentTime = SystemClock.elapsedRealtime();
		if (currentTime - lastClickTime > MIN_CLICK_INTERVAL) {
			lastClickTime = currentTime;
			return true;
	}
		return false;
}
	public static String getCurrentTimeStamp(){
	    try {

	        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	        String currentTimeStamp = dateFormat.format(new Date()); // Find todays date

	        return currentTimeStamp;
	    } catch (Exception e) {
	        e.printStackTrace();

	        return null;
	    }
	}
}
