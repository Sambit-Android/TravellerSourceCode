package com.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

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
import android.util.Log;

import com.bcits.bsmartbilling.MasterLibraryFunction;
import com.utils.helper.BackupHelper;
import com.utils.helper.DetailedReportHelper;
import com.utils.helper.MStatusReportHelper;
import com.utils.helper.UnbilledViewPojo;
import com.utils.helper.UploadHandler;

public class DatabaseHandler {

	static Logger logger ;
	 private static String TAG = "DatabaseHandler";
	
	static File dir = new File(Environment.getExternalStorageDirectory() + "");
	private static String MYDATABASE_NAME = dir + "/CESCTRM/CESCTRMELECTRICITYBOARD.db";

	
	public static final String MYDATABASE_TABLE = "CONSUMER_INPUT_LIVE";
	public static final int MYDATABASE_VERSION = 1;
	public static final String KEY_ID = "ID";
	public static final String KEY_CONSUMER_SC_NO = "CONSUMER_SC_NO";                   
	public static final String KEY_METER_CONSTANT = "METER_CONSTANT";
	public static final String KEY_CONSUMER_NAME = "CONSUMER_NAME";
	public static final String KEY_ADDRESS = "ADDRESS";
	public static final String KEY_ADDRESS1 = "ADDRESS1";
	public static final String KEY_TARIFF = "TARIFF";
	public static final String KEY_TARIFFDESC = "TARIFFDESC";
	public static final String KEY_LEDGER_NO = "LEDGER_NO";
	public static final String KEY_FOLIO_NO = "FOLIO_NO";
	public static final String KEY_CONNECTED_LOAD = "CONNECTED_LOAD";
	public static final String KEY_D_AND_R_FEE = "D_AND_R_FEE";
	public static final String KEY_ARREARS = "ARREARS";
	public static final String KEY_INTEREST = "INTEREST";
	public static final String KEY_OTHERS = "OTHERS";
	public static final String KEY_BACKBILLARR = "BACKBILLARR";
	public static final String KEY_AVERAGE_CONSUMPTION = "AVERAGE_CONSUMPTION";
	public static final String KEY_DL_OR_MNR_PREV_MONTH = "DL_OR_MNR_PREV_MONTH";
	public static final String KEY_PREVIOUS_READING = "PREVIOUS_READING";
	public static final String KEY_PRESENTREADING = "PRESENT_READING";
	public static final String KEY_CONSUMPTION = "CONSUMPTION";
	public static final String KEY_POWER_FACTOR = "POWER_FACTOR";
	public static final String KEY_READING_DATE = "READING_DATE";
	public static final String KEY_METER_CHANGE_UNITS_CONSUMED = "METER_CHANGE_UNITS_CONSUMED";
	public static final String KEY_NO_OF_MONTHS_ISSUED = "NO_OF_MONTHS_ISSUED";
	public static final String KEY_CREDIT_OR_REBATE = "CREDIT_OR_REBATE";
	public static final String KEY_FIXED_GES = "FIXED_GES";
	public static final String KEY_AUDIT_ARREARS = "AUDIT_ARREARS";
	public static final String KEY_OLD_INTEREST = "OLD_INTEREST";
	public static final String KEY_TRIVECTOR = "TRIVECTOR";
	public static final String KEY_CKWHLKWH = "CKWHLKWH";
	public static final String KEY_DOORLOCKAVG = "DOORLOCKAVG";
	public static final String KEY_CONSUMERCODE = "CONSUMERCODE";
	public static final String KEY_ADDITIONALDEP = "ADDITIONALDEP";
	public static final String KEY_MRCODE = "MRCODE";
	public static final String KEY_BILLMONTH = "BILLMONTH";
	public static final String KEY_SITECODE = "SITECODE";
	public static final String KEY_SYNCSTATUS = "SYNCSTATUS";
	public static final String KEY_DATAPREPAREDDATE = "DATAPREPAREDDATE";
	public static final String KEY_SERVERTOSBMDATE = "SERVERTOSBMDATE";
	public static final String KEY_METERNO = "METERNO";
	public static final String KEY_INTERESTONDEPOSIT = "INTERESTONDEPOSIT";
	public static final String KEY_DL_ADJ = "DL_ADJ";
	public static final String KEY_DL_COUNT = "DL_COUNT";
	public static final String KEY_METERRENT = "METERRENT";
	public static final String KEY_FPPCA = "FPPCA";
	public static final String KEY_EXTRA1 = "EXTRA1";
	public static final String KEY_EXTRA2 = "EXTRA2";
	public static final String KEY_BILLNO = "BILLNO";
	public static final String KEY_STATUS = "STATUS";
	public static final String KEY_BILLEDSTATUS = "BILLEDSTATUS";
	//public String exist = "0";
	public static final String  KEY_TAX = "TAX";
    public static final String  KEY_EC = "KEY_EC";
	public static final  String  KEY_FC = "KEY_FC";
	public static final	String	 KEY_TOTAL = "KEY_TOATL";
	
	public static final	String	 KEY_ROUNDOFF = "ROUNDOFF";
	public static final	String	 KEY_ROUNDOFF_TOTAL = "KEY_ROUNDOFFTOATL";
	
	
	public static final	String	 KEY_PREBILLDATE = "KEY_PREBILLDATE";
	public static final	String	 KEY_BILLDATE = "KEY_BILLDATE";
	public static final	String	 KEY_DUEDATE = "KEY_DUEDATE";
	public static final String KEY_PRESENTMETERSTATUS = "PRESENTMETERSTATUS";
	
	public static final String KEY_CYCLENAME = "CYCLENAME";
	public static final String KEY_CONSUMER_KEY = "CONSUMER_KEY";
	public static final String KEY_INSTALLATIONO = "INSTALLATIONO";
	public static final String KEY_CONSUMERNO = "CONSUMERNO";  
	
	public static final String KEY_DIVISION = "DIVISION";
	public static final String KEY_SUBDIVISION = "SUBDIVISION";
	public static final String KEY_BOOKNO = "BOOKNO";
	
	public static final String KEY_DEVICEFIRMWAREVERSION = "DEVICEFIRMWAREVERSION";
	public static final String KEY_BILLEDATETIMESTAMP = "BILLEDATETIMESTAMP";
	
/** 3 new field added**********************/	
	public static final String PREVIOUS_BILLEDDATE = "PREVIOUS_READING_DATE";
	public static final String ACTUAL_PREVIOUS_BILLEDDATE = "PREVIOUS_ACTUAL_READING_DATE" ;
	
	public static String SEASONAL_CONSUMER = "SEASONAL_CONSUMER";
	public static String KEY_LINEMINIMUM = "LINEMINIMUM";
	public static String KEY_LIGPOINTS = "LIGPOINTS";
	public static String KEY_METERED = "METERED";
	



/** 8 new field added **********************/	
	public static final String KEY_PF_READING = "PF_READING";
	public static final String KEY_PF_PENALITY = "PF_PENALITY";
	
	public static final String KEY_BMD_PENALITY = "BMD_PENALITY";
	public static final String KEY_BMD_READING_PENALITY = "BMD_READING_PENALITY";
	
	public static final String  KEY_IMAGE= 	"IMAGE" ;
	public static final String  KEY_IMAGEPATH= 	"IMAGEPATH" ;
	
	public static final String  KEY_LONGITUDE= 	"LONGITUDE" ;
	public static final String  KEY_LATTITUDE= 	"LATTITUDE" ;
	public static final String  KEY_TAKENTIME= 	"TAKENTIME" ;
	
	
	public static final String KEY_EXTRA3 = "EXTRA3";
	public static final String KEY_EXTRA4 = "EXTRA4";
	public static final String KEY_EXTRA5 = "EXTRA5";
	public static final String KEY_EXTRA6 = "EXTRA6";
	public static final String KEY_EXTRA7 = "EXTRA7";
	public static final String KEY_EXTRA8 = "EXTRA8";
	

	// create table MY_DATABASE (ID integer primary key, Content text not null);
	private static final String SCRIPT_CREATE_DATABASE = "create table "
			+ MYDATABASE_TABLE + " (" + KEY_ID
			+ " integer primary key autoincrement, "
			+ KEY_CONSUMER_SC_NO + " text , "
			+ KEY_METER_CONSTANT + " text , " 
			+ KEY_CONSUMER_NAME + " text , "
			+ KEY_ADDRESS + " text , " 
			+ KEY_ADDRESS1 + " text , " 
			+ KEY_TARIFF	+ " text , " 
			+ KEY_TARIFFDESC	+ " text , "
			+ KEY_LEDGER_NO + " text , " 
			+ KEY_FOLIO_NO + " text , "
			+ KEY_CONNECTED_LOAD + " text , " 
			+ KEY_D_AND_R_FEE + " text , " 
			+ KEY_ARREARS + " text , " 
			+ KEY_INTEREST + " text , "
			+ KEY_OTHERS + " text , " 
			+ KEY_BACKBILLARR + " text , "
			+ KEY_AVERAGE_CONSUMPTION + " text , " 
			+ KEY_DL_OR_MNR_PREV_MONTH + " text , " 
			+ KEY_PREVIOUS_READING+ " text , " 
			+ KEY_PRESENTREADING+ " text , "
			+ KEY_CONSUMPTION+ " text , "
			+ KEY_POWER_FACTOR + " text , " 
			+ KEY_READING_DATE + " text , "
			+ KEY_METER_CHANGE_UNITS_CONSUMED + " text , " 
			+ KEY_NO_OF_MONTHS_ISSUED + " text , "
			+ KEY_CREDIT_OR_REBATE + " text , " 
			+ KEY_FIXED_GES + " text , "
			+ KEY_AUDIT_ARREARS + " text , " 
			+ KEY_OLD_INTEREST + " text , "
			+ KEY_TRIVECTOR + " text , "    
			+ KEY_CKWHLKWH + " text , "  
			+ KEY_DOORLOCKAVG + " text , "
			+ KEY_CONSUMERCODE + " text , " 
			+ KEY_ADDITIONALDEP + " text , "
			+ KEY_MRCODE + " text , " 
			+ KEY_BILLMONTH + " text , " 
			+ KEY_SITECODE	+ " text , " 
			+ KEY_SYNCSTATUS + " text , " 
			+ KEY_DATAPREPAREDDATE	+ " text , " 
			+ KEY_SERVERTOSBMDATE + " text , " 
			+ KEY_METERNO + " text , " 
			+ KEY_INTERESTONDEPOSIT + " text , "
			+ KEY_DL_ADJ+ " text ,"  
			+ KEY_DL_COUNT+ " text ,"
			+ KEY_METERRENT+" text, " 
			+KEY_FPPCA +" text, " 
			+KEY_TAX +" text, " 
			+KEY_EC +" text, "
			+KEY_FC +" text, "
			+KEY_TOTAL +" text, "  
			+KEY_ROUNDOFF +" text, "
			+KEY_ROUNDOFF_TOTAL +" text, "
			
			+KEY_PREBILLDATE +" text, "
			+KEY_BILLDATE +" text, "
			+KEY_DUEDATE +" text, "
			+KEY_EXTRA1+" text,"
			+KEY_EXTRA2 + " text ," 
			
			+KEY_CYCLENAME + " text ,"
			+KEY_CONSUMER_KEY + " text ,"
			+KEY_INSTALLATIONO + " text ,"
			+KEY_CONSUMERNO + " text ,"  
			+KEY_DIVISION + " text ,"  
			+KEY_SUBDIVISION + " text ," 
			
			+KEY_BOOKNO + " text ,"
			
			+KEY_DEVICEFIRMWAREVERSION + " text ,"  
			+KEY_BILLEDATETIMESTAMP + " text ,"
			
			
			
			+KEY_BILLNO + " text ,"	
			+KEY_PRESENTMETERSTATUS + " text ,"	
			+KEY_BILLEDSTATUS + " text ,"
			+ KEY_STATUS + " text ,"
			+PREVIOUS_BILLEDDATE + " text ,"
			+ACTUAL_PREVIOUS_BILLEDDATE + " text ,"
			+KEY_LINEMINIMUM + " text ,"
			+SEASONAL_CONSUMER + " text," 
			+KEY_LIGPOINTS +		"  text ," 
			+KEY_METERED +		"  text ," 
			
			+KEY_PF_READING +		"  text ," 
			+KEY_PF_PENALITY +		"  text ,"  
			
			+KEY_BMD_PENALITY +		"  text ,"
			+KEY_BMD_READING_PENALITY +		"  text ,"
			
			+KEY_IMAGE  + " blob , "  
			+KEY_IMAGEPATH +		"  text ," 
			
			+KEY_LONGITUDE +		"  text ," 
			+KEY_LATTITUDE +		"  text ," 
			+KEY_TAKENTIME +		"  text ," 
			
			+KEY_EXTRA3 +		"  text ," 
			+KEY_EXTRA4 +		"  text ," 
			+KEY_EXTRA5 +		"  text ," 
			+KEY_EXTRA6 +		"  text ," 
			+KEY_EXTRA7 +		"  text ," 
			+KEY_EXTRA8+		"  text  );"
			
			/*+KEY_METERED+		"  text  );"*/
			;

	private SQLiteHelper sqLiteHelper;
	private SQLiteDatabase sqLiteDatabase;

	private Context context;

	public DatabaseHandler(Context c) {
		context = c;
		 if(logger == null){
	    		logger = MasterLibraryFunction.getlogger(context, TAG);
	    		logger.info("In Side DatabaseHandler (Context c)");
		 }
	        
	}

	public DatabaseHandler openToRead() throws android.database.SQLException {
		sqLiteHelper = new SQLiteHelper(context, MYDATABASE_NAME, null,
				MYDATABASE_VERSION);
		sqLiteDatabase = sqLiteHelper.getReadableDatabase();
		return this;
	}

	public DatabaseHandler openToWrite() throws android.database.SQLException {
		sqLiteHelper = new SQLiteHelper(context, MYDATABASE_NAME, null,
				MYDATABASE_VERSION);
		sqLiteDatabase = sqLiteHelper.getWritableDatabase();
		return this;
	}

	public void close() {
		if(sqLiteHelper!=null)
		sqLiteHelper.close();
	}
  

	
	
