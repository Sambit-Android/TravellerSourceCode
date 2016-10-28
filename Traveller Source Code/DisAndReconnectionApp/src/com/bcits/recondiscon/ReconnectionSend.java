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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bcits.utils.AmrMethods;
import com.sqlite.mvs.DbDisconnection;

public class ReconnectionSend extends MasterActivity {
	EditText edRemarks;
	TextView txtAccount;
	Button btnDisconnect, btnCapture;

	AlertDialog show;
	String  remarks, serverId;
	DbDisconnection discDb;

	ImageView imgSetImage;
	String imageString;

	private static final int CAMERA_PIC_REQUEST_ONE = 23;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle("Reconnection");
		setContentView(R.layout.reconnection_send);

		imgSetImage = (ImageView) findViewById(R.id.imgSetImage);
		btnCapture = (Button) findViewById(R.id.btnCapture);
		discDb = new DbDisconnection(getBaseContext());
		edRemarks = (EditText) findViewById(R.id.edRemarks);
		txtAccount = (TextView) findViewById(R.id.txtAccount);
		btnDisconnect = (Button) findViewById(R.id.btnDisconnect);
		txtAccount.setText(ReconnectionView.disc.getRrno());
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
				}
			}

		});

		btnDisconnect.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (AmrMethods.doubleClick()) {
					boolean hasDrawable = (imgSetImage.getDrawable() != null);
					if(hasDrawable==false){
						 new AlertDialog.Builder(ReconnectionSend.this).setMessage("Please Capture Meter").setPositiveButton("Ok", null).show();	
						}
						else{
							remarks = edRemarks.getText().toString().trim();
							serverId = ReconnectionView.disc.getId();
							offlineSaving();
						}
				}
			}
		});
	}

	@Override
	public void onBackPressed() {
		Intent intent = new Intent(ReconnectionSend.this,ReconnectionView.class);
		startActivity(intent);
	};

private void offlineSaving(){
	discDb.open();
	DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.ENGLISH);
	if(discDb.updateReConnection(remarks, serverId, format.format(new Date()),imageString, "OFFLINE")) {
		discDb.close();
		Intent intent = new Intent(ReconnectionSend.this,ReconnectionView.class);
		startActivity(intent);
		Toast.makeText(getApplicationContext(), "Saved Details", Toast.LENGTH_LONG).show();
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
			Intent intent = new Intent(ReconnectionSend.this,ReconnectionView.class);
			startActivity(intent);
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
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
}
