package com.pgrs.daoimpl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.pgrs.dao.TicketDocumentDAO;
import com.pgrs.entity.TicketDocumentsEntity;

@Repository
public class TicketDocumentDAOImpl extends GenericDAOImpl<TicketDocumentsEntity> implements TicketDocumentDAO {

	@Override
	public TicketDocumentsEntity findDocumentByDocketNo(int docketNo) {
		try{
			List<TicketDocumentsEntity> docList = entityManager.createNamedQuery("TicketDocumentsEntity.findDocumentByDocketNo",TicketDocumentsEntity.class).setParameter("docketNo", docketNo).getResultList();
			return docList.get(0); 
		} catch(Exception e){
			return null;
		}
	}

}
