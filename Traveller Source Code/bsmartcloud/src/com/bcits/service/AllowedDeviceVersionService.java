package com.bcits.service;

import java.util.List;

import com.bcits.entity.AllowedDeviceVersionsEntity;

public interface AllowedDeviceVersionService extends GenericService<AllowedDeviceVersionsEntity>
{
	public List<AllowedDeviceVersionsEntity> findAll();

	public Integer updateAllowedFlagStatus(Integer id, String status);

	public List<AllowedDeviceVersionsEntity> findAllVersionsToAllow();
}
