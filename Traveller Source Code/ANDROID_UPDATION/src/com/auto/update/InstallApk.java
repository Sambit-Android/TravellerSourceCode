package com.auto.update;
import java.io.File;
import java.io.FilenameFilter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Window;
import android.widget.Toast;

public class InstallApk extends Activity {
	public static boolean uninstallStatus = false;
	public long serverfileLength = 0 ;
	public  File filename =  null ;
	
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
			this.requestWindowFeature(Window.FEATURE_NO_TITLE);
	        setContentView(R.layout.update_main);
	        serverfileLength = getIntent().getLongExtra("serverfileLength" , 0);
	        Install_apk();
	     
	 	}
	  
	 @SuppressLint("SdCardPath")
	private void Install_apk()
	 {
		 
		 try{
		 
		   ExtFilter apkFilter = new ExtFilter("apk");
	        File file[] = Environment.getExternalStorageDirectory().listFiles(apkFilter);
	        
	         filename = new File( "/sdcard/"+AndroidautoUpdateActivity.fileName);
	        Log.d("InstallApk", "Filter applied. Size: "+ file.length);
	        
	

        if(filename.exists())
        {
	
        	if( filename.length() == serverfileLength)
        	{
	
        		
        		Intent intent = new Intent(Intent.ACTION_VIEW);
        		intent.setDataAndType(Uri.fromFile(filename), "application/vnd.android.package-archive");
        		startActivity(intent);
    
        		uninstallStatus = true ;
        		finish();
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
			 Toast.makeText(getApplicationContext(), ("Sorry...! Unable to install new version."), Toast.LENGTH_LONG).show();
			 moveTaskToBack(true);
		}
		 
           
	 }
	 
	 @Override
	protected void onDestroy() {
		super.onDestroy();
	}


	public boolean onKeyDown(int keycode, KeyEvent event) {
			if (keycode == KeyEvent.KEYCODE_BACK) {
				
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

	 
}
