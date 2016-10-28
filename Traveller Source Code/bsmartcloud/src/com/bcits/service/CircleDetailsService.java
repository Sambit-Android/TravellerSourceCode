package com.bcits.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;

import com.bcits.entity.CircleDataUpload;
import com.bcits.entity.CircleDetails;


public interface CircleDetailsService extends GenericService<CircleDetails> 
{
	public String circleDetailsUpload(CircleDataUpload circleDataUpload, ModelMap model,HttpServletRequest request); 
}
