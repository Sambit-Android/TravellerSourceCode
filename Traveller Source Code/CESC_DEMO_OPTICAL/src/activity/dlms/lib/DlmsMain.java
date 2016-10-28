package activity.dlms.lib;

import android.content.Context;
import android.content.Intent;

public class DlmsMain {
	
	static Class<?> activityOut;
	public static String meterReading,meterNumber;
	 
	
	public static String getMeterReading() {
		return meterReading;
	}
	public static void setMeterReading(String meterReading) {
		DlmsMain.meterReading = meterReading;
	}

	public static String getMeterNumber() {
		return meterNumber;
	}
	public static void setMeterNumber(String meterNumber) {
		DlmsMain.meterNumber = meterNumber;
	}


	public static void readMeter(Context activityInput,Class<?> activityOutPutClass) {
		setMeterNumber(null);
		setMeterReading(null);
		activityOut=activityOutPutClass;
		activityInput.startActivity(new Intent(activityInput, MainActivity.class));
	}
}
