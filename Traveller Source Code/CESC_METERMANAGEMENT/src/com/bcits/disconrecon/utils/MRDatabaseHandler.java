package com.bcits.disconrecon.utils;

import java.io.File;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;

public class MRDatabaseHandler {

	static File dir = new File(Environment.getExternalStorageDirectory() + "");
	private static String MYDATABASE_NAME = dir + "/METERMGMT/METERREPLACE.db";


	public static final String MYDATABASE_TABLE = "METERREPLACE_INPUT_LIVE";
	public static final int MYDATABASE_VERSION = 1;
	public static final String KEY_IDSERIAL = "IDSERIAL";


	public static final String KEY_RRNO	= "RRNO";
	public static final String KEY_LISTNO	= "LISTNO";
	public static final String KEY_LISTDATE	= "LISTDATE";
	public static final String KEY_TARIFF	= "TARIFF";
	public static final String KEY_SOCODE	= "SOCODE";
	public static final String KEY_DRAMT	= "DRAMT";
	public static final String KEY_RDNGDATE	= "RDNGDATE";
	public static final String KEY_MTRCODE	= "MTRCODE";
	public static final String KEY_ARREARS	= "ARREARS";
	public static final String KEY_USERNAME	= "USERNAME";
	public static final String KEY_DATESTAMP	= "DATESTAMP";
	public static final String KEY_AGEING	= "AGEING";
	public static final String KEY_STATUS	= "STATUS";
	public static final String KEY_ID	= "ID";
	public static final String KEY_COLLECTIONID	= "COLLECTIONID";
	public static final String KEY_OLDRRNO	= "OLDRRNO";
	public static final String KEY_CONSUMER_NAME	= "CONSUMER_NAME";
	public static final String KEY_ADDRESS	= "ADDRESS";
	public static final String KEY_FDRCODE	= "FDRCODE";
	public static final String KEY_TCCODE	= "TCCODE";
	public static final String KEY_POLENO	= "POLENO";
	public static final String KEY_BALANCE	= "BALANCE";
	public static final String KEY_AVERAGE	= "AVERAGE";




	public static final String KEY_OMDATEOFRELEASE = "OMDATEOFRELEASE";
	public static final String KEY_OMSERIALNO ="OMSERIALNO";
	public static final String KEY_OMMAKE = "OMMAKE";
	public static final String KEY_OMCAPACITY ="OMCAPACITY";
	public static final String KEY_OMCOVER = "OMCOVER";
	public static final String KEY_OMPOSITION = "OMPOSITION";
	public static final String KEY_OMTYPE = "OMTYPE";
	public static final String KEY_OMFR = "OMFR";
	public static final String KEY_OMIMAGE = "OMIMAGE";
	public static final String KEY_OMLONGITUDE = "OMLONGITUDE";
	public static final String KEY_OMLATTITUDE = "OMLATTITUDE";


	public static final String KEY_NMDATEOFINSTALL ="NMDATEOFINSTALL";
	public static final String KEY_NMSERIALNO ="NMSERIALNO";
	public static final String KEY_NMMAKE ="NMMAKE";
	public static final String KEY_NMCAPACITY ="NMCAPACITY";
	public static final String KEY_NMCOVER = "NMCOVER";
	public static final String KEY_NMPOSITION = "NMPOSITION";
	public static final String KEY_NMTYPE = "NMTYPE";
	public static final String KEY_NMIR ="NMIR";
	public static final String KEY_NMIMAGE = "NMIMAGE";
	public static final String KEY_NMLONGITUDE = "NMLONGITUDE";
	public static final String KEY_NMLATTITUDE = "NMLATTITUDE";
	public static final String KEY_NMCONSTANT = "NMCONSTANT";



	public static final String KEY_METERREPLACEDATE = "METERREPLACEDATE";
	public static final String KEY_DEVICEID = "DEVICEID";
	public static final String KEY_METERREPLACESTATUS = "METERREPLACESTATUS";
	public static final String KEY_MR = "MR";
	public static final String KEY_SERVERTOMOBILEDATE	= "SERVERTOMOBILEDATE";
	public static final String KEY_DEVICEFIRMWAREVERSION	= "DEVICEFIRMWAREVERSION";
	public static final String KEY_METERFLAG	= "METERFLAG";



	public static final String KEY_EXTRA1	= "EXTRA1";
	public static final String KEY_EXTRA2	= "EXTRA2";
	public static final String KEY_EXTRA3	= "EXTRA3";
	public static final String KEY_EXTRA4	= "EXTRA4";
	public static final String KEY_EXTRA5	= "EXTRA5";
	public static final String KEY_EXTRA6	= "EXTRA6";
	
	public static final String KEY_EXTRA7	= "EXTRA7";
	public static final String KEY_EXTRA8	= "EXTRA8";
	public static final String KEY_EXTRA9	= "EXTRA9";
	public static final String KEY_EXTRA10	= "EXTRA10";






	private static final String SCRIPT_CREATE_DATABASE_MRLIST = 
			"create table "
					+ MYDATABASE_TABLE + " ( " + KEY_IDSERIAL
					+ " integer primary key autoincrement, "
					+ KEY_RRNO	+ " text , "
					+ KEY_LISTNO	+ " text , "
					+ KEY_LISTDATE	+ " text , "
					+ KEY_TARIFF	+ " text , "
					+ KEY_SOCODE	+ " text , "
					+ KEY_DRAMT	+ " text , "
					+ KEY_RDNGDATE	+ " text , "
					+ KEY_MTRCODE	+ " text , "
					+ KEY_ARREARS	+ " text , "
					+ KEY_USERNAME	+ " text , "
					+ KEY_DATESTAMP	+ " text , "
					+ KEY_AGEING	+ " text , "
					+ KEY_STATUS	+ " text , "
					+ KEY_ID	+ " text , "
					+ KEY_COLLECTIONID	+ " text , "
					+ KEY_OLDRRNO	+ " text , "
					+ KEY_CONSUMER_NAME	+ " text , "
					+ KEY_ADDRESS	+ " text , "
					+ KEY_FDRCODE	+ " text , "
					+ KEY_TCCODE	+ " text , "
					+ KEY_POLENO	+ " text , "
					+ KEY_BALANCE   + " text , "
					+ KEY_AVERAGE   + " text , "
					+ KEY_OMDATEOFRELEASE + " text , "
					+ KEY_OMSERIALNO +" text ,"
					+ KEY_OMMAKE +" text ,"
					+ KEY_OMCAPACITY +" text ,"
					+ KEY_OMCOVER +" text ,"
					+ KEY_OMPOSITION +" text ,"
					+ KEY_OMTYPE +" text ,"
					+ KEY_OMFR +" text ,"
					+ KEY_OMIMAGE +" text ,"
					+ KEY_OMLONGITUDE +" text ,"
					+ KEY_OMLATTITUDE +" text ,"
					+ KEY_NMDATEOFINSTALL +" text ,"
					+ KEY_NMSERIALNO +" text ,"
					+ KEY_NMMAKE +" text ,"
					+ KEY_NMCAPACITY +" text ,"
					+ KEY_NMCOVER +" text ,"
					+ KEY_NMPOSITION +" text ,"
					+ KEY_NMTYPE +" text ,"
					+ KEY_NMIR +" text ,"
					+ KEY_NMIMAGE +" text ,"
					+ KEY_NMLONGITUDE +" text ,"
					+ KEY_NMLATTITUDE +" text ,"
					+ KEY_NMCONSTANT +" text ,"
					+ KEY_METERREPLACEDATE	+ " text , "
					+ KEY_DEVICEID	+ " text , "
					+ KEY_METERREPLACESTATUS	+ " text , "
					+ KEY_MR	+ " text , "
					+ KEY_SERVERTOMOBILEDATE	+ " text , "
					+ KEY_DEVICEFIRMWAREVERSION	+ " text , "
					+ KEY_METERFLAG	+ " text , "
					+ KEY_EXTRA1	+ " text , "
					+ KEY_EXTRA2	+ " text , "
					+ KEY_EXTRA3	+ " text , "
					+ KEY_EXTRA4	+ " text , "
					+ KEY_EXTRA5	+ " text , "
					+ KEY_EXTRA6	+ " text , "
					+ KEY_EXTRA7	+ " text , "
					+ KEY_EXTRA8	+ " text , "
					+ KEY_EXTRA9	+ " text , "
					+ KEY_EXTRA10	+ " text ); ";



	private SQLiteHelper sqLiteHelper;
	private SQLiteDatabase sqLiteDatabase;

	private Context context;

	public MRDatabaseHandler(Context c) {
		context = c;
	}

	public MRDatabaseHandler openToRead() throws android.database.SQLException {
		sqLiteHelper = new SQLiteHelper(context, MYDATABASE_NAME, null,
				MYDATABASE_VERSION);
		sqLiteDatabase = sqLiteHelper.getReadableDatabase();
		return this;
	}

	public MRDatabaseHandler openToWrite() throws android.database.SQLException {
		sqLiteHelper = new SQLiteHelper(context, MYDATABASE_NAME, null,
				MYDATABASE_VERSION);
		sqLiteDatabase = sqLiteHelper.getWritableDatabase();
		return this;
	}

	public void close() {
		sqLiteHelper.close();
	}






	public void insert(
			String   id,
			String  		rrno	,
			String  		listno	,
			String  		consumer_name	,
			String  		address	,
			String  		meterstatus,
			String  		poleno	,
			String  		fdrcode	,
			String  		tccode	,
			String  		socode	,
			String  		tariff	,
			String  		status	,
			String  		meterflag,
			String  		metereplacestatus,
			String  		deviceid,
			String  		servertomobiledate	,
			String  		devicefirmwareversion	,
			String  		extra1	,
			String  		extra2	,
			String  		extra3	,
			String  		extra4	,
			String  		extra5	,
			String  		extra6	,
			String  		extra7	,
			String  		extra8	,
			String  		extra9	,
			String  		extra10	

			) {
		System.out.println("insert");

		boolean db_status = checkDataBase();
		System.out.println("db_status" + db_status);

		if (db_status) {

			System.out
			.println("step 1************************************************************");

			String q = "SELECT RRNO FROM " + MYDATABASE_TABLE
					+ " WHERE RRNO ='" + rrno + "' ;";
			System.out.println("check ****************   "+q);
			Cursor mCursor = sqLiteDatabase.rawQuery(q, null);
			if (mCursor != null && mCursor.moveToFirst()) {
				mCursor.moveToFirst();
				System.out
				.println("step 2************************************************************");

			} else {
				System.out
				.println("step 3************************************************************");



				System.out.println("Check bluetooth value *****************KEY_STATUS*********  : "+status);
				System.out.println("Check bluetooth value *************KEY_BILLEDSTATUS**************  : "+KEY_METERREPLACESTATUS);


				System.out
				.println("rrno no exits............*******************************************************");
				ContentValues contentValues = new ContentValues();

				contentValues.put(  KEY_ID	,			 id	);
				contentValues.put(  KEY_RRNO	,			 rrno	);
				contentValues.put(  KEY_LISTNO	,			 listno	);
				contentValues.put(  KEY_TARIFF	,			 tariff	);
				contentValues.put(  KEY_SOCODE	,			 socode	);
				contentValues.put(  KEY_MTRCODE	,			 meterstatus	);
				
				
				contentValues.put(  KEY_STATUS	,			 status	);
				contentValues.put(  KEY_CONSUMER_NAME	,			 consumer_name	);
				contentValues.put(  KEY_ADDRESS	,			 address	);
				contentValues.put(  KEY_FDRCODE	,			 fdrcode	);
				contentValues.put(  KEY_TCCODE	,			 tccode	);
				contentValues.put(  KEY_POLENO	,			 poleno	);
				contentValues.put(  KEY_METERFLAG	,			 meterflag	);
				contentValues.put(  KEY_DEVICEID	,			 deviceid	);
				contentValues.put(  KEY_METERREPLACESTATUS	,			 metereplacestatus	);
				contentValues.put(  KEY_SERVERTOMOBILEDATE	,			 servertomobiledate	);
				contentValues.put(  KEY_DEVICEFIRMWAREVERSION	,			 devicefirmwareversion	);
				contentValues.put(  KEY_EXTRA1	,			 extra1	);
				contentValues.put(  KEY_EXTRA4	,			 extra2	);
				contentValues.put(  KEY_EXTRA5	,			 extra3	);
				contentValues.put(  KEY_EXTRA6	,			 extra4	);
				contentValues.put(  KEY_EXTRA2	,			 extra5	);
				contentValues.put(  KEY_EXTRA3	,			 extra6	);
				contentValues.put(  KEY_EXTRA7	,			 extra7	);
				contentValues.put(  KEY_EXTRA8	,			 extra8	);
				contentValues.put(  KEY_EXTRA9	,			 extra9	);
				contentValues.put(  KEY_EXTRA10	,			 extra10);


				System.out.println("insert");
				sqLiteDatabase.insert(MYDATABASE_TABLE, null, contentValues);

				sqLiteDatabase.close();

			}
			mCursor.close();
			// sqLiteDatabase.close();
		}

		else {
			System.out
			.println("step 4************************************************************");
			ContentValues contentValues = new ContentValues();





			contentValues.put(  KEY_ID	,			 id	);
			contentValues.put(  KEY_RRNO	,			 rrno	);
			contentValues.put(  KEY_LISTNO	,			 listno	);
			contentValues.put(  KEY_TARIFF	,			 tariff	);
			contentValues.put(  KEY_SOCODE	,			 socode	);
			contentValues.put(  KEY_MTRCODE	,			 meterstatus	);
			
			
			contentValues.put(  KEY_STATUS	,			 status	);
			contentValues.put(  KEY_CONSUMER_NAME	,			 consumer_name	);
			contentValues.put(  KEY_ADDRESS	,			 address	);
			contentValues.put(  KEY_FDRCODE	,			 fdrcode	);
			contentValues.put(  KEY_TCCODE	,			 tccode	);
			contentValues.put(  KEY_POLENO	,			 poleno	);
			contentValues.put(  KEY_METERFLAG	,			 meterflag	);
			contentValues.put(  KEY_DEVICEID	,			 deviceid	);
			contentValues.put(  KEY_METERREPLACESTATUS	,			 metereplacestatus	);
			contentValues.put(  KEY_SERVERTOMOBILEDATE	,			 servertomobiledate	);
			contentValues.put(  KEY_DEVICEFIRMWAREVERSION	,			 devicefirmwareversion	);
			contentValues.put(  KEY_EXTRA1	,			 extra1	);
			contentValues.put(  KEY_EXTRA4	,			 extra2	);
			contentValues.put(  KEY_EXTRA5	,			 extra3	);
			contentValues.put(  KEY_EXTRA6	,			 extra4	);
			contentValues.put(  KEY_EXTRA2	,			 extra5	);
			contentValues.put(  KEY_EXTRA3	,			 extra6	);
			contentValues.put(  KEY_EXTRA7	,			 extra7	);
			contentValues.put(  KEY_EXTRA8	,			 extra8	);
			contentValues.put(  KEY_EXTRA9	,			 extra9	);
			contentValues.put(  KEY_EXTRA10	,			 extra10);



			System.out.println("insert");
			sqLiteDatabase.insert(MYDATABASE_TABLE, null, contentValues);

			sqLiteDatabase.close();

		}

	}

	/*
	 * Check if the database exist
	 * 
	 * @return true if it exists, false if it doesn't
	 **/
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

	public int deleteAll() {
		return sqLiteDatabase.delete(MYDATABASE_TABLE, null, null);
	}




	public Cursor checkuser_RRNO() throws SQLException {

		String q = "SELECT  RRNO FROM " + MYDATABASE_TABLE + " WHERE  "+KEY_METERREPLACESTATUS+"='0' AND  STATUS='NOTUPDATED' ;";
		Cursor mCursor = sqLiteDatabase.rawQuery(q, null);

		System.out.println("row size ::::"+mCursor.getCount());

		if (mCursor != null) {
			mCursor.moveToFirst();




		}
		sqLiteDatabase.close();
		return mCursor;
	}



	public int Updatetolocaldb(
			String rrno,
			String omdateofrelease,
			String omserialno,
			String ommake, 
			String omcapacity, 
			String omcover, 
			String omposion, 
			String omtype,
			String omfr,
			String omlongitude ,
			String omlatitude,
			String omimage,
			String nmdateofinstall,
			String nmserialno,
			String nmmake, 
			String nmcapacity, 
			String nmcover, 
			String nmposion, 
			String nmtype,
			String nmir,
			String nmlongitude ,
			String nmlatitude,
			String nmimage,
			String nmconstant,
			String devicefirmwareversion,
			String submitdatetimestamp,
			String submitstatus,
			String extra7,
			String extra8,
			String extra9,
			String extra10


			)
					throws SQLException {


		String syncstatus ="NOTUPDATED";


		ContentValues args = new ContentValues();

		args.put(KEY_OMDATEOFRELEASE, omdateofrelease);
		args.put(KEY_OMSERIALNO, omserialno);
		args.put(KEY_OMMAKE, ommake);
		args.put(KEY_OMCAPACITY, omcapacity);
		args.put(KEY_OMCOVER, omcover);
		args.put(KEY_OMPOSITION, omposion);
		args.put(KEY_OMTYPE, omtype);
		args.put(KEY_OMFR, omfr);
		args.put(KEY_OMLONGITUDE, omlongitude);
		args.put(KEY_OMLATTITUDE, omlatitude);
		args.put(KEY_OMIMAGE, omimage);

		args.put(KEY_NMDATEOFINSTALL, nmdateofinstall);
		args.put(KEY_NMSERIALNO, nmserialno);
		args.put(KEY_NMMAKE, nmmake);
		args.put(KEY_NMCAPACITY, nmcapacity);
		args.put(KEY_NMCOVER, nmcover);
		args.put(KEY_NMPOSITION, nmposion);
		args.put(KEY_NMTYPE, nmtype);
		args.put(KEY_NMIR, nmir);
		args.put(KEY_NMLONGITUDE, nmlongitude);
		args.put(KEY_NMLATTITUDE, nmlatitude);
		args.put(KEY_NMIMAGE, nmimage);
		args.put(KEY_NMCONSTANT, nmconstant);


		args.put(KEY_DEVICEFIRMWAREVERSION, devicefirmwareversion);
		args.put(KEY_METERREPLACEDATE, submitdatetimestamp);

		args.put(KEY_METERREPLACESTATUS, submitstatus);
		
		args.put(KEY_EXTRA7, submitdatetimestamp);
		args.put(KEY_EXTRA8, extra8);
		args.put(KEY_EXTRA9, extra9);
		args.put(KEY_EXTRA10, extra10);

		int flag = sqLiteDatabase.update(MYDATABASE_TABLE, args, ""+KEY_RRNO+" ='"
				+ rrno + "'", null);


		sqLiteDatabase.close();
		return flag;

	}






	public Cursor checkmReadMR() throws SQLException {







		String q = "SELECT  DISTINCT("+KEY_LISTNO+") FROM " + MYDATABASE_TABLE + ";";

		System.out.println("query :- " + q);
		Cursor mCursor = sqLiteDatabase.rawQuery(q, null);

		System.out.println("no of rows :"+mCursor.getCount());

		if (mCursor != null) {

			mCursor.moveToFirst();


		}
		sqLiteDatabase.close();
		return mCursor;
	}




	public Cursor Gettotalcount() throws SQLException {

		String q = "SELECT  count(*) AS unbilled FROM " + MYDATABASE_TABLE
				+ ";";
		Cursor mCursor = sqLiteDatabase.rawQuery(q, null);
		if (mCursor != null) {
			mCursor.moveToFirst();

		}
		sqLiteDatabase.close();
		return mCursor;
	}



	public Cursor GetReplacedcount() throws SQLException {


		String q = "SELECT  count(*) AS billed FROM " + MYDATABASE_TABLE
				+ " WHERE  "+KEY_METERREPLACESTATUS+"='1';";
		System.out.println("query :- "+q);
		Cursor mCursor = sqLiteDatabase.rawQuery(q, null);
		if (mCursor != null) {
			mCursor.moveToFirst();

		}
		sqLiteDatabase.close();
		return mCursor;
	}


	public Cursor GetNotReplacedCount() throws SQLException {

		String q = "SELECT  count(*) AS unbilled FROM " + MYDATABASE_TABLE
				+ " WHERE "+KEY_METERREPLACESTATUS+"='0';";
		System.out.println("query :- " + q);
		Cursor mCursor = sqLiteDatabase.rawQuery(q, null);
		if (mCursor != null) {
			mCursor.moveToFirst();

		}
		sqLiteDatabase.close();
		return mCursor;
	}



	public Cursor GetSyncedToserverCount() throws SQLException {

		String q = "SELECT count(*) as billed FROM " + MYDATABASE_TABLE
				+ " WHERE "+KEY_METERREPLACESTATUS+"='1' AND ( STATUS='UPDATED'  OR STATUS='ALREADYBILLED'  ) ;";
		System.out.println("qurey :- "+q);
		Cursor mCursor = sqLiteDatabase.rawQuery(q, null);
		if (mCursor != null) {
			mCursor.moveToFirst();

		}
		sqLiteDatabase.close();
		return mCursor;
	}



	public Cursor GetNotSyncedToServerCount() throws SQLException {
		// System.out.println("check user" + username + password);****

		String q = "SELECT count(*) as billed FROM " + MYDATABASE_TABLE
				+ " WHERE "+KEY_METERREPLACESTATUS+"='1' AND STATUS='NOTUPDATED';";
		System.out.println("qurey :- "+q);
		Cursor mCursor = sqLiteDatabase.rawQuery(q, null);
		if (mCursor != null) {
			mCursor.moveToFirst();

		}
		sqLiteDatabase.close();
		return mCursor;
	}




	public Cursor Getconsumer_detailsbyRRNo(String account_no) throws SQLException {

		String q = "SELECT *  FROM " + MYDATABASE_TABLE + " WHERE TRIM(RRNO) = TRIM('"+account_no+"') ;";

		System.out.println("query   "+q);
		Cursor mCursor = sqLiteDatabase.rawQuery(q, null);
		if (mCursor != null) {
			mCursor.moveToFirst();

		}
		sqLiteDatabase.close();
		return mCursor;
	}




	public Cursor Fetchnextconsumer() throws SQLException {

		String q = "SELECT  "+KEY_RRNO+" ,ID FROM "
				+ MYDATABASE_TABLE + " WHERE  "+KEY_STATUS+"='NOTUPDATED' AND "+KEY_METERREPLACESTATUS+"='0';";
		Cursor mCursor = sqLiteDatabase.rawQuery(q, null);
		if (mCursor != null) {
			mCursor.moveToFirst();

		}
		sqLiteDatabase.close();
		return mCursor;
	}



	public Cursor GetDistictRRNO() throws SQLException {
		

		String q = "SELECT DISTINCT("+KEY_RRNO+") FROM "+MYDATABASE_TABLE+" WHERE "+KEY_RRNO+" NOTNULL AND "+KEY_RRNO+" NOT LIKE '';";
		System.out.println("check user query  " +q);
		Cursor mCursor = sqLiteDatabase.rawQuery(q, null);
		if (mCursor != null) {
			mCursor.moveToFirst();

		}
		sqLiteDatabase.close();
		return mCursor;
	}
	
	
	
	public Cursor Getconsumer_detailsbyconsumer_no(String account_no) throws SQLException {

		String q = "SELECT *  FROM " + MYDATABASE_TABLE + " WHERE TRIM(RRNO) = TRIM('"+account_no+"') ;";

		System.out.println("query   "+q);
		Cursor mCursor = sqLiteDatabase.rawQuery(q, null);
		if (mCursor != null) {
			mCursor.moveToFirst();

		}
		sqLiteDatabase.close();
		return mCursor;
	}


	public Cursor GetSubmitButNotSyncedCount() throws SQLException {
		// System.out.println("check user" + username + password);****

		String q = "SELECT count(*) as synced FROM " + MYDATABASE_TABLE
				+ " WHERE "+KEY_METERREPLACESTATUS+"='1' AND "+KEY_STATUS+"='NOTUPDATED' ;";
		System.out.println("qurey :- "+q);
		Cursor mCursor = sqLiteDatabase.rawQuery(q, null);
		if (mCursor != null) {
			mCursor.moveToFirst();

		}
		sqLiteDatabase.close();
		return mCursor;
	}

	
	public Cursor GetSubmitandSevercountfromlocal() throws SQLException {
		// System.out.println("check user" + username + password);****

		String q = "SELECT count(*) as synced FROM " + MYDATABASE_TABLE
				+ " WHERE "+KEY_METERREPLACESTATUS+"='1' AND  ( "+KEY_STATUS+"='UPDATED'  OR "+KEY_STATUS+"='ALREADYBILLED'  ) ;";
		System.out.println("qurey :- "+q);
		Cursor mCursor = sqLiteDatabase.rawQuery(q, null);
		if (mCursor != null) {
			mCursor.moveToFirst();

		}
		sqLiteDatabase.close();
		return mCursor;
	}

	
	
	public Cursor GetSubmittedconsumers_fromlocal() throws SQLException {
		

		String q = "SELECT * FROM "
				+ MYDATABASE_TABLE + "  WHERE "+KEY_METERREPLACESTATUS+"='1' AND "+KEY_STATUS+"='NOTUPDATED';";
		System.out.println("check user query  " +q);
		Cursor mCursor = sqLiteDatabase.rawQuery(q, null);
		if (mCursor != null) {
			mCursor.moveToFirst();

		}
		sqLiteDatabase.close();
		return mCursor;
	}

	

	public int Changesgpastatus(String RRNO,String status) throws SQLException {

		  ContentValues args = new ContentValues();
		  args.put(KEY_STATUS, status);

		  int flag = sqLiteDatabase.update(MYDATABASE_TABLE, args, KEY_RRNO+" = '"
		    + RRNO + "'", null);
		  sqLiteDatabase.close();
		  System.out.println("flag :  "+ flag);
		  return flag;

		 }

	
	

	public class SQLiteHelper extends SQLiteOpenHelper {

		public SQLiteHelper(Context context, String name,
				CursorFactory factory, int version) {

			super(context, name, factory, version);


			System.out.println("came hereeeeee first");


		}

		@Override
		public void onCreate(SQLiteDatabase db) {





			System.out.println("came hereeeeee toooooooooooooooooooo");



			System.out.println(" &&&&&&&&&&&&&&&&&&&&&&********************************check ********************^&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
			// TODO Auto-generated method stub
			db.execSQL(SCRIPT_CREATE_DATABASE_MRLIST);
			System.out.println("******************************");
			//ALTER TABLE CONSUMER_INPUT_LIVE_GED ADD COLUMN  'PF_READING' text ,  'PF_PENALITY'  text, 'EXTRA3' text , 'EXTRA4'  text ,'EXTRA5' text , 'EXTRA6' text , 'EXTRA7' text , 'EXTRA8' text ;


			// CREATE INDEX idx_ex1 ON ex1(a,b,c,d,e,...,y,z);  ,     ,  ,  ,  ,  , 
			// "CREATE INDEX CONSUMER_INPUT_LIVE_GED_INDEX ON "+ MYDATABASE_TABLE + " ( "+a+" , "+b+" , "+c+" , "+d+" , "+e+" , "+.+" , "+.+" , "+y+" , "+z +") ; "
			/*db.execSQL("CREATE INDEX CONSUMER_INPUT_LIVE_GED_INDEX ON "+ MYDATABASE_TABLE + " (  "+KEY_BILLEDSTATUS+" , "+KEY_CUSTOMER_NUMBER +" , "+KEY_METERNO+" , "+KEY_STATUS+"  , "+KEY_TARIFF+" ) ; ");
			System.out.println("CREATE INDEX CONSUMER_INPUT_LIVE_GED_INDEX ON "+ MYDATABASE_TABLE + " (  "+KEY_BILLEDSTATUS+" , "+KEY_CUSTOMER_NUMBER +" , "+KEY_METERNO+" , "+KEY_STATUS+"  , "+KEY_TARIFF+" ) ; ");
			for(int colCount = 0 ; colCount > 8 ; colCount++)
			{

				db.execSQL(sql)

			}*/

		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS " + MYDATABASE_TABLE);
		}

	}



}
