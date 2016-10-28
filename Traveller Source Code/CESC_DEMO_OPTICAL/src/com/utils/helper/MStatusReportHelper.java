package com.utils.helper;

public class MStatusReportHelper {
	
	public String status ;
	public String count ;
	
	
	
	public String rtariff, rtariffdesc,   rmeterStatus ,
			rsyncstatus , rbilledstatus ;
	public float rt_total_rev , rtotal_units  , rconn_count; 
	
	
	//SELECT SYNCSTATUS , BILLEDSTATUS ,TARIFF , TARIFFDESC, PRESENTMETERSTATUS AS METER_STATUS  , count(*) AS CONN_COUNT , sum(CONSUMPTION) AS TOTAL_UNITS , sum(KEY_ROUNDOFFTOATL) AS T_TOTAL_REV FROM "CONSUMER_INPUT_LIVE" WHERE PRESENTMETERSTATUS <> '' GROUP BY TARIFF ,PRESENTMETERSTATUS;

	
	
	/**
	 * @category OBJECT FOR SUMMARY REPORT 
	 * @param status
	 * @param count
	 */
	public MStatusReportHelper(String status, String count) {
		this.status = status;
		this.count = count;
	}


	/**
	 * @category OBJECT FOR DETAILED SUMMARY REPORT 
	 * @param rtariff
	 * @param rtariffdesc
	 * @param rmeterStatus
	 * @param rsyncstatus
	 * @param rbilledstatus
	 * @param rt_total_rev
	 * @param rtotal_units
	 * @param rconn_count
	 */
	public MStatusReportHelper(String rtariff, String rtariffdesc,
			String rmeterStatus, String rsyncstatus, String rbilledstatus,
			float rt_total_rev, float rtotal_units, float rconn_count) {
		super();
		this.rtariff = rtariff;
		this.rtariffdesc = rtariffdesc;
		this.rmeterStatus = rmeterStatus;
		this.rsyncstatus = rsyncstatus;
		this.rbilledstatus = rbilledstatus;
		this.rt_total_rev = rt_total_rev;
		this.rtotal_units = rtotal_units;
		this.rconn_count = rconn_count;
	}


	

	

	
	
}
