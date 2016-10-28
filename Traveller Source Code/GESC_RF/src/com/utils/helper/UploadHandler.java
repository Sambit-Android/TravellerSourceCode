package com.utils.helper;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.BatteryManager;
import android.util.Base64;

public class UploadHandler {

	 String   meterreader_code = "";
	 String   consumer_sc_no = "";
	 String   sbm_no = "";
	 String   sitecode = "";
	 String   present_reading = "";
	 String   ckwhlkwh = "";
	 String   units = "";
	 String   ec = "";
	 String   fc = "";
	 String   pf_reading = "";
	 String   bmd_reading = "";
	 String   pf_penality = "";
	 String   bmd_penality = "";
	 String   netamountdue = "";
	 String   tax = "";
	 String   credit = "";
	 String   grandtotal = "";
	 String   out_mnths_bill_tobe_issued = "";
	 String   meterreadingtype = "";
	 String   receipt_no = "";
	 String   cheque_no = "";
	 String   cheque_date = "";
	 String   bank = "";
	 String   bill_no = "";
	 String   bill_status = "";
	 String   trans_type = "";
	 String   billedatetimestamp = "";
	 String   tariff = "";
	 String   dl_adj_given = "";
	 String   fixed_charges = "";
	 String   syncmain = "";
	 String   sbmtoserverdatestamp = "";
	 String   recordid = "";
	 String   recordmoved_flag = "";
	
	 String longitude= "";
		String lattitude= "";
		 byte[]  mimage  =null ;
		String takentime= "";
	 String bill_month = "";
	 String previous_reading = "";
	 String meter_no = "";
	 String reading_date = "";
	 String extra6 = ""; // deviceinfo
	 
	 String d_and_r_fee = "";
	 String backbillarr = "";
	 String audit_arrears = "";
	 String others = "";
	 
	 
	 
	
	
	


	/**
	 * @param meterreader_code
	 * @param consumer_sc_no
	 * @param sbm_no
	 * @param sitecode
	 * @param present_reading
	 * @param ckwhlkwh
	 * @param units
	 * @param ec
	 * @param pf_reading
	 * @param bmd_reading
	 * @param pf_penality
	 * @param bmd_penality
	 * @param netamountdue
	 * @param tax
	 * @param credit
	 * @param grandtotal
	 * @param out_mnths_bill_tobe_issued
	 * @param meterreadingtype
	 * @param receipt_no
	 * @param cheque_no
	 * @param cheque_date
	 * @param bank
	 * @param bill_no
	 * @param bill_status
	 * @param trans_type
	 * @param billedatetimestamp
	 * @param tariff
	 * @param dl_adj_given
	 * @param fixed_charges
	 * @param syncmain
	 * @param sbmtoserverdatestamp
	 * @param recordid
	 * @param recordmoved_flag
	 * @param longitude
	 * @param lattitude
	 * @param mimage
	 * @param takentime
	 */
	public UploadHandler(String meterreader_code, String consumer_sc_no,
				String sbm_no, String sitecode, String present_reading,
				String ckwhlkwh, String units, String ec,String fc, String pf_reading,
				String bmd_reading, String pf_penality, String bmd_penality,
				String netamountdue, String tax, String credit,
				String grandtotal, String out_mnths_bill_tobe_issued,
				String meterreadingtype, String receipt_no, String cheque_no,
				String cheque_date, String bank, String bill_no,
				String bill_status, String trans_type,
				String billedatetimestamp, String tariff, String dl_adj_given,
				String fixed_charges, String syncmain,
				String sbmtoserverdatestamp, String recordid,
				String recordmoved_flag, String longitude, String lattitude,
				byte[] mimage, String takentime , String bill_month , String previous_reading ,
			String meter_no	, String reading_date, String extra6, String d_and_r_fee, String backbillarr, String audit_arrears, String others
			) {
			super();
			this.meterreader_code = meterreader_code;
			this.consumer_sc_no = consumer_sc_no;
			this.sbm_no = sbm_no;
			this.sitecode = sitecode;
			this.present_reading = present_reading;
			this.ckwhlkwh = ckwhlkwh;
			this.units = units;
			this.ec = ec;
			this.fc= fc;
			this.pf_reading = pf_reading;
			this.bmd_reading = bmd_reading;
			this.pf_penality = pf_penality;
			this.bmd_penality = bmd_penality;
			this.netamountdue = netamountdue;
			this.tax = tax;
			this.credit = credit;
			this.grandtotal = grandtotal;
			this.out_mnths_bill_tobe_issued = out_mnths_bill_tobe_issued;
			this.meterreadingtype = meterreadingtype;
			this.receipt_no = receipt_no;
			this.cheque_no = cheque_no;
			this.cheque_date = cheque_date;
			this.bank = bank;
			this.bill_no = bill_no;
			this.bill_status = bill_status;
			this.trans_type = trans_type;
			this.billedatetimestamp = billedatetimestamp;
			this.tariff = tariff;
			this.dl_adj_given = dl_adj_given;
			this.fixed_charges = fixed_charges;
			this.syncmain = syncmain;
			this.sbmtoserverdatestamp = sbmtoserverdatestamp;
			this.recordid = recordid;
			this.recordmoved_flag = recordmoved_flag;
			this.longitude = longitude;
			this.lattitude = lattitude;
			this.mimage = mimage;
			this.takentime = takentime;
			this.bill_month = bill_month ;
			this.previous_reading = previous_reading ;
			this.meter_no = meter_no ;
			this.reading_date = reading_date ;
			this.extra6 = extra6;
			this.d_and_r_fee = d_and_r_fee;
			this.backbillarr = backbillarr;
			this.audit_arrears = audit_arrears;
			this.others = others;
			
		}


	/**
	 *  EMPTY CONSTRUCTOR 
	 */
	public UploadHandler() {}
	
	
	public JSONObject getJSONObject() {
        JSONObject object = new JSONObject();
		try {
			object.put("METERREADER_CODE", this.meterreader_code);
			object.put("CONSUMER_SC_NO", this.consumer_sc_no);
			object.put("SBM_NO", this.sbm_no);
			object.put("SITECODE", this.sitecode);
			object.put("PRESENT_READING", this.present_reading);
			object.put("CKWHLKWH", this.ckwhlkwh);
			object.put("UNITS", this.units);
			object.put("EC", this.ec);
			object.put("FC", this.fc);
			
			object.put("PF_READING", this.pf_reading);
			object.put("BMD_PENALITY_READING", this.bmd_reading);
			object.put("PF_PENALITY", this.pf_penality);
			object.put("BMD_PENALITY", this.bmd_penality);
			object.put("NETAMOUNTDUE", this.netamountdue);
			object.put("TAX", this.tax);
			object.put("CREDIT", this.credit);
			object.put("GRANDTOTAL", this.grandtotal);
			object.put("OUT_MNTHS_BILL_TOBE_ISSUED",this.out_mnths_bill_tobe_issued);
			object.put("METERREADINGTYPE", this.meterreadingtype);
			object.put("RECEIPT_NO", this.receipt_no);
			object.put("CHEQUE_NO", this.cheque_no);
			object.put("CHEQUE_DATE", this.cheque_date);
			object.put("BANK", this.bank);
			object.put("BILL_NO", this.bill_no);
			object.put("BILL_STATUS", this.bill_status);
			object.put("TRANS_TYPE", this.trans_type);
			object.put("BILLEDATETIMESTAMP", this.billedatetimestamp);
			object.put("TARIFF", this.tariff);
			object.put("DL_ADJ_GIVEN", this.dl_adj_given);
			object.put("FIXED_CHARGES", this.fixed_charges);
			object.put("SYNCMAIN", this.syncmain);
			object.put("SBMTOSERVERDATESTAMP", this.sbmtoserverdatestamp);
			object.put("RECORDID", this.recordid);
			object.put("RECORDMOVED_FLAG", this.recordmoved_flag);
			
			object.put("LONGITUDE", this.longitude);
			object.put("LATTITUDE", this.lattitude);
			object.put("TAKENTIME", this.takentime);
			
			/****************ADD TO UPLOAD OLD BACKUP DATA, WHERE IMAGE WILL BE NULL******************/
			if(this.mimage == null)this.mimage = "".getBytes();
			
			object.put("IMAGE", Base64.encodeToString(this.mimage, Base64.DEFAULT));
			
			object.put("BILL_MONTH", this.bill_month);
			object.put("PREVIOUS_READING", this.previous_reading); 
			object.put("METER_NO", this.meter_no);
			object.put("READING_DATE", this.reading_date);
			object.put("EXTRA6", this.extra6);
			
			object.put("D_AND_R_FEE", this.d_and_r_fee);
			object.put("BACKBILL_ARREARS", this.backbillarr);
			object.put("AUDIT_ARREARS", this.audit_arrears);
			object.put("OTHERS", this.others);

			
			
		//	object.put("BMD_PENALITY", this.bmd_penality);
		//	object.put("BMD_PENALITY_READING", this.bmd_penality_reading);
			
			/*if (jobject.has("BMD_PENALITY")) {	BMD_PENALITY = jobject.getString("BMD_PENALITY");	}
			if (jobject.has( "BMD_PENALITY_READING"))  {	BMD_PENALITY_READING			 = jobject	.getString( "BMD_PENALITY_READING");}*/
			
			
			
			
		

		} catch (JSONException e) {
	           
        }
        return object;
    }
	
}
