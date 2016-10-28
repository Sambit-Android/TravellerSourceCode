package com.bcits.service;

import java.util.List;

import com.bcits.entity.AllowedDeviceVersionsEntityNew;

public interface AllowedDeviceVersionServiceNew extends GenericService<AllowedDeviceVersionsEntityNew>
{
	public List<AllowedDeviceVersionsEntityNew> findAll();

	public Integer updateAllowedFlagStatus(Integer id, String status);

	public List<AllowedDeviceVersionsEntityNew> findAllVersionsToAllow();
}
