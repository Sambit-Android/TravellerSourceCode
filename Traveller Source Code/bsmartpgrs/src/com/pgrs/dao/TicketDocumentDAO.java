package com.pgrs.dao;

import com.pgrs.entity.TicketDocumentsEntity;

public interface TicketDocumentDAO extends GenericDAO<TicketDocumentsEntity> {

	TicketDocumentsEntity findDocumentByDocketNo(int docketNo);

}
