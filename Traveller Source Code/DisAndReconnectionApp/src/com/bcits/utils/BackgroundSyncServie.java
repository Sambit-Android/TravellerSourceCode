package com.bcits.utils;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.IBinder;
import android.os.StrictMode;
import android.util.Log;

import com.sqlite.mvs.DbDisconnection;

public class BackgroundSyncServie extends Service {

	 String sdoCodeOffline;
	 String listNumberOffline;
	@Override
	public void onCreate() {
		Log.e("Service", "=============Created");
	}

	@Override
	public IBinder onBind(Intent intent) {
		Log.e("Service", "=============onBind");

		return null;
	}

	@Override
	public void onDestroy() { 
		super.onDestroy();
		Log.e("Service", "=============Destroy");

	}

	@Override
	public void onStart(Intent intent, int startId) {
		try {
			if (android.os.Build.VERSION.SDK_INT > 9) {
				StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
				StrictMode.setThreadPolicy(policy);
			}
			Log.e("Service", "=============Started");
			if(haveNetworkConnection()){
			DbDisconnection db=new DbDisconnection(getBaseContext());
			db.runSyncDisconnection();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean onUnbind(Intent intent) {
		Log.e("Service", "=============Unbind");
		return super.onUnbind(intent);
	}
	private boolean haveNetworkConnection() {
		
		try {
			ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo[] netInfo = cm.getAllNetworkInfo();
			for (NetworkInfo ni : netInfo) {
				if (ni.getTypeName().equalsIgnoreCase("MOBILE")
						|| ni.getTypeName().equalsIgnoreCase("wifi"))
					if (ni.isConnected())
						return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return true;
		}
		
		return false;
	}
	
	
	

/*	private void updateListFromServer() {
		System.out.println("inside the update method");
		if (haveNetworkConnection()) {
			System.out.println("Have network ========");
			DbDisconnection dbbb = new DbDisconnection(getBaseContext());
			dbbb.open();
			if (dbbb.getListNo().split("@@@")[0] != null && dbbb.getListNo().split("@@@")[1] != null) {
				System.out.println("List Number and sdocode from db is not null   ========");
				 sdoCodeOffline = dbbb.getListNo().split("@@@")[0];
				 listNumberOffline = dbbb.getListNo().split("@@@")[1];
				 System.out.println("List Number and sdocode splitted and asigned to variables  ========");
				System.out.println(sdoCodeOffline +"    "+listNumberOffline+"   ===sdocode  and listnumber======");
				if (dbbb.checkListExists(listNumberOffline, sdoCodeOffline) && dbbb.getAllOfflineData()==false) {
					 System.out.println("List Number and sdocode exist and no data to update  ========");
					new GetListOffline().execute();
				} else {
				}
			}
		}
	}*/
	

/*	private class GetListOffline extends AsyncTask<Void, Void, Void> {
		String responsefromserver = null;
		protected void onPreExecute() {
			System.out.println("Insise synctask======");
		}

		protected Void doInBackground(Void... params) {
			try {
				JSONArray ja1 = new JSONArray();
				JSONObject jsonarray = new JSONObject();
				try {
					jsonarray.put("SDOCODE", sdoCodeOffline);
					jsonarray.put("LISTNO", listNumberOffline);
					ja1.put(jsonarray);
					SendRequest req = new SendRequest();
					responsefromserver = req.uploadToServer("getDisReConnectionMobileUser", ja1);
					Log.e("Response Offline", responsefromserver);

				} catch (JSONException e) {
					e.printStackTrace();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

		protected void onPostExecute(Void unused) {
			if (responsefromserver == null) {
				
				System.out.println("Server is down");
				
			} else if (responsefromserver.equals("[]")) {
				
				System.out.println("No Data Found. Please check the list number again. Sync Table");
				
			} else {
				
				System.out.println("Going to delete table ===");
				
				DbDisconnection db = new DbDisconnection(getBaseContext());
				db.open();
				db.deleteDisconnectionTable();
			
				System.out.println("Going to add table ===");
				
				try {
					JSONArray ja = new JSONArray(responsefromserver);
					
					for (int i = 0; i < ja.length(); i++) {
						JSONObject obj = ja.getJSONObject(i);

						System.out.println(obj.getString("username")
								+ "=====UserName");

						db.insertDisconnection(obj.getString("rrno"),
								obj.getString("listno"),
								obj.getString("listdate"),
								obj.getString("tariff"),
								obj.getString("disdate"),
								obj.getString("disfr"),
								obj.getString("sdocode"),
								obj.getString("remarks"),
								obj.getString("dramt"),
								obj.getString("rdngdate"),
								obj.getString("mtrcode"),
								obj.getString("arrears"),
								obj.getString("username"),
								obj.getString("datestamp"),
								obj.getString("reclistno"),
								obj.getString("redate"),
								obj.getString("ageing"),
								obj.getString("status"), obj.getString("id"),
								obj.getString("collectionid"),
								obj.getString("oldrrno"),
								obj.getString("consumer_name"),
								obj.getString("address"),
								obj.getString("fdrcode"),
								obj.getString("tccode"),
								obj.getString("poleno"));
					}
					System.out.println("Completed to add table ===");
					db.close();

				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		}
	}*/
}