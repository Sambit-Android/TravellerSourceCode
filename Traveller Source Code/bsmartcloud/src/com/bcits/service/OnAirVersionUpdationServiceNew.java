package com.bcits.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;

import com.bcits.entity.OnAirVersionUpdationEntity;
import com.bcits.entity.OnAirVersionUpdationEntityNew;

public interface OnAirVersionUpdationServiceNew extends GenericService<OnAirVersionUpdationEntityNew> {

	public List<OnAirVersionUpdationEntityNew> findAll(ModelMap model,HttpServletRequest request);
	public List<OnAirVersionUpdationEntityNew> getlatestVersion(String appname) ;
	public long checkVersionExist(String apkVersion, String remarks);
	public OnAirVersionUpdationEntityNew findByRemarks(String apkVersion,String remarks);
}
