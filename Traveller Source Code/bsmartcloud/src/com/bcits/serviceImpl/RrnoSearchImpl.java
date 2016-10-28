package com.bcits.serviceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

import com.bcits.entity.HHBM_ConsumerEntity;
import com.bcits.entity.RrnoSearchEntity;
import com.bcits.service.HHBM_ConsumerService;
import com.bcits.service.RrnoSearchService;
import com.bcits.utility.BCITSLogger;

@Repository
public class RrnoSearchImpl extends GenericServiceImplOracle<RrnoSearchEntity> implements RrnoSearchService {

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Map<String, Object>> getRRnumberlist(String inputtype, String inputVariable, String schemaname, String year) 
	{
		BCITSLogger.logger.info("REPORT NAME======================>GET RR NO according to " + inputtype+"==="+schemaname);
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		List<?> list = null;
		if (inputtype.equalsIgnoreCase("rrno")) {
			try {
				String sql = "select RRNO, CONSUMERNAME AS NAME, TARIFFDCB AS TARIFF, (CASE WHEN INSTSTATUS =1 THEN 'LD' WHEN INSTSTATUS =0 THEN 'PD' WHEN INSTSTATUS =2 THEN 'TD' WHEN INSTSTATUS =3 THEN 'LIVE' WHEN (INSTSTATUS =4 OR INSTSTATUS =-4)  THEN 'BILLSTOP' END )	AS INSTSTATUS, VILLAGENAME, LATITUDE, LONGTITUTED from "+schemaname+".master where rrno like '%"+inputVariable+"%' ORDER BY RRNO";
				list = oracleEntityManager.createNativeQuery(sql).getResultList();
				for (Iterator<?> iterator = list.iterator(); iterator.hasNext();) {
					Object[] obj = (Object[]) iterator.next();
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("RRNO", obj[0]);
					map.put("CONSUMERNAME", obj[1]);
					map.put("TARIFFDCB", obj[2]);
					map.put("INSTSTATUS", obj[3]);
					map.put("VILLAGENAME", obj[4]);
					map.put("LATITUDE", obj[5]);
					map.put("LONGTITUTED", obj[6]);
					result.add(map);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (inputtype.equalsIgnoreCase("name")) {
			try {
				String sql = "select RRNO, CONSUMERNAME AS NAME, TARIFFDCB AS TARIFF, (CASE WHEN INSTSTATUS =1 THEN 'LD' WHEN INSTSTATUS =0 THEN 'PD' WHEN INSTSTATUS =2 THEN 'TD' WHEN INSTSTATUS =3 THEN 'LIVE' WHEN (INSTSTATUS =4 OR INSTSTATUS =-4)  THEN 'BILLSTOP' END ) AS INSTSTATUS, VILLAGENAME, LATITUDE, LONGTITUTED from "+schemaname+".master where UPPER(CONSUMERNAME) like '%"+inputVariable.toUpperCase()+"%' ORDER BY RRNO";
				list = oracleEntityManager.createNativeQuery(sql).getResultList();
				for (Iterator<?> iterator = list.iterator(); iterator.hasNext();) {
					Object[] obj = (Object[]) iterator.next();
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("RRNO", obj[0]);
					map.put("CONSUMERNAME", obj[1]);
					map.put("TARIFFDCB", obj[2]);
					map.put("INSTSTATUS", obj[3]);
					map.put("VILLAGENAME", obj[4]);
					map.put("LATITUDE", obj[5]);
					map.put("LONGTITUTED", obj[6]);
					result.add(map);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (inputtype.equalsIgnoreCase("village")) {
			try {
				String sql = "select RRNO, CONSUMERNAME AS NAME, TARIFFDCB AS TARIFF, (CASE WHEN INSTSTATUS =1 THEN 'LD' WHEN INSTSTATUS =0 THEN 'PD' WHEN INSTSTATUS =2 THEN 'TD' WHEN INSTSTATUS =3 THEN 'LIVE' WHEN (INSTSTATUS =4 OR INSTSTATUS =-4)  THEN 'BILLSTOP' END ) AS INSTSTATUS, VILLAGENAME, LATITUDE, LONGTITUTED from "+schemaname+".master where UPPER(VILLAGENAME) like '%"+inputVariable.toUpperCase()+"%' ORDER BY RRNO";
				list = oracleEntityManager.createNativeQuery(sql).getResultList();
				for (Iterator<?> iterator = list.iterator(); iterator.hasNext();) {
					Object[] obj = (Object[]) iterator.next();
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("RRNO", obj[0]);
					map.put("CONSUMERNAME", obj[1]);
					map.put("TARIFFDCB", obj[2]);
					map.put("INSTSTATUS", obj[3]);
					map.put("VILLAGENAME", obj[4]);
					map.put("LATITUDE", obj[5]);
					map.put("LONGTITUTED", obj[6]);
					result.add(map);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// TODO Auto-generated method stub
		return result;
	}
}
