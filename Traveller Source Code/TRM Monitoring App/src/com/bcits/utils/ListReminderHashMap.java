package com.bcits.utils;

import java.util.HashMap;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Adapter;
import android.widget.ListView;

public class ListReminderHashMap {
	public static int listSelectedPosition=0;
	@SuppressWarnings("unchecked")
	public static boolean setSelectedView(String uniqueKey,String hashKeyValue,ListView listView,Adapter adapter,int customTextViewId,Context context) {
		
		if(getDefaults(uniqueKey, context)!=null)
		{//BEFORE CALLING THIS METHOD, ADAPTER SHOULD BE SET TO LISTVIEW
			if(!getDefaults(uniqueKey, context).trim().equals("")) {
				String savedFed=getDefaults(uniqueKey, context).trim();
				if(adapter!=null) {
					//Toast.makeText(context, "Searching Previous Selection", Toast.LENGTH_SHORT).show();
					HashMap<String, String> hashList = new HashMap<String, String>();
					for (int i = 0; i < adapter.getCount(); i++) {
						hashList=(HashMap<String, String>) listView.getItemAtPosition(i);
						String feedr = hashList.get(hashKeyValue).toString();
						
						if(feedr.trim().equals(savedFed.trim())) {
							
							
						//	View clickedView = getViewByPosition(i, listView);
						//	TextView c = (TextView) clickedView.findViewById(customTextViewId);
						//    c.setTextColor(Color.RED);
							int h1 = listView.getHeight();
						//	int h2 = clickedView.getHeight();
							//listView.smoothScrollToPosition(i);

							listView.smoothScrollToPositionFromTop(i, h1/2 - h1/2, 2300);
							listSelectedPosition=i;
						    return true;
						}
					}
				}
			}
		}
		 return false;
	}
	
	public static void setListViewText(String uniqueKey,String listTextValue,Context context) {
		setDefaults(uniqueKey, listTextValue, context);
	}
	
	
	private static View getViewByPosition(int pos, ListView listView) {
	    final int firstListItemPosition = listView.getFirstVisiblePosition();
	    final int lastListItemPosition = firstListItemPosition + listView.getChildCount() - 1;
	    
	    // 8   #################  0   #################  7
	    
	    if (pos < firstListItemPosition || pos > lastListItemPosition ) {
	        return listView.getAdapter().getView(pos, null, listView);
	    } else {
	        final int childIndex = pos - firstListItemPosition;
	        

	        
	        return listView.getChildAt(childIndex);
	    }
	}
	
	private static void setDefaults(String key, String value, Context context) {
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
