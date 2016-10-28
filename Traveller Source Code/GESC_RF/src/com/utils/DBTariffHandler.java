package com.utils;

import java.io.File;
import java.util.HashMap;

import com.bcits.bsmartbilling.rf.MasterLibraryFunction;

import Utils.Logger;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;

public class DBTariffHandler {

	static Logger logger ;
	 private static String TAG = "DBTariffHandler";
	static File dir = new File(Environment.getExternalStorageDirectory() + "");
	private static String MYDATABASE_NAME = dir + "/GESCOM/TARIFF.db";// dir+"/
	public static final String MYDATABASE_TABLE = "CESC_TARIFF";
	public static final int MYDATABASE_VERSION = 1;
	public static final String KEY_TDATE  = "TDATE" ;
	public static final String KEY_TARIFFCODE = "TARIFFCODE" ;
	public static final String KEY_TARIFFDESC ="TARIFFDESC" ;
	public static final String KEY_TARIFFDCB = "TARIFFDCB" ;
	public static final String KEY_ACCOUNTCODE = "ACCOUNTCODE" ;
	public static final String KEY_TARIFFSC = "TARIFFSC" ;
	public static final String KEY_VOLTAGECLASS  = "VOLTAGECLASS" ;
	public static final String KEY_FEC  = "FEC" ;
	public static final String KEY_TAX = "TAX" ;
	public static final String KEY_FCSLAB1  = "FCSLAB1" ;
	public static final String KEY_FCRATE1 = "FCRATE1" ;
	public static final String KEY_FCSLAB2= "FCSLAB2" ;
	public static final String KEY_FCRATE2 = "FCRATE2" ;
	public static final String KEY_FCSLAB3 = "FCSLAB3" ;
	public static final String KEY_FCRATE3 = "FCRATE3" ;
	public static final String KEY_FCSLAB4 = "FCSLAB4" ;
	public static final String KEY_FCRATE4 = "FCRATE4" ;
	public static final String KEY_ECSLAB1 = "ECSLAB1" ;
	public static final String KEY_ECRATE1 = "ECRATE1" ;
	public static final String KEY_ECSLAB2 = "ECSLAB2" ;
	public static final String KEY_ECRATE2 = "ECRATE2" ;
	public static final String KEY_ECSLAB3 = "ECSLAB3" ;
	public static final String KEY_ECRATE3 = "ECRATE3" ;
	public static final String KEY_ECSLAB4 = "ECSLAB4" ;
	public static final String KEY_ECRATE4 = "ECRATE4" ;
	public static final String KEY_ECSLAB5 = "ECSLAB5" ;
	public static final String KEY_ECRATE5 = "ECRATE5" ;
	public static final String KEY_ECSLAB6 = "ECSLAB6" ;
	public static final String KEY_ECRATE6 = "ECRATE6" ;
	public static final String KEY_PFCODE = "PFCODE" ;
	public static final String KEY_FCREDUCTION = "FCREDUCTION" ;
	public static final String KEY_ECREDUCTION = "ECREDUCTION" ;
	public static final String KEY_FCMAX = "FCMAX" ;
	public static final String KEY_ECMAX = "ECMAX";
	public static final String KEY_CALFC = "CALFC";
	public static final String KEY_CALEC = "CALEC";
	public static final String KEY_METERINSTALLATION = "METERINSTALLATION";
	public static final String KEY_REMARKS = "REMARKS";
	public static final String KEY_CBTARIFF = "CBTARIFF";
	public static final String KEY_RECTARIFF = "RECTARIFF";
	public static final String KEY_PFTARIFF = "PFTARIFF";
	public static final String KEY_EDPENALTY = "EDPENALTY";
	public static final String KEY_INTRESTRATE = "INTRESTRATE";
	public static final String KEY_USERNAME = "USERNAME";
	public static final String KEY_DATESTAMP = "DATESTAMP";
	public static final String KEY_ID = "ID";
	public static final String KEY_TARIFFSLNO = "TARIFFSLNO";
	public static final String KEY_TARIFFFCBASIS = "TARIFFFCBASIS";


	
	private static final String SCRIPT_CREATE_DATABASE = "create table "
			+ MYDATABASE_TABLE + " (" + KEY_ID
			+ " integer primary key autoincrement, "
			+KEY_TDATE  + " text , "
			+KEY_TARIFFCODE  + " text ,"
			+KEY_TARIFFDESC  + " text ,"
			+KEY_TARIFFDCB   + " text ,"
			+KEY_ACCOUNTCODE   + " text ,"
			+KEY_TARIFFSC   + " text ,"
			+KEY_VOLTAGECLASS    + " text ,"
			+KEY_FEC    + " text ,"
			+KEY_TAX   + " text ,"
			+KEY_FCSLAB1   + " text ,"
			+KEY_FCRATE1   + " text ,"
			+KEY_FCSLAB2  + " text ,"
			+KEY_FCRATE2   + " text ,"
			+KEY_FCSLAB3   + " text ,"
			+KEY_FCRATE3   + " text ,"
			+KEY_FCSLAB4   + " text ,"
			+KEY_FCRATE4   + " text ," 
			+KEY_ECSLAB1   + " text ,"
			+KEY_ECRATE1   + " text ,"
			+KEY_ECSLAB2   + " text ,"
			+KEY_ECRATE2   + " text ,"
			+KEY_ECSLAB3   + " text ,"
			+KEY_ECRATE3   + " text ,"
			+KEY_ECSLAB4   + " text ,"
			+KEY_ECRATE4   + " text ,"
			+KEY_ECSLAB5   + " text ,"
			+KEY_ECRATE5   + " text ,"
			+KEY_ECSLAB6   + " text ,"
			+KEY_ECRATE6   + " text ,"
			+KEY_PFCODE   + " text ,"
			+KEY_FCREDUCTION   + " text ,"
			+KEY_ECREDUCTION   + " text ,"
			+KEY_FCMAX   + " text ,"
			+KEY_ECMAX   + " text ,"
			+KEY_CALFC   + " text ,"
			+KEY_CALEC   + " text ,"
			+KEY_METERINSTALLATION   + " text ,"
			+KEY_REMARKS   + " text ,"
			+KEY_CBTARIFF   + " text ,"
			+KEY_RECTARIFF   + " text ,"
			+KEY_PFTARIFF   + " text ,"
			+KEY_EDPENALTY   + " text ,"
			+KEY_INTRESTRATE  + " text ,"
			+KEY_USERNAME   + " text ,"
			+KEY_DATESTAMP   + " text ,"
			
