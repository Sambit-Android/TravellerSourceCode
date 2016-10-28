package com.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.bcits.bsmartbilling.rf.MasterLibraryFunction;
import com.utils.helper.BackupHelper;
import com.utils.helper.LiveStatusHelper;






import Utils.Logger;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.os.Environment;

public class DbHandlerBillStatus {

	static Logger logger ;
	private SQLiteHelper sqLiteHelper;
	private SQLiteDatabase sqLiteDatabase;
	private Context context;
	
	
	 private static String TAG = "DBTariffHandler";
	static File dir = new File(Environment.getExternalStorageDirectory() + "");
	private static String MYDATABASE_NAME = dir + "/GESCOM/log/BILLINGSTATUS.db";// dir+"/
	public static final String MYDATABASE_TABLE = "LIVESTATUS";
	public static final int MYDATABASE_VERSION = 1;
	public static final String KEY_ID = "ID";
	public static final String KEY_SITECODE  = "SITECODE" ;
	public static final String KEY_MRCODE = "MRCODE" ;
	public static final String KEY_DATETIMESTAMP ="DATETIMESTAMP" ;
	public static final String KEY_BILLED = "BILLED" ;
	public static final String KEY_SYNCHED = "SYNCHED" ;
	public static final String KEY_TOTAL = "TOTAL" ;
	public static final String KEY_STATUS  = "STATUS" ;
	
	
	private static final String SCRIPT_CREATE_DATABASE = "create table "
			+ MYDATABASE_TABLE + " (" + KEY_ID
			+ " integer primary key autoincrement, "
			+KEY_SITECODE  + " text , "
			+KEY_MRCODE  + " text ,"
			+KEY_BILLED  + " text ,"
			+KEY_SYNCHED   + " text ,"
			+KEY_TOTAL   + " text ,"
			+KEY_DATETIMESTAMP   + " text ,"
			+KEY_STATUS    + "  text  );" ;
	
	
	
	
	 /*REQUIRED*/
    public void setUpdatedStatus( String date ,String status)
    {
    	try{
    	ContentValues values =  new ContentValues();
  	  	values.put(KEY_STATUS,status);
  	  	sqLiteDatabase.update(MYDATABASE_TABLE, values, KEY_DATETIMESTAMP+" = '"+date+"'" , null);
    	}catch (Exception e) {
    		e.printStackTrace();
		}
    }
	
	
    public long insert(String sitecode ,String mrcode ,String billed , String synched , String total , String datetimestamp ){
    	ContentValues contentValues = new ContentValues();
		contentValues.put( KEY_SITECODE  , sitecode );
		contentValues.put( KEY_MRCODE  , mrcode );			
		contentValues.put( KEY_BILLED  , billed );
		contentValues.put( KEY_SYNCHED , synched );
		contentValues.put( KEY_TOTAL   , total);
		contentValues.put( KEY_DATETIMESTAMP   , datetimestamp );
		contentValues.put( KEY_STATUS    , "0" );
		return sqLiteDatabase.insert(MYDATABASE_TABLE, null, contentValues);
    			
    }
    
    
    public List<LiveStatusHelper> getLiveStatusToUpdate() throws SQLException {
		
		 List<LiveStatusHelper> contactList = new ArrayList<LiveStatusHelper>();
      	 Cursor cursor = null,cursorgps=null;
      	 try{
    
      		String bMColumns = KEY_SITECODE			+ " , " +
      				KEY_MRCODE			+" , " +
      				KEY_BILLED			+" , " +
      				KEY_SYNCHED			+" , " +
      				KEY_TOTAL			+" , " +
      				KEY_DATETIMESTAMP			+" , " +
      				KEY_STATUS			;
       	   
      		String selectQuery = "SELECT  " +bMColumns+"  FROM "+ MYDATABASE_TABLE + "  WHERE  "+KEY_STATUS+" = '0' ";
       	    
       	    
       	     cursor = sqLiteDatabase.rawQuery(selectQuery, null);
       	  LiveStatusHelper contact = null;
			if (cursor.moveToFirst()) {
				do {
					contact = new LiveStatusHelper(
							cursor.getString(cursor	.getColumnIndex(KEY_SITECODE)),
							cursor.getString(cursor.getColumnIndex(KEY_MRCODE)),
							cursor.getString(cursor.getColumnIndex(KEY_BILLED)),
							cursor.getString(cursor.getColumnIndex(KEY_SYNCHED)),
							cursor.getString(cursor.getColumnIndex(KEY_TOTAL)),
							cursor.getString(cursor.getColumnIndex(KEY_DATETIMESTAMP)));
					contactList.add(contact);
				} while (cursor.moveToNext());
			}
			cursor.close();
		} catch (Exception e) {
			e.printStackTrace();
			if (cursor != null)
				cursor.close();
		}
		return contactList;
	}
	
	public boolean getCount() {
		String query = "SELECT count(*) FROM  " + MYDATABASE_TABLE	+ "  WHERE  " + KEY_STATUS + " = '0' ";
		Cursor cursor = null;
		cursor = sqLiteDatabase.rawQuery(query, null);
		int count = 0;
		if (cursor.moveToFirst()) {
			count = cursor.getInt(0);
		}
		if (cursor != null)
			cursor.close();
		
		return count>0?true:false;
	}
  	
    
	public boolean removeOldrecords(String count){
	  String whereClause =	KEY_ID +"  < ( (SELECT max("+KEY_ID +" ) FROM  "+MYDATABASE_TABLE+" ) - "+count+")  AND  "+KEY_STATUS +" <>  '0' " ;
	  return  sqLiteDatabase.delete(MYDATABASE_TABLE, whereClause, null) > 0;
	}
	
	
	
	/*-------------------DB UTILITY FUNCTIONS------------------------*/
	
	
	public class SQLiteHelper extends SQLiteOpenHelper {

		public SQLiteHelper(Context context, String name,
				CursorFactory factory, int version) {
			super(context, name, factory, version);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(SCRIPT_CREATE_DATABASE);
			db.execSQL("	CREATE INDEX "+MYDATABASE_TABLE+"_INDEX ON  "+MYDATABASE_TABLE+" ( "+KEY_ID+" ,  "+KEY_SITECODE+"  , "+KEY_MRCODE+" , "+KEY_SYNCHED+" , "+KEY_STATUS+" ) ; " ) ;
			
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("DROP TABLE IF EXISTS " + MYDATABASE_TABLE);
		}

	}
	public DbHandlerBillStatus(Context c) {
		context = c;
		 if(logger == null){
	    		logger = MasterLibraryFunction.getlogger(context, TAG);
	    		logger.info("In Side DBTariffHandler(Context c)");
		 }
	}

	public DbHandlerBillStatus openToRead() throws android.database.SQLException {
		sqLiteHelper = new SQLiteHelper(context, MYDATABASE_NAME, null,	MYDATABASE_VERSION);
		sqLiteDatabase = sqLiteHelper.getReadableDatabase();
		return this;
	}

	public DbHandlerBillStatus openToWrite() throws android.database.SQLException {
		sqLiteHelper = new SQLiteHelper(context, MYDATABASE_NAME, null,	MYDATABASE_VERSION);
		sqLiteDatabase = sqLiteHelper.getWritableDatabase();
		return this;
	}
	 public boolean isOpen() {
         return sqLiteDatabase!=null && sqLiteDatabase.isOpen();
     }

	public void close() {
		sqLiteHelper.close();
	}
	public boolean checkDataBase() {
		SQLiteDatabase checkDB = null;
		try {
			checkDB = SQLiteDatabase.openDatabase(MYDATABASE_NAME, null,
					SQLiteDatabase.OPEN_READONLY);
			checkDB.close();
		} catch (SQLiteException e) {
			// database doesn't exist yet.
		}
		return checkDB != null ? true : false;
	}
}
