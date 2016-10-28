package com.bcits.bsmartbilling;

import com.utils.DatabaseHandler;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;

public class AmrBillSupporter {
	
	
	public static boolean setConsumerDetails(Cursor cursor_next,Context context,MasterLibraryFunction libraryFunction,String mbatteryLevel ,String signalStrength11 ,String mmemoryTotal,String mmemoryAvailable)throws NumberFormatException, SQLException 
	{
		boolean result = false;

		UtilMaster. mconsumer_sc_no   =     	cursor_next.getString(cursor_next.getColumnIndex( 	DatabaseHandler. KEY_CONSUMER_SC_NO )).trim() ;                  
		UtilMaster. mmeter_constant   =     	cursor_next.getString(cursor_next.getColumnIndex(  	DatabaseHandler. KEY_METER_CONSTANT )).trim() ;
		UtilMaster. mconsumer_name   =     	cursor_next.getString(cursor_next.getColumnIndex( 	DatabaseHandler. KEY_CONSUMER_NAME )).trim() ;
		UtilMaster. maddress   =     	cursor_next.getString(cursor_next.getColumnIndex(  	DatabaseHandler. KEY_ADDRESS )).trim() ;
		UtilMaster. maddress1   =     	cursor_next.getString(cursor_next.getColumnIndex(  	DatabaseHandler. KEY_ADDRESS1 )).trim() ;

		UtilMaster. mtariff =	cursor_next.getString(cursor_next.getColumnIndex(  	DatabaseHandler. KEY_TARIFF )).trim() ;

		if (libraryFunction == null){
			libraryFunction = new MasterLibraryFunction(context);
		}

		UtilMaster. mtariffdesc =	libraryFunction.getTariffDesc(context, UtilMaster. mtariff);

		UtilMaster. mledger_no   =     	cursor_next.getString(cursor_next.getColumnIndex(  	DatabaseHandler. KEY_LEDGER_NO )).trim() ;
		UtilMaster. mfolio_no   =     	cursor_next.getString(cursor_next.getColumnIndex( 	DatabaseHandler. KEY_FOLIO_NO )).trim() ;
		UtilMaster. mconnected_load   =     	cursor_next.getString(cursor_next.getColumnIndex(  	DatabaseHandler. KEY_CONNECTED_LOAD)).trim() ;
		UtilMaster. md_and_r_fee   =     	cursor_next.getString(cursor_next.getColumnIndex(  	DatabaseHandler. KEY_D_AND_R_FEE )).trim() ;
		UtilMaster. marrears   =     	cursor_next.getString(cursor_next.getColumnIndex(  	DatabaseHandler. KEY_ARREARS )).trim() ;
		UtilMaster. minterest   =     	cursor_next.getString(cursor_next.getColumnIndex( 	DatabaseHandler. KEY_INTEREST )).trim() ;
		UtilMaster. mothers   =     	cursor_next.getString(cursor_next.getColumnIndex(  	DatabaseHandler. KEY_OTHERS )).trim() ;
		UtilMaster. mbackbillarr   =     	cursor_next.getString(cursor_next.getColumnIndex( 	DatabaseHandler. KEY_BACKBILLARR )).trim() ;

		UtilMaster. mdl_or_mnr_prev_month   =     	cursor_next.getString(cursor_next.getColumnIndex(  	DatabaseHandler. KEY_DL_OR_MNR_PREV_MONTH )).trim() ;

		UtilMaster.mpreviousreadingstatus = cursor_next.getString(cursor_next.getColumnIndex(  	DatabaseHandler. KEY_DL_OR_MNR_PREV_MONTH )).trim() ;
		UtilMaster. mprevious_reading  =     	cursor_next.getString(cursor_next.getColumnIndex(  	DatabaseHandler. KEY_PREVIOUS_READING )).trim() ;
		UtilMaster. mpresentreading  =     	cursor_next.getString(cursor_next.getColumnIndex( 	DatabaseHandler. KEY_PRESENTREADING )).trim() ;
		UtilMaster. mconsumption  =     	cursor_next.getString(cursor_next.getColumnIndex( 	DatabaseHandler. KEY_CONSUMPTION )).trim() ;
		UtilMaster. mpower_factor   =     	cursor_next.getString(cursor_next.getColumnIndex(  	DatabaseHandler. KEY_POWER_FACTOR )).trim() ;
		UtilMaster. mreading_date   =     	cursor_next.getString(cursor_next.getColumnIndex( 	DatabaseHandler. KEY_READING_DATE )).trim() ;
		UtilMaster. mmeter_change_units_consumed   =     	cursor_next.getString(cursor_next.getColumnIndex(  	DatabaseHandler. KEY_METER_CHANGE_UNITS_CONSUMED )).trim() ;

		UtilMaster. mno_of_months_issued   =     	cursor_next.getString(cursor_next.getColumnIndex( 	DatabaseHandler. KEY_NO_OF_MONTHS_ISSUED )).trim() ;
		UtilMaster.mActualBmForDL = UtilMaster. mno_of_months_issued;

		UtilMaster. maverage_consumption   =     	cursor_next.getString(cursor_next.getColumnIndex(  	DatabaseHandler. KEY_AVERAGE_CONSUMPTION )).trim() ;

		float averageConsumtion = (Float.valueOf(UtilMaster. maverage_consumption)/ Float.valueOf(UtilMaster. mno_of_months_issued ));

		UtilMaster. maverage_consumption = String.valueOf(averageConsumtion);

		System.out.println("UtilMaster. maverage_consumption IS :"+UtilMaster. maverage_consumption);

		UtilMaster. mcredit_or_rebate   =     	cursor_next.getString(cursor_next.getColumnIndex(  	DatabaseHandler. KEY_CREDIT_OR_REBATE)).trim() ;
		UtilMaster. mfixed_ges   =     	cursor_next.getString(cursor_next.getColumnIndex( 	DatabaseHandler. KEY_FIXED_GES )).trim() ;
		UtilMaster. maudit_arrears   =     	cursor_next.getString(cursor_next.getColumnIndex(  	DatabaseHandler. KEY_AUDIT_ARREARS )).trim() ;
		UtilMaster. mold_interest   =     	cursor_next.getString(cursor_next.getColumnIndex( 	DatabaseHandler. KEY_OLD_INTEREST )).trim() ;
		UtilMaster. mtrivector   =     	cursor_next.getString(cursor_next.getColumnIndex(  	DatabaseHandler. KEY_TRIVECTOR )).trim() ;
		UtilMaster. mckwhlkwh   =     	cursor_next.getString(cursor_next.getColumnIndex(  	DatabaseHandler. KEY_CKWHLKWH )).trim() ;
		UtilMaster. mdoorlockavg   =     	cursor_next.getString(cursor_next.getColumnIndex( 	DatabaseHandler. KEY_DOORLOCKAVG )).trim() ;
		UtilMaster. mconsumercode   =     	cursor_next.getString(cursor_next.getColumnIndex(  	DatabaseHandler. KEY_CONSUMERCODE )).trim() ;
		UtilMaster. madditionaldep   =     	cursor_next.getString(cursor_next.getColumnIndex( 	DatabaseHandler. KEY_ADDITIONALDEP)).trim() ;
		UtilMaster. mmrcode   =     	cursor_next.getString(cursor_next.getColumnIndex(  	DatabaseHandler. KEY_MRCODE )).trim() ;
		UtilMaster. mbillmonth   =     	cursor_next.getString(cursor_next.getColumnIndex(  	DatabaseHandler. KEY_BILLMONTH )).trim() ;
		UtilMaster. msitecode=	cursor_next.getString(cursor_next.getColumnIndex(  	DatabaseHandler. KEY_SITECODE )).trim() ;
		UtilMaster. msyncstatus   =     	cursor_next.getString(cursor_next.getColumnIndex(  	DatabaseHandler. KEY_SYNCSTATUS )).trim() ;
		UtilMaster. mdataprepareddate =	cursor_next.getString(cursor_next.getColumnIndex(  	DatabaseHandler. KEY_DATAPREPAREDDATE )).trim() ;
		UtilMaster. mservertosbmdate   =     	cursor_next.getString(cursor_next.getColumnIndex(  	DatabaseHandler. KEY_SERVERTOSBMDATE)).trim() ;
		UtilMaster. mmeterno   =     	cursor_next.getString(cursor_next.getColumnIndex(  	DatabaseHandler. KEY_METERNO )).trim() ;
		UtilMaster. minterestondeposit   =     	cursor_next.getString(cursor_next.getColumnIndex( 	DatabaseHandler. KEY_INTERESTONDEPOSIT )).trim() ;
		UtilMaster. mdl_adj   =     	cursor_next.getString(cursor_next.getColumnIndex( 	DatabaseHandler. KEY_DL_ADJ )).trim() ;
		UtilMaster. mdl_count   =     	cursor_next.getString(cursor_next.getColumnIndex( 	DatabaseHandler. KEY_DL_COUNT )).trim() ;
		UtilMaster. mmeterrent   =     	cursor_next.getString(cursor_next.getColumnIndex(  	DatabaseHandler. KEY_METERRENT )).trim() ;
		UtilMaster. mfppca    =     	cursor_next.getString(cursor_next.getColumnIndex(  	DatabaseHandler. KEY_FPPCA )).trim() ;

		UtilMaster. mtax    =     	cursor_next.getString(cursor_next.getColumnIndex(  	DatabaseHandler. KEY_TAX )).trim() ;
		UtilMaster. mec    =     	cursor_next.getString(cursor_next.getColumnIndex( 	DatabaseHandler. KEY_EC )).trim() ;
		UtilMaster. mfc    =     	cursor_next.getString(cursor_next.getColumnIndex( 	DatabaseHandler. KEY_FC )).trim() ;
		UtilMaster. mtotal    =     	cursor_next.getString(cursor_next.getColumnIndex( 	DatabaseHandler. KEY_TOTAL )).trim() ;

		UtilMaster. mprebilldate    =     	cursor_next.getString(cursor_next.getColumnIndex( 	DatabaseHandler. KEY_PREBILLDATE )).trim() ;
		UtilMaster. mbilldate    =     	cursor_next.getString(cursor_next.getColumnIndex( 	DatabaseHandler.  KEY_BILLDATE )).trim() ;
		UtilMaster. mduedate    =     	cursor_next.getString(cursor_next.getColumnIndex( 	DatabaseHandler.  KEY_DUEDATE )).trim() ;

		UtilMaster. mcyclename    =     	cursor_next.getString(cursor_next.getColumnIndex( 	DatabaseHandler.KEY_CYCLENAME )).trim() ;
		UtilMaster. mconsumer_key    =     	cursor_next.getString(cursor_next.getColumnIndex( 	DatabaseHandler.KEY_CONSUMER_KEY )).trim() ;
		UtilMaster. minstallationo    =     	cursor_next.getString(cursor_next.getColumnIndex( 	DatabaseHandler.KEY_INSTALLATIONO )).trim() ;
		UtilMaster. mconsumerno    =     	cursor_next.getString(cursor_next.getColumnIndex(   	DatabaseHandler. KEY_CONSUMERNO)).trim() ;
		UtilMaster. mdivision    =     	cursor_next.getString(cursor_next.getColumnIndex(   	DatabaseHandler. KEY_DIVISION )).trim() ;
		UtilMaster. msubdivision    =     	cursor_next.getString(cursor_next.getColumnIndex(  	DatabaseHandler. KEY_SUBDIVISION )).trim() ;

		UtilMaster. mbookno    =     	cursor_next.getString(cursor_next.getColumnIndex( 	DatabaseHandler. KEY_BOOKNO )).trim() ;



		UtilMaster. mdevicefirmwareversion    =     	cursor_next.getString(cursor_next.getColumnIndex(   	DatabaseHandler. KEY_DEVICEFIRMWAREVERSION )).trim() ;
		//UtilMaster. mbilledatetimestamp    =     	cursor_next.getString(cursor_next.getColumnIndex( 	DatabaseHandler. KEY_BILLEDATETIMESTAMP )).trim() ;


		UtilMaster. mbilledatetimestamp    = UtilMaster.getMobileBillDatetimeStamp();

		UtilMaster. mbillno    =     	cursor_next.getString(cursor_next.getColumnIndex( 	DatabaseHandler. KEY_BILLNO )).trim() ;
		UtilMaster. mpresentmeterstatus    =     	cursor_next.getString(cursor_next.getColumnIndex( 	DatabaseHandler. KEY_PRESENTMETERSTATUS )).trim() ;
		UtilMaster. mbilledstatus    =     	cursor_next.getString(cursor_next.getColumnIndex( 	DatabaseHandler. KEY_BILLEDSTATUS)).trim() ;
		UtilMaster. mstatus    =     	cursor_next.getString(cursor_next.getColumnIndex( 	DatabaseHandler. KEY_STATUS )).trim() ;

		UtilMaster. mprevious_billeddate    =     	cursor_next.getString(cursor_next.getColumnIndex( 	DatabaseHandler. KEY_READING_DATE )).trim() ;
		UtilMaster. mactual_previous_billeddate    =     	cursor_next.getString(cursor_next.getColumnIndex( 	DatabaseHandler. ACTUAL_PREVIOUS_BILLEDDATE )) ;
		UtilMaster. mlineminimum    =     	cursor_next.getString(cursor_next.getColumnIndex( 	DatabaseHandler. KEY_LINEMINIMUM)) ;
		UtilMaster. mseasonal_consumer  =     	cursor_next.getString(cursor_next.getColumnIndex(  	DatabaseHandler. SEASONAL_CONSUMER 	 )) ;
		UtilMaster. mligpoints    =     	cursor_next.getString(cursor_next.getColumnIndex(  	DatabaseHandler. KEY_LIGPOINTS )) ;
		UtilMaster. mmetered    =     	cursor_next.getString(cursor_next.getColumnIndex(  	DatabaseHandler. KEY_METERED ));
		UtilMaster. mpf_reading    =     	cursor_next.getString(cursor_next.getColumnIndex(  	DatabaseHandler. KEY_PF_READING )) ;
		UtilMaster. mmaster_Pf_reading    =     	cursor_next.getString(cursor_next.getColumnIndex(  	DatabaseHandler. KEY_PF_READING )) ;
		UtilMaster. mpf_penality    =     	cursor_next.getString(cursor_next.getColumnIndex(  	DatabaseHandler. KEY_PF_PENALITY )) ;
		UtilMaster.mbmd_penality  =     	cursor_next.getString(cursor_next.getColumnIndex(  	DatabaseHandler. KEY_BMD_PENALITY )) ;
		UtilMaster.mbmd_reading =     	cursor_next.getString(cursor_next.getColumnIndex(  	DatabaseHandler. KEY_BMD_READING_PENALITY )) ;


		UtilMaster. mImagePath    =     	cursor_next.getString(cursor_next.getColumnIndex(	DatabaseHandler. KEY_IMAGEPATH )) ;

		UtilMaster. mextra1    =     	cursor_next.getString(cursor_next.getColumnIndex(  	DatabaseHandler. KEY_EXTRA1)) ;
		UtilMaster. mextra2    =     	cursor_next.getString(cursor_next.getColumnIndex(  	DatabaseHandler. KEY_EXTRA2 )) ;
		UtilMaster. mextra3    =     	cursor_next.getString(cursor_next.getColumnIndex(  	DatabaseHandler. KEY_EXTRA3)) ;
		UtilMaster. mextra4    =     	cursor_next.getString(cursor_next.getColumnIndex(  	DatabaseHandler. KEY_EXTRA4 )) ;
		UtilMaster. mextra5    =     	cursor_next.getString(cursor_next.getColumnIndex(  	DatabaseHandler. KEY_EXTRA5)) ;
		UtilMaster. mextra6    =     	cursor_next.getString(cursor_next.getColumnIndex(  	DatabaseHandler. KEY_EXTRA6 )) ;
		UtilMaster. mextra7    =     	cursor_next.getString(cursor_next.getColumnIndex(  	DatabaseHandler. KEY_EXTRA7 ));
		UtilMaster. mextra8    =     	cursor_next.getString(cursor_next.getColumnIndex(	DatabaseHandler. KEY_EXTRA8 )) ;

		UtilMaster.deviceInfo = mbatteryLevel+","+signalStrength11+","+mmemoryTotal+","+mmemoryAvailable;
		
		/* 
		 * 
		 * these changes to resolve value printing as null for receipt date and amt*/

		if(UtilMaster. mextra4.equals("null") || UtilMaster. mextra4 == null){

			UtilMaster. mextra4 = "-";
		}

		if(UtilMaster. mextra7.equals("null") || UtilMaster. mextra7 == null){

			UtilMaster. mextra7 = "-";
		}

		/*	
		 * Note shivanand
		 *  
		 *  extra 5 for mobile number
		 extra 3 for tax flag
		 extra 4 for receipt date 
		 extra 7 for receipt amout

		 *we used extra 6 for deviceInfo
		 *
		 */
		result = true;
		return result;
	}
	
	public static void resetParams(){
		UtilMaster. mmeter_constant   =     	"0";
		UtilMaster. mconsumer_name   =     	"0";
		UtilMaster. maddress   =     	"0";
		UtilMaster. maddress1   =     	"0";
		UtilMaster. mtariff =	"0";
		UtilMaster. mtariffdesc =	"0";
		UtilMaster. mledger_no   =     	"0";
		UtilMaster. mfolio_no   =     	"0";
		UtilMaster. mconnected_load   =     	"0";
		UtilMaster. md_and_r_fee   =     	"0";
		UtilMaster. marrears   =     	"0";
		UtilMaster. minterest   =     	"0";
		UtilMaster. mothers   =     	"0";
		UtilMaster. mbackbillarr   =     	"0";
		UtilMaster. maverage_consumption   =     	"0";
		UtilMaster. mdl_or_mnr_prev_month   =     	"0";
		UtilMaster.mpreviousreadingstatus = "0" ;
		UtilMaster. mprevious_reading  =     	"0";
		UtilMaster. mpresentreading  =     	"0";
		UtilMaster. mconsumption  =     	"0";
		UtilMaster. mpower_factor   =     	"0";
		UtilMaster. mreading_date   =     	"0";
		UtilMaster. mmeter_change_units_consumed   =     	"0";
		UtilMaster. mno_of_months_issued   =     	"0";
		UtilMaster. mcredit_or_rebate   =     	"0";
		UtilMaster. mfixed_ges   =     	"0";
		UtilMaster. maudit_arrears   =     	"0";
		UtilMaster. mold_interest   =     	"0";
		UtilMaster. mtrivector   =     	"0";
		UtilMaster. mdoorlockavg   =     	"0";
		UtilMaster. mconsumercode   =     	"0";
		UtilMaster. madditionaldep   =     	"0";
		UtilMaster. mmrcode   =     	"0";
		UtilMaster. mbillmonth   =     	"0";
		UtilMaster. msitecode=	"0";
		UtilMaster. msyncstatus   =     	"0";
		UtilMaster. mdataprepareddate =	"0";
		UtilMaster. mservertosbmdate   =     	"0";
		UtilMaster. mmeterno   =     	"0";
		UtilMaster. minterestondeposit   =     	"0";
		UtilMaster. mdl_adj   =     	"0";
		UtilMaster. mdl_count   =   "0";
		UtilMaster. mmeterrent   =     	"0";
		UtilMaster. mfppca    =     	"0";
		UtilMaster. mtax    =     	"0";
		UtilMaster. mec    =     	"0";
		UtilMaster. mfc    =     	"0";
		UtilMaster. mtotal    =     	"0";
		UtilMaster. mprebilldate    =     	"0";
		UtilMaster. mbilldate    =     	"0";
		UtilMaster. mduedate    =     	"0";
		UtilMaster. mextra1   =     	"0";
		UtilMaster. mextra2    =     	"0";
		UtilMaster. mcyclename    =     	"0";
		UtilMaster. mconsumer_key    =     	"0";
		UtilMaster. minstallationo    =     	"0";
		UtilMaster. mconsumerno    =     	"0";
		UtilMaster. mdivision    =     	"0";
		UtilMaster. msubdivision    =     	"0";
		UtilMaster. mbookno    =     	"0";
		UtilMaster. mdevicefirmwareversion    =     	"0";
		UtilMaster. mbilledatetimestamp    =     	"0";
		UtilMaster. mbillno    =     	"0";
		UtilMaster. mpresentmeterstatus    =     	"0";
		UtilMaster. mbilledstatus    =     	"0";
		UtilMaster. mstatus    =     	"0";
		UtilMaster. mprevious_billeddate    =     	"0";
		UtilMaster. mactual_previous_billeddate    =     	"0";
		UtilMaster. mlineminimum    =     	"0";
		UtilMaster. mseasonal_consumer  =     	"0";
		UtilMaster. mligpoints    =     	"0";
		UtilMaster. mmetered    =     	"0";
		UtilMaster. mpf_reading    =     	"0";
		UtilMaster. mmaster_Pf_reading =     	"0";
		UtilMaster. mpf_penality    =     	"0";
		UtilMaster. mextra3    =     	"0";
		UtilMaster. mextra4    =     	"0";
		UtilMaster. mextra5    =     	"0";
		UtilMaster. mextra6    =     	"0";
		UtilMaster. mextra7    =     	"0";
		UtilMaster. mextra8    =     	"0";
		UtilMaster. mImagePath    =    "";
		UtilMaster.mbmd_reading   =     	"0";
		UtilMaster.mbmd_penality  =     	"0";
	}
	
	public static android.content.DialogInterface.OnClickListener getDialogListener(
			final Context context, final Class<?> class1) {

		return new android.content.DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {

				move_inten(context, class1);
			}
		};

	}
	public static void move_inten(Context context, Class<?> class1) {
		Intent intent = new Intent(context, class1);
		context.startActivity(intent);
	}
}
