package com.sqlite.cescip;

import java.io.File;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;

public class DBLocation {

	public static final int DATABASE_VERSION = 18;
	static File dir = new File(Environment.getExternalStorageDirectory() + "");
	private static String DATABASE_NAME = dir+ "/CESC_IP/LOCATION.db";
	public static final String TABLE_NAME = "LOCATION";
	public static final String  COLUMN_ID = "ID";
	public static final String  COLUMN_LATITUDE = "COLUMN_LATITUDE";
	public static final String  COLUMN_LONGITUDE = "COLUMN_LONGITUDE";
	public static final String  COLUMN_LETTER = "COLUMN_LETTER";
	public static final String  COLUMN_AUTORISED = "COLUMN_AUTORISED";
	public static final String  COLUMN_DTC = "COLUMN_DTC";
	public static final String  COL_MOB_STATUS = "status";
	private DatabaseHelper myDbHelper;
	private SQLiteDatabase myDb;
	private final Context context;

	private static class DatabaseHelper extends SQLiteOpenHelper {

		DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL("create table "+TABLE_NAME+" ("+COLUMN_ID+" integer primary key, "+COLUMN_LATITUDE+" text, "+COLUMN_LONGITUDE+" text, "+COLUMN_LETTER+" text, "+COLUMN_AUTORISED+" text, "+COLUMN_DTC+" text, "+COL_MOB_STATUS+" text)");
			try {
				db.execSQL("CREATE INDEX if not exists statusIndex ON "+ TABLE_NAME + "(" + COL_MOB_STATUS + ")");
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
			onCreate(db);
		}
	}

	public DBLocation(Context ctx) {
		this.context = ctx;
	}

	public void open() {
		myDbHelper= new DatabaseHelper(context); 
		try {
			myDb = myDbHelper.getWritableDatabase();
		} catch (Exception e) {
		}

	}

	public void close() {
		if (myDbHelper != null) {
			myDbHelper.close();
		}
	}
	public boolean saveLocations(String latitude,String longitude,String letter,String authorised,String dtc)
	{
	ContentValues initialValues = new ContentValues();
	initialValues.put(COLUMN_LATITUDE, latitude);
	initialValues.put(COLUMN_LONGITUDE, longitude);
	initialValues.put(COLUMN_LETTER, letter);
	initialValues.put(COLUMN_AUTORISED, authorised);
	initialValues.put(COLUMN_DTC, dtc);
	initialValues.put(COL_MOB_STATUS, "0");
	 
	myDb.insert(TABLE_NAME, null, initialValues);
	return true;
	}	
	


	

	public void deleteDisconnectionTable() {
		myDb.delete(TABLE_NAME,null, null);
	}

	public boolean deleteAllNames() {
		int doneDelete = myDb.delete(DATABASE_NAME, null, null);
		return doneDelete > 0;
	}

	public Cursor getOfflineLocations(){
		String[] columns = new String[] {COLUMN_LATITUDE, COLUMN_LONGITUDE,COLUMN_LETTER,COLUMN_AUTORISED,COLUMN_DTC,COL_MOB_STATUS};
		Cursor cursor = myDb.query(TABLE_NAME, columns, COL_MOB_STATUS+"='0'", null, null, null, null);
		return cursor;
	}

		public void emptyTable() {
		myDb.delete(TABLE_NAME,null, null);
	}



}

