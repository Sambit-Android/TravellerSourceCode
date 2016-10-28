package com.bcits.disconreconn;

import java.io.InputStream;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bcits.disconnreconn.R;
import com.bcits.disconrecon.utils.MRDatabaseHandler;
import com.bcits.disconrecon.utils.Sendrequest;

public class MRStatusActivity extends Activity{

	TextView title, version, txlistno, txmrname, txTotalConnections, txNotReplaced, txReplaced, txNotSyncedToServer, txSyncedToServer;
	Button btnNext, btnDownload, btnUpload;
	Intent intent;
    MRDatabaseHandler databasehandler;
    boolean dbstatus;
	String sqlit_rrno;
	ProgressDialog dialogDownload , dialogUpload;
	String totalcount =null,sqliteSubmitCount=null, sqliteNotReadCount=null, sqliteSyncedtoServercount=null, upload_billed=null;
	
	InputStream str=null,str2=null;
	Sendrequest send= null ,send1=null;
	JSONArray json;
	String listno;
	boolean serverStatus = false;
	String mrcode = null;
	int updatedcount = 0;
	InputStream data_upload =null;
	public static String sealdate;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.mrstatusactivity);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.window_title);
		title = (TextView) findViewById(R.id.title);
		title.setText("Home");

		version =(TextView) findViewById(R.id.title_right_textll);
		version.setText("Logout");
	
		btnNext = (Button) findViewById(R.id.buttonNext);
		btnDownload = (Button) findViewById(R.id.button1);
		btnUpload = (Button) findViewById(R.id.btnUpload);
		
		
		txlistno = (TextView) findViewById(R.id.txtsdo_code);
		txmrname = (TextView) findViewById(R.id.txtUserName);
		txTotalConnections = (TextView) findViewById(R.id.totalconnectionvalue);
		txNotReplaced = (TextView) findViewById(R.id.nocunbilledvalue);
        txReplaced = (TextView) findViewById(R.id.nocbilledvalue);
        txNotSyncedToServer = (TextView) findViewById(R.id.nocsyncedvalue);
        txSyncedToServer = (TextView) findViewById(R.id.txtUnsynched);

        
        
		
		if ((LoginActivity.mrcode != null)	) {
			txlistno.setText( " "+LoginActivity.mrcode );
			listno = LoginActivity.mrcode;
			
		} else {

			MRUtilMaster.show(MRStatusActivity.this, "Error", 0, "Sorry..! \nSession expired please Login again", "OK", getDialogListener(MRStatusActivity.this, LoginActivity.class)).show();

		}
		
		
		
		
		
		
		
		
	}


	@Override
	protected void onStart() {
		
		try {
			
			// TODO Auto-generated method stub
			super.onStart();
			
			
			setDashboard();

			btnNext.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					
					
					
					
					if(mrcode.equals("") || mrcode.equals(" ") || mrcode == null){
						
						Toast.makeText(getApplicationContext(), "Please download data", Toast.LENGTH_SHORT ).show();
					}
					
					else if(!(mrcode.trim().equals(listno.trim()))){
					 
					
						Toast.makeText(getApplicationContext(), "Please check the list number or download new data", Toast.LENGTH_LONG ).show();
					 
				 }else{
					 
					 move_inten(getApplicationContext(), MRListActivity.class, sqlit_rrno);
					 
				 }
					
					
					
				
					
					
				/*intent = new Intent(getApplicationContext(), MRListActivity.class);
				startActivity(intent);*/
					 
				}
			});
		
		
		
		btnDownload.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				

				// TODO Auto-generated method stub

				btnDownload.setEnabled(false);

				dialogDownload =  new ProgressDialog(MRStatusActivity.this, R.style.Theme_MyDialog);
				dialogDownload.setCancelable(false);
				dialogDownload.setMessage("Downloading Please wait...");
				dialogDownload.show();




				try {
					databasehandler = new MRDatabaseHandler(getApplicationContext());
					databasehandler.openToRead();
					// fetching billed count from sqlite DB
					Cursor cursor_billed = databasehandler.GetSubmitButNotSyncedCount();
					//		startManagingCursor(cursor_billed);
					if (cursor_billed != null&& cursor_billed.moveToFirst()) 
					{
						upload_billed = cursor_billed.getString(0);
					}
					//		stopManagingCursor(cursor_billed);
					cursor_billed.close();
				} catch (Exception e) 
				{
					e.printStackTrace();
				}
				databasehandler.close();

				if (!(upload_billed.equals("0")))
				{

					btnDownload.setEnabled(true);
					dialogDownload.dismiss();
					new AlertDialog.Builder(MRStatusActivity.this)
					.setTitle("Info")
					.setMessage("You have meter installed connections please upload before download")
					.setPositiveButton("OK", null).show();


				} else {

					new performBackgroundTaskgetServerDateToDownloadBilledData().execute();
				}

			
				
				
				
				
			}
		});
		
		
		
		
		
		
		
		btnUpload.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				

				// TODO Auto-generated method stub


				btnUpload.setEnabled(false);

				dialogUpload = new ProgressDialog(MRStatusActivity.this, R.style.Theme_MyDialog);
				dialogUpload.setCancelable(false);
				dialogUpload.setMessage(" Uploading Please wait...");
				dialogUpload.show();

				try {
					databasehandler = new MRDatabaseHandler(getApplicationContext());
					databasehandler.openToRead();
					// fetching billed count from sqlite DB
					Cursor cursor_billed = databasehandler.GetSubmitButNotSyncedCount();
					//		startManagingCursor(cursor_billed);
					if (cursor_billed != null&& cursor_billed.moveToFirst()) 
					{
						upload_billed = cursor_billed.getString(0);
					}
					//		stopManagingCursor(cursor_billed);
					cursor_billed.close();
				} catch (Exception e) 
				{
					e.printStackTrace();
				}
				databasehandler.close();

				if (upload_billed.equals("0"))
				{

					btnUpload.setEnabled(true);
					dialogUpload.dismiss();
					new AlertDialog.Builder(MRStatusActivity.this)
					.setTitle("Info")
					.setMessage("You don't have Installed Connections to upload")
					.setPositiveButton("OK", null).show();
				} else {

					new performBackgroundTaskgetServerDateToUploadBilledData().execute();
				}
			
				
				
				
				
			}
		});
		
		
		
		
		
		
		
		
		version.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				version.setEnabled(false);
				MRUtilMaster.show(MRStatusActivity.this, "Info", 0, "Do you want to log out", "Log Out", getDialogListener(MRStatusActivity.this, LoginActivity.class),"Cancel", null).show();

				version.setEnabled(true);
			}



		});
		
		
		
		
		
		
		
		
		
			} catch (Exception e) {

				// TODO: handle exception
				
				
				e.printStackTrace();
				
				
				if ((LoginActivity.mrcode != null)	) {

				} else {

					MRUtilMaster.show(MRStatusActivity.this, "Error", 0, "Sorry..! \nSession expired please Login again", "OK", getDialogListener(MRStatusActivity.this, LoginActivity.class)).show();

				}
			}
		
		
	}



	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		MRUtilMaster.show(MRStatusActivity.this, "Info", 0, "Do you want to log out", "Log Out", getDialogListener(MRStatusActivity.this, LoginActivity.class),"Cancel", null).show();


	}










	/* ********************* USER DEFINED METHODS *******************************/	



	public android.content.DialogInterface.OnClickListener getDialogListener(
			final Context context, final Class class1) {

		return new android.content.DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				move_inten(context, class1);
			}
		};

	}

	public void move_inten( Context context , Class class1 ) {
		Intent intent = new Intent(context,class1);
		startActivity(intent);
	}
	public void move_inten( Context context , Class class1 , String data ) {

		Intent intent = new Intent(context,class1);
		intent.putExtra("RRno/bu", data);
		startActivity(intent);
	}
	


	public void setDashboard(){

		databasehandler = new MRDatabaseHandler(getApplicationContext());
		try {
			databasehandler.openToRead();
			dbstatus = databasehandler.checkDataBase();
			databasehandler.close();
			if(dbstatus)
			{
				databasehandler = new MRDatabaseHandler(getApplicationContext());
				try {
					
					
					
					//NEXT RRNO
					databasehandler.openToRead();
					Cursor cursor = databasehandler.checkuser_RRNO();
					/*startManagingCursor(cursor);*/
					if (cursor != null && cursor.moveToFirst()) {
						sqlit_rrno = cursor.getString(0);
					}else{
						sqlit_rrno = "-";
					}
					cursor.close();
					databasehandler.close();


					//TOTAL CONNECTIONS
					databasehandler.openToRead();
					 cursor = databasehandler.Gettotalcount();
					/*startManagingCursor(cursor);*/
					if (cursor != null && cursor.moveToFirst()) {
						
						txTotalConnections.setText(cursor.getString(0));
						
					}else{
						txTotalConnections.setText("-");
					}
					cursor.close();
					databasehandler.close();


					 //BILLED COUNT
					databasehandler.openToRead();
					cursor = databasehandler.GetReplacedcount();
					/*startManagingCursor(cursor);*/
					if (cursor != null && cursor.moveToFirst()) {
					
						txReplaced.setText(cursor.getString(0));
						
					}else{
						
						txReplaced.setText("-");
					}
					cursor.close();
					databasehandler.close();


					//UNBILLED COUNT
					databasehandler.openToRead();
					cursor = databasehandler.GetNotReplacedCount();
					/*startManagingCursor(cursor);*/
					if (cursor != null
							&& cursor.moveToFirst()) {
						
						
						txNotReplaced.setText(cursor.getString(0));
						
					}else {
						
						txNotReplaced.setText("-");
						
					}
					cursor.close();
					databasehandler.close();


					//SYCN TO SERVER
					databasehandler.openToRead();
					cursor = databasehandler.GetSyncedToserverCount();
				/*	startManagingCursor(cursor);*/
					if (cursor != null && cursor.moveToFirst()) {
						
						txSyncedToServer.setText(cursor.getString(0));
						
					}else {

						txSyncedToServer.setText("-");
						
					}
					cursor.close();
					databasehandler.close();


					//not synced to server

					databasehandler.openToRead();
					cursor = databasehandler.GetNotSyncedToServerCount();
				/*	startManagingCursor(cursor);*/
					if (cursor != null && cursor.moveToFirst()) {
						
						txNotSyncedToServer.setText(cursor.getString(0));
					}
					else{
						
						txNotSyncedToServer.setText("-");
						
					}
					
					
					cursor.close();
					databasehandler.close();


					// Mrname
					
					databasehandler.openToRead();
					cursor = databasehandler.checkmReadMR();
				/*	startManagingCursor(cursor);*/
					if (cursor != null && cursor.moveToFirst()) {
						mrcode = " "+cursor.getString(cursor	.getColumnIndex("LISTNO"));
						txmrname.setText(mrcode);
						/*mrCodeVal = mrcode ;*/
					} else {
						
						mrcode ="";
						txmrname.setText(" - ");
						/*mrCodeVal ="-";*/

					}
					cursor.close();
					databasehandler.close();



				} catch (Exception e) {
					e.printStackTrace();
				}

				//databasehandler.close();

			}

		}catch(Exception exception){
			exception.printStackTrace();
		}

	}


	
	
	
	
	
	public class performBackgroundTaskgetServerDateToDownloadBilledData extends AsyncTask<Void, Void, Void> {
		boolean mobileDateMatch = false;
		String serverDate = null ;
		protected void onPreExecute() {
			super.onPreExecute();
		}

		protected Void doInBackground(Void... params) {

			send  =  new Sendrequest();
			str = send.sendrequest("getdatenew");
			System.out.println("str ******* : "+str);
			return null;
		}
		protected void onPostExecute(Void unused) {
			if(str == null)
			{


				btnDownload.setEnabled(true);
				dialogDownload.dismiss();
				new AlertDialog.Builder(MRStatusActivity.this)
				.setTitle("Info")
				.setMessage(
						"Sorry, server down.")
						.setPositiveButton("OK", null).show();

			}
			else{
				try {
					serverDate =send.read(str);
				} catch (Exception e) {

					btnDownload.setEnabled(true);
					dialogDownload.dismiss();
					new AlertDialog.Builder(MRStatusActivity.this)
					.setTitle("Error..!")
					.setMessage(
							"Some error occurred while connecting to server.")
							.setPositiveButton("OK", null).show();
					e.printStackTrace();

				}





				System.out.println("serverDate  ********"+serverDate);

				Date dateServer = null ;
				Date dateMobile = null ;



				if(serverDate == null){



					btnDownload.setEnabled(true);
					dialogDownload.dismiss();
					new AlertDialog.Builder(MRStatusActivity.this)
					.setTitle("Error..!")
					.setMessage("Some error occurred while connecting to server")
					.setPositiveButton("OK", null)
					.show();
				}


				else{
					if(!serverDate.contains("<html>"))
					{
						try {
							SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
							dateServer = format.parse(serverDate);
							Calendar calendar = Calendar.getInstance();
							dateMobile =format.parse(format.format( calendar.getTime()));




						}catch (Exception e) {
							
							e.printStackTrace();
							
							
							btnDownload.setEnabled(true);
							dialogDownload.dismiss();
							new AlertDialog.Builder(MRStatusActivity.this)
							.setTitle("Error..!")
							.setMessage(
									"Some error occurred while connecting to server.")
									.setPositiveButton("OK", null).show();

						}

						/*
						 * 
						 * 
						 * 
						 * 
						These changes are made in order to testing purpose and will not affect the actual code
						 */


						SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
						String testdate12 = "29/06/2014";
						String testdate13 = "30/06/2014";

						String date1 = "31/10/2014";
						String date2 = "07/10/2014";
						String date3 = "08/10/2014";
						String date4 = "09/10/2014";
						String date5 = "10/10/2014";


						Date testdate1 = null;
						Date testdate2 = null;
						Date serverTestdate1 =null;
						Date serverTestdate2 =null;
						Date serverTestdate3 =null;
						Date serverTestdate4 =null;
						Date serverTestdate5 =null;

						try {
							testdate1 = new java.sql.Date(format.parse(testdate12).getTime());
							testdate2 = new java.sql.Date(format.parse(testdate13).getTime());
							serverTestdate1 = new java.sql.Date(format.parse(date1).getTime());
							serverTestdate2 = new java.sql.Date(format.parse(date2).getTime());
							serverTestdate3 = new java.sql.Date(format.parse(date3).getTime());
							serverTestdate4 = new java.sql.Date(format.parse(date4).getTime());
							serverTestdate5 = new java.sql.Date(format.parse(date5).getTime());


						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						if( dateServer.compareTo(serverTestdate1) == 0 || dateServer.compareTo(serverTestdate2) == 0 
								|| dateServer.compareTo(serverTestdate3) == 0 || dateServer.compareTo(serverTestdate3) == 0
								|| dateServer.compareTo(serverTestdate4) == 0 || dateServer.compareTo(serverTestdate5) == 0){



							System.out.println("--------testing purpuse-------------");
							System.out.println("--------testing purpuse-------------");
							System.out.println("--------testing purpuse-------------");


							System.out.println(dateServer);
							System.out.println("serverTestdate1" + serverTestdate1);
							System.out.println("serverTestdate2" + serverTestdate2);
							System.out.println("serverTestdate3" + serverTestdate3);

							System.out.println("--------testing purpuse-------------");
							System.out.println("--------testing purpuse-------------");


							int n = 1;
							int k = dateMobile.getMonth() + 1;
							int year = dateMobile.getYear() + 1900;

							System.out.println("n :" + n);
							System.out.println("k :" + k);

							//if  ( dateMobile.compareTo(testdate1) == 0 )
							if  ( n == k && year == 2015 ){

								System.out.println();
								System.out.println();

								/*System.out.println("dateMobile" + dateMobile);
								System.out.println("testdate1" + testdate1);
								 */


								System.out.println();


								System.out.println("--------testing purpuse-------------");

								btnDownload.setEnabled(true);
								dialogDownload.dismiss();
								new performBackgroundTaskgetApk_Tariff_DataValidation().execute();




							}

							else{
								btnDownload.setEnabled(true);
								dialogDownload.dismiss();

								new AlertDialog.Builder(MRStatusActivity.this)
								.setTitle("Info")
								.setMessage(
										"Mobile date has been changed. please set the date on device and then continue ")
										.setPositiveButton("Set Date",	
												new DialogInterface.OnClickListener() 
										{
											public void onClick(DialogInterface arg0,int arg1) 
											{
												startActivity(new Intent(android.provider.Settings.ACTION_DATE_SETTINGS));

											}
										})
										.show();
							}
						}




						else if
						( dateServer.compareTo(dateMobile) == 0 ){

							// DEMO CHANGES MUST BE REPLACED START END 

							System.out.println("**********************Update happend***************************************");
							System.out.println("SUCCESSSSSSSSSSSSSSSSSSSSSSS");
							
							btnDownload.setEnabled(true);
							dialogDownload.dismiss();
							new performBackgroundTaskgetApk_Tariff_DataValidation().execute();



						}else {
							btnDownload.setEnabled(true);
							dialogDownload.dismiss();

							new AlertDialog.Builder(MRStatusActivity.this)
							.setTitle("Info")
							.setMessage(
									"Mobile date has been changed. please set the date on device and then continue ")
									.setPositiveButton("Set Date",	
											new DialogInterface.OnClickListener() 
									{
										public void onClick(DialogInterface arg0,int arg1) 
										{
											startActivity(new Intent(android.provider.Settings.ACTION_DATE_SETTINGS));

										}
									})
									.show();

						}
					}
					else
					{
						btnDownload.setEnabled(true);
						dialogDownload.dismiss();
						new AlertDialog.Builder(MRStatusActivity.this)
						.setTitle("Error..!")
						.setMessage(
								"Sorry, server down / some error occurred while connecting server.")
								.setPositiveButton("OK", null).show();
					}
				}
			}
		}
	}

	
	
	
	
	
	public class performBackgroundTaskgetApk_Tariff_DataValidation extends AsyncTask<Void, Void, Void>
	{

		private ProgressDialog dialog = new ProgressDialog(MRStatusActivity.this, R.style.Theme_MyDialog);

		boolean mobileDateMatch = false;
		String serverDate = null ;
		protected void onPreExecute() {

			System.out.println("***********Started :**************** ");

			super.onPreExecute();
			dialog.setCancelable(false);

			String m =  ""+ Html.fromHtml("<font color='#FFFFFF'>Please wait...</font>") ;
			dialog.setMessage(m);
			dialog.show();
		}


		protected Void doInBackground(Void... params) {

			send  =  new Sendrequest();
			//ged_validation/validateapkversion_tariffdate_data/2.45/1A/1061
			String versionName = "0";
			try {
				versionName = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
			} catch (NameNotFoundException e) 
			{
				// TODO Auto-generated catch block
				versionName = "0" ;
				e.printStackTrace();

			}

			TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
			String imeino = tm.getDeviceId();
			System.out.println("*********************imeino : "+imeino);
			//imeino = "356128050778124";
			//versionName = "2.28";



			String demoVersion = versionName.replace(".", "z");

			System.out.println("demoVersion :"+demoVersion);

			str = send.sendrequest("mobiledownloaddatavalidation/"+listno);
			System.out.println("str ******* : "+str);
			//Dialog.dismiss();
			return null;
		}
		protected void onPostExecute(Void unused) {
			if(str == null)
			{
				dialog.dismiss();
				new AlertDialog.Builder(MRStatusActivity.this)
				.setTitle("Info")
				.setMessage(
						"Sorry, server down.")
						.setPositiveButton("OK", null).show();

			}
			else{
				dialog.dismiss();
				serverDate =send.read(str);

				System.out.println("serverDate  ********"+serverDate);

				Date dateServer = null ;
				Date dateMobile = null ;
				if(!serverDate.contains("<html>"))
				{
					try {

						System.out.println(" data : *********12 : "+ serverDate);	


						String[] dataFrmSever =  serverDate.split("#");

						if(dataFrmSever[0].equalsIgnoreCase( "LISTISVALID")){

							if(dataFrmSever[1].equalsIgnoreCase( "DOWNLOADAVAILABLE"))
							{

								dialog.dismiss();

								int unbilled1 = 1;

								if(unbilled1> (float)0){
									new AlertDialog.Builder(MRStatusActivity.this)
									.setTitle("Info..! Download details")
									.setMessage("Valid Device, press OK to Download")
									.setPositiveButton("OK", new DialogInterface.OnClickListener() {

										@Override
										public void onClick(DialogInterface dialog, int which) {



											dialog.dismiss();

											new performBackgroundTaskdownload_consumerDate().execute();
											
											

										}
									})
									.show();



								}

							}
							else{
								dialog.dismiss();
								new AlertDialog.Builder(MRStatusActivity.this)
								.setTitle("Info..!")
								.setMessage("Sorry..! No data to download")
								.setPositiveButton("OK", null)
								.show();
							}




						}


						else{
							dialog.dismiss();
							new AlertDialog.Builder(MRStatusActivity.this)
							.setTitle("Info..!")
							.setMessage("Sorry the list number is not valid")
							.setPositiveButton("OK", null)
							.show();

						}

					}catch(Exception e){
						e.printStackTrace();
						dialog.dismiss();
						new AlertDialog.Builder(MRStatusActivity.this)
						.setTitle("Error..!")
						.setMessage("Sorry,Some error occurred while connecting to server.")
						.setPositiveButton("OK", null)
						.show();
					}
				}

				else
				{
					dialog.dismiss();
					new AlertDialog.Builder(MRStatusActivity.this)
					.setTitle("Error..!")
					.setMessage("Sorry, server down / some error occurred while connecting server.")
					.setPositiveButton("OK", null)
					.show();
				}
			}
		}
	}
	
	

	public class performBackgroundTaskdownload_consumerDate extends AsyncTask<String, String, String>
	{
		int current = 0;
		int count = 0;
		public  ProgressDialog mProgressDialog;
		JSONArray consumer_json_arr = null;
		int noOfRecords = 0 ;
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			mProgressDialog = new ProgressDialog(MRStatusActivity.this, R.style.Theme_MyDialog);
			mProgressDialog.setMessage("Downloading file..");
			mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			mProgressDialog.setCancelable(false);
			mProgressDialog.show();
			databasehandler = new MRDatabaseHandler(getApplicationContext());


		}

		@Override
		protected String doInBackground(String... arg0) {
			try {
				send = new Sendrequest();
				TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
				String imeino = tm.getDeviceId();
				//imeino = "356128050778124";
				System.out.println(" imeino  :"+imeino);
				str = send.sendrequest("getconsumerdataformobile/"+listno );



			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("Sendrequest.length  " +Sendrequest.length);
			if (str == null   || str.equals(null) || str.equals("null") ) {
				mProgressDialog.dismiss();
				serverStatus = true ;

			} else {
				String data = send.read(str);
				serverStatus = false ;
				if (	data == null 
						|| 	data == "null"
						|| 	data.equals("null") 
						||  data.equals(null)	)
				{
					mProgressDialog.dismiss();
					mProgressDialog.dismiss();
					serverStatus = true ;
				}
				else {
					JSONArray json = null;
					try {
						System.out.println(data);
						json = new JSONArray(data);
					} catch (JSONException e) {
						e.printStackTrace();
					}

					if(json == null || json.equals("null"))
					{
						mProgressDialog.dismiss();
						serverStatus =  true ;
					}
					else
					{

						serverStatus =  false ;

						databasehandler = new MRDatabaseHandler(getApplicationContext());
						databasehandler.openToRead();
						databasehandler.deleteAll();
						databasehandler.close();


						try{


							String servertomobiledate = getToday();
							sealdate=servertomobiledate;
							TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
							String deviceid = telephonyManager.getDeviceId();
							
							String  devicefirmwareversion="";
							try {
								devicefirmwareversion = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
							} catch (NameNotFoundException e) 
							{
								devicefirmwareversion = "0" ;
								e.printStackTrace();

							}

							for (int i = 0; i < json.length(); i++) 
							{
								JSONObject obje = json.getJSONObject(i);
								databasehandler = new MRDatabaseHandler(getApplicationContext());

								try 
								{
									databasehandler.openToWrite();
									
									
									String id  =  obje.getString("id");
									String rrno	 = obje.getString("rrno");
									String listno	= obje.getString("listno");
									String tariff	= obje.getString("tariffdcb");
									String socode	= obje.getString("socode");
									String status	= "NOTUPDATED";
									String consumer_name	=  obje.getString("consumername");
									String address	=obje.getString("consumeraddress");
									String fdrcode	= obje.getString("federcode");
									String tccode	= obje.getString("tccode");
									String poleno	= obje.getString("poleno");
									String meterstatus = obje.getString("meterstatus");
									
									

									String meterflag = "0";
									String metereplacestatus = "0";
									//String serverdate = getToday();
									
									String extra1	=obje.getString("presentreading");
									String extra2	=obje.getString("sactionkw");
									String extra3	=obje.getString("sactionhp");
									String extra4	="";
									String extra5	="";
									String extra6   ="";
									String extra7	=sealdate;
									String extra8	="";
									String extra9   ="";
									String extra10   ="";
									
									/*String extra7	="27-1-16";
									String extra8	="1";
									String extra9   ="plastic";
									String extra10   ="broken";
*/
									
									
									
									
									
									
									
									
									
									
									




									








									databasehandler.insert(
											
											id,
											rrno	,
											listno	,
											consumer_name	,
											address	,
											meterstatus,
											poleno	,
											fdrcode	,
											tccode	,
											socode	,
											tariff	,
											status	,
											meterflag,
											metereplacestatus,
											deviceid,
											servertomobiledate	,
											devicefirmwareversion	,
											extra1	,
											extra2	,
											extra3	,
											extra4	,
											extra5	,
											extra6	,
											extra7	,
											extra8	,
											extra9	,
											extra10



											);






									databasehandler.close();
									count++;
									current++;

								} catch (Exception e) {
									// TODO: handle exception
									e.printStackTrace();
								}
							}


						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						noOfRecords = count ;





					}

				}
			}
			return null;

		}
		protected void onProgressUpdate(String... values) {
			mProgressDialog.setProgress(Integer.parseInt(values[0]));
			mProgressDialog.setSecondaryProgress(Integer.parseInt(values[0]));
			System.out.println("total"+values[0]);
			DecimalFormat df = new DecimalFormat("###.##");
			System.out.println("Mega bytes mbs"+df.format(((Float.valueOf(values[0])/1048576)*100)/100));
			mProgressDialog.setMessage("Downloading file.. "+df.format((Float.valueOf(values[0])))+" of "+df.format(Float.valueOf(consumer_json_arr.length())));

		}
		@Override
		protected void onPostExecute(String unused) {
			mProgressDialog.dismiss();
			setDashboard();
			if (!serverStatus) {

				MRUtilMaster.show(MRStatusActivity.this, "Info", 0,
						"Successfully downloaded " + noOfRecords
						+ " Consumers data...", "Ok", null).show();

			}else
			{
				MRUtilMaster
				.show(MRStatusActivity.this,
						"Error",
						0,
						"Sorry,connection cannot made at this time. Server is down. Please try again later",
						"Ok", null).show();
			}

		} 


	}

	public String getToday(){


		Date d = new Date();
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		String today =   format.format(d);


		return today;

	}

	
	
	
	public class performBackgroundTaskgetServerDateToUploadBilledData extends AsyncTask<Void, Void, Void> {
		/*private ProgressDialog dialog = new ProgressDialog(ModeActivity.this, R.style.Theme_MyDialog);*/
		boolean mobileDateMatch = false;
		String serverDate = null ;
		protected void onPreExecute() {
			super.onPreExecute();
			/*dialog.setMessage("Please wait...");
			dialog.show();
			dialog.setCancelable(false);*/
		}

		protected Void doInBackground(Void... params) {

			send  =  new Sendrequest();
			str = send.sendrequest("getdatenew");
			System.out.println("str ******* : "+str);
			//Dialog.dismiss();
			return null;
		}
		protected void onPostExecute(Void unused) {
			if(str == null)
			{


				btnUpload.setEnabled(true);
				dialogUpload.dismiss();
				new AlertDialog.Builder(MRStatusActivity.this)
				.setTitle("Info")
				.setMessage(
						"Sorry, server down.")
						.setPositiveButton("OK", null).show();

			}
			else{
				try {
					serverDate =send.read(str);
				} catch (Exception e) {

					btnUpload.setEnabled(true);
					dialogUpload.dismiss();
					new AlertDialog.Builder(MRStatusActivity.this)
					.setTitle("Error..!")
					.setMessage(
							"Some error occurred while connecting to server.")
							.setPositiveButton("OK", null).show();
					e.printStackTrace();

				}





				System.out.println("serverDate  ********"+serverDate);

				Date dateServer = null ;
				Date dateMobile = null ;



				if(serverDate == null){



					btnUpload.setEnabled(true);
					dialogUpload.dismiss();
					new AlertDialog.Builder(MRStatusActivity.this)
					.setTitle("Error..!")
					.setMessage("Some error occurred while connecting to server")
					.setPositiveButton("OK", null)
					.show();
				}


				else{
					if(!serverDate.contains("<html>"))
					{
						try {
							SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
							dateServer = format.parse(serverDate);
							Calendar calendar = Calendar.getInstance();
							dateMobile =format.parse(format.format( calendar.getTime()));




						}catch (Exception e) {
							btnUpload.setEnabled(true);
							
							e.printStackTrace();
							
							dialogUpload.dismiss();
							new AlertDialog.Builder(MRStatusActivity.this)
							.setTitle("Error..!")
							.setMessage(
									"Some error occurred while connecting to server.")
									.setPositiveButton("OK", null).show();

						}

						/*
						 * 
						 * 
						 * 
						 * 
						These changes are made in order to testing purpose and will not affect the actual code
						 */


						SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
						String testdate12 = "29/06/2014";
						String testdate13 = "30/06/2014";

						String date1 = "06/10/2014";
						String date2 = "07/10/2014";
						String date3 = "08/10/2014";
						String date4 = "09/10/2014";
						String date5 = "10/10/2014";


						Date testdate1 = null;
						Date testdate2 = null;
						Date serverTestdate1 =null;
						Date serverTestdate2 =null;
						Date serverTestdate3 =null;
						Date serverTestdate4 =null;
						Date serverTestdate5 =null;

						try {
							testdate1 = new java.sql.Date(format.parse(testdate12).getTime());
							testdate2 = new java.sql.Date(format.parse(testdate13).getTime());
							serverTestdate1 = new java.sql.Date(format.parse(date1).getTime());
							serverTestdate2 = new java.sql.Date(format.parse(date2).getTime());
							serverTestdate3 = new java.sql.Date(format.parse(date3).getTime());
							serverTestdate4 = new java.sql.Date(format.parse(date4).getTime());
							serverTestdate5 = new java.sql.Date(format.parse(date5).getTime());


						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						if( dateServer.compareTo(serverTestdate1) == 0 || dateServer.compareTo(serverTestdate2) == 0 
								|| dateServer.compareTo(serverTestdate3) == 0 || dateServer.compareTo(serverTestdate3) == 0
								|| dateServer.compareTo(serverTestdate4) == 0 || dateServer.compareTo(serverTestdate5) == 0){



							System.out.println("--------testing purpuse-------------");
							System.out.println("--------testing purpuse-------------");
							System.out.println("--------testing purpuse-------------");


							System.out.println(dateServer);
							System.out.println("serverTestdate1" + serverTestdate1);
							System.out.println("serverTestdate2" + serverTestdate2);
							System.out.println("serverTestdate3" + serverTestdate3);

							System.out.println("--------testing purpuse-------------");
							System.out.println("--------testing purpuse-------------");


							int n = 6;
							int k = dateMobile.getMonth() + 1;
							int year = dateMobile.getYear() + 1900;

							System.out.println("n :" + n);
							System.out.println("k :" + k);

							//if  ( dateMobile.compareTo(testdate1) == 0 )
							if  ( n == k && year == 2014 ){

								System.out.println();
								System.out.println();

								/*System.out.println("dateMobile" + dateMobile);
								System.out.println("testdate1" + testdate1);
								 */


								System.out.println();


								System.out.println("--------testing purpuse-------------");



								/*dialogUpload.dismiss();*/
								System.out.println("**********************Update happend***************************************");
								new performBackgroundTaskupload().execute();


							}

							else{
								btnUpload.setEnabled(true);
								dialogUpload.dismiss();

								new AlertDialog.Builder(MRStatusActivity.this)
								.setTitle("Info")
								.setMessage(
										"Mobile date has been changed. please set the date on device and then continue ")
										.setPositiveButton("Set Date",	
												new DialogInterface.OnClickListener() 
										{
											public void onClick(DialogInterface arg0,int arg1) 
											{
												startActivity(new Intent(android.provider.Settings.ACTION_DATE_SETTINGS));

											}
										})
										.show();




							}












						}




						else if
						( dateServer.compareTo(dateMobile) == 0 ){




							// DEMO CHANGES MUST BE REPLACED START END 


							/*dialogUpload.dismiss();*/
							System.out.println("**********************Update happend***************************************");
							System.out.println("SUCCESSSSSSSSSSSSSSSSSSSSSSS");

							new performBackgroundTaskupload().execute();


						}else {
							btnUpload.setEnabled(true);
							dialogUpload.dismiss();

							new AlertDialog.Builder(MRStatusActivity.this)
							.setTitle("Info")
							.setMessage(
									"Mobile date has been changed. please set the date on device and then continue ")
									.setPositiveButton("Set Date",	
											new DialogInterface.OnClickListener() 
									{
										public void onClick(DialogInterface arg0,int arg1) 
										{
											startActivity(new Intent(android.provider.Settings.ACTION_DATE_SETTINGS));

										}
									})
									.show();

						}
					}
					else
					{
						btnUpload.setEnabled(true);
						dialogUpload.dismiss();
						new AlertDialog.Builder(MRStatusActivity.this)
						.setTitle("Error..!")
						.setMessage(
								"Sorry, server down / some error occurred while connecting server.")
								.setPositiveButton("OK", null).show();

					}


				}








			}

		}


	}

	
	
	
	
	
	
	
	public class performBackgroundTaskupload extends AsyncTask<Void, Void, Void> 
	{
		int count_update = 0 ;
		/*private ProgressDialog dialog = new ProgressDialog(	ModeActivity.this, R.style.Theme_MyDialog);*/
		boolean isdataCount = false ;
		protected void onPreExecute() 
		{
			/*dialog.setCancelable(false);
			dialog.setMessage(" Uploading Please wait...");
			dialog.show();*/
		}
		protected Void doInBackground(Void... params) 
		{
			databasehandler = new MRDatabaseHandler(getApplicationContext());
			try 
			{
				/*
				 * getting rrno,billed and unbilled count
				 */
				databasehandler.openToRead();
				/*fetching RRNO from sqlite DB
				 */
				//	ArrayList<ConsumerDB> list = new ArrayList<ConsumerDB>();
				final Cursor cursor = databasehandler.GetSubmittedconsumers_fromlocal();
				//	startManagingCursor(cursor);
				System.out.println(">>>>>>>>>>cursor :  "+cursor +"  & >>>>>>>>>>cursor.getCount() :  "+cursor.getCount() );
				json = new JSONArray();
				updatedcount= 0 ;
				if(cursor.getCount() == 0 )
				{
					isdataCount = true;
					dialogUpload.dismiss();
					//setUploadedmsg("No more consumer");
				}
				else
				{
					while (cursor != null && cursor.moveToPosition(updatedcount)) 
					{
						updatedcount++;

						JSONObject jsonOb = new JSONObject();

						jsonOb.put("ID" , cursor.getString(cursor.getColumnIndex("ID")));
						jsonOb.put("RRNO" , cursor.getString(cursor.getColumnIndex("RRNO")));
						jsonOb.put("LISTNO" , cursor.getString(cursor.getColumnIndex("LISTNO")));
						jsonOb.put("LISTDATE" , cursor.getString(cursor.getColumnIndex("LISTDATE")));
						jsonOb.put("TARIFF" , cursor.getString(cursor.getColumnIndex("TARIFF")));
						jsonOb.put("SOCODE" , cursor.getString(cursor.getColumnIndex("SOCODE")));
						jsonOb.put("DRAMT" , cursor.getString(cursor.getColumnIndex("DRAMT")));
						jsonOb.put("RDNGDATE" , cursor.getString(cursor.getColumnIndex("RDNGDATE")));
						jsonOb.put("MTRCODE" , cursor.getString(cursor.getColumnIndex("MTRCODE")));
						jsonOb.put("ARREARS" , cursor.getString(cursor.getColumnIndex("ARREARS")));
						jsonOb.put("USERNAME" , cursor.getString(cursor.getColumnIndex("USERNAME")));
						jsonOb.put("DATESTAMP" , cursor.getString(cursor.getColumnIndex("DATESTAMP")));
						jsonOb.put("AGEING" , cursor.getString(cursor.getColumnIndex("AGEING")));
						jsonOb.put("STATUS" , cursor.getString(cursor.getColumnIndex("STATUS")));
						jsonOb.put("COLLECTIONID" , cursor.getString(cursor.getColumnIndex("COLLECTIONID")));
						jsonOb.put("OLDRRNO" , cursor.getString(cursor.getColumnIndex("OLDRRNO")));
						jsonOb.put("CONSUMER_NAME" , cursor.getString(cursor.getColumnIndex("CONSUMER_NAME")));
						jsonOb.put("ADDRESS" , cursor.getString(cursor.getColumnIndex("ADDRESS")));
						jsonOb.put("FDRCODE" , cursor.getString(cursor.getColumnIndex("FDRCODE")));
						jsonOb.put("TCCODE" , cursor.getString(cursor.getColumnIndex("TCCODE")));
						jsonOb.put("POLENO" , cursor.getString(cursor.getColumnIndex("POLENO")));
						jsonOb.put("BALANCE" , cursor.getString(cursor.getColumnIndex("BALANCE")));
						jsonOb.put("AVERAGE" , cursor.getString(cursor.getColumnIndex("AVERAGE")));
						jsonOb.put("OMDATEOFRELEASE" , cursor.getString(cursor.getColumnIndex("OMDATEOFRELEASE")));
						jsonOb.put("OMSERIALNO" , cursor.getString(cursor.getColumnIndex("OMSERIALNO")));
						jsonOb.put("OMMAKE" , cursor.getString(cursor.getColumnIndex("OMMAKE")));
						jsonOb.put("OMCAPACITY" , cursor.getString(cursor.getColumnIndex("OMCAPACITY")));
						jsonOb.put("OMCOVER" , cursor.getString(cursor.getColumnIndex("OMCOVER")));
						jsonOb.put("OMPOSITION" , cursor.getString(cursor.getColumnIndex("OMPOSITION")));
						jsonOb.put("OMTYPE" , cursor.getString(cursor.getColumnIndex("OMTYPE")));
						jsonOb.put("OMFR" , cursor.getString(cursor.getColumnIndex("OMFR")));
						jsonOb.put("OMIMAGE" , cursor.getString(cursor.getColumnIndex("OMIMAGE")));
						jsonOb.put("OMLONGITUDE" , cursor.getString(cursor.getColumnIndex("OMLONGITUDE")));
						jsonOb.put("OMLATTITUDE" , cursor.getString(cursor.getColumnIndex("OMLATTITUDE")));
						jsonOb.put("NMDATEOFINSTALL" , cursor.getString(cursor.getColumnIndex("NMDATEOFINSTALL")));
						jsonOb.put("NMSERIALNO" , cursor.getString(cursor.getColumnIndex("NMSERIALNO")));
						jsonOb.put("NMMAKE" , cursor.getString(cursor.getColumnIndex("NMMAKE")));
						jsonOb.put("NMCAPACITY" , cursor.getString(cursor.getColumnIndex("NMCAPACITY")));
						jsonOb.put("NMCOVER" , cursor.getString(cursor.getColumnIndex("NMCOVER")));
						jsonOb.put("NMPOSITION" , cursor.getString(cursor.getColumnIndex("NMPOSITION")));
						jsonOb.put("NMTYPE" , cursor.getString(cursor.getColumnIndex("NMTYPE")));
						jsonOb.put("NMIR" , cursor.getString(cursor.getColumnIndex("NMIR")));
						jsonOb.put("NMIMAGE" , cursor.getString(cursor.getColumnIndex("NMIMAGE")));
						jsonOb.put("NMLONGITUDE" , cursor.getString(cursor.getColumnIndex("NMLONGITUDE")));
						jsonOb.put("NMLATTITUDE" , cursor.getString(cursor.getColumnIndex("NMLATTITUDE")));
						jsonOb.put("NMCONSTANT" , cursor.getString(cursor.getColumnIndex("NMCONSTANT")));
						jsonOb.put("METERREPLACEDATE" , cursor.getString(cursor.getColumnIndex("METERREPLACEDATE")));
						jsonOb.put("DEVICEID" , cursor.getString(cursor.getColumnIndex("DEVICEID")));
						jsonOb.put("METERREPLACESTATUS" , cursor.getString(cursor.getColumnIndex("METERREPLACESTATUS")));
						jsonOb.put("MR" , cursor.getString(cursor.getColumnIndex("MR")));
						jsonOb.put("SERVERTOMOBILEDATE" , cursor.getString(cursor.getColumnIndex("SERVERTOMOBILEDATE")));
						jsonOb.put("DEVICEFIRMWAREVERSION" , cursor.getString(cursor.getColumnIndex("DEVICEFIRMWAREVERSION")));
						jsonOb.put("METERFLAG" , cursor.getString(cursor.getColumnIndex("METERFLAG")));
						jsonOb.put("EXTRA1" , cursor.getString(cursor.getColumnIndex("EXTRA1")));
						jsonOb.put("EXTRA2" , cursor.getString(cursor.getColumnIndex("EXTRA2")));
						jsonOb.put("EXTRA3" , cursor.getString(cursor.getColumnIndex("EXTRA3")));
						jsonOb.put("EXTRA4" , cursor.getString(cursor.getColumnIndex("EXTRA4")));
						jsonOb.put("EXTRA5" , cursor.getString(cursor.getColumnIndex("EXTRA5")));
						jsonOb.put("EXTRA6" , cursor.getString(cursor.getColumnIndex("EXTRA6")));
						jsonOb.put("EXTRA7" , cursor.getString(cursor.getColumnIndex("EXTRA7")));
						jsonOb.put("EXTRA8" , cursor.getString(cursor.getColumnIndex("EXTRA8")));
						jsonOb.put("EXTRA9" , cursor.getString(cursor.getColumnIndex("EXTRA9")));
						jsonOb.put("EXTRA10" , cursor.getString(cursor.getColumnIndex("EXTRA10")));


						TelephonyManager tm1 = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
						String imeino1 = tm1.getDeviceId(); 
						jsonOb.put("SBM_NO",	imeino1);

						String versionName1 = null;

						try {
							versionName1 = getPackageManager()
									.getPackageInfo(
											getPackageName(), 0).versionName;

						} catch (NameNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}



						DateFormat dateFormatFCNO = new SimpleDateFormat("dd/MM/yyyy:HH:mm:ss");
						Calendar calFCNO = Calendar.getInstance();
						String bill_dateFCNO = dateFormatFCNO.format(calFCNO.getTime());
						jsonOb.put("SBMTOSERVERDATESTAMP" ,bill_dateFCNO );


						json.put(jsonOb);
						System.out.println("RRNO:***************************************"+cursor.getString(0));
						System.out.println("consumer details"+cursor.getString(0)+cursor.getString(1)+cursor.getString(2)+cursor.getString(3));

						System.out.println("json********************************************** value : "+json.toString().length() +" : "+json.toString()+"/extra9+"+cursor.getString(cursor.getColumnIndex("EXTRA9"))+"/extra10+"+cursor.getString(cursor.getColumnIndex("EXTRA10")));


					}

					cursor.close();
					System.out.println(" updatedcount  :" +updatedcount);

					send1 = new Sendrequest();
					data_upload = send1.uploadtoServerfromlocaldb("insertdatatometerchangetable", json);
					System.out.println("data_upload :"+ data_upload);

				}

			}
			catch (Exception e) {
				// TODO: handle exception


				dialogUpload.dismiss();
				btnUpload.setEnabled(true);
				e.printStackTrace();
			}

			return null;
		}

		protected void onPostExecute(Void unused) {

			if(isdataCount){
				btnUpload.setEnabled(true);
				dialogUpload.dismiss();
				setUploadedmsg("No more consumer to upload");
			}else{
				if (data_upload == null) 
				{

					btnUpload.setEnabled(true);
					dialogUpload.dismiss();
					new AlertDialog.Builder(MRStatusActivity.this)
					.setTitle("Info")
					.setMessage("Sorry,connection cannot made at this time. Server is down. Please try again later")
					.setPositiveButton("OK", null).show();
				} else 
				{
					String response = send1.read(data_upload);
					System.out.println("data from server......check..Data......"+ response);


					if(response==null){


						btnUpload.setEnabled(true);
						dialogUpload.dismiss();
						new AlertDialog.Builder(MRStatusActivity.this)
						.setTitle("Info")
						.setMessage("Sorry,connection error")
						.setPositiveButton("OK", null).show();

					}

					else{
						try 
						{

							System.out.println("data from server......check..Data......"+ response);
							JSONArray jsonarr = new JSONArray(response);
							System.out.println(jsonarr.length()+"<<<<<<<<"+jsonarr.toString());

							if (jsonarr != null) 
							{
								for (int i = 0; i < jsonarr.length(); i++) 
								{
									JSONObject obje = jsonarr.getJSONObject(i);
									databasehandler = new MRDatabaseHandler(getApplicationContext());
									try 
									{
										databasehandler.openToWrite();
										// fetching RRNO from sqlite DB
										int k = databasehandler.Changesgpastatus(obje.getString("rrno"),obje.getString("status"));
										if(k > 0){ count_update++ ; }

										// mySQLiteAdapter.close();
									} catch (Exception e) {
										// TODO: handle exception
										e.printStackTrace();
									}
									databasehandler.close();
								}
							} 

						} catch (JSONException e1)
						{
							e1.printStackTrace();
						}


					}

					btnUpload.setEnabled(true);
					dialogUpload.dismiss();

					setUploadedmsg(count_update);

				}
			}
		}

	}


	public void setUploadedmsg(int count_update)
	{

		int updateCount = 0 ;
		int notupdatedcount = 0 ;

		updateCount  = getBilledsynDataCount();
		notupdatedcount = getBilledUnsynDataCount11();


		if(notupdatedcount == 0){
			new AlertDialog.Builder(MRStatusActivity.this)
			.setTitle("Info")
			.setMessage("Consumers uploaded = "+updateCount+"\n"+
					"Not synched        = "+notupdatedcount)
					//.setMessage(" Consumer Records have been Uploaded to server...")

					.setPositiveButton("OK", new DialogInterface.OnClickListener()
					{

						public void onClick(DialogInterface dialog,int which) 
						{

							setDashboard();
						}
					}).show();

		}else{
			new AlertDialog.Builder(MRStatusActivity.this)
			.setTitle("Info")
			.setMessage("Consumers uploaded = "+updateCount+"\n"+
					"Not synched        = "+notupdatedcount)
					.setPositiveButton("OK", new DialogInterface.OnClickListener()
					{

						public void onClick(DialogInterface dialog,int which) 
						{

							setDashboard();
						}
					}).show();
		}


	}
	public void setUploadedmsg(String msg)
	{

		Toast.makeText(getApplicationContext(),msg,	Toast.LENGTH_LONG).show();


	}


	public int getBilledsynDataCount(){
		int status = 0 ;
		//int upDAteCount = 0 ;
		MRDatabaseHandler	databasehandler111 = new MRDatabaseHandler(getApplicationContext());
		try {
			databasehandler111.openToRead();
			Cursor cursor_billed1 = databasehandler111.GetSubmitandSevercountfromlocal();
			//		startManagingCursor(cursor_billed1);
			if (cursor_billed1 != null && cursor_billed1.moveToFirst()) 
			{	System.out.println(" cursor_billed.getString(0) : "+ cursor_billed1.getString(0));
			status = Integer.parseInt(cursor_billed1.getString(0));
			System.out.println("totakl couynt1111111 : "+ status);
			}
			cursor_billed1.close();
		} catch (Exception e) {
			e.printStackTrace();
			status = 0 ;
		}
		databasehandler111.close();

		return	status ;
	}



	public int getBilledUnsynDataCount11(){
		boolean status = false ;
		int upDAteCount = 0 ;
		MRDatabaseHandler	databasehandler11 = new MRDatabaseHandler(getApplicationContext());
		try {
			databasehandler11.openToRead();
			Cursor cursor_billed = databasehandler11.GetSubmitButNotSyncedCount();
			//startManagingCursor(cursor_billed);
			if (cursor_billed != null && cursor_billed.moveToFirst()) 
			{
				System.out.println(" cursor_billed.getString(0) : "+ cursor_billed.getString(0));
				upDAteCount = Integer.parseInt(cursor_billed.getString(0));
			}
			cursor_billed.close();
		} catch (Exception e) {
			status = false ;
			upDAteCount = 0 ;
			e.printStackTrace();
		}
		databasehandler11.close();

		if(upDAteCount > 0 ){
			status = true ;
		}else{
			status = false ;
		}
		return	upDAteCount ;
	}

	
	
	

}