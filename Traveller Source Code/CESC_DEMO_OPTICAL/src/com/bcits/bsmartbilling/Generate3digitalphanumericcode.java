package com.bcits.bsmartbilling;





import android.app.Activity;
import android.telephony.TelephonyManager;

public class Generate3digitalphanumericcode extends Activity {
    /** Called when the activity is first created. */
 /*   @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		String device_id = getDeviceID(telephonyManager);
		System.out.println("IMEI No........................." + device_id);
		String authCode=generateAuthCode(device_id);
		System.out.println("alphanumeric code generated from IMEI : "+authCode);
    }
    */
    
    String getDeviceID(TelephonyManager phonyManager) {

		String id = phonyManager.getDeviceId();		
		if (id == null) {
			id = "not available";
		}

		int phoneType = phonyManager.getPhoneType();
 		switch (phoneType) {
		case TelephonyManager.PHONE_TYPE_NONE:
			return "NONE: " + id;

		case TelephonyManager.PHONE_TYPE_GSM:
			return id;

		case TelephonyManager.PHONE_TYPE_CDMA:
			return "CDMA: MEID/ESN=" + id;

			/*
			 * for API Level 11 or above case TelephonyManager.PHONE_TYPE_SIP:
			 * return "SIP";
			 */

		default:
			return "UNKNOWN: ID=" + id;
		}

	}
    
    //Java code to generate 3 digit alphanumeric code from mobile IMEI Number
  	public static String generateAuthCode(String imei) {

  		//imei="3561760406506190";		
  		long decimalNumber = Long.parseLong(imei);			

  		//To convert IMEI Number to alphanumeric code.
  		//We need to convert base10(decimal) to base36
  		String strBaseDigits = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
  		String strTempVal = "";
  		int mod = 0;
  		// String concat is costly, instead we could have use stringbuffer or stringbuilder
  		//but here it wont make much difference.
  		while(decimalNumber!= 0){
  			mod=(int) (decimalNumber % 62);
  			strTempVal=strBaseDigits.substring(mod,mod+1)+strTempVal;
  			decimalNumber=decimalNumber/62;
  		}		
  		strTempVal=strTempVal.substring(2,3)+strTempVal.substring(5,6)+strTempVal.substring(8,9);  		
  		return strTempVal;

  	}
    
    

}