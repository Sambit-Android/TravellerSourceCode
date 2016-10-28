
package com.utils;

import java.io.File;
import java.util.List;

import com.utils.helper.BackupHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;

public class DatabaseBackup {

	static File dir = new File(Environment.getExternalStorageDirectory() + "");
	
	
	private static String MYDATABASE_NAME = dir + "/CESCTRM/CESCTRMELECTRICITYBOARD_BACKUP.db";// dir+"/


	public static final String MYDATABASE_TABLE = "CONSUMER_INPUT_LIVE_BACKUP";
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
	public String exist = "0";
	public static final String  KEY_TAX = "TAX";
	public static final String  KEY_EC = " KEY_EC ";
	public static final  String  KEY_FC = " KEY_FC";
	
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
+KEY_IMAGEPATH +		"  text ," 

+KEY_LONGITUDE +		"  text ," 
+KEY_LATTITUDE +		"  text ," 
+KEY_TAKENTIME +		"  text ," 

+KEY_EXTRA3 +		"  text ," 
+KEY_EXTRA4 +		"  text ," 
+KEY_EXTRA5 +		"  text ," 
+KEY_EXTRA6 +		"  text ," 
+KEY_EXTRA7 +		"  text ," 
+KEY_EXTRA8+		"  text  );" ;
	
	

	private SQLiteHelper sqLiteHelper;
	private SQLiteDatabase sqLiteDatabase;

	private Context context;

	public DatabaseBackup(Context c) {
		context = c;
	}

	public DatabaseBackup openToRead() throws android.database.SQLException {
		sqLiteHelper = new SQLiteHelper(context, MYDATABASE_NAME, null,
				MYDATABASE_VERSION);
		sqLiteDatabase = sqLiteHelper.getReadableDatabase();
		return this;
	}

	public DatabaseBackup openToWrite() throws android.database.SQLException {
		sqLiteHelper = new SQLiteHelper(context, MYDATABASE_NAME, null,
				MYDATABASE_VERSION);
		sqLiteDatabase = sqLiteHelper.getWritableDatabase();
		return this;
	}

	public void close() {
		sqLiteHelper.close();
	}
  

	
	
	public void insert(List<BackupHelper> bList ) {
		System.out.println("insert");

		boolean db_status = checkDataBase();
		System.out.println("db_status" + db_status);
		BackupHelper  backupHelper ;
		for (int cout_list = 0; cout_list < bList.size(); cout_list++) 
		{
			backupHelper  =bList.get(cout_list);
			System.out.println(" >>>>>>>>>>>>>>>>>>>>>> cout_list :" + backupHelper.mbconsumer_sc_no );
			if (db_status) {/*
				
				String q = "SELECT CONSUMER_SC_NO FROM " + MYDATABASE_TABLE
						+ " WHERE  " + KEY_CONSUMER_SC_NO + "  = '"	+ backupHelper.mbconsumer_sc_no + "'  " +
						"   AND  "+KEY_BILLMONTH+ "   =  '"	+ backupHelper.mbbillmonth + "'              ;";
				Cursor mCursor = sqLiteDatabase.rawQuery(q, null);
				if (mCursor != null && mCursor.moveToFirst()) {
					mCursor.moveToFirst();
					System.out.println("step 2************************************************************");

				} else {*/
					
					ContentValues contentValues = new ContentValues();
					contentValues.put(	KEY_CONSUMER_SC_NO		,	backupHelper.	mbconsumer_sc_no			);
					contentValues.put(	KEY_METER_CONSTANT		,	backupHelper.	mbmeter_constant			);
					contentValues.put(	KEY_CONSUMER_NAME		,	backupHelper.	mbconsumer_name			);
					contentValues.put(	KEY_ADDRESS		,	backupHelper.	mbaddress			);
					contentValues.put(	KEY_ADDRESS1		,	backupHelper.	mbaddress1			);
					contentValues.put(	KEY_TARIFF		,	backupHelper.	mbtariff			);
					contentValues.put(	KEY_TARIFFDESC		,	backupHelper.	mbtariffdesc			);
					contentValues.put(	KEY_LEDGER_NO		,	backupHelper.	mbledger_no			);
					contentValues.put(	KEY_FOLIO_NO		,	backupHelper.	mbfolio_no			);
					contentValues.put(	KEY_CONNECTED_LOAD		,	backupHelper.	mbconnected_load			);
					contentValues.put(	KEY_D_AND_R_FEE		,	backupHelper.	mbd_and_r_fee			);
					contentValues.put(	KEY_ARREARS		,	backupHelper.	mbarrears			);
					contentValues.put(	KEY_INTEREST		,	backupHelper.	mbinterest			);
					contentValues.put(	KEY_OTHERS		,	backupHelper.	mbothers			);
					contentValues.put(	KEY_BACKBILLARR		,	backupHelper.	mbbackbillarr			);
					contentValues.put(	KEY_AVERAGE_CONSUMPTION		,	backupHelper.	mbaverage_consumption			);
					contentValues.put(	KEY_DL_OR_MNR_PREV_MONTH		,	backupHelper.	mbdl_or_mnr_prev_month			);
					contentValues.put(	KEY_PREVIOUS_READING		,	backupHelper.	mbprevious_reading			);
					contentValues.put(	KEY_PRESENTREADING		,	backupHelper.	mbpresent_reading			);
					contentValues.put(	KEY_CONSUMPTION		,	backupHelper.	mbconsumption			);
					contentValues.put(	KEY_POWER_FACTOR		,	backupHelper.	mbpower_factor			);
					contentValues.put(	KEY_READING_DATE		,	backupHelper.	mbreading_date			);
					contentValues.put(	KEY_METER_CHANGE_UNITS_CONSUMED		,	backupHelper.	mbmeter_change_units_consumed			);
					contentValues.put(	KEY_NO_OF_MONTHS_ISSUED		,	backupHelper.	mbno_of_months_issued			);
					contentValues.put(	KEY_CREDIT_OR_REBATE		,	backupHelper.	mbcredit_or_rebate			);
					contentValues.put(	KEY_FIXED_GES		,	backupHelper.	mbfixed_ges			);
					contentValues.put(	KEY_AUDIT_ARREARS		,	backupHelper.	mbaudit_arrears			);
					contentValues.put(	KEY_OLD_INTEREST		,	backupHelper.	mbold_interest			);
					contentValues.put(	KEY_TRIVECTOR		,	backupHelper.	mbtrivector			);
					contentValues.put(	KEY_CKWHLKWH		,	backupHelper.	mckwhlkwh			);
					contentValues.put(	KEY_DOORLOCKAVG		,	backupHelper.	mbdoorlockavg			);
					contentValues.put(	KEY_CONSUMERCODE		,	backupHelper.	mbconsumercode			);
					contentValues.put(	KEY_ADDITIONALDEP		,	backupHelper.	mbadditionaldep			);
					contentValues.put(	KEY_MRCODE		,	backupHelper.	mbmrcode			);
					contentValues.put(	KEY_BILLMONTH		,	backupHelper.	mbbillmonth			);
					contentValues.put(	KEY_SITECODE		,	backupHelper.	mbsitecode			);
					contentValues.put(	KEY_SYNCSTATUS		,	backupHelper.	mbsyncstatus			);
					contentValues.put(	KEY_DATAPREPAREDDATE		,	backupHelper.	mbdataprepareddate			);
					contentValues.put(	KEY_SERVERTOSBMDATE		,	backupHelper.	mbservertosbmdate			);
					contentValues.put(	KEY_METERNO		,	backupHelper.	mbmeterno			);
					contentValues.put(	KEY_INTERESTONDEPOSIT		,	backupHelper.	mbinterestondeposit			);
					contentValues.put(	KEY_DL_ADJ		,	backupHelper.	mbdl_adj			);
					contentValues.put(	KEY_DL_COUNT		,	backupHelper.	mbdl_count			);
					contentValues.put(	KEY_METERRENT		,	backupHelper.	mbmeterrent			);
					contentValues.put(	KEY_FPPCA		,	backupHelper.	mbfppca			);
					contentValues.put(	KEY_TAX		,	backupHelper.	mbtax			);
					contentValues.put(	KEY_EC		,	backupHelper.	mbkey_ec			);
					contentValues.put(	KEY_FC		,	backupHelper.	mbkey_fc			);
					contentValues.put(	KEY_TOTAL		,	backupHelper.	mbkey_toatl			);
					contentValues.put(	KEY_ROUNDOFF		,	backupHelper.	mbroundoff			);
					contentValues.put(	KEY_ROUNDOFF_TOTAL		,	backupHelper.	mbkey_roundofftoatl			);
					contentValues.put(	KEY_PREBILLDATE		,	backupHelper.	mbkey_prebilldate			);
					contentValues.put(	KEY_BILLDATE		,	backupHelper.	mbkey_billdate			);
					contentValues.put(	KEY_DUEDATE		,	backupHelper.	mbkey_duedate			);
					contentValues.put(	KEY_EXTRA1		,	backupHelper.	mbextra1			);
					contentValues.put(	KEY_EXTRA2		,	backupHelper.	mbextra2			);
					contentValues.put(	KEY_CYCLENAME		,	backupHelper.	mbcyclename			);
					contentValues.put(	KEY_CONSUMER_KEY		,	backupHelper.	mbconsumer_key			);
					contentValues.put(	KEY_INSTALLATIONO		,	backupHelper.	mbinstallationo			);
					contentValues.put(	KEY_CONSUMERNO		,	backupHelper.	mbconsumerno			);
					contentValues.put(	KEY_DIVISION		,	backupHelper.	mbdivision			);
					contentValues.put(	KEY_SUBDIVISION		,	backupHelper.	mbsubdivision			);
					contentValues.put(	KEY_BOOKNO		,	backupHelper.	mbbookno			);
					contentValues.put(	KEY_DEVICEFIRMWAREVERSION		,	backupHelper.	mbdevicefirmwareversion			);
					contentValues.put(	KEY_BILLEDATETIMESTAMP		,	backupHelper.	mbbilledatetimestamp			);
					contentValues.put(	KEY_BILLNO		,	backupHelper.	mbbillno			);
					contentValues.put(	KEY_PRESENTMETERSTATUS		,	backupHelper.	mbpresentmeterstatus			);
					contentValues.put(	KEY_BILLEDSTATUS		,	backupHelper.	mbbilledstatus			);
					contentValues.put(	KEY_STATUS		,	backupHelper.	mbstatus			);
					contentValues.put(	PREVIOUS_BILLEDDATE		,	backupHelper.	mbprevious_reading_date			);
					contentValues.put(	ACTUAL_PREVIOUS_BILLEDDATE 		,	backupHelper.	mbprevious_actual_reading_date			);
					contentValues.put(	KEY_LINEMINIMUM 		,	backupHelper.	mblineminimum			);
					contentValues.put(	  SEASONAL_CONSUMER 		,	backupHelper.	mbseasonal_consumer			);
					contentValues.put(	  KEY_LIGPOINTS 		,	backupHelper.	mbligpoints			);
					contentValues.put(	  KEY_METERED 		,	backupHelper.	mbmetered			);
					contentValues.put(	KEY_PF_READING		,	backupHelper.	mbpf_reading			);
					contentValues.put(	KEY_PF_PENALITY		,	backupHelper.	mbpf_penality			);
					
					contentValues.put(	KEY_BMD_PENALITY		,	backupHelper.	bmd_penality			);
					contentValues.put(	KEY_BMD_READING_PENALITY		,	backupHelper.	bmd_reading			);
					
					contentValues.put(	KEY_IMAGEPATH		,	backupHelper.	mbimagepath			);
					contentValues.put(	KEY_LONGITUDE		,	backupHelper.	mblongitude			);
					contentValues.put(	KEY_LATTITUDE		,	backupHelper.	mblattitude			);
					contentValues.put(	KEY_TAKENTIME		,	backupHelper.	mbtakentime			);
					contentValues.put(	KEY_EXTRA3		,	backupHelper.	mbextra3			);
					contentValues.put(	KEY_EXTRA4		,	backupHelper.	mbextra4			);
					contentValues.put(	KEY_EXTRA5		,	backupHelper.	mbextra5			);
					contentValues.put(	KEY_EXTRA6		,	backupHelper.	mbextra6			);
					contentValues.put(	KEY_EXTRA7		,	backupHelper.	mbextra7			);
					contentValues.put(	KEY_EXTRA8		,	backupHelper.	mbextra8			);

					System.out.println("insert");
					sqLiteDatabase.insert(MYDATABASE_TABLE, null, contentValues);
				/*}*/
				
			}else{
				ContentValues contentValues = new ContentValues();
				contentValues.put(	KEY_CONSUMER_SC_NO		,	backupHelper.	mbconsumer_sc_no			);
				contentValues.put(	KEY_METER_CONSTANT		,	backupHelper.	mbmeter_constant			);
				contentValues.put(	KEY_CONSUMER_NAME		,	backupHelper.	mbconsumer_name			);
				contentValues.put(	KEY_ADDRESS		,	backupHelper.	mbaddress			);
				contentValues.put(	KEY_ADDRESS1		,	backupHelper.	mbaddress1			);
				contentValues.put(	KEY_TARIFF		,	backupHelper.	mbtariff			);
				contentValues.put(	KEY_TARIFFDESC		,	backupHelper.	mbtariffdesc			);
				contentValues.put(	KEY_LEDGER_NO		,	backupHelper.	mbledger_no			);
				contentValues.put(	KEY_FOLIO_NO		,	backupHelper.	mbfolio_no			);
				contentValues.put(	KEY_CONNECTED_LOAD		,	backupHelper.	mbconnected_load			);
				contentValues.put(	KEY_D_AND_R_FEE		,	backupHelper.	mbd_and_r_fee			);
				contentValues.put(	KEY_ARREARS		,	backupHelper.	mbarrears			);
				contentValues.put(	KEY_INTEREST		,	backupHelper.	mbinterest			);
				contentValues.put(	KEY_OTHERS		,	backupHelper.	mbothers			);
				contentValues.put(	KEY_BACKBILLARR		,	backupHelper.	mbbackbillarr			);
				contentValues.put(	KEY_AVERAGE_CONSUMPTION		,	backupHelper.	mbaverage_consumption			);
				contentValues.put(	KEY_DL_OR_MNR_PREV_MONTH		,	backupHelper.	mbdl_or_mnr_prev_month			);
				contentValues.put(	KEY_PREVIOUS_READING		,	backupHelper.	mbprevious_reading			);
				contentValues.put(	KEY_PRESENTREADING		,	backupHelper.	mbpresent_reading			);
				contentValues.put(	KEY_CONSUMPTION		,	backupHelper.	mbconsumption			);
				contentValues.put(	KEY_POWER_FACTOR		,	backupHelper.	mbpower_factor			);
				contentValues.put(	KEY_READING_DATE		,	backupHelper.	mbreading_date			);
				contentValues.put(	KEY_METER_CHANGE_UNITS_CONSUMED		,	backupHelper.	mbmeter_change_units_consumed			);
				contentValues.put(	KEY_NO_OF_MONTHS_ISSUED		,	backupHelper.	mbno_of_months_issued			);
				contentValues.put(	KEY_CREDIT_OR_REBATE		,	backupHelper.	mbcredit_or_rebate			);
				contentValues.put(	KEY_FIXED_GES		,	backupHelper.	mbfixed_ges			);
				contentValues.put(	KEY_AUDIT_ARREARS		,	backupHelper.	mbaudit_arrears			);
				contentValues.put(	KEY_OLD_INTEREST		,	backupHelper.	mbold_interest			);
				contentValues.put(	KEY_TRIVECTOR		,	backupHelper.	mbtrivector			);
				contentValues.put(	KEY_CKWHLKWH		,	backupHelper.	mckwhlkwh			);
				contentValues.put(	KEY_DOORLOCKAVG		,	backupHelper.	mbdoorlockavg			);
				contentValues.put(	KEY_CONSUMERCODE		,	backupHelper.	mbconsumercode			);
				contentValues.put(	KEY_ADDITIONALDEP		,	backupHelper.	mbadditionaldep			);
				contentValues.put(	KEY_MRCODE		,	backupHelper.	mbmrcode			);
				contentValues.put(	KEY_BILLMONTH		,	backupHelper.	mbbillmonth			);
				contentValues.put(	KEY_SITECODE		,	backupHelper.	mbsitecode			);
				contentValues.put(	KEY_SYNCSTATUS		,	backupHelper.	mbsyncstatus			);
				contentValues.put(	KEY_DATAPREPAREDDATE		,	backupHelper.	mbdataprepareddate			);
				contentValues.put(	KEY_SERVERTOSBMDATE		,	backupHelper.	mbservertosbmdate			);
				contentValues.put(	KEY_METERNO		,	backupHelper.	mbmeterno			);
				contentValues.put(	KEY_INTERESTONDEPOSIT		,	backupHelper.	mbinterestondeposit			);
				contentValues.put(	KEY_DL_ADJ		,	backupHelper.	mbdl_adj			);
				contentValues.put(	KEY_DL_COUNT		,	backupHelper.	mbdl_count			);
				contentValues.put(	KEY_METERRENT		,	backupHelper.	mbmeterrent			);
				contentValues.put(	KEY_FPPCA		,	backupHelper.	mbfppca			);
				contentValues.put(	KEY_TAX		,	backupHelper.	mbtax			);
				contentValues.put(	KEY_EC		,	backupHelper.	mbkey_ec			);
				contentValues.put(	KEY_FC		,	backupHelper.	mbkey_fc			);
				contentValues.put(	KEY_TOTAL		,	backupHelper.	mbkey_toatl			);
				contentValues.put(	KEY_ROUNDOFF		,	backupHelper.	mbroundoff			);
				contentValues.put(	KEY_ROUNDOFF_TOTAL		,	backupHelper.	mbkey_roundofftoatl			);
				contentValues.put(	KEY_PREBILLDATE		,	backupHelper.	mbkey_prebilldate			);
				contentValues.put(	KEY_BILLDATE		,	backupHelper.	mbkey_billdate			);
				contentValues.put(	KEY_DUEDATE		,	backupHelper.	mbkey_duedate			);
				contentValues.put(	KEY_EXTRA1		,	backupHelper.	mbextra1			);
				contentValues.put(	KEY_EXTRA2		,	backupHelper.	mbextra2			);
				contentValues.put(	KEY_CYCLENAME		,	backupHelper.	mbcyclename			);
				contentValues.put(	KEY_CONSUMER_KEY		,	backupHelper.	mbconsumer_key			);
				contentValues.put(	KEY_INSTALLATIONO		,	backupHelper.	mbinstallationo			);
				contentValues.put(	KEY_CONSUMERNO		,	backupHelper.	mbconsumerno			);
				contentValues.put(	KEY_DIVISION		,	backupHelper.	mbdivision			);
				contentValues.put(	KEY_SUBDIVISION		,	backupHelper.	mbsubdivision			);
				contentValues.put(	KEY_BOOKNO		,	backupHelper.	mbbookno			);
				contentValues.put(	KEY_DEVICEFIRMWAREVERSION		,	backupHelper.	mbdevicefirmwareversion			);
				contentValues.put(	KEY_BILLEDATETIMESTAMP		,	backupHelper.	mbbilledatetimestamp			);
				contentValues.put(	KEY_BILLNO		,	backupHelper.	mbbillno			);
				contentValues.put(	KEY_PRESENTMETERSTATUS		,	backupHelper.	mbpresentmeterstatus			);
				contentValues.put(	KEY_BILLEDSTATUS		,	backupHelper.	mbbilledstatus			);
				contentValues.put(	KEY_STATUS		,	backupHelper.	mbstatus			);
				contentValues.put(	PREVIOUS_BILLEDDATE		,	backupHelper.	mbprevious_reading_date			);
				contentValues.put(	ACTUAL_PREVIOUS_BILLEDDATE 		,	backupHelper.	mbprevious_actual_reading_date			);
				contentValues.put(	KEY_LINEMINIMUM 		,	backupHelper.	mblineminimum			);
				contentValues.put(	  SEASONAL_CONSUMER 		,	backupHelper.	mbseasonal_consumer			);
				contentValues.put(	  KEY_LIGPOINTS 		,	backupHelper.	mbligpoints			);
				contentValues.put(	  KEY_METERED 		,	backupHelper.	mbmetered			);
				contentValues.put(	KEY_PF_READING		,	backupHelper.	mbpf_reading			);
				contentValues.put(	KEY_PF_PENALITY		,	backupHelper.	mbpf_penality			);
				
				contentValues.put(	KEY_BMD_PENALITY		,	backupHelper.	bmd_penality			);
				contentValues.put(	KEY_BMD_READING_PENALITY		,	backupHelper.	bmd_reading			);
				
				contentValues.put(	KEY_IMAGEPATH		,	backupHelper.	mbimagepath			);
				contentValues.put(	KEY_LONGITUDE		,	backupHelper.	mblongitude			);
				contentValues.put(	KEY_LATTITUDE		,	backupHelper.	mblattitude			);
				contentValues.put(	KEY_TAKENTIME		,	backupHelper.	mbtakentime			);
				contentValues.put(	KEY_EXTRA3		,	backupHelper.	mbextra3			);
				contentValues.put(	KEY_EXTRA4		,	backupHelper.	mbextra4			);
				contentValues.put(	KEY_EXTRA5		,	backupHelper.	mbextra5			);
				contentValues.put(	KEY_EXTRA6		,	backupHelper.	mbextra6			);
				contentValues.put(	KEY_EXTRA7		,	backupHelper.	mbextra7			);
				contentValues.put(	KEY_EXTRA8		,	backupHelper.	mbextra8			);

				System.out.println("insert");
				sqLiteDatabase.insert(MYDATABASE_TABLE, null, contentValues);
			}
			
			
			
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

	

	public Cursor checkuser() throws SQLException {
		
		String q = "SELECT  CONSUMER_SC_NO FROM " + MYDATABASE_TABLE
				+ " WHERE STATUS=' NOT UPDATED' AND BILLEDSTATUS='0';";
		
		System.out.println("query :- " +q);
		Cursor mCursor = sqLiteDatabase.rawQuery(q, null);
		if (mCursor != null) {
			mCursor.moveToFirst();

		}
		sqLiteDatabase.close();
		return mCursor;
	}

	public Cursor checkuser_RRNO() throws SQLException {

		String q = "SELECT  CONSUMER_SC_NO FROM " + MYDATABASE_TABLE + ";";
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
		System.out.println("query :- "+q);
		Cursor mCursor = sqLiteDatabase.rawQuery(q, null);
		if (mCursor != null) {
			mCursor.moveToFirst();

		}
		sqLiteDatabase.close();
		return mCursor;
	}
	
	public Cursor Gettotalcount() throws SQLException {
		

		String q = "SELECT  count(*) AS unbilled FROM " + MYDATABASE_TABLE+ ";";
		Cursor mCursor = sqLiteDatabase.rawQuery(q, null);
		if (mCursor != null) {
			mCursor.moveToFirst();

		}
		sqLiteDatabase.close();
		return mCursor;
	}

	public int Updatetolocaldb(String meterstatus, String presentreading, String presentmtstatus ,
			String consumed, String ec,String fc, String total,String fppca, String billno,
			 String rrno, String monthyear ,String billDate , String dueDate ,String tariffDesc
			 )
			throws SQLException {

		ContentValues args = new ContentValues();
	args.put(KEY_METER_CONSTANT, meterstatus);
		args.put(KEY_PRESENTREADING, presentreading);
		args.put(KEY_CONSUMPTION, consumed);
		args.put(KEY_EC, ec);
		args.put(KEY_FC, fc);

		args.put(KEY_TOTAL, total);
		
		args.put(KEY_BILLEDSTATUS, "1");
		
		args.put(KEY_FPPCA, fppca);
		args.put(KEY_BILLNO, billno);
		args.put(KEY_CONSUMER_SC_NO,rrno);
		args.put(KEY_BILLMONTH ,monthyear);
		args.put(KEY_BILLDATE ,billDate);
		args.put(KEY_DUEDATE ,dueDate);
		args.put(KEY_PRESENTMETERSTATUS, presentmtstatus); 
		args.put(KEY_TARIFFDESC, tariffDesc);
		
		int flag = sqLiteDatabase.update(MYDATABASE_TABLE, args, "CONSUMER_SC_NO='"
				+ rrno + "'", null);
		sqLiteDatabase.close();
		return flag;

	}

	public int ChangeStatus() throws SQLException {

		int flag = 0;

		sqLiteDatabase.close();
		return flag;

	}

	public int ChangeStatusonlineoffline(String RRNO) throws SQLException {

		int flag = 0;
		sqLiteDatabase.close();
		return flag;

	}

	

	public Cursor Getbillledcount() throws SQLException {
		

		String q = "SELECT  count(*) AS unbilled FROM " + MYDATABASE_TABLE
				+ " WHERE  BILLEDSTATUS='1';";
		System.out.println("query :- "+q);
		Cursor mCursor = sqLiteDatabase.rawQuery(q, null);
		if (mCursor != null) {
			mCursor.moveToFirst();

		}
		sqLiteDatabase.close();
		return mCursor;
	}

	public Cursor Getconsumer_details(String account_no) throws SQLException {
		
		String q = "SELECT CONSUMER_SC_NO,METER_CONSTANT,CONSUMER_NAME,ADDRESS,ADDRESS1,TARIFF,"+
					"LEDGER_NO,FOLIO_NO,CONNECTED_LOAD,D_AND_R_FEE,ARREARS,INTEREST,OTHERS,"+
				    "BACKBILLARR,AVERAGE_CONSUMPTION ,DL_OR_MNR_PREV_MONTH ,PREVIOUS_READING ,"
					+"POWER_FACTOR ,READING_DATE ,METER_CHANGE_UNITS_CONSUMED,NO_OF_MONTHS_ISSUED ,"
				    +"CREDIT_OR_REBATE ,FIXED_GES ,AUDIT_ARREARS ,OLD_INTEREST ,TRIVECTOR,DOORLOCKAVG "
					+",CONSUMERCODE, ADDITIONALDEP ,MRCODE,BILLMONTH ,SITECODE ,SYNCSTATUS, DATAPREPAREDDATE ,"
				    +"SERVERTOSBMDATE ,METERNO ,INTERESTONDEPOSIT ,DL_ADJ ,METERRENT ,FPPCA, EXTRA1 ,EXTRA2, BILLNO, " +
				    "STATUS,BILLEDSTATUS,KEY_BILLDATE,KEY_DUEDATE,KEY_Toatl, KEY_EC, KEY_FC, BILLNO, PRESENT_READING,TARIFFDESC,KEY_PREBILLDATE ,FPPCA, CONSUMPTION ,TAX ," +
				    " PRESENTMETERSTATUS,CYCLENAME,CONSUMER_KEY , INSTALLATIONO , CONSUMERNO , METERED FROM "
				+ MYDATABASE_TABLE + " WHERE CONSUMER_SC_NO='" + account_no + "' ;";
	
		System.out.println("query   "+q);
		Cursor mCursor = sqLiteDatabase.rawQuery(q, null);
		if (mCursor != null) {
			mCursor.moveToFirst();

		}
		sqLiteDatabase.close();
		return mCursor;
	}

	public Cursor Getconsumer_details_mtrnno(String meterno) throws SQLException {
		

		String q = "SELECT CONSUMER_SC_NO,METER_CONSTANT,CONSUMER_NAME,ADDRESS,ADDRESS1,TARIFF,"+
					"LEDGER_NO,FOLIO_NO,CONNECTED_LOAD,D_AND_R_FEE,ARREARS,INTEREST,OTHERS,"+
				    "BACKBILLARR,AVERAGE_CONSUMPTION ,DL_OR_MNR_PREV_MONTH ,PREVIOUS_READING ,"
					+"POWER_FACTOR ,READING_DATE ,METER_CHANGE_UNITS_CONSUMED,NO_OF_MONTHS_ISSUED ,"
				    +"CREDIT_OR_REBATE ,FIXED_GES ,AUDIT_ARREARS ,OLD_INTEREST ,TRIVECTOR,DOORLOCKAVG "
					+",CONSUMERCODE, ADDITIONALDEP ,MRCODE,BILLMONTH ,SITECODE ,SYNCSTATUS, DATAPREPAREDDATE ,"
				    +"SERVERTOSBMDATE ,METERNO ,INTERESTONDEPOSIT ,DL_ADJ ,METERRENT ,FPPCA, EXTRA1 ,EXTRA2, BILLNO, " +
				    "STATUS,BILLEDSTATUS,KEY_BILLDATE,KEY_DUEDATE,KEY_Toatl, KEY_EC, KEY_FC, BILLNO, PRESENT_READING,TARIFFDESC,KEY_PREBILLDATE ,FPPCA, CONSUMPTION ,TAX ," +
				    " PRESENTMETERSTATUS,CYCLENAME,CONSUMER_KEY , INSTALLATIONO , CONSUMERNO , METERED FROM "
				+ MYDATABASE_TABLE + " WHERE METERNO='" + meterno + "' ;";
		Cursor mCursor = sqLiteDatabase.rawQuery(q, null);
		if (mCursor != null) {
			mCursor.moveToFirst();

		}
		sqLiteDatabase.close();
		return mCursor;
	}

	public Cursor Fetchnextconsumer() throws SQLException {
		
		
		String q = "SELECT CONSUMER_SC_NO,METERNO,ID FROM " + MYDATABASE_TABLE
				+ " WHERE BILLEDSTATUS='0';";
		Cursor mCursor = sqLiteDatabase.rawQuery(q, null);
		if (mCursor != null) {
			mCursor.moveToFirst();

		}
		sqLiteDatabase.close();
		return mCursor;
	}

	public Cursor GetBilledcountfromlocal() throws SQLException {
		

		String q = "SELECT count(*) as billed FROM " + MYDATABASE_TABLE
				+ " WHERE BILLEDSTATUS='1' AND STATUS=' NOT UPDATED';";
		System.out.println("qurey :- "+q);
		Cursor mCursor = sqLiteDatabase.rawQuery(q, null);
		if (mCursor != null) {
			mCursor.moveToFirst();

		}
		sqLiteDatabase.close();
		return mCursor;
	}

	public Cursor GetBilledupdatedcountfromlocal() throws SQLException {
		

		String q = "SELECT count(*) as billed FROM " + MYDATABASE_TABLE
				+ " WHERE BILLEDSTATUS='1' AND STATUS='UPDATED';";
		System.out.println("qurey :- "+q);
		Cursor mCursor = sqLiteDatabase.rawQuery(q, null);
		if (mCursor != null) {
			mCursor.moveToFirst();

		}
		sqLiteDatabase.close();
		return mCursor;
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
				+ MYDATABASE_TABLE + " WHERE BILLEDSTATUS='1' AND STATUS=' NOT UPDATED';";
		Cursor mCursor = sqLiteDatabase.rawQuery(q, null);
		if (mCursor != null) {
			mCursor.moveToFirst();

		}
		sqLiteDatabase.close();
		return mCursor;
	}
	
	
	public Cursor Getbilledconsumersbackup() throws SQLException {
		

		String q = "SELECT * FROM "+MYDATABASE_TABLE+" WHERE BILLEDSTATUS='1' AND STATUS='UPDATED';";
		Cursor mCursor = sqLiteDatabase.rawQuery(q, null);
		if (mCursor != null) {
			mCursor.moveToFirst();

		}
		sqLiteDatabase.close();
		return mCursor;
	}
	
	
	
	
	public Cursor Getbilledconsumers_imagename() throws SQLException {
		

		String q = "SELECT IMAGENAME FROM "
				+ MYDATABASE_TABLE + " WHERE BILLEDSTATUS='1' AND PR <> 0 AND STATUS='NOT UPDATED';";
		Cursor mCursor = sqLiteDatabase.rawQuery(q, null);
		if (mCursor != null) {
			mCursor.moveToFirst();

		}
		sqLiteDatabase.close();
		return mCursor;
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
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS " + MYDATABASE_TABLE);
		}

	}
	
	public Cursor GetTariffDetails_for_ECFC(String meterno) throws SQLException {
		

		String q = "SELECT * FROM "
				+ MYDATABASE_TABLE + " WHERE METERNO='" + meterno + "' ;";
		Cursor mCursor = sqLiteDatabase.rawQuery(q, null);
		if (mCursor != null) {
			mCursor.moveToFirst();

		}
		sqLiteDatabase.close();
		return mCursor;
	
	}
	
	
	
	
	public Cursor checkmRead(String mrcode) throws SQLException {
		

		String q = "SELECT  * FROM " + MYDATABASE_TABLE
				+ " WHERE  MRCODE = '"+mrcode+"';";
		
		System.out.println("query :- " +q);
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

		  int flag = sqLiteDatabase.update(MYDATABASE_TABLE, args, "CONSUMER_SC_NO= '"
		    + RRNO + "'", null);
		  sqLiteDatabase.close();
		  return flag;

		 }
	 
	 
	 public Cursor getDBStatus(){
		 String q = "SELECT " + KEY_PF_PENALITY + " , " + KEY_PF_READING + " , " + KEY_EXTRA3 + " , " + KEY_EXTRA4 + " , " + KEY_EXTRA5 + 
			     ", " + KEY_EXTRA6 + ", " + KEY_EXTRA7 + ", " + KEY_EXTRA8 + " FROM " + MYDATABASE_TABLE + " ORDER BY ROWID ASC LIMIT 1 ;" ;

			System.out.println(" select query :- " + q);
			Cursor mCursor = sqLiteDatabase.rawQuery(q, null);
			if (mCursor != null) {
				mCursor.moveToFirst();

			}
			sqLiteDatabase.close();
			return mCursor;
	 }
	 
	 
	 public void addColumns() throws SQLException 
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
			
		}
	

}



















