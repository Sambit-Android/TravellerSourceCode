package com.pgrs.service;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.MessageSource;

import com.pgrs.entity.Department;

public interface DepartmentService {	
	
	String getDepartmentNameBasedOnId(int deptId);
	
	public List<Department> findAll();
	
	public void save(Department department);
	
	public void update(Department department);
	
	public void setDepartmentStatus(int deptId, String operation, HttpServletResponse response, MessageSource messageSource, Locale locale);
	
	public List<?> readDepartmentNames();
	
	public void deleteDepartment(int deptId);
	
	public List<?> getDepartmentNameFilter();
}
