package activity.dlms.lib;

import gurux.dlms.client.sampleclient;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;

import amr.utils.AmrMethods;
import amr.utils.UsbRecieverService;
import amr.utils.Utils;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.view.View;

public class MainActivity extends MasterActivity {

	private String ACTION_USB_PERMISSION = "com.bsmart.amr.USB_PERMISSION";
	HashMap<String, String> hash;
	
	private class ReadMeter extends AsyncTaskParent {
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			linearMeterDetails.setVisibility(View.GONE);
			hash = new HashMap<String, String>(); 
			AmrMethods.customToast(context, "Reading meter ...");
		}
		@Override
		protected Void doInBackground(Void... params) {
			String[] parameters = {"/m=gil", "/sp=/dev/bus/usb", "/s=DLMS", "/a=Low", "/pw="+meterPassword};
				hash = sampleClient.getMeterReading(parameters,MainActivity.this);
			return null;
		}
		
		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			
			try {
				 usbHelper.closeUsbSerial();
				  String error="";
				 
				if(!sampleclient.errorMessage.isEmpty())
				{
					if (sampleclient.errorMessage.contains("Authentication failure")) {
		                 error = "AUTHENTICATION FAILURE.\nPLEASE CHECK THE SELECTED METER.";
		             } else if (sampleclient.errorMessage.contains("Serial port is not open")) {
		                 error = "SERIAL PORT IS NOT OPEN.\nPLEASE CHECK THE CONNECTION.";
		             } else if (sampleclient.errorMessage.contains("Invalid tag")) {
		                 error = "ERROR.\nPLEASE READ AGAIN";
		             } else {
		            	 error=sampleclient.errorMessage;
		             }
					AmrMethods.customAlert(MainActivity.this, "ERROR", error);
					return;
				}
				
				if(hash==null) {
					AmrMethods.customAlert(context, "ERROR", "Reading not completed. Please try again");
					return;
				}
				
				 if(hash.size()<=0) {
						new AlertDialog.Builder(MainActivity.this).setMessage("Reading not completed").setPositiveButton("Ok", new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {
								resetUI();
							}
						}).show();
						return;
	             }else {
	            	linearMeterDetails.setVisibility(View.VISIBLE);
	            	
	            	if(hash.get("meterReading")!=null)
	            	{
	            		txtKWH.setText(hash.get("meterReading"));
	            		DecimalFormat format = new DecimalFormat("0");
	            		DlmsMain.setMeterReading(String.format(format.format(Double.parseDouble(hash.get("meterReading").trim()))));
	            	}
	             }
			} catch (Exception e) {
				AmrMethods.customToast(context,"Something went wrong");
				Utils.dirctLogcat(e.toString());
			}
		}
	}
	
	
  public void onClickConnectAndRead(View v) {
	  resetUI();
	  
	  if(!UsbRecieverService.usbConnected) {
		  AmrMethods.customToast(context, "Please connect to USB");
			return;
		}
	 startCommunication();
	}
	
	public void onClickGoForBilling(View v) {

		if(DlmsMain.getMeterReading()!=null)
		{
			startActivity(new Intent(MainActivity.this, DlmsMain.activityOut));
		    usbHelper.closeUsbSerial();
		    MainActivity.this.finish();
		}
		    else
		    {
		    	AmrMethods.customToast(context, "Values not available. Please try again");
		    }
	}
	
	public void startCommunication() {
		
		UsbManager manager = (UsbManager) context.getSystemService(Context.USB_SERVICE);
		HashMap<String, UsbDevice> devices = manager.getDeviceList();
		
		if(devices.size()<=0) //CHECKING DEVICE CONNECTION TO THE PHONE
		{
         AmrMethods.customToast(context, "Please Connect to USB");
         return; //IF THERE IS NO DEVICE REUTRN BACK
		}
		
		
		Iterator<String> it = devices.keySet().iterator();
		
		if (it.hasNext())  //IF DEVICE CONNECTED
		{
			 String deviceName = it.next();
			 UsbDevice device = devices.get(deviceName);
			 String	VID = Integer.toHexString(device.getVendorId()).toUpperCase(Locale.ENGLISH);
			 String PID = Integer.toHexString(device.getProductId()).toUpperCase(Locale.ENGLISH);
			 
			 Utils.dirctLogcat( VID + " : " + PID + " : " + String.valueOf(manager.hasPermission(device)));		 
	
			 if(!manager.hasPermission(device)) //IF THERE IS NO PERMISSION GRANTED 
			 {
				    PendingIntent mPermissionIntent = PendingIntent.getBroadcast(context, 0, new Intent(ACTION_USB_PERMISSION), 0);
				    IntentFilter filter = new IntentFilter(ACTION_USB_PERMISSION);
				 
				    context.registerReceiver(mUsbReceiver, filter); //REGISTER BROADCAST FOR DETECTING PERMISSION
				    manager.requestPermission(device, mPermissionIntent); //ASK FOR PERMISSION
				    return; //IF PERMISSION GRANTED, REMAINING PART WILL BE EXECUTED IN ON_RECEIVE PART OF BROADCAST
			 }
			 if(usbHelper.openUsbSerial())
			 {
             new ReadMeter().execute();
			 }
			 }
	}
	
	private final BroadcastReceiver mUsbReceiver = new BroadcastReceiver() {

	    public void onReceive(Context context, Intent intent) {
	        String action = intent.getAction();
	        if (ACTION_USB_PERMISSION.equals(action)) {
	            synchronized (this) {
	                UsbDevice device = (UsbDevice)intent.getParcelableExtra(UsbManager.EXTRA_DEVICE);

	                if (intent.getBooleanExtra(UsbManager.EXTRA_PERMISSION_GRANTED, false)) {
	                    if(device != null){
	                      if(usbHelper.openUsbSerial()) {
	                    	  new ReadMeter().execute();
	                      }
	                   }
	                } 
	                else {
	                	AmrMethods.customToast(context, "Permission denied for device");
	                	resetUI();
	                }
	            }
	        }
	    }
	};
}
