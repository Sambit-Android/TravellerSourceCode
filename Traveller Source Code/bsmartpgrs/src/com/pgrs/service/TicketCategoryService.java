package com.pgrs.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.pgrs.entity.TicketCategoryEntity;

public interface TicketCategoryService {

	List<?> getAllActiveCategories();

	List<?> getAllDivisions(int projectId);

	List<?> findAllCategories();

	List<String> getAllCategoryNames();

	void setCategoryStatus(int categoryId,String operation,HttpServletResponse response);

	void save(TicketCategoryEntity categoryEntity);

	void update(TicketCategoryEntity categoryEntity);

	void delete(int categoryId);

	List<?> getAllCircles(int projectId);

	List<?> getAllSubDivisions(int projectId);

	List<?> getAllSections();

}
