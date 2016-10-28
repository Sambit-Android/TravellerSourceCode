package com.sqlite.cescip;

import java.io.File;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;

public class DBDivisioncesc {


	public static final int DATABASE_VERSION = 20;
	static File dir = new File(Environment.getExternalStorageDirectory() + "");
	private static String DATABASE_NAME = dir+ "/CESC_IP/LOCATION_DETAILS_CESC.db";
	public static final String TABLE_NAME = "LOCATION_DETAILS_CESC";
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
	
	
	public static final String COL_rrno = "rrno";
	public static final String COL_listno = "listno";
	public static final String COL_listdate = "listdate";
	public static final String COL_tariff = "tariff";
	public static final String COL_disdate = "disdate";
	public static final String COL_disfr = "disfr";
	public static final String COL_sdocode = "sdocode";
	public static final String COL_remarks = "remarks";
	public static final String COL_dramt = "dramt";
	public static final String COL_rdngdate = "rdngdate";
	public static final String COL_mtrcode = "mtrcode";
	public static final String COL_arrears = "arrears";
	public static final String COL_username = "username";
	public static final String COL_datestamp = "datestamp";
	public static final String COL_reclistno = "reclistno";
	public static final String COL_redate = "redate";
	public static final String COL_ageing = "ageing";
	public static final String COL_status = "status";
	public static final String COL_id = "id";
	public static final String COL_collectionid = "collectionid";
	public static final String COL_oldrrno = "oldrrno";
	public static final String COL_consumer_name = "consumer_name";
	public static final String COL_address = "address";
	public static final String COL_fdrcode = "fdrcode";
	public static final String COL_tccode = "tccode";
	public static final String COL_poleno = "poleno";
	public static final String COL_longitude_dis = "longitude_dis";
	public static final String COL_latitude_dis = "latitude_dis";
	public static final String COL_longitude_recon = "longitude_recon";
	public static final String COL_latitude_recon = "latitude_recon";

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

	public DBDivisioncesc(Context ctx) {
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


	

	public void deleteDisconnectionTable() {
		myDb.delete(TABLE_NAME,null, null);
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
	
	public boolean insertDisconnection(String rrno, String listno,String listdate, String tariff, String disdate, String disfr,String sdocode, String remarks
			, String dramt,String rdngdate, String mtrcode, String arrears, String username,String datestamp, String reclistno
			, String redate,String ageing, String status, String id, String collectionid,String oldrrno, String consumer_name
			, String address,String fdrcode, String tccode, String poleno  , String latdis, String longdis, String latrec, String longrec) {

		ContentValues contentValues = new ContentValues();

		contentValues.put(COL_rrno, rrno);
		contentValues.put(COL_listno, listno);
		contentValues.put(COL_listdate, listdate);
		contentValues.put(COL_tariff, tariff);
		contentValues.put(COL_disdate, disdate);
		contentValues.put(COL_disfr, disfr);
		contentValues.put(COL_sdocode, sdocode);
		contentValues.put(COL_remarks, remarks);
		contentValues.put(COL_dramt, dramt);
		contentValues.put(COL_rdngdate, rdngdate);
		contentValues.put(COL_mtrcode, mtrcode);
		contentValues.put(COL_arrears, arrears);
		contentValues.put(COL_username, username);
		contentValues.put(COL_datestamp, datestamp);
		contentValues.put(COL_reclistno, reclistno);
		contentValues.put(COL_redate, redate);
		contentValues.put(COL_ageing, ageing);
		contentValues.put(COL_status, status);
		contentValues.put(COL_id, id);
		contentValues.put(COL_collectionid, collectionid);
		contentValues.put(COL_oldrrno, oldrrno);
		contentValues.put(COL_consumer_name, consumer_name);
		contentValues.put(COL_address, address);
		contentValues.put(COL_fdrcode, fdrcode);
		contentValues.put(COL_tccode, tccode);
		contentValues.put(COL_poleno, poleno);
		
		contentValues.put(COL_latitude_dis, latdis);
		contentValues.put(COL_longitude_dis, longdis);
		contentValues.put(COL_latitude_recon, latrec);
		contentValues.put(COL_longitude_recon, longrec);
		
		
		
		
		
		myDb.insert(TABLE_NAME, null, contentValues);
		return true;
	}	
}
