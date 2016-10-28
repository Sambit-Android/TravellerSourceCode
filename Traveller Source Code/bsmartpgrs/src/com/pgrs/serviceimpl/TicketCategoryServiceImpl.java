package com.pgrs.serviceimpl;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pgrs.dao.TicketCategoryDAO;
import com.pgrs.entity.TicketCategoryEntity;
import com.pgrs.service.TicketCategoryService;

@Service
public class TicketCategoryServiceImpl implements TicketCategoryService {

	@Autowired
	private TicketCategoryDAO ticketCategoryDAO;
	
	@Override
	public List<?> getAllActiveCategories() {
		
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		Map<String, Object> categoryMap = null;
		for (Iterator<?> iterator = ticketCategoryDAO.getAllActiveCategories().iterator(); iterator.hasNext();){
				final Object[] values = (Object[]) iterator.next();
				categoryMap = new HashMap<String, Object>();
				
				categoryMap.put("categoryId", (Integer)values[0]);
				categoryMap.put("categoryName", (String)values[1]);
			
			result.add(categoryMap);
	     }
		 return result;
	}

	@Override
	public List<?> getAllDivisions(int projectId) {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		Map<String, Object> divisionMap = null;
		for (Iterator<?> iterator = ticketCategoryDAO.getAllDivisions(projectId).iterator(); iterator.hasNext();){
				final Object[] values = (Object[]) iterator.next();
				divisionMap = new HashMap<String, Object>();
				
				divisionMap.put("divisionSiteCode", (Integer)values[0]);
				divisionMap.put("divisionName", (String)values[1]);
			
			result.add(divisionMap);
	     }
		 return result;
	}

	@Override
	public List<?> findAllCategories() {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		Map<String, Object> categoryMap = null;
		for (Iterator<?> iterator = ticketCategoryDAO.findAllCategories().iterator(); iterator.hasNext();){
				final Object[] values = (Object[]) iterator.next();
				categoryMap = new HashMap<String, Object>();
				
				categoryMap.put("categoryId", (Integer)values[0]);
				categoryMap.put("categoryName", (String)values[1]);
				categoryMap.put("status", (Integer)values[2]);
			
			result.add(categoryMap);
	     }
		 return result;
	}

	@Override
	public List<String> getAllCategoryNames() {
		return ticketCategoryDAO.getAllCategoryNames();
	}

	@Override
	public void setCategoryStatus(int categoryId,String operation,HttpServletResponse response) {
		try{
			PrintWriter out = response.getWriter();	
			if(operation.equalsIgnoreCase("activate")){
				ticketCategoryDAO.setCategoryStatus(categoryId,1);
				out.write("Category activated successfully");
			} else{
				List<Integer> categoryList = ticketCategoryDAO.getAssignedCategoryIds(categoryId);
				if(categoryList.isEmpty()){
				   ticketCategoryDAO.setCategoryStatus(categoryId,0);
				   out.write("Category deactivated successfully");	
			    }else{
				   out.write("Cannot be deactivate,because this category assigned to some docket");
			    }
			}			
		}catch(Exception e){
		   e.printStackTrace();
	    }
	}

	@Override
	public void save(TicketCategoryEntity categoryEntity) {
		List<String> lastCategory = ticketCategoryDAO.getLastCategory();
		if(lastCategory.isEmpty()){
			categoryEntity.setCategoryName("A - "+categoryEntity.getCategoryName());
		}else{
			char ch = lastCategory.get(0).charAt(0);
			categoryEntity.setCategoryName((++ch)+" - "+categoryEntity.getCategoryName());
		}
		ticketCategoryDAO.save(categoryEntity);
	}

	@Override
	public void update(TicketCategoryEntity categoryEntity) {
		ticketCategoryDAO.update(categoryEntity);
	}

	@Override
	public void delete(int categoryId) {
		ticketCategoryDAO.delete(categoryId);
	}

	@Override
	public List<?> getAllCircles(int projectId) {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		Map<String, Object> circleMap = null;
		for (Iterator<?> iterator = ticketCategoryDAO.getAllCircles(projectId).iterator(); iterator.hasNext();){
				final Object[] values = (Object[]) iterator.next();
				circleMap = new HashMap<String, Object>();
				
				circleMap.put("circleSiteCode", (Integer)values[0]);
				circleMap.put("circleName", (String)values[1]);
			
			result.add(circleMap);
	     }
		 return result;
	}

	@Override
	public List<?> getAllSubDivisions(int projectId) {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		Map<String, Object> subDivisionMap = null;
		for (Iterator<?> iterator = ticketCategoryDAO.getAllSubDivisions(projectId).iterator(); iterator.hasNext();){
				final Object[] values = (Object[]) iterator.next();
				subDivisionMap = new HashMap<String, Object>();
				
				subDivisionMap.put("subDivisionSiteCode", (Integer)values[0]);
				subDivisionMap.put("subDivisionName", (String)values[1]);
			
			result.add(subDivisionMap);
	     }
		 return result;
	}

	@Override
	public List<?> getAllSections() {

		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		Map<String, Object> subDivisionMap = null;
		for (Iterator<?> iterator = ticketCategoryDAO.getAllSections().iterator(); iterator.hasNext();){
				final Object[] values = (Object[]) iterator.next();
				subDivisionMap = new HashMap<String, Object>();
				
				subDivisionMap.put("sectionSiteCode", (String)values[0]);
				subDivisionMap.put("sectionName", (String)values[1]+" - "+(String)values[0]);
			
			result.add(subDivisionMap);
	     }
		 return result;
	}

}
