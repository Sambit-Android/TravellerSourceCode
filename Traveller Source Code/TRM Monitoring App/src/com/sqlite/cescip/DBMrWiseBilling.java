package com.sqlite.cescip;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;

import com.bcits.utils.MrWiseBillingProgressGetterSetter;

public class DBMrWiseBilling {

	public static final int DATABASE_VERSION = 9;
	static File dir = new File(Environment.getExternalStorageDirectory() + "");
	private static String DATABASE_NAME = dir
			+ "/MR-LOCATOR/MRWISE_BILLING_DETAILS.db";
	public static final String TABLE_NAME = "MRWISE_BILLING_DETAILS";
	public static final String TABLE_NAME2 = "MRCODEWISE_RRNUMBERLIST";
	public static final String COLUMN_ID = "ID";
	public static final String SECTION = "SECTION";
	public static final String SITECODE = "SITECODE";
	public static final String MRCODE = "MRCODE";
	public static final String TOBEBILLED = "TOBEBILLED";
	public static final String BILLED = "BILLED";
	public static final String UNBILLED = "UNBILLED";
	public static final String TOBEBILLED_DAY = "TOBEBILLED_DAY";
	public static final String BILLED_DAY = "BILLED_DAY";
	public static final String UNBILLED_DAY = "UNBILLED_DAY";
	public static final String MR_NAME = "MR_NAME";
	public static final String MR_MOBILE_NUMBER = "MR_MOBILE_NUMBER";
	public static final String EXTRA1 = "EXTRA1";
	public static final String EXTRA2 = "EXTRA2";
	public static final String EXTRA3 = "EXTRA3";
	public static final String EXTRA4 = "EXTRA4";
	
	
	
	
	

	public static final String RDNGDATE = "RDNGDATE";
	public static final String METERSTATUS = "METERSTATUS";
	public static final String CONSUMERADDRESS = "CONSUMERADDRESS";
	public static final String CONSUMERADDRESS1 = "CONSUMERADDRESS1";
	public static final String SOCODE = "SOCODE";
	public static final String FEEDERCODE = "FEEDERCODE";
	public static final String TARIFFDESC = "TARIFFDESC";
	public static final String RDNGMONTH = "RDNGMONTH";
	public static final String TCCODE = "TCCODE";
	public static final String BILLNO = "BILLNO";
	public static final String RR_NO = "RR_NO";
	public static final String NAME = "NAME";
	public static final String MOBILENO = "MOBILENO";
	public static final String LONGITUDE = "LONGITUDE";
	public static final String LATITUDE = "LATITUDE";
	public static final String RREXTRA1 = "RREXTRA1";
	public static final String RREXTRA2 = "RREXTRA2";
	public static final String RREXTRA3 = "RREXTRA3";
	public static final String RREXTRA4 = "RREXTRA4";
	public static final String RREXTRA5 = "RREXTRA5";
	public static final String RREXTRA6 = "RREXTRA6";
	public static final String RREXTRA7 = "RREXTRA7";
	public static final String RREXTRA8 = "RREXTRA8";
	public static final String BILLDATE = "BILLDATE";
	public static final String NETAMTDUE = "NETAMTDUE";


	private DatabaseHelper myDbHelper;
	private SQLiteDatabase myDb;
	private final Context context;

	private static class DatabaseHelper extends SQLiteOpenHelper {

		DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		
		@Override
		public void onCreate(SQLiteDatabase db) {
			try {
				db.execSQL("create table " + TABLE_NAME + " (" + COLUMN_ID
						+ " integer primary key, " + SECTION + " text, "
						+ SITECODE + " text, " + MRCODE + " text, "
						+ TOBEBILLED + " text, " + BILLED + " text, "
						+ UNBILLED + " text, " + TOBEBILLED_DAY + " text, "
						+ BILLED_DAY + " text, " + UNBILLED_DAY + " text, "
						+ MR_NAME + " text, " + MR_MOBILE_NUMBER + " text"
						+ ", " + EXTRA1 + " text, " + EXTRA2 + " text, "
						+ EXTRA3 + " text, " + EXTRA4 + " text)");
				db.execSQL("create table " + TABLE_NAME2 + " (" + COLUMN_ID
						+ " integer primary key, " + RR_NO + " text, " 
						+ NAME	+ " text, " + MOBILENO + " text, "
						+ LONGITUDE	+ " text, " + LATITUDE + " text, " 
						+ RDNGDATE+" text, "+METERSTATUS+" text,"+ CONSUMERADDRESS+" text,"
						+ CONSUMERADDRESS1+" text,"+SOCODE+" text,"+FEEDERCODE+" text,"
						+ TARIFFDESC+" text,"+ ""+RDNGMONTH+" text,"+TCCODE+" text,"
						+ BILLNO+" text,"+ ""+RREXTRA1+" text,"+RREXTRA2+" text,"
						+ RREXTRA3+" text,"+RREXTRA4+" text,"+RREXTRA5+" text,"+BILLDATE+" text,"+NETAMTDUE+" text)");
			} catch (Exception e) {
				e.printStackTrace();
			}
			/*
			 * try { db.execSQL("CREATE INDEX if not exists statusIndex ON "+
			 * TABLE_NAME + "(" + UNBILLED_DAY + ")");
			 * (Exception e) { e.printStackTrace(); }
			 */

		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME2);
			onCreate(db);
		}
	}

	public DBMrWiseBilling(Context ctx) {
		this.context = ctx;
	}

	public void open() {
		myDbHelper = new DatabaseHelper(context);
		try {
			myDb = myDbHelper.getWritableDatabase();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void close() {
		if (myDbHelper != null) {
			myDbHelper.close();
		}
	}

	/*
	 * public boolean adddivision(String circlename) { ContentValues
	 * initialValues = new ContentValues();
	 * initialValues.put(COLUMN_DIVISION_NAME, circlename);
	 * initialValues.put(COL_MOB_STATUS, "0");
	 * 
	 * myDb.insert(TABLE_NAME, null, initialValues); return true; }
	 */

	public boolean checkDataBase() {
		SQLiteDatabase checkDB = null;
		try {
			checkDB = SQLiteDatabase.openDatabase(DATABASE_NAME, null,
					SQLiteDatabase.OPEN_READONLY);
			checkDB.close();
		} catch (SQLiteException e) {
			// database doesn't exist yet.
		}
		return checkDB != null ? true : false;
	}

	public void insert(String section, String sitecode, String mrcode,
			String tobebilled, String billed, String unbilled,
			String tobebilledday, String billedday, String unbilledday,
			String mrname, String mrmobilenumber) {

		ContentValues contentValues = new ContentValues();
		contentValues.put(SECTION, section);
		contentValues.put(SITECODE, sitecode);
		contentValues.put(MRCODE, mrcode);
		contentValues.put(TOBEBILLED, tobebilled);
		contentValues.put(BILLED, billed);
		contentValues.put(UNBILLED, unbilled);
		contentValues.put(TOBEBILLED_DAY, tobebilledday);
		contentValues.put(BILLED_DAY, billedday);
		contentValues.put(UNBILLED_DAY, unbilledday);
		contentValues.put(MR_NAME, mrname);
		contentValues.put(MR_MOBILE_NUMBER, mrmobilenumber);

		long insert = myDb.insert(TABLE_NAME, null, contentValues);
	}

	public void insertintorrnumberlist(JSONArray arr) {
		
		for(int i =0;i<arr.length();i++){
			try{
			ContentValues contentValues = new ContentValues();
			contentValues.put(RDNGDATE, arr.getJSONObject(i).getString("rdngdate"));
			contentValues.put(METERSTATUS, arr.getJSONObject(i).getString("meterstatus"));
			contentValues.put(CONSUMERADDRESS1, arr.getJSONObject(i).getString("consumeraddress1"));
			contentValues.put(CONSUMERADDRESS, arr.getJSONObject(i).getString("consumeraddress"));
			contentValues.put(SOCODE, arr.getJSONObject(i).getString("socode"));
			contentValues.put(FEEDERCODE, arr.getJSONObject(i).getString("feedercode"));
			
			contentValues.put(TARIFFDESC, arr.getJSONObject(i).getString("tariffdesc"));
			contentValues.put(RDNGMONTH, arr.getJSONObject(i).getString("rdngmonth"));
			contentValues.put(TCCODE, arr.getJSONObject(i).getString("tccode"));
			contentValues.put(BILLNO, arr.getJSONObject(i).getString("billno"));
			contentValues.put(RR_NO, arr.getJSONObject(i).getString("rrno"));
			
			contentValues.put(NAME, arr.getJSONObject(i).getString("consumername"));
			contentValues.put(LONGITUDE, arr.getJSONObject(i).getString("longitude"));
			contentValues.put(LATITUDE, arr.getJSONObject(i).getString("latitude"));
			
			contentValues.put(BILLDATE, arr.getJSONObject(i).getString("billdate"));
			contentValues.put(NETAMTDUE, arr.getJSONObject(i).getString("netamtdue"));
			
			long insert = myDb.insert(TABLE_NAME2, null, contentValues);

			
			}catch(Exception e){
				e.printStackTrace();
			}
		}		
	}

	public JSONArray getmrcodeandsdocodewiserrnumberlistdatabilledmonth(String count) {

		String[] columns = new String[] { RR_NO, NAME, TARIFFDESC, LONGITUDE,
				LATITUDE , CONSUMERADDRESS1 , CONSUMERADDRESS , BILLNO , RDNGDATE ,MOBILENO , METERSTATUS , BILLDATE , NETAMTDUE};
		Cursor c = myDb.query(TABLE_NAME2, columns, BILLNO + " not in ('0','1')", null, null, null,	RDNGDATE+","+BILLNO,count);

		JSONArray comList = new JSONArray();
		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			JSONObject obj = new JSONObject();
			try {
				obj.put("rr_no", c.getString(c.getColumnIndex(RR_NO)));
				obj.put("name", c.getString(c.getColumnIndex(NAME)));
				obj.put("tariffdesc", c.getString(c.getColumnIndex(TARIFFDESC)));

				obj.put("longitude", c.getString(c.getColumnIndex(LONGITUDE)));
				obj.put("latitude", c.getString(c.getColumnIndex(LATITUDE)));
				
				obj.put("address1", c.getString(c.getColumnIndex(CONSUMERADDRESS)));
				obj.put("address2", c.getString(c.getColumnIndex(CONSUMERADDRESS1)));
				
				obj.put("billno", c.getString(c.getColumnIndex(BILLNO)));
				obj.put("rdngdate", c.getString(c.getColumnIndex(RDNGDATE)));
				
				obj.put("mobile", c.getString(c.getColumnIndex(MOBILENO))==null?"":c.getString(c.getColumnIndex(MOBILENO)));
				obj.put("meterstatus", c.getString(c.getColumnIndex(METERSTATUS)));
				
				obj.put("billdate", c.getString(c.getColumnIndex(BILLDATE))==null?"-":c.getString(c.getColumnIndex(BILLDATE)));
				obj.put("netamtdue", c.getString(c.getColumnIndex(NETAMTDUE)));

			} catch (Exception e) {
				e.printStackTrace();
			}

			comList.put(obj);

		}
		return comList;
	}
	
	public JSONArray getmrcodeandsdocodewiserrnumberlistdatabilledday(String count) {

		String[] columns = new String[] { RR_NO, NAME, TARIFFDESC, LONGITUDE,
				LATITUDE , CONSUMERADDRESS1 , CONSUMERADDRESS, BILLNO , RDNGDATE , MOBILENO , METERSTATUS, BILLDATE , NETAMTDUE};
		Cursor c = myDb.query(TABLE_NAME2, columns, BILLNO + " not in ('0','1')", null, null, null,
				RDNGDATE+","+BILLNO,count);
		
		JSONArray comList = new JSONArray();
		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			JSONObject obj = new JSONObject();
			try {
				obj.put("rr_no", c.getString(c.getColumnIndex(RR_NO)));
				obj.put("name", c.getString(c.getColumnIndex(NAME)));
				obj.put("tariffdesc", c.getString(c.getColumnIndex(TARIFFDESC)));

				obj.put("longitude", c.getString(c.getColumnIndex(LONGITUDE)));
				obj.put("latitude", c.getString(c.getColumnIndex(LATITUDE)));
				
				obj.put("address1", c.getString(c.getColumnIndex(CONSUMERADDRESS)));
				obj.put("address2", c.getString(c.getColumnIndex(CONSUMERADDRESS1)));
				
				obj.put("billno", c.getString(c.getColumnIndex(BILLNO)));
				obj.put("rdngdate", c.getString(c.getColumnIndex(RDNGDATE)));
				
				obj.put("mobile", c.getString(c.getColumnIndex(MOBILENO))==null?"":c.getString(c.getColumnIndex(MOBILENO)));
				obj.put("meterstatus", c.getString(c.getColumnIndex(METERSTATUS)));
				
				obj.put("billdate", c.getString(c.getColumnIndex(BILLDATE))==null?"-":c.getString(c.getColumnIndex(BILLDATE)));
				obj.put("netamtdue", c.getString(c.getColumnIndex(NETAMTDUE)));

			} catch (Exception e) {
				e.printStackTrace();
			}

			comList.put(obj);

		}
		return comList;
	}
	
	public JSONArray getmrcodeandsdocodewiserrnumberlistdataunbilledmonth(String count) {

		String[] columns = new String[] { RR_NO, NAME, TARIFFDESC, LONGITUDE,
				LATITUDE , CONSUMERADDRESS1 , CONSUMERADDRESS, BILLNO , RDNGDATE , MOBILENO ,METERSTATUS, BILLDATE , NETAMTDUE};
		Cursor c = myDb.query(TABLE_NAME2, columns, BILLNO + " in ('0','1')", null, null, null,
				RDNGDATE+","+BILLNO,count);

		JSONArray comList = new JSONArray();
		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			JSONObject obj = new JSONObject();
			try {
				obj.put("rr_no", c.getString(c.getColumnIndex(RR_NO)));
				obj.put("name", c.getString(c.getColumnIndex(NAME)));
				obj.put("tariffdesc", c.getString(c.getColumnIndex(TARIFFDESC)));

				obj.put("longitude", c.getString(c.getColumnIndex(LONGITUDE)));
				obj.put("latitude", c.getString(c.getColumnIndex(LATITUDE)));
				
				obj.put("address1", c.getString(c.getColumnIndex(CONSUMERADDRESS)));
				obj.put("address2", c.getString(c.getColumnIndex(CONSUMERADDRESS1)));
				
				obj.put("billno", c.getString(c.getColumnIndex(BILLNO)));
				obj.put("rdngdate", c.getString(c.getColumnIndex(RDNGDATE)));
				
				obj.put("mobile", c.getString(c.getColumnIndex(MOBILENO))==null?"":c.getString(c.getColumnIndex(MOBILENO)));
				obj.put("meterstatus", c.getString(c.getColumnIndex(METERSTATUS)));
				
				obj.put("billdate", c.getString(c.getColumnIndex(BILLDATE))==null?"-":c.getString(c.getColumnIndex(BILLDATE)));
				obj.put("netamtdue", c.getString(c.getColumnIndex(NETAMTDUE)));

			} catch (Exception e) {
				e.printStackTrace();
			}

			comList.put(obj);

		}
		return comList;
	}
	
	public JSONArray getmrcodeandsdocodewiserrnumberlistdataunbilledday(String count) {

		String[] columns = new String[] { RR_NO, NAME, TARIFFDESC, LONGITUDE,
				LATITUDE , CONSUMERADDRESS1 , CONSUMERADDRESS , BILLNO , RDNGDATE ,MOBILENO ,METERSTATUS, BILLDATE , NETAMTDUE};
		Cursor c = myDb.query(TABLE_NAME2, columns, BILLNO + " in ('0','1')", null, null, null,
				RDNGDATE+","+BILLNO,count);

		JSONArray comList = new JSONArray();
		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			JSONObject obj = new JSONObject();
			try {
				obj.put("rr_no", c.getString(c.getColumnIndex(RR_NO)));
				obj.put("name", c.getString(c.getColumnIndex(NAME)));
				obj.put("tariffdesc", c.getString(c.getColumnIndex(TARIFFDESC)));

				obj.put("longitude", c.getString(c.getColumnIndex(LONGITUDE)));
				obj.put("latitude", c.getString(c.getColumnIndex(LATITUDE)));
				
				obj.put("address1", c.getString(c.getColumnIndex(CONSUMERADDRESS)));
				obj.put("address2", c.getString(c.getColumnIndex(CONSUMERADDRESS1)));
				
				obj.put("billno", c.getString(c.getColumnIndex(BILLNO)));
				obj.put("rdngdate", c.getString(c.getColumnIndex(RDNGDATE)));
				
				obj.put("mobile", c.getString(c.getColumnIndex(MOBILENO))==null?"":c.getString(c.getColumnIndex(MOBILENO)));
				obj.put("meterstatus", c.getString(c.getColumnIndex(METERSTATUS)));
				
				obj.put("billdate", c.getString(c.getColumnIndex(BILLDATE))==null?"-":c.getString(c.getColumnIndex(BILLDATE)));
				obj.put("netamtdue", c.getString(c.getColumnIndex(NETAMTDUE)));

			} catch (Exception e) {
				e.printStackTrace();
			}

			comList.put(obj);

		}
		return comList;
	}
	
	
	
	/*public JSONArray mrwise_billed_day() {

		String[] columns = new String[] { RR_NO, NAME, TARIFFDESC, LONGITUDE,
				LATITUDE , CONSUMERADDRESS1 , CONSUMERADDRESS};
		Cursor c = myDb.query(TABLE_NAME2, columns, BILLNO + " in ('0','1')", null, null, null,
				null);

		JSONArray comList = new JSONArray();
		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			JSONObject obj = new JSONObject();
			try {
				obj.put("rr_no", c.getString(c.getColumnIndex(RR_NO)));
				obj.put("name", c.getString(c.getColumnIndex(NAME)));
				obj.put("tariffdesc", c.getString(c.getColumnIndex(TARIFFDESC)));

				obj.put("longitude", c.getString(c.getColumnIndex(LONGITUDE)));
				obj.put("latitude", c.getString(c.getColumnIndex(LATITUDE)));
				
				obj.put("address1", c.getString(c.getColumnIndex(CONSUMERADDRESS)));
				obj.put("address2", c.getString(c.getColumnIndex(CONSUMERADDRESS1)));

			} catch (Exception e) {
				e.printStackTrace();
			}

			comList.put(obj);

		}
		return comList;
	}
	
	public JSONArray mrwise_unbilled_day() {

		String[] columns = new String[] { RR_NO, NAME, TARIFFDESC, LONGITUDE,
				LATITUDE , CONSUMERADDRESS1 , CONSUMERADDRESS};
		Cursor c = myDb.query(TABLE_NAME2, columns, BILLNO + " in ('0','1')", null, null, null,
				null);

		JSONArray comList = new JSONArray();
		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			JSONObject obj = new JSONObject();
			try {
				obj.put("rr_no", c.getString(c.getColumnIndex(RR_NO)));
				obj.put("name", c.getString(c.getColumnIndex(NAME)));
				obj.put("tariffdesc", c.getString(c.getColumnIndex(TARIFFDESC)));

				obj.put("longitude", c.getString(c.getColumnIndex(LONGITUDE)));
				obj.put("latitude", c.getString(c.getColumnIndex(LATITUDE)));
				
				obj.put("address1", c.getString(c.getColumnIndex(CONSUMERADDRESS)));
				obj.put("address2", c.getString(c.getColumnIndex(CONSUMERADDRESS1)));

			} catch (Exception e) {
				e.printStackTrace();
			}

			comList.put(obj);

		}
		return comList;
	}*/

	public ArrayList<MrWiseBillingProgressGetterSetter> getAllBillingData() {

		String[] columns = new String[] { SECTION, SITECODE, MRCODE,
				TOBEBILLED, BILLED, UNBILLED, TOBEBILLED_DAY, BILLED_DAY,
				UNBILLED_DAY ,MR_NAME,MR_MOBILE_NUMBER};
		Cursor c = myDb.query(TABLE_NAME, columns, null, null, null, null,
				MRCODE);

		ArrayList<MrWiseBillingProgressGetterSetter> comList = new ArrayList<MrWiseBillingProgressGetterSetter>();
		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {

			MrWiseBillingProgressGetterSetter comp = new MrWiseBillingProgressGetterSetter();
			comp.setSection(c.getString(c.getColumnIndex(SECTION)));
			comp.setSitecode(c.getString(c.getColumnIndex(SITECODE)));
			comp.setMrcode(c.getString(c.getColumnIndex(MRCODE)));
			comp.setTobebilled(c.getString(c.getColumnIndex(TOBEBILLED)));
			comp.setBilled(c.getString(c.getColumnIndex(BILLED)));
			comp.setUnbilled(c.getString(c.getColumnIndex(UNBILLED)));
			comp.setTobebilledday(c.getString(c.getColumnIndex(TOBEBILLED_DAY)));
			comp.setBilledday(c.getString(c.getColumnIndex(BILLED_DAY)));
			comp.setUnbilledday(c.getString(c.getColumnIndex(UNBILLED_DAY)));
			
			comp.setMrname(c.getString(c.getColumnIndex(MR_NAME)));
			
			if (c.getString(c.getColumnIndex(MR_MOBILE_NUMBER)).trim().length()<=5) {
				comp.setMrmobilenumber("NA");
			}else{
				comp.setMrmobilenumber(c.getString(c.getColumnIndex(MR_MOBILE_NUMBER)));
			}
			comList.add(comp);
		}
		return comList;
	}
	
	public JSONObject getMrDetailsDataFromMrCode(String mrcode) {
		

		String[] columns = new String[] { MR_NAME, MR_MOBILE_NUMBER };
		Cursor c = myDb
				.query(TABLE_NAME, columns, MRCODE + "='" + mrcode + "'", null, null, null, null);
		JSONObject obj = new JSONObject();
		String result = "";
		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			
			try {
				obj.put("name", c.getString(c.getColumnIndex(MR_NAME)));
				obj.put("mobile", c.getString(c.getColumnIndex(MR_MOBILE_NUMBER)));
				

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return obj;
	}

	public JSONArray getMrWiseReportData() {

		String[] columns = new String[] { SECTION, SITECODE, MRCODE,
				TOBEBILLED, BILLED, UNBILLED, TOBEBILLED_DAY, BILLED_DAY,
				UNBILLED_DAY, MR_NAME, MR_MOBILE_NUMBER };
		Cursor c = myDb
				.query(TABLE_NAME, columns, null, null, null, null, MRCODE);

		JSONArray comList = new JSONArray();
		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			JSONObject obj = new JSONObject();
			try {
				obj.put("mr_code", c.getString(c.getColumnIndex(MRCODE)));
				obj.put("mr_name", c.getString(c.getColumnIndex(MR_NAME)));
				obj.put("mr_mobile",
						c.getString(c.getColumnIndex(MR_MOBILE_NUMBER))==null?"":c.getString(c.getColumnIndex(MR_MOBILE_NUMBER)));

				obj.put("section", c.getString(c.getColumnIndex(SECTION)));
				obj.put("sitecode", c.getString(c.getColumnIndex(SITECODE)));
				obj.put("tobebilled", c.getString(c.getColumnIndex(TOBEBILLED)));
				obj.put("billed", c.getString(c.getColumnIndex(BILLED)));
				obj.put("unbilled", c.getString(c.getColumnIndex(UNBILLED)));
				obj.put("tobebilledday",
						c.getString(c.getColumnIndex(TOBEBILLED_DAY)));
				obj.put("billedday", c.getString(c.getColumnIndex(BILLED_DAY)));
				obj.put("unbilledday",
						c.getString(c.getColumnIndex(UNBILLED_DAY)));

			} catch (Exception e) {
				e.printStackTrace();
			}

			/*
			 * obj.put(name, value); obj.put(name, value); obj.put(name, value);
			 * obj.put(name, value); obj.put(name, value); obj.put(name, value);
			 * obj.put(name, value); obj.put(name, value);
			 * MrWiseBillingProgressGetterSetter comp = new
			 * MrWiseBillingProgressGetterSetter();
			 * comp.setSection(c.getString(c.getColumnIndex(SECTION)));
			 * comp.setSitecode(c.getString(c.getColumnIndex(SITECODE)));
			 * comp.setMrcode(c.getString(c.getColumnIndex(MRCODE)));
			 * comp.setTobebilled(c.getString(c.getColumnIndex(TOBEBILLED)));
			 * comp.setBilled(c.getString(c.getColumnIndex(BILLED)));
			 * comp.setUnbilled(c.getString(c.getColumnIndex(UNBILLED)));
			 * comp.setTobebilledday
			 * (c.getString(c.getColumnIndex(TOBEBILLED_DAY)));
			 * comp.setBilledday(c.getString(c.getColumnIndex(BILLED_DAY)));
			 * comp.setUnbilledday(c.getString(c.getColumnIndex(UNBILLED_DAY)));
			 * comp.setMrname(c.getString(c.getColumnIndex(MR_NAME)));
			 * comp.setMrmobilenumber
			 * (c.getString(c.getColumnIndex(MR_MOBILE_NUMBER)));
			 * 
			 * comList.put(comp);
			 */

			comList.put(obj);

		}
		return comList;
	}

	public JSONObject getMrCodeWiseDetails(String mrcode) {

		String[] columns = new String[] { SECTION, SITECODE, MRCODE,
				TOBEBILLED, BILLED, UNBILLED, TOBEBILLED_DAY, BILLED_DAY,
				UNBILLED_DAY, MR_NAME, MR_MOBILE_NUMBER };
		Cursor c = myDb.query(TABLE_NAME, columns,
				MRCODE + "='" + mrcode + "'", null, null, null, null);

		JSONObject obj = new JSONObject();

		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {

			try {
				obj.put("mr_code", c.getString(c.getColumnIndex(MRCODE)));
				obj.put("mr_name", c.getString(c.getColumnIndex(MR_NAME)));
				obj.put("mr_mobile",
						c.getString(c.getColumnIndex(MR_MOBILE_NUMBER)));

				obj.put("section", c.getString(c.getColumnIndex(SECTION)));
				obj.put("sitecode", c.getString(c.getColumnIndex(SITECODE)));
				obj.put("tobebilled", c.getString(c.getColumnIndex(TOBEBILLED)));
				obj.put("billed", c.getString(c.getColumnIndex(BILLED)));
				obj.put("unbilled", c.getString(c.getColumnIndex(UNBILLED)));
				obj.put("tobebilledday",
						c.getString(c.getColumnIndex(TOBEBILLED_DAY)));
				obj.put("billedday", c.getString(c.getColumnIndex(BILLED_DAY)));
				obj.put("unbilledday",
						c.getString(c.getColumnIndex(UNBILLED_DAY)));

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return obj;
	}

	public void deleteDisconnectionTable() {
		myDb.delete(TABLE_NAME, null, null);
	}

	public int deleteAll() {
		return myDb.delete(TABLE_NAME, null, null);
	}
	public int deleteAllRR() {
		return myDb.delete(TABLE_NAME2, null, null);
	}

	public boolean deleteAllNames() {
		int doneDelete = myDb.delete(DATABASE_NAME, null, null);
		return doneDelete > 0;
	}
	@SuppressWarnings("deprecation")
    public void startBooster() {
        try {
            myDb.execSQL("PRAGMA synchronous=OFF");
            myDb.setLockingEnabled(false);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @SuppressWarnings("deprecation")
    public void stopBooster() {
        try {
            myDb.execSQL("PRAGMA synchronous=OFF");
            myDb.setLockingEnabled(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    } 

}
