package com.pgrs.serviceimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.pgrs.dao.IDesignationDAO;
import com.pgrs.entity.Designation;
import com.pgrs.service.DesignationService;

@Service
public class DesignationServiceImpl implements DesignationService {	
	
	@Autowired
	private IDesignationDAO iDesignationDAO;

	@Override
	public List<Designation> findAllDesignation(){
		return iDesignationDAO.findAllDesignation();
	}
	
	@Override
	public List<?> getAllActiveDesignations(){
		
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		Map<String, Object> designationMap = null;
		for (Iterator<?> iterator = iDesignationDAO.exicuteAllActiveDesignationsQuery().iterator(); iterator.hasNext();){
				final Object[] values = (Object[]) iterator.next();
				designationMap = new HashMap<String, Object>();
				
				designationMap.put("dnId", (Integer)values[0]);
				designationMap.put("dnName", (String)values[1]);
				designationMap.put("deptId", (Integer)values[2]);
			
			result.add(designationMap);
	     }
		 return result;
	}

	@Override
	public List<?> findAll() {
		
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		Map<String, Object> designationMap = null;
		for (Iterator<?> iterator = iDesignationDAO.findAllDesignation().iterator(); iterator.hasNext();){
				final Object[] values = (Object[]) iterator.next();
				designationMap = new HashMap<String, Object>();
				
				designationMap.put("dnId", (Integer)values[0]);
				designationMap.put("dnName", (String)values[1]);
				designationMap.put("dnDescription", (String)values[2]);
				designationMap.put("designationStatus", (String)values[3]);
				designationMap.put("deptName", (String)values[4]);
				designationMap.put("deptId", (Integer)values[5]);
			
			result.add(designationMap);
	     }
		return result;
	}
	
	@Override
	public void save(Designation designation) {
		iDesignationDAO.save(designation);
	}
	
	@Override
	public void update(Designation designation){
		iDesignationDAO.update(designation);
	}
	
	@Override
	public void setDesignationStatus(int dnId, String operation,HttpServletResponse response, MessageSource messageSource,Locale locale){
		iDesignationDAO.updateDesignationStatus(dnId,operation,response,messageSource);
	}

	@Override
	public List<?> readDesignationNames(){
		return iDesignationDAO.readDesignationNamesForUniqueness();
	}

	@Override
	public String getDnNameBasedOnId(int dptId, int dnId) {
		
		return iDesignationDAO.executeGetDnNameBasedOnId(dptId,dnId);
	}

	@SuppressWarnings("serial")
	@Override
	public List<?> getAllActiveDesignationsForRegistration() {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>(); 
	    for (final Designation record : iDesignationDAO.exicuteAllActiveDesignationsQueryForRegistration()) 
	    {
	        result.add(new HashMap<String, Object>() 
	        {{
		        put("dnName", record.getDnName());
		        put("dnId", record.getDnId() );
	        }});
	    }
	    return result;
	}

	@Override
	public Designation find(int designationId) {
		return iDesignationDAO.find(designationId);
	}

	@Override
	public void deleteDesignation(int dnId) {
		iDesignationDAO.delete(dnId);
	}
	
	@Override
	public List<?> readDesignationNamesForUniqueness(int deptId){
		return iDesignationDAO.readDesignationNamesForUniqueness(deptId);
	}
}
