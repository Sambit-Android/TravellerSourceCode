package com.bcits.bsmartbilling;

import Utils.Logger;
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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Meterobservation extends Activity {

	static Logger logger ;
	private static final String TAG = "Meterobservation";
	Button btn_nxt ,  btn_bacK;
	TextView txtPreviousMtrStatus  , presentmeterstatus1 , title;
	LinearLayout phoneLayout;
	
	TextView txtConsName , txtRrNo , txtAdd , txtTariff ;
	EditText edPhoneNumber;
	Spinner  presntmeterstatus; 
	CheckBox cbPhone;
	
	boolean  dbstatus = false , mtrStatus = false ;
	MasterLibraryFunction libraryFunction ;
	String phoneNumber;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.activity_meterobservation);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,	R.layout.window_title);

		
		try {
			if(logger == null){
	    		logger = MasterLibraryFunction.getlogger(getApplicationContext(), TAG);
	    		}
			logger.info("In Side onCreate()");
		} catch (Exception e) {
			 logger.error("logger is NULL");
		}
		logger.info("In Side onCreate()");
		btn_bacK = (Button) findViewById(R.id.titleBack);
		title = (TextView) findViewById(R.id.title);
		title.setText(getString(R.string.cus_title_name)
				+ MasterLibraryFunction.getVersion(Meterobservation.this));

		txtPreviousMtrStatus = (TextView) findViewById(R.id.previousmeterstatusval);
		presentmeterstatus1 = (TextView) findViewById(R.id.presentmeterstatus1);
		presntmeterstatus = (Spinner) findViewById(R.id.presentmeterstatus);
		btn_nxt = (Button) findViewById(R.id.buttonnext);
		
		txtConsName = (TextView)findViewById(R.id.txtCons_name);
		txtRrNo = (TextView)findViewById(R.id.txtrr_no);
		txtAdd = (TextView)findViewById(R.id.txt_address);
		txtTariff = (TextView)findViewById(R.id.txt_tariff);
		edPhoneNumber = (EditText) findViewById(R.id.phonenumber);
		cbPhone = (CheckBox) findViewById(R.id.checkBox1);
		phoneLayout =  (LinearLayout) findViewById(R.id.phonenumberlayout);
		
	}

	@Override
	protected void onStart() {
		super.onStart();
		
		
		
		
		
	//	Toast.makeText(getApplicationContext(), UtilMaster.deviceInfo, Toast.LENGTH_LONG).show();;
		
		
		
		
	if(UtilMaster.mdoorlockavg.trim().equalsIgnoreCase("1")){
			
			phoneLayout.setVisibility(View.GONE);
			cbPhone.setChecked(true);
			
			
		}
		
		
		cbPhone.setOnCheckedChangeListener(new OnCheckedChangeListener()
		{
		    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
		    {
		        if ( isChecked )
		        {
		            // perform logic
		        	phoneLayout.setVisibility(View.GONE);
		        	
		        }
		        else{
		        	
		        	phoneLayout.setVisibility(View.VISIBLE);
		        	
		        }
		        
		        

		    }
		});
		
		
		
		
		
		if( (UtilMaster.mGLogin_MRCode.equalsIgnoreCase("")) ||  UtilMaster.mGLogin_SiteCode.equalsIgnoreCase("") ){
			 logger.error("Session timeout ..! Please Login again UtilMaster.mGLogin_MRCode OR UtilMaster.mGLogin_SiteCode is NULL");
			new AlertDialog.Builder(Meterobservation.this)
							.setTitle("Sorry..!")
							.setMessage("Session timeout ..! Please Login again")
					   .setPositiveButton("OK", new DialogInterface.OnClickListener()
					   {
						   public void onClick(DialogInterface dialog,int which) 
						   {
							   startActivity(new Intent(Meterobservation.this,Login.class));
							//   return;
						   }
					   }).show();
		} else {
			setPreviousStatus();
		}
		
		
		
		
		
		
		
		
		
		
		
		
		btn_bacK.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
				move_inten(Meterobservation.this, SearchActivity.class);
			}
		});

		btn_nxt.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				try {

					
					phoneNumber = "0";// by default
					boolean allowNext = false;
					
					
					if(!cbPhone.isChecked()){
						
						phoneNumber = edPhoneNumber.getText().toString().trim();
						if(phoneNumber.equals("") || phoneNumber.equals(" ") || phoneNumber == null ){
							
							Toast.makeText(Meterobservation.this, "Please Enter Phone Number", Toast.LENGTH_SHORT).show();
							
						}else if(phoneNumber.length() <10){
							
							
							Toast.makeText(Meterobservation.this, "Enter valid 10 digit Phone Number", Toast.LENGTH_SHORT).show();
						}
						else{
							allowNext = true;
							
						}
						
					}
					else{
						phoneNumber = "0";
						allowNext = true;
						
					}
					
					
					
					
					
					
					
					
					if(allowNext){
						
						
						
						UtilMaster.phoneNumber = phoneNumber;
						
						
						
						libraryFunction = new MasterLibraryFunction(Meterobservation.this);
						if (libraryFunction.isdataValid()) {

							// / DO YOUR FUNCTION
							if (!mtrStatus) {
								UtilMaster.setMpresentmeterstatus(setPresentStatus(presntmeterstatus.getSelectedItem().toString()));
							}
							// Lstatus_id_c ==1 || Lstatus_id_c ==3 || Lstatus_id_c
							// ==4 || Lstatus_id_c ==5 || Lstatus_id_c ==6 ||
							// Lstatus_id_c ==7
							System.out
									.println(" UtilMaster.getMpresentmeterstatus() : "
											+ UtilMaster.getMpresentmeterstatus()
											+ "  : presntmeterstatus.getSelectedItem().toString() : "
											+ presntmeterstatus.getSelectedItem()
													.toString()

									);

							// ///////////// expect normal (1) and Diss
							// connections(5) for all other status..
							// present_reading= prevsious_reading

							if ((!(UtilMaster.getMpresentmeterstatus().equals("1") || UtilMaster	.getMpresentmeterstatus().equals("5")))
								/*|| (UtilMaster.mpreviousreadingstatus.trim().equalsIgnoreCase("5") && UtilMaster.getMpresentmeterstatus().equalsIgnoreCase("5"))*/ ) 
							{
								UtilMaster.setMpresentreading(UtilMaster.getMprevious_reading().trim());

							}
							
							//CR8) DL->IDLE (ADJ amount should be effected)-DONE   & for DL case dl_adj_amount should be zero
							
							// By shivanand
							
							/*if( (UtilMaster.mpreviousreadingstatus.equals("2")&& (UtilMaster.getMpresentmeterstatus().equals("1") || UtilMaster.getMpresentmeterstatus().equals("6") )))
							{
								UtilMaster.mdl_adj = UtilMaster.mdl_adj.trim() ;
							}else{
								UtilMaster.mdl_adj = "0";
							}*/

							move_inten(Meterobservation.this, CameraActivity.class);

						} else {
							new AlertDialog.Builder(Meterobservation.this)
									.setTitle("Info")
									.setMessage(
											"Mobile Date has been changed please set the Date correctly OR Download fresh data to Procced next ")
									.setPositiveButton("Set Date",
											new DialogInterface.OnClickListener() {
												public void onClick(
														DialogInterface arg0,
														int arg1) {

													startActivity(new Intent(
															android.provider.Settings.ACTION_DATE_SETTINGS));
												}
											}).show();
						}

						/*
						 * /////////////old code ///////////////////////
						 * 
						 * 
						 * if (UtilMaster.getMpresentmeterstatus().equals("1") ||
						 * UtilMaster.getMpresentmeterstatus().equals("3") ||
						 * UtilMaster.getMpresentmeterstatus().equals("4") ||
						 * UtilMaster.getMpresentmeterstatus().equals("5") ||
						 * UtilMaster.getMpresentmeterstatus().equals("6") ||
						 * UtilMaster.getMpresentmeterstatus().equals("7")) {
						 * 
						 * if (UtilMaster.getMpresentmeterstatus().equals("1")) {
						 * move_inten(Meterobservation.this, CameraActivity.class);
						 * } else { //move_inten(MeterDetails.this,
						 * PrintBill.class); //calculationOtherthenNormal(); } }
						 * else { UtilMaster.showToast(Meterobservation.this,
						 * R.string.invalidmtrstatus).show(); }
						 */// /////////////////////////////
						
						
						
						
					}
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
				

				} catch (Exception e) {
					logger.error(Log.getStackTraceString(e));
					UtilMaster.show(
							Meterobservation.this,
							"Sorry..! Error",
							0,
							"Consumer details are not correct, You can not bill for this consumer no = "
									+ UtilMaster.getMconsumer_sc_no(),
							"Ok",
							getDialogListener(Meterobservation.this,
									SearchActivity.class)).show();
				}

			}
		});
	}
	
	@Override
	public void onBackPressed() {
		finish();
		move_inten(Meterobservation.this, SearchActivity.class);
		//super.onBackPressed();
		
		
	}
	
	
	
	public void setConsDetails(){
		try {
			
		
		txtConsName.setText(UtilMaster.mconsumer_name) ;
		txtRrNo .setText(UtilMaster.mconsumer_sc_no);
		
		
		if(UtilMaster.maddress.trim().equals("")
				|| 
				UtilMaster.maddress.trim().equals(" ")
				||
				UtilMaster.maddress.trim().equalsIgnoreCase("null")
				||
				UtilMaster.maddress == null
				){
			
			txtAdd.setText("NA");
			
		}else{
			txtAdd.setText(UtilMaster.maddress);
		}
		
		
		
		
		txtTariff.setText(UtilMaster.mtariffdesc) ;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void setPreviousStatus() {
		mtrStatus = false ;
		
		
		/*
 ///////////////// Following are the status
		--------------------------------

		// 1 - NORMAL
		// 2 - Door Lock  - 
		// 3 - MNR
		// 4 - Direct Connection
		// 5 - Diss Connection
		// 6 - IDLE
		// 7 - MB- MeterSeal Broken
		// 8 - MS - Meter Sticky
		// 9 - NV -Not Visible	
*/		
///////////////// for MNR (3) and DC (4) .. present status would be same as prev status
		
		
		if(UtilMaster.mpreviousreadingstatus.equals("1")){
			txtPreviousMtrStatus.setText(R.string.a);
		}else if(UtilMaster.mpreviousreadingstatus.equals("2")){
			txtPreviousMtrStatus.setText(R.string.b);
		}else if(UtilMaster.mpreviousreadingstatus.equals("3")){
			
			txtPreviousMtrStatus.setText(R.string.c);
			presentmeterstatus1.setVisibility(View.VISIBLE);
			presentmeterstatus1.setText(R.string.c);
			presntmeterstatus.setVisibility(View.INVISIBLE);
			UtilMaster.setMpresentmeterstatus("3");
			mtrStatus = true ;
			
			
		
		}else if(UtilMaster.mpreviousreadingstatus.equals("4")){
			txtPreviousMtrStatus.setText(R.string.d);
			presentmeterstatus1.setVisibility(View.VISIBLE);
			presentmeterstatus1.setText(R.string.d);
			presntmeterstatus.setVisibility(View.INVISIBLE);
			UtilMaster.setMpresentmeterstatus("4");
			mtrStatus = true ;
		}else if(UtilMaster.mpreviousreadingstatus.equals("5")){
			txtPreviousMtrStatus.setText(R.string.e);
		}else if(UtilMaster.mpreviousreadingstatus.equals("6")){
			txtPreviousMtrStatus.setText(R.string.f);
		}else if(UtilMaster.mpreviousreadingstatus.equals("7")){
			
			txtPreviousMtrStatus.setText(R.string.g);
			presentmeterstatus1.setVisibility(View.VISIBLE);
			presentmeterstatus1.setText(R.string.g);
			presntmeterstatus.setVisibility(View.INVISIBLE);
			UtilMaster.setMpresentmeterstatus("7");
			mtrStatus = true ;
					
		}else if(UtilMaster.mpreviousreadingstatus.equals("8")){
			
			
			txtPreviousMtrStatus.setText(R.string.h);
			presentmeterstatus1.setVisibility(View.VISIBLE);
			presentmeterstatus1.setText(R.string.h);
			presntmeterstatus.setVisibility(View.INVISIBLE);
			UtilMaster.setMpresentmeterstatus("8");
			mtrStatus = true ;
			
		}else if(UtilMaster.mpreviousreadingstatus.equals("9")){
			/*txtPreviousMtrStatus.setText(R.string.i);
			presentmeterstatus1.setVisibility(View.VISIBLE);
			presentmeterstatus1.setText(R.string.i);
			presntmeterstatus.setVisibility(View.INVISIBLE);
			UtilMaster.setMpresentmeterstatus("9");
			mtrStatus = true ;*/
			
			
			// new change as shafi
			
			txtPreviousMtrStatus.setText(R.string.i);
			
			
			
		}else {
			txtPreviousMtrStatus.setText("-");
		}
		setConsDetails();
		
		if(UtilMaster.mtariff.trim().equals("1"))
		{
			/*String mtrStatus[] = {"DC","DL"} ;*/
			ArrayAdapter<CharSequence> adapter1 = ArrayAdapter	.createFromResource(this, R.array.meter_status_unmeterd,	android.R.layout.simple_spinner_item);
			adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			presntmeterstatus.setAdapter(adapter1);
		}
		
		
		if(        UtilMaster.mtariff.trim().equals("2")
				|| UtilMaster.mtariff.trim().equals("54")
				|| UtilMaster.mtariff.trim().equals("204")
				|| UtilMaster.mtariff.trim().equals("104")
				|| UtilMaster.mtariff.trim().equals("208")
				
				
				
				)
		{
			/*String mtrStatus[] = {"DC","DL"} ;*/
			ArrayAdapter<CharSequence> adapter1 = ArrayAdapter	.createFromResource(this, R.array.meter_status_lt1lt2fllt3ol,	android.R.layout.simple_spinner_item);
			adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			presntmeterstatus.setAdapter(adapter1);
		}
		
		
		
		
		
	}

	
	private String setPresentStatus(String value) {
		String result = null ;
		System.out.println(" value.equals(R.string.a) : "+value.equals(R.string.a) +"R.string.a : "+getString(R.string.a));
		if(value.equals(getString(R.string.a))){
			result = "1";
		}else if(value.equals(getString(R.string.b))){
			result = "2";
		}else if(value.equals(getString(R.string.c))){
			result = "3";
		}else if(value.equals(getString(R.string.d))){
			result = "4";
		}else if(value.equals(getString(R.string.e))){
			result = "5";
		}else if(value.equals(getString(R.string.f))){
			result = "6";
		}else if(value.equals(getString(R.string.g))){
			result = "7";
		}else if(value.equals(getString(R.string.h))){
			result = "8";
		}else if(value.equals(getString(R.string.i))){
			result = "9";
		}else {
			result = "0";
			//txtPreviousMtrStatus.setText("-");
		}
		return result ;
	}
	

	
	public android.content.DialogInterface.OnClickListener getDialogListener(
			final Context context, @SuppressWarnings("rawtypes") final Class class1) {

		return new android.content.DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				move_inten(context, class1);
			}
		};

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
}
