package com.bcits.serviceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.bcits.entity.MatrixEntity;
import com.bcits.entity.RrnoSearchEntity;
import com.bcits.service.ConsumerDetailsSearchService;
import com.bcits.service.RrnoSearchService;
import com.bcits.utility.BCITSLogger;

@Repository
public class ConsumerDetailsSearchImpl extends GenericServiceImplOracle<MatrixEntity>
		implements ConsumerDetailsSearchService {

	@Override
	public List<Map<String, Object>> getConsumerlistforrrno(String rrno, String schemaname) {
		BCITSLogger.logger.info("REPORT NAME======================>GET consumer details " + rrno + "===" + schemaname);
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		List<?> list = null;
		try {
			String sql = "SELECT RRNO, CONSUMERNAME AS NAME, CONSUMERADDRESS AS ADDRESS, CONSUMERADDRESS1 AS ADDRESS1, PLACE, LCNO||'/'||FOLIONO AS LC_FOLIO,"
					+ "FC, (CTRATION/CTRATIOD) AS METERCONSTANT, CORPORATION, AREANAME, SOCODE, FEEDERCODE, TCCODE, POLENO, MRCODE, TOD, LDDATE, WARD, BKWH, "
					+ "TARIFFDCB AS TARIFF,TARIFFCODE , DATEOFSERVICE, SANCTIONKW, SANCTIONHP, CONTRACTDMD, PDDATE, TALUK, NOOFFLATS, "
					+ "(CASE WHEN INSTSTATUS =1 THEN 'LD' WHEN INSTSTATUS =0 THEN 'PD' WHEN INSTSTATUS =2 THEN 'TD' WHEN INSTSTATUS =3 THEN 'LIVE'"
					+ "WHEN (INSTSTATUS =4 OR INSTSTATUS =-4)  THEN 'BILLSTOP' END ) AS INSTSTATUS,STATUSDATE, AVERAGE, READINGDAY, SIDERRNO, PF, TELEPHONENO, PANCHAYATH, "
					+ "NOOFMETERS,'' AS TERMINATIONDATE,'' AS DISPUTEAMOUNT,'' AS AFORMDATE,'' AS BFORMDATE,'' AS CFORMDATE,TRIVECTOR FROM "
					+ schemaname + ".MASTER WHERE RRNO LIKE '" + rrno + "'";
			System.out.println("QUERYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYY" + sql);
			list = oracleEntityManager.createNativeQuery(sql).getResultList();
			for (Iterator<?> iterator = list.iterator(); iterator.hasNext();) {
				Object[] obj = (Object[]) iterator.next();
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("RRNO", obj[0]);
				map.put("CONSUMERNAME", obj[1]);
				map.put("CONSUMERADDRESS", obj[2]);
				map.put("CONSUMERADDRESS1", obj[3]);
				map.put("PLACE", obj[4]);
				map.put("LC_FOLIO", obj[5]);
				map.put("FC", obj[6]);
				map.put("METERCONSTANT", obj[7]);
				map.put("CORPORATION", obj[8]);
				map.put("AREANAME", obj[9]);
				map.put("SOCODE", obj[10]);
				map.put("FEEDERCODE", obj[11]);
				map.put("TCCODE", obj[12]);
				map.put("POLENO", obj[13]);
				map.put("MRCODE", obj[14]);
				map.put("TOD", obj[15]);
				map.put("LDDATE", obj[16]);
				map.put("WARD", obj[17]);
				map.put("BKWH", obj[18]);
				map.put("TARIFFDCB", obj[19]);
				map.put("TARIFFCODE", obj[20]);
				map.put("DATEOFSERVICE", obj[21]);
				map.put("SANCTIONKW", obj[22]);
				map.put("SANCTIONHP", obj[23]);
				map.put("CONTRACTDMD", obj[24]);
				map.put("PDDATE", obj[25]);
				map.put("TALUK", obj[26]);
				map.put("NOOFFLATS", obj[27]);
				map.put("INSTSTATUS", obj[28]);
				map.put("STATUSDATE", obj[29]);
				map.put("AVERAGE", obj[30]);
				map.put("READINGDAY", obj[31]);
				map.put("SIDERRNO", obj[32]);
				map.put("PF", obj[33]);
				map.put("TELEPHONENO", obj[34]);
				map.put("PANCHAYATH", obj[35]);
				map.put("NOOFMETERS", obj[36]);
				map.put("TERMINATIONDATE", obj[37]);
				map.put("DISPUTEAMOUNT", obj[38]);
				map.put("AFORMDATE", obj[39]);
				map.put("BFORMDATE", obj[40]);
				map.put("CFORMDATE", obj[41]);
				map.put("TRIVECTOR", obj[42]);
				result.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<Map<String, Object>> getBillingDetailsforrrno(String rrno, String schemaname) {
		BCITSLogger.logger.info("REPORT NAME======================>GET consumer details " + rrno + "===" + schemaname);
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		List<?> list = null;
		try {
			String sql = "SELECT A.RRNO, A.MONTHYEAR, A.ScheduledDay , A.BILLDATE,A.BILLNO,"
					+ "A.FR, A.IR, A.UNITS, A.MC_Units, A.BM, A.STATUS,A.OB, A.FC, A.EC, A.FAC, A.PF,"
					+ "A.PFPEN, A.BMD, A.PENAMT, A.REBATE,A.ARREARS, A.INTE, A.OLDINT, A.TAX, A.NET,"
					+ "A.DRAMT, A.OTHERS, A.AUDITARR, A.MMD, A.BBC,A.APPEAL, A.SUSADJ, A.SUSCB , A.CB,"
					+ "NVL(B.AMOUNT,0)  AS COLL, A.DUEDATE  ,A. YEARMONTH ,"
					+ "(A.OB+A.FC+A.EC+A.PFPEN+A.OTHERS+A.AUDITARR+A.BBC+A.PENAMT+"
					+ "A.DRAMT-A.REBATE-A.DL_ADJ_GIVEN+A.INTE-A.OLDINT+A.TAX+A.FAC)-NVL(B.AMOUNT,0) AS CLOSINGBALANCE,A.DL_ADJ_GIVEN,A.SUSOB,A.ELPEN"
					+ " FROM " + "(SELECT RRNO, TO_CHAR(RDNGDATE,'MON-YYYY') AS MONTHYEAR,"
					+ "TO_CHAR(RDNGDATE,'DD/MM/YYYY') AS ScheduledDay , TO_CHAR(BILLDATE,'DD/MM/YYYY') AS BILLDATE,BILLNO,"
					+ "PRESENTREADING AS FR, PREVIOUSREADING AS IR, UNITS, MCUNITSCONSUMED AS MC_Units, BM,"
					+ "METERSTATUS AS STATUS,OBA+OBI+OBT+OBF+SUSOB AS OB, FCAMT AS FC,"
					+ "ECAMT  AS EC, FPPCA AS FAC, PF, PFPEN, BILLINGDEMAND AS BMD, PENAMT,"
					+ "CREDITREBATE AS REBATE,ARREARS, INTEREST AS INTE, OLDINTEREST AS OLDINT, TAX, NETAMTDUE AS NET, DRAMT,"
					+ "OTHERS, AUDITARR, AUDITMMD AS MMD, BACKBILLARR AS BBC,APPINT AS APPEAL, SUSADJ, SUSCB ,"
					+ "CBA+CBI+CBT+CBF+SUSCB AS CB, TO_CHAR(RDNGDATE+14,'DD/MM/YYYY') AS DUEDATE ,TO_CHAR(RDNGDATE,'YYYYMM') AS YEARMONTH ,"
					+ "0 AS DL_ADJ_GIVEN,0 AS SUSOB,  0 AS ELPEN FROM "+schemaname+".MATRIX )A,"
					+ "(select RRNO, SUM(AMOUNT) AS AMOUNT, TO_CHAR(RECDATE,'YYYYMM') AS YEARMONTH"
					+ " from "+schemaname+".CASHMASTER WHERE CASHTYPE <=13"
					+ "GROUP BY RRNO, TO_CHAR(RECDATE,'YYYYMM'))B WHERE A.RRNO=B.RRNO(+) AND A.YEARMONTH=B.YEARMONTH(+)"
					+ "AND A.RRNO LIKE '"+rrno+"' ORDER BY A.YEARMONTH DESC";
			
			
			list = oracleEntityManager.createNativeQuery(sql).getResultList();
			for (Iterator<?> iterator = list.iterator(); iterator.hasNext();) {
				Object[] obj = (Object[]) iterator.next();
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("RRNO", obj[0]);
				map.put("MONTHYEAR", obj[1]);
				map.put("ScheduledDay", obj[2]);
				map.put("BILLDATE", obj[3]);
				map.put("BILLNO", obj[4]);
				map.put("FR", obj[5]);
				map.put("IR", obj[6]);
				map.put("UNITS", obj[7]);
				map.put("MC_Units", obj[8]);
				map.put("BM", obj[9]);
				map.put("STATUS", obj[10]);
				map.put("OB", obj[11]);
				map.put("FC", obj[12]);
				map.put("EC", obj[13]);
				map.put("FAC", obj[14]);
				map.put("PF", obj[15]);
				map.put("PFPEN", obj[16]);
				map.put("BMD", obj[17]);
				map.put("PENAMT", obj[18]);
				map.put("REBATE", obj[19]);
				map.put("ARREARS", obj[20]);
				map.put("INTE", obj[21]);
				map.put("OLDINT", obj[22]);
				map.put("TAX", obj[23]);
				map.put("NET", obj[24]);
				map.put("DRAMT", obj[25]);
				map.put("OTHERS", obj[26]);
				map.put("AUDITARR", obj[27]);
				map.put("MMD", obj[28]);
				map.put("BBC", obj[29]);
				map.put("APPEAL", obj[30]);
				map.put("SUSADJ", obj[31]);
				map.put("SUSCB", obj[32]);
				map.put("CB", obj[33]);
				map.put("COLL", obj[34]);
				map.put("DUEDATE", obj[35]);
				map.put("YEARMONTH", obj[36]);
				map.put("CLOSINGBALANCE", obj[37]);
				map.put("DL_ADJ_GIVEN", obj[38]);
				map.put("SUSOB", obj[39]);
				map.put("ELPEN", obj[40]);

				result.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<Map<String, Object>> getPaymentDetailsforrrno(String rrno, String schemaname) {
		BCITSLogger.logger.info("REPORT NAME======================>GET consumer details " + rrno + "===" + schemaname);
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		List<?> list = null;
		try {
			String sql = "SELECT RRNO, TO_CHAR(RECDATE,'DD/MM/YYYY') AS RECEIPTDATE, RECNO AS RECEIPTNO, AMOUNT, BRANCH, TOWARDS, CASHTYPE, PAYMENTMODE, BOOKNO, REASON, ENTRYDATE   FROM "
					+ schemaname + ".CASHMASTER WHERE CASHTYPE <=13 AND RRNO LIKE '" + rrno
					+ "' ORDER BY RECEIPTDATE DESC";
			list = oracleEntityManager.createNativeQuery(sql).getResultList();
			for (Iterator<?> iterator = list.iterator(); iterator.hasNext();) {
				Object[] obj = (Object[]) iterator.next();
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("RRNO", obj[0]);
				map.put("RECEIPTDATE", obj[1]);
				map.put("RECEIPTNO", obj[2]);
				map.put("AMOUNT", obj[3]);
				map.put("BRANCH", obj[4]);
				map.put("TOWARDS", obj[5]);
				map.put("CASHTYPE", obj[6]);
				map.put("PAYMENTMODE", obj[7]);
				map.put("BOOKNO", obj[8]);
				map.put("REASON", obj[9]);
				map.put("ENTRYDATE", obj[10]);

				result.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<Map<String, Object>> getDepositDetailsforrrno(String rrno, String schemaname) {
		BCITSLogger.logger.info("REPORT NAME======================>GET consumer details " + rrno + "===" + schemaname);
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		List<?> list = null;
		try {
			String sql = "SELECT RRNO, TO_CHAR(RECDATE,'DD/MM/YYYY') AS RECEIPTDATE, RECNO AS RECEIPTNO,AMOUNT, REMARKS, STATUS   FROM "
					+ schemaname + ".DEPOSITMASTER WHERE RRNO LIKE '" + rrno + "' ORDER BY RECEIPTDATE DESC";
			list = oracleEntityManager.createNativeQuery(sql).getResultList();
			for (Iterator<?> iterator = list.iterator(); iterator.hasNext();) {
				Object[] obj = (Object[]) iterator.next();
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("RRNO", obj[0]);
				map.put("RECEIPTDATE", obj[1]);
				map.put("RECEIPTNO", obj[2]);
				map.put("AMOUNT", obj[3]);
				map.put("REMARKS", obj[4]);
				map.put("STATUS", obj[5]);

				result.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<Map<String, Object>> getMeterDetailsforrrno(String rrno, String schemaname) {
		BCITSLogger.logger.info("REPORT NAME======================>GET consumer details " + rrno + "===" + schemaname);
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		List<?> list = null;
		try {
			String sql = "SELECT RRNO, TO_CHAR(FIXEDDATE,'DD/MM/YYYY') AS FIXEDDATE, SERIALNO, MAKE, CAPACITY, PHASE, DIGITS, TYPE, COVER, POSITION, REMARKS FROM "
					+ schemaname + ".METERDETAILS WHERE RRNO LIKE '" + rrno + "'";
			list = oracleEntityManager.createNativeQuery(sql).getResultList();
			for (Iterator<?> iterator = list.iterator(); iterator.hasNext();) {
				Object[] obj = (Object[]) iterator.next();
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("RRNO", obj[0]);
				map.put("FIXEDDATE", obj[1]);
				map.put("SERIALNO", obj[2]);
				map.put("MAKE", obj[3]);
				map.put("CAPACITY", obj[4]);
				map.put("PHASE", obj[5]);
				map.put("DIGITS", obj[6]);
				map.put("TYPE", obj[7]);
				map.put("COVER", obj[8]);
				map.put("POSITION", obj[9]);
				map.put("REMARKS", obj[10]);

				result.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<Map<String, Object>> getMeterChangeDetailsforrrno(String rrno, String schemaname) {
		BCITSLogger.logger.info("REPORT NAME======================>GET consumer details " + rrno + "===" + schemaname);
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		List<?> list = null;
		try {
			String sql = "SELECT RRNO, TO_CHAR(MCDATE,'DD/MM/YYYY') AS MCDATE, TO_CHAR(RELEASEDATE,'DD/MM/YYYY') AS RELEASEDATE,TO_CHAR(ENTERDATE,'DD/MM/YYYY')AS ENTERDATE, OLDSERIALNO, OLDMAKE, OLDCAPACITY,FINALREADING, NEWMAKE, NEWSERIALNO, NEWCAPACITY, INITIALREADING,MCUNITSCONSUMED  FROM "
					+ schemaname + ".METERCHANGE WHERE RRNO LIKE '" + rrno + "'";
			list = oracleEntityManager.createNativeQuery(sql).getResultList();
			for (Iterator<?> iterator = list.iterator(); iterator.hasNext();) {
				Object[] obj = (Object[]) iterator.next();
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("RRNO", obj[0]);
				map.put("MCDATE", obj[1]);
				map.put("RELEASEDATE", obj[2]);
				map.put("ENTERDATE", obj[3]);
				map.put("OLDSERIALNO", obj[4]);
				map.put("OLDMAKE", obj[5]);
				map.put("OLDCAPACITY", obj[6]);
				map.put("FINALREADING", obj[7]);
				map.put("NEWMAKE", obj[8]);
				map.put("NEWSERIALNO", obj[9]);
				map.put("NEWCAPACITY", obj[10]);
				map.put("INITIALREADING", obj[11]);
				map.put("MCUNITSCONSUMED", obj[12]);

				result.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
