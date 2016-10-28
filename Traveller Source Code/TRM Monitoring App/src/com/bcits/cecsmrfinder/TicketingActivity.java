package com.bcits.cecsmrfinder;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bcits.cecsmrfinder.DeviceStatusSearch.GetDeviceDetails;
import com.bcits.utils.SendRequest;

public class TicketingActivity extends Activity {

	LinearLayout datelaLayout,enterLayout,headerLv;
	Spinner ticketstatusspin;
	Button search;
	ListView listView;
	public static String responsefromserver = "";
	Calendar c;
	static final int DATE_PICKER_from = 1111;
	static final int DATE_PICKER_to = 0000;
	int year,month,day;
	EditText fromdate,username, todate; 
	RadioGroup rgViews;
	TextView marker;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		getActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM); 
		getActionBar().setCustomView(R.layout.custom_title_ticketingstatus);
		getActionBar().getCustomView().findViewById(R.id.title);
		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.custom_title_ticketingstatus, null);
		actionBar.setCustomView(view, new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
		actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		setContentView(R.layout.activity_ticketing);

		datelaLayout = (LinearLayout) findViewById(R.id.datelayout);
		enterLayout = (LinearLayout) findViewById(R.id.enterlayout);
		headerLv = (LinearLayout) findViewById(R.id.headerlistview);
		listView = (ListView) findViewById(R.id.listticket);
		fromdate = (EditText) findViewById(R.id.edfromdate);
		todate = (EditText) findViewById(R.id.edtodate);
		username = (EditText) findViewById(R.id.username);
		marker = (TextView) findViewById(R.id.marker);
		username.addTextChangedListener(new TextWatcher(){

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				//username.setText("XXXXXXXXXX");
			}

			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				//System.out.println(s+"     "+s.length()+"     "+(10-s.length()));
				int markercount = 10 - s.length();
				if(markercount==0){
					marker.setBackgroundResource(R.drawable.tick);
					marker.setText("");
				}else{
					marker.setBackgroundColor(Color.TRANSPARENT);
					marker.setText(""+markercount);	
				}
			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}
			
		});
		ticketstatusspin = (Spinner) findViewById(R.id.ticketstatusspin);
		search = (Button) findViewById(R.id.btnsearch);
		rgViews = (RadioGroup) findViewById(R.id.ticketgroup);
		FloatingActionButton fabButton = new FloatingActionButton.Builder(TicketingActivity.this)
		.withDrawable(getResources().getDrawable(R.drawable.plus))
		.withButtonColor(Color.TRANSPARENT)
		.withGravity(Gravity.BOTTOM | Gravity.START)
		.withMargins(16, 0, 0, 16)
		.create();
		fabButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(TicketingActivity.this, Registerticketactivity.class);
				startActivity(i);
			}
		});

		enterLayout.setVisibility(View.VISIBLE);
		c = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy",Locale.US);
		String formattedDate = df.format(c.getTime());

		fromdate.setText("From :"+formattedDate);
		todate.setText("To :"+formattedDate);
		new GetComplaints3().execute();
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		fromdate.setOnClickListener(new OnClickListener() {

			@SuppressWarnings("deprecation")
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showDialog(DATE_PICKER_from);
			}
		});

		fromdate.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				CustomTicketListAdapter adapter = null;
				listView.setAdapter(adapter);
				headerLv.setVisibility(View.GONE);

			}
		});
		todate.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				CustomTicketListAdapter adapter = null;
				listView.setAdapter(adapter);
				headerLv.setVisibility(View.GONE);

			}
		});
		username.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				CustomTicketListAdapter adapter = null;
				listView.setAdapter(adapter);
				headerLv.setVisibility(View.GONE);

			}
		});
		todate.setOnClickListener(new OnClickListener() {

			@SuppressWarnings("deprecation")
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showDialog(DATE_PICKER_to);
			}
		});
		rgViews.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				/*if(checkedId == R.id.daterbtn){
					datelaLayout.setVisibility(View.VISIBLE);
					enterLayout.setVisibility(View.GONE);
					ticketstatusspin.setVisibility(View.GONE);
					CustomTicketListAdapter adapter = null;
					listView.setAdapter(adapter);
					headerLv.setVisibility(View.GONE);


				}else */if(checkedId == R.id.ticketnorbtn){

					enterLayout.setVisibility(View.VISIBLE);
					datelaLayout.setVisibility(View.GONE);
					username.setText("");
					ticketstatusspin.setVisibility(View.GONE);
					CustomTicketListAdapter adapter = null;
					listView.setAdapter(adapter);
					headerLv.setVisibility(View.GONE);
					new GetComplaints3().execute();
				}else if(checkedId == R.id.statusrbtn){

					datelaLayout.setVisibility(View.GONE);
					enterLayout.setVisibility(View.GONE);
					ticketstatusspin.setVisibility(View.VISIBLE);
					ticketstatusspin.setSelection(0);
					CustomTicketListAdapter adapter = null;
					listView.setAdapter(adapter);
					headerLv.setVisibility(View.GONE);


				}
			}
		});

		ticketstatusspin.setOnItemSelectedListener(new OnItemSelectedListener() {


			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				CustomTicketListAdapter adapter = null;
				listView.setAdapter(adapter);
				headerLv.setVisibility(View.GONE);

			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}

		});
		search.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				if (rgViews.getCheckedRadioButtonId()==R.id.ticketnorbtn) {
					if (username.getText().toString().length()!=0) {
						new	GetComplaints().execute();

					}
					else {
						username.setError("Please Enter a Ticket No. ");
						new AlertDialog.Builder(new ContextThemeWrapper(
								TicketingActivity.this, R.style.CustomAlertDialogStyle))
								.setTitle("Error !")
								.setMessage("Please Enter a Ticket No. ").setCancelable(true)
								.setPositiveButton("OK", new DialogInterface.OnClickListener() {
								    public void onClick(DialogInterface dialog, int id) {
										dialog.dismiss();
								     }
								})
								.create()
								.show();
					}

				}
				else if (rgViews.getCheckedRadioButtonId()==R.id.statusrbtn) {
					if (ticketstatusspin.getSelectedItem().toString().equalsIgnoreCase("Select Status")) {
						new AlertDialog.Builder(new ContextThemeWrapper(
								TicketingActivity.this, R.style.CustomAlertDialogStyle))
								.setTitle("Error !")
								.setMessage("Please Select a valid status").setCancelable(true)
								.setPositiveButton("OK", new DialogInterface.OnClickListener() {
								    public void onClick(DialogInterface dialog, int id) {
										dialog.dismiss();
								     }
								})
								.create()
								.show();
					}
					else{
						new	GetComplaints2().execute();
					}

				}

			}
		});


	}
	@Override
	public void onBackPressed() {
		finish();
		hideSoftKeyboard();

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
	/*protected Dialog onCreateDialog(int id) {
		switch (id) {
		case DATE_PICKER_from:
			DatePickerDialog.OnDateSetListener pickerListenerfrom = new DatePickerDialog.OnDateSetListener() {

				// when dialog box is closed, below method will be called.
				@Override
				public void onDateSet(DatePicker view, int selectedYear,
						int selectedMonth, int selectedDay) {

					String months = null, days = null;
					year  = selectedYear;
					month = selectedMonth+1;
					day   = selectedDay;

					if (month<10) {
						months = "0"+month;
					}
					else {
						int mon = month;
						months = String.valueOf(mon);
					}
					if (day<10) {
						days = "0"+selectedDay;
					}
					else {
						days = String.valueOf(day);
					}

					SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy",Locale.US);
					String formattedDate = df.format(day+"/"+(month+1)+"/"+year+" ");
					System.out.println("To :"+formattedDate);
					 // Show selected date 
					fromdate.setText("From :"+days+"/"+months+"/"+year);
				}
			};
			// open datepicker dialog. 
			// set date picker for current date 
			// add pickerListener listner to date picker
			DatePickerDialog 	dialog= new DatePickerDialog(TicketingActivity.this, pickerListenerfrom, year, month,day);
			dialog.getDatePicker().setMinDate(new Date().getTime());
			return dialog;		

		case DATE_PICKER_to:
			DatePickerDialog.OnDateSetListener pickerListenerto = new DatePickerDialog.OnDateSetListener() {

				// when dialog box is closed, below method will be called.
				@Override
				public void onDateSet(DatePicker view, int selectedYear,
						int selectedMonth, int selectedDay) {

					String months = null, days = null;
					year  = selectedYear;
					month = selectedMonth+1;
					day   = selectedDay;

					if (month<10) {
						months = "0"+month;
					}
					else {
						int mon = month;
						months = String.valueOf(mon);
					}
					if (day<10) {
						days = "0"+selectedDay;
					}
					else {
						days = String.valueOf(day);
					}

					SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy",Locale.US);
					String formattedDate = df.format(day+"/"+(month+1)+"/"+year+" ");
					System.out.println("To :"+formattedDate);
					 // Show selected date 
					todate.setText("To :"+days+"/"+months+"/"+year);
				}
			};

			// open datepicker dialog. 
			// set date picker for current date 
			// add pickerListener listner to date picker
			DatePickerDialog 	dialog2= new DatePickerDialog(TicketingActivity.this, pickerListenerto, year, month,day);
			dialog2.getDatePicker().setMinDate(new Date().getTime());
			return dialog2;
		}
		return null;
	}*/


	public void hideSoftKeyboard() {
		if(getCurrentFocus()!=null) {
			InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
			inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
		}
	}
	private class GetComplaints extends AsyncTask<Void, Integer, Void> {
		String responsefromserver = null;
		ProgressDialog mProgressDialog =null;
		//TODO
		protected void onPreExecute() {
			mProgressDialog = new ProgressDialog(TicketingActivity.this,R.style.MyTheme2);
			mProgressDialog.setTitle("Please Wait!!!");
			mProgressDialog.setMessage("Please Wait While We Are Searching Your Previous Complaints...");
			//mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			mProgressDialog.setIndeterminate(false);
			mProgressDialog.setCancelable(false);
			mProgressDialog.show();
		}
		@Override
		protected void onProgressUpdate(Integer... values) {
			mProgressDialog.setProgress(values[0]);
		}
		protected Void doInBackground(Void... params) {
			try {
				JSONArray ja1 = new JSONArray();
				JSONObject jsonarray = new JSONObject();
				try {
					jsonarray.put("", "");
					ja1.put(jsonarray);

					SendRequest req = new SendRequest();
					String url1 = "/helpDesk/searchDocketNumber/"+username.getText().toString()+"/"+Login.sdoCode;
					responsefromserver = req.uploadToServer2(url1, ja1);

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
			headerLv.setVisibility(View.VISIBLE);
			
				if (responsefromserver == null) 
				{	
					hideSoftKeyboard();
					new AlertDialog.Builder(TicketingActivity.this)
					.setTitle("Info")
					.setMessage("Sorry,connection cannot made at this time. Server is down. Please try again later")
					.setPositiveButton("OK", null).show();
				}
				
				else if (responsefromserver.contains("html")) 
				{
					hideSoftKeyboard();
					new AlertDialog.Builder(TicketingActivity.this)
					.setTitle("Info")
					.setMessage("Sorry,connection cannot made at this time. Server is down. Please try again later")
					.setPositiveButton("OK", null).show();
				}
				else if (responsefromserver.equals("[]")) 
				{
					hideSoftKeyboard();
					new AlertDialog.Builder(TicketingActivity.this)
					.setTitle("Info")
					.setMessage("No Complaints Registered")
					.setPositiveButton("OK", null).show();
				}
				
				else 
				{
					try {
						hideSoftKeyboard();
						CustomTicketListAdapter adapter = new CustomTicketListAdapter(TicketingActivity.this,responsefromserver);
						listView.setAdapter(adapter);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
		}
	}
	
	private class GetComplaints2 extends AsyncTask<Void, Integer, Void> {
		String responsefromserver = null;
		ProgressDialog mProgressDialog =null;
		//TODO
		protected void onPreExecute() {
			mProgressDialog = new ProgressDialog(TicketingActivity.this,R.style.MyTheme2);
			mProgressDialog.setTitle("Please Wait!!!");
			mProgressDialog.setMessage("Please Wait While We Are Searching Your Previous Complaints...");
			//mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			mProgressDialog.setIndeterminate(false);
			mProgressDialog.setCancelable(false);
			mProgressDialog.show();
		}
		@Override
		protected void onProgressUpdate(Integer... values) {
			mProgressDialog.setProgress(values[0]);
		}
		protected Void doInBackground(Void... params) {
			try {
				
				int statusid = 0;
				if (ticketstatusspin.getSelectedItem().toString().equals("Registered&Assigned")) {
					statusid = 1;
				}
				if (ticketstatusspin.getSelectedItem().toString().equals("On Hold")) {
					statusid = 2;
				}if (ticketstatusspin.getSelectedItem().toString().equals("Resolved")) {
					statusid = 3;
				}if (ticketstatusspin.getSelectedItem().toString().equals("Reopened")) {
					statusid = 4;
				}
				JSONArray ja1 = new JSONArray();
				JSONObject jsonarray = new JSONObject();
				try {
					jsonarray.put("", "");
					ja1.put(jsonarray);

					SendRequest req = new SendRequest();
					String url1 = "/helpDesk/getTicketDataBasedOnStatusMobile/"+statusid+"/"+Login.sdoCode;
					responsefromserver = req.uploadToServer2(url1, ja1);

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
			headerLv.setVisibility(View.VISIBLE);
			
				if (responsefromserver == null) 
				{
					hideSoftKeyboard();
					new AlertDialog.Builder(TicketingActivity.this)
					.setTitle("Info")
					.setMessage("Sorry,connection cannot made at this time. Server is down. Please try again later")
					.setPositiveButton("OK", null).show();
				}
				
				else if (responsefromserver.contains("html")) 
				{
					hideSoftKeyboard();
					new AlertDialog.Builder(TicketingActivity.this)
					.setTitle("Info")
					.setMessage("Sorry,connection cannot made at this time. Server is down. Please try again later")
					.setPositiveButton("OK", null).show();
				}
				else if (responsefromserver.equals("[]")) 
				{
					hideSoftKeyboard();
					new AlertDialog.Builder(TicketingActivity.this)
					.setTitle("Info")
					.setMessage("No Complaints Registered")
					.setPositiveButton("OK", null).show();
				}
				
				else 
				{
					try {
						hideSoftKeyboard();
						CustomTicketListAdapter adapter = new CustomTicketListAdapter(TicketingActivity.this,responsefromserver);
						listView.setAdapter(adapter);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
		}
	}
	private class GetComplaints3 extends AsyncTask<Void, Integer, Void> {
		String responsefromserver = null;
		ProgressDialog mProgressDialog =null;
		//TODO
		protected void onPreExecute() {
			mProgressDialog = new ProgressDialog(TicketingActivity.this,R.style.MyTheme2);
			mProgressDialog.setTitle("Please Wait!!!");
			mProgressDialog.setMessage("Please Wait While We Are Searching Your Previous Complaints...");
			//mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			mProgressDialog.setIndeterminate(false);
			mProgressDialog.setCancelable(false);
			mProgressDialog.show();
		}
		@Override
		protected void onProgressUpdate(Integer... values) {
			mProgressDialog.setProgress(values[0]);
		}
		protected Void doInBackground(Void... params) {
			try {
				
				int statusid = 0;
				if (ticketstatusspin.getSelectedItem().toString().equals("Registered&Assigned")) {
					statusid = 1;
				}
				if (ticketstatusspin.getSelectedItem().toString().equals("On Hold")) {
					statusid = 2;
				}if (ticketstatusspin.getSelectedItem().toString().equals("Resolved")) {
					statusid = 3;
				}if (ticketstatusspin.getSelectedItem().toString().equals("Reopened")) {
					statusid = 4;
				}
				JSONArray ja1 = new JSONArray();
				JSONObject jsonarray = new JSONObject();
				try {
					jsonarray.put("", "");
					ja1.put(jsonarray);

					SendRequest req = new SendRequest();
					String url1 = "/helpDesk/getTicketDataBasedOnStatusMobile2/"+(Login.sdoCode.equals("1410")?"2120":Login.sdoCode);
					responsefromserver = req.uploadToServer2(url1, ja1);
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
			headerLv.setVisibility(View.VISIBLE);
			
				if (responsefromserver == null) 
				{
					hideSoftKeyboard();
					new AlertDialog.Builder(TicketingActivity.this)
					.setTitle("Info")
					.setMessage("Sorry,connection cannot made at this time. Server is down. Please try again later")
					.setPositiveButton("OK", null).show();
					
				}
				
				else if (responsefromserver.contains("html")) 
				{
					hideSoftKeyboard();
					new AlertDialog.Builder(TicketingActivity.this)
					.setTitle("Info")
					.setMessage("Sorry,connection cannot made at this time. Server is down. Please try again later")
					.setPositiveButton("OK", null).show();
					
				}
				else if (responsefromserver.equals("[]")) 
				{
					hideSoftKeyboard();
					new AlertDialog.Builder(TicketingActivity.this)
					.setTitle("Info")
					.setMessage("No Complaints Registered")
					.setPositiveButton("OK", null).show();
					
				}
				
				else 
				{
					try {
						hideSoftKeyboard();
						CustomTicketListAdapter adapter = new CustomTicketListAdapter(TicketingActivity.this,responsefromserver);
						listView.setAdapter(adapter);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
		}
	}
	
}
