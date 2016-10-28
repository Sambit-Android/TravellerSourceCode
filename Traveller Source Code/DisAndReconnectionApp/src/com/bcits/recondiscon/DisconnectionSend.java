package com.bcits.recondiscon;

import java.io.ByteArrayOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bcits.utils.AmrMethods;
import com.sqlite.mvs.DbDisconnection;

public class DisconnectionSend extends MasterActivity {
	EditText edCurrentReading, edRemarks;
	TextView txtAccount;
	Button btnDisconnect,btnCapture;

	AlertDialog show;
	String mrReading, remarks="", serverId;
	DbDisconnection discDb;
	
	Spinner  spinnerPresentStatus;
	CheckBox checkDisconnected;
	TextView txtPresentStatus;
	TextView txtCurrentReading;

	String presentStaus;
	ImageView imgSetImage;
	String imageString ;
	LinearLayout linearImage;
	
	private static final int CAMERA_PIC_REQUEST_ONE = 14;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle("Disconnection");
		discDb = new DbDisconnection(getBaseContext());
		setContentView(R.layout.disconnection_send);
		
		txtCurrentReading=(TextView)findViewById(R.id.txtCurrentReading);
		imgSetImage=(ImageView)findViewById(R.id.imgSetImage);
		btnCapture=(Button)findViewById(R.id.btnCapture);
		edCurrentReading = (EditText) findViewById(R.id.edCurrentReading);
		edRemarks = (EditText) findViewById(R.id.edRemarks);
		txtAccount = (TextView) findViewById(R.id.txtAccount);
		btnDisconnect = (Button) findViewById(R.id.btnDisconnect);
		txtAccount.setText(DisconnectionView.disc.getRrno());
		spinnerPresentStatus=(Spinner)findViewById(R.id.spinnerPresentStatus);
		checkDisconnected=(CheckBox)findViewById(R.id.checkDisconnected);
		txtPresentStatus=(TextView)findViewById(R.id.txtPresentStatus);
		txtPresentStatus.setVisibility(View.GONE);
		spinnerPresentStatus.setVisibility(View.GONE);
		linearImage=(LinearLayout)findViewById(R.id.linearImage);
		
		sendNoImage();
		presentStatusSpinner();
		checkBoxDisputeFlag();
		
		Toast.makeText(getApplicationContext(), DisconnectionView.disc.getConsumer_name(), Toast.LENGTH_SHORT).show();
		
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
				} 
				catch (Exception e) {
				e.printStackTrace();
				}
			}

		});
		
		btnDisconnect.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (AmrMethods.doubleClick()) {
					boolean hasDrawable = (imgSetImage.getDrawable() != null);
				remarks = edRemarks.getText().toString().trim();
				serverId = DisconnectionView.disc.getId().trim();
				//TODO BUTTON
				if (checkDisconnected.isChecked()) {
					mrReading="0";
				
				if(presentStaus.equals("Select")){
					
					show =new AlertDialog.Builder(DisconnectionSend.this).setMessage("Please Choose Reason").setPositiveButton("Ok", null).show();	
			
				}
				/*else if(presentStaus.equals("Payment Done")&& hasDrawable==false){
					show =new AlertDialog.Builder(DisconnectionSend.this).setMessage("Please Capture Reciept").setPositiveButton("Ok", null).show();	
				}
				
				else if(presentStaus.equals("Door Lock")&& hasDrawable==false){
					
					show =new AlertDialog.Builder(DisconnectionSend.this).setMessage("Please Capture Capture Door No./House").setPositiveButton("Ok", null).show();	
			
				}*/

				else if(presentStaus.equals("Others")&& remarks.isEmpty()){
					edRemarks.setError("Please Enter The Others Reason Here");
					edRemarks.requestFocus();
//				show =new AlertDialog.Builder(DisconnectionSend.this).setMessage("Please Capture Reciept").setPositiveButton("Ok", null).show();	
			}
				
				else{
					
					if(presentStaus.equals("Payment Done") || presentStaus.equals("Will Pay Today")|| presentStaus.equals("Others")) {
						remarks=presentStaus+" - "+remarks;
					}
					
					offlineSaving(false);
						}
					}

				else{
					mrReading = edCurrentReading.getText().toString().trim();
					
				if(mrReading.equals("")){
				 new AlertDialog.Builder(DisconnectionSend.this).setMessage("Please enter current meter reading").setPositiveButton("Ok", null).show();	
				}
				else if(Integer.parseInt(mrReading)<=0){
				 new AlertDialog.Builder(DisconnectionSend.this).setMessage("Please enter a valid meter reading").setPositiveButton("Ok", null).show();	
					}
				else if(hasDrawable==false){
				 new AlertDialog.Builder(DisconnectionSend.this).setMessage("Please Capture Meter").setPositiveButton("Ok", null).show();	
				}
				else{
					offlineSaving(true);
				}
				}
			}}
		});
	}
	 
	private void checkBoxDisputeFlag(){
	    checkDisconnected.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
				if (checkDisconnected.isChecked()) {
					linearImage.setVisibility(View.GONE);
					edCurrentReading.setVisibility(View.GONE);
					txtCurrentReading.setVisibility(View.GONE);
					txtPresentStatus.setVisibility(View.VISIBLE);
					spinnerPresentStatus.setVisibility(View.VISIBLE);
					btnDisconnect.setText("Save the reason");
				} else {
					linearImage.setVisibility(View.VISIBLE);
					edCurrentReading.setVisibility(View.VISIBLE);
					txtCurrentReading.setVisibility(View.VISIBLE);
					spinnerPresentStatus.setVisibility(View.GONE);
					txtPresentStatus.setVisibility(View.GONE);
					btnDisconnect.setText("Print Disconnection Notice");
				}
			}
		});
	}
	
	private void presentStatusSpinner(){
		String[]status={"Select", "Payment Done","Will Pay Today","Others"};
		ArrayAdapter<String> adapter =new ArrayAdapter<String>(DisconnectionSend.this, R.layout.z_auto_cmpt,R.id.txtAutoFill,status);
		spinnerPresentStatus.setAdapter(adapter);
		spinnerPresentStatus.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,int position, long id) {
				presentStaus=spinnerPresentStatus.getItemAtPosition(position).toString();
				//TODO
						if (presentStaus.equals("Payment Done")) {
							btnCapture.setText("Capture Payment Receipt");
						} else if (presentStaus.equals("Door Lock")) {
							btnCapture.setText("Capture Door No./House");
						} else {
							btnCapture.setText("Capture Meter");
						}
	
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				
			}
		});
		
	}
	
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
		}
	}
	
	
@Override
public void onBackPressed() {
	Intent intent =new Intent(DisconnectionSend.this, DisconnectionView.class);
	startActivity(intent);
};


	private void offlineSaving(boolean isDisconnected){
		discDb.open(); 
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.ENGLISH);
		if(discDb.updateDisconnection(mrReading, remarks, serverId, format.format(new Date()), imageString,  "OFFLINE",isDisconnected)) {
			discDb.close();
			BluetoothChat.isReport=false;
			if(isDisconnected)
			{
				Intent intent = new Intent(DisconnectionSend.this,BluetoothChat.class);
				startActivity(intent);
			}else
			{
				Intent intent = new Intent(DisconnectionSend.this,DisconnectionView.class);
				startActivity(intent);
				Toast.makeText(getApplicationContext(), "Saved Details", Toast.LENGTH_LONG).show();
			}
			this.finish();
		}else {
			discDb.close();
			Toast.makeText(getApplicationContext(), "Not Saved", Toast.LENGTH_SHORT).show();
		}
		
	
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.back_button, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case R.id.btn_back:

			Intent intent = new Intent(DisconnectionSend.this, DisconnectionView.class);
			startActivity(intent);
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
	private void sendNoImage() {
		@SuppressWarnings("deprecation")
		Drawable noimage = getResources().getDrawable(R.drawable.no_image);
		Bitmap bitmap = ((BitmapDrawable) noimage).getBitmap();
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
		byte[] bitmapdata = stream.toByteArray();
		bitmapdata = stream.toByteArray();
		imageString = Base64.encodeToString(bitmapdata, Base64.DEFAULT);
	}
}
