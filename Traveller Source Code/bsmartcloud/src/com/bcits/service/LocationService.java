package com.bcits.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;

import com.bcits.entity.LocationEntity;

public interface LocationService extends GenericService<LocationEntity> 
{
	List<LocationEntity> findAll();	
	int getLocationCode(int level,int digitsCode);
	List<LocationEntity> getLocationNamesForLevels(String locType,int locCode,int digitCode);
	void getLocationData(ModelMap model,HttpServletRequest request);
	public void getTopMostLevelData(HttpServletRequest request,ModelMap model);
	public List getChildNodes(String locationCode,String region,int level);
	public List getZerothNodeAndRegion(String locationCode) ;	
	public List<LocationEntity> getLocationNamest(int digitsCode,String category);
	
	 void getLocationCategory(ModelMap model);
	 public List<LocationEntity> getLocationNames(String locationCategory,ModelMap model);
	 List<LocationEntity> getLocationData(int locationcode,ModelMap model);
	 //List<LocationEntity> findLocName(String locationName,int level);
	 public List<LocationEntity> findLocName(String locationName,int loccode,int level);
	List<String> getLocationTypes(HttpServletRequest request);
	List<LocationEntity> findSiteCode(int operation,int level);
	List<LocationEntity> getAllSdoLevel4(HttpServletRequest request);
	List<LocationEntity> checkLevelLocation(int locationcode);
	int deleteLocation(String locCode);
	
	public List<LocationEntity> findSdoAndSubdiv();
	public List<LocationEntity> findSubdiv();
	//public List<Object> getAllSiteCodes();
}