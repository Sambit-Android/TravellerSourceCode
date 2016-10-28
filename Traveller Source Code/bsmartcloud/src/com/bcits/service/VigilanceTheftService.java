package com.bcits.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.ModelMap;

import com.bcits.entity.VigilanceTheftEntity;

public interface VigilanceTheftService extends GenericService<VigilanceTheftEntity>
{
	List<VigilanceTheftEntity> findAll();
	public void getTheftData(ModelMap model,HttpServletRequest request);
	byte[] findOnlyImage(ModelMap model, HttpServletRequest request,HttpServletResponse response, String consumer_Sc_No,String value) throws IOException;
	int updateStatus(int id, String status);
	List<VigilanceTheftEntity> findByUser( String username);
}
