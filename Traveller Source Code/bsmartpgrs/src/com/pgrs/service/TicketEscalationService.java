package com.pgrs.service;

import java.util.List;

import com.pgrs.entity.TicketEscalationEntity;

public interface TicketEscalationService {

	List<?> findAllEscalationLevels(int subCategoryId);

	void save(TicketEscalationEntity escalationEntity);

	void update(TicketEscalationEntity escalationEntity);

	void delete(int escId);

}
