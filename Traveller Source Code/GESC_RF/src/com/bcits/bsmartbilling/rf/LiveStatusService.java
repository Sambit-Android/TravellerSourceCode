package com.bcits.bsmartbilling.rf;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;



import com.utils.DatabaseHandler;
import com.utils.DbHandlerBillStatus;
import com.utils.Sendrequest;
import com.utils.helper.LiveStatusHelper;


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

public class LiveStatusService extends Service {
	
	/*@Override
	public void onCreate() {
		System.out.println("*******************My Live Status Service ************************");
	}*/
	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}

	/*@Override
	public void onDestroy() {
	 super.onDestroy();
	}
	
	
	@Override
	public void onStart(Intent intent, int startId) {
	 super.onStart(intent, startId);
	 if (getLiveStatus()) {
		if (haveNetworkConnection()) {
				new performBackgroundTaskupload().execute();
			}
		}
	 
	}
	
	*//**
	 * @author Guru
	 * @date 05-Nov-2014
	 * @category Updating Billing live status to server 
	 *//*
	public class performBackgroundTaskupload extends AsyncTask<Void, Void, Void> {
		Sendrequest send;
		DbHandlerBillStatus handlerBillStatus;
		List<LiveStatusHelper>list;
		String responsefromserver ;
		protected void onPreExecute() {
			super.onPreExecute();
			try {
				handlerBillStatus = new DbHandlerBillStatus(getApplicationContext());
				handlerBillStatus.openToRead();
				list =  handlerBillStatus.getLiveStatusToUpdate();
				handlerBillStatus.close();
			} catch (Exception e) {
				FilePropertyManager.appendLog("LiveStatusService : \n"	+ Log.getStackTraceString(e));
				e.printStackTrace();
			}
		}

		protected Void doInBackground(Void... params) {
			try {
				if( list != null  &&  list.size()>0 ){
				JSONArray ja1 = new JSONArray();
				TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
				String   imeino = tm.getDeviceId();
				for (int i = 0; (i < list.size()); i++) {
					ja1.put(list.get(i).getJSONObject(imeino));
				}
				String url = "LiveStatus";
				send = new Sendrequest();
				responsefromserver = send.uploadtoServerfromlocaldb_forService(	url, ja1);
				System.out.println("Response from server" + responsefromserver);
				}
			} catch (Exception e) {
				FilePropertyManager.appendLog("LiveStatusService : \n"+ Log.getStackTraceString(e));
				e.printStackTrace();
			}
			return null;
		}

		protected void onPostExecute(Void unused) {
			try {
				if (MasterLibraryFunction.IsNullOrEmpty(ErrorClass.getErrorMessages())) {
					ErrorClass.errorMessage = "";
				} else {
					if (responsefromserver != null && (!responsefromserver.contains("<html>"))) { 
						JSONArray arr = null;
						arr = new JSONArray(responsefromserver);

						handlerBillStatus.openToWrite();
						JSONObject downloadjson = null;
						downloadjson = arr.getJSONObject(0);
						@SuppressWarnings("rawtypes")
						Iterator downloaditer = downloadjson.keys();
						while (downloaditer.hasNext()) {
							String consumerno = (String) downloaditer.next();
							String updatedstatus = null;
							updatedstatus = downloadjson.getString(consumerno);
							
							handlerBillStatus.setUpdatedStatus(consumerno,	updatedstatus);
						}
						handlerBillStatus.removeOldrecords("200");
						handlerBillStatus.close();
					}
				}
			} catch (Exception e) {
				FilePropertyManager.appendLog(Log.getStackTraceString(e));
				e.printStackTrace();
			}
		}
	}
	*//**
	 * @author Guru
	 * @date 05-Nov-2014
	 * @category Dashboard 
	 *//*
	public boolean getLiveStatus(){
		
		long l = 0;
		 DatabaseHandler databasehandler = new DatabaseHandler(getApplicationContext());
			try {
				 String[]  dashboardStatus=null ;
				databasehandler.openToRead();
				dashboardStatus = databasehandler.getLiveStatus();
				databasehandler.close();
				if(dashboardStatus != null)
				{
					 // TOTAL , BILLED , SYNCH , SITECODE , MRCODE
						 DbHandlerBillStatus status = new DbHandlerBillStatus(getApplicationContext());
						 status.openToWrite();
						  l = status.insert(dashboardStatus[4], dashboardStatus[5], dashboardStatus[1], dashboardStatus[2], dashboardStatus[0],String.valueOf(new Timestamp(new Date().getTime())));
						 status.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				System.gc() ;
			}
			 return l>0? true:false;
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
		try {
			DbHandlerBillStatus dbHandlerBillStatus = new DbHandlerBillStatus(getApplicationContext()).openToRead();
			dbHandlerBillStatus.openToRead();
			b = dbHandlerBillStatus.getCount();
			dbHandlerBillStatus.close();
		} catch (Exception exception) {
			FilePropertyManager.appendLog(Log.getStackTraceString(exception));
		}
		return b ;
	}*/
}
