package amr.utils;

import activity.dlms.lib.MainActivity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Utils {

	public static void setDefaults(String key, String value, Context context) {
		SharedPreferences prefs = PreferenceManager	.getDefaultSharedPreferences(context);
		SharedPreferences.Editor editor = prefs.edit();
		editor.putString(key, value);
		editor.commit();
	}

	public static String getDefaults(String key, Context context) {
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
		return preferences.getString(key, null);
}
	public static void dirctLogcat(String message){
		Utils.setDefaults("logCate", Utils.getDefaults("logCate", MainActivity.context)+" \n "+message+" \n ", MainActivity.context);
	}
}