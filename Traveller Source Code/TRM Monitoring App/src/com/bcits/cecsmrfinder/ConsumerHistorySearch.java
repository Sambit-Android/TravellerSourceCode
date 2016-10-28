package com.bcits.cecsmrfinder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.bcits.utils.SendRequest;
import com.sqlite.cescip.DBDivision;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class ConsumerHistorySearch extends Activity {
	ListView listView;
	Button search;
	RadioButton rd_btn_rrno;
	String searchType, edValue;
	EditText ed_search;
	public static String responsefromserver = null;
	public static String rrno = null;
	RadioGroup rg;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		getActionBar().setCustomView(R.layout.custom_title_consumerhistory);
		getActionBar().getCustomView().findViewById(R.id.title);

		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		LayoutInflater inflater = (LayoutInflater) this
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.custom_title_consumerhistory,
				null);
		actionBar.setCustomView(view, new ActionBar.LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.MATCH_PARENT));
		actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);

		setContentView(R.layout.consumer_history_search);
		hideSoftKeyboard();
		listView = (ListView) findViewById(R.id.list);
		rd_btn_rrno = (RadioButton) findViewById(R.id.radioButton1);
		ed_search = (EditText) findViewById(R.id.username);
		

		/*
		 * try { CustomHistorySearchAdapter adapter = new
		 * CustomHistorySearchAdapter(getApplicationContext());
		 * listView.setAdapter(adapter);
		 * 
		 * // ListView Item Click Listener listView.setOnItemClickListener(new
		 * OnItemClickListener() {
		 * 
		 * 
		 * @Override public void onItemClick(AdapterView<?> parent, View view,
		 * int position, long id) {
		 * 
		 * 
		 * 
		 * Intent intent = new
		 * Intent(ConsumerHistorySearch.this,ConsumerHistoryAllDetails.class);
		 * startActivity(intent); }
		 * 
		 * }); } catch (JSONException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */
		search = (Button) findViewById(R.id.btnsearch);
		search.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				edValue = ed_search.getText().toString().trim();
				
				if(edValue.length()==0){
					Toast.makeText(getApplicationContext(), "Enter a consumer rr no/ name / village to search", Toast.LENGTH_LONG).show();
				}else{
					// searchType = "";
					listView.setVisibility(View.VISIBLE);
					hideSoftKeyboard();
					new GetRRnolist().execute();
				}
				
			}
		});
		
		RadioGroup rg = (RadioGroup) findViewById(R.id.radiogrp);
		rg.check(R.id.radioButton1);
		searchType = "rrno";
		rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch (checkedId) {
				case R.id.radioButton1:
					// do operations specific to this selection
					searchType = "rrno";
					break;

				case R.id.radioButton2:
					// do operations specific to this selection
					searchType = "name";
					break;

				case R.id.radioButton3:
					// do operations specific to this selection
					searchType = "village";
					break;

				}

			}
		});
		ed_search.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ed_search.setText("");
				CustomHistorySearchAdapter adapter = null;
				listView.setAdapter(adapter);
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
		hideSoftKeyboard();

	}

	public void hideSoftKeyboard() {
		if (getCurrentFocus() != null) {
			InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
			inputMethodManager.hideSoftInputFromWindow(getCurrentFocus()
					.getWindowToken(), 0);
		}
	}
	
	public class setItemsToListView extends AsyncTask<Void, Void, Void> {

		private ProgressDialog mProgressDialog;
		JSONArray json = null;

		protected void onPreExecute() {
			mProgressDialog = new ProgressDialog(ConsumerHistorySearch.this,R.style.MyTheme2);
			mProgressDialog.setMessage("Please Wait We Are Preparing The Data...");
			mProgressDialog.setCancelable(false);
			mProgressDialog.show();
		}

		protected Void doInBackground(Void... params) {
			

			
				

				
			

			return null;
		}

		protected void onPostExecute(Void unused) {
			try {
				json = new JSONArray(responsefromserver);
				CustomHistorySearchAdapter adapter = new CustomHistorySearchAdapter(
						getApplicationContext());
				listView.setAdapter(adapter);
				adapter.notifyDataSetChanged();
				mProgressDialog.dismiss();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				mProgressDialog.dismiss();
			}
			
			

		}
	}

	public class GetRRnolist extends AsyncTask<Void, Void, Void> {

		private ProgressDialog mProgressDialog;
		JSONArray json = null;

		protected void onPreExecute() {
			mProgressDialog = new ProgressDialog(ConsumerHistorySearch.this,R.style.MyTheme2);
			mProgressDialog.setMessage("Please Wait ...");
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
						"/consumerHistoryGetRrnos/" + edValue.toUpperCase() + "/"
								+ searchType + "/" + "2016" + "/"
								+ Login.dbSchema, ja1);
			} catch (Exception e) {
				e.printStackTrace();
			}

			return null;
		}

		protected void onPostExecute(Void unused) {
			mProgressDialog.dismiss();
			if (responsefromserver == null) {

				new AlertDialog.Builder(ConsumerHistorySearch.this)
						.setTitle(getResources().getString(R.string.Info))
						.setMessage("Unable To Get Data")
						.setPositiveButton(
								getResources().getString(R.string.OK), null)
						.show();
				json = new JSONArray();
				try {
					CustomHistorySearchAdapter adapter = null;
					listView.setAdapter(adapter);
					adapter.notifyDataSetChanged();
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			else if (responsefromserver.equals("NoData")) {
				new AlertDialog.Builder(ConsumerHistorySearch.this)
						.setTitle(getResources().getString(R.string.Info))
						.setMessage("NO DATA")
						.setPositiveButton(
								getResources().getString(R.string.OK), null)
						.show();
				json = new JSONArray();
				try {
					CustomHistorySearchAdapter adapter = null;
					listView.setAdapter(adapter);
					adapter.notifyDataSetChanged();
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			else {

				new setItemsToListView().execute();

			}

		}
	}

}
