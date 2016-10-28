package com.pgrs.dao;

import java.util.List;

import com.pgrs.entity.TicketEscalationEntity;

public interface TicketEscalationDAO extends GenericDAO<TicketEscalationEntity> {

	List<?> findAllEscalationLevels(int subCategoryId);

}
