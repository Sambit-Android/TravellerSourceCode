package com.pgrs.dao;
// default package

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.MessageSource;

import com.pgrs.entity.Designation;

public interface IDesignationDAO extends GenericDAO<Designation>
{
	List<?> findAllDnNames();
	List<?> exicuteAllActiveDesignationsQuery();
	String executeGetDnNameBasedOnId(int dptId,int dnId);
	void updateDesignationStatus(int dnId, String operation,HttpServletResponse response, MessageSource messageSource);
	public List<?> readDesignationNamesForUniqueness();
	List<Designation> findAllDesignation();
	
	/*---------------- chandra ---------------*/
	List<Designation> exicuteAllActiveDesignationsQueryForRegistration();
	public List<?> readDesignationNamesForUniqueness(int deptId);}