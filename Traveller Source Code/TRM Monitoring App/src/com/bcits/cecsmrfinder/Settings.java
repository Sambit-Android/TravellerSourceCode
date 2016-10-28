package com.bcits.cecsmrfinder;


import java.util.Locale;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Settings extends Activity {

	Locale myLocale =null; 
	Button changelanguage =null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle("Settings");
		setContentView(R.layout.settings);
		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
					.permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}		
		changelanguage = (Button) findViewById(R.id.changelanguage);
		
		
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
			Intent intent = new Intent(Settings.this, Dashboard.class);
			startActivity(intent);
			
			return true;
			
			
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
	
	
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		
		
		
		changelanguage.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				
				/*new AlertDialog.Builder(MainActivity.this)
				.setTitle("Info..! ")
				.setMessage("Select OK to change language")
				.setCancelable(false)
				.setNegativeButton(getResources().getString(R.string.OK), new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {

						setLocale("hi");

					}
				}).show();*/
				
				
				
				new AlertDialog.Builder(Settings.this)
				.setTitle(Settings.this.getString(R.string.change))
				.setMessage(Settings.this.getString(R.string.change))
				.setNegativeButton(Settings.this.getString(R.string.kannada), new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						
						
						setLocale("ka");
						
						
					}
				})
				.setPositiveButton(Settings.this.getString(R.string.english), new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						
						
						setLocale("en");
						
						
					}
				}).show();
				
			}
		});
		
	}
	
	
	
	
	public void setLocale(String lang) { 
	    myLocale = new Locale(lang); 
	    Resources res = getResources(); 
	    DisplayMetrics dm = res.getDisplayMetrics(); 
	    Configuration conf = res.getConfiguration(); 
	    conf.locale = myLocale; 
	    res.updateConfiguration(conf, dm); 
	    Intent refresh = new Intent(this, Settings.class); 
	    startActivity(refresh); 
	} 
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
	}
	

}
