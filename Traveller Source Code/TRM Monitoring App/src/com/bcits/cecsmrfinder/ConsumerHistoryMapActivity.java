package com.bcits.cecsmrfinder;

import org.json.JSONObject;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class ConsumerHistoryMapActivity extends Activity {
	static LatLng MYSURU = new LatLng(12.2958, 76.6394);
	private GoogleMap map;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rrno_map);
		getActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM); 
		getActionBar().setCustomView(R.layout.custom_title_consumerhistory);
		getActionBar().getCustomView().findViewById(R.id.title);
		
		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.custom_title_consumerhistory, null);
		actionBar.setCustomView(view, new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
		actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		
		String lat = getIntent().getStringExtra("latitude");
		String lon = getIntent().getStringExtra("longitude");
		MYSURU = new LatLng(Double.parseDouble(lat), Double.parseDouble(lon));
		try {
			

			map = ((MapFragment) getFragmentManager()
					.findFragmentById(R.id.map)).getMap();
			Marker hamburg = map.addMarker(new MarkerOptions()
					.position(MYSURU)
					.title("Consumer Details")
					.icon(
															  BitmapDescriptorFactory
															 .fromResource(R.
															  drawable
															  .homeiconblue))				
					.draggable(false));

			

			// Move the camera instantly to hamburg with a zoom of 15.
			map.moveCamera(CameraUpdateFactory.newLatLngZoom(MYSURU, 8));

			// Zoom in, animating the camera.
			map.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		RadioGroup rgViews = (RadioGroup) findViewById(R.id.rg_views);
		 
        rgViews.setOnCheckedChangeListener(new OnCheckedChangeListener() {
 
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.rb_normal){
                	map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                }else if(checkedId == R.id.rb_satellite){
                	map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                }else if(checkedId == R.id.rb_terrain){
                	map.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                }
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