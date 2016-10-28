package com.pgrs.service;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import com.pgrs.entity.TicketSubCategoryEntity;

public interface TicketSubCategoryService {

	List<?> getAllActiveSubCategories(int categoryId);

	List<?> getAllSubDivisions(int divisionId);

	List<?> findAllSubCategories();

	List<String> getAllSubCategoryNames();

	void setSubCategoryStatus(int categoryId,String operation,HttpServletResponse response);

	void save(TicketSubCategoryEntity subCategoryEntity);

	void update(TicketSubCategoryEntity subCategoryEntity);

	void delete(int subCategoryId);

	List<?> getAllDivisions(int circleId);

	List<?> getAllSection(Set<Integer> subDivisionIds);

	List<?> getAllSubDivisions1(Set<Integer> divisionIds);

	List<?> getAllSubDivisionsForAndroid();

	List<?> getAllSections(int subDivisionId);

}
