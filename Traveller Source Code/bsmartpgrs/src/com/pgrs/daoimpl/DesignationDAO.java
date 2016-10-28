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

import com.pgrs.dao.IDesignationDAO;
import com.pgrs.entity.Designation;

@Repository
public class DesignationDAO extends GenericDAOImpl<Designation> implements IDesignationDAO {
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Designation> findAllDesignation() 
	{
		return entityManager.createNamedQuery("Designation.findAllDesignation").getResultList();
	}
	
	@Override
	public List<?> exicuteAllActiveDesignationsQuery() {
		return entityManager.createNamedQuery("Designation.getAllActiveDesignations").getResultList();
	}
	
	@Override
	public List<?> findAllDnNames()
	{
		return readAllDesignation(entityManager.createNamedQuery("Designation.findAllDnNames").getResultList());
	}
	@SuppressWarnings({ "rawtypes" })
	private List readAllDesignation(List<?> designation)
	{
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		Map<String, Object> designationData = null;
		for (Iterator<?> iterator = designation.iterator(); iterator.hasNext();)
		{
			final Object[] values = (Object[]) iterator.next();
			 designationData = new HashMap<String, Object>();
			 
			 designationData.put("dnId", (Integer)values[0]);
			 designationData.put("dnName",(String)values[1]);		
		     result.add(designationData); 
		 }
		 return result;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void updateDesignationStatus(int dnId, String operation,HttpServletResponse response, MessageSource messageSource){	
		try{
			PrintWriter out = response.getWriter();	
			if(operation.equalsIgnoreCase("activate"))
			{
				entityManager.createNamedQuery("Designation.UpdateStatus").setParameter("status", "Active").setParameter("dnId", dnId).executeUpdate();
				out.write("Designation Activated");
			}else{
			   List<Integer> userList = entityManager.createNamedQuery("Designation.ActiveUsers").setParameter("dnId", dnId).getResultList();
			   if(userList.isEmpty()){
				   entityManager.createNamedQuery("Designation.UpdateStatus").setParameter("status", "Inactive").setParameter("dnId", dnId).executeUpdate();
				   out.write("Designation Deactivated");
			   }else{
				   out.write("Cannot be Deactivate,because this designation assigned user is active");
			   }
			}			
		}catch(Exception e){
		   e.printStackTrace();
	    }
    }

	@Override
	public List<?> readDesignationNamesForUniqueness()
	{
		 return entityManager.createNamedQuery("Designation.readDesignationNames").getResultList();	
	}

	@Override
	public String executeGetDnNameBasedOnId(int dptId, int dnId) {
		
		List<String> dnIds = entityManager.createNamedQuery("Designation.getDnNameBasedOnId",String.class)
				.setParameter("dnId", dnId)
				.getResultList();	
		Iterator<String> it=dnIds.iterator();
		while(it.hasNext()){

			return (String) it.next();
		}
		return null;
	}

	@Override
	public List<Designation> exicuteAllActiveDesignationsQueryForRegistration() {
		return entityManager.createNamedQuery("Designation.getAllActiveDesignationsForRegistration",Designation.class).getResultList();
	}
	
	@Override
	public List<?> readDesignationNamesForUniqueness(int deptId)
	{
		 return entityManager.createNamedQuery("Designation.getUniqueDesignationNames").setParameter("deptId", deptId).getResultList();	
	}
	
}