package com.bcits.cecsmrfinder;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bcits.utils.AmrMethods;
import com.bcits.utils.MrWiseBillingProgressGetterSetter;
import com.bcits.utils.SendRequest;
import com.bcits.utils.TransactionDOAdapter;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.sqlite.cescip.DBLocation;
import com.sqlite.cescip.DBMrWiseBilling;




public class MrSummaryActivity extends Activity{
	
	LinearLayout summary_report,detailed_report;
	ArrayList<MrWiseBillingProgressGetterSetter> arrayComplaints;
	DBMrWiseBilling databasehandler;
	TransactionDOAdapter adaptContents;
	TextView section,todaysdate,todaysdate2,previousdate,todaytarget,todaycompleted,todaypercentage,previoustarget,previouscompleted,previouspercentage,mrslive,totalbillsgenerated;
	String dateofview="";
	AlertDialog show;
	public static String mrcode;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM); 
		getActionBar().setCustomView(R.layout.custom_title_billing_progress);
		getActionBar().getCustomView().findViewById(R.id.title);
		
		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.custom_title_billing_progress, null);
		actionBar.setCustomView(view, new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
		actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		
		setContentView(R.layout.mr_summary);
		detailed_report = (LinearLayout) findViewById(R.id.detailed_report);
		summary_report = (LinearLayout) findViewById(R.id.summary_report);
		
		section = (TextView)findViewById(R.id.section);
		section.setText("DETAILS FOR "+Login.sdoLocation);
		todaysdate = (TextView)findViewById(R.id.todaysdate);
		todaysdate2= (TextView)findViewById(R.id.todaysdate2);
		mrslive = (TextView)findViewById(R.id.textView111);
		totalbillsgenerated = (TextView)findViewById(R.id.textView222);
		todaytarget = (TextView)findViewById(R.id.textView1);
		todaycompleted = (TextView)findViewById(R.id.textView2);
		todaypercentage= (TextView)findViewById(R.id.textView300);
		previoustarget = (TextView)findViewById(R.id.textView11);
		previouscompleted = (TextView)findViewById(R.id.textView22);
		previouspercentage= (TextView)findViewById(R.id.textView33);
		previousdate = (TextView)findViewById(R.id.lastmonthdate);
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String formattedDate = sdf.format(date);
		todaysdate.setText("Current Month Progress As On "+formattedDate);
		//new BillingProgressAsync().execute();
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, -1);// then one month
		String prevdate = sdf.format(c.getTime());
		todaysdate2.setText("Today's Bill Progress - "+formattedDate);
		previousdate.setText("Previous Month Progress As On "+prevdate);
		detailed_report.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent intent = new Intent(MrSummaryActivity.this,DetailedMRReportGrid.class);
				startActivity(intent);
				
			}
		});
		
		
		summary_report.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent intent = new Intent(MrSummaryActivity.this,BillingProgress.class);
				startActivity(intent);
				
			}
		});
		
		new performBackgroundTaskLoadInstalledIPandDTC().execute();
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
	
	
	@Override
	public void onBackPressed() {
		//super.onBackPressed();
		
		/*Intent intent = new Intent(MrSummaryActivity.this,Dashboard.class);
		startActivity(intent);*/
		finish();
		
	}
	
	
	public class BillingProgressAsync extends	AsyncTask<Void, Void, Void> {

		String responsefromserver = null;
		private ProgressDialog mProgressDialog;
		JSONArray json = null;

		protected void onPreExecute() {
			mProgressDialog = new ProgressDialog(MrSummaryActivity.this,R.style.MyTheme2);
			mProgressDialog.setMessage("Fetching Billing Progress Details...");
			mProgressDialog.setCancelable(false);
			mProgressDialog.show();
		}


		@Override
		protected Void doInBackground(Void... params) {
			try {
				JSONArray ja1 = new JSONArray();
				JSONObject jsonarray = new JSONObject();
				
				//Login.sdoCode = "5111";
				
				jsonarray.put("sdocode",Login.sdoCode);

				ja1.put(jsonarray);

				SendRequest req = new SendRequest();
				responsefromserver = req.uploadToServer("getBillingProgress", ja1);
				

			} catch (Exception e) {
				e.printStackTrace();
			}

			return null;
		}
		
		
		protected void onPostExecute(Void unused) {
			
			
			mProgressDialog.dismiss();
			if (responsefromserver == null) {
				
				 new AlertDialog.Builder(MrSummaryActivity.this).setTitle(getResources().getString(R.string.Info)).setMessage("Unable To Get Billing Progress")
				 .setPositiveButton(getResources().getString(R.string.OK), null).show();
				
			}
		
			else if (responsefromserver.contains("NoData")) {
				 new AlertDialog.Builder(MrSummaryActivity.this).setTitle(getResources().getString(R.string.Info)).setMessage("No Data Found For This SDO")
				 .setPositiveButton(getResources().getString(R.string.OK), null).show();
			}
	
			else  {
				
				try {
					json = new JSONArray(responsefromserver);
					
					
					if (json != null) {
						
						
					/*	
						databasehandler = new DBMrWiseBilling(getBaseContext());
						databasehandler.open();
						//databasehandler.deleteAll();
						databasehandler.close();*/


						databasehandler = new DBMrWiseBilling(getBaseContext());
						databasehandler.open();
						try {
							databasehandler.deleteAll();
						} catch (Exception e) {
							e.printStackTrace();
						}
						

						for (int i = 0; i < json.length(); i++) {

							try {	
								//Asst_json_obj = json.optJSONObject("consumerData");
								JSONObject Asst_json_obj  = json.getJSONObject(i) ;

								String section = "";
								String sitecode = "";
								String mrcode = "";
								String tobebilled = "";
								String billed = "";
								String unbilled = "";
								String tobebilledday = "";
								String billedday = "";
								String unbilledday = "";
								String mrname = "";
								String mrmobilenumber = "";

								if( 		Asst_json_obj.isNull("section") 
										|| Asst_json_obj.get("section") == null
										|| Asst_json_obj.get("section") == "" ) 
								{

									Asst_json_obj.put("section", "-");

									section = Asst_json_obj.getString("section");

								} else {

									section = Asst_json_obj.getString("section");
								}

								if( 		Asst_json_obj.isNull("sitecode") 
										|| Asst_json_obj.get("sitecode") == null
										|| Asst_json_obj.get("sitecode") == "" ) 
								{

									Asst_json_obj.put("sitecode", "-");

									sitecode = Asst_json_obj.getString("sitecode");

								} else {

									sitecode = Asst_json_obj.getString("sitecode");
								}

								if( 		Asst_json_obj.isNull("billed") 
										|| Asst_json_obj.get("billed") == null
										|| Asst_json_obj.get("billed") == "" ) 
								{

									Asst_json_obj.put("billed", "-");

									billed = Asst_json_obj.getString("billed");

								} else {

									billed = Asst_json_obj.getString("billed");
								}

								/*if( 		Asst_json_obj.isNull("unbilled") 
										|| Asst_json_obj.get("unbilled") == null
										|| Asst_json_obj.get("unbilled") == "" ) 
								{

									Asst_json_obj.put("unbilled", "-");

									unbilled = Asst_json_obj.getString("unbilled");

								} else {

									unbilled = Asst_json_obj.getString("unbilled");
								}*/
								int ubm = Integer.parseInt(Asst_json_obj.getString("tobebilled"))-Integer.parseInt(Asst_json_obj.getString("billed"));
								unbilled = String.valueOf(ubm);

								if( 		Asst_json_obj.isNull("mrcode") 
										|| Asst_json_obj.get("mrcode") == null
										|| Asst_json_obj.get("mrcode") == "" ) 
								{

									Asst_json_obj.put("mrcode", "-");

									mrcode = Asst_json_obj.getString("mrcode");

								} else {

									mrcode = Asst_json_obj.getString("mrcode");
								}

								if( 		Asst_json_obj.isNull("billedday") 
										|| Asst_json_obj.get("billedday") == null
										|| Asst_json_obj.get("billedday") == "" ) 
								{

									Asst_json_obj.put("billedday", "-");

									billedday = Asst_json_obj.getString("billsissueday");

								} else {

									billedday = Asst_json_obj.getString("billsissueday");
								}

								/*if( 		Asst_json_obj.isNull("unbilledday") 
										|| Asst_json_obj.get("unbilledday") == null
										|| Asst_json_obj.get("unbilledday") == "" ) 
								{

									Asst_json_obj.put("unbilledday", "-");

									unbilledday = Asst_json_obj.getString("unbilledday");

								} else {

									unbilledday = Asst_json_obj.getString("unbilledday");
								}*/
								int ubd = Integer.parseInt(Asst_json_obj.getString("tobebilledday"))-Integer.parseInt(Asst_json_obj.getString("billedday"));
								unbilledday = String.valueOf(ubd);

								if( 		Asst_json_obj.isNull("tobebilled") 
										|| Asst_json_obj.get("tobebilled") == null
										|| Asst_json_obj.get("tobebilled") == "" ) 
								{

									Asst_json_obj.put("tobebilled", "-");

									tobebilled = Asst_json_obj.getString("tobebilled");

								} else {

									tobebilled = Asst_json_obj.getString("tobebilled");
								}

								if( 		Asst_json_obj.isNull("tobebilledday") 
										|| Asst_json_obj.get("tobebilledday") == null
										|| Asst_json_obj.get("tobebilledday") == "" ) 
								{

									Asst_json_obj.put("tobebilledday", "-");

									tobebilledday = Asst_json_obj.getString("tobebilledday");

								} else {

									tobebilledday = Asst_json_obj.getString("tobebilledday");
								}
								
								if( 		Asst_json_obj.isNull("mrqualification") 
										|| Asst_json_obj.get("mrqualification") == null
										|| Asst_json_obj.get("mrqualification") == "" ) 
								{

									Asst_json_obj.put("mrqualification", "-");

									mrmobilenumber = Asst_json_obj.getString("mrqualification");

								} else {

									mrmobilenumber = Asst_json_obj.getString("mrqualification");
								}
								
								
								if( 		Asst_json_obj.isNull("mrname") 
										|| Asst_json_obj.get("mrname") == null
										|| Asst_json_obj.get("mrname") == "" ) 
								{

									Asst_json_obj.put("mrname", "-");

									mrname = Asst_json_obj.getString("mrname");

								} else {

									mrname = Asst_json_obj.getString("mrname");
								}

								
								databasehandler.insert(section, sitecode,	 mrcode, tobebilled, billed, unbilled,	 tobebilledday,	 billedday,unbilledday,mrname,mrmobilenumber);





							} catch (Exception e) {
								e.printStackTrace();
							}

						}
						databasehandler.close();
						
						
						databasehandler.open();
						
						 arrayComplaints = databasehandler.getAllBillingData();
						 
						databasehandler.close();
						
					}else {
						
						 new AlertDialog.Builder(MrSummaryActivity.this).setTitle(getResources().getString(R.string.Info)).setMessage("Unable To Connect Please Try Again Later")
						 .setPositiveButton(getResources().getString(R.string.OK), null).show();
						
					}
					
					
					
					
				} catch (JSONException e2) {
					e2.printStackTrace();
				}
				
			
		}
			
			
			
			
		}



	}
	public class performBackgroundTaskLoadInstalledIPandDTC extends
	AsyncTask<Void, Void, Void> {
String responsefromserver = null;
private ProgressDialog mProgressDialog;

protected void onPreExecute() {
	mProgressDialog = new ProgressDialog(MrSummaryActivity.this,R.style.MyTheme2);
	mProgressDialog.setMessage(getResources().getString(R.string.LoadingDetails));
	mProgressDialog.setCancelable(false);
	mProgressDialog.show();
}

protected Void doInBackground(Void... params) {
	try {
		JSONArray ja1 = new JSONArray();
		JSONObject jsonarray = new JSONObject();
		try {
			
			//Login.sdoCode = "5111";
			dateofview = AmrMethods.getCurrentTimeStamp2().trim();
			if(Login.sdoCode.equals("1410")){
				jsonarray.put("value","2120");
			}else{
			jsonarray.put("value",Login.sdoCode);
			}
			jsonarray.put("date", dateofview);
			ja1.put(jsonarray);

			SendRequest req = new SendRequest();
			String url1 = "showMrTrackLiveMobile";
			responsefromserver = req.uploadToServer(url1, ja1);
		} catch (JSONException e) {
			e.printStackTrace();
		}

	} catch (Exception e) {
		e.printStackTrace();
	}

	return null;
}

protected void onPostExecute(Void unused) {
	mProgressDialog.dismiss();
	if (responsefromserver == null) {
		
		new AlertDialog.Builder(MrSummaryActivity.this)
		.setTitle("Error..!")
		.setMessage(
				"Sorry, server down / some error occurred while connecting server.")
				.setPositiveButton("OK", null).show();
		
	} else if (responsefromserver.equals("invalid")) {
		show = new AlertDialog.Builder(MrSummaryActivity.this).setTitle(getResources().getString(R.string.Info))
				.setMessage("Something went wrog try again Later")
				.setPositiveButton(getResources().getString(R.string.OK), null).show();

	} else if (responsefromserver.equalsIgnoreCase("[]")) {
		show = new AlertDialog.Builder(MrSummaryActivity.this).setTitle(getResources().getString(R.string.Info))
				.setMessage("no proper lattitude and langitude")
				.setPositiveButton(getResources().getString(R.string.OK), null).show();
	}

	else if (responsefromserver != null) {
		JSONArray ja = null;
		int mrlivecount=0;
		int totalbillsgeneratedtoday=0;
		double tobebilled =0 , billed =0;
		try {
			ja = new JSONArray(responsefromserver);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		// arrayCircle = new ArrayList<GetSetCircle>();
		if (ja != null) {
			JSONObject obj = null;
			DBLocation dbAdapter = new DBLocation(getBaseContext());
			dbAdapter.open();
			dbAdapter.emptyTable();
			for (int i = 0; i < ja.length(); i++) {
				
				try {
					obj = ja.getJSONObject(i);
				} catch (JSONException e1) {
					e1.printStackTrace();
				}
				
					//if(obj.getString("letter").equalsIgnoreCase("DTC")){
					
					try {
						mrcode = obj.getString("mrcode");
						DBMrWiseBilling databaseHandler = new DBMrWiseBilling(getApplicationContext());
			            databaseHandler.open();
			            
			            JSONObject object = databaseHandler.getMrCodeWiseDetails(mrcode);
			            
			            databaseHandler.close();
			            
			            Date date = new Date();
			    		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			    		String formattedDate = sdf.format(date);
						
			            if(!obj.getString("billdate").equals("0")){
			            	++mrlivecount;
			            	totalbillsgeneratedtoday= totalbillsgeneratedtoday+Integer.parseInt(object.getString("billedday"));
			            }
			            
			            tobebilled = tobebilled+Integer.parseInt(object.getString("tobebilled"));
			            billed = billed+Integer.parseInt(object.getString("billed"));
			            
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
				
			}
			NumberFormat nf = new DecimalFormat("##");
			NumberFormat nf2 = new DecimalFormat("##.##");
			double percentage = billed/tobebilled;
			String currmthbillingpercentage = nf2.format((percentage)*100); 
            todaytarget.setText("TARGET\n"+nf.format(tobebilled));
            todaycompleted.setText("COMPLETED\n"+nf.format(billed));
            todaypercentage.setText("PERCENTAGE\n"+currmthbillingpercentage+"%");
            previoustarget.setText("TARGET\n"+nf.format(tobebilled-1));
            previouscompleted.setText("COMPLETED\n"+nf.format(billed-1));
            
            double percentage2 = (billed-100)/(tobebilled);
			String currmthbillingpercentage2 = nf2.format((percentage2)*100);
			
            previouspercentage.setText("PERCENTAGE\n"+currmthbillingpercentage2+"%");
            mrslive.setText("Total MRs Live\n"+mrlivecount);
            totalbillsgenerated.setText("Total Bills Generated\n"+totalbillsgeneratedtoday);
			dbAdapter.close();
			
		}

	//	ArrayAdapter<String> adapter = new ArrayAdapter<String>(Add_IP.this,android.R.layout.simple_dropdown_item_1line, listdata);
	//	rrnumberauto.setThreshold(1);
	//	rrnumberauto.setAdapter(adapter);

	} else {

	}
}

}
	
	
}
