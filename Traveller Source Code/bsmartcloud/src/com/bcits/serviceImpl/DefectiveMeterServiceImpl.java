package com.bcits.serviceImpl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.servlet.http.HttpServletRequest;

import org.codehaus.jettison.json.JSONArray;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bcits.entity.DefectiveMeterEntity;
import com.bcits.service.DefectiveMeterService;

@Repository
public class DefectiveMeterServiceImpl extends GenericServiceImplOracle<DefectiveMeterEntity> implements  DefectiveMeterService {
	
	
	/*@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<DefectiveMeterEntity> getMobileBillingDataDefective(String listno, HttpServletRequest request) 
	{
		
		
		int lcno = Integer.valueOf(listno);
		System.out.println("lcno :"+lcno);
		@SuppressWarnings("unchecked")
		List<DefectiveMeterEntity> data = entityManager.createNamedQuery("DefectiveMeterEntity.findallbylistdefective").setParameter("listno",lcno).getResultList();

		if(data.size()>0){

			System.out.println(">>>>>>>>>>>>>>"+data);
			return data;

		}
		else{
			return null;
		}
	
		
		return null;
	}
		*/


	/*@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public String getListNoValidationResultDefective(String listno, HttpServletRequest request) 
	{
		
		
		int lcno = Integer.valueOf(listno);
		
		String listnovalidation = "LISTISNOTVALID";
		String downloadavailability = "DOWNLOADNOTAVAILABLE";
		Long a=  (Long) entityManager.createNamedQuery("DefectiveMeterEntity.findcountdefective").setParameter("listno",lcno).getSingleResult();

		if(a> 0){

			listnovalidation = "LISTISVALID";

		}


		Long b = (Long) entityManager.createNamedQuery("DefectiveMeterEntity.findavailabledownloadsdefective").setParameter("listno",lcno).getSingleResult();


		if(b>0){

			downloadavailability = "DOWNLOADAVAILABLE";
		}

		System.out.println("b :"+b);

		String result = listnovalidation + "#"+  downloadavailability;

		return result;

		
		
		
	}*/

	
	
	
	
	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public JSONArray getAllDisConnectionListDataNew(final int originalSocode, String schema, int readingmth) {
		List<Object[]> list = null;
		JSONArray approvalMap = new JSONArray();
		String query = "SELECT RRNO,CONSUMERNAME,CONSUMERADDRESS,METERSTATUS,POLENO,FEEDERCODE,TCCODE,SOCODE,PRESENTREADING,SANCTIONKW,SANCTIONHP,TARIFFDCB,LCNO,ACCOUNTID,MRCODE,STATIONCODE,LINEMANCODE,RDNGDATE,CONSUMERADDRESS1,BILLED,LATITUDE,LONGTITUTED" + " FROM "
				+ schema + ".MATRIX WHERE METERSTATUS IN ('MNR','BURNT','STICKY') AND RDNGMONTH="+readingmth+" AND RRNO NOT IN(SELECT RRNO FROM "+schema+".METERCHANGE WHERE TO_CHAR(ENTERDATE,'YYYYMM')=TO_CHAR(SYSDATE,'YYYYMM'))";
		
		
		
		
		System.out.println(query);

		
		
		list = oracleEntityManager.createNativeQuery(query).getResultList();
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			final Object[] objects = (Object[]) iterator.next();
			approvalMap.put(new HashMap<String, Object>() {
				
				
				{
					put("rrno", (objects[0] != null) ? objects[0].toString() : "NA");
					put("consumername", (objects[1] != null) ? objects[1].toString() : "NA");
					put("consumeraddress", (objects[2] != null) ? objects[2].toString() : "NA");
					put("meterstatus", (objects[3] != null) ? objects[3].toString() : "NA");
					put("poleno", (objects[4] != null) ? objects[4].toString() : "NA");
					put("federcode", (objects[5] != null) ? objects[5].toString() : "NA");
					put("tccode", (objects[6] != null) ? objects[6].toString() : "NA");
					//put("socode", (objects[7] != null) ? objects[7].toString() : "NA");
					put("socode", ""+originalSocode+"");
					put("presentreading", (objects[8] != null) ? objects[8].toString() : "NA");
					put("sactionkw", (objects[9] != null) ? objects[9].toString() : "NA");
					put("sactionhp", (objects[10] != null) ? objects[10].toString() : "NA");
					put("tariffdcb", (objects[11] != null) ? objects[11].toString() : "NA");
					put("listno", (objects[12] != null) ? objects[12].toString() : "NA");
					put("id", (objects[13] != null) ? objects[13].toString() : "NA");
					put("mrcode", (objects[14] != null) ? objects[14].toString() : "NA");
					put("stationcode", (objects[15] != null) ? objects[15].toString() : "NA");
					put("linemancode", (objects[16] != null) ? objects[16].toString() : "NA");
					put("readingdate", (objects[17] != null) ? objects[17].toString() : "NA");
					put("consumeraddress1", (objects[18] != null) ? objects[18].toString() : "NA");
					put("billed", (objects[19] != null) ? objects[19].toString() : "NA");
					put("lattitude", (objects[20] != null) ? objects[20].toString() : "NA");
					put("longitude", (objects[21] != null) ? objects[21].toString() : "NA");
					
					
					
					
					
					
					
					
					
					
					
				}
			});
		}
		
		
		System.out.println(approvalMap);
		
		
		
		return approvalMap;
	}
	
	
	



}
