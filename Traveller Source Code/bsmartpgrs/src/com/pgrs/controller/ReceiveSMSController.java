package com.pgrs.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pgrs.entity.HelpDeskTicketEntity;
import com.pgrs.service.ReceiveSmsService;

@Controller

public class ReceiveSMSController {
	
Logger logger=Logger.getLogger(ReceiveSMSController.class);
	
	@Autowired
	private ReceiveSmsService receiveSmsService;
	
	@RequestMapping(value = "/recieveSMS", method ={ RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody List<HelpDeskTicketEntity> receiveSmsFromSender(HttpServletRequest request,HttpServletResponse response) throws InterruptedException{
		
		//String number=request.getParameter("number");
		logger.info("Inside the sms receiving part!!!");
		String senderMobileNumber=request.getParameter("who");
		logger.info("Mobile number  "+senderMobileNumber);
		String message=request.getParameter("what");
		logger.info("Message text "+message);
		if(senderMobileNumber.contains("91")){
			senderMobileNumber=senderMobileNumber.substring(2);
		}
		
		logger.info(",,, Mobile==>"+senderMobileNumber +",,,Message==>"+message);
		HelpDeskTicketEntity ticketEntity=new HelpDeskTicketEntity();
		receiveSmsService.receiveSmsInfo(ticketEntity,senderMobileNumber,message.trim());
		
		return null;
	}
	
	@RequestMapping(value = "/recieveSMSTL", method ={ RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody List<HelpDeskTicketEntity> receiveSmsDetailsFromSender(HttpServletRequest request,HttpServletResponse response) throws InterruptedException{
		
		logger.info("Inside the sms receiving part!!!");
		String message=null;
		String senderMobileNumber=request.getParameter("sender");
		logger.info("Mobile number  "+senderMobileNumber);
		String keyword=request.getParameter("keyword");
		String content=request.getParameter("content");
		if(content!=null){
			message=new StringBuilder(keyword).toString()+new StringBuilder(content).toString();
		}else{
			message=new StringBuilder(keyword).toString();
		}
		logger.info("Message text "+keyword+content);
		if(senderMobileNumber.contains("91")){
			senderMobileNumber=senderMobileNumber.substring(2);
		}
		
		logger.info("Mobile==>"+senderMobileNumber +"Message==>"+keyword+content);
		HelpDeskTicketEntity ticketEntity=new HelpDeskTicketEntity();
		receiveSmsService.receiveSmsInfo(ticketEntity,senderMobileNumber,message.trim());
		
		return null;
	}
	
}
