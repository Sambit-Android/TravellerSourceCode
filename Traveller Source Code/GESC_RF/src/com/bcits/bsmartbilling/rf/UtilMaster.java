package com.bcits.bsmartbilling.rf;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import com.utils.helper.DetailedReportHelper;
import com.utils.helper.MStatusReportHelper;

import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.widget.Toast;

public class UtilMaster extends Application {

	
	public static boolean stationerySelection  = true ;
	
	


	public static String  mGLogin_SiteCode = ""  ;
	public static String  mGLogin_MRCode = "" ;
	public static String  mconsumer_sc_no  ; 
	public static String  mmeter_constant  ;  
	public static String  mconsumer_name  ; 
	public static String  maddress  ;  
	public static String  maddress1  ;  
	public static String  mtariff	 ;  
	public static String  mtariffdesc	 ; 
	public static String  mledger_no  ;  
	public static String  mfolio_no  ; 
	public static String  mconnected_load  ;  
	public static String  md_and_r_fee  ;  
	public static String  marrears  ;  
	public static String  minterest  ; 
	public static String  mothers  ;  
	public static String  mbackbillarr  ; 
	public static String  maverage_consumption  ;  
	public static String  mdl_or_mnr_prev_month  ;  
	public static String  mpreviousreadingstatus ;
	public static String  mprevious_reading ;  
	public static String  mpresentreading ; 
	public static String  mconsumption ; 
	public static String  mpower_factor  ;  
	public static String  mreading_date  ; 
	public static String  mmeter_change_units_consumed  ;  
	public static String  mno_of_months_issued  ; 
	public static String  mcredit_or_rebate  ;  
	public static String  mfixed_ges  ; 
	public static String  maudit_arrears  ;  
	public static String  mold_interest  ; 
	public static String  mtrivector  ; 
	public static String  mckwhlkwh  ; 
	
	public static String  mdoorlockavg  ; 
	public static String  mconsumercode  ;  
	public static String  madditionaldep  ; 
	public static String  mmrcode  ;  
	public static String  mbillmonth  ;  
	public static String  msitecode	 ;  
	public static String  msyncstatus  ;  
	public static String  mdataprepareddate	 ;  
	public static String  mservertosbmdate  ;  
	public static String  mmeterno  ;  
	public static String  minterestondeposit  ; 
	public static String  mdl_adj  ; 
	public static String  mdl_count ; 
	public static String  mmeterrent  ;  
	public static String  mfppca   ;  
	public static String  mtax   ;  
	public static String  mec   ; 
	public static String  mfc   ; 
	public static String  mtotal   ; 
	public static String  mroundOff   ;
	public static String  mroundfftotal   ; 
	public static String  mprebilldate   ; 
	public static String  mbilldate   ; 
	public static String  mduedate   ; 
	public static String  mextra1  ;
	public static String  mextra2   ;  
	public static String  mcyclename   ; 
	public static String  mconsumer_key   ; 
	public static String  minstallationo   ; 
	public static String  mconsumerno   ;   
	public static String  mdivision   ;   
	public static String  msubdivision   ;  
	public static String  mbookno   ; 
	public static String  mdevicefirmwareversion   ;   
	public static String  mbilledatetimestamp   ; 
	public static String  mbillno   ; 	
	public static String  mpresentmeterstatus   ; 	
	public static String  mbilledstatus   ; 
	public static String  mstatus   ; 
	public static String  mprevious_billeddate   ; 
	public static String  mactual_previous_billeddate   ; 
	public static String  mlineminimum   ; 
	public static String  mseasonal_consumer ; 
	public static String  mligpoints   ;  
	public static String  mmetered   ;  
	public static String  mpf_reading   ;  
	public static String  mmaster_Pf_reading   ; 
	public static String  mpf_penality   ; 
	
	public static String  mbmd_reading   ;  
	public static String  mbmd_penality   ; 
	
	
	/*IMAGE*/
	public static String mImagePath;
	public static byte[]  mImage  ;
	
	public static String mlangitude ;
	public static String mlattitude ;
	public static String mtakeTime ;
	public static String mActualBmForDL   ;
	
	
	
	public static String  mextra3   ;  
	public static String  mextra4   ;  
	public static String  mextra5   ;  
	public static String  mextra6   ;  
	public static String  mextra7   ;  
	public static String  mextra8   ;
	
	public static List<MStatusReportHelper> mGlobalList ;
	public static List<DetailedReportHelper> detailedReportHelpers ;
	public static String reportType ;
	public static String phoneNumber;
	public static String deviceInfo;
	
	/* Billing parameters  */
	public static int m_unitsConsumed;
	public static int m_previousReading ;
	public static int m_presentReading ;
	public static float m_ec, m_fc, m_fppca, m_tax,
						m_meterRent, m_arrears,	m_others, m_sundry, m_lps,
						m_total, m_due_total, m_bd, m_due_bd;
	
	
	
	
	/** TARIFF SLABS AND RATES */
	
