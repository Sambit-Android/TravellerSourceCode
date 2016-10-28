package com.bcits.bsmartbilling;

import Utils.Logger;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.SQLException;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.BatteryManager;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.utils.DatabaseHandler;
import com.utils.helper.MemoryCheck;

public class SearchActivity extends Activity {
	static Logger logger ;
	private static String TAG = "SearchActivity";
	TextView txt_unbilled , txt_billed , txt_synch  , title , txt_siteCode;
	EditText edt_consumerNo;
	Button btn_fv, btn_pvs , btn_back , btn_search ;
	RadioButton rBtn_consumerNo , rBtn_meterNo ;
	static String accNo ; 
	Location curLocation;
	FusedLocationService fusedLocationService;
	MasterLibraryFunction libraryFunction ;
	private Boolean locationChanged;
	private LocationManager locMan;
	int levelbattery;
	MyPhoneStateListener    MyListener;
	TelephonyManager        Tel;
	String mbatteryLevel, msignalStrength, mmemoryTotal, mmemoryAvailable;
	MemoryCheck check;
	String deviceInfo;
	//private static final long MINIMUM_DISTANCE_CHANGE_FOR_UPDATES = 1; // in Meters
	//private static final long MINIMUM_TIME_BETWEEN_UPDATES = 30000; // in Milliseconds
	protected LocationManager locationManager;
	int signalStrength11;

