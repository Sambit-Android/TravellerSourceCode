package com.bcits.serviceImpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bcits.entity.BillingProgressEntity;
import com.bcits.service.MRBillingService;

@Repository
public class MRBillingServiceImpl extends GenericServiceImplOracle<BillingProgressEntity> implements MRBillingService {

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<?> getMrBillingProgress(String sdocode) {
		String query = "SELECT "
				+ "B.MRNAME,B.MRQUALIFICATION,A.SECTION,A.SITECODE,A.TOBEBILLED,A.BILLED,A.TOBEBILLED_DAY, A.BILLED_DAY,A.MRCODE,A.BILLS_ISSUED_DAY"
				+ " FROM "
				+ "(SELECT MRCODE, SECTION,SITECODE,NVL(SUM(CASE WHEN INSTSTATUS=3 THEN 1 END ),0) AS TOBEBILLED"
				+ ",NVL(SUM(CASE WHEN INSTSTATUS=3 AND BILLNO NOT IN ('0','1') THEN 1 END ),0) AS BILLED"
				+ ",NVL(SUM(CASE WHEN INSTSTATUS=3 AND TO_CHAR(RDNGDATE,'DD')=TO_CHAR(SYSDATE,'DD') THEN 1 END ),0) AS TOBEBILLED_DAY"
				+ ",NVL(SUM(CASE WHEN INSTSTATUS=3 AND BILLNO NOT IN ('0','1') AND TO_CHAR(RDNGDATE,'DD')=TO_CHAR(SYSDATE,'DD') THEN 1 END ),0) AS BILLED_DAY ,"
				+ "NVL(SUM(CASE WHEN INSTSTATUS=3 AND TO_CHAR(BILLDATE,'DD')=TO_CHAR(SYSDATE,'DD') THEN 1 END ),0) BILLS_ISSUED_DAY "
				+ "FROM MATRIX WHERE TO_CHAR(RDNGDATE,'YYYYMM')=TO_CHAR(SYSDATE,'YYYYMM') "
				+ "AND SITECODE LIKE '" + sdocode
				+ "' GROUP BY MRCODE, SECTION,SITECODE )A,(SELECT SECTION, MRCODE, MRNAME, MRQUALIFICATION FROM MRMASTER_DETAILS WHERE SITECODE LIKE '"
				+ sdocode + "')B " + "WHERE A.SECTION=B.SECTION(+) AND A.MRCODE=B.MRCODE(+)";
		return oracleEntityManager.createNativeQuery(query).getResultList();
	}

}
