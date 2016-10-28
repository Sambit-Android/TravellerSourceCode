package com.bcits.bsmartbilling;

import Utils.FilePropertyManager;
import amr.usb.catcher.UsbLibMain;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.utils.DatabaseHandler;

public class AmrBiller extends Activity{
   
   public static boolean isDirectBilling=false; 
   EditText edPresentReading,edMeterNo;
   LinearLayout linearInput;
   Context context;
   MasterLibraryFunction libraryFunction ;
   int previousReading = 0 , presentReading = 0 , units = 0 ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.amr_biller);
		edPresentReading=(EditText)findViewById(R.id.edPresentReading);
		edMeterNo=(EditText)findViewById(R.id.edMeterNo);
		linearInput=(LinearLayout)findViewById(R.id.linearInput);
		context=this;
		
		if(isDirectBilling) {
			linearInput.setVisibility(View.GONE);
			startCalculation(UsbLibMain.getMeterNumber(),UsbLibMain.getMeterReading());
			return;
		}
	}
	
	public void onClickGenerateBill(View v) {
		String meterNo="",currentReading="";
		meterNo=edMeterNo.getText().toString().trim();
		currentReading=edPresentReading.getText().toString().trim();
		startCalculation(meterNo,currentReading);
	}
	
	private void startCalculation(String meterNo ,String currentReading) {

		if (meterNo.equals("") || meterNo.equals(null)) {
				Toast.makeText(getApplicationContext(),"Meter Number Not Available",Toast.LENGTH_LONG).show();
				this.finish();
				return;
		}
			try {
				libraryFunction = new  MasterLibraryFunction(context) ;
				if (libraryFunction.isdataValid()) {
					AmrBillSupporter.resetParams();
					DatabaseHandler handler = new DatabaseHandler(context);
					handler.openToRead();
					Cursor cursor ;
					boolean isdetailsSet ;

						cursor = handler.getDetailsByMeterNo(meterNo);
						handler.close();
						if (cursor != null && cursor.moveToFirst()) 
						{
							//Cursor cursor_next,Context context,MasterLibraryFunction libraryFunction,String mbatteryLevel ,String signalStrength11 ,String mmemoryTotal,String mmemoryAvailable
							isdetailsSet  = AmrBillSupporter.setConsumerDetails(cursor,context,libraryFunction,"0","0","0","0");
							cursor.close();
							if (isdetailsSet) {
								isvalidConDetails(context,currentReading);
							} else {
								Toast.makeText(getApplicationContext(),"Invalid Meter No",Toast.LENGTH_LONG).show();
								this.finish();
							}

						}else
						{
							cursor.close();
							Toast.makeText(getApplicationContext(),"Invalid Meter No",Toast.LENGTH_LONG).show();
							this.finish();
						}

				} else {
					new AlertDialog.Builder(context)
					.setTitle("Info")
					.setMessage(
							"Mobile Date has been changed please set the Date correctly OR Download fresh data to Procced next ")
							.setPositiveButton(
									"Set Date",
									new DialogInterface.OnClickListener() {
										public void onClick(
												DialogInterface arg0,
												int arg1) {
                                              startActivity(new Intent(context, MainActivity.class));
										}
									}).show();
				}

			} catch (Exception e) {
				Toast.makeText(	getApplicationContext(),"Consumer details are insufficient, You can not bill for this Consumer",Toast.LENGTH_LONG).show();
				e.printStackTrace();
				this.finish();
			}
	}
	
	public void isvalidConDetails(Context context,String currentReading){
		/*if(UtilMaster. mbilledstatus.equalsIgnoreCase("1") ){
			UtilMaster.show(context, "Info", 0, "Consumer Already Billed,Do you want to generate duplicate bill", "Yes", AmrBillSupporter.getDialogListener(context, PrintBillAction.class),"No",null).show();
		}
		else */if(UtilMaster. mtariff.trim().equals("-") || UtilMaster. mtariff.trim().equals("0")){
			UtilMaster.show(context, "Info", 0, "Tariff is Not correct for the Consumer, Kindly Download the Consumers again", "OK", AmrBillSupporter.getDialogListener(context, MainActivity.class),"No",null).show();
		}
		else if(UtilMaster. mtariffdesc.trim().equals("-")){
			UtilMaster.show(context, "Info", 0, "Tariff Details are Not correct, Kindly Download the tariff details again", "OK", AmrBillSupporter.getDialogListener(context, MainActivity.class),"No",null).show();
		}
		else
		{
			 // ENDING OF SEARCH ACTIVITY
			UtilMaster.setMpresentmeterstatus("1"); // TODO TAKEN FROM METEROBSERVATION
		    // CAMERA ACTIVITY NOTHING TAKEN
			 meterReadingActivity(currentReading); // METER READING ACTIVITY
		}
	}

	
	private void meterReadingActivity(String currentReading) {
		previousReading = Integer.parseInt(UtilMaster.getMprevious_reading().trim());
		
		if(UtilMaster.mtrivector.trim().equalsIgnoreCase("0")){
		}else{
			Toast.makeText(context, "mtrivector is not Zero", Toast.LENGTH_SHORT).show();
			return;
		}
		
		
		try { // BUTTON NEXT CLICK FROM METER READING ACTIVITY

			String temp_prstRdg = currentReading;//TODO PRESENET READING
			if(temp_prstRdg.equalsIgnoreCase("")){
				UtilMaster.show(context,"Info",0,	"Please enter Present reading",	"Ok",null).show();
			}
			else{
				if(UtilMaster.mtrivector.trim().equalsIgnoreCase("1")){
					String temp_pf = ""; //TODO 
					String temp_bmd ="";
					String temp_ckwh = "";

					String fRegularExpression = "^\\d{1,1}(\\.\\d{1,4})?$";
					String fRegularExpression_pf = "^[0]{1,1}(\\.\\d{1,3})?$";

					if(temp_pf.equalsIgnoreCase("")){
						UtilMaster.show(
								context,
								"Info",
								0,
								"Trivector meter. Please enter PF reading",
								"Ok",null).show();
					}else if(temp_bmd.equalsIgnoreCase("")){
						UtilMaster.show(
								context,
								"Info",
								0,
								"Trivector meter. Please enter BMD reading",
								"Ok",null).show();
					}else if(!temp_pf.matches(fRegularExpression_pf)){
						UtilMaster.show(
								context,
								"Info",
								0,
								"Please enter PF reading, 0 to 0.999",
								"Ok",null).show();
					}else if(!temp_bmd.matches(fRegularExpression)){
						UtilMaster.show(
								context,
								"Info",
								0,
								"Please enter BMD reading, 0 to 9.9999",
								"Ok",null).show();
					}else if(((float)0 > Float.parseFloat(temp_pf)) || (Float.parseFloat(temp_pf) >= (float)1) ){
						UtilMaster.show(
								context,
								"Info",
								0,
								"Please enter PF reading, 0 to 0.999",
								"Ok",null).show();
					}else if(((float)0 > Float.parseFloat(temp_bmd)) || (Float.parseFloat(temp_bmd) >= (float)10) ){
						UtilMaster.show(
								context,
								"Info",
								0,
								"Please enter BMD reading, 0 to 9.9999",
								"Ok",null).show();
					}else if(temp_ckwh.equalsIgnoreCase("")){
						UtilMaster.show(
								context,
								"Info",
								0,
								"Trivector meter. Please enter CKWH/LKWH reading",
								"Ok",null).show();
					}
					else{
						UtilMaster.mbmd_reading =     	temp_bmd;
						UtilMaster. mpf_reading    =   temp_pf;
						UtilMaster. mckwhlkwh = temp_ckwh ;
						readingManger(temp_prstRdg);
					}
				}else{

					UtilMaster.mckwhlkwh = "0";
					UtilMaster.mbmd_reading = "0";

					readingManger(temp_prstRdg);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			UtilMaster.show(context, "Error..!", 0, "Sorry..!\n some problem has occurred, Consumer data is missing or please try again", "Ok", AmrBillSupporter.getDialogListener(context, MainActivity.class));

		}
	}
	
	private void readingManger(String temp_prstRdg)	throws Exception {
		presentReading = Integer.parseInt(temp_prstRdg);

		long tempConsumtion = getConsumtion(String.valueOf(presentReading), String.valueOf(previousReading),Float.parseFloat(UtilMaster.getMmeter_constant().trim()));

		System.out.println("presentReading :"+String.valueOf(presentReading));
		System.out.println("previous reading :"+String.valueOf(previousReading) );
		System.out.println("tempConsumtion :"+tempConsumtion);
		
		calculationForNormal(String.valueOf(presentReading), String.valueOf(previousReading),Float.parseFloat(UtilMaster.getMmeter_constant().trim())); // DIRECT BILLING WITHOUT VALIDATIONS

		/*if(presentReading < previousReading){

			//UtilMaster.showToast(context, "Present reading should be grater than previous reading").show();
			new AlertDialog.Builder(context)
			.setTitle("Info").setMessage("Present Reading is less then Previous Reading.\nIs Dial Rotated.?")
			.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					try {
						calculationForNormal(String.valueOf(presentReading), String.valueOf(previousReading),Float.parseFloat(UtilMaster.getMmeter_constant().trim()));
					} catch (NumberFormatException e) {
						e.printStackTrace();
						UtilMaster.show(context, "Error..!", 0, "Sorry..!\n some problem has occurred, Consumer data is missing or please try again", "Ok", AmrBillSupporter.getDialogListener(context, MainActivity.class));
					} catch (Exception e) {
						e.printStackTrace();
						UtilMaster.show(context, "Error..!", 0, "Sorry..!\n some problem has occurred, Consumer data is missing or please try again", "Ok", AmrBillSupporter.getDialogListener(context, MainActivity.class));
					}
				}
			}).setNegativeButton("No", null)
			.show();


		}else if (presentReading == previousReading){

				new AlertDialog.Builder(context)
				.setTitle("Info").setMessage("consumption = 0 ...? Yes/No")
				.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						try {
							calculationForNormal(String.valueOf(presentReading), String.valueOf(previousReading),Float.parseFloat(UtilMaster.getMmeter_constant().trim()));

						} catch (NumberFormatException e) {
							e.printStackTrace();
							UtilMaster.show(context, "Error..!", 0, "Sorry..!\n some problem has occurred, Consumer data is missing or please try again", "Ok", AmrBillSupporter.getDialogListener(context, MainActivity.class));
						} catch (Exception e) {
							e.printStackTrace();
							UtilMaster.show(context, "Error..!", 0, "Sorry..!\n some problem has occurred, Consumer data is missing or please try again", "Ok", AmrBillSupporter.getDialogListener(context, MainActivity.class));
						}
					}
				}).setNegativeButton("No", null)
				.show();
			}else{
				new AlertDialog.Builder(context)
				.setTitle("Info").setMessage("For Normal case(consumption = 0) is not allowed")
				.setPositiveButton("Ok", null)
				.show();
			}

		}
		
		else if((tempConsumtion > Float.valueOf(UtilMaster.getMaverage_consumption())) && (tempConsumtion < 3 * Float.valueOf(UtilMaster.getMaverage_consumption()))){
				
				new AlertDialog.Builder(context)
				.setTitle("Info").setMessage("Subnormal Consumtion")
				.setPositiveButton("Allow", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						try {
							calculationForNormal(String.valueOf(presentReading), String.valueOf(previousReading),Float.parseFloat(UtilMaster.getMmeter_constant().trim()));
						} catch (NumberFormatException e) {
							e.printStackTrace();
							UtilMaster.show(context, "Error..!", 0, "Sorry..!\n some problem has occurred, Consumer data is missing or please try again", "Ok", AmrBillSupporter.getDialogListener(context, MainActivity.class));
						} catch (Exception e) {
							e.printStackTrace();
							UtilMaster.show(context, "Error..!", 0, "Sorry..!\n some problem has occurred, Consumer data is missing or please try again", "Ok", AmrBillSupporter.getDialogListener(context, MainActivity.class));
						}
					}})
				.setNegativeButton("ReEnter", null)
					
				.show();
				
			

		}
		else if(tempConsumtion > 3 * Float.valueOf(UtilMaster.getMaverage_consumption())){

			
				new AlertDialog.Builder(context)
				.setTitle("Info").setMessage("Abnormal Consumtion")
				.setPositiveButton("Allow", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						try {
							calculationForNormal(String.valueOf(presentReading), String.valueOf(previousReading),Float.parseFloat(UtilMaster.getMmeter_constant().trim()));
						} catch (NumberFormatException e) {
							e.printStackTrace();
							UtilMaster.show(context, "Error..!", 0, "Sorry..!\n some problem has occurred, Consumer data is missing or please try again", "Ok", AmrBillSupporter.getDialogListener(context, MainActivity.class));
						} catch (Exception e) {
							e.printStackTrace();
							UtilMaster.show(context, "Error..!", 0, "Sorry..!\n some problem has occurred, Consumer data is missing or please try again", "Ok", AmrBillSupporter.getDialogListener(context, MainActivity.class));
						}
					}})
				.setNegativeButton("ReEnter", null)
					
				.show();
				
		}

		else{
			calculationForNormal(String.valueOf(presentReading), String.valueOf(previousReading),Float.parseFloat(UtilMaster.getMmeter_constant().trim()));

		}*/
	}
	public void calculationForNormal(  String presReading ,  String prevReading ,  float meterConstatnt ) throws Exception{
		MasterLibraryFunction calculation = new MasterLibraryFunction(context ,UtilMaster. mtariff.trim() );
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
				AmrBillSupporter.move_inten(context, PrintBillAction2.class);
			}else{
				UtilMaster.showToast(context, " ! result > 0").show();
			}
		}else{
			FilePropertyManager.appendLog("Error :\n CONSUMPION == "+ UtilMaster.mconsumption);
			UtilMaster.show(
					context,
					"Sorry..!",
					0,
					"Consumer details are not correct (Consumpion < 0), You can not bill for this consumer no = "
							+ UtilMaster.getMconsumer_sc_no(),
							"Ok",
							AmrBillSupporter.getDialogListener(context,
									MainActivity.class)).show();
		}

	}
	public long getConsumtion(String presReading, String prevReading, float meterConstatnt){

		MasterLibraryFunction calculation = new MasterLibraryFunction(context ,UtilMaster. mtariff.trim() );
		long consumtionValue =  (long) (calculation.consumption(presReading, prevReading, meterConstatnt) + (Float.parseFloat(UtilMaster. mmeter_change_units_consumed.trim())*meterConstatnt ))	;

		return consumtionValue;
	}
}
