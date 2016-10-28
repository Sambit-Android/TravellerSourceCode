package com.bcits.cecsmrfinder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Paint;
import android.location.Location;
import android.location.LocationListener;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.provider.Settings;
import android.support.v4.app.FragmentActivity;
import android.text.util.Linkify;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.BounceInterpolator;
import android.view.animation.Interpolator;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;

import com.bcits.utils.AmrMethods;
import com.bcits.utils.GPSTracker;
import com.bcits.utils.SendRequest;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerDragListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.sqlite.cescip.DBLocation;

@SuppressLint("NewApi")
public class MapSurveyArreas extends FragmentActivity implements
		OnMarkerClickListener, OnMarkerDragListener, OnMapClickListener,
		LocationListener {
	GoogleMap mMap;
	private TextView mTapTextView;
	private Button b;
	double lat_post = 0.00;
	double long_post = 0.00;
	private Marker currentlocation;
	String dateofview = "";
	AlertDialog show;

	protected void onCreate(Bundle savedInstanceState) {
		getActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM); 
		getActionBar().setCustomView(R.layout.custom_title_arrears);
		getActionBar().getCustomView().findViewById(R.id.title);
		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.custom_title_arrears, null);
		actionBar.setCustomView(view, new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
		actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mTapTextView = (TextView) findViewById(R.id.tap_text);
		setUpMapIfNeeded();
		if (AmrMethods.doubleClick()) {
			Boolean isSDPresent2 = android.os.Environment
					.getExternalStorageState().equals(
							android.os.Environment.MEDIA_MOUNTED);
			if (isSDPresent2) {
				if (haveNetworkConnection()) {
					try {

						GPSTracker gpsTracker = new GPSTracker(
								MapSurveyArreas.this);
						if (gpsTracker.getIsGPSTrackingEnabled()) {
							new performBackgroundTaskLoadInstalledIPandDTC()
									.execute();
						} else {
							// can't get location
							showSettingsAlert(MapSurveyArreas.this);
						}

					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {

					// rr numbers

					DBLocation db = new DBLocation(getBaseContext());
					db.open();
					Cursor cursor = db.getOfflineLocations();
					if (cursor == null) {
						Toast.makeText(
								getApplicationContext(),
								getResources().getString(
										R.string.nolocationSaved),
								Toast.LENGTH_LONG).show();
					} else {
						int k = 0;
						while (cursor != null && cursor.moveToPosition(k)) {
							k++;
							LatLng BCITS = new LatLng(Double.parseDouble(cursor
									.getString(0)), Double.parseDouble(cursor
									.getString(1)));
							// currentlocation = mMap.addMarker(new
							// MarkerOptions().position(BCITS).title(cursor.getString(2)).snippet("Type :"+cursor.getString(3)+",IP :"+cursor.getString(4)+",DTC :"+cursor.getString(5)).draggable(false));
							mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
									BCITS, 8));
							mMap.setOnMapClickListener(MapSurveyArreas.this);
							mMap.setOnMarkerClickListener(new OnMarkerClickListener() {
								@Override
								public boolean onMarkerClick(Marker arg0) {
									lat_post = (double) arg0.getPosition().latitude;
									long_post = (double) arg0.getPosition().longitude;
									mTapTextView.setText("tapped, point="
											+ lat_post + " , " + long_post);
									return false;
								}
							});
						}
					}
					db.close();
					// End of rr numbers

				}
			}

		} else {
			show = new AlertDialog.Builder(MapSurveyArreas.this)
					.setMessage(
							getResources().getString(R.string.NoSDCARDFOUND))
					.setPositiveButton(getResources().getString(R.string.OK),
							null).show();
		}
		b = (Button) findViewById(R.id.okButton);
		b.setVisibility(View.GONE);
		/*
		 * b.setOnClickListener(new OnClickListener() {
		 * 
		 * @Override public void onClick(View v) { finish(); } });
		 */

		dateofview = AmrMethods.getCurrentTimeStamp2().trim();
		
		RadioGroup rgViews = (RadioGroup) findViewById(R.id.rg_views);
		 
        rgViews.setOnCheckedChangeListener(new OnCheckedChangeListener() {
 
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.rb_normal){
                	mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                }else if(checkedId == R.id.rb_satellite){
                	mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                }else if(checkedId == R.id.rb_terrain){
                	mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                }
            }
        });
		
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

	@Override
	protected void onResume() {
		if (Login.sdoCode == null || Login.sdoCode.isEmpty()
				|| Login.sdoCode.trim().equals("")) {
			show = new AlertDialog.Builder(MapSurveyArreas.this)
					.setCancelable(false)
					.setMessage(
							getResources().getString(R.string.sessionexpired))
					.setPositiveButton(getResources().getString(R.string.OK),
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									Intent intent = new Intent(
											MapSurveyArreas.this, Login.class);
									startActivity(intent);

								}
							}).show();
		}
		super.onResume();
		setUpMapIfNeeded();
	}

	private void setUpMapIfNeeded() {
		// Do a null check to confirm that we have not already instantiated the
		// map.
		if (mMap == null) {
			// Try to obtain the map from the SupportMapFragment.
			mMap = ((SupportMapFragment) getSupportFragmentManager()
					.findFragmentById(R.id.map)).getMap();
			// Check if we were successful in obtaining the map.
			if (mMap != null) {
				setUpMap();
			}
		}
	}

	private void setUpMap() {

		GPSTracker mGPS = new GPSTracker(this);
		if (mGPS.getIsGPSTrackingEnabled()) {
			LatLng currentloc = new LatLng(mGPS.getLatitude(),
					mGPS.getLongitude());
			@SuppressWarnings("unused")
			LatLng BCITS = new LatLng(13.04, 77.58);
			// currentlocation = mMap.addMarker(new
			// MarkerOptions().position(currentloc).title("Location").snippet("Current Location").draggable(true));
			mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentloc, 8));

		} else {
			LatLng BCITS = new LatLng(13.04, 77.58);
			// currentlocation = mMap.addMarker(new
			// MarkerOptions().position(BCITS).title("Location").snippet("Last Loaded Location").draggable(true));
			mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(BCITS, 8));
		}
		mMap.setOnMapClickListener(this);
		mMap.setOnMarkerClickListener(new OnMarkerClickListener() {

			@Override
			public boolean onMarkerClick(Marker arg0) {
				lat_post = (double) arg0.getPosition().latitude;
				long_post = (double) arg0.getPosition().longitude;
				mTapTextView.setText("tapped, point=" + lat_post + " , "
						+ long_post);
				return false;
			}
		});

		mMap.setOnMarkerDragListener(new OnMarkerDragListener() {

			@Override
			public void onMarkerDragStart(Marker arg0) {

			}

			@Override
			public void onMarkerDragEnd(Marker arg0) {

			}

			@Override
			public void onMarkerDrag(Marker arg0) {
				lat_post = (double) arg0.getPosition().latitude;
				long_post = (double) arg0.getPosition().longitude;
				mTapTextView.setText("tapped, point=" + lat_post + " , "
						+ long_post);

			}
		});
	}

	@Override
	public void onMarkerDrag(Marker arg0) {

	}

	@Override
	public void onMarkerDragEnd(Marker arg0) {

	}

	@Override
	public void onMarkerDragStart(Marker arg0) {

	}

	@Override
	public boolean onMarkerClick(final Marker marker) {
		if (marker.equals(currentlocation)) {
			// This causes the marker at Perth to bounce into position when it
			// is clicked.
			final Handler handler = new Handler();
			final long start = SystemClock.uptimeMillis();
			final long duration = 1500;

			final Interpolator interpolator = new BounceInterpolator();

			handler.post(new Runnable() {
				@Override
				public void run() {
					long elapsed = SystemClock.uptimeMillis() - start;
					float t = Math.max(
							1 - interpolator.getInterpolation((float) elapsed
									/ duration), 0);
					marker.setAnchor(0.5f, 1.0f + 2 * t);

					if (t > 0.0) {
						// Post again 16ms later.
						handler.postDelayed(this, 16);
					}
				}
			});
		}
		return false;
	}

	@Override
	public void onMapClick(LatLng point) {
		lat_post = (double) point.latitude;
		long_post = (double) point.longitude;
		mTapTextView.setText("tapped, point=" + point);

	}

	@SuppressWarnings("unused")
	private double[] createRandLocation(double latitude, double longitude) {

		return new double[] { latitude + ((Math.random() - 0.5) / 500),
				longitude + ((Math.random() - 0.5) / 500),
				150 + ((Math.random() - 0.5) * 8) };
	}

	@Override
	public void onLocationChanged(Location location) {
		double latitude = location.getLatitude();
		double longitude = location.getLongitude();
		LatLng latLng = new LatLng(latitude, longitude);
		// mMap.addMarker(new MarkerOptions().position(latLng));
		mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
		mMap.animateCamera(CameraUpdateFactory.zoomTo(8));

	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {

	}

	@Override
	public void onProviderEnabled(String provider) {

	}

	@Override
	public void onProviderDisabled(String provider) {

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

			finish();
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}

	private class performBackgroundTaskLoadInstalledIPandDTC extends
			AsyncTask<Void, Void, Void> {
		String responsefromserver = null;
		private ProgressDialog mProgressDialog;

		protected void onPreExecute() {
			mProgressDialog = new ProgressDialog(MapSurveyArreas.this,R.style.MyTheme2);
			mProgressDialog.setMessage("Fetching Arrears Details On Map...");
			mProgressDialog.setCancelable(false);
			mProgressDialog.show();
		}

		protected Void doInBackground(Void... params) {
			try {
				JSONArray ja1 = new JSONArray();
				JSONObject jsonarray = new JSONObject();
				try {

					// Login.sdoCode = "5111";
					if(Login.sdoCode.equals("1410")){
						jsonarray.put("sdocode","2120");
					}else{
					jsonarray.put("sdocode",Login.sdoCode);
					}
					//jsonarray.put("sdocode", Login.sdoCode);
					jsonarray.put("billmonth", Dashboard.month_arreas);
					
					if(ArrearsRangeGridActivity.arrearsRange.equals("500 to 1000")){
						jsonarray.put("arrearrange",
								"500 and 1000");
					}else if(ArrearsRangeGridActivity.arrearsRange.equals("1001 to 5000")){
						jsonarray.put("arrearrange",
								"1000 and 5000");
					}else if(ArrearsRangeGridActivity.arrearsRange.equals("5001 to 10000")){
						jsonarray.put("arrearrange",
								"5000 and 10000");
					}else if(ArrearsRangeGridActivity.arrearsRange.equals("10001 to 25000")){
						jsonarray.put("arrearrange",
								"10000 and 25000");
					}else if(ArrearsRangeGridActivity.arrearsRange.equals("25001 to 50000")){
						jsonarray.put("arrearrange",
								"25000 and 50000");
					}else if(ArrearsRangeGridActivity.arrearsRange.equals("50001 to 100000")){
						jsonarray.put("arrearrange",
								"50000 and 100000");
					}else if(ArrearsRangeGridActivity.arrearsRange.equals("Above 100000")){
						jsonarray.put("arrearrange",
								"100000");
					}
					
					ja1.put(jsonarray);

					SendRequest req = new SendRequest();
					String url1 = "getConsumersArrearMobile";
					responsefromserver = req.uploadToServer(url1, ja1);
					System.out.println("INPUT     "+ja1.toString()+"          DATA  "+responsefromserver);
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
			if (responsefromserver == null) {

				new AlertDialog.Builder(MapSurveyArreas.this)
						.setTitle("Error..!")
						.setMessage(
								"Sorry, server down / some error occurred while connecting server.")
						.setPositiveButton("OK", null).show();

			} else if (responsefromserver.equals("invalid")) {
				show = new AlertDialog.Builder(MapSurveyArreas.this)
						.setTitle(getResources().getString(R.string.Info))
						.setMessage("Something went wrog try again Later")
						.setPositiveButton(
								getResources().getString(R.string.OK), null)
						.show();

			} else if (responsefromserver.equalsIgnoreCase("[]")) {
				show = new AlertDialog.Builder(MapSurveyArreas.this)
						.setTitle(getResources().getString(R.string.Info))
						.setMessage("no proper lattitude and langitude")
						.setPositiveButton(
								getResources().getString(R.string.OK), null)
						.show();
			}

			else if (responsefromserver != null) {
				JSONArray ja = null;
				try {
					ja = new JSONArray(responsefromserver);
				} catch (JSONException e) {
					e.printStackTrace();
					finish();
					Toast.makeText(getApplicationContext(),
							"Data not available", Toast.LENGTH_SHORT).show();
				}
				// arrayCircle = new ArrayList<GetSetCircle>();
				if (ja != null) {

					DBLocation dbAdapter = new DBLocation(getBaseContext());
					dbAdapter.open();
					dbAdapter.emptyTable();
					for (int i = 0; i < ja.length(); i++) {
						JSONObject obj = null;
						try {
							obj = ja.getJSONObject(i);
						} catch (JSONException e1) {
							e1.printStackTrace();
						}
						try {

							String lats = "";
							String longs = "";
							try {
								if (obj.getString("lattitude").equals("-")
										|| obj.getString("longitude")
												.equals("")) {
									lats = "12.2958";
									longs = "76.6394";
								} else if(!obj.getString("lattitude").equals("")||!obj.getString("longitude").equals("")){
									if(obj.getString("lattitude").contains("E")||obj.getString("longitude").contains("E")||obj.getString("lattitude").equals("0.0")||obj.getString("longitude").equals("0.0")){
										lats = "12.2958";
										longs = "76.6394";
									}else{
									lats = obj.getString("lattitude");
									longs =obj.getString("longitude");
									}
								} else {
									lats = obj.getString("lattitude");
									longs = obj.getString("longitude");
								}
							} catch (Exception e) {
								lats = "12.2958";
								longs = "76.6394";
							}
							LatLng BCITS = new LatLng(Double.parseDouble(lats),
									Double.parseDouble(longs));

							// LatLng BCITS = new
							// LatLng(Double.parseDouble(obj.getString("lattitude")),Double.parseDouble(obj.getString("longitude")));
							// if(obj.getString("letter").equalsIgnoreCase("DTC")){
							currentlocation = mMap
									.addMarker(new MarkerOptions()
											.position(BCITS)
											.title(obj.getString("arrears"))
											.snippet(
													obj.getString("consumer_sc_no")
															+ ","
															+ obj.getString("bill_month")
															+ ","+ obj.getString("sdo_code")
															+ ","+ obj.getString("consumer_name")
															+ ","+ obj.getString("reading_date")
															/*+ ","+ obj.getString("mobile")*/)
											.draggable(false)
															  .icon(
															  BitmapDescriptorFactory
															 .fromResource(R.
															  drawable
															  .homeicon))
															 );
							// }
							// else{
							// currentlocation = mMap.addMarker(new
							// MarkerOptions().position(BCITS).title(obj.getString("letter")).snippet(obj.getString("autharized")+",Feeder :"+obj.getString("substation")+","+obj.getString("dtc")+",latitude :"+obj.getString("latitude")+",longitude :"+obj.getString("longitude")).draggable(false).icon(BitmapDescriptorFactory.fromResource(R.drawable.pegman)));
							// }
							// currentlocation.showInfoWindow();
							mMap.setInfoWindowAdapter(new InfoWindowAdapter() {

								@Override
								public View getInfoWindow(Marker arg0) {

									return null;
								}

								@Override
								public View getInfoContents(Marker arg0) {
									// Getting view from the layout file
									// info_window_layout
									View v = getLayoutInflater().inflate(
											R.layout.custom_arrear_info, null);

									TextView tvType = (TextView) v
											.findViewById(R.id.info1);
									TextView tvfeeder = (TextView) v
											.findViewById(R.id.info2);
									TextView tvucode = (TextView) v
											.findViewById(R.id.info3);
									TextView tvlat = (TextView) v
											.findViewById(R.id.info4);
									TextView tvlati = (TextView) v
											.findViewById(R.id.info5);
									TextView mobile = (TextView) v
											.findViewById(R.id.mobile);
									TextView tvrdngdt = (TextView) v
											.findViewById(R.id.infordngdt);
									
									/*tvinfo.setText(arg0.getTitle());
									tvinfo.setPaintFlags(tvinfo.getPaintFlags()
											| Paint.UNDERLINE_TEXT_FLAG);*/
									//tvType.setTextColor(Color.BLACK);
									tvType.setText(arg0.getSnippet().split(",")[0]
											.trim().equals(null)?"-":arg0.getSnippet().split(",")[0]
													.trim());
									tvfeeder.setText(arg0.getSnippet().split(
											",")[3].equals(null)?"-":arg0.getSnippet().split(",")[3]);
									tvucode.setText(arg0.getSnippet()
											.split(",")[2].equals(null)?"-":arg0.getSnippet().split(",")[2]);
									tvlat.setText(arg0.getSnippet()
											.split(",")[1].equals(null)?"-":arg0.getSnippet().split(",")[1]);
									tvlati.setText(arg0.getTitle());
									tvrdngdt.setText(arg0.getSnippet()
											.split(",")[4].equals(null)?"-":arg0.getSnippet().split(",")[4]);
									/*mobile.setText(arg0.getSnippet()
											.split(",")[5].equals(null)?"-":arg0.getSnippet().split(",")[5]);*/
									mobile.setText("-");
									Linkify.addLinks(mobile, Linkify.PHONE_NUMBERS);
									return v;
								}
							});
							mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
									BCITS, 8));
							mMap.animateCamera(CameraUpdateFactory.zoomTo(11), 2000, null);
							mMap.setOnMapClickListener(MapSurveyArreas.this);
							mMap.setOnMarkerClickListener(new OnMarkerClickListener() {
								@Override
								public boolean onMarkerClick(Marker arg0) {
									lat_post = (double) arg0.getPosition().latitude;
									long_post = (double) arg0.getPosition().longitude;
									mTapTextView.setText("tapped, point="
											+ lat_post + " , " + long_post);
									return false;
								}
							});
							/*
							 * dbAdapter.saveLocations(
							 * obj.getString("latitude"),
							 * obj.getString("longitude"),
							 * obj.getString("letter"),
							 * obj.getString("autharized"),
							 * obj.getString("dtc"));
							 */

						} catch (JSONException e) {
							e.printStackTrace();
						}
					}
					dbAdapter.close();
				}

				// ArrayAdapter<String> adapter = new
				// ArrayAdapter<String>(Add_IP.this,android.R.layout.simple_dropdown_item_1line,
				// listdata);
				// rrnumberauto.setThreshold(1);
				// rrnumberauto.setAdapter(adapter);

			} else {

			}
		}

	}

	public void showSettingsAlert(final Context context) {
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
		alertDialog.setTitle("Turn on Location");
		alertDialog
				.setMessage("Turn on Location Services to See Your Current Location");
		alertDialog.setPositiveButton(getResources().getString(R.string.OK),
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						Intent intent = new Intent(
								Settings.ACTION_LOCATION_SOURCE_SETTINGS);
						context.startActivity(intent);
					}
				});
		alertDialog.setNegativeButton("CANCEL",
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.cancel();
					}
				});
		alertDialog.show();
	}

}
