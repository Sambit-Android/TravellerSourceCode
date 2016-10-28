package com.bcits.bsmartbilling;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import Utils.Logger;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class CameraActivity extends Activity {

	static Logger logger ;
	Button button = null;
	static final int FOTO_MODE = 0;
	private static final String TAG = "CamaraView";
	private static final int CAMERA_REQUEST = 1888; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.e(TAG, "onCreate");
		MasterLibraryFunction.collectLogs1(TAG+ " onCreate");
		getWindow().setFormat(PixelFormat.TRANSLUCENT);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.camera_activity);

		button = (Button) findViewById(R.id.capture);
		if (android.provider.Settings.System.getInt(getContentResolver(),Settings.System.ACCELEROMETER_ROTATION, 0) == 1) {
			android.provider.Settings.System.putInt(getContentResolver(),Settings.System.ACCELEROMETER_ROTATION, 0);
		}
		try {
			if(logger == null){
				logger = MasterLibraryFunction.getlogger(getApplicationContext(), TAG);
			}
			logger.info("In Side onCreate()");
		} catch (Exception e) {
			logger.error("logger is NULL");
		}
		if( (UtilMaster.mGLogin_MRCode.equalsIgnoreCase("")) ||  UtilMaster.mGLogin_SiteCode.equalsIgnoreCase("") ){
			logger.error("Session timeout ..! Please Login again UtilMaster.mGLogin_MRCode OR UtilMaster.mGLogin_SiteCode is NULL");
			new AlertDialog.Builder(CameraActivity.this)
			.setTitle("Sorry..!")
			.setMessage("Session timeout ..! Please Login again")
			.setPositiveButton("OK", new DialogInterface.OnClickListener()
			{

				public void onClick(DialogInterface dialog,int which) 
				{
					startActivity(new Intent(CameraActivity.this,Login.class));
				}
			}).show();
		}
		else{



			/*
			 * 
			 * 
			 * new change for photo setting option*/


			if(UtilMaster.isPhotoEnable()){

				Intent intent = new Intent( MediaStore.ACTION_IMAGE_CAPTURE );
				Uri	fileUri = getOutputMediaFileUri();
				intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri );
				startActivityForResult(intent, CAMERA_REQUEST);	

			}

			else{

				byPassCameraActivity();

			}

			/*	
			 * 
			 * 
			 * This was old
			 * 	

		byPassCameraActivity(); // this needs to be execute and below lines should be commented
		Intent intent = new Intent( MediaStore.ACTION_IMAGE_CAPTURE );
    	Uri	fileUri = getOutputMediaFileUri();
		intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri );
		startActivityForResult(intent, CAMERA_REQUEST);		

			 */


		}
	}

	@Override
	protected void onStart() {
		super.onStart();
	}
	public void byPassCameraActivity(){
		try {
			Log.d("MyCameraApp", " byPassCameraActivity"); 

			int Ltariff_code_i = 0;
			try {
				Ltariff_code_i = Integer.parseInt(UtilMaster.mtariff.trim());
			} catch (Exception e) {
				logger.error(TAG, Log.getStackTraceString(e));
			}

			/*///we are doing at search activity itself

			///	UtilMaster.maverage_consumption = ""+Float.parseFloat(UtilMaster.maverage_consumption.trim())/Float.parseFloat(UtilMaster.mno_of_months_issued) ; 
			 * 
			 */	

			// by shivanand
			
			if(Ltariff_code_i == 1  /*|| Ltariff_code_i == 111 || Ltariff_code_i == 112 || Ltariff_code_i == 113 || Ltariff_code_i == 114*/  )
			{
				calculationOtherThenNormal();

			}else{
				if ((!(UtilMaster.getMpresentmeterstatus().equals("1") || UtilMaster.getMpresentmeterstatus().equals("5")))
						/*|| (UtilMaster.mpreviousreadingstatus.trim().equalsIgnoreCase("5") && UtilMaster.getMpresentmeterstatus().equalsIgnoreCase("5"))*/ )
					calculationOtherThenNormal();
				else
					startActivity(new Intent(CameraActivity.this,MeterReading.class));
			}

			/*
		else{
			logger.debug("MyCameraApp","Error");
		}*/

		} catch (Exception e) {
			logger.error(TAG, Log.getStackTraceString(e));
			UtilMaster.show(
					CameraActivity.this,
					"Sorry..! Error",
					0,
					"Consumer details are not correct, You can not bill for this consumer no = "
							+ UtilMaster.getMconsumer_sc_no(),
							"Ok",
							getDialogListener(CameraActivity.this,MainActivity.class)).show();
		}
	}

	@Override
	public void onBackPressed() {
		Intent intent = new Intent(CameraActivity.this, SearchActivity.class);
		finish();
		startActivity(intent);

	}

	public void onDestroy() {
		logger.info(TAG, "onDestroy");
		super.onDestroy();
		System.gc();

	}

	private static Uri getOutputMediaFileUri(){
		return Uri.fromFile(getOutputMediaFile());

	}
	private static File getOutputMediaFile(){
		File mediaStorageDir = new File(Environment.getExternalStorageDirectory(), "CESCTRM/CESCImages/");
		if (! mediaStorageDir.exists()){
			if (! mediaStorageDir.mkdirs()){
				logger.error(TAG, "failed to create directory");
				return null;
			}
		}
		Calendar c = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String formattedDate = df.format(c.getTime());
		String imagename = formattedDate.replace("/", "");
		imagename = UtilMaster.getmGLogin_SiteCode() + "_" + UtilMaster.getmGLogin_MRCode() + "_"+ UtilMaster.mconsumer_sc_no + "_" + imagename + ".PNG";
		UtilMaster.mImagePath = imagename;
		File mediaFile = new File(mediaStorageDir.getPath() + File.separator +imagename );
		return mediaFile;
	}








	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		try {
			Log.d("MyCameraApp", requestCode +" && resultCode:  Activity.RESULT_CANCELED: "+ Activity.RESULT_CANCELED + "resultCode : "+ resultCode); 
			if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK)
			{
				int Ltariff_code_i = 0;
				try {
					Ltariff_code_i = Integer.parseInt(UtilMaster.mtariff.trim());
				} catch (Exception e) {
					logger.error(TAG, Log.getStackTraceString(e));
				}

				// By shivanand
				
				if(Ltariff_code_i == 1  /*|| Ltariff_code_i == 111 || Ltariff_code_i == 112 || Ltariff_code_i == 113 || Ltariff_code_i == 114*/  )
				{
					calculationOtherThenNormal();

				}else{
					if ((!(UtilMaster.getMpresentmeterstatus().equals("1") || UtilMaster.getMpresentmeterstatus().equals("5")))
							|| (UtilMaster.mpreviousreadingstatus.trim().equalsIgnoreCase("5") && UtilMaster.getMpresentmeterstatus().equalsIgnoreCase("5")) )
						calculationOtherThenNormal();
					else
						startActivity(new Intent(CameraActivity.this,MeterReading.class));
				}
			}else if (requestCode == CAMERA_REQUEST && resultCode == RESULT_CANCELED)
			{
				Intent intent = new Intent(CameraActivity.this, Meterobservation.class);
				finish();
				startActivity(intent);
			}

			else{
				logger.debug("MyCameraApp", requestCode +" && resultCode:");
			}

		} catch (Exception e) {
			logger.error(TAG, Log.getStackTraceString(e));
			UtilMaster.show(
					CameraActivity.this,
					"Sorry..! Error",
					0,
					"Consumer details are not correct, You can not bill for this consumer no = "
							+ UtilMaster.getMconsumer_sc_no(),
							"Ok",
							getDialogListener(CameraActivity.this,MainActivity.class)).show();
		}
	} 




	/**
	 * Check the SDCard is mounted on device
	 * 
	 * @param path
	 *            of image file
	 * @throws IOException
	 */
	public void onPause() {
		logger.info(TAG, "onPause");
		//MasterLibraryFunction.collectLogs1(TAG+ " onPause()");
		super.onPause();

	}

	void checkForSDCardPresence(String path) throws IOException {
		String state = android.os.Environment.getExternalStorageState();
		if (!state.equals(android.os.Environment.MEDIA_MOUNTED)) {
			System.out
			.println("Plz insert sdcard other wise image won't stored");
			// Toast.makeText(this,
			// "Plz insert sdcard other wise image won't stored",
			// Toast.LENGTH_SHORT).show();
			throw new IOException("SD Card is not mounted.  It is " + state
					+ ".");
		}

		// make sure the directory we plan to store the recording is exists
		File directory = new File(path).getParentFile();
		if (!directory.exists() && !directory.mkdirs()) {
			throw new IOException("Path to file could not be created.");
		}
	}

	/*private String makePath(String path) {
			if (!path.startsWith("/")) {
				path = "/" + path;
			}
			return Environment.getExternalStorageDirectory().getAbsolutePath()
					+ path;
		}*/

	protected void onResume() {
		logger.info(TAG, "onResume Activity.RESULT_CANCELED : "+ Activity.RESULT_CANCELED);
		//MasterLibraryFunction.collectLogs1(TAG+ " onResume() Activity.RESULT_CANCELED"+ Activity.RESULT_CANCELED);
		//button.setEnabled(true);
		super.onResume();
	}

	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
	}

	protected void onStop() {
		logger.info(TAG, "onStop");
		//MasterLibraryFunction.collectLogs1(TAG+ " onStop()");
		super.onStop();
	}


	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			/*if (consumer.whichactivity == 1) {
					Intent intent = new Intent(CamaraView.this, FirstActivity.class);
					startActivity(intent);
				} else {
					Intent intent = new Intent(CamaraView.this,	MeterObservation.class);
					startActivity(intent);
				}*/
			Intent intent = new Intent(CameraActivity.this,	Meterobservation.class);
			startActivity(intent);
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}



	public void calculationOtherThenNormal() throws Exception {
		int Ltariff_code_i_  = Integer.parseInt(UtilMaster.mtariff.trim()) ;
		MasterLibraryFunction calculation = new MasterLibraryFunction(CameraActivity.this, UtilMaster.mtariff.trim());

		if(UtilMaster.getMpresentmeterstatus().equals("6")){
			UtilMaster.mconsumption = "0";
		} 


		 else if(UtilMaster.getMpresentmeterstatus().equals("2") || UtilMaster.mpreviousreadingstatus.equals("2") || UtilMaster.getMpresentmeterstatus().equals("9"))

		// BY SHIVANAND
		/*else if(UtilMaster.getMpresentmeterstatus().equals("2") || UtilMaster.mpreviousreadingstatus.equals("2"))*/
		{
			/*---------DL COUNT LOGIC--------------*/
			//float bm = Float.parseFloat(UtilMaster.mno_of_months_issued);
			//float dl_count = Float.parseFloat(UtilMaster.mdl_count.trim());
			float dl_BM = 1;
			/*---------DL COUNT LOGIC--------------
			 * if(bm > dl_count )
				dl_BM = bm - dl_count ;*/




			float average = ( dl_BM ) * ( Float.parseFloat(UtilMaster.maverage_consumption.trim()) );

			/*UtilMaster.mconsumption = ""+ (  MasterLibraryFunction.doubRound(average, 0) * Float.parseFloat(UtilMaster.mmeter_constant.trim()));
			
			*/
			
			// MODIFIED ON 07-04-2016 REMOVED METERCONST
			
		    UtilMaster.mconsumption = ""+ (  MasterLibraryFunction.doubRound(average, 0));
			
			
			
			UtilMaster.mno_of_months_issued = String.valueOf(dl_BM) ;
		}else {
			/*UtilMaster.mconsumption = ""+ (MasterLibraryFunction.doubRound(	(Float.parseFloat(UtilMaster.maverage_consumption.trim()) *Float.parseFloat(UtilMaster.mno_of_months_issued)), 0) * Float.parseFloat(UtilMaster.mmeter_constant.trim()));
		*/
		

			// MODIFIED ON 07-04-2016 REMOVED METERCONST
			
			
			UtilMaster.mconsumption = ""+ (MasterLibraryFunction.doubRound(	(Float.parseFloat(UtilMaster.maverage_consumption.trim()) *Float.parseFloat(UtilMaster.mno_of_months_issued)), 0));
			
		
		}

		// by shivanand
		
		if(Ltariff_code_i_ == 1 /*||   Ltariff_code_i_ == 111 || Ltariff_code_i_ == 112 || Ltariff_code_i_ == 113 || Ltariff_code_i_ == 114  */)
		{
			UtilMaster.mconsumption = "0";
		}else if(Ltariff_code_i_ == 2){


			/*By shivanad DATE:28-11-2015*/

			/*------------- LT1M If Avg <= 18 EC=0 BY GURU HR DATE:14-04-2015 -----------------
			if(Float.parseFloat(UtilMaster.maverage_consumption.trim()) <= (float)18)
				UtilMaster.mconsumption = "0";*/
		}

		UtilMaster.m_previousReading = (int)Float.parseFloat(UtilMaster.getMprevious_reading().trim());
		UtilMaster.m_presentReading = (int)Float.parseFloat(UtilMaster.getMprevious_reading().trim());

		/*TRIVECTOR METER -- MAKING PF READING AND BMD READING TO ZERO */
		UtilMaster.mbmd_reading = "0";
		UtilMaster.mpf_reading = "0";
		UtilMaster. mckwhlkwh = 	"0";

		/*FOR CONSUMPION CHECK POINT*/

		if(Float.parseFloat(UtilMaster.mconsumption.trim()) >= (float)0){
			int result = calculation.calculate();
			if (result > 0) {
				move_inten(CameraActivity.this, PrintBillAction.class);
			} else {
				UtilMaster
				.show(CameraActivity.this,
						"Error..!",
						0,
						"Sorry..!\n some problem has occurred, Consumer data is missing or please try again",
						"Ok",
						getDialogListener(CameraActivity.this,
								MainActivity.class));
			}
		}else{
			logger.info("Error :\n CONSUMPION == "+ UtilMaster.mconsumption);
			UtilMaster.show(
					CameraActivity.this,
					"Sorry..!",
					0,
					"Consumer details are not correct (Consumpion < 0), You can not bill for this consumer no = "
							+ UtilMaster.getMconsumer_sc_no(),
							"Ok",
							getDialogListener(CameraActivity.this,
									MainActivity.class)).show();
		}
	}

	public void move_inten( Context context , Class class1 ) {
		Intent intent = new Intent(context,class1);
		//	mEnabled=false;      
		startActivity(intent);
	}
	public android.content.DialogInterface.OnClickListener getDialogListener(
			final Context context, final Class class1) {

		return new android.content.DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				move_inten(context, class1);
			}
		};

	}
}
