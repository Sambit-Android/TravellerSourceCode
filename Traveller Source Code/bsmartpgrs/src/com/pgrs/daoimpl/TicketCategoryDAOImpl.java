package com.pgrs.daoimpl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.pgrs.dao.TicketCategoryDAO;
import com.pgrs.entity.TicketCategoryEntity;

@Repository
public class TicketCategoryDAOImpl extends GenericDAOImpl<TicketCategoryEntity> implements TicketCategoryDAO {

	@Override
	public List<?> getAllActiveCategories() {
		return entityManager.createNamedQuery("TicketCategoryEntity.getAllActiveCategories").getResultList();
	}

	@Override
	public List<?> getAllDivisions(int projectId) {
		return entityManager.createNamedQuery("TicketCategoryEntity.getAllDivisions").setParameter("projectId", projectId).getResultList();
	}

	@Override
	public List<?> findAllCategories() {
		return entityManager.createNamedQuery("TicketCategoryEntity.findAllCategories").getResultList();
	}

	@Override
	public List<String> getAllCategoryNames() {
		return entityManager.createNamedQuery("TicketCategoryEntity.getAllCategoryNames",String.class).getResultList();
	}

	@Override
	public void setCategoryStatus(int categoryId, int status) {
		entityManager.createNamedQuery("TicketCategoryEntity.setCategoryStatus").setParameter("categoryId", categoryId).setParameter("status", status).executeUpdate();
	}

	@Override
	public List<Integer> getAssignedCategoryIds(int categoryId) {
		return entityManager.createNamedQuery("TicketCategoryEntity.getAssignedCategoryIds",Integer.class).setParameter("categoryId", categoryId).getResultList();
	}

	@Override
	public List<String> getLastCategory() {
		return entityManager.createNamedQuery("TicketCategoryEntity.getLastCategory",String.class).setMaxResults(1).getResultList();
	}

	@Override
	public List<?> getAllCircles(int projectId) {
		return entityManager.createNamedQuery("TicketCategoryEntity.getAllCircles").setParameter("projectId", projectId).getResultList();
	}

	@Override
	public List<?> getAllSubDivisions(int projectId) {
		return entityManager.createNamedQuery("TicketCategoryEntity.getAllSubDivisions").setParameter("projectId", projectId).getResultList();
	}

	@Override
	public List<?> getAllSections() {
		return entityManager.createNamedQuery("LocationsEntity.getAllSections").getResultList();
	}

}
