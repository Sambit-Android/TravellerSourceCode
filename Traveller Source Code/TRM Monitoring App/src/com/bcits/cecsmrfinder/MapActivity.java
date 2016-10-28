package com.bcits.cecsmrfinder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.location.Location;
import android.location.LocationListener;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.BounceInterpolator;
import android.view.animation.Interpolator;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bcits.utils.AmrMethods;
import com.bcits.utils.GPSTracker;
import com.bcits.utils.SendRequest;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerDragListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.sqlite.cescip.DBLocation;

@SuppressLint("NewApi")
public class MapActivity extends FragmentActivity implements OnMarkerClickListener, OnMarkerDragListener, OnMapClickListener,LocationListener {
	GoogleMap mMap;
	private TextView mTapTextView;
	private Button b;
	double lat_post=0.00;
	double long_post = 0.00;
	private Marker currentlocation;
	String page="";
	AlertDialog show;

	protected void onCreate(Bundle savedInstanceState) {
		setTitle(getResources().getString(R.string.mapnavigation));
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
						new performBackgroundTaskLoadInstalledIPandDTC().execute();

					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					

					// rr numbers

					DBLocation db = new DBLocation(getBaseContext());
					db.open();
					Cursor cursor = db.getOfflineLocations();
					if (cursor == null) {
						Toast.makeText(getApplicationContext(),getResources().getString(R.string.nolocationSaved), Toast.LENGTH_LONG).show();
					} else {
						int k = 0;
						while (cursor != null && cursor.moveToPosition(k)) {
							k++;
							LatLng BCITS = new LatLng(Double.parseDouble(cursor.getString(0)),Double.parseDouble(cursor.getString(1)));
					    	currentlocation = mMap.addMarker(new MarkerOptions().position(BCITS).title(cursor.getString(2)).snippet("Type :"+cursor.getString(3)+",IP :"+cursor.getString(4)+",DTC :"+cursor.getString(5)).draggable(false));
					    	mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(BCITS, 15));
							mMap.setOnMapClickListener(MapActivity.this);
							mMap.setOnMarkerClickListener(new OnMarkerClickListener() {
								@Override
								public boolean onMarkerClick(Marker arg0) {
									lat_post  = (double) arg0.getPosition().latitude;
									long_post = (double)  arg0.getPosition().longitude;
									mTapTextView.setText("tapped, point=" + lat_post+" , "+long_post);
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
			show = new AlertDialog.Builder(MapActivity.this)
					.setMessage(getResources().getString(R.string.NoSDCARDFOUND))
					.setPositiveButton(getResources().getString(R.string.OK), null).show();
		}
		Intent myIntent = getIntent(); 
		page = myIntent.getStringExtra("page");
		b = (Button)findViewById(R.id.okButton);
		b.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(page.matches("transformer")){
					//Add_transformer.lat_final=lat_post+"";
					//Add_transformer.long_final=long_post+"";
					finish();
				}
				else{/*
					Add_IP.lat_final=lat_post+"";
					Add_IP.long_final=long_post+"";
					Add_IP.isEdit=false;
					finish();
				*/}
				
				
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
		if(Login.sdoCode ==null || Login.sdoCode.isEmpty () || Login.sdoCode.trim().equals("")){
			show =new AlertDialog.Builder(MapActivity.this).setCancelable(false).setMessage(getResources().getString(R.string.sessionexpired)).setPositiveButton(getResources().getString(R.string.OK), new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					Intent intent = new Intent(MapActivity.this, Login.class);
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
		    if(mGPS.getIsGPSTrackingEnabled() ){
		    	LatLng currentloc = new LatLng(mGPS.getLatitude(), mGPS.getLongitude());
		    	@SuppressWarnings("unused")
				LatLng BCITS = new LatLng(13.04, 77.58);
		    	currentlocation = mMap.addMarker(new MarkerOptions().position(currentloc).title("Location").snippet("Current Location").draggable(true));
				mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentloc, 15));
		    	
		    	
		    }else{
		    	LatLng BCITS = new LatLng(13.04, 77.58);
		    	currentlocation = mMap.addMarker(new MarkerOptions().position(BCITS).title("Location").snippet("Last Loaded Location").draggable(true));
				mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(BCITS, 15));
		    }
		mMap.setOnMapClickListener(this);
		mMap.setOnMarkerClickListener(new OnMarkerClickListener() {
			
			@Override
			public boolean onMarkerClick(Marker arg0) {
				lat_post  = (double) arg0.getPosition().latitude;
				long_post = (double)  arg0.getPosition().longitude;
				mTapTextView.setText("tapped, point=" + lat_post+" , "+long_post);
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
				lat_post  = (double) arg0.getPosition().latitude;
				long_post = (double)  arg0.getPosition().longitude;
				mTapTextView.setText("tapped, point=" + lat_post+" , "+long_post);
				
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
		lat_post  = (double) point.latitude;
		long_post = (double) point.longitude;
		mTapTextView.setText("tapped, point=" + point);

	}

	@SuppressWarnings("unused")
	private double[] createRandLocation(double latitude, double longitude) {

		return new double[] { latitude + ((Math.random() - 0.5) / 500),
				longitude + ((Math.random() - 0.5) / 500),
				150 + ((Math.random() - 0.5) * 10) };
	}
	@Override
	public void onLocationChanged(Location location) {
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();
        LatLng latLng = new LatLng(latitude, longitude);
        mMap.addMarker(new MarkerOptions().position(latLng));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(15));
		
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
	
	
	public class performBackgroundTaskLoadInstalledIPandDTC extends
			AsyncTask<Void, Void, Void> {
		String responsefromserver = null;
		private ProgressDialog mProgressDialog;

		protected void onPreExecute() {
			mProgressDialog = new ProgressDialog(MapActivity.this,R.style.MyTheme2);
			mProgressDialog.setMessage(getResources().getString(R.string.LoadingDetails));
			mProgressDialog.setCancelable(false);
			mProgressDialog.show();
		}

		protected Void doInBackground(Void... params) {
			try {
				JSONArray ja1 = new JSONArray();
				JSONObject jsonarray = new JSONObject();
				try {
					jsonarray.put("dtcipsets", "dtcipsets");
					ja1.put(jsonarray);

					SendRequest req = new SendRequest();
					String url1 = "ipSet/mobileMapView";
					responsefromserver = req.uploadToServer(url1, ja1);

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

				// rr numbers

				DBLocation db = new DBLocation(getBaseContext());
				db.open();
				Cursor cursor = db.getOfflineLocations();
				if (cursor == null) {
					Toast.makeText(getApplicationContext(),getResources().getString(R.string.nolocationSaved), Toast.LENGTH_LONG).show();
				} else {
					int k = 0;
					while (cursor != null && cursor.moveToPosition(k)) {
						k++;
					//	listdata.add(cursor.getColumnIndex(DBRRnumbers.COLUMN_RRNUMBER_NAME),cursor.getString(0));
						LatLng BCITS = new LatLng(Double.parseDouble(cursor.getString(0)),Double.parseDouble(cursor.getString(1)));
				    	currentlocation = mMap.addMarker(new MarkerOptions().position(BCITS).title(cursor.getString(2)).snippet("Type :"+cursor.getString(3)+",IP :"+cursor.getString(4)+",DTC :"+cursor.getString(5)).draggable(false));
				    	mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(BCITS, 15));
						mMap.setOnMapClickListener(MapActivity.this);
						mMap.setOnMarkerClickListener(new OnMarkerClickListener() {
							@Override
							public boolean onMarkerClick(Marker arg0) {
								lat_post  = (double) arg0.getPosition().latitude;
								long_post = (double)  arg0.getPosition().longitude;
								mTapTextView.setText("tapped, point=" + lat_post+" , "+long_post);
								return false;
							}
						});
					}
				}
				db.close();
				// End of rr numbers
			} else if (responsefromserver.equals("invalid")) {
				show = new AlertDialog.Builder(MapActivity.this).setTitle(getResources().getString(R.string.Info))
						.setMessage("Something went wrog try again Later")
						.setPositiveButton(getResources().getString(R.string.OK), null).show();

			} else if (responsefromserver.equalsIgnoreCase("[]")) {
				show = new AlertDialog.Builder(MapActivity.this).setTitle(getResources().getString(R.string.Info))
						.setMessage(getResources().getString(R.string.NoDetailsFound))
						.setPositiveButton(getResources().getString(R.string.OK), null).show();
			}

			else if (responsefromserver != null) {
				JSONArray ja = null;
				try {
					ja = new JSONArray(responsefromserver);
				} catch (JSONException e) {
					e.printStackTrace();
				}
				// arrayCircle = new ArrayList<GetSetCircle>();
				if (ja != null) {

					DBLocation dbAdapter = new DBLocation(getBaseContext());
					dbAdapter.open();
					for (int i = 0; i < ja.length(); i++) {
						JSONObject obj = null;
						try {
							obj = ja.getJSONObject(i);
						} catch (JSONException e1) {
							e1.printStackTrace();
						}
						try {
							LatLng BCITS = new LatLng(Double.parseDouble(obj.getString("latitude")),Double.parseDouble(obj.getString("longitude")));
					    	currentlocation = mMap.addMarker(new MarkerOptions().position(BCITS).title(obj.getString("letter")).snippet("Type :"+obj.getString("autharized")+",IP :"+obj.getString("noIPSet")+",ID :"+obj.getString("dtc")).draggable(false));
					    	mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(BCITS, 15));
							mMap.setOnMapClickListener(MapActivity.this);
							mMap.setOnMarkerClickListener(new OnMarkerClickListener() {
								@Override
								public boolean onMarkerClick(Marker arg0) {
									lat_post  = (double) arg0.getPosition().latitude;
									long_post = (double)  arg0.getPosition().longitude;
									mTapTextView.setText("tapped, point=" + lat_post+" , "+long_post);
									return false;
								}
							});
							dbAdapter.saveLocations(obj.getString("latitude"), obj.getString("longitude"), obj.getString("letter"),  obj.getString("autharized"), obj.getString("dtc"));

						} catch (JSONException e) {
							e.printStackTrace();
						}
					}
					dbAdapter.close();
				}

			//	ArrayAdapter<String> adapter = new ArrayAdapter<String>(Add_IP.this,android.R.layout.simple_dropdown_item_1line, listdata);
			//	rrnumberauto.setThreshold(1);
			//	rrnumberauto.setAdapter(adapter);

			} else {

			}
		}

	}
}
