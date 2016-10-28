package com.bcits.bsmartbilling;

import java.io.InputStream;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import Utils.ErrorClass;
import Utils.FilePropertyManager;
import Utils.Logger;
import amr.usb.catcher.UsbLibMain;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.auto.update.AndroidautoUpdateActivity;
import com.utils.AvailableSpaceHandler;
import com.utils.DBTariffHandler;
import com.utils.DatabaseBackup;
import com.utils.DatabaseHandler;
import com.utils.Sendrequest;
import com.utils.helper.BackupHelper;
import com.utils.helper.UploadHandler;

public class MainActivity extends Activity {

	static Logger logger ;
	 private static String TAG = "MainActivity";
	private Button btn_next , btn_logout1 ;
	private TextView title , mrcode , siteCode , txt_totalCon , txt_billed , txtSynched , txtNotSynched 	, totalspacevalue ,	 availablespacevalue;
	LinearLayout btn_downlaodd , btn_uploadd , btn_tariffupdated , btn_settings ,  btn_reports ;
	MasterLibraryFunction libraryFunction ;
	int totalUpdateCount = 0 ;
	String KEY_SERVERTOSBMDATE = null;
	Button btnDirectOptical;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     // CUSTOM ACTION BAR  
     		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
     		
        setContentView(R.layout.activity_main);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,	R.layout.window_title);
        
        if(logger == null){
    		logger = MasterLibraryFunction.getlogger(getApplicationContext(), TAG);
    		}
        logger.info("In Side Oncreate");
        btn_next = (Button)findViewById(R.id.btnnxt);
        btn_logout1 = (Button)findViewById(R.id.titleBack);
        btn_logout1.setBackgroundResource(R.drawable.logout);
        title = (TextView )findViewById(R.id.title);
        btn_downlaodd = (LinearLayout)findViewById(R.id.btn_download);
        btn_uploadd = (LinearLayout)findViewById(R.id.btn_upload);
        btn_tariffupdated = (LinearLayout)findViewById(R.id.btn_tariffupdate);
        btn_settings = (LinearLayout)findViewById(R.id.btn_settings);
        btn_reports= (LinearLayout)findViewById(R.id.btn_reports);
        btnDirectOptical=(Button)findViewById(R.id.btnDirectOptical);
        mrcode = (TextView )findViewById(R.id.textMRName);
        siteCode = (TextView )findViewById(R.id.txtsiteNo);
    	title.setText(getString(R.string.cus_title_name)+MasterLibraryFunction.getVersion(MainActivity.this));
        
        
        txt_totalCon = (TextView )findViewById(R.id.disptotal_con);
        txt_billed = (TextView )findViewById(R.id.dispBilled);
        txtSynched = (TextView )findViewById(R.id.txtsynched);
        txtNotSynched = (TextView )findViewById(R.id.txtnotsynched);
    	totalspacevalue = (TextView) findViewById(R.id.totalspacevalue);
		availablespacevalue = (TextView) findViewById(R.id.availablespacevalue);
        setDashboard();
        
 btnDirectOptical.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				UtilMaster.setPhotoEnable(false);
				AmrBiller.isDirectBilling=true; 
