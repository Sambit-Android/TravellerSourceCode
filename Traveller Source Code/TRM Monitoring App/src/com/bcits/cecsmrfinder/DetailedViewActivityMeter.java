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

public class DetailedViewActivityMeter extends Activity{
	
	TextView info,info1,info2,info3,info4,info5,info6,info7,
	info8,info9,consumerdetailstitle;
	
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
		
		
		setContentView(R.layout.consumer_history_detailed_view_meter);
		
		
		try {
			String response = ConsumerHistoryAllDetails.responsefromservermeterdetails;
			JSONObject obj= new JSONArray(response).getJSONObject(ConsumerHistoryAllDetails.meterposition);
			
			
			
			
			
			consumerdetailstitle = (TextView)findViewById(R.id.consumerdetailstitle);
			consumerdetailstitle.setText("Meter Details Of RR No - "+ConsumerHistorySearch.rrno);
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
			
		
			info.setText(obj.getString("FIXEDDATE").equalsIgnoreCase("null")?"":obj.getString("FIXEDDATE"));
			info1.setText(obj.getString("SERIALNO").equalsIgnoreCase("null")?"":obj.getString("SERIALNO"));
			info2.setText(obj.getString("MAKE").equalsIgnoreCase("null")?"":obj.getString("MAKE"));
			info3.setText(obj.getString("CAPACITY").equalsIgnoreCase("null")?"":obj.getString("CAPACITY"));
			info4.setText(obj.getString("PHASE").equalsIgnoreCase("null")?"":obj.getString("PHASE"));
			info5.setText(obj.getString("DIGITS").equalsIgnoreCase("null")?"":obj.getString("DIGITS"));
			info6.setText(obj.getString("TYPE").equalsIgnoreCase("null")?"":obj.getString("TYPE"));
			info7.setText(obj.getString("COVER").equalsIgnoreCase("null")?"":obj.getString("COVER"));
			info8.setText(obj.getString("POSITION").equalsIgnoreCase("null")?"":obj.getString("POSITION"));
			info9.setText(obj.getString("REMARKS").equalsIgnoreCase("null")?"":obj.getString("REMARKS"));
			
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
