package amr.rf.catcher;

import org.json.JSONArray;
import org.json.JSONObject;

import amr.rf.utils.AmrMethods;
import amr.rf.utils.DbMeterDetails;
import android.content.Context;
import android.content.Intent;

public class RfLibMain {
	public static Class<?> activityOut;   //AFTER READING OUT ACTIVITY
	public static Class<?> activityIn;    // FROM ACTIVITY
	public static Class<?> activityHome;  // FOR DISCONNECTION AND RECONNECTION (AFTER SUCCESS WILL GO TO APP HOME SCREEN)
	public static String meterNumber="";
	public static String meterReading="";
	public static String arrears="";
	
	public static String getArrears() {
		return arrears;
	}
	public static void setArrears(String arrears) {
		RfLibMain.arrears = arrears;
	}

	public static boolean isGroupBilling =false;
	
	
	public static String getMeterNumber() {
		return meterNumber;
	}
	public static void setMeterNumber(String meterNumber) {
		RfLibMain.meterNumber = meterNumber;
	}
	
	public static String getMeterReading() {
		return meterReading;
	}
	public static void setMeterReading(String meterReading) {
		RfLibMain.meterReading = meterReading;
	}
//TODO
	public static void readMeter(Context activityInput,Class<?> activityOutPutClass,Class<?> activityMyHome,String meterNo,String arrears,boolean isGroupBill) {
		int meterChannel=1;
		activityOut=activityOutPutClass;
		activityIn=activityInput.getClass();
		activityHome=activityMyHome;
		isGroupBilling=isGroupBill;
		
		if(isGroupBilling) { // GROUP BILLLING
			activityInput.startActivity(new Intent(activityInput, GroupBilling.class));
			return;
		}
		
		// SINGLE BILLING
		DbMeterDetails dbMeterDetails = new DbMeterDetails(activityInput);
		dbMeterDetails.open();
		String [] meterData= dbMeterDetails.getUUIDandChannel(meterNo.trim());
		if(null==meterData) {
			AmrMethods.customToast(activityInput, "No consumer found for RF reading");
			return;
		}
		
		String uuid =meterData[0];
		String channel=meterData[1];
		try {
			meterChannel=Integer.parseInt(channel);
		} catch (Exception e) {
			e.printStackTrace();
			AmrMethods.customToast(activityInput, "INVALID CHANNEL NUMBER OF METER = "+ channel);
			return;
		}
		setMeterNumber(meterNo);
		setArrears(arrears);
		SingleBilling.METER_ADDRESS=uuid;
		SingleBilling.METER_CHANNEL=meterChannel;
		setMeterReading("");
		activityInput.startActivity(new Intent(activityInput, SingleBilling.class));
		
	}
	
	public static boolean removeMeterNoFromTemp(Context context,String meterNo) {
		try {
			String savedData=AmrMethods.getDefaults(AmrMethods.KEY_SEARCHED_DATA, context);
			if(savedData!=null) {
				JSONArray array = new JSONArray(savedData);
				JSONArray newArray= new JSONArray();
				for (int i = 0; i < array.length(); i++) {
					JSONObject obj= array.getJSONObject(i);
					if(!obj.getString(GroupBilling.KEY_METER_NO).trim().equalsIgnoreCase(meterNo.trim())) {
						newArray.put(obj);
					}
				}
				AmrMethods.setDefaults(AmrMethods.KEY_SEARCHED_DATA, newArray.toString(), context);
				return true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
		
}
