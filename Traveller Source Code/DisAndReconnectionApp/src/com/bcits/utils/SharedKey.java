package com.bcits.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SharedKey {

	public static final String KEY_LOGIN_CREDENTIALS="key.login.credentials"; //LOGIN DATA
	public static final String KEY_LOGIN_REMEMBER = "key.login.remember";     //REMEMBER LOGIN DATA FLAG  (YES/NO)
	public static final String KEY_EMPLOYEE_DETAILS="key.employee.details";   //EMPLOYEE DETAILS IN JSON FORMAT
	public static final String KEY_LOCATIONS ="key.locations";                //ALL LOCATIONS IN JSON FORMAT

	
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
}
