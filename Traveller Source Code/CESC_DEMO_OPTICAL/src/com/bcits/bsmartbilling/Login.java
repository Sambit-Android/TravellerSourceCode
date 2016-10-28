package com.bcits.bsmartbilling;

import java.io.File;
import java.io.InputStream;
import java.util.Calendar;

import Utils.DefaultClass;
import Utils.ErrorClass;
import Utils.FilePropertyManager;
import Utils.Logger;
import amr.utils.UsbRecieverService;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import bcits.camera.lib.CustomCamera;

import com.auto.update.AndroidautoUpdateActivity;
import com.utils.AvailableSpaceHandler;
import com.utils.LoginDBHandler;
import com.utils.Sendrequest;

public class Login extends Activity {

	 private static String TAG = "Login";
	 Logger logger ;
	static int i=0;
	private Button btn_login , btn_logout ;
	TextView version ;
	EditText edt_siteCode , edt_mrCode , edt_passwrd;
	String strSiteCode, strMRCode , strPaswrd ;
	public static String loginSiteCode , loginMRCode, imeiNumber;
	//public static String server_ip = "192.168.2.178:8080" ; //LOCAL;
	//public static String server_ip = "1.23.144.187:5555" ;//LIVE STATIC LINUX;

	//public static String server_ip = "122.166.217.53:9090" ;// LIVE and MAINTEST SERVER ;
	
	//public static String server_ip = "192.168.2.65:5555" ;  // my local tomcat
	
	
	//public static String server_ip = "122.166.217.53:5555" ; // 2.84 (tomcat)
	
	//public static String server_ip = "1.23.144.187:8070" ; //10.67
	
	 //public static String server_ip = "192.168.2.65:8080" ;  // my local JBOSS
	  
    	 public static String server_ip = "1.23.144.189:8002" ;  // gescom live
    	
