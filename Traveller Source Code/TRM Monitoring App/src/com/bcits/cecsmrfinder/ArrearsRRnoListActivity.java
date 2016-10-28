package com.bcits.cecsmrfinder;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;

import com.sqlite.cescip.DBMrWiseBilling;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class ArrearsRRnoListActivity extends Activity {
	ListView list;
	TextView todaysdate,range,count;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		getActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		getActionBar().setCustomView(R.layout.custom_title_arrears);
		getActionBar().getCustomView().findViewById(R.id.title);
		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		LayoutInflater inflater = (LayoutInflater) this
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.custom_title_arrears, null);
		actionBar.setCustomView(view, new ActionBar.LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.MATCH_PARENT));
		actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.arrearsrrno_list);
		
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String formattedDate = sdf.format(date);
		
		todaysdate = (TextView) findViewById(R.id.todaysdate);
		range = (TextView) findViewById(R.id.range);
		range.setText("From "+ArrearsRangeGridActivity.arrearsRange);
		count = (TextView) findViewById(R.id.count);
		count.setText("Total - "+ArrearsRangeGridActivity.consumer_count+" Consumers");
		todaysdate.setText("Details As On - " + formattedDate);
		list = (ListView)findViewById(R.id.list);
		
		
		
		try {
			ArrearsRRnoCustomListAdapter adapter = new ArrearsRRnoCustomListAdapter(ArrearsRRnoListActivity.this);
			list.setAdapter(adapter);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	public void onBackPressed(){
		finish();
	}
}
