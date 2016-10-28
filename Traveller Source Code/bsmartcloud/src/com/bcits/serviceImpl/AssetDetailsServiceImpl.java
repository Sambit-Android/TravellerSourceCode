package com.bcits.serviceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.bcits.entity.MRMasterEntity;
import com.bcits.service.AssetDetailsService;

@Repository
public class AssetDetailsServiceImpl extends GenericServiceImpl<MRMasterEntity> implements AssetDetailsService {

	/*@Override
	public List<?> getAllAssetDetails() {
		return readAssetDetails(entityManager.createNamedQuery("MRMasterEntity.getAllAssetDetails").getResultList());
	}*/

	private List<?> readAssetDetails(List<?> resultList) {
		List<Map<String, Object>> result=new ArrayList();
		int i=1;
		for(Iterator<?> iterator=resultList.iterator();iterator.hasNext();){
			Object[] obj=(Object[]) iterator.next();
			Map<String, Object> data=new HashMap();
			//a.mrCode,a.deviceid,a.sdoCode,d.make,a.mrMaster.mrName,l.circle,l.division,l.subDivision,l.section
			
			data.put("slno",i++);
			data.put("mrCode", obj[0]);
			data.put("deviceid", obj[1]);
			data.put("sdoCode", obj[2]);
			data.put("make", obj[3]);
			data.put("mrName", obj[4]);
			data.put("circle", obj[5]);
			data.put("division", obj[6]);
			data.put("subDivision", obj[7]);
			data.put("section", obj[8]);
			result.add(data);
		}
		return result;
	}

	@Override
	public int updateMRDeviceEntity(String deviceid, String status) {
		return entityManager.createNamedQuery("MRDeviceEntity.update").setParameter("status", status).setParameter("deviceId",deviceid).executeUpdate();
	}

}
