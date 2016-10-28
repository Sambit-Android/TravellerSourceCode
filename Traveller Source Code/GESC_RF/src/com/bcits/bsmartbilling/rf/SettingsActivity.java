package com.bcits.bsmartbilling.rf;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import Utils.Logger;
import android.app.Activity;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.auto.update.ManualVersionUpdateActivity;
import com.bcits.bsmartbilling.rf.R;
import com.utils.AvailableSpaceHandler;
import com.utils.DatabaseHandler;
import com.utils.helper.DetailedReportHelper;
import com.utils.helper.MStatusReportHelper;

public class SettingsActivity extends Activity{
	
	private static String TAG = "Settings";
	ImageView view ;
	ImageView btn , headerIcon ;
	ToggleButton   toggle_btn_autoupdate , wifi_toggleButton , enablephoto_toggleButton, enablegps_toggleButton, stationery_type_toggleButton ;
	Button btnlogout , btn_summary_report , btn_detailed_reports , btn_device_manager;
	Logger logger ;
	TextView txt_Versionupdae;
	
	byte[] img=null;
	
	SharedPreferences sharedPreferences ;
	WifiManager   wifi ;
	
	public static String totalCount = "0" , billedCount= "0" , unBilledCount= "0"  , synced_total= "0" , drepotTotal_rev= "0" ,drepotTotal_Units= "0"  ; 
	public static List<MStatusReportHelper> list ;
	
	
@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	this.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
	setContentView(R.layout.settings_test);
	//setContentView(R.layout.settings_test);
	getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,R.layout.window_title);
	TextView txtTitle=(TextView)findViewById(R.id.title);
	txtTitle.setText("Settings");
	headerIcon = (ImageView)findViewById(R.id.header);
	 view = (ImageView)findViewById(R.id.imageView_image);
	 btn = (ImageView)findViewById(R.id.vewimage);
	 headerIcon.setImageDrawable(getResources().getDrawable(R.drawable.settings_white));
	 btnlogout = (Button) findViewById(R.id.titleBack);
	 btn_summary_report= (Button) findViewById(R.id.btn_summary_report);
	 btn_detailed_reports= (Button) findViewById(R.id.btndetailedreports); 
	 btn_device_manager = (Button) findViewById(R.id.btn_device_manager); 
	 txt_Versionupdae = (TextView) findViewById(R.id.btn_versionupdate); 
	 
	 stationery_type_toggleButton = (ToggleButton) findViewById(R.id.settings_stationery_type_toggleButton);
	 
		toggle_btn_autoupdate 	= (ToggleButton) findViewById(R.id.autoupdate);
		enablephoto_toggleButton = (ToggleButton) findViewById(R.id.enablephoto);
		enablegps_toggleButton = (ToggleButton) findViewById(R.id.enablegps);
		
		wifi_toggleButton = (ToggleButton) findViewById(R.id.tg_wifi);
		sharedPreferences = getPreferences(MODE_PRIVATE);
		UtilMaster.setUpdateEnable(sharedPreferences.getBoolean("autoupdate", false)); 
		UtilMaster.setPhotoEnable(sharedPreferences.getBoolean("photobilling", false));
		UtilMaster.setGpsEnable(sharedPreferences.getBoolean("gpsenable", false));
		
		
		if (logger == null) {
			logger = MasterLibraryFunction.getlogger(getApplicationContext(),TAG);
		}
		
		
		
		
		wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
		
		if(wifi.isWifiEnabled()){
			wifi_toggleButton.setChecked(true);
		}else{
			wifi_toggleButton.setChecked(false);
		}
		
		if(!UtilMaster.isUpdateEnable())
		{
			toggle_btn_autoupdate.setChecked(true);
			
		}else
		{
			toggle_btn_autoupdate.setChecked(false);
		}
		
		
		
		/*------------PRINTER SELECTION SETTINGS----------------*/
		if (!UtilMaster.isStationerySelection())
			stationery_type_toggleButton.setChecked(true);
		else
			stationery_type_toggleButton.setChecked(false);
		
		
		
		
		/*if(UtilMaster.isPhotoEnable())
		{
			enablephoto_toggleButton.setChecked(true);
			
		}else
		{
			enablephoto_toggleButton.setChecked(false);
		}
		
		
		if(UtilMaster.isGpsEnable())
		{
			enablegps_toggleButton.setChecked(true);
			
		}else
		{
			enablegps_toggleButton.setChecked(false);
		}*/
		
		
		
		
		
		
	
}


@Override
public void onBackPressed() {
	 finish();
	 move_inten(SettingsActivity.this, MainActivity.class);
}


@Override
protected void onResume() {
	super.onResume();
}


@Override
protected void onStart() {
	super.onStart();
	
	BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();    
	if(mBluetoothAdapter!=null)
	{
	   if (!mBluetoothAdapter.isEnabled()) {
	    }else{ 
	        mBluetoothAdapter.disable(); 
	    }
	}
	
	if( (UtilMaster.mGLogin_MRCode.equalsIgnoreCase("")) ||  UtilMaster.mGLogin_SiteCode.equalsIgnoreCase("") ){
		new AlertDialog.Builder(SettingsActivity.this)
						.setTitle("Sorry..!")
						.setMessage("Session timeout ..! Please Login again")
				   .setPositiveButton("OK", new DialogInterface.OnClickListener()
				   {
					   
					   public void onClick(DialogInterface dialog,int which) 
					   {
						 
					startActivity(new Intent(SettingsActivity.this,Login.class));
						//   return;

							
					   }
				   }).show();
	}
	
	
	wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
	if(wifi.isWifiEnabled()){
		wifi_toggleButton.setChecked(true);
	}else{
		wifi_toggleButton.setChecked(false);
	}
	
	btnlogout.setOnClickListener(new OnClickListener() {
		
		public void onClick(View v) {
			// TODO Auto-generated method stub
			//btnlogout.setEnabled(false);
			Intent inte_back = new Intent(	SettingsActivity.this,	MainActivity.class);
			inte_back.putExtra("status", "add");
			startActivity(inte_back);
			
		}
	});
	btn_summary_report.setOnClickListener(new OnClickListener() {
		
		public void onClick(View v) {
			//generateSummeryReport();
			
			
			 new AlertDialog.Builder(SettingsActivity.this)
			   .setTitle("Info")
			   .setMessage("Do you want to generate Summary report..?")
			   .setPositiveButton("Generate", new DialogInterface.OnClickListener()
			   {
				   
				   public void onClick(DialogInterface dialog,int which) 
				   {
					 

					   generateSummeryReport();
				   }
			   }).setNegativeButton("Cancel", null)
			   .show();
			
		}
	});
	
	
	
	stationery_type_toggleButton.setOnCheckedChangeListener(new OnCheckedChangeListener() {

		public void onCheckedChanged(CompoundButton buttonView,	boolean isChecked) {
			
			if(isChecked)
			{
				System.out.println("11111111111");
				 new AlertDialog.Builder(SettingsActivity.this)
				   .setTitle("Info")
				   .setMessage("Analogics printer has been selected")
				   .setPositiveButton("OK", new DialogInterface.OnClickListener()
				   {
					   public void onClick(DialogInterface dialog,int which) 
					   {
							SavePreferences("stationary_type", false);
							UtilMaster.setStationerySelection(sharedPreferences.getBoolean("stationary_type", false));
					   }
				   }).show();
			}
			else
			{
				System.out.println("22222222222");
				
				 new AlertDialog.Builder(SettingsActivity.this)
				   .setTitle("Info")
				   .setMessage("Quantum Aeon has been selected")
				   .setPositiveButton("OK", new DialogInterface.OnClickListener()
				   {
					   
					   public void onClick(DialogInterface dialog,int which) 
					   {
						   SavePreferences("stationary_type", true);
						   UtilMaster.setStationerySelection(sharedPreferences.getBoolean("stationary_type", false));
					   }
				   }).show();
			}
		}
	});
	
	
	
	
	btn_detailed_reports.setOnClickListener(new OnClickListener() {
		
		public void onClick(View v) {
			//generateDetailedSummeryReport();
			
			 new AlertDialog.Builder(SettingsActivity.this)
			   .setTitle("Info")
			   .setMessage("Do you want to generate Detailed Tariff wise report..?")
			   .setPositiveButton("Generate", new DialogInterface.OnClickListener()
			   {
				   
				   public void onClick(DialogInterface dialog,int which) 
				   {
					 

					   generateDetailedSummeryReport();
				   }
			   }).setNegativeButton("Cancel", null)
			   .show();
			
		}
	});
	
	btn_device_manager.setOnClickListener(new OnClickListener() {
		
		public void onClick(View v) {
			//generateDetailedSummeryReport();
			
			/* new AlertDialog.Builder(SettingsActivity.this)
			   .setTitle("Info")
			   .setMessage("Do you want to Start App manager..?")
			   .setPositiveButton("Start", new DialogInterface.OnClickListener()
			   {
				   
				   public void onClick(DialogInterface dialog,int which) 
				   {
					    startActivity(new Intent(SettingsActivity.this , DeviceInformationMainActiovity.class));
				   }
			   }).setNegativeButton("Cancel", null)
			   .show();*/
			 new AlertDialog.Builder(SettingsActivity.this)
			   .setTitle("Info")
			   .setMessage("Sorry..! App manager is disabled")
			   .setPositiveButton("OK",null)
			   .show();
		}
		
		
		
		
	});
	
	
	btn.setOnClickListener(new OnClickListener() {

		public void onClick(View v) {

			Intent gpsOptionsIntent = new Intent(
					android.provider.Settings.ACTION_WIFI_SETTINGS);
			startActivityForResult(gpsOptionsIntent, 0);
		}
	});
	
	
	wifi_toggleButton.setOnCheckedChangeListener(new OnCheckedChangeListener() {

		public void onCheckedChanged(CompoundButton buttonView,	boolean isChecked) {
			
			if(isChecked)
			{
				System.out.println("11111111111");
				 new AlertDialog.Builder(SettingsActivity.this)
				   .setTitle("Info")
				   .setMessage("Turning on wifi")
				   .setPositiveButton("OK", new DialogInterface.OnClickListener()
				   {
					   public void onClick(DialogInterface dialog,int which) 
					   {
						   wifi.setWifiEnabled(true);
					   }
				   }).show();
			}
			else
			{
				 new AlertDialog.Builder(SettingsActivity.this)
				   .setTitle("Info")
				   .setMessage("Turning off wifi")
				   .setPositiveButton("OK", new DialogInterface.OnClickListener()
				   {
					   public void onClick(DialogInterface dialog,int which) 
					   {
						   wifi.setWifiEnabled(false);
					   }
				   }).show();
				
				
			}
			
		}
	});
	
	
	
	
	toggle_btn_autoupdate.setOnCheckedChangeListener(new OnCheckedChangeListener() {
		public void onCheckedChanged(CompoundButton buttonView,	boolean isChecked) {
			if(isChecked)
			{
				System.out.println("11111111111");
				 new AlertDialog.Builder(SettingsActivity.this)
				   .setTitle("Info")
				   .setMessage("Autoupdate is turned on")
				   .setPositiveButton("OK", new DialogInterface.OnClickListener()
				   {
					   public void onClick(DialogInterface dialog,int which) 
					   {
							SavePreferences("autoupdate", false);
							UtilMaster.setUpdateEnable(sharedPreferences.getBoolean("autoupdate", false));
					   }
				   }).show();
			}
			else
			{
				System.out.println("22222222222");
				 new AlertDialog.Builder(SettingsActivity.this)
				   .setTitle("Info")
				   .setMessage("Autoupdate is turned off")
				   .setPositiveButton("OK", new DialogInterface.OnClickListener()
				   {
					   public void onClick(DialogInterface dialog,int which) 
					   {
						   SavePreferences("autoupdate", true);
						   UtilMaster.setUpdateEnable(sharedPreferences.getBoolean("autoupdate", false));
					   }
				   }).show();
			}
		}
	});
	
	
				
	
	enablephoto_toggleButton.setOnCheckedChangeListener(new OnCheckedChangeListener() {
		public void onCheckedChanged(CompoundButton buttonView,	boolean isChecked) {
			if(isChecked)
			{
				System.out.println("11111111111");
				 new AlertDialog.Builder(SettingsActivity.this)
				   .setTitle("Info")
				   .setMessage("PhotoBilling is enabled")
				   .setPositiveButton("OK", new DialogInterface.OnClickListener()
				   {
					   public void onClick(DialogInterface dialog,int which) 
					   {
							SavePreferences("photobilling", true);
							UtilMaster.setPhotoEnable(sharedPreferences.getBoolean("photobilling", false));
							System.out.println();
							
							
					   }
				   }).show();
			}
			else
			{
				System.out.println("22222222222");
				 new AlertDialog.Builder(SettingsActivity.this)
				   .setTitle("Info")
				   .setMessage("PhotoBilling is turned off")
				   .setPositiveButton("OK", new DialogInterface.OnClickListener()
				   {
					   public void onClick(DialogInterface dialog,int which) 
					   {
						   SavePreferences("photobilling", false);
						   UtilMaster.setPhotoEnable(sharedPreferences.getBoolean("photobilling", false));
					   }
				   }).show();
			}
		}
	});
	
	
	
	
	
	
	enablegps_toggleButton.setOnCheckedChangeListener(new OnCheckedChangeListener() {
		public void onCheckedChanged(CompoundButton buttonView,	boolean isChecked) {
			if(isChecked)
			{
				System.out.println("11111111111");
				 new AlertDialog.Builder(SettingsActivity.this)
				   .setTitle("Info")
				   .setMessage("GPS is enabled")
				   .setPositiveButton("OK", new DialogInterface.OnClickListener()
				   {
					   public void onClick(DialogInterface dialog,int which) 
					   {
							SavePreferences("gpsenable", true);
							UtilMaster.setGpsEnable(sharedPreferences.getBoolean("gpsenable", false));
							System.out.println();
							/*turnGPSOn();*/
							
							
							
							
					   }
				   }).show();
			}
			else
			{
				System.out.println("22222222222");
				 new AlertDialog.Builder(SettingsActivity.this)
				   .setTitle("Info")
				   .setMessage("GPS is turned off")
				   .setPositiveButton("OK", new DialogInterface.OnClickListener()
				   {
					   public void onClick(DialogInterface dialog,int which) 
					   {
						   SavePreferences("gpsenable", false);
						   UtilMaster.setGpsEnable(sharedPreferences.getBoolean("gpsenable", false));
						   /*turnGPSOff();*/
						   
					   }
				   }).show();
			}
		}
	});
	
	
	
	
	txt_Versionupdae.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
	
			long availablespaceInMb = AvailableSpaceHandler.getExternalAvailableSpaceInMB();
			String  domain_name = null;
			  File externalStorage = Environment.getExternalStorageDirectory();
			   if(availablespaceInMb < 500)
				{
				   logger.error("SD card is about to full. Press menu to delete the old files in sdcard");
					 new AlertDialog.Builder(SettingsActivity.this)
					.setTitle("Error")
					.setMessage(
							"SD card is about to full. Press menu to delete the old files in sdcard. ")
					.setPositiveButton("OK", null).show();
				}
			
			else {
				String versionName = MasterLibraryFunction
						.getVersion(SettingsActivity.this);
				logger.debug("version name****************" + versionName);
				logger.debug("versionname" + versionName);

				Intent updateapk = new Intent(SettingsActivity.this,	ManualVersionUpdateActivity.class);
				updateapk.putExtra("apk_version", "CESC.apk" + "/"+ versionName + "/" + Login.server_ip);
				startActivity(updateapk);
			}
			
			
			
			
		}
	});
	
	
	
	
	
	
}


@SuppressWarnings("unused")
private void SavePreferences(String key, Boolean value) {
	SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
	SharedPreferences.Editor editor = sharedPreferences.edit();
	// editor.putString(key, value);
	editor.putBoolean(key, value);	
	editor.commit();
}

public void generateSummeryReport(){
	 DatabaseHandler databasehandler = new DatabaseHandler(SettingsActivity.this);
		try {
			databasehandler.openToRead();
			String totalCount =	databasehandler.getTotalCount() ;
			String billCount =databasehandler.Getbillledcount() ;
			String unbilled = "-" ;
			
			try {
				unbilled = (Integer.parseInt(totalCount) - Integer.parseInt(billCount))+"";
			} catch (NumberFormatException e) {
				unbilled = "0";
			}catch (Exception e) {
				unbilled = "0";
			}
			SettingsActivity.totalCount = totalCount ;
			 billedCount =billCount ;
			 unBilledCount =unbilled; 
			 
			 if (UtilMaster.mGlobalList != null)
					UtilMaster.mGlobalList.clear();
				
				if (list!= null)
					list.clear();
			 
			 
			 
			 list = new ArrayList<MStatusReportHelper>();
			List<MStatusReportHelper> list_ =databasehandler.getMStatusWiseReport();
			databasehandler.close();
			for (MStatusReportHelper mStatusReportHelper : list_) {
				mStatusReportHelper.status  = addPostSpace(getMeterStatus(mStatusReportHelper.status),14) ;
				list.add(mStatusReportHelper);
			}
			UtilMaster.mGlobalList = (list) ;
			if(list != null)
			{
				if(list.size() > 0){
					UtilMaster.	reportType  ="SUMMARY_REPORT" ;
					startActivity(new Intent(SettingsActivity.this, BluetoothChat_Reports.class));
				}else{
					new AlertDialog.Builder(SettingsActivity.this)
					.setTitle("Info")
					.setMessage("Sorry..!\nNot Able to generate report")
					.setPositiveButton(	"Ok",	null)
					.show();
				}
			}else{
				new AlertDialog.Builder(SettingsActivity.this)
				.setTitle("Info")
				.setMessage("Sorry..!\nNot Able to generate report")
				.setPositiveButton(	"Ok",	null)
				.show();
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
}





public void generateDetailedSummeryReport(){
	 DatabaseHandler databasehandler = new DatabaseHandler(SettingsActivity.this);
		try {
			databasehandler.openToRead();
			String totalCount =	databasehandler.getTotalCount() ;
			String billCount =databasehandler.Getbillledcount() ;
			String unbilled = "-" ;
			synced_total = databasehandler.GetBilledupdatedcountfromlocal();
			
			try {
				unbilled = (Integer.parseInt(totalCount) - Integer.parseInt(billCount))+"";
			} catch (NumberFormatException e) {
				unbilled = "0";
			}catch (Exception e) {
				unbilled = "0";
			}
			
			SettingsActivity.totalCount = totalCount ;
			 billedCount =billCount ;
			 unBilledCount =unbilled; 
			 
			if (UtilMaster.detailedReportHelpers != null)
				UtilMaster.detailedReportHelpers.clear();
			
			
			
			 
			// int temp_units = 0 ,  temp_BD = 0;
			List<DetailedReportHelper> list_ =databasehandler.getDetailedMStatusWiseReport();
			
			List<DetailedReportHelper> reportHelpers   = new ArrayList<DetailedReportHelper>();
			MasterLibraryFunction	libraryFunction = new MasterLibraryFunction(SettingsActivity.this);
				
			
			drepotTotal_rev = "0" ;
			drepotTotal_Units = "0";
			int unit_01 = 0 , rev_01 = 0 ;
			for (DetailedReportHelper helper : list_) {
				helper.traifDesc  = libraryFunction.getTariffDesc(SettingsActivity.this, helper.tariffCode);
				rev_01 += helper.report_totalRev ;
				unit_01 += helper.report_Totalunits;
				reportHelpers.add(helper);
			}
			drepotTotal_rev = rev_01+"" ;
			drepotTotal_Units = unit_01+"";
			
			UtilMaster.detailedReportHelpers = (reportHelpers) ;
			if(reportHelpers != null)
			{
				if (reportHelpers.size() > 0) {
					
					UtilMaster.	reportType  ="DETAILED_SUMMARY_REPORT" ;
					startActivity(new Intent(SettingsActivity.this,	BluetoothChat_Reports.class));
				} else {
					new AlertDialog.Builder(SettingsActivity.this)
					.setTitle("Info")
					.setMessage("Sorry..!\nNot Able to generate report")
					.setPositiveButton(	"Ok",	null)
					.show();
				}
			}
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		databasehandler.close();
}






private String getMeterStatus(String value) {
	String mtrStatus = "-";
	if (value.equals("1")) {
		mtrStatus = getString(R.string.a);
	} else if (value.equals("2")) {
		mtrStatus = getString(R.string.b);
	} else if (value.equals("3")) {

		mtrStatus = getString(R.string.c);
	} else if (value.equals("4")) {
		mtrStatus = getString(R.string.d);
	} else if (value.equals("5")) {
		mtrStatus = getString(R.string.e);
	} else if (value.equals("6")) {
		mtrStatus = getString(R.string.f);
	} else if (value.equals("7")) {
		mtrStatus = getString(R.string.g);
	} else if (value.equals("8")) {
		mtrStatus = getString(R.string.h);
	} else if (value.equals("9")) {
		mtrStatus = getString(R.string.i);
	} else {
		mtrStatus = ("-");
	}

	return mtrStatus ;
}

public static String addPostSpace(String string, int length) {

	int n = 0;
	if (string == null) {
		string = "-";
		n = string.length();
	} else {
		n = string.length();

	}

	int space = length - n;
	//StringBuilder s = new StringBuilder(" ");
	StringBuilder k = new StringBuilder(" ");
	
	for (int i = 0; i < space; i++) {
		k = k.append(" ");
	}

	String f = k.toString();
	return  string+f;
}
public void move_inten(Context context, Class<MainActivity> class1) {
	Intent intent = new Intent(context, class1);
	startActivity(intent);
}






/*public void turnGPSOn()
{
     Intent intent = new Intent("android.location.GPS_ENABLED_CHANGE");
     intent.putExtra("enabled", true);
     this.sendBroadcast(intent);

    String provider = Settings.Secure.getString(this.getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
    if(!provider.contains("gps")){ //if gps is disabled
        final Intent poke = new Intent();
        poke.setClassName("com.android.settings", "com.android.settings.widget.SettingsAppWidgetProvider"); 
        poke.addCategory(Intent.CATEGORY_ALTERNATIVE);
        poke.setData(Uri.parse("3")); 
        this.sendBroadcast(poke);


    }
}
// automatic turn off the gps
public void turnGPSOff()
{
    String provider = Settings.Secure.getString(this.getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
    if(provider.contains("gps")){ //if gps is enabled  
        final Intent poke = new Intent();
        poke.setClassName("com.android.settings", "com.android.settings.widget.SettingsAppWidgetProvider");
        poke.addCategory(Intent.CATEGORY_ALTERNATIVE);
        poke.setData(Uri.parse("3")); 
        this.sendBroadcast(poke);
    }
}*/














}
