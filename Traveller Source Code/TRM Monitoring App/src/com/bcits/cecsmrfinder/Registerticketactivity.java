package com.bcits.cecsmrfinder;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.bcits.utils.AmrMethods;
import com.bcits.utils.SendRequest;

public class Registerticketactivity extends Activity {
	Button submit;
	private Spinner category, subcategory;
	EditText ticketno, officername, mobilenumber, email, description;
	AlertDialog.Builder builder;
	public static String responsefromserver = "";
	ArrayList<String> idCategoryArray = new ArrayList<String>();
	ArrayList<String> subCategoryidArray = new ArrayList<String>();
	String categoryId = "", sitecode = "", subcategoryId = "",
			subDivisionId = "";
	String cat;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		getActionBar().setCustomView(R.layout.custom_title_ticketingstatus);
		getActionBar().getCustomView().findViewById(R.id.title);
		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		LayoutInflater inflater = (LayoutInflater) this
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.custom_title_ticketingstatus,
				null);
		actionBar.setCustomView(view, new ActionBar.LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.MATCH_PARENT));
		actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		setContentView(R.layout.activity_registerticketactivity);
		builder = new AlertDialog.Builder(Registerticketactivity.this);
		submit = (Button) findViewById(R.id.rregister);
		category = (Spinner) findViewById(R.id.category);
		subcategory = (Spinner) findViewById(R.id.subcategory);
		ticketno = (EditText) findViewById(R.id.ticketno);
		officername = (EditText) findViewById(R.id.officername);
		mobilenumber = (EditText) findViewById(R.id.mobilenumber);
		//mobilenumber.addTextChangedListener(new PhoneNumberTextWatcher(mobilenumber));
		email = (EditText) findViewById(R.id.emailid);
		description = (EditText) findViewById(R.id.description);
		officername.setText(Login.userName.toUpperCase());
		new DownloadCategory().execute();
		/*
		 * else{ spinnerData(); }
		 */

		submit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (mobilenumber.getText().toString().equals("")||mobilenumber.getText().toString().length()!=10
						|| mobilenumber.getText().toString().equals(" ")
						|| mobilenumber.getText().toString().equals(null)||description.getText().toString().equals("")
						|| description.getText().toString().equals(" ")
						|| description.getText().toString().equals(null)||email.getText().toString().equals("")
						|| email.getText().toString().equals(" ")
						|| email.getText().toString().equals(null)||isValidEmail(email.getText().toString())==false) {

					new AlertDialog.Builder(new ContextThemeWrapper(
							Registerticketactivity.this, R.style.CustomAlertDialogStyle))
							.setTitle("Error !")
							.setMessage("Please Enter Mobile Number(10 digits) , email(valid email address) and description to register the ticket.").setCancelable(true)
							.setPositiveButton("OK", new DialogInterface.OnClickListener() {
							    public void onClick(DialogInterface dialog, int id) {
							    	mobilenumber.setText("");
									email.setText("");
									description.setText("");
									category.setSelection(0);
									hideSoftKeyboard();
									dialog.dismiss();
							     }
							})
							.create()
							.show();
					
				} else {
					new RegisterTicket().execute();
				}

			}
		});

		/*
		 * category.setOnItemSelectedListener(new OnItemSelectedListener() {
		 * 
		 * @Override public void onItemSelected(AdapterView<?> arg0, View arg1,
		 * int arg2, long arg3) { categoryId=idCategoryArray.get(arg2);
		 * //Toast.makeText(getApplicationContext(), categoryId,
		 * Toast.LENGTH_SHORT).show(); ArrayList<String> categorryArray=
		 * getSubCategory(Registerticketactivity.this, category); ArrayAdapter<String>
		 * adapter= new ArrayAdapter<String>(Registerticketactivity.this,
		 * android.R.layout.simple_dropdown_item_1line, categorryArray);
		 * spinnerSubIssue.setAdapter(adapter); }
		 * 
		 * @Override public void onNothingSelected(AdapterView<?> arg0) {
		 * 
		 * } });
		 * 
		 * 
		 * subcategory.setOnItemSelectedListener(new OnItemSelectedListener() {
		 * 
		 * @Override public void onItemSelected(AdapterView<?> parent, View
		 * view, int position, long id) {
		 * 
		 * subcategoryId=subCategoryidArray.get(position);
		 * //Toast.makeText(getApplicationContext(), subcategoryId,
		 * Toast.LENGTH_SHORT).show();
		 * 
		 * }
		 * 
		 * @Override public void onNothingSelected(AdapterView<?> parent) { //
		 * TODO Auto-generated method stub
		 * 
		 * } });
		 */

		/*
		 * category.setOnItemSelectedListener(new OnItemSelectedListener() {
		 * 
		 * @Override public void onItemSelected(AdapterView<?> parent, View
		 * view, int position, long id) { // TODO Auto-generated method stub if
		 * (category.getSelectedItem().toString().equalsIgnoreCase(
		 * "A - Application level bug fixing and troubleshooting / Database related problems"
		 * )) { ArrayAdapter<CharSequence> adapter =
		 * ArrayAdapter.createFromResource( Registerticketactivity.this,
		 * R.array.subcategoryA, android.R.layout.simple_spinner_item);
		 * adapter.setDropDownViewResource
		 * (android.R.layout.simple_spinner_dropdown_item);
		 * subcategory.setAdapter(adapter); } else if
		 * (category.getSelectedItem()
		 * .toString().equalsIgnoreCase("B - Procedural level changes / enhancement"
		 * )) { ArrayAdapter<CharSequence> adapter =
		 * ArrayAdapter.createFromResource( Registerticketactivity.this,
		 * R.array.subcategoryB, android.R.layout.simple_spinner_item);
		 * adapter.setDropDownViewResource
		 * (android.R.layout.simple_spinner_dropdown_item);
		 * subcategory.setAdapter(adapter); } else if
		 * (category.getSelectedItem()
		 * .toString().equalsIgnoreCase("C - Architectural level changes")) {
		 * ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
		 * Registerticketactivity.this, R.array.subcategoryC,
		 * android.R.layout.simple_spinner_item);
		 * adapter.setDropDownViewResource
		 * (android.R.layout.simple_spinner_dropdown_item);
		 * subcategory.setAdapter(adapter); } else if
		 * (category.getSelectedItem().toString().equalsIgnoreCase(
		 * "D - Repair/replacement of all kinds of Hardware")) {
		 * ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
		 * Registerticketactivity.this, R.array.subcategoryD,
		 * android.R.layout.simple_spinner_item);
		 * adapter.setDropDownViewResource
		 * (android.R.layout.simple_spinner_dropdown_item);
		 * subcategory.setAdapter(adapter); }else{ ArrayAdapter<CharSequence>
		 * adapter = ArrayAdapter.createFromResource(
		 * Registerticketactivity.this, R.array.subcategorynone,
		 * android.R.layout.simple_spinner_item);
		 * adapter.setDropDownViewResource
		 * (android.R.layout.simple_spinner_dropdown_item);
		 * subcategory.setAdapter(adapter); } }
		 * 
		 * @Override public void onNothingSelected(AdapterView<?> parent) { //
		 * TODO Auto-generated method stub
		 * 
		 * } });
		 */

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

	public void hideSoftKeyboard() {
		if (getCurrentFocus() != null) {
			InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
			inputMethodManager.hideSoftInputFromWindow(getCurrentFocus()
					.getWindowToken(), 0);
		}
	}

	public class RegisterTicket extends AsyncTask<Void, Void, Void> {

		private ProgressDialog mProgressDialog;
		JSONArray json = null;
		String mobilevar = null;
		String emailidvar = null;
		String desc = null;

		protected void onPreExecute() {
			mProgressDialog = new ProgressDialog(Registerticketactivity.this,
					R.style.MyTheme2);
			mProgressDialog
					.setMessage("Registering Your Ticket . Please Wait .....");
			mProgressDialog.setCancelable(false);
			mProgressDialog.show();
			mobilevar = mobilenumber.getText().toString();
			emailidvar= email.getText().toString();
			desc = description.getText().toString();
		}

		protected Void doInBackground(Void... params) {
			try {
				JSONArray ja1 = new JSONArray();
				JSONObject jsonarray = new JSONObject();
				jsonarray.put("sitecode", (Login.sdoCode.equals("1410")?"2120":Login.sdoCode));
				jsonarray.put("officerName", Login.userName);
				jsonarray.put("mobileNo", mobilenumber.getText().toString());
				jsonarray.put("emailId", email.getText().toString());
				jsonarray.put("description", description.getText().toString());
				jsonarray.put("category", Integer.parseInt(categoryId));
				jsonarray.put("subcategory", Integer.parseInt(subcategoryId));
				// jsonarray.put("RRNO","");
				// jsonarray.put("DocketSource","Mobile");

				ja1.put(jsonarray);

				SendRequest req = new SendRequest();
				responsefromserver = req.uploadToServer2(
						"createTicketTrmMobile/", ja1);
			} catch (Exception e) {/* bcitS$90 */
				e.printStackTrace();
			}

			return null;
		}

		protected void onPostExecute(Void unused) {
			mProgressDialog.dismiss();
			if (responsefromserver.equals("novalue")) {

				new AlertDialog.Builder(Registerticketactivity.this)
						.setTitle(getResources().getString(R.string.Info))
						.setMessage(responsefromserver)
						.setPositiveButton(
								getResources().getString(R.string.OK),
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										// TODO Auto-generated method stub
										finish();
									}

								}).show();

			} else if (responsefromserver.equals("")
					|| responsefromserver.equals(null)) {
				new AlertDialog.Builder(Registerticketactivity.this)
						.setTitle("Ticket Status")
						.setMessage("Blank Response from Server")
						.setCancelable(true).create().show();
			}

			else {

				new AlertDialog.Builder(new ContextThemeWrapper(
						Registerticketactivity.this, R.style.CustomAlertDialogStyle))
						.setTitle("Ticket Registered Successfully !")
						.setMessage(responsefromserver).setCancelable(true)
						.setPositiveButton("OK", new DialogInterface.OnClickListener() {
						    public void onClick(DialogInterface dialog, int id) {
						    	finish();
						     }
						})
						.create()
						.show();
						
				
			}

		}
	}

	private class DownloadCategory extends AsyncTask<Void, Integer, Void> {
		String responsefromserver = null;
		ProgressDialog mProgressDialog = null;

		// TODO
		protected void onPreExecute() {
			mProgressDialog = new ProgressDialog(Registerticketactivity.this,
					R.style.MyTheme2);
			mProgressDialog.setMessage("Please wait...");
			// mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
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
					jsonarray.put("Sub-Category", "");
					ja1.put(jsonarray);

					SendRequest req = new SendRequest();
					String url1 = "getListOfCategory";
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
			if (responsefromserver == null) {
				new AlertDialog.Builder(Registerticketactivity.this)
						.setTitle("Info")
						.setMessage(
								"Sorry,connection cannot made at this time. Server is down. Please try again later")
						.setPositiveButton("OK", null).show();
			}

			else if (responsefromserver.contains("html")) {
				new AlertDialog.Builder(Registerticketactivity.this)
						.setTitle("Info")
						.setMessage(
								"Sorry,connection cannot made at this time. Server is down. Please try again later")
						.setPositiveButton("OK", null).show();
			}

			else {
				/*System.out.println(">>>>>>>>>>>>>>CATEGORY>>>>>>>>>>>"
						+ responsefromserver);*/

				AmrMethods.setDefaults("category", responsefromserver,
						Registerticketactivity.this);
				new DownloadSubCategory().execute();

			}
		}
	}

	private class DownloadSubCategory extends AsyncTask<Void, Integer, Void> {
		String responsefromserver = null;
		ProgressDialog mProgressDialog = null;

		// TODO
		protected void onPreExecute() {
			mProgressDialog = new ProgressDialog(Registerticketactivity.this,
					R.style.MyTheme2);
			mProgressDialog.setMessage("Please wait...");
			// mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
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
					jsonarray.put("Sub-Category", "");
					ja1.put(jsonarray);

					SendRequest req = new SendRequest();
					String url1 = "getListOfSubcategory";
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
			if (responsefromserver == null) {
				hideSoftKeyboard();
				new AlertDialog.Builder(Registerticketactivity.this)
						.setTitle("Info")
						.setMessage(
								"Sorry,connection cannot made at this time. Server is down. Please try again later")
						.setPositiveButton("OK", null).show();
				
			}

			else if (responsefromserver.contains("html")) {
				hideSoftKeyboard();
				new AlertDialog.Builder(Registerticketactivity.this)
						.setTitle("Info")
						.setMessage(
								"Sorry,connection cannot made at this time. Server is down. Please try again later")
						.setPositiveButton("OK", null).show();
				
			}

			else {
				/*System.out.println(">>>>>>>>>>>>>>SUB CATEGORY>>>>>>>>>>>"
						+ responsefromserver);*/
				hideSoftKeyboard();
				AmrMethods.setDefaults("subCategory", responsefromserver,
						Registerticketactivity.this);
				spinnerData();
				
			}
		}
	}

	public ArrayList<String> getCategory(Context context,
			String responsefromserver) {
		ArrayList<String> categoryArray = new ArrayList<String>();

		if (responsefromserver == null) {

			Toast.makeText(context, "Please Refresh", Toast.LENGTH_SHORT)
					.show();

		} else if (responsefromserver.contains("<html>")) {

			Toast.makeText(context, "Please Refresh", Toast.LENGTH_SHORT)
					.show();

		} else if (responsefromserver.equals("no_data")) {

			// Toast.makeText(context,
			// "No Data Found",Toast.LENGTH_SHORT).show();
		} else {
			// Log.e("Response", responsefromserver);
			try {
				JSONArray jsonarray = new JSONArray(responsefromserver);
				JSONObject jsondata = null;
				for (int i = 0; i < jsonarray.length(); i++) {
					jsondata = jsonarray.getJSONObject(i);
					categoryArray
							.add(jsondata.getString("categoryName").trim());
					idCategoryArray
							.add(jsondata.getString("categoryId").trim());

				}

			} catch (JSONException e) {
				e.printStackTrace();

			}
		}
		return categoryArray;
	}

	public ArrayList<String> getSubCategory(Context context,
			String responsefromserver) {
		ArrayList<String> categoryArray = new ArrayList<String>();

		if (responsefromserver == null) {

			Toast.makeText(context, "Please Refresh", Toast.LENGTH_SHORT)
					.show();

		} else if (responsefromserver.contains("<html>")) {

			Toast.makeText(context, "Please Refresh", Toast.LENGTH_SHORT)
					.show();

		} else if (responsefromserver.equals("no_data")) {

			// Toast.makeText(context,
			// "No Data Found",Toast.LENGTH_SHORT).show();
		} else {
			// Log.e("Response", responsefromserver);
			try {
				JSONArray jsonarray = new JSONArray(responsefromserver);
				JSONObject jsondata = null;
				subCategoryidArray = new ArrayList<String>();
				for (int i = 0; i < jsonarray.length(); i++) {
					jsondata = jsonarray.getJSONObject(i);
					if (categoryId.equals(jsondata.getString("Categoryid") 
							.trim())) {
						subCategoryidArray.add(jsondata.getString(
								"subCategoryid").trim());
						categoryArray.add(jsondata.getString("subCategoryName")
								.trim());
					}

				}

			} catch (JSONException e) {
				e.printStackTrace();

			}
		}
		return categoryArray;
	}

	private void spinnerData() {
		if (AmrMethods.getDefaults("category", Registerticketactivity.this) != null) {
			cat = AmrMethods.getDefaults("category",
					Registerticketactivity.this);
			ArrayList<String> categorryArray = getCategory(
					Registerticketactivity.this, cat);
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(
					Registerticketactivity.this,
					android.R.layout.simple_dropdown_item_1line, categorryArray);
			category.setAdapter(adapter);
		}

		if (AmrMethods.getDefaults("subCategory", Registerticketactivity.this) != null) {
			cat = AmrMethods.getDefaults("subCategory",
					Registerticketactivity.this);
			ArrayList<String> categorryArray = getSubCategory(
					Registerticketactivity.this, cat);
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(
					Registerticketactivity.this,
					android.R.layout.simple_dropdown_item_1line, categorryArray);
			subcategory.setAdapter(adapter);
		}
		
		category.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,	int arg2, long arg3) {
				categoryId=idCategoryArray.get(arg2);
				ArrayList<String> categorryArray=	getSubCategory(Registerticketactivity.this, cat);
				ArrayAdapter<String> adapter= new ArrayAdapter<String>(Registerticketactivity.this, android.R.layout.simple_dropdown_item_1line, categorryArray);
				subcategory.setAdapter(adapter);	
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				
			}
		});
		
		subcategory.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				subcategoryId = subCategoryidArray.get(arg2);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {

			}
		});

	}
	public final static boolean isValidEmail(CharSequence target) {
		return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches();
	}
	
	

}
