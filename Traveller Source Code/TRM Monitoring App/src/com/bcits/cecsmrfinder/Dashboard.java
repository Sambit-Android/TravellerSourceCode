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
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.bcits.utils.MrWiseBillingProgressGetterSetter;
import com.bcits.utils.SendRequest;
import com.sqlite.cescip.DBDivision;
import com.sqlite.cescip.DBMrWiseBilling;

public class Dashboard extends Activity {
	LinearLayout linearDisconnection, linearConnection, linearMeterReplace,linaearNewMeter, contactusbtn, mapsurvery, downloadlayout,languagesettings,hide55,hide2,hide1,hide5;
	TextView sdotext, usernametext;
	String typeofsearch ="Circle";
	public static int hideMenuList,logoutDemoIdea, loadOneTimeMyInfo;
	String appmode = "MRFINDER";
	DBDivision  databasehandler ;
	DBMrWiseBilling databasehandlerMR;
	ArrayList<MrWiseBillingProgressGetterSetter> arrayComplaints;
	LinearLayout LinarLayoutLocation1,LinearLayoutLocation,linearLayoutArreas,linearLayoutArreas2,linearLayoutArreas1,billingProgress,linearMrLocator,linearArrears,linearPerformance;
	AlertDialog show;
	//ArrayList<String> listdata;
	ArrayList<String> loclistdata;
	ArrayList<String> arreaslist;
	ArrayList<String> arreaslist1;
	ArrayList<String> arreaslist2;
	TextView textviewChanger,maintext;
	RadioGroup radioGroup4;
	ProgressDialog mProgressDialog;
	Spinner circle,locationSpinner,spinenrAreas1,spinenrAreas2,subdivision;
	LinearLayout sbmstatus,consumerview,messaging,ticketting_status;
	
	
	String circle_load= "";
	String division_load="";
	String Subdivision_load="";
	String Sitecode_load="";
	String type_of_loc= "";
	static int loadOneTime = 0; // This reduce to sending url again and again.
	public static String value ="1";
	public static String month_arreas ="";
	public static String range_arreas ="5000 and 49999";
	String billmonth=null;
	int progress_temp =0;
	public static int flag =0;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM); 
		getActionBar().setCustomView(R.layout.custom_title);
		getActionBar().getCustomView().findViewById(R.id.title);
		
		hideSoftKeyboard();
		
		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.custom_title, null);
		actionBar.setCustomView(view, new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
		actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		
		
		setContentView(R.layout.dashboard2);
		consumerview = (LinearLayout) findViewById(R.id.consumerview);
		consumerview.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (haveNetworkConnection()){
				flag =1;
				Intent in = new Intent(getApplicationContext(),ConsumerHistorySearch.class);
				startActivity(in);
				}else{
					show = new AlertDialog.Builder(Dashboard.this).setTitle(getResources().getString(R.string.Info)).setMessage(getResources().getString(R.string.Internetnotavailable)).setPositiveButton(getResources().getString(R.string.OK), null).show();
				}
			}
		});
		sbmstatus = (LinearLayout) findViewById(R.id.sbmstatus);
		sbmstatus.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (haveNetworkConnection()){
				flag =2;
				Intent in = new Intent(getApplicationContext(),DeviceStatusSearch.class);
				startActivity(in);
				}else{
					show = new AlertDialog.Builder(Dashboard.this).setTitle(getResources().getString(R.string.Info)).setMessage(getResources().getString(R.string.Internetnotavailable)).setPositiveButton(getResources().getString(R.string.OK), null).show();
				}
			}
		});
		messaging = (LinearLayout) findViewById(R.id.message);
		messaging.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				flag =3;
				Intent in = new Intent(getApplicationContext(),UnderConstruction.class);
				startActivity(in);
			}
		});
		ticketting_status = (LinearLayout) findViewById(R.id.ticket);
		ticketting_status.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				flag =4;
				Intent in = new Intent(getApplicationContext(),TicketingActivity.class);
				startActivity(in);
			}
		});
		/*mapsurvery = (LinearLayout) findViewById(R.id.mapsurvey);
		languagesettings = (LinearLayout) findViewById(R.id.linaearlanguage);
		contactusbtn = (LinearLayout) findViewById(R.id.contactus_button);
		textviewChanger = (TextView) findViewById(R.id.textviewChanger);
		LinarLayoutLocation1 = (LinearLayout) findViewById(R.id.LinarLayoutLocation1);
		LinearLayoutLocation = (LinearLayout) findViewById(R.id.LinearLayoutLocation);
		linearLayoutArreas = (LinearLayout) findViewById(R.id.linearLayoutArreas);
		linearLayoutArreas1 = (LinearLayout) findViewById(R.id.linearLayoutArreas1);
		linearLayoutArreas2 = (LinearLayout) findViewById(R.id.linearLayoutArreas2);
		billingProgress = (LinearLayout)findViewById(R.id.billingProgress);*/
		billingProgress = (LinearLayout)findViewById(R.id.linearbillingprogress);
		linearMrLocator = (LinearLayout)findViewById(R.id.linearMrLocator);
		linearArrears = (LinearLayout)findViewById(R.id.linearArrears);
		linearPerformance = (LinearLayout)findViewById(R.id.linearPerformance); 
		//subdivision = (Spinner)findViewById(R.id.subdivision);

		/*linearLayoutArreas.setVisibility(View.GONE);
		linearLayoutArreas1.setVisibility(View.GONE);
		linearLayoutArreas2.setVisibility(View.GONE);

		hide5 = (LinearLayout) findViewById(R.id.hide5);
		circle = (Spinner)findViewById(R.id.spinner2);
		locationSpinner = (Spinner)findViewById(R.id.locationSpine);
		spinenrAreas1 = (Spinner)findViewById(R.id.spinenrAreas1);
		spinenrAreas2 = (Spinner)findViewById(R.id.spinenrAreas2);

		hide5.setVisibility(View.GONE);

		usernametext = (TextView) findViewById(R.id.username);*/
		sdotext = (TextView) findViewById(R.id.sdocode);
		usernametext = (TextView) findViewById(R.id.usernametext);
		usernametext.setText("WELCOME : "+Login.userName.toUpperCase());
		sdotext.setText(Login.sdoLocation.toUpperCase());
		//usernametext.setText("User : "+ Login.userDesignation);
		//BackgroundSyncServie.isActive =true;
		//Background auto Synchronisation
		//callSerive();
		// languagesettings.setEnabled(true);
		Date today = new Date(System.currentTimeMillis());
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String curDate = format.format(today);

		String Str = new String(curDate);
		String[] arr=Str.split("-");
		billmonth=arr[0]+arr[1];
		month_arreas=billmonth;
		
		
		

		new DownloadLocation().execute();
		new BillingProgressAsync().execute();
		
		
		
		
		
		
		//getSubdivision();

		/*arreaslist1 = new ArrayList<String>();
		arreaslist1.add(billmonth);
		ArrayAdapter<String> adapter301;
		adapter301 = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item, arreaslist1);
		adapter301.setDropDownViewResource(R.layout.doublespinneritems);
		spinenrAreas1.setAdapter(adapter301);
		spinenrAreas1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				month_arreas = billmonth;
			}
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});





		arreaslist2 = new ArrayList<String>();
		arreaslist2.add("500 and 1000");
		arreaslist2.add("1000 and 5000");
		arreaslist2.add("5000 and 10000");
		arreaslist2.add("10000 and 25000");
		arreaslist2.add("25000 and 50000");
		arreaslist2.add("50000 and 100000");
		arreaslist2.add("100001");
		ArrayAdapter<String> adapter311;
		adapter311 = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item, arreaslist2);
		adapter311.setDropDownViewResource(R.layout.doublespinneritems);
		spinenrAreas2.setAdapter(adapter311);
		spinenrAreas2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				range_arreas = spinenrAreas2.getSelectedItem().toString();
			}
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});*/







		/*radioGroup4 = (RadioGroup) findViewById(R.id.radiochoose);
		radioGroup4.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				int id = radioGroup4.getCheckedRadioButtonId();
				View radioButton = radioGroup4.findViewById(id);
				if (radioButton.getId() == R.id.radiolocation) {
					appmode = "MRFINDER";
					LinarLayoutLocation1.setVisibility(View.VISIBLE);
					LinearLayoutLocation.setVisibility(View.VISIBLE);
					linearLayoutArreas1.setVisibility(View.GONE);
					linearLayoutArreas2.setVisibility(View.GONE);
				} else if (radioButton.getId() == R.id.radioarreas) {
					appmode = "ARREARS";
					LinarLayoutLocation1.setVisibility(View.VISIBLE);
					LinearLayoutLocation.setVisibility(View.VISIBLE);
					linearLayoutArreas1.setVisibility(View.VISIBLE);
					linearLayoutArreas2.setVisibility(View.VISIBLE);
				}else if (radioButton.getId() == R.id.radiobillingprogress) {
					appmode = "BILLINGPROGRESS";
					LinarLayoutLocation1.setVisibility(View.VISIBLE);
					LinearLayoutLocation.setVisibility(View.VISIBLE);
					linearLayoutArreas1.setVisibility(View.GONE);
					linearLayoutArreas2.setVisibility(View.GONE);
				}else{
					appmode = "PERFORMANCE";
					LinarLayoutLocation1.setVisibility(View.VISIBLE);
					LinearLayoutLocation.setVisibility(View.VISIBLE);
					linearLayoutArreas1.setVisibility(View.GONE);
					linearLayoutArreas2.setVisibility(View.GONE);


				}
			}
		});*/
		
		
		


		/*imgLogout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (AmrMethods.doubleClick()) {
					AlertDialog.Builder builder = new AlertDialog.Builder(
							Dashboard.this);
					builder.setMessage(getResources().getString(R.string.Doyouwanttologout))
					.setPositiveButton(getResources().getString(R.string.Yes),
							new DialogInterface.OnClickListener() {

						@Override
						public void onClick(
								DialogInterface dialog,
								int which) {
							finish();
							Login.userName = null;
							Intent intent = new Intent(
									Dashboard.this, Login.class);
							startActivity(intent);
						}
					}).setNegativeButton(getResources().getString(R.string.No), null).show();
				}
			}
		});*/


		billingProgress.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				
				if (haveNetworkConnection()){
					
						
						Intent intent = new Intent(
								Dashboard.this, MrSummaryActivity.class);
						startActivity(intent);
						
				}
				else{
					show = new AlertDialog.Builder(Dashboard.this).setTitle(getResources().getString(R.string.Info)).setMessage(getResources().getString(R.string.Internetnotavailable)).setPositiveButton(getResources().getString(R.string.OK), null).show();
				}
				
				
			}
		});
		
		
		linearMrLocator.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				
				if (haveNetworkConnection()){
					
						
						Intent intent = new Intent(
								Dashboard.this, MapSurvey.class);
						startActivity(intent);
						
				}
				else{
					show = new AlertDialog.Builder(Dashboard.this).setTitle(getResources().getString(R.string.Info)).setMessage(getResources().getString(R.string.Internetnotavailable)).setPositiveButton(getResources().getString(R.string.OK), null).show();
				}
				
				
			}
		});
		
		
		linearArrears.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				
				if (haveNetworkConnection()){
					
						
						Intent intent = new Intent(
								Dashboard.this, ArrearsRangeGridActivity.class);
						startActivity(intent);
						
				}
				else{
					show = new AlertDialog.Builder(Dashboard.this).setTitle(getResources().getString(R.string.Info)).setMessage(getResources().getString(R.string.Internetnotavailable)).setPositiveButton(getResources().getString(R.string.OK), null).show();
				}
				
				
			}
		});
		
		linearPerformance.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				
				if (haveNetworkConnection()){
					
						
						Intent intent = new Intent(
								Dashboard.this, MapSurveyPerformance.class);
						startActivity(intent);
						
				}
				else{
					show = new AlertDialog.Builder(Dashboard.this).setTitle(getResources().getString(R.string.Info)).setMessage(getResources().getString(R.string.Internetnotavailable)).setPositiveButton(getResources().getString(R.string.OK), null).show();
				}
				
				
			}
		});


		/*mapsurvery.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if (haveNetworkConnection()){
					if(typeofsearch.equalsIgnoreCase("Circle")){
						DBDivision db=new DBDivision(getBaseContext());
						db.open();
						Cursor cursor =db.getSendingValueCircle(circle_load);
						if(cursor.moveToNext())
						{
							value =cursor.getString(cursor.getColumnIndex(DBDivision.SITECODE));
							value =value.substring(0,1);
						}
						db.close();
					}
					else if(typeofsearch.equalsIgnoreCase("Division")){
						DBDivision db=new DBDivision(getBaseContext());
						db.open();
						Cursor cursor =db.getSendingValueDivision(division_load);
						if(cursor.moveToNext())
						{
							value =cursor.getString(cursor.getColumnIndex(DBDivision.SITECODE));
							value =value.substring(0,2);
						}
						db.close();
					}
					else if(typeofsearch.equalsIgnoreCase("SubDivision")){
						DBDivision db=new DBDivision(getBaseContext());
						db.open();
						Cursor cursor =db.getSendingValueSubDiv(Subdivision_load);
						if(cursor.moveToNext())
						{
							value =cursor.getString(cursor.getColumnIndex(DBDivision.SITECODE));
							value =value.substring(0,3);
						}
						db.close();
					}
					else if(typeofsearch.equalsIgnoreCase("Section")){
						value = Sitecode_load;
					}	

					if(appmode.equalsIgnoreCase("MRFINDER"))
					{	

						Intent intent = new Intent(Dashboard.this, MapSurvey.class);
						startActivity(intent);
					}
					else if(appmode.equalsIgnoreCase("PERFORMANCE"))
					{	



						Intent intent = new Intent(Dashboard.this, MapSurveyPerformance.class);
						startActivity(intent);
					}
					else if(appmode.equalsIgnoreCase("BILLINGPROGRESS"))
					{	



						if (haveNetworkConnection()){
							if(typeofsearch.equalsIgnoreCase("SubDivision")){
								DBDivision db=new DBDivision(getBaseContext());
								db.open();
								Cursor cursor =db.getSendingValueSubDiv(Subdivision_load);
								if(cursor.moveToNext())
								{
									value =cursor.getString(cursor.getColumnIndex(DBDivision.SITECODE));
									value =value.substring(0,3);
								}
								db.close();
								
								Intent intent = new Intent(
										Dashboard.this, BillingProgress.class);
								startActivity(intent);
								
							}
							else if(typeofsearch.equalsIgnoreCase("Section")){
								value = Sitecode_load;
								
								Intent intent = new Intent(
										Dashboard.this, BillingProgress.class);
								startActivity(intent);
								
							}else{
								
								show = new AlertDialog.Builder(Dashboard.this).setTitle(getResources().getString(R.string.Info)).setMessage(
										"Please Select Section To See Billing Progress").setPositiveButton(getResources().getString(R.string.OK), null).show();
								
								
							}

							

						}
						else{
							show = new AlertDialog.Builder(Dashboard.this).setTitle(getResources().getString(R.string.Info)).setMessage(getResources().getString(R.string.Internetnotavailable)).setPositiveButton(getResources().getString(R.string.OK), null).show();
						}
					}
					
					else{

						Intent intent = new Intent(Dashboard.this, MapSurveyArreas.class);
						startActivity(intent);

					}

				}
				else{
					show = new AlertDialog.Builder(Dashboard.this).setTitle(getResources().getString(R.string.Info)).setMessage(getResources().getString(R.string.Internetnotavailable)).setPositiveButton(getResources().getString(R.string.OK), null).show();
				}

			}
		});

		contactusbtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

					Intent intent = new Intent(Dashboard.this, Settings.class);
				startActivity(intent);
				 
			}
		});*/
		/*loclistdata = new ArrayList<String>();
		loclistdata.add("Circle");
		loclistdata.add("Division");
		loclistdata.add("SubDivision");
		loclistdata.add("Section");*/
		/*String[] array = getResources().getStringArray(R.array.loginCategory);
		ArrayAdapter<String> adapter300;
		adapter300 = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item, array);
		adapter300.setDropDownViewResource(R.layout.doublespinneritems);
		locationSpinner.setAdapter(adapter300);
		locationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


				type_of_loc = locationSpinner.getSelectedItem().toString();
				if(type_of_loc.equalsIgnoreCase("Circle")){
					typeofsearch ="Circle";
					textviewChanger.setText("Choose a Circle");
					DBDivision db=new DBDivision(getBaseContext());
					db.open();
					Cursor cursor =db.getOfflineCircles();
					if(cursor == null)
					{
						Toast.makeText(getApplicationContext(),"No Circle Found",Toast.LENGTH_LONG).show();
					}
					else
					{
						int k=0;
						ArrayList<String>	listdata = new ArrayList<String>();

						while (cursor != null && cursor.moveToPosition(k)) {
							k++;
							listdata.add(cursor.getColumnIndex(DBDivision.CIRCLE), cursor.getString(0));
						}
						db.close();
						ArrayAdapter<String> adapter3;
						adapter3 = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item, listdata);
						adapter3.setDropDownViewResource(R.layout.doublespinneritems);
						circle.setAdapter(adapter3);
						circle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
							public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
								circle_load = circle.getSelectedItem().toString();
							}
							public void onNothingSelected(AdapterView<?> parent) {
							}
						});
					}
				}

				else if(type_of_loc.equalsIgnoreCase("Division")){
					typeofsearch ="Division";
					textviewChanger.setText("Choose a Division");
					DBDivision db=new DBDivision(getBaseContext());
					db.open();
					Cursor cursor =db.getOfflineDivisions();
					if(cursor == null)
					{
						Toast.makeText(getApplicationContext(),"No Division Found",Toast.LENGTH_LONG).show();
					}
					else
					{
						int k=0;
						ArrayList<String>	 listdata = new ArrayList<String>();

						while (cursor != null && cursor.moveToPosition(k)) {
							k++;
							listdata.add(cursor.getColumnIndex(DBDivision.DIVISION), cursor.getString(0));
						}
						db.close();
						ArrayAdapter<String> adapter3;
						adapter3 = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item, listdata);
						adapter3.setDropDownViewResource(R.layout.doublespinneritems);
						circle.setAdapter(adapter3);
						circle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
							public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
								division_load = circle.getSelectedItem().toString();
							}
							public void onNothingSelected(AdapterView<?> parent) {
							}
						});
					}
				}



				else if(type_of_loc.equalsIgnoreCase("SubDivision")){
					typeofsearch ="SubDivision";
					textviewChanger.setText("Choose a SubDivision");
					DBDivision db=new DBDivision(getBaseContext());
					db.open();
					Cursor cursor =db.getOfflineSubDivisions();
					if(cursor == null)
					{
						Toast.makeText(getApplicationContext(),"No SubDivision Found",Toast.LENGTH_LONG).show();
					}
					else
					{
						int k=0;
						ArrayList<String>		listdata = new ArrayList<String>();

						while (cursor != null && cursor.moveToPosition(k)) {
							k++;
							listdata.add(cursor.getColumnIndex(DBDivision.SUBDIV), cursor.getString(0));
						}
						db.close();
						ArrayAdapter<String> adapter3;
						adapter3 = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item, listdata);
						adapter3.setDropDownViewResource(R.layout.doublespinneritems);
						circle.setAdapter(adapter3);
						circle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
							public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
								Subdivision_load = circle.getSelectedItem().toString();
							}
							public void onNothingSelected(AdapterView<?> parent) {
							}
						});
					}
				}


				else if(type_of_loc.equalsIgnoreCase("Section")){
					typeofsearch ="Section";
					textviewChanger.setText("Choose a Section");
					DBDivision db=new DBDivision(getBaseContext());
					db.open();
					Cursor cursor =db.getOfflineSDOwithName();
					if(cursor == null)
					{
						Toast.makeText(getApplicationContext(),"No Section Found",Toast.LENGTH_LONG).show();
					}
					else
					{
						int k=0;
						ArrayList<String>	listdata = new ArrayList<String>();

						while (cursor != null && cursor.moveToPosition(k)) {
							k++;
							String site =cursor.getString(cursor.getColumnIndex(DBDivision.SITECODE));
							String site_name =cursor.getString(cursor.getColumnIndex(DBDivision.SECT));
							listdata.add(site_name+"-"+site);
						}
						db.close();
						ArrayAdapter<String> adapter3;
						adapter3 = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item, listdata);
						adapter3.setDropDownViewResource(R.layout.doublespinneritems);
						circle.setAdapter(adapter3);
						circle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
							public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
								Sitecode_load = circle.getSelectedItem().toString();
								String[] parts = Sitecode_load.split("-");
								Sitecode_load = parts[1];
							}
							public void onNothingSelected(AdapterView<?> parent) {
							}
						});
					}
				}
			}
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});*/

	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

	
		switch (item.getItemId()) {
	

		case R.id.action_logout:
			AlertDialog.Builder adb = new AlertDialog.Builder(Dashboard.this);
			adb.setIcon(R.drawable.logout_alert);
			adb.setTitle("LogOut").setMessage("DO YOU WANT TO LOGOUT ?");
			adb.setPositiveButton("YES", new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface arg0, int arg1) {
					
					logoutDemoIdea = 1; // clear the remember me option while logout
					Dashboard.loadOneTime = 0; // reduce the url sending  dash board
					loadOneTimeMyInfo = 0; // reduce the url  info activity

					Intent intent = new Intent(Dashboard.this, Login.class);
					finish();
					startActivity(intent);

				}
			});

			adb.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();

				}
			});

			AlertDialog alert = adb.create();
			alert.show();

			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
	
	public class BillingProgressAsync extends	AsyncTask<Void, Void, Void> {

		String responsefromserver = null;
		private ProgressDialog mProgressDialog;
		JSONArray json = null;

		protected void onPreExecute() {
			mProgressDialog = new ProgressDialog(Dashboard.this,R.style.MyTheme2);
			mProgressDialog.setMessage("Please Wait While We Are Setting Up The Application ...");
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
				
				 new AlertDialog.Builder(Dashboard.this).setTitle(getResources().getString(R.string.Info)).setMessage("Unable To Get Billing Progress")
				 .setPositiveButton(getResources().getString(R.string.OK), null).show();
				
			}
		
			else if (responsefromserver.contains("NoData")) {
				 new AlertDialog.Builder(Dashboard.this).setTitle(getResources().getString(R.string.Info)).setMessage("No Data Found For This SDO")
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


						databasehandlerMR = new DBMrWiseBilling(getBaseContext());
						databasehandlerMR.open();
						try {
							databasehandlerMR.deleteAll();
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

								
								databasehandlerMR.insert(section, sitecode,	 mrcode, tobebilled, billed, unbilled,	 tobebilledday,	 billedday,unbilledday,mrname,mrmobilenumber);





							} catch (Exception e) {
								e.printStackTrace();
							}

						}
						databasehandlerMR.close();
						
						
						databasehandlerMR.open();
						
						 arrayComplaints = databasehandlerMR.getAllBillingData();
						 
						 databasehandlerMR.close();
						
					}else {
						
						 new AlertDialog.Builder(Dashboard.this).setTitle(getResources().getString(R.string.Info)).setMessage("Unable To Connect Please Try Again Later")
						 .setPositiveButton(getResources().getString(R.string.OK), null).show();
						
					}
					
					
					
					
				} catch (JSONException e2) {
					e2.printStackTrace();
				}
				
			
		}
			
			
			
			
		}



	}


	public class DownloadLocation extends	AsyncTask<Void, Void, Void> {
		String responsefromserver = null;
		private ProgressDialog mProgressDialog;
		JSONArray json = null;

		protected void onPreExecute() {
			

			
			mProgressDialog = new ProgressDialog(Dashboard.this,R.style.MyTheme2);
			mProgressDialog.setMessage("Fetching Location Details...");
			mProgressDialog.setCancelable(false);
			mProgressDialog.show();
		}

		protected Void doInBackground(Void... params) {
			try {
				JSONArray ja1 = new JSONArray();
				JSONObject jsonarray = new JSONObject();


				ja1.put(jsonarray);

				SendRequest req = new SendRequest();
				responsefromserver = req.uploadToServer("location", ja1);

			} catch (Exception e) {
				e.printStackTrace();
			}

			return null;
		}

		protected void onPostExecute(Void unused) {
			mProgressDialog.dismiss();
			if (responsefromserver == null) {

				new AlertDialog.Builder(Dashboard.this).setTitle(getResources().getString(R.string.Info)).setMessage("Unable To DownLoad Location")
				.setPositiveButton(getResources().getString(R.string.OK), null).show();

			}

			else if (responsefromserver.equals("NoData")) {
				new AlertDialog.Builder(Dashboard.this).setTitle(getResources().getString(R.string.Info)).setMessage(getResources().
						getString(R.string.InvalidCredentials)).setPositiveButton(getResources().getString(R.string.OK), null).show();
			}

			else  {

				try {
					json = new JSONArray(responsefromserver);
				

				databasehandler = new DBDivision(getBaseContext());
				databasehandler.open();
				if (databasehandler.checkDataBase()) {
					databasehandler.deleteAll();
				}
				databasehandler.close();

				} catch (JSONException e2) {
					e2.printStackTrace();
				}
				
				databasehandler = new DBDivision(getBaseContext());
				databasehandler.open();

				try {
				
				for (int i = 0; i < json.length(); i++) {

						
						//Asst_json_obj = json.optJSONObject("consumerData");
						JSONObject Asst_json_obj  = json.getJSONObject(i) ;

						String zone = "";
						String section = "";
						String company = "";
						String circle = "";
						String dbuser = "";
						String division = "";
						String sitecode = "";
						String subdivision = "";
						String newsitecode = "";

						if( 		Asst_json_obj.isNull("zone") 
								|| Asst_json_obj.get("zone") == null
								|| Asst_json_obj.get("zone") == "" ) 
						{

							Asst_json_obj.put("zone", "-");

							zone = Asst_json_obj.getString("zone");

						} else {

							zone = Asst_json_obj.getString("zone");
						}

						if( 		Asst_json_obj.isNull("section") 
								|| Asst_json_obj.get("section") == null
								|| Asst_json_obj.get("section") == "" ) 
						{

							Asst_json_obj.put("section", "-");

							section = Asst_json_obj.getString("section");

						} else {

							section = Asst_json_obj.getString("section");
						}

						if( 		Asst_json_obj.isNull("company") 
								|| Asst_json_obj.get("company") == null
								|| Asst_json_obj.get("company") == "" ) 
						{

							Asst_json_obj.put("company", "-");

							company = Asst_json_obj.getString("company");

						} else {

							company = Asst_json_obj.getString("company");
						}

						if( 		Asst_json_obj.isNull("circle") 
								|| Asst_json_obj.get("circle") == null
								|| Asst_json_obj.get("circle") == "" ) 
						{

							Asst_json_obj.put("circle", "-");

							circle = Asst_json_obj.getString("circle");

						} else {

							circle = Asst_json_obj.getString("circle");
						}

						if( 		Asst_json_obj.isNull("dbuser") 
								|| Asst_json_obj.get("dbuser") == null
								|| Asst_json_obj.get("dbuser") == "" ) 
						{

							Asst_json_obj.put("dbuser", "-");

							dbuser = Asst_json_obj.getString("dbuser");

						} else {

							dbuser = Asst_json_obj.getString("dbuser");
						}

						if( 		Asst_json_obj.isNull("division") 
								|| Asst_json_obj.get("division") == null
								|| Asst_json_obj.get("division") == "" ) 
						{

							Asst_json_obj.put("division", "-");

							division = Asst_json_obj.getString("division");

						} else {

							division = Asst_json_obj.getString("division");
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

						if( 		Asst_json_obj.isNull("subdivision") 
								|| Asst_json_obj.get("subdivision") == null
								|| Asst_json_obj.get("subdivision") == "" ) 
						{

							Asst_json_obj.put("subdivision", "-");

							subdivision = Asst_json_obj.getString("sitecode");

						} else {

							subdivision = Asst_json_obj.getString("subdivision");
						}

						if( 		Asst_json_obj.isNull("newsitecode") 
								|| Asst_json_obj.get("newsitecode") == null
								|| Asst_json_obj.get("newsitecode") == "" ) 
						{

							Asst_json_obj.put("newsitecode", "-");

							newsitecode = Asst_json_obj.getString("newsitecode");

						} else {

							newsitecode = Asst_json_obj.getString("newsitecode");
						}




						databasehandler.insert(company, zone,	 circle, division, subdivision, section,	 sitecode,	 dbuser);





					

				}} catch (Exception e) {
					e.printStackTrace();
				}
				databasehandler.close();
			}

		}

	}



	@Override
	protected void onResume() {
		if(Login.sdoCode ==null || Login.sdoCode.isEmpty () || Login.sdoCode.trim().equals("")){
			show =new AlertDialog.Builder(Dashboard.this).setCancelable(false).setMessage(getResources().getString(R.string.sessionexpired)).setPositiveButton(getResources().getString(R.string.OK), new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					Intent intent = new Intent(Dashboard.this, Login.class);
					startActivity(intent);

				}
			}).show();
		}
		hideSoftKeyboard();
		super.onResume();
		
	}


	@Override
	public void onBackPressed() {
		AlertDialog.Builder adb = new AlertDialog.Builder(Dashboard.this);
		adb.setTitle(getResources().getString(R.string.Exit)).setMessage(getResources().getString(R.string.Doyouwantexit));
		adb.setPositiveButton(getResources().getString(R.string.Yes), new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {

				Intent intent = new Intent(getApplicationContext(), Login.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				intent.putExtra("exit", "exit");
				finish();
				startActivity(intent);

			}
		});
		adb.setNegativeButton(getResources().getString(R.string.No), new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {

				dialog.dismiss();

			}
		});
		AlertDialog alert = adb.create();
		alert.show();
	}
	
	private void getSubdivision(){
		
		
		DBDivision db=new DBDivision(getBaseContext());
		db.open();
		Cursor cursor =db.getSendingValueSubDiv(Subdivision_load);
		if(cursor.moveToNext())
		{
			value =cursor.getString(cursor.getColumnIndex(DBDivision.SITECODE));
			value =value.substring(0,3);
		}
		
		db.close();
		
		db.open();
		
		int k=0;
		ArrayList<String>	listdata = new ArrayList<String>();

		while (cursor != null && cursor.moveToPosition(k)) {
			k++;
			String site =cursor.getString(cursor.getColumnIndex(DBDivision.SITECODE));
			String site_name =cursor.getString(cursor.getColumnIndex(DBDivision.SECT));
			listdata.add(site_name+"-"+site);
		}
		
		
		ArrayAdapter<String> adapter3;
		adapter3 = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item, listdata);
		adapter3.setDropDownViewResource(R.layout.doublespinneritems);
		subdivision.setAdapter(adapter3);
		subdivision.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				Sitecode_load = subdivision.getSelectedItem().toString();
				String[] parts = Sitecode_load.split("-");
				Sitecode_load = parts[1];
			}
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});
		
		db.close();
		
		
	}
	
	
	private boolean haveNetworkConnection() {
		boolean haveConnectedMobile = false;
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo[] netInfo = cm.getAllNetworkInfo();
		for (NetworkInfo ni : netInfo) {
			if (ni.getTypeName().equalsIgnoreCase("MOBILE")
					|| ni.getTypeName().equalsIgnoreCase("wifi"))
				if (ni.isConnected())
					haveConnectedMobile = true;
		}
		return haveConnectedMobile;
	}
	public void hideSoftKeyboard() {
	    if(getCurrentFocus()!=null) {
	        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
	        inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
	    }
	}
	/*@Override
	public void onResume(){
	    super.onResume();
	    hideSoftKeyboard();

	}*/

}
