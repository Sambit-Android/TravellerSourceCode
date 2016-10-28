package com.bcits.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;

import com.bcits.entity.MRDeviceEntity;

public interface MRDeviceService extends GenericService<MRDeviceEntity> {


	public List<MRDeviceEntity> findAllMRDevices(HttpServletRequest request);

	public List<String> getMatchedDeviceIds(Integer sdoCode);

	public List<String> getMatchedDeviceIdsForAllocation(Integer sdoCode);
	
	int approveDevice(String deviceid,HttpServletRequest request,ModelMap model);

	//List<MRDeviceEntity> getNotAllocatedDevice();

	public String findByDevice(String deviceid ) ;

	public void updateStatus(String deviceid, String status);

	public int updateDeviceSdocode(String deviceid, Integer sdoCode,
			String allostatus);

	int updateDeviceMaster(String deviceidPk);

	public int findByPk(String deviceid);

	public List<MRDeviceEntity> getNotAllocatedDevice(int parseInt);

	public List<Object[]> getDivisionMobCount(ModelMap model);

	public List<Object[]> getDivisionMobiles(String division, ModelMap model);

	public List<Object[]> getSectionMobiles(String sitecode, ModelMap model);

	public List<Object[]> getDivisionMobCountCesc(ModelMap model);

	public List<Object[]> getDivisionMobilesCesc(String division, String sitecode,ModelMap model);

	public List<Map<String,Object>> findAllMRDevicesMobile(String sdocode);

	String findByDeviceId(String deviceid);

	public List<String> getDeviceFromMobileMaster(String provider);
}
