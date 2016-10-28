package amr.bluetooth.ble;

import java.util.List;
import java.util.Locale;
import java.util.UUID;

import org.json.JSONObject;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothProfile;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bcits.bsmartbilling.R;

@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
public class BluetoothMReader extends Activity {
	public static final String EXTRAS_DEVICE_NAME = "DEVICE_NAME"; /* Name passed by intent that launched this activity*/
	public static final String EXTRAS_DEVICE_ADDRESS = "DEVICE_ADDRESS";  /*MAC address passed by intent that launched this activity*/
	private final static String TAG = BluetoothMReader.class.getSimpleName(); /* Get name of activity to tag debug and warning messages*/
	private static final String MLDP_PRIVATE_SERVICE = "00035b03-58e6-07dd-021a-08123a000300"; /* Private service for Microchip MLDP*/
	private static final String MLDP_DATA_PRIVATE_CHAR = "00035b03-58e6-07dd-021a-08123a000301";  /*Characteristic for MLDP Data, properties - notify, write*/
	private static final String MLDP_CONTROL_PRIVATE_CHAR = "00035b03-58e6-07dd-021a-08123a0003ff";  /*Characteristic for MLDP Control, properties read, write*/
	private static final String CHARACTERISTIC_NOTIFICATION_CONFIG = "00002902-0000-1000-8000-00805f9b34fb"; /* Special UUID for descriptor needed to enable notifications*/
	private BluetoothAdapter mBluetoothAdapter; /*BluetoothAdapter controls the Bluetooth radio in the phone*/
	private BluetoothGatt mBluetoothGatt; /* BluetoothGatt controls the Bluetooth communication link*/
	private BluetoothGattCharacteristic mDataMDLP;  /*The BLE	characteristic used for MLDP datatransfers*/
	private String mDeviceName, mDeviceAddress; /*Strings for the Bluetooth device name and MAC address*/
	private boolean mConnected = false; /*Indicator of an active Bluetooth connection*/
	public static boolean st = true;
	TextView mConnectionState, tvRecv;

	TextView txtMeterNumber, txtMeterReading;

	private static final String R_N_R_N = "(\\r|\\n|\\r\\n)+";

	/*------ LINKWELL METER commands ---------*/
	private static final String PASSWORD_FOR_READ = "3A 08 00 7A 00 56 49 53 49 4F 4E 33 36 3D C2 0D 0A".replace(" ", "");
	private static final String CURRENT_METER_DETAILS = "3A 00 00 00 01 FF 00 0D 0A".replace(" ", "");
	//private static final String BILLING_HISTORIES = "3A 00 00 00 02 FE 01 0D 0A".replace(" ", "");

	String[] data;
	TextView sdocode;
	TextView date;
	TextView logout;
	AlertDialog show;
	Location curLocation;
	TextView title;
	protected LocationManager locationManager;
	protected Button retrieveLocationButton;
	
	JSONObject venues = null;
	private Button mConnectBtn;
	private Button mbtReaderConfigBtn;
	private Button btn_bt_reader_takereading;
	TextView menu_btn;
	TextView device_pass_log;

	String message = "";
	String sdocode1;
	String meterReading,meterNumber;
	ProgressBar progressBar3;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.activity_bluetooth_m_reader);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,R.layout.window_title);
		Button	titleBack = (Button) findViewById(R.id.titleBack);
                titleBack.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				handler.removeCallbacks(null);
				handler.removeCallbacksAndMessages(null);
				onBackPressed();
				finish();
			}
		});
                
		initiateWidgets();
		setBluetooth();
		buttonClicks();
	}

	// Look for message with switch pressed indicator "->S1\n\r"
	private void processIncomingPacket(final String data) { // TODO INCOMING PACKET MAIN METHOD. This method collect all converted message data from meter.

		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				
				tvRecv.setText(message);
				
				if (message.replaceAll(R_N_R_N, "").toUpperCase(Locale.ENGLISH).replace(" ", "").length() == 22) {
					Toast.makeText(getApplicationContext(), "Connected To Meter", Toast.LENGTH_SHORT).show();
					readMeterData();//READING METER
				}
				if (message.replaceAll(R_N_R_N, "").toUpperCase(Locale.ENGLISH).replace(" ", "").length() > 60) {
					getMeterValues();
				}
			}
		});

	}

	private void getMeterValues() {// TODO

		String ip = message.trim().replaceAll(R_N_R_N, "").toUpperCase(Locale.ENGLISH).replace(" ", "");
		System.out.println(ip + "  HERE FULL MESSAGE FOR CONVERSION");
		if (ip.length() == 122) {
			ip = "3A00000001FF000D0A" + ip.trim();
		}

		byte[] inputByteArray_00 = null;
		System.out.println("ip.length():" + ip.length() + " OUTPUT: " + ip);
		if (ip.length() > 70) {

			inputByteArray_00 = Conversation.fromHexString(ip);

			System.out.println(inputByteArray_00.length + "    LENGTH");

			if (inputByteArray_00.length == 70) {
				System.out.println("_+_+_+_+_+_+_+_inputByteArray_00.length == 70");
				ip = ip.substring(18);
				LinWell_Meter meter = new LinWell_Meter();
				String[] meterDetails = meter.getLinWelData(ip);
				
				meterNumber=meterDetails[0];
				
				int i = Double.valueOf(meterDetails[4]).intValue();
				meterReading=i+"";
				
				btn_bt_reader_takereading.setEnabled(true);
				txtMeterNumber.setText(meterDetails[0]);
				txtMeterReading.setText(meterReading + "  kWh");
				progressBar3.setVisibility(View.GONE);
				mConnectBtn.setVisibility(View.VISIBLE);
			}

		} else {
			progressBar3.setVisibility(View.GONE);
			mConnectBtn.setVisibility(View.VISIBLE);
			System.out.println("Read meter data again");

		}
	}

	private void buttonClicks() {


		mConnectBtn.setOnClickListener(new View.OnClickListener() { // TODO ConnectionButton
					@Override
					public void onClick(View arg0) {
						progressBar3.setVisibility(View.VISIBLE);
						//mConnectBtn.setVisibility(View.GONE);
						txtMeterNumber.setText("-");
						txtMeterReading.setText("-");
						meterReading="";
						meterNumber="";
						message = "";
						tvRecv.setText("");
						SystemClock.sleep(500);
						bleWriterQuantom(mDataMDLP, "17", PASSWORD_FOR_READ);
						handler.postAtTime(runnable, System.currentTimeMillis()+interval);
						handler.postDelayed(runnable, interval);
					}
				});

		mbtReaderConfigBtn.setOnClickListener(new View.OnClickListener() {//TODO 
					@Override
					public void onClick(View v) {
						
						readMeterData();
					}
				});

		btn_bt_reader_takereading
				.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						mBluetoothGatt.disconnect(); // Activity is ending so disconnect from
						mBluetoothGatt.close(); // Close the connection

					}
				});
	}

	public void onStart() {
		super.onStart();
	}



	@Override
	public void onPause() {
		super.onPause();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			handler.removeCallbacks(null);
			handler.removeCallbacksAndMessages(null);
			onBackPressed();
			finish();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	protected void onResume() {
		super.onResume();
try {
	if (mBluetoothAdapter == null || mDeviceAddress == null) { // Check for Bluetooth adappter and device address
		Log.w(TAG,"BluetoothAdapter not initialized or unspecified address."); 
		finish(); // End the Activity
	}
	final BluetoothDevice device = mBluetoothAdapter.getRemoteDevice(mDeviceAddress); // Get the bluetooth device by referencing its address
	if (device == null) { // Check whether a device was returned
		Log.w(TAG, "Device not found.  Unable to connect."); // Warn that something went wrong
		finish(); // End the Activity
	}
	mBluetoothGatt = device.connectGatt(this, false, mGattCallback); // Directly connect to the device so autoConnect is false
	Log.d(TAG, "Trying to create a new connection.");
	SystemClock.sleep(1000);
} catch (Exception e) {
	e.printStackTrace();
	startActivity(new Intent(BluetoothMReader.this, DeviceScanActivity.class));
}
	
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	try {
		mBluetoothGatt.disconnect(); // Activity is ending so disconnect from Bluetooth device
		mBluetoothGatt.close(); // Close the connection
	} catch (Exception e) {
		e.printStackTrace();
	}
		
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.gatt_services, menu); 
		if (mConnected) { // See if connected
			menu.findItem(R.id.menu_connect).setVisible(false); 
			menu.findItem(R.id.menu_disconnect).setVisible(true);
		} else { // If not connected
			menu.findItem(R.id.menu_connect).setVisible(true);
			menu.findItem(R.id.menu_disconnect).setVisible(false);
		}
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) { // Get which menu item was selected
		case R.id.menu_connect: // Option to Connect chosen
			if (mBluetoothGatt != null) { // If there is a valid GATT connection
				mBluetoothGatt.connect(); // then connect

				st = true;
			}
			return true;
		case R.id.menu_disconnect: // Option to Disconnect chosen
			if (mBluetoothGatt != null) { // If there is a valid GATT connection
				mBluetoothGatt.disconnect(); // then disconnect

				st = false;
			}
			return true;
		case android.R.id.home: // Option to go back was chosen
			handler.removeCallbacks(null);
			handler.removeCallbacksAndMessages(null);
			onBackPressed(); // Execute functionality of back button
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private void updateConnectionState(final int resourceId) {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				mConnectionState.setText(resourceId);
			}
		});
	}
	private void findMldpGattService(List<BluetoothGattService> gattServices) {
		if (gattServices == null) { 
			Log.d(TAG, "findMldpGattService found no Services");
			return;
		}
		String uuid; 
		mDataMDLP = null; 

		for (BluetoothGattService gattService : gattServices) { 
			uuid = gattService.getUuid().toString(); 
			if (uuid.equals(MLDP_PRIVATE_SERVICE)) { 
				List<BluetoothGattCharacteristic> gattCharacteristics = gattService.getCharacteristics(); 
				for (BluetoothGattCharacteristic gattCharacteristic : gattCharacteristics) {
					uuid = gattCharacteristic.getUuid().toString(); 
					if (uuid.equals(MLDP_DATA_PRIVATE_CHAR)) { 
						mDataMDLP = gattCharacteristic;
						Log.d(TAG, "Found MLDP data characteristics");
					} else if (uuid.equals(MLDP_CONTROL_PRIVATE_CHAR)) {
						Log.d(TAG, "Found MLDP control characteristics");
					}
					final int characteristicProperties = gattCharacteristic	.getProperties();
					if ((characteristicProperties & (BluetoothGattCharacteristic.PROPERTY_NOTIFY)) > 0) {
						mBluetoothGatt.setCharacteristicNotification(gattCharacteristic, true);
						BluetoothGattDescriptor descriptor = gattCharacteristic.getDescriptor(UUID	.fromString(CHARACTERISTIC_NOTIFICATION_CONFIG));
						descriptor.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE); 
						mBluetoothGatt.writeDescriptor(descriptor); 				
					}
					if ((characteristicProperties & (BluetoothGattCharacteristic.PROPERTY_INDICATE)) > 0) { 
						mBluetoothGatt.setCharacteristicNotification(gattCharacteristic, true); 
						BluetoothGattDescriptor descriptor = gattCharacteristic.getDescriptor(UUID.fromString(CHARACTERISTIC_NOTIFICATION_CONFIG)); 
						descriptor.setValue(BluetoothGattDescriptor.ENABLE_INDICATION_VALUE); 
						mBluetoothGatt.writeDescriptor(descriptor); 
					}
					if ((characteristicProperties & (BluetoothGattCharacteristic.PROPERTY_WRITE)) > 0) {
						gattCharacteristic.setWriteType(BluetoothGattCharacteristic.WRITE_TYPE_DEFAULT); 
																								
					}
					if ((characteristicProperties & (BluetoothGattCharacteristic.PROPERTY_WRITE_NO_RESPONSE)) > 0) {
						gattCharacteristic	.setWriteType(BluetoothGattCharacteristic.WRITE_TYPE_NO_RESPONSE); 
																
					}
				}
				break;
			}
		}
		if (mDataMDLP == null) { // See if the MLDP data characteristic was not
									// found
			Toast.makeText(this, R.string.mldp_not_supported,
					Toast.LENGTH_SHORT).show(); // If so then show an error
												// message
			Log.d(TAG, "found no MLDP service");
			finish(); // and end the activity
		}
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				device_pass_log.setText("BLE device Initialized");
			}
		}); 
	}


	private final BluetoothGattCallback mGattCallback = new BluetoothGattCallback() {
		@Override
		public void onConnectionStateChange(BluetoothGatt gatt, int status,
				int newState) { // Change in connection state
			if (newState == BluetoothProfile.STATE_CONNECTED) { // See if we are
																// connected
				Log.i(TAG, "Connected to GATT server.");
				mConnected = true; // Record the new connection state
				updateConnectionState(R.string.connected);
				invalidateOptionsMenu();
				mBluetoothGatt.discoverServices(); 
			} else if (newState == BluetoothProfile.STATE_DISCONNECTED) {
				Log.i(TAG, "Disconnected from GATT server.");
				mConnected = false; // Record the new connection state
				updateConnectionState(R.string.disconnected); 
				invalidateOptionsMenu(); 
			}
		}

		@Override
		public void onServicesDiscovered(BluetoothGatt gatt, int status) { 
			if (status == BluetoothGatt.GATT_SUCCESS && mBluetoothGatt != null) { 
				findMldpGattService(mBluetoothGatt.getServices()); 
			} else { 
				Log.w(TAG, "onServicesDiscovered received: " + status);
			}
		}

		
		@Override
		public void onCharacteristicRead(BluetoothGatt gatt,
				BluetoothGattCharacteristic characteristic, int status) { 
			if (status == BluetoothGatt.GATT_SUCCESS) { 
				String dataValue = characteristic.getStringValue(0); 
				System.out.println(dataValue+ "Coming here ======================onCharacteristicRead==================");
			}
		}

		
		@Override
		public void onCharacteristicWrite(BluetoothGatt gatt,
				BluetoothGattCharacteristic characteristic, int status) { 
			if (status == BluetoothGatt.GATT_SUCCESS) { 
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						device_pass_log.setText("Data Sending Success");
					}
				});

			} else {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						device_pass_log.setText("Data Sending Failed !!. Please Reconnect Bluetooth");
					}
				});
			}
		}

		@Override
		public void onCharacteristicChanged(BluetoothGatt gatt,
			BluetoothGattCharacteristic characteristic) { // TODO Indication or notification was received

			String dataValue = characteristic.getStringValue(0);
			System.out.println(dataValue+ "Coming here ======================onCharacteristicChanged====================");

			DataInterpretationPojo p = new DataInterpretationPojo(2, 2, 3);
			
			try {
				int bytesRead = -1;
				int bufferSize = 1024;
				byte[] buffer = new byte[bufferSize];
				while (true) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException ie) {
						System.out.println(ie);
					}
					bytesRead = characteristic.getValue().length;

					buffer = characteristic.getValue();
					System.out.println("BUFFER++++++ [  " + buffer+ "  ]  BUFFER");

					if (bytesRead != -1) {

						message = message.trim()+ StringUtil.setSerialDataToTextView(2, buffer,	(bytesRead), "0d", "0a", p).toUpperCase(Locale.ENGLISH).trim();

						System.out.println("MESSAGE++++++ [  " + message+ "  ]  ");
						break;
						
					} else {
						break;
					}
				}

			} catch (Exception e) {
				System.out.println("Exception  " + e);
			}
			System.out.println(" Final  message:   "
					+ message.replaceAll(R_N_R_N, ""));
			processIncomingPacket(message);
		}
	};

	
	private void writeCharacteristic(BluetoothGattCharacteristic characteristic) {
		if (mBluetoothAdapter == null || mBluetoothGatt == null) { // Check that
																	// we have
																	// access to
			progressBar3.setVisibility(View.GONE);				// a
			mConnectBtn.setVisibility(View.VISIBLE);											// Bluetooth
																	// radio
			Log.w(TAG, "BluetoothAdapter not initialized");
			return;
		}
		int test = characteristic.getProperties(); // Get the properties of the
													// characteristic
		if ((test & BluetoothGattCharacteristic.PROPERTY_WRITE) == 0
				&& (test & BluetoothGattCharacteristic.PROPERTY_WRITE_NO_RESPONSE) == 0) {
			return;
		}

		if (mBluetoothGatt.writeCharacteristic(characteristic)) { 
			Log.d(TAG, "writeCharacteristic successful");

		} else {
			Log.d(TAG, "writeCharacteristic failed");
			Toast.makeText(getApplicationContext(), "Writing failed. Please Connect Again",	Toast.LENGTH_SHORT).show();
			handler.removeCallbacks(null);
			handler.removeCallbacksAndMessages(null);
			finish();
		}
	}
	
	
	
	private void initiateWidgets() {
		progressBar3=(ProgressBar)findViewById(R.id.progressBar3);
		txtMeterNumber = (TextView) findViewById(R.id.txtMeterNumber);
		txtMeterReading = (TextView) findViewById(R.id.txtMeterReading);
		tvRecv = (TextView) findViewById(R.id.tvRecv);
		menu_btn = (TextView) findViewById(R.id.title_right_text);
		mConnectBtn = (Button) findViewById(R.id.btn_bt_reader_connect);
		mbtReaderConfigBtn = (Button) findViewById(R.id.btn_bt_reader_config);
		btn_bt_reader_takereading = (Button) findViewById(R.id.btn_bt_reader_takereading);
		device_pass_log = (TextView) findViewById(R.id.device_pass_log);
		sdocode = (TextView) findViewById(R.id.sdocodevalue_bt_reader);
		date = (TextView) findViewById(R.id.datevalue_bt_reader);
		btn_bt_reader_takereading.setEnabled(false);
		mConnectionState = (TextView) findViewById(R.id.connectionState);
		tvRecv.setMovementMethod(new ScrollingMovementMethod());
		progressBar3.setVisibility(View.GONE);
		mConnectBtn.setVisibility(View.VISIBLE);
	}
	
	private void setBluetooth() {
		final Intent intent = getIntent(); 
		mDeviceName = intent.getStringExtra(EXTRAS_DEVICE_NAME);
		mDeviceAddress = intent.getStringExtra(EXTRAS_DEVICE_ADDRESS); 
		((TextView) findViewById(R.id.deviceAddress)).setText("Bluetooth Address : "+mDeviceAddress	+ "\nBluetooth Name : " + mDeviceName); 
		final BluetoothManager bluetoothManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
		mBluetoothAdapter = bluetoothManager.getAdapter(); 
		if (mBluetoothAdapter == null) { 
			Toast.makeText(this, R.string.bluetooth_not_supported,	Toast.LENGTH_SHORT).show(); 
			finish(); 
		}
	}
	private void readMeterData() { //TODO
		if (message.replaceAll(R_N_R_N, "").toUpperCase(Locale.ENGLISH).replace(" ", "").length() < 22) {
			progressBar3.setVisibility(View.GONE);
			new AlertDialog.Builder(BluetoothMReader.this).setTitle("Info").setMessage("Connection Not Proper.\nPlease Read Again").setPositiveButton("Ok", null).show();
			return;
		}
		message = "";
		tvRecv.setText("");
		SystemClock.sleep(500);
		bleWriterQuantom(mDataMDLP, "09", CURRENT_METER_DETAILS);
	}
	private void bleWriterQuantom(BluetoothGattCharacteristic mDataMDLP,String keyCount,String key) {

		if (mDataMDLP != null) {
			mDataMDLP.setValue("BCITS"+keyCount.trim());
			writeCharacteristic(mDataMDLP);
		}

		SystemClock.sleep(300);

		byte[] newData = Conversation.hexStringToByteArray(key);
		if (mDataMDLP != null) {
			mDataMDLP.setValue(newData);
			writeCharacteristic(mDataMDLP);
		} 
	}
	
	private final int interval = 10000; // 7 Second
	private Handler handler = new Handler();
	private Runnable runnable = new Runnable(){
	    public void run() {
	    	if(txtMeterNumber.getText().toString().equals("-")) {
	    		progressBar3.setVisibility(View.VISIBLE);
	    		txtMeterNumber.setText("-");
				txtMeterReading.setText("-");
				meterReading="";
				meterNumber="";
				message = "";
				tvRecv.setText("");
				SystemClock.sleep(1000);
				bleWriterQuantom(mDataMDLP, "17", PASSWORD_FOR_READ);
				handler.postAtTime(runnable, System.currentTimeMillis()+interval);
				handler.postDelayed(runnable, interval);
				// Toast.makeText(BluetoothMReader.this, "Reading Failed. Connecting Again", Toast.LENGTH_SHORT).show();
	    	}
	    	else {
	    		handler.removeCallbacks(null);
				handler.removeCallbacksAndMessages(null);
	    		 Toast.makeText(BluetoothMReader.this, "Reading Completed", Toast.LENGTH_SHORT).show();
	    	}
	    	
	       
	    }
	};
}