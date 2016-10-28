package amr.rf.utils;

import java.io.File;
import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;

public class DbMeterDetails {
	public static final int DATABASE_VERSION = 1;
	static File dir = new File(Environment.getExternalStorageDirectory() + "");
	private static String DATABASE_NAME = dir+ "/RF_METER_DB.db";
	
	public static final String  TABLE_METERS ="TABLE_METERS";
	public static final String  COL_ID = "COL_ID";
	public static final String  COL_METER_NO="COL_METER_NO";
	public static final String  COL_RR_NO="COL_RR_NO";
	public static final String  COL_UUID="COL_UUID";
	public static final String  COL_CHANNEL="COL_CHANNEL";
	public static final String  COL_BILLED_STATUS="COL_BILLED_STATUS";
	
	private DatabaseHelper myDbHelper;
	private SQLiteDatabase myDbb;
	private final Context context;
	private static class DatabaseHelper extends SQLiteOpenHelper {

		DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL("create table "+TABLE_METERS+" ("+COL_ID+" integer primary key, "+COL_METER_NO+" text, "+COL_RR_NO+" text, "+COL_UUID+" text, "+COL_CHANNEL+" text, "+COL_BILLED_STATUS+" text)");
			System.out.println("Table is created==============");
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("DROP TABLE IF EXISTS "+TABLE_METERS);
			onCreate(db);
		}
	}
	public DbMeterDetails(Context ctx) {
		this.context = ctx;
	}
	public void open() {
		myDbHelper = new DatabaseHelper(context);
		myDbb = myDbHelper.getWritableDatabase();

	}
	public void close() {
		if (myDbHelper != null) {
			myDbHelper.close();
		}
	}
	public boolean deleteAllNames() {
		int doneDelete = myDbb.delete(DATABASE_NAME, null, null);
		return doneDelete > 0;
	}

	
	public ArrayList<String> getDbContents() {

		String[] columns = new String[] { COL_METER_NO ,COL_RR_NO,COL_UUID,COL_BILLED_STATUS,COL_CHANNEL};

	Cursor c = myDbb.query(TABLE_METERS, columns, null, null, null, null,null);
//			Cursor c = myDbb.query(TABLE_METERS, columns, COL_BILLED_STATUS+"='0'", null, null, null, null);
		

		ArrayList<String> txnList = new ArrayList<String>();

		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			String meterNo=c.getString(c.getColumnIndex(COL_METER_NO));
			String rrNo=c.getString(c.getColumnIndex(COL_RR_NO));
			String uuid =c.getString(c.getColumnIndex(COL_UUID));
			String channel=c.getString(c.getColumnIndex(COL_CHANNEL));
			txnList.add(meterNo+" - "+rrNo+" - "+uuid+" - "+channel);
		}
		return txnList;
	}
	public boolean saveMetersToDb(String meterNo,String rrNo,String uuid,String channel) {
		ContentValues initialValues = new ContentValues();
		initialValues.put(COL_METER_NO, meterNo);
		initialValues.put(COL_RR_NO, rrNo);
		initialValues.put(COL_UUID, uuid);
		initialValues.put(COL_CHANNEL, channel);
		initialValues.put(COL_BILLED_STATUS, "0");
		
		Cursor c= myDbb.rawQuery("select * from "+TABLE_METERS+" where "+COL_METER_NO+" = '"+meterNo+"'", null);
		
		if(c.moveToNext()){
		}
		else{
		if(myDbb.insert(TABLE_METERS, null, initialValues)>0) {
			return true;
		}
		
		}
		return false;
	}
	
	
	public boolean deleteRow(String meterNo) {
		
		if(myDbb.delete(TABLE_METERS, COL_METER_NO	+ "='" + meterNo + "'", null)>0) {
			return true;
		}
		
//		myDbb.execSQL("DELETE FROM " + TABLE_METERS + " WHERE " + COL_METER_NO	+ "='" + meterNo + "'");
		return false;
	}
	
	public boolean updateToBilled(String meterNo) {
		ContentValues initialValues = new ContentValues();
		initialValues.put(COL_BILLED_STATUS, "1");
		if(myDbb.update(TABLE_METERS, initialValues,  COL_METER_NO	+ "='" + meterNo + "'", null)>0) {
			return true;
		}
		return false;
	}
	
	
	public String getConsumerNo(String uuid) {
		String[] columns = new String[] { COL_METER_NO ,COL_RR_NO,COL_UUID,COL_BILLED_STATUS,COL_CHANNEL};
		Cursor c = myDbb.query(TABLE_METERS, columns, COL_UUID+"='"+uuid+"'", null, null, null, null);
		if (c.moveToNext()) {
			return c.getString(c.getColumnIndex(COL_RR_NO));
		}
		return null;
	}
	
	public String[] getUUIDandChannel(String meterNo) {
		System.out.println("==================METER NO "+meterNo);
		String[] columns = new String[] { COL_METER_NO ,COL_RR_NO,COL_UUID,COL_BILLED_STATUS,COL_CHANNEL};
		Cursor c = myDbb.query(TABLE_METERS, columns, COL_METER_NO+"='"+meterNo+"'", null, null, null, null);
		System.out.println("==================c.getCount() "+c.getCount());
		if (c.moveToNext()) {
			return new String[] {c.getString(c.getColumnIndex(COL_UUID)),c.getString(c.getColumnIndex(COL_CHANNEL))};
		}
		return null;
	}
}
