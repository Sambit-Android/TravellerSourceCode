package com.bcits.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bcits.entity.MasterDeviceStatusEntity;
import com.bcits.service.MasterDeviceStatusService;

@Repository
public class MasterDeviceStatusServiceImpl  extends GenericServiceImpl<MasterDeviceStatusEntity> implements MasterDeviceStatusService {

	
	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<MasterDeviceStatusEntity> findAll()
	{
		return entityManager.createNamedQuery("MasterDeviceStatusEntity.findAll").getResultList();

	}
	
	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<MasterDeviceStatusEntity> getDeviceHealth(String mrCode,int sdoCode,String date)
	{
		return entityManager.createNamedQuery("MasterDeviceStatusEntity.DeviceHealth").setParameter("mrcode", mrCode).setParameter("sdoCode", sdoCode).setParameter("date",date).getResultList();

	}

	
	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public MasterDeviceStatusEntity getDeviceDetails(String deviceId)
	{
		List<MasterDeviceStatusEntity> list= entityManager.createNamedQuery("MasterDeviceStatusEntity.getDeviceDetails").setParameter("deviceId",deviceId).getResultList();
		
            if(list.size()>0)
            {
            	return (MasterDeviceStatusEntity) list.get(0);
            }
            return null;
	}
	
}
