package bcits.camera.lib;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.Toast;

public class CustomCamera {
	static String path = "/sdcard/BCITS/IMAGES/";
	
    static Context activityIn;
    
	static Class<?> activityOut;

	public static String imagePath;
	
	public static Bitmap imageBitmap;
	
	public static byte[] imageByteArray;
	

	
	public static byte[] getImageByteArray() {
		return imageByteArray;
	}
	public static void setImageByteArray(byte[] imageByteArray) {
		CustomCamera.imageByteArray = imageByteArray;
	}
	public static String getImageBase64Format() {
		return imageBase64Format;
	}
	public static void setImageBase64Format(String imageBase64Format) {
		CustomCamera.imageBase64Format = imageBase64Format;
	}

	public static String imageBase64Format;
	
	public static Bitmap getImageBitmap() {
		return imageBitmap;
	}
	public static void setImageBitmap(Bitmap imageBitmap) {
		CustomCamera.imageBitmap = imageBitmap;
	}
	
	public static String getImagePath() {
		return imagePath;
	}
	public static void setImagePath(String imagePath) {
		CustomCamera.imagePath = imagePath;
	}
	
	
	
	public static void openCamera(Context activityInput,Class<?> activityOutPutClass,String imageNameHint) {
		Calendar c = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("ddMMyyyy",Locale.getDefault());
		String date = df.format(c.getTime());
		
		setImagePath(path+imageNameHint+"_"+date+ ".jpg");
		activityOut=activityOutPutClass;
		
		
		File videoDirectory = new File(path);

		if (!videoDirectory.exists()) {
			videoDirectory.mkdirs();
		}
		
		
		activityInput.startActivity(new Intent(activityInput, MainActivity.class));
	}
	
	
	
	public static void clearImages(Context context) {
		activityIn=context;
		new DeleteOldImages().execute();
	}
	
	private static class DeleteOldImages extends AsyncTask<Void, Void, Void>{
		boolean isDeleted;
		private ProgressDialog dialog;
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			dialog=new ProgressDialog(activityIn);
			dialog.setMessage("Clearing Memory...");
			dialog.setCancelable(false);
			dialog.show();
			
		}
		
		@Override
		protected Void doInBackground(Void... params) {
			
		        //Delete Image New Path
			  File dir = new File("/sdcard/BCITS/IMAGES"); 
			  if (dir.isDirectory()) 
			  {
			      String[] children = dir.list();
			      for (int i = 0; i < children.length; i++)
			      {
			    	  isDeleted=   new File(dir, children[i]).delete();
			      }
			  }
			  
			  //Delete Image Old Path
			  File dirr = new File("/sdcard/CESCTRM/CESCImages"); 
			  if (dirr.isDirectory()) 
			  {
			      String[] children = dirr.list();
			      for (int i = 0; i < children.length; i++)
			      {
			    	  isDeleted=   new File(dirr, children[i]).delete();
			      }
			  }
			return null;
		}
		
		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			dialog.dismiss();
			  if(isDeleted) {
		        	Toast.makeText(activityIn, "Memory Cleared", Toast.LENGTH_SHORT).show();
		        }else {
		        	Toast.makeText(activityIn, "Memory Not Cleared", Toast.LENGTH_SHORT).show();
		        }
		}
	}
	
}
