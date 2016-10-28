package com.bcits.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;

import com.bcits.entity.VigilanceComplaintEntity;

public interface VigilanceComplaintService extends GenericService<VigilanceComplaintEntity> 
{
	List<VigilanceComplaintEntity> findAll();
	public void getConsComplData(ModelMap model,HttpServletRequest request);
	List<VigilanceComplaintEntity> findByUser(String username);
	public int updateStatus(int id, String status) ;
}
