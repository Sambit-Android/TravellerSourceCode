package com.pgrs.serviceimpl;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pgrs.dao.TicketSubCategoryDAO;
import com.pgrs.entity.TicketSubCategoryEntity;
import com.pgrs.service.TicketSubCategoryService;

@Service
public class TicketSubCategoryServiceImpl implements TicketSubCategoryService {

	@Autowired
	private TicketSubCategoryDAO ticketSubCategoryDAO;
	
	@Override
	public List<?> getAllActiveSubCategories(int categoryId) {
		
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		Map<String, Object> categoryMap = null;
		for (Iterator<?> iterator = ticketSubCategoryDAO.getAllActiveSubCategories(categoryId).iterator(); iterator.hasNext();){
				final Object[] values = (Object[]) iterator.next();
				categoryMap = new HashMap<String, Object>();
				
				categoryMap.put("subCategoryId", (Integer)values[0]);
				categoryMap.put("subCategoryName", (String)values[1]);
			
			result.add(categoryMap);
	     }
		 return result;
	}

	@Override
	public List<?> getAllSubDivisions(int divisionId) {

		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		Map<String, Object> subDivisionMap = null;
		for (Iterator<?> iterator = ticketSubCategoryDAO.getAllSubDivisions(divisionId).iterator(); iterator.hasNext();){
				final Object[] values = (Object[]) iterator.next();
				subDivisionMap = new HashMap<String, Object>();
				
				subDivisionMap.put("siteCode", (String)values[0]);
				subDivisionMap.put("subDivisionName", (String)values[1]);
				subDivisionMap.put("subId", (Integer)values[2]);
			
			result.add(subDivisionMap);
	     }
		 return result;
	}
	@Override
	public List<?> getAllSubDivisions1(Set<Integer> officeIds) {
		
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		Map<String, Object> subDivisionMap = null;
		for (Iterator<?> iterator = ticketSubCategoryDAO.getAllSubDivisions1(officeIds).iterator(); iterator.hasNext();){
			final Object[] values = (Object[]) iterator.next();
			subDivisionMap = new HashMap<String, Object>();
			
			subDivisionMap.put("siteCode", (String)values[0]);
			subDivisionMap.put("subDivisionName", (String)values[1]);
			subDivisionMap.put("subId", (Integer)values[2]);
			
			result.add(subDivisionMap);
		}
		return result;
	}
	@Override
	public List<?> getAllDivisions(int circleId) {
		
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		Map<String, Object> subDivisionMap = null;
		for (Iterator<?> iterator = ticketSubCategoryDAO.getAllDivisions(circleId).iterator(); iterator.hasNext();){
			final Object[] values = (Object[]) iterator.next();
			subDivisionMap = new HashMap<String, Object>();
			
			subDivisionMap.put("siteCode", (Integer)values[0]);
			subDivisionMap.put("divisionName", (String)values[1]);
			
			result.add(subDivisionMap);
		}
		return result;
	}
	
	@Override
	public List<?> findAllSubCategories() {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		Map<String, Object> subCategoryMap = null;
		for (Iterator<?> iterator = ticketSubCategoryDAO.findAllSubCategories().iterator(); iterator.hasNext();){
				final Object[] values = (Object[]) iterator.next();
				subCategoryMap = new HashMap<String, Object>();
				
				subCategoryMap.put("subCategoryId", (Integer)values[0]);
				subCategoryMap.put("subCategoryName", (String)values[1]);
				subCategoryMap.put("status", (Integer)values[2]);
				subCategoryMap.put("noLevels", (Integer)values[3]);
				subCategoryMap.put("categoryId", (Integer)values[4]);
				subCategoryMap.put("categoryName", (String)values[5]);
			
			result.add(subCategoryMap);
	     }
		 return result;
	}

	@Override
	public List<String> getAllSubCategoryNames() {
		return ticketSubCategoryDAO.getAllSubCategoryNames();
	}

	@Override
	public void setSubCategoryStatus(int subCategoryId,String operation,HttpServletResponse response) {
		try{
			PrintWriter out = response.getWriter();	
			if(operation.equalsIgnoreCase("activate")){
				ticketSubCategoryDAO.setSubCategoryStatus(subCategoryId,1);
				out.write("Category activated successfully");
			} else{
				List<Integer> categoryList = ticketSubCategoryDAO.getAssignedSubCategoryIds(subCategoryId);
				if(categoryList.isEmpty()){
					ticketSubCategoryDAO.setSubCategoryStatus(subCategoryId,0);
				   out.write("Category deactivated successfully");	
			    }else{
				   out.write("Cannot be deactivate,because this sub category assigned to some docket");
			    }
			}			
		}catch(Exception e){
		   e.printStackTrace();
	    }
	}

	@Override
	public void save(TicketSubCategoryEntity subCategoryEntity) {
		ticketSubCategoryDAO.save(subCategoryEntity);
	}

	@Override
	public void update(TicketSubCategoryEntity subCategoryEntity) {
		ticketSubCategoryDAO.update(subCategoryEntity);
	}

	@Override
	public void delete(int subCategoryId) {
		ticketSubCategoryDAO.delete(subCategoryId);
	}

	@Override
	public List<?> getAllSection(Set<Integer> subDivisionIds) {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		Map<String, Object> subDivisionMap = null;
		for (Iterator<?> iterator = ticketSubCategoryDAO.getAllSection(subDivisionIds).iterator(); iterator.hasNext();){
				final Object[] values = (Object[]) iterator.next();
				subDivisionMap = new HashMap<String, Object>();
				
				subDivisionMap.put("siteCode", (String)values[0]);
				subDivisionMap.put("sectionName", (String)values[1]);
			
			result.add(subDivisionMap);
	     }
		 return result;
	}

	@Override
	public List<?> getAllSubDivisionsForAndroid() {

		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		Map<String, Object> subDivisionMap = null;
		for (Iterator<?> iterator = ticketSubCategoryDAO.getAllSubDivisionsForAndroidPart().iterator(); iterator.hasNext();){
				final Object[] values = (Object[]) iterator.next();
				subDivisionMap = new HashMap<String, Object>();
				
				subDivisionMap.put("siteCode", (String)values[2]);
				subDivisionMap.put("subDivisionName", (String)values[1]);
				subDivisionMap.put("divistionCode", (int)values[0]);//Added By Raju For Android
				
			
			result.add(subDivisionMap);
	     }
		 return result;
	}

	@Override
	public List<?> getAllSections(int subDivisionId) {
		
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		Map<String, Object> subDivisionMap = null;
		for (Iterator<?> iterator = ticketSubCategoryDAO.getAllSections(subDivisionId).iterator(); iterator.hasNext();){
				final Object[] values = (Object[]) iterator.next();
				subDivisionMap = new HashMap<String, Object>();
				
				subDivisionMap.put("siteCode", (String)values[0]);
				subDivisionMap.put("sectionName", (String)values[1]);
				subDivisionMap.put("subId", (Integer)values[2]);
			
			result.add(subDivisionMap);
	     }
		 return result;
	}

}
