package com.bcits.disconreconn;


import java.io.ByteArrayOutputStream;

import com.bcits.disconnreconn.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class MROldMtrDetailsRecordingActivity extends Activity{

	TextView title;
	Button btnNext, btnCapture;
	Intent intent;
	private static final int CAMERA_PIC_REQUEST_ONE = 14;
	ImageView imgSetImage;
	String imageString ;

	protected LocationManager locationManager;
	boolean isGPSEnabled = false;
	boolean isNetworkEnabled = false;
	boolean canGetLocation = false;
	Location location; 
	String latitude; // latitude
	String longitude; // longitude


	EditText edMeterSerialno,edMeterFR,sealnum,sealdateset;
	Spinner spMeterMake, spMeterCapacity, spMeterCover, spMeterPosition, spMeterType,spSealType,spMeterCondition;
	String  meterSerialno, meterFR, meterMake, meterCapacity, meterCover, meterPosition, meterType,sealdatenow,sealno,sealtype,mcondition;
	String colors[] = {"PolyCarbonate","Meter Roto","Plastic","Anchor", "Techlok"};
	String colorsnew[] = {"Working","Faulty","Broken","Repair"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.mroldmtrdetailsrecordingactivity);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.window_title);
		title = (TextView) findViewById(R.id.title);
		title.setText("Old meter form");

		btnNext = (Button) findViewById(R.id.next);
		btnCapture = (Button) findViewById(R.id.btnCapture);
		imgSetImage = (ImageView) findViewById(R.id.imgSetImage);

		edMeterSerialno = (EditText) findViewById(R.id.edtserial);
		edMeterFR = (EditText) findViewById(R.id.presentreadin);
		sealdateset = (EditText) findViewById(R.id.presentreadinnew);
		sealdateset.setText(MRStatusActivity.sealdate);
		sealnum = (EditText) findViewById(R.id.presentreadinnew1);
		spMeterMake = (Spinner) findViewById(R.id.spnmetermake);
		spMeterCapacity = (Spinner) findViewById(R.id.spnmetercapacity);
		spMeterCover = (Spinner) findViewById(R.id.spnmetercover);
		spMeterPosition = (Spinner) findViewById(R.id.spnmeterposition);
		spMeterType = (Spinner) findViewById(R.id.spnmetertype);
		
		spSealType = (Spinner) findViewById(R.id.spnmetertypenew);
		spMeterCondition = (Spinner) findViewById(R.id.spnmetertypenew1);


		ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this,   android.R.layout.simple_spinner_item, colors);
		spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
		spSealType.setAdapter(spinnerArrayAdapter);
		
		
		ArrayAdapter<String> spinnerArrayAdapternew = new ArrayAdapter<String>(this,   android.R.layout.simple_spinner_item, colorsnew);
		spinnerArrayAdapternew.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
		spMeterCondition.setAdapter(spinnerArrayAdapternew);
		
		/*
		if ((LoginActivity.listno != null)	) {

		} else {

			MRUtilMaster.show(MROldMtrDetailsRecordingActivity.this, "Error", 0, "Sorry..! \nSession expired please Login again", "OK", getDialogListener(MROldMtrDetailsRecordingActivity.this, LoginActivity.class)).show();

		}*/

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
						MRUtilMaster.setMomserialno(meterSerialno);

						meterFR = edMeterFR.getText().toString().trim();
						MRUtilMaster.setMomfr(meterFR);

						meterMake = spMeterMake.getSelectedItem().toString().trim();
						MRUtilMaster.setMommake(meterMake);

						meterCapacity = spMeterCapacity.getSelectedItem().toString().trim();
						MRUtilMaster.setMomcapacity(meterCapacity);

						meterCover = spMeterCover.getSelectedItem().toString().trim();
						MRUtilMaster.setMomcover(meterCover);

						meterPosition = spMeterPosition.getSelectedItem().toString().trim();
						MRUtilMaster.setMomposition(meterPosition);

						meterType = spMeterType.getSelectedItem().toString().trim();
						MRUtilMaster.setMomtype(meterType);
						
						sealdatenow = MRStatusActivity.sealdate;
						MRUtilMaster.setMextra7(sealdatenow);
						
						sealno=sealnum.getText().toString();
						MRUtilMaster.setMextra8(sealno);
						//meterType = spMeterType.getSelectedItem().toString().trim();
						
						//
						sealtype = spSealType.getSelectedItem().toString().trim();
						MRUtilMaster.setMextra9(sealtype);
						
						mcondition = spMeterCondition.getSelectedItem().toString().trim();
						MRUtilMaster.setMextra10(mcondition);

						//MRUtilMaster.getMextra9(),
						//MRUtilMaster.getMextra10()

						MRUtilMaster.setMomlattitude(latitude);
						MRUtilMaster.setMomlongitude(longitude);
						MRUtilMaster.setMomimage(imageString);

						System.out.println("sealdatenow/ :"+sealdatenow+"sealno/ :"+sealno+"sealtype/ :"+sealtype+"mcondition/ :"+mcondition);

						System.out.println("meterType :"+meterType);

						System.out.println("Longitude :"+longitude);
						System.out.println("Latitude :"+latitude);

						System.out.println("Image String length :"+imageString.length());

						System.out.println("Image String :"+imageString);



						if(meterSerialno.trim().equals("") || meterSerialno.trim().equals(" ") || meterSerialno == null ){


							new AlertDialog.Builder(MROldMtrDetailsRecordingActivity.this)
							.setTitle("Error")
							.setMessage("Enter the Meter Serial Number")
							.setCancelable(false)
							.setPositiveButton("OK", null)
							.show();
						}
						else if(meterFR.trim().equals("") || meterFR.trim().equals(" ") || meterFR == null ){


							new AlertDialog.Builder(MROldMtrDetailsRecordingActivity.this)
							.setTitle("Error")
							.setMessage("Enter the Meter Final Reading")
							.setCancelable(false)
							.setPositiveButton("OK", null)
							.show();
						}
						else{


							intent = new Intent(getApplicationContext(), MRNewMtrDetailsRecordingActivity.class);
							startActivity(intent);



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

					MRUtilMaster.show(MROldMtrDetailsRecordingActivity.this, "Error", 0, "Sorry..! \nSession expired please Login again", "OK", getDialogListener(MROldMtrDetailsRecordingActivity.this, LoginActivity.class)).show();

				}
			}
		
		
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();

		intent = new Intent(getApplicationContext(), MRCosumerActivity.class);
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


}