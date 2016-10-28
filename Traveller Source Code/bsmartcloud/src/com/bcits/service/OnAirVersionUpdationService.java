package com.bcits.service;

import java.util.List;

import com.bcits.entity.OnAirVersionUpdationEntity;

public interface OnAirVersionUpdationService extends
		GenericService<OnAirVersionUpdationEntity> {

	public List<OnAirVersionUpdationEntity> findAll();
	
	long checkVersionExist(String version);
	public List<OnAirVersionUpdationEntity> getlatestVersion() ;
}
