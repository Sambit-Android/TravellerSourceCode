package com.bcits.cecsmrfinder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.bcits.utils.SendRequest;
import com.sqlite.cescip.DBDivision;
import com.sqlite.cescip.DBMrWiseBilling;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class DeviceStatusSearch extends Activity {

	public static String searchType;
	EditText ed_search;
	TextView devicecount;
	String edValue;
	Button search;
	RadioButton allocated,unallocated,total;
	ListView listView;
	public static String responsefromserver = "";
	JSONArray allocatedjson,unallocatedjson;
	View header;
	LinearLayout ll;
	RadioGroup rg;
	TextView title;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		getActionBar().setCustomView(R.layout.custom_title_sbmstatus);
		getActionBar().getCustomView().findViewById(R.id.title);

		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		LayoutInflater inflater = (LayoutInflater) this
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.custom_title_sbmstatus, null);
		actionBar.setCustomView(view, new ActionBar.LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.MATCH_PARENT));
		actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);

		setContentView(R.layout.device_status_search);
		rg = (RadioGroup) findViewById(R.id.toggle);
		devicecount = (TextView) findViewById(R.id.devicecount);
		title = (TextView) findViewById(R.id.title);
		allocated = (RadioButton) findViewById(R.id.allocated);
		unallocated = (RadioButton) findViewById(R.id.unallocated);
		total = (RadioButton) findViewById(R.id.total);
		hideSoftKeyboard();
		ed_search = (EditText) findViewById(R.id.username);
		listView = (ListView) findViewById(R.id.list);
		search = (Button) findViewById(R.id.btnsearch);
		header = (View) findViewById(R.id.header);
		header.setVisibility(View.GONE);
		new GetDeviceDetails().execute();
		total.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				title.setText("Total Mobiles");
				edValue = ed_search.getText().toString().trim();
				//rg.clearCheck();
				header.setVisibility(View.VISIBLE);
				CustomDeviceStatusSearchAdapter adapter;
				try {
					listView.setVisibility(View.VISIBLE);
					JSONArray arr = new JSONArray(responsefromserver);
					/*DBMrWiseBilling databasehandler = new DBMrWiseBilling(DeviceStatusSearch.this);
					databasehandler.open();
					
					
					JSONArray mrgriddata = databasehandler.getMrWiseReportData();
					databasehandler.close();*/
					adapter = new CustomDeviceStatusSearchAdapter(
							DeviceStatusSearch.this,arr);
					listView.setAdapter(adapter);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				//listView.setVisibility(View.VISIBLE);
				
			}
		});
		allocated.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				title.setText("Allocated Mobiles");
				listView.setVisibility(View.VISIBLE);
				try {
					header.setVisibility(View.VISIBLE);
					//allocatedjson = new JSONArray();
					JSONArray arr = new JSONArray(responsefromserver);
					/*for (int i = 0; i < arr.length(); i++) {
						JSONObject obj = arr.getJSONObject(i);
						
						if(obj.getString("allocatedflag").equalsIgnoreCase("ALLOCATED")){
							allocatedjson.put(obj);
						}
					}*/
					
					/*DBMrWiseBilling databasehandler = new DBMrWiseBilling(DeviceStatusSearch.this);
					databasehandler.open();
					
					
					JSONArray mrgriddata = databasehandler.getMrWiseReportData();
					databasehandler.close();*/
					CustomDeviceStatusSearchAdapter adapter = new CustomDeviceStatusSearchAdapter(
							DeviceStatusSearch.this,allocatedjson);
					listView.setAdapter(adapter);
					
					
						
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		unallocated.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				title.setText("Not Allocated Mobiles");
				listView.setVisibility(View.VISIBLE);
				try {
					header.setVisibility(View.VISIBLE);
					//unallocatedjson = new JSONArray();
					JSONArray arr = new JSONArray(responsefromserver);
					/*for (int i = 0; i < arr.length(); i++) {
						JSONObject obj = arr.getJSONObject(i);
						
						if(obj.getString("allocatedflag").equalsIgnoreCase("NOT ALLOCATED")){
							unallocatedjson.put(obj);
						}
					}*/
					/*DBMrWiseBilling databasehandler = new DBMrWiseBilling(DeviceStatusSearch.this);
					databasehandler.open();
					
					
					JSONArray mrgriddata = databasehandler.getMrWiseReportData();
					databasehandler.close();*/
					CustomDeviceStatusSearchAdapter adapter = new CustomDeviceStatusSearchAdapter(
							DeviceStatusSearch.this,unallocatedjson);
					listView.setAdapter(adapter);
					
					
						
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
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
		// super.onBackPressed();

		/*
		 * Intent intent = new Intent(MrSummaryActivity.this,Dashboard.class);
		 * startActivity(intent);
		 */
		finish();

	}

	public void hideSoftKeyboard() {
		if (getCurrentFocus() != null) {
			InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
			inputMethodManager.hideSoftInputFromWindow(getCurrentFocus()
					.getWindowToken(), 0);
		}
	}

	public class GetDeviceDetails extends AsyncTask<Void, Void, Void> {

		private ProgressDialog mProgressDialog;
		JSONArray json = null;

		protected void onPreExecute() {
			mProgressDialog = new ProgressDialog(DeviceStatusSearch.this,R.style.MyTheme2);
			mProgressDialog.setMessage("Fetching Device Allocation Details...");
			mProgressDialog.setCancelable(false);
			mProgressDialog.show();
		}

		protected Void doInBackground(Void... params) {
			try {
				JSONArray ja1 = new JSONArray();
				JSONObject jsonarray = new JSONObject();

				ja1.put(jsonarray);

				SendRequest req = new SendRequest();
				responsefromserver = req.uploadToServer(
						"mrDeviceAccessManagmentMob/" + (Login.sdoCode.equals("1410")?"2120":Login.sdoCode), ja1);
			} catch (Exception e) {/* bcitS$90 */
				e.printStackTrace();
			}

			return null;
		}

		protected void onPostExecute(Void unused) {
			mProgressDialog.dismiss();
			if (responsefromserver == null) {

				new AlertDialog.Builder(DeviceStatusSearch.this)
						.setTitle(getResources().getString(R.string.Info))
						.setMessage("Unable To Get Data")
						.setPositiveButton(
								getResources().getString(R.string.OK), new DialogInterface.OnClickListener(){

									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										// TODO Auto-generated method stub
										finish();
									}
									
								})
						.show();
				json = new JSONArray();
				try {
					CustomDeviceStatusSearchAdapter adapter = null;
					listView.setAdapter(adapter);

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			else if (responsefromserver.equals("NoData")) {
				new AlertDialog.Builder(DeviceStatusSearch.this)
						.setTitle(getResources().getString(R.string.Info))
						.setMessage("NO DATA")
						.setPositiveButton(
								getResources().getString(R.string.OK), new DialogInterface.OnClickListener(){

									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										// TODO Auto-generated method stub
										finish();
									}
									
								})
						.show();
				json = new JSONArray();
				try {
					CustomDeviceStatusSearchAdapter adapter = null;
					listView.setAdapter(adapter);

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			else {

				JSONArray arr;
				try {
					total.setChecked(true);
					arr = new JSONArray(responsefromserver);
					total.setText("Total\n"+arr.length());
					allocatedjson = new JSONArray();
					for (int i = 0; i < arr.length(); i++) {
						JSONObject obj = arr.getJSONObject(i);
						
						if(obj.getString("allocatedflag").equalsIgnoreCase("ALLOCATED")){
							allocatedjson.put(obj);
						}
					}
					allocated.setText("Allocated\n"+allocatedjson.length());
					unallocatedjson = new JSONArray();
					for (int i = 0; i < arr.length(); i++) {
						JSONObject obj = arr.getJSONObject(i);
						
						if(obj.getString("allocatedflag").equalsIgnoreCase("NOT ALLOCATED")){
							unallocatedjson.put(obj);
						}
					}
					unallocated.setText("Not Allocated\n"+unallocatedjson.length());
					
					title.setText("Total Mobiles");
					edValue = ed_search.getText().toString().trim();
					//rg.clearCheck();
					header.setVisibility(View.VISIBLE);
					CustomDeviceStatusSearchAdapter adapter;
					listView.setVisibility(View.VISIBLE);
					JSONArray arr2 = new JSONArray(responsefromserver);
					/*DBMrWiseBilling databasehandler = new DBMrWiseBilling(DeviceStatusSearch.this);
					databasehandler.open();
					
					
					JSONArray mrgriddata = databasehandler.getMrWiseReportData();
					databasehandler.close();*/
					adapter = new CustomDeviceStatusSearchAdapter(
							DeviceStatusSearch.this,arr2);
					listView.setAdapter(adapter);
					
					
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				

			}

		}
	}

}
