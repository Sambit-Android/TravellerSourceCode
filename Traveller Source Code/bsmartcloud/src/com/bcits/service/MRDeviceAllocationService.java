package com.bcits.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;

import com.bcits.entity.MRDeviceAllocationEntity;

public interface MRDeviceAllocationService extends
		GenericService<MRDeviceAllocationEntity> {

	public List<MRDeviceAllocationEntity> findAllMRDeviceAllocations(HttpServletRequest request,ModelMap model);
	public List<MRDeviceAllocationEntity> findSdoCodeByMrcode(String mrCode);
  
	boolean checkAllocated(HttpServletRequest request,String mrcode,String deviceid);
	public List<Object> findBySdoCode(String sdoCode);
	public int deleteDevice(String deviceidPk);
	List<MRDeviceAllocationEntity> findAllMRDeviceAllocations1(
			HttpServletRequest request, String sdocode, ModelMap model);
	public List<Object[]> getDeviceWiseReport(ModelMap model, String parameter);
	public List<MRDeviceAllocationEntity> findByDeviceId(String deviceid);
}
