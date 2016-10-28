package com.bcits.cecsmrfinder;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

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

public class DetailedViewActivityPayment extends Activity{
	
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
		
		
		setContentView(R.layout.consumer_history_detailed_view_payment);
		
		
		try {
			String response = ConsumerHistoryAllDetails.responsefromserverpaymentdetails;
			JSONObject obj= new JSONArray(response).getJSONObject(ConsumerHistoryAllDetails.paymentposition);
			
			
			
			
			
			consumerdetailstitle = (TextView)findViewById(R.id.consumerdetailstitle);
			consumerdetailstitle.setText("Payment Details Of RR No - "+ConsumerHistorySearch.rrno);
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
			
			String date = obj.getString("ENTRYDATE");
			DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			long milliSeconds= Long.parseLong(date);
		    Calendar calendar = Calendar.getInstance();
		    calendar.setTimeInMillis(milliSeconds);
		
			
			
			info.setText(obj.getString("RECEIPTDATE").equalsIgnoreCase("null")?"":obj.getString("RECEIPTDATE"));
			info1.setText(obj.getString("RECEIPTNO").equalsIgnoreCase("null")?"":obj.getString("RECEIPTNO"));
			info2.setText(obj.getString("AMOUNT").equalsIgnoreCase("null")?"":obj.getString("AMOUNT"));
			info3.setText(obj.getString("BRANCH").equalsIgnoreCase("null")?"":obj.getString("BRANCH"));
			info4.setText(obj.getString("TOWARDS").equalsIgnoreCase("null")?"":obj.getString("TOWARDS"));
			info5.setText(obj.getString("CASHTYPE").equalsIgnoreCase("null")?"":obj.getString("CASHTYPE"));
			info6.setText(obj.getString("PAYMENTMODE").equalsIgnoreCase("null")?"":obj.getString("PAYMENTMODE"));
			info7.setText(obj.getString("BOOKNO").equalsIgnoreCase("null")?"":obj.getString("BOOKNO"));
			info8.setText(obj.getString("REASON").equalsIgnoreCase("null")?"":obj.getString("REASON"));
			info9.setText(formatter.format(calendar.getTime()));
			
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
