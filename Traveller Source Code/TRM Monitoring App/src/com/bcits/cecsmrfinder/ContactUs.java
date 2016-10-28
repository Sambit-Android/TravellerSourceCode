package com.bcits.cecsmrfinder;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.util.Linkify;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Switch;
import android.widget.TextView;

import com.bcits.utils.AmrMethods;

public class ContactUs extends Activity{

	String SDOCODE,USER;
	AlertDialog show;
	ProgressDialog dialog;
	int sendLocation = 0;
	TextView txtPhone,txtWebsite;
	 private Switch mySwitch;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.contactus);
		setTitle(getResources().getString(R.string.settingsstring));
		 mySwitch = (Switch) findViewById(R.id.myswitch);
		txtWebsite=(TextView)findViewById(R.id.txtWebsite);
		txtPhone=(TextView)findViewById(R.id.txtPhone);

		Linkify.addLinks(txtPhone, Linkify.PHONE_NUMBERS);
		Linkify.addLinks(txtWebsite, Linkify.WEB_URLS);
		
		
		  mySwitch.setChecked(true);
		  //attach a listener to check for changes in state
		  mySwitch.setOnCheckedChangeListener(new OnCheckedChangeListener() {
		   @Override
		   public void onCheckedChanged(CompoundButton buttonView,
		     boolean isChecked) {
		    if(isChecked){
		    	AmrMethods.setDefaults("Autosync", "ON", ContactUs.this);
		    }else{
		    	AmrMethods.setDefaults("Autosync", "OFF", ContactUs.this);
		    }

		   }
		  });
		  if(AmrMethods.getDefaults("Autosync", ContactUs.this) == null){
			 		 mySwitch.setChecked(true);
		  }
		  else{
			  if(AmrMethods.getDefaults("Autosync", ContactUs.this).equalsIgnoreCase("ON")){
			 		 mySwitch.setChecked(true);
			 	}
			  else{
				  mySwitch.setChecked(false);
			  }
		  }
		  
	 }
		
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
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
}

