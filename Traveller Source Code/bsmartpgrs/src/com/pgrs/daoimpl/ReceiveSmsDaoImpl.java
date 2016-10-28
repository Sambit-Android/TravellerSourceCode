package com.pgrs.daoimpl;

import org.springframework.stereotype.Repository;

import com.pgrs.dao.ReceiveSmsDAO;
import com.pgrs.daoimpl.GenericDAOImpl;
import com.pgrs.entity.HelpDeskTicketEntity;

@Repository
public class ReceiveSmsDaoImpl extends GenericDAOImpl<HelpDeskTicketEntity> implements ReceiveSmsDAO{


}
