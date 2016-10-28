package com.bcits.disconreconn;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.bcits.disconnreconn.R;
import com.bcits.disconrecon.utils.MRDatabaseHandler;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class MRNewMtrDetailsRecordingActivity extends Activity{

	TextView title ;
	Button btnNext, btnCapture;
	Intent intent;
	private static final int CAMERA_PIC_REQUEST_ONE = 14;
	ImageView imgSetImage;
	String imageString ;
	MRDatabaseHandler databasehandler;
	int uploaded;

	protected LocationManager locationManager;
	boolean isGPSEnabled = false;
	boolean isNetworkEnabled = false;
	boolean canGetLocation = false;
	Location location; 
	String latitude; // latitude
	String longitude; // longitude
	String	versionName;


	EditText edMeterSerialno,edMeterIR, edMeterConstant;
	Spinner spMeterMake, spMeterCapacity, spMeterCover, spMeterPosition, spMeterType,spSealType,spMeterCondition;
	String meterSerialno, meterIR, meterMake, meterCapacity, meterCover, meterPosition, meterType, meterConstant;

	String colors[] = {"PolyCarbonate","Meter Roto","Plastic","Anchor", "Techlok"};
	String colorsnew[] = {"Working","Faulty","Broken","Repair"};
	private int year;
	private int month;
	private int day;

	static final int DATE_PICKER_ID = 1111; 


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.mrnewmtrdetailsrecordingactivity);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.window_title);
		title = (TextView) findViewById(R.id.title);
		title.setText("New meter form");

		btnNext = (Button) findViewById(R.id.next);

		btnCapture = (Button) findViewById(R.id.btnCapture);
		imgSetImage = (ImageView) findViewById(R.id.imgSetImage);


		edMeterSerialno = (EditText) findViewById(R.id.edtserial);
		edMeterIR = (EditText) findViewById(R.id.presentreadin);
		edMeterConstant = (EditText) findViewById(R.id.meterconstanted);

		spMeterMake = (Spinner) findViewById(R.id.spnmetermake);
		spMeterCapacity = (Spinner) findViewById(R.id.spnmetercapacity);
		spMeterCover = (Spinner) findViewById(R.id.spnmetercover);
		spMeterPosition = (Spinner) findViewById(R.id.spnmeterposition);
		spMeterType = (Spinner) findViewById(R.id.spnmetertype);


		/*spSealType = (Spinner) findViewById(R.id.spnmetertypenew);
		spMeterCondition = (Spinner) findViewById(R.id.spnmetertypenew1);
*/

	/*	ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this,   android.R.layout.simple_spinner_item, colors);
		spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
		spSealType.setAdapter(spinnerArrayAdapter);
		
		
		ArrayAdapter<String> spinnerArrayAdapternew = new ArrayAdapter<String>(this,   android.R.layout.simple_spinner_item, colorsnew);
		spinnerArrayAdapternew.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
		spMeterCondition.setAdapter(spinnerArrayAdapternew);
*/
		if ((LoginActivity.mrcode != null)	) {

		} else {

			MRUtilMaster.show(MRNewMtrDetailsRecordingActivity.this, "Error", 0, "Sorry..! \nSession expired please Login again", "OK", getDialogListener(MRNewMtrDetailsRecordingActivity.this, LoginActivity.class)).show();

		}




		getLocation();
		sendNoImage();


	}


	@Override
	protected void onResume() {
		super.onResume();




		btnCapture.setEnabled(true);
		btnCapture.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				btnCapture.setEnabled(false);
				try {
					Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
					startActivityForResult(cameraIntent, CAMERA_PIC_REQUEST_ONE);
				} catch (Exception e) {
					
					e.printStackTrace();
					
				}
			}

		});
	}

	@Override
	public void onActivityResult(final int requestCode, int resultCode,
			Intent data) {
		try {
			switch (requestCode) {
			case CAMERA_PIC_REQUEST_ONE:
				btnCapture.setEnabled(true);
				if (resultCode == RESULT_OK) {
					try {
						Bitmap photo;
						byte[] img;
						photo = (Bitmap) data.getExtras().get("data");
						imgSetImage.setImageBitmap(photo);
						btnCapture.setText("Change Image");
						ByteArrayOutputStream baos = new ByteArrayOutputStream();
						photo.compress(Bitmap.CompressFormat.PNG, 100, baos);
						img = baos.toByteArray();
						imageString = Base64.encodeToString(img,Base64.DEFAULT);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				break;

			default:
				break;
			}
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
	}

	@Override
	protected void onStart() {
		
		
		try {
			
			// TODO Auto-generated method stub
			super.onStart();

			btnNext.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub

					try {


						meterSerialno = edMeterSerialno.getText().toString().trim();
						MRUtilMaster.setMnmserialno(meterSerialno);

						meterIR = edMeterIR.getText().toString().trim();
						MRUtilMaster.setMnmir(meterIR);

						meterMake = spMeterMake.getSelectedItem().toString().trim();
						MRUtilMaster.setMnmmake(meterMake);

						meterCapacity = spMeterCapacity.getSelectedItem().toString().trim();
						MRUtilMaster.setMnmcapacity(meterCapacity);

						meterCover = spMeterCover.getSelectedItem().toString().trim();
						MRUtilMaster.setMnmcover(meterCover);

						meterPosition = spMeterPosition.getSelectedItem().toString().trim();
						MRUtilMaster.setMnmposition(meterPosition);

						meterType = spMeterType.getSelectedItem().toString().trim();
						MRUtilMaster.setMnmtype(meterType);

						meterConstant = edMeterConstant.getText().toString().trim();
						MRUtilMaster.setMnmconstant(meterConstant);


						MRUtilMaster.setMnmlattitude(latitude);
						MRUtilMaster.setMnmlongitude(longitude);
						MRUtilMaster.setMnmimage(imageString);



						System.out.println("meterType :"+meterType);

						System.out.println("Longitude :"+longitude);
						System.out.println("Latitude :"+latitude);

						System.out.println("Image String length :"+imageString.length());

						System.out.println("Image String :"+imageString);





						if(!(isDateValidToSubmit(MRUtilMaster.getMservertomobiledate()))){


						/*	new AlertDialog.Builder(MRNewMtrDetailsRecordingActivity.this)
							.setTitle("Info")
							.setMessage("Please check the mobile date. If date is correct then you have to download the new Data")
							.setPositiveButton("OK", null).show();*/
							
							
							
							new AlertDialog.Builder(MRNewMtrDetailsRecordingActivity.this)
							.setTitle("Info")
							.setMessage(
									"You can only bill for 7 days after downloading data. Please download the new data. If the mobile date is not correct click on SET DATE button  ")
									
									.setPositiveButton("Set Date",	
											new DialogInterface.OnClickListener() 
									{
										public void onClick(DialogInterface arg0,int arg1) 
										{
											startActivity(new Intent(android.provider.Settings.ACTION_DATE_SETTINGS));

										}
									})
									.setNegativeButton("OK", new DialogInterface.OnClickListener() 
									{
										public void onClick(DialogInterface arg0,int arg1) 
										{
											move_inten(MRNewMtrDetailsRecordingActivity.this, MRStatusActivity.class);

										}
									})
									.show();

						}


						else if(meterSerialno.trim().equals("") || meterSerialno.trim().equals(" ") || meterSerialno == null ){


							new AlertDialog.Builder(MRNewMtrDetailsRecordingActivity.this)
							.setTitle("Error")
							.setMessage("Enter the Meter Serial Number")
							.setCancelable(false)
							.setPositiveButton("OK", null)
							.show();
						}
						else if(meterIR.trim().equals("") || meterIR.trim().equals(" ") || meterIR == null ){


							new AlertDialog.Builder(MRNewMtrDetailsRecordingActivity.this)
							.setTitle("Error")
							.setMessage("Enter the Meter Initial Reading")
							.setCancelable(false)
							.setPositiveButton("OK", null)
							.show();
						}
						else if(meterConstant.trim().equals("") || meterConstant.trim().equals(" ") || meterConstant == null ){


							new AlertDialog.Builder(MRNewMtrDetailsRecordingActivity.this)
							.setTitle("Error")
							.setMessage("Enter Meter Constant")
							.setCancelable(false)
							.setPositiveButton("OK", null)
							.show();
						}


						else if(Double.parseDouble(meterConstant.trim()) <= 0){

							new AlertDialog.Builder(MRNewMtrDetailsRecordingActivity.this)
							.setTitle("Error")
							.setMessage("Meter Constant cannot be zero and negative")
							.setCancelable(false)
							.setPositiveButton("OK", null)
							.show();

						}

						else{

							try {
								versionName = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
							} catch (NameNotFoundException e) 
							{
								// TODO Auto-generated catch block
								versionName = "0" ;
								e.printStackTrace();

							}


							databasehandler = new MRDatabaseHandler(getApplicationContext());
							databasehandler.openToWrite();
							String submitstatus ="1";
							Date d = new Date();
							SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
							String submitdatetimestamp =   format.format(d);

							MRUtilMaster.setMnmdateofinstall(submitdatetimestamp);	
							MRUtilMaster.setMomdateofrelease(submitdatetimestamp);



							if(MRUtilMaster.getMmeterflag().equals("1")){

								MRUtilMaster.setMomdateofrelease("00-00-0000");
								MRUtilMaster.setMomcover("Having Cover");
								MRUtilMaster.setMomcapacity("");
								MRUtilMaster.setMomfr("0");
								MRUtilMaster.setMomlattitude("");
								MRUtilMaster.setMomlongitude("");
								MRUtilMaster.setMommake("");
								MRUtilMaster.setMomposition("Inside Premisis");
								MRUtilMaster.setMomserialno("0");
								MRUtilMaster.setMomtype("STATIC");
								MRUtilMaster.setMomimage("");

							}




							try {

								uploaded=databasehandler.Updatetolocaldb(
										MRUtilMaster.getMrrno(),
										MRUtilMaster.getMomdateofrelease(),
										MRUtilMaster.getMomserialno(),
										MRUtilMaster.getMommake(),
										MRUtilMaster.getMomcapacity(),
										MRUtilMaster.getMomcover(),
										MRUtilMaster.getMomposition(),
										MRUtilMaster.getMomtype(),
										MRUtilMaster.getMomfr(),
										MRUtilMaster.getMomlongitude(),
										MRUtilMaster.getMomlattitude(),
										MRUtilMaster.getMomimage(),
										MRUtilMaster.getMnmdateofinstall(),
										MRUtilMaster.getMnmserialno(),
										MRUtilMaster.getMnmmake(),
										MRUtilMaster.getMnmcapacity(),
										MRUtilMaster.getMnmcover(),
										MRUtilMaster.getMnmposition(),
										MRUtilMaster.getMnmtype(),
										MRUtilMaster.getMnmir(),
										MRUtilMaster.getMnmlongitude(),
										MRUtilMaster.getMnmlattitude(),
										MRUtilMaster.getMnmimage(),
										MRUtilMaster.getMnmconstant(),
										versionName,
										submitdatetimestamp,
										submitstatus,
										MRUtilMaster.getMextra7(),
										MRUtilMaster.getMextra8(),
										MRUtilMaster.getMextra9(),
										MRUtilMaster.getMextra10()
										
										);
								databasehandler.close();

								System.out.println("Uploaded value is : "+uploaded);

								if(uploaded > 0){

									new AlertDialog.Builder(MRNewMtrDetailsRecordingActivity.this)
									.setTitle("Success")
									.setMessage("Records Submitted")
									.setCancelable(false)
									.setPositiveButton("OK", new DialogInterface.OnClickListener() {

										@Override
										public void onClick(DialogInterface dialog, int which) {
											// TODO Auto-generated method stub

											move_inten(getApplicationContext(), MRStatusActivity.class);


										}
									})

									.setNegativeButton("ReSubmit", null)
									.show();
								}

								else{

									new AlertDialog.Builder(MRNewMtrDetailsRecordingActivity.this)
									.setTitle("Error")
									.setMessage("Failed to Submit")
									.setCancelable(false)
									.setPositiveButton("OK", new DialogInterface.OnClickListener() {

										@Override
										public void onClick(DialogInterface dialog, int which) {
											// TODO Auto-generated method stub

											move_inten(getApplicationContext(), MRStatusActivity.class);


										}
									})

									.setNegativeButton("ReSubmit", null)
									.show();
								}

							} catch (Exception e) {
								// TODO: handle exception


								
								e.printStackTrace();
								
								
								new AlertDialog.Builder(MRNewMtrDetailsRecordingActivity.this)
								.setTitle("Error")
								.setMessage("Failed to Submit")
								.setCancelable(false)
								.setPositiveButton("OK", new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog, int which) {
										// TODO Auto-generated method stub

										move_inten(getApplicationContext(), MRStatusActivity.class);


									}
								})

								.setNegativeButton("ReSubmit", null)
								.show();


							}

						}

					} catch (Exception e) {
						// TODO: handle exception

						e.printStackTrace();



					}

				}
			});
		
			} catch (Exception e) {

				// TODO: handle exception
				
				
				e.printStackTrace();
				
				
				if ((LoginActivity.mrcode != null)	) {

				} else {

					MRUtilMaster.show(MRNewMtrDetailsRecordingActivity.this, "Error", 0, "Sorry..! \nSession expired please Login again", "OK", getDialogListener(MRNewMtrDetailsRecordingActivity.this, LoginActivity.class)).show();

				}
			}
		
		
		
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();

		if(MRUtilMaster.getMmeterflag().equals("0")){
			intent = new Intent(getApplicationContext(), MROldMtrDetailsRecordingActivity.class);
			startActivity(intent);
		}
		else{

			intent = new Intent(getApplicationContext(), MRCosumerActivity.class);
			startActivity(intent);
		}


	}






	/* ********************* USER DEFINED METHODS *******************************/	



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

	public void move_inten( Context context , Class class1 ) {
		Intent intent = new Intent(context,class1);
		startActivity(intent);
	}
	public void move_inten( Context context , Class class1 , String data ) {

		Intent intent = new Intent(context,class1);
		intent.putExtra("RRno/bu", data);
		startActivity(intent);
	}



	private void getLocation() {
		try {
			locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
			isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
			isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
			if (!isGPSEnabled && !isNetworkEnabled) {
				latitude = "";
				longitude ="";
			} else {
				this.canGetLocation = true;
				if (locationManager != null) {
					location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
					if (location != null) {
						latitude = location.getLatitude()+"".trim();
						longitude = location.getLongitude()+"".trim();
					}
					else{
						latitude = "";
						longitude ="";
					}
				}
			}
			if (isGPSEnabled) {
			}
			else{
				latitude = "";
				longitude ="";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	private void sendNoImage() {
		Drawable noimage = getResources().getDrawable(R.drawable.no_image);
		Bitmap bitmap = ((BitmapDrawable) noimage).getBitmap();
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
		byte[] bitmapdata = stream.toByteArray();
		bitmapdata = stream.toByteArray();
		imageString = Base64.encodeToString(bitmapdata, Base64.DEFAULT);
	}





	public String getValidStringFormat(String datestring){

		/*String a ="12-5-2015";*/



		String v[] = datestring.split("-");

		if(v[0].length() == 1){

			v[0] = "0" + v[0];

		}

		if(v[1].length() == 1){

			v[1] = "0"+v[1];
		}

		String b = v[0]+"-"+v[1]+"-"+v[2];

		return b;

	}

	
	
public static boolean isDateValidToSubmit(String servertomobiledate){


		
		
		
		boolean result = false;
		boolean validationResult = false;
		
		
		try{
			
			
			System.out.println("Day :"+servertomobiledate);
			
			String dayString = servertomobiledate.substring(0, 2);

			String monthString = servertomobiledate.substring(3,5);

			String yearString = servertomobiledate.substring(6, 10);


			int day = Integer.valueOf(dayString);
			int month = Integer.valueOf(monthString);
			int year = Integer.valueOf(yearString);



			Date d = new Date();
			SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
			servertomobiledate =   format.format(d);

			
			System.out.println("Day1 :"+servertomobiledate);
			
			
			dayString = servertomobiledate.substring(0, 2);

			monthString = servertomobiledate.substring(3,5);
			yearString = servertomobiledate.substring(6, 10);

			int day1 = Integer.valueOf(dayString);
			int month1 = Integer.valueOf(monthString);
			int year1 = Integer.valueOf(yearString);



			
				
				if(!(year1 == year)){
					
					result = false;
				}
				else if(!(month1 == month)){
					
					result = false;
					
				}
				else if(day1< day){
					result = false;
				}
				
				else{
					result = true;
					
				}
				
				
				if(result == true){
					
					
					if(day1 == day){
						
						validationResult = true;
						
					}
					
					else if((day1 - day ) <= 7){
						
						validationResult = true;
						
					}
					
				}
				
				else{
					
					
					validationResult = false;
					
				}
				
			
			
			
				
				
				if(
						(day == 28 || day == 29 || day == 30 || day == 31)
						
						&&
						
						validationResult == false
						
						&&
						
						
						(day1 == 1 || day1 == 2 || day1 == 3 || day1 == 4)
						
						
						
						&&
						
						(month1 == month+1)
						
						
						&&
						
						(year1 == year)
						
						
						){
					
					
					System.out.println("Date allows>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
					
					validationResult = true;
					
					
					
				}
				
				
			
			
			
			
			
			
		}catch(Exception e){
			
			e.printStackTrace();
			
		}
		
		
		
		
		return validationResult;
	}
	
}