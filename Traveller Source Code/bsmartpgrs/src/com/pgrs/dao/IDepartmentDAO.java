package com.pgrs.dao;
// default package

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.MessageSource;

import com.pgrs.entity.Department;

public interface IDepartmentDAO extends GenericDAO<Department> {	
	List<Department> findAll();	
	
	void updateDepartmentStatus(int deptId, String operation,HttpServletResponse response, MessageSource messageSource);
	
	String executeGetDeptNameBasedOnId(int deptId);
	
	List<?> getDepartmentNameFilter();
}