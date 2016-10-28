package com.bcits.service;

import java.util.List;

import com.bcits.entity.Device_Logs_Entity;
import com.bcits.entity.MasterDeviceStatusEntity;

public interface DeviceLogService  extends GenericService<Device_Logs_Entity> {

	public List<Device_Logs_Entity> findAll();
	
}
