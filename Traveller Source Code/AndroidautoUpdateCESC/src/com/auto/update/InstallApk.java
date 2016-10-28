package com.auto.update;
import java.io.File;
import java.io.FilenameFilter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;

public class InstallApk extends Activity {
	public static boolean uninstallStatus = false;
	public long serverfileLength = 0 ;
	public  File filename =  null ;
	
	
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.install);
	        serverfileLength = getIntent().getLongExtra("serverfileLength" , 0);
	        System.out.println("<--AndroidautoUpdate  --------     InstallApk--------onCreate ---------->");
	        Install_apk();
	     
	 	}
	 
	 
	 public void Install_apk()
	 {
		 
		 try{
		  System.out.println("<--AndroidautoUpdate  --------     InstallApk--------Install_apk ---------->");
		 
		   ExtFilter apkFilter = new ExtFilter("apk");
		   System.out.println("<--apkFilter  --------     apkFilter--------Install_apk ---------->"+apkFilter);
	        File file[] = Environment.getExternalStorageDirectory().listFiles(apkFilter);
	        
	         filename = new File("/sdcard/"+AndroidautoUpdateActivity.fileName);
	        Log.d("InstallApk", "Filter applied. Size: "+ file.length);
	        
System.out.println("filename size : "+ filename.length());
	

        if(filename.exists())
        {
	
        	System.out.println("filename size : "+ filename.length());
        	if( filename.length() == serverfileLength)
        	{
	
        		Intent intent = new Intent(Intent.ACTION_VIEW);
        		intent.setDataAndType(Uri.fromFile(filename), "application/vnd.android.package-archive");
        		startActivity(intent);
        		
        		uninstallStatus = true ;
        		System.out.println("Coming here************************************intent*****************************"+intent);
        	}
        	else
        	{
        		filename.delete();
        		finish();
        	}
        }else
        {
        		Toast.makeText(getApplicationContext(), ("Sorry...! Server is down. Please try again later"), Toast.LENGTH_LONG).show();
        		finish();
        }
		 }catch (Exception e) {
			// TODO: handle exception
			 Toast.makeText(getApplicationContext(), ("Sorry...! Unable to install new version."), Toast.LENGTH_LONG).show();
			 moveTaskToBack(true);
		}
		 
           
	 }
	 
	 @Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}


	public boolean onKeyDown(int keycode, KeyEvent event) {
			if (keycode == KeyEvent.KEYCODE_BACK) {
				
				  System.out.println("<--AndroidautoUpdate  --------     InstallApk--------onKeyDown ---------->");
				Log.e("Main Destroyvvvvvvv ++", "  Main  Destroy++");
				moveTaskToBack(true);
			}
			return super.onKeyDown(keycode, event);
		}

	 
	 class ExtFilter implements FilenameFilter { 
	        String ext; 
	        public ExtFilter(String ext) { 
	        	
	            this.ext = "." + ext; 
	        } 
	       
			public boolean accept(File dir, String name) { 
	            return name.endsWith(ext); 
	        }

	    }

	 public void removeFile() {
		 AlertDialog	show = new AlertDialog.Builder(
					InstallApk.this)
					.setTitle("Info")
					.setMessage(
							"Sorry,connection cannot made at this time. Server is down. Please try again later")
					.setPositiveButton("OK", new DialogInterface.OnClickListener() {
						
						public void onClick(DialogInterface arg0, int arg1) {
							filename.delete();
						}
					})
					.show();
		}
	 
}
