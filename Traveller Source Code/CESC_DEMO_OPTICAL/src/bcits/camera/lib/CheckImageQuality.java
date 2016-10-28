package bcits.camera.lib;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bcits.bsmartbilling.R;

public class CheckImageQuality extends Activity {

	ImageView imageViewImage;
	Button btnBackToCamera,btnForward;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.check_image_quality);
		imageViewImage = (ImageView) findViewById(R.id.imageViewImageQuality);
		btnBackToCamera=(Button)findViewById(R.id.btnBackToCamera);
		btnForward =(Button)findViewById(R.id.btnForward);

		Bitmap imageBitmap = CustomCamera.getImageBitmap();

		imageViewImage.setImageBitmap(imageBitmap);
		
		btnBackToCamera.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			Toast.makeText(getApplicationContext(), "Take Photo Again", Toast.LENGTH_SHORT).show();
			startActivity(new Intent(CheckImageQuality.this, MainActivity.class));
			CheckImageQuality.this.finish();
			
				CustomCamera.setImageBitmap(null); // SETTING THE BITMAP
				CustomCamera.setImageByteArray(null); // SETTING THE BYTE ARRAY
				CustomCamera.setImageBase64Format(null); // SETTING BASE 64
															// STRING
				
				CustomCamera.imageByteArray=null;
				CustomCamera.imageBase64Format=null;
				CustomCamera.imageBitmap=null;
			}
		});
		
		btnForward.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			startActivity(new Intent(CheckImageQuality.this, CustomCamera.activityOut));
			CheckImageQuality.this.finish();
			}
		});
	}
	
	 @Override
	public void onBackPressed() {
		super.onBackPressed();
	             CustomCamera.setImageBitmap(null); //SETTING THE BITMAP
				CustomCamera.setImageByteArray(null);  //SETTING THE BYTE ARRAY
				CustomCamera.setImageBase64Format(null); //SETTING BASE 64 STRING
				
				CustomCamera.imageByteArray=null;
				CustomCamera.imageBase64Format=null;
				CustomCamera.imageBitmap=null;
	 }
}
