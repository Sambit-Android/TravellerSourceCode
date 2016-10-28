package com.pgrs.dao;

import java.util.List;

import com.pgrs.entity.TicketCategoryEntity;

public interface TicketCategoryDAO extends GenericDAO<TicketCategoryEntity> {

	List<?> getAllActiveCategories();

	List<?> getAllDivisions(int projectId);

	List<?> findAllCategories();

	List<String> getAllCategoryNames();

	void setCategoryStatus(int categoryId, int i);

	List<Integer> getAssignedCategoryIds(int categoryId);

	List<String> getLastCategory();

	List<?> getAllCircles(int projectId);

	List<?> getAllSubDivisions(int projectId);

	List<?> getAllSections();

}
