package com.pgrs.service;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.MessageSource;

import com.pgrs.entity.Designation;

public interface DesignationService 
{	
	public List<Designation> findAllDesignation();
	
	List<?> getAllActiveDesignations();
	
	String getDnNameBasedOnId(int dptId,int dnId);
	
	public List<?> findAll();
	
	public void save(Designation designation);
	
	public void update(Designation designation);
	
	void setDesignationStatus(int dnId, String operation,HttpServletResponse response, MessageSource messageSource,Locale locale);
	
	public List<?> readDesignationNames();
	
	public List<?> getAllActiveDesignationsForRegistration();
	
	public Designation find(int designationId);
	
	public void deleteDesignation(int dnId);

	public List<?> readDesignationNamesForUniqueness(int deptId);
}