	/*FC */
	public static String Lfcslab1;
	public static String Lfcrate1;
	public static String Lfcslab2;
	public static String Lfcrate2;
	public static String Lfcslab3;
	public static String Lfcrate3;
	public static String Lfcslab4;
	public static String Lfcrate4;
	/*EC*/
	public static String Lecslab1;
	public static String Lecrate1;
	public static String Lecslab2;
	public static String Lecrate2;
	public static String Lecslab3;
	public static String Lecrate3;
	public static String Lecslab4;
	public static String Lecrate4;
	public static String Lecslab5;
	public static String Lecrate5;
	public static String Lecslab6;
	public static String Lecrate6;
	/*FPPCA*/
	public static String Lfppcaslab1;
	public static String Lfppcarate1;
	public static String Lfppcaslab2;
	public static String Lfppcarate2;
	public static String Lfppcaslab3;
	public static String Lfppcarate3;
	public static String Lfppcaslab4;
	public static String Lfppcarate4;
	public static String Lfppcaslab5;
	public static String Lfppcarate5;
	public static String Lfppcaslab6;
	public static String Lfppcarate6;
	
	
	public static String LFcReduction;
	public static String LEcReduction;
	public static String LFcMax;
	public static String LEcMax;
	
	
	
	
	public static String Ltax_rate;

	
	
	public static String dutyAftdue ;
	   public static String chrgAftdue ;
	   public static String nettotalAftdue ;

	   public static String dutyBfrdue ;
	   public static String chrgBfrdue ;
	   public static String nettotalBfrdue ; 
	
	   public static boolean serverDatesync_key  ;
	   public static boolean updateEnable   ;
	   public static boolean photoEnable ;
	   public static boolean gpsEnable ;
	   
	   public static boolean isBJKJLessThan18Units = false;
	   public static float netamountForBJKJ = 0f;
	   
	   
	   
	   
	
	public static boolean isPhotoEnable() {
		return photoEnable;
	}

	public static void setPhotoEnable(boolean photoEnable) {
		UtilMaster.photoEnable = photoEnable;
	}

	public static boolean isGpsEnable() {
		return gpsEnable;
	}

	public static void setGpsEnable(boolean gpsEnable) {
		UtilMaster.gpsEnable = gpsEnable;
	}

	
	


	
	
	
	
	public static boolean isStationerySelection() {
		return stationerySelection;
	}

	public static void setStationerySelection(boolean stationerySelection) {
		UtilMaster.stationerySelection = stationerySelection;
	}

	public static boolean isUpdateEnable() {
		return updateEnable;
	}

	public static void setUpdateEnable(boolean updateEnable) {
		UtilMaster.updateEnable = updateEnable;
	}

	public static String getMbmd_reading() {
		return mbmd_reading;
	}

	public static void setMbmd_reading(String mbmd_reading) {
		UtilMaster.mbmd_reading = mbmd_reading;
	}

	public static String getMbmd_penality() {
		return mbmd_penality;
	}

	public static void setMbmd_penality(String mbmd_penality) {
		UtilMaster.mbmd_penality = mbmd_penality;
	}

	public static boolean isServerDatesync_key() {
		return serverDatesync_key;
	}

	public static void setServerDatesync_key(boolean serverDatesync_key) {
		UtilMaster.serverDatesync_key = serverDatesync_key;
	}

	public static String getMroundOff() {
		return mroundOff;
	}

	public static void setMroundOff(String mroundOff) {
		UtilMaster.mroundOff = mroundOff;
	}

	public static String getMroundfftotal() {
		return mroundfftotal;
	}

	public static void setMroundfftotal(String mroundfftotal) {
		UtilMaster.mroundfftotal = mroundfftotal;
	}

	public static String getmGLogin_SiteCode() {
		return mGLogin_SiteCode;
	}

	public static void setmGLogin_SiteCode(String mGLogin_SiteCode) {
		UtilMaster.mGLogin_SiteCode = mGLogin_SiteCode;
	}

	public static String getmGLogin_MRCode() {
		return mGLogin_MRCode;
	}

	public static void setmGLogin_MRCode(String mGLogin_MRCode) {
		UtilMaster.mGLogin_MRCode = mGLogin_MRCode;
	}

	public static String getMconsumer_sc_no() {
		return mconsumer_sc_no;
	}

	public static void setMconsumer_sc_no(String mconsumer_sc_no) {
		UtilMaster.mconsumer_sc_no = mconsumer_sc_no;
	}

	public static String getMmeter_constant() {
		return mmeter_constant;
	}

	public static void setMmeter_constant(String mmeter_constant) {
		UtilMaster.mmeter_constant = mmeter_constant;
	}

	public static String getMconsumer_name() {
		return mconsumer_name;
	}

	public static void setMconsumer_name(String mconsumer_name) {
		UtilMaster.mconsumer_name = mconsumer_name;
	}

	public static String getMaddress() {
		return maddress;
	}