    //	 public static String server_ip = "192.168.2.164:8080" ;  // rajesh system
	  
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.loginactivity);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,	R.layout.window_title);
		if (logger == null) {
			logger = MasterLibraryFunction.getlogger(getApplicationContext(),TAG);
		}
		logger.info("In Side Oncreate");
		btn_login = (Button)findViewById(R.id.btnlogin);
		version = (TextView)findViewById(R.id.version_name);
		btn_logout = (Button)findViewById(R.id.titleBack);
		btn_logout.setVisibility(View.INVISIBLE);
		edt_mrCode = (EditText)findViewById(R.id.editTextmrcode);
		edt_siteCode = (EditText)findViewById(R.id.editTextusername);
		edt_passwrd = (EditText)findViewById(R.id.editTextpassword);
		MasterLibraryFunction.createfolder_if_not_exist_sdcard(DefaultClass.mainDir);
		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}
		
		startService(new Intent(Login.this,UsbRecieverService.class)); // START USB SERVICE
		
		
		try {
			SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
			if(null==sharedPreferences.getString("singleRunCheck", null) )
			{
			clearApplicationData();
			
			AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
			Intent liveStatusintent = new Intent(Login.this, LiveStatusService.class);
	    	PendingIntent     pendingStatusIntent = PendingIntent.getService(Login.this, 0, liveStatusintent, 0);
	    	pendingStatusIntent.cancel();
	    	alarmManager.cancel(pendingStatusIntent);	
	    	SavePreferences("singleRunCheck", "ALREDY RUN");
	    	Toast.makeText(getApplicationContext(), "Memory Released", Toast.LENGTH_LONG).show();
			}
		} catch (Exception e) {
		e.printStackTrace();
		}
		
		
		 

		
		
	}

	private void SavePreferences(String key, String value) {
		SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPreferences.edit();
		editor.putString(key, value);
		editor.commit();
	}

	
	@Override
	protected void onStart() {
		super.onStart();
		
		
		
		long availablespaceInMb1 = AvailableSpaceHandler.getExternalAvailableSpaceInMB();
		String  domain_name1 = null;
		  File externalStorage1 = Environment.getExternalStorageDirectory();
		   if(availablespaceInMb1 < 100)
			{
			   logger.error("SD card is about to full. Press OK to delete Old Photos");
				 new AlertDialog.Builder(Login.this)
				.setTitle("Error")
				.setCancelable(false)
				.setMessage(
						"SD card is about to full. Press OK to delete Old Photos")
				.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						
						try {
							CustomCamera.clearImages(Login.this);
						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
						}
						
						
					}
				}).show();
			}
		
		
		
		
		
		logger.info("In Side onStart()");
		version.setText("Version M"+MasterLibraryFunction.getVersion(Login.this));
		
		logger.debug("check :**********111111111111111111111111111111111111111111111111*********** ");
		
		
		TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		imeiNumber = tm.getDeviceId();
		
		//////////////////////////////   UPDATE SERVICE ///////////////////////////////////////////////
		/*TIME LAPS : 30*10000 = 5mints
		 * UPDATING  4 RECORDS AT TIME 
		 * 
		 * */
		
    	
		
		Intent myIntent = new Intent(Login.this, MyAlarmService.class);
    	PendingIntent     pendingIntent = PendingIntent.getService(Login.this, 0, myIntent, 0);
	    AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
	    Calendar calendar = Calendar.getInstance();
	    calendar.setTimeInMillis(System.currentTimeMillis());
	    calendar.add(Calendar.SECOND, 15 );
		alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 6*10000, pendingIntent);
		
		
		////////////////////////////// END UPDATE SERVICE ///////////////////////////////////////////////
		
		/*---------------------------------LIVE STATUS ---------------------------------*/
		
		/*Intent liveStatusintent = new Intent(Login.this, LiveStatusService.class);
    	PendingIntent     pendingStatusIntent = PendingIntent.getService(Login.this, 0, liveStatusintent, 0);
	    AlarmManager alarmManagerliveStatus = (AlarmManager)getSystemService(ALARM_SERVICE);
	    Calendar liveStatusCalendar = Calendar.getInstance();
	    liveStatusCalendar.setTimeInMillis(System.currentTimeMillis());
	    liveStatusCalendar.add(Calendar.SECOND, 30 );
		alarmManagerliveStatus.setRepeating(AlarmManager.RTC_WAKEUP, liveStatusCalendar.getTimeInMillis(), 6*10000, pendingStatusIntent);
		*/
	     /*---------------------------------END LIVE STATUS ---------------------------------*/
		
		//////////////////////////////APP MANAGER SERVICE ///////////////////////////////////////////////
		
		/*String V_name="";
		try {
			V_name = getApplicationContext().getPackageManager().getPackageInfo(getApplicationContext().getPackageName(), 0).versionName;
		} catch (NameNotFoundException e1) {
			e1.printStackTrace();
		}
		Intent utilityIntent = new Intent(Login.this, com.bcits.utilitymodelappmvs.UtilityAlarmService.class);
		utilityIntent.putExtra("version", V_name);
		PendingIntent utilityPendingIntent = PendingIntent.getService(Login.this, 0, utilityIntent, 0);
		AlarmManager utilityalarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(System.currentTimeMillis());
		cal.add(Calendar.SECOND,30 );
		utilityalarmManager.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), 3600*1000, utilityPendingIntent);*/
		
	     //////////////////////////////END APP MANAGER SERVICE ///////////////////////////////////////////////
		
		
		///////////////////// AUTO UPDATING OF APK VERSION ///////////////////////////////
		if(i == 0)
		{
			i++;
			//vesion.setText("Version "+versionName);
			long availablespaceInMb = AvailableSpaceHandler.getExternalAvailableSpaceInMB();
			String  domain_name = null;
			  File externalStorage = Environment.getExternalStorageDirectory();
			   if(availablespaceInMb < 500)
				{
				   logger.error("SD card is about to full. Press menu to delete the old files in sdcard");
					 new AlertDialog.Builder(Login.this)
					.setTitle("Error")
					.setMessage(
							"SD card is about to full. Press menu to delete the old files in sdcard. ")
					.setPositiveButton("OK", null).show();
				}
			
			else {
				String versionName = MasterLibraryFunction
						.getVersion(Login.this);
				logger.debug("version name****************" + versionName);
				logger.debug("versionname" + versionName);

				Intent updateapk = new Intent(Login.this,	AndroidautoUpdateActivity.class);
				updateapk.putExtra("apk_version", "CESC.apk" + "/"+ versionName + "/" + server_ip);
				startActivity(updateapk);
			}
		}
	//////////////////// END  AUTO UPDATING OF APK VERSION ///////////////////////////////
		
		
		btn_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				strSiteCode = edt_siteCode.getText().toString().trim();
				strMRCode = edt_mrCode.getText().toString().trim().toUpperCase();
				strPaswrd = edt_passwrd.getText().toString().trim();
				if(strPaswrd != null)
				   strPaswrd =  new StringBuffer(edt_passwrd.getText().toString().trim()).reverse().toString();
				
				if(strSiteCode.equals(null) || strSiteCode == null || strSiteCode == "" || strSiteCode.equals(""))
					Toast.makeText(Login.this, "Enter Site Code", Toast.LENGTH_SHORT).show();
				else if (strMRCode.equals(null) || strMRCode == null || strMRCode == "" || strMRCode.equals(""))
					Toast.makeText(Login.this, "Enter MR Code", Toast.LENGTH_SHORT).show();
				else if (strPaswrd.equals(null) || strPaswrd == null || strPaswrd == "" || strPaswrd.equals(""))
					Toast.makeText(Login.this, "Enter Password", Toast.LENGTH_SHORT).show();
				else if(strPaswrd.equals(strSiteCode) )
				{
					
					//strPaswrd =  new StringBuffer(strPaswrd).reverse().toString();
					loginSiteCode = strSiteCode ;
					loginMRCode = strMRCode ;
					UtilMaster.setmGLogin_SiteCode(loginSiteCode);
					UtilMaster.setmGLogin_MRCode(loginMRCode);
					//startActivity(new Intent(Login.this, MainActivity.class));
					
					if(haveNetworkConnection()){
						loginOnline();
					}else{
						new AlertDialog.Builder(Login.this)
						.setTitle("Info")
						.setMessage( 
								"Internet Connection is Not Working. Signing in using mobile credentials")
						.setPositiveButton("OK", new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
									loginOffline();
							}}).show();
					}
					
					
				}
				else
					Toast.makeText(Login.this, "Password not matching", Toast.LENGTH_SHORT).show();
			}
		});

	}

	@Override
	protected void onResume() {
		super.onResume();
		logger.info("In Side onResume()");
	}
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			i=0;
			Intent startMain = new Intent(Intent.ACTION_MAIN);
			startMain.addCategory(Intent.CATEGORY_HOME);
			startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(startMain);
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
	
	
	
	public void loginOffline(){
		boolean logintrue = false;
		LoginDBHandler login_db = new LoginDBHandler(getApplicationContext());
		login_db.openToRead();
		
		if (login_db.checkwhthereempty())
			logintrue = login_db.checklogin(loginMRCode, loginSiteCode);
		 
		logger.debug("logintrue:",logintrue);
		login_db.close();
		
		if(logintrue){
			startActivity(new Intent(Login.this, MainActivity.class));
		}else{
			 new AlertDialog.Builder(Login.this)
			.setTitle("Info")
			.setMessage( 
					"Invalid Credentials. Please try again")
			.setPositiveButton("OK", null).show();
		}
		
	}
	public void loginOnline(){
		boolean logintrue = false;
		LoginDBHandler login_db = new LoginDBHandler(getApplicationContext());
		login_db.openToRead();
		
		if (login_db.checkwhthereempty())
			logintrue = login_db.checklogin(loginMRCode, loginSiteCode);
		 
		logger.debug("logintrue:",logintrue);
		login_db.close();
		
		if(logintrue){
			startActivity(new Intent(Login.this, MainActivity.class));
		}else{
			
			if (haveNetworkConnection())
				new performBackgroundTask().execute();
			else{
				new AlertDialog.Builder(Login.this)
				.setTitle("Info")
				.setMessage( 
						"Internet Connection is Not Working. Signing in using mobile credentials")
				.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,
							int which) {
							loginOffline();
					}}).show();
			}
			 
		}
		
	}
	
	
	
	/******************************************************FOR BACKGROUND TASK*******************************/
	public class performBackgroundTask extends AsyncTask<Void, Void, Void> {
		private ProgressDialog Dialog = new ProgressDialog(Login.this);
		Sendrequest req ;
		InputStream instream ;
		protected void onPreExecute() {
			Dialog.setMessage("Signing in Please wait...");
			Dialog.show();
		}

		protected Void doInBackground(Void... params) {
			req = new Sendrequest();
			//String uri =  "login/validation/" + loginMRCode.trim() +"-" + loginSiteCode.trim();
			
			String uri =  "newlogin/newvalidation/" + loginMRCode.trim() +"-" + loginSiteCode.trim()+"-" +imeiNumber;
			
			instream = req.sendrequest(uri);
			return null;
		}

		protected void onPostExecute(Void unused) {
			Dialog.dismiss();
			//loginbuton.setEnabled(true);
			if(MasterLibraryFunction.IsNullOrEmpty(ErrorClass.getErrorMessages()))
			{
				try
				{
				logger.debug("This is error message " + ErrorClass.getErrorMessages());
				if(ErrorClass.getErrorMessages().contains("timed out"))
				{
					ErrorClass.errorMessage = "";
						 new AlertDialog.Builder(Login.this)
						.setTitle("Info")
						.setMessage( 
								"Connection timed out.Server might be shut down.Trying Signing in using mobile credentials")
						.setPositiveButton("OK", new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								loginOffline();
							}
							}).show();
					
				}else if(ErrorClass.getErrorMessages().contains("java.net.SocketTimeoutException"))
				{
					ErrorClass.errorMessage = "";
					 new AlertDialog.Builder(Login.this)
					.setTitle("Info")
					.setMessage( 
							"Socket timed out. Signing in using mobile credentials")
					.setPositiveButton("OK", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int which) {
							loginOffline();
						}
						}).show();
				}else if(ErrorClass.getErrorMessages().contains("Connection refused") || ErrorClass.getErrorMessages().contains("refused") ||  ErrorClass.getErrorMessages().contains("Network unreachable"))
				{
					ErrorClass.errorMessage = "";
					 new AlertDialog.Builder(Login.this)
					.setTitle("Info")
					.setMessage( 
							"Network Unreachable. Signing in using mobile credentials")
					.setPositiveButton("OK", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int which) {
							loginOffline();
						}
						}).show();
				}
				}
				catch (Exception e) {
					// TODO: handle exception
					FilePropertyManager.appendLog(e.toString());
				}
			}else
				{
				try
				{
				if (instream != null) {
					String jaxrsmessage = req.read(instream);
					if (jaxrsmessage.contains("<html>")) {
						jaxrsmessage = "";
						 new AlertDialog.Builder(Login.this)
								.setTitle("Info")
								.setMessage( 
										"Sorry, there is a problem with server. Please try again later")
								.setPositiveButton("OK", null).show();
						Log.e("jaxrsmessage", jaxrsmessage);
					} else {
						
						
						String[] datafromserver = jaxrsmessage.split("-");
						if (datafromserver[0].equals("0")) {
							jaxrsmessage = "";
							 new AlertDialog.Builder(Login.this)
									.setTitle("Error")
									.setMessage("Sorry, the SiteCode, MRCode is Not binded to Device.Please register the device and try again.")
									.setPositiveButton("OK", null).show();
						}else if(datafromserver[0].equals("2"))
						{
							jaxrsmessage = "";
							 new AlertDialog.Builder(Login.this)
							.setTitle("Error")
							.setMessage(
									"Sorry, there is no data in server.")
							.setPositiveButton("OK", null).show();
						}
						else if(datafromserver[0].equals("1") && datafromserver[1].equalsIgnoreCase("valid") ) {
							LoginDBHandler dbhelper = new LoginDBHandler(getApplicationContext());
							dbhelper.openToRead();
							boolean	login = dbhelper.searchForMrname(loginMRCode.trim(),loginSiteCode.trim());
							dbhelper.close();
							
							if(!login)
							{
								dbhelper.openToWrite();
								//System.out.println(userid.toUpperCase() +" Metercode login doent exist in LOGIN table . So inserting..");
								String values[] = new String[]{loginMRCode.trim(),loginSiteCode.trim(),"1"};
								long k = dbhelper.insertLogin(values);
								if(k==1)
								{
									logger.debug("Successfully inserted to login table");
								}
								else
								{
									logger.debug("Not inserted to login table");
								}
							}
							dbhelper.close();
							new DefaultClass();
							startActivity(new Intent(Login.this, MainActivity.class));
						}else{
							 new AlertDialog.Builder(Login.this)
								.setTitle("Error")
								.setMessage(
										"Sorry, the SiteCode or MrCode is incorrect.Please check and try again later.")
								.setPositiveButton("OK", null).show();
						}
						
						
					}
				} else {
					 new AlertDialog.Builder(Login.this).setTitle("Info")
							.setMessage("Sorry, Server is down. Please try again later.")
							.setPositiveButton("OK", null).show();
				}
			}
				catch (Exception e) {
					// TODO: handle exception
					FilePropertyManager.appendLog(e.toString());
				}
		}
		}
	
	}
	
	
	
	public  boolean haveNetworkConnection() {
	    boolean haveConnectedMobile = false;
	    
	    try{
	    ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo[] netInfo = cm.getAllNetworkInfo();
	    for (NetworkInfo ni : netInfo) {
	    	Log.e("Internet connection checking" , ni.getTypeName());
	        if (ni.getTypeName().equalsIgnoreCase("MOBILE") || ni.getTypeName().equalsIgnoreCase("wifi"))
	            if (ni.isConnected())
	                haveConnectedMobile = true;
	    }
	    }catch(Exception exception){
			logger.error("haveNetworkConnection Exception:", Log.getStackTraceString(exception));
			//FilePropertyManager.appendLog(Log.getStackTraceString(exception));
		}
	    return  haveConnectedMobile;
	}
	
	
	
	
	private void clearApplicationData() {
	    File cache = getCacheDir();
	    File appDir = new File(cache.getParent());
	    if (appDir.exists()) {
	        String[] children = appDir.list();
	        for (String s : children) {
	            if (!s.equals("lib")) {
	                deleteDir(new File(appDir, s));
	                Log.i("TAG", "**************** File /data/data/APP_PACKAGE/" + s + " DELETED *******************");
	            }
	        }
	    }
	}
	
	
	private static boolean deleteDir(File dir) {
	    if (dir != null && dir.isDirectory()) {
	        String[] children = dir.list();
	        for (int i = 0; i < children.length; i++) {
	            boolean success = deleteDir(new File(dir, children[i]));
	            if (!success) {
	                return false;
	            }
	        }
	    }

	    return dir.delete();
	}
	
	
	
	
}
