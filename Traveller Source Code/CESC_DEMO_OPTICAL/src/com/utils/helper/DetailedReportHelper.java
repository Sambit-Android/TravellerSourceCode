package com.utils.helper;

public class DetailedReportHelper {

	public int mtrStatusNrm , mtrStatusDL , mtrStatusMNR , mtrStatusDC , mtrStatusDISSCONN , mtrStatusIDLE , mtrStatusMB , mtrStatusMS , mtrStatusNV ;
	public int report_Totalunits ,  report_totalRev  , report_Billed , report_totalConn;
	public String tariffCode , traifDesc ;
	
	
	public DetailedReportHelper(int mtrStatusNrm, int mtrStatusDL,
			int mtrStatusMNR, int mtrStatusDC, int mtrStatusDISSCONN,
			int mtrStatusIDLE, int mtrStatusMB, int mtrStatusMS,
			int mtrStatusNV, int report_Totalunits, int report_totalRev,
			int report_Billed, int report_totalConn, String tariffCode , String traifDesc) {
		super();
		this.mtrStatusNrm = mtrStatusNrm;
		this.mtrStatusDL = mtrStatusDL;
		this.mtrStatusMNR = mtrStatusMNR;
		this.mtrStatusDC = mtrStatusDC;
		this.mtrStatusDISSCONN = mtrStatusDISSCONN;
		this.mtrStatusIDLE = mtrStatusIDLE;
		this.mtrStatusMB = mtrStatusMB;
		this.mtrStatusMS = mtrStatusMS;
		this.mtrStatusNV = mtrStatusNV;
		this.report_Totalunits = report_Totalunits;
		this.report_totalRev = report_totalRev;
		this.report_Billed = report_Billed;
		this.report_totalConn = report_totalConn;
		this.tariffCode = tariffCode;
		this.traifDesc  = traifDesc ;
	}
	
	
	
	
	
	
}
