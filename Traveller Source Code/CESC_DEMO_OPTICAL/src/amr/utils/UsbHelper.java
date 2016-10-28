package amr.utils;

import android.content.Context;

import com.physicaloid.lib.Physicaloid;
import com.physicaloid.lib.usb.driver.uart.UartConfig;

public class UsbHelper {
	
	private Context context;
	private Physicaloid mSerial;
	public static String DEVICE_NOT_SUPPORTED="THIS DEVICE IS NOT SUPPORTED";
	public static String USB_NOT_CONNECTED="USB NOT CONNECTED";
	
	
	
	public UsbHelper(Context context,Physicaloid mSerial) {
		this.mSerial=mSerial;
		this.context=context;
	}
	
	
	
	public boolean openUsbSerial() {
		try {
			
			if (null==mSerial) {
				AmrMethods.customToast(context,"mSerial is null");
				return false;
			}
			if (!mSerial.isOpened()) {//CHECKING CONNECTION
				if (!mSerial.open()) {
					return false;
				}
					mSerial.setConfig(new UartConfig(MeterSettings.mBaudrate, MeterSettings.mDataBits,	MeterSettings.mStopBits, MeterSettings.mParity,MeterSettings.dtrOn, MeterSettings.rtsOn));
					Utils.dirctLogcat("openUsbSerial 7"); 
					AmrMethods.customToast(context,"Connected.");
					activity.dlms.lib.MainActivity.isConnected = true;
					return true;
			} else {
				AmrMethods.customToast(context,"Already Activated");
				return true;
			}

		} catch (Exception e) {
			AmrMethods.customToast(context,"Cannot open USB ");
			return false;
		}
	}
	
	public void closeUsbSerial() {
		try {
			if (mSerial != null)
				mSerial.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
