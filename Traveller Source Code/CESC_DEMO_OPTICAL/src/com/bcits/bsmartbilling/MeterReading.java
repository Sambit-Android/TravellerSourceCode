package com.bcits.bsmartbilling;

import bcits.rf.catcher.RfLibMain;
import Utils.FilePropertyManager;
import Utils.Logger;
import activity.dlms.lib.DlmsMain;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MeterReading extends Activity {

	static Logger logger ;
	private static String TAG = "MeterReading";
	Button btn_nxt, btn_back ;
	TextView txt_previousReading , title;
	EditText edt_presentReading , edt_pf_reading , edt_bmd_reading , edt_ckwh_reading;

	int previousReading = 0 , presentReading = 0 , units = 0 ;
	int abNormalalertCount, subNormalAlertCount;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.activity_meterreading);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,	R.layout.window_title);


		btn_back = (Button)findViewById(R.id.titleBack);
		btn_nxt = (Button)findViewById(R.id.button_calc);
		title = (TextView )findViewById(R.id.title);
		title.setText(getString(R.string.cus_title_name)+MasterLibraryFunction.getVersion(MeterReading.this));
		txt_previousReading = (TextView)findViewById(R.id.previousreadingval);
		edt_presentReading = (EditText)findViewById(R.id.presentreadin);
		edt_pf_reading   = (EditText)findViewById(R.id.pf_reading);

		edt_bmd_reading   = (EditText)findViewById(R.id.bmd_reading);
		edt_ckwh_reading = (EditText)findViewById(R.id.ckwh_reading);
		try {
			if(logger == null){
				logger = MasterLibraryFunction.getlogger(getApplicationContext(), TAG);
			}
			logger.info("In Side onCreate()");
		} catch (Exception e) {
			logger.error("logger is NULL");
		}
		logger.info("In Side onCreate()");

		
		abNormalalertCount = 0;
		subNormalAlertCount = 0;

	}

	@Override
	protected void onResume() {
		super.onResume();
		
		if(Meterobservation.isThroughRF || Meterobservation.isThroughBluetooth || Meterobservation.isThroughDLMS) {
			//TODO
			
			if(Meterobservation.isThroughRF ) {
				edt_presentReading.setText(RfLibMain.getMeterReading());
			}else if(Meterobservation.isThroughDLMS) {
				edt_presentReading.setText(DlmsMain.getMeterReading());
			}else if(Meterobservation.isThroughBluetooth) {
				edt_presentReading.setText("0000");
			}
			
			edt_presentReading.setEnabled(false); // DISABLE THE EDITTEXT. IN ANY CASE TO ENTER PF AND OTHER VALUES
			btn_nxt.performClick();
		}
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		try {

			txt_previousReading.setText(UtilMaster.getMprevious_reading().trim());
			previousReading = Integer.parseInt(UtilMaster.getMprevious_reading().trim());

			if( (UtilMaster.mGLogin_MRCode.trim().equalsIgnoreCase("")) ||  UtilMaster.mGLogin_SiteCode.trim().equalsIgnoreCase("") ){
				logger.error("Session timeout ..! Please Login again UtilMaster.mGLogin_MRCode OR UtilMaster.mGLogin_SiteCode is NULL");
				new AlertDialog.Builder(MeterReading.this)
				.setTitle("Sorry..!")
				.setMessage("Session timeout ..! Please Login again")
				.setPositiveButton("OK", new DialogInterface.OnClickListener()
				{

					public void onClick(DialogInterface dialog,int which) 
					{

						startActivity(new Intent(MeterReading.this,Login.class));
						//   return;
					}
				}).show();
			}

			if(UtilMaster.mtrivector.trim().equalsIgnoreCase("0")){
				edt_pf_reading.setEnabled(false);
				edt_bmd_reading.setEnabled(false);
				edt_ckwh_reading.setEnabled(false);
			}else{
				edt_pf_reading.setEnabled(true);
				edt_bmd_reading.setEnabled(true);
				edt_ckwh_reading.setEnabled(true);
			}

			btn_back.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					finish();
					move_inten(MeterReading.this, SearchActivity.class);
				}
			});



			btn_nxt.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					
					//TODO
					abNormalalertCount = abNormalalertCount + 1;
					subNormalAlertCount = subNormalAlertCount + 1;
					

					Log.e(TAG, "setOnClickListener");
					try {


						String temp_prstRdg = edt_presentReading.getText().toString().trim();
						if(temp_prstRdg.equalsIgnoreCase("")){
							UtilMaster.show(
									MeterReading.this,
									"Info",
									0,
									"Please enter Present reading",
									"Ok",null).show();
						}
						else{



							temp_prstRdg = edt_presentReading.getText().toString().trim();
							if(UtilMaster.mtrivector.trim().equalsIgnoreCase("1")){
								String temp_pf = edt_pf_reading.getText().toString().trim();
								String temp_bmd = edt_bmd_reading.getText().toString().trim();
								String temp_ckwh = edt_ckwh_reading.getText().toString().trim(); 

								String fRegularExpression = "^\\d{1,1}(\\.\\d{1,4})?$";
								String fRegularExpression_pf = "^[0]{1,1}(\\.\\d{1,3})?$";



								/*  	^[0]+(\.\d+)?   */
								logger.debug("temp_pf READING:"+	temp_pf   );
								logger.debug("temp_bmd READING:"+	temp_bmd   );		

								if(temp_pf.equalsIgnoreCase("")){
									UtilMaster.show(
											MeterReading.this,
											"Info",
											0,
											"Trivector meter. Please enter PF reading",
											"Ok",null).show();
								}else if(temp_bmd.equalsIgnoreCase("")){
									UtilMaster.show(
											MeterReading.this,
											"Info",
											0,
											"Trivector meter. Please enter BMD reading",
											"Ok",null).show();
								}else if(!temp_pf.matches(fRegularExpression_pf)){
									UtilMaster.show(
											MeterReading.this,
											"Info",
											0,
											"Please enter PF reading, 0 to 0.999",
											"Ok",null).show();
								}else if(!temp_bmd.matches(fRegularExpression)){
									UtilMaster.show(
											MeterReading.this,
											"Info",
											0,
											"Please enter BMD reading, 0 to 9.9999",
											"Ok",null).show();
								}else if(((float)0 > Float.parseFloat(temp_pf)) || (Float.parseFloat(temp_pf) >= (float)1) ){
									UtilMaster.show(
											MeterReading.this,
											"Info",
											0,
											"Please enter PF reading, 0 to 0.999",
											"Ok",null).show();
								}else if(((float)0 > Float.parseFloat(temp_bmd)) || (Float.parseFloat(temp_bmd) >= (float)10) ){
									UtilMaster.show(
											MeterReading.this,
											"Info",
											0,
											"Please enter BMD reading, 0 to 9.9999",
											"Ok",null).show();
								}else if(temp_ckwh.equalsIgnoreCase("")){
									UtilMaster.show(
											MeterReading.this,
											"Info",
											0,
											"Trivector meter. Please enter CKWH/LKWH reading",
											"Ok",null).show();
								}
								else{
									logger.debug("temp_pf READING:"+	temp_pf   );
									logger.debug("temp_bmd READING:"+	temp_bmd   );	
									UtilMaster.mbmd_reading =     	temp_bmd;
									UtilMaster. mpf_reading    =   temp_pf;
									UtilMaster. mckwhlkwh = temp_ckwh ;
									readingManger(temp_prstRdg);
								}
							}else{




								UtilMaster.mckwhlkwh = "0";
								UtilMaster.mbmd_reading = "0";

								//BY SHIVANAND
								//UtilMaster.mpf_reading = "0";
								readingManger(temp_prstRdg);
							}
						}
					} catch (Exception e) {
						logger.error(Log.getStackTraceString(e));
						UtilMaster.show(MeterReading.this, "Error..!", 0, "Sorry..!\n some problem has occurred, Consumer data is missing or please try again", "Ok", getDialogListener(MeterReading.this, MainActivity.class));

					}
				}
			});
		} catch (Exception e) {
			logger.error(Log.getStackTraceString(e));
			UtilMaster.show(
					MeterReading.this,
					"Error",
					0,
					"Consumer details are not correct, You can not bill for this consumer no = "
							+ UtilMaster.getMconsumer_sc_no(),
							"Ok",
							getDialogListener(MeterReading.this,
									MainActivity.class)).show();
		}

	}


	@Override
	public void onBackPressed() {
		//super.onBackPressed();
		move_inten(MeterReading.this, Meterobservation.class);
	}
	public android.content.DialogInterface.OnClickListener getDialogListener(final Context context, @SuppressWarnings("rawtypes") final Class class1) {

		return new android.content.DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				move_inten(context, class1);
			}
		};

	}


	private void readingManger(String temp_prstRdg)
			throws Exception {
		presentReading = Integer.parseInt(temp_prstRdg);

		logger.debug("presentReading : "+	presentReading   );
		logger.debug("PF READING:"+	UtilMaster. mpf_reading   );
		logger.debug("BMD READING:"+	UtilMaster. mbmd_reading   );

		long tempConsumtion = getConsumtion(String.valueOf(presentReading), String.valueOf(previousReading),Float.parseFloat(UtilMaster.getMmeter_constant().trim()));

		System.out.println("presentReading :"+String.valueOf(presentReading));
		System.out.println("previous reading :"+String.valueOf(previousReading) );
		System.out.println("tempConsumtion :"+tempConsumtion);
		
		

		if(presentReading < previousReading){

			//UtilMaster.showToast(MeterReading.this, "Present reading should be grater than previous reading").show();
			new AlertDialog.Builder(MeterReading.this)
			.setTitle("Info").setMessage("Present Reading is less then Previous Reading.\nIs Dial Rotated.?")
			.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					try {
						calculationForNormal(String.valueOf(presentReading), String.valueOf(previousReading),Float.parseFloat(UtilMaster.getMmeter_constant().trim()));
					} catch (NumberFormatException e) {
						e.printStackTrace();
						UtilMaster.show(MeterReading.this, "Error..!", 0, "Sorry..!\n some problem has occurred, Consumer data is missing or please try again", "Ok", getDialogListener(MeterReading.this, MainActivity.class));
					} catch (Exception e) {
						e.printStackTrace();
						UtilMaster.show(MeterReading.this, "Error..!", 0, "Sorry..!\n some problem has occurred, Consumer data is missing or please try again", "Ok", getDialogListener(MeterReading.this, MainActivity.class));
					}
				}
			}).setNegativeButton("No", null)
			.show();


		}else if (presentReading == previousReading){


			//UtilMaster.showToast(MeterReading.this, "Present reading should be grater than previous reading").show();

			/*if(UtilMaster.getMpresentmeterstatus().equals("5")){*/

				new AlertDialog.Builder(MeterReading.this)
				.setTitle("Info").setMessage("consumption = 0 ...? Yes/No")
				.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						try {
							/*IDLE CASE */
							//	UtilMaster.setMpresentmeterstatus("6");

							calculationForNormal(String.valueOf(presentReading), String.valueOf(previousReading),Float.parseFloat(UtilMaster.getMmeter_constant().trim()));


						} catch (NumberFormatException e) {
							logger.error(Log.getStackTraceString(e));
							e.printStackTrace();
							UtilMaster.show(MeterReading.this, "Error..!", 0, "Sorry..!\n some problem has occurred, Consumer data is missing or please try again", "Ok", getDialogListener(MeterReading.this, MainActivity.class));
						} catch (Exception e) {
							logger.error(Log.getStackTraceString(e));
							e.printStackTrace();
							UtilMaster.show(MeterReading.this, "Error..!", 0, "Sorry..!\n some problem has occurred, Consumer data is missing or please try again", "Ok", getDialogListener(MeterReading.this, MainActivity.class));
						}
					}
				}).setNegativeButton("No", null)
				.show();
			/*}else{
				new AlertDialog.Builder(MeterReading.this)
				.setTitle("Info").setMessage("For Normal case(consumption = 0) is not allowed")
				.setPositiveButton("Ok", null)
				.show();
			}*/


		}
		
		else if((tempConsumtion > Float.valueOf(UtilMaster.getMaverage_consumption())) && (tempConsumtion < 3 * Float.valueOf(UtilMaster.getMaverage_consumption()))){

			
			if(subNormalAlertCount < 3){
				
				
				new AlertDialog.Builder(MeterReading.this)
				.setTitle("Info").setMessage("Subnormal Consumtion")
				.setPositiveButton("Ok", null)
				.show();
				
			}else{
				
				
				new AlertDialog.Builder(MeterReading.this)
				.setTitle("Info").setMessage("Subnormal Consumtion")
				.setPositiveButton("Allow", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						try {
							calculationForNormal(String.valueOf(presentReading), String.valueOf(previousReading),Float.parseFloat(UtilMaster.getMmeter_constant().trim()));
						} catch (NumberFormatException e) {
							e.printStackTrace();
							UtilMaster.show(MeterReading.this, "Error..!", 0, "Sorry..!\n some problem has occurred, Consumer data is missing or please try again", "Ok", getDialogListener(MeterReading.this, MainActivity.class));
						} catch (Exception e) {
							e.printStackTrace();
							UtilMaster.show(MeterReading.this, "Error..!", 0, "Sorry..!\n some problem has occurred, Consumer data is missing or please try again", "Ok", getDialogListener(MeterReading.this, MainActivity.class));
						}
					}})
				.setNegativeButton("ReEnter", null)
					
				.show();
				
			}
			
			

		}
		else if(tempConsumtion > 3 * Float.valueOf(UtilMaster.getMaverage_consumption())){

			
			
			if(abNormalalertCount < 3){
				
				new AlertDialog.Builder(MeterReading.this)
				.setTitle("Info").setMessage("Abnormal Consumtion")
				.setPositiveButton("Ok", null)
				.show();
				
				
			}
			
			else{
				
				
				
				new AlertDialog.Builder(MeterReading.this)
				.setTitle("Info").setMessage("Abnormal Consumtion")
				.setPositiveButton("Allow", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						try {
							calculationForNormal(String.valueOf(presentReading), String.valueOf(previousReading),Float.parseFloat(UtilMaster.getMmeter_constant().trim()));
						} catch (NumberFormatException e) {
							e.printStackTrace();
							UtilMaster.show(MeterReading.this, "Error..!", 0, "Sorry..!\n some problem has occurred, Consumer data is missing or please try again", "Ok", getDialogListener(MeterReading.this, MainActivity.class));
						} catch (Exception e) {
							e.printStackTrace();
							UtilMaster.show(MeterReading.this, "Error..!", 0, "Sorry..!\n some problem has occurred, Consumer data is missing or please try again", "Ok", getDialogListener(MeterReading.this, MainActivity.class));
						}
					}})
				.setNegativeButton("ReEnter", null)
					
				.show();
				
				
			}
			

		}
		



		else{
			calculationForNormal(String.valueOf(presentReading), String.valueOf(previousReading),Float.parseFloat(UtilMaster.getMmeter_constant().trim()));

		}
	}





	public void calculationForNormal(  String presReading ,  String prevReading ,  float meterConstatnt ) throws Exception{


		MasterLibraryFunction calculation = new MasterLibraryFunction(MeterReading.this ,UtilMaster. mtariff.trim() );



		if(UtilMaster.getMpresentmeterstatus().equals("6")){
			/*IDLE CASE */
			/*UtilMaster.mconsumption = ""
					+ (MasterLibraryFunction.doubRound((Float.parseFloat(UtilMaster.maverage_consumption.trim())), 0)
			 * meterConstatnt);*/
			UtilMaster.mconsumption = "0";
		}else{
			UtilMaster.mconsumption = calculation.toString1( calculation.consumption(presReading, prevReading, meterConstatnt) + (Float.parseFloat(UtilMaster. mmeter_change_units_consumed.trim())*meterConstatnt )	);
		}



		UtilMaster.m_previousReading = Integer.parseInt(prevReading) ;
		UtilMaster.m_presentReading  = Integer.parseInt(presReading) ;

		UtilMaster.mpresentreading = presReading ;


		//	UtilMaster.m_arrears  
		/*FOR CONSUMPION CHECK POINT*/

		if(Float.parseFloat(UtilMaster.mconsumption.trim()) >= (float)0){
			int result = calculation.calculate();
			if(result > 0){
				move_inten(MeterReading.this, PrintBillAction.class);
			}else{
				UtilMaster.showToast(MeterReading.this, " ! result > 0").show();
			}
		}else{
			FilePropertyManager.appendLog("Error :\n CONSUMPION == "+ UtilMaster.mconsumption);
			UtilMaster.show(
					MeterReading.this,
					"Sorry..!",
					0,
					"Consumer details are not correct (Consumpion < 0), You can not bill for this consumer no = "
							+ UtilMaster.getMconsumer_sc_no(),
							"Ok",
							getDialogListener(MeterReading.this,
									MainActivity.class)).show();
		}




	}
	public void move_inten( Context context , @SuppressWarnings("rawtypes") Class class1 ) {
		Intent intent = new Intent(context,class1);
		//	mEnabled=false;      
		startActivity(intent);
	}
	public void move_inten( Context context , @SuppressWarnings("rawtypes") Class class1 , String data ) {

		Intent intent = new Intent(context,class1);
		intent.putExtra("RRno/bu", data);
		//mEnabled=false; 
		startActivity(intent);
	}




	public long getConsumtion(String presReading, String prevReading, float meterConstatnt){

		MasterLibraryFunction calculation = new MasterLibraryFunction(MeterReading.this ,UtilMaster. mtariff.trim() );
		long consumtionValue =  (long) (calculation.consumption(presReading, prevReading, meterConstatnt) + (Float.parseFloat(UtilMaster. mmeter_change_units_consumed.trim())*meterConstatnt ))	;

		return consumtionValue;
	}




}