	public static void setMaddress(String maddress) {
		UtilMaster.maddress = maddress;
	}

	public static String getMaddress1() {
		return maddress1;
	}

	public static void setMaddress1(String maddress1) {
		UtilMaster.maddress1 = maddress1;
	}

	public static String getMtariff() {
		return mtariff;
	}

	public static void setMtariff(String mtariff) {
		UtilMaster.mtariff = mtariff;
	}

	public static String getMtariffdesc() {
		return mtariffdesc;
	}

	public static void setMtariffdesc(String mtariffdesc) {
		UtilMaster.mtariffdesc = mtariffdesc;
	}

	public static String getMledger_no() {
		return mledger_no;
	}

	public static void setMledger_no(String mledger_no) {
		UtilMaster.mledger_no = mledger_no;
	}

	public static String getMfolio_no() {
		return mfolio_no;
	}

	public static void setMfolio_no(String mfolio_no) {
		UtilMaster.mfolio_no = mfolio_no;
	}

	public static String getMconnected_load() {
		return mconnected_load;
	}

	public static void setMconnected_load(String mconnected_load) {
		UtilMaster.mconnected_load = mconnected_load;
	}

	public static String getMd_and_r_fee() {
		return md_and_r_fee;
	}

	public static void setMd_and_r_fee(String md_and_r_fee) {
		UtilMaster.md_and_r_fee = md_and_r_fee;
	}

	public static String getMarrears() {
		return marrears;
	}

	public static void setMarrears(String marrears) {
		UtilMaster.marrears = marrears;
	}

	public static String getMinterest() {
		return minterest;
	}

	public static void setMinterest(String minterest) {
		UtilMaster.minterest = minterest;
	}

	public static String getMothers() {
		return mothers;
	}

	public static void setMothers(String mothers) {
		UtilMaster.mothers = mothers;
	}

	public static String getMbackbillarr() {
		return mbackbillarr;
	}

	public static void setMbackbillarr(String mbackbillarr) {
		UtilMaster.mbackbillarr = mbackbillarr;
	}

	public static String getMaverage_consumption() {
		return maverage_consumption;
	}

	public static void setMaverage_consumption(String maverage_consumption) {
		UtilMaster.maverage_consumption = maverage_consumption;
	}

	public static String getMdl_or_mnr_prev_month() {
		return mdl_or_mnr_prev_month;
	}

	public static void setMdl_or_mnr_prev_month(String mdl_or_mnr_prev_month) {
		UtilMaster.mdl_or_mnr_prev_month = mdl_or_mnr_prev_month;
	}

	
	
	public static String getMpreviousreadingstatus() {
		return mpreviousreadingstatus;
	}

	public static void setMpreviousreadingstatus(String mpreviousreadingstatus) {
		UtilMaster.mpreviousreadingstatus = mpreviousreadingstatus;
	}

	public static String getMprevious_reading() {
		return mprevious_reading;
	}

	public static void setMprevious_reading(String mprevious_reading) {
		UtilMaster.mprevious_reading = mprevious_reading;
	}

	public static String getMpresentreading() {
		return mpresentreading;
	}

	public static void setMpresentreading(String mpresentreading) {
		UtilMaster.mpresentreading = mpresentreading;
	}

	public static String getMconsumption() {
		return mconsumption;
	}

	public static void setMconsumption(String mconsumption) {
		UtilMaster.mconsumption = mconsumption;
	}

	public static String getMpower_factor() {
		return mpower_factor;
	}

	public static void setMpower_factor(String mpower_factor) {
		UtilMaster.mpower_factor = mpower_factor;
	}

	public static String getMreading_date() {
		return mreading_date;
	}

	public static void setMreading_date(String mreading_date) {
		UtilMaster.mreading_date = mreading_date;
	}

	public static String getMmeter_change_units_consumed() {
		return mmeter_change_units_consumed;
	}

	public static void setMmeter_change_units_consumed(
			String mmeter_change_units_consumed) {
		UtilMaster.mmeter_change_units_consumed = mmeter_change_units_consumed;
	}

	public static String getMno_of_months_issued() {
		return mno_of_months_issued;
	}

	public static void setMno_of_months_issued(String mno_of_months_issued) {
		UtilMaster.mno_of_months_issued = mno_of_months_issued;
	}

	public static String getMcredit_or_rebate() {
		return mcredit_or_rebate;
	}

	public static void setMcredit_or_rebate(String mcredit_or_rebate) {
		UtilMaster.mcredit_or_rebate = mcredit_or_rebate;
	}

	public static String getMfixed_ges() {
		return mfixed_ges;
	}

	public static void setMfixed_ges(String mfixed_ges) {
		UtilMaster.mfixed_ges = mfixed_ges;
	}

	public static String getMaudit_arrears() {
		return maudit_arrears;
	}

	public static void setMaudit_arrears(String maudit_arrears) {
		UtilMaster.maudit_arrears = maudit_arrears;
	}

	public static String getMold_interest() {
		return mold_interest;
	}

	public static void setMold_interest(String mold_interest) {
		UtilMaster.mold_interest = mold_interest;
	}

	public static String getMtrivector() {
		return mtrivector;
	}

	public static void setMtrivector(String mtrivector) {
		UtilMaster.mtrivector = mtrivector;
	}

	
	
	public static String getMckwhlkwh() {
		return mckwhlkwh;
	}

	public static void setMckwhlkwh(String mckwhlkwh) {
		UtilMaster.mckwhlkwh = mckwhlkwh;
	}

	public static String getMdoorlockavg() {
		return mdoorlockavg;
	}

	public static void setMdoorlockavg(String mdoorlockavg) {
		UtilMaster.mdoorlockavg = mdoorlockavg;
	}

	public static String getMconsumercode() {
		return mconsumercode;
	}

	public static void setMconsumercode(String mconsumercode) {
		UtilMaster.mconsumercode = mconsumercode;
	}

	public static String getMadditionaldep() {
		return madditionaldep;
	}

	public static void setMadditionaldep(String madditionaldep) {
		UtilMaster.madditionaldep = madditionaldep;
	}

	public static String getMmrcode() {
		return mmrcode;
	}

	public static void setMmrcode(String mmrcode) {
		UtilMaster.mmrcode = mmrcode;
	}

	public static String getMbillmonth() {
		return mbillmonth;
	}

	public static void setMbillmonth(String mbillmonth) {
		UtilMaster.mbillmonth = mbillmonth;
	}

	public static String getMsitecode() {
		return msitecode;
	}

	public static void setMsitecode(String msitecode) {
		UtilMaster.msitecode = msitecode;
	}

	public static String getMsyncstatus() {
		return msyncstatus;
	}

	public static void setMsyncstatus(String msyncstatus) {
		UtilMaster.msyncstatus = msyncstatus;
	}

	public static String getMdataprepareddate() {
		return mdataprepareddate;
	}

	public static void setMdataprepareddate(String mdataprepareddate) {
		UtilMaster.mdataprepareddate = mdataprepareddate;
	}

	public static String getMservertosbmdate() {
		return mservertosbmdate;
	}

	public static void setMservertosbmdate(String mservertosbmdate) {
		UtilMaster.mservertosbmdate = mservertosbmdate;
	}

	public static String getMmeterno() {
		return mmeterno;
	}

	public static void setMmeterno(String mmeterno) {
		UtilMaster.mmeterno = mmeterno;
	}

	public static String getMinterestondeposit() {
		return minterestondeposit;
	}

	public static void setMinterestondeposit(String minterestondeposit) {
		UtilMaster.minterestondeposit = minterestondeposit;
	}

	public static String getMdl_adj() {
		return mdl_adj;
	}

	public static void setMdl_adj(String mdl_adj) {
		UtilMaster.mdl_adj = mdl_adj;
	}

	
	public static String getMdl_count() {
		return mdl_count;
	}

	public static void setMdl_count(String mdl_count) {
		UtilMaster.mdl_count = mdl_count;
	}
	
	public static String getMmeterrent() {
		return mmeterrent;
	}

	

	public static void setMmeterrent(String mmeterrent) {
		UtilMaster.mmeterrent = mmeterrent;
	}

	public static String getMfppca() {
		return mfppca;
	}

	public static void setMfppca(String mfppca) {
		UtilMaster.mfppca = mfppca;
	}

	public static String getMtax() {
		return mtax;
	}

	public static void setMtax(String mtax) {
		UtilMaster.mtax = mtax;
	}

	public static String getMec() {
		return mec;
	}

	public static void setMec(String mec) {
		UtilMaster.mec = mec;
	}

	public static String getMfc() {
		return mfc;
	}

	public static void setMfc(String mfc) {
		UtilMaster.mfc = mfc;
	}

	public static String getMtotal() {
		return mtotal;
	}

	public static void setMtotal(String mtotal) {
		UtilMaster.mtotal = mtotal;
	}

	public static String getMprebilldate() {
		return mprebilldate;
	}

	public static void setMprebilldate(String mprebilldate) {
		UtilMaster.mprebilldate = mprebilldate;
	}

	public static String getMbilldate() {
		return mbilldate;
	}

	public static void setMbilldate(String mbilldate) {
		UtilMaster.mbilldate = mbilldate;
	}

	public static String getMduedate() {
		return mduedate;
	}

	public static void setMduedate(String mduedate) {
		UtilMaster.mduedate = mduedate;
	}

	public static String getMextra1() {
		return mextra1;
	}

	public static void setMextra1(String mextra1) {
		UtilMaster.mextra1 = mextra1;
	}

	public static String getMextra2() {
		return mextra2;
	}

	public static void setMextra2(String mextra2) {
		UtilMaster.mextra2 = mextra2;
	}

	public static String getMcyclename() {
		return mcyclename;
	}

	public static void setMcyclename(String mcyclename) {
		UtilMaster.mcyclename = mcyclename;
	}

	public static String getMconsumer_key() {
		return mconsumer_key;
	}

	public static void setMconsumer_key(String mconsumer_key) {
		UtilMaster.mconsumer_key = mconsumer_key;
	}

	public static String getMinstallationo() {
		return minstallationo;
	}

	public static void setMinstallationo(String minstallationo) {
		UtilMaster.minstallationo = minstallationo;
	}

	public static String getMconsumerno() {
		return mconsumerno;
	}

	public static void setMconsumerno(String mconsumerno) {
		UtilMaster.mconsumerno = mconsumerno;
	}

	public static String getMdivision() {
		return mdivision;
	}

	public static void setMdivision(String mdivision) {
		UtilMaster.mdivision = mdivision;
	}

	public static String getMsubdivision() {
		return msubdivision;
	}

	public static void setMsubdivision(String msubdivision) {
		UtilMaster.msubdivision = msubdivision;
	}

	public static String getMbookno() {
		return mbookno;
	}

	public static void setMbookno(String mbookno) {
		UtilMaster.mbookno = mbookno;
	}

	public static String getMdevicefirmwareversion() {
		return mdevicefirmwareversion;
	}

	public static void setMdevicefirmwareversion(String mdevicefirmwareversion) {
		UtilMaster.mdevicefirmwareversion = mdevicefirmwareversion;
	}

	public static String getMbilledatetimestamp() {
		return mbilledatetimestamp;
	}

	public static void setMbilledatetimestamp(String mbilledatetimestamp) {
		UtilMaster.mbilledatetimestamp = mbilledatetimestamp;
	}

	public static String getMbillno() {
		return mbillno;
	}

	public static void setMbillno(String mbillno) {
		UtilMaster.mbillno = mbillno;
	}

	public static String getMpresentmeterstatus() {
		return mpresentmeterstatus;
	}

	public static void setMpresentmeterstatus(String mpresentmeterstatus) {
		UtilMaster.mpresentmeterstatus = mpresentmeterstatus;
	}

	public static String getMbilledstatus() {
		return mbilledstatus;
	}

	public static void setMbilledstatus(String mbilledstatus) {
		UtilMaster.mbilledstatus = mbilledstatus;
	}

	public static String getMstatus() {
		return mstatus;
	}

	public static void setMstatus(String mstatus) {
		UtilMaster.mstatus = mstatus;
	}

	public static String getMprevious_billeddate() {
		return mprevious_billeddate;
	}

	public static void setMprevious_billeddate(String mprevious_billeddate) {
		UtilMaster.mprevious_billeddate = mprevious_billeddate;
	}

	public static String getMactual_previous_billeddate() {
		return mactual_previous_billeddate;
	}

	public static void setMactual_previous_billeddate(
			String mactual_previous_billeddate) {
		UtilMaster.mactual_previous_billeddate = mactual_previous_billeddate;
	}

	public static String getMlineminimum() {
		return mlineminimum;
	}

	public static void setMlineminimum(String mlineminimum) {
		UtilMaster.mlineminimum = mlineminimum;
	}

	public static String getMseasonal_consumer() {
		return mseasonal_consumer;
	}

	public static void setMseasonal_consumer(String mseasonal_consumer) {
		UtilMaster.mseasonal_consumer = mseasonal_consumer;
	}

	public static String getMligpoints() {
		return mligpoints;
	}

	public static void setMligpoints(String mligpoints) {
		UtilMaster.mligpoints = mligpoints;
	}

	public static String getMmetered() {
		return mmetered;
	}

	public static void setMmetered(String mmetered) {
		UtilMaster.mmetered = mmetered;
	}

	
	
	
	
	public static String getMmaster_Pf_reading() {
		return mmaster_Pf_reading;
	}

	public static void setMmaster_Pf_reading(String mmaster_Pf_reading) {
		UtilMaster.mmaster_Pf_reading = mmaster_Pf_reading;
	}

	public static String getMpf_reading() {
		return mpf_reading;
	}

	public static void setMpf_reading(String mpf_reading) {
		UtilMaster.mpf_reading = mpf_reading;
	}

	public static String getMpf_penality() {
		return mpf_penality;
	}

	public static void setMpf_penality(String mpf_penality) {
		UtilMaster.mpf_penality = mpf_penality;
	}

	public static String getmImagePath() {
		return mImagePath;
	}

	public static void setmImagePath(String mImagePath) {
		UtilMaster.mImagePath = mImagePath;
	}

	public static byte[] getmImage() {
		return mImage;
	}

	public static void setmImage(byte[] mImage) {
		UtilMaster.mImage = mImage;
	}

	
	
	
	
	


	public static String getMlangitude() {
		return mlangitude;
	}

	public static void setMlangitude(String mlangitude) {
		UtilMaster.mlangitude = mlangitude;
	}

	public static String getMlattitude() {
		return mlattitude;
	}

	public static void setMlattitude(String mlattitude) {
		UtilMaster.mlattitude = mlattitude;
	}

	public static String getMtakeTime() {
		return mtakeTime;
	}

	public static void setMtakeTime(String mtakeTime) {
		UtilMaster.mtakeTime = mtakeTime;
	}

	public static String getMextra3() {
		return mextra3;
	}

	public static void setMextra3(String mextra3) {
		UtilMaster.mextra3 = mextra3;
	}

	public static String getMextra4() {
		return mextra4;
	}

	public static void setMextra4(String mextra4) {
		UtilMaster.mextra4 = mextra4;
	}

	public static String getMextra5() {
		return mextra5;
	}

	public static void setMextra5(String mextra5) {
		UtilMaster.mextra5 = mextra5;
	}

	public static String getMextra6() {
		return mextra6;
	}

	public static void setMextra6(String mextra6) {
		UtilMaster.mextra6 = mextra6;
	}

	public static String getMextra7() {
		return mextra7;
	}

	public static void setMextra7(String mextra7) {
		UtilMaster.mextra7 = mextra7;
	}

	public static String getMextra8() {
		return mextra8;
	}

	public static void setMextra8(String mextra8) {
		UtilMaster.mextra8 = mextra8;
	}

	public static int getM_unitsConsumed() {
		return m_unitsConsumed;
	}

	public static void setM_unitsConsumed(int m_unitsConsumed) {
		UtilMaster.m_unitsConsumed = m_unitsConsumed;
	}

	public static int getM_previousReading() {
		return m_previousReading;
	}

	public static void setM_previousReading(int m_previousReading) {
		UtilMaster.m_previousReading = m_previousReading;
	}

	public static int getM_presentReading() {
		return m_presentReading;
	}

	public static void setM_presentReading(int m_presentReading) {
		UtilMaster.m_presentReading = m_presentReading;
	}

	public static float getM_ec() {
		return m_ec;
	}

	public static void setM_ec(float m_ec) {
		UtilMaster.m_ec = m_ec;
	}

	public static float getM_fc() {
		return m_fc;
	}

	public static void setM_fc(float m_fc) {
		UtilMaster.m_fc = m_fc;
	}

	public static float getM_fppca() {
		return m_fppca;
	}

	public static void setM_fppca(float m_fppca) {
		UtilMaster.m_fppca = m_fppca;
	}

	public static float getM_tax() {
		return m_tax;
	}

	public static void setM_tax(float m_tax) {
		UtilMaster.m_tax = m_tax;
	}

	public static float getM_meterRent() {
		return m_meterRent;
	}

	public static void setM_meterRent(float m_meterRent) {
		UtilMaster.m_meterRent = m_meterRent;
	}

	public static float getM_arrears() {
		return m_arrears;
	}

	public static void setM_arrears(float m_arrears) {
		UtilMaster.m_arrears = m_arrears;
	}

	public static float getM_others() {
		return m_others;
	}

	public static void setM_others(float m_others) {
		UtilMaster.m_others = m_others;
	}

	public static float getM_sundry() {
		return m_sundry;
	}

	public static void setM_sundry(float m_sundry) {
		UtilMaster.m_sundry = m_sundry;
	}

	public static float getM_lps() {
		return m_lps;
	}

	public static void setM_lps(float m_lps) {
		UtilMaster.m_lps = m_lps;
	}

	public static float getM_total() {
		return m_total;
	}

	public static void setM_total(float m_total) {
		UtilMaster.m_total = m_total;
	}

	public static float getM_due_total() {
		return m_due_total;
	}

	public static void setM_due_total(float m_due_total) {
		UtilMaster.m_due_total = m_due_total;
	}

	public static float getM_bd() {
		return m_bd;
	}

	public static void setM_bd(float m_bd) {
		UtilMaster.m_bd = m_bd;
	}

	public static float getM_due_bd() {
		return m_due_bd;
	}

	public static void setM_due_bd(float m_due_bd) {
		UtilMaster.m_due_bd = m_due_bd;
	}

	public static String getLfcslab1() {
		return Lfcslab1;
	}

	public static void setLfcslab1(String lfcslab1) {
		Lfcslab1 = lfcslab1;
	}

	public static String getLfcrate1() {
		return Lfcrate1;
	}

	public static void setLfcrate1(String lfcrate1) {
		Lfcrate1 = lfcrate1;
	}

	public static String getLfcslab2() {
		return Lfcslab2;
	}

	public static void setLfcslab2(String lfcslab2) {
		Lfcslab2 = lfcslab2;
	}

	public static String getLfcrate2() {
		return Lfcrate2;
	}

	public static void setLfcrate2(String lfcrate2) {
		Lfcrate2 = lfcrate2;
	}

	public static String getLfcslab3() {
		return Lfcslab3;
	}

	public static void setLfcslab3(String lfcslab3) {
		Lfcslab3 = lfcslab3;
	}

	public static String getLfcrate3() {
		return Lfcrate3;
	}

	public static void setLfcrate3(String lfcrate3) {
		Lfcrate3 = lfcrate3;
	}

	public static String getLfcslab4() {
		return Lfcslab4;
	}

	public static void setLfcslab4(String lfcslab4) {
		Lfcslab4 = lfcslab4;
	}

	public static String getLfcrate4() {
		return Lfcrate4;
	}

	public static void setLfcrate4(String lfcrate4) {
		Lfcrate4 = lfcrate4;
	}

	public static String getLecslab1() {
		return Lecslab1;
	}

	public static void setLecslab1(String lecslab1) {
		Lecslab1 = lecslab1;
	}

	public static String getLecrate1() {
		return Lecrate1;
	}

	public static void setLecrate1(String lecrate1) {
		Lecrate1 = lecrate1;
	}

	public static String getLecslab2() {
		return Lecslab2;
	}

	public static void setLecslab2(String lecslab2) {
		Lecslab2 = lecslab2;
	}

	public static String getLecrate2() {
		return Lecrate2;
	}

	public static void setLecrate2(String lecrate2) {
		Lecrate2 = lecrate2;
	}

	public static String getLecslab3() {
		return Lecslab3;
	}

	public static void setLecslab3(String lecslab3) {
		Lecslab3 = lecslab3;
	}

	public static String getLecrate3() {
		return Lecrate3;
	}

	public static void setLecrate3(String lecrate3) {
		Lecrate3 = lecrate3;
	}

	public static String getLecslab4() {
		return Lecslab4;
	}

	public static void setLecslab4(String lecslab4) {
		Lecslab4 = lecslab4;
	}

	public static String getLecrate4() {
		return Lecrate4;
	}

	public static void setLecrate4(String lecrate4) {
		Lecrate4 = lecrate4;
	}

	public static String getLecslab5() {
		return Lecslab5;
	}

	public static void setLecslab5(String lecslab5) {
		Lecslab5 = lecslab5;
	}

	public static String getLecrate5() {
		return Lecrate5;
	}

	public static void setLecrate5(String lecrate5) {
		Lecrate5 = lecrate5;
	}

	public static String getLecslab6() {
		return Lecslab6;
	}

	public static void setLecslab6(String lecslab6) {
		Lecslab6 = lecslab6;
	}

	public static String getLecrate6() {
		return Lecrate6;
	}

	public static void setLecrate6(String lecrate6) {
		Lecrate6 = lecrate6;
	}

	public static String getLfppcaslab1() {
		return Lfppcaslab1;
	}

	public static void setLfppcaslab1(String lfppcaslab1) {
		Lfppcaslab1 = lfppcaslab1;
	}

	public static String getLfppcarate1() {
		return Lfppcarate1;
	}

	public static void setLfppcarate1(String lfppcarate1) {
		Lfppcarate1 = lfppcarate1;
	}

	public static String getLfppcaslab2() {
		return Lfppcaslab2;
	}

	public static void setLfppcaslab2(String lfppcaslab2) {
		Lfppcaslab2 = lfppcaslab2;
	}

	public static String getLfppcarate2() {
		return Lfppcarate2;
	}

	public static void setLfppcarate2(String lfppcarate2) {
		Lfppcarate2 = lfppcarate2;
	}

	public static String getLfppcaslab3() {
		return Lfppcaslab3;
	}

	public static void setLfppcaslab3(String lfppcaslab3) {
		Lfppcaslab3 = lfppcaslab3;
	}

	public static String getLfppcarate3() {
		return Lfppcarate3;
	}

	public static void setLfppcarate3(String lfppcarate3) {
		Lfppcarate3 = lfppcarate3;
	}

	public static String getLfppcaslab4() {
		return Lfppcaslab4;
	}

	public static void setLfppcaslab4(String lfppcaslab4) {
		Lfppcaslab4 = lfppcaslab4;
	}

	public static String getLfppcarate4() {
		return Lfppcarate4;
	}

	public static void setLfppcarate4(String lfppcarate4) {
		Lfppcarate4 = lfppcarate4;
	}

	public static String getLfppcaslab5() {
		return Lfppcaslab5;
	}

	public static void setLfppcaslab5(String lfppcaslab5) {
		Lfppcaslab5 = lfppcaslab5;
	}

	public static String getLfppcarate5() {
		return Lfppcarate5;
	}

	public static void setLfppcarate5(String lfppcarate5) {
		Lfppcarate5 = lfppcarate5;
	}

	public static String getLfppcaslab6() {
		return Lfppcaslab6;
	}

	public static void setLfppcaslab6(String lfppcaslab6) {
		Lfppcaslab6 = lfppcaslab6;
	}

	public static String getLfppcarate6() {
		return Lfppcarate6;
	}

	public static void setLfppcarate6(String lfppcarate6) {
		Lfppcarate6 = lfppcarate6;
	}

	
	
	
	public static String getLFcReduction() {
		return LFcReduction;
	}

	public static void setLFcReduction(String lFcReduction) {
		LFcReduction = lFcReduction;
	}

	public static String getLEcReduction() {
		return LEcReduction;
	}

	public static void setLEcReduction(String lEcReduction) {
		LEcReduction = lEcReduction;
	}

	public static String getLFcMax() {
		return LFcMax;
	}

	public static void setLFcMax(String lFcMax) {
		LFcMax = lFcMax;
	}

	public static String getLEcMax() {
		return LEcMax;
	}

	public static void setLEcMax(String lEcMax) {
		LEcMax = lEcMax;
	}

	public static String getLtax_rate() {
		return Ltax_rate;
	}

	public static void setLtax_rate(String ltax_rate) {
		Ltax_rate = ltax_rate;
	}
	
	public static void resetBillParams(){
		
		m_unitsConsumed = 0;
		m_presentReading = 0;
		m_ec = 0;
		m_fc = 0;
		m_fppca = 0;
		m_tax = 0;
		m_total = 0;
		m_due_total = 0;
		m_bd = 0;
		m_due_bd = 0;
		
		Lfcslab1 = "0" ;
		Lfcrate1 = "0" ;
		Lfcslab2 = "0" ;
		Lfcrate2 = "0" ;
		Lfcslab3 = "0" ;
		Lfcrate3 = "0" ;
		Lfcslab4 = "0" ;
		Lfcrate4 = "0" ;
		/*EC*/
		Lecslab1 = "0" ;
		Lecrate1 = "0" ;
		Lecslab2 = "0" ;
		Lecrate2 = "0" ;
		Lecslab3 = "0" ;
		Lecrate3 = "0" ;
		Lecslab4 = "0" ;
		Lecrate4 = "0" ;
		Lecslab5 = "0" ;
		Lecrate5 = "0" ;
		Lecslab6 = "0" ;
		Lecrate6 = "0" ;
		/*FPPCA*/
		Lfppcaslab1 = "0" ;
		Lfppcarate1 = "0" ;
		Lfppcaslab2 = "0" ;
		Lfppcarate2 = "0" ;
		Lfppcaslab3 = "0" ;
		Lfppcarate3 = "0" ;
		Lfppcaslab4 = "0" ;
		Lfppcarate4 = "0" ;
		Lfppcaslab5 = "0" ;
		Lfppcarate5 = "0" ;
		Lfppcaslab6 = "0" ;
		Lfppcarate6 = "0" ;
		
		
		
		 LFcReduction = "0" ;
		 LEcReduction = "0" ;
		 LFcMax = "0" ;
		 LEcMax = "0" ;
		
		Ltax_rate = "0" ;
	}
	public static Toast showToast(Context context , int msg){
		
		   return	Toast.makeText(context,msg,Toast.LENGTH_LONG);
			
		}
		public static Toast showToast(Context context , String msg){
			
			   return	Toast.makeText(context,msg,Toast.LENGTH_LONG);
				
			}
		
		/**
		 * @category Dialog box with +ve & -ve Key
		 * @param context
		 * @param title
		 * @param icon
		 * @param msg
		 * @param pvtstrg
		 * @param pvt_listener
		 * @param ngtstrg
		 * @param ngt_listener
		 * @return AlertDialog.Builder
		 */
		public static AlertDialog.Builder show(Context context , String title,int icon,String msg, String pvtstrg , OnClickListener pvt_listener , String ngtstrg , OnClickListener ngt_listener ) {
			  return	new AlertDialog.Builder(context).setTitle(title).setIcon(icon).setMessage(msg).setPositiveButton(pvtstrg, pvt_listener).setNegativeButton(ngtstrg, ngt_listener);
				
				
			}
		
		/**
		 * @category Dialog box with one key
		 * @param context
		 * @param title
		 * @param icon
		 * @param msg
		 * @param pvtstrg
		 * @param pvt_listener
		 * @return AlertDialog.Builder
		 */
		public static AlertDialog.Builder show(Context context , String title,int icon,String msg, String pvtstrg , OnClickListener pvt_listener  ) {
			  return	new AlertDialog.Builder(context).setTitle(title).setIcon(icon).setMessage(msg).setPositiveButton(pvtstrg, pvt_listener);
				
				
			}
		
		public static String addPostSpace(String string, int length) {

			int n = 0;
			if (string == null) {
				string = "-";
				n = string.length();
			} else {
				n = string.length();

			}

			int space = length - n;
			//StringBuilder s = new StringBuilder(" ");
			StringBuilder k = new StringBuilder(" ");
			
			for (int i = 0; i < space; i++) {
				k = k.append(" ");
			}

			String f = k.toString();
			return  string+f;
		}
		
		
		
		public static String getMobileBillDatetimeStamp(){
			
			String timeStamp = "";
			
			try {
				
				Calendar calendar =   Calendar.getInstance();
				timeStamp = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(calendar.getTime());
				
			} catch (Exception e) {
				// TODO: handle exception
				
				timeStamp = UtilMaster.mbilldate+" 00:00:00";
				
			}
			
			
			
			return timeStamp;
		}
		
		
		
}
