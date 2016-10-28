package com.bcits.uitls.maps;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Toast;

import com.bcits.recondiscon.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.sqlite.mvs.DbDisconnection;

public class MapMainActivity extends FragmentActivity {private GoogleMap googleMap;
private HashMap<CustomMarker, Marker> markersHashMap;
private Iterator<Entry<CustomMarker, Marker>> iter;
private CameraUpdate cu;
private CustomMarker  customMarkerTwo;


public static boolean isSataliteView=true ,isTrafficView=true;
public static boolean isdisconnection;

@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.mr_locator);
	
	try {
		// LOADING MAP
		initilizeMap();
		googleMap.getUiSettings().setCompassEnabled(true);
		googleMap.getUiSettings().setRotateGesturesEnabled(false);
		googleMap.getUiSettings().setTiltGesturesEnabled(true);
		googleMap.getUiSettings().setZoomControlsEnabled(true);
		googleMap.getUiSettings().setMyLocationButtonEnabled(true);
		
		googleMap.setMyLocationEnabled(true);
		googleMap.setTrafficEnabled(false);
		googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
		
		googleMap.setIndoorEnabled(true);
		googleMap.setBuildingsEnabled(false);
	} catch (Exception e) {
		e.printStackTrace();
	}
}

void setCustomMarkerTwoPosition() {
	
	try {
		String accountNo =getIntent().getStringExtra("accountNo");
		JSONObject latLong ;
		DbDisconnection db = new DbDisconnection(getBaseContext());
		db.open();
		latLong  = db.getLatLong(accountNo);
		db.close();
		
		double lattitude=Double.parseDouble(latLong.getString("lat"));
		double longitude=Double.parseDouble(latLong.getString("long"));
		
		if(lattitude <=0  || longitude <=0)
		{
			Toast.makeText(getApplicationContext(), "GPS cordinates not available\nTesting loading another value", Toast.LENGTH_SHORT).show();
			 lattitude= 12.2946759; //TODO
			 longitude= 76.6383433;
		}
		
		customMarkerTwo = new CustomMarker("markerTwo",lattitude, longitude);
		addMarker(customMarkerTwo);
		zoomAnimateLevelToFitMarkers(50);
	} catch (NumberFormatException e) {
		e.printStackTrace();
	} catch (JSONException e) {
		e.printStackTrace();
	}
}

@Override
protected void onResume() {
	super.onResume();
	
/*	googleMap.setOnMapLongClickListener( new OnMapLongClickListener() {
		
		@Override
		public void onMapLongClick(LatLng arg0) {
			Toast.makeText(getApplicationContext(), arg0.toString(), Toast.LENGTH_SHORT).show();
		}
	});
	
	googleMap.setOnMarkerClickListener(new OnMarkerClickListener() {
		@Override
		public boolean onMarkerClick(Marker marker) {
			if (marker.getId() == customMarkerOne.getCustomMarkerId()) {
				Toast.makeText(getApplicationContext(), "Clicked on First",Toast.LENGTH_SHORT).show();
			} else if (marker.equals(customMarkerTwo)) {
				Toast.makeText(getApplicationContext(),	"Clicked on Second", Toast.LENGTH_SHORT).show();
			}
			return false;
		}
	});*/
}

@SuppressWarnings("deprecation")
private void initilizeMap() {
	googleMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapFragment)).getMap();
	// check if map is created successfully or not
	if (googleMap == null) {
		Toast.makeText(getApplicationContext(),	"Sorry! unable to create maps", Toast.LENGTH_SHORT).show();
	}
	(findViewById(R.id.mapFragment)).getViewTreeObserver().addOnGlobalLayoutListener(	new android.view.ViewTreeObserver.OnGlobalLayoutListener() {
						@SuppressLint("NewApi")
						@Override
						public void onGlobalLayout() {
							// gets called after layout has been done but before display so we can get the height then hide the view
							if (android.os.Build.VERSION.SDK_INT >= 16) {
								(findViewById(R.id.mapFragment)).getViewTreeObserver().removeOnGlobalLayoutListener(this);
							} else {
								(findViewById(R.id.mapFragment)).getViewTreeObserver().removeGlobalOnLayoutListener(this);
							}
							setCustomMarkerTwoPosition();
						}
					});
}



public void onClickZoomToMarkers(View v) {
	zoomAnimateLevelToFitMarkers(100);
}


@SuppressWarnings("deprecation")
public void onClickSataliteView(View v) {
	if(isSataliteView)
	{
		googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
		v.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_light));
		isSataliteView=false;
	}else {
		googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
		v.setBackgroundColor(getResources().getColor(android.R.color.transparent));
		isSataliteView=true;
	}
}

@SuppressWarnings("deprecation")
public void  onClickTraffic (View v) {
	if(isTrafficView)
	{
		googleMap.setTrafficEnabled(true);
		v.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_light));
		isTrafficView=false;
	}else {
		googleMap.setTrafficEnabled(false);
		v.setBackgroundColor(getResources().getColor(android.R.color.transparent));
		isTrafficView=true;
	}
}


// this is method to help us set up a Marker that stores the Markers we want
// to plot on the map
public void setUpMarkersHashMap() {
	if (markersHashMap == null) {
		markersHashMap = new HashMap<CustomMarker, Marker>();
	}
}

// this is method to help us add a Marker into the hashmap that stores the
// Markers
public void addMarkerToHashMap(CustomMarker customMarker, Marker marker) {
	setUpMarkersHashMap();
	markersHashMap.put(customMarker, marker);
}

// this is method to help us find a Marker that is stored into the hashmap
public Marker findMarker(CustomMarker customMarker) {
	iter = markersHashMap.entrySet().iterator();
	while (iter.hasNext()) {
		Entry<CustomMarker, Marker> mEntry = iter.next();
		CustomMarker key = (CustomMarker) mEntry.getKey();
		if (customMarker.getCustomMarkerId().equals(key.getCustomMarkerId())) {
			Marker value = (Marker) mEntry.getValue();
			return value;
		}
	}
	return null;
}

// this is method to help us add a Marker to the map
public void addMarker(CustomMarker customMarker) {
	MarkerOptions markerOption = new MarkerOptions().position(new LatLng(customMarker.getCustomMarkerLatitude(), customMarker	.getCustomMarkerLongitude())).icon(BitmapDescriptorFactory.defaultMarker());
	// .icon(BitmapDescriptorFactory.fromResource(android.R.drawable.ic_menu_mapmode));
	Marker newMark = googleMap.addMarker(markerOption);
	
	
/*	googleMap.setInfoWindowAdapter(new InfoWindowAdapter() {
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
	});*/
	
	addMarkerToHashMap(customMarker, newMark);
}

// this is method to help us remove a Marker
public void removeMarker(CustomMarker customMarker) {
	if (markersHashMap != null) {
		if (findMarker(customMarker) != null) {
			findMarker(customMarker).remove();
			markersHashMap.remove(customMarker);
		}
	}
}

// this is method to help us fit the Markers into specific bounds for camera
// position
public void zoomAnimateLevelToFitMarkers(int padding) {
	LatLngBounds.Builder b = new LatLngBounds.Builder();
	iter = markersHashMap.entrySet().iterator();

	while (iter.hasNext()) {
		Entry<CustomMarker, Marker> mEntry = iter.next();
		CustomMarker key = (CustomMarker) mEntry.getKey();
		LatLng ll = new LatLng(key.getCustomMarkerLatitude(),key.getCustomMarkerLongitude());
		b.include(ll);
	}
	LatLngBounds bounds = b.build();

	// Change the padding as per needed
	cu = CameraUpdateFactory.newLatLngBounds(bounds, padding);
	googleMap.animateCamera(cu);
}

// this is method to help us move a Marker.
public void moveMarker(CustomMarker customMarker, LatLng latlng) {
	if (findMarker(customMarker) != null) {
		findMarker(customMarker).setPosition(latlng);
		customMarker.setCustomMarkerLatitude(latlng.latitude);
		customMarker.setCustomMarkerLongitude(latlng.longitude);
	}
}

@Override
public void onBackPressed() {
	this.finish();
}}
