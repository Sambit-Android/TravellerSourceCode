package com.pgrs.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pgrs.dao.TicketDocumentDAO;
import com.pgrs.entity.TicketDocumentsEntity;
import com.pgrs.service.TicketDocumentService;

@Service
public class TicketDocumentServiceImpl implements TicketDocumentService {
	
	@Autowired
	private TicketDocumentDAO ticketDocumentDAO;

	@Override
	public void save(TicketDocumentsEntity document) {
		ticketDocumentDAO.save(document);
	}

	@Override
	public TicketDocumentsEntity findDocumentByDocketNo(int docketNo) {
		return ticketDocumentDAO.findDocumentByDocketNo(docketNo);
	}

}
