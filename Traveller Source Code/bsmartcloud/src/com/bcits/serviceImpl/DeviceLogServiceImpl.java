package com.bcits.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bcits.entity.Device_Logs_Entity;
import com.bcits.entity.MasterDeviceStatusEntity;
import com.bcits.service.DeviceLogService;
import com.bcits.service.MasterDeviceStatusService;

@Repository
public class DeviceLogServiceImpl extends GenericServiceImpl<Device_Logs_Entity> implements DeviceLogService {
 


	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<Device_Logs_Entity> findAll()
	{
		return entityManager.createNamedQuery("MasterDeviceStatusEntity.findAll").getResultList();

	}
}
