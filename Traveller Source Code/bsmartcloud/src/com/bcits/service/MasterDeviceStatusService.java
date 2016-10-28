package com.bcits.service;

import java.util.List;

import com.bcits.entity.MasterDeviceStatusEntity;

public interface MasterDeviceStatusService extends GenericService<MasterDeviceStatusEntity>  {
public List<MasterDeviceStatusEntity> findAll();

List<MasterDeviceStatusEntity> getDeviceHealth(String mrCode,int sdoCode,String date);

MasterDeviceStatusEntity getDeviceDetails(String deviceId);
}
