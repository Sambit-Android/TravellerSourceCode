package com.pgrs.dao;

import java.util.List;
import java.util.Set;

import com.pgrs.entity.TicketSubCategoryEntity;

public interface TicketSubCategoryDAO extends GenericDAO<TicketSubCategoryEntity> {

	List<?> getAllActiveSubCategories(int categoryId);

	List<?> getAllSubDivisions(int divisionId);

	List<?> findAllSubCategories();

	List<String> getAllSubCategoryNames();

	void setSubCategoryStatus(int subCategoryId, int status);

	List<Integer> getAssignedSubCategoryIds(int subCategoryId);

	List<?> getAllDivisions(int circleId);

	List<?> getAllSection(Set<Integer> subDivisionIds);

	List<?> getAllSubDivisions1(Set<Integer> officeIds);

	List<?> getAllSubDivisionsForAndroidPart();

	List<?> getAllSections(int subDivisionId);

}
