package bcits.camera.lib;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.hardware.Camera.ErrorCallback;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.ShutterCallback;
import android.media.ExifInterface;
import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bcits.bsmartbilling.R;

public class MainActivity extends Activity {

	//ImageView image;
	Activity context;
	Preview preview;
	Camera camera;
	ImageView fotoButton;
	LinearLayout progressLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.custom_camera_activity_main_bcits);
		context = this;

		fotoButton = (ImageView) findViewById(R.id.imageView_foto);
		//image = (ImageView) findViewById(R.id.imageView_photo);
		progressLayout = (LinearLayout) findViewById(R.id.progress_layout);
		progressLayout.setVisibility(View.GONE);
		preview = new Preview(this,	(SurfaceView) findViewById(R.id.KutCameraFragment));
		FrameLayout frame = (FrameLayout) findViewById(R.id.preview);
		frame.addView(preview);
		preview.setKeepScreenOn(true);
		fotoButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				try {
					takeFocusedPicture();
				} catch (Exception e) {

				}
				fotoButton.setClickable(false);
				progressLayout.setVisibility(View.VISIBLE);
			}
		});
	}

	@Override
	protected void onResume() {
		super.onResume();
try {
	if (camera == null) {
		camera = Camera.open();
		camera.startPreview();
		camera.setErrorCallback(new ErrorCallback() {
			public void onError(int error, Camera mcamera) {

				camera.release();
				camera = Camera.open();
				Log.d("Camera Error", "error camera");

			}
		});
	}
	if (camera != null) {
		if (Build.VERSION.SDK_INT >= 14)
			setCameraDisplayOrientation(context,CameraInfo.CAMERA_FACING_BACK, camera);
	     	preview.setCamera(camera);
	}
} catch (Exception e) {
	e.printStackTrace();
	System.out.println(e.getMessage());
	camera=null;
	MainActivity.this.finish();
}
		
	}

	private void setCameraDisplayOrientation(Activity activity, int cameraId,android.hardware.Camera camera) {
		
		android.hardware.Camera.CameraInfo info = new android.hardware.Camera.CameraInfo();
		android.hardware.Camera.getCameraInfo(cameraId, info);
		int rotation = activity.getWindowManager().getDefaultDisplay().getRotation();
		int degrees = 0;
		switch (rotation) {
		case Surface.ROTATION_0:
			degrees = 0;
			break;
		case Surface.ROTATION_90:
			degrees = 90;
			break;
		case Surface.ROTATION_180:
			degrees = 180;
			break;
		case Surface.ROTATION_270:
			degrees = 270;
			break;
		}

		int result;
		if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
			result = (info.orientation + degrees) % 360;
			result = (360 - result) % 360; // compensate the mirror
		} else { // back-facing
			result = (info.orientation - degrees + 360) % 360;
		}
		camera.setDisplayOrientation(result);
	}

	Camera.AutoFocusCallback mAutoFocusCallback = new Camera.AutoFocusCallback() {
		@Override
		public void onAutoFocus(boolean success, Camera camera) {

			try {
				camera.takePicture(mShutterCallback, null, jpegCallback);
			} catch (Exception e) {

			}

		}
	};

	Camera.ShutterCallback mShutterCallback = new ShutterCallback() {

		@Override
		public void onShutter() {

		}
	};

	public void takeFocusedPicture() {
		camera.autoFocus(mAutoFocusCallback);

	}
/*
	PictureCallback rawCallback = new PictureCallback() {
		public void onPictureTaken(byte[] data, Camera camera) {
			// Log.d(TAG, "onPictureTaken - raw");
		}
	};*/

	PictureCallback jpegCallback = new PictureCallback() {
		public void onPictureTaken(byte[] data, Camera camera) {

			FileOutputStream outStream = null;

			try {
				// Write to SD Card
				outStream = new FileOutputStream(CustomCamera.getImagePath());
				outStream.write(data);
				outStream.close();

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {

			}

			Bitmap realImage;
			final BitmapFactory.Options options = new BitmapFactory.Options();
			options.inSampleSize = 5;

			options.inPurgeable = true; // Tell to gc that whether it needs free
										// memory, the Bitmap can be cleared

			options.inInputShareable = true; // Which kind of reference will be
												// used to recover the Bitmap
												// data after being clear, when
												// it will be used in the future

			realImage = BitmapFactory.decodeByteArray(data, 0, data.length,	options);
			ExifInterface exif = null;
			try {
				exif = new ExifInterface(CustomCamera.getImagePath());
			} catch (IOException e) {
				e.printStackTrace();
			}

			try {
				Log.d("EXIF value",
						exif.getAttribute(ExifInterface.TAG_ORIENTATION));
				if (exif.getAttribute(ExifInterface.TAG_ORIENTATION).equalsIgnoreCase("1")) {
					realImage = rotate(realImage, 90);
				} else if (exif.getAttribute(ExifInterface.TAG_ORIENTATION)	.equalsIgnoreCase("8")) {
					realImage = rotate(realImage, 90);
				} else if (exif.getAttribute(ExifInterface.TAG_ORIENTATION)	.equalsIgnoreCase("3")) {
					realImage = rotate(realImage, 90);
				} else if (exif.getAttribute(ExifInterface.TAG_ORIENTATION)	.equalsIgnoreCase("0")) {
					realImage = rotate(realImage, 90);
				}
			} catch (Exception e) {

			}

			
			
		//	image.setImageBitmap(realImage);

			if(storeImage(realImage)) {
			
				Toast.makeText(getApplicationContext(), "Image Captured", Toast.LENGTH_SHORT).show();
				startActivity(new Intent(MainActivity.this,CheckImageQuality.class));
				MainActivity.this.finish();
				
			}else {
			
				Toast.makeText(getApplicationContext(), "Image Not Captured", Toast.LENGTH_SHORT).show();
				MainActivity.this.finish();
			}
			
		//	fotoButton.setClickable(true);
		//	camera.startPreview();
		//	progressLayout.setVisibility(View.GONE);

		}
	};

	private static Bitmap rotate(Bitmap source, float angle) {
		Matrix matrix = new Matrix();
		matrix.postRotate(angle);
		return Bitmap.createBitmap(source, 0, 0, source.getWidth(),	source.getHeight(), matrix, false);
	}
	private boolean storeImage(Bitmap image) {
		
		//image  = imageToSqure(image);// CROP IMAGE 1
		
		int reductionHeightTop=120;
		int reductionHeightBottom=40;
		image=	Bitmap.createBitmap(image,0, reductionHeightTop, image.getWidth(), image.getHeight()-(reductionHeightTop+reductionHeightBottom)); //CUTTING THE TOP 120 pixels and Bottom 40 pixels
		
		
		int width=image.getWidth();
		int height =image.getHeight();
		
		double widthFinal =width/1.8;
		double heightFinal=height/1.8;
		
		image=getResizedBitmap(image, (int)widthFinal, (int)heightFinal);
		//image = Bitmap.createScaledBitmap(image, (int)widthFinal, (int)heightFinal, false); // RESIZE IMAGE 3
		
		/*File directory = new File(CustomCamera.getImagePath());

		if (!directory.exists()) {
			directory.mkdirs();
		}*/
	  
	    try {
	     /*   FileOutputStream fos = new FileOutputStream(CustomCamera.getImagePath());
	        image.compress(Bitmap.CompressFormat.PNG, 100, fos);  //SAVING TO MEMORY CARD
	        fos.close(); */
	        
	        ByteArrayOutputStream baos = new ByteArrayOutputStream();  
	        image.compress(Bitmap.CompressFormat.PNG, 100, baos);
		    byte[] b = baos.toByteArray();   //MAKING BYTE ARRAY
		    String imageEncoded = Base64.encodeToString(b,Base64.DEFAULT); // MAKING BASE 64 STRING
	        
		    CustomCamera.setImageBitmap(image); //SETTING THE BITMAP
			CustomCamera.setImageByteArray(b);  //SETTING THE BYTE ARRAY
			CustomCamera.setImageBase64Format(imageEncoded); //SETTING BASE 64 STRING
	        
	      /*  File imageFile=new File(CustomCamera.getImagePath());
	        
	        if(imageFile.delete()) {
	        	System.out.println("DELECTED FILE +++++++++++++++");
	        }else {
	        	System.out.println("NOT DELECTED FILE +++++++++++++++");

	        }
			*/
	        return true;
	        
	    } /*catch (FileNotFoundException e) {
	        Log.d("ERROR", "File not found: " + e.getMessage());
	    }*/ catch (Exception e) {
	        Log.d("ERROR", "Error accessing file: " + e.getMessage());
	    }  
	    return false;
	}
	
	private Bitmap getResizedBitmap(Bitmap bm, int newHeight, int newWidth)
	{
	    int width = bm.getWidth();
	    int height = bm.getHeight();
	    float scaleWidth = ((float) newWidth) / width;
	    float scaleHeight = ((float) newHeight) / height;
	    // create a matrix for the manipulation
	    Matrix matrix = new Matrix();
	    // resize the bit map
	    matrix.postScale(scaleWidth, scaleHeight);
	    // recreate the new Bitmap
	    Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, false);
	    return resizedBitmap;
	}
	
	

	
	private byte[] getByteValue(Bitmap b) {
		//calculate how0 4many bytes our image consists of.
		int bytes = b.getByteCount();
		//or we can calculate bytes this way. Use a different value than 4 if you don't use 32bit images.
		//int bytes = b.getWidth()*b.getHeight()*4; 
		ByteBuffer buffer = ByteBuffer.allocate(bytes); //Create a new buffer
		b.copyPixelsToBuffer(buffer); //Move the byte data to the buffer

		byte[] array = buffer.array(); //Get the underlying array containing the data.
		
		return  array;
	}
	
/*	private String getBase64Image(Bitmap b) {
		String convertedImageOne = Base64.encodeToString(getByteValue(b), Base64.DEFAULT);
		return  convertedImageOne;
	}*/
	private static String getBase64Image(Bitmap image)
	{
	    Bitmap immagex=image;
	    ByteArrayOutputStream baos = new ByteArrayOutputStream();  
	    immagex.compress(Bitmap.CompressFormat.JPEG, 100, baos);
	    byte[] b = baos.toByteArray();
	    String imageEncoded = Base64.encodeToString(b,Base64.DEFAULT);

	    Log.e("LOOK", imageEncoded);
	    return imageEncoded;
	}
	private Bitmap imageToSqure(Bitmap srcBmp) {
		//Bitmap croppedBitmap = Bitmap.createBitmap(originalBitmap, 10, 10, originalBitmap.getWidth() - 20, originalBitmap.getHeight() - 20);
		//srcBmp=	Bitmap.createBitmap(srcBmp,0, 100, srcBmp.getWidth(), srcBmp.getHeight()-100);
		
		Bitmap dstBmp = null;
		if (srcBmp.getWidth() >= srcBmp.getHeight()) {

			dstBmp = Bitmap.createBitmap(srcBmp,
					srcBmp.getWidth() / 2 - srcBmp.getHeight() / 2, 0,
					srcBmp.getHeight(), srcBmp.getHeight());

		} else {

			dstBmp = Bitmap.createBitmap(srcBmp, 0, srcBmp.getHeight() / 2
					- srcBmp.getWidth() / 2, srcBmp.getWidth(),
					srcBmp.getWidth());
		}
		return dstBmp;
	}
	private Bitmap cropBitmap1(Bitmap bitmap)
	{
	    Bitmap bmOverlay = Bitmap.createBitmap(320, 480, Bitmap.Config.ARGB_8888);

	    Paint p = new Paint();
	    p.setXfermode(new PorterDuffXfermode(Mode.CLEAR));              
	    Canvas c = new Canvas(bmOverlay); 
	    c.drawBitmap(bitmap, 0, 0, null); 
	    c.drawRect(30, 30, 100, 100, p);

	    return bmOverlay;
	}
	
	
	
	 @Override
	    protected void onPause() {
	        super.onPause();
	        if (camera != null) {
	        	camera = null;
	        }
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
