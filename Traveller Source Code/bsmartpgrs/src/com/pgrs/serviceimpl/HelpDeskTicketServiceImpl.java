package com.pgrs.serviceimpl;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.hibernate.Hibernate;
import org.joda.time.DateTime;
import org.joda.time.Hours;
import org.joda.time.Period;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.pgrs.core.dao.MasterGenericDAO;
import com.pgrs.dao.EscalatedComplaintsDAO;
import com.pgrs.dao.HelpDeskTicketDAO;
import com.pgrs.dao.TicketCategoryDAO;
import com.pgrs.dao.TicketSubCategoryDAO;
import com.pgrs.dao.TicketTransactionDAO;
import com.pgrs.entity.EscalatedComplaintsEntity;
import com.pgrs.entity.HelpDeskTicketEntity;
import com.pgrs.entity.ProjectHeirarchyEntity;
import com.pgrs.entity.TicketEscalationEntity;
import com.pgrs.entity.TicketTransactionEntity;
import com.pgrs.service.HelpDeskTicketService;
import com.pgrs.util.EmailCredentialsDetailsBean;
import com.pgrs.util.HelpDeskMailSender;
import com.pgrs.util.SendTicketInfoSMS;
import com.pgrs.util.SendTicketInfoSMSToConsumer;
import com.pgrs.util.SessionData;
import com.pgrs.util.SmsCredentialsDetailsBean;

@Service
public class HelpDeskTicketServiceImpl implements HelpDeskTicketService {
	
	@Autowired
	private HelpDeskTicketDAO helpDeskTicketDAO; 
	
	@Autowired
	private TicketTransactionDAO ticketTransactionDAO; 
	
	@Autowired
	private TicketCategoryDAO ticketCategoryDAO;
	
	@Autowired
	private TicketSubCategoryDAO ticketSubCategoryDAO;
	
	@Autowired
	private EscalatedComplaintsDAO escalatedComplaintsDAO; 

	@Autowired
	private MasterGenericDAO masterGenericDAO;
	@Override
	public String saveTicketInfo(HelpDeskTicketEntity ticketEntity) {

		ticketEntity.setDocketCreatedDt(new Timestamp(new Date().getTime()));
		ticketEntity.setDocketUpdatedDt(new Timestamp(new Date().getTime()));
		
		Set<String> assignmentTypeSet = new HashSet<>();
		assignmentTypeSet.add("Assigned");
		
		List<TicketEscalationEntity> escList = helpDeskTicketDAO.getIntialLevelDesignation(ticketEntity.getSubCategoryId(),assignmentTypeSet);
		
		List<?> userNamesList = helpDeskTicketDAO.getUserLoginNamesBasedOnDesignation(escList.get(0).getDnId());
		/*if(userNamesList.isEmpty()){
			ProjectHeirarchyEntity parentEntitySubDivision = helpDeskTicketDAO.getParentProjectHeirarchyEntityBasedOnSiteCode(ticketEntity.getSiteCode());
			userNamesList = helpDeskTicketDAO.getUserLoginNamesBasedOnDesignation(escList.get(0).getDnId(),parentEntitySubDivision.getSiteCode());
			if(userNamesList.isEmpty()){
				ProjectHeirarchyEntity parentEntityDivision = helpDeskTicketDAO.getParentProjectHeirarchyEntityBasedOnSiteCode(parentEntitySubDivision.getSiteCode());
				userNamesList = helpDeskTicketDAO.getUserLoginNamesBasedOnDesignation(escList.get(0).getDnId(),parentEntityDivision.getSiteCode());
				if(userNamesList.isEmpty()){
					ProjectHeirarchyEntity parentEntityCircle = helpDeskTicketDAO.getParentProjectHeirarchyEntityBasedOnSiteCode(parentEntityDivision.getSiteCode());
					userNamesList = helpDeskTicketDAO.getUserLoginNamesBasedOnDesignation(escList.get(0).getDnId(),parentEntityCircle.getSiteCode());
					if(userNamesList.isEmpty()){
						ProjectHeirarchyEntity parentEntityZone = helpDeskTicketDAO.getParentProjectHeirarchyEntityBasedOnSiteCode(parentEntityCircle.getSiteCode());
						userNamesList = helpDeskTicketDAO.getUserLoginNamesBasedOnDesignation(escList.get(0).getDnId(),parentEntityZone.getSiteCode());
						if(userNamesList.isEmpty()){
							ProjectHeirarchyEntity parentEntityProject = helpDeskTicketDAO.getParentProjectHeirarchyEntityBasedOnSiteCode(parentEntityZone.getSiteCode());
							userNamesList = helpDeskTicketDAO.getUserLoginNamesBasedOnDesignation(escList.get(0).getDnId(),parentEntityProject.getSiteCode());
						}
					}
				}
			}
			
		}*/
		
		Object[] values = (Object[])userNamesList.get(0);
		ticketEntity.setAssignedTo((String)values[0]);
		ticketEntity.setOfficerName((String)values[1]);
		ticketEntity.setDocketStatus(1);
		ticketEntity.setEscLevel(escList.get(0).getLevel());
		ticketEntity.setEscFlag("No");
		ticketEntity.setInvalidFlag("No");
		
		int escTimeLines;
		escTimeLines = escList.get(0).getUrbanTimeLines();

		Calendar calender = Calendar.getInstance();
		calender.setTime(new Date());
		calender.add(Calendar.HOUR, escTimeLines);
		Timestamp actualEstDate = new Timestamp(calender.getTime().getTime());
		 
		ticketEntity.setEstResolveTime(actualEstDate);
		
		helpDeskTicketDAO.save(ticketEntity);
		sendMailAndSmsCodeForConsumer(ticketEntity);
		sendMailAndSmsCodeForOfficial(ticketEntity);
		
		TicketTransactionEntity ticketTransactionEntity = new TicketTransactionEntity();
		ticketTransactionEntity.setAction("Helpdesk Person Created Ticket");
		ticketTransactionSaveCode(ticketEntity,ticketTransactionEntity);
		return "Mobile Created Ticket";
	}
	
	@Async
	private void ticketTransactionSaveCode(HelpDeskTicketEntity ticketEntity,TicketTransactionEntity ticketTransactionEntity){
		
		ticketTransactionEntity.setDocketNumber(ticketEntity.getDocketNumber());
		ticketTransactionEntity.setConsumerName(ticketEntity.getConsumerName());
		ticketTransactionEntity.setDocketSource(ticketEntity.getDocketSource());
		ticketTransactionEntity.setDocketCreatedDt(ticketEntity.getDocketCreatedDt());
		ticketTransactionEntity.setConsumerMobileNo(ticketEntity.getConsumerMobileNo());
		ticketTransactionEntity.setCategoryId(ticketEntity.getCategoryId());
		ticketTransactionEntity.setSubCategoryId(ticketEntity.getSubCategoryId());
		ticketTransactionEntity.setConsumerEmailId(ticketEntity.getConsumerEmailId());
		ticketTransactionEntity.setFacebookId(ticketEntity.getFacebookId());
		ticketTransactionEntity.setUserName(ticketEntity.getUserName());
		ticketTransactionEntity.setSiteCode(ticketEntity.getSiteCode());
		ticketTransactionEntity.setDocketUpdatedDt(ticketEntity.getDocketUpdatedDt());
		ticketTransactionEntity.setLandMark(ticketEntity.getLandMark());
		ticketTransactionEntity.setRrNumber(ticketEntity.getRrNumber());
		ticketTransactionEntity.setEstResolveTime(ticketEntity.getEstResolveTime());
		ticketTransactionEntity.setFeedBackRating(ticketEntity.getFeedBackRating());
		ticketTransactionEntity.setFeedBackComments(ticketEntity.getFeedBackComments());
		ticketTransactionEntity.setResolvedDate(ticketEntity.getResolvedDate());
		ticketTransactionEntity.setDocketSummary(ticketEntity.getDocketSummary());
		ticketTransactionEntity.setAssignedTo(ticketEntity.getAssignedTo());
		ticketTransactionEntity.setDocketStatus(ticketEntity.getDocketStatus());
		ticketTransactionEntity.setAlternativeMobileNo(ticketEntity.getAlternativeMobileNo());
		ticketTransactionEntity.setAddress(ticketEntity.getAddress());
		ticketTransactionEntity.setEscFlag(ticketEntity.getEscFlag());
		ticketTransactionEntity.setEscLevel(ticketEntity.getEscLevel());
		ticketTransactionEntity.setRemarks(ticketEntity.getRemarks());
		ticketTransactionEntity.setOfficerName(ticketEntity.getOfficerName());
		ticketTransactionEntity.setInvalidFlag(ticketEntity.getInvalidFlag());
		
		ticketTransactionDAO.save(ticketTransactionEntity);
		
	}
	
	@Async
	private void sendMailAndSmsCodeForOfficial(HelpDeskTicketEntity ticketEntity){
		
		EmailCredentialsDetailsBean emailCredentialsDetailsBean = null;
		try {
			emailCredentialsDetailsBean = new EmailCredentialsDetailsBean();
		} catch (Exception e) {
			e.printStackTrace();
		}
		SmsCredentialsDetailsBean smsCredentialsDetailsBean = new SmsCredentialsDetailsBean();
		
		Object[] officialDetails = helpDeskTicketDAO.getOfficialDetailsBasedOnLoginName(ticketEntity.getAssignedTo());

		int docketNo = ticketEntity.getDocketNumber();
		String officialName = (String)officialDetails[0];
		String officialContactNo = (String)officialDetails[1];
		String officialEmail = (String)officialDetails[2];
		String categoryName = ticketCategoryDAO.find(ticketEntity.getCategoryId()).getCategoryName();	
		String subCategoryName = ticketSubCategoryDAO.find(ticketEntity.getSubCategoryId()).getSubCategoryName();
		String consumerName = ticketEntity.getUserName();
		
		String categorySms = "";
		if(categoryName.length()>35){
			categorySms = categoryName.substring(0, 35);
		} else{
			categorySms = categoryName;
		}
		
		String consumerSms = "";
		if(consumerName.length()>10){
			consumerSms = consumerName.substring(0, 10);
		}else{
			consumerSms = consumerName;
		}
		
		String messageOfficial = docketNo+"(Consumer:"+consumerSms+",Mobile:"+ticketEntity.getConsumerMobileNo()+",Ctg:"+categorySms+") has been registered and assigned to you";
		smsCredentialsDetailsBean.setNumber(officialContactNo);
		smsCredentialsDetailsBean.setUserName(officialName);
		smsCredentialsDetailsBean.setMessage(messageOfficial);
			
		String messageContentForOfficial = "<html>"						   
							+ "<style type=\"text/css\">"
							+ "table.hovertable {"
							+ "font-family: verdana,arial,sans-serif;"
							+ "font-size:11px;"
							+ "color:#333333;"
							+ "border-width: 1px;"
							+ "border-color: #999999;"
							+ "border-collapse: collapse;"
							+ "}"
							+ "table.hovertable th {"
							+ "background-color:#c3dde0;"
							+ "border-width: 1px;"
							+ "padding: 8px;"
							+ "border-style: solid;"
							+ "border-color: #394c2e;"
							+ "}"
							+ "table.hovertable tr {"
							+ "background-color:#88ab74;"
							+ "}"
							+ "table.hovertable td {"
							+ "border-width: 1px;"
							+ "padding: 8px;"
							+ "border-style: solid;"
							+ "border-color: #394c2e;"
							+ "}"
							+ "</style>"
							+ "<h2  align=\"center\"  style=\"background-color:#88ab74;\">BCITS PGRS Services</h2> <br /> Dear "+officialName+", <br/> <br/> "
							+"This email is to inform you that ticket with ticket number "+ticketEntity.getDocketNumber()+" has been registered and assigned to you.<br>Kindly click on the below link to access it.<br><a href=http://"+ emailCredentialsDetailsBean.getTrackComplaintOfficial()+">click to login</a><br><br><br> Ticket Details are:<br>Ticket Number : "+docketNo+"<br>Ticket Created Date : "+new SimpleDateFormat("dd/MM/yyyy hh:mm a").format(ticketEntity.getDocketCreatedDt())+"<br> SLA Date for Resolving : "+new SimpleDateFormat("dd/MM/yyyy hh:mm a").format(ticketEntity.getEstResolveTime())+"<br> Category : "+categoryName+"<br> Sub Category : "+subCategoryName+" </b><br/>"
							+"Respond as early as possible.<br>"
							+"<br/><br/><br/> Please do not reply to this mail as this is an automated mail service."
							+"</body></html>";
					
		emailCredentialsDetailsBean.setToAddressEmail(officialEmail);
		emailCredentialsDetailsBean.setMessageContent(messageContentForOfficial);
		emailCredentialsDetailsBean.setMailSubject("This mail regarding new ticket creation");
			
		Set<String> assignmentTypeSet = new HashSet<>();
		assignmentTypeSet.add("Assigned");
		assignmentTypeSet.add("Notified");
				
		List<TicketEscalationEntity> escList = helpDeskTicketDAO.getIntialLevelDesignation(ticketEntity.getSubCategoryId(),assignmentTypeSet);
				
		for (TicketEscalationEntity ticketEscalationEntity : escList) {
					
			if(ticketEscalationEntity.getAssignmentType().equals("Assigned")){
					
				if(ticketEscalationEntity.getNotificationType().equals("All")){
					officialsSmsCode(smsCredentialsDetailsBean);
					officialsEmailCode(emailCredentialsDetailsBean);
				} else if(ticketEscalationEntity.getNotificationType().equals("Sms")){
					officialsSmsCode(smsCredentialsDetailsBean);
				} else if(ticketEscalationEntity.getNotificationType().equals("Email")){
					officialsEmailCode(emailCredentialsDetailsBean);
				}
			} else if (ticketEscalationEntity.getAssignmentType().equals("Notified")){
				
				List<?> userNamesList = helpDeskTicketDAO.getUserLoginNamesBasedOnDesignation(ticketEscalationEntity.getDnId());
				if(userNamesList.isEmpty()){
					//ProjectHeirarchyEntity parentEntity = helpDeskTicketDAO.getParentProjectHeirarchyEntityBasedOnSiteCode(ticketEntity.getSiteCode());
					userNamesList = helpDeskTicketDAO.getUserLoginNamesBasedOnDesignation(ticketEscalationEntity.getDnId());
				}		
						
				if(!userNamesList.isEmpty()){
				 for (Iterator<?> iterator = userNamesList.iterator(); iterator.hasNext();){
							
					final Object[] values = (Object[]) iterator.next();
					Object[] stationOfficerData = helpDeskTicketDAO.getOfficialDetailsBasedOnLoginName((String)values[0]);
							
					String stationOfficerName = "";
					if((String)stationOfficerData[0]!=null){
					stationOfficerName = (String)stationOfficerData[0];
					}
							
					String stationOfficerContactNo = "";
					if((String)stationOfficerData[1]!=null){
					stationOfficerContactNo = (String)stationOfficerData[1];
					}
							
					String stationOfficerEmail = "";
					if((String)stationOfficerData[2]!=null){
						stationOfficerEmail = (String)stationOfficerData[2];
					}
					
					String categorySmsService = "";
					if(categoryName.length()>20){
						categorySmsService = categoryName.substring(0, 20);
					} else{
						categorySmsService = categoryName;
					}
					
					String consumerSmsService = "";
					if(consumerName.length()>8){
						consumerSmsService = consumerName.substring(0, 8);
					}else{
						consumerSmsService = consumerName;
					}
					
					String officialNameService = "";
					if(officialName.length()>8){
						officialNameService = officialName.substring(0, 8);
					}else{
						officialNameService = officialName;
					}
							
					String messageStationOfficer = docketNo+"(Consumer:"+consumerSmsService+",Mobile:"+ticketEntity.getConsumerMobileNo()+",Ctg:"+categorySmsService+")has been registered and assigned to "+officialNameService+"-"+officialContactNo;
							
					if(ticketEscalationEntity.getNotificationType().equals("All")){
								
						smsCredentialsDetailsBean.setUserName(stationOfficerName);
						smsCredentialsDetailsBean.setNumber(stationOfficerContactNo);
						smsCredentialsDetailsBean.setMessage(messageStationOfficer);
							
						emailCredentialsDetailsBean.setToAddressEmail(stationOfficerEmail);
						
						officialsSmsCode(smsCredentialsDetailsBean);
						officialsEmailCode(emailCredentialsDetailsBean);
					} else if(ticketEscalationEntity.getNotificationType().equals("Sms")){
								
							smsCredentialsDetailsBean.setUserName(stationOfficerName);
							smsCredentialsDetailsBean.setNumber(stationOfficerContactNo);
							smsCredentialsDetailsBean.setMessage(messageStationOfficer);
							officialsSmsCode(smsCredentialsDetailsBean);
					} else if(ticketEscalationEntity.getNotificationType().equals("Email")){
							emailCredentialsDetailsBean.setToAddressEmail(stationOfficerEmail);
							officialsEmailCode(emailCredentialsDetailsBean);
							}
						}
					  }
					}
				}
				
	}
	
	@Async
	private void sendMailAndSmsCodeForConsumer(HelpDeskTicketEntity ticketEntity){
		
		EmailCredentialsDetailsBean emailCredentialsDetailsBean = null;
		try {
			emailCredentialsDetailsBean = new EmailCredentialsDetailsBean();
		} catch (Exception e) {
			e.printStackTrace();
		}
		SmsCredentialsDetailsBean smsCredentialsDetailsBean = new SmsCredentialsDetailsBean();
		
		
		String consumerName = ticketEntity.getConsumerName();
		//String userName = ticketEntity.getUserName();
		int docketNo = ticketEntity.getDocketNumber();
		String consumerMailId = ticketEntity.getConsumerEmailId();
		long consumerMobileNo = ticketEntity.getConsumerMobileNo();
		
		String messageConsumer = docketNo+" has been registered on "+new SimpleDateFormat("dd/MM/yyyy hh:mm a").format(ticketEntity.getDocketCreatedDt())+".You can check your Ticket status at "+emailCredentialsDetailsBean.getTrackComplaintConsumer()+" or by contacting our call center "+emailCredentialsDetailsBean.getTollFreeNumber()+".Thank you";		
					
		String messageContentForConsumer = "<html>"						   
									+ "<style type=\"text/css\">"
									+ "table.hovertable {"
									+ "font-family: verdana,arial,sans-serif;"
									+ "font-size:11px;"
									+ "color:#333333;"
									+ "border-width: 1px;"
									+ "border-color: #999999;"
									+ "border-collapse: collapse;"
									+ "}"
									+ "table.hovertable th {"
									+ "background-color:#c3dde0;"
									+ "border-width: 1px;"
									+ "padding: 8px;"
									+ "border-style: solid;"
									+ "border-color: #394c2e;"
									+ "}"
									+ "table.hovertable tr {"
									+ "background-color:#88ab74;"
									+ "}"
									+ "table.hovertable td {"
									+ "border-width: 1px;"
									+ "padding: 8px;"
									+ "border-style: solid;"
									+ "border-color: #394c2e;"
									+ "}"
									+ "</style>"
									+ "<h2  align=\"center\"  style=\"background-color:#88ab74;\">BCITS PGRS Services.</h2> <br /> Dear "+consumerName+", <br/> <br/> "
									+"This is an email from BCITS to inform you that,based on your request a ticket with ticket number "+ticketEntity.getDocketNumber()+" has been registered on "+new SimpleDateFormat("dd/MM/yyyy hh:mm a").format(ticketEntity.getDocketCreatedDt())+" and shall be resolved on or before "+new SimpleDateFormat("dd/MM/yyyy hh:mm a").format(ticketEntity.getEstResolveTime())+". Kindly use the below link to track your complaint with the above mentioned ticket number.</b><br/><br/>"
									+"<a href=http://"+ emailCredentialsDetailsBean.getTrackComplaintConsumer()+">click to login</a><br><br><br>"
									+"<br/>Please do not reply to this mail as this is an automated mail service."
									+"</body></html>";
				
				//For Consumer
				emailCredentialsDetailsBean.setToAddressEmail(consumerMailId);
				emailCredentialsDetailsBean.setMessageContent(messageContentForConsumer);
				emailCredentialsDetailsBean.setMailSubject("This mail regarding new docket creation");
				
				smsCredentialsDetailsBean.setNumber(""+consumerMobileNo);
				smsCredentialsDetailsBean.setUserName(consumerName);
				smsCredentialsDetailsBean.setMessage(messageConsumer);
				
				consumerEmailAndMailCode(emailCredentialsDetailsBean,smsCredentialsDetailsBean);
				
	}
	
	private void officialsEmailCode(EmailCredentialsDetailsBean emailCredentialsDetailsBean){
		
		new Thread(new HelpDeskMailSender(emailCredentialsDetailsBean)).start();
	}
	
	private void officialsSmsCode(SmsCredentialsDetailsBean smsCredentialsDetailsBean){
		
		new Thread(new SendTicketInfoSMS(smsCredentialsDetailsBean)).start();
	}
	
	private void consumerEmailAndMailCode(EmailCredentialsDetailsBean emailCredentialsDetailsBean,SmsCredentialsDetailsBean smsCredentialsDetailsBean){
		
		new Thread(new HelpDeskMailSender(emailCredentialsDetailsBean)).start();
		new Thread(new SendTicketInfoSMSToConsumer(smsCredentialsDetailsBean)).start();
	}

	@Override
	public List<Map<String, Object>> searchDocketNumber(int docketNumber,String sitecode) {
		
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		 Map<String, Object> docketMap = null;
		 for (Iterator<?> iterator = helpDeskTicketDAO.searchDocketNumber(docketNumber,sitecode).iterator(); iterator.hasNext();)
			{
				final Object[] values = (Object[]) iterator.next();
				docketMap = new HashMap<String, Object>();
				
				docketMap.put("docketNumber", (int)values[0]);
				if(((int)values[1])==1){
					docketMap.put("docketStatus", "Registered&Assigned");
					docketMap.put("docketUpdatedDt", "");
				}else if(((int)values[1])==2){
					docketMap.put("docketStatus", "On Hold");
					docketMap.put("docketUpdatedDt", "");
				}else if(((int)values[1])==3){
					docketMap.put("docketStatus", "Resolved");
					docketMap.put("docketUpdatedDt", "");
				}else if(((int)values[1])==4){
					docketMap.put("docketStatus", "Reopened");
					docketMap.put("docketUpdatedDt", new SimpleDateFormat("dd/MM/yyyy hh:mm a").format((Timestamp)values[12]));
				}
				docketMap.put("categoryName", (String)values[2]);
				docketMap.put("subCategoryName", (String)values[3]);
				docketMap.put("urName", (String)values[4]);
				docketMap.put("userName", (String)values[5]);
				docketMap.put("docketCreatedDt", new SimpleDateFormat("dd/MM/yyyy hh:mm a").format((Timestamp)values[6]));
				try{
					docketMap.put("resolvedDate", new SimpleDateFormat("dd/MM/yyyy hh:mm a").format((Timestamp)values[7]));
				}catch(Exception e){
					docketMap.put("resolvedDate", "");
				}
				
				docketMap.put("docketSummary", (String)values[8]);
				
				try{
					docketMap.put("estResolveTime", new SimpleDateFormat("dd/MM/yyyy hh:mm a").format((Timestamp)values[9]));
				}catch(Exception e){
					docketMap.put("estResolveTime", "");
				}		
				
				docketMap.put("designation", (String)values[10]);
				docketMap.put("docketSource", (String)values[15]);
				docketMap.put("consumerName", (String)values[16]);
				if(values[17]!=null){
					docketMap.put("consumerMobileNo", (Long)values[17]);
				} else{
					docketMap.put("consumerMobileNo", "");
				}
				docketMap.put("consumerEmailId", (String)values[18]);
				docketMap.put("section", (String)values[19]);
			
			    result.add(docketMap);
		 }
		 return result;
	}

	@Override
	public int getTicketsCountBasedOnStatus(Set<Integer> statusSet, String urLoginName) {
		return helpDeskTicketDAO.getTicketsCountBasedOnStatus(statusSet,urLoginName).intValue();
	}

	@Override
	public List<?> docketDetailsRead(String loginName,Set<Integer> statusSet) {
		
		 List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		 Map<String, Object> docketMap = null;
		 for (Iterator<?> iterator = helpDeskTicketDAO.docketDetailsRead(loginName,statusSet).iterator(); iterator.hasNext();)
			{
				final Object[] values = (Object[]) iterator.next();
				docketMap = new HashMap<String, Object>();
				
				docketMap.put("docketNumber", (int)values[0]);
				if(((int)values[1])==0){
					docketMap.put("docketStatus", "Pending for Registration");
				}else if(((int)values[1])==1){
					docketMap.put("docketStatus", "Registered&Assigned");
				}else if(((int)values[1])==2){
					docketMap.put("docketStatus", "On Hold");
				}else if(((int)values[1])==3){
					docketMap.put("docketStatus", "Resolved");
				}else if(((int)values[1])==4){
					docketMap.put("docketStatus", "Reopened");
				}
				docketMap.put("docketSource", (String)values[2]);
				docketMap.put("consumerName", (String)values[3]);
				docketMap.put("consumerMobileNo", (Long)values[4]);
				docketMap.put("rrNumber", (String)values[5]);
				docketMap.put("address", (String)values[6]);
				docketMap.put("categoryName", (String)values[7]);
				docketMap.put("subCategoryName", (String)values[8]);
				docketMap.put("remarks", (String)values[9]);
				docketMap.put("userName", (String)values[10]);
				docketMap.put("docketCreatedDt", new SimpleDateFormat("dd/MM/yyyy hh:mm a").format((Timestamp)values[11]));
				try{
					docketMap.put("resolvedDate", new SimpleDateFormat("dd/MM/yyyy hh:mm a").format((Timestamp)values[12]));
				}catch(Exception e){
					docketMap.put("resolvedDate", "");
				}
				
				docketMap.put("docketSummary", (String)values[13]);
				docketMap.put("divisionName", (String)values[14]);
				docketMap.put("subDivisionName", (String)values[15]);
				
				if(((Timestamp)values[11])!=null){
					if(((Timestamp)values[16])==null){
						docketMap.put("estResolveDate", "");
						docketMap.put("timePending", "");
						docketMap.put("totalTimeTaken", "");
					}else{
						docketMap.put("estResolveDate", new SimpleDateFormat("dd/MM/yyyy hh:mm a").format((Timestamp)values[16]));
						DateTime dt1 = new DateTime(new Timestamp(new Date().getTime()));
						DateTime dt2 = new DateTime((Timestamp)values[16]);
						int hours = Hours.hoursBetween(dt1, dt2).getHours() % 24;
						docketMap.put("timePending", hours+" Hrs");
						
						DateTime dt11 = new DateTime((Timestamp)values[11]);
						DateTime dt22 = new DateTime((Timestamp)values[12]);
						int totalHours = Hours.hoursBetween(dt11, dt22).getHours() % 24;	
						docketMap.put("totalTimeTaken", totalHours+" Hrs");
						
					}
				}else{
					docketMap.put("estResolveDate", "");
					docketMap.put("timePending", "");
					docketMap.put("totalTimeTaken", "");
				}
				
			result.add(docketMap);
	     }
		 return result;
	}

	@Override
	public void updateDocket(HelpDeskTicketEntity ticketEntity, HttpSession session) {
		
		ticketEntity.setDocketUpdatedDt(new Timestamp(new Date().getTime()));
		
		helpDeskTicketDAO.update(ticketEntity);
		
		if(ticketEntity.getDocketStatus()==3){
			sendMailAndSmsClosedTicketCode(ticketEntity);
		}else if(ticketEntity.getDocketStatus()==4){
			sendMailAndSmsClosedTicketCode(ticketEntity);
		}else if(ticketEntity.getInvalidFlag().equals("Yes")){
			sendMailAndSmsClosedTicketCode(ticketEntity);
		}
		
		TicketTransactionEntity ticketTransactionEntity = new TicketTransactionEntity();
		ticketTransactionEntity.setAction("Official Changed Docket status");
		ticketEntity.setEscFlag("No");
		String userName = (String)session.getAttribute("userId");
		ticketEntity.setUserName(userName);
		ticketTransactionSaveCode(ticketEntity,ticketTransactionEntity);
	}
	
	private void sendMailAndSmsClosedTicketCode(HelpDeskTicketEntity ticketEntity){
		
		String docketStatus = "";
		if(ticketEntity.getDocketStatus()==2){
			docketStatus = "On Hold";
		}else if(ticketEntity.getDocketStatus()==3){
			docketStatus = "Resolved";
		}else if(ticketEntity.getDocketStatus()==4){
			docketStatus = "Reopened";
		}else if(ticketEntity.getInvalidFlag().equals("Yes")){
			docketStatus = "marked as Invalid";
		}
				
		
		EmailCredentialsDetailsBean emailCredentialsDetailsBean = null;
		try {
			emailCredentialsDetailsBean = new EmailCredentialsDetailsBean();
		} catch (Exception e) {
			e.printStackTrace();
		}
		SmsCredentialsDetailsBean smsCredentialsDetailsBean = new SmsCredentialsDetailsBean();
		
		//Object[] officialDetails = helpDeskTicketDAO.getOfficialDetailsBasedOnLoginName(ticketEntity.getAssignedTo());
		
		String consumerName = ticketEntity.getConsumerName();
		//String userName = ticketEntity.getUserName();
		int docketNo = ticketEntity.getDocketNumber();
		String consumerMailId = ticketEntity.getConsumerEmailId();
		long consumerMobileNo = ticketEntity.getConsumerMobileNo();
		
		/*String officialName = (String)officialDetails[0];
		String officialContactNo = (String)officialDetails[1];
		String officialEmail = (String)officialDetails[2];*/
		
		String messageConsumer = docketNo+" registered by you on "+new SimpleDateFormat("dd/MM/yyyy hh:mm a").format(ticketEntity.getDocketCreatedDt())+" has been "+docketStatus+".Thank you for contacting us";
		String messageContentForConsumer = "<html>"						   
									+ "<style type=\"text/css\">"
									+ "table.hovertable {"
									+ "font-family: verdana,arial,sans-serif;"
									+ "font-size:11px;"
									+ "color:#333333;"
									+ "border-width: 1px;"
									+ "border-color: #999999;"
									+ "border-collapse: collapse;"
									+ "}"
									+ "table.hovertable th {"
									+ "background-color:#c3dde0;"
									+ "border-width: 1px;"
									+ "padding: 8px;"
									+ "border-style: solid;"
									+ "border-color: #394c2e;"
									+ "}"
									+ "table.hovertable tr {"
									+ "background-color:#88ab74;"
									+ "}"
									+ "table.hovertable td {"
									+ "border-width: 1px;"
									+ "padding: 8px;"
									+ "border-style: solid;"
									+ "border-color: #394c2e;"
									+ "}"
									+ "</style>"
									+ "<h2  align=\"center\"  style=\"background-color:#88ab74;\">CESC PGRS Services.</h2> <br /> Dear "+consumerName+", <br/> <br/> "
									+" Complaint with docket number "+docketNo+" registered by you on "+new SimpleDateFormat("dd/MM/yyyy hh:mm a").format(ticketEntity.getDocketCreatedDt())+" has been "+docketStatus+".</b><br/><br/>"
									+"<br/> Please do not reply to this mail as this is an automated mail service. "
									+"</body></html>";
				
				//For Consumer
				emailCredentialsDetailsBean.setToAddressEmail(consumerMailId);
				emailCredentialsDetailsBean.setMessageContent(messageContentForConsumer);
				emailCredentialsDetailsBean.setMailSubject("This mail regarding "+docketNo+" docket "+docketStatus);
				
				smsCredentialsDetailsBean.setNumber(""+consumerMobileNo);
				smsCredentialsDetailsBean.setUserName(consumerName);
				smsCredentialsDetailsBean.setMessage(messageConsumer);
				
				new Thread(new HelpDeskMailSender(emailCredentialsDetailsBean)).start();
				new Thread(new SendTicketInfoSMSToConsumer(smsCredentialsDetailsBean)).start();
	}

	@Override
	public List<Map<String, Object>> searchDocketHistory(int docketNumber) {
		
		int serialNo = 1;
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		 Map<String, Object> docketMap = null;
		 for (Iterator<?> iterator = helpDeskTicketDAO.searchDocketHistory(docketNumber).iterator(); iterator.hasNext();)
			{
			 final Object[] values = (Object[]) iterator.next();
				docketMap = new HashMap<String, Object>();
				
				docketMap.put("serialNo", serialNo);
				docketMap.put("docketCreatedDt", new SimpleDateFormat("dd/MM/yyyy hh:mm a").format((Timestamp)values[0]));
				if(((int)values[1])==0){
					docketMap.put("docketStatus", "Pending for Registration");
				}else if(((int)values[1])==1){
					docketMap.put("docketStatus", "Registered&Assigned");
				}else if(((int)values[1])==2){
					docketMap.put("docketStatus", "On Hold");
				}else if(((int)values[1])==3){
					docketMap.put("docketStatus", "Resolved");
				} else if(((int)values[1])==4){
					docketMap.put("docketStatus", "Reopened");
				}
				docketMap.put("action", (String)values[2]);
				
				if(((String)values[6])==null||((String)values[6]).equals("")){
					docketMap.put("personName", (String)values[3]);
					docketMap.put("userName", "");
					docketMap.put("actionBy", "Consumer");
					docketMap.put("office", "");
					docketMap.put("designation", "");
				}else{
					docketMap.put("personName", (String)values[9]);
					docketMap.put("userName", (String)values[6]);
					docketMap.put("actionBy", "Official");
					docketMap.put("office", (String)values[11]);
					docketMap.put("designation", (String)values[10]);
				}
				
				if(((String)values[12])==null &&((String)values[12]).equals("")){
					docketMap.put("officialMobileNo", "");
				}else{
					docketMap.put("officialMobileNo", (String)values[12]);
				}
				/*if(((String)values[5])==null||((String)values[5]).equals("")){
					docketMap.put("consumerEmailId", "");
				}else{
					docketMap.put("consumerEmailId", (String)values[5]);
				}*/
				if(((String)values[8])==null || ((String)values[8]).equals("")){
					docketMap.put("remarks", "");
				}else{
					docketMap.put("remarks", (String)values[8]);
				}
				docketMap.put("docketUpdatedDt", new SimpleDateFormat("dd/MM/yyyy hh:mm a").format((Timestamp)values[13]));
			
			result.add(docketMap);
			serialNo++;
	     }
		 return result;
	}

	@Override
	public List<Map<String, Object>> searchDocketEscHistory(int docketNumber) {
		
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		 Map<String, Object> docketMap = null;
		 int serialNo = 1;
		 for (Iterator<?> iterator = helpDeskTicketDAO.searchDocketEscHistory(docketNumber).iterator(); iterator.hasNext();)
			{
				final Object[] values = (Object[]) iterator.next();
				docketMap = new HashMap<String, Object>();
				
				docketMap.put("serialNo", serialNo);
				docketMap.put("docketCreatedDt", new SimpleDateFormat("dd/MM/yyyy hh:mm a").format((Timestamp)values[0]));
				docketMap.put("escalatedDate", new SimpleDateFormat("dd/MM/yyyy hh:mm a").format((Timestamp)values[1]));
				docketMap.put("escLevel", (Integer)values[2]);
				docketMap.put("fromUserData", (String)values[3]+" - "+(String)values[4]+" - "+(String)values[5]+" - "+(String)values[6]+" - "+(String)values[7]);
				docketMap.put("toUserData", (String)values[8]+" - "+(String)values[9]+" - "+(String)values[10]+" - "+(String)values[11]);
				
			
			result.add(docketMap);
			serialNo++;				
	     }
		 return result;
	}

	@Override
	public int getPendingTicketsCount(int status) {
		return helpDeskTicketDAO.getPendingTicketsCount(status).intValue();
	}

	@Override
	public int getescalatedTicketsCountBasedOnLogin(String loginName) {
		return helpDeskTicketDAO.getescalatedTicketsCountBasedOnLogin(loginName).intValue();
	}

	@Override
	public List<Map<String, Object>> pendingForRegistrationTickets() {
		
		 List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		 Map<String, Object> docketMap = null;
		 for (Iterator<?> iterator = helpDeskTicketDAO.pendingForRegistrationTickets().iterator(); iterator.hasNext();){
			 final Object[] values = (Object[]) iterator.next();
				docketMap = new HashMap<String, Object>();
				
				docketMap.put("docketNumber", (int)values[0]);
				if(((int)values[1])==1){
					docketMap.put("docketStatus", "Registered&Assigned");
					docketMap.put("docketUpdatedDt", "");
				}else if(((int)values[1])==2){
					docketMap.put("docketStatus", "On Hold");
					docketMap.put("docketUpdatedDt", "");
				}else if(((int)values[1])==3){
					docketMap.put("docketStatus", "Resolved");
					docketMap.put("docketUpdatedDt", "");
				}else if(((int)values[1])==4){
					docketMap.put("docketStatus", "Reopened");
					docketMap.put("docketUpdatedDt", new SimpleDateFormat("dd/MM/yyyy hh:mm a").format((Timestamp)values[12]));
				}
				docketMap.put("categoryName", (String)values[2]);
				docketMap.put("subCategoryName", (String)values[3]);
				docketMap.put("urName", (String)values[4]);
				docketMap.put("userName", (String)values[5]);
				docketMap.put("docketCreatedDt", new SimpleDateFormat("dd/MM/yyyy hh:mm a").format((Timestamp)values[6]));
				try{
					docketMap.put("resolvedDate", new SimpleDateFormat("dd/MM/yyyy hh:mm a").format((Timestamp)values[7]));
				}catch(Exception e){
					docketMap.put("resolvedDate", "");
				}
				
				docketMap.put("docketSummary", (String)values[8]);
				
				try{
					docketMap.put("estResolveTime", new SimpleDateFormat("dd/MM/yyyy hh:mm a").format((Timestamp)values[9]));
				}catch(Exception e){
					docketMap.put("estResolveTime", "");
				}		
				
				docketMap.put("designation", (String)values[10]);
			
			    result.add(docketMap);
			}
		 return result;
	}

	@Override
	public void update(HelpDeskTicketEntity ticketEntity) {
		
		ticketEntity.setDocketCreatedDt(new Timestamp(new Date().getTime()));
		String userName = (String) SessionData.getUserDetails().get("userID");
		ticketEntity.setUserName(userName);
		ticketEntity.setDocketUpdatedDt(new Timestamp(new Date().getTime()));
		
		Set<String> assignmentTypeSet = new HashSet<>();
		assignmentTypeSet.add("Assigned");
		
		List<TicketEscalationEntity> escList = helpDeskTicketDAO.getIntialLevelDesignation(ticketEntity.getSubCategoryId(),assignmentTypeSet);
		List<?> userNamesList = helpDeskTicketDAO.getUserLoginNamesBasedOnDesignation(escList.get(0).getDnId());
		/*if(userNamesList.isEmpty()){
			ProjectHeirarchyEntity parentEntitySubDivision = helpDeskTicketDAO.getParentProjectHeirarchyEntityBasedOnSiteCode(ticketEntity.getSiteCode());
			userNamesList = helpDeskTicketDAO.getUserLoginNamesBasedOnDesignation(escList.get(0).getDnId(),parentEntitySubDivision.getSiteCode());
			if(userNamesList.isEmpty()){
				ProjectHeirarchyEntity parentEntityDivision = helpDeskTicketDAO.getParentProjectHeirarchyEntityBasedOnSiteCode(parentEntitySubDivision.getSiteCode());
				userNamesList = helpDeskTicketDAO.getUserLoginNamesBasedOnDesignation(escList.get(0).getDnId(),parentEntityDivision.getSiteCode());
				if(userNamesList.isEmpty()){
					ProjectHeirarchyEntity parentEntityCircle = helpDeskTicketDAO.getParentProjectHeirarchyEntityBasedOnSiteCode(parentEntityDivision.getSiteCode());
					userNamesList = helpDeskTicketDAO.getUserLoginNamesBasedOnDesignation(escList.get(0).getDnId(),parentEntityCircle.getSiteCode());
					if(userNamesList.isEmpty()){
						ProjectHeirarchyEntity parentEntityZone = helpDeskTicketDAO.getParentProjectHeirarchyEntityBasedOnSiteCode(parentEntityCircle.getSiteCode());
						userNamesList = helpDeskTicketDAO.getUserLoginNamesBasedOnDesignation(escList.get(0).getDnId(),parentEntityZone.getSiteCode());
						if(userNamesList.isEmpty()){
							ProjectHeirarchyEntity parentEntityProject = helpDeskTicketDAO.getParentProjectHeirarchyEntityBasedOnSiteCode(parentEntityZone.getSiteCode());
							userNamesList = helpDeskTicketDAO.getUserLoginNamesBasedOnDesignation(escList.get(0).getDnId(),parentEntityProject.getSiteCode());
						}
					}
				}
			}
			
		}*/
		Object[] values = (Object[])userNamesList.get(0);
		ticketEntity.setAssignedTo((String)values[0]);
		ticketEntity.setDocketStatus(1);
		ticketEntity.setEscLevel(escList.get(0).getLevel());
		ticketEntity.setEscFlag("No");
		ticketEntity.setOfficerName((String)values[1]);
		ticketEntity.setInvalidFlag("No");

		int escTimeLines;
		String classificationType = helpDeskTicketDAO.getLocationClassificationTypeBasedOnSiteCode(ticketEntity.getSiteCode());
		if(classificationType.equalsIgnoreCase("Urban")){
			escTimeLines = escList.get(0).getUrbanTimeLines();
		}else{
			escTimeLines = escList.get(0).getRuralTimeLines();
		}

		Calendar calender = Calendar.getInstance();
		calender.setTime(new Date());
		calender.add(Calendar.HOUR, escTimeLines);
		Timestamp actualEstDate = new Timestamp(calender.getTime().getTime());
		 
		ticketEntity.setEstResolveTime(actualEstDate);
		
		helpDeskTicketDAO.update(ticketEntity);
		sendMailAndSmsCodeForOfficial(ticketEntity);		
		TicketTransactionEntity ticketTransactionEntity = new TicketTransactionEntity();
		ticketTransactionEntity.setAction("Helpdesk Person Updated Ticket");
		ticketTransactionSaveCode(ticketEntity,ticketTransactionEntity);
	}

	@Override
	public List<Map<String, Object>> searchMobileNumber(long mobileNo) {
		
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		 Map<String, Object> docketMap = null;
		 for (Iterator<?> iterator = helpDeskTicketDAO.searchMobileNumber(mobileNo).iterator(); iterator.hasNext();)
			{
				final Object[] values = (Object[]) iterator.next();
				docketMap = new HashMap<String, Object>();
				
				docketMap.put("docketNumber", (int)values[0]);
				if(((int)values[1])==0){
					docketMap.put("docketStatus", "Pending for Registration");
				}else if(((int)values[1])==1){
					docketMap.put("docketStatus", "Registered&Assigned");
				}else if(((int)values[1])==2){
					docketMap.put("docketStatus", "On Hold");
				}else if(((int)values[1])==3){
					docketMap.put("docketStatus", "Resolved");
				}else if(((int)values[1])==4){
					docketMap.put("docketStatus", "Reopened");
				}
				docketMap.put("docketSource", (String)values[2]);
				docketMap.put("consumerName", (String)values[3]);
				docketMap.put("consumerMobileNo", (Long)values[4]);
				docketMap.put("rrNumber", (String)values[5]);
				docketMap.put("address", (String)values[6]);
				docketMap.put("categoryName", (String)values[7]);
				docketMap.put("subCategoryName", (String)values[8]);
				docketMap.put("urName", (String)values[9]);
				docketMap.put("userName", (String)values[10]);
				docketMap.put("docketCreatedDt", new SimpleDateFormat("dd/MM/yyyy hh:mm a").format((Timestamp)values[11]));
				try{
					docketMap.put("resolvedDate", new SimpleDateFormat("dd/MM/yyyy hh:mm a").format((Timestamp)values[12]));
				}catch(Exception e){
					docketMap.put("resolvedDate", "");
				}
				
				docketMap.put("docketSummary", (String)values[13]);
				docketMap.put("divisionName", (String)values[14]);
				docketMap.put("subDivisionName", (String)values[15]);
				
				if((Long)values[16]==null ||(Long)values[16]==0){
					docketMap.put("alternativeMobileNo", "");
				}else{
					docketMap.put("alternativeMobileNo", (Long)values[16]);
				}
				
				if((String)values[17]==null ||((String)values[17]).equals("")){
					docketMap.put("consumerEmailId", "");
				}else{
					docketMap.put("consumerEmailId", (String)values[17]);
				}
				
				if((String)values[18]==null ||((String)values[18]).equals("")){
					docketMap.put("facebookId", "");
				}else{
					docketMap.put("facebookId", (String)values[18]);
				}
				
				if((String)values[19]==null ||((String)values[19]).equals("")){
					docketMap.put("landMark", "");
				}else{
					docketMap.put("landMark", (String)values[19]);
				}
				
				try{
					docketMap.put("estResolveTime", new SimpleDateFormat("dd/MM/yyyy hh:mm a").format((Timestamp)values[20]));
				}catch(Exception e){
					docketMap.put("estResolveTime", "");
				}
				
				if((String)values[21]==null ||((String)values[21]).equals("")){
					docketMap.put("designation", "");
				}else{
					docketMap.put("designation", (String)values[21]);
				}
				
				if((String)values[22]==null ||((String)values[22]).equals("")){
					docketMap.put("assinedName", "");
				}else{
					docketMap.put("assinedName", (String)values[22]);
				}	
				
				if((String)values[23]==null ||((String)values[23]).equals("")){
					docketMap.put("officialMobileNo", "");
				}else{
					docketMap.put("officialMobileNo", (String)values[23]);
				}
				
				docketMap.put("docketUpdatedDt", new SimpleDateFormat("dd/MM/yyyy hh:mm a").format((Timestamp)values[24]));
				
				if((String)values[25]==null ||((String)values[25]).equals("")){
					docketMap.put("remarks", "");
				}else{
					docketMap.put("remarks", (String)values[25]);
				}
				docketMap.put("invalidFlag", (String)values[26]);
			result.add(docketMap);
	     }
		 return result;
	}


	@Override

	public List<?> getTodaysComplaints(Date date) {
		return readData(helpDeskTicketDAO.getTodaysComplaints(date));
	}
	@SuppressWarnings("static-access")
	public List<?> readData(List<?> list){
		List<Map<String, Object>> result=new ArrayList<>();
		for(Iterator<?> iterator=list.iterator();iterator.hasNext();){
			Object[] data=(Object[]) iterator.next();
			Map<String, Object> searchdata=new HashMap<>();
			searchdata.put("docketNumber", data[0]);
			if((Integer)data[1]==0){
				searchdata.put("docketStatus","Pending For Registeration");
			}else if((Integer)data[1]==1){
				searchdata.put("docketStatus","Registered&Assigned");
			}else if((Integer)data[1]==2){
				searchdata.put("docketStatus","OnHold");
			}else if((Integer)data[1]==3){
				searchdata.put("docketStatus","Resolved");
			}else if((Integer)data[1]==4){
				searchdata.put("docketStatus","Reopened");
			}else{
				searchdata.put("docketStatus","Closed");
			}
			searchdata.put("docketCreatedDt",new SimpleDateFormat("MM/dd/yyyy hh:mm a").format((Timestamp)data[2]));
			try{
				searchdata.put("resolvedDate",new SimpleDateFormat("MM/dd/yyyy hh:mm a").format((Timestamp)data[3]));
			}catch(Exception e){
				searchdata.put("resolvedDate","");
			}
			
			searchdata.put("categoryName", data[4]);
			searchdata.put("subCategoryName", data[5]);
			searchdata.put("consumerName", data[6]);
			if(data[7]!=null){
				searchdata.put("consumerMobileNo", data[7]);
			} else{
				searchdata.put("consumerMobileNo", "");
			}
			searchdata.put("assignedTo", data[8]);
			try{
				searchdata.put("estResolveTime", new SimpleDateFormat("MM/dd/yyyy hh:mm a").format((Timestamp)data[9]));
			}catch(Exception e){
				searchdata.put("estResolveTime", " ");
			}
			
			if(data[2]!=null && data[9]!=null){
			Date timeIn = (Timestamp)data[2];
			Date timeOut =  (Timestamp) data[9];
			long diff = timeOut.getTime() - timeIn.getTime();
			final Double diffInHours = diff / ((double) 1000 * 60 * 60);
			String s = String.format("%.2f", diffInHours);
			final double diffInHours1 = (double) diffInHours
					.parseDouble(s);
			searchdata.put("totalTime",diffInHours1+ " Hrs");
			}
			if(data[9]!=null && (Integer)data[1]!=3){
				java.util.Date currentDate=new java.util.Date();
				Date resolveDate =  (Timestamp) data[9];
				DateTime dt1 = null;
				DateTime dt2 =null;
				if(currentDate.after(resolveDate)){
					dt2 = new DateTime(resolveDate);
					dt1 = new DateTime(currentDate);
				}else{
					dt1= new DateTime(resolveDate);
					dt2 = new DateTime(currentDate);
				}
		    	
				final Period period = new Period(dt2,dt1);
				String noOfMonths="";
				if(period.getYears()!=0){
					if(period.getYears()==1){
						noOfMonths=period.getYears()+" Year, ";
					}else{
					noOfMonths=period.getYears()+" Years, ";
					}
				}
				if(period.getMonths()!=0){
					if(period.getMonths()==1){
						noOfMonths=noOfMonths+""+period.getMonths()+" Month, ";
					}else{
					noOfMonths=noOfMonths+""+period.getMonths()+" Months, ";
					}
				}
				if(period.getWeeks()!=0){
					if(period.getWeeks()==1){
						noOfMonths=noOfMonths+""+period.getWeeks()+" Week, ";
					}else{
						noOfMonths=noOfMonths+""+period.getWeeks()+" Weeks, ";
					}
				}
				noOfMonths=noOfMonths+" "+period.getDays()+" Days, "+period.getHours()+" Hours";
				if(currentDate.after(resolveDate)){
					searchdata.put("pending",noOfMonths +" Exceeded");
				}else{
					searchdata.put("pending",noOfMonths +" Remaining");
				}
			}
			else
			{
				searchdata.put("pending","");
			}
			//added By Raju

			if((String)data[10]==null ||((String)data[10]).equals("")){
				searchdata.put("designation", "");
			}else{
				searchdata.put("designation", (String)data[10]);
			}
			
			if((String)data[11]==null ||((String)data[11]).equals("")){
				searchdata.put("assinedName", "");
			}else{
				searchdata.put("assinedName", (String)data[11]);
			}	
			
			if((String)data[12]==null ||((String)data[12]).equals("")){
				searchdata.put("officialMobileNo", "");
			}else{
				searchdata.put("officialMobileNo", (String)data[12]);
			}
			result.add(searchdata);
		}
		return result;
	}
	@SuppressWarnings("static-access")
	public List<?> readData1(List<?> list){
		List<Map<String, Object>> result=new ArrayList<>();
		for(Iterator<?> iterator=list.iterator();iterator.hasNext();){
			Object[] data=(Object[]) iterator.next();
			Map<String, Object> searchdata=new HashMap<>();
			searchdata.put("docketNumber", data[0]);
			if((Integer)data[1]==0){
				searchdata.put("docketStatus","Pending For Registeration");
			}else if((Integer)data[1]==1){
				searchdata.put("docketStatus","Registered&Assigned");
			}else if((Integer)data[1]==2){
				searchdata.put("docketStatus","OnHold");
			}else if((Integer)data[1]==3){
				searchdata.put("docketStatus","Resolved");
			}else if((Integer)data[1]==4){
				searchdata.put("docketStatus","Reopened");
			}else{
				searchdata.put("docketStatus","Closed");
			}
			searchdata.put("docketCreatedDt",new SimpleDateFormat("MM/dd/yyyy hh:mm a").format((Timestamp)data[2]));
			try{
				searchdata.put("resolvedDate",new SimpleDateFormat("MM/dd/yyyy hh:mm a").format((Timestamp)data[3]));
			}catch(Exception e){
				searchdata.put("resolvedDate","");
			}
			
			searchdata.put("categoryName", data[4]);
			searchdata.put("subCategoryName", data[5]);
			searchdata.put("consumerName", data[6]);
			searchdata.put("consumerMobileNo", data[7]);
			searchdata.put("assignedTo", data[8]);
			try{
				searchdata.put("estResolveTime", new SimpleDateFormat("MM/dd/yyyy hh:mm a").format((Timestamp)data[9]));
			}catch(Exception e){
				searchdata.put("estResolveTime", " ");
			}
			
			if(data[2]!=null && data[9]!=null){
				Date timeIn = (Timestamp)data[2];
				Date timeOut =  (Timestamp) data[9];
				long diff = timeOut.getTime() - timeIn.getTime();
				final Double diffInHours = diff / ((double) 1000 * 60 * 60);
				String s = String.format("%.2f", diffInHours);
				final double diffInHours1 = (double) diffInHours
						.parseDouble(s);
				searchdata.put("totalTime",diffInHours1+ " Hrs");
			}
			if(data[9]!=null && (Integer)data[1]!=3){
				java.util.Date currentDate=new java.util.Date();
				Date resolveDate =  (Timestamp) data[9];
				DateTime dt1 = null;
				DateTime dt2 =null;
				if(currentDate.after(resolveDate)){
					dt2 = new DateTime(resolveDate);
					dt1 = new DateTime(currentDate);
				}else{
					dt1= new DateTime(resolveDate);
					dt2 = new DateTime(currentDate);
				}
				
				final Period period = new Period(dt2,dt1);
				String noOfMonths="";
				if(period.getYears()!=0){
					if(period.getYears()==1){
						noOfMonths=period.getYears()+" Year, ";
					}else{
						noOfMonths=period.getYears()+" Years, ";
					}
				}
				if(period.getMonths()!=0){
					if(period.getMonths()==1){
						noOfMonths=noOfMonths+""+period.getMonths()+" Month, ";
					}else{
						noOfMonths=noOfMonths+""+period.getMonths()+" Months, ";
					}
				}
				if(period.getWeeks()!=0){
					if(period.getWeeks()==1){
						noOfMonths=noOfMonths+""+period.getWeeks()+" Week, ";
					}else{
						noOfMonths=noOfMonths+""+period.getWeeks()+" Weeks, ";
					}
				}
				noOfMonths=noOfMonths+" "+period.getDays()+" Days, "+period.getHours()+" Hours";
				if(currentDate.after(resolveDate)){
					searchdata.put("pending",noOfMonths +" Exceeded");
				}else{
					searchdata.put("pending",noOfMonths +" Remaining");
				}
			}
			else
			{
				searchdata.put("pending","");
			}
			//added By Raju
			
			if((String)data[13]==null ||((String)data[13]).equals("")){
				searchdata.put("designation", "");
			}else{
				searchdata.put("designation", (String)data[13]);
			}
			
			if((String)data[14]==null ||((String)data[14]).equals("")){
				searchdata.put("assinedName", "");
			}else{
				searchdata.put("assinedName", (String)data[14]);
			}	
			
			if((String)data[15]==null ||((String)data[15]).equals("")){
				searchdata.put("officialMobileNo", "");
			}else{
				searchdata.put("officialMobileNo", (String)data[15]);
			}
			result.add(searchdata);
		}
		return result;
	}

	public List<?> getNotificationDataBasedOnStatus(Set<Integer> statusSet,String loginName) {

		
		 List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		 Map<String, Object> docketMap = null;
		 for (Iterator<?> iterator = helpDeskTicketDAO.getNotificationDataBasedOnStatus(statusSet,loginName).iterator(); iterator.hasNext();)
			{
				final Object[] values = (Object[]) iterator.next();
				docketMap = new HashMap<String, Object>();
				
				docketMap.put("docketStatus", (Integer)values[0]);
				docketMap.put("docketCreatedDt", new SimpleDateFormat("dd/MM/yyyy hh:mm a").format((Timestamp)values[1]));
				docketMap.put("docketNumber", (Integer)values[2]);
				docketMap.put("estResolveTime", new SimpleDateFormat("dd/MM/yyyy hh:mm a").format((Timestamp)values[3]));
			
			result.add(docketMap);
			}
		 return result;
	}

	@Override
	public List<?> getPendingNotificationDataBasedOnStatus(Set<Integer> statusSet) {
		
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		 Map<String, Object> docketMap = null;
		 for (Iterator<?> iterator = helpDeskTicketDAO.getPendingNotificationDataBasedOnStatus(statusSet).iterator(); iterator.hasNext();)
			{
				final Object[] values = (Object[]) iterator.next();
				docketMap = new HashMap<String, Object>();
				
				docketMap.put("docketStatus", (Integer)values[0]);
				docketMap.put("docketCreatedDt", new SimpleDateFormat("dd/MM/yyyy hh:mm a").format((Timestamp)values[1]));
				docketMap.put("docketNumber", (Integer)values[2]);
				docketMap.put("consumerMobileNo", (Long)values[3]);
			
			result.add(docketMap);
			}
		 return result;
	}

	@Override
	public List<?> getComplaints(List<?> list) {
		return readData(list);
	}
	
	public List<?> getComplaints1(List<?> list) {
		return readData1(list);
	}

	@Override
	public List<?> getAllResolvedComplaints() {
		
		 List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		 Map<String, Object> docketMap = null;
		 for (Iterator<?> iterator = helpDeskTicketDAO.getAllResolvedComplaints().iterator(); iterator.hasNext();)
			{
				final Object[] values = (Object[]) iterator.next();
				docketMap = new HashMap<String, Object>();
				
				docketMap.put("docketNumber", (int)values[0]);
				if(((int)values[1])==0){
					docketMap.put("docketStatus", "Pending for Registration");
				}else if(((int)values[1])==1){
					docketMap.put("docketStatus", "Registered&Assigned");
				}else if(((int)values[1])==2){
					docketMap.put("docketStatus", "On Hold");
				}else if(((int)values[1])==3){
					docketMap.put("docketStatus", "Resolved");
				}else if(((int)values[1])==4){
					docketMap.put("docketStatus", "Reopened");
				}else if(((int)values[1])==5){
					docketMap.put("docketStatus", "Closed");
				}
				docketMap.put("docketSource", (String)values[2]);
				docketMap.put("consumerName", (String)values[3]);
				docketMap.put("consumerMobileNo", (Long)values[4]);
				docketMap.put("rrNumber", (String)values[5]);
				docketMap.put("address", (String)values[6]);
				docketMap.put("categoryName", (String)values[7]);
				docketMap.put("subCategoryName", (String)values[8]);
				docketMap.put("remarks", (String)values[9]);
				docketMap.put("userName", (String)values[10]);	
			    docketMap.put("docketCreatedDt", new SimpleDateFormat("dd/MM/yyyy hh:mm a").format((Timestamp)values[11]));
				try{
					docketMap.put("resolvedDate", new SimpleDateFormat("dd/MM/yyyy hh:mm a").format((Timestamp)values[12]));
				}catch(Exception e){
					docketMap.put("resolvedDate", "");
				}
				
				docketMap.put("docketSummary", (String)values[13]);
				docketMap.put("divisionName", (String)values[14]);
				docketMap.put("subDivisionName", (String)values[15]);
				
				if(((Timestamp)values[11])!=null){
					if(((Timestamp)values[16])==null){
						docketMap.put("estResolveDate", "");
						docketMap.put("timePending", "");
						docketMap.put("totalTimeTaken", "");
					}else{
						docketMap.put("estResolveDate", new SimpleDateFormat("MM/dd/yyyy hh:mm a").format((Timestamp)values[16]));
						DateTime dt1 = new DateTime(new Timestamp(new Date().getTime()));
						DateTime dt2 = new DateTime((Timestamp)values[16]);
						int hours = Hours.hoursBetween(dt1, dt2).getHours() % 24;
						docketMap.put("timePending", hours+" Hrs");
						
						DateTime dt11 = new DateTime((Timestamp)values[11]);
						DateTime dt22 = new DateTime((Timestamp)values[12]);
						int totalHours = Hours.hoursBetween(dt11, dt22).getHours() % 24;	
						docketMap.put("totalTimeTaken", totalHours+" Hrs");
						
					}
				}else{
					docketMap.put("estResolveDate", "");
					docketMap.put("timePending", "");
					docketMap.put("totalTimeTaken", "");
				}
				if(((Integer)values[17])==null){
					docketMap.put("feedbackRating", "");
				}else{
					docketMap.put("feedbackRating", (Integer)values[17]);
				}
				if(((String)values[18])==null || ((String)values[18]).equals("")){
					docketMap.put("feedbackComments", "");
				}else{
					docketMap.put("feedbackComments", (String)values[18]);
				}
				
			result.add(docketMap);
	     }
		 return result;
	}

	@Override
	public void submitFeedbackcomments(HelpDeskTicketEntity ticketEntity) {
		helpDeskTicketDAO.update(ticketEntity);
	}

	@Override
	public List<?> searchEsclation(String sitecode) {
		List<?> data=helpDeskTicketDAO.searchEsclation(sitecode);
		List<Map<String, Object>> result=new ArrayList<>();
		for(Iterator<?> iterator=data.iterator();iterator.hasNext();){
			Object[] obj=(Object[]) iterator.next();
			Map<String, Object> mapdata=new HashMap<>();
			mapdata.put("levels",(Integer)obj[2]);
			mapdata.put("notickets",(Long)obj[0]);
			result.add(mapdata);
		}
		
		return result;
	}

	@Override
	@Async
	public void escalationSheduler() {
		
		for (Iterator<?> iterator = helpDeskTicketDAO.getAllTicketsForEscalationProcess().iterator(); iterator.hasNext();) {
			
		    final Object[] values = (Object[]) iterator.next();				
		    Hours hours = Hours.hoursBetween(new DateTime((Timestamp)values[1]),new DateTime(new Timestamp(new java.util.Date().getTime())));
		    //Minutes hours = Minutes.minutesBetween(new DateTime((Timestamp)values[1]),new DateTime(new Timestamp(new java.util.Date().getTime())));
		    List<TicketEscalationEntity> escList = helpDeskTicketDAO.getAllLevelDesignation((Integer)values[2]);
			for (TicketEscalationEntity ticketEscalationEntity : escList) {
				if(ticketEscalationEntity.getLevel()!=1 && ticketEscalationEntity.getAssignmentType().equals("Notified")){
					
					int escTimeLines = ticketEscalationEntity.getUrbanTimeLines();
					
					if(hours.getHours()+1 == escTimeLines){
						
						EscalatedComplaintsEntity escalatedComplaintsEntity = new EscalatedComplaintsEntity();	
						
						HelpDeskTicketEntity ticketEntity = helpDeskTicketDAO.find((Integer)values[0]);
						
						escalatedComplaintsEntity.setDocketNumber((Integer)values[0]);
						escalatedComplaintsEntity.setEscalatedDate(new Timestamp(new Date().getTime()));
						escalatedComplaintsEntity.setLevel(ticketEscalationEntity.getLevel());
						escalatedComplaintsEntity.setFromUser((String)values[3]);
						
						List<?> userNamesList = helpDeskTicketDAO.getUserLoginNamesBasedOnDesignation(ticketEscalationEntity.getDnId());
						Object[] data = (Object[])userNamesList.get(0);
						escalatedComplaintsEntity.setToUser((String)data[0]);
						escalatedComplaintsEntity.setRemarks("This ticket escalated from "+escalatedComplaintsEntity.getFromUser()+" to "+escalatedComplaintsEntity.getToUser());
						escalatedComplaintsEntity.setOfficerName((String)data[1]);
						
						escalatedComplaintsDAO.save(escalatedComplaintsEntity);
						
						TicketTransactionEntity ticketTransactionEntity = new TicketTransactionEntity();
						ticketTransactionEntity.setAction("Ticket Escalated");
						ticketEntity.setEscFlag("Yes");
						ticketEntity.setEscLevel(ticketEscalationEntity.getLevel());
						ticketEntity.setDocketUpdatedDt(escalatedComplaintsEntity.getEscalatedDate());
						ticketEntity.setOfficerName(escalatedComplaintsEntity.getOfficerName());
						helpDeskTicketDAO.update(ticketEntity);
						ticketTransactionSaveCode(ticketEntity,ticketTransactionEntity);
						sendMailAndSmsEscalatedComplaintsCodeForOfficial(ticketEntity,(String)data[0],ticketEscalationEntity);
					}
				}
			}
		}
	}

	@Override
	public int getescalatedTicketsCount() {
		return helpDeskTicketDAO.getescalatedTicketsCount().intValue();
	}

	@Override
	public List<?> escalatedNotifications(String loginName) {
		
		 List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		 Map<String, Object> docketMap = null;
		 for (Iterator<?> iterator = helpDeskTicketDAO.escalatedNotifications(loginName).iterator(); iterator.hasNext();)
			{
				final Object[] values = (Object[]) iterator.next();
				docketMap = new HashMap<String, Object>();
				
				docketMap.put("docketNumber", (int)values[0]);
				docketMap.put("escalatedDate", new SimpleDateFormat("dd/MM/yyyy hh:mm a").format((Timestamp)values[1]));
				
			result.add(docketMap);
	     }
		 return result;
	}


	@Override
	public List<?> getlocationWiseDetails(Set<String> projectIds) {
		List<?> resultDate=helpDeskTicketDAO.getlocationWiseDetails(projectIds);
		List<Map<String,Object>> resulList=new ArrayList<>();
		for(Iterator<?> itr=resultDate.iterator();itr.hasNext();){
			Object[] obj=(Object[]) itr.next();
			Map<String, Object> data=new HashMap<>();
					if((Integer)obj[1]==0){
						data.put("pending",(long)obj[0]);
					}else if((Integer)obj[1]==1){
						data.put("assigned",(long)obj[0]);
					}else if((Integer)obj[1]==2){
						data.put("onhold",(long)obj[0]);
					}else if((Integer)obj[1]==3){
						data.put("resolved",(long)obj[0]);
					}else if((Integer)obj[1]==4){
						data.put("reopen",(long)obj[0]);
					}
			resulList.add(data);
		}		
		return resulList;
		
		
	}

	@Override
	public List<?> getAllEscalatedTickets(String loginName) {

		 List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		 Map<String, Object> docketMap = null;
		 for (Iterator<?> iterator = helpDeskTicketDAO.getAllEscalatedTickets(loginName).iterator(); iterator.hasNext();)
			{
				final Object[] values = (Object[]) iterator.next();
				docketMap = new HashMap<String, Object>();
				
				docketMap.put("docketNumber", (int)values[0]);
				if(((int)values[1])==0){
					docketMap.put("docketStatus", "Pending for Registration");
				}else if(((int)values[1])==1){
					docketMap.put("docketStatus", "Registered&Assigned");
				}else if(((int)values[1])==2){
					docketMap.put("docketStatus", "On Hold");
				}else if(((int)values[1])==3){
					docketMap.put("docketStatus", "Resolved");
				}else if(((int)values[1])==4){
					docketMap.put("docketStatus", "Reopened");
				}
				docketMap.put("escalatedDate", new SimpleDateFormat("dd/MM/yyyy hh:mm a").format((Timestamp)values[2]));
				docketMap.put("level", (Integer)values[3]);
				docketMap.put("fromUser", (String)values[4]);
				docketMap.put("toUser", (String)values[5]);
				
				result.add(docketMap);
	     }
		 return result;
	}

	@Override
	public List<?> showAllEscalatedNotificationsForCCCD() {
		
		 List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		 Map<String, Object> docketMap = null;
		 for (Iterator<?> iterator = helpDeskTicketDAO.showAllEscalatedNotificationsForCCCD().iterator(); iterator.hasNext();)
			{
				final Object[] values = (Object[]) iterator.next();
				docketMap = new HashMap<String, Object>();
				
				docketMap.put("docketNumber", (int)values[0]);
				docketMap.put("escalatedDate", new SimpleDateFormat("dd/MM/yyyy hh:mm a").format((Timestamp)values[1]));
				
				result.add(docketMap);
	     }
		 return result;
	}

	@Override
	public List<?> getAllEscalatedTicketsForCCCD() {
		
		 List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		 Map<String, Object> docketMap = null;
		 for (Iterator<?> iterator = helpDeskTicketDAO.getAllEscalatedTicketsForCCCD().iterator(); iterator.hasNext();)
			{
				final Object[] values = (Object[]) iterator.next();
				docketMap = new HashMap<String, Object>();
				
				docketMap.put("docketNumber", (int)values[0]);
				if(((int)values[1])==0){
					docketMap.put("docketStatus", "Pending for Registration");
				}else if(((int)values[1])==1){
					docketMap.put("docketStatus", "Registered&Assigned");
				}else if(((int)values[1])==2){
					docketMap.put("docketStatus", "On Hold");
				}else if(((int)values[1])==3){
					docketMap.put("docketStatus", "Resolved");
				}else if(((int)values[1])==4){
					docketMap.put("docketStatus", "Reopened");
				}
				docketMap.put("docketCreatedDt", new SimpleDateFormat("dd/MM/yyyy hh:mm a").format((Timestamp)values[2]));
				docketMap.put("escalatedDate", new SimpleDateFormat("dd/MM/yyyy hh:mm a").format((Timestamp)values[3]));
				docketMap.put("estResolveTime", new SimpleDateFormat("dd/MM/yyyy hh:mm a").format((Timestamp)values[4]));
				docketMap.put("level", (Integer)values[5]);
				docketMap.put("fromUser", (String)values[6]);
				docketMap.put("toUser", (String)values[7]);
				docketMap.put("OfficerName", (String)values[8]);
				docketMap.put("officerMobileNo", (String)values[9]);
				docketMap.put("office", (String)values[10]);
				docketMap.put("complaintCatagory", (String)values[11]);
				
				result.add(docketMap);
	     }
		 return result;
	}

	@Override
	public void updateDocketAsInvalidated(int docketNumber) {
		helpDeskTicketDAO.updateDocketAsInvalidated(docketNumber);
		
		HelpDeskTicketEntity ticketEntity = helpDeskTicketDAO.find(docketNumber);
		
		TicketTransactionEntity ticketTransactionEntity = new TicketTransactionEntity();
		ticketTransactionEntity.setAction("Marked docket as invalid");
		ticketEntity.setInvalidFlag("Yes");
		ticketEntity.setDocketUpdatedDt(new Timestamp(new Date().getTime()));
		String userName = (String) SessionData.getUserDetails().get("userID");
		ticketEntity.setUserName(userName);
		ticketTransactionSaveCode(ticketEntity,ticketTransactionEntity);
	}

	@Override
	public List<?> searchEsclation(String sitecode, Date date1, Date date2) {
		List<?> data=helpDeskTicketDAO.searchEsclation(sitecode,date1,date2);
		List<Map<String, Object>> result=new ArrayList<>();
		for(Iterator<?> iterator=data.iterator();iterator.hasNext();){
			Object[] obj=(Object[]) iterator.next();
			Map<String, Object> mapdata=new HashMap<>();
			mapdata.put("levels",(Integer)obj[2]);
			mapdata.put("notickets",(Long)obj[0]);
			result.add(mapdata);
		}
		
		return result;
	}

	@Override
	public List<?> getEscalationAnalysisDetails(String siteCode,String fromDate, String toDate, Integer level) {
			Date date1 = null;
			Date date2 = null;
			try {
				date1 = new SimpleDateFormat("dd/MM/yyyy").parse(fromDate);
				date2 = new SimpleDateFormat("dd/MM/yyyy").parse(toDate);
				
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			ProjectHeirarchyEntity projectHeirarchyEntity=masterGenericDAO.getByUniqueAttribute(ProjectHeirarchyEntity.class,"siteCode",siteCode);
			Set<String> sitecodes=new HashSet<>();
			sitecodes.add(siteCode);
			fetchChildren1(projectHeirarchyEntity,sitecodes);
		
			return readEscalationAnalysisDetails(helpDeskTicketDAO.getEscalationAnalysisDetails(sitecodes,date1,date2,level));
	}
	private ProjectHeirarchyEntity fetchChildren1(ProjectHeirarchyEntity parent,Set<String> divisionIds) {
		Hibernate.initialize(parent.getChilds());
		for (ProjectHeirarchyEntity child : parent.getChilds()) {
			fetchChildren1(child,divisionIds);
			divisionIds.add(child.getSiteCode());
		}
		return parent;
	}
	private List<?> readEscalationAnalysisDetails(List<?> escalationData) {
		List<Map<String,Object>> result=new ArrayList<>();
		for(Iterator<?> iterator=escalationData.iterator();iterator.hasNext();){
			Object[] obj=(Object[]) iterator.next();
			Map<String,Object> data=new HashMap<>();
			
			data.put("docketNumber",(int)obj[0]);
			if(((int)obj[1])==0){
				data.put("docketStatus", "Pending for Registration");
			}else if(((int)obj[1])==1){
				data.put("docketStatus", "Registered&Assigned");
			}else if(((int)obj[1])==2){
				data.put("docketStatus", "On Hold");
			}else if(((int)obj[1])==3){
				data.put("docketStatus", "Resolved");
			}else if(((int)obj[1])==4){
				data.put("docketStatus", "Reopened");
			}else if(((int)obj[1])==5){
				data.put("docketStatus", "Closed");
			}
			data.put("docketCreatedDt",new SimpleDateFormat("dd/MM/yyyy hh:mm a").format((Timestamp)obj[2]));
			data.put("escalatedDate",new SimpleDateFormat("dd/MM/yyyy hh:mm a").format((Timestamp)obj[3]));
			data.put("estResolveTime",new SimpleDateFormat("dd/MM/yyyy hh:mm a").format((Timestamp)obj[4]));
			data.put("level",(Integer)obj[5]);
			data.put("categoryName",(String)obj[6]);
			data.put("subCategoryName",(String)obj[7]);
			result.add(data);
		}
		return result;
	}
	
	@Override
	public List<?> getDocketDetailsBasedOnStatus(Set<String> projectIds,int status) throws ParseException {
		return readDetailsInMap(helpDeskTicketDAO.getDocketDetailsBasedOnStatusAndProjectId(projectIds,status));
	}
	
	@SuppressWarnings("static-access")
	@Override
	public List<?> readDetailsInMap(List<?> list) throws ParseException
	{

		List<Map<String, Object>> result=new ArrayList<>();
		for(Iterator<?> iterator=list.iterator();iterator.hasNext();){
			Object[] data=(Object[]) iterator.next();
			Map<String, Object> searchdata=new HashMap<>();
			searchdata.put("docketNumber", data[0]);
			if((Integer)data[1]==0){
				searchdata.put("docketStatus","Pending For Registeration");
			}else if((Integer)data[1]==1){
				searchdata.put("docketStatus","Registered&Assigned");
			}else if((Integer)data[1]==2){
				searchdata.put("docketStatus","OnHold");
			}else if((Integer)data[1]==3){
				searchdata.put("docketStatus","Resolved");
			}else if((Integer)data[1]==4){
				searchdata.put("docketStatus","Reopened");
			}else{
				searchdata.put("docketStatus","Closed");
			}
			
			String resld="";
			if((Timestamp)data[3]!=null)
			{
				resld=new SimpleDateFormat("dd/MM/yyyy HH:mm a").format((Timestamp) data[3]);
			}
			String estTime="";
			if((Timestamp)data[9]!=null)
			{
				estTime=new SimpleDateFormat("dd/MM/yyyy HH:mm a").format((Timestamp) data[9]);
			}
			String dckCrtd="";
			if((Timestamp)data[2]!=null)
			{
				dckCrtd=new SimpleDateFormat("dd/MM/yyyy HH:mm a").format((Timestamp) data[2]);
			}
		 
			searchdata.put("docketCreatedDt",dckCrtd);
			searchdata.put("resolvedDate", resld);
			searchdata.put("categoryName", data[4]);
			searchdata.put("subCategoryName", data[5]);
			searchdata.put("consumerName", data[6]);
			searchdata.put("consumerMobileNo", data[7]);
			searchdata.put("assignedTo", data[8]);
			searchdata.put("estResolveTime", estTime);
			
			if(data[2]!=null && data[9]!=null){
			Date timeIn = (Timestamp)data[2];
			Date timeOut =  (Timestamp) data[9];
			long diff = timeOut.getTime() - timeIn.getTime();
			final Double diffInHours = diff / ((double) 1000 * 60 * 60);
			String s = String.format("%.2f", diffInHours);
			final double diffInHours1 = (double) diffInHours.parseDouble(s);
			searchdata.put("totalTime",diffInHours1+ " Hrs");
			}
			if(data[9]!=null && (Integer)data[1]!=3){
				java.util.Date currentDate=new java.util.Date();
				Date resolveDate =  (Timestamp) data[9];
		    	DateTime dt1 = new DateTime(resolveDate);
				DateTime dt2 = new DateTime(currentDate);
				final Period period = new Period(dt1,dt2);
				String noOfMonths="";
				if(period.getYears()!=0){
					if(period.getYears()==1){
						noOfMonths=period.getYears()+" Year, ";
					}else{
					noOfMonths=period.getYears()+" Years, ";
					}
				}
				if(period.getMonths()!=0){
					if(period.getMonths()==1){
						noOfMonths=noOfMonths+""+period.getMonths()+" Month, ";
					}else{
					noOfMonths=noOfMonths+""+period.getMonths()+" Months, ";
					}
				}
				if(period.getWeeks()!=0){
					if(period.getWeeks()==1){
						noOfMonths=noOfMonths+""+period.getWeeks()+" Week, ";
					}else{
						noOfMonths=noOfMonths+""+period.getWeeks()+" Weeks, ";
					}
				}
				noOfMonths=noOfMonths+" "+period.getDays()+" Days, "+period.getHours()+" Hours";
			searchdata.put("pending",noOfMonths);
			}else{
				searchdata.put("pending","");
			}
			result.add(searchdata);
		}
		return result;
	
	}

	@Override
	public Map<String, Object> getCircleWiseData(int circleId, String text,Date date, Integer categoryId, Integer subCategoryId) {
		Set<String> sitecodes=new HashSet<>();
		ProjectHeirarchyEntity projectHeirarchyEntity=masterGenericDAO.getById(ProjectHeirarchyEntity.class,circleId);
		sitecodes.add(projectHeirarchyEntity.getSiteCode());
		
		fetchChildren1(projectHeirarchyEntity,sitecodes);
		System.out.println(sitecodes);
		List<?> data=helpDeskTicketDAO.getCircleWiseData(sitecodes,date,categoryId,subCategoryId);
		Map<String, Object> result=new HashMap<>();
		for(Iterator<?> iterator=data.iterator();iterator.hasNext();){
			Object[] obj=(Object[]) iterator.next();
			if((Long)obj[0]!=0){
			result.put("total",(Long)obj[0]);
			result.put("asPerSLA",(Long)obj[1]);
			result.put("beyondSLA",(Long)obj[2]);
			result.put("text",text);
			result.put("circleId",circleId);
			return result;
			}
		}
		return null;
	}

	@Override
	public Map<String, Integer> getDayWiseComplaints() {
		
		Map<String, Integer> dayCounterData = new HashMap<>();
		
		Date todayDate = new Date();
		
		Calendar cal = Calendar.getInstance();
	    cal.setTime(todayDate);
	    int year = cal.get(Calendar.YEAR);
	    int m = cal.get(Calendar.MONTH);
	    int month = m+1;
	    int day = cal.get(Calendar.DAY_OF_MONTH);
	    
	    //dayCounterData.put("pendingForRegistrationDayCount",helpDeskTicketDAO.getComplaintsCountBasedOnDayWise(0,day,month,year).intValue());
		dayCounterData.put("registered",helpDeskTicketDAO.getComplaintsCountBasedOnDayWise(1,day,month,year).intValue());
		dayCounterData.put("resolved",helpDeskTicketDAO.getComplaintsCountBasedOnDayWise(3,day,month,year).intValue());
		dayCounterData.put("reopened",helpDeskTicketDAO.getComplaintsCountBasedOnDayWise(4,day,month,year).intValue());
		dayCounterData.put("onHold",helpDeskTicketDAO.getComplaintsCountBasedOnDayWise(2,day,month,year).intValue());
		dayCounterData.put("escalated",helpDeskTicketDAO.getEscalatedComplaintsCountBasedOnDayWise(day,month,year).intValue());
		
		return dayCounterData;
	}

	@Override
	public Map<String, String> getComplaintAgingStatistics() {
		
		Map<String, String> agingData = new HashMap<>();
		int count1 = 0;
		int count2 = 0;
		int count3 = 0;
		int count4 = 0;
		int count5 = 0;
		int count6 = 0;
		
		String docCount1="";
		String docCount2="";
		String docCount3="";
		String docCount4="";
		String docCount5="";
		String docCount6="";
		
		for(Iterator<?> iterator = helpDeskTicketDAO.getComplaintAgingStatistics().iterator();iterator.hasNext();){
			
			Object[] values = (Object[]) iterator.next();
			
			DateTime dt1 = new DateTime(new Timestamp(new Date().getTime()));
			DateTime dt2 = new DateTime((Timestamp)values[0]);
			int hours = Hours.hoursBetween(dt1, dt2).getHours();
			
			int actualValue = Math.abs(hours);
			if(actualValue>=0 && actualValue<=1){
				docCount1+=(int)values[1]+",";
				count1+=1;
			}else if(actualValue>=2 && actualValue<=4){
				docCount2+=(int)values[1]+",";
				count2+=1;
			}else if(actualValue>=5 && actualValue<=24){
				docCount3+=(int)values[1]+",";
				count3+=1;
			}else  if(actualValue>=25 && actualValue<=240){
				docCount4+=(int)values[1]+",";
				count4+=1;
			}else if(actualValue>=240 && actualValue<=744){
				docCount5+=(int)values[1]+",";
				count5+=1;
			}else {
				docCount6+=(int)values[1]+",";
				count6+=1;
			}
		}
		
		agingData.put("level1",String.valueOf(count1));
		agingData.put("level2",String.valueOf(count2));
		agingData.put("level3",String.valueOf(count3));
		agingData.put("level4",String.valueOf(count4));
		agingData.put("level5",String.valueOf(count5));
		agingData.put("level6",String.valueOf(count6));
		
		agingData.put("doclevel1",docCount1);
		agingData.put("doclevel2",docCount2);
		agingData.put("doclevel3",docCount3);
		agingData.put("doclevel4",docCount4);
		agingData.put("doclevel5",docCount5);
		agingData.put("doclevel6",docCount6);
		
		return agingData;
	}
// Android part  
	@Override
	public void saveTicketFromMobil(HelpDeskTicketEntity ticketEntity) {
		
		helpDeskTicketDAO.save(ticketEntity);
		sendMailAndSmsCodeForConsumer(ticketEntity);
		
		TicketTransactionEntity ticketTransactionEntity = new TicketTransactionEntity();
		ticketTransactionEntity.setAction("Helpdesk Person Created Ticket");
		ticketTransactionSaveCode(ticketEntity,ticketTransactionEntity);
	}

	@Override
	public Map<String, String> getComplaintAgingForPendingResovtion() {
		
		Map<String, String> agingDataForPending = new HashMap<>();
		int count1 = 0;
		int count2 = 0;
		int count3 = 0;
		int count4 = 0;
		int count5 = 0;
		int count6 = 0;
		
		String docCount1="";
		String docCount2="";
		String docCount3="";
		String docCount4="";
		String docCount5="";
		String docCount6="";
	
		for(Iterator<?> iterator=helpDeskTicketDAO.getComplaintAgingForPendingResov().iterator();iterator.hasNext();)
		{
			Object[] obj=(Object[])iterator.next();
			
			DateTime dt1 = new DateTime(new Timestamp(new Date().getTime()));
			DateTime reslnDate=new DateTime((Timestamp)obj[0]);
			
			if(dt1.isBefore(reslnDate))
			{
			
			int hours = Hours.hoursBetween(dt1, reslnDate).getHours();
			int actualValue = Math.abs(hours);
			if(actualValue>=0 && actualValue<=1){
				docCount1+=(int)obj[1]+",";
				count1+=1;
			}else if(actualValue>=2 && actualValue<=4){
				docCount2+=(int)obj[1]+",";
				count2+=1;
			}else if(actualValue>=5 && actualValue<=24){
				docCount3+=(int)obj[1]+",";
				count3+=1;
			}else  if(actualValue>=25 && actualValue<=240){
				docCount4+=(int)obj[1]+",";
				count4+=1;
			}else if(actualValue>=240 && actualValue<=744){
				docCount5+=(int)obj[1]+",";
				count5+=1;
			}else {
				docCount6+=(int)obj[1]+",";
				count6+=1;
			}
			}
			
			
		}
		
		agingDataForPending.put("level1",String.valueOf(count1));
		agingDataForPending.put("level2",String.valueOf(count2));
		agingDataForPending.put("level3",String.valueOf(count3));
		agingDataForPending.put("level4",String.valueOf(count4));
		agingDataForPending.put("level5",String.valueOf(count5));
		agingDataForPending.put("level6",String.valueOf(count6));
		
		
		agingDataForPending.put("doclevel1",docCount1);
		agingDataForPending.put("doclevel2",docCount2);
		agingDataForPending.put("doclevel3",docCount3);
		agingDataForPending.put("doclevel4",docCount4);
		agingDataForPending.put("doclevel5",docCount5);
		agingDataForPending.put("doclevel6",docCount6);
		
		
		return agingDataForPending;
	}

	@Override
	public List<?> getSupportTeamDetails() {
		
		return readListIntoMap1(helpDeskTicketDAO.getSupprtTeamDetails());
	}

	@Override
	public List<?> getSupportTeamBaseOnDate(Date fdate, Date tdate,String userName) {
		
		return readListIntoMap1(helpDeskTicketDAO.getSupportBasedDateDetails(fdate,tdate,userName));
	}

	public List<?> readListIntoMap(List<?> recrd)
	{
		
		List<Map<String, Object>> getDetails=new ArrayList<>();
		for(Iterator<?> iterator=recrd.iterator();iterator.hasNext();)
		{
			Object[] obj=(Object[])iterator.next();
			Map<String,Object> map=new HashMap<>();
			map.put("userName", (String)obj[0]);
			map.put("pending", (BigInteger)obj[1]);
			map.put("assigned", (BigInteger)obj[2]);
			map.put("onhold", (BigInteger)obj[3]);
			map.put("resolvd", (BigInteger)obj[4]);
			map.put("reopne", (BigInteger)obj[5]);
			map.put("total",  (BigInteger)obj[6]);
			map.put("uEmail",  (String)obj[7]);
			map.put("invalid",  (BigInteger)obj[8]);
			getDetails.add(map);
		}
		return getDetails;
		
	}
	public List<?> readListIntoMap1(List<?> recrd)
	{
		
		List<Map<String, Object>> getDetails=new ArrayList<>();
		for(Iterator<?> iterator=recrd.iterator();iterator.hasNext();)
		{
			Object[] obj=(Object[])iterator.next();
			Map<String,Object> map=new HashMap<>();
			map.put("userName", (String)obj[0]);
			map.put("pending", (BigInteger)obj[1]);
			map.put("assigned", (BigInteger)obj[2]);
			map.put("onhold", (BigInteger)obj[3]);
			map.put("resolvd", (BigInteger)obj[4]);
			map.put("reopne", (BigInteger)obj[5]);
			map.put("total",  (BigInteger)obj[6]);
			map.put("uEmail",  (String)obj[7]);
			map.put("invalid",  (BigInteger)obj[8]);
			getDetails.add(map);
		}
		return getDetails;
		
	}

	@Override
	public List<?> getMycomplaints(String loginName) {
		return readListIntoMap1(helpDeskTicketDAO.readMyComplaints(loginName));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<?> getteamDetails(Date fdate, Date tdate, Set<Integer> statusid,String uemail) {
		if(statusid.size()==1){
			Iterator<Integer> itr=statusid.iterator();itr.hasNext();
			int status=itr.next();
			if(status==5){
				List<Map<String, Object>> result=(List<Map<String, Object>>) getComplaints1(helpDeskTicketDAO.getteamDetails(fdate,tdate,statusid,uemail));
				List<?> data=helpDeskTicketDAO.getInvalidTeamDetails(fdate,tdate,statusid,uemail);
				for(Iterator<?> iterator=data.iterator();iterator.hasNext();){
					Object[] objects=(Object[]) iterator.next();
					Map<String, Object> mapdata=new HashMap<>();
					mapdata.put("docketNumber", (int)objects[0]);
					String str[]={"Pending for Registration","Registered&Assigned","On Hold","Resolved","Reopen"};
					mapdata.put("docketStatus", str[(int)objects[1]]);
					mapdata.put("docketCreatedDt", new SimpleDateFormat("dd/MM/yyyy hh:mm a").format((Timestamp)objects[2]));
					if(((String)objects[3])==null || ((String)objects[3]).equals("")){
						mapdata.put("consumerName", "");
					}else{
						mapdata.put("consumerName", (String)objects[3]);
					}
					if((Long)objects[4]==0){
						mapdata.put("consumerMobileNo", "");
					}else{
						mapdata.put("consumerMobileNo", (Long)objects[4]);
					}
					if(objects[5]==null || ((String)objects[5]).equals("")){
						mapdata.put("consumerEmailId", "");
					}else{
						mapdata.put("consumerEmailId", (String)objects[5]);
					}
					
					mapdata.put("categoryName", "");
					mapdata.put("subCategoryName", "");
					mapdata.put("pending", "");
					mapdata.put("estResolveTime", "");
					mapdata.put("resolvedDate", "");
					mapdata.put("assinedName", "");
					mapdata.put("assignedTo", "");
					mapdata.put("designation", "");
					mapdata.put("officialMobileNo", "");
					result.add(mapdata);
				}
			return result;
			}
		}else if(statusid.size()>2){
			List<Map<String, Object>> result=(List<Map<String, Object>>) getComplaints1(helpDeskTicketDAO.getteamDetails(fdate,tdate,statusid,uemail));
			List<?> data=helpDeskTicketDAO.getInvalidTeamDetails(fdate,tdate,statusid,uemail);
			for(Iterator<?> iterator=data.iterator();iterator.hasNext();){
				Object[] objects=(Object[]) iterator.next();
				Map<String, Object> mapdata=new HashMap<>();
				mapdata.put("docketNumber", (int)objects[0]);
				String str[]={"Pending for Registration","Registered&Assigned","On Hold","Resolved","Reopen"};
				mapdata.put("docketStatus", str[(int)objects[1]]);
				mapdata.put("docketCreatedDt", new SimpleDateFormat("dd/MM/yyyy hh:mm a").format((Timestamp)objects[2]));
				if(((String)objects[3])==null || ((String)objects[3]).equals("")){
					mapdata.put("consumerName", "");
				}else{
					mapdata.put("consumerName", (String)objects[3]);
				}
				if((Long)objects[4]==0){
					mapdata.put("consumerMobileNo", "");
				}else{
					mapdata.put("consumerMobileNo", (Long)objects[4]);
				}
				if(objects[5]==null || ((String)objects[5]).equals("")){
					mapdata.put("consumerEmailId", "");
				}else{
					mapdata.put("consumerEmailId", (String)objects[5]);
				}
				
				mapdata.put("categoryName", "");
				mapdata.put("subCategoryName", "");
				mapdata.put("pending", "");
				mapdata.put("estResolveTime", "");
				mapdata.put("resolvedDate", "");
				mapdata.put("assinedName", "");
				mapdata.put("assignedTo", "");
				mapdata.put("designation", "");
				mapdata.put("officialMobileNo", "");
				result.add(mapdata);
			}
		return result;
			
		}
		return getComplaints1(helpDeskTicketDAO.getteamDetails(fdate,tdate,statusid,uemail));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<?> getTeamWiseMothlyWise(Set<Integer> statusid, String uEmail) {
		if(statusid.size()==1){
			Iterator<Integer> itr=statusid.iterator();itr.hasNext();
			int status=itr.next();
			if(status==5){
				List<Map<String, Object>> result=(List<Map<String, Object>>) getComplaints1(helpDeskTicketDAO.getSupportTeamMonthyWise(statusid,uEmail));
				List<?> data=helpDeskTicketDAO.getInvalidPendingForSupportTeam(uEmail);
				for(Iterator<?> iterator=data.iterator();iterator.hasNext();){
					Object[] objects=(Object[]) iterator.next();
					Map<String, Object> mapdata=new HashMap<>();
					mapdata.put("docketNumber", (int)objects[0]);
					String str[]={"Pending for Registration","Registered&Assigned","On Hold","Resolved","Reopen"};
					mapdata.put("docketStatus", str[(int)objects[1]]);
					mapdata.put("docketCreatedDt", new SimpleDateFormat("dd/MM/yyyy hh:mm a").format((Timestamp)objects[2]));
					if(((String)objects[3])==null || ((String)objects[3]).equals("")){
						mapdata.put("consumerName", "");
					}else{
						mapdata.put("consumerName", (String)objects[3]);
					}
					if((Long)objects[4]==0){
						mapdata.put("consumerMobileNo", "");
					}else{
						mapdata.put("consumerMobileNo", (Long)objects[4]);
					}
					if(objects[5]==null || ((String)objects[5]).equals("")){
						mapdata.put("consumerEmailId", "");
					}else{
						mapdata.put("consumerEmailId", (String)objects[5]);
					}
					
					mapdata.put("categoryName", "");
					mapdata.put("subCategoryName", "");
					mapdata.put("pending", "");
					mapdata.put("estResolveTime", "");
					mapdata.put("resolvedDate", "");
					mapdata.put("assinedName", "");
					mapdata.put("assignedTo", "");
					mapdata.put("designation", "");
					mapdata.put("officialMobileNo", "");
					result.add(mapdata);
				}
			return result;
			}
		}else if(statusid.size()>2){
			List<Map<String, Object>> result=(List<Map<String, Object>>) getComplaints1(helpDeskTicketDAO.getSupportTeamMonthyWise(statusid,uEmail));
			List<?> data=helpDeskTicketDAO.getInvalidPendingForSupportTeam(uEmail);
			for(Iterator<?> iterator=data.iterator();iterator.hasNext();){
				Object[] objects=(Object[]) iterator.next();
				Map<String, Object> mapdata=new HashMap<>();
				mapdata.put("docketNumber", (int)objects[0]);
				String str[]={"Pending for Registration","Registered&Assigned","On Hold","Resolved","Reopen"};
				mapdata.put("docketStatus", str[(int)objects[1]]);
				mapdata.put("docketCreatedDt", new SimpleDateFormat("dd/MM/yyyy hh:mm a").format((Timestamp)objects[2]));
				if(((String)objects[3])==null || ((String)objects[3]).equals("")){
					mapdata.put("consumerName", "");
				}else{
					mapdata.put("consumerName", (String)objects[3]);
				}
				if((Long)objects[4]==0){
					mapdata.put("consumerMobileNo", "");
				}else{
					mapdata.put("consumerMobileNo", (Long)objects[4]);
				}
				if(objects[5]==null || ((String)objects[5]).equals("")){
					mapdata.put("consumerEmailId", "");
				}else{
					mapdata.put("consumerEmailId", (String)objects[5]);
				}
				
				mapdata.put("categoryName", "");
				mapdata.put("subCategoryName", "");
				mapdata.put("pending", "");
				mapdata.put("estResolveTime", "");
				mapdata.put("resolvedDate", "");
				mapdata.put("assinedName", "");
				mapdata.put("assignedTo", "");
				mapdata.put("designation", "");
				mapdata.put("officialMobileNo", "");
				result.add(mapdata);
			}
		return result;
		}
		return getComplaints1(helpDeskTicketDAO.getSupportTeamMonthyWise(statusid,uEmail));
	}

	@Override
	public List<?> getDocketDetailsBasedOnStatusForPending(
			Set<String> projectIds, int status) {
		 List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		 Map<String, Object> docketMap = null;
		 for (Iterator<?> iterator = helpDeskTicketDAO.pendingForRegistrationTicketsBasedOnSectionWise(projectIds,status).iterator(); iterator.hasNext();)
			{
				final Object[] values = (Object[]) iterator.next();
				docketMap = new HashMap<String, Object>();
				
				docketMap.put("docketNumber", (int)values[0]);
				docketMap.put("docketSource", (String)values[1]);
				docketMap.put("docketCreatedDt", new SimpleDateFormat("dd/MM/yyyy hh:mm a").format((Timestamp)values[2]));
				if(((String)values[3])==null || ((String)values[3]).equals("")){
					docketMap.put("consumerName", "");
				}else{
					docketMap.put("consumerName", (String)values[3]);
				}
				if((Long)values[4]==0){
					docketMap.put("consumerMobileNo", "");
				}else{
					docketMap.put("consumerMobileNo", (Long)values[4]);
				}
				if(((String)values[5])==null || ((String)values[5]).equals("")){
					docketMap.put("consumerEmailId", "");
				}else{
					docketMap.put("consumerEmailId", (String)values[5]);
				}
				if(((String)values[6])==null || ((String)values[6]).equals("")){
					docketMap.put("facebookId", "");
				}else{
					docketMap.put("facebookId", (String)values[6]);
				}
				//Added By Raju
				
				java.util.Date currentDate=new java.util.Date();
				Date creatdt =  (Timestamp) values[2];
				DateTime dt1 = null;
				DateTime dt2 =null;
				if(currentDate.after(creatdt)){
					dt2 = new DateTime(creatdt);
					dt1 = new DateTime(currentDate);
				}else{
					dt1= new DateTime(creatdt);
					dt2 = new DateTime(currentDate);
				}
				
				final Period period = new Period(dt2,dt1);
				String noOfMonths="";
				if(period.getYears()!=0){
					if(period.getYears()==1){
						noOfMonths=period.getYears()+" Year, ";
					}else{
					noOfMonths=period.getYears()+" Years, ";
					}
				}
				if(period.getMonths()!=0){
					if(period.getMonths()==1){
						noOfMonths=noOfMonths+""+period.getMonths()+" Month, ";
					}else{
					noOfMonths=noOfMonths+""+period.getMonths()+" Months, ";
					}
				}
				if(period.getWeeks()!=0){
					if(period.getWeeks()==1){
						noOfMonths=noOfMonths+""+period.getWeeks()+" Week, ";
					}else{
						noOfMonths=noOfMonths+""+period.getWeeks()+" Weeks, ";
					}
				}
				noOfMonths=noOfMonths+" "+period.getDays()+" Days, "+period.getHours()+" Hours";
				docketMap.put("elapsedTime", noOfMonths);
			
			result.add(docketMap);
			}
		 return result;
	
	}

	@Override
	public List<?> getTodayComplaints(Integer status) {
		
		Date todayDate = new Date();
		Calendar cal = Calendar.getInstance();
	    cal.setTime(todayDate);
	    int year = cal.get(Calendar.YEAR);
	    int m = cal.get(Calendar.MONTH);
	    int month = m+1;
	    int day = cal.get(Calendar.DAY_OF_MONTH);
	    
	    if(status==0){
	    List<?> complaints = helpDeskTicketDAO.getTodayPendingComplaints(status,day,month,year);
	    List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		 Map<String, Object> docketMap = null;
		 for (Iterator<?> iterator = complaints.iterator(); iterator.hasNext();)
			{
				final Object[] values = (Object[]) iterator.next();
				docketMap = new HashMap<String, Object>();
				
				docketMap.put("docketNumber", (int)values[0]);
				docketMap.put("docketSource", (String)values[1]);
				docketMap.put("docketCreatedDt", new SimpleDateFormat("MM/dd/yyyy hh:mm a").format((Timestamp)values[2]));
				if(((String)values[3])==null || ((String)values[3]).equals("")){
					docketMap.put("consumerName", "");
				}else{
					docketMap.put("consumerName", (String)values[3]);
				}
				if((Long)values[4]==0){
					docketMap.put("consumerMobileNo", "");
				}else{
					docketMap.put("consumerMobileNo", (Long)values[4]);
				}
				if(((String)values[5])==null || ((String)values[5]).equals("")){
					docketMap.put("consumerEmailId", "");
				}else{
					docketMap.put("consumerEmailId", (String)values[5]);
				}
				if(((String)values[6])==null || ((String)values[6]).equals("")){
					docketMap.put("facebookId", "");
				}else{
					docketMap.put("facebookId", (String)values[6]);
				}
				
				java.util.Date currentDate=new java.util.Date();
				Date creatdt =  (Timestamp) values[2];
				DateTime dt1 = null;
				DateTime dt2 =null;
				if(currentDate.after(creatdt)){
					dt2 = new DateTime(creatdt);
					dt1 = new DateTime(currentDate);
				}else{
					dt1= new DateTime(creatdt);
					dt2 = new DateTime(currentDate);
				}
				
				final Period period = new Period(dt2,dt1);
				String noOfMonths="";
				noOfMonths=period.getHours()+" Hours";
				docketMap.put("elapsedTime", noOfMonths);
			
			result.add(docketMap);
			}
		 return result;
	    } else if(status!=5 && status!=6){
	    	 List<?> complaints= helpDeskTicketDAO.getTodayComplaints(status,day,month,year);
	    	 return readData(complaints);
	    }else if(status==5) {
	    	
	    	List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
			 Map<String, Object> docketMap = null;
			 for (Iterator<?> iterator = helpDeskTicketDAO.getTodayEscalatedComplaints(status,day,month,year).iterator(); iterator.hasNext();)
				{
					final Object[] values = (Object[]) iterator.next();
					docketMap = new HashMap<String, Object>();
					
					docketMap.put("docketNumber", (int)values[0]);
					if(((int)values[1])==0){
						docketMap.put("docketStatus", "Pending for Registration");
					}else if(((int)values[1])==1){
						docketMap.put("docketStatus", "Registered&Assigned");
					}else if(((int)values[1])==2){
						docketMap.put("docketStatus", "On Hold");
					}else if(((int)values[1])==3){
						docketMap.put("docketStatus", "Resolved");
					}else if(((int)values[1])==4){
						docketMap.put("docketStatus", "Reopened");
					}
					docketMap.put("docketCreatedDt", new SimpleDateFormat("dd/MM/yyyy hh:mm a").format((Timestamp)values[2]));
					docketMap.put("escalatedDate", new SimpleDateFormat("dd/MM/yyyy hh:mm a").format((Timestamp)values[3]));
					docketMap.put("estResolveTime", new SimpleDateFormat("dd/MM/yyyy hh:mm a").format((Timestamp)values[4]));
					docketMap.put("level", (Integer)values[5]);
					docketMap.put("fromUser", (String)values[6]);
					docketMap.put("toUser", (String)values[7]);
					docketMap.put("OfficerName", (String)values[8]);
					docketMap.put("officerMobileNo", (String)values[9]);
					docketMap.put("office", (String)values[10]);
					
					result.add(docketMap);
		     }
			 return result;
	    }else if(status==6){
	    	 List<?> complaints= helpDeskTicketDAO.getAllTodayComplaints(status,day,month,year);
	    	 List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
	 		Map<String, Object> docketMap = null;
	 		for (Iterator<?> iterator = complaints.iterator(); iterator.hasNext();)
	 		{
	 			final Object[] values = (Object[]) iterator.next();
	 			docketMap = new HashMap<String, Object>();
	 			/*c.docketNumber,c.docketStatus,c.docketSource,c.consumerName,c.consumerMobileNo,c.rrNumber,
	 			c.address,c.remarks,c.userName,c.docketCreatedDt,c.resolvedDate,c.docketSummary,c.assignedTo*/
	 			
	 			docketMap.put("docketNumber", (int)values[0]);
	 			String str[]={"Pending for Registration","Registered/Assigned","On Hold","Resolved","Reopen"};
	 			docketMap.put("docketStatus", str[(int)values[1]]);
	 			docketMap.put("docketSource", (String)values[2]);
	 			docketMap.put("consumerName", (String)values[3]);
	 			if(values[4]!=null){
	 				docketMap.put("consumerMobileNo", (Long)values[4]);
	 			} else{
	 				docketMap.put("consumerMobileNo", "");
	 			}
	 			docketMap.put("rrNumber", (String)values[5]);
	 			docketMap.put("address", (String)values[6]);
	 			docketMap.put("remarks", (String)values[7]);
	 			docketMap.put("userName", (String)values[8]);
	 			docketMap.put("docketCreatedDt", new SimpleDateFormat("MM/dd/yyyy HH:mm:ss aa").format((Timestamp)values[9]));
	 			try{
	 				docketMap.put("resolvedDate", new SimpleDateFormat("MM/dd/yyyy HH:mm:ss aa").format((Timestamp)values[10]));
	 			}catch(Exception e){
	 				docketMap.put("resolvedDate", "");
	 			}
	 			
	 			docketMap.put("docketSummary", (String)values[11]);
	 			result.add(docketMap);
	 		}
	 		return result;
	    }
		
	    
	    
	    return null;
	}

	@Override
	public List<Map<String, Object>> searchDocketRRNumber(String rrNumber) {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		 Map<String, Object> docketMap = null;
		 for (Iterator<?> iterator = helpDeskTicketDAO.searchDocketRRNumber(rrNumber).iterator(); iterator.hasNext();)
			{
				final Object[] values = (Object[]) iterator.next();
				docketMap = new HashMap<String, Object>();
				
				docketMap.put("docketNumber", (int)values[0]);
				if(((int)values[1])==0){
					docketMap.put("docketStatus", "Pending for Registration");
				}else if(((int)values[1])==1){
					docketMap.put("docketStatus", "Registered&Assigned");
				}else if(((int)values[1])==2){
					docketMap.put("docketStatus", "On Hold");
				}else if(((int)values[1])==3){
					docketMap.put("docketStatus", "Resolved");
				}else if(((int)values[1])==4){
					docketMap.put("docketStatus", "Reopened");
				}
				docketMap.put("docketSource", (String)values[2]);
				docketMap.put("consumerName", (String)values[3]);
				docketMap.put("consumerMobileNo", (Long)values[4]);
				docketMap.put("rrNumber", (String)values[5]);
				docketMap.put("address", (String)values[6]);
				docketMap.put("categoryName", (String)values[7]);
				docketMap.put("subCategoryName", (String)values[8]);
				docketMap.put("urName", (String)values[9]);
				docketMap.put("userName", (String)values[10]);
				docketMap.put("docketCreatedDt", new SimpleDateFormat("dd/MM/yyyy hh:mm a").format((Timestamp)values[11]));
				try{
					docketMap.put("resolvedDate", new SimpleDateFormat("dd/MM/yyyy hh:mm a").format((Timestamp)values[12]));
				}catch(Exception e){
					docketMap.put("resolvedDate", "");
				}
				
				docketMap.put("docketSummary", (String)values[13]);
				docketMap.put("divisionName", (String)values[14]);
				docketMap.put("subDivisionName", (String)values[15]);
				
				if((Long)values[16]==null ||(Long)values[16]==0){
					docketMap.put("alternativeMobileNo", "");
				}else{
					docketMap.put("alternativeMobileNo", (Long)values[16]);
				}
				
				if((String)values[17]==null ||((String)values[17]).equals("")){
					docketMap.put("consumerEmailId", "");
				}else{
					docketMap.put("consumerEmailId", (String)values[17]);
				}
				
				if((String)values[18]==null ||((String)values[18]).equals("")){
					docketMap.put("facebookId", "");
				}else{
					docketMap.put("facebookId", (String)values[18]);
				}
				
				if((String)values[19]==null ||((String)values[19]).equals("")){
					docketMap.put("landMark", "");
				}else{
					docketMap.put("landMark", (String)values[19]);
				}
				
				try{
					docketMap.put("estResolveTime", new SimpleDateFormat("dd/MM/yyyy hh:mm a").format((Timestamp)values[20]));
				}catch(Exception e){
					docketMap.put("estResolveTime", "");
				}
				
				if((String)values[21]==null ||((String)values[21]).equals("")){
					docketMap.put("designation", "");
				}else{
					docketMap.put("designation", (String)values[21]);
				}
				
				if((String)values[22]==null ||((String)values[22]).equals("")){
					docketMap.put("assinedName", "");
				}else{
					docketMap.put("assinedName", (String)values[22]);
				}	
				
				if((String)values[23]==null ||((String)values[23]).equals("")){
					docketMap.put("officialMobileNo", "");
				}else{
					docketMap.put("officialMobileNo", (String)values[23]);
				}
				
				docketMap.put("docketUpdatedDt", new SimpleDateFormat("dd/MM/yyyy hh:mm a").format((Timestamp)values[24]));
				
				if((String)values[25]==null ||((String)values[25]).equals("")){
					docketMap.put("remarks", "");
				}else{
					docketMap.put("remarks", (String)values[25]);
				}
				docketMap.put("invalidFlag", (String)values[26]);
			result.add(docketMap);
	     }
		 return result;
	}

	@Override
	public List<?> readInAllMyComplaintDetails(Set<Integer> statusid,String uEmail) {
		return getComplaints1(helpDeskTicketDAO.readInAllMyComplaintDetails(statusid,uEmail));
	}

	@Override
	public List<?> getMyComplaintsDetails(Date fdate, Date tdate,Set<Integer> statusid, String uEmail) {
		return getComplaints1(helpDeskTicketDAO.getMyComplaintsDetails(fdate,tdate,statusid,uEmail));
	}

	@Override
	public List<?> getSupportTeamBaseOnDate(Date fdate, Date tdate) {
		return readListIntoMap1(helpDeskTicketDAO.getSupportBasedDateDetails(fdate,tdate));
	}
	
	@Async
	private void sendMailAndSmsEscalatedComplaintsCodeForOfficial(HelpDeskTicketEntity ticketEntity,String toUserLogin,TicketEscalationEntity ticketEscalationEntity){
		
		EmailCredentialsDetailsBean emailCredentialsDetailsBean = null;
		try {
			emailCredentialsDetailsBean = new EmailCredentialsDetailsBean();
		} catch (Exception e) {
			e.printStackTrace();
		}
		SmsCredentialsDetailsBean smsCredentialsDetailsBean = new SmsCredentialsDetailsBean();
		
		Object[] officialDetails = helpDeskTicketDAO.getOfficialDetailsBasedOnLoginName(toUserLogin);

		int docketNo = ticketEntity.getDocketNumber();
		String officialName = (String)officialDetails[0];
		String officialContactNo = (String)officialDetails[1];
		String officialEmail = (String)officialDetails[2];
		
		String messageContentForOfficial = "<html>"						   
							+ "<style type=\"text/css\">"
							+ "table.hovertable {"
							+ "font-family: verdana,arial,sans-serif;"
							+ "font-size:11px;"
							+ "color:#333333;"
							+ "border-width: 1px;"
							+ "border-color: #999999;"
							+ "border-collapse: collapse;"
							+ "}"
							+ "table.hovertable th {"
							+ "background-color:#c3dde0;"
							+ "border-width: 1px;"
							+ "padding: 8px;"
							+ "border-style: solid;"
							+ "border-color: #394c2e;"
							+ "}"
							+ "table.hovertable tr {"
							+ "background-color:#88ab74;"
							+ "}"
							+ "table.hovertable td {"
							+ "border-width: 1px;"
							+ "padding: 8px;"
							+ "border-style: solid;"
							+ "border-color: #394c2e;"
							+ "}"
							+ "</style>"
							+ "<h2  align=\"center\"  style=\"background-color:#88ab74;\">BCITS PGRS Services</h2> <br /> Dear "+officialName+", <br/> <br/> "
							+"This email is to inform you that ticket with docket number "+ticketEntity.getDocketNumber()+" has been escalated to you.<br>Kindly click on the below link to access it.<br><a href=http://"+ emailCredentialsDetailsBean.getTrackComplaintOfficial()+">click to login</a><br/><br/>"
							+"Respond as early as possible.<br>"
							+"<br/><br/><br/> Please do not reply to this mail as this is an automated mail service."
							+"</body></html>";
					
		emailCredentialsDetailsBean.setToAddressEmail(officialEmail);
		emailCredentialsDetailsBean.setMessageContent(messageContentForOfficial);
		emailCredentialsDetailsBean.setMailSubject("This mail regarding ticket escalation");
		
		String messageOfficial = docketNo+" has been escalated to you";
		smsCredentialsDetailsBean.setNumber(officialContactNo);
		smsCredentialsDetailsBean.setUserName(officialName);
		smsCredentialsDetailsBean.setMessage(messageOfficial);
		
		if(ticketEscalationEntity.getNotificationType().equals("All")){			
			officialsSmsCode(smsCredentialsDetailsBean);
			officialsEmailCode(emailCredentialsDetailsBean);
			
		} else if(ticketEscalationEntity.getNotificationType().equals("Sms")){					
			officialsSmsCode(smsCredentialsDetailsBean);
		} else if(ticketEscalationEntity.getNotificationType().equals("Email")){
			officialsEmailCode(emailCredentialsDetailsBean);
		}
		
	}

	@Override
	public List<Map<String, Object>> getTicketDataBasedOnStatus(int status) {

		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		 Map<String, Object> docketMap = null;
		 for (Iterator<?> iterator = helpDeskTicketDAO.getTicketDataBasedOnStatus(status).iterator(); iterator.hasNext();)
			{
				final Object[] values = (Object[]) iterator.next();
				docketMap = new HashMap<String, Object>();
				
				docketMap.put("docketNumber", (int)values[0]);
				if(((int)values[1])==1){
					docketMap.put("docketStatus", "Registered&Assigned");
					docketMap.put("docketUpdatedDt", "");
				}else if(((int)values[1])==2){
					docketMap.put("docketStatus", "On Hold");
					docketMap.put("docketUpdatedDt", "");
				}else if(((int)values[1])==3){
					docketMap.put("docketStatus", "Resolved");
					docketMap.put("docketUpdatedDt", "");
				}else if(((int)values[1])==4){
					docketMap.put("docketStatus", "Reopened");
					docketMap.put("docketUpdatedDt", new SimpleDateFormat("dd/MM/yyyy hh:mm a").format((Timestamp)values[12]));
				}
				docketMap.put("categoryName", (String)values[2]);
				docketMap.put("subCategoryName", (String)values[3]);
				docketMap.put("urName", (String)values[4]);
				docketMap.put("userName", (String)values[5]);
				docketMap.put("docketCreatedDt", new SimpleDateFormat("dd/MM/yyyy hh:mm a").format((Timestamp)values[6]));
				try{
					docketMap.put("resolvedDate", new SimpleDateFormat("dd/MM/yyyy hh:mm a").format((Timestamp)values[7]));
				}catch(Exception e){
					docketMap.put("resolvedDate", "");
				}
				
				docketMap.put("docketSummary", (String)values[8]);
				
				try{
					docketMap.put("estResolveTime", new SimpleDateFormat("dd/MM/yyyy hh:mm a").format((Timestamp)values[9]));
				}catch(Exception e){
					docketMap.put("estResolveTime", "");
				}		
				
				docketMap.put("designation", (String)values[10]);
			
			    result.add(docketMap);
		 }
		 return result;
	}

	@Override
	public List<Map<String, Object>> getTicketDataBasedOnStatusMobile(int status,String sitecode) {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		 Map<String, Object> docketMap = null;
		 for (Iterator<?> iterator = helpDeskTicketDAO.getTicketDataBasedOnStatusMobile(status,sitecode).iterator(); iterator.hasNext();)
			{
				final Object[] values = (Object[]) iterator.next();
				docketMap = new HashMap<String, Object>();
				
				docketMap.put("docketNumber", (int)values[0]);
				if(((int)values[1])==1){
					docketMap.put("docketStatus", "Registered&Assigned");
					docketMap.put("docketUpdatedDt", "");
				}else if(((int)values[1])==2){
					docketMap.put("docketStatus", "On Hold");
					docketMap.put("docketUpdatedDt", "");
				}else if(((int)values[1])==3){
					docketMap.put("docketStatus", "Resolved");
					docketMap.put("docketUpdatedDt", "");
				}else if(((int)values[1])==4){
					docketMap.put("docketStatus", "Reopened");
					docketMap.put("docketUpdatedDt", new SimpleDateFormat("dd/MM/yyyy hh:mm a").format((Timestamp)values[12]));
				}
				docketMap.put("categoryName", (String)values[2]);
				docketMap.put("subCategoryName", (String)values[3]);
				docketMap.put("urName", (String)values[4]);
				docketMap.put("userName", (String)values[5]);
				docketMap.put("docketCreatedDt", new SimpleDateFormat("dd/MM/yyyy hh:mm a").format((Timestamp)values[6]));
				try{
					docketMap.put("resolvedDate", new SimpleDateFormat("dd/MM/yyyy hh:mm a").format((Timestamp)values[7]));
				}catch(Exception e){
					docketMap.put("resolvedDate", "");
				}
				
				docketMap.put("docketSummary", (String)values[8]);
				
				try{
					docketMap.put("estResolveTime", new SimpleDateFormat("dd/MM/yyyy hh:mm a").format((Timestamp)values[9]));
				}catch(Exception e){
					docketMap.put("estResolveTime", "");
				}		
				
				docketMap.put("designation", (String)values[10]);
				docketMap.put("docketSource", (String)values[15]);
				docketMap.put("consumerName", (String)values[16]);
				if(values[17]!=null){
					docketMap.put("consumerMobileNo", (Long)values[17]);
				} else{
					docketMap.put("consumerMobileNo", "");
				}
				docketMap.put("consumerEmailId", (String)values[18]);
				docketMap.put("section", (String)values[19]);
			    result.add(docketMap);
		 }
		 return result;
	}
	
	@Override
	public List<Map<String, Object>> getTicketDataBasedOnStatusMobile2(String sitecode) {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		 Map<String, Object> docketMap = null;
		 for (Iterator<?> iterator = helpDeskTicketDAO.getTicketDataBasedOnStatusMobile2(sitecode).iterator(); iterator.hasNext();)
			{
				final Object[] values = (Object[]) iterator.next();
				docketMap = new HashMap<String, Object>();
				
				docketMap.put("docketNumber", (int)values[0]);
				if(((int)values[1])==1){
					docketMap.put("docketStatus", "Registered&Assigned");
					docketMap.put("docketUpdatedDt", "");
				}else if(((int)values[1])==2){
					docketMap.put("docketStatus", "On Hold");
					docketMap.put("docketUpdatedDt", "");
				}else if(((int)values[1])==3){
					docketMap.put("docketStatus", "Resolved");
					docketMap.put("docketUpdatedDt", "");
				}else if(((int)values[1])==4){
					docketMap.put("docketStatus", "Reopened");
					docketMap.put("docketUpdatedDt", new SimpleDateFormat("dd/MM/yyyy hh:mm a").format((Timestamp)values[12]));
				}
				docketMap.put("categoryName", (String)values[2]);
				docketMap.put("subCategoryName", (String)values[3]);
				docketMap.put("urName", (String)values[4]);
				docketMap.put("userName", (String)values[5]);
				docketMap.put("docketCreatedDt", new SimpleDateFormat("dd/MM/yyyy hh:mm a").format((Timestamp)values[6]));
				try{
					docketMap.put("resolvedDate", new SimpleDateFormat("dd/MM/yyyy hh:mm a").format((Timestamp)values[7]));
				}catch(Exception e){
					docketMap.put("resolvedDate", "");
				}
				
				docketMap.put("docketSummary", (String)values[8]);
				
				try{
					docketMap.put("estResolveTime", new SimpleDateFormat("dd/MM/yyyy hh:mm a").format((Timestamp)values[9]));
				}catch(Exception e){
					docketMap.put("estResolveTime", "");
				}		
				
				docketMap.put("designation", (String)values[10]);
				docketMap.put("docketSource", (String)values[15]);
				docketMap.put("consumerName", (String)values[16]);
				if(values[17]!=null){
					docketMap.put("consumerMobileNo", (Long)values[17]);
				} else{
					docketMap.put("consumerMobileNo", "");
				}
				docketMap.put("consumerEmailId", (String)values[18]);
				docketMap.put("section", (String)values[19]);
			    result.add(docketMap);
		 }
		 return result;
	}


}
