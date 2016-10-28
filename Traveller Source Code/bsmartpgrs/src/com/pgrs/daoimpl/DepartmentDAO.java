package com.pgrs.daoimpl;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Repository;

import com.pgrs.dao.IDepartmentDAO;
import com.pgrs.entity.Department;


@Repository
public class DepartmentDAO extends GenericDAOImpl<Department> implements IDepartmentDAO {

	@Override
	@SuppressWarnings("unchecked")
	public List<Department> findAll()
	{	
		return readDepartment(entityManager.createNamedQuery("Department.findAll").getResultList());
	}
	@SuppressWarnings("rawtypes")
	private List readDepartment(List<?> department)
	{
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		Map<String, Object> departmentData = null;
		for (Iterator<?> iterator = department.iterator(); iterator.hasNext();)
		{
			 final Object[] values = (Object[]) iterator.next();
			 departmentData = new HashMap<String, Object>();
			 
			 departmentData.put("deptId", (Integer)values[0]);
			 departmentData.put("deptName",(String)values[1]);		
			 departmentData.put("deptDesc",(String)values[2]);
			 departmentData.put("departmentStatus", (String)values[3]);
		     result.add(departmentData); 
		 }
		 return result;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void updateDepartmentStatus(int deptId, String operation,HttpServletResponse response, MessageSource messageSource){	
		try{
			PrintWriter out = response.getWriter();	
			if(operation.equalsIgnoreCase("activate"))
			{
				entityManager.createNamedQuery("Department.UpdateStatus").setParameter("status", "Active").setParameter("deptId", deptId).executeUpdate();
				out.write("Department Activated");
			}else{
				List<Integer> userList = entityManager.createNamedQuery("Department.ActiveUsers").setParameter("deptId", deptId).getResultList();
				   if(userList.isEmpty()){
					   entityManager.createNamedQuery("Department.UpdateStatus").setParameter("status", "Inactive").setParameter("deptId", deptId).executeUpdate();
					   out.write("Department Deactivated");
				   }else{
					   out.write("Cannot be Deactivate,because this department assigned user is active");
				   }
			}			
		}
		catch(Exception e){
		   e.printStackTrace();
	    }
    }
	
	@Override
	public String executeGetDeptNameBasedOnId(int deptId) {
		
		List<String> deptNames = entityManager.createNamedQuery("Department.getDeptNameBasedOnId",String.class)
				.setParameter("deptId", deptId)
				.getResultList();	
		Iterator<String> it=deptNames.iterator();
		while(it.hasNext()){

			return (String) it.next();
		}
		return null;
	}
	@Override
	public List<?> getDepartmentNameFilter() {
		return entityManager.createNamedQuery("Department.getDepartmentNameFilter").getResultList();
	}	
}