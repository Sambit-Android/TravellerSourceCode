package com.bcits.cecsmrfinder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DetailedViewActivityMeterChange extends Activity{
	
	TextView info,info1,info2,info3,info4,info5,info6,info7,
	info8,info9,info10,info11,consumerdetailstitle;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM); 
		getActionBar().setCustomView(R.layout.custom_title_consumerhistory);
		getActionBar().getCustomView().findViewById(R.id.title);
		
		//hideSoftKeyboard();
		
		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.custom_title_consumerhistory, null);
		actionBar.setCustomView(view, new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
		actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		
		
		setContentView(R.layout.consumer_history_detailed_view_meterchange);
		
		
		try {
			String response = ConsumerHistoryAllDetails.responsefromservermeterchangedetails;
			JSONObject obj= new JSONArray(response).getJSONObject(ConsumerHistoryAllDetails.meterchangeposition);
			
			
			
			
			
			consumerdetailstitle = (TextView)findViewById(R.id.consumerdetailstitle);
			consumerdetailstitle.setText("Meter Change Details Of RR No - "+ConsumerHistorySearch.rrno);
			info = (TextView)findViewById(R.id.info);
			info1 = (TextView)findViewById(R.id.info1);
			info2 = (TextView)findViewById(R.id.info2);
			info3 = (TextView)findViewById(R.id.info3);
			info4 = (TextView)findViewById(R.id.info4);
			info5 = (TextView)findViewById(R.id.info5);
			info6 = (TextView)findViewById(R.id.info6);
			info7 = (TextView)findViewById(R.id.info7);
			info8 = (TextView)findViewById(R.id.info8);
			info9 = (TextView)findViewById(R.id.info9);
			info10 = (TextView)findViewById(R.id.info10);
			info11 = (TextView)findViewById(R.id.info11);
			
			info.setText(obj.getString("MCDATE").equalsIgnoreCase("null")?"":obj.getString("MCDATE"));
			info1.setText(obj.getString("RELEASEDATE").equalsIgnoreCase("null")?"":obj.getString("RELEASEDATE"));
			info2.setText(obj.getString("ENTERDATE").equalsIgnoreCase("null")?"":obj.getString("ENTERDATE"));
			info3.setText(obj.getString("OLDSERIALNO").equalsIgnoreCase("null")?"":obj.getString("OLDSERIALNO"));
			info4.setText(obj.getString("OLDMAKE").equalsIgnoreCase("null")?"":obj.getString("OLDMAKE"));
			info5.setText(obj.getString("OLDCAPACITY").equalsIgnoreCase("null")?"":obj.getString("OLDCAPACITY"));
			info6.setText(obj.getString("FINALREADING").equalsIgnoreCase("null")?"":obj.getString("FINALREADING"));
			info7.setText(obj.getString("NEWMAKE").equalsIgnoreCase("null")?"":obj.getString("NEWMAKE"));
			info8.setText(obj.getString("NEWSERIALNO").equalsIgnoreCase("null")?"":obj.getString("NEWSERIALNO"));
			info9.setText(obj.getString("NEWCAPACITY").equalsIgnoreCase("null")?"":obj.getString("NEWCAPACITY"));
			info10.setText(obj.getString("INITIALREADING").equalsIgnoreCase("null")?"":obj.getString("INITIALREADING"));
			info11.setText(obj.getString("MCUNITSCONSUMED").equalsIgnoreCase("null")?"":obj.getString("MCUNITSCONSUMED"));
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.back_button, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case R.id.btn_back:
			onBackPressed();
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}

}
