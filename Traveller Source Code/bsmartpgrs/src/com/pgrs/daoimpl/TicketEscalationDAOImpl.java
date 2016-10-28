package com.pgrs.daoimpl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.pgrs.dao.TicketEscalationDAO;
import com.pgrs.entity.TicketEscalationEntity;

@Repository
public class TicketEscalationDAOImpl extends GenericDAOImpl<TicketEscalationEntity> implements TicketEscalationDAO {

	@Override
	public List<?> findAllEscalationLevels(int subCategoryId) {
		return entityManager.createNamedQuery("TicketEscalationEntity.findAllEscalationLevels").setParameter("subCategoryId", subCategoryId).getResultList();
	}

}
