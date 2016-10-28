package com.pgrs.serviceimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.WordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.pgrs.dao.IDepartmentDAO;
import com.pgrs.entity.Department;
import com.pgrs.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService
{
	@Autowired
	private IDepartmentDAO iDepartmentDAO;

	@Override
	public List<Department> findAll()
	{	
		return iDepartmentDAO.findAll();
	}

	@Override
	public void save(Department department)
	{
	   department.setDeptName(WordUtils.capitalizeFully((String) department.getDeptName()));
	   iDepartmentDAO.save(department);	
	}

	@Override
	public void update(Department department) 
	{
	   	iDepartmentDAO.update(department);
	}
	
	@Override
	public void setDepartmentStatus(int deptId, String operation,HttpServletResponse response, MessageSource messageSource,Locale locale) 
	{	
		iDepartmentDAO.updateDepartmentStatus(deptId,operation,response,messageSource);
	}

	@Override
	public String getDepartmentNameBasedOnId(int deptId) {
		return iDepartmentDAO.executeGetDeptNameBasedOnId(deptId);
	}

	@Override
	public List<?> readDepartmentNames() {		
		return iDepartmentDAO.executeSimpleQuery("SELECT d.deptName FROM Department d");
	}

	@Override
	public void deleteDepartment(int deptId) {
		iDepartmentDAO.delete(deptId);
	}

	@Override
	public List<?> getDepartmentNameFilter() {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		Map<String, Object> departmentMap = null;
		for (Iterator<?> iterator = iDepartmentDAO.getDepartmentNameFilter().iterator(); iterator.hasNext();){
				final Object[] values = (Object[]) iterator.next();
				departmentMap = new HashMap<String, Object>();
				
				departmentMap.put("deptId", (Integer)values[0]);
				departmentMap.put("deptName", (String)values[1]);
			
			result.add(departmentMap);
	     }
		 return result;
	}
}
