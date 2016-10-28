package com.pgrs.serviceimpl;

import java.sql.Timestamp;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pgrs.dao.ReceiveSmsDAO;
import com.pgrs.dao.TicketTransactionDAO;
import com.pgrs.entity.HelpDeskTicketEntity;
import com.pgrs.entity.TicketTransactionEntity;
import com.pgrs.service.ReceiveSmsService;
import com.pgrs.util.SendDocketInfoSMS;
import com.pgrs.util.SmsCredentialsDetailsBean;

@Service
public class ReceiveSmsServiceImpl implements ReceiveSmsService {

	Logger logger = Logger.getLogger(ReceiveSmsServiceImpl.class);

	@Autowired
	public ReceiveSmsDAO receiveSmsDao;
	
	@Autowired
	public TicketTransactionDAO ticketTransactionDAO;

	@Override
	public void receiveSmsInfo(HelpDeskTicketEntity helpDeskTicketEntity,
			String mobile, String message) throws InterruptedException {

		helpDeskTicketEntity.setDocketSource("Sms");
		helpDeskTicketEntity.setConsumerMobileNo(Long.parseLong(mobile));
		helpDeskTicketEntity.setDocketCreatedDt(new Timestamp(new Date().getTime()));
		helpDeskTicketEntity.setDocketUpdatedDt(new Timestamp(new Date().getTime()));
		helpDeskTicketEntity.setInvalidFlag("No");
		helpDeskTicketEntity.setRemarks(message.replaceFirst("CESCCESC", ""));
		receiveSmsDao.save(helpDeskTicketEntity);
		
		TicketTransactionEntity ticketTransactionEntity = new TicketTransactionEntity();
		ticketTransactionEntity.setDocketNumber(helpDeskTicketEntity.getDocketNumber());
		ticketTransactionEntity.setAction("Consumer sent sms");
		ticketTransactionEntity.setDocketSource("Sms");
		ticketTransactionEntity.setConsumerMobileNo(helpDeskTicketEntity.getConsumerMobileNo());
		ticketTransactionEntity.setDocketCreatedDt(helpDeskTicketEntity.getDocketCreatedDt());
		ticketTransactionEntity.setDocketUpdatedDt(helpDeskTicketEntity.getDocketUpdatedDt());
		ticketTransactionEntity.setRemarks(message.replaceFirst("CESCCESC", ""));
		ticketTransactionDAO.save(ticketTransactionEntity);
		
		int docketNo =helpDeskTicketEntity.getDocketNumber();
		SmsCredentialsDetailsBean smsCredentialsDetailsBean = new SmsCredentialsDetailsBean();
		
		String messageOfficial = "Consumer, complaint with docket number ("+ docketNo+") has been registered on "
				+" "+ new Timestamp(new java.util.Date().getTime())+" "
				+ "and shall be resolved very soon."+System.lineSeparator();
		
		String username="Sir/Madam";
		smsCredentialsDetailsBean.setNumber(mobile);
		smsCredentialsDetailsBean.setUserName(username);
		smsCredentialsDetailsBean.setMessage(messageOfficial);

		new Thread(new SendDocketInfoSMS(smsCredentialsDetailsBean)).start();

	}

}
