package com.bcits.cecsmrfinder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.bcits.utils.MrWiseBillingProgressGetterSetter;
import com.bcits.utils.SendRequest;
import com.bcits.utils.TransactionDOAdapter;
import com.sqlite.cescip.DBMrWiseBilling;




public class BillingProgress extends Activity{
	
	ArrayList<MrWiseBillingProgressGetterSetter> arrayComplaints;
	DBMrWiseBilling databasehandler;
	TransactionDOAdapter adaptContents;
	ListView listView;
	TextView todaysdatesummary;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		getActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM); 
		getActionBar().setCustomView(R.layout.custom_title_billing_progress);
		getActionBar().getCustomView().findViewById(R.id.title);
		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.custom_title_billing_progress, null);
		actionBar.setCustomView(view, new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
		actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.billing_progress);
		listView=(ListView)findViewById(R.id.allcashshow);
		todaysdatesummary = (TextView)findViewById(R.id.todaysdatesummary);
		
		
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String formattedDate = sdf.format(date);

		//new BillingProgressAsync().execute();
		
		databasehandler = new DBMrWiseBilling(getBaseContext());
		databasehandler.open();
		
		 arrayComplaints = databasehandler.getAllBillingData();
		 try {
			 
		
		adaptContents = new TransactionDOAdapter(
				BillingProgress.this,
				R.layout.custom_allcash_list, arrayComplaints);
		
		listView.setAdapter(adaptContents);
		listView.setTextFilterEnabled(true);		
		// we register for the contextmneu        
		registerForContextMenu(listView); 
		todaysdatesummary.setText("As On - " + formattedDate);
		
		
		 } catch (Exception e) {
			 
				e.printStackTrace();
				
			}
		
		databasehandler.close();
		

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
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	}


	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}


	public class BillingProgressAsync extends	AsyncTask<Void, Void, Void> {

		String responsefromserver = null;
		private ProgressDialog mProgressDialog;
		JSONArray json = null;

		protected void onPreExecute() {
			mProgressDialog = new ProgressDialog(BillingProgress.this,R.style.MyTheme2);
			mProgressDialog.setMessage("Please Wait While We Are Fetching Data For You ...");
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
				
				 new AlertDialog.Builder(BillingProgress.this).setTitle(getResources().getString(R.string.Info)).setMessage("Unable To Get Billing Progress")
				 .setPositiveButton(getResources().getString(R.string.OK), null).show();
				
			}
		
			else if (responsefromserver.contains("NoData")) {
				 new AlertDialog.Builder(BillingProgress.this).setTitle(getResources().getString(R.string.Info)).setMessage("No Data Found For This SDO")
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
								


								databasehandler.insert(section, sitecode,	 mrcode, tobebilled, billed, unbilled,	 tobebilledday,	 billedday,unbilledday,mrname,mrmobilenumber);





							} catch (Exception e) {
								e.printStackTrace();
							}

						}
						databasehandler.close();
						
						
						databasehandler.open();
						
						 arrayComplaints = databasehandler.getAllBillingData();
						 
						 try {
							 
						
						adaptContents = new TransactionDOAdapter(
								BillingProgress.this,
								R.layout.custom_allcash_list, arrayComplaints);
						
						listView.setAdapter(adaptContents);
						listView.setTextFilterEnabled(true);		
						// we register for the contextmneu        
						registerForContextMenu(listView); 
						
						
						 } catch (Exception e) {
							 
								e.printStackTrace();
							}
						
						databasehandler.close();
						
					}else {
						
						 new AlertDialog.Builder(BillingProgress.this).setTitle(getResources().getString(R.string.Info)).setMessage("Unable To Connect Please Try Again Later")
						 .setPositiveButton(getResources().getString(R.string.OK), null).show();
						
					}
					
					
					
					
				} catch (JSONException e2) {
					e2.printStackTrace();
				}
				
			
		}
			
			
			
			
		}



	}


	@Override
	public void onBackPressed() {
		//super.onBackPressed();
		
		
		finish();
		
	}




}