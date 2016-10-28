package com.pgrs.service;

import com.pgrs.entity.TicketDocumentsEntity;

public interface TicketDocumentService {

	void save(TicketDocumentsEntity document);

	TicketDocumentsEntity findDocumentByDocketNo(int docketNo);

}
