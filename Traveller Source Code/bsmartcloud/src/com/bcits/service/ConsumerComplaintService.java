package com.bcits.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;

import com.bcits.entity.ConsumerComplaintEntity;

public interface ConsumerComplaintService extends GenericService<ConsumerComplaintEntity> 
{
	List<ConsumerComplaintEntity> findAll();
	public void getConsComplData(ModelMap model,HttpServletRequest request);
}
