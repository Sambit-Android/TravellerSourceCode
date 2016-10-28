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

public class DetailedViewActivityBilling extends Activity{
	
	TextView info,info1,info2,info3,info4,info5,info6,info7,
	info8,info9,info10,info11,info12,info13,info14,info15,info16,
	info17,info18,info19,info20,info21,info22,info23,info24,info25,
	info26,info27,info28,info29,info30,info31,info32,info33,info34,info35,info36,info37,consumerdetailstitle;
	
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
		
		
		setContentView(R.layout.consumer_history_detailed_view_billing);
		
		
		try {
			String response = ConsumerHistoryAllDetails.responsefromserverbillingdetails;
			JSONObject obj= new JSONArray(response).getJSONObject(ConsumerHistoryAllDetails.billingposition);
			
			
			
			
			
			consumerdetailstitle = (TextView)findViewById(R.id.consumerdetailstitle);
			consumerdetailstitle.setText("Billing Details Of RR No - "+ConsumerHistorySearch.rrno);
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
			
			info.setText(obj.getString("MONTHYEAR").equalsIgnoreCase("null")?"":obj.getString("MONTHYEAR"));
			info1.setText(obj.getString("ScheduledDay").equalsIgnoreCase("null")?"":obj.getString("ScheduledDay"));
			info2.setText(obj.getString("BILLDATE").equalsIgnoreCase("null")?"":obj.getString("BILLDATE"));
			info3.setText(obj.getString("BILLNO").equalsIgnoreCase("null")?"":obj.getString("BILLNO"));
			info4.setText(obj.getString("FR").equalsIgnoreCase("null")?"":obj.getString("FR"));
			info5.setText(obj.getString("IR").equalsIgnoreCase("null")?"":obj.getString("IR"));
			info6.setText(obj.getString("UNITS").equalsIgnoreCase("null")?"":obj.getString("UNITS"));
			info7.setText(obj.getString("MC_Units").equalsIgnoreCase("null")?"":obj.getString("MC_Units"));
			info8.setText(obj.getString("BM").equalsIgnoreCase("null")?"":obj.getString("BM"));
			info9.setText(obj.getString("STATUS").equalsIgnoreCase("null")?"":obj.getString("STATUS"));
			info10.setText(obj.getString("OB").equalsIgnoreCase("null")?"":obj.getString("OB"));
			info11.setText(obj.getString("FC").equalsIgnoreCase("null")?"":obj.getString("FC"));
			info12.setText(obj.getString("EC").equalsIgnoreCase("null")?"":obj.getString("EC"));
			info13.setText(obj.getString("FAC").equalsIgnoreCase("null")?"":obj.getString("FAC"));
			info14.setText(obj.getString("PF").equalsIgnoreCase("null")?"":obj.getString("PF"));
			info15.setText(obj.getString("PFPEN").equalsIgnoreCase("null")?"":obj.getString("PFPEN"));
			info16.setText(obj.getString("BMD").equalsIgnoreCase("null")?"":obj.getString("BMD"));
			info17.setText(obj.getString("PENAMT").equalsIgnoreCase("null")?"":obj.getString("PENAMT"));
			info18.setText(obj.getString("REBATE").equalsIgnoreCase("null")?"":obj.getString("REBATE"));
			info19.setText(obj.getString("ARREARS").equalsIgnoreCase("null")?"":obj.getString("ARREARS"));
			info20.setText(obj.getString("INTE").equalsIgnoreCase("null")?"":obj.getString("INTE"));
			info21.setText(obj.getString("OLDINT").equalsIgnoreCase("null")?"":obj.getString("OLDINT"));
			info22.setText(obj.getString("TAX").equalsIgnoreCase("null")?"":obj.getString("TAX"));
			info23.setText(obj.getString("NET").equalsIgnoreCase("null")?"":obj.getString("NET"));
			info24.setText(obj.getString("DRAMT").equalsIgnoreCase("null")?"":obj.getString("DRAMT"));
			info25.setText(obj.getString("OTHERS").equalsIgnoreCase("null")?"":obj.getString("OTHERS"));
			info26.setText(obj.getString("AUDITARR").equalsIgnoreCase("null")?"":obj.getString("AUDITARR"));
			info27.setText(obj.getString("MMD").equalsIgnoreCase("null")?"":obj.getString("MMD"));
			info28.setText(obj.getString("BBC").equalsIgnoreCase("null")?"":obj.getString("BBC"));
			info29.setText(obj.getString("APPEAL").equalsIgnoreCase("null")?"":obj.getString("APPEAL"));
			info30.setText(obj.getString("SUSADJ").equalsIgnoreCase("null")?"":obj.getString("SUSADJ"));
			info31.setText(obj.getString("SUSCB").equalsIgnoreCase("null")?"":obj.getString("SUSCB"));
			info32.setText(obj.getString("CLOSINGBALANCE").equalsIgnoreCase("null")?"":obj.getString("CLOSINGBALANCE"));
			//info32.setText(obj.getString("CB").equalsIgnoreCase("null")?"":obj.getString("CB"));
			info33.setText(obj.getString("COLL").equalsIgnoreCase("null")?"":obj.getString("COLL"));
			info34.setText(obj.getString("DUEDATE").equalsIgnoreCase("null")?"":obj.getString("DUEDATE"));
			info35.setText(obj.getString("DL_ADJ_GIVEN").equalsIgnoreCase("null")?"":obj.getString("DL_ADJ_GIVEN"));
			info36.setText(obj.getString("SUSOB").equalsIgnoreCase("null")?"":obj.getString("SUSOB"));
			info37.setText(obj.getString("ELPEN").equalsIgnoreCase("null")?"":obj.getString("ELPEN"));
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