//				startActivity(new Intent(MainActivity.this, AmrBiller.class));
				UsbLibMain.readMeter(MainActivity.this, AmrBiller.class);
			}
		});
    }
	@Override
	protected void onResume() 
	{
		super.onResume();
		if (logger == null) 
		{
			logger = MasterLibraryFunction.getlogger(getApplicationContext(),TAG);
		}
		logger.info("In Side onResume()");
		if( UtilMaster.mGLogin_MRCode.equalsIgnoreCase("") ||  UtilMaster.mGLogin_SiteCode.equalsIgnoreCase("") )
		{
			 logger.error("Session timeout ..! Please Login again UtilMaster.mGLogin_MRCode OR UtilMaster.mGLogin_SiteCode is NULL");
			 new AlertDialog.Builder(MainActivity.this)
							.setTitle("Sorry..!")
							.setMessage("Session timeout ..! Please Login again")
							.setPositiveButton("OK", new DialogInterface.OnClickListener()
							{
								public void onClick(DialogInterface dialog,int which) 
								{
									startActivity(new Intent(MainActivity.this,Login.class));
								}
							}).show();
		} else 
		{
			mrcode.setText("MR : " + UtilMaster.mGLogin_MRCode);
			siteCode.setText(":"+UtilMaster.mGLogin_SiteCode);
		}
		setDashboard();
	}
	
	
	@Override
	public void onBackPressed() {
		new AlertDialog.Builder(MainActivity.this)
		.setTitle("Info")
		.setMessage(
				"Backpress is disabled \nUse Logout Button to close Session ")
		.setPositiveButton("Ok",null)
		.show();
	}
	@Override
	protected void onStart() {
		super.onStart();
		if(logger == null){
    		logger = MasterLibraryFunction.getlogger(getApplicationContext(), TAG);
    		}
		logger.info("In Side onStart()");
		try {
			if( (UtilMaster.mGLogin_MRCode.equalsIgnoreCase("")) ||  UtilMaster.mGLogin_SiteCode.equalsIgnoreCase("") ){
			 logger.error("Session timeout ..! Please Login again UtilMaster.mGLogin_MRCode OR UtilMaster.mGLogin_SiteCode is NULL");
			new AlertDialog.Builder(MainActivity.this)
							.setTitle("Sorry..!")
							.setMessage("Session timeout ..! Please Login again")
					   .setPositiveButton("OK", new DialogInterface.OnClickListener()
					   {
						   
						   public void onClick(DialogInterface dialog,int which) 
						   {
							 
						startActivity(new Intent(MainActivity.this,Login.class));
							//   return;

								
						   }
					   }).show();
		}
		DecimalFormat twoDForm = new DecimalFormat("0.00");
		totalspacevalue.setText(" : "+twoDForm.format(AvailableSpaceHandler.getExternalTotalSpaceInGB()) + " GB");
		availablespacevalue.setText(" : "+AvailableSpaceHandler.getExternalAvailableSpaceInMB() + " MB");
		} catch (Exception e) {
			logger.error("Session timeout ..! Exception:", e);
		}
		btn_logout1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				
				
				 new AlertDialog.Builder(MainActivity.this)
				.setTitle("Info")
				.setMessage("Do you want to logout? ")
				.setPositiveButton("Logout", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						Intent int_login = new Intent(MainActivity.this,Login.class);
						startActivity(int_login);
					}
				})
				.setNegativeButton("No", null).show();
			}
		});
		
		btn_downlaodd.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if(haveNetworkConnection())
				{
					logger.debug("btn_downlaodd >>  TimeCheckTask");
					new TimeCheckTask().execute();
				}else
				{
					  new AlertDialog.Builder(MainActivity.this)
						.setTitle("Info")
						.setMessage("Network is unreachable")
						.setPositiveButton("OK", null).show();
				}
			}
		});
		btn_uploadd.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				
				if(haveNetworkConnection()){
				
				if(isUnSycData())
				{
					totalUpdateCount = 0 ;
					logger.debug("btn_uploadd >>  performBackgroundTaskgetServerDateToUploadBilledData");
					new	performBackgroundTaskgetServerDateToUploadBilledData().execute();
				}else{
					
					 new AlertDialog.Builder(MainActivity.this)
					.setTitle("Info")
					.setMessage("No billed consumer to upload..")
					.setPositiveButton("OK", null).show();
				}
				}
				else
				{
					  new AlertDialog.Builder(MainActivity.this)
						.setTitle("Info")
						.setMessage("Network is unreachable")
						.setPositiveButton("OK", null).show();
				}
				
			//	new performBackgroundTaskdownload_consumerDate().execute();
				
				
			}
		});
		btn_tariffupdated.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				if(haveNetworkConnection()){
					logger.debug("btn_downlaodd >>  performBackgroundTaskTariff");
				new performBackgroundTaskTariff().execute();
				}
				else
				{
					  new AlertDialog.Builder(MainActivity.this)
						.setTitle("Info")
						.setMessage("Network is unreachable")
						.setPositiveButton("OK", null).show();
				}
			}
		});
		
		
		btn_settings.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
				startActivity(intent);

			}
		});
		btn_reports.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				Intent intent = new Intent(MainActivity.this, ReportActionClass.class);
				startActivity(intent);

			}
		});
		
		btn_next.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// isTariffDataValid
				
				
				
				if(  !UtilMaster.mGLogin_SiteCode.equalsIgnoreCase("") && !UtilMaster.mGLogin_MRCode.equalsIgnoreCase("") )
				{
				DBTariffHandler tariffHandler = new DBTariffHandler(
						MainActivity.this);
				tariffHandler.openToRead();
				boolean istariffValid = tariffHandler.isTariffDataValid();
				tariffHandler.close();
				if (istariffValid) {
					DatabaseHandler handler = new DatabaseHandler(
							MainActivity.this);
					handler.openToRead();
					boolean ismain_db = handler.checkDataBase();
					boolean isMrvalid = handler.isUserValid(UtilMaster.mGLogin_SiteCode.trim(), UtilMaster.mGLogin_MRCode.trim());
					handler.close();

					if (ismain_db) {
						if (isMrvalid) {
							
							libraryFunction = new  MasterLibraryFunction(MainActivity.this) ;
							if (libraryFunction.isdataValid()) {
							
							startActivity(new Intent(MainActivity.this,
									SearchActivity.class));
							} else {
								new AlertDialog.Builder(MainActivity.this)
								.setTitle("Info")
								.setMessage(
										"You have exceeded the 3 days limit. Please download new data to continue")
								.setPositiveButton("OK",null).show();
							}
							
						} else {
							new AlertDialog.Builder(MainActivity.this)
									.setTitle("Info")
									.setMessage(
											"Please download the  new data. No data exists in the DB for this meter reader code")
									.setPositiveButton("OK", null).show();
						}
					} else {
						new AlertDialog.Builder(MainActivity.this)
								.setTitle("Info").setMessage("No Data to bill")
								.setPositiveButton("OK", null).show();
					}
				} else {
					new AlertDialog.Builder(MainActivity.this).setTitle("Info")
							.setMessage("Update Tariff")
							.setPositiveButton("OK", null).show();
				}
				
			}else {
				
				 logger.error("Session timeout ..! Please Login again UtilMaster.mGLogin_MRCode OR UtilMaster.mGLogin_SiteCode is NULL");
					 new AlertDialog.Builder(MainActivity.this)
							.setTitle("Error")
							.setMessage("Sorry..! \nSession expired please Login again")
							.setPositiveButton("OK",
									new DialogInterface.OnClickListener() {
										public void onClick(DialogInterface dialog,
												int which) {
											Intent int_login = new Intent(
													MainActivity.this,
													Login.class);
											startActivity(int_login);
										}
									}).show();
				}
				
				
			}
		});
	}




	 /**
	 * @author Guru
	 * @date 05-Nov-2014
	 * @category Dashboard 
	 */
	public void setDashboard(){
		 String[]  dashboardStatus=null ;
		 DatabaseHandler databasehandler = new DatabaseHandler(getApplicationContext());
		 try {
			 databasehandler.openToRead();
			 dashboardStatus = databasehandler.getDashBoardStatus();
			 if(dashboardStatus != null)
			 {
				 txt_totalCon.setText(dashboardStatus[0]);
				 txt_billed .setText(dashboardStatus[1]);
				 txtSynched .setText(dashboardStatus[2]);
				 txtNotSynched.setText(dashboardStatus[3]);
						  
				 if (	!(  Integer.parseInt(dashboardStatus[1]) == Integer.parseInt(dashboardStatus[2]) )
						&& ( !(Integer.parseInt(dashboardStatus[3]) == 0))	) {
					btn_downlaodd.setEnabled(false);
					btn_uploadd.setEnabled(true);

				}else{
					btn_uploadd.setEnabled(false);
					btn_downlaodd.setEnabled(true);
				}
							 
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			databasehandler.close();
		 
	 }
	
/** *********************new method to download consumer data   ************************/
	
	/**
	 * @author Guru
	 * @date 05-Nov-2014
	 * @category Downloading billing data 
	 *
	 */
	public class performBackgroundTaskdownload_consumerDate extends AsyncTask<String, String, String>
	{
		int current = 0;
		int count = 0;
		public  ProgressDialog mProgressDialog;
		JSONArray consumer_json_arr = null;
		int noOfRecords = 0 ;
		DatabaseHandler databasehandler ;
		DatabaseBackup databasehandlerbackup ;
		boolean serverStatus = false ;
		Sendrequest send ;
		InputStream str=null ;
		 DateFormat dateFormat111 = new SimpleDateFormat("dd/MM/yyyy");
		 Calendar cal111 ;
		
		
		@Override
		protected void onPreExecute() {
	        	  
			super.onPreExecute();
			
			  mProgressDialog = new ProgressDialog(MainActivity.this);
	          mProgressDialog.setMessage("Downloading file..");
	          mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
	          mProgressDialog.setCancelable(false);
	          mProgressDialog.show();
	         
			
	        
		}

		@Override
		protected String doInBackground(String... arg0) {
			
			
			
			
			
			try {
				
				
				
				
				String sbmtoserverdate  = null;
				try {
				
				 cal111 = Calendar.getInstance();
				 sbmtoserverdate = dateFormat111.format(cal111.getTime());
				 KEY_SERVERTOSBMDATE = sbmtoserverdate ;
				} catch (Exception e) {
					e.printStackTrace();
				}
				
						
						
				System.out.println("KEY_SERVERTOSBMDATE is :"+KEY_SERVERTOSBMDATE);
				
				
				
				
			send = new Sendrequest();
			str = send.sendrequest("downloadbilldata/getData/"+ UtilMaster.mGLogin_MRCode.trim() + "/"+ UtilMaster.mGLogin_SiteCode.trim()  );
			} catch (Exception e) {
				e.printStackTrace();
			}
		logger.debug("Sendrequest.length  " +Sendrequest.length);

		if (str == null   || str.equals(null) || str.equals("null") ) {
			mProgressDialog.dismiss();
			serverStatus = true ;
	
		} else {
				String data = send.read(str);
				serverStatus = false ;
				if (	data == null 
					|| 	data == "null"
					|| 	data.equals("null") 
					||  data.equals(null)	|| data.contains("<html>"))
				{
					mProgressDialog.dismiss();
					mProgressDialog.dismiss();
					serverStatus = true ;
				}
				else {
					JSONArray json = null;
					try {
						System.out.println(data);
						json = new JSONArray(data);
					} catch (JSONException e) {
						e.printStackTrace();
					}
					
					if(json == null || json.equals("null"))
					{
						mProgressDialog.dismiss();
						serverStatus =  false ;
					}
					else
					{
					
						
						
						  databasehandler = new DatabaseHandler(getApplicationContext());

							try {
								
								databasehandler.openToRead();
							
					//fetching billed count from sqlite DB
								 
								List<BackupHelper> cursor_billed = databasehandler.Getbilledconsumersbackup();
								
								//int z=0;
				               logger.debug("cursor_billed :>>>>>>>>>>>size: "+cursor_billed.size());
				               
				               logger.debug("cursor_billed :>>>>>not null>>>>>> ");
								if(cursor_billed.size() > 0) {
								//z++;
									
								logger.debug("cursor_billed : --> "+cursor_billed.get(0).toString());
									
								databasehandlerbackup = new DatabaseBackup(getApplicationContext());
								databasehandlerbackup.openToWrite();
								databasehandlerbackup.insert(cursor_billed);
								databasehandlerbackup.close();
							 
								}
								//databasehandlerbackup.close();

							} catch (Exception e) {
								logger.debug(" errorr ***");
								// TODO: handle exception
								e.printStackTrace();
							}

							databasehandler.close();
						
						
						
						//	consumer_json_arr = json.optJSONObject("consumerData");
						consumer_json_arr = json;

						
						if (consumer_json_arr == null) {
							logger.debug("count"+"json array null"+"*************************************");
							JSONObject Asst_json_obj = null;
							//Asst_json_obj = json.optJSONObject("consumerData");
							try {
								Asst_json_obj = json.getJSONObject(0) ;
							} catch (JSONException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							if (Asst_json_obj == null) {
								
								new AlertDialog.Builder(MainActivity.this)
													  .setTitle("Info")
													  .setMessage("No more consumer to be billed")
													  .setPositiveButton("OK", null)
													  .show();
							} else {
									
								databasehandler = new DatabaseHandler(getApplicationContext());
								databasehandler.openToRead();
								databasehandler.deleteAll();
								databasehandler.close();
								
							
							try{	
								
								
								 String KEY_CONSUMER_SC_NO = Asst_json_obj.getString("consumer_sc_no");
								 String KEY_METER_CONSTANT = Asst_json_obj.getString("meter_constant");
								 String KEY_CONSUMER_NAME = Asst_json_obj.getString("consumer_name");
								 String KEY_ADDRESS2  ;//= Asst_json_obj.getString("address2");
								 if( 		Asst_json_obj.isNull("address2") 
										 || Asst_json_obj.get("address2") == null
									     || Asst_json_obj.get("address2") == "" ) 
								 {
									
									 Asst_json_obj.put("address2", "-");
									
									 KEY_ADDRESS2 = Asst_json_obj.getString("address2");

								} else {
										 
									KEY_ADDRESS2 = Asst_json_obj.getString("address2");
							    }
								 String KEY_ADDRESS1 ;
								 if( 		Asst_json_obj.isNull("address1") 
										 || Asst_json_obj.get("address1") == null
									     || Asst_json_obj.get("address1") == "" ) 
								 {
									
									Asst_json_obj.put("address1", "-");
									
									KEY_ADDRESS1 = Asst_json_obj.getString("address1");

								} else {
										 
									KEY_ADDRESS1 = Asst_json_obj.getString("address1");
							    }
								 
								 String KEY_TARIFF = Asst_json_obj.getString("tariff");
								 
								 
								 String KEY_TARIFFDESC ;//= Asst_json_obj.getString("TARIFFDESC");
								 if( 		Asst_json_obj.isNull("tariffdesc") 
										 || Asst_json_obj.get("tariffdesc") == null
									     || Asst_json_obj.get("tariffdesc") == "" ) 
								 {
									
									Asst_json_obj.put("tariffdesc", "");
									
									KEY_TARIFFDESC = Asst_json_obj.getString("tariffdesc");

								} else {
										 
									KEY_TARIFFDESC = Asst_json_obj.getString("tariffdesc");
							    }
								 
								 String KEY_LEDGER_NO  = Asst_json_obj.getString("ledger_no");
								 String KEY_FOLIO_NO  = Asst_json_obj.getString("folio_no");
								 String KEY_CONNECTED_LOAD  = Asst_json_obj.getString("connected_load");
								 String KEY_D_AND_R_FEE  = Asst_json_obj.getString("d_and_r_fee");
								 String KEY_ARREARS  = Asst_json_obj.getString("arrears");
								 
								 String KEY_INTEREST  = null;
							
							if( Asst_json_obj.isNull("interest") 
									|| Asst_json_obj.get("interest") == null
								    || Asst_json_obj.get("interest") == "" ) 
							{
								
								Asst_json_obj.put("interest", "0");
								KEY_INTEREST = Asst_json_obj.getString("interest");

							 } 
							else {
									 
								KEY_INTEREST = Asst_json_obj.getString("interest");
							    }
							
							
							
								 String KEY_OTHERS  = Asst_json_obj.getString("others");
								 String KEY_BACKBILLARR  = Asst_json_obj.getString("backbillarr");
								 
							 String KEY_AVERAGE_CONSUMPTION = "0";
								 
						if( Asst_json_obj.isNull("average_consumption")
				   				|| Asst_json_obj.get("average_consumption") == null
							  || Asst_json_obj.get("average_consumption") == "") {
								Asst_json_obj.put("average_consumption", "0");
								KEY_AVERAGE_CONSUMPTION = Asst_json_obj.getString("average_consumption").trim();

				   		} else {
				   		
				   			KEY_AVERAGE_CONSUMPTION = Asst_json_obj.getString("average_consumption").trim();
							
				   		}
						
						
								 
								 String KEY_DL_OR_MNR_PREV_MONTH = Asst_json_obj.getString("dl_or_mnr_prev_month");
								 String KEY_PREVIOUS_READING = Asst_json_obj.getString("previous_reading");
								 String KEY_POWER_FACTOR = Asst_json_obj.getString("power_factor");
								 
								 String KEY_READING_DATE= Asst_json_obj.getString("reading_date");
								 
								 
								 String KEY_METER_CHANGE_UNITS_CONSUMED = null;
								 
								 if( Asst_json_obj.isNull("meter_change_units_consumed") || Asst_json_obj.get("meter_change_units_consumed") == null|| Asst_json_obj.get("meter_change_units_consumed") == "") 
									{
										Asst_json_obj.put("meter_change_units_consumed", "0");
										KEY_METER_CHANGE_UNITS_CONSUMED = Asst_json_obj.getString("meter_change_units_consumed");
									} else 
									{
										KEY_METER_CHANGE_UNITS_CONSUMED = Asst_json_obj.getString("meter_change_units_consumed");
							        }			
										
								 
								 
								 
								 String KEY_NO_OF_MONTHS_ISSUED = Asst_json_obj.getString("no_of_months_issued");
								 String KEY_CREDIT_OR_REBATE = Asst_json_obj.getString("credit_or_rebate");
								 String KEY_FIXED_GES = Asst_json_obj.getString("fixed_ges");

								 String KEY_AUDIT_ARREARS = Asst_json_obj.getString("audit_arrears");
								 String KEY_OLD_INTEREST = Asst_json_obj.getString("old_interest");
								 String KEY_TRIVECTOR = null;
									if( Asst_json_obj.isNull("trivector") || Asst_json_obj.get("trivector") == null|| Asst_json_obj.get("trivector") == "") 
									{
										Asst_json_obj.put("trivector", "0");
										KEY_TRIVECTOR = Asst_json_obj.getString("trivector");
									} else 
									{
										KEY_TRIVECTOR = Asst_json_obj.getString("trivector");
							        }
									 String  ckwhlkwh = "0" ;
									 if( Asst_json_obj.isNull("ckwhlkwh") || Asst_json_obj.get("ckwhlkwh") == null|| Asst_json_obj.get("ckwhlkwh") == "") 
										{
											Asst_json_obj.put("ckwhlkwh", "0");
											ckwhlkwh = Asst_json_obj.getString("ckwhlkwh");
										} else 
										{
											ckwhlkwh = Asst_json_obj.getString("ckwhlkwh");
								        }
								 String KEY_DOORLOCKAVG = Asst_json_obj.getString("doorlockavg");
								 String KEY_CONSUMERCODE ;
								 	if(     Asst_json_obj.isNull("consumercode") 
										 || Asst_json_obj.get("consumercode") == null
									     || Asst_json_obj.get("consumercode") == "" ) {
								
								 		 Asst_json_obj.put("consumercode", "null");
								 		 KEY_CONSUMERCODE = Asst_json_obj.getString("consumercode");
									 } else {
										  
										 KEY_CONSUMERCODE = Asst_json_obj.getString("consumercode");
									 }
								  
								 String KEY_ADDITIONALDEP = Asst_json_obj.getString("additionaldep");
								 String KEY_MRCODE = Asst_json_obj.getString("bmdReading");
								 String KEY_BILLMONTH = Asst_json_obj.getString("billMonth");
								 String KEY_SITECODE = Asst_json_obj.getString("sdoCode");
								 String KEY_SYNCSTATUS = Asst_json_obj.getString("syncstatus");
							
						String KEY_DATAPREPAREDDATE;
						if( Asst_json_obj.isNull("dataprepareddate")|| Asst_json_obj.get("dataprepareddate") == null|| Asst_json_obj.get("dataprepareddate") == "" ) 
						{									
						 		Asst_json_obj.put("dataprepareddate", "-");
							    KEY_DATAPREPAREDDATE = Asst_json_obj.getString("dataprepareddate");
									    
						} else 
						{

								KEY_DATAPREPAREDDATE = Asst_json_obj.getString("dataprepareddate");
						}
								 
							/*	 String KEY_SERVERTOSBMDATE="";
						
						if( Asst_json_obj.isNull("servertosbmdate")|| Asst_json_obj.get("servertosbmdate") == null|| Asst_json_obj.get("servertosbmdate") == "" ) 
						{									
						 		Asst_json_obj.put("servertosbmdate", "-");
						 		KEY_SERVERTOSBMDATE = Asst_json_obj.getString("servertosbmdate");
									    
						} else 
						{

							KEY_SERVERTOSBMDATE = Asst_json_obj.getString("servertosbmdate");
						}
						*/
								 String KEY_METERNO;
								 if(    Asst_json_obj.isNull("meterno")
									 || Asst_json_obj.get("meterno") == null
									 || Asst_json_obj.get("meterno") == "" ) {
									
									 Asst_json_obj.put("meterno", "0");
									 KEY_METERNO = Asst_json_obj.getString("meterno");

								 } else {
										
									 KEY_METERNO = Asst_json_obj.getString("meterno");
									 
								 }
								  
								
								 String KEY_INTERESTONDEPOSIT;
								 if (Asst_json_obj.isNull("interestondeposit")
											|| Asst_json_obj.get("interestondeposit") == null
											|| Asst_json_obj.get("interestondeposit") == ""
									)
								 {
									 Asst_json_obj.put("interestondeposit", "0");
									  KEY_INTERESTONDEPOSIT = Asst_json_obj.getString("interestondeposit");
								 }
								 else
								 {
									  KEY_INTERESTONDEPOSIT = Asst_json_obj.getString("interestondeposit");
								 }
								 
								 String KEY_DL_ADJ;
								 if( Asst_json_obj.getString("dl_adj") == null)
								 {
									  KEY_DL_ADJ = "0";
								 }
								 else
								 {
									  KEY_DL_ADJ = Asst_json_obj.getString("dl_adj");
								 }
								
								  
								 String KEY_DL_COUNT;
								 if( Asst_json_obj.getString("dl_count") == null)
								 {
									 KEY_DL_COUNT = "0";
								 }
								 else
								 {
									 KEY_DL_COUNT = Asst_json_obj.getString("dl_count");
								 }
								 
								 
								 String KEY_METERRENT;
								 if( Asst_json_obj.isNull("meterrent") 
										 || Asst_json_obj.get("meterrent") == null
									     || Asst_json_obj.get("meterrent") == "" ) 
								 	{
									
									 Asst_json_obj.put("meterrent", "0");
									 KEY_METERRENT = Asst_json_obj.getString("meterrent");
    							    }
								 else {
										 
    								   KEY_METERRENT = Asst_json_obj.getString("meterrent");
								    }
								  
								
								 
								 String KEY_FPPCA;
								 if( Asst_json_obj.getString("fppca") == null)
								 {
									  KEY_FPPCA = "0";
								 }
								 else
								 {
									  KEY_FPPCA = Asst_json_obj.getString("fppca");
								 }
								
								 
								 String KEY_EXTRA1;
								 if( Asst_json_obj.isNull("extra1") 
										 || Asst_json_obj.get("extra1") == null
									     || Asst_json_obj.get("extra1") == ""  ) {
									Asst_json_obj.put("extra1", " ");
									KEY_EXTRA1 = Asst_json_obj.getString("extra1");

								 } 
								 else {
										  
									 KEY_EXTRA1 = Asst_json_obj.getString("extra1");
								      }
								  
								 
								 
							
								 
								 String KEY_EXTRA2;
								 
								 if( Asst_json_obj.isNull("extra2") 
										 || Asst_json_obj.get("extra2") == null
									     || Asst_json_obj.get("extra2") == "" ) {
									
									 Asst_json_obj.put("extra2", "null");
									 KEY_EXTRA2 = Asst_json_obj.getString("extra2");

									   } 
								 else {
										  
									 KEY_EXTRA2 = Asst_json_obj.getString("extra2");
								     }
								  
								 
								 String key_cyclename ;
								 if( Asst_json_obj.isNull("cyclename") 
										 || Asst_json_obj.get("cyclename") == null
									     || Asst_json_obj.get("cyclename") == "" ) 
								 {
									
									 Asst_json_obj.put("cyclename", "-");
									 key_cyclename = Asst_json_obj.getString("cyclename");

								 } 
								 else {
										   key_cyclename = Asst_json_obj.getString("cyclename");
									  }
								  
								 
								 
								
								 String key_consumer_key ;
								 
									if( Asst_json_obj.isNull("consumer_key") 
											|| Asst_json_obj.get("consumer_key") == null
										    || Asst_json_obj.get("consumer_key") == "" ) 
									{
										
										Asst_json_obj.put("consumer_key", "0");
										key_consumer_key = Asst_json_obj.getString("consumer_key");

									 } 
									else {
											 
										key_consumer_key = Asst_json_obj.getString("consumer_key");
									    }
									  
									 
								 
								 String key_installationo ;
									if( Asst_json_obj.isNull("installationo")
											|| Asst_json_obj.get("installationo") == null
										    || Asst_json_obj.get("installationo") == "" ) 
									{
										
										Asst_json_obj.put("installationo", "-");
										key_installationo = Asst_json_obj.getString("installationo");

										 
									} 
									else {
											 
										key_installationo = Asst_json_obj.getString("installationo");
										  
									}
									 
									
								 String key_consumerno ;
						   		if( Asst_json_obj.isNull("consumerno")
						   				|| Asst_json_obj.get("consumerno") == null
									  || Asst_json_obj.get("consumerno") == "") 
						   		
						   		{
										Asst_json_obj.put("consumerno", "-");
										key_consumerno = Asst_json_obj.getString("consumerno").trim();

						   		} else {
						   		
						   			key_consumerno = Asst_json_obj.getString("consumerno").trim();
									
						   		}
									 
								 
								 
								 String key_division1  = " ";
									if( Asst_json_obj.isNull("division") 
											|| Asst_json_obj.get("division") == null
										    || Asst_json_obj.get("division") == "") 
									{
									
										Asst_json_obj.put("division", "-");
										key_division1 = Asst_json_obj.getString("division");

									} else {
											   key_division1 = Asst_json_obj.getString("division");
										   }
									 
							 
			String key_subdivision1 = " ";
			if( Asst_json_obj.isNull("subdivision")|| Asst_json_obj.get("subdivision") == null|| Asst_json_obj.get("subdivision") == "")
			{
				Asst_json_obj.put("subdivision", "-");
				key_subdivision1 = Asst_json_obj.getString("subdivision");
			} else
			{
				key_subdivision1 = Asst_json_obj.getString("subdivision");
			}
									 
									 
			String key_bookno1="";
			if( Asst_json_obj.isNull("bookno") || Asst_json_obj.get("bookno") == null|| Asst_json_obj.get("bookno") == "") 
			{
				Asst_json_obj.put("bookno", "-");
				key_bookno1 = Asst_json_obj.getString("bookno");
			} else 
			{
			   key_bookno1 = Asst_json_obj.getString("bookno");
	        }
			
			String key_devicefirmwareversion = "";
			String versionName = null;
			try {
				versionName = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
				} catch (NameNotFoundException e) 
				{
				// TODO Auto-generated catch block
				e.printStackTrace();
				}

			key_devicefirmwareversion = versionName;
									
			String key_billedatetimestamp = "-" ;	
			String KEY_BILLNO  = "0";
			String  KEY_STATUS = "0";
			String  KEY_BILLEDSTATUS ="0";
									
/** 3 new field added**********************/		
									
			String previous_bdate = "";
			if( Asst_json_obj.isNull("previous_reading_date") || Asst_json_obj.get("previous_reading_date") == null|| Asst_json_obj.get("previous_reading_date") == "") 
			{
				Asst_json_obj.put("previous_reading_date", " ");
				previous_bdate = Asst_json_obj.getString("previous_reading_date");
			} else 
			{
				previous_bdate = Asst_json_obj.getString("previous_reading_date");
	        }
			
			
			String actual_previous_bdate = "";
			if( Asst_json_obj.isNull("previous_actual_reading_date") || Asst_json_obj.get("previous_actual_reading_date") == null|| Asst_json_obj.get("previous_actual_reading_date") == "") 
			{
				Asst_json_obj.put("previous_actual_reading_date", " ");
				actual_previous_bdate = Asst_json_obj.getString("previous_actual_reading_date");
			} else 
			{
				actual_previous_bdate = Asst_json_obj.getString("previous_actual_reading_date");
	        }
			
			String seasonal_consumer = "";
			if( Asst_json_obj.isNull("seasonal_consumer") || Asst_json_obj.get("seasonal_consumer") == null|| Asst_json_obj.get("seasonal_consumer") == "") 
			{
				Asst_json_obj.put("seasonal_consumer", "");
				seasonal_consumer = Asst_json_obj.getString("seasonal_consumer");
			} else 
			{
				seasonal_consumer = Asst_json_obj.getString("seasonal_consumer");
	        }
									
			String lineMinimum = "";
			if( Asst_json_obj.isNull("lineminimum") || Asst_json_obj.get("lineminimum") == null|| Asst_json_obj.get("lineminimum") == "") 
			{
				Asst_json_obj.put("lineminimum", "0");
				lineMinimum = Asst_json_obj.getString("lineminimum");
			} else 
			{
				lineMinimum = Asst_json_obj.getString("lineminimum");
	        }
			
			String ligPoints = "";
			if( Asst_json_obj.isNull("ligpoints") || Asst_json_obj.get("ligpoints") == null|| Asst_json_obj.get("ligpoints") == "") 
			{
				Asst_json_obj.put("ligpoints", "0");
				ligPoints = Asst_json_obj.getString("ligpoints");
			} else 
			{
				ligPoints = Asst_json_obj.getString("ligpoints");
	        }
			
			String 	metered	 = "";
			
			if( Asst_json_obj.isNull("fppca") || Asst_json_obj.get("fppca") == null|| Asst_json_obj.get("fppca") == "") 
			{
				Asst_json_obj.put("fppca", "0");
				metered = Asst_json_obj.getString("fppca");
			} else 
			{
				metered = Asst_json_obj.getString("fppca");
	        }	
			
			String taxexemptionExtra6 = "0";
			if( Asst_json_obj.isNull("taxexemption") || Asst_json_obj.get("taxexemption") == null|| Asst_json_obj.get("taxexemption") == "") 
			{
				Asst_json_obj.put("taxexemption", "0");
				taxexemptionExtra6 = Asst_json_obj.getString("taxexemption");
			} else 
			{
				taxexemptionExtra6 = Asst_json_obj.getString("taxexemption");
	        }	
			
							
								   
						
							 databasehandler = new DatabaseHandler(getApplicationContext());
							 databasehandler.openToWrite();
							 databasehandler.insert(
									 KEY_CONSUMER_SC_NO ,
									 KEY_METER_CONSTANT,
										   KEY_CONSUMER_NAME,
										   KEY_ADDRESS1 ,
										   KEY_ADDRESS2 ,
										   KEY_TARIFF,
										   KEY_TARIFFDESC,
										   KEY_LEDGER_NO ,
										   KEY_FOLIO_NO ,
										   KEY_CONNECTED_LOAD ,
										   KEY_D_AND_R_FEE,
										   KEY_ARREARS ,
										   KEY_INTEREST ,
										   KEY_OTHERS,
										   KEY_BACKBILLARR,
										   KEY_AVERAGE_CONSUMPTION ,
										   KEY_DL_OR_MNR_PREV_MONTH ,
										   KEY_PREVIOUS_READING ,
										   KEY_POWER_FACTOR ,
										   KEY_READING_DATE,
										   KEY_METER_CHANGE_UNITS_CONSUMED ,
										   KEY_NO_OF_MONTHS_ISSUED ,
										   KEY_CREDIT_OR_REBATE ,
										   KEY_FIXED_GES,

										   KEY_AUDIT_ARREARS ,
										   KEY_OLD_INTEREST,
										   KEY_TRIVECTOR ,
										   ckwhlkwh,
										   KEY_DOORLOCKAVG ,
										   KEY_CONSUMERCODE ,
										   KEY_ADDITIONALDEP ,
										   KEY_MRCODE ,
										   KEY_BILLMONTH ,
										   KEY_SITECODE ,
										   KEY_SYNCSTATUS,
										   KEY_DATAPREPAREDDATE ,
										   KEY_SERVERTOSBMDATE,
										   KEY_METERNO ,
										   KEY_INTERESTONDEPOSIT ,
										   KEY_DL_ADJ ,
										   KEY_DL_COUNT,
										   KEY_METERRENT,
										   KEY_FPPCA,
										   KEY_EXTRA1,
										   KEY_EXTRA2,
										   
										   
										   key_cyclename,
										   key_consumer_key,
										   key_installationo,
										   key_consumerno,
										   
										   key_division1,
										   key_subdivision1,
										   key_bookno1,
										   key_devicefirmwareversion,
										   
										   key_billedatetimestamp,
										   
										   KEY_BILLNO ,
										    KEY_STATUS ,
										    KEY_BILLEDSTATUS,  previous_bdate,
											    actual_previous_bdate,lineMinimum ,
											    seasonal_consumer, ligPoints,
											    metered, taxexemptionExtra6);
								databasehandler.close();
								logger.debug("count"+count+"*************************************");
								
								count++;
								current++;
	
							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							noOfRecords = count ;

							}

						} else {
							
							databasehandler = new DatabaseHandler(	getApplicationContext());
							databasehandler.openToRead();
							databasehandler.deleteAll();
							databasehandler.close();
							logger.debug("count"+"json array "+"*************************************");
							//JSONArray array = null;
							/*try {
								array = new JSONArray("consumerData");
								
							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}*/
							
							 long total = 0;
							 int progress = 0;
							 // long temp= 0;
							 int progress_temp =0;
							 mProgressDialog.setMax((int) consumer_json_arr.length());
							 for (int i = 0; i < consumer_json_arr.length(); i++) {
								JSONObject obj = null;
								try {
									
									 total =  count;
									// temp = total;
				                        progress_temp = (int) ((int) total * 100 / consumer_json_arr.length());
				                        logger.debug("SendRequest.length"+consumer_json_arr.length());
				                       //mProgressDialog.setMessage("g"+ progress_temp);
				                        String[] val = new String[2];
				                        		val[0]=""+total;
				                        val[1] = ""+progress_temp;
				                      
									logger.debug("count"+count+"*************************************");
									obj = consumer_json_arr.getJSONObject(i);
									String KEY_CONSUMER_SC_NO = obj.getString("consumer_sc_no");
									 String KEY_METER_CONSTANT = obj.getString("meter_constant");
									 String KEY_CONSUMER_NAME = obj.getString("consumer_name");
									 String KEY_ADDRESS2  ; //= obj.getString("address2");
									 String KEY_ADDRESS1 ;
									 if( 		obj.isNull("address1") 
											 || obj.get("address1") == null
										     || obj.get("address1") == "" ) 
									 {
										
										 obj.put("address1", "-");
										
										KEY_ADDRESS1 = obj.getString("address1");

									} else {
											 
										KEY_ADDRESS1 = obj.getString("address1");
								    }
									 
									 
									 if( 		obj.isNull("address2") 
											 || obj.get("address2") == null
										     || obj.get("address2") == "" ) 
									 {
										
										 obj.put("address2", "-");
										
										 KEY_ADDRESS2 = obj.getString("address2");

									} else {
											 
										KEY_ADDRESS2 = obj.getString("address2");
								    }
									 
									 
									 String KEY_TARIFF = obj.getString("tariff");
									// String KEY_TARIFFDESC = obj.getString("TARIFF");
									 String KEY_TARIFFDESC ;//= Asst_json_obj.getString("TARIFFDESC");
									 if( 		obj.isNull("tariffdesc") 
											 || obj.get("tariffdesc") == null
										     || obj.get("tariffdesc") == "" ) 
									 {
										
										 obj.put("tariffdesc", "");
										
										KEY_TARIFFDESC = obj.getString("tariffdesc");

									} else {
											 
										KEY_TARIFFDESC = obj.getString("tariffdesc");
								    }
									 logger.debug("KEY_TARIFFDESC ***********"+KEY_TARIFFDESC);
									 String KEY_LEDGER_NO  = obj.getString("ledger_no");
									 String KEY_FOLIO_NO  = obj.getString("folio_no");
									 String KEY_CONNECTED_LOAD  = obj.getString("connected_load");
									 String KEY_D_AND_R_FEE  = obj.getString("d_and_r_fee");
									 String KEY_ARREARS  = obj.getString("arrears");
									 
									 String KEY_INTEREST  ;//= obj.getString("interest");
									 
								
								if( obj.isNull("interest") 
										|| obj.get("interest") == null
									    || obj.get("interest") == "" ) 
								{
									
									obj.put("interest", "0");
									KEY_INTEREST = obj.getString("interest");

								 } 
								else {
										 
									KEY_INTEREST = obj.getString("interest");
								    }
								
								
									 
									 String KEY_OTHERS  = obj.getString("others");
									 String KEY_BACKBILLARR  = obj.getString("backbillarr");
									 String KEY_AVERAGE_CONSUMPTION = obj.getString("average_consumption");
									 String KEY_DL_OR_MNR_PREV_MONTH = obj.getString("dl_or_mnr_prev_month");
									 String KEY_PREVIOUS_READING = obj.getString("previous_reading");
									 String KEY_POWER_FACTOR = obj.getString("power_factor");
									 
									 String KEY_READING_DATE= obj.getString("reading_date");
									 String KEY_METER_CHANGE_UNITS_CONSUMED = obj.getString("meter_change_units_consumed");
									 String KEY_NO_OF_MONTHS_ISSUED = obj.getString("no_of_months_issued");
									 String KEY_CREDIT_OR_REBATE = obj.getString("credit_or_rebate");
									 String KEY_FIXED_GES = obj.getString("fixed_ges");

									 String KEY_AUDIT_ARREARS = obj.getString("audit_arrears");
									 String KEY_OLD_INTEREST = obj.getString("old_interest");
									 String KEY_TRIVECTOR ;
									 
									 if( obj.isNull("trivector") || obj.get("trivector") == null|| obj.get("trivector") == "") 
										{
										 obj.put("trivector", "0");
											KEY_TRIVECTOR = obj.getString("trivector");
										} else 
										{
											KEY_TRIVECTOR = obj.getString("trivector");
								        }			
											
									 String  ckwhlkwh = "0" ;
									 if( obj.isNull("ckwhlkwh") || obj.get("ckwhlkwh") == null|| obj.get("ckwhlkwh") == "") 
										{
										 obj.put("ckwhlkwh", "0");
											ckwhlkwh = obj.getString("ckwhlkwh");
										} else 
										{
											ckwhlkwh = obj.getString("ckwhlkwh");
								        }
									 
									 
									 String KEY_DOORLOCKAVG = obj.getString("doorlockavg");
									 
									 
									 String KEY_CONSUMERCODE ;
									 
									 if( obj.isNull("consumercode") || obj.get("consumercode") == null
										     || obj.get("consumercode") == "") {
										obj.put("consumercode", "null");
										KEY_CONSUMERCODE = obj.getString("consumercode");

										   } else {
											   KEY_CONSUMERCODE = obj.getString("consumercode");
										   }
									  
									 
									 
									 
									 
									 String KEY_ADDITIONALDEP = obj.getString("additionaldep");
									 String KEY_MRCODE = obj.getString("bmdReading");
									 String KEY_BILLMONTH = obj.getString("billMonth");
									 String KEY_SITECODE = obj.getString("sdoCode");
									 String KEY_SYNCSTATUS = obj.getString("syncstatus");
									
									 String KEY_DATAPREPAREDDATE;
										if( obj.isNull("dataprepareddate") || obj.get("dataprepareddate") == null
										     || obj.get("dataprepareddate") == "") 
										{
										      obj.put("dataprepareddate", "-");
										   
										      KEY_DATAPREPAREDDATE = obj.getString("dataprepareddate");

										}
										else {
										
											KEY_DATAPREPAREDDATE = obj.getString("dataprepareddate");
											
										}
									 
									 
									 /*String KEY_SERVERTOSBMDATE="";
										
										if( obj.isNull("servertosbmdate")|| obj.get("servertosbmdate") == null|| obj.get("servertosbmdate") == "" ) 
										{									
											obj.put("servertosbmdate", "-");
											KEY_SERVERTOSBMDATE = obj.getString("servertosbmdate");
													    
										} else 
										{

											KEY_SERVERTOSBMDATE = obj.getString("servertosbmdate");
										}*/
									
									 
									 String KEY_METERNO;
									 if( obj.isNull("meterno") 
											 || obj.get("meterno") == null
										     || obj.get("meterno") == "")
									 {
									
										 obj.put("meterno", "0");
										 KEY_METERNO = obj.getString("meterno");

									 } else {
											   KEY_METERNO = obj.getString("meterno");
										   }
									  
									 
									 
								
									 String KEY_INTERESTONDEPOSIT;
									 if( !obj.has("interestondeposit") )
									 {
										  KEY_INTERESTONDEPOSIT = "0";
									 }
									 else
									 {
										  KEY_INTERESTONDEPOSIT = obj.getString("interestondeposit");
									 }
									
									 String KEY_DL_ADJ;
									 if( !obj.has("dl_adj") )
									 {
										  KEY_DL_ADJ = "0";
									 }
									 else
									 {
										  KEY_DL_ADJ = obj.getString("dl_adj");
									 }
									 String KEY_DL_COUNT;
									 if( !obj.has("dl_count") )
									 {
										 KEY_DL_COUNT = "0";
									 }
									 else
									 {
										 KEY_DL_COUNT = obj.getString("dl_count");
									 }
									 
									 String KEY_METERRENT;
									 if( obj.isNull("meterrent") || obj.get("meterrent") == null
										     || obj.get("meterrent") == "") {
										obj.put("meterrent", "0");
										KEY_METERRENT = obj.getString("meterrent");

									 }
									 else {
											   KEY_METERRENT = obj.getString("meterrent");
										   }
									  
									 
									 
									 String KEY_FPPCA;
									 if( obj.isNull("fppca") || obj.get("fppca") == null
										     || obj.get("fppca") == "")
									 {
										 obj.put("fppca", "0");
										  KEY_FPPCA = "0";
									 }
									 else
									 {
										  KEY_FPPCA = obj.getString("fppca");
									 }
									
									 
									 String KEY_EXTRA1;
									 if( obj.isNull("extra1") || obj.get("extra1") == null
										     || obj.get("extra1") == "") {
										obj.put("extra1", "null");
										KEY_EXTRA1 = obj.getString("extra1");

									 } 
									 else {
											   KEY_EXTRA1 = obj.getString("extra1");
										   }
									  
									 
								 
									 String KEY_EXTRA2;
									 if( obj.isNull("extra2") || obj.get("extra2") == null
										     || obj.get("extra2") == "") 
									 {
									
										 obj.put("extra2", "null");
										 KEY_EXTRA2 = obj.getString("extra2");

									 } 
									 else {
											   KEY_EXTRA2 = obj.getString("extra2");
										   }
									  
									 
								 
									 String key_cyclename ;
									 if( obj.isNull("cyclename") || obj.get("cyclename") == null
										     || obj.get("cyclename") == "")
									 {
									
										 obj.put("cyclename", "null");
										 key_cyclename = obj.getString("cyclename");

									 }
									 else {
											   key_cyclename = obj.getString("cyclename");
										   }
									  
								 String key_consumer_key ;
								if( obj.isNull("consumer_key") || obj.get("consumer_key") == null
											     || obj.get("consumer_key") == "")
								{
								
									obj.put("consumer_key", "null");
									key_consumer_key = obj.getString("consumer_key");

								}
								else {
									key_consumer_key = obj.getString("consumer_key");
								
								}
									 
									
								String key_installationo ;
								if( obj.isNull("installationo") 
										|| obj.get("installationo") == null
										|| obj.get("installationo") == "") 
								{
									
									obj.put("installationo", "null");
									key_installationo = obj.getString("installationo");

								} else {
			
									key_installationo = obj.getString("installationo");
									
								}
										 
									 
								 String key_consumerno ;
								if( obj.isNull("consumerno") 
										|| obj.get("consumerno") == null
										|| obj.get("consumerno") == "") 
								{
										
									obj.put("consumerno", "null");
									key_consumerno = obj.getString("consumerno").trim();

								} else {
												   key_consumerno = obj.getString("consumerno").trim();
											   }
										 
									 String key_division1  = " ";
									 
										if( obj.isNull("division") || obj.get("division") == null
											     || obj.get("division") == "") {
											obj.put("division", "null");
											key_division1 = obj.getString("division");

											   } else {
												   key_division1 = obj.getString("division");
											   }
										 
									 
										String key_subdivision1 = " ";
										if( obj.isNull("subdivision") || obj.get("subdivision") == null
											     || obj.get("subdivision") == "") {
											obj.put("subdivision", "null");
											key_subdivision1 = obj.getString("subdivision");

											   } else {
												   key_subdivision1 = obj.getString("subdivision");
											   }
										 
										 
										String key_bookno1="";
									 
									
										if( obj.isNull("bookno") || obj.get("bookno") == null
											     || obj.get("bookno") == "") {
											obj.put("bookno", "null");
											key_bookno1 = obj.getString("bookno");

											   } else {
												   key_bookno1 = obj.getString("bookno");
											   }
										 
											
								String key_devicefirmwareversion = ""	;	
									String versionName = null;

									try {
										versionName = getPackageManager()
												.getPackageInfo(
														getPackageName(), 0).versionName;

									} catch (NameNotFoundException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}

									key_devicefirmwareversion = versionName;
										
							
							  String key_billedatetimestamp = "-" ;	
							  String KEY_BILLNO  = "0";
							  String  KEY_STATUS = "0";
							  String  KEY_BILLEDSTATUS ="0";
							  
							  /** 3 new field added**********************/		
								
								String previous_bdate = "";
								if( obj.isNull("previous_reading_date") || obj.get("previous_reading_date") == null|| obj.get("previous_reading_date") == "") 
								{
									obj.put("previous_reading_date", " ");
									previous_bdate = obj.getString("previous_reading_date");
								} else 
								{
									previous_bdate = obj.getString("previous_reading_date");
						        }
								
								
								String actual_previous_bdate = "";
								if( obj.isNull("previous_actual_reading_date") || obj.get("previous_actual_reading_date") == null|| obj.get("previous_actual_reading_date") == "") 
								{
									obj.put("previous_actual_reading_date", " ");
									actual_previous_bdate = obj.getString("previous_actual_reading_date");
								} else 
								{
									actual_previous_bdate = obj.getString("previous_actual_reading_date");
						        }
								
								String seasonal_consumer = "";
								if( obj.isNull("seasonal_consumer") || obj.get("seasonal_consumer") == null|| obj.get("seasonal_consumer") == "") 
								{
									obj.put("seasonal_consumer", "");
									seasonal_consumer = obj.getString("seasonal_consumer");
								} else 
								{
									seasonal_consumer = obj.getString("seasonal_consumer");
						        }
								
								
								String lineMinimum = "";
								if( obj.isNull("lineminimum") || obj.get("lineminimum") == null|| obj.get("lineminimum") == "") 
								{
									obj.put("lineminimum", "0");
									lineMinimum = obj.getString("lineminimum");
								} else 
								{
									lineMinimum = obj.getString("lineminimum");
						        }
								
								String ligPoints = "";
								if( obj.isNull("ligpoints") || obj.get("ligpoints") == null|| obj.get("ligpoints") == "") 
								{
									obj.put("ligpoints", "0");
									ligPoints = obj.getString("ligpoints");
								} else 
								{
									ligPoints = obj.getString("ligpoints");
						        }
								
								
								String 	metered	 = "";
								
								if( obj.isNull("fppca") || obj.get("fppca") == null|| obj.get("fppca") == "") 
								{
									obj.put("fppca", "0");
									metered = obj.getString("fppca");
								} else 
								{
									metered = obj.getString("fppca");
						        }		
								
								
								String taxexemptionExtra6 = "0";
								if( obj.isNull("taxexemption") || obj.get("taxexemption") == null|| obj.get("taxexemption") == "") 
								{
									obj.put("taxexemption", "0");
									taxexemptionExtra6 = obj.getString("taxexemption");
								} else 
								{
									taxexemptionExtra6 = obj.getString("taxexemption");
						        }	
								
								
								
								
									   
							  databasehandler = new DatabaseHandler(getApplicationContext());
							  databasehandler.openToWrite();
							  databasehandler.insert(  KEY_CONSUMER_SC_NO ,
											   KEY_METER_CONSTANT,
											   KEY_CONSUMER_NAME,
											   KEY_ADDRESS1 ,
											   KEY_ADDRESS2 ,
											   KEY_TARIFF,
											   KEY_TARIFFDESC,
											   KEY_LEDGER_NO ,
											   KEY_FOLIO_NO ,
											   KEY_CONNECTED_LOAD ,
											   KEY_D_AND_R_FEE,
											   KEY_ARREARS ,
											   KEY_INTEREST ,
											   KEY_OTHERS,
											   KEY_BACKBILLARR,
											   KEY_AVERAGE_CONSUMPTION ,
											   KEY_DL_OR_MNR_PREV_MONTH ,
											   KEY_PREVIOUS_READING ,
											   KEY_POWER_FACTOR ,
											   KEY_READING_DATE,
											   KEY_METER_CHANGE_UNITS_CONSUMED ,
											   KEY_NO_OF_MONTHS_ISSUED ,
											   KEY_CREDIT_OR_REBATE ,
											   KEY_FIXED_GES,

											   KEY_AUDIT_ARREARS ,
											   KEY_OLD_INTEREST,
											   KEY_TRIVECTOR ,
											   ckwhlkwh ,
											   KEY_DOORLOCKAVG ,
											   KEY_CONSUMERCODE ,
											   KEY_ADDITIONALDEP ,
											   KEY_MRCODE ,
											   KEY_BILLMONTH ,
											   KEY_SITECODE ,
											   KEY_SYNCSTATUS,
											   KEY_DATAPREPAREDDATE ,
											   KEY_SERVERTOSBMDATE,
											   KEY_METERNO ,
											   KEY_INTERESTONDEPOSIT ,
											   KEY_DL_ADJ ,
											   KEY_DL_COUNT ,
											   KEY_METERRENT,
											   KEY_FPPCA,
											   KEY_EXTRA1,
											   KEY_EXTRA2,
											   
											   
											   key_cyclename,
											   key_consumer_key,
											   key_installationo,
											   key_consumerno,
											   
											   key_division1,
											   key_subdivision1,
											   key_bookno1,
											   key_devicefirmwareversion,
											   
											   key_billedatetimestamp,
											   
											   KEY_BILLNO ,
											    KEY_STATUS ,
											    KEY_BILLEDSTATUS ,  previous_bdate,
											    actual_previous_bdate,lineMinimum,
											    seasonal_consumer,ligPoints,metered , taxexemptionExtra6);
									databasehandler.close();
								
									logger.debug("count"+count+"*************************************");
									
									publishProgress(val);
				                    
									if ( progress != progress_temp) {
				                            progress = progress_temp ;
				                        }
									
									count++;
									current++;
									
									//progress++;
								} catch (JSONException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}

								// prepare the list of all records

							}
							
							mProgressDialog.dismiss();
							noOfRecords = count ;
							
						}

					}

				}
			}
		return null;
		
		}
		
		
		
	
		protected void onProgressUpdate(String... values) {
			mProgressDialog.setProgress(Integer.parseInt(values[0]));
			mProgressDialog.setSecondaryProgress(Integer.parseInt(values[0]));
		    System.out.println("total"+values[0]);
		    DecimalFormat df = new DecimalFormat("###.##");
		    System.out.println("Mega bytes mbs"+df.format(((Float.valueOf(values[0])/1048576)*100)/100));
		    mProgressDialog.setMessage("Downloading file.. "+df.format((Float.valueOf(values[0])))+" of "+df.format(Float.valueOf(consumer_json_arr.length())));

		}
		 @Override
	        protected void onPostExecute(String unused) {
			 mProgressDialog.dismiss();
		 
			 
			 if(noOfRecords > 0 )
			 {
				 
				 int tInDb = 0;
				 
					databasehandler = new DatabaseHandler(getApplicationContext());

					try {

						
						databasehandler.openToRead();
						/*fetching billed count from sqlite DB
						 */
						String cursor_billed = databasehandler.getTotalCount();
						tInDb = Integer.parseInt(cursor_billed);
						
					} catch (Exception e) {
						e.printStackTrace();

					}

					databasehandler.close();
				 
				 
				 if(tInDb == noOfRecords)
				 {
					  new AlertDialog.Builder(MainActivity.this)
					 					   .setTitle("Info")
					 					   .setMessage("Successfully downloaded "+noOfRecords+" Consumers data...")
					 					   .setPositiveButton("OK", new DialogInterface.OnClickListener()
					 					   {
					 						   
					 						   public void onClick(DialogInterface dialog,int which) 
					 						   {
					 							  setDashboard();
					 						   }
					 					   }).show();
				 }else if(noOfRecords > tInDb)
				 {
					 
					 
					new AlertDialog.Builder(MainActivity.this)
					   .setTitle("Info")
					    .setMessage("Downloaded records : "+noOfRecords+"\n Duplicate records :"+(noOfRecords-tInDb)+"\n Total records available : "+tInDb)
					   .setPositiveButton("OK", new DialogInterface.OnClickListener()
					   {
						   
						   public void onClick(DialogInterface dialog,int which) 
						   {
							   setDashboard();
						   }
					   }).show();
				 }
				 
			 }else if(serverStatus )
			 {
			
				 serverStatus= false ;
				  new AlertDialog.Builder(MainActivity.this)
				 					   .setTitle("Info")
				 					   .setMessage("Sorry,connection cannot made at this time. Server is down. Please try again later")
				 					   .setPositiveButton("OK", 
				 							   new DialogInterface.OnClickListener()
				 					   {

				 						   public void onClick(DialogInterface dialog,int which) 
				 						   {
				 							  setDashboard();
				 						   }
				 					   }).show();
			 }else
			 {
				 new AlertDialog.Builder(MainActivity.this)
				 					   .setTitle("Info")
				 					   .setMessage("No more consumer to be download for this MR code")
				 					   .setPositiveButton("OK", 
				 							   new DialogInterface.OnClickListener()
				 					   {
				 						   public void onClick(DialogInterface dialog,int which) 
				 						   {
				 							  setDashboard();
				 						   }
				 					   }).show();
		 	}
		 }
	}
	
	/************************************ FOR BACKGROUND TASK FOR TARIFF  *******************************/
	 /**
	 * @author Guru
	 * @date 05-Nov-2014
	 * @category Tariff_Downloading/Updating  for billing   
	 * 
	 */
	
	public class performBackgroundTaskTariff extends AsyncTask <String, String, String> {
		int current = 0;
		int count = 0;
		public  ProgressDialog mProgressDialog;
		JSONArray consumer_json_arr = null;
		int noOfRecords = 0 ;
		int progress_temp =0;
		
		String error_msg_01 = "NOERROR";
		InputStream str=null ;
		
		
		/*BILLING PARAM'S*/
		String meterreadercode_down, response ;
		String url_to_download ;
		Sendrequest req_to_download ;
		DBTariffHandler db_helper = null;
		int totaldownload = 0;
		
		
		@Override
		protected void onPreExecute() {
	        	  
			super.onPreExecute();
			ErrorClass.errorMessage = "";
			  mProgressDialog = new ProgressDialog(MainActivity.this);
	          mProgressDialog.setMessage("Downloading Tariff..");
	          mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
	          mProgressDialog.setCancelable(false);
	          mProgressDialog.show();
	          url_to_download = "getTariff";
	          req_to_download = new Sendrequest();
		}

		@Override
		protected String doInBackground(String... arg0) {
			
			try {
				error_msg_01 = "NOERROR";
				str = req_to_download.sendrequest(url_to_download);
				response = req_to_download.read(str);
				logger.debug("This is message from server" + response);
					if (MasterLibraryFunction.IsNullOrEmpty(ErrorClass.getErrorMessages()))
					{
						
					//	ERROR MSG 
						if (ErrorClass.getErrorMessages().contains("timed out")) 
						{
							ErrorClass.errorMessage = "";
							error_msg_01 = "Connection timed out. Server might be shut down." ;
						} else if (ErrorClass.getErrorMessages().contains("java.net.SocketTimeoutException"))
						{
							ErrorClass.errorMessage = "";
							error_msg_01 = "Socket timed out";
						} else if (ErrorClass.getErrorMessages().contains("Connection refused")) 
						{
							ErrorClass.errorMessage = "";
							error_msg_01 ="Network unreachable . Please Try again later" ;
						}
					} else 
					{
						
					/****************	TAKE BACKUP **************/
						if (MasterLibraryFunction.IsNullOrEmpty(response)) 
						{
							JSONArray json = null;
							json = new JSONArray(response);
							Object item = null;
							item = json;
							totaldownload = 0 ;
							long total = 0;
		                    int progress = 0;
		                    count = 0;
							if (item instanceof JSONArray) 
							{
								JSONArray venues = null;
								venues = json ;
								consumer_json_arr = venues ;
								mProgressDialog.setMax((int) venues.length());
								 logger.error(" *********************   >>>>>> venues.length() :"+venues.length());
							if (venues.length() > 0) {
								db_helper = new DBTariffHandler(getApplicationContext());
								db_helper.openToWrite();
								db_helper.deleteAll();
								db_helper.close();
							}
							 logger.debug(" *********************   Start  ******************** ");
								for (int i = 0; i < venues.length(); i++)
								{
									total =  count;
									// temp = total;
									progress_temp = (int) ((int) total * 100 / venues.length());
									//logger.debug("SendRequest.length venues "+venues.length());
									
									String[] val = new String[2];
									val[0]=""+total;
									val[1] = ""+progress_temp;
										
									JSONObject obj = null;
									//String consumerno = null;
									//consumerno = venues.getJSONObject(i).optString("CONSUMER_SC_NO").trim();
									//String name = null;
									try 
									{
										obj = consumer_json_arr.getJSONObject(i);
										String temp = "0";
//*  //if (jobject.has("BMD_PENALITY")) 
										String KEY_TDATE  = "0";//= obj.getString("tdate");
										if (obj.has("tdate"))
										KEY_TDATE = obj.getString("tdate");



									String KEY_TARIFFCODE = "0";
									if (obj.has("tariffcode"))
										KEY_TARIFFCODE = obj.getString("tariffcode");
									
									String KEY_TARIFFDESC =  "0";
									if (obj.has("tariffdesc"))
									 KEY_TARIFFDESC = obj.getString("tariffdesc");
									
									String KEY_TARIFFDCB = "0";
									if (obj.has("tariffdcb"))
									 KEY_TARIFFDCB = obj.getString("tariffdcb");
									
									
									String KEY_ACCOUNTCODE ="0";
									if (obj.has("accountcode"))
										 KEY_ACCOUNTCODE =	obj	.getString("accountcode");
									
									String KEY_TARIFFSC = "0";
									if (obj.has("tariffsc"))
										KEY_TARIFFSC = obj.getString("tariffsc");
									
									String KEY_VOLTAGECLASS = "0";
									if (obj.has("voltageclass"))
									 KEY_VOLTAGECLASS = obj	.getString("voltageclass");
									
									
									String KEY_FEC = "0";

									temp = obj.optString("fec");
									if (temp == null || temp == "") {
										KEY_FEC = "0";
									} else {
										KEY_FEC = temp;
									}

											// String KEY_TAX = obj.getString( "TAX" ) ;
											String KEY_TAX = " ";
											String object = obj.optString("tax");
											if (object == null || object == "") {
												KEY_TAX = "0";
											} else {
												KEY_TAX = obj.getString("tax");

											}
											
											String KEY_FCSLAB1 = "0" ;//obj.getString("fcslab1");
										if (obj.has("fcslab1"))
											KEY_FCSLAB1 = obj.getString("fcslab1");
											
											String KEY_FCRATE1 = "0" ;//obj.getString("fcrate1");
											if (obj.has("fcrate1"))
												KEY_FCRATE1 = obj.getString("fcrate1");
											
											String KEY_FCSLAB2 = "0";
											if (obj.has("fcslab2"))
											 KEY_FCSLAB2 = obj.getString("fcslab2");
											
											
											
											
											
											String KEY_FCRATE2;
											String fcr2 = obj.optString("fcrate2");
											if (fcr2 == null || fcr2 == "") {
												KEY_FCRATE2 = "0";
											} else {
												KEY_FCRATE2 = fcr2;
											}

											// String KEY_FCSLAB3 = obj.getString(
											// "FCSLAB3" ) ;
											String KEY_FCSLAB3;
											String fcr3 = obj.optString("fcslab3");
											if (fcr3 == null || fcr3 == "") {
												KEY_FCSLAB3 = "0";
											} else {
												KEY_FCSLAB3 = fcr3;
											}
											// String KEY_FCRATE3 = obj.getString(
											// "FCRATE3" ) ;
											String KEY_FCRATE3;
											String fcrr3 = obj.optString("fcrate3");
											if (fcrr3 == null || fcrr3 == "") {
												KEY_FCRATE3 = "0";
											} else {
												KEY_FCRATE3 = fcrr3;
											}

											String KEY_FCSLAB4;
											temp = obj.optString("fcslab4");
											if (temp == null || temp == "") {
												KEY_FCSLAB4 = "0";
											} else {
												KEY_FCSLAB4 = temp;
											}
											temp = "0";
											String KEY_FCRATE4;
											temp = obj.optString("fcrate4");
											if (temp == null || temp == "") {
												KEY_FCRATE4 = "0";
											} else {
												KEY_FCRATE4 = temp;
											}
											temp = "0";
											String KEY_ECSLAB1;
											temp = obj.optString("ecslab1");
											if (temp == null || temp == "") {
												KEY_ECSLAB1 = "0";
											} else {
												KEY_ECSLAB1 = obj.optString("ecslab1");
												System.out
														.println("ecslb111111******** "
																+ KEY_ECSLAB1);
											}
											temp = "0";
											String KEY_ECRATE1;
											temp = obj.optString("ecrate1");
											if (temp == null || temp == "") {
												KEY_ECRATE1 = "0";
											} else {
												KEY_ECRATE1 = obj.optString("ecrate1");
												System.out
														.println("KEY_ECRATE1111111******** "
																+ KEY_ECRATE1);
											}
											temp = "0";
											String KEY_ECSLAB2;
											temp = obj.optString("ecslab2");

											if (temp == null || temp == "") {
												KEY_ECSLAB2 = "0";
											} else {
												KEY_ECSLAB2 = obj.optString("ecslab2");
												;
											}
											temp = "0";
											String KEY_ECRATE2;
											temp = obj.optString("ecrate2");
											if (temp == null || temp == "") {
												KEY_ECRATE2 = "0";
											} else {
												KEY_ECRATE2 = obj.optString("ecrate2");
												;
											}
											temp = "0";
											String KEY_ECSLAB3;
											temp = obj.optString("ecslab3");
											if (temp == null || temp == "") {
												KEY_ECSLAB3 = "0";
											} else {
												KEY_ECSLAB3 = obj.optString("ecslab3");
												;
											}
											temp = "0";
											String KEY_ECRATE3;
											temp = obj.optString("ecrate3");
											if (temp == null || temp == "") {
												KEY_ECRATE3 = "0";
											} else {
												KEY_ECRATE3 = obj.optString("ecrate3");
												;
											}
											temp = "0";
											String KEY_ECSLAB4;
											temp = obj.optString("ecslab4");
											if (temp == null || temp == "") {
												KEY_ECSLAB4 = "0";
											} else {
												KEY_ECSLAB4 = obj.optString("ecslab4");
												;
											}
											temp = "0";
											String KEY_ECRATE4;
											temp = obj.optString("ecrate4");
											if (temp == null || temp == "") {
												KEY_ECRATE4 = "0";
											} else {
												KEY_ECRATE4 = obj.optString("ecrate4");
												;
											}
											temp = "0";
											String KEY_ECSLAB5;
											temp = obj.optString("ecslab5");
											if (temp == null || temp == "") {
												KEY_ECSLAB5 = "0";
											} else {
												KEY_ECSLAB5 = obj.optString("ecslab5");
											}
											temp = "0";
											String KEY_ECRATE5;
											temp = obj.optString("ecrate5");
											if (temp == null || temp == "") {
												KEY_ECRATE5 = "0";
											} else {
												KEY_ECRATE5 = obj.optString("ecrate5");
											}
											temp = "0";
											String KEY_ECSLAB6;
											temp = obj.optString("ecslab6");
											if (temp == null || temp == "") {
												KEY_ECSLAB6 = "0";
											} else {
												KEY_ECSLAB6 = obj.optString("ecslab6");
											}
											temp = "0";
											String KEY_ECRATE6;
											temp = obj.optString("ecrate6");
											if (temp == null || temp == "") {
												KEY_ECRATE6 = "0";
											} else {
												KEY_ECRATE6 = obj.optString("ecrate6");
											}
											temp = "0";
											String KEY_PFCODE;
											temp = obj.optString("pfcode");
											if (temp == null || temp == "") {
												KEY_PFCODE = " ";
											} else {
												KEY_PFCODE = obj.optString("pfcode");
											}
											temp = "0";
											String KEY_FCREDUCTION;
											temp = obj.optString("fcreduction");
											if (temp == null || temp == "") {
												KEY_FCREDUCTION = "0";
											} else {
												KEY_FCREDUCTION = obj
														.optString("fcreduction");
											}
											temp = "0";
											String KEY_ECREDUCTION;
											temp = obj.optString("ecreduction");
											if (temp == null || temp == "") {
												KEY_ECREDUCTION = "0";
											} else {
												KEY_ECREDUCTION = obj
														.optString("ecreduction");
											}
											temp = "0";
											String KEY_FCMAX;
											temp = obj.optString("fcmax");
											if (temp == null || temp == "") {
												KEY_FCMAX = "0";
											} else {
												KEY_FCMAX = obj.optString("fcmax");
											}
											temp = "0";
											String KEY_ECMAX;
											temp = obj.optString("ecmax");
											if (temp == null || temp == "") {
												KEY_ECMAX = "0";
											} else {
												KEY_ECMAX = obj.optString("ecmax");
											}
											temp = "0";
											String KEY_CALFC;
											temp = obj.optString("calfc");
											if (temp == null || temp == "") {
												KEY_CALFC = "0";
											} else {
												KEY_CALFC = obj.optString("calfc");
											}
											temp = "0";
											String KEY_CALEC;
											temp = obj.optString("calec");
											if (temp == null || temp == "") {
												KEY_CALEC = "0";
											} else {
												KEY_CALEC = obj.optString("calec");
											}
											temp = "0";
											String KEY_METERINSTALLATION;
											temp = obj.optString("meterinstallation");
											if (temp == null || temp == "") {
												KEY_METERINSTALLATION = "0";
											} else {
												KEY_METERINSTALLATION = obj
														.optString("meterinstallation");
											}
											temp = "0";
											String KEY_REMARKS;
											temp = obj.optString("remarks");
											if (temp == null || temp == "") {
												KEY_REMARKS = " ";
											} else {
												KEY_REMARKS = obj.optString("remarks");
											}
											temp = "0";
											String KEY_CBTARIFF;
											temp = obj.optString("cbtariff");
											if (temp == null || temp == "") {
												KEY_CBTARIFF = " ";
											} else {
												KEY_CBTARIFF = obj
														.optString("cbtariff");
											}
											temp = "0";
											String KEY_RECTARIFF;
											temp = obj.optString("rectariff");
											if (temp == null || temp == "") {
												KEY_RECTARIFF = " ";
											} else {
												KEY_RECTARIFF = obj
														.optString("rectariff");
												;
											}

											temp = "0";
											String KEY_PFTARIFF;
											temp = obj.optString("pftariff");
											if (temp == null || temp == "") {
												KEY_PFTARIFF = " ";
											} else {
												KEY_PFTARIFF = obj
														.optString("pftariff");
												;
											}
											temp = "0";
											String KEY_EDPENALTY;
											temp = obj.optString("edpenalty");
											if (temp == null || temp == "") {
												KEY_EDPENALTY = " ";
											} else {
												KEY_EDPENALTY = obj
														.optString("edpenalty");
												;
											}
											temp = "0";

											String KEY_INTRESTRATE;
											temp = obj.optString("intrestrate");
											if (temp == null || temp == "") {
												KEY_INTRESTRATE = " ";
											} else {
												KEY_INTRESTRATE = obj
														.optString("intrestrate");
											}

											temp = "0";
											String KEY_USERNAME;
											temp = obj.optString("username");
											if (temp == null || temp == "") {
												KEY_USERNAME = " ";
											} else {
												KEY_USERNAME = obj
														.optString("username");
											}
											temp = "0";
											String KEY_DATESTAMP;
											temp = obj.optString("datestamp");
											if (temp == null || temp == "") {
												KEY_DATESTAMP = " ";
											} else {
												KEY_DATESTAMP = obj
														.optString("datestamp");
											}
											temp = "0";

											/*
											 * String KEY_ID ; temp = obj.optString(
											 * "ID" ) ; if(temp == null || temp== "") {
											 * KEY_ID = " "; } else{ KEY_ID = temp ; }
											 */
											String KEY_TARIFFSLNO;
											temp = obj.optString("tariffslno");
											if (temp == null || temp == "") {
												KEY_TARIFFSLNO = " ";
											} else {
												KEY_TARIFFSLNO = obj
														.optString("tariffslno");
											}
											temp = "0";
											String KEY_TARIFFFCBASIS;
											temp = obj.optString("tarifffcbasis");
											if (temp == null || temp == "") {
												KEY_TARIFFFCBASIS = " ";
											} else {
												KEY_TARIFFFCBASIS = obj
														.optString("tarifffcbasis");
											}
											temp = "0";
											logger.debug("   count  : "+ count);
											db_helper = new DBTariffHandler(getApplicationContext());
											db_helper.openToWrite();
											db_helper.insert(KEY_TDATE,
													KEY_TARIFFCODE, KEY_TARIFFDESC,
													KEY_TARIFFDCB, KEY_ACCOUNTCODE,
													KEY_TARIFFSC, KEY_VOLTAGECLASS,
													KEY_FEC, KEY_TAX, KEY_FCSLAB1,
													KEY_FCRATE1, KEY_FCSLAB2,
													KEY_FCRATE2, KEY_FCSLAB3,
													KEY_FCRATE3, KEY_FCSLAB4,
													KEY_FCRATE4, KEY_ECSLAB1,
													KEY_ECRATE1, KEY_ECSLAB2,
													KEY_ECRATE2, KEY_ECSLAB3,
													KEY_ECRATE3, KEY_ECSLAB4,
													KEY_ECRATE4, KEY_ECSLAB5,
													KEY_ECRATE5, KEY_ECSLAB6,
													KEY_ECRATE6, KEY_PFCODE,
													KEY_FCREDUCTION, KEY_ECREDUCTION,
													KEY_FCMAX, KEY_ECMAX, KEY_CALFC,
													KEY_CALEC, KEY_METERINSTALLATION,
													KEY_REMARKS, KEY_CBTARIFF,
													KEY_RECTARIFF, KEY_PFTARIFF,
													KEY_EDPENALTY, KEY_INTRESTRATE,
													KEY_USERNAME, KEY_DATESTAMP,
													KEY_TARIFFSLNO, KEY_TARIFFFCBASIS);
											db_helper.close();

										} catch (JSONException e) {
											FilePropertyManager.appendLog(Log.getStackTraceString(e));
											e.printStackTrace();
											}
										totaldownload = totaldownload + 1;
										publishProgress(val);
										if ( progress != progress_temp) 
										{
											progress = progress_temp ;
										}
										count++;
										
									}
								
								logger.debug(" *********************   END  ******************** ");
								} else {
									JSONObject obj = null;
									obj = (JSONObject) item;
									try {
										String temp = "0";

										String KEY_TDATE  = "0";//= obj.getString("tdate");
										if (obj.has("tdate"))
										KEY_TDATE = obj.getString("tdate");



									String KEY_TARIFFCODE = "0";
									if (obj.has("tariffcode"))
										KEY_TARIFFCODE = obj.getString("tariffcode");
									
									String KEY_TARIFFDESC =  "0";
									if (obj.has("tariffdesc"))
									 KEY_TARIFFDESC = obj.getString("tariffdesc");
									
									String KEY_TARIFFDCB = "0";
									if (obj.has("tariffdcb"))
									 KEY_TARIFFDCB = obj.getString("tariffdcb");
									
									
									String KEY_ACCOUNTCODE ="0";
									if (obj.has("accountcode"))
										 KEY_ACCOUNTCODE =	obj	.getString("accountcode");
									
									String KEY_TARIFFSC = "0";
									if (obj.has("tariffsc"))
										KEY_TARIFFSC = obj.getString("tariffsc");
									
									String KEY_VOLTAGECLASS = "0";
									if (obj.has("voltageclass"))
									 KEY_VOLTAGECLASS = obj	.getString("voltageclass");
									
										String KEY_FEC = " ";
										temp = obj.optString("fec");
										if (temp == null || temp == "") {
											KEY_FEC = "0";
										} else {
											KEY_FEC = temp;
										}

										// String KEY_TAX = obj.getString( "TAX" ) ;
										String KEY_TAX = " ";
										String object = obj.optString("tax");
										if (object == null || object == "") {
											KEY_TAX = "0";
										} else {
											KEY_TAX = obj.getString("tax");

										}
										
										
										String KEY_FCSLAB1 = "0" ;//obj.getString("fcslab1");
										if (obj.has("fcslab1"))
											KEY_FCSLAB1 = obj.getString("fcslab1");
											
											String KEY_FCRATE1 = "0" ;//obj.getString("fcrate1");
											if (obj.has("fcrate1"))
												KEY_FCRATE1 = obj.getString("fcrate1");
											
											String KEY_FCSLAB2 = "0";
											if (obj.has("fcslab2"))
											 KEY_FCSLAB2 = obj.getString("fcslab2");
											
											
										String KEY_FCRATE2;
										String fcr2 = obj.optString("fcrate2");
										if (fcr2 == null || fcr2 == "") {
											KEY_FCRATE2 = "0";
										} else {
											KEY_FCRATE2 = fcr2;
										}

										// String KEY_FCSLAB3 = obj.getString(
										// "FCSLAB3" ) ;
										String KEY_FCSLAB3;
										String fcr3 = obj.optString("fcslab3");
										if (fcr3 == null || fcr3 == "") {
											KEY_FCSLAB3 = "0";
										} else {
											KEY_FCSLAB3 = fcr3;
										}
										// String KEY_FCRATE3 = obj.getString(
										// "FCRATE3" ) ;
										String KEY_FCRATE3;
										String fcrr3 = obj.optString("fcrate3");
										if (fcrr3 == null || fcrr3 == "") {
											KEY_FCRATE3 = "0";
										} else {
											KEY_FCRATE3 = fcrr3;
										}

										String KEY_FCSLAB4;
										temp = obj.optString("fcslab4");
										if (temp == null || temp == "") {
											KEY_FCSLAB4 = "0";
										} else {
											KEY_FCSLAB4 = temp;
										}
										temp = "0";
										String KEY_FCRATE4;
										temp = obj.optString("fcrate4");
										if (temp == null || temp == "") {
											KEY_FCRATE4 = "0";
										} else {
											KEY_FCRATE4 = temp;
										}
										temp = "0";
										String KEY_ECSLAB1;
										temp = obj.optString("ecslab1");
										if (temp == null || temp == "") {
											KEY_ECSLAB1 = "0";
										} else {
											KEY_ECSLAB1 = obj.optString("ecslab1");
											System.out
													.println("ecslb111111******** "
															+ KEY_ECSLAB1);
										}
										temp = "0";
										String KEY_ECRATE1;
										temp = obj.optString("ecrate1");
										if (temp == null || temp == "") {
											KEY_ECRATE1 = "0";
										} else {
											KEY_ECRATE1 = obj.optString("ecrate1");
											System.out
													.println("KEY_ECRATE1111111******** "
															+ KEY_ECRATE1);
										}
										temp = "0";
										String KEY_ECSLAB2;
										temp = obj.optString("ecslab2");

										if (temp == null || temp == "") {
											KEY_ECSLAB2 = "0";
										} else {
											KEY_ECSLAB2 = obj.optString("ecslab2");
											;
										}
										temp = "0";
										String KEY_ECRATE2;
										temp = obj.optString("ecrate2");
										if (temp == null || temp == "") {
											KEY_ECRATE2 = "0";
										} else {
											KEY_ECRATE2 = obj.optString("ecrate2");
											;
										}
										temp = "0";
										String KEY_ECSLAB3;
										temp = obj.optString("ecslab3");
										if (temp == null || temp == "") {
											KEY_ECSLAB3 = "0";
										} else {
											KEY_ECSLAB3 = obj.optString("ecslab3");
											;
										}
										temp = "0";
										String KEY_ECRATE3;
										temp = obj.optString("ecrate3");
										if (temp == null || temp == "") {
											KEY_ECRATE3 = "0";
										} else {
											KEY_ECRATE3 = obj.optString("ecrate3");
											;
										}
										temp = "0";
										String KEY_ECSLAB4;
										temp = obj.optString("ecslab4");
										if (temp == null || temp == "") {
											KEY_ECSLAB4 = "0";
										} else {
											KEY_ECSLAB4 = obj.optString("ecslab4");
											;
										}
										temp = "0";
										String KEY_ECRATE4;
										temp = obj.optString("ecrate4");
										if (temp == null || temp == "") {
											KEY_ECRATE4 = "0";
										} else {
											KEY_ECRATE4 = obj.optString("ecrate4");
											;
										}
										temp = "0";
										String KEY_ECSLAB5;
										temp = obj.optString("ecslab5");
										if (temp == null || temp == "") {
											KEY_ECSLAB5 = "0";
										} else {
											KEY_ECSLAB5 = obj.optString("ecslab5");
										}
										temp = "0";
										String KEY_ECRATE5;
										temp = obj.optString("ecrate5");
										if (temp == null || temp == "") {
											KEY_ECRATE5 = "0";
										} else {
											KEY_ECRATE5 = obj.optString("ecrate5");
										}
										temp = "0";
										String KEY_ECSLAB6;
										temp = obj.optString("ecslab6");
										if (temp == null || temp == "") {
											KEY_ECSLAB6 = "0";
										} else {
											KEY_ECSLAB6 = obj.optString("ecslab6");
										}
										temp = "0";
										String KEY_ECRATE6;
										temp = obj.optString("ecrate6");
										if (temp == null || temp == "") {
											KEY_ECRATE6 = "0";
										} else {
											KEY_ECRATE6 = obj.optString("ecrate6");
										}
										temp = "0";
										String KEY_PFCODE;
										temp = obj.optString("pfcode");
										if (temp == null || temp == "") {
											KEY_PFCODE = " ";
										} else {
											KEY_PFCODE = obj.optString("pfcode");
										}
										temp = "0";
										String KEY_FCREDUCTION;
										temp = obj.optString("fcreduction");
										if (temp == null || temp == "") {
											KEY_FCREDUCTION = "0";
										} else {
											KEY_FCREDUCTION = obj
													.optString("fcreduction");
										}
										temp = "0";
										String KEY_ECREDUCTION;
										temp = obj.optString("ecreduction");
										if (temp == null || temp == "") {
											KEY_ECREDUCTION = "0";
										} else {
											KEY_ECREDUCTION = obj
													.optString("ecreduction");
										}
										temp = "0";
										String KEY_FCMAX;
										temp = obj.optString("fcmax");
										if (temp == null || temp == "") {
											KEY_FCMAX = "0";
										} else {
											KEY_FCMAX = obj.optString("fcmax");
										}
										temp = "0";
										String KEY_ECMAX;
										temp = obj.optString("ecmax");
										if (temp == null || temp == "") {
											KEY_ECMAX = "0";
										} else {
											KEY_ECMAX = obj.optString("ecmax");
										}
										temp = "0";
										String KEY_CALFC;
										temp = obj.optString("calfc");
										if (temp == null || temp == "") {
											KEY_CALFC = "0";
										} else {
											KEY_CALFC = obj.optString("calfc");
										}
										temp = "0";
										String KEY_CALEC;
										temp = obj.optString("calec");
										if (temp == null || temp == "") {
											KEY_CALEC = "0";
										} else {
											KEY_CALEC = obj.optString("calec");
										}
										temp = "0";
										String KEY_METERINSTALLATION;
										temp = obj.optString("meterinstallation");
										if (temp == null || temp == "") {
											KEY_METERINSTALLATION = "0";
										} else {
											KEY_METERINSTALLATION = obj
													.optString("meterinstallation");
										}
										temp = "0";
										String KEY_REMARKS;
										temp = obj.optString("remarks");
										if (temp == null || temp == "") {
											KEY_REMARKS = " ";
										} else {
											KEY_REMARKS = obj.optString("remarks");
										}
										temp = "0";
										String KEY_CBTARIFF;
										temp = obj.optString("cbtariff");
										if (temp == null || temp == "") {
											KEY_CBTARIFF = " ";
										} else {
											KEY_CBTARIFF = obj
													.optString("cbtariff");
										}
										temp = "0";
										String KEY_RECTARIFF;
										temp = obj.optString("rectariff");
										if (temp == null || temp == "") {
											KEY_RECTARIFF = " ";
										} else {
											KEY_RECTARIFF = obj
													.optString("rectariff");
											;
										}

										temp = "0";
										String KEY_PFTARIFF;
										temp = obj.optString("pftariff");
										if (temp == null || temp == "") {
											KEY_PFTARIFF = " ";
										} else {
											KEY_PFTARIFF = obj
													.optString("pftariff");
											;
										}
										temp = "0";
										String KEY_EDPENALTY;
										temp = obj.optString("edpenalty");
										if (temp == null || temp == "") {
											KEY_EDPENALTY = " ";
										} else {
											KEY_EDPENALTY = obj
													.optString("edpenalty");
											;
										}
										temp = "0";

										String KEY_INTRESTRATE;
										temp = obj.optString("intrestrate");
										if (temp == null || temp == "") {
											KEY_INTRESTRATE = " ";
										} else {
											KEY_INTRESTRATE = obj
													.optString("intrestrate");
										}

										temp = "0";
										String KEY_USERNAME;
										temp = obj.optString("username");
										if (temp == null || temp == "") {
											KEY_USERNAME = " ";
										} else {
											KEY_USERNAME = obj
													.optString("username");
										}
										temp = "0";
										String KEY_DATESTAMP;
										temp = obj.optString("datestamp");
										if (temp == null || temp == "") {
											KEY_DATESTAMP = " ";
										} else {
											KEY_DATESTAMP = obj
													.optString("datestamp");
										}
										temp = "0";

										/*
										 * String KEY_ID ; temp = obj.optString(
										 * "ID" ) ; if(temp == null || temp== "") {
										 * KEY_ID = " "; } else{ KEY_ID = temp ; }
										 */
										String KEY_TARIFFSLNO;
										temp = obj.optString("tariffslno");
										if (temp == null || temp == "") {
											KEY_TARIFFSLNO = " ";
										} else {
											KEY_TARIFFSLNO = obj
													.optString("tariffslno");
										}
										temp = "0";
										String KEY_TARIFFFCBASIS;
										temp = obj.optString("tarifffcbasis");
										if (temp == null || temp == "") {
											KEY_TARIFFFCBASIS = " ";
										} else {
											KEY_TARIFFFCBASIS = obj
													.optString("tarifffcbasis");
										}
										temp = "0";

										db_helper = new DBTariffHandler(getApplicationContext());
										db_helper.openToWrite();
										db_helper.insert(KEY_TDATE,
												KEY_TARIFFCODE, KEY_TARIFFDESC,
												KEY_TARIFFDCB, KEY_ACCOUNTCODE,
												KEY_TARIFFSC, KEY_VOLTAGECLASS,
												KEY_FEC, KEY_TAX, KEY_FCSLAB1,
												KEY_FCRATE1, KEY_FCSLAB2,
												KEY_FCRATE2, KEY_FCSLAB3,
												KEY_FCRATE3, KEY_FCSLAB4,
												KEY_FCRATE4, KEY_ECSLAB1,
												KEY_ECRATE1, KEY_ECSLAB2,
												KEY_ECRATE2, KEY_ECSLAB3,
												KEY_ECRATE3, KEY_ECSLAB4,
												KEY_ECRATE4, KEY_ECSLAB5,
												KEY_ECRATE5, KEY_ECSLAB6,
												KEY_ECRATE6, KEY_PFCODE,
												KEY_FCREDUCTION, KEY_ECREDUCTION,
												KEY_FCMAX, KEY_ECMAX, KEY_CALFC,
												KEY_CALEC, KEY_METERINSTALLATION,
												KEY_REMARKS, KEY_CBTARIFF,
												KEY_RECTARIFF, KEY_PFTARIFF,
												KEY_EDPENALTY, KEY_INTRESTRATE,
												KEY_USERNAME, KEY_DATESTAMP,

												KEY_TARIFFSLNO, KEY_TARIFFFCBASIS);
										db_helper.close();
									} catch (JSONException e1) {
										
										FilePropertyManager.appendLog(Log.getStackTraceString(e1));
										e1.printStackTrace(); 
								}
									 totaldownload = totaldownload + 1;
								}
						} else 
						{
							error_msg_01 ="No Data to download" ; 
						}
						db_helper.close();
						mProgressDialog.dismiss();
					}
			} catch (Exception e) {
				e.printStackTrace();
				FilePropertyManager.appendLog(Log.getStackTraceString(e));
			}
	/*************	delete this if else block once done */
		return null;
		}
		protected void onProgressUpdate(String... values) {
			mProgressDialog.setProgress(Integer.parseInt(values[0]));
			mProgressDialog.setSecondaryProgress(Integer.parseInt(values[0]));
		    System.out.println("total"+values[0]);
		    DecimalFormat df = new DecimalFormat("###.##");
		    System.out.println("Mega bytes mbs"+df.format(((Float.valueOf(values[0])/1048576)*100)/100));
		    mProgressDialog.setMessage("Downloading file.. "+df.format((Float.valueOf(values[0])))+" of "+df.format(Float.valueOf(consumer_json_arr.length())));
		}
		 @Override
	        protected void onPostExecute(String unused) {
			 mProgressDialog.dismiss();
			// error_msg_01 = "NOERROR";
			 if( error_msg_01.trim().equalsIgnoreCase("NOERROR") )
			 {
				if(count > 0) {
				 if(count == totaldownload)
				 {
					  new AlertDialog.Builder(MainActivity.this)
					 					   .setTitle("Info")
					 					   .setMessage("Successfully Updated Tariff")
					 					   .setPositiveButton("OK",null).show();
				 }
				}else{
					
					  new AlertDialog.Builder(MainActivity.this)
					   .setTitle("Info")
					   .setMessage("N0 Data to download")
					   .setPositiveButton("OK",null).show();
				}
				 
			 }else{
				 new AlertDialog.Builder(MainActivity.this)
				   .setTitle("Info")
				   .setMessage(error_msg_01)
				   .setPositiveButton("OK",null).show();
			 }
		 }
	}
	
	/**
	 * @author Guru
	 * @date 05-Nov-2014
	 * @category Date Validation for mobile date 
	 * 
	 */
	public class performBackgroundTaskgetServerDateToUploadBilledData extends AsyncTask<Void, Void, Void> {
		private ProgressDialog Dialog = new ProgressDialog(MainActivity.this);
	    boolean mobileDateMatch = false;
	    String serverDate = null ;
	    Sendrequest requestToServer ;
	    InputStream str=null ;
		protected void onPreExecute() {
		    super.onPreExecute();
			Dialog.setMessage("Please wait...");
			Dialog.setCancelable(false);
			Dialog.show();
		}
		
		
		protected Void doInBackground(Void... params) {
			
			requestToServer  =  new Sendrequest();
			str = requestToServer.sendrequest("updatefromlocal/getserverdate");
			logger.debug("str ******* : "+str);
			//Dialog.dismiss();
			return null;
		}
		protected void onPostExecute(Void unused) {
			if(str == null)
			{
				Dialog.dismiss();
				 new AlertDialog.Builder(MainActivity.this)
				.setTitle("Info")
				.setMessage(
						"Sorry, server down.")
				.setPositiveButton("OK", null).show();
				
			}
			else{
				Dialog.dismiss();
				 serverDate =requestToServer.read(str);
				
				logger.debug("serverDate  ********"+serverDate);
				
				Date dateServer = null ;
				Date dateMobile = null ;
				if(!serverDate.contains("<html>"))
				{
					try {
						SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
						 dateServer = format.parse(serverDate);
						Calendar calendar = Calendar.getInstance();
						 dateMobile =format.parse(format.format( calendar.getTime()));
						
					}catch (Exception e) {
						Dialog.dismiss();
						 new AlertDialog.Builder(MainActivity.this)
						.setTitle("Error..!")
						.setMessage(
								"Some error occurred while connecting to server.")
						.setPositiveButton("OK", null).show();
						
					}
					
					
	                 if  ( dateServer.compareTo(dateMobile) == 0 ){
	                	 Dialog.dismiss();
	                	 logger.debug("btn_uploadd >>  performBackgroundTaskupload Started" , new SimpleDateFormat("dd-MM-yyyy hh:mm:ss" ).format(new Date()));
	              	 logger.debug("**********************Update happend***************************************");
	            new performBackgroundTaskupload().execute();
					
	                	 
	                 }else {
	                	 Dialog.dismiss();
	                	
	                		 new AlertDialog.Builder(MainActivity.this)
							.setTitle("Info")
							.setMessage(
									"Mobile date has been changed. please set the date on device and then continue ")
							.setPositiveButton("Set Date",	
									new DialogInterface.OnClickListener() 
							{
								public void onClick(DialogInterface arg0,int arg1) 
								{
									startActivity(new Intent(android.provider.Settings.ACTION_DATE_SETTINGS));
									
								}
							})
						.show();
	                		 
	                 }
				}
				else
				{
					Dialog.dismiss();
					 new AlertDialog.Builder(MainActivity.this)
					.setTitle("Error..!")
					.setMessage(
							"Sorry, server down / some error occurred while connecting server.")
					.setPositiveButton("OK", null).show();
				
				}
			
				
			}
		
		}
		
		
	}
	
	/**
	 * @author Guru
	 * @date 05-Nov-2014
	 * @category Updating billed data from local to server 
	 */
	public class performBackgroundTaskupload extends AsyncTask<Void, Void, Void> 
	{
		int count_update = 0 ;
		private ProgressDialog Dialog = new ProgressDialog(	MainActivity.this);
		DatabaseHandler databaseHandler ;
		Sendrequest send ;
		String responsefromserver ;
		String err_msg_show = "OK";
		protected void onPreExecute() 
		{
			Dialog.setMessage("Uploading Please wait...");
			Dialog.setCancelable(false);
			Dialog.show();
		}

		protected Void doInBackground(Void... params) {
			
			try {
				
				TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
				String   imeino = tm.getDeviceId();
				databaseHandler = new DatabaseHandler(getApplicationContext());
				/** getting rrno,billed and unbilled count */
				databaseHandler.openToRead();
				
				List<UploadHandler> download = databaseHandler.getBilledDataToUpload("10" , imeino);
				databaseHandler.close() ;
				JSONArray ja1 = new JSONArray();
				//JSONObject sdoonj = new JSONObject();
				for (int i = 0;  ( i < download.size()  ) ; i++) {
					ja1.put(download.get(i).getJSONObject());
				}
				String url1 = "updatefromlocal/billeddata";
				send = new Sendrequest();
				responsefromserver = send.uploadtoServerfromlocaldb(url1, ja1);
				
				System.out.println("Jsonnnnnnnnnnnnnnnnnnnnnn : \n"+ja1);
				
				logger.debug("Response from server" + responsefromserver);
				
			} catch (Exception e) {
				e.printStackTrace();
			}

			return null;
		}

		protected void onPostExecute(Void unused) {

			try{
				count_update = 0 ;
			if (MasterLibraryFunction.IsNullOrEmpty(ErrorClass.getErrorMessages()))
			{
			//	ERROR MSG 
				if (ErrorClass.getErrorMessages().contains("timed out")) 
				{
					ErrorClass.errorMessage = "";
					err_msg_show = "Connection timed out. Server might be shut down." ;
				} else if (ErrorClass.getErrorMessages().contains("java.net.SocketTimeoutException"))
				{
					ErrorClass.errorMessage = "";
					err_msg_show = "Socket timed out";
					
					
					
				} else if (ErrorClass.getErrorMessages().contains("Connection refused")) 
				{
					ErrorClass.errorMessage = "";
					err_msg_show ="Network unreachable . Please Try again later" ;
					 
				}
			//	mProgressDialog.dismiss();

			}else{
				if (responsefromserver != null) {
					
					JSONArray arr = null;
					arr = new JSONArray(responsefromserver);
					
					databaseHandler.openToWrite();
					JSONObject downloadjson = null;
					downloadjson = arr.getJSONObject(0);
					Iterator downloaditer = downloadjson.keys();
					while (downloaditer.hasNext()) {
						
						String consumerno = (String) downloaditer.next();
						String updatedstatus = null;
						updatedstatus = downloadjson.getString(consumerno);
						
						
						if(updatedstatus.trim().equalsIgnoreCase("1"))  {
							
							count_update++;
						}
						databaseHandler.setServerUpdateResponse(consumerno,	updatedstatus);
					}
					databaseHandler.close();
				
			}else{
				err_msg_show = "Uploading failed, try again later" ;
			}
			}
		} catch (Exception e) {
			logger.error( Log.getStackTraceString(e));
			e.printStackTrace();
		}
				Dialog.dismiss();
				 logger.debug("btn_uploadd >>  performBackgroundTaskupload Completed" , new SimpleDateFormat("dd-MM-yyyy hh:mm:ss" ).format(new Date()));
				if(err_msg_show.equalsIgnoreCase("OK")){
					
					String msg_up = "NOMSG";
					if(count_update == 0 || count_update < 0 )
					{
						msg_up = "Records not uploaded to server"; 
						new AlertDialog.Builder(MainActivity.this)
						   .setTitle("Info")
						   .setCancelable(false)
						   .setMessage(msg_up)
						   .setPositiveButton("OK",new DialogInterface.OnClickListener()
						   {
							   public void onClick(DialogInterface dialog,int which) 
							   {
								   setDashboard();
							   }
						   }).show();
					}else{
						msg_up = "Updated "+count_update+" records";
						checkAndContinueuploading(msg_up ,count_update );
					}
				 }else{
					  new AlertDialog.Builder(MainActivity.this)
					   .setTitle("Info")
					   .setMessage(err_msg_show)
					   .setCancelable(false)
					   .setPositiveButton("OK",new DialogInterface.OnClickListener()
					   {
						   public void onClick(DialogInterface dialog,int which) 
						   {
							   setDashboard();
						   }
					   }).show();
				 }
				
	}
	}
	
	/**
	 * @author Guru
	 * @date 05-Nov-2014
	 * @category Upload_check_function
	 * @param msg
	 */
	public void checkAndContinueuploading(String msg , int presentCout){
		int count_status = 0 ;
		totalUpdateCount  += presentCout ; 
		try {
			
		
		
		if(haveNetworkConnection()){
			DatabaseHandler update_helper = new DatabaseHandler(getApplicationContext());
			update_helper.openToRead();
			count_status = Integer.parseInt(update_helper.GetBilledcountfromlocal().trim());
			update_helper.close() ;
			if(count_status > 0)
			{
				 logger.debug("btn_uploadd >>  performBackgroundTaskupload Started loop : " , new SimpleDateFormat("dd-MM-yyyy hh:mm:ss" ).format(new Date()));
				  new performBackgroundTaskupload().execute();
			}else
			{
				
				msg = "Updated "+totalUpdateCount+" records";
				new AlertDialog.Builder(MainActivity.this)
				   .setTitle("Info")
				   .setCancelable(false)
				   .setMessage(msg)
				   .setPositiveButton("OK",new DialogInterface.OnClickListener()
				   {
					   public void onClick(DialogInterface dialog,int which) 
					   {
						   setDashboard();
					   }
				   }).show();
			}
		}else{
			
			new AlertDialog.Builder(MainActivity.this)
			   .setTitle("Info")
			   .setCancelable(false)
			   .setMessage(msg)
			   .setPositiveButton("OK",new DialogInterface.OnClickListener()
			   {
				   public void onClick(DialogInterface dialog,int which) 
				   {
					   setDashboard();
				   }
			   }).show();
		}
		} catch (Exception e) {
			logger.error("checkAndContinueuploading Exception:", e);
		}
		
	}
	
	
	public class TimeCheckTask extends AsyncTask<Void, Void, Void> {
		private ProgressDialog Dialog = new ProgressDialog(MainActivity.this);
		 String versionName = null;
		 Sendrequest send_time ;
		 InputStream int_strm ;
		protected void onPreExecute() {
			Dialog.setMessage("Connecting to server, Please wait...");
			Dialog.setCancelable(false);
			Dialog.show();
		}

		protected Void doInBackground(Void... params) {
			
				
			try
			{
			Date date1 = new Date();
	        logger.debug("mobiledate"+date1.getTime()); 
	        DateFormat serverdate_time  = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	        logger.debug("mobiledate"+date1.getTime()+serverdate_time.format(date1)); 
	        
	        String devicetime=serverdate_time.format(date1);
	        String[] Date_time = devicetime.trim().split(" ");
	        System.out.println(Date_time[0]+Date_time[1]);
	        String devicedate = Date_time[0].trim().replace("/", "@");
	        String devicetime1=Date_time[1];
	        
	       
	    	try {
	    		versionName = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
	    		} catch (NameNotFoundException e) 
	    		{
	    			FilePropertyManager.appendLog(Log.getStackTraceString(e));
	    			versionName = null ;
	    			e.printStackTrace();
	    		}
	    	TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
			String imeino = tm.getDeviceId();
	        
	         send_time = new Sendrequest();
			 //int_strm = send_time.sendrequest("login/getTime/"+devicedate+"/"+devicetime1);
			// int_strm = send_time.sendrequest("device/validate/"+devicedate+"/"+devicetime1+"/"+versionName +"/"+imeino);
			 
			 int_strm = send_time.sendrequest("newdevice/newvalidate/"+devicedate+"/"+devicetime1+"/"+versionName +"/"+imeino+"/"+UtilMaster.mGLogin_SiteCode.trim()+"/"+UtilMaster.mGLogin_MRCode.trim());
			 
			}
			catch (Exception e) {
				logger.error( Log.getStackTraceString(e));
				//FilePropertyManager.appendLog(Log.getStackTraceString(e));
			}
			return null;
		}

		protected void onPostExecute(Void unused) {
			Dialog.dismiss();
			try
			{
			if(int_strm != null)
			{
				String response_time = send_time.read(int_strm);
				if(response_time != null)
				{
					String[] time = response_time.split("@");
					if(time[0].equals("validtime"))
					{
						if(time[2].equalsIgnoreCase("latest"))
						{
							if(time[3].equalsIgnoreCase("reg"))
							{
								
								if(time[4].equalsIgnoreCase("new"))
								{
									
									new AlertDialog.Builder(MainActivity.this)
									   .setTitle("Info")
									    .setMessage("Billed data will be moved to Backup and Erased from main Db \nPress OK to confirm")
									   .setPositiveButton("OK", new DialogInterface.OnClickListener()
									   {
										   
										   public void onClick(DialogInterface dialog,int which) 
										   {
											   new performBackgroundTaskdownload_consumerDate().execute();	
										   }
									   }).setNegativeButton("NO", null)						   
									   .show();
									
									
								}else{
									
									new AlertDialog.Builder(MainActivity.this)
									  .setTitle("Info..!")
									  .setMessage("Sorry your using old version. Please install latest version and try again")
									  .setPositiveButton("OK", new DialogInterface.OnClickListener()
									   {
										   
										   public void onClick(DialogInterface dialog,int which) 
										   {
											   Intent updateapk = new Intent(MainActivity.this,	AndroidautoUpdateActivity.class);
												updateapk.putExtra("apk_version", "CESC.apk" + "/"+ versionName + "/" + Login.server_ip);
												startActivity(updateapk);
										   }
									   })
									  .show();
									
								}
							}else{
								
								new AlertDialog.Builder(MainActivity.this)
								  .setTitle("Info..!")
								  .setMessage("Sorry your using Unauthorized device. Please register device and try again")
								  .setPositiveButton("OK", null)
								  .show();
								
							}
							
							
							
						}else{
							new AlertDialog.Builder(MainActivity.this)
							  .setTitle("Info..!")
							  .setMessage("Sorry Application version which your using is not allowed. Please install latest version and try again")
							  .setPositiveButton("OK", null)
							  .show();
						}
						
					}
					else
					{
					    Date date = new Date(time[1]);
					      String strDateFormat = "HH:mm a";
					      SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
					      System.out.println(sdf.format(date));
						String[] server_date = time[1].split(" ");
					      String _24HourTime = server_date[1];
				           SimpleDateFormat _24HourSDF = new SimpleDateFormat("HH:mm");
				           SimpleDateFormat _12HourSDF = new SimpleDateFormat("hh:mm a");
				           Date _24HourDt = null;
						try {
							_24HourDt = _24HourSDF.parse(_24HourTime);
						} catch (ParseException e) {
							FilePropertyManager.appendLog(Log.getStackTraceString(e));
						
						}
				           System.out.println(_24HourDt);
				           System.out.println(_12HourSDF.format(_24HourDt)); 
				           
						 new AlertDialog.Builder(MainActivity.this)
						.setTitle("Info")
						.setMessage("Please set valid date and time in device .. Server time is "+server_date[0]+" "+_12HourSDF.format(_24HourDt))
						.setPositiveButton("OK", new DialogInterface.OnClickListener() {
							
							public void onClick(DialogInterface dialog, int which) {
								startActivity(new Intent(android.provider.Settings.ACTION_DATE_SETTINGS));
							}
						}).show();
						
					}
				}
			}
			else
			{
				//download.setEnabled(true);
				 setDashboard();
			}
			
			//checkUpdates();
		}
			catch (Exception e) {
				logger.error( Log.getStackTraceString(e));
			//	FilePropertyManager.appendLog(Log.getStackTraceString(e));
			
			}
			
		}

		
	}
	
	
	boolean isUnSycData(){
		boolean b = false ;
		try{
			
		DatabaseHandler update_helper = new DatabaseHandler(getApplicationContext());
		update_helper.openToRead();
		int count_status = Integer.parseInt(update_helper.GetBilledcountfromlocal().trim());
		update_helper.close() ;
		if(count_status > 0)
		{
			b = true ;
		}else
		{
			b = false ;
		}
		}catch(Exception exception){
			logger.error("checkAndContinueuploading Exception:", Log.getStackTraceString(exception));
			//FilePropertyManager.appendLog(Log.getStackTraceString(exception));
		}
		return b;
	
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
}
