package com.pgrs.service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import com.pgrs.entity.HelpDeskTicketEntity;

public interface HelpDeskTicketService {

	String saveTicketInfo(HelpDeskTicketEntity ticketEntity);


	int getTicketsCountBasedOnStatus(Set<Integer> statusSet, String userId);

	List<?> docketDetailsRead(String loginName,Set<Integer> statusSet);

	void updateDocket(HelpDeskTicketEntity ticketEntity, HttpSession session);

	List<Map<String, Object>> searchDocketHistory(int docketNumber);

	List<Map<String, Object>> searchDocketEscHistory(int docketNumber);

	int getPendingTicketsCount(int i);

	int getescalatedTicketsCountBasedOnLogin(String loginName);

	List<Map<String, Object>> pendingForRegistrationTickets();

	void update(HelpDeskTicketEntity helpdeskBean);

	List<Map<String, Object>> searchMobileNumber(long mobileNo);

	List<?> getTodaysComplaints(Date date);

	List<?> getNotificationDataBasedOnStatus(Set<Integer> statusSet,String loginName);

	List<?> getPendingNotificationDataBasedOnStatus(Set<Integer> statusSet);

	List<?> getComplaints(List<?> list);

	List<?> getAllResolvedComplaints();

	void submitFeedbackcomments(HelpDeskTicketEntity ticketEntity);

	List<?> searchEsclation(String sitecode);

	void escalationSheduler();

	int getescalatedTicketsCount();

	List<?> escalatedNotifications(String loginName);

	List<?> getlocationWiseDetails(Set<String> projectIds);

	List<?> getAllEscalatedTickets(String loginName);

	List<?> showAllEscalatedNotificationsForCCCD();

	List<?> getAllEscalatedTicketsForCCCD();

	void updateDocketAsInvalidated(int docketNumber);

	List<?> searchEsclation(String sitecode, Date date1, Date date2);

	List<?> getEscalationAnalysisDetails(String siteCode, String fromDate,
			String toDate, Integer level);

	List<?> getDocketDetailsBasedOnStatus(Set<String> projectIds, int status) throws ParseException;

	List<?> readDetailsInMap(List<?> list) throws ParseException;

	Map<String, Object> getCircleWiseData(int circleId, String text, Date date,Integer categoryId, Integer subCategoryId);

	Map<String, Integer> getDayWiseComplaints();

	Map<String, String> getComplaintAgingStatistics();

	void saveTicketFromMobil(HelpDeskTicketEntity complEntity);

	Map<String, String> getComplaintAgingForPendingResovtion();

	List<?> getSupportTeamDetails();

	List<?> getSupportTeamBaseOnDate(Date fdate, Date tdate, String userName);

	List<?> getMycomplaints(String loginName);

	List<?> getteamDetails(Date fdate, Date tdate, Set<Integer> statusid,String uemail);

	List<?> getTeamWiseMothlyWise(Set<Integer> statusid, String uEmail);

	List<?> getDocketDetailsBasedOnStatusForPending(Set<String> projectIds,
			int status);

	List<?> getTodayComplaints(Integer status);

	List<Map<String, Object>> searchDocketRRNumber(String rrNumber);

	List<?> readInAllMyComplaintDetails(Set<Integer> statusid, String uEmail);

	List<?> getMyComplaintsDetails(Date fdate, Date tdate,Set<Integer> statusid, String uEmail);

	List<?> getSupportTeamBaseOnDate(Date fdate, Date tdate);

	List<Map<String, Object>> getTicketDataBasedOnStatus(int status);

	List<Map<String, Object>> getTicketDataBasedOnStatusMobile(int status,String sitecode);
	
	List<Map<String, Object>> getTicketDataBasedOnStatusMobile2(String sitecode);

	List<Map<String, Object>> searchDocketNumber(int docketNumber, String sitecode);

}