	int gpsAllowCount=0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.e(TAG, "In onCreate ");
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.activity_search);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,	R.layout.window_title);


		MyListener   = new MyPhoneStateListener();
		Tel       = ( TelephonyManager )getSystemService(Context.TELEPHONY_SERVICE);
		Tel.listen(MyListener ,PhoneStateListener.LISTEN_SIGNAL_STRENGTHS);

		UtilMaster.mlangitude ="0";
		UtilMaster.mlattitude ="0";

		btn_back 	= (Button)findViewById(R.id.titleBack); 
		title = (TextView )findViewById(R.id.title);
		title.setText(getString(R.string.cus_title_name)+MasterLibraryFunction.getVersion(SearchActivity.this));

		txt_unbilled = (TextView)findViewById(R.id.unbilledvalue);    
		txt_billed = (TextView)findViewById(R.id.billedvalue); 
		txt_synch = (TextView)findViewById(R.id.syncbilledvalue);  
		txt_siteCode = (TextView)findViewById(R.id.sdocodevalue);

		edt_consumerNo = (EditText)findViewById(R.id.accountno); 
		btn_fv = (Button)findViewById(R.id.Buttonforward); 
		btn_pvs = (Button)findViewById(R.id.button1previous); 


		btn_search = (Button)findViewById(R.id.next); 

		rBtn_consumerNo = (RadioButton)findViewById(R.id.acctno); 
		rBtn_meterNo = (RadioButton)findViewById(R.id.mtrno); 
		if(logger == null){
			logger = MasterLibraryFunction.getlogger(getApplicationContext(), TAG);
		}
		logger.info("In Side Oncreate");

		//  fusedLocationService=null;

		fusedLocationService = new FusedLocationService(SearchActivity.this);



		getBatteryPercentage();

	}



	@Override
	protected void onStart() {
		super.onStart();

		if(logger == null){
			logger = MasterLibraryFunction.getlogger(getApplicationContext(), TAG);
		}
		logger.info(TAG, "In onStart ");
		try{


			/*if(UtilMaster.isGpsEnable()){

				getLoc();
			}*/    



			/*checkAccuracy();*/





			BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();    
			if(mBluetoothAdapter!=null)
			{
				if (!mBluetoothAdapter.isEnabled()) {
				}else{ 
					mBluetoothAdapter.disable(); 
				}
			}
			if( (UtilMaster.mGLogin_MRCode.trim().equalsIgnoreCase("")) ||  UtilMaster.mGLogin_SiteCode.trim().equalsIgnoreCase("") ){
				new AlertDialog.Builder(SearchActivity.this)
				.setTitle("Sorry..!")
				.setMessage("Session timeout ..! Please Login again")
				.setPositiveButton("OK", new DialogInterface.OnClickListener()
				{
					public void onClick(DialogInterface dialog,int which) 
					{
						startActivity(new Intent(SearchActivity.this,Login.class));
						//   return;
					}
				}).show();
			}else{
				setDashboard() ; 
			}
		}catch(Exception e){
			logger.error("getBestLocation Exception:", Log.getStackTraceString(e));
		}
		rBtn_consumerNo.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				if(edt_consumerNo.getText().toString().trim().equalsIgnoreCase("")){
					if(!accNo.trim().equalsIgnoreCase("")){
						edt_consumerNo.setText(accNo);
					}
				}
				edt_consumerNo.setHint("Enter Consumer No");
				rBtn_meterNo.setChecked(false);
			}
		});

		rBtn_meterNo.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				edt_consumerNo.setHint("Enter Meter No");
				if(!edt_consumerNo.getText().toString().trim().equalsIgnoreCase(""))
					accNo = edt_consumerNo.getText().toString().trim() ;


				edt_consumerNo.setText("");
				rBtn_consumerNo.setChecked(false);

			}
		});

		btn_search.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub






				// Get GSM Signal Strength (Dbm)

				try {



					mmemoryTotal = "0";
					mmemoryAvailable = "0";


					check = new MemoryCheck();


					/*MyListener   = new MyPhoneStateListener();*/
					mmemoryTotal = check.Memory().split("@")[0];
					mmemoryAvailable = check.Memory().split("@")[1];



					/*TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
					CellInfoGsm GSM = (CellInfoGsm)telephonyManager.getAllCellInfo().get(0);
					CellSignalStrengthGsm cellSignalStrengthGsm = GSM.getCellSignalStrength();
					signalStrength11 = cellSignalStrengthGsm.getAsuLevel() * 100/31;*/

				} catch (Exception e) {


					e.printStackTrace();







				}







				/*LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE );
				boolean statusOfGPS = manager.isProviderEnabled(LocationManager.GPS_PROVIDER);

				if(!statusOfGPS)
				{
					new AlertDialog.Builder(SearchActivity.this).setTitle("Info").setCancelable(false).setMessage("Please switch on your GPS").setPositiveButton("Turn ON", new DialogInterface.OnClickListener() {
						@Override				public void onClick(DialogInterface dialog, int which) {
							Intent viewIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
							startActivity(viewIntent);	
						}}).show();

					return;
				}*/

				
				UtilMaster.mlangitude = "6544644";
				UtilMaster.mlattitude = "5646464";

				if(/*!checkAccuracy()||*/ "0".equals(UtilMaster.mlangitude.trim())|| "0".equals(UtilMaster.mlattitude.trim()))
				{
					/*if("0".equals(UtilMaster.mlangitude.trim())|| "0".equals(UtilMaster.mlattitude.trim())){*/

					if(gpsAllowCount==0 || gpsAllowCount==1)
					{

						new AlertDialog.Builder(SearchActivity.this).setCancelable(false)
						.setTitle("GPS ACCURACY IS LESS").setMessage("Do You Want To Retry Or Continue?").setPositiveButton("Retry", new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {
								fusedLocationService=new FusedLocationService(SearchActivity.this);
								Toast.makeText(getApplicationContext(),"Reloading GPS...",Toast.LENGTH_SHORT).show();
							}
						}).show();
						gpsAllowCount++;
						return;
					}else{





						try {


							DatabaseHandler handler = new DatabaseHandler(SearchActivity.this);
							handler.openToRead();

							String a[] = 	handler.getlastStoredLatLong();


							UtilMaster.mlangitude=a[0];
							UtilMaster.mlattitude=a[1];

							handler.close();




							gpsAllowCount=0;
							goToNextActivity();


						} catch (Exception e) {
							// TODO: handle exception




							e.printStackTrace();


						UtilMaster.mlangitude="0";
						UtilMaster.mlattitude="0";
						gpsAllowCount=0;
						goToNextActivity();



						}



					}

					/*}*/

					/*	

						new AlertDialog.Builder(SearchActivity.this).setCancelable(false)
						.setTitle("GPS ACCURACY IS LESS").setMessage("Do You Want To Retry Or Continue?").setPositiveButton("Retry", new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {
								fusedLocationService=new FusedLocationService(SearchActivity.this);
								Toast.makeText(getApplicationContext(),"Reloading GPS...",Toast.LENGTH_SHORT).show();
							}
						}).setNegativeButton("Continue", new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {
								goToNextActivity();
							}
						}).show();*/
				}else{
					goToNextActivity();
				}

			}
		});


		btn_fv.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (rBtn_consumerNo.isChecked()) {
					Log.e("came in account check", "came in account check");

					if (edt_consumerNo.getText().toString().equals("")) {
						Toast.makeText(getApplicationContext(),
								"Enter consumer no or meter no",
								Toast.LENGTH_LONG).show();
					} else {
						DatabaseHandler db_helper1 = new DatabaseHandler(SearchActivity.this);
						db_helper1.openToRead(); 
						String consumer_no = db_helper1.fetchnextconsumer(edt_consumerNo.getText().toString(),0);
						db_helper1.close();
						System.out.println(consumer_no);
						if (!consumer_no.equals("null")) {
							edt_consumerNo.setText(consumer_no);
						} else {
							Toast.makeText(getApplicationContext(),
									"No more consumer or meter no",
									Toast.LENGTH_LONG).show();
						}

					}
				}else
				{
					if (edt_consumerNo.getText().toString().equals("")) {
						Toast.makeText(getApplicationContext(),
								"Enter consumer no or meter no",
								Toast.LENGTH_LONG).show();
					} else {
						DatabaseHandler db_helper1 = new DatabaseHandler(SearchActivity.this);
						db_helper1.openToRead();
						String consumer_no = db_helper1.fetchnextconsumer(edt_consumerNo.getText().toString(),1);
						db_helper1.close();
						System.out.println(consumer_no);
						if (!consumer_no.equals("null")) {
							edt_consumerNo.setText(consumer_no);
						} else {
							Toast.makeText(getApplicationContext(),
									"No more consumer or meter no",
									Toast.LENGTH_LONG).show();
						}
					}


				}


			}
		});

		btn_pvs.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				if (rBtn_consumerNo.isChecked()) {
					Log.e("came in account check", "came in account check");

					if (edt_consumerNo.getText().toString().equals("")) {
						Toast.makeText(getApplicationContext(),
								"Enter consumer no or meter no",
								Toast.LENGTH_LONG).show();
					} else {
						DatabaseHandler db_helper1 = new DatabaseHandler(SearchActivity.this);
						db_helper1.openToRead();
						String consumer_no = db_helper1.fetchprevconsumer(edt_consumerNo.getText().toString().trim(),0);
						db_helper1.close();
						System.out.println(consumer_no);
						if (!consumer_no.equals("null")) {
							edt_consumerNo.setText(consumer_no);
						} else {
							Toast.makeText(getApplicationContext(),
									"No more consumer or meter no",
									Toast.LENGTH_LONG).show();
						}

					}
				}else
				{
					if (edt_consumerNo.getText().toString().equals("")) {
						Toast.makeText(getApplicationContext(),
								"Enter consumer no or meter no",
								Toast.LENGTH_LONG).show();
					} else {
						DatabaseHandler db_helper1 = new DatabaseHandler(SearchActivity.this);
						db_helper1.openToRead();
						String consumer_no = db_helper1.fetchprevconsumer(edt_consumerNo.getText().toString(),1);
						db_helper1.close();
						System.out.println(consumer_no);
						if (!consumer_no.equals("null")) {
							edt_consumerNo.setText(consumer_no);
						} else {
							Toast.makeText(getApplicationContext(),
									"No more consumer or meter no",
									Toast.LENGTH_LONG).show();
						}
					}


				}



			}
		});

		btn_back.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
				move_inten(SearchActivity.this, MainActivity.class);
			}
		});


	}



	@Override
	protected void onResume() {
		super.onResume();


		getBatteryPercentage();


		fusedLocationService = new FusedLocationService(SearchActivity.this);
		if(logger == null){
			logger = MasterLibraryFunction.getlogger(getApplicationContext(), TAG);
		}
		logger.info(TAG, "In onResume ");
		if( (UtilMaster.mGLogin_MRCode.equalsIgnoreCase("")) ||  UtilMaster.mGLogin_SiteCode.equalsIgnoreCase("") ){
			logger.error("Session timeout ..! Please Login again UtilMaster.mGLogin_MRCode OR UtilMaster.mGLogin_SiteCode is NULL");
			new AlertDialog.Builder(SearchActivity.this)
			.setTitle("Sorry..!")
			.setMessage("Session timeout ..! Please Login again")
			.setPositiveButton("OK", new DialogInterface.OnClickListener()
			{

				public void onClick(DialogInterface dialog,int which) 
				{
					startActivity(new Intent(SearchActivity.this,Login.class));
				}
			}).show();
		}



		/*
		 * 
		 * this is to make compulsary photo and GPS*/

		UtilMaster.gpsEnable = true;
		UtilMaster.photoEnable = true;

		if(UtilMaster.isGpsEnable()){

			/*getLoc();*/



			//	 checkAccuracy();







		}

		 if(Meterobservation.isThroughRF) {
			btn_search.performLongClick(); 
		 }
		
	}

	@Override
	public void onBackPressed() {
		//super.onBackPressed();
		logger.info(TAG, "In onBackPressed ");
		finish();
		move_inten(SearchActivity.this, MainActivity.class);
	}



	public void setDashboard(){
		txt_siteCode.setText(UtilMaster.mGLogin_SiteCode.trim()+"("+ UtilMaster.mGLogin_MRCode.trim()+")");
		String[]  dashboardStatus=null ;
		DatabaseHandler databasehandler = new DatabaseHandler(getApplicationContext());
		try {
			databasehandler.openToRead();
			dashboardStatus = databasehandler.getDashBoardStatus();
			if(dashboardStatus != null)
			{
				txt_unbilled.setText(dashboardStatus[0]);
				txt_billed .setText(dashboardStatus[1]);
				txt_synch .setText(dashboardStatus[2]);
				String conNo = databasehandler.getConsumerno();
				edt_consumerNo.setText(conNo);
			}

		} catch (Exception e) {
			logger.error("setDashboard Exception:", e);
			e.printStackTrace();
		}
		databasehandler.close();

	}

	public boolean setConsumerDetails(Cursor cursor_next)throws NumberFormatException, SQLException 
	{
		boolean result = false;

		UtilMaster. mconsumer_sc_no   =     	cursor_next.getString(cursor_next.getColumnIndex( 	DatabaseHandler. KEY_CONSUMER_SC_NO )).trim() ;                  
		UtilMaster. mmeter_constant   =     	cursor_next.getString(cursor_next.getColumnIndex(  	DatabaseHandler. KEY_METER_CONSTANT )).trim() ;
		UtilMaster. mconsumer_name   =     	cursor_next.getString(cursor_next.getColumnIndex( 	DatabaseHandler. KEY_CONSUMER_NAME )).trim() ;
		UtilMaster. maddress   =     	cursor_next.getString(cursor_next.getColumnIndex(  	DatabaseHandler. KEY_ADDRESS )).trim() ;
		UtilMaster. maddress1   =     	cursor_next.getString(cursor_next.getColumnIndex(  	DatabaseHandler. KEY_ADDRESS1 )).trim() ;

		UtilMaster. mtariff =	cursor_next.getString(cursor_next.getColumnIndex(  	DatabaseHandler. KEY_TARIFF )).trim() ;

		if (libraryFunction == null){
			libraryFunction = new MasterLibraryFunction(SearchActivity.this);
		}

		UtilMaster. mtariffdesc =	libraryFunction.getTariffDesc(SearchActivity.this, UtilMaster. mtariff);



		UtilMaster. mledger_no   =     	cursor_next.getString(cursor_next.getColumnIndex(  	DatabaseHandler. KEY_LEDGER_NO )).trim() ;
		UtilMaster. mfolio_no   =     	cursor_next.getString(cursor_next.getColumnIndex( 	DatabaseHandler. KEY_FOLIO_NO )).trim() ;
		UtilMaster. mconnected_load   =     	cursor_next.getString(cursor_next.getColumnIndex(  	DatabaseHandler. KEY_CONNECTED_LOAD)).trim() ;
		UtilMaster. md_and_r_fee   =     	cursor_next.getString(cursor_next.getColumnIndex(  	DatabaseHandler. KEY_D_AND_R_FEE )).trim() ;
		UtilMaster. marrears   =     	cursor_next.getString(cursor_next.getColumnIndex(  	DatabaseHandler. KEY_ARREARS )).trim() ;
		UtilMaster. minterest   =     	cursor_next.getString(cursor_next.getColumnIndex( 	DatabaseHandler. KEY_INTEREST )).trim() ;
		UtilMaster. mothers   =     	cursor_next.getString(cursor_next.getColumnIndex(  	DatabaseHandler. KEY_OTHERS )).trim() ;
		UtilMaster. mbackbillarr   =     	cursor_next.getString(cursor_next.getColumnIndex( 	DatabaseHandler. KEY_BACKBILLARR )).trim() ;

		UtilMaster. mdl_or_mnr_prev_month   =     	cursor_next.getString(cursor_next.getColumnIndex(  	DatabaseHandler. KEY_DL_OR_MNR_PREV_MONTH )).trim() ;







		UtilMaster.mpreviousreadingstatus = cursor_next.getString(cursor_next.getColumnIndex(  	DatabaseHandler. KEY_DL_OR_MNR_PREV_MONTH )).trim() ;
		UtilMaster. mprevious_reading  =     	cursor_next.getString(cursor_next.getColumnIndex(  	DatabaseHandler. KEY_PREVIOUS_READING )).trim() ;
		UtilMaster. mpresentreading  =     	cursor_next.getString(cursor_next.getColumnIndex( 	DatabaseHandler. KEY_PRESENTREADING )).trim() ;
		UtilMaster. mconsumption  =     	cursor_next.getString(cursor_next.getColumnIndex( 	DatabaseHandler. KEY_CONSUMPTION )).trim() ;
		UtilMaster. mpower_factor   =     	cursor_next.getString(cursor_next.getColumnIndex(  	DatabaseHandler. KEY_POWER_FACTOR )).trim() ;
		UtilMaster. mreading_date   =     	cursor_next.getString(cursor_next.getColumnIndex( 	DatabaseHandler. KEY_READING_DATE )).trim() ;
		UtilMaster. mmeter_change_units_consumed   =     	cursor_next.getString(cursor_next.getColumnIndex(  	DatabaseHandler. KEY_METER_CHANGE_UNITS_CONSUMED )).trim() ;


		UtilMaster. mno_of_months_issued   =     	cursor_next.getString(cursor_next.getColumnIndex( 	DatabaseHandler. KEY_NO_OF_MONTHS_ISSUED )).trim() ;
		UtilMaster.mActualBmForDL = UtilMaster. mno_of_months_issued;


		UtilMaster. maverage_consumption   =     	cursor_next.getString(cursor_next.getColumnIndex(  	DatabaseHandler. KEY_AVERAGE_CONSUMPTION )).trim() ;


		/*BY SHIVANAND
		 * 
		 * //Done by shivanad to resolve issues of gescom_1.05apk as average consumtion is coming from web-service after multiplying BM.
		 */		


		float averageConsumtion = (Float.valueOf(UtilMaster. maverage_consumption)/ Float.valueOf(UtilMaster. mno_of_months_issued ));

		UtilMaster. maverage_consumption = String.valueOf(averageConsumtion);


		System.out.println("UtilMaster. maverage_consumption IS :"+UtilMaster. maverage_consumption);






		UtilMaster. mcredit_or_rebate   =     	cursor_next.getString(cursor_next.getColumnIndex(  	DatabaseHandler. KEY_CREDIT_OR_REBATE)).trim() ;
		UtilMaster. mfixed_ges   =     	cursor_next.getString(cursor_next.getColumnIndex( 	DatabaseHandler. KEY_FIXED_GES )).trim() ;
		UtilMaster. maudit_arrears   =     	cursor_next.getString(cursor_next.getColumnIndex(  	DatabaseHandler. KEY_AUDIT_ARREARS )).trim() ;
		UtilMaster. mold_interest   =     	cursor_next.getString(cursor_next.getColumnIndex( 	DatabaseHandler. KEY_OLD_INTEREST )).trim() ;
		UtilMaster. mtrivector   =     	cursor_next.getString(cursor_next.getColumnIndex(  	DatabaseHandler. KEY_TRIVECTOR )).trim() ;
		UtilMaster. mckwhlkwh   =     	cursor_next.getString(cursor_next.getColumnIndex(  	DatabaseHandler. KEY_CKWHLKWH )).trim() ;
		UtilMaster. mdoorlockavg   =     	cursor_next.getString(cursor_next.getColumnIndex( 	DatabaseHandler. KEY_DOORLOCKAVG )).trim() ;
		UtilMaster. mconsumercode   =     	cursor_next.getString(cursor_next.getColumnIndex(  	DatabaseHandler. KEY_CONSUMERCODE )).trim() ;
		UtilMaster. madditionaldep   =     	cursor_next.getString(cursor_next.getColumnIndex( 	DatabaseHandler. KEY_ADDITIONALDEP)).trim() ;
		UtilMaster. mmrcode   =     	cursor_next.getString(cursor_next.getColumnIndex(  	DatabaseHandler. KEY_MRCODE )).trim() ;
		UtilMaster. mbillmonth   =     	cursor_next.getString(cursor_next.getColumnIndex(  	DatabaseHandler. KEY_BILLMONTH )).trim() ;
		UtilMaster. msitecode=	cursor_next.getString(cursor_next.getColumnIndex(  	DatabaseHandler. KEY_SITECODE )).trim() ;
		UtilMaster. msyncstatus   =     	cursor_next.getString(cursor_next.getColumnIndex(  	DatabaseHandler. KEY_SYNCSTATUS )).trim() ;
		UtilMaster. mdataprepareddate =	cursor_next.getString(cursor_next.getColumnIndex(  	DatabaseHandler. KEY_DATAPREPAREDDATE )).trim() ;
		UtilMaster. mservertosbmdate   =     	cursor_next.getString(cursor_next.getColumnIndex(  	DatabaseHandler. KEY_SERVERTOSBMDATE)).trim() ;
		UtilMaster. mmeterno   =     	cursor_next.getString(cursor_next.getColumnIndex(  	DatabaseHandler. KEY_METERNO )).trim() ;
		UtilMaster. minterestondeposit   =     	cursor_next.getString(cursor_next.getColumnIndex( 	DatabaseHandler. KEY_INTERESTONDEPOSIT )).trim() ;
		UtilMaster. mdl_adj   =     	cursor_next.getString(cursor_next.getColumnIndex( 	DatabaseHandler. KEY_DL_ADJ )).trim() ;
		UtilMaster. mdl_count   =     	cursor_next.getString(cursor_next.getColumnIndex( 	DatabaseHandler. KEY_DL_COUNT )).trim() ;
		UtilMaster. mmeterrent   =     	cursor_next.getString(cursor_next.getColumnIndex(  	DatabaseHandler. KEY_METERRENT )).trim() ;
		UtilMaster. mfppca    =     	cursor_next.getString(cursor_next.getColumnIndex(  	DatabaseHandler. KEY_FPPCA )).trim() ;



		UtilMaster. mtax    =     	cursor_next.getString(cursor_next.getColumnIndex(  	DatabaseHandler. KEY_TAX )).trim() ;
		UtilMaster. mec    =     	cursor_next.getString(cursor_next.getColumnIndex( 	DatabaseHandler. KEY_EC )).trim() ;
		UtilMaster. mfc    =     	cursor_next.getString(cursor_next.getColumnIndex( 	DatabaseHandler. KEY_FC )).trim() ;
		UtilMaster. mtotal    =     	cursor_next.getString(cursor_next.getColumnIndex( 	DatabaseHandler. KEY_TOTAL )).trim() ;

		UtilMaster. mprebilldate    =     	cursor_next.getString(cursor_next.getColumnIndex( 	DatabaseHandler. KEY_PREBILLDATE )).trim() ;
		UtilMaster. mbilldate    =     	cursor_next.getString(cursor_next.getColumnIndex( 	DatabaseHandler.  KEY_BILLDATE )).trim() ;
		UtilMaster. mduedate    =     	cursor_next.getString(cursor_next.getColumnIndex( 	DatabaseHandler.  KEY_DUEDATE )).trim() ;

		UtilMaster. mcyclename    =     	cursor_next.getString(cursor_next.getColumnIndex( 	DatabaseHandler.KEY_CYCLENAME )).trim() ;
		UtilMaster. mconsumer_key    =     	cursor_next.getString(cursor_next.getColumnIndex( 	DatabaseHandler.KEY_CONSUMER_KEY )).trim() ;
		UtilMaster. minstallationo    =     	cursor_next.getString(cursor_next.getColumnIndex( 	DatabaseHandler.KEY_INSTALLATIONO )).trim() ;
		UtilMaster. mconsumerno    =     	cursor_next.getString(cursor_next.getColumnIndex(   	DatabaseHandler. KEY_CONSUMERNO)).trim() ;
		UtilMaster. mdivision    =     	cursor_next.getString(cursor_next.getColumnIndex(   	DatabaseHandler. KEY_DIVISION )).trim() ;
		UtilMaster. msubdivision    =     	cursor_next.getString(cursor_next.getColumnIndex(  	DatabaseHandler. KEY_SUBDIVISION )).trim() ;

		UtilMaster. mbookno    =     	cursor_next.getString(cursor_next.getColumnIndex( 	DatabaseHandler. KEY_BOOKNO )).trim() ;



		UtilMaster. mdevicefirmwareversion    =     	cursor_next.getString(cursor_next.getColumnIndex(   	DatabaseHandler. KEY_DEVICEFIRMWAREVERSION )).trim() ;
		//UtilMaster. mbilledatetimestamp    =     	cursor_next.getString(cursor_next.getColumnIndex( 	DatabaseHandler. KEY_BILLEDATETIMESTAMP )).trim() ;


		UtilMaster. mbilledatetimestamp    = UtilMaster.getMobileBillDatetimeStamp();

		UtilMaster. mbillno    =     	cursor_next.getString(cursor_next.getColumnIndex( 	DatabaseHandler. KEY_BILLNO )).trim() ;
		UtilMaster. mpresentmeterstatus    =     	cursor_next.getString(cursor_next.getColumnIndex( 	DatabaseHandler. KEY_PRESENTMETERSTATUS )).trim() ;
		UtilMaster. mbilledstatus    =     	cursor_next.getString(cursor_next.getColumnIndex( 	DatabaseHandler. KEY_BILLEDSTATUS)).trim() ;
		UtilMaster. mstatus    =     	cursor_next.getString(cursor_next.getColumnIndex( 	DatabaseHandler. KEY_STATUS )).trim() ;







		UtilMaster. mprevious_billeddate    =     	cursor_next.getString(cursor_next.getColumnIndex( 	DatabaseHandler. KEY_READING_DATE )).trim() ;
		UtilMaster. mactual_previous_billeddate    =     	cursor_next.getString(cursor_next.getColumnIndex( 	DatabaseHandler. ACTUAL_PREVIOUS_BILLEDDATE )) ;
		UtilMaster. mlineminimum    =     	cursor_next.getString(cursor_next.getColumnIndex( 	DatabaseHandler. KEY_LINEMINIMUM)) ;
		UtilMaster. mseasonal_consumer  =     	cursor_next.getString(cursor_next.getColumnIndex(  	DatabaseHandler. SEASONAL_CONSUMER 	 )) ;
		UtilMaster. mligpoints    =     	cursor_next.getString(cursor_next.getColumnIndex(  	DatabaseHandler. KEY_LIGPOINTS )) ;
		UtilMaster. mmetered    =     	cursor_next.getString(cursor_next.getColumnIndex(  	DatabaseHandler. KEY_METERED ));
		UtilMaster. mpf_reading    =     	cursor_next.getString(cursor_next.getColumnIndex(  	DatabaseHandler. KEY_PF_READING )) ;
		UtilMaster. mmaster_Pf_reading    =     	cursor_next.getString(cursor_next.getColumnIndex(  	DatabaseHandler. KEY_PF_READING )) ;
		UtilMaster. mpf_penality    =     	cursor_next.getString(cursor_next.getColumnIndex(  	DatabaseHandler. KEY_PF_PENALITY )) ;
		UtilMaster.mbmd_penality  =     	cursor_next.getString(cursor_next.getColumnIndex(  	DatabaseHandler. KEY_BMD_PENALITY )) ;
		UtilMaster.mbmd_reading =     	cursor_next.getString(cursor_next.getColumnIndex(  	DatabaseHandler. KEY_BMD_READING_PENALITY )) ;


		UtilMaster. mImagePath    =     	cursor_next.getString(cursor_next.getColumnIndex(	DatabaseHandler. KEY_IMAGEPATH )) ;

		UtilMaster. mextra1    =     	cursor_next.getString(cursor_next.getColumnIndex(  	DatabaseHandler. KEY_EXTRA1)) ;
		UtilMaster. mextra2    =     	cursor_next.getString(cursor_next.getColumnIndex(  	DatabaseHandler. KEY_EXTRA2 )) ;
		UtilMaster. mextra3    =     	cursor_next.getString(cursor_next.getColumnIndex(  	DatabaseHandler. KEY_EXTRA3)) ;
		UtilMaster. mextra4    =     	cursor_next.getString(cursor_next.getColumnIndex(  	DatabaseHandler. KEY_EXTRA4 )) ;
		UtilMaster. mextra5    =     	cursor_next.getString(cursor_next.getColumnIndex(  	DatabaseHandler. KEY_EXTRA5)) ;
		UtilMaster. mextra6    =     	cursor_next.getString(cursor_next.getColumnIndex(  	DatabaseHandler. KEY_EXTRA6 )) ;
		UtilMaster. mextra7    =     	cursor_next.getString(cursor_next.getColumnIndex(  	DatabaseHandler. KEY_EXTRA7 ));
		UtilMaster. mextra8    =     	cursor_next.getString(cursor_next.getColumnIndex(	DatabaseHandler. KEY_EXTRA8 )) ;

		UtilMaster.deviceInfo = mbatteryLevel+","+signalStrength11+","+mmemoryTotal+","+mmemoryAvailable;



		
		/* 
		 * 
		 * these changes to resolve value printing as null for receipt date and amt*/

		if(UtilMaster. mextra4.equals("null") || UtilMaster. mextra4 == null){

			UtilMaster. mextra4 = "-";
		}

		if(UtilMaster. mextra7.equals("null") || UtilMaster. mextra7 == null){

			UtilMaster. mextra7 = "-";
		}




		/*	
		 * Note shivanand
		 *  
		 *  extra 5 for mobile number
		 extra 3 for tax flag
		 extra 4 for receipt date 
		 extra 7 for receipt amout

		 *we used extra 6 for deviceInfo
		 *
		 */



		result = true;

		return result;
	}

	public void resetParams(){
		//UtilMaster. mconsumer_sc_no   =     	"0";
		UtilMaster. mmeter_constant   =     	"0";
		UtilMaster. mconsumer_name   =     	"0";
		UtilMaster. maddress   =     	"0";
		UtilMaster. maddress1   =     	"0";
		UtilMaster. mtariff =	"0";
		UtilMaster. mtariffdesc =	"0";
		UtilMaster. mledger_no   =     	"0";
		UtilMaster. mfolio_no   =     	"0";
		UtilMaster. mconnected_load   =     	"0";
		UtilMaster. md_and_r_fee   =     	"0";
		UtilMaster. marrears   =     	"0";
		UtilMaster. minterest   =     	"0";
		UtilMaster. mothers   =     	"0";
		UtilMaster. mbackbillarr   =     	"0";
		UtilMaster. maverage_consumption   =     	"0";
		UtilMaster. mdl_or_mnr_prev_month   =     	"0";
		UtilMaster.mpreviousreadingstatus = "0" ;
		UtilMaster. mprevious_reading  =     	"0";
		UtilMaster. mpresentreading  =     	"0";
		UtilMaster. mconsumption  =     	"0";
		UtilMaster. mpower_factor   =     	"0";
		UtilMaster. mreading_date   =     	"0";
		UtilMaster. mmeter_change_units_consumed   =     	"0";
		UtilMaster. mno_of_months_issued   =     	"0";
		UtilMaster. mcredit_or_rebate   =     	"0";
		UtilMaster. mfixed_ges   =     	"0";
		UtilMaster. maudit_arrears   =     	"0";
		UtilMaster. mold_interest   =     	"0";
		UtilMaster. mtrivector   =     	"0";
		UtilMaster. mdoorlockavg   =     	"0";
		UtilMaster. mconsumercode   =     	"0";
		UtilMaster. madditionaldep   =     	"0";
		UtilMaster. mmrcode   =     	"0";
		UtilMaster. mbillmonth   =     	"0";
		UtilMaster. msitecode=	"0";
		UtilMaster. msyncstatus   =     	"0";
		UtilMaster. mdataprepareddate =	"0";
		UtilMaster. mservertosbmdate   =     	"0";
		UtilMaster. mmeterno   =     	"0";
		UtilMaster. minterestondeposit   =     	"0";
		UtilMaster. mdl_adj   =     	"0";
		UtilMaster. mdl_count   =   "0";
		UtilMaster. mmeterrent   =     	"0";
		UtilMaster. mfppca    =     	"0";
		UtilMaster. mtax    =     	"0";
		UtilMaster. mec    =     	"0";
		UtilMaster. mfc    =     	"0";
		UtilMaster. mtotal    =     	"0";
		UtilMaster. mprebilldate    =     	"0";
		UtilMaster. mbilldate    =     	"0";
		UtilMaster. mduedate    =     	"0";
		UtilMaster. mextra1   =     	"0";
		UtilMaster. mextra2    =     	"0";
		UtilMaster. mcyclename    =     	"0";
		UtilMaster. mconsumer_key    =     	"0";
		UtilMaster. minstallationo    =     	"0";
		UtilMaster. mconsumerno    =     	"0";
		UtilMaster. mdivision    =     	"0";
		UtilMaster. msubdivision    =     	"0";
		UtilMaster. mbookno    =     	"0";
		UtilMaster. mdevicefirmwareversion    =     	"0";
		UtilMaster. mbilledatetimestamp    =     	"0";
		UtilMaster. mbillno    =     	"0";
		UtilMaster. mpresentmeterstatus    =     	"0";
		UtilMaster. mbilledstatus    =     	"0";
		UtilMaster. mstatus    =     	"0";
		UtilMaster. mprevious_billeddate    =     	"0";
		UtilMaster. mactual_previous_billeddate    =     	"0";
		UtilMaster. mlineminimum    =     	"0";
		UtilMaster. mseasonal_consumer  =     	"0";
		UtilMaster. mligpoints    =     	"0";
		UtilMaster. mmetered    =     	"0";
		UtilMaster. mpf_reading    =     	"0";
		UtilMaster. mmaster_Pf_reading =     	"0";
		UtilMaster. mpf_penality    =     	"0";
		UtilMaster. mextra3    =     	"0";
		UtilMaster. mextra4    =     	"0";
		UtilMaster. mextra5    =     	"0";
		UtilMaster. mextra6    =     	"0";
		UtilMaster. mextra7    =     	"0";
		UtilMaster. mextra8    =     	"0";
		UtilMaster. mImagePath    =    "";
		UtilMaster.mbmd_reading   =     	"0";
		UtilMaster.mbmd_penality  =     	"0";


	}


	/**
	 * @category GET GPS LOCATION 
	 * @return Location
	 */
	/*	private Location getBestLocation() {
        Location gpslocation = null;
        Location networkLocation = null;
        if(locMan==null){
          locMan = (LocationManager) getApplicationContext() .getSystemService(Context.LOCATION_SERVICE);
        }
        try {
            if(locMan.isProviderEnabled(LocationManager.GPS_PROVIDER)){
                locMan.requestLocationUpdates(LocationManager.GPS_PROVIDER,50000, 1, gpsListener);// here you can set the 2nd argument time interval also that after how much time it will get the gps location
                gpslocation = locMan.getLastKnownLocation(LocationManager.GPS_PROVIDER);

            }
            if(locMan.isProviderEnabled(LocationManager.NETWORK_PROVIDER)){
                locMan.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,50000, 1, gpsListener);
                networkLocation = locMan.getLastKnownLocation(LocationManager.NETWORK_PROVIDER); 
            }
        } catch (IllegalArgumentException e) {
        	logger.error("getBestLocation Exception:", Log.getStackTraceString(e));

        }
        if(gpslocation==null && networkLocation==null)
            return null;

        if(gpslocation!=null && networkLocation!=null){
            if(gpslocation.getTime() < networkLocation.getTime()){
                gpslocation = null;
                return networkLocation;
            }else{
                networkLocation = null;
                return gpslocation;
            }
        }
        if (gpslocation == null) {
            return networkLocation;
        }
        if (networkLocation == null) {
            return gpslocation;
        }
        return null;
    }*/
	LocationListener gpsListener = new LocationListener() {
		public void onLocationChanged(Location location) {
			System.out.println("curLocation ================== "+curLocation);
			if (curLocation == null) {
				curLocation = location;
				locationChanged = true;
			}else if (curLocation.getLatitude() == location.getLatitude() && curLocation.getLongitude() == location.getLongitude()){

				System.out.println(location.getLatitude()+" GAP " + location.getLongitude());
				System.out.println("locationChanged = false");
				locationChanged = false;
				return;
			}else
			{

				System.out.println(location.getLatitude()+" GAP " + location.getLongitude());
				System.out.println("locationChanged = true");
				locationChanged = true;
			}
			curLocation = location;
			if (locationChanged)
				locMan.removeUpdates(gpsListener);

		}
		public void onProviderDisabled(String provider) {
		}

		public void onProviderEnabled(String provider) {
		}

		public void onStatusChanged(String provider, int status,Bundle extras) {
			if (status == 0)// UnAvailable
			{
			} else if (status == 1)// Trying to Connect
			{
			} else if (status == 2) {// Available
			}
		}

	};

	/*    private void getLoc(){
    	curLocation= getBestLocation();
    	 float accuracy  = curLocation.getAccuracy();



    	System.out.println("Accuracy is >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> :"+accuracy);



		 if (curLocation == null) 
		 {





		     new AlertDialog.Builder(SearchActivity.this)
			.setTitle("Info")
			.setMessage("Please switch on your GPS")
			.setPositiveButton("OK", new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {


					Intent viewIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
					startActivity(viewIntent);



				}
			})
			.setNegativeButton("Retry", new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {

					getLoc();
				}
			})
			.setCancelable(false)
			.show();

		 }





	        else{
	        	UtilMaster.mlangitude = curLocation.getLongitude()+"";
	        	UtilMaster.mlattitude = curLocation.getLatitude()+"";


	        	 new AlertDialog.Builder(SearchActivity.this)
	 			.setTitle("GPS Co-Ordinates")
	 			.setMessage("Longitude :"+curLocation.getLongitude()+"\n"+"Lattitude :"+curLocation.getLatitude())
	 			.setPositiveButton("OK", null).show();


	        }
    }*/

	public void isvalidConDetails(Context context){

		// By shivanand allowed duplicate bill for the DL cases


		/*Toast.makeText(getApplicationContext(), "Present Meter status is :"+UtilMaster.mpresentmeterstatus, Toast.LENGTH_LONG).show();*/





		if(UtilMaster. mbilledstatus.equalsIgnoreCase("1") ){
			UtilMaster.show(context, "Info", 0, "Consumer Already Billed,Do you want to generate duplicate bill", "Yes", getDialogListener(context, PrintBillAction.class),"No",null).show();
		}


		
		else if(UtilMaster. mtariff.trim().equals("-") || UtilMaster. mtariff.trim().equals("0")){

			UtilMaster.show(context, "Info", 0, "Tariff is Not correct for the Consumer, Kindly Download the Consumers again", "OK", getDialogListener(context, MainActivity.class),"No",null).show();


		}
		
		else if(UtilMaster. mtariffdesc.trim().equals("-")){

			UtilMaster.show(context, "Info", 0, "Tariff Details are Not correct, Kindly Download the tariff details again", "OK", getDialogListener(context, MainActivity.class),"No",null).show();


		}

		else


		{
			move_inten(SearchActivity.this,Meterobservation.class);
		}
	}

	@SuppressWarnings("rawtypes")
	public android.content.DialogInterface.OnClickListener getDialogListener(
			final Context context, final Class class1) {

		return new android.content.DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {

				move_inten(context, class1);
			}
		};

	}

	@SuppressWarnings("rawtypes")
	public void move_inten(Context context, Class class1) {
		Intent intent = new Intent(context, class1);
		startActivity(intent);
	}




	@SuppressLint("NewApi")
	private boolean checkAccuracy() {

		System.out.println("coming to check accuracy");

		Location location = fusedLocationService.getLocation();
		String locationResult = "";
		if (null != location) {
			double latitude = location.getLatitude();
			double longitude = location.getLongitude();
			float accuracy = location.getAccuracy();
			double elapsedTimeSecs = (double) location.getElapsedRealtimeNanos()/ 1000000000.0;
			String provider = location.getProvider();
			double altitude = location.getAltitude();
			locationResult = "Latitude: " + latitude + "\n" +

                    "Longitude: " + longitude + "\n" +
                    "Altitude: " + altitude + "\n" +
                    "Accuracy: " + accuracy + "\n" +
                    "Elapsed Time: " + elapsedTimeSecs + " secs" + "\n" +
                    "Provider: " + provider + "\n";

			UtilMaster.mlangitude = longitude+"";
			UtilMaster.mlattitude = latitude+"";

			System.out.println("locationResult : "+locationResult);

			System.out.println("Accuracy point--------------------------------"+accuracy);


			if(accuracy<=50)
			{
				return true;
			}


		} else{
			fusedLocationService = new FusedLocationService(SearchActivity.this);
			location = fusedLocationService.getLocation();
			Toast.makeText(getApplicationContext(), "Location Not Available.\nPlease Press Again", Toast.LENGTH_SHORT).show();
		}

		return false;
	}



	private void goToNextActivity(){


		String accNo = edt_consumerNo.getText().toString().trim();
		if (accNo.equals("") || accNo.equals(null)) {
			if (rBtn_consumerNo.isChecked()) {
				Toast.makeText(getApplicationContext(),"Enter Consumer No ",Toast.LENGTH_LONG).show();
			} else if (rBtn_meterNo.isChecked()) {
				Toast.makeText(getApplicationContext(),"Enter MeterNo ",Toast.LENGTH_LONG).show();
			}
		} else 
		{




			try {

				libraryFunction = new  MasterLibraryFunction(SearchActivity.this) ;
				if (libraryFunction.isdataValid()) {


					resetParams();
					DatabaseHandler handler = new DatabaseHandler(SearchActivity.this);
					handler.openToRead();
					Cursor cursor ;
					boolean isdetailsSet ;
					if (rBtn_consumerNo.isChecked()) {
						cursor = handler.getDetailsByConScNo(accNo);
						handler.close();
						if (cursor != null && cursor.moveToFirst()) 
						{
							isdetailsSet  = setConsumerDetails(cursor);
							cursor.close();
							if (isdetailsSet) {
								isvalidConDetails(SearchActivity.this);
							} else {
								Toast.makeText(getApplicationContext(),
										"Invalid Customer No",
										Toast.LENGTH_LONG).show();
							}

						}else
						{
							cursor.close();
							//*UtilMaster.showToast(BilledUnbilled.this,"Invalid Customer No").show();
							Toast.makeText(getApplicationContext(),"Invalid Customer No",Toast.LENGTH_LONG).show();


						}


					} else if (rBtn_meterNo.isChecked()) {

						cursor = handler.getDetailsByMeterNo(accNo);
						handler.close();
						if (cursor != null && cursor.moveToFirst()) 
						{
							isdetailsSet  = setConsumerDetails(cursor);
							cursor.close();
							if (isdetailsSet) {
								isvalidConDetails(SearchActivity.this);
							} else {
								Toast.makeText(getApplicationContext(),
										"Invalid Meter No",
										Toast.LENGTH_LONG).show();
							}

						}else
						{
							cursor.close();
							//*UtilMaster.showToast(BilledUnbilled.this,"Invalid Customer No").show();
							Toast.makeText(getApplicationContext(),"Invalid Meter No",Toast.LENGTH_LONG).show();


						}


					}
				} else {
					new AlertDialog.Builder(SearchActivity.this)
					.setTitle("Info")
					.setMessage(
							"Mobile Date has been changed please set the Date correctly OR Download fresh data to Procced next ")
							.setPositiveButton(
									"Set Date",
									new DialogInterface.OnClickListener() {
										public void onClick(
												DialogInterface arg0,
												int arg1) {

											move_inten(SearchActivity.this, MainActivity.class);
										}
									}).show();
				}

			} catch (Exception e) {
				logger.error( Log.getStackTraceString(e));
				Toast.makeText(	getApplicationContext(),"Consumer details are insufficient, You can not bill for this Consumer",Toast.LENGTH_LONG).show();
				e.printStackTrace();
			}

		}



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
				//  editBatteryStrength.setText(batteryLevel+"");




				mbatteryLevel = String.valueOf(batteryLevel);

				System.out.println("mbatteryLevel :"+mbatteryLevel);
				System.out.println("batteryLevel :"+batteryLevel);



				levelbattery = batteryLevel;
				//batteryPercent.setText("Battery Level Remaining: " + level + "%");

			}
		}; 
		IntentFilter batteryLevelFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
		registerReceiver(batteryLevelReceiver, batteryLevelFilter);
		//return batteryLevel;
	}



	private class MyPhoneStateListener extends PhoneStateListener
	{
		/* Get the Signal strength from the provider, each tiome there is an update */
		@Override
		public void onSignalStrengthsChanged(SignalStrength signalStrength)
		{
			super.onSignalStrengthsChanged(signalStrength);
			if(signalStrength.getGsmSignalStrength()==99 || (signalStrength.getGsmSignalStrength()*100/31)==45 ){

				/*editsignalStrength.setText(String.valueOf(0));*/

				signalStrength11 = 0;


			}
			else{
				signalStrength11 = signalStrength.getGsmSignalStrength()*100/31;
				//	editsignalStrength.setText(String.valueOf(signalStrength.getGsmSignalStrength()*100/31));
			}



		}

	};


}