			+KEY_TARIFFSLNO   + "  text ,"
			+KEY_TARIFFFCBASIS  + "  text  );"
;

	private SQLiteHelper sqLiteHelper;
	private SQLiteDatabase tariffDatabase;

	private Context context;

	public DBTariffHandler(Context c) {
		context = c;
		 if(logger == null){
	    		logger = MasterLibraryFunction.getlogger(context, TAG);
	    		logger.info("In Side DBTariffHandler(Context c)");
		 }
	}

	public DBTariffHandler openToRead() throws android.database.SQLException {
		sqLiteHelper = new SQLiteHelper(context, MYDATABASE_NAME, null,
				MYDATABASE_VERSION);
		tariffDatabase = sqLiteHelper.getReadableDatabase();
		return this;
	}

	public DBTariffHandler openToWrite() throws android.database.SQLException {
		sqLiteHelper = new SQLiteHelper(context, MYDATABASE_NAME, null,
				MYDATABASE_VERSION);
		tariffDatabase = sqLiteHelper.getWritableDatabase();
		return this;
	}

	public void close() {
		sqLiteHelper.close();
	}
  

	
	
	public void insert(String tdate,
			String  tariffcode,
			String  tariffdesc,
			String  tariffdcb,
			String  accountcode,
			String  tariffsc,
			String  voltageclass,
			String  fec,
			String  tax,
			String  fcslab1,
			String  fcrate1,
			String  fcslab2,
			String  fcrate2,
			String  fcslab3,
			String  fcrate3,
			String  fcslab4,
			String  fcrate4,
			String  ecslab1,
			String  ecrate1,
			String  ecslab2,
			String  ecrate2,
			String  ecslab3,
			String  ecrate3,
			String  ecslab4,
			String  ecrate4,
			String  ecslab5,
			String  ecrate5,
			String  ecslab6,
			String  ecrate6,
			String  pfcode,
			String  fcreduction,
			String  ecreduction,
			String  fcmax,
			String  ecmax,
			String  calfc,
			String  calec,
			String  meterinstallation,
			String  remarks,
			String  cbtariff,
			String  rectariff,
			String  pftariff,
			String  edpenalty,
			String  interstate,
			String  username,
			String  datestamp,
			
			String  tariffslno,String tarifffcbasis
 ) {
		//logger.debug("insert");

		boolean db_status = checkDataBase();
	//	logger.debug("db_status" + db_status);

		if (db_status) {

			
			String q = "SELECT TDATE, TARIFFCODE FROM " + MYDATABASE_TABLE	+ " WHERE TDATE ='" + tdate + "' AND TARIFFCODE ='" + tariffcode + "' ;";
			Cursor mCursor = tariffDatabase.rawQuery(q, null);
			
			/*if(tariffcode.equals("134"))
				logger.debug(" *************tdate :"+ tdate +" : tariffcode:"+tariffcode + " ,ecrate1 :"+ecrate1);*/
			
			
			if (mCursor != null && mCursor.moveToFirst()) {
				mCursor.moveToFirst();
				logger.debug("Duplicate*************************tdate :"+ tdate +" : tariffcode:"+tariffcode);
			} else {
				//logger.debug(" ELSE *************************tdate :"+ tdate +" : tariffcode:"+tariffcode);
				ContentValues contentValues = new ContentValues();
				contentValues.put( KEY_TDATE  , tdate );
				contentValues.put( KEY_TARIFFCODE  , tariffcode );			
				contentValues.put( KEY_TARIFFDESC  , tariffdesc );
				contentValues.put( KEY_TARIFFDCB , tariffdesc );
				contentValues.put( KEY_ACCOUNTCODE   , accountcode);
				contentValues.put( KEY_TARIFFSC   , tariffsc );
				contentValues.put( KEY_VOLTAGECLASS    , voltageclass );
				contentValues.put( KEY_FEC    , fec );
				contentValues.put( KEY_TAX   ,tax );
				contentValues.put( KEY_FCSLAB1   ,fcslab1 );
				contentValues.put( KEY_FCRATE1   , fcrate1);
				contentValues.put( KEY_FCSLAB2  , fcslab2);
				contentValues.put( KEY_FCRATE2   , fcrate2 );
				contentValues.put( KEY_FCSLAB3   , fcslab3 );
				contentValues.put( KEY_FCRATE3   , fcrate3 );
				contentValues.put( KEY_FCSLAB4   , fcslab4);
				contentValues.put( KEY_FCRATE4   , fcrate4  ); 
				contentValues.put( KEY_ECSLAB1   , ecslab1  );
				contentValues.put( KEY_ECRATE1   ,ecrate1);
				contentValues.put( KEY_ECSLAB2   , ecslab2  );
				contentValues.put( KEY_ECRATE2   , ecrate2);
				contentValues.put( KEY_ECSLAB3   , ecslab3  );
				contentValues.put( KEY_ECRATE3   , ecrate3);
				contentValues.put( KEY_ECSLAB4   , ecslab4  );
				contentValues.put( KEY_ECRATE4   , ecrate4);
				contentValues.put( KEY_ECSLAB5   , ecslab5  );
				contentValues.put( KEY_ECRATE5   , ecrate5);
				contentValues.put( KEY_ECSLAB6   , ecslab6  );
				contentValues.put( KEY_ECRATE6   , ecrate6);
				contentValues.put( KEY_PFCODE   ,pfcode);
				contentValues.put( KEY_FCREDUCTION   , fcreduction );
				contentValues.put( KEY_ECREDUCTION   , ecreduction   );
				contentValues.put( KEY_FCMAX   , fcmax   );
				contentValues.put( KEY_ECMAX   , ecmax   );
				contentValues.put( KEY_CALFC   , calfc   );
				contentValues.put( KEY_CALEC   , calec   );
				contentValues.put( KEY_METERINSTALLATION   , meterinstallation);
				contentValues.put( KEY_REMARKS   , remarks   );
				contentValues.put( KEY_CBTARIFF   , cbtariff   );
				contentValues.put( KEY_RECTARIFF   , rectariff   );
				contentValues.put( KEY_PFTARIFF   , pftariff   );
				contentValues.put( KEY_EDPENALTY   , edpenalty   );
				contentValues.put( KEY_INTRESTRATE  , interstate  );
				contentValues.put( KEY_USERNAME   , username   );
				contentValues.put( KEY_DATESTAMP   , datestamp   );
				
				contentValues.put( KEY_TARIFFSLNO   , tariffslno   );
				contentValues.put( KEY_TARIFFFCBASIS  , tarifffcbasis  );


				//logger.debug("insert");
				tariffDatabase.insert(MYDATABASE_TABLE, null, contentValues);
				//mCursor.close();
				tariffDatabase.close();

			}
			mCursor.close();
			// tariffDatabase.close();
		}

		else {
			
			ContentValues contentValues = new ContentValues();
			contentValues.put( KEY_TDATE  , tdate );
			contentValues.put( KEY_TARIFFCODE  , tariffcode );			
			contentValues.put( KEY_TARIFFDESC  , tariffdesc );
			contentValues.put( KEY_TARIFFDCB , tariffdesc );
			contentValues.put( KEY_ACCOUNTCODE   , accountcode);
			contentValues.put( KEY_TARIFFSC   , tariffsc );
			contentValues.put( KEY_VOLTAGECLASS    , voltageclass );
			contentValues.put( KEY_FEC    , fec );
			contentValues.put( KEY_TAX   ,tax );
			contentValues.put( KEY_FCSLAB1   ,fcslab1 );
			contentValues.put( KEY_FCRATE1   , fcrate1);
			contentValues.put( KEY_FCSLAB2  , fcslab2);
			contentValues.put( KEY_FCRATE2   , fcrate2 );
			contentValues.put( KEY_FCSLAB3   , fcslab3 );
			contentValues.put( KEY_FCRATE3   , fcrate3 );
			contentValues.put( KEY_FCSLAB4   , fcslab4);
			contentValues.put( KEY_FCRATE4   , fcrate4  ); 
			contentValues.put( KEY_ECSLAB1   , ecslab1  );
			contentValues.put( KEY_ECRATE1   ,ecrate1);
			contentValues.put( KEY_ECSLAB2   , ecslab2  );
			contentValues.put( KEY_ECRATE2   , ecrate2);
			contentValues.put( KEY_ECSLAB3   , ecslab3  );
			contentValues.put( KEY_ECRATE3   , ecrate3);
			contentValues.put( KEY_ECSLAB4   , ecslab4  );
			contentValues.put( KEY_ECRATE4   , ecrate4);
			contentValues.put( KEY_ECSLAB5   , ecslab5  );
			contentValues.put( KEY_ECRATE5   , ecrate5);
			contentValues.put( KEY_ECSLAB6   , ecslab6  );
			contentValues.put( KEY_ECRATE6   , ecrate6);
			contentValues.put( KEY_PFCODE   ,pfcode);
			contentValues.put( KEY_FCREDUCTION   , fcreduction );
			contentValues.put( KEY_ECREDUCTION   , ecreduction   );
			contentValues.put( KEY_FCMAX   , fcmax   );
			contentValues.put( KEY_ECMAX   , ecmax   );
			contentValues.put( KEY_CALFC   , calfc   );
			contentValues.put( KEY_CALEC   , calec   );
			contentValues.put( KEY_METERINSTALLATION   , meterinstallation);
			contentValues.put( KEY_REMARKS   , remarks   );
			contentValues.put( KEY_CBTARIFF   , cbtariff   );
			contentValues.put( KEY_RECTARIFF   , rectariff   );
			contentValues.put( KEY_PFTARIFF   , pftariff   );
			contentValues.put( KEY_EDPENALTY   , edpenalty   );
			contentValues.put( KEY_INTRESTRATE  , interstate  );
			contentValues.put( KEY_USERNAME   , username   );
			contentValues.put( KEY_DATESTAMP   , datestamp   );
			
			contentValues.put( KEY_TARIFFSLNO   , tariffslno   );
			contentValues.put( KEY_TARIFFFCBASIS  , tarifffcbasis  );


			//logger.debug("insert");
			tariffDatabase.insert(MYDATABASE_TABLE, null, contentValues);
		
			tariffDatabase.close();

		}

	}

	/**
	 * Check if the database exist
	 * 
	 * @return true if it exists, false if it doesn't
	 */
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

	public boolean isTariffDataValid(){
		boolean flag = false ;
		if(checkDataBase()){
			String q = "SELECT  * FROM " + MYDATABASE_TABLE + "  LIMIT 1 ;";

			logger.debug("query :- " + q);
			try {
			Cursor mCursor = tariffDatabase.rawQuery(q, null);
			if (mCursor != null && mCursor.moveToFirst()) {
				flag = true ;
			}else{
				flag = false;
			}
			mCursor.close();
			} catch (Exception e) {
				flag = false;
				e.printStackTrace();
			}
		}
		return flag ;
	}
	
	public int deleteAll() {
		return tariffDatabase.delete(MYDATABASE_TABLE, null, null);
	}

	
	public int ChangeStatus() throws SQLException {
		int flag = 0;
		tariffDatabase.close();
		return flag;
	}

	

	public class SQLiteHelper extends SQLiteOpenHelper {

		public SQLiteHelper(Context context, String name,
				CursorFactory factory, int version) {

			super(context, name, factory, version);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			db.execSQL(SCRIPT_CREATE_DATABASE);
			
			db.execSQL("	CREATE INDEX "+MYDATABASE_TABLE+"INDEX ON  "+MYDATABASE_TABLE+" ( TDATE , TARIFFCODE , ID ) ; " ) ;
			
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS " + MYDATABASE_TABLE);
		}

	}
	

	
	public HashMap<String, String> getAllTariffCodeWithDesc(){
		HashMap<String, String> map = new HashMap<String, String>();
		String desc = "-";/*SELECT  trim(TARIFFCODE) , TARIFFDESC FROM "CESC_TARIFF" GROUP BY  TARIFFCODE*/
		String q = "SELECT  trim("+KEY_TARIFFCODE+") , trim("+KEY_TARIFFDESC+")  FROM "+MYDATABASE_TABLE+"  GROUP BY  "+KEY_TARIFFCODE+" ; " ;
		Cursor mCursor = tariffDatabase.rawQuery(q, null);
		if (mCursor != null && mCursor.moveToFirst()) {
			do {
				map.put(mCursor.getString(0), mCursor.getString(1));
		//	desc = mCursor.getString(mCursor.getColumnIndex(KEY_TARIFFDESC));
			
			 } while (mCursor.moveToNext());
		}
		return map ;
	}
	public Cursor getTariffDetails(String tariffCode ) throws SQLException {
		
		logger.debug("MYDATABASE_TABLE  :- "+MYDATABASE_TABLE );
		String q = "SELECT * FROM "+MYDATABASE_TABLE+" WHERE TARIFFCODE='"+tariffCode+"' ORDER BY date( TDATE ) DESC;";
		logger.debug("query :- "+q);
		Cursor mCursor = tariffDatabase.rawQuery(q, null);
		if (mCursor != null) {
			mCursor.moveToFirst();

		}
		tariffDatabase.close();
		return mCursor;
	}
	
	
	
	public String getTariffDesc(String tCode){
		String desc = "-";
		String q = "SELECT  "+KEY_TARIFFDESC+"  FROM "+MYDATABASE_TABLE+" WHERE  "+KEY_TARIFFCODE+" ='"+tCode+"' ORDER BY TDATE DESC  LIMIT 1 ; " ;
		Cursor mCursor = tariffDatabase.rawQuery(q, null);
		if (mCursor != null && mCursor.moveToFirst()) {
			desc = mCursor.getString(mCursor.getColumnIndex(KEY_TARIFFDESC));
		}
		return desc ;
	}
	
	
	
	/**
	 * @param tariffCode
	 * @return Cursor For TDATE : yyyy-MM-dd
	 */
	public Cursor getTdateforECFC(String tariffCode)
	{
		String q =  "SELECT SUBSTR(TRIM(TDATE) , 0, 11) as TDATE  FROM "+MYDATABASE_TABLE+" WHERE TRIM(TARIFFCODE) ='"+tariffCode+"' ORDER BY TDATE DESC;" ;
		logger.debug("query for Tdate : "+ q);
		Cursor mCursor = tariffDatabase.rawQuery(q, null);
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		tariffDatabase.close();
		return mCursor;
	}
	
	

}
