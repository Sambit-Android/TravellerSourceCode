
package com.bcits.recondiscon;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.sqlite.mvs.DbDisconnection;


/*******************DO NOT delete any line (both uncommented and commented lines)********************************/

public class BluetoothChat extends MasterActivity {

	Dialog  dialog ;
	public String billName = " " ;

	// Debugging
	private static final String TAG = "BluetoothChat";
	private static final boolean D = true;
	public static int bill;
	public static boolean isReport;
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

	// Layout Views
	private TextView mTitle;
	//	private ListView mConversationView;
	//	private EditText mOutEditText;
	private Button mSendButton , button_menu1 ;
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
	private BluetoothChatService mChatService = null;
	String backbuttonenable="YES";

	
	String sdo;
	String listNo;
	
	String totalConnections;
	String totalReconne;
	String totalDisconne ;
	String totalDisconnected ;
	String totalReconnected ;
	String failedDisconnection;
	
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
		button_menu1 =  (Button) findViewById(R.id.button_menu);
		registerForContextMenu(button_menu1);

		mSendButton = (Button) findViewById(R.id.button_print);


		//mSendButton.setClickable(true);
		mSendButton.setVisibility(View.GONE);
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

		}
		catch (Exception e) {e.printStackTrace();}
		autoConnect();
	}

	public void moveactivity()
	{
		System.out.println("move activity method");
		Intent int_print = new Intent(BluetoothChat.this,DashBoard.class);
		startActivity(int_print);
	}

	@Override
	public void onStart() 
	{
		super.onStart();

		if (!mBluetoothAdapter.isEnabled()) 
		{
			Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
			startActivityForResult(enableIntent, REQUEST_ENABLE_BT);

		} else
		{
			if (mChatService == null)
				setupChat();
		}

	}

	@Override
	public synchronized void onResume() 
	{
		super.onResume();
		if (mChatService != null) 
		{
			// Only if the state is STATE_NONE, do we know that we haven't
			// started already
			if (mChatService.getState() == BluetoothChatService.STATE_NONE) 
			{
				mChatService.start();
			}
		}
	}

	private void setupChat() 
	{
		Log.d(TAG, "setupChat()");

		mSendButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				mSendButton.setVisibility(View.GONE);
			    sendMessage();
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
					//	mSendButton.setVisibility(View.VISIBLE);
					registerForContextMenu(button_menu1);
					openContextMenu(button_menu1);
					new AlertDialog.Builder(BluetoothChat.this)
					.setTitle("Bluetooth menu")
					.setPositiveButton(	"Connect a device",	new DialogInterface.OnClickListener() 
					{
						public void onClick(DialogInterface dialog,	int id)
						{
							Intent serverIntent = new Intent(BluetoothChat.this,DeviceListActivity.class);
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



		// Initialize the BluetoothChatService to perform bluetooth connections
		mChatService = new BluetoothChatService(this, mHandler);

		// Initialize the buffer for outgoing messages
		mOutStringBuffer = new StringBuffer("");
	}


	@Override
	public synchronized void onPause() {
		super.onPause();
	}

	@Override
	public void onStop() {
		super.onStop();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		// Stop the Bluetooth chat services
		if (mChatService != null)
			try {
				mChatService.stop();
			} catch (Exception e) {
				e.printStackTrace();
			}
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

	private void sendMessage() {
		try {
			
			if (mChatService.getState() != BluetoothChatService.STATE_CONNECTED) {
				mSendButton.setVisibility(View.VISIBLE);
				//mSendButton.setClickable(true);
				backbuttonenable="YES";
				Toast.makeText(this, R.string.not_connected, Toast.LENGTH_SHORT).show();
				return;
			}else{
				backbuttonenable="NO";
				mSendButton.setVisibility(View.GONE);
				

				byte[] heading1,heading2;
			    heading1 = "\n      CHAMUNDESHWARI ELECTRICITY SUPPLY".getBytes();
				heading2 = "\n      CORPORATION Ltd. MYSORE".getBytes();
				
				if(isReport) {
					new GetReportData(heading1,heading2).execute();
//					SystemClock.sleep(1000);
//					mChatService.new PrintBill(BluetoothChat.this, heading1, heading2,sdo,listNo,totalConnections,totalReconne,totalDisconne,totalDisconnected,totalReconnected,failedDisconnection).execute();
				}else {
					mChatService.new PrintBill(BluetoothChat.this, heading1, heading2,null,null,null,null,null,null,null,null,null).execute();
				}
				}
			
			} catch (Exception e2) {
				e2.printStackTrace();
				 new AlertDialog.Builder(BluetoothChat.this)
		         .setTitle("Info")
		         .setMessage("Consumer details are not correct / Problem occured while connecting to device ,Please try again  ")
		             .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
		                 public void onClick(DialogInterface dialog, int id) {
		                  
		                	 Intent nfData_intnt = new Intent(BluetoothChat.this,DashBoard.class);
		     				startActivity(nfData_intnt);
		         			
		                 }
		             }).show();
			}
		
		
	}

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
						mSendButton.performClick();
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
					Toast.makeText(getApplicationContext(),"Connected to " + mConnectedDeviceName,Toast.LENGTH_SHORT).show();
					mSendButton.setVisibility(View.VISIBLE);
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
				//BluetoothChatService.STATE_NONE:
				mTitle.setText(R.string.title_not_connected);
			}
		}
	};

	@Override 
	public void onActivityResult(int requestCode, int resultCode, Intent data) { //TODO
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
				mChatService.connect(device);		    			    
			}   
			return true;

		case R.id.discoverable:
			ensureDiscoverable();
			return true;
		}
		return false;
	}





	@Override
    public void onBackPressed() {
		new AlertDialog.Builder(BluetoothChat.this).setCancelable(false).setMessage("Do you want to cancel printing ?").setPositiveButton("Yes",new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				startActivity(new Intent(BluetoothChat.this, DashBoard.class));
				BluetoothChat.this.finish();
			}
		}).setNegativeButton("No", null).show();
	}
	




private void autoConnect() {
try {
		setupChat();
		SharedPreferences sp = getSharedPreferences("prefs", 0);  
		String mac_address = sp.getString("last_macid", "no device");
		if(mac_address.equals("no device")){

			System.out.println("------------------------------");
			System.out.println("no device is executing ");
			System.out.println("------------------------------");

			Intent serverIntent = new Intent(this, DeviceListActivity.class);
			startActivityForResult(serverIntent, REQUEST_CONNECT_DEVICE);
		}else{ 			
			Toast.makeText(getApplicationContext(), "\n\n\nCONNECTING TO PRINTER\n\nPLEASE WAIT...\n\n\n", Toast.LENGTH_SHORT).show();
			System.out.println("------------------------------");
			System.out.println(" same device is executing ");
			System.out.println("------------------------------");

			BluetoothDevice device = mBluetoothAdapter.getRemoteDevice(mac_address);
			// Attempt to connect to the device
			mChatService.connect(device);		    			    
		}   
} catch (Exception e) {
	e.printStackTrace();
}
}
private class GetReportData extends AsyncTask<Void, Void, Void>{
	 ArrayList<String> allData;
	byte[]  heading1; byte[] heading2;
	byte[] headTotalConnection,headTotalDiconnecti,headTotalDisconnected,headTotalFailedDisc,headTotalReconnection,headTotalReconnected;
	public GetReportData(byte[] heading1, byte[] heading2) {
		this.heading1= heading1;
		this.heading2 =heading2;
	}

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
	}
	
	@Override
	protected Void doInBackground(Void... arg0) {
		DbDisconnection db = new DbDisconnection(BluetoothChat.this);
		db.open();
		
		 String sdoList= db.getListNo();
		 sdo= sdoList.split("@@@")[0];
		 listNo= sdoList.split("@@@")[1];
		
		 totalConnections = db.getTotalConnection();
		 totalReconne = db.getTodalReconnection();
		 totalDisconne = db.getTodalDisconnection();
		 totalDisconnected = db.getTotalDisconnected();
		 totalReconnected = db.getTodalReConnected();
		 failedDisconnection=db.getFailedDisconnection();
		 
		 allData =db.getAllDataForPrint();
		 
		db.close();
		
		
		String headTotalConnectio   ="Total connections     : "+totalConnections;
		String headTotalDiconnect   ="Total disconnection   : "+totalDisconne;
		String headTotalDisconnecte ="Disconnected          : "+totalDisconnected;
		String headTotalFailedDis   ="Not disconnected      : "+failedDisconnection;
		String headTotalReconnectio ="Total reconnection    : "+totalReconne;
		String headTotalReconnecte  ="Reconnected           : "+totalReconnected;
		
		
		System.out.println("  =========  "+headTotalConnectio);
		System.out.println("  =========  "+headTotalDiconnect);
		System.out.println("  =========  "+headTotalDisconnecte);
		System.out.println("  =========  "+headTotalFailedDis);
		System.out.println("  =========  "+headTotalReconnectio);
		System.out.println("  =========  "+headTotalReconnecte);
		
		
		headTotalConnection =headTotalConnectio.getBytes();
		headTotalDiconnecti=headTotalDiconnect.getBytes();
		headTotalDisconnected=headTotalDisconnecte.getBytes();
		headTotalFailedDisc=headTotalFailedDis.getBytes();
		headTotalReconnection =headTotalReconnectio.getBytes();
		headTotalReconnected= headTotalReconnecte.getBytes();
		return null;
	}
	@Override
	protected void onPostExecute(Void result) {
		super.onPostExecute(result);
		mChatService.new PrintBill(BluetoothChat.this, heading1, heading2,sdo,listNo,headTotalConnection,headTotalDiconnecti,headTotalDisconnected,headTotalFailedDisc,headTotalReconnection,headTotalReconnected,allData).execute();
	}
}

}