package com.bcits.bsmartbilling;

import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import Utils.ErrorClass;
import Utils.FilePropertyManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.IBinder;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.utils.DatabaseHandler;
import com.utils.Sendrequest;
import com.utils.helper.UploadHandler;

public class MyAlarmService extends Service {

	@Override
	public void onCreate() {
		System.out.println("*******************MY Alarm service ************************");
	}

	@Override
	public IBinder onBind(Intent intent) {
	 return null;
	}

	@Override
	public void onDestroy() {
	 super.onDestroy();
	}
	
	
	@Override
	public void onStart(Intent intent, int startId) {
	 // TODO Auto-generated method stub
	 super.onStart(intent, startId);
	
		if ((!UtilMaster.isUpdateEnable()) && haveNetworkConnection()	&& isUnSycData())
			new performBackgroundTaskupload().execute();
	 
	}
	/**
	 * @author Guru
	 * @date 05-Nov-2014
	 * @category Updating billed data from local to server 
	 */
	public class performBackgroundTaskupload extends AsyncTask<Void, Void, Void> 
	{
		int count_update = 0 ;
		DatabaseHandler databaseHandler ;
		Sendrequest send ;
		String responsefromserver ;
		String err_msg_show = "OK";
		protected void onPreExecute() 
		{
			super.onPreExecute();
			/*Dialog.setMessage(" Uploading Please wait...");
			Dialog.setCancelable(false);
			Dialog.show();*/
		}

		protected Void doInBackground(Void... params) {
			
			try {
				
				TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
				String   imeino = tm.getDeviceId();
				
				
				databaseHandler = new DatabaseHandler(getApplicationContext());
				/** getting rrno,billed and unbilled count */
				databaseHandler.openToRead();
				List<UploadHandler> download = databaseHandler.getBilledDataToUpload("6" , imeino);
				databaseHandler.close() ;
				JSONArray ja1 = new JSONArray();
				//JSONObject sdoonj = new JSONObject();
				for (int i = 0;  ( i < download.size()  ) ; i++) {
					ja1.put(download.get(i).getJSONObject());
				}
				String url1 = "updatefromlocal/billeddata";
				send = new Sendrequest();
				responsefromserver = send.uploadtoServerfromlocaldb_forService(url1, ja1);
				System.out.println("Response from server" + responsefromserver);
				
			} catch (Exception e) {
				FilePropertyManager.appendLog("MyAlarmService : \n"+Log.getStackTraceString(e));
				e.printStackTrace();
			}

			return null;
		}

		protected void onPostExecute(Void unused) {

			try{
				count_update = 0 ;
			if (MasterLibraryFunction.IsNullOrEmpty(ErrorClass.getErrorMessages()))
			{
			//	ERROR MSG 
				if (ErrorClass.getErrorMessages().contains("timed out")) 
				{
					ErrorClass.errorMessage = "";
					err_msg_show = "Connection timed out. Server might be shut down." ;
				} else if (ErrorClass.getErrorMessages().contains("java.net.SocketTimeoutException"))
				{
					ErrorClass.errorMessage = "";
					err_msg_show = "Socket timed out";
					
					
					
				} else if (ErrorClass.getErrorMessages().contains("Connection refused")) 
				{
					ErrorClass.errorMessage = "";
					err_msg_show ="Network unreachable . Please Try again later" ;
					 
				}
			//	mProgressDialog.dismiss();

			}else{
				if (responsefromserver != null) {
					
					JSONArray arr = null;
					arr = new JSONArray(responsefromserver);
					
					databaseHandler.openToWrite();
					JSONObject downloadjson = null;
					downloadjson = arr.getJSONObject(0);
					@SuppressWarnings("rawtypes")
					Iterator downloaditer = downloadjson.keys();
					while (downloaditer.hasNext()) {
						
						String consumerno = (String) downloaditer.next();
						String updatedstatus = null;
						updatedstatus = downloadjson.getString(consumerno);
						
						
						if(updatedstatus.trim().equalsIgnoreCase("1"))  {
							
							count_update++;
						}
						databaseHandler.setServerUpdateResponse(consumerno,	updatedstatus);
					}
					databaseHandler.close();
				
			}else{
				err_msg_show = "Uploading failed, try again later" ;
			}
			}
		} catch (Exception e) {
			FilePropertyManager.appendLog(Log.getStackTraceString(e));
			e.printStackTrace();
		}
				//Dialog.dismiss();
				/*if(err_msg_show.equalsIgnoreCase("OK")){
					String msg_up = "NOMSG";
					if(count_update == 0 || count_update < 0 )
					{
						msg_up = "Records not uploaded to server"; 
						new AlertDialog.Builder(MainActivity.this)
						   .setTitle("Info")
						   .setCancelable(false)
						   .setMessage(msg_up)
						   .setPositiveButton("OK",new DialogInterface.OnClickListener()
						   {
							   public void onClick(DialogInterface dialog,int which) 
							   {
								   setDashboard();
							   }
						   }).show();
					}else{
						msg_up = "Updated "+count_update+" records";
						checkAndContinueuploading(msg_up ,count_update );
					}
				 }else{
					  new AlertDialog.Builder(MainActivity.this)
					   .setTitle("Info")
					   .setMessage(err_msg_show)
					   .setCancelable(false)
					   .setPositiveButton("OK",new DialogInterface.OnClickListener()
					   {
						   public void onClick(DialogInterface dialog,int which) 
						   {
							   setDashboard();
						   }
					   }).show();
				 }*/
				
	}
	}
	
	public  boolean haveNetworkConnection() {
	    boolean haveConnectedMobile = false;
	    ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo[] netInfo = cm.getAllNetworkInfo();
	    for (NetworkInfo ni : netInfo) {
	    	Log.e("Internet connection checking" , ni.getTypeName());
	        if (ni.getTypeName().equalsIgnoreCase("MOBILE") || ni.getTypeName().equalsIgnoreCase("wifi"))
	            if (ni.isConnected())
	                haveConnectedMobile = true;
	    }
	    return  haveConnectedMobile;
	}
	boolean isUnSycData(){
		boolean b = false ;
		try{
			
		DatabaseHandler update_helper = new DatabaseHandler(getApplicationContext());
		update_helper.openToRead();
		int count_status = Integer.parseInt(update_helper.GetBilledcountfromlocal().trim());
		update_helper.close() ;
		if(count_status > 0)
		{
			b = true ;
		}else
		{
			b = false ;
		}
		}catch(Exception exception){
			FilePropertyManager.appendLog(Log.getStackTraceString(exception));
		}
		return b;
	
	}
}
