package com.bcits.disconreconn;



import com.bcits.disconnreconn.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity {

	private Dialog dialog;
	TextView mTitle;
	Button btnlogin;
	Intent intent;
	public static String mrcode , password ,sitecode;
	EditText  edMrcode, edPassword , edSitecode;
	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		/*new LoadViewTask().execute();*/
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.loginactivity);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,R.layout.window_title);
		mTitle = (TextView) findViewById(R.id.title);
		btnlogin = (Button) findViewById(R.id.buttonLogin);
		edMrcode = (EditText) findViewById(R.id.editText1);
		edPassword = (EditText) findViewById(R.id.password);
		edSitecode = (EditText) findViewById(R.id.editText3);
		

		// changes to solve the error android.os.NetworkOnMainThreadException start
		
		if (android.os.Build.VERSION.SDK_INT > 9) {
		    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		    StrictMode.setThreadPolicy(policy);
		}
		
		// changes to solve the error android.os.NetworkOnMainThreadException end 
		
		
		
	}
	
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	
		mTitle.setText("Meter Management ");
		btnlogin.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				
				
				mrcode = edMrcode.getText().toString().trim().replaceAll(" +", "");
				sitecode = edSitecode.getText().toString().trim();
				password = edPassword.getText().toString().trim();
				
				if (sitecode.equals(" ") || sitecode.equals("") )
				{
					Toast.makeText(getApplicationContext(), "Please enter the site code", Toast.LENGTH_SHORT).show();
				}	
				else if (mrcode.equals(" ") || mrcode.equals("") )
				{
					Toast.makeText(getApplicationContext(), "Please enter the mr code", Toast.LENGTH_SHORT).show();
				}
				
				else if (password.equals(" ") || password.equals("") )
				{
					Toast.makeText(getApplicationContext(), "Please enter the password", Toast.LENGTH_SHORT).show();
				}
				else if (!(password.trim().equals("12345")))
				{
					Toast.makeText(getApplicationContext(), "Invalid password", Toast.LENGTH_SHORT).show();
				}
				
				
				else{
					
					intent = new Intent(getApplicationContext(), MRStatusActivity.class);
					startActivity(intent);
					
				}
			}
		});
	
	
	}
	
	
	
	class LoadViewTask extends AsyncTask<Void, Integer, Void> {
		// Before running code in separate thread
		@Override
		protected void onPreExecute() {
			dialog = new Dialog(LoginActivity.this, AlertDialog.BUTTON_POSITIVE);

			dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
			dialog.setContentView(R.layout.splash);
			dialog.show();
		}

		// The code to be executed in a background thread.
		@Override
		protected Void doInBackground(Void... params) {
			try {
				// Get the current thread's token
				synchronized (this) {
					// Initialize an integer (that will act as a counter) to
					// zero
					int counter = 0;
					// While the counter is smaller than four
					while (counter <= 4) {
						// Wait 850 milliseconds
						this.wait(720);
						// Increment the counter
						counter++;
						// Set the current progress.
						// This value is going to be passed to the
						// onProgressUpdate() method.
						publishProgress(counter * 25);
					}
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return null;
		}

		// Update the progress
		@Override
		protected void onProgressUpdate(Integer... values) {

		}

		// after executing the code in the thread
		@Override
		protected void onPostExecute(Void result) {
			// close the progress dialog
			dialog.dismiss();

		}
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}
	
	
	
	
	/* 
	 * 
	 * this code is used to close the application after pressing back button*/
	
	 public boolean onKeyDown(int keycode, KeyEvent event) {
		  if (keycode == KeyEvent.KEYCODE_BACK) {
		   Log.e("Main Destroyvvvvvvv ++", "  Main  Destroy++");
		   moveTaskToBack(true);
		  }
		  return super.onKeyDown(keycode, event);
		 }
	
}
