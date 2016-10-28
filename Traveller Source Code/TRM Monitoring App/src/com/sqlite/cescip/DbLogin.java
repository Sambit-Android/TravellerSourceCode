package com.sqlite.cescip;

import java.io.File;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;

public class DbLogin {
	public static final int DATABASE_VERSION = 10;
	static File dir = new File(Environment.getExternalStorageDirectory() + "");
	private static String DATABASE_NAME = dir+ "/CESC_IP/LOGIN.db";
	public static final String TABLE_NAME = "LOGIN";
	public static final String  COLUMN_ID = "ID";
	public static final String  COLUMN_USERNAME = "USERNAME";
	public static final String COLUMN_PASSWORD = "PASSWORD";
	public static final String COLUMN_SDOCODE = "SDOCODE";
	//public static final String COLUMN_NAME = "NAME";
	//public static final String COLUMN_MRCODE = "MRCODE";

	private DatabaseHelper myDbHelper;
	private SQLiteDatabase myDbb;
	private final Context context;
	private static class DatabaseHelper extends SQLiteOpenHelper {

		DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {

			db.execSQL("create table "+TABLE_NAME+" ("+COLUMN_ID+" integer primary key, "+COLUMN_USERNAME+" text not null,"+COLUMN_PASSWORD+" text,"+COLUMN_SDOCODE+" text)");
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
			onCreate(db);
		}
	}
	public DbLogin(Context ctx) {
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

	public long register(String userName,String password,String sdoCode)
	{
	ContentValues initialValues = new ContentValues();
	initialValues.put(COLUMN_USERNAME, userName);
	initialValues.put(COLUMN_PASSWORD, password);
	initialValues.put(COLUMN_SDOCODE, sdoCode);
	 
	return myDbb.insert(TABLE_NAME, null, initialValues);
	}
	public boolean loginCheck(String username, String password, String sdoCode) throws SQLException
	{
	Cursor mCursor = myDbb.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE "+COLUMN_USERNAME+"=? AND "+COLUMN_PASSWORD+"=? AND "+COLUMN_SDOCODE+"=?", new String[]{username,password,sdoCode});
	if (mCursor != null) {
	if(mCursor.getCount() > 0)
	{
	return true;
	}
	}
	return false;
	}
	/*public String getMrReaderInfo(String userName, String sdoCode) {
		String[] columns = new String[] {COLUMN_USERNAME, COLUMN_SDOCODE,COLUMN_NAME, COLUMN_MRCODE};
		Cursor c = myDbb.query(TABLE_NAME, columns, COLUMN_USERNAME+"='"+userName+"' AND "+COLUMN_SDOCODE+"='"+sdoCode+"'", null, null, null, null);
     int name = 0,code = 0;
		if (c.moveToFirst()) {
			 name = c.getColumnIndex(COLUMN_NAME);
			 code = c.getColumnIndex(COLUMN_MRCODE);
		}
		String mrName = c.getString(name);
		String mrCode = c.getString(code);
		return mrName+"@@@"+mrCode;
	}*/
}
