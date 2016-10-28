package com.bcits.recondiscon;

import android.app.Application;

public class UtilDisconnect extends Application {

	
	public static String  mAccountNumber = ""  ;
	public static String  mConsumerName = "" ;
	public static String  mAddress = "" ; 
	public static String  mTariff  = "";  
	public static String  mArrears  = "";
	public static String  mdisdate  = "";
	
	
	public static String getmAccountNumber() {
		return mAccountNumber;
	}
	public static void setmAccountNumber(String mAccountNumber) {
		UtilDisconnect.mAccountNumber = mAccountNumber;
	}
	public static String getmConsumerName() {
		return mConsumerName;
	}
	public static void setmConsumerName(String mConsumerName) {
		UtilDisconnect.mConsumerName = mConsumerName;
	}
	public static String getmAddress() {
		return mAddress;
	}
	public static void setmAddress(String mAddress) {
		UtilDisconnect.mAddress = mAddress;
	}
	public static String getmTariff() {
		return mTariff;
	}
	public static void setmTariff(String mTariff) {
		UtilDisconnect.mTariff = mTariff;
	}
	public static String getmArrears() {
		return mArrears;
	}
	public static void setmArrears(String mArrears) {
		UtilDisconnect.mArrears = mArrears;
	} 
	
	
}
