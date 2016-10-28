package com.bcits.cecsmrfinder;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.auto.update.AndroidautoUpdateActivity;
import com.bcits.utils.AmrMethods;
import com.bcits.utils.SendRequest;

@SuppressLint("ClickableViewAccessibility")
public class Login extends Activity {
	Button login;
	EditText edUserName, edPassword;
	AutoCompleteTextView edSdoCode;
	TextView version_name;
	ImageView imgLoadLocation;
	public static String userDesignation;
	CheckBox chckremember;
	public static final String MY_PREFS_NAME = "loginCredentials";

	public static String userName, dbSchema, passWord, sdoCode, sdoLocation;
	ArrayList<String> arrayLocation = new ArrayList<String>();
	Boolean isSDPresent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.login);

		

		updateApk(Login.this);
		Intent intent = getIntent();
		if (intent.getStringExtra("exit") != null
				&& intent.getStringExtra("exit").equals("exit")) {
			Intent homeIntent = new Intent(Intent.ACTION_MAIN);
			homeIntent.addCategory(Intent.CATEGORY_HOME);
			homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(homeIntent);
		}

		chckremember = (CheckBox) findViewById(R.id.checkBox1);

		imgLoadLocation = (ImageView) findViewById(R.id.imgLoadLocation);
		login = (Button) findViewById(R.id.btnlogin);
		edUserName = (EditText) findViewById(R.id.username);
		edPassword = (EditText) findViewById(R.id.password);
		edSdoCode = (AutoCompleteTextView) findViewById(R.id.sdo);
		version_name = (TextView) findViewById(R.id.version_name);

		/*if (chckremember.isChecked()) {

			if (!(edSdoCode.getText().toString().equals(""))
					&& !(edUserName.getText().toString().equals(""))
					&& !(edPassword.getText().toString().equals(""))) {
				Log.e("checked", "checked");
				SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME,
						MODE_PRIVATE).edit();
				editor.putString("username", edUserName.getText().toString());
				editor.putString("password", edPassword.getText().toString());
				editor.putString("schema", edSdoCode.getText().toString());
				editor.apply();
				
			} else {
				Log.e("checked", "unchecked");
				Toast.makeText(getApplicationContext(),
						"Enter Location Name and Password", Toast.LENGTH_SHORT)
						.show();
				chckremember.setChecked(false);
			}

		}*/
		

		isSDPresent = android.os.Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_MOUNTED);
		String versionName = null;
		try {
			versionName = Login.this.getPackageManager().getPackageInfo(
					Login.this.getPackageName(), 0).versionName;
			version_name.setText(getResources().getString(R.string.Version)
					+ " " + versionName);
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}

		if (AmrMethods.getDefaults("LOCATIONS_KEY", Login.this) == null) {
			new GetLocationDetails().execute();
		} else {
			setLocation(AmrMethods.getDefaults("LOCATIONS_KEY", Login.this));
		}

		Spinner sp = (Spinner) findViewById(R.id.spinnerLogin);
		String[] array = getResources().getStringArray(R.array.loginCategory);

		ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(Login.this,
				R.layout.simple_spinner_dropdown_item, R.id.txtSpinner, array);
		sp.setAdapter(myAdapter);

	}

	public void SavePreferences(String key, String value) {
		SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME,
				MODE_PRIVATE).edit();
		editor.putString(key, value);
		editor.apply();
	}

	@Override
	public void onBackPressed() {
		this.finish();
	}

	@Override
	protected void onResume() {
		super.onResume();

		login.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {

				if (!isSDPresent) {
					Toast.makeText(getApplicationContext(),
							"No Memory Card Found", Toast.LENGTH_LONG).show();
					return;
				}

				userName = edUserName.getText().toString();
				String location = edSdoCode.getText().toString();
				passWord = edPassword.getText().toString();
				sdoLocation = edSdoCode.getText().toString();

				if (location.trim().equals("")) {
					edSdoCode.setError("Enter Location");
					edSdoCode.requestFocus();
					return;
				}
				if (userName.trim().equals("")) {
					edUserName.setError("Enter User Name");
					edUserName.requestFocus();
					return;
				}
				if (passWord.trim().equals("")) {
					edPassword.setError("Enter Password");
					edPassword.requestFocus();
					return;
				}

				if (!isExactLocation(location.trim())) {

					edSdoCode.setError("Invalid Location");
					edSdoCode.requestFocus();
					return;
				}

				if (dbSchema.equals("NO_LOCATION")) {
					Toast.makeText(getApplicationContext(),
							"No Schema Found for this Location",
							Toast.LENGTH_SHORT).show();
					return;
				}

				// Toast.makeText(getApplicationContext(),
				// dbSchema,Toast.LENGTH_SHORT).show();

				if (!haveNetworkConnection()) {
					//loginFromMobile();
					new AlertDialog.Builder(Login.this)
					.setTitle(getResources().getString(R.string.Info))
					.setMessage(
							getResources().getString(
									R.string.Internetnotavailable))
					.setPositiveButton(
							getResources().getString(R.string.OK), null)
					.show();
					return;
				}

				new LoginCheck().execute();
			}
		});

		imgLoadLocation.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				edSdoCode.setText("");
				new GetLocationDetails().execute();
			}
		});
		edSdoCode.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				edSdoCode.setError(null);
				return false;
			}
		});
		edPassword.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				edPassword.setError(null);
				return false;
			}
		});
		edUserName.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				edUserName.setError(null);
				return false;
			}
		});
		SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME,
				MODE_PRIVATE);
		String restoredschema = prefs.getString("schema", null);
		String restoredusername = prefs.getString("username", null);
		String restoredpassword = prefs.getString("password", null);
		if (restoredschema != null && restoredusername != null
				&& restoredpassword != null) {
			edUserName.setText(restoredusername);
			edPassword.setText(restoredpassword);
			edSdoCode.setText(restoredschema);
			chckremember.setChecked(true);
			edSdoCode.dismissDropDown();
		} else {
			/*Toast.makeText(getApplicationContext(),
					"No Credentials stored in Preferences", Toast.LENGTH_SHORT)
					.show();*/
		}
		
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

	public class LoginCheck extends AsyncTask<Void, Void, Void> {
		String responsefromserver = null;
		private ProgressDialog mProgressDialog;

		protected void onPreExecute() {
			mProgressDialog = new ProgressDialog(Login.this,R.style.MyTheme2);
			mProgressDialog.setMessage(getResources().getString(
					R.string.LoggingInPleasewait));
			mProgressDialog.setCancelable(false);
			mProgressDialog.show();
		}

		protected Void doInBackground(Void... params) {
			try {
				JSONArray ja1 = new JSONArray();
				JSONObject jsonarray = new JSONObject();

				jsonarray.put("userName", userName);
				jsonarray.put("passWord", passWord);
				jsonarray.put("dbSchema", dbSchema);
				ja1.put(jsonarray);

				SendRequest req = new SendRequest();
				responsefromserver = req.uploadToServer("mobileCommonLogin",
						ja1);

			} catch (Exception e) {
				e.printStackTrace();
			}

			return null;
		}

		protected void onPostExecute(Void unused) {


			mProgressDialog.dismiss();
			if (responsefromserver == null) {
				//loginFromMobile();
				new AlertDialog.Builder(Login.this)
				.setTitle(getResources().getString(R.string.Info))
				.setMessage(
						getResources().getString(
								R.string.Internetnotavailable))
				.setPositiveButton(
						getResources().getString(R.string.OK), null)
				.show();
			}

			else if (responsefromserver.equals("NoData")) {
				new AlertDialog.Builder(Login.this)
						.setTitle(getResources().getString(R.string.Info))
						.setMessage(
								getResources().getString(
										R.string.InvalidCredentials))
						.setPositiveButton(
								getResources().getString(R.string.OK), null)
						.show();
			}

			else if (responsefromserver.contains("DESIGNATION")
					&& responsefromserver.contains("EMPNAME")) {


				AmrMethods
						.setDefaults("LOGIN_KEY", userName.trim() + "@@@"
								+ passWord.trim() + "@@@" + dbSchema.trim(),
								Login.this);
				AmrMethods.setDefaults("EMPLOYEE_KEY", responsefromserver,
						Login.this);

				// TODO

				try {
					JSONObject obj = new JSONObject(responsefromserver);

					String alert = "NAME        : " + obj.getString("EMPNAME")
							+ "\n" + "DESIGNATION : "
							+ obj.getString("DESIGNATION") + "\n"
							+ "EMAIL       : " + obj.getString("EMAIL") + "\n"
							+ "MOBILE      : " + obj.getString("MOBILE");
					// Toast.makeText(getApplicationContext(), alert,
					// Toast.LENGTH_LONG).show();

					userDesignation = obj.getString("DESIGNATION");

				} catch (JSONException e) {
					e.printStackTrace();
				}

				// {"DESIGNATION":"SA","USERTYPE":"SA","EMAIL":"NA","EMPNAME":"NA","MOBILE":"NA","SITECODE":"4140"}

				hideSoftKeyboard();
				
				if (chckremember.isChecked()) {

					if (!(edSdoCode.getText().toString().equals(""))
							&& !(edUserName.getText().toString().equals(""))
							&& !(edPassword.getText().toString().equals(""))) {
						Log.e("checked", "checked");
						SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME,
								MODE_PRIVATE).edit();
						editor.putString("username", edUserName.getText().toString());
						editor.putString("password", edPassword.getText().toString());
						editor.putString("schema", edSdoCode.getText().toString());
						editor.commit();
						
					} else {
						Log.e("checked", "unchecked");
						new AlertDialog.Builder(Login.this)
						.setTitle(getResources().getString(R.string.Info))
						.setMessage("Enter Location and Password to continue logging in .")
						.setPositiveButton(
								getResources().getString(R.string.OK), null)
						.show();
						chckremember.setChecked(false);
					}

				}else{
					SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME,
							MODE_PRIVATE).edit();
					editor.clear();
					editor.commit();
				}
				
				Intent intent = new Intent(Login.this, Dashboard.class);
				startActivity(intent);
				finish();

			} else {
				Log.e("Response", responsefromserver);
				//loginFromMobile();
				new AlertDialog.Builder(Login.this)
				.setTitle("Info")
				.setMessage("Check Your Internet and Try Again.")
				.setPositiveButton("OK", null).show();
			}
		}
	}

	/*private void loginFromMobile() {

		try {

			if (AmrMethods.getDefaults("LOGIN_KEY", Login.this) != null) {
				if (AmrMethods.getDefaults("LOGIN_KEY", Login.this).equals(
						userName.trim() + "@@@" + passWord.trim() + "@@@"
								+ dbSchema.trim())) {
					new AlertDialog.Builder(Login.this)
							.setTitle(
									getResources().getString(
											R.string.NetworkProblem))
							.setCancelable(false)
							.setMessage(
									getResources().getString(
											R.string.Loginfrommobile))
							.setPositiveButton(
									getResources().getString(R.string.OK),
									new DialogInterface.OnClickListener() {
										@Override
										public void onClick(
												DialogInterface dialog,
												int which) {

											try {
												JSONObject obj = new JSONObject(
														AmrMethods.getDefaults(
																"EMPLOYEE_KEY",
																Login.this));

												String alert = "NAME        : "
														+ obj.getString("EMPNAME")
														+ "\n"
														+ "DESIGNATION : "
														+ obj.getString("DESIGNATION")
														+ "\n"
														+ "EMAIL       : "
														+ obj.getString("EMAIL")
														+ "\n"
														+ "MOBILE      : "
														+ obj.getString("MOBILE");
												Toast.makeText(
														getApplicationContext(),
														alert,
														Toast.LENGTH_LONG)
														.show();

											} catch (JSONException e) {
												e.printStackTrace();
											}
											if (chckremember.isChecked()) {

												if (!(edSdoCode.getText().toString().equals(""))
														&& !(edUserName.getText().toString().equals(""))
														&& !(edPassword.getText().toString().equals(""))) {
													Log.e("checked", "checked");
													SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME,
															MODE_PRIVATE).edit();
													editor.putString("username", edUserName.getText().toString());
													editor.putString("password", edPassword.getText().toString());
													editor.putString("schema", edSdoCode.getText().toString());
													editor.commit();
													
												} else {
													Log.e("checked", "unchecked");
													new AlertDialog.Builder(Login.this)
													.setTitle(getResources().getString(R.string.Info))
													.setMessage("Enter location name and password to continue loggin in .")
													.setPositiveButton(
															getResources().getString(R.string.OK), null)
													.show();
													chckremember.setChecked(false);
												}

											}else{
												SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME,
														MODE_PRIVATE).edit();
												editor.clear();
												editor.commit();
											}
											Intent intent = new Intent(
													Login.this, Dashboard.class);
											startActivity(intent);
											finish();
										}
									}).show();

				} else {
					new AlertDialog.Builder(Login.this)
							.setTitle(
									getResources().getString(
											R.string.NetworkProblem))
							.setCancelable(false)
							.setMessage(
									getResources()
											.getString(
													R.string.LoginfrommobileInvalidCredentials))
							.setPositiveButton(
									getResources().getString(R.string.OK), null)
							.show();
				}
			} else {
				new AlertDialog.Builder(Login.this)
				.setTitle(getResources().getString(R.string.Info))
				.setMessage(
						getResources().getString(
								R.string.Internetnotavailable))
				.setPositiveButton(
						getResources().getString(R.string.OK), null)
				.show();
			}

		} catch (Exception e) {
			e.printStackTrace();
			new AlertDialog.Builder(Login.this)
					.setTitle(getResources().getString(R.string.Info))
					.setMessage(
							"Some problem occured. Please close the application and try again.")
					.setPositiveButton(getResources().getString(R.string.OK),
							null).show();
		}
	}*/

	private class GetLocationDetails extends AsyncTask<Void, Void, Void> {
		String responsefromserver = null;
		private ProgressBar progressBar1;

		@Override
		protected void onPreExecute() {
			progressBar1 = (ProgressBar) findViewById(R.id.progressBar1);
			progressBar1.setVisibility(View.VISIBLE);
			edSdoCode.setHint("Loading Locations...");
			edSdoCode.setEnabled(false);
			imgLoadLocation.setVisibility(View.GONE);
		}

		@Override
		protected Void doInBackground(Void... params) {
			try {
				JSONArray ja1 = new JSONArray();
				SendRequest req = new SendRequest();
				responsefromserver = req.uploadToServer("getLocationDetails",
						ja1);

			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void unused) {
			progressBar1.setVisibility(View.GONE);
			imgLoadLocation.setVisibility(View.VISIBLE);
			edSdoCode.setHint("Enter Location");
			edSdoCode.setEnabled(true);

			if (responsefromserver == null) {
				new AlertDialog.Builder(Login.this)
						.setTitle(getResources().getString(R.string.Info))
						.setMessage("Server is down.")
						.setPositiveButton(
								getResources().getString(R.string.OK), null)
						.show();
			} else if (responsefromserver.equals("[]")) {
				new AlertDialog.Builder(Login.this)
						.setTitle(getResources().getString(R.string.Info))
						.setMessage("No Locations Found")
						.setPositiveButton(
								getResources().getString(R.string.OK), null)
						.show();
			} else if (responsefromserver.contains("dbuser")
					&& responsefromserver.contains("sitecode")) {
				setLocation(responsefromserver);
				AmrMethods.setDefaults("LOCATIONS_KEY", responsefromserver,
						Login.this);
			} else {

				new AlertDialog.Builder(Login.this)
						.setTitle(getResources().getString(R.string.Info))
						.setMessage("Something went wrong.\nPlease try again")
						.setPositiveButton(
								getResources().getString(R.string.OK), null)
						.show();
			}
		}
	}

	private void setLocation(String locationArray) {
		try {
			JSONArray array = new JSONArray(locationArray);
			arrayLocation = new ArrayList<String>();
			for (int i = 0; i < array.length(); i++) {
				JSONObject obj = array.getJSONObject(i);
				arrayLocation.add(obj.getString("section").trim() + " "
						+ obj.getString("sitecode").trim());
			}
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(Login.this,
					R.layout.custom_dropdown, R.id.txtCustomDropdown,
					arrayLocation);
			edSdoCode.setAdapter(adapter);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private boolean isExactLocation(String location) {

		for (int i = 0; i < arrayLocation.size(); i++) {
			if (arrayLocation.get(i).trim().equals(location)) {
				dbSchema = getDbSchema(location);
				return true;
			}
		}
		return false;
	}

	private String getDbSchema(String location) {
		try {
			if (AmrMethods.getDefaults("LOCATIONS_KEY", Login.this) == null) {
				return "NO_LOCATION";
			}
			JSONArray array = new JSONArray(AmrMethods.getDefaults(
					"LOCATIONS_KEY", Login.this));
			for (int i = 0; i < array.length(); i++) {
				JSONObject obj = array.getJSONObject(i);
				if (location.substring(location.length() - 4).trim()
						.equals(obj.getString("sitecode").trim())) {
					if (obj.getString("sitecode").trim().equals("2120")) {
						sdoCode = "1410";
					}else{
						sdoCode = obj.getString("sitecode").trim();
					}
					
					return obj.getString("dbuser").trim();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			return "NO_LOCATION";
		}
		return "NO_LOCATION";
	}

	public void hideSoftKeyboard() {
		if (getCurrentFocus() != null) {
			InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
			inputMethodManager.hideSoftInputFromWindow(getCurrentFocus()
					.getWindowToken(), 0);
		}
	}

	public void updateApk(Context context) {
		try {// TODO
			String versionName = context.getPackageManager().getPackageInfo(
					getPackageName(), 0).versionName;
			Intent updateapk = new Intent(context,
					AndroidautoUpdateActivity.class);
			// updateapk.putExtra("    key    ", " apk  name " + "@@@" + app
			// version + "@@@" + full url );
			updateapk.putExtra("apk_version", "TRMCESC" + "@@@" + versionName
					+ "@@@" + SendRequest.url1);
			startActivity(updateapk);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}