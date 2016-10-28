package com.bcits.bsmartbilling.rf;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import Utils.Logger;
import android.app.Activity;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bcits.bsmartbilling.rf.R;

public class BluetoothChat_Reports extends Activity {
	// Debugging
		static Logger logger ;
		private static final String TAG = "BluetoothChat";
		private static final boolean D = true;
		public static int bill;
		// Message types sent from the BluetoothChatService Handler
		public static final int MESSAGE_STATE_CHANGE = 1;
		public static final int MESSAGE_READ = 2;
		public static final int MESSAGE_WRITE = 3;
		public static final int MESSAGE_DEVICE_NAME = 4;
		public static final int MESSAGE_TOAST = 5;
		// Key names received from the BluetoothChatService Handler
		public static final String DEVICE_NAME = "device_name";
		public static final String TOAST = "toast";

		// Intent request codes
		private static final int REQUEST_CONNECT_DEVICE = 1;
		private static final int REQUEST_ENABLE_BT = 2;
		//DatabaseHandler databasehandler;

		// Layout Views
		private TextView mTitle;
		private ListView mConversationView;
		private EditText mOutEditText;
		private Button mSendButton , button_menu1 , bluetoothbckpress1;
		String sqlit_rrno,sqlit_unbilled,sqlit_billed,notupdatedcount,updtedcount;

		// Name of the connected device
		private String mConnectedDeviceName = null;
		// Array adapter for the conversation thread
		private ArrayAdapter<String> mConversationArrayAdapter;
		// String buffer for outgoing messages
		private StringBuffer mOutStringBuffer;
		// Local Bluetooth adapter
		private BluetoothAdapter mBluetoothAdapter = null;
		// Member object for the chat services
		private BluetoothChatService_Reports mChatService = null;
		String backbuttonenable="YES";
		
		@Override
		public void onCreate(Bundle savedInstanceState) 
		{
			super.onCreate(savedInstanceState);
			if (D)
			Log.e(TAG, "+++ ON CREATE +++");

			// Set up the window layout
			requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
			setContentView(R.layout.bluetooth);
			getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,R.layout.custom_title);

			// Set up the custom title
			mTitle = (TextView) findViewById(R.id.title_left_text);
			mTitle.setText(R.string.app_name);
			mTitle = (TextView) findViewById(R.id.title_right_text);
			bluetoothbckpress1 =  (Button) findViewById(R.id.bluetoothbckpress);
			button_menu1 =  (Button) findViewById(R.id.button_menu);
			registerForContextMenu(button_menu1);
			
			try {
				if(logger == null){
		    		logger = MasterLibraryFunction.getlogger(getApplicationContext(), TAG);
		    		}
				logger.info("In Side onCreate()");
			} catch (Exception e) {
				 logger.error("logger is NULL");
			}
			logger.info("In Side onCreate()");
			mSendButton = (Button) findViewById(R.id.button_print);
			
			
			mSendButton.setClickable(true);
			backbuttonenable="YES";
			// Get local Bluetooth adapter
			mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
			try{

			// If the adapter is null, then Bluetooth is not supported
			if (mBluetoothAdapter == null) 
			{
				System.out.println("check****************");
				Toast.makeText(this, "Bluetooth is not available",Toast.LENGTH_LONG).show();
				finish();
				return;
			}
			
			
			String deviceID = "000";
			//System.out.println("SITE CODE   : "+LoginActivity.sitecode1);
			TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
			String imeino = tm.getDeviceId();
			
			//imeino = "352384056737168";
			System.out.println("imeino : "+imeino);
			System.out.println("3 DIGITE CODE    : "+Generate3digitalphanumericcode.generateAuthCode(imeino));
			deviceID = Generate3digitalphanumericcode.generateAuthCode(imeino);
			
			}
			catch (Exception e) {
				logger.error(Log.getStackTraceString(e));
				new AlertDialog.Builder(BluetoothChat_Reports.this)
	             .setTitle("Info")
	             .setMessage("Consumer details is not correct or unable to bill , Please try again later ")
	                 .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	                     public void onClick(DialogInterface dialog, int id) {
	                      
	                    	 Intent nfData_intnt = new Intent(BluetoothChat_Reports.this,MainActivity.class);
	         				startActivity(nfData_intnt);
	             			
	                     }
	                 }).show();
			}
			
		}
		
		@Override
		public void onStart() 
		{
			super.onStart();
			
			if( (UtilMaster.mGLogin_MRCode.trim().equalsIgnoreCase("")) ||  UtilMaster.mGLogin_SiteCode.trim().equalsIgnoreCase("") ){
				logger.error("Session timeout ..! Please Login again UtilMaster.mGLogin_MRCode OR UtilMaster.mGLogin_SiteCode is NULL");
				new AlertDialog.Builder(BluetoothChat_Reports.this)
								.setTitle("Sorry..!")
								.setMessage("Session timeout ..! Please Login again")
						   .setPositiveButton("OK", new DialogInterface.OnClickListener()
						   {
							   
							   public void onClick(DialogInterface dialog,int which) 
							   {
								 
							startActivity(new Intent(BluetoothChat_Reports.this,Login.class));
								//   return;

									
							   }
						   }).show();
			}
			
			
			if (D)
				logger.info(TAG, "++ ON START ++");
			// If BT is not on, request that it be enabled.
			// setupChat() will then be called during onActivityResult
			if (!mBluetoothAdapter.isEnabled()) 
			{
				System.out.println("***********mBluetoothAdapter.enable();*********");
				Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
				startActivityForResult(enableIntent, REQUEST_ENABLE_BT);
			
			} else
			{
				if (mChatService == null)
					setupChat();
			}
			
		}
		
		private void setupChat() 
		{
			Log.d(TAG, "setupChat()");

		
			mSendButton.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					
					mSendButton.setClickable(false);
					
					 System.out.println("Consumer.LSitecode  "+UtilMaster.mGLogin_SiteCode);
					// String [] labels =new String[]{(String) Consumer.LSitecode.subSequence(0, 2),Consumer.LBillno,Consumer.getlBilldate(),Consumer.getLDuedate(),"",(String) Consumer.LSitecode.subSequence(0, 3)};
					//System.out.println(labels[0]);
						
					 if(UtilMaster.	reportType != null)
					 {	
						
					 byte[] heading1 = "-".getBytes(),heading2 = "-".getBytes() ,heading3 ="-".getBytes();
						byte[] total = "-".getBytes() ;
						byte[] billed = "-".getBytes() ;
						byte[] unbilled = "-".getBytes();
						
						total =   ReportViewHelper.totalCount.getBytes() ;
						billed = ReportViewHelper.billedCount .getBytes() ;
						unbilled = ReportViewHelper.unBilledCount.getBytes();
						
						
						heading2 =  (new SimpleDateFormat("EEE, dd/MM/yyyy HH:mm:ss").format(new Date())+"").getBytes();
						TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
						String imeino = tm.getDeviceId();
						heading3 = ((Login.loginMRCode)+" DeviceID: "+imeino).getBytes();
						
						 if(UtilMaster.	reportType.equalsIgnoreCase("SUMMARY_REPORT"))
						 {
							 heading1 = "SUMMARY REPORT".getBytes();
							 sendMessage(UtilMaster.reportType,heading1,heading2 , heading3 , total , billed , unbilled , UtilMaster.mGlobalList);
						 }else if (UtilMaster.	reportType.equalsIgnoreCase("DETAILED_SUMMARY_REPORT"))
						 {
							 heading1 = "DETAILED REPORT".getBytes();
							 sendMessage(UtilMaster.reportType,heading1,heading2 , heading3 , total , billed , unbilled , UtilMaster.detailedReportHelpers);
							 
						 }else if (UtilMaster.reportType.equalsIgnoreCase("ROUTEWISE_SUMMERY_REPORT"))
						 {
							 heading1 = "READING DATE WISE REPORT".getBytes();
							 sendMessage(UtilMaster.reportType,heading1,heading2 , heading3 , total , billed , unbilled , UtilMaster.detailedReportHelpers);
							 
						 }
						
					 }else{
						 new AlertDialog.Builder(BluetoothChat_Reports.this)
						   .setTitle("Info")
						   .setMessage(	"Details are not correct or unable to print , Please try again later ")
						   .setPositiveButton("OK",
						   new DialogInterface.OnClickListener() 
						   {
								public void onClick(DialogInterface dialog,	int which) 
								{
									startActivity(new Intent(BluetoothChat_Reports.this,ReportActionClass.class));
								}
							}).show();
					 }
						
						
				}
			});

			button_menu1.setOnClickListener(new android.view.View.OnClickListener() {
				
				public void onClick(View arg0) {

					System.out.println(" testing :backbuttonenable.equals(NO): "+ backbuttonenable.equals("NO") );
					
					if (backbuttonenable.equals("NO")) { // Here no means
															// dont allow
															// user RECONNECT
															// back

					} else 
					{
						mSendButton.setVisibility(View.VISIBLE);
						registerForContextMenu(button_menu1);
						openContextMenu(button_menu1);
						new AlertDialog.Builder(BluetoothChat_Reports.this)
								.setTitle("Bluetooth menu")
								.setPositiveButton(	"Connect a device",	new DialogInterface.OnClickListener() 
								{
									public void onClick(DialogInterface dialog,	int id)
									{
										Intent serverIntent = new Intent(BluetoothChat_Reports.this,DeviceListActivity.class);
										startActivityForResult(	serverIntent,REQUEST_CONNECT_DEVICE);
									}
								})
								.setNegativeButton("Make discoverable",	new DialogInterface.OnClickListener() 
								{
									public void onClick(DialogInterface dialog,	int id) 
									{
										ensureDiscoverable();
									}
								}).show();
						// Create the AlertDialog object and return it
					}
				}
			});
			
			
			bluetoothbckpress1.setOnClickListener(new OnClickListener() {
				
				public void onClick(View arg0) {
					onBackPressed();
					
				}
			});
			
			// Initialize the BluetoothChatService to perform bluetooth connections
			mChatService = new BluetoothChatService_Reports(this, mHandler);

			// Initialize the buffer for outgoing messages
			mOutStringBuffer = new StringBuffer("");
		}

		@Override
		public synchronized void onPause() {
			super.onPause();
			if (D)
				logger.info(TAG, "- ON PAUSE -");
		}

		@Override
		public void onStop() {
			super.onStop();
			if (D)
				logger.info(TAG, "-- ON STOP --");
		}

		@Override
		public void onDestroy() {
			super.onDestroy();
			// Stop the Bluetooth chat services
			if (mChatService != null)
				mChatService.stop();
			if (D)
				logger.info(TAG, "--- ON DESTROY ---");
		}

		private void ensureDiscoverable() {
			if (D)
				Log.d(TAG, "ensure discoverable");
			if (mBluetoothAdapter.getScanMode() != BluetoothAdapter.SCAN_MODE_CONNECTABLE_DISCOVERABLE) 
			{
				Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
				discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
				startActivity(discoverableIntent);
			}
		}
		
		/**
		 * Sends a message.
		 * 
		 * @param message
		 *            A string of text to send.
		 */
		private void sendMessage(String type,byte[] heading1,byte[] heading2, byte[] heading3 , byte[] total ,	byte[] billed,	byte[] unbilled , List list_) {
			try {
				
				
				
				// Check that we're actually connected before trying anything
				if (mChatService.getState() != BluetoothChatService.STATE_CONNECTED) {
					mSendButton.setClickable(true);
					backbuttonenable="YES";
					Toast.makeText(this, R.string.not_connected, Toast.LENGTH_SHORT).show();
					return;
				}else{
					backbuttonenable="NO";

					mSendButton.setEnabled(false);
			mChatService.write(getApplicationContext(), type, heading1 , heading2 , heading3, total , billed , unbilled , list_ );

			//print_billl();
			System.out.println("***********************");
			
			if(UtilMaster.	reportType.equalsIgnoreCase("SUMMARY_REPORT")){
			 Movetobilledactivity(5);
			 }else{
				 Movetobilledactivity(12);
			 }
			
				
				}
				
				} catch (Exception e2) {
					e2.printStackTrace();
					logger.error(Log.getStackTraceString(e2));
					 new AlertDialog.Builder(BluetoothChat_Reports.this)
			         .setTitle("Info")
			         .setMessage("Consumer details are not correct / Problem occured while connecting to device ,Please try again  ")
			             .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			                 public void onClick(DialogInterface dialog, int id) {
			                  
			                	 Intent nfData_intnt = new Intent(BluetoothChat_Reports.this,MainActivity.class);
			     				startActivity(nfData_intnt);
			         			
			                 }
			             }).show();
				}
			
			
		}
		
		
	public void Movetobilledactivity(int time ) 
	{

		System.out.println("Movetobilledactivity  ");
		System.out.println("gps data updated****************************************************************************");
		// time = 3 ;

		try 
		{
			new Handler().postDelayed(new Runnable() 
			{
				public void run() 
				{
					startActivity(new Intent(BluetoothChat_Reports.this,ReportActionClass.class));
				}
			}, 1000 * time);
		
		} catch (Exception e) 
		{
			logger.error(Log.getStackTraceString(e));
			e.printStackTrace();
			new Handler().postDelayed(new Runnable() 
			{
				public void run() 
				{
					new AlertDialog.Builder(BluetoothChat_Reports.this)
								   .setTitle("Info")
								   .setMessage(	"Details are not correct or unable to print , Please try again later ")
								   .setPositiveButton("OK",
								   new DialogInterface.OnClickListener() 
								   {
										public void onClick(DialogInterface dialog,	int which) 
										{
											startActivity(new Intent(BluetoothChat_Reports.this,ReportActionClass.class));
										}
									}).show();
				}
			}, 1000 * time);

		}

	}
		
		
		
		
		
		
		
		// The Handler that gets information back from the BluetoothChatService
		private final Handler mHandler = new Handler() {
			@Override
			
			public void handleMessage(Message msg) {
				try {
				
				switch (msg.what) {
				case MESSAGE_STATE_CHANGE:
					if (D)
						Log.i(TAG, "MESSAGE_STATE_CHANGE: " + msg.arg1);
					switch (msg.arg1) {
					case BluetoothChatService.STATE_CONNECTED:
						mTitle.setText(R.string.title_connected_to);
						mTitle.append(mConnectedDeviceName);
						// mConversationArrayAdapter.clear();
						break;
					case BluetoothChatService.STATE_CONNECTING:
						mTitle.setText(R.string.title_connecting);
						break;
					case BluetoothChatService.STATE_LISTEN:
					case BluetoothChatService.STATE_NONE:
						mTitle.setText(R.string.title_not_connected);
						break;
					}
					break;
				case MESSAGE_WRITE:
					byte[] writeBuf = (byte[]) msg.obj;
					// construct a string from the buffer
					String writeMessage = new String(writeBuf);
					mConversationArrayAdapter.add("Me:  " + writeMessage);
					break;
				case MESSAGE_READ:
					byte[] readBuf = (byte[]) msg.obj;
					// construct a string from the valid bytes in the buffer
					String readMessage = new String(readBuf, 0, msg.arg1);
					if(mConversationArrayAdapter!=null)
					mConversationArrayAdapter.add(mConnectedDeviceName + ":  "
							+ readMessage);
					break;
				case MESSAGE_DEVICE_NAME:
					// save the connected device's name
					mConnectedDeviceName = msg.getData().getString(DEVICE_NAME);
					Toast.makeText(getApplicationContext(),
							"Connected to " + mConnectedDeviceName,
							Toast.LENGTH_SHORT).show();
					break;
				/*case MESSAGE_TOAST:
					Toast.makeText(getApplicationContext(),
							msg.getData().getString(TOAST), Toast.LENGTH_SHORT)
							.show();
					break;*/
				case MESSAGE_TOAST:
					///////////////////////ADDED CODE////////////////////////////////////////
					String txt = msg.getData().getString(TOAST);
					if(txt.equalsIgnoreCase("Unable to connect device")){
						SharedPreferences sp = getSharedPreferences("prefs", 0);
						SharedPreferences.Editor editor = sp.edit();
						editor.clear();
						editor.commit();
						Toast.makeText(getApplicationContext(),	txt, Toast.LENGTH_SHORT).show();
						Intent serverIntent = new Intent(getApplicationContext(), DeviceListActivity.class);
						startActivityForResult(serverIntent, REQUEST_CONNECT_DEVICE);
						break;
						/////////////////////////////////////////////////////////////////////////////
					}
					
				}
				
				}catch (Exception e) {
					// TODO: handle exception
					//BluetoothChatService.STATE_NONE:
							mTitle.setText(R.string.title_not_connected);
				}
			}
		};

		@Override
		public void onActivityResult(int requestCode, int resultCode, Intent data) {
			if (D)
				Log.d(TAG, "onActivityResult >>>>>>>>>>>onActivityResult>>>>>>>>>>>>>>>>>>>>>>>>>>>: " + resultCode);
			switch (requestCode) {
			case REQUEST_CONNECT_DEVICE:
				// When DeviceListActivity returns with a device to connect
				if (resultCode == Activity.RESULT_OK) {
					// Get the device MAC address
					String address = data.getExtras().getString(DeviceListActivity.EXTRA_DEVICE_ADDRESS);
					
					
					   /////////////////ADDED CODE///////////////////
								SharedPreferences sp = getSharedPreferences("prefs", 0);
								SharedPreferences.Editor editor = sp.edit();
								editor.putString("last_macid", address);					     
								editor.commit();	
								/////////////////////////////////////////////
								
					// Get the BLuetoothDevice object
					BluetoothDevice device = mBluetoothAdapter.getRemoteDevice(address);
					// Attempt to connect to the device
					mChatService.connect(device);
				}
				break;
			case REQUEST_ENABLE_BT:
				// When the request to enable Bluetooth returns
				if (resultCode == Activity.RESULT_OK) {
					// Bluetooth is now enabled, so set up a chat session
					setupChat();
					
					/////////////////ADDED CODE///////////////////
					SharedPreferences sp = getSharedPreferences("prefs", 0);  
			        String mac_address = sp.getString("last_macid", "no device");
			        if(mac_address.equals("no device")){
			        	
			        	System.out.println("------------------------------");
			        	System.out.println("no device is executing ");
			        	System.out.println("------------------------------");
			        	
			        	Intent serverIntent = new Intent(this, DeviceListActivity.class);
						startActivityForResult(serverIntent, REQUEST_CONNECT_DEVICE);
			        }else{ 			
			        	
			        	System.out.println("------------------------------");
			        	System.out.println(" same device is executing ");
			        	System.out.println("------------------------------");
			        	
					    BluetoothDevice device = mBluetoothAdapter.getRemoteDevice(mac_address);
						// Attempt to connect to the device
						mChatService.connect(device);		    			    
			        }   
			        ///////////////////////////////////////////////
				} else {
					// User did not enable Bluetooth or an error occured
					Log.d(TAG, "BT not enabled");
					Toast.makeText(this, R.string.bt_not_enabled_leaving,
							Toast.LENGTH_SHORT).show();
					finish();
				}
			}
		}

		@Override
		public boolean onCreateOptionsMenu(Menu menu) {
			MenuInflater inflater = getMenuInflater();
			inflater.inflate(R.menu.option_menu, menu);
			return true;
		}

		@Override
		public boolean onOptionsItemSelected(MenuItem item) {
			switch (item.getItemId()) {
			case R.id.scan:
				// Launch the DeviceListActivity to see devices and do scan
				/*Intent serverIntent = new Intent(this, DeviceListActivity.class);
				startActivityForResult(serverIntent, REQUEST_CONNECT_DEVICE);
				return true;*/
				
	/////////////////ADDED CODE///////////////////
		SharedPreferences sp = getSharedPreferences("prefs", 0);  
	    String mac_address = sp.getString("last_macid", "no device");
	    if(mac_address.equals("no device")){
	    	
	    	System.out.println("------------------------------");
	    	System.out.println("no device is executing ");
	    	System.out.println("------------------------------");
	    	
	    	Intent serverIntent = new Intent(this, DeviceListActivity.class);
			startActivityForResult(serverIntent, REQUEST_CONNECT_DEVICE);
	    }else{ 			
	    	
	    	System.out.println("------------------------------");
	    	System.out.println(" same device is executing ");
	    	System.out.println("------------------------------");
	    	
		    BluetoothDevice device = mBluetoothAdapter.getRemoteDevice(mac_address);
			// Attempt to connect to the device
			mChatService.connect(device);		    			    
	    }   
	    ///////////////////////////////////////////////
	    return true;
				
			case R.id.discoverable:
				// Ensure this device is discoverable by others
				ensureDiscoverable();
				return true;
			}
			return false;
		}

		private String convertbillmonth(String lbillmonth) {
			// TODO Auto-generated method stub
			String yearno = lbillmonth.substring(2, 4);
			String billmonth = lbillmonth.substring(4, 6);
			String year = lbillmonth.substring(0, 4);
			if (billmonth.equals("01")) {
				billmonth = "JAN";
			} else if (billmonth.equals("02")) {
				billmonth = "FEB";
			} else if (billmonth.equals("03")) {
				billmonth = "MAR";
			} else if (billmonth.equals("04")) {
				billmonth = "APR";
			} else if (billmonth.equals("05")) {
				billmonth = "MAY";
			} else if (billmonth.equals("06")) {
				billmonth = "JUN";
			} else if (billmonth.equals("07")) {
				billmonth = "JUL";
			} else if (billmonth.equals("08")) {
				billmonth = "AUG";
			} else if (billmonth.equals("09")) {
				billmonth = "SEP";
			} else if (billmonth.equals("10")) {
				billmonth = "OCT";
			} else if (billmonth.equals("11")) {
				billmonth = "NOV";
			} else if (billmonth.equals("12")) {
				billmonth = "DEC";
			}

			return billmonth + yearno;
		}
		
		
		public static double doubRoundInChart(double val, int places) {
			long factor = (long)Math.pow(10,places);

			// Shift the decimal the correct number of places
			// to the right.
			val = val * factor;

			// Round to the nearest integer.
			long tmp = Math.round(val);

			// Shift the decimal the correct number of places
			// back to the left.
			return (double)tmp / factor;
		}
		@Override
	    public void onBackPressed() {
			System.out.println(" onBackPressed ");

			System.out.println(" backbuttonenable=;"+ backbuttonenable);
			
	       if (backbuttonenable.equals("NO")) {  //Here no means dont allow user to go back

	       } else {
	    	   Intent inte_back = new Intent(	BluetoothChat_Reports.this,	ReportActionClass.class);
	   		startActivity(inte_back); 
	       }

	   }
		public static String addPreSpace(String string, int length) {

			int n = 0;
			if (string == null) {
				string = "-";
				n = string.length();
			} else {
				n = string.length();

			}

			int space = length - n;
			//StringBuilder s = new StringBuilder(" ");
			StringBuilder k = new StringBuilder(" ");
			
			for (int i = 0; i < space; i++) {
				k = k.append(" ");
			}

			String f = k.toString();
			return  f+string;
		}
		public static String addPostSpace(String string, int length) {

			int n = 0;
			if (string == null) {
				string = "-";
				n = string.length();
			} else {
				n = string.length();
			}

			int space = length - n;
			//StringBuilder s = new StringBuilder(" ");
			StringBuilder k = new StringBuilder(" ");
			
			for (int i = 0; i < space; i++) {
				k = k.append(" ");
			}

			String f = k.toString();
			return  string+f;
		}
}
