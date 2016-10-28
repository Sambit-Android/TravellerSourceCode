package com.pgrs.daoimpl;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Repository;

import com.pgrs.dao.TicketSubCategoryDAO;
import com.pgrs.entity.TicketSubCategoryEntity;

@Repository
public class TicketSubCategoryDAOImpl extends GenericDAOImpl<TicketSubCategoryEntity> implements TicketSubCategoryDAO {

	@Override
	public List<?> getAllActiveSubCategories(int categoryId) {
		return entityManager.createNamedQuery("TicketSubCategoryEntity.getAllActiveSubCategories").setParameter("categoryId",categoryId).getResultList();
	}

	@Override
	public List<?> getAllSubDivisions(int divisionId) {
		return entityManager.createNamedQuery("TicketSubCategoryEntity.getAllSubDivisions").setParameter("divisionId",divisionId).getResultList();
	}
	@Override
	public List<?> getAllSubDivisions1(Set<Integer> divisionIds) {
		return entityManager.createNamedQuery("TicketSubCategoryEntity.getAllSubDivisions1").setParameter("divisionIds",divisionIds).getResultList();
	}
	@Override
	public List<?> getAllDivisions(int circleId) {
		return entityManager.createNamedQuery("TicketSubCategoryEntity.getAllDivisions").setParameter("circleId",circleId).getResultList();
	}
	
	@Override
	public List<?> findAllSubCategories() {
		return entityManager.createNamedQuery("TicketSubCategoryEntity.findAllSubCategories").getResultList();
	}

	@Override
	public List<String> getAllSubCategoryNames() {
		return entityManager.createNamedQuery("TicketSubCategoryEntity.getAllSubCategoryNames",String.class).getResultList();
	}

	@Override
	public void setSubCategoryStatus(int subCategoryId, int status) {
		entityManager.createNamedQuery("TicketSubCategoryEntity.setSubCategoryStatus").setParameter("subCategoryId", subCategoryId).setParameter("status", status).executeUpdate();
	}

	@Override
	public List<Integer> getAssignedSubCategoryIds(int subCategoryId) {
		return entityManager.createNamedQuery("TicketSubCategoryEntity.getAssignedSubCategoryIds",Integer.class).setParameter("subCategoryId", subCategoryId).getResultList();
	}

	@Override
	public List<?> getAllSection(Set<Integer> subDivisionIds) {
		return entityManager.createNamedQuery("TicketSubCategoryEntity.getAllSection").setParameter("subDivisionIds",subDivisionIds).getResultList();
	}

	@Override
	public List<?> getAllSubDivisionsForAndroidPart() {
		return entityManager.createNamedQuery("TicketSubCategoryEntity.getAllSubDivisionsForAndroidPart").getResultList();
	}

	@Override
	public List<?> getAllSections(int subDivisionId) {
		return entityManager.createNamedQuery("TicketSubCategoryEntity.getAllSections").setParameter("subDivisionId",subDivisionId).getResultList();
	}

}
