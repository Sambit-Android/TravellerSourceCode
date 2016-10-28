package com.bcits.bsmartbilling;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.LocationManager;
import android.os.BatteryManager;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;

import com.utils.helper.MemoryCheck;

public class AmrHardWareWatcher extends Activity{
	private MyPhoneStateListener    MyListener;
	private TelephonyManager        Tel;
	public String mbatteryLevel,  mmemoryTotal, mmemoryAvailable;
	private MemoryCheck check;

	//private static final long MINIMUM_DISTANCE_CHANGE_FOR_UPDATES = 1; // in Meters
	//private static final long MINIMUM_TIME_BETWEEN_UPDATES = 30000; // in Milliseconds
	protected LocationManager locationManager;
	int signalStrength11;

	int gpsAllowCount=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		MyListener   = new MyPhoneStateListener();
		Tel       = ( TelephonyManager )getSystemService(Context.TELEPHONY_SERVICE);
		Tel.listen(MyListener ,PhoneStateListener.LISTEN_SIGNAL_STRENGTHS);
		UtilMaster.mlangitude ="0";
		UtilMaster.mlattitude ="0";
		getBatteryPercentage();

	}
	
	@Override
	protected void onResume() {
		super.onResume();
		UtilMaster.gpsEnable = true;
		if(UtilMaster.isGpsEnable()){
			//TODO
		}
			
	}
	
	boolean setHardwareInfo() { //UtilMaster.deviceInfo = mbatteryLevel+","+signalStrength11+","+mmemoryTotal+","+mmemoryAvailable;
		try {
			mmemoryTotal = "0";
			mmemoryAvailable = "0";
			check = new MemoryCheck();
			mmemoryTotal = check.Memory().split("@")[0];
			mmemoryAvailable = check.Memory().split("@")[1];
		} catch (Exception e) {
			e.printStackTrace();
		}
         return true;
	/*	LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE );
		boolean statusOfGPS = manager.isProviderEnabled(LocationManager.GPS_PROVIDER);
		if(!statusOfGPS)
		{
			new AlertDialog.Builder(AmrHardWareWatcher.this).setTitle("Info").setCancelable(false).setMessage("Please switch on your GPS").setPositiveButton("Turn ON", new DialogInterface.OnClickListener() {
				@Override				public void onClick(DialogInterface dialog, int which) {
					Intent viewIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
					startActivity(viewIntent);	
				}}).show();

			return false;
		}

		if(!checkAccuracy()|| "0".equals(UtilMaster.mlangitude.trim())|| "0".equals(UtilMaster.mlattitude.trim()))
		{
			if(gpsAllowCount==0 || gpsAllowCount==1)
			{
				new AlertDialog.Builder(AmrHardWareWatcher.this).setCancelable(false)
				.setTitle("GPS ACCURACY IS LESS").setMessage("Do You Want To Retry Or Continue?").setPositiveButton("Retry", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						fusedLocationService=new FusedLocationService(AmrHardWareWatcher.this);
						Toast.makeText(getApplicationContext(),"Reloading GPS...",Toast.LENGTH_SHORT).show();
					}
				}).show();
				gpsAllowCount++;
				return false;
			}else{

				try {
					DatabaseHandler handler = new DatabaseHandler(AmrHardWareWatcher.this);
					handler.openToRead();
					String a[] = 	handler.getlastStoredLatLong();

					UtilMaster.mlangitude=a[0];
					UtilMaster.mlattitude=a[1];
					handler.close();

					gpsAllowCount=0;
					return true;
				} catch (Exception e) {
					e.printStackTrace();
				UtilMaster.mlangitude="0";
				UtilMaster.mlattitude="0";
				gpsAllowCount=0;
				return true;
				}
			}

		}else{
			return true;
		}*/
	}
	
	private void getBatteryPercentage() {
		BroadcastReceiver batteryLevelReceiver = new BroadcastReceiver() {
			public void onReceive(Context context, Intent intent) {
				context.unregisterReceiver(this);
				int currentLevel = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
				int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
				int batteryLevel = -1;
				if (currentLevel >= 0 && scale > 0) {
					batteryLevel = (currentLevel * 100) / scale;
				}
				mbatteryLevel = String.valueOf(batteryLevel);
				System.out.println("mbatteryLevel :"+mbatteryLevel);
				System.out.println("batteryLevel :"+batteryLevel);
			}
		}; 
		IntentFilter batteryLevelFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
		registerReceiver(batteryLevelReceiver, batteryLevelFilter);
	}
	private class MyPhoneStateListener extends PhoneStateListener
	{
		@Override
		public void onSignalStrengthsChanged(SignalStrength signalStrength)
		{	super.onSignalStrengthsChanged(signalStrength);
			if(signalStrength.getGsmSignalStrength()==99 || (signalStrength.getGsmSignalStrength()*100/31)==45 ){
				signalStrength11 = 0;
			}
			else{
				signalStrength11 = signalStrength.getGsmSignalStrength()*100/31;
			}
		}
	};
}
