package amr.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Color;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

public class AmrMethods {
	/** A M Remith Methods */

	public static String getAppVersion(Context context) {
		PackageInfo pInfo;
		String version="";
		try {
			pInfo = context. getPackageManager().getPackageInfo(context.getPackageName(), 0);
			version = pInfo.versionName;
		} catch (NameNotFoundException e) {
			version="0.0";
			e.printStackTrace();
		}
		 return version;
	}
	
	public static void customAlert(Context context, String title,String msg) {
		
		AlertDialog.Builder builder1 = new AlertDialog.Builder(context,android.R.style.Theme_DeviceDefault_Dialog);
		builder1.setMessage(msg).setTitle(title).setCancelable(false);

		builder1.setPositiveButton("Ok", null);

		AlertDialog dialog = builder1.create();
		//alert11.getWindow().setBackgroundDrawableResource(R.drawable.transp);
		dialog.show();
		//Grab the window of the dialog, and change the width
		WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
		Window window = dialog.getWindow();
		lp.copyFrom(window.getAttributes());
		//This makes the dialog take up the full width
		lp.width = WindowManager.LayoutParams.MATCH_PARENT;
		lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
		window.setAttributes(lp);
	}
	
	public static void customToast(Context context,String message) {
		Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.CENTER, 0, 0);
		View view = toast.getView();
		TextView v = (TextView) view.findViewById(android.R.id.message);
		v.setTextColor(Color.WHITE);
		view.setBackgroundResource(android.R.drawable.screen_background_dark_transparent);
		toast.show();
	}
	

	public static void setDefaults(String key, String value, Context context) {
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
		SharedPreferences.Editor editor = prefs.edit();
		editor.putString(key, value);
		editor.commit();
	}

	public static String getDefaults(String key, Context context) {
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
		return preferences.getString(key, null);
	}
	
	public boolean listAssetFiles(Context context,String path) {
		 File rootDir = new File(Environment.getExternalStorageDirectory() + "");
		 String PATH = rootDir+ "/Manufacturer_Settings";
		   try {
			   
			File outDir = new File(PATH);
			if(!outDir.exists()){
			  if(outDir.mkdir()) {
			     System.out.println("Directory created");
			  } else {
			     System.out.println("Directory is not created");
			  }
			}else {
				return true; //Already folder copied
			}

			
			
	    String [] list = context.getAssets().list(path);
	        if (list.length > 0) {
	            for (String file : list) {
	                System.out.println(file+ "     FILENAME");
	                
	                
	                File ff = new File(PATH+"/"+file);
	                if(!ff.exists()){
	                Log.v("AMR","File Not Exist");
	                InputStream in = context.getAssets().open("Manufacturer_Settings/"+file);
	                OutputStream out = new FileOutputStream(PATH+"/"+file);

	                byte[] buffer = new byte[1024];
	                int length;
	                while ((length = in.read(buffer)) > 0) {
	                    out.write(buffer, 0, length);
	                }
	                in.close();
	                out.close();
	                
	                }
	                }
	        } 
	    } catch (IOException e) {
	        return false;
	    }

	    return true; 
	} 

	
	
}