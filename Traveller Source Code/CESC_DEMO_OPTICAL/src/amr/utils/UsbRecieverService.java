package amr.utils;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.usb.UsbManager;
import android.os.IBinder;

public class UsbRecieverService extends Service{
 
	public static final String USB_ENABLED_INTENT = "usb.enabled.intent.radio.key";
	Intent local = new Intent(); // BROADCASTING FOR UI CHANGE
	public static boolean usbConnected=false;

	@Override
	public void onCreate() {
		super.onCreate();
		local.setAction(USB_ENABLED_INTENT);
		registerUsbReceiver();
	}
	
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	private	BroadcastReceiver mUsbReceiver = new BroadcastReceiver() {
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			if (UsbManager.ACTION_USB_DEVICE_ATTACHED.equals(action)) {
				AmrMethods.customToast(getApplicationContext(), "Connected USB");
				local.putExtra("USB_CONNECTED", true);
				UsbRecieverService.this.sendBroadcast(local);
				usbConnected=true;
				
			} else if (UsbManager.ACTION_USB_DEVICE_DETACHED.equals(action)) {
				AmrMethods.customToast(getApplicationContext(), "USB Disconnected");
				local.putExtra("USB_CONNECTED", false);
				UsbRecieverService.this.sendBroadcast(local);
				usbConnected=false;
			}
		}
	};
	
	private void registerUsbReceiver() {//REGISTER USB RECEIVER
		IntentFilter filter = new IntentFilter();  
		filter.addAction(UsbManager.ACTION_USB_DEVICE_ATTACHED);
		filter.addAction(UsbManager.ACTION_USB_DEVICE_DETACHED);
		registerReceiver(mUsbReceiver, filter);
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		try {
			unregisterReceiver(mUsbReceiver);	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
