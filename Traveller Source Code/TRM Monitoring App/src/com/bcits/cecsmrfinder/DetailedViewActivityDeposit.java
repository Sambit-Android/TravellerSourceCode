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

public class DetailedViewActivityDeposit extends Activity{
	
	TextView info,info1,info2,info3,info4,consumerdetailstitle;
	
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
		
		
		setContentView(R.layout.consumer_history_detailed_view_deposit);
		
		
		try {
			String response = ConsumerHistoryAllDetails.responsefromserverdepositdetails;
			JSONObject obj= new JSONArray(response).getJSONObject(ConsumerHistoryAllDetails.depositposition);
			
			
			
			
			
			consumerdetailstitle = (TextView)findViewById(R.id.consumerdetailstitle);
			consumerdetailstitle.setText("Deposit Details Of RR No - "+ConsumerHistorySearch.rrno);
			info = (TextView)findViewById(R.id.info);
			info1 = (TextView)findViewById(R.id.info1);
			info2 = (TextView)findViewById(R.id.info2);
			info3 = (TextView)findViewById(R.id.info3);
			info4 = (TextView)findViewById(R.id.info4);
					
			info.setText(obj.getString("RECEIPTDATE").equalsIgnoreCase("null")?"":obj.getString("RECEIPTDATE"));
			info1.setText(obj.getString("RECEIPTNO").equalsIgnoreCase("null")?"":obj.getString("RECEIPTNO"));
			info2.setText(obj.getString("AMOUNT").equalsIgnoreCase("null")?"":obj.getString("AMOUNT"));
			info3.setText(obj.getString("REMARKS").equalsIgnoreCase("null")?"":obj.getString("REMARKS"));
			info4.setText(obj.getString("STATUS").equalsIgnoreCase("null")?"":obj.getString("STATUS"));
			
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
