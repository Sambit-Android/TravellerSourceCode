package com.sqlite.cescip;

import java.io.File;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;

public class DBDivision {

	public static final int DATABASE_VERSION = 1;
	static File dir = new File(Environment.getExternalStorageDirectory() + "");
	private static String DATABASE_NAME = dir+ "/MR-LOCATOR/LOCATION_DETAILS.db";
	public static final String TABLE_NAME = "LOCATION_DETAILS";
	public static final String  COLUMN_ID = "ID";
	public static final String  COMPANY = "COMPANY";
	public static final String  ZONE = "ZONE";
	public static final String  CIRCLE = "CIRCLE";
	public static final String  DIVISION = "DIVISION";
	public static final String  SUBDIV = "SUBDIV";
	public static final String  SECT = "SECT";
	public static final String  SITECODE = "SITECODE";
	public static final String  DBUSER = "DBUSER";
	public static final String  EMAIL = "EMAIL";
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
			db.execSQL("create table "+TABLE_NAME+" ("+COLUMN_ID+" integer primary key, "+COMPANY+" text, "+ZONE+" text, "+CIRCLE+" text, "+DIVISION+" text, "+SUBDIV+" text, "+SECT+" text, "+SITECODE+" text, "+DBUSER+" text, "+EMAIL+" text, "+COL_MOB_STATUS+" text)");
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

	public DBDivision(Context ctx) {
		this.context = ctx;
	}

	public void open() {
		myDbHelper= new DatabaseHelper(context); 
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
/*	public boolean adddivision(String circlename)
	{
	ContentValues initialValues = new ContentValues();
	initialValues.put(COLUMN_DIVISION_NAME, circlename);
	initialValues.put(COL_MOB_STATUS, "0");
	 
	myDb.insert(TABLE_NAME, null, initialValues);
	return true;
	}	
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
	
	public void insert(String company,  String zone,  String circle,  String division,  String subdiv,  String  section,  String  sitecode  ,  	String  dbuser/*  ,  String  email,  String  status*/) {


				ContentValues contentValues = new ContentValues();
				contentValues.put(  COMPANY , 	  company  );  
				contentValues.put(  ZONE , 	  zone  );  
				contentValues.put(  CIRCLE , 	  circle  );  
				contentValues.put(  DIVISION , 	  division  );  
				contentValues.put(  SUBDIV , 	  subdiv  );  
				contentValues.put(  SECT , 	  section  );  
				contentValues.put(  SITECODE ,  	   sitecode  );  
				contentValues.put(  DBUSER   , 	   dbuser    );
				contentValues.put(  COL_MOB_STATUS , 	   "Active"  );
				/*contentValues.put(  EMAIL   , 	   email    );  
				contentValues.put(  COL_MOB_STATUS , 	   status  ); */ 

				if(myDb.insert(TABLE_NAME, null, contentValues)>0){
				}
	}

	

	public void deleteDisconnectionTable() {
		myDb.delete(TABLE_NAME,null, null);
	}
	
	public int deleteAll() {
		return myDb.delete(TABLE_NAME, null, null);
	}

	public boolean deleteAllNames() {
		int doneDelete = myDb.delete(DATABASE_NAME, null, null);
		return doneDelete > 0;
	}

	public Cursor getOfflineCircles(){
		String[] columns = new String[] {CIRCLE, COL_MOB_STATUS};
		boolean distict=true;
		Cursor cursor = myDb.query(distict, TABLE_NAME, columns, COL_MOB_STATUS+"='Active'", null, null, null, null, null);
		return cursor;
	}
	
	
	public Cursor getOfflineDivisions(){
		String[] columns = new String[] {DIVISION, COL_MOB_STATUS,SITECODE};
		boolean distinct=true;
		//Cursor cursor = myDb.query(distict, TABLE_NAME, columns, COL_MOB_STATUS+"='Active'", null, null, null, null, null);
		Cursor cursor = myDb.query(distinct, TABLE_NAME, columns,  COL_MOB_STATUS+"='Active'", null, DIVISION, null, DIVISION, null);
		return cursor;
	}
	
	
	
	
	public Cursor getOfflineSubDivisions(){
		String[] columns = new String[] {SUBDIV, COL_MOB_STATUS};
		boolean distinct=true;
		Cursor cursor = myDb.query(distinct, TABLE_NAME, columns,  COL_MOB_STATUS+"='Active'", null, SUBDIV, null, SUBDIV, null);

	//	Cursor cursor = myDb.query(distict, TABLE_NAME, columns, COL_MOB_STATUS+"='Active'", null, null, null, null, null);
		return cursor;
	}

	public Cursor getOfflineSDOwithName(){
		String[] columns = new String[] {SITECODE,SECT, COL_MOB_STATUS};
		boolean distinct=true;
		//Cursor cursor = myDb.query(distict, TABLE_NAME, columns, COL_MOB_STATUS+"='Active'", null, null, null, null, null);
		Cursor cursor = myDb.query(distinct, TABLE_NAME, columns,  COL_MOB_STATUS+"='Active'", null, SITECODE, null, SECT, null);
		return cursor;
	}
	
	
	public Cursor getSendingValueCircle(String value){
		String[] columns = new String[] {SITECODE, COL_MOB_STATUS};
		Cursor cursor = myDb.query(TABLE_NAME, columns,CIRCLE + "='" + value + "'", null, null, null, null, "1");
		return cursor;
	}
	
	
	public Cursor getSendingValueDivision(String value){
		String[] columns = new String[] {SITECODE, COL_MOB_STATUS};
		Cursor cursor = myDb.query(TABLE_NAME, columns,DIVISION + "='" + value + "'", null, null, null, null, "1");
		return cursor;
	}
	
	public Cursor getSendingValueSubDiv(String value){
		String[] columns = new String[] {SITECODE, COL_MOB_STATUS};
		Cursor cursor = myDb.query(TABLE_NAME, columns,SUBDIV + "='" + value + "'", null, null, null, null, "1");
		return cursor;
	}
}

