package com.bcits.recondiscon;

import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.provider.Settings;
import android.support.v4.app.FragmentActivity;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.view.animation.Interpolator;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bcits.utils.GPSTracker;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerDragListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.sqlite.mvs.DbDisconnection;

@SuppressLint("NewApi")
public class TrackDisconnection extends FragmentActivity implements OnMarkerClickListener, OnMarkerDragListener, OnMapClickListener,LocationListener {
	GoogleMap mMap;
	private TextView mTapTextView;
	private Button b;
	double lat_post=0.00;
	double long_post = 0.00;
	private Marker currentlocation;
	String dateofview="";
	public static boolean isdisconnection=false; 
	AlertDialog show;
    double lattitude,longitude;
	protected void onCreate(Bundle savedInstanceState) {
		setTitle("Track Consumer");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_newmap);
		
		try {
			String accountNo =getIntent().getStringExtra("accountNo");
			JSONObject latLong ;
			DbDisconnection db = new DbDisconnection(getBaseContext());
			db.open();
			latLong  = db.getLatLong(accountNo);
			db.close();
			lattitude= 12.2946759; //TODO
			longitude= 76.6383433;
//			lattitude=Double.parseDouble(latLong.getString("lat"));
//			longitude=Double.parseDouble(latLong.getString("long"));
			System.out.println(lattitude+"  ====================   "+longitude);
			
			if(lattitude==0.0 || longitude==0.0) {
				Toast.makeText(getApplicationContext(), "GPS cordinates not available", Toast.LENGTH_SHORT).show();
				this.finish();
				return;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			Toast.makeText(getApplicationContext(), "GPS cordinates not available", Toast.LENGTH_SHORT).show();
			this.finish();
			return;
		}
		
		
		mTapTextView = (TextView) findViewById(R.id.tap_text);
		setUpMapIfNeeded();
					try {
						GPSTracker gpsTracker = new GPSTracker(TrackDisconnection.this);
						if (gpsTracker.getIsGPSTrackingEnabled()) {
							
//							if(isdisconnection){
								
							LatLng BCITS = new LatLng(lattitude,longitude);
							
							//LatLng BCITS = new LatLng(Double.parseDouble(DisconnectionView.disc.getLatitude_dis()),Double.parseDouble(DisconnectionView.disc.getLongitude_dis()));
							currentlocation = mMap.addMarker(new MarkerOptions().position(BCITS).title("MR10").snippet("Disconnection RR : 783463,SDO Code : Sdocode,Dis.Date : 19/12/1999,Last MR :xxxx ,yyy: 888").draggable(false)/*.icon(BitmapDescriptorFactory.fromResource(R.drawable.android_icon))*/);
							mMap.setInfoWindowAdapter(new InfoWindowAdapter() {
								@Override
								public View getInfoWindow(Marker arg0) {
									
									return null;
								}
								@Override
								public View getInfoContents(Marker arg0) {
						            View v = getLayoutInflater().inflate(R.layout.custom_info, null);
						                TextView tvinfo = (TextView)  v.findViewById(R.id.info); 
						                TextView tvType = (TextView)  v.findViewById(R.id.info1);
						                TextView tvfeeder = (TextView)  v.findViewById(R.id.info2);
						                TextView tvfeeder2 = (TextView)  v.findViewById(R.id.info33);
						                TextView tvlat = (TextView)  v.findViewById(R.id.info4);
						                TextView tvlong = (TextView)  v.findViewById(R.id.info5);
						                TextView dd = (TextView)  v.findViewById(R.id.info9);
						                tvfeeder.setAutoLinkMask(Linkify.PHONE_NUMBERS);
						                tvfeeder .setMovementMethod(LinkMovementMethod.getInstance());
						                tvfeeder.setClickable(true);
						                tvinfo.setText(DisconnectionView.disc.getRrno());
						                tvinfo.setPaintFlags(tvinfo.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
						                	tvType.setTextColor(Color.BLACK);
						                	tvType.setText("Cons.Name : "+DisconnectionView.disc.getConsumer_name());
						                	tvfeeder.setText("Mobile No : 9738575024");
						                	tvfeeder2.setText("Address : "+DisconnectionView.disc.getAddress());
						                	tvlat.setTextColor(getResources().getColor(R.color.Green));
						                	tvlat.setText("Available to Disconnect : Yes");
						                	tvlong.setText("Arrears : ₹ "+DisconnectionView.disc.getArrears());
						                	dd.setText("");
						            return v;
								}
							});
					    	mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(BCITS, 12));
							mMap.setOnMapClickListener(TrackDisconnection.this);
							mMap.setOnMarkerClickListener(new OnMarkerClickListener() {
								@Override
								public boolean onMarkerClick(Marker arg0) {
									lat_post  = (double) arg0.getPosition().latitude;
									long_post = (double)  arg0.getPosition().longitude;
									mTapTextView.setText("tapped, point=" + lat_post+" , "+long_post);
									return false;
								}
							});
//							}
							
						} else {
							showSettingsAlert(TrackDisconnection.this);
						}

					} catch (Exception e) {
						e.printStackTrace();
					}

		b = (Button)findViewById(R.id.okButton);
		b.setVisibility(View.GONE);
		/*b.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
					finish();
			}
		});*/
	}
	



	@Override
	protected void onResume() {
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
		    	//currentlocation = mMap.addMarker(new MarkerOptions().position(currentloc).title("Location").snippet("Current Location").draggable(true));
				mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentloc, 8));
		    	
		    	
		    }else{
		    	LatLng BCITS = new LatLng(13.04, 77.58);
		    	//currentlocation = mMap.addMarker(new MarkerOptions().position(BCITS).title("Location").snippet("Last Loaded Location").draggable(true));
				mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(BCITS, 11));
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
			if(isdisconnection){
				Intent intent = new Intent(TrackDisconnection.this, DisconnectionView.class);
				startActivity(intent);
			}
			else{
				Intent intent = new Intent(TrackDisconnection.this, ReconnectionView.class);
				startActivity(intent);
					
			}
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
	  private void showSettingsAlert(final Context context) {
	        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
	        alertDialog.setTitle("Turn on Location");
	        alertDialog.setMessage("Turn on Location Services to See Your Current Location");
	        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
	            @Override
	            public void onClick(DialogInterface dialog, int which) 
	            {
	                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	                context.startActivity(intent);
	            }
	        });
	        alertDialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
	            @Override
	            public void onClick(DialogInterface dialog, int which) 
	            {
	                dialog.cancel();
	            }
	        });
	        alertDialog.show();
	    }
	
}
