package com.bcits.disconreconn;

import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.widget.Toast;

public class DCUtilMaster extends Application {
	
	
	
	    public static String  mcustomer_number  ;   
		public static String  mcusomer_name  ;    
		public static String  maddress1  ;    
		public static String  maddress2  ;   
		public static String  maddress3  ;   
		public static String  mchalan_no  ;    
		public static String  mfolio_no  ;   
		public static String  mmeterno  ;    
		public static String  mtariff  ;   
		public static String  mmultiplicationfactor  ;    
		public static String  mpreviousreading  ;    
		public static String  mpreviousreadingstatus  ;    
		public static String  mprevisousreadingdate  ;    
		public static String  marrearsnew  ;    
		public static String  madditionalcharges_arrears  ;   
		public static String  minterestnew  ;   
		public static String  mothers  ;   
		public static String  mtax  ;   
		public static String  maverageunitspermonth  ;   
		public static String  mconnectedload  ;    
		public static String  mbilldate  ;    
		public static String  mbillnumber  ;   
		public static String  mmeter_change_units_consumed  ;   
		public static String  mno_of_months  ;    
		public static String  msubtariff1  ;   
		public static String  mpercentage1  ;   
		public static String  msubtariff2  ;   
		public static String  mpercentage2  ;    
		public static String  mhouse_no  ;    
		public static String  mward_no  ;    
		public static String  mmeterrent  ;    
		public static String  msupervisorcharges  ;   
		public static String  mpenaltycharges  ;   
		public static String  madjwithdraw  ;    
		public static String  mothers1  ;    
		public static String  mno_of_months_mnr  ;   
		public static String  mmrname  ;   
		public static String  mmnrcount  ; 

		public static String  mtariffdesc  ;   

		public static String  mbackbillarr  ;   

		public static String  mdl_or_mnr_prev_month  ;   

		public static String  mpresentreading  ;    
		public static String  mconsumption  ;    

		public static String  mreading_date  ;   

		public static String  mno_of_months_issued  ;   

		public static String  mcredit_or_rebate  ;  
		public static String  mfixed_ges   ;    

		   

		public static String  maudit_arrears  ;    

		public static String  mold_interest  ;    

		public static String  mtrivector  ;    
		public static String  mdoorlockavg  ;    

		public static String  mbillmonth  ;    

		public static String  msyncstatus  ;    

		public static String  mdataprepareddate  ;    
		public static String  mservertosbmdate  ;    

		public static String  minterestondeposit  ;   
		public static String  mbillno  ;    
		public static String  mstatus  ;    
		public static String  mbilledstatus  ;   

		public static String  mec  ;    
		public static String  mfc  ;   
		public static String  mtotal  ;    
		public static String  mprebilldate  ;    

		public static String  mduedate  ;    

		public static String  mdivision  ;   
		public static String  msubdivision  ;    
		public static String  mdevicefirmwareversion  ;    
		public static String  mbilledatetimestamp  ;   
		public static String  mlineminimum  ;    
		public static String  mpf_reading  ;   
		public static String  mpf_penality  ;    
		public static String  mpresentmeterstatus  ;   
		
		
		public static String  madditional_chanrges;
		public static String getMadditional_chanrges() {
			return madditional_chanrges;
		}
		public static void setMadditional_chanrges(String madditional_chanrges) {
			DCUtilMaster.madditional_chanrges = madditional_chanrges;
		}
		public static String getMjusco_interest() {
			return mjusco_interest;
		}
		public static void setMjusco_interest(String mjusco_interest) {
			DCUtilMaster.mjusco_interest = mjusco_interest;
		}
		public static String getMjusco_arreears() {
			return mjusco_arreears;
		}
		public static void setMjusco_arreears(String mjusco_arreears) {
			DCUtilMaster.mjusco_arreears = mjusco_arreears;
		}
		public static String getMugd() {
			return mugd;
		}
		public static void setMugd(String mugd) {
			DCUtilMaster.mugd = mugd;
		}
		public static String getMbackbillarrears() {
			return mbackbillarrears;
		}
		public static void setMbackbillarrears(String mbackbillarrears) {
			DCUtilMaster.mbackbillarrears = mbackbillarrears;
		}
		public static String getMbackbillinterest() {
			return mbackbillinterest;
		}
		public static void setMbackbillinterest(String mbackbillinterest) {
			DCUtilMaster.mbackbillinterest = mbackbillinterest;
		}

		public static String mjusco_interest;
		public static String mjusco_arreears;
		public static String mugd;
		public static String mbackbillarrears;
		public static String mbackbillinterest;
		
		
		public static String  mextra1  ;    
		public static String  mextra2  ;    
		public static String  mextra3  ;    
		public static String  mextra4  ;   
		public static String  mextra5  ;    
		public static String  mextra6  ;   
		public static String  mextra7  ;    
		public static String  mextra8  ;
		public static String  mBM  ;
		public static String  tempBM ;
		
		
		
		 public static boolean serverDatesync_key ;
		
		
/**********************SALB RATEB VALUES		*/
		public static float slab_1_Factor ;
		public static float slab_2_Factor ;
		public static float slab_3_Factor ;
		public static float slab_4_Factor ;
		public static float slab_5_Factor ;
		public static float slab_6_Factor ;
	//	public static float Lreading_value_float ;
		public static float no_of_months_the_bill_has_to_be_issued;
		public static float  EnergyCharges ;
		public static float tempNewbm ;
		public static float tempOldbm ;
		
		
		
public static boolean isServerDatesync_key() {
			return serverDatesync_key;
		}
		public static void setServerDatesync_key(boolean serverDatesync_key) {
			DCUtilMaster.serverDatesync_key = serverDatesync_key;
		}
public static float getTempNewbm() {
			return tempNewbm;
		}
		public static void setTempNewbm(float tempNewbm) {
			DCUtilMaster.tempNewbm = tempNewbm;
		}
		public static float getTempOldbm() {
			return tempOldbm;
		}
		public static void setTempOldbm(float tempOldbm) {
			DCUtilMaster.tempOldbm = tempOldbm;
		}
public static String getTempBM() {
			return tempBM;
		}
		public static void setTempBM(String tempBM) {
			DCUtilMaster.tempBM = tempBM;
		}
		public static float getEnergyCharges() {
			return EnergyCharges;
		}
		public static void setEnergyCharges(float energyCharges) {
			EnergyCharges = energyCharges;
		}
public static float getNo_of_months_the_bill_has_to_be_issued() {
			return no_of_months_the_bill_has_to_be_issued;
		}
		public static void setNo_of_months_the_bill_has_to_be_issued(
				float no_of_months_the_bill_has_to_be_issued) {
			DCUtilMaster.no_of_months_the_bill_has_to_be_issued = no_of_months_the_bill_has_to_be_issued;
		}
	/*	public static float getLreading_value_float() {
			return Lreading_value_float;
		}
		public static void setLreading_value_float(float lreading_value_float) {
			Lreading_value_float = lreading_value_float;
		}*/
	public static String getmBM() {
			return mBM;
		}
		public static void setmBM(String mBM) {
			DCUtilMaster.mBM = mBM;
		}
	public  float getSlab_1_Factor() {
			return slab_1_Factor;
		}
		public void setSlab_1_Factor(float slab_1_Factor) {
			DCUtilMaster.slab_1_Factor = slab_1_Factor;
		}
		public  float getSlab_2_Factor() {
			return slab_2_Factor;
		}
		public  void setSlab_2_Factor(float slab_2_Factor) {
			DCUtilMaster.slab_2_Factor = slab_2_Factor;
		}
		public  float getSlab_3_Factor() {
			return slab_3_Factor;
		}
		public  void setSlab_3_Factor(float slab_3_Factor) {
			DCUtilMaster.slab_3_Factor = slab_3_Factor;
		}
		public  float getSlab_4_Factor() {
			return slab_4_Factor;
		}
		public  void setSlab_4_Factor(float slab_4_Factor) {
			DCUtilMaster.slab_4_Factor = slab_4_Factor;
		}
		public  float getSlab_5_Factor() {
			return slab_5_Factor;
		}
		public  void setSlab_5_Factor(float slab_5_Factor) {
			DCUtilMaster.slab_5_Factor = slab_5_Factor;
		}
		public  float getSlab_6_Factor() {
			return slab_6_Factor;
		}
		public  void setSlab_6_Factor(float slab_6_Factor) {
			DCUtilMaster.slab_6_Factor = slab_6_Factor;
		}
	public static String getMcustomer_number() {
			return mcustomer_number;
		}
		public static void setMcustomer_number(String mcustomer_number) {
			DCUtilMaster.mcustomer_number = mcustomer_number;
		}
		public static String getMcusomer_name() {
			return mcusomer_name;
		}
		public static void setMcusomer_name(String mcusomer_name) {
			DCUtilMaster.mcusomer_name = mcusomer_name;
		}
		public static String getMaddress1() {
			return maddress1;
		}
		public static void setMaddress1(String maddress1) {
			DCUtilMaster.maddress1 = maddress1;
		}
		public static String getMaddress2() {
			return maddress2;
		}
		public static void setMaddress2(String maddress2) {
			DCUtilMaster.maddress2 = maddress2;
		}
		public static String getMaddress3() {
			return maddress3;
		}
		public static void setMaddress3(String maddress3) {
			DCUtilMaster.maddress3 = maddress3;
		}
		public static String getMchalan_no() {
			return mchalan_no;
		}
		public static void setMchalan_no(String mchalan_no) {
			DCUtilMaster.mchalan_no = mchalan_no;
		}
		public static String getMfolio_no() {
			return mfolio_no;
		}
		public static void setMfolio_no(String mfolio_no) {
			DCUtilMaster.mfolio_no = mfolio_no;
		}
		public static String getMmeterno() {
			return mmeterno;
		}
		public static void setMmeterno(String mmeterno) {
			DCUtilMaster.mmeterno = mmeterno;
		}
		public static String getMtariff() {
			return mtariff;
		}
		public static void setMtariff(String mtariff) {
			DCUtilMaster.mtariff = mtariff;
		}
		public static String getMmultiplicationfactor() {
			return mmultiplicationfactor;
		}
		public static void setMmultiplicationfactor(String mmultiplicationfactor) {
			DCUtilMaster.mmultiplicationfactor = mmultiplicationfactor;
		}
		public static String getMpreviousreading() {
			return mpreviousreading;
		}
		public static void setMpreviousreading(String mpreviousreading) {
			DCUtilMaster.mpreviousreading = mpreviousreading;
		}
		public static String getMpreviousreadingstatus() {
			return mpreviousreadingstatus;
		}
		public static void setMpreviousreadingstatus(String mpreviousreadingstatus) {
			DCUtilMaster.mpreviousreadingstatus = mpreviousreadingstatus;
		}
		public static String getMprevisousreadingdate() {
			return mprevisousreadingdate;
		}
		public static void setMprevisousreadingdate(String mprevisousreadingdate) {
			DCUtilMaster.mprevisousreadingdate = mprevisousreadingdate;
		}
		public static String getMarrearsnew() {
			return marrearsnew;
		}
		public static void setMarrearsnew(String marrearsnew) {
			DCUtilMaster.marrearsnew = marrearsnew;
		}
		public static String getMadditionalcharges_arrears() {
			return madditionalcharges_arrears;
		}
		public static void setMadditionalcharges_arrears(
				String madditionalcharges_arrears) {
			DCUtilMaster.madditionalcharges_arrears = madditionalcharges_arrears;
		}
		public static String getMinterestnew() {
			return minterestnew;
		}
		public static void setMinterestnew(String minterestnew) {
			DCUtilMaster.minterestnew = minterestnew;
		}
		public static String getMothers() {
			return mothers;
		}
		public static void setMothers(String mothers) {
			DCUtilMaster.mothers = mothers;
		}
		public static String getMtax() {
			return mtax;
		}
		public static void setMtax(String mtax) {
			DCUtilMaster.mtax = mtax;
		}
		public static String getMaverageunitspermonth() {
			return maverageunitspermonth;
		}
		public static void setMaverageunitspermonth(String maverageunitspermonth) {
			DCUtilMaster.maverageunitspermonth = maverageunitspermonth;
		}
		public static String getMconnectedload() {
			return mconnectedload;
		}
		public static void setMconnectedload(String mconnectedload) {
			DCUtilMaster.mconnectedload = mconnectedload;
		}
		public static String getMbilldate() {
			return mbilldate;
		}
		public static void setMbilldate(String mbilldate) {
			DCUtilMaster.mbilldate = mbilldate;
		}
		public static String getMbillnumber() {
			return mbillnumber;
		}
		public static void setMbillnumber(String mbillnumber) {
			DCUtilMaster.mbillnumber = mbillnumber;
		}
		public static String getMmeter_change_units_consumed() {
			return mmeter_change_units_consumed;
		}
		public static void setMmeter_change_units_consumed(
				String mmeter_change_units_consumed) {
			DCUtilMaster.mmeter_change_units_consumed = mmeter_change_units_consumed;
		}
		public static String getMno_of_months() {
			return mno_of_months;
		}
		public static void setMno_of_months(String mno_of_months) {
			DCUtilMaster.mno_of_months = mno_of_months;
		}
		public static String getMsubtariff1() {
			return msubtariff1;
		}
		public static void setMsubtariff1(String msubtariff1) {
			DCUtilMaster.msubtariff1 = msubtariff1;
		}
		public static String getMpercentage1() {
			return mpercentage1;
		}
		public static void setMpercentage1(String mpercentage1) {
			DCUtilMaster.mpercentage1 = mpercentage1;
		}
		public static String getMsubtariff2() {
			return msubtariff2;
		}
		public static void setMsubtariff2(String msubtariff2) {
			DCUtilMaster.msubtariff2 = msubtariff2;
		}
		public static String getMpercentage2() {
			return mpercentage2;
		}
		public static void setMpercentage2(String mpercentage2) {
			DCUtilMaster.mpercentage2 = mpercentage2;
		}
		public static String getMhouse_no() {
			return mhouse_no;
		}
		public static void setMhouse_no(String mhouse_no) {
			DCUtilMaster.mhouse_no = mhouse_no;
		}
		public static String getMward_no() {
			return mward_no;
		}
		public static void setMward_no(String mward_no) {
			DCUtilMaster.mward_no = mward_no;
		}
		public static String getMmeterrent() {
			return mmeterrent;
		}
		public static void setMmeterrent(String mmeterrent) {
			DCUtilMaster.mmeterrent = mmeterrent;
		}
		public static String getMsupervisorcharges() {
			return msupervisorcharges;
		}
		public static void setMsupervisorcharges(String msupervisorcharges) {
			DCUtilMaster.msupervisorcharges = msupervisorcharges;
		}
		public static String getMpenaltycharges() {
			return mpenaltycharges;
		}
		public static void setMpenaltycharges(String mpenaltycharges) {
			DCUtilMaster.mpenaltycharges = mpenaltycharges;
		}
		public static String getMadjwithdraw() {
			return madjwithdraw;
		}
		public static void setMadjwithdraw(String madjwithdraw) {
			DCUtilMaster.madjwithdraw = madjwithdraw;
		}
		public static String getMothers1() {
			return mothers1;
		}
		public static void setMothers1(String mothers1) {
			DCUtilMaster.mothers1 = mothers1;
		}
		public static String getMno_of_months_mnr() {
			return mno_of_months_mnr;
		}
		public static void setMno_of_months_mnr(String mno_of_months_mnr) {
			DCUtilMaster.mno_of_months_mnr = mno_of_months_mnr;
		}
		public static String getMmrname() {
			return mmrname;
		}
		public static void setMmrname(String mmrname) {
			DCUtilMaster.mmrname = mmrname;
		}
		public static String getMmnrcount() {
			return mmnrcount;
		}
		public static void setMmnrcount(String mmnrcount) {
			DCUtilMaster.mmnrcount = mmnrcount;
		}
		public static String getMtariffdesc() {
			return mtariffdesc;
		}
		public static void setMtariffdesc(String mtariffdesc) {
			DCUtilMaster.mtariffdesc = mtariffdesc;
		}
		public static String getMbackbillarr() {
			return mbackbillarr;
		}
		public static void setMbackbillarr(String mbackbillarr) {
			DCUtilMaster.mbackbillarr = mbackbillarr;
		}
		public static String getMdl_or_mnr_prev_month() {
			return mdl_or_mnr_prev_month;
		}
		public static void setMdl_or_mnr_prev_month(String mdl_or_mnr_prev_month) {
			DCUtilMaster.mdl_or_mnr_prev_month = mdl_or_mnr_prev_month;
		}
		public static String getMpresentreading() {
			return mpresentreading;
		}
		public static void setMpresentreading(String mpresentreading) {
			DCUtilMaster.mpresentreading = mpresentreading;
		}
		public static String getMconsumption() {
			return mconsumption;
		}
		public static void setMconsumption(String mconsumption) {
			DCUtilMaster.mconsumption = mconsumption;
		}
		public static String getMreading_date() {
			return mreading_date;
		}
		public static void setMreading_date(String mreading_date) {
			DCUtilMaster.mreading_date = mreading_date;
		}
		public static String getMno_of_months_issued() {
			return mno_of_months_issued;
		}
		public static void setMno_of_months_issued(String mno_of_months_issued) {
			DCUtilMaster.mno_of_months_issued = mno_of_months_issued;
		}
		public static String getMcredit_or_rebate() {
			return mcredit_or_rebate;
		}
		public static void setMcredit_or_rebate(String mcredit_or_rebate) {
			DCUtilMaster.mcredit_or_rebate = mcredit_or_rebate;
		}
		public static String getMfixed_ges() {
			return mfixed_ges;
		}
		public static void setMfixed_ges(String mfixed_ges) {
			DCUtilMaster.mfixed_ges = mfixed_ges;
		}
		public static String getMaudit_arrears() {
			return maudit_arrears;
		}
		public static void setMaudit_arrears(String maudit_arrears) {
			DCUtilMaster.maudit_arrears = maudit_arrears;
		}
		public static String getMold_interest() {
			return mold_interest;
		}
		public static void setMold_interest(String mold_interest) {
			DCUtilMaster.mold_interest = mold_interest;
		}
		public static String getMtrivector() {
			return mtrivector;
		}
		public static void setMtrivector(String mtrivector) {
			DCUtilMaster.mtrivector = mtrivector;
		}
		public static String getMdoorlockavg() {
			return mdoorlockavg;
		}
		public static void setMdoorlockavg(String mdoorlockavg) {
			DCUtilMaster.mdoorlockavg = mdoorlockavg;
		}
		public static String getMbillmonth() {
			return mbillmonth;
		}
		public static void setMbillmonth(String mbillmonth) {
			DCUtilMaster.mbillmonth = mbillmonth;
		}
		public static String getMsyncstatus() {
			return msyncstatus;
		}
		public static void setMsyncstatus(String msyncstatus) {
			DCUtilMaster.msyncstatus = msyncstatus;
		}
		public static String getMdataprepareddate() {
			return mdataprepareddate;
		}
		public static void setMdataprepareddate(String mdataprepareddate) {
			DCUtilMaster.mdataprepareddate = mdataprepareddate;
		}
		public static String getMservertosbmdate() {
			return mservertosbmdate;
		}
		public static void setMservertosbmdate(String mservertosbmdate) {
			DCUtilMaster.mservertosbmdate = mservertosbmdate;
		}
		public static String getMinterestondeposit() {
			return minterestondeposit;
		}
		public static void setMinterestondeposit(String minterestondeposit) {
			DCUtilMaster.minterestondeposit = minterestondeposit;
		}
		public static String getMbillno() {
			return mbillno;
		}
		public static void setMbillno(String mbillno) {
			DCUtilMaster.mbillno = mbillno;
		}
		public static String getMstatus() {
			return mstatus;
		}
		public static void setMstatus(String mstatus) {
			DCUtilMaster.mstatus = mstatus;
		}
		public static String getMbilledstatus() {
			return mbilledstatus;
		}
		public static void setMbilledstatus(String mbilledstatus) {
			DCUtilMaster.mbilledstatus = mbilledstatus;
		}
		public static String getMec() {
			return mec;
		}
		public static void setMec(String mec) {
			DCUtilMaster.mec = mec;
		}
		public static String getMfc() {
			return mfc;
		}
		public static void setMfc(String mfc) {
			DCUtilMaster.mfc = mfc;
		}
		public static String getMtotal() {
			return mtotal;
		}
		public static void setMtotal(String mtotal) {
			DCUtilMaster.mtotal = mtotal;
		}
		public static String getMprebilldate() {
			return mprebilldate;
		}
		public static void setMprebilldate(String mprebilldate) {
			DCUtilMaster.mprebilldate = mprebilldate;
		}
		public static String getMduedate() {
			return mduedate;
		}
		public static void setMduedate(String mduedate) {
			DCUtilMaster.mduedate = mduedate;
		}
		public static String getMdivision() {
			return mdivision;
		}
		public static void setMdivision(String mdivision) {
			DCUtilMaster.mdivision = mdivision;
		}
		public static String getMsubdivision() {
			return msubdivision;
		}
		public static void setMsubdivision(String msubdivision) {
			DCUtilMaster.msubdivision = msubdivision;
		}
		public static String getMdevicefirmwareversion() {
			return mdevicefirmwareversion;
		}
		public static void setMdevicefirmwareversion(String mdevicefirmwareversion) {
			DCUtilMaster.mdevicefirmwareversion = mdevicefirmwareversion;
		}
		public static String getMbilledatetimestamp() {
			return mbilledatetimestamp;
		}
		public static void setMbilledatetimestamp(String mbilledatetimestamp) {
			DCUtilMaster.mbilledatetimestamp = mbilledatetimestamp;
		}
		public static String getMlineminimum() {
			return mlineminimum;
		}
		public static void setMlineminimum(String mlineminimum) {
			DCUtilMaster.mlineminimum = mlineminimum;
		}
		public static String getMpf_reading() {
			return mpf_reading;
		}
		public static void setMpf_reading(String mpf_reading) {
			DCUtilMaster.mpf_reading = mpf_reading;
		}
		public static String getMpf_penality() {
			return mpf_penality;
		}
		public static void setMpf_penality(String mpf_penality) {
			DCUtilMaster.mpf_penality = mpf_penality;
		}
		public static String getMpresentmeterstatus() {
			return mpresentmeterstatus;
		}
		public static void setMpresentmeterstatus(String mpresentmeterstatus) {
			DCUtilMaster.mpresentmeterstatus = mpresentmeterstatus;
		}
		public static String getMextra1() {
			return mextra1;
		}
		public static void setMextra1(String mextra1) {
			DCUtilMaster.mextra1 = mextra1;
		}
		public static String getMextra2() {
			return mextra2;
		}
		public static void setMextra2(String mextra2) {
			DCUtilMaster.mextra2 = mextra2;
		}
		public static String getMextra3() {
			return mextra3;
		}
		public static void setMextra3(String mextra3) {
			DCUtilMaster.mextra3 = mextra3;
		}
		public static String getMextra4() {
			return mextra4;
		}
		public static void setMextra4(String mextra4) {
			DCUtilMaster.mextra4 = mextra4;
		}
		public static String getMextra5() {
			return mextra5;
		}
		public static void setMextra5(String mextra5) {
			DCUtilMaster.mextra5 = mextra5;
		}
		public static String getMextra6() {
			return mextra6;
		}
		public static void setMextra6(String mextra6) {
			DCUtilMaster.mextra6 = mextra6;
		}
		public static String getMextra7() {
			return mextra7;
		}
		public static void setMextra7(String mextra7) {
			DCUtilMaster.mextra7 = mextra7;
		}
		public static String getMextra8() {
			return mextra8;
		}
		public static void setMextra8(String mextra8) {
			DCUtilMaster.mextra8 = mextra8;
		}
		
		
		
		
	public static Toast showToast(Context context , int msg){
		
	   return	Toast.makeText(context,msg,Toast.LENGTH_LONG);
		
	}
	public static Toast showToast(Context context , String msg){
		
		   return	Toast.makeText(context,msg,Toast.LENGTH_LONG);
			
		}
	
	public static AlertDialog.Builder show(Context context , String title,int icon,String msg, String pvtstrg , OnClickListener pvt_listener , String ngtstrg , OnClickListener ngt_listener ) {
		  return	new AlertDialog.Builder(context).setTitle(title).setIcon(icon).setMessage(msg).setPositiveButton(pvtstrg, pvt_listener).setNegativeButton(ngtstrg, ngt_listener);
			
			
		}
	
	public static AlertDialog.Builder show(Context context , String title,int icon,String msg, String pvtstrg , OnClickListener pvt_listener  ) {
		  return	new AlertDialog.Builder(context).setTitle(title).setIcon(icon).setMessage(msg).setPositiveButton(pvtstrg, pvt_listener);
			
			
		}

}
