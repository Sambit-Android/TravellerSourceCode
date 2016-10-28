package bcits.rf.catcher;

import amr.utils.AmrMethods;
import android.content.Context;
import android.content.Intent;

public class RfLibMain {
	public static Class<?> activityOut;   //AFTER READING OUT ACTIVITY
	public static Class<?> activityIn;    // FROM ACTIVITY
	public static Class<?> activityHome;  // FOR DISCONNECTION AND RECONNECTION (AFTER SUCCESS WILL GO TO APP HOME SCREEN)
	public static String meterReading="";
	
	public static boolean isGroupBilling =false;
	
	public static String getMeterReading() {
		return meterReading;
	}
	public static void setMeterReading(String meterReading) {
		RfLibMain.meterReading = meterReading;
	}

	public static void readMeter(Context activityInput,Class<?> activityOutPutClass,Class<?> activityMyHome,String meterInfo,String channel,boolean isGroupBill) {
		int meterChannel=1;
		activityOut=activityOutPutClass;
		activityIn=activityInput.getClass();
		activityHome=activityMyHome;
		isGroupBilling=isGroupBill;
		try {
			meterChannel=Integer.parseInt(channel);
		} catch (Exception e) {
			AmrMethods.customToast(activityInput, "INVALID CHANNEL NUMBER OF METER = "+ channel);
			activityInput.startActivity(new Intent(activityInput, activityIn));
			return;
		}
		
		SingleBilling.METER_ADDRESS=meterInfo;
		SingleBilling.METER_CHANNEL=meterChannel;
		setMeterReading("");
		if(isGroupBilling) {
			activityInput.startActivity(new Intent(activityInput, GroupBilling.class));
		}else {
			activityInput.startActivity(new Intent(activityInput, SingleBilling.class));
		}
		
	}
}
