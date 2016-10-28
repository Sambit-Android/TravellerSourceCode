package com.bcits.uitls.maps;


import java.util.ArrayList;

import org.osmdroid.api.IGeoPoint;
import org.osmdroid.api.IMapController;
import org.osmdroid.util.BoundingBoxE6;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.OverlayItem;
import org.osmdroid.views.overlay.compass.CompassOverlay;
import org.osmdroid.views.overlay.compass.IOrientationProvider;
import org.osmdroid.views.overlay.compass.InternalCompassOrientationProvider;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ImageButton;

import com.bcits.recondiscon.R;
public class OfflineMap extends Activity {
//osmdroid folder for offline maps
//	private MyLocationNewOverlay myLocationOverlay = null;
	private MapView mapView;

	private IMapController myMapController;
	private ImageButton  gps;

	private ItemizedIconOverlay<OverlayItem> items;
	private MyOverlayGestureListener markerGestureListener;
	private IOrientationProvider orient;
	private CompassOverlay compassOverlay;

	private BoundingBoxE6 boundingBox;

	//---------------------------------------- on tap marker guesture listener..------------------------------------

	class MyOverlayGestureListener implements ItemizedIconOverlay.OnItemGestureListener<OverlayItem>
	{
		public boolean onItemLongPress(int i, OverlayItem item)
		{ //TODO
			//Toast.makeText(MainActivity.this, "LONG PRESS: " + item.getSnippet(), Toast.LENGTH_LONG).show();
			return true;
		}
		public boolean onItemSingleTapUp(int i, OverlayItem item)
		{
			IGeoPoint point = item.getPoint();
			int latitu =point.getLatitudeE6();
			int longitu = point.getLongitudeE6();
			AlertDialog.Builder dialog = new AlertDialog.Builder(OfflineMap.this);
			dialog.setTitle("RR-NO: "+item.getTitle());
			dialog.setIcon(R.drawable.ic_location_on_48pt);
			dialog.setMessage("Location: "+item.getSnippet()+"\n"+"Latitude: "+latitu+"\n"+"Longitude: "+longitu);
			dialog.setNegativeButton("OK", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					dialog.cancel();
				}
			});
			dialog.show();
			return true;
		}
	}



	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.offline_map);

		Log.d("mapapp", "Activity created");
		markerGestureListener = new MyOverlayGestureListener();
		orient= (IOrientationProvider) new InternalCompassOrientationProvider(OfflineMap.this);
		items = new ItemizedIconOverlay<OverlayItem>(OfflineMap.this, new ArrayList<OverlayItem>(), markerGestureListener);
		gps = (ImageButton) findViewById(R.id.imageButtongps);
		LocationManager service = (LocationManager) getSystemService(LOCATION_SERVICE);
		boolean enabled = service.isProviderEnabled(LocationManager.GPS_PROVIDER);
		mapView = (MapView) findViewById(R.id.mapview);
		Log.d("mapapp", "map view defined");
		compassOverlay = new CompassOverlay(this, orient, mapView);
		compassOverlay.enableCompass(orient);

		if (!enabled) {
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("GPS Disabled")
			.setMessage("Please turn on your GPS.")
			.setCancelable(false)
			.setIcon(R.drawable.point_location)
			.setPositiveButton("Turn On", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
					startActivity(intent);
				}
			})
			.setNegativeButton("Quit", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					OfflineMap.this.finish();
				}
			});

			builder.create().show();

		}

		{
			
			boundingBox = new BoundingBoxE6(19.311143, 79.365234,11.436955,74.091796);

			//----setting map characteristics
			mapView.setBuiltInZoomControls(true);
			mapView.setMultiTouchControls(true);
			mapView.setMinZoomLevel(7);
			mapView.setUseDataConnection(false);
			mapView.setScrollableAreaLimit(boundingBox);

			Log.d("mapapp", "map view settings");

			myMapController = mapView.getController();
			myMapController.setZoom(18);


		/*	myLocationOverlay = new MyLocationNewOverlay(this, mapView);
			myLocationOverlay.enableFollowLocation();
			myLocationOverlay.enableMyLocation();
			mapView.getOverlays().add(myLocationOverlay);	


			myLocationOverlay.runOnFirstFix(new Runnable() {
				public void run() {
					mapView.getController().animateTo(myLocationOverlay.getMyLocation());
				}
			});*/

		}
	/*	gps.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				try {
					mapView.getController().animateTo(myLocationOverlay.getMyLocation());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});*/

	}


	@Override
	protected void onStart() {

		super.onStart();
	}


	@Override
	protected void onResume() {
		super.onResume();
//		myLocationOverlay.enableMyLocation();
		//myLocationOverlay.enableCompass();
//		myLocationOverlay.enableFollowLocation();
		
		loadMap(12.333777, 76.552937, "1213456", "CESC");
	}

	@Override
	protected void onPause() {
		super.onPause();
//		myLocationOverlay.disableMyLocation();
		//myLocationOverlay.disableCompass();
//		myLocationOverlay.disableFollowLocation();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		return true;

	}

	@Override
	public void onBackPressed() {
		this.finish();
	}

	
	@SuppressWarnings("deprecation")
	private void loadMap(double latitude,double longitude,String rrNo,String place) {

		GeoPoint geoPoint = new GeoPoint(latitude, longitude);
		OverlayItem overlayitem = new OverlayItem(rrNo,place,geoPoint);
		overlayitem.setMarker(getResources().getDrawable(R.drawable.ic_location_on_48pt));

		items.addItem(overlayitem);
		mapView.getOverlays().add(items);
		myMapController= mapView.getController();
		myMapController.setZoom(15);
		myMapController.setCenter(geoPoint);
		myMapController.animateTo(geoPoint);
	
	}
}

