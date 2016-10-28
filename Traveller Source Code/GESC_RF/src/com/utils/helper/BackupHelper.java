package com.utils.helper;

public class BackupHelper {

	
	public String 	mbconsumer_sc_no		= "";
	public String 	mbmeter_constant		= "";
	public String 	mbconsumer_name		= "";
	public String 	mbaddress		= "";
	public String 	mbaddress1		= "";
	public String 	mbtariff		= "";
	public String 	mbtariffdesc		= "";
	public String 	mbledger_no		= "";
	public String 	mbfolio_no		= "";
	public String 	mbconnected_load		= "";
	public String 	mbd_and_r_fee		= "";
	public String 	mbarrears		= "";
	public String 	mbinterest		= "";
	public String 	mbothers		= "";
	public String 	mbbackbillarr		= "";
	public String 	mbaverage_consumption		= "";
	public String 	mbdl_or_mnr_prev_month		= "";
	public String 	mbprevious_reading		= "";
	public String 	mbpresent_reading		= "";
	public String 	mbconsumption		= "";
	public String 	mbpower_factor		= "";
	public String 	mbreading_date		= "";
	public String 	mbmeter_change_units_consumed		= "";
	public String 	mbno_of_months_issued		= "";
	public String 	mbcredit_or_rebate		= "";
	public String 	mbfixed_ges		= "";
	public String 	mbaudit_arrears		= "";
	public String 	mbold_interest		= "";
	public String 	mbtrivector		= "";
	public String 	mckwhlkwh		= "";
	public String 	mbdoorlockavg		= "";
	public String 	mbconsumercode		= "";
	public String 	mbadditionaldep		= "";
	public String 	mbmrcode		= "";
	public String 	mbbillmonth		= "";
	public String 	mbsitecode		= "";
	public String 	mbsyncstatus		= "";
	public String 	mbdataprepareddate		= "";
	public String 	mbservertosbmdate		= "";
	public String 	mbmeterno		= "";
	public String 	mbinterestondeposit		= "";
	public String 	mbdl_adj		= "";
	public String 	mbdl_count		= "";
	public String 	mbmeterrent		= "";
	public String 	mbfppca		= "";
	public String 	mbtax		= "";
	public String 	mbkey_ec		= "";
	public String 	mbkey_fc		= "";
	public String 	mbkey_toatl		= "";
	public String 	mbroundoff		= "";
	public String 	mbkey_roundofftoatl		= "";
	public String 	mbkey_prebilldate		= "";
	public String 	mbkey_billdate		= "";
	public String 	mbkey_duedate		= "";
	public String 	mbextra1		= "";
	public String 	mbextra2		= "";
	public String 	mbcyclename		= "";
	public String 	mbconsumer_key		= "";
	public String 	mbinstallationo		= "";
	public String 	mbconsumerno		= "";
	public String 	mbdivision		= "";
	public String 	mbsubdivision		= "";
	public String 	mbbookno		= "";
	public String 	mbdevicefirmwareversion		= "";
	public String 	mbbilledatetimestamp		= "";
	public String 	mbbillno		= "";
	public String 	mbpresentmeterstatus		= "";
	public String 	mbbilledstatus		= "";
	public String 	mbstatus		= "";
	public String 	mbprevious_reading_date		= "";
	public String 	mbprevious_actual_reading_date		= "";
	public String 	mblineminimum		= "";
	public String 	mbseasonal_consumer		= "";
	public String 	mbligpoints		= "";
	public String 	mbmetered		= "";
	public String 	mbpf_reading		= "";
	public String 	mbpf_penality		= "";
	
	public String   bmd_reading = "";
	 
	 public String   bmd_penality = "";
	
	public  byte[] 	mbimage		= null;
	 public String 	mbimagepath		= "";
	public String 	mblongitude		= "";
	public String 	mblattitude		= "";
	public String 	mbtakentime		= "";
	public String 	mbextra3		= "";
	public String 	mbextra4		= "";
	public String 	mbextra5		= "";
	public String 	mbextra6		= "";
	public String 	mbextra7		= "";
	public String 	mbextra8		= "";
	
	
	
	public BackupHelper() {	}



	public BackupHelper(String mbconsumer_sc_no, String mbmeter_constant,
			String mbconsumer_name, String mbaddress, String mbaddress1,
			String mbtariff, String mbtariffdesc, String mbledger_no,
			String mbfolio_no, String mbconnected_load, String mbd_and_r_fee,
			String mbarrears, String mbinterest, String mbothers,
			String mbbackbillarr, String mbaverage_consumption,
			String mbdl_or_mnr_prev_month, String mbprevious_reading,
			String mbpresent_reading, String mbconsumption,
			String mbpower_factor, String mbreading_date,
			String mbmeter_change_units_consumed, String mbno_of_months_issued,
			String mbcredit_or_rebate, String mbfixed_ges,
			String mbaudit_arrears, String mbold_interest, String mbtrivector,
			String mckwhlkwh, String mbdoorlockavg, String mbconsumercode,
			String mbadditionaldep, String mbmrcode, String mbbillmonth,
			String mbsitecode, String mbsyncstatus, String mbdataprepareddate,
			String mbservertosbmdate, String mbmeterno,
			String mbinterestondeposit, String mbdl_adj,String mbdl_count, String mbmeterrent,
			String mbfppca, String mbtax, String mbkey_ec, String mbkey_fc,
			String mbkey_toatl, String mbroundoff, String mbkey_roundofftoatl,
			String mbkey_prebilldate, String mbkey_billdate,
			String mbkey_duedate, String mbextra1, String mbextra2,
			String mbcyclename, String mbconsumer_key, String mbinstallationo,
			String mbconsumerno, String mbdivision, String mbsubdivision,
			String mbbookno, String mbdevicefirmwareversion,
			String mbbilledatetimestamp, String mbbillno,
			String mbpresentmeterstatus, String mbbilledstatus,
			String mbstatus, String mbprevious_reading_date,
			String mbprevious_actual_reading_date, String mblineminimum,
			String mbseasonal_consumer, String mbligpoints, String mbmetered,
			String mbpf_reading, String mbpf_penality, String bmd_reading,
			String bmd_penality, byte[] mbimage, String mbimagepath,
			String mblongitude, String mblattitude, String mbtakentime,
			String mbextra3, String mbextra4, String mbextra5, String mbextra6,
			String mbextra7, String mbextra8) {
		super();
		this.mbconsumer_sc_no = mbconsumer_sc_no;
		this.mbmeter_constant = mbmeter_constant;
		this.mbconsumer_name = mbconsumer_name;
		this.mbaddress = mbaddress;
		this.mbaddress1 = mbaddress1;
		this.mbtariff = mbtariff;
		this.mbtariffdesc = mbtariffdesc;
		this.mbledger_no = mbledger_no;
		this.mbfolio_no = mbfolio_no;
		this.mbconnected_load = mbconnected_load;
		this.mbd_and_r_fee = mbd_and_r_fee;
		this.mbarrears = mbarrears;
		this.mbinterest = mbinterest;
		this.mbothers = mbothers;
		this.mbbackbillarr = mbbackbillarr;
		this.mbaverage_consumption = mbaverage_consumption;
		this.mbdl_or_mnr_prev_month = mbdl_or_mnr_prev_month;
		this.mbprevious_reading = mbprevious_reading;
		this.mbpresent_reading = mbpresent_reading;
		this.mbconsumption = mbconsumption;
		this.mbpower_factor = mbpower_factor;
		this.mbreading_date = mbreading_date;
		this.mbmeter_change_units_consumed = mbmeter_change_units_consumed;
		this.mbno_of_months_issued = mbno_of_months_issued;
		this.mbcredit_or_rebate = mbcredit_or_rebate;
		this.mbfixed_ges = mbfixed_ges;
		this.mbaudit_arrears = mbaudit_arrears;
		this.mbold_interest = mbold_interest;
		this.mbtrivector = mbtrivector;
		this.mckwhlkwh = mckwhlkwh;
		this.mbdoorlockavg = mbdoorlockavg;
		this.mbconsumercode = mbconsumercode;
		this.mbadditionaldep = mbadditionaldep;
		this.mbmrcode = mbmrcode;
		this.mbbillmonth = mbbillmonth;
		this.mbsitecode = mbsitecode;
		this.mbsyncstatus = mbsyncstatus;
		this.mbdataprepareddate = mbdataprepareddate;
		this.mbservertosbmdate = mbservertosbmdate;
		this.mbmeterno = mbmeterno;
		this.mbinterestondeposit = mbinterestondeposit;
		this.mbdl_adj = mbdl_adj; 
		this. mbdl_count = mbdl_count;
		this.mbmeterrent = mbmeterrent;
		this.mbfppca = mbfppca;
		this.mbtax = mbtax;
		this.mbkey_ec = mbkey_ec;
		this.mbkey_fc = mbkey_fc;
		this.mbkey_toatl = mbkey_toatl;
		this.mbroundoff = mbroundoff;
		this.mbkey_roundofftoatl = mbkey_roundofftoatl;
		this.mbkey_prebilldate = mbkey_prebilldate;
		this.mbkey_billdate = mbkey_billdate;
		this.mbkey_duedate = mbkey_duedate;
		this.mbextra1 = mbextra1;
		this.mbextra2 = mbextra2;
		this.mbcyclename = mbcyclename;
		this.mbconsumer_key = mbconsumer_key;
		this.mbinstallationo = mbinstallationo;
		this.mbconsumerno = mbconsumerno;
		this.mbdivision = mbdivision;
		this.mbsubdivision = mbsubdivision;
		this.mbbookno = mbbookno;
		this.mbdevicefirmwareversion = mbdevicefirmwareversion;
		this.mbbilledatetimestamp = mbbilledatetimestamp;
		this.mbbillno = mbbillno;
		this.mbpresentmeterstatus = mbpresentmeterstatus;
		this.mbbilledstatus = mbbilledstatus;
		this.mbstatus = mbstatus;
		this.mbprevious_reading_date = mbprevious_reading_date;
		this.mbprevious_actual_reading_date = mbprevious_actual_reading_date;
		this.mblineminimum = mblineminimum;
		this.mbseasonal_consumer = mbseasonal_consumer;
		this.mbligpoints = mbligpoints;
		this.mbmetered = mbmetered;
		this.mbpf_reading = mbpf_reading;
		this.mbpf_penality = mbpf_penality;
		this.bmd_reading = bmd_reading;
		this.bmd_penality = bmd_penality;
		this.mbimage = mbimage;
		this.mbimagepath = mbimagepath;
		this.mblongitude = mblongitude;
		this.mblattitude = mblattitude;
		this.mbtakentime = mbtakentime;
		this.mbextra3 = mbextra3;
		this.mbextra4 = mbextra4;
		this.mbextra5 = mbextra5;
		this.mbextra6 = mbextra6;
		this.mbextra7 = mbextra7;
		this.mbextra8 = mbextra8;
	}



	

	
	
	
}
