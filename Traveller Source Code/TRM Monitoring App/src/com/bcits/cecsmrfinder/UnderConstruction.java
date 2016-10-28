package com.bcits.cecsmrfinder;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

public class UnderConstruction extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		if(Dashboard.flag==1){
			getActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM); 
			getActionBar().setCustomView(R.layout.custom_title_consumerhistory);
			getActionBar().getCustomView().findViewById(R.id.title);
			final ActionBar actionBar = getActionBar();
			actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
			LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View view = inflater.inflate(R.layout.custom_title_consumerhistory, null);
			actionBar.setCustomView(view, new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
			actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		}else if (Dashboard.flag==2){
			getActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM); 
			getActionBar().setCustomView(R.layout.custom_title_sbmstatus);
			getActionBar().getCustomView().findViewById(R.id.title);
			final ActionBar actionBar = getActionBar();
			actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
			LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View view = inflater.inflate(R.layout.custom_title_sbmstatus, null);
			actionBar.setCustomView(view, new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
			actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		}else if (Dashboard.flag==3){
			getActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM); 
			getActionBar().setCustomView(R.layout.custom_title_messaging);
			getActionBar().getCustomView().findViewById(R.id.title);
			final ActionBar actionBar = getActionBar();
			actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
			LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View view = inflater.inflate(R.layout.custom_title_messaging, null);
			actionBar.setCustomView(view, new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
			actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		}else if (Dashboard.flag==4){
			getActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM); 
			getActionBar().setCustomView(R.layout.custom_title_ticketingstatus);
			getActionBar().getCustomView().findViewById(R.id.title);
			final ActionBar actionBar = getActionBar();
			actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
			LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View view = inflater.inflate(R.layout.custom_title_ticketingstatus, null);
			actionBar.setCustomView(view, new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
			actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		}
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.underconstruction);
	}
	@Override
	public void onBackPressed() {
		finish();
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
