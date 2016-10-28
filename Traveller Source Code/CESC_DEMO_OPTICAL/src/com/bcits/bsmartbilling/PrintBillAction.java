package com.bcits.bsmartbilling;

import Utils.FilePropertyManager;
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
import android.widget.Button;
import android.widget.TextView;

public class PrintBillAction extends Activity {

	static Logger logger;
	private static String TAG = "PrintBillAction";
	private Button btn_next, btn_backpress;
	MasterLibraryFunction libraryFunction;
	TextView title, txtprtReading, txtConsuption, txtEc, txtFC, txtarrears,txtTotalA,txtPreviousReading;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.activity_printpreview);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,R.layout.window_title);

		btn_backpress = (Button) findViewById(R.id.titleBack);

		title = (TextView) findViewById(R.id.title);
		title.setText(getString(R.string.cus_title_name)
				+ MasterLibraryFunction.getVersion(PrintBillAction.this));
		txtprtReading = (TextView) findViewById(R.id.txtPresentReading);
		txtConsuption = (TextView) findViewById(R.id.txtConsuption);
		txtEc = (TextView) findViewById(R.id.txtEcAmount);

		txtFC = (TextView) findViewById(R.id.txtFcAmount);
		txtarrears = (TextView) findViewById(R.id.txtArrears);
		txtTotalA = (TextView) findViewById(R.id.txtTotalAmount);
		txtPreviousReading=(TextView)findViewById(R.id.txtPreviousReading);
		
		btn_next = (Button) findViewById(R.id.buttonprintpreview);

		try {
			if (logger == null) {
				logger = MasterLibraryFunction.getlogger(
						getApplicationContext(), TAG);
			}
			logger.info("In Side onCreate()");
		} catch (Exception e) {
			logger.error("logger is NULL");
		}
		logger.info("In Side onCreate()");

		Log.e(TAG, UtilMaster.mec);
		Log.e(TAG, UtilMaster.mfc);
		Log.e(TAG, UtilMaster.mtax);
		Log.e(TAG, UtilMaster.mmeterrent);
		Log.e(TAG, UtilMaster.mtotal);
		Log.e(TAG, UtilMaster.mtariff);

		
		Meterobservation.isThroughBluetooth=false;
		Meterobservation.isThroughDLMS=false;
		Meterobservation.isThroughRF=false;
		
		/*
		 * Log.e(TAG, "mbmd_penality: "+UtilMaster.mbmd_penality); Log.e(TAG,
		 * "mbmd_reading: "+UtilMaster.mbmd_reading);
		 */

		if (!UtilMaster.mGLogin_SiteCode.trim().equalsIgnoreCase("")
				&& !UtilMaster.mGLogin_MRCode.trim().equalsIgnoreCase("")) {
			setPrintDashboard();

			/*
			 * mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter(); try{
			 * 
			 * // If the adapter is null, then Bluetooth is not supported if
			 * (mBluetoothAdapter != null) { mBluetoothAdapter.enable(); }
			 * }catch(Exception exception){ new
			 * AlertDialog.Builder(PrintBillAction.this) .setTitle("Info")
			 * .setMessage(
			 * "Consumer details is not correct or unable to bill , Please try again later "
			 * ) .setPositiveButton("Ok", new DialogInterface.OnClickListener()
			 * { public void onClick(DialogInterface dialog, int id) {
			 * 
			 * Intent nfData_intnt = new
			 * Intent(PrintBillAction.this,MainActivity.class);
			 * startActivity(nfData_intnt);
			 * 
			 * } }).show(); }
			 */
		} else {
			logger.error("Session timeout ..! Please Login again UtilMaster.mGLogin_MRCode OR UtilMaster.mGLogin_SiteCode is NULL");
			new AlertDialog.Builder(PrintBillAction.this)
					.setTitle("Error")
					.setMessage("Sorry..! \nSession expired please Login again")
					.setPositiveButton("OK",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int which) {
									Intent int_login = new Intent(
											PrintBillAction.this, Login.class);
									startActivity(int_login);
								}
							}).show();
		}

	}

	@Override
	protected void onStart() {
		super.onStart();

		
		
		
		
		btn_backpress.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				clearBill();

			}
		});

		btn_next.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				if ((!UtilMaster.mGLogin_SiteCode.trim().equalsIgnoreCase(""))
						&& (!UtilMaster.mGLogin_MRCode.trim().equalsIgnoreCase(
								""))) {

					libraryFunction = new MasterLibraryFunction(
							PrintBillAction.this);
					if (libraryFunction.isdataValid()) {
						double billDemand = 0;// UtilMaster.getMtax()+energy_charges+fixed_charges+Lothers_float+Linterest_float-Lcredit_or_rebate_float
						float units = 0, ec = 0;

						try {

							billDemand = Float.parseFloat(UtilMaster.getMtax()
									.trim())
									+ Float.parseFloat(UtilMaster.getMec()
											.trim())
									+ Float.parseFloat(UtilMaster.getMfc()
											.trim())
									+ Float.parseFloat(UtilMaster.getMothers()
											.trim())
									+ Float.parseFloat(UtilMaster
											.getMinterest().trim())
									- Float.parseFloat(UtilMaster
											.getMcredit_or_rebate().trim())
									- Float.parseFloat(UtilMaster.getMdl_adj()
											.trim());

							
							
							
							System.out.println("1 :"+Float.parseFloat(UtilMaster.getMtax().trim()));
							System.out.println("2 :"+Float.parseFloat(UtilMaster.getMec()
									.trim()));
							
							System.out.println("3 :"+Float.parseFloat(UtilMaster.getMfc()
									.trim()));
							
							System.out.println("4 :"+Float.parseFloat(UtilMaster.getMothers()
									.trim()));
							
							System.out.println("5 :"+Float.parseFloat(UtilMaster
									.getMinterest().trim()));
							
							
							System.out.println("-6 :"+ Float.parseFloat(UtilMaster
									.getMcredit_or_rebate().trim()));
							
							System.out.println("-7 :"+ Float.parseFloat(UtilMaster.getMdl_adj()
									.trim()));
							
							
							
							
							System.out.println("Bill demand is :"+billDemand);
							
							
							units = Float.parseFloat(UtilMaster.mconsumption
									.trim());
							ec = Float.parseFloat(UtilMaster.getMec().trim());
						} catch (Exception e) {
							logger.error(Log.getStackTraceString(e));
							UtilMaster
									.show(PrintBillAction.this,
											"Error",
											0,
											"Sorry..! \nSession expired please Login again",
											"OK",
											getDialogListener(
													PrintBillAction.this,
													Login.class)).show();
						}

						if (isBillNormalNew(billDemand, ec, units, UtilMaster
								.getMtariff().trim()))
							
						{
							startActivity(new Intent(PrintBillAction.this,
									BluetoothChat.class));
						}
							
						else {

							FilePropertyManager
									.appendLog(" Abnormal billDemand: "
											+ billDemand);
							UtilMaster
									.show(PrintBillAction.this,
											"Sorry..!",
											0,
											"Abnormal bill Not Allowed Bill Demand: "
													+ billDemand
													+ "\nEC: "
													+ ec
													+ "\nUnits: "
													+ units
													+ "\nBill will be cancelled",
											"OK",
											getDialogListener(
													PrintBillAction.this,
													SearchActivity.class))
									.setCancelable(false).show();
						}

					} else {

						/*
						 * new AlertDialog.Builder(PrintBillAction.this)
						 * .setTitle("Info") .setMessage(
						 * "Mobile Date has been changed please set the Date correctly OR Download fresh data to Procced next "
						 * ) .setPositiveButton( "Set Date", new
						 * DialogInterface.OnClickListener() { public void
						 * onClick( DialogInterface arg0, int arg1) {
						 * 
						 * move_inten(PrintBillAction.this, MainActivity.class);
						 * } }).show();
						 */

						UtilMaster
								.show(PrintBillAction.this,
										"Error",
										0,
										"Mobile Date has been changed please set the Date correctly or Download fresh data to Procced next",
										"OK",
										getDialogListener(PrintBillAction.this,
												MainActivity.class)).show();

					}

				} else {

					logger.error("Session timeout ..! Please Login again UtilMaster.mGLogin_MRCode OR UtilMaster.mGLogin_SiteCode is NULL");
					UtilMaster
							.show(PrintBillAction.this,
									"Error",
									0,
									"Sorry..! \nSession expired please Login again",
									"OK",
									getDialogListener(PrintBillAction.this,
											Login.class)).show();

				}

			}
		});

	}

	@Override
	public void onBackPressed() {
		clearBill();

	}

	public boolean isBillNormal(double billDemand, float ec, float units,
			String tCode) {
		boolean normal = false;

		if (billDemand < 5000000) {

			if (tCode.equals("5") || tCode.equals("52") || tCode.equals("54")
					|| tCode.equals("56") || tCode.equals("58")
					|| tCode.equals("51") || tCode.equals("204")
					|| tCode.equals("50") || tCode.equals("57")
					|| tCode.equals("59") || tCode.equals("53")
					|| tCode.equals("6") || tCode.equals("62")
					|| tCode.equals("64") || tCode.equals("69")
					|| tCode.equals("63") || tCode.equals("65")) {

				if (billDemand < 500000)
					normal = true;
				else
					normal = false;
			} else {
				normal = true;
			}
		} else {
			normal = false;
		}

		if (units < 0)
			normal = false;

		if (ec < 0)
			normal = false;

		return normal;

	}

	public boolean isBillNormalNew(double billDemand, float ec, float units,
			String tCode) {
		boolean normal = false;
		
		
		
		
		
		
		
		
		
		
		
		

		if (billDemand < 1000000) {
			
			if (units < 0){
				normal = false;
			}
			else if (ec < 0){
				normal = false;
			}
			
			// for LT1
			else if((tCode.equals("1") || tCode.equals("2")) && ec > 20000){
				normal = false;
			}
			// For LT2
			else if((
					tCode.equals("54") ||
					tCode.equals("69") ||
					tCode.equals("57") ||
					tCode.equals("62") ||
					tCode.equals("67") ||
					tCode.equals("6") ||
					tCode.equals("50") ||
					tCode.equals("204") ||
					tCode.equals("58") ||
					tCode.equals("68") ||
					tCode.equals("66") ||
					tCode.equals("53") ||
					tCode.equals("5") ||
					tCode.equals("63") ||
					tCode.equals("55") ||
					tCode.equals("61") ||
					tCode.equals("56") ||
					tCode.equals("59") ||
					tCode.equals("65") ||
					tCode.equals("52") ||
					tCode.equals("64") ||
					tCode.equals("51") 
					)
	               && 
	              (ec > 75000)
	 
					){
				
				normal = false;
				
			}
			// for LT 3
			else if(
				(	tCode.equals("106") ||
					tCode.equals("104") ||
					tCode.equals("208") ||
					tCode.equals("101") ||
					tCode.equals("102") ||
					tCode.equals("105") ||
					tCode.equals("103") ||
					tCode.equals("107") ||
					tCode.equals("10") ||
					tCode.equals("108") 
	            )				
				
	            &&
	            (ec > 100000)
					
					){
				normal = false;
				
			}
			// for LT 5
			else if(
				(	tCode.equals("175") ||
					tCode.equals("171") ||
					tCode.equals("172") ||
					tCode.equals("173") ||
					tCode.equals("176") ||
					tCode.equals("17") ||
					tCode.equals("178") ||
					tCode.equals("177") ||
					tCode.equals("174") 
				)	
				&&
				(ec >  200000)
					){
				normal = false;
			}
			else{
				normal = true;
			}
			
		} else {
			normal = false;
		}
		
		
		
		// For LT 6 tariffs
		
		if(
				(tCode.equals("19") ||
				tCode.equals("191") ||
				tCode.equals("192") ||
				tCode.equals("193") ||
				tCode.equals("194") 
				
				)
				
				&& (ec <  1000000)
				
				
				)
			
			
			
			
		{
			
			
			
			normal = true;
			
			
			
			
		}
		
		
		
		
		
		
		return normal;

	}

	private void clearBill() {
		new AlertDialog.Builder(PrintBillAction.this)
				.setTitle("Info")
				.setMessage("Do you want to Cancel bill?")
				.setPositiveButton("Yes Cancel",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								Intent int_login = new Intent(
										PrintBillAction.this,
										SearchActivity.class);
								finish();

								startActivity(int_login);
							}
						}).setNegativeButton("No", null).show();
		/*
		 * AlertDialog.Builder builder = UtilMaster.show(PrintBillAction.this,
		 * "Info", 0, "Do you want to Cancel bill?",
		 * "Yes Cancel",getDialogListener(PrintBillAction.this,
		 * SearchActivity.class), "No" , null); finish(); builder.show() ;
		 */
	}

	private void setPrintDashboard() {
		txtPreviousReading.setText(UtilMaster.mprevious_reading);
		txtprtReading.setText(UtilMaster.mpresentreading + "");
		txtConsuption.setText(UtilMaster.mconsumption.trim());
		txtEc.setText(UtilMaster.mec);
		txtFC.setText(UtilMaster.mfc);
		txtarrears.setText(UtilMaster.marrears);
		txtTotalA.setText((float) MasterLibraryFunction.doubRound((Float.parseFloat(UtilMaster.mtotal)), 2)	+ "");

	}

	public void move_inten(Context context,
			@SuppressWarnings("rawtypes") Class class1) {
		Intent intent = new Intent(context, class1);
		// mEnabled=false;
		startActivity(intent);
	}

	public android.content.DialogInterface.OnClickListener getDialogListener(
			final Context context,
			@SuppressWarnings("rawtypes") final Class class1) {

		return new android.content.DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				move_inten(context, class1);
			}
		};

	}
}
