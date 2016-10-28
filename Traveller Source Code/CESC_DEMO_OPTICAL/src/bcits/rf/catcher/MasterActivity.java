package bcits.rf.catcher;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

import amr.utils.AmrMethods;
import amr.utils.FileManager;
import amr.utils.FrameMaster;
import amr.utils.FtdiManager;
import amr.utils.HelperClass;
import amr.utils.UsbRecieverService;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bcits.bsmartbilling.R;

public class MasterActivity extends FtdiManager {
	TextView txtProgressInfo;
	ListView listMeterNumbers;
	LinearLayout linearReadingFrame,linearSearchingFrame;
	Button btnReadMeter,btnDisconnect,btnReconnect,btnDiscover,btnStopDiscover;
	
	public static Context context;
	IntentFilter filter = new IntentFilter();
	public static String logNameTime; 
    boolean isReadingMeter; //CONROLLING ALL THE THREADS AND OTHER ACTIVITIES
	public static String readingStatus="";
	public static final String STOP_SEARCHING = "Stop Search";
	//  RF RELATED VARIABLES
    public static final String PING = "PING"; 
    public static final String SET_CONTEXT = "SET CONTEXT";
    public static final String DISCOVER = "DISCOVER";
    public static final String OPEN_CHANNEL = "OPEN CHANNEL";
    public static final String CLOSE_CHANNEL = "CLOSE CHANNEL";
    public static final String BILLING = "BILLING";
    public static final String DISCONNECT_METER = "DISCONNECT METER";
    public static final String RECONNECT_METER = "RECONNECT METER";
    
    public static final String MAGIC_NUMBER="C91ECF57";
    public static final String SUCCESS = "SUCCESS";
    public static final int DATA_MIN_LENGTH = 32; // RECEIEVE DATA MINIMUM LENGTH (20 zeros+ magic number + frame size)
    public static int DISCOVERY_DELAY=5000;
    
    public String ACTION_USB_PERMISSION = "com.bsmart.amr.radio.USB_PERMISSION";
    public static String SOURCE_ADDRESS="00000000";
    public static String METER_ADDRESS="0000BDBB"; // DEFAULT GENUS 0000BDBB   //L&T  0000B9A4
    public static int METER_CHANNEL=2;
    public String finalResponse=""; 
    
    public static int asyncTimeout=20000; // DEFAULT TIME OUT OF ASYNCTASK IS 20 seconds
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE); 
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.rf_main);
		listMeterNumbers=(ListView)findViewById(R.id.listMeterNumbers);
		btnReadMeter=(Button)findViewById(R.id.btnReadMeter);
		btnDiscover=(Button)findViewById(R.id.btnDiscover);
		btnStopDiscover=(Button)findViewById(R.id.btnStopDiscover);
		btnStopDiscover.setText(STOP_SEARCHING);
		btnStopDiscover.setVisibility(View.GONE);
		txtProgressInfo=(TextView)findViewById(R.id.txtProgressInfo);
		linearReadingFrame=(LinearLayout)findViewById(R.id.linearReadingFrame);
		linearSearchingFrame=(LinearLayout)findViewById(R.id.linearSearchingFrame);
		btnDisconnect=(Button)findViewById(R.id.btnDisconnect);
		btnReconnect=(Button)findViewById(R.id.btnReconnect);
		
		FileManager.createAllFolders();
		logNameTime = new SimpleDateFormat("yyyyMMdd",Locale.getDefault()).format(Calendar.getInstance().getTime());//GET CURRENT READING TIME
		
		startService(new Intent(MasterActivity.this,UsbRecieverService.class)); // START USB SERVICE
		
		context=this;
		
		filter.addAction(UsbRecieverService.USB_ENABLED_INTENT);  //INTENT FOR USB DETECTION
		registerReceiver(updateUIReciver,filter);                 //REGISTER USB RECEIVER
		
		resetUI();
	}
	

	public abstract class AsyncTaskParent extends  AsyncTask<String, String, String> {
		ProgressBar progressBar;
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			progressBar =(ProgressBar)findViewById(R.id.progressBar);
			progressBar.setVisibility(View.VISIBLE);
			btnReadMeter.setEnabled(false);
			btnDisconnect.setEnabled(false);
			btnReconnect.setEnabled(false);
			new CountDownTimer(asyncTimeout, asyncTimeout) {
		        public void onTick(long millisUntilFinished) {
		            // You can monitor the progress here as well by changing the onTick() time
		        }
		        public void onFinish() {
		            // stop async task if not in progress
		            if (AsyncTaskParent.this.getStatus() == AsyncTask.Status.RUNNING) {
		            	AsyncTaskParent.this.cancel(true);
		            	AsyncTaskParent.this.onPostExecute("RF TIME OUT");
		            	AmrMethods.customToast(getApplicationContext(), "RF Device Timeout. Try Again");
//		            	disconnectFunction();
		            	try {
		            		startActivity(new Intent(MasterActivity.this,  RfLibMain.activityIn));
			            	MasterActivity.this.finish();
						} catch (Exception e) {
							e.printStackTrace();
						}
		            	
		            }
		        }
		    }.start();
		}
		
		@Override
		protected void onProgressUpdate(String... response) {
			super.onProgressUpdate(response);
			String append=response[0]; // ADDING NEXT LINE
			SpannableString text = new SpannableString(append);
			text.setSpan(new ForegroundColorSpan(Color.BLACK), 0, append.length(), 0);
			txtProgressInfo.setText(text);
		}
		
		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			progressBar.setVisibility(View.GONE);
			btnReadMeter.setEnabled(true);
			btnDisconnect.setEnabled(true);
			btnReconnect.setEnabled(true);
			onResume();//CHECKING FOR THE USB STATUS
		}
	}
	
	
	
	
	BroadcastReceiver updateUIReciver = new BroadcastReceiver() { //THIS CUSTOM BROADCAST USED FOR DETECTING USB FROM SERVICE
        @Override
		public void onReceive(Context context, Intent intent) {
			if (intent.getBooleanExtra("USB_CONNECTED", false)) {
				uiUsbConnected();
			} else {
				uiUsbNotConnected();
			}
		}
    };
    
    @Override
    protected void onResume() {
		super.onResume();
		
		if(context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_USB_HOST))
		{
			if(UsbRecieverService.usbConnected) {
				uiUsbConnected();
			}else {
				uiUsbNotConnected();
			}
		}else {
			txtProgressInfo.setText("THIS PHONE IS NOT SUPPORTED");
		}
    };
    

	@Override
	protected void onDestroy() {
		super.onDestroy();
		try {
			unregisterReceiver(updateUIReciver);
		} catch (Exception e) {
			Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
		}
	};
	
	public void uiUsbConnected() {
		/*if(!isReadingMeter) {
			linearReadingFrame.setVisibility(View.VISIBLE);
			linearSearchingFrame.setVisibility(View.VISIBLE);
		}*/
		txtProgressInfo.setText("RF DEVICE CONNECTED");
		txtProgressInfo.setCompoundDrawablesWithIntrinsicBounds( android.R.drawable.presence_online, 0, 0, 0);
		txtProgressInfo.setTextColor(Color.parseColor(getResources().getString(R.string.Green)));
	}
	public void uiUsbNotConnected() {
		try {
			if(isReadingMeter) { // HANDLING DISCONNECTION WHILE READING THE METER
				AmrMethods.customToast(context, "Try Again");
				isReadingMeter= false;
//				android.os.Process.killProcess(android.os.Process.myPid()); // KILLING READING PROCESS
//				disconnectFunction();
				startActivity(new Intent(MasterActivity.this,  RfLibMain.activityIn));
				MasterActivity.this.finish();
				return;
			}
			
//			linearReadingFrame.setVisibility(View.GONE);
//			linearSearchingFrame.setVisibility(View.GONE);
			txtProgressInfo.setText("RF DEVICE NOT CONNECTED");
			txtProgressInfo.setCompoundDrawablesWithIntrinsicBounds( android.R.drawable.presence_offline, 0, 0, 0);
			txtProgressInfo.setTextColor(Color.parseColor(getResources().getString(R.string.Red)));
		} catch (Exception e) {
			AmrMethods.customToast(context, e.getMessage());
		}
		
	}
	

	public void resetUI() {
//		linearSearchingFrame.setVisibility(View.GONE);
//		linearReadingFrame.setVisibility(View.GONE);
		txtProgressInfo.setVisibility(View.VISIBLE);
		onResume(); //GETTING THE USB CONNECTED STATUS
	}
	
	@Override
	public void onBackPressed() {
		if(isReadingMeter) {
			AmrMethods.customToast(context, "READING THE METER \n Back Press Not Allowed...");
			return;
		}
		try {
			startActivity(new Intent(MasterActivity.this,  RfLibMain.activityIn));
			MasterActivity.this.finish();
		} catch (Exception e) {
			MasterActivity.this.finish();
		}
		
	}
	
// =================================RF RELATED=============================================================	//TODO
	
	  
	  public class OneTapReading extends AsyncTaskParent{
			String METER_ADDRESS;
			String readingType;
			int meterChannel;
			public OneTapReading(int meterChannel,String meterAddress,String readingType) {
				this.METER_ADDRESS=meterAddress;
				this.readingType=readingType;
				this.meterChannel=meterChannel;
//				linearReadingFrame.setVisibility(View.GONE);
				isReadingMeter=true;  
				connection();
			}

			@Override
			protected String doInBackground(String... arg0) {
				publishProgress("Connecting to RF...");
				String response="";
				       finalResponse="";
				try {
					
					writeToDevice(FrameMaster.ping());
				    response= parseResponse(PING);
				    publishProgress(PING+" "+response);
					if(SUCCESS.equals(response))
					{   writeToDevice(FrameMaster.setContext(SOURCE_ADDRESS,meterChannel));
						response= parseResponse(SET_CONTEXT);
						 publishProgress(SET_CONTEXT+" "+response);
						if(SUCCESS.equals(response))
						{		writeToDevice(FrameMaster.openChannel(SOURCE_ADDRESS,METER_ADDRESS));
								response= parseResponse(OPEN_CHANNEL);
								publishProgress(OPEN_CHANNEL+" "+response);
								if(SUCCESS.equals(response))
								{	// MAIN READING HAPPENS HERE TODO
									if(readingType.equals(BILLING)) {
									writeToDevice(FrameMaster.readInstantaneous(SOURCE_ADDRESS,METER_ADDRESS));
									response= parseResponse(BILLING);
									publishProgress(BILLING+" "+response);
									}
									else if(readingType.equals(DISCONNECT_METER)) {
									writeToDevice(FrameMaster.disConnectMeter(SOURCE_ADDRESS,METER_ADDRESS));
									response= parseResponse(DISCONNECT_METER);
									publishProgress(DISCONNECT_METER+" "+response);
									}
									else if(readingType.equals(RECONNECT_METER))
									{
									writeToDevice(FrameMaster.reConnectMeter(SOURCE_ADDRESS,METER_ADDRESS));
									response= parseResponse(RECONNECT_METER);
									publishProgress(RECONNECT_METER+" "+response);
									}		
									if(SUCCESS.equals(response))
									{
										writeToDevice(FrameMaster.closeChannel(SOURCE_ADDRESS,METER_ADDRESS));
										response= parseResponse(CLOSE_CHANNEL);
										publishProgress(CLOSE_CHANNEL+" "+response);
										if(SUCCESS.equals(response))
										{
											publishProgress(response);
										}
									}
								}
						}
					}
				} catch (Exception e) {
					FileManager.writeLog("OneTapReading doinback - "+e.getMessage());
					e.printStackTrace();
				}
				return response;
			}
			
			
			@Override
			protected void onPostExecute(String result) {
				super.onPostExecute(result);
				isReadingMeter=false;
//				linearReadingFrame.setVisibility(View.VISIBLE);
				SpannableString text = new SpannableString(result);
				text.setSpan(new ForegroundColorSpan(Color.BLUE), 0, result.length(), 0);
				txtProgressInfo.setText(text);
				if(finalResponse.isEmpty()) {
					return;
				}
				txtProgressInfo.setText(finalResponse);
				customAlertRF(readingType, finalResponse);
			}
			
		}
	  
	  
	
	public synchronized String parseResponse(String readingType) { // TODO THIS METHOD LOOPING HAS TO BE STOPPED
		try {
			String dataInHex = null;
			while (isReadingMeter) {
//				System.out.println("PARSING DATTTTAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
				if (null != receivedData)
					dataInHex = HelperClass.bytesToHex(receivedData).replaceAll(UNWANTED_SPACE, "").trim();
					if (null!=dataInHex && !dataInHex.isEmpty()) 
						if (dataInHex.length() > DATA_MIN_LENGTH && dataInHex.contains(MAGIC_NUMBER)) // CHECK MINIMUM LENGTH TO GET THE SIZE OF FRAME
							if(containFullData(dataInHex,readingType)) // IF DATA IS NOT FULL, LOOP CONTINUE ITS WORK
							{
							String frameId = getFrameId(dataInHex,readingType);
							switch (readingType) {

							case PING:
								if(frameId.equals("18")) {
									SOURCE_ADDRESS =dataInHex.substring( dataInHex.length()-12, dataInHex.length()-4);
									return SUCCESS;
								}else {
									return	frameException(readingType,frameId);
									}
								
							case SET_CONTEXT:
								
								if(frameId.equals("80") ) {
									return SUCCESS;
								}else {
									return	frameException(readingType,frameId);
									} 
						
							case OPEN_CHANNEL:
								if(frameId.equals("80")) {
									return SUCCESS;
								}else {
								return	frameException(readingType,frameId);
									}
							case CLOSE_CHANNEL:
								if(frameId.equals("80")) {
									return SUCCESS;
								}else {
									return readingType+" FRAME NOT OK "+frameId;
								}
							case BILLING:
								if(frameId.equals("80")) {
										finalResponse=getKwh(dataInHex);
										return SUCCESS;
								}else {
									
									return frameException(readingType,frameId);
								}
							case DISCONNECT_METER:
								if(frameId.equals("80")) {
									finalResponse=isDiconnected(dataInHex,DISCONNECT_METER);
									return SUCCESS;
								}else {
									return	frameException(readingType,frameId);
								}
							case RECONNECT_METER: 
								if(frameId.equals("80")) {
									finalResponse=isDiconnected(dataInHex,RECONNECT_METER);
									return SUCCESS;
								}else {
									return	frameException(readingType,frameId);
									}
							}
							}
			}
		} catch (Exception e) {
			FileManager.writeLog("ParseResponse  - "+e.getMessage());
			e.printStackTrace();
		}
		return readingType+" FAILED";
	}

	public String frameException(String readingType,String frameId) {

		if(frameId.equals("87"))
		{
			return "TIME OUT. METER NOT RESPONDING";
		}
		
		if(frameId.equals("82")) {
			writeToDevice(FrameMaster.closeChannel(SOURCE_ADDRESS,METER_ADDRESS));
			return "FRAME ALREADY OPENED. TRY AGAIN";
		}
		return readingType+" FRAME NOT OK "+frameId;
	
	}
	
	
	
	public String getKwh(String dataInHex) {
		 String kWh="";
		 String dataAfterMagicNo = dataInHex.split(MAGIC_NUMBER)[2].trim(); // TAKING SECOND MAGIC NUMBER FOR BILLING DATA
		 String afterStartFrame=dataAfterMagicNo.split("C4E1")[1].trim();
		 kWh=afterStartFrame.substring(16,24);
		 FileManager.writeLog("kWH in HexaDecimal  ==  "+ kWh);
		 DecimalFormat format= new DecimalFormat("0.00");
		 kWh=String.valueOf(format.format((HelperClass.hexToDoubleConvert(kWh)/1000)));
		 FileManager.writeLog("kWH in DOUBLE  ==  "+ kWh);
		return kWh;
	}

	
	public String isDiconnected(String dataInHex,String readingType) {
		 String dataAfterMagicNo = dataInHex.split(MAGIC_NUMBER)[2].trim(); // TAKING SECOND MAGIC NUMBER FOR BILLING DATA
		 String afterStartFrame=dataAfterMagicNo.split("C4E1")[1].trim();
		 String data=afterStartFrame.substring(4,6);
		 FileManager.writeLog("DISCONNECT DATA   ==  "+ data);
		 if(readingType.equals(DISCONNECT_METER) && data.equals("00")) {
			 return "DISCONNECTED";
		 }else if( readingType.equals(RECONNECT_METER) && data.equals("01")){
			 return "RECONNECTED";
		 }
		return "FAILED";
	}
	
	public String getFrameId(String dataInHex,String readingType) {  
		String dataAfterMagicNo = dataInHex.split(MAGIC_NUMBER)[1].trim();
		String frameId=dataAfterMagicNo.substring(4, 6);
		FileManager.writeLog("FRAME_ID of "+readingType+" - "+ frameId);
		return frameId;
	}

	public boolean containFullData(String dataInHex,String readingType) { // CHECKING, IT CONTAINS FULL DATA ACCORDING TO THE RESPONSE DATA SIZE
	if(readingType.equals(BILLING)|| readingType.equals(DISCONNECT_METER) || readingType.equals(RECONNECT_METER))
	{
		return containFullBillingData(dataInHex,readingType);
	}
	   String dataAfterMagicNo =dataInHex.split(MAGIC_NUMBER)[1].trim(); 
	   int sizeFrame=Integer.parseInt(dataAfterMagicNo.substring(0,4), 16); // TAKING SIZE OF THE FRAME FROM THE RESPONSE
	   if(dataAfterMagicNo.length()==(sizeFrame*2)+4)// INCLUDING CRC LENGTH
	   { 
		   return true;
	   } 
		return false;
	}
	
	public boolean containFullBillingData(String dataInHex,String readingType) {
	int dataLength =100;
	if(readingType.equals(BILLING)) {
		dataLength=152;
	}
	if(dataInHex.length()>dataLength) {
	   String dataAfterMagicNo =dataInHex.split(MAGIC_NUMBER)[2].trim(); // TAKING SECOND MAGIC NUMBER FOR BILLING DATA
	   int sizeFrame=Integer.parseInt(dataAfterMagicNo.substring(0,4), 16); // TAKING SIZE OF THE FRAME FROM THE RESPONSE
	   if(dataAfterMagicNo.length()==(sizeFrame*2)+4)// INCLUDING CRC LENGTH
	   {
		   return true;
	   }
	}
		return false;
	}
public void customAlertRF(final String readingType,final String msg) {
		
		final Dialog dialog1 = new Dialog(context);
	    dialog1.requestWindowFeature(Window.FEATURE_NO_TITLE);
	    dialog1.setContentView(R.layout.rf_final_result);
	    dialog1.setCancelable(false);
	    
	    Button btnOkAlert = (Button) dialog1.findViewById(R.id.btnOkAlert);
	    Button btnCancelAlert = (Button) dialog1.findViewById(R.id.btnCancelAlert);
	    TextView txtTitleAlert=(TextView)dialog1.findViewById(R.id.txtTitleAlert);
	    TextView txtMsgAlert=(TextView)dialog1.findViewById(R.id.txtMsgAlert);
	    TextView txtKwhAlert=(TextView)dialog1.findViewById(R.id.txtKwhAlert);
	    txtKwhAlert.setVisibility(View.GONE);
	    if(readingType.equals(BILLING))
	    {
	    	btnOkAlert.setText("Continue Billing");
	    	txtKwhAlert.setVisibility(View.VISIBLE);
	    }else if(readingType.equals(DISCONNECT_METER)) {
	    	txtMsgAlert.setTextColor(Color.RED);
	    }else if(readingType.equals(RECONNECT_METER)) {
	    	txtMsgAlert.setTextColor(Color.parseColor(getResources().getString(R.string.Green)));
	    }
	    txtTitleAlert.setText(readingType);
	    txtMsgAlert.setText(msg);
	    
	    
	    btnOkAlert.setOnClickListener(new OnClickListener()
	    {
	        @Override
	        public void onClick(View v) 
	        {
	        	dialog1.dismiss();
	        	if(readingType.equals(BILLING))
	    	    {
	        		DecimalFormat format = new DecimalFormat("0");
	        		try {
		        		RfLibMain.setMeterReading(String.format(format.format(Double.parseDouble(msg))));
		        		startActivity(new Intent(MasterActivity.this, RfLibMain.activityOut));
			    		MasterActivity.this.finish();
					} catch (Exception e) {
						AmrMethods.customToast(context, "Libray not connected with the billing app/ \n"+e.getMessage());
					}
	    	    }else {
	    	    	startActivity( new Intent(MasterActivity.this, RfLibMain.activityHome));
	    	    	MasterActivity.this.finish();
	    	    }
	        }
	    });
	    btnCancelAlert.setOnClickListener(new OnClickListener(){@Override public void onClick(View v){dialog1.dismiss();}}); dialog1.show();
		}

public boolean startCommunication() {
	 resetUI();
	  if(!UsbRecieverService.usbConnected) {
		  AmrMethods.customToast(context, "Please connect to USB");
			return false;
		}
	UsbManager manager = (UsbManager) context.getSystemService(Context.USB_SERVICE);
	HashMap<String, UsbDevice> devices = manager.getDeviceList();
	
	if(devices.size()<=0) //CHECKING DEVICE CONNECTION TO THE PHONE
	{
   AmrMethods.customToast(context, "No USB Devices Detected");
   return false; //IF THERE IS NO DEVICE REUTRN BACK
	}
	if (devices.keySet().iterator().hasNext())  //IF DEVICE CONNECTED
	{
		return true;
	}
	return false;
}
}
