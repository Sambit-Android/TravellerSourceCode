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

public class DetailedViewActivity extends Activity{
	
	TextView info,info1,info2,info3,info4,info5,info6,info7,
	info8,info9,info10,info11,info12,info13,info14,info15,info16,
	info17,info18,info19,info20,info21,info22,info23,info24,info25,
	info26,info27,info28,info29,info30,info31,info32,info33,info34,info35,info36,info37,info38,info39,info40,consumerdetailstitle;
	DateFormat formatter,formatter2;
	String trivector;
	Calendar calendar,calendar2;
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
		
		
		setContentView(R.layout.consumer_history_detailed_view);
		
		
		try {
			String response = ConsumerHistoryAllDetails.responsefromserver;
			JSONObject obj= new JSONArray(response).getJSONObject(0);
			
			
			
			
			
			consumerdetailstitle = (TextView)findViewById(R.id.consumerdetailstitle);
			consumerdetailstitle.setText("Consumer Details Of RR No - "+ConsumerHistorySearch.rrno);
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
			info12 = (TextView)findViewById(R.id.info12);
			info13 = (TextView)findViewById(R.id.info13);
			info14 = (TextView)findViewById(R.id.info14);
			info15 = (TextView)findViewById(R.id.info15);
			info16 = (TextView)findViewById(R.id.info16);
			info17 = (TextView)findViewById(R.id.info17);
			info18 = (TextView)findViewById(R.id.info18);
			info19 = (TextView)findViewById(R.id.info19);
			info20 = (TextView)findViewById(R.id.info20);
			info21 = (TextView)findViewById(R.id.info21);
			info22 = (TextView)findViewById(R.id.info22);
			info23 = (TextView)findViewById(R.id.info23);
			info24 = (TextView)findViewById(R.id.info24);
			info25 = (TextView)findViewById(R.id.info25);
			info26 = (TextView)findViewById(R.id.info26);
			info27 = (TextView)findViewById(R.id.info27);
			info28 = (TextView)findViewById(R.id.info28);
			info29 = (TextView)findViewById(R.id.info29);
			info30 = (TextView)findViewById(R.id.info30);
			info31 = (TextView)findViewById(R.id.info31);
			info32 = (TextView)findViewById(R.id.info32);
			info33 = (TextView)findViewById(R.id.info33);
			info34 = (TextView)findViewById(R.id.info34);
			
			info35 = (TextView)findViewById(R.id.info35);
			info36 = (TextView)findViewById(R.id.info36);
			info37 = (TextView)findViewById(R.id.info37);
			info38 = (TextView)findViewById(R.id.info38);
			info39 = (TextView)findViewById(R.id.info39);
			info40 = (TextView)findViewById(R.id.info40);
			
			String date = obj.getString("DATEOFSERVICE");
			if(date.equalsIgnoreCase("null")||date.equalsIgnoreCase("")){
				
			}else{
			formatter = new SimpleDateFormat("dd/MM/yyyy");
			long milliSeconds= Long.parseLong(date);
		    calendar = Calendar.getInstance();
		    calendar.setTimeInMillis(milliSeconds);
			}
		    String date2 = obj.getString("STATUSDATE");
		    if(date2.equalsIgnoreCase("null")||date2.equalsIgnoreCase("")){
				
			}else{
			formatter2 = new SimpleDateFormat("dd/MM/yyyy");
			long milliSeconds2= Long.parseLong(date2);
		    calendar2 = Calendar.getInstance();
		    calendar2.setTimeInMillis(milliSeconds2);
			}
		    
		    if(obj.getString("TRIVECTOR").equalsIgnoreCase("null")){
		    	trivector = "";
		    }else if(obj.getString("TRIVECTOR").equalsIgnoreCase("0")){
		    	trivector = "NO";
		    }else if(obj.getString("TRIVECTOR").equalsIgnoreCase("1")){
		    	trivector = "YES";
		    }else{
		    	trivector = "";
		    }
		    
			info.setText(obj.getString("CONSUMERNAME").equalsIgnoreCase("null")?"":obj.getString("CONSUMERNAME"));
			info1.setText(obj.getString("CONSUMERADDRESS").equalsIgnoreCase("null")?"":obj.getString("CONSUMERADDRESS"));
			info2.setText(obj.getString("CONSUMERADDRESS1").equalsIgnoreCase("null")?"":obj.getString("CONSUMERADDRESS1"));
			info3.setText(obj.getString("PLACE").equalsIgnoreCase("null")?"":obj.getString("PLACE"));
			info4.setText(obj.getString("LC_FOLIO").equalsIgnoreCase("null")?"":obj.getString("LC_FOLIO"));
			info5.setText(obj.getString("FC").equalsIgnoreCase("null")?"":obj.getString("FC"));
			info6.setText(obj.getString("METERCONSTANT").equalsIgnoreCase("null")?"":obj.getString("METERCONSTANT"));
			info7.setText(obj.getString("CORPORATION").equalsIgnoreCase("null")?"":obj.getString("CORPORATION"));
			info8.setText(obj.getString("AREANAME").equalsIgnoreCase("null")?"":obj.getString("AREANAME"));
			info9.setText(obj.getString("SOCODE").equalsIgnoreCase("null")?"":obj.getString("SOCODE"));
			info10.setText(obj.getString("FEEDERCODE").equalsIgnoreCase("null")?"":obj.getString("FEEDERCODE"));
			info11.setText(obj.getString("TCCODE").equalsIgnoreCase("null")?"":obj.getString("TCCODE"));
			info12.setText(obj.getString("POLENO").equalsIgnoreCase("null")?"":obj.getString("POLENO"));
			info13.setText(obj.getString("MRCODE").equalsIgnoreCase("null")?"":obj.getString("MRCODE"));
			info14.setText(obj.getString("TOD").equalsIgnoreCase("null")?"":obj.getString("TOD"));
			info15.setText(obj.getString("LDDATE").equalsIgnoreCase("null")?"":obj.getString("LDDATE"));
			info16.setText(obj.getString("WARD").equalsIgnoreCase("null")?"":obj.getString("WARD"));
			info17.setText(obj.getString("BKWH").equalsIgnoreCase("null")?"":obj.getString("BKWH"));
			info18.setText(obj.getString("TARIFFDCB").equalsIgnoreCase("null")?"":obj.getString("TARIFFDCB"));
			info19.setText(obj.getString("TARIFFCODE").equalsIgnoreCase("null")?"":obj.getString("TARIFFCODE"));
			info20.setText(date.equalsIgnoreCase("null")||date.equalsIgnoreCase("")?"":formatter.format(calendar.getTime()));
			info21.setText(obj.getString("SANCTIONKW").equalsIgnoreCase("null")?"":obj.getString("SANCTIONKW"));
			info22.setText(obj.getString("SANCTIONHP").equalsIgnoreCase("null")?"":obj.getString("SANCTIONHP"));
			info23.setText(obj.getString("CONTRACTDMD").equalsIgnoreCase("null")?"":obj.getString("CONTRACTDMD"));
			info24.setText(obj.getString("PDDATE").equalsIgnoreCase("null")?"":obj.getString("PDDATE"));
			info25.setText(obj.getString("TALUK").equalsIgnoreCase("null")?"":obj.getString("TALUK"));
			info26.setText(obj.getString("NOOFFLATS").equalsIgnoreCase("null")?"":obj.getString("NOOFFLATS"));
			info27.setText(obj.getString("INSTSTATUS").equalsIgnoreCase("null")?"":obj.getString("INSTSTATUS"));
			info28.setText(date2.equalsIgnoreCase("null")||date2.equalsIgnoreCase("")?"":formatter2.format(calendar2.getTime()));
			info29.setText(obj.getString("AVERAGE").equalsIgnoreCase("null")?"":obj.getString("AVERAGE"));
			info30.setText(obj.getString("READINGDAY").equalsIgnoreCase("null")?"":obj.getString("READINGDAY"));
			info31.setText(obj.getString("SIDERRNO").equalsIgnoreCase("null")?"":obj.getString("SIDERRNO"));
			info32.setText(obj.getString("PF").equalsIgnoreCase("null")?"":obj.getString("PF"));
			info33.setText(obj.getString("TELEPHONENO").equalsIgnoreCase("null")?"":obj.getString("TELEPHONENO"));
			info34.setText(obj.getString("NOOFMETERS").equalsIgnoreCase("null")?"":obj.getString("NOOFMETERS"));
			info35.setText(obj.getString("TERMINATIONDATE").equalsIgnoreCase("null")?"":obj.getString("TERMINATIONDATE"));
			info36.setText(obj.getString("DISPUTEAMOUNT").equalsIgnoreCase("null")?"":obj.getString("DISPUTEAMOUNT"));
			info37.setText(obj.getString("AFORMDATE").equalsIgnoreCase("null")?"":obj.getString("AFORMDATE"));
			info38.setText(obj.getString("BFORMDATE").equalsIgnoreCase("null")?"":obj.getString("BFORMDATE"));
			info39.setText(obj.getString("CFORMDATE").equalsIgnoreCase("null")?"":obj.getString("CFORMDATE"));
			info40.setText(trivector);
			
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
