package com.bcits.recondiscon;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.auto.update.AndroidautoUpdateActivity;
import com.bcits.utils.AmrMethods;
import com.bcits.utils.SendRequest;
import com.sqlite.mvs.DbLogin;

public class Login extends Activity {
	private Button login;
	EditText username;
	EditText password;
	AutoCompleteTextView edSdoCode;
	TextView version_name;

	AlertDialog show;
	public static String userName;
	public static String sdoCode;
	public static String passWord;
	public static String location;
	
	String [] locations;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.login);

		Intent intent = getIntent();
		if (intent.getStringExtra("exit") != null
				&& intent.getStringExtra("exit").equals("exit")) {
			Intent homeIntent = new Intent(Intent.ACTION_MAIN);
			homeIntent.addCategory(Intent.CATEGORY_HOME);
			homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(homeIntent);
		}
		login = (Button) findViewById(R.id.btnlogin);
		username = (EditText) findViewById(R.id.username);
		password = (EditText) findViewById(R.id.password);
		edSdoCode = (AutoCompleteTextView) findViewById(R.id.sdo);
		version_name=(TextView)findViewById(R.id.version_name);
		String versionName = null;
		try {
			
			locations= getResources().getStringArray(R.array.allLocations);
			ArrayAdapter<String> adapter= new ArrayAdapter<>(Login.this, R.layout.simple_spinner_dropdown_item,R.id.txtSpinner,locations);
			edSdoCode.setAdapter(adapter);
			
			
			versionName = Login.this.getPackageManager().getPackageInfo(Login.this.getPackageName(), 0).versionName;
			version_name.setText("Version "+versionName);
			
			
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			updateApk(Login.this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onBackPressed() {
		Intent homeIntent = new Intent(Intent.ACTION_MAIN);
		homeIntent.addCategory(Intent.CATEGORY_HOME);
		homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(homeIntent);
	}

	@Override
	protected void onResume() {
		super.onResume();
		
	}

	@Override
	protected void onStart() {

		super.onStart();
		
		login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if (AmrMethods.doubleClick()) {
				Boolean isSDPresent = android.os.Environment
						.getExternalStorageState().equals(
								android.os.Environment.MEDIA_MOUNTED);

				if (isSDPresent) {

					userName = username.getText().toString().trim();
					sdoCode = edSdoCode.getText().toString().trim();
					passWord = password.getText().toString().trim();

					if (sdoCode.length()<9) {
						edSdoCode.setError("Enter Proper Location");
						edSdoCode.requestFocus();
						/*show = new AlertDialog.Builder(Login.this)
								.setTitle("Info").setMessage("Enter SDO Code")
								.setPositiveButton("OK", null).show();*/
					} 
					else if(!isLocationProper(sdoCode)) {
						edSdoCode.setError("Please select location from the drop list");
						edSdoCode.requestFocus();
					}
					
					else if (userName.matches("")) {
						username.setError("Enter MR Code");
						username.requestFocus();
						/*show = new AlertDialog.Builder(Login.this)
								.setTitle("Info").setMessage("Enter MR Code")
								.setPositiveButton("OK", null).show();*/

					} else if (passWord.matches("")) {
						password.setError("Enter Password");
						password.requestFocus();
						/*show = new AlertDialog.Builder(Login.this)
								.setTitle("Info").setMessage("Enter Password")
								.setPositiveButton("OK", null).show();*/

					}
					else if (!passWord.matches("1234")) {
						password.setError("Invalid Password");
						password.requestFocus();
						/*show = new AlertDialog.Builder(Login.this)
								.setTitle("Info").setMessage("Invalid Password")
								.setPositiveButton("OK", null).show();*/

					}
					else if (true) {
						try {

							try {
								location=sdoCode.split("-")[1].trim();
								sdoCode=sdoCode.split("-")[0].trim();
							} catch (Exception e) {
								Toast.makeText(getApplicationContext(), "Error on location. Please check it", Toast.LENGTH_SHORT).show();
								return;
							}
							
							Intent intent = new Intent(Login.this, DashBoard.class);
							startActivity(intent);
							finish();
							
							//new performBackgroundTaskupload_login().execute();
							

						} catch (Exception e) {
							e.printStackTrace();
						}
					} 

				} else {
					show = new AlertDialog.Builder(Login.this)
							.setMessage("No SD CARD FOUND")
							.setPositiveButton("Ok", null).show();
				}

				}}
		});
	}


	public class performBackgroundTaskupload_login extends
			AsyncTask<Void, Void, Void> {
		String responsefromserver = null;
		private ProgressDialog mProgressDialog;

		protected void onPreExecute() {
			mProgressDialog = new ProgressDialog(Login.this);
			mProgressDialog.setMessage("Logging In. Please wait...");
			mProgressDialog.setCancelable(false);
			mProgressDialog.show();
		}

		protected Void doInBackground(Void... params) {
			try {
				JSONArray ja1 = new JSONArray();
				JSONObject jsonarray = new JSONObject();
				try {
					Log.e("ResponseForInputdata", username+passWord+sdoCode);
					jsonarray.put("MRCODE", userName);
					jsonarray.put("PASSWORD", passWord);
					jsonarray.put("SDOCODE", sdoCode);
					ja1.put(jsonarray);

					SendRequest req = new SendRequest();
					String url1 = "loginMobileMvsUser";
					responsefromserver = req.sendRequest(url1, ja1);

					Log.e("Response", responsefromserver);
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
			if (responsefromserver == null) {loginFromMobile();}

			else if (responsefromserver.equals("NotValid")) {
				show = new AlertDialog.Builder(Login.this).setTitle("Info")
						.setMessage("Invalid Credentials")
						.setPositiveButton("OK", null).show();

			}

			else if (responsefromserver.equals("valid")) {

				DbLogin dbAdapter = new DbLogin(getBaseContext());
				dbAdapter.open();
				if (dbAdapter.loginCheck(userName, passWord, sdoCode) == false) {
					dbAdapter.register(userName, passWord, sdoCode);
				}
				dbAdapter.close();
				Intent intent = new Intent(Login.this, DashBoard.class);
				startActivity(intent);
				finish();

			} else {
				loginFromMobile();
			}
		}
	}
private void loginFromMobile(){


	try {
		DbLogin dbAdapter = new DbLogin(getBaseContext());
		dbAdapter.open();
		if (dbAdapter.loginCheck(userName, passWord, sdoCode)) {
			dbAdapter.close();

			show = new AlertDialog.Builder(Login.this)
					.setTitle("Network Problem")
					.setCancelable(false)
					.setMessage("Login from mobile")
					.setPositiveButton("OK",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(
										DialogInterface dialog,
										int which) {
									Intent intent = new Intent(
											Login.this,
											DashBoard.class);
									startActivity(intent);
									finish();
								}
							}).show();

		} else {
			
			show = new AlertDialog.Builder(Login.this)
					.setTitle("Network Problem")
					.setCancelable(false)
					.setMessage(
							"Login from mobile\nInvalid Credentials")
					.setPositiveButton("OK", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							
						}
					}).show();
		}

	} catch (Exception e) {
		show = new AlertDialog.Builder(Login.this)
				.setTitle("Info")
				.setMessage(
						"Some problem occured. Please close the application and try again.")
				.setPositiveButton("OK", null).show();
	}
}

private boolean isLocationProper(String location) {
	for (int i = 0; i < locations.length; i++) {
		if(locations[i].trim().equals(location)) {
			return true;
		}
	}
	return false;
}

private void updateApk(Context context) {
    try {// TODO
        String versionName = context.getPackageManager().getPackageInfo( getPackageName(), 0).versionName;
        Intent updateapk = new Intent(context, AndroidautoUpdateActivity.class);
        // updateapk.putExtra("    key    ", " apk  name " + "@@@" + app
        // version + "@@@" + full url );
        updateapk.putExtra("apk_version", "DISCONNECTION_CESC" + "@@@" + versionName + "@@@" + SendRequest.url1);
        startActivity(updateapk);
    } catch (Exception e) {
        e.printStackTrace();
    }
}

public void onClickCheckNewVersion(View v) {
	updateApk(Login.this);
}

}