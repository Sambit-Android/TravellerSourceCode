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

public class RRnoMapActivity extends Activity {
	// static final LatLng HAMBURG = new LatLng(53.558, 9.927);
	// static final LatLng KIEL = new LatLng(53.551, 9.993);
	private GoogleMap map;
	LatLng mypoint;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rrno_map);
		getActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM); 
		getActionBar().setCustomView(R.layout.custom_title_billing_progress);
		getActionBar().getCustomView().findViewById(R.id.title);
		
		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.custom_title_billing_progress, null);
		actionBar.setCustomView(view, new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
		actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		try {
			String map_obj = getIntent().getStringExtra("map_obj");
			JSONObject latlong = new JSONObject(map_obj);
			String lat = latlong.getString("lat");
			String longitude = latlong.getString("long");
			String rrno = latlong.getString("rr_no");
			String mrcod = latlong.getString("mrcode");
			String tariffdesc = latlong.getString("tariff");
			String billno = latlong.getString("billno");
			String rdngdate = latlong.getString("rdngdate");
			Double latitud = Double.parseDouble(lat);
			Double longitid = Double.parseDouble(longitude);
			
			String name = latlong.getString("name");
			String mobile = latlong.getString("mobile");
			String meterstatus = latlong.getString("meterstatus");
			String billdate = latlong.getString("billdate");
			String netamtdue= latlong.getString("netamtdue");

			if (latitud > 0 && longitid > 0) {
				mypoint = new LatLng(latitud, longitid);
			} else {
				mypoint = new LatLng(12.2958, 76.6394);
			}

			map = ((MapFragment) getFragmentManager()
					.findFragmentById(R.id.map)).getMap();
			Marker hamburg = map.addMarker(new MarkerOptions()
					.position(mypoint)
					.title("Consumer Details")
					.snippet(
							mrcod + "," +rrno+ "," +tariffdesc +","+billno+","+rdngdate+","+name+","+mobile+","+meterstatus+","+billdate+","+netamtdue)
					.icon(
															  BitmapDescriptorFactory
															 .fromResource(R.
															  drawable
															  .homeiconblue))				
					.draggable(false));

			map.setInfoWindowAdapter(new InfoWindowAdapter() {

				@Override
				public View getInfoWindow(Marker arg0) {

					return null;
				}

				@Override
				public View getInfoContents(Marker arg0) {
					// Getting view from the layout file
					// info_window_layout
					View v = getLayoutInflater().inflate(R.layout.custom_map_info,
							null);
					TextView tvinfo = (TextView) v.findViewById(R.id.info);
					TextView netamtduetv = (TextView) v.findViewById(R.id.netamtduetxt);
					TextView tvType = (TextView) v.findViewById(R.id.info1);
					TextView tvfeeder = (TextView) v.findViewById(R.id.info2);
					TextView tvucode = (TextView) v.findViewById(R.id.info3);
					// tvucode.setVisibility(View.GONE);
					TextView tvlat = (TextView) v.findViewById(R.id.info4);
					//tvlat.setVisibility(View.GONE);
					TextView tvlong = (TextView) v.findViewById(R.id.info5);
					//tvlong.setVisibility(View.GONE);
					TextView tvbillmonth = (TextView) v
							.findViewById(R.id.info6);
					//tvbillmonth.setVisibility(View.GONE);
					TextView tvreader = (TextView) v.findViewById(R.id.info7);
					//tvreader.setVisibility(View.GONE);
					TextView tvreadermobile = (TextView) v
							.findViewById(R.id.info8);
					//tvreadermobile.setVisibility(View.GONE);
					//TextView tariffc = (TextView) v.findViewById(R.id.info9);
					//tariffc.setVisibility(View.GONE);

					tvucode.setAutoLinkMask(Linkify.PHONE_NUMBERS);
					tvreadermobile.setAutoLinkMask(Linkify.PHONE_NUMBERS);
					tvinfo.setText(arg0.getSnippet().split(",")[0].trim().equals(null)?"-":arg0.getSnippet().split(",")[0]);
					
					tvType.setText(arg0.getSnippet().split(",")[1].trim().equals(null)?"-":arg0.getSnippet().split(",")[1]);
					tvfeeder.setText(arg0.getSnippet().split(",")[2].equals(null)?"-":arg0.getSnippet().split(",")[2]);
					tvucode.setText(arg0.getSnippet().split(",")[3].equals(null)?"-":arg0.getSnippet().split(",")[3]);
					tvlat.setText(arg0.getSnippet().split(",")[4].equals(null)?"-":arg0.getSnippet().split(",")[4]);
					tvlong.setText(arg0.getSnippet().split(",")[5].equals(null)?"-":arg0.getSnippet().split(",")[5]);
					tvbillmonth.setText(arg0.getSnippet().split(",")[6].equals(null)?"-":arg0.getSnippet().split(",")[6]);
					tvreader.setText(arg0.getSnippet().split(",")[7].equals(null)?"-":arg0.getSnippet().split(",")[7]);
					tvreadermobile.setText(arg0.getSnippet().split(",")[8].equals("null")?"":arg0.getSnippet().split(",")[8]);
					netamtduetv.setText(arg0.getSnippet().split(",")[9].equals(null)?"-":arg0.getSnippet().split(",")[9]);
					
					return v;
				}
			});

			// Move the camera instantly to hamburg with a zoom of 15.
			map.moveCamera(CameraUpdateFactory.newLatLngZoom(mypoint, 8));

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