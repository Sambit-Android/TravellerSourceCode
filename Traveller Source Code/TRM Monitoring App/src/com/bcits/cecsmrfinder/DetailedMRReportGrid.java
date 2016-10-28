package com.bcits.cecsmrfinder;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.sqlite.cescip.DBMrWiseBilling;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

public class DetailedMRReportGrid extends Activity {
	GridView mrgridview;
	Context context;
	JSONArray mrgriddata;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM); 
		getActionBar().setCustomView(R.layout.custom_title_billing_progress);
		getActionBar().getCustomView().findViewById(R.id.title);
		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.custom_title_billing_progress, null);
		actionBar.setCustomView(view, new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
		actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		setContentView(R.layout.mrreportgrid);
		
		
		
		
		
		mrgridview = (GridView) findViewById(R.id.mrgridview);
		
		DBMrWiseBilling databasehandler = new DBMrWiseBilling(getApplicationContext());
		databasehandler.open();
		
		
		mrgriddata = databasehandler.getMrWiseReportData();
		databasehandler.close();
		/*databasehandler.close();
		for (int i = 0; i < mrgriddata.length(); i++) {
			try {
				obj.put("mr_code", mrgriddata.getJSONObject(i).getString("mr_code"));
				obj.put("mr_name", mrgriddata.getJSONObject(i).getString("mr_name"));
				obj.put("mr_mobile", mrgriddata.getJSONObject(i).getString("mr_mobile"));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			mrgriddata.put(obj);
		}*/
		try {
			mrgridview.setAdapter(new MrGridCustomAdapter(this, mrgriddata
					.toString()));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mrgridview.setOnItemClickListener(new OnItemClickListener() {
	        
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				TextView mrcode = (TextView)view.findViewById(R.id.mrcode);
				TextView mrname = (TextView)view.findViewById(R.id.name);
				TextView mrmobile = (TextView)view.findViewById(R.id.mobilenumber);
				// Toast.makeText(getApplicationContext(), "Clicked details \nMR CODE = "+mrcode.getText().toString()+"\nMR NAME = "+mrname.getText().toString()+"\nMR MOBILE NUMBER = "+mrmobile.getText().toString(), Toast.LENGTH_LONG).show();
				Intent detailedCardActivity = new Intent(getApplicationContext(),DetailedMRCardActivity.class);
				detailedCardActivity.putExtra("mrcode", mrcode.getText().toString());
				detailedCardActivity.putExtra("name", mrname.getText().toString());
				detailedCardActivity.putExtra("mobilenumber", mrmobile.getText().toString());
				
				startActivity(detailedCardActivity);
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

}