	public void insert(String KEY_CONSUMER_SC_NO1 ,
			 String KEY_METER_CONSTANT1 ,
			 String KEY_CONSUMER_NAME1 ,
			 String ADDRESS1 ,
			 String ADDRESS11 ,
			 String KEY_TARIFF1 ,
			String  KEY_TARIFFDESC1,
			 String KEY_LEDGER_NO1 ,
			 String KEY_FOLIO_NO1 ,
			 String KEY_CONNECTED_LOAD1 ,
			 String KEY_D_AND_R_FEE1 ,
			 String KEY_ARREARS1 ,
			 String KEY_INTEREST1 ,
			 String KEY_OTHERS1 ,
			 String KEY_BACKBILLARR1 ,
			 String KEY_AVERAGE_CONSUMPTION1 ,
			 String KEY_DL_OR_MNR_PREV_MONTH1 ,
			 String KEY_PREVIOUS_READING1 ,
			 String KEY_POWER_FACTOR1 ,
			 String KEY_READING_DATE1 ,
			 String KEY_METER_CHANGE_UNITS_CONSUMED1 ,
			 String KEY_NO_OF_MONTHS_ISSUED1 ,
			 String KEY_CREDIT_OR_REBATE1 ,
			 String KEY_FIXED_GES1 ,

			 String KEY_AUDIT_ARREARS1 ,
			 String KEY_OLD_INTEREST1 ,
			 String KEY_TRIVECTOR1 ,
			 String  ckwhlkwh, 
			 String KEY_DOORLOCKAVG1 ,
			 String KEY_CONSUMERCODE1 ,
			 String KEY_ADDITIONALDEP1 ,
			 String KEY_MRCODE1 ,
			 String KEY_BILLMONTH1 ,
			 String KEY_SITECODE1 ,
			 String KEY_SYNCSTATUS1 ,
			 String KEY_DATAPREPAREDDATE1 ,
			 String KEY_SERVERTOSBMDATE1,
			 String KEY_METERNO1 ,
			 String KEY_INTERESTONDEPOSIT1 ,
			 String KEY_DL_ADJ1 ,
			 String KEY_DL_COUNT1,
			 String KEY_METERRENT1 ,
			 String KEY_FPPCA1 ,
			 String KEY_EXTRA11 ,
			 String KEY_EXTRA21 ,
			String key_cyclename ,
			String key_consumer_key ,
			String key_installationo ,
			String key_consumerno ,
			
			String key_division ,
			String key_subdivision ,
			
			String key_bookno,
			String key_devicefirmwareversion, 
		String 	Key_billedatetimestamp,
			
			
			String  key_billno ,
			   String  key_status1 ,
			   String  key_billedstatus ,
			 
			   String previous_bdate,
			   String actual_previous_bdate,
			   String lineMinimum,
			   String seasonal_consumer,
			   String ligPoints,
			String metered
			, String key_extra6 
			) {
		logger.debug("insert");

		boolean db_status = checkDataBase();
		logger.debug("db_status" + db_status);

		if (db_status) {

			System.out
					.println("step 1************************************************************");

			String q = "SELECT CONSUMER_SC_NO FROM " + MYDATABASE_TABLE
					+ " WHERE CONSUMER_SC_NO ='" + KEY_CONSUMER_SC_NO1 + "' ;";
			logger.debug("check ****************   "+q);
			Cursor mCursor = sqLiteDatabase.rawQuery(q, null);
			if (mCursor != null && mCursor.moveToFirst()) {
				mCursor.moveToFirst();
				System.out
						.println("step 2************************************************************");

			} else {
				System.out
						.println("step 3************************************************************");
				
				logger.debug("check status **********  " +key_status1);
				
				 logger.debug("Check bluetooth value *****************KEY_STATUS*********  : "+key_status1);
				 logger.debug("Check bluetooth value *************KEY_BILLEDSTATUS**************  : "+KEY_BILLEDSTATUS);
				
				
				System.out
						.println("rrno no exits............*******************************************************");
				ContentValues contentValues = new ContentValues();
				contentValues.put( KEY_CONSUMER_SC_NO , KEY_CONSUMER_SC_NO1 );
				 contentValues.put( KEY_METER_CONSTANT,KEY_METER_CONSTANT1);
				 contentValues.put( KEY_CONSUMER_NAME,KEY_CONSUMER_NAME1 );
				 contentValues.put( KEY_ADDRESS ,ADDRESS1 );
				 contentValues.put( KEY_ADDRESS1 ,ADDRESS11 );
				 contentValues.put( KEY_TARIFF,KEY_TARIFF1 );  
				 contentValues.put( KEY_TARIFFDESC,KEY_TARIFFDESC1 );
				 contentValues.put( KEY_LEDGER_NO ,KEY_LEDGER_NO1 );
				 contentValues.put( KEY_FOLIO_NO ,KEY_FOLIO_NO1 );
				 contentValues.put( KEY_CONNECTED_LOAD , KEY_CONNECTED_LOAD1 );
				 contentValues.put( KEY_D_AND_R_FEE, KEY_D_AND_R_FEE1 );
				 contentValues.put( KEY_ARREARS , KEY_ARREARS1 );
				 contentValues.put( KEY_INTEREST , KEY_INTEREST1 );
				 contentValues.put( KEY_OTHERS, KEY_OTHERS1 );
				 contentValues.put( KEY_BACKBILLARR, KEY_BACKBILLARR1 );
				 contentValues.put( KEY_AVERAGE_CONSUMPTION , KEY_AVERAGE_CONSUMPTION1 );
				 contentValues.put( KEY_DL_OR_MNR_PREV_MONTH , KEY_DL_OR_MNR_PREV_MONTH1 );
				 contentValues.put( KEY_PREVIOUS_READING , KEY_PREVIOUS_READING1 );
				 contentValues.put( KEY_PRESENTREADING, "0");
				 contentValues.put( KEY_CONSUMPTION, "0");
				 contentValues.put( KEY_POWER_FACTOR , KEY_POWER_FACTOR1 );
				 contentValues.put( KEY_READING_DATE, KEY_READING_DATE1 );
				 contentValues.put( KEY_METER_CHANGE_UNITS_CONSUMED , KEY_METER_CHANGE_UNITS_CONSUMED1 );
				 contentValues.put( KEY_NO_OF_MONTHS_ISSUED , KEY_NO_OF_MONTHS_ISSUED1 );
				 contentValues.put( KEY_CREDIT_OR_REBATE , KEY_CREDIT_OR_REBATE1 );
				 contentValues.put( KEY_FIXED_GES, KEY_FIXED_GES1 );

				 contentValues.put( KEY_AUDIT_ARREARS , KEY_AUDIT_ARREARS1 );
				 contentValues.put( KEY_OLD_INTEREST, KEY_OLD_INTEREST1 );
				 contentValues.put( KEY_TRIVECTOR , KEY_TRIVECTOR1 );  
				 contentValues.put( KEY_CKWHLKWH , ckwhlkwh ); 
				 contentValues.put( KEY_DOORLOCKAVG , KEY_DOORLOCKAVG1 );
				 contentValues.put( KEY_CONSUMERCODE , KEY_CONSUMERCODE1 );
				 contentValues.put( KEY_ADDITIONALDEP , KEY_ADDITIONALDEP1 );
				 contentValues.put( KEY_MRCODE , KEY_MRCODE1 );
				 contentValues.put( KEY_BILLMONTH , KEY_BILLMONTH1 );
				 contentValues.put( KEY_SITECODE , KEY_SITECODE1 );
				 contentValues.put( KEY_SYNCSTATUS, KEY_SYNCSTATUS1 );
				 contentValues.put( KEY_DATAPREPAREDDATE , KEY_DATAPREPAREDDATE1 );
				 contentValues.put( KEY_SERVERTOSBMDATE, KEY_SERVERTOSBMDATE1 );
				 contentValues.put( KEY_METERNO , KEY_METERNO1 );
				 contentValues.put( KEY_INTERESTONDEPOSIT ,  KEY_INTERESTONDEPOSIT1 );
				 contentValues.put( KEY_DL_ADJ ,  KEY_DL_ADJ1 );
				 contentValues.put( KEY_DL_COUNT ,  KEY_DL_COUNT1 );
				 contentValues.put( KEY_METERRENT,  KEY_METERRENT1 );
				 contentValues.put( KEY_FPPCA,  KEY_FPPCA1 );  
				 contentValues.put( KEY_TAX,  "0" ); 
				 contentValues.put( KEY_EC,  "0" );
				 contentValues.put( KEY_FC,  "0" );
				 contentValues.put( KEY_TOTAL,  "0" ); 
				 contentValues.put( KEY_ROUNDOFF,  "0" );
				 contentValues.put( KEY_ROUNDOFF_TOTAL,  "0" );
				 contentValues.put( KEY_PREBILLDATE,  "-" );
				 contentValues.put( KEY_BILLDATE,  "-" );
				 contentValues.put( KEY_DUEDATE,  "-" );
				 contentValues.put( KEY_EXTRA1,  KEY_EXTRA11 );
				 contentValues.put( KEY_EXTRA2  , KEY_EXTRA21 );
				 
				 contentValues.put( KEY_CYCLENAME  , key_cyclename );
				 contentValues.put( KEY_CONSUMER_KEY  , key_consumer_key );
				 contentValues.put( KEY_INSTALLATIONO  , key_installationo );
				 contentValues.put( KEY_CONSUMERNO  , key_consumerno );
				 
				 contentValues.put( KEY_DIVISION   , key_division  );
				 contentValues.put( KEY_SUBDIVISION  , key_subdivision );
				 
				 contentValues.put( KEY_BOOKNO  , key_bookno );
				 contentValues.put( KEY_DEVICEFIRMWAREVERSION    , key_devicefirmwareversion  );   
				 
				 
				 contentValues.put( KEY_BILLEDATETIMESTAMP    , Key_billedatetimestamp  ); 
				 
				 contentValues.put( this.KEY_BILLNO, key_billno );
				 contentValues.put( this.KEY_BILLEDSTATUS, key_billedstatus );
				 contentValues.put(this. KEY_STATUS  , key_status1 );
				 contentValues.put( KEY_PRESENTMETERSTATUS , "" );
				 contentValues.put( PREVIOUS_BILLEDDATE , previous_bdate );
				 contentValues.put( ACTUAL_PREVIOUS_BILLEDDATE , actual_previous_bdate );
				 contentValues.put( SEASONAL_CONSUMER , seasonal_consumer );
				 contentValues.put( KEY_LINEMINIMUM , lineMinimum );
				 contentValues.put( KEY_LIGPOINTS , ligPoints );
				 contentValues.put( KEY_METERED , metered );
				 
			 contentValues.put( KEY_PF_READING ,KEY_POWER_FACTOR1 );
				 contentValues.put( KEY_PF_PENALITY , "0" ); 
				 contentValues.put( KEY_BMD_PENALITY , "0" ); 
				 contentValues.put( KEY_BMD_READING_PENALITY , "0" ); 
				 
				 contentValues.put( KEY_LONGITUDE , "-" );
				 contentValues.put( KEY_LATTITUDE , "-" );
				 contentValues.put( KEY_TAKENTIME , "-" );
				 
				 
				 
				 contentValues.put( KEY_EXTRA3 , "0" );
				 contentValues.put( KEY_EXTRA4 , "0" );
				 contentValues.put( KEY_EXTRA5 , "0");
				 contentValues.put( KEY_EXTRA6 , key_extra6 );
				 contentValues.put( KEY_EXTRA7 , "0" );
				 contentValues.put( KEY_EXTRA8 , "0" );
				 

				logger.debug("insert");
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
			contentValues.put( KEY_CONSUMER_SC_NO , KEY_CONSUMER_SC_NO1 );
			 contentValues.put( KEY_METER_CONSTANT,KEY_METER_CONSTANT1);
			 contentValues.put( KEY_CONSUMER_NAME,KEY_CONSUMER_NAME1 );
			 contentValues.put( KEY_ADDRESS ,ADDRESS1 );
			 contentValues.put( KEY_ADDRESS1 ,ADDRESS11 );
			 contentValues.put( KEY_TARIFF,KEY_TARIFF1 );
			 contentValues.put( KEY_LEDGER_NO ,KEY_LEDGER_NO1 );
			 contentValues.put( KEY_FOLIO_NO ,KEY_FOLIO_NO1 );
			 contentValues.put( KEY_CONNECTED_LOAD , KEY_CONNECTED_LOAD1 );
			 contentValues.put( KEY_D_AND_R_FEE, KEY_D_AND_R_FEE1 );
			 contentValues.put( KEY_ARREARS , KEY_ARREARS1 );
			 contentValues.put( KEY_INTEREST , KEY_INTEREST1 );
			 contentValues.put( KEY_OTHERS, KEY_OTHERS1 );
			 contentValues.put( KEY_BACKBILLARR, KEY_BACKBILLARR1 );
			 contentValues.put( KEY_AVERAGE_CONSUMPTION , KEY_AVERAGE_CONSUMPTION1 );
			 contentValues.put( KEY_DL_OR_MNR_PREV_MONTH , KEY_DL_OR_MNR_PREV_MONTH1 );
			 contentValues.put( KEY_PREVIOUS_READING , KEY_PREVIOUS_READING1 );
			 contentValues.put(KEY_PRESENTREADING, "0");
			 contentValues.put( KEY_CONSUMPTION, "0");
			 contentValues.put( KEY_POWER_FACTOR , KEY_POWER_FACTOR1 );
			 contentValues.put( KEY_READING_DATE, KEY_READING_DATE1 );
			 contentValues.put( KEY_METER_CHANGE_UNITS_CONSUMED , KEY_METER_CHANGE_UNITS_CONSUMED1 );
			 contentValues.put( KEY_NO_OF_MONTHS_ISSUED , KEY_NO_OF_MONTHS_ISSUED1 );
			 contentValues.put( KEY_CREDIT_OR_REBATE , KEY_CREDIT_OR_REBATE1 );
			 contentValues.put( KEY_FIXED_GES, KEY_FIXED_GES1 );

			 contentValues.put( KEY_AUDIT_ARREARS , KEY_AUDIT_ARREARS1 );
			 contentValues.put( KEY_OLD_INTEREST, KEY_OLD_INTEREST1 );
			 contentValues.put( KEY_TRIVECTOR , KEY_TRIVECTOR1 );
			 contentValues.put( KEY_CKWHLKWH , ckwhlkwh ); 
			 contentValues.put( KEY_DOORLOCKAVG , KEY_DOORLOCKAVG1 );
			 contentValues.put( KEY_CONSUMERCODE , KEY_CONSUMERCODE1 );
			 contentValues.put( KEY_ADDITIONALDEP , KEY_ADDITIONALDEP1 );
			 contentValues.put( KEY_MRCODE , KEY_MRCODE1 );
			 contentValues.put( KEY_BILLMONTH , KEY_BILLMONTH1 );
			 contentValues.put( KEY_SITECODE , KEY_SITECODE1 );
			 contentValues.put( KEY_SYNCSTATUS, KEY_SYNCSTATUS1 );
			 contentValues.put( KEY_DATAPREPAREDDATE , KEY_DATAPREPAREDDATE1 );
			 contentValues.put( KEY_SERVERTOSBMDATE, KEY_SERVERTOSBMDATE1 );
			 contentValues.put( KEY_METERNO , KEY_METERNO1 );
			 contentValues.put( KEY_INTERESTONDEPOSIT ,  KEY_INTERESTONDEPOSIT1 );
			 contentValues.put( KEY_DL_ADJ ,  KEY_DL_ADJ1 );
			 contentValues.put( KEY_DL_COUNT ,  KEY_DL_COUNT1 );
			 contentValues.put( KEY_METERRENT,  KEY_METERRENT1 );
			 contentValues.put( KEY_FPPCA,  KEY_FPPCA1 );
			 contentValues.put( KEY_TAX,  "0" ); 
			 contentValues.put( KEY_EC,  "0" );
			 contentValues.put( KEY_FC,  "0" );
			 contentValues.put( KEY_TOTAL,  "0" ); 
			 contentValues.put( KEY_ROUNDOFF,  "0" );
			 contentValues.put( KEY_ROUNDOFF_TOTAL,  "0" );
			 contentValues.put( KEY_PREBILLDATE,  "-" );
			 contentValues.put( KEY_BILLDATE,  "-" );
			 contentValues.put( KEY_DUEDATE,  "-" );
			 contentValues.put( KEY_EXTRA1,  KEY_EXTRA11 );
			 contentValues.put( KEY_EXTRA2  , KEY_EXTRA21 );
			 
			 contentValues.put( KEY_CYCLENAME  , key_cyclename );
			 contentValues.put( KEY_CONSUMER_KEY  , key_consumer_key );
			 contentValues.put( KEY_INSTALLATIONO  , key_installationo );
			 contentValues.put( KEY_CONSUMERNO  , key_consumerno );
			 
			 
			 contentValues.put( KEY_DIVISION   , key_division  );
			 contentValues.put( KEY_SUBDIVISION  , key_subdivision );
			 
			 contentValues.put( KEY_BOOKNO  , key_bookno );
			 contentValues.put( KEY_DEVICEFIRMWAREVERSION    , key_devicefirmwareversion  ); 
			 
			 contentValues.put( KEY_BILLEDATETIMESTAMP    , Key_billedatetimestamp  ); 
			 
			 contentValues.put( this.KEY_BILLNO, key_billno );
			 contentValues.put( this.KEY_BILLEDSTATUS, key_billedstatus );
			 contentValues.put(this. KEY_STATUS  , key_status1 );
			 contentValues.put( KEY_PRESENTMETERSTATUS , "" );
			 
			 contentValues.put( PREVIOUS_BILLEDDATE , previous_bdate );
			 contentValues.put( ACTUAL_PREVIOUS_BILLEDDATE , actual_previous_bdate );
			 contentValues.put( SEASONAL_CONSUMER , seasonal_consumer );
			 contentValues.put( KEY_LINEMINIMUM , lineMinimum );
			 contentValues.put( KEY_LIGPOINTS , ligPoints );
			 contentValues.put( KEY_METERED , metered );
			 
			 contentValues.put( KEY_PF_READING , KEY_POWER_FACTOR1 );
			 contentValues.put( KEY_PF_PENALITY , "0" );
			 contentValues.put( KEY_BMD_PENALITY , "0" ); 
			 contentValues.put( KEY_BMD_READING_PENALITY , "0" ); 
			 contentValues.put( KEY_LONGITUDE , "-" );
			 contentValues.put( KEY_LATTITUDE , "-" );
			 contentValues.put( KEY_TAKENTIME , "-" );
			 contentValues.put( KEY_EXTRA3 , "0" );
			 contentValues.put( KEY_EXTRA4 , "0" );
			 contentValues.put( KEY_EXTRA5 , "0");
			 contentValues.put( KEY_EXTRA6 , key_extra6 );
			 contentValues.put( KEY_EXTRA7 , "0" );
			 contentValues.put( KEY_EXTRA8 , "0" );
			 
			logger.debug("insert");
			sqLiteDatabase.insert(MYDATABASE_TABLE, null, contentValues);
			
			sqLiteDatabase.close();

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

	public int deleteAll() {
		return sqLiteDatabase.delete(MYDATABASE_TABLE, null, null);
	}

	/*
	 * public Cursor queueAll() { String[] columns = new String[] { KEY_ID,
	 * KEY_ACCOUNT_NO, KEY_SDO, KEY_USERNAME, KEY_PASSWORD, KEY_FLAG }; Cursor
	 * cursor = sqLiteDatabase.query(MYDATABASE_TABLE, columns, null, null,
	 * null, null, null);
	 * 
	 * return cursor; }
	 */


	/**************OLD***********************/
	/*
  
 
  	public Cursor checkuser() throws SQLException {
		// System.out.println("check user" + username + password);
		
		 * String q = " SELECT " + KEY_RRNO + " FROM " + MYDATABASE_TABLE +
		 * " WHERE " + KEY_BLLED + "= '0' " + " AND " + KEY_PR + "= '0' " +
		 * KEY_STATUS + "= 'NOT UPDATED' " + ";";
		 

		String q = "SELECT  CONSUMER_SC_NO FROM " + MYDATABASE_TABLE
				+ " WHERE STATUS='NOT UPDATED' AND BILLEDSTATUS='0';";
		
		System.out.println("query :- " +q);
		Cursor mCursor = sqLiteDatabase.rawQuery(q, null);
		if (mCursor != null) {
			mCursor.moveToFirst();

		}
		sqLiteDatabase.close();
		return mCursor;
	}*/

	public Cursor checkuser() throws SQLException {

		String q = "SELECT  CONSUMERNO FROM " + MYDATABASE_TABLE
				+ " WHERE STATUS='0' AND BILLEDSTATUS='0';";

		logger.debug("query :- " + q);
		Cursor mCursor = sqLiteDatabase.rawQuery(q, null);
		if (mCursor != null) {
			mCursor.moveToFirst();

		}
		sqLiteDatabase.close();
		return mCursor;
	}
	
	
	
	
	
	public Cursor checkuser_RRNO() throws SQLException {

		String q = "SELECT  CONSUMERNO FROM " + MYDATABASE_TABLE + " WHERE  STATUS='0' AND  BILLEDSTATUS='0' ;";
		Cursor mCursor = sqLiteDatabase.rawQuery(q, null);
		if (mCursor != null) {
			mCursor.moveToFirst();

		}
		sqLiteDatabase.close();
		return mCursor;
	}

	public Cursor Getunbillledcount() throws SQLException {

		String q = "SELECT  count(*) AS unbilled FROM " + MYDATABASE_TABLE
				+ " WHERE BILLEDSTATUS='0';";
		logger.debug("query :- " + q);
		Cursor mCursor = sqLiteDatabase.rawQuery(q, null);
		if (mCursor != null) {
			mCursor.moveToFirst();

		}
		sqLiteDatabase.close();
		return mCursor;
	}
	
	public String getTotalCount() throws SQLException {

		String totalCon= "0";
		String q = "SELECT  count(*) AS unbilled FROM " + MYDATABASE_TABLE
				+ ";";
		Cursor mCursor = sqLiteDatabase.rawQuery(q, null);
		if (mCursor != null) {
			if (mCursor != null && mCursor.moveToFirst()) {
				totalCon = mCursor.getString(0);
			}
		}
		if(mCursor!=null)mCursor.close();
		return totalCon;
	}
	
	

	public int Updatetolocaldb(String meterconstant, String presentreading,
			String presentmtstatus, String consumed, String tax, String ec,
			String fc, String total, String fppca, String billno, String rrno,
			String monthyear, String billDate, String dueDate,
			String tariffDesc, String billedatetimestamp, String mRent,
			String sbmBM, String ligPoints, String ExtraDps, String ckwhlkwh ,String pf_reading,
			String pf_penalty, String dl_Adj1, String interest, String mcUnits,
			String version, String roundValue, String roundTotalValue,
			byte[] image, String imagepath , String longitude , String lattitude ,String takentime , String division,
			String bmd_penality , String bmd_reading_penality , String credit_or_rebate , String avgUnits, String phoneNumber, String deviceInfo
			)
			throws SQLException {

		Log.e("Updatetolocaldb", "mbmd_penality: "+bmd_penality);
		Log.e("Updatetolocaldb", "mbmd_reading: "+bmd_reading_penality);
		ContentValues args = new ContentValues();
	args.put(KEY_METER_CONSTANT, meterconstant);
		args.put(KEY_PRESENTREADING, presentreading);
		args.put(KEY_CONSUMPTION, consumed);
		args.put(KEY_TAX, tax);
		args.put(KEY_EC, ec);
		args.put(KEY_FC, fc);

		args.put(KEY_TOTAL, total);
		
		args.put(KEY_BILLEDSTATUS, "1");
		
		args.put(KEY_FPPCA, fppca);
		args.put(KEY_BILLNO, billno);
		args.put(KEY_CONSUMER_SC_NO,rrno);
		
		
		// Commented as to resolve bill month zero issue
		/*args.put(KEY_BILLMONTH ,monthyear);*/
		args.put(KEY_BILLDATE ,billDate);
		args.put(KEY_DUEDATE ,dueDate);
		args.put(KEY_PRESENTMETERSTATUS, presentmtstatus); 
		args.put(KEY_TARIFFDESC, tariffDesc); 
		args.put(KEY_BILLEDATETIMESTAMP, billedatetimestamp);
		args.put(KEY_METERRENT, mRent);
		args.put(KEY_NO_OF_MONTHS_ISSUED, sbmBM); 
		args.put(KEY_LIGPOINTS, ligPoints);
		args.put(KEY_EXTRA2, ExtraDps);
		args.put(KEY_CKWHLKWH, ckwhlkwh);
		args.put(KEY_PF_READING, pf_reading);
		args.put(KEY_PF_PENALITY, pf_penalty);    			 
		args.put( KEY_BMD_PENALITY , bmd_penality ); 
		args.put( KEY_BMD_READING_PENALITY , bmd_reading_penality); 
		
		
		args.put(KEY_DL_ADJ, dl_Adj1);
		args.put(KEY_INTEREST, interest);
		args.put(KEY_METER_CHANGE_UNITS_CONSUMED, mcUnits);
		args.put(KEY_DEVICEFIRMWAREVERSION, version);
		args.put(KEY_ROUNDOFF, roundValue); 
		args.put(KEY_ROUNDOFF_TOTAL, roundTotalValue);
		args.put(  KEY_IMAGE  , 	image	);
		args.put(  KEY_IMAGEPATH  , 	imagepath	);
		args.put( KEY_LONGITUDE , longitude );
		args.put( KEY_LATTITUDE , lattitude);
		args.put( KEY_TAKENTIME , takentime );
		args.put( KEY_DIVISION , division );
		args.put( KEY_CREDIT_OR_REBATE , credit_or_rebate );
		args.put( KEY_AVERAGE_CONSUMPTION , avgUnits );
		args.put(KEY_EXTRA5, phoneNumber);
		args.put(KEY_EXTRA6, deviceInfo);
		
		int flag = sqLiteDatabase.update(MYDATABASE_TABLE, args, "CONSUMER_SC_NO ='"
				+ rrno + "'", null);
		
		
		sqLiteDatabase.close();
		return flag;

	}

	public int ChangeStatus() throws SQLException {

		/*
		 * ContentValues args = new ContentValues(); args.put(KEY_STATUS,
		 * "UPDATED");
		 */

		int flag = 0;// = sqLiteDatabase.update(MYDATABASE_TABLE, args,
		// "BILLED=" + '1', null);
		sqLiteDatabase.close();
		return flag;

	}

	public int ChangeStatusonlineoffline(String RRNO) throws SQLException {

		
		int flag = 0;
		sqLiteDatabase.close();
		return flag;

	}

	

	public String Getbillledcount() throws SQLException {
		String billed = "0";

		String q = "SELECT  count(*) AS billed FROM " + MYDATABASE_TABLE+ " WHERE  BILLEDSTATUS='1';";
		logger.debug("query :- "+q);
		Cursor mCursor = sqLiteDatabase.rawQuery(q, null);
		if (mCursor != null && mCursor.moveToFirst()) {
			billed = mCursor.getString(0);

		}mCursor.close();
		//sqLiteDatabase.close();
		return billed;
	}
	
	/**
	 * @category  SUMMARY REPORT 
	 * @return MStatusReportHelper LIST 
	 * @throws SQLException
	 */
	public  List<MStatusReportHelper> getMStatusWiseReport() throws SQLException {
		List<MStatusReportHelper> list = new ArrayList<MStatusReportHelper>();

		String q = "SELECT  PRESENTMETERSTATUS AS M_STATUS  , count(*) AS C_COUNT FROM "	+ MYDATABASE_TABLE +  " WHERE PRESENTMETERSTATUS <> '' GROUP BY PRESENTMETERSTATUS ;";
		logger.debug("query :- "+q);
		Cursor mCursor = sqLiteDatabase.rawQuery(q, null);
		  if (mCursor.moveToFirst()) 
		  {
			  MStatusReportHelper helper ;
			  do {
				  helper = new MStatusReportHelper( mCursor.getString(mCursor.getColumnIndex("M_STATUS")) , mCursor.getString(mCursor.getColumnIndex("C_COUNT")) );
				  list.add(helper);
     	        } while (mCursor.moveToNext());
		}mCursor.close();
		//sqLiteDatabase.close();
		return list;
	}
	
	
	
	
	public Cursor getAllTariff() throws SQLException {
		String q = "SELECT  DISTINCT  trim ( TARIFF ) FROM " + MYDATABASE_TABLE+ " GROUP BY TARIFF ORDER BY CAST( "+KEY_TARIFF+" AS INTEGER) ;";
		logger.debug("getAllTariff : query :- "+q);
		Cursor mCursor = sqLiteDatabase.rawQuery(q, null);
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	}
	
	public Cursor getAllRngDates() throws SQLException {
		String q = "SELECT  DISTINCT  trim ( "+KEY_READING_DATE+" ) FROM " + MYDATABASE_TABLE+ " GROUP BY "+KEY_READING_DATE+" ORDER BY   "+KEY_READING_DATE+"  DESC ;";
		logger.debug("getAllTariff : query :- "+q);
		Cursor mCursor = sqLiteDatabase.rawQuery(q, null);
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	}
	
	
	
	
	/**
	 *  @category  DETAILED SUMMARY REPORT USING :  DetailedReportHelper
	 * @return MStatusReportHelper LIST 
	 * @throws SQLException
	 * 
	 */
	public  List<DetailedReportHelper> getDetailedMStatusWiseReport() throws SQLException {
		Cursor mCursor =  getAllTariff() ;
		
		List<DetailedReportHelper> list = new ArrayList<DetailedReportHelper>();
		if(mCursor != null && mCursor.moveToFirst())
		{
			 do {
				 
				 String q = "SELECT  trim(TARIFF ) AS TARIFF , TARIFFDESC, PRESENTMETERSTATUS AS M_STATUS  , BILLEDSTATUS , count(*) AS CONN_COUNT , sum(CONSUMPTION) AS TOTAL_UNITS , sum(KEY_ROUNDOFFTOATL) AS T_TOTAL_REV"
						 		+" FROM  "	+ MYDATABASE_TABLE +  
                                "  WHERE trim("+KEY_TARIFF+") = trim ( '"+mCursor.getString(0)+"' )"+
                                " GROUP BY TARIFF , TARIFFDESC, PRESENTMETERSTATUS , BILLEDSTATUS ORDER BY TARIFF , TARIFFDESC, PRESENTMETERSTATUS  DESC;"; 
				 
				 Cursor innerCursor = sqLiteDatabase.rawQuery(q, null);
				 int report_Totalunits=0 ,  report_totalRev =0  , report_Billed = 0 , report_totalConn = 0;
				 String tariffCode ="-" , traifDesc = "-";
				 if(innerCursor != null && innerCursor.moveToFirst())
					{
					 int mtrStatusNrm = 0 , mtrStatusDL = 0 , mtrStatusMNR = 0 , mtrStatusDC= 0  ,
							 mtrStatusDISSCONN= 0  , mtrStatusIDLE = 0 , mtrStatusMB= 0  , mtrStatusMS = 0 , mtrStatusNV= 0  ;
					 tariffCode = mCursor.getString(0) ;
					 do {
							// int reportStatus_ = 0 ;
						 report_Totalunits  += innerCursor.getInt(innerCursor.getColumnIndex("TOTAL_UNITS")) ; 
							report_totalRev += innerCursor.getInt(innerCursor.getColumnIndex("T_TOTAL_REV"))  ;
							
							if(innerCursor.getString(innerCursor.getColumnIndex("BILLEDSTATUS")).equalsIgnoreCase("1") ){
								report_Billed += innerCursor.getInt(innerCursor.getColumnIndex("CONN_COUNT"))  ;
							}
							report_totalConn += innerCursor.getInt(innerCursor.getColumnIndex("CONN_COUNT")) ;
							
							int M_status =  innerCursor.getInt(innerCursor.getColumnIndex("M_STATUS")) ;
							switch (M_status) {
							case 1:
								mtrStatusNrm = innerCursor.getInt(innerCursor.getColumnIndex("CONN_COUNT"))  ;
								
								break;
								
							case 2:
								mtrStatusDL = innerCursor.getInt(innerCursor.getColumnIndex("CONN_COUNT"))  ;
								
								break;
							case 3:
								mtrStatusMNR = innerCursor.getInt(innerCursor.getColumnIndex("CONN_COUNT"))  ;
								
								break;
							case 4:
								mtrStatusDC = innerCursor.getInt(innerCursor.getColumnIndex("CONN_COUNT"))  ;
								
								break;
							case 5:
								mtrStatusDISSCONN = innerCursor.getInt(innerCursor.getColumnIndex("CONN_COUNT"))  ;
								
								break;
							case 6:
								mtrStatusIDLE = innerCursor.getInt(innerCursor.getColumnIndex("CONN_COUNT"))  ;
								
								break;
								
							case 7:
								mtrStatusMB = innerCursor.getInt(innerCursor.getColumnIndex("CONN_COUNT"))  ;
								
								break;
							case 8:
								mtrStatusMS = innerCursor.getInt(innerCursor.getColumnIndex("CONN_COUNT"))  ;
								
								break;
							case 9:
								mtrStatusNV = innerCursor.getInt(innerCursor.getColumnIndex("CONN_COUNT"))  ;
								
								break;

							default:
								mtrStatusNrm = 0 ;
								break;
							}
							 
							
				 
						 } while (innerCursor.moveToNext());
						 
					DetailedReportHelper detailedReportHelper = new DetailedReportHelper(
							mtrStatusNrm, mtrStatusDL, mtrStatusMNR,
							mtrStatusDC, mtrStatusDISSCONN, mtrStatusIDLE,
							mtrStatusMB, mtrStatusMS, mtrStatusNV,
							report_Totalunits, report_totalRev, report_Billed,
							report_totalConn, tariffCode , traifDesc);
					
					list.add(detailedReportHelper)  ;
					} 
				 
				 
			 } while (mCursor.moveToNext());
		}
		
		
		return list;
	}
	/**
	 *  @category  DETAILED SUMMARY REPORT USING :  DetailedReportHelper
	 * @return MStatusReportHelper LIST 
	 * @throws SQLException
	 * 
	 */
	public  List<DetailedReportHelper> getDetailedRouteWiseReport() throws SQLException {
		Cursor mCursor =  getAllRngDates() ;
		
		List<DetailedReportHelper> list = new ArrayList<DetailedReportHelper>();
		if(mCursor != null && mCursor.moveToFirst())
		{
			 do {
				 
				 String q = "SELECT  trim("+KEY_READING_DATE+" ) AS TARIFF , TARIFFDESC, PRESENTMETERSTATUS AS M_STATUS  , BILLEDSTATUS , count(*) AS CONN_COUNT , sum(CONSUMPTION) AS TOTAL_UNITS , sum(KEY_ROUNDOFFTOATL) AS T_TOTAL_REV"
						 		+" FROM  "	+ MYDATABASE_TABLE +  
                                "  WHERE trim("+KEY_READING_DATE+") = trim ( '"+mCursor.getString(0)+"' )"+
                                " GROUP BY  "+KEY_READING_DATE+"  , TARIFFDESC, PRESENTMETERSTATUS , BILLEDSTATUS ORDER BY  "+KEY_READING_DATE+"  , TARIFFDESC, PRESENTMETERSTATUS  DESC;"; 
				 
				 Cursor innerCursor = sqLiteDatabase.rawQuery(q, null);
				 int report_Totalunits=0 ,  report_totalRev =0  , report_Billed = 0 , report_totalConn = 0;
				 String tariffCode ="-" , traifDesc = "-";
				 if(innerCursor != null && innerCursor.moveToFirst())
					{
					 int mtrStatusNrm = 0 , mtrStatusDL = 0 , mtrStatusMNR = 0 , mtrStatusDC= 0  ,
							 mtrStatusDISSCONN= 0  , mtrStatusIDLE = 0 , mtrStatusMB= 0  , mtrStatusMS = 0 , mtrStatusNV= 0  ;
					 tariffCode = mCursor.getString(0) ;
					 do {
							// int reportStatus_ = 0 ;
						 report_Totalunits  += innerCursor.getInt(innerCursor.getColumnIndex("TOTAL_UNITS")) ; 
							report_totalRev += innerCursor.getInt(innerCursor.getColumnIndex("T_TOTAL_REV"))  ;
							
							if(innerCursor.getString(innerCursor.getColumnIndex("BILLEDSTATUS")).equalsIgnoreCase("1") ){
								report_Billed += innerCursor.getInt(innerCursor.getColumnIndex("CONN_COUNT"))  ;
							}
							report_totalConn += innerCursor.getInt(innerCursor.getColumnIndex("CONN_COUNT")) ;
							
							int M_status =  innerCursor.getInt(innerCursor.getColumnIndex("M_STATUS")) ;
							switch (M_status) {
							case 1:
								mtrStatusNrm = innerCursor.getInt(innerCursor.getColumnIndex("CONN_COUNT"))  ;
								
								break;
								
							case 2:
								mtrStatusDL = innerCursor.getInt(innerCursor.getColumnIndex("CONN_COUNT"))  ;
								
								break;
							case 3:
								mtrStatusMNR = innerCursor.getInt(innerCursor.getColumnIndex("CONN_COUNT"))  ;
								
								break;
							case 4:
								mtrStatusDC = innerCursor.getInt(innerCursor.getColumnIndex("CONN_COUNT"))  ;
								
								break;
							case 5:
								mtrStatusDISSCONN = innerCursor.getInt(innerCursor.getColumnIndex("CONN_COUNT"))  ;
								
								break;
							case 6:
								mtrStatusIDLE = innerCursor.getInt(innerCursor.getColumnIndex("CONN_COUNT"))  ;
								
								break;
								
							case 7:
								mtrStatusMB = innerCursor.getInt(innerCursor.getColumnIndex("CONN_COUNT"))  ;
								
								break;
							case 8:
								mtrStatusMS = innerCursor.getInt(innerCursor.getColumnIndex("CONN_COUNT"))  ;
								
								break;
							case 9:
								mtrStatusNV = innerCursor.getInt(innerCursor.getColumnIndex("CONN_COUNT"))  ;
								
								break;

							default:
								mtrStatusNrm = 0 ;
								break;
							}
							 
							
				 
						 } while (innerCursor.moveToNext());
						 
					DetailedReportHelper detailedReportHelper = new DetailedReportHelper(
							mtrStatusNrm, mtrStatusDL, mtrStatusMNR,
							mtrStatusDC, mtrStatusDISSCONN, mtrStatusIDLE,
							mtrStatusMB, mtrStatusMS, mtrStatusNV,
							report_Totalunits, report_totalRev, report_Billed,
							report_totalConn, tariffCode , traifDesc);
					
					list.add(detailedReportHelper)  ;
					} 
				 
				 
			 } while (mCursor.moveToNext());
		}
		
		
		return list;
	}

	public Cursor getDetailsByConScNo(String accNo) throws SQLException {
		String q = " SELECT * FROM  "	+ MYDATABASE_TABLE +  " WHERE TRIM("+KEY_CONSUMER_SC_NO+") = TRIM( '"+accNo+"' ) ;";
		Cursor mCursor = sqLiteDatabase.rawQuery(q, null);
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	}

	
	public Cursor getDetailsByMeterNo(String accNo) throws SQLException {
		String q = "SELECT *  FROM " + MYDATABASE_TABLE +  "  WHERE TRIM("+KEY_METERNO+") = TRIM( '"+accNo+"' ) ;";
		Cursor mCursor = sqLiteDatabase.rawQuery(q, null);
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	}
	
	
	public Cursor Fetchnextconsumer() throws SQLException {

		String q = "SELECT CONSUMERNO,INSTALLATIONO, CONSUMER_SC_NO,ID FROM "
				+ MYDATABASE_TABLE + " WHERE STATUS='0' AND BILLEDSTATUS='0';";
		Cursor mCursor = sqLiteDatabase.rawQuery(q, null);
		if (mCursor != null) {
			mCursor.moveToFirst();

		}
		sqLiteDatabase.close();
		return mCursor;
	}

	public String GetBilledcountfromlocal() throws SQLException {
		// System.out.println("check user" + username + password);****

		String notUploaded = "0";
		String q = "SELECT count(*) as billed FROM " + MYDATABASE_TABLE
				+ " WHERE BILLEDSTATUS='1' AND STATUS='0';";
		logger.debug("qurey :- "+q);
		Cursor mCursor = sqLiteDatabase.rawQuery(q, null);
		if (mCursor != null && mCursor.moveToFirst()) {
			notUploaded = mCursor.getString(0);
		}
		mCursor.close();
		//sqLiteDatabase.close();
		return notUploaded;
	}

	public Cursor GetBilledandSevercountfromlocal() throws SQLException {
		// System.out.println("check user" + username + password);****

		String q = "SELECT count(*) as billed FROM " + MYDATABASE_TABLE
				+ " WHERE BILLEDSTATUS='1' AND STATUS='1';";
		logger.debug("qurey :- "+q);
		Cursor mCursor = sqLiteDatabase.rawQuery(q, null);
		if (mCursor != null) {
			mCursor.moveToFirst();

		}
		sqLiteDatabase.close();
		return mCursor;
	}
	
	
	public String  GetBilledupdatedcountfromlocal() throws SQLException {
		String updated = "0" ;

		String q = "SELECT count(*) as billed FROM " + MYDATABASE_TABLE	+ " WHERE BILLEDSTATUS='1' AND STATUS='1';";
		logger.debug("qurey :- "+q);
		Cursor mCursor = sqLiteDatabase.rawQuery(q, null);
		if (mCursor != null&& mCursor.moveToFirst()) {
			updated = mCursor.getString(0);
		}
		mCursor.close();
		//sqLiteDatabase.close();
		return updated;
	}

	public Cursor Getbilledconsumers() throws SQLException {
		

		String q = "SELECT * FROM "
				+ MYDATABASE_TABLE + " WHERE BILLEDSTATUS='1' ;";
		Cursor mCursor = sqLiteDatabase.rawQuery(q, null);
		if (mCursor != null) {
			mCursor.moveToFirst();

		}
		sqLiteDatabase.close();
		return mCursor;
	}
	
	public Cursor Getbilledconsumers_fromlocal() throws SQLException {
		

		String q = "SELECT * FROM "
				+ MYDATABASE_TABLE + " WHERE BILLEDSTATUS='1' AND  "+KEY_STATUS+" ='0';";
		logger.debug("check user query  " +q);
		Cursor mCursor = sqLiteDatabase.rawQuery(q, null);
		if (mCursor != null) {
			mCursor.moveToFirst();

		}
		sqLiteDatabase.close();
		return mCursor;
	}
	/**
	 * @category UPLOAD
	 * 
 	 * @return  UploadHandler List<UploadHandler> TO UPLOAD BILLED DATA 
 	 */
 	public  List<UploadHandler> getBilledDataToUpload(String limit_val , String sbm_no) {
		   List<UploadHandler> contactList = new ArrayList<UploadHandler>();
		   Cursor cursor = null;
		   String version_name = "-" ;
		   try{
			   if(context!=null){
				   version_name =  MasterLibraryFunction.getVersion(context);
			   }
			   
			   
		    String selectQuery = "SELECT  * FROM  "+ MYDATABASE_TABLE + "  WHERE " + KEY_BILLEDSTATUS+ " = '1'  AND  "+KEY_STATUS+" = '0'  LIMIT " + limit_val +"  ;"; 
		     cursor = sqLiteDatabase.rawQuery(selectQuery, null);
		     UploadHandler contact = null;
		    // looping through all rows and adding to list
		    if (cursor.moveToFirst()) {
		      do {
		    	 
		    	  
		    	  
		    	    float d_and_r_fee = Float.parseFloat(cursor.getString(cursor.getColumnIndex(KEY_D_AND_R_FEE)));
					float backbillarrears = Float.parseFloat(cursor.getString(cursor.getColumnIndex(KEY_BACKBILLARR)));
					float audit_arrears = Float.parseFloat(cursor.getString(cursor.getColumnIndex(KEY_AUDIT_ARREARS)));
					float othersOriginal =Float.parseFloat(cursor.getString(cursor.getColumnIndex(KEY_OTHERS)));
					float combinedfppca = Float.parseFloat(cursor.getString(cursor.getColumnIndex(KEY_FPPCA)));
					
					
					
					
					
					
					String onlyFppca = String.valueOf(combinedfppca - (d_and_r_fee + backbillarrears + audit_arrears + othersOriginal));
					
		    	  
		    	  
		    	  
		    	  // Changes due to FPPCA charges
		    	 /* String bank = cursor.getString(cursor.getColumnIndex(KEY_FPPCA))+",CESCTRM_VM"+version_name+",0,0,0,"+cursor.getString(cursor.getColumnIndex(KEY_EXTRA5)); */
					
					 String bank = onlyFppca+",CESCTRM_VM"+version_name+",0,0,0,"+cursor.getString(cursor.getColumnIndex(KEY_EXTRA5)); 
					
		    	  //String bank =  "0,CESCTRM_VM"+version_name+",0,0,0,0",
		    	  
		    		// to resolve bill No updating 0 issue
					
					String billNo = cursor.getString(cursor.getColumnIndex(  KEY_BILLNO )).trim();
					
					if(billNo.equals("0")){
						
						billNo = "10000";
						
					}
		    	  
		    	  
		    	  
		    	  
		    	  contact = new UploadHandler(cursor.getString(cursor.getColumnIndex(  KEY_MRCODE)).trim(),
		    			  cursor.getString(cursor.getColumnIndex(  KEY_CONSUMER_SC_NO )).trim(),
		    			  sbm_no,
		    			   cursor.getString(cursor.getColumnIndex(  KEY_SITECODE )).trim(),
		    			   cursor.getString(cursor.getColumnIndex(  KEY_PRESENTREADING)).trim(),
		    			   cursor.getString(cursor.getColumnIndex(  KEY_CKWHLKWH)).trim(),
		    			   cursor.getString(cursor.getColumnIndex( KEY_CONSUMPTION )).trim(),
		    			   cursor.getString(cursor.getColumnIndex(  KEY_EC )).trim(),
		    			   cursor.getString(cursor.getColumnIndex(  KEY_FC )).trim(),
		    			   cursor.getString(cursor.getColumnIndex(  KEY_PF_READING )).trim(),
		    			   cursor.getString(cursor.getColumnIndex(  KEY_BMD_READING_PENALITY )).trim(),
		    			   cursor.getString(cursor.getColumnIndex(  KEY_PF_PENALITY )).trim(),
		    			   cursor.getString(cursor.getColumnIndex(  KEY_BMD_PENALITY )).trim(),
		    				
		    			   //	cursor.getString(cursor.getColumnIndex(  KEY_TOTAL)).trim(),
							cursor.getString(cursor.getColumnIndex(  KEY_ROUNDOFF_TOTAL)).trim(),
		    			  
							cursor.getString(cursor.getColumnIndex(  KEY_TAX )).trim(),
		    			   cursor.getString(cursor.getColumnIndex(  KEY_CREDIT_OR_REBATE )).trim(),
		    			 
		    			   //cursor.getString(cursor.getColumnIndex(  KEY_TOTAL )).trim(),
							cursor.getString(cursor.getColumnIndex(  KEY_ROUNDOFF_TOTAL)).trim(),
							
		    			   cursor.getString(cursor.getColumnIndex(  KEY_NO_OF_MONTHS_ISSUED )).trim(),
		    			   cursor.getString(cursor.getColumnIndex(  KEY_PRESENTMETERSTATUS)).trim(),
		    			   cursor.getString(cursor.getColumnIndex(KEY_BILLMONTH)),
		    			   cursor.getString(cursor.getColumnIndex(KEY_ROUNDOFF)),
		    			   cursor.getString(cursor.getColumnIndex(KEY_READING_DATE)),
		    			   bank,
		    			   billNo,
		    			  "1",
		    			  "0",
		    			   cursor.getString(cursor.getColumnIndex(  KEY_BILLEDATETIMESTAMP )).trim(),
		    			   cursor.getString(cursor.getColumnIndex(  KEY_TARIFF )).trim(),
		    			   cursor.getString(cursor.getColumnIndex(  KEY_DL_ADJ )).trim(),
		    			   cursor.getString(cursor.getColumnIndex(  KEY_FC )).trim(),
		    			  "0",
		    			  "",
		    			  "0",
		    			  "0" ,
		    			  cursor.getString(cursor.getColumnIndex(KEY_LONGITUDE)).trim(),
		    			  cursor.getString(cursor.getColumnIndex(KEY_LATTITUDE)).trim(),
		    			  cursor.getBlob(cursor.getColumnIndex(KEY_IMAGE)),
		    			  cursor.getString(cursor.getColumnIndex(KEY_TAKENTIME)).trim(),
		    				cursor.getString(cursor.getColumnIndex(KEY_BILLMONTH)),
		    				cursor.getString(cursor.getColumnIndex(KEY_PREVIOUS_READING)),
		    				cursor.getString(cursor.getColumnIndex(KEY_METERNO)),
		    				cursor.getString(cursor.getColumnIndex(KEY_READING_DATE)),
		    				cursor.getString(cursor.getColumnIndex(KEY_EXTRA6)) ,
		    				cursor.getString(cursor.getColumnIndex(KEY_D_AND_R_FEE)),
							cursor.getString(cursor.getColumnIndex(KEY_BACKBILLARR)),
							cursor.getString(cursor.getColumnIndex(KEY_AUDIT_ARREARS)),
							cursor.getString(cursor.getColumnIndex(KEY_OTHERS))
		    			  
		    			  
		    			  ) ;
		    	  
		    	  
		    	  
		    	  
		    	  
		    	  
		    	// Adding contact to list
		           contactList.add(contact);
		      } while (cursor.moveToNext());
		    }
		    cursor.close();
		   }catch (Exception e) {
			   if(cursor!=null)
    				cursor.close();
			   e.printStackTrace();
		}
		    return contactList;
		}
	
	/**
	 * @author GURU 
	 * @category BACKUP DATA
	 * @return List<BackupHelper>
	 * @throws SQLException
	 */
	public List<BackupHelper> Getbilledconsumersbackup() throws SQLException {
		
		 List<BackupHelper> contactList = new ArrayList<BackupHelper>();
       	 Cursor cursor = null,cursorgps=null;
       	 try{
       		String bMColumns = KEY_CONSUMER_SC_NO			+"," +
       				KEY_METER_CONSTANT			+"," +
       				KEY_CONSUMER_NAME			+"," +
       				KEY_ADDRESS			+"," +
       				KEY_ADDRESS1			+"," +
       				KEY_TARIFF			+"," +
       				KEY_TARIFFDESC			+"," +
       				KEY_LEDGER_NO			+"," +
       				KEY_FOLIO_NO			+"," +
       				KEY_CONNECTED_LOAD			+"," +
       				KEY_D_AND_R_FEE			+"," +
       				KEY_ARREARS			+"," +
       				KEY_INTEREST			+"," +
       				KEY_OTHERS			+"," +
       				KEY_BACKBILLARR			+"," +
       				KEY_AVERAGE_CONSUMPTION			+"," +
       				KEY_DL_OR_MNR_PREV_MONTH			+"," +
       				KEY_PREVIOUS_READING			+"," +
       				KEY_PRESENTREADING			+"," +
       				KEY_CONSUMPTION			+"," +
       				KEY_POWER_FACTOR			+"," +
       				KEY_READING_DATE			+"," +
       				KEY_METER_CHANGE_UNITS_CONSUMED			+"," +
       				KEY_NO_OF_MONTHS_ISSUED			+"," +
       				KEY_CREDIT_OR_REBATE			+"," +
       				KEY_FIXED_GES			+"," +
       				KEY_AUDIT_ARREARS			+"," +
       				KEY_OLD_INTEREST			+"," +
       				KEY_TRIVECTOR			+"," +  
       				KEY_CKWHLKWH			+"," + 
       				KEY_DOORLOCKAVG			+"," +
       				KEY_CONSUMERCODE			+"," +
       				KEY_ADDITIONALDEP			+"," +
       				KEY_MRCODE			+"," +
       				KEY_BILLMONTH			+"," +
       				KEY_SITECODE			+"," +
       				KEY_SYNCSTATUS			+"," +
       				KEY_DATAPREPAREDDATE			+"," +
       				KEY_SERVERTOSBMDATE			+"," +
       				KEY_METERNO			+"," +
       				KEY_INTERESTONDEPOSIT			+"," +
       				KEY_DL_ADJ			+"," +
       				KEY_DL_COUNT			+"," +
       				KEY_METERRENT			+"," +
       				KEY_FPPCA			+"," +
       				KEY_TAX			+"," +
       				KEY_EC			+"," +
       				KEY_FC			+"," +
       				KEY_TOTAL			+"," +
       				KEY_ROUNDOFF			+"," +
       				KEY_ROUNDOFF_TOTAL			+"," +
       				KEY_PREBILLDATE			+"," +
       				KEY_BILLDATE			+"," +
       				KEY_DUEDATE			+"," +
       				KEY_EXTRA1			+"," +
       				KEY_EXTRA2			+"," +
       				KEY_CYCLENAME			+"," +
       				KEY_CONSUMER_KEY			+"," +
       				KEY_INSTALLATIONO			+"," +
       				KEY_CONSUMERNO			+"," +
       				KEY_DIVISION			+"," +
       				KEY_SUBDIVISION			+"," +
       				KEY_BOOKNO			+"," +
       				KEY_DEVICEFIRMWAREVERSION			+"," +
       				KEY_BILLEDATETIMESTAMP			+"," +
       				KEY_BILLNO			+"," +
       				KEY_PRESENTMETERSTATUS			+"," +
       				KEY_BILLEDSTATUS			+"," +
       				KEY_STATUS			+"," +
       				PREVIOUS_BILLEDDATE			+"," +
       				ACTUAL_PREVIOUS_BILLEDDATE 			+"," +
       				KEY_LINEMINIMUM 			+"," +
       				  SEASONAL_CONSUMER 			+"," +
       				  KEY_LIGPOINTS 			+"," +
       				  KEY_METERED 			+"," +
       				KEY_PF_READING			+"," +
       				KEY_PF_PENALITY			+"," + 
       				
					KEY_BMD_READING_PENALITY			+"," + 
					KEY_BMD_PENALITY			+"," + 
       							
       				KEY_IMAGEPATH			+"," +
       				KEY_LONGITUDE			+"," +
       				KEY_LATTITUDE			+"," +
       				KEY_TAKENTIME			+"," +
       				KEY_EXTRA3			+"," +
       				KEY_EXTRA4			+"," +
       				KEY_EXTRA5			+"," +
       				KEY_EXTRA6			+"," +
       				KEY_EXTRA7			+"," +
       				KEY_EXTRA8			;

       	    // Select All Query
       	    String selectQuery = "SELECT    " +bMColumns+"  FROM "+ MYDATABASE_TABLE + "  WHERE " + KEY_BILLEDSTATUS+ " = '1'  AND  "+KEY_STATUS+" = '1' ";
       	    
       	    
       	     cursor = sqLiteDatabase.rawQuery(selectQuery, null);
       	  BackupHelper contact = null;
       	    // looping through all rows and adding to list
       	    if (cursor.moveToFirst()) {
       	        do {
       	        	
       	        	
       	        	
       	        		contact = new BackupHelper(cursor.getString(cursor.getColumnIndex(			KEY_CONSUMER_SC_NO			)),
       	        				cursor.getString(cursor.getColumnIndex(			KEY_METER_CONSTANT			)),
       	        				cursor.getString(cursor.getColumnIndex(			KEY_CONSUMER_NAME			)),
       	        				cursor.getString(cursor.getColumnIndex(			KEY_ADDRESS			)),
       	        				cursor.getString(cursor.getColumnIndex(			KEY_ADDRESS1			)),
       	        				cursor.getString(cursor.getColumnIndex(			KEY_TARIFF			)),
       	        				cursor.getString(cursor.getColumnIndex(			KEY_TARIFFDESC			)),
       	        				cursor.getString(cursor.getColumnIndex(			KEY_LEDGER_NO			)),
       	        				cursor.getString(cursor.getColumnIndex(			KEY_FOLIO_NO			)),
       	        				cursor.getString(cursor.getColumnIndex(			KEY_CONNECTED_LOAD			)),
       	        				cursor.getString(cursor.getColumnIndex(			KEY_D_AND_R_FEE			)),
       	        				cursor.getString(cursor.getColumnIndex(			KEY_ARREARS			)),
       	        				cursor.getString(cursor.getColumnIndex(			KEY_INTEREST			)),
       	        				cursor.getString(cursor.getColumnIndex(			KEY_OTHERS			)),
       	        				cursor.getString(cursor.getColumnIndex(			KEY_BACKBILLARR			)),
       	        				cursor.getString(cursor.getColumnIndex(			KEY_AVERAGE_CONSUMPTION			)),
       	        				cursor.getString(cursor.getColumnIndex(			KEY_DL_OR_MNR_PREV_MONTH			)),
       	        				cursor.getString(cursor.getColumnIndex(			KEY_PREVIOUS_READING			)),
       	        				cursor.getString(cursor.getColumnIndex(			KEY_PRESENTREADING			)),
       	        				cursor.getString(cursor.getColumnIndex(			KEY_CONSUMPTION			)),
       	        				cursor.getString(cursor.getColumnIndex(			KEY_POWER_FACTOR			)),
       	        				cursor.getString(cursor.getColumnIndex(			KEY_READING_DATE			)),
       	        				cursor.getString(cursor.getColumnIndex(			KEY_METER_CHANGE_UNITS_CONSUMED			)),
       	        				cursor.getString(cursor.getColumnIndex(			KEY_NO_OF_MONTHS_ISSUED			)),
       	        				cursor.getString(cursor.getColumnIndex(			KEY_CREDIT_OR_REBATE			)),
       	        				cursor.getString(cursor.getColumnIndex(			KEY_FIXED_GES			)),
       	        				cursor.getString(cursor.getColumnIndex(			KEY_AUDIT_ARREARS			)),
       	        				cursor.getString(cursor.getColumnIndex(			KEY_OLD_INTEREST			)),
       	        				cursor.getString(cursor.getColumnIndex(			KEY_TRIVECTOR			)), 
       	        				cursor.getString(cursor.getColumnIndex(			KEY_CKWHLKWH			)),
       	        				cursor.getString(cursor.getColumnIndex(			KEY_DOORLOCKAVG			)),
       	        				cursor.getString(cursor.getColumnIndex(			KEY_CONSUMERCODE			)),
       	        				cursor.getString(cursor.getColumnIndex(			KEY_ADDITIONALDEP			)),
       	        				cursor.getString(cursor.getColumnIndex(			KEY_MRCODE			)),
       	        				cursor.getString(cursor.getColumnIndex(			KEY_BILLMONTH			)),
       	        				cursor.getString(cursor.getColumnIndex(			KEY_SITECODE			)),
       	        				cursor.getString(cursor.getColumnIndex(			KEY_SYNCSTATUS			)),
       	        				cursor.getString(cursor.getColumnIndex(			KEY_DATAPREPAREDDATE			)),
       	        				cursor.getString(cursor.getColumnIndex(			KEY_SERVERTOSBMDATE			)),
       	        				cursor.getString(cursor.getColumnIndex(			KEY_METERNO			)),
       	        				cursor.getString(cursor.getColumnIndex(			KEY_INTERESTONDEPOSIT			)),
       	        				cursor.getString(cursor.getColumnIndex(			KEY_DL_ADJ			)),
       	        				cursor.getString(cursor.getColumnIndex(			KEY_DL_COUNT			)),
       	        				cursor.getString(cursor.getColumnIndex(			KEY_METERRENT			)),
       	        				cursor.getString(cursor.getColumnIndex(			KEY_FPPCA			)),
       	        				cursor.getString(cursor.getColumnIndex(			KEY_TAX			)),
       	        				cursor.getString(cursor.getColumnIndex(			KEY_EC			)),
       	        				cursor.getString(cursor.getColumnIndex(			KEY_FC			)),
       	        				cursor.getString(cursor.getColumnIndex(			KEY_TOTAL			)),
       	        				cursor.getString(cursor.getColumnIndex(			KEY_ROUNDOFF			)),
       	        				cursor.getString(cursor.getColumnIndex(			KEY_ROUNDOFF_TOTAL			)),
       	        				cursor.getString(cursor.getColumnIndex(			KEY_PREBILLDATE			)),
       	        				cursor.getString(cursor.getColumnIndex(			KEY_BILLDATE			)),
       	        				cursor.getString(cursor.getColumnIndex(			KEY_DUEDATE			)),
       	        				cursor.getString(cursor.getColumnIndex(			KEY_EXTRA1			)),
       	        				cursor.getString(cursor.getColumnIndex(			KEY_EXTRA2			)),
       	        				cursor.getString(cursor.getColumnIndex(			KEY_CYCLENAME			)),
       	        				cursor.getString(cursor.getColumnIndex(			KEY_CONSUMER_KEY			)),
       	        				cursor.getString(cursor.getColumnIndex(			KEY_INSTALLATIONO			)),
       	        				cursor.getString(cursor.getColumnIndex(			KEY_CONSUMERNO			)),
       	        				cursor.getString(cursor.getColumnIndex(			KEY_DIVISION			)),
       	        				cursor.getString(cursor.getColumnIndex(			KEY_SUBDIVISION			)),
       	        				cursor.getString(cursor.getColumnIndex(			KEY_BOOKNO			)),
       	        				cursor.getString(cursor.getColumnIndex(			KEY_DEVICEFIRMWAREVERSION			)),
       	        				cursor.getString(cursor.getColumnIndex(			KEY_BILLEDATETIMESTAMP			)),
       	        				cursor.getString(cursor.getColumnIndex(			KEY_BILLNO			)),
       	        				cursor.getString(cursor.getColumnIndex(			KEY_PRESENTMETERSTATUS			)),
       	        				cursor.getString(cursor.getColumnIndex(			KEY_BILLEDSTATUS			)),
       	        				cursor.getString(cursor.getColumnIndex(			KEY_STATUS			)),
       	        				cursor.getString(cursor.getColumnIndex(			PREVIOUS_BILLEDDATE			)),
       	        				cursor.getString(cursor.getColumnIndex(			ACTUAL_PREVIOUS_BILLEDDATE 			)),
       	        				cursor.getString(cursor.getColumnIndex(			KEY_LINEMINIMUM 			)),
       	        				cursor.getString(cursor.getColumnIndex(			  SEASONAL_CONSUMER 			)),
       	        				cursor.getString(cursor.getColumnIndex(			  KEY_LIGPOINTS 			)),
       	        				cursor.getString(cursor.getColumnIndex(			  KEY_METERED 			)),
       	        				cursor.getString(cursor.getColumnIndex(			KEY_PF_READING			)),
       	        				cursor.getString(cursor.getColumnIndex(			KEY_PF_PENALITY			)),
       	        				cursor.getString(cursor.getColumnIndex(			KEY_BMD_READING_PENALITY			)),
       	        				cursor.getString(cursor.getColumnIndex(			KEY_BMD_PENALITY			)),
       	        				null						,
       	        				cursor.getString(cursor.getColumnIndex(			KEY_IMAGEPATH			)),
       	        				cursor.getString(cursor.getColumnIndex(			KEY_LONGITUDE			)),
       	        				cursor.getString(cursor.getColumnIndex(			KEY_LATTITUDE			)),
       	        				cursor.getString(cursor.getColumnIndex(			KEY_TAKENTIME			)),
       	        				cursor.getString(cursor.getColumnIndex(			KEY_EXTRA3			)),
       	        				cursor.getString(cursor.getColumnIndex(			KEY_EXTRA4			)),
       	        				cursor.getString(cursor.getColumnIndex(			KEY_EXTRA5			)),
       	        				cursor.getString(cursor.getColumnIndex(			KEY_EXTRA6			)),
       	        				cursor.getString(cursor.getColumnIndex(			KEY_EXTRA7			)),
       	        				cursor.getString(cursor.getColumnIndex(			KEY_EXTRA8			)));

       	        	
       	            // Adding contact to list
       	           contactList.add(contact);
       	        } while (cursor.moveToNext());
       	    }
       	    cursor.close();
       	 }catch (Exception e) {
			// TODO: handle exception
       		 e.printStackTrace();
       		 if(cursor!=null)
       			 cursor.close();
		}
       	    // return contact list
       	    return contactList;
	}
	
	
	public List<UnbilledViewPojo> getUnBilled()
	{
		List<UnbilledViewPojo> list = new ArrayList<UnbilledViewPojo>();
		String[] bMColumns = {KEY_CONSUMER_SC_NO,KEY_TARIFF,  KEY_CONSUMER_NAME };
	 	 UnbilledViewPojo pojo = null ;
		Cursor mCursor = sqLiteDatabase.query(MYDATABASE_TABLE, bMColumns, KEY_BILLEDSTATUS+ " = '0' ", null	, null, null, null);
		if (mCursor != null) {
			mCursor.moveToFirst();
			int sl_no = 1;
   	        do {
				pojo = new UnbilledViewPojo(
						sl_no,
						mCursor.getString(mCursor.getColumnIndex(KEY_CONSUMER_SC_NO)),
						mCursor.getString(mCursor.getColumnIndex(KEY_TARIFF)),
						mCursor.getString(mCursor.getColumnIndex(KEY_CONSUMER_NAME))) ;
   	        	list.add(pojo);
                sl_no++ ;
    	        } while (mCursor.moveToNext());
		}
	//	sqLiteDatabase.close();
		return list;
	}
	
	
	/**
	 * @return   String : DOWNLOADED DATE TO COMPARE MOBILE DATE TO VALIDATE MOBILE DATE  
	 * @throws SQLException
	 */
	public String getServerToSBMDate() throws SQLException {
 
		String  serverDate = null;
			String q = "SELECT  "+KEY_SERVERTOSBMDATE+" FROM  " + MYDATABASE_TABLE + "  ORDER BY DATE( "+KEY_SERVERTOSBMDATE+" ,'dd/MM/yyyy')   DESC  LIMIT 1 ; ";

			logger.debug("query :- " + q);
			Cursor mCursor = sqLiteDatabase.rawQuery(q, null);
			if (mCursor != null) {
				if(mCursor.moveToFirst())
					serverDate=	mCursor.getString(mCursor.getColumnIndex(KEY_SERVERTOSBMDATE));
			}
			mCursor.close();
			//sqLiteDatabase.close();
			return serverDate;
		}
	
	
	
	public Cursor Getbilledconsumers_imagename() throws SQLException {
		

		String q = "SELECT IMAGENAME FROM "
				+ MYDATABASE_TABLE + " WHERE BILLEDSTATUS='1' AND PR <> 0 AND STATUS='0';";
		Cursor mCursor = sqLiteDatabase.rawQuery(q, null);
		if (mCursor != null) {
			mCursor.moveToFirst();

		}
		sqLiteDatabase.close();
		return mCursor;
	}
	
	
	/**
 	 * @return NEXT BILL NO 
 	 */
 	public String getNextbillnolocal() {
		int billno= 0;
		 String countQuery = "SELECT MAX("+KEY_BILLNO+") as maxcount FROM "+MYDATABASE_TABLE ;
	        Cursor cursor = sqLiteDatabase.rawQuery(countQuery, null);
	        if(cursor.moveToNext())
	        {
	        	billno = cursor.getInt(cursor.getColumnIndex("maxcount"));
	        }else
	        {
	        	billno = 0;
	        }
	        cursor.close();
	        Log.e("Local db bill no" ,billno+"");
	        if(billno == 0 )
	        {
	        	return "10000";
	        }else
	        {
	        	return billno+"";
	        }
	}
	
 	

	public String fetchnextconsumer(String consumerno,int type) {
		// TODO Auto-generated method stub
		String consumer_no = "";
		int flag = 0;
		
		logger.debug(consumerno+" === This is consumer id");
	  
	    Cursor cursor = null;
	    
	    try{
	    cursor = sqLiteDatabase.query(MYDATABASE_TABLE, new String[] { KEY_CONSUMER_SC_NO,KEY_METERNO}, null, null, null, null, null, null);
	    
	  while(cursor.moveToNext())
	    {
		  if(type==0)
		  {
	    //	cursor.moveToNext();
		  if(flag ==1)
	    	{
			  consumer_no =  cursor.getString(0);
			  break;
	    	}else
	    	{
	    		consumer_no = "null";
	    	}
	    	if(cursor.getString(0).trim().equals(consumerno.trim()))
	    	{
	    		flag=1;
	    	}
		  }else
		  {
			  if(flag ==1)
		    	{
				  consumer_no =  cursor.getString(1);
				  break;
		    	}else
		    	{
		    		consumer_no = "null";
		    	}
		    	if(cursor.getString(1).trim().equals(consumerno.trim()))
		    	{
		    		flag=1;
		    	}
		  }
	    }
	    cursor.close();
	    }catch (Exception e) {
			// TODO: handle exception
	    	if(cursor!=null)
				cursor.close();
			e.printStackTrace();
		}
	    	return consumer_no;
	    
	}
	
	public String fetchprevconsumer(String consumerno, int type) {
		// TODO Auto-generated method stub
		String consumer_no = "";
		int flag = 0;
		logger.debug(consumerno+"this is consumer id");
	    Cursor cursor;
	    cursor = sqLiteDatabase.query(MYDATABASE_TABLE, new String[] { KEY_CONSUMER_SC_NO,KEY_METERNO}, null, null, null, null, null, null);
		String nextconsumer = "";
			while(cursor.moveToNext())
			{
				if(type == 0 )
				{
					if(cursor.getString(0).trim().equals(consumerno.trim()))
					{
						flag =1;
					}
				}else
				{     
					if(cursor.getString(1).trim().equals(consumerno.trim()))
					{
						flag =1;
					}
				}
				if(flag == 0)
				{
					if(type == 0)
					{
						nextconsumer = cursor.getString(0);
					}else
					{
						nextconsumer = cursor.getString(1);
					}
				}
			}
			cursor.close();
			if(!nextconsumer.equals(""))
			{
				return nextconsumer;
			}else
			{
				return "null";
			}
	}
	
	
	

	public class SQLiteHelper extends SQLiteOpenHelper {

		public SQLiteHelper(Context context, String name,
				CursorFactory factory, int version) {

			super(context, name, factory, version);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			
			logger.debug(" &&&&&&&&&&&&&&&&&&&&&&********************************check ********************^&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
			// TODO Auto-generated method stub
			db.execSQL(SCRIPT_CREATE_DATABASE);
			logger.debug("******************************");
			//ALTER TABLE CONSUMER_INPUT_LIVE_GED ADD COLUMN  'PF_READING' text ,  'PF_PENALITY'  text, 'EXTRA3' text , 'EXTRA4'  text ,'EXTRA5' text , 'EXTRA6' text , 'EXTRA7' text , 'EXTRA8' text ;
			
			
			// CREATE INDEX idx_ex1 ON ex1(a,b,c,d,e,...,y,z);  ,     ,  ,  ,  ,  , 
			// "CREATE INDEX CONSUMER_INPUT_LIVE_GED_INDEX ON "+ MYDATABASE_TABLE + " ( "+a+" , "+b+" , "+c+" , "+d+" , "+e+" , "+.+" , "+.+" , "+y+" , "+z +") ; "
			db.execSQL("CREATE INDEX CONSUMER_INPUT_LIVE_INDEX ON "+ MYDATABASE_TABLE + " ( "+KEY_ID+" , "+KEY_BILLEDSTATUS+" , "+KEY_STATUS+" , "+KEY_CONSUMERNO+" , "+KEY_CONSUMER_SC_NO+" , "+KEY_INSTALLATIONO+" , "+KEY_TARIFF+" ) ; ");
			logger.debug("CREATE INDEX CONSUMER_INPUT_LIVE_INDEX ON "+ MYDATABASE_TABLE + " ( "+KEY_ID+" , "+KEY_BILLEDSTATUS+" , "+KEY_STATUS+" , "+KEY_CONSUMERNO+" , "+KEY_CONSUMER_SC_NO+" , "+KEY_INSTALLATIONO+" , "+KEY_TARIFF+" ) ; ");
			/*for(int colCount = 0 ; colCount > 8 ; colCount++)
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
	
	public Cursor GetTariffDetails_for_ECFC(String meterno) throws SQLException {

		String q = "SELECT * FROM " + MYDATABASE_TABLE + " WHERE METERNO='"
				+ meterno + "' ;";
		Cursor mCursor = sqLiteDatabase.rawQuery(q, null);
		if (mCursor != null) {
			mCursor.moveToFirst();

		}
		sqLiteDatabase.close();
		return mCursor;

	}
	
	
	
	/**
	 * @param sdoCode
	 * @param mrCode
	 * @return true if sdoCode and MR CODE is having data 
	 */
	public boolean isUserValid(String siteCode	, String mrCode) {
		boolean result = false;
		int count =0;
		   Cursor cursor = null;
		try{
		String selectQuery = "SELECT count(*) as TOTALCOUNT FROM  "+ MYDATABASE_TABLE + "  WHERE  "+ KEY_SITECODE + "  = upper('"+siteCode+"')  AND  "+ KEY_MRCODE + "  =  upper('"+mrCode.trim()+"')   AND  "+KEY_BILLEDSTATUS+"  <> '1' ;" ;
		logger.debug(" query : " + selectQuery);
      cursor = sqLiteDatabase.rawQuery(selectQuery, null);
      if (cursor.moveToFirst()) {
    	  count = Integer.parseInt(cursor.getString(cursor.getColumnIndex("TOTALCOUNT")));
    	     if(count > 0){
    	    	 result = true;
    	     }
	    }
	    cursor.close();
		}catch (Exception e) {
			 result = false ;
			if(cursor!=null)
				cursor.close();
			e.printStackTrace();
		}
		
		return result;

	}
	
	
	/**
	 * @return CONSUMER NO
	 */
	public String getConsumerno() {
		String consumerno = "";
		 Cursor cursor = null;
		  try{
		 String countQuery = "SELECT "+KEY_CONSUMER_SC_NO+" FROM  "+ MYDATABASE_TABLE+" where "+KEY_BILLEDSTATUS+" <> '1'   LIMIT 1 ";
	         cursor = sqLiteDatabase.rawQuery(countQuery, null);
	        if(cursor.moveToFirst())
	        consumerno = cursor.getString(cursor.getColumnIndex(KEY_CONSUMER_SC_NO));
	        cursor.close();
		  }catch (Exception e) {
				e.printStackTrace();
				if(cursor!=null)
					cursor.close();
			}
		return consumerno;
	}
	
	
	
	public Cursor isMRValid(String mrcode ,String sitcode ) throws SQLException {

		String q = "SELECT  * FROM " + MYDATABASE_TABLE + " WHERE   "+KEY_MRCODE+"  = '"	+ mrcode + "'  AND  "+KEY_SITECODE+"  = '"+sitcode +"' ;";

		logger.debug("query :- " + q);
		Cursor mCursor = sqLiteDatabase.rawQuery(q, null);
		if (mCursor != null) {
			mCursor.moveToFirst();

		}
		sqLiteDatabase.close();
		return mCursor;
	}
	
	/* public int Changesgpastatus(String RRNO,String status) throws SQLException {

		  ContentValues args = new ContentValues();
		  args.put(KEY_STATUS, status);

		  int flag = sqLiteDatabase.update(MYDATABASE_TABLE, args, "CONSUMER_SC_NO = '"
		    + RRNO + "'", null);
		  sqLiteDatabase.close();
		  System.out.println("flag :  "+ flag);
		  return flag;

		 }*/
	 
	/**
	 * UPDATE SERVER RESPONSE TO LOCAL DB 
	 *
	 */

public int setServerUpdateResponse(String consumernumber, String response) {
	int i = 0 ;
	try {
		ContentValues values = new ContentValues();
		values.put(KEY_STATUS, response);
		i = sqLiteDatabase.update(MYDATABASE_TABLE, values, KEY_CONSUMER_SC_NO	+ " ='" + consumernumber + "'", null);
	} catch (Exception e) {
		e.printStackTrace();
	}
	return i ;
}
	
	 
	 
	 
	 public Cursor checkmReadAndBookNo(String mrcode, String bookNo) throws SQLException {
			

			String q = "SELECT  * FROM " + MYDATABASE_TABLE
					+ " WHERE TRIM( MRCODE) = TRIM('"+mrcode+"')  AND  TRIM(BOOKNO) =   TRIM('"+ bookNo +"') ;";
			
			logger.debug("query :- " +q);
			Cursor mCursor = sqLiteDatabase.rawQuery(q, null);
			if (mCursor != null) {
				mCursor.moveToFirst();

			}
			sqLiteDatabase.close();
			return mCursor;
		}
	 

	 
/********Billed date ***********/
	 
	 public Cursor checkmBilledDate() throws SQLException {

		String q = "SELECT ( BILLEDATETIMESTAMP) FROM " + MYDATABASE_TABLE + " WHERE  BILLEDSTATUS='1'  ORDER BY BILLEDATETIMESTAMP DESC";

		logger.debug("query :- " + q);
		Cursor mCursor = sqLiteDatabase.rawQuery(q, null);
		if (mCursor != null) {
			mCursor.moveToFirst();

		}
		sqLiteDatabase.close();
		return mCursor;
	}
 

	 
	 public Cursor checkmReadMR() throws SQLException {

			String q = "SELECT  MRCODE , BOOKNO , SITECODE  FROM " + MYDATABASE_TABLE + ";";
			logger.debug("query :- " + q);
			Cursor mCursor = sqLiteDatabase.rawQuery(q, null);
			if (mCursor != null) {
				mCursor.moveToFirst();

			}
			sqLiteDatabase.close();
			return mCursor;
		}
		
	 public Cursor checkMblDate() throws SQLException {

			String q = "SELECT  SERVERTOSBMDATE FROM  " + MYDATABASE_TABLE + "  ORDER BY SERVERTOSBMDATE DESC ;";

			logger.debug("query :- " + q);
			Cursor mCursor = sqLiteDatabase.rawQuery(q, null);
			if (mCursor != null) {
				mCursor.moveToFirst();

			}
			sqLiteDatabase.close();
			return mCursor;
		}
	 
	//SELECT EXTRA1 ,ID  FROM CONSUMER_INPUT_LIVE_GED ORDER BY ROWID ASC LIMIT 1 
	 
	 
	 public Cursor getDBStatus(){
		 String q = "SELECT " + KEY_PF_PENALITY + " , " + KEY_PF_READING + " , " + KEY_EXTRA3 + " , " + KEY_EXTRA4 + " , " + KEY_EXTRA5 + 
			     ", " + KEY_EXTRA6 + ", " + KEY_EXTRA7 + ", " + KEY_EXTRA8 + " FROM " + MYDATABASE_TABLE + " ORDER BY ROWID ASC LIMIT 2 ;" ;

			logger.debug(" select query :- " + q);
			Cursor mCursor = sqLiteDatabase.rawQuery(q, null);
			if (mCursor != null) {
				mCursor.moveToFirst();

			}
			sqLiteDatabase.close();
			return mCursor;
	 }
	 
	 /*
	  * CURRENT RUNNING 
	  * public String[] getDashBoardStatus(){
		 String[]  result= new String[4];
		 // TOTAL , BILLED , SYNCH
		 result[0] = getTotalCount() ;
		 result[1] = Getbillledcount() ;
		 result[2] = GetBilledupdatedcountfromlocal();
		 result[3] = GetBilledcountfromlocal();
		 return result ;
	 }*/
	 
	 
	 public String[] getDashBoardStatus(){
		String[]  result= new String[4];
		 // TOTAL , BILLED , SYNCH
		/* result[0] = getTotalCount() ;
		 result[1] = Getbillledcount() ;
		 result[2] = GetBilledupdatedcountfromlocal();
		 result[3] = GetBilledcountfromlocal();*/
		 
		 int total_count = 0 ;
		 int billed_count = 0 ;
		 int sync_count = 0 ;
		 
		 String q = "SELECT  BILLEDSTATUS , STATUS, count(BILLEDSTATUS) AS TOTAL_COUNT FROM  " + MYDATABASE_TABLE+ "  GROUP BY BILLEDSTATUS , STATUS;";
			logger.debug("query :- "+q);
			Cursor mCursor = sqLiteDatabase.rawQuery(q, null);
			if (mCursor != null && mCursor.moveToFirst()) {
				do {
					int bil_st=0 , synch_st=0 , total_ct=0 ;
					try {
						
					
					bil_st =   mCursor.getInt(mCursor.getColumnIndex("BILLEDSTATUS")) ;
					synch_st =   mCursor.getInt(mCursor.getColumnIndex("STATUS")) ;
					total_ct =   mCursor.getInt(mCursor.getColumnIndex("TOTAL_COUNT")) ;
					
					if(bil_st== 1){
						billed_count += total_ct ;
					}
					if(bil_st== 1 &&  synch_st != 0 ){
						sync_count += total_ct ;
					}
					
					total_count += total_ct ;
					} catch (Exception e) {
						// TODO: handle exception
					}
				 } while (mCursor.moveToNext());

			}mCursor.close();
		 
			
			result[0] = String.valueOf(total_count) ;
			 result[1] = String.valueOf(billed_count) ; 
			 result[2] =  String.valueOf(sync_count);
			 result[3] = String.valueOf(billed_count -sync_count) ; 
			
		 return result ;
	 }
	 
	 /*--------NEW METHOD : NOT TO CHANGE EXISITING FLOW----------*/
	 public String[] getLiveStatus(){
			String[]  result= new String[6];
			 // TOTAL , BILLED , SYNCH , SITECODE , MRCODE
			/* result[0] = getTotalCount() ;
			 result[1] = Getbillledcount() ;
			 result[2] = GetBilledupdatedcountfromlocal();
			 result[3] = GetBilledcountfromlocal();*/
			 
			 int total_count = 0 ;
			 int billed_count = 0 ;
			 int sync_count = 0 ;
			 String siteCode = null, mrCode = null ;
			 
			 String q = "SELECT  BILLEDSTATUS , STATUS, count(BILLEDSTATUS) AS TOTAL_COUNT ,  "+KEY_SITECODE+" , "+KEY_MRCODE+"  FROM  " + MYDATABASE_TABLE+ "  GROUP BY BILLEDSTATUS , STATUS;";
				logger.debug("query :- "+q);
				Cursor mCursor = sqLiteDatabase.rawQuery(q, null);
				if (mCursor != null && mCursor.moveToFirst()) {
					siteCode = mCursor.getString(mCursor.getColumnIndex(KEY_SITECODE)) ;
					mrCode = mCursor.getString(mCursor.getColumnIndex(KEY_MRCODE)) ;
					do {
						int bil_st=0 , synch_st=0 , total_ct=0 ;
						try {
							
						
						bil_st =   mCursor.getInt(mCursor.getColumnIndex("BILLEDSTATUS")) ;
						synch_st =   mCursor.getInt(mCursor.getColumnIndex("STATUS")) ;
						total_ct =   mCursor.getInt(mCursor.getColumnIndex("TOTAL_COUNT")) ;
						
						if(bil_st== 1){
							billed_count += total_ct ;
						}
						if(bil_st== 1 &&  synch_st != 0 ){
							sync_count += total_ct ;
						}
						
						total_count += total_ct ;
						} catch (Exception e) {
							// TODO: handle exception
						}
					 } while (mCursor.moveToNext());

				}mCursor.close();
			 
				
				result[0] = String.valueOf(total_count) ;
				 result[1] = String.valueOf(billed_count) ; 
				 result[2] =  String.valueOf(sync_count);
				 result[3] = String.valueOf(billed_count -sync_count) ; 
				 result[4] = siteCode ;
				 result[5] = mrCode ;
			 return result ;
		 }
	 
	 
	 
	/* public void addColumns() throws SQLException 
	 {

		//	String q = "SELECT  SERVERTOSBMDATE FROM  " + MYDATABASE_TABLE + "  ORDER BY SERVERTOSBMDATE DESC ;";
		
		 String q = " ALTER TABLE  " + MYDATABASE_TABLE + "  ADD COLUMN  " + KEY_PF_PENALITY + " text ; " ;
		 sqLiteDatabase.execSQL(q);
			
		 q = " ALTER TABLE  " + MYDATABASE_TABLE + "  ADD COLUMN  " + KEY_PF_READING + " text ; " ;
		 sqLiteDatabase.execSQL(q);
			
		 q = " ALTER TABLE  " + MYDATABASE_TABLE + "  ADD COLUMN  " + KEY_EXTRA3 + " text ; " ;
		 sqLiteDatabase.execSQL(q);
			
			
		 q = " ALTER TABLE  " + MYDATABASE_TABLE + "  ADD COLUMN  " + KEY_EXTRA4 + " text ; " ;
		 sqLiteDatabase.execSQL(q);
			
			
		 q = " ALTER TABLE  " + MYDATABASE_TABLE + "  ADD COLUMN  " + KEY_EXTRA5 + " text ; " ;
		 sqLiteDatabase.execSQL(q);
			
			
		 q = " ALTER TABLE  " + MYDATABASE_TABLE + "  ADD COLUMN  " + KEY_EXTRA6 + " text ; " ;
		 sqLiteDatabase.execSQL(q);
			
			
		 q = " ALTER TABLE  " + MYDATABASE_TABLE + "  ADD COLUMN  " + KEY_EXTRA7 + " text ; " ;
		 sqLiteDatabase.execSQL(q);
			
			
		 q = " ALTER TABLE  " + MYDATABASE_TABLE + "  ADD COLUMN  " + KEY_EXTRA8 + " text ; " ;
		 sqLiteDatabase.execSQL(q);
			
		 sqLiteDatabase.close();
			
		}*/
	 
	 
}
