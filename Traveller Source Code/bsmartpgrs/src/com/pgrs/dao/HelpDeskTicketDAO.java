package com.pgrs.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.pgrs.entity.HelpDeskTicketEntity;
import com.pgrs.entity.ProjectHeirarchyEntity;
import com.pgrs.entity.TicketEscalationEntity;

public interface HelpDeskTicketDAO extends GenericDAO<HelpDeskTicketEntity> {

	List<Map<String, Object>> searchDocketNumber(int docketNumber,String sitecode);

	List<TicketEscalationEntity> getIntialLevelDesignation(int subCategoryId,Set<String> assignmentTypeSet);

	List<?> getUserLoginNamesBasedOnDesignation(Integer integer);

	Long getTicketsCountBasedOnStatus(Set<Integer> statusSet, String urLoginName);

	Object[] getOfficialDetailsBasedOnLoginName(String assignedTo);

	List<?> docketDetailsRead(String loginName,Set<Integer> statusSet);

	List<Integer> getCategoryList(int docketNumber);

	List<?> searchDocketNumberSourceSms(int docketNumber);

	List<Integer> getCategoryListFromTransactionHistory(int docketNumber);

	List<?> searchDocketHistorySourceSms(int docketNumber);

	List<?> searchDocketHistory(int docketNumber);

	List<?> searchDocketEscHistory(int docketNumber);

	Long getPendingTicketsCount(int status);

	Long getescalatedTicketsCount();

	List<?> pendingForRegistrationTickets();

	List<?> searchMobileNumber(long mobileNo);

	List<?> getTodaysComplaints(Date date);

	List<?> getNotificationDataBasedOnStatus(Set<Integer> statusSet,String loginName);

	List<?> getPendingNotificationDataBasedOnStatus(Set<Integer> statusSet);

	List<?> getAllResolvedComplaints();

	List<?> searchEsclation(String sitecode);

	List<?> getAllTicketsForEscalationProcess();

	Long getescalatedTicketsCountBasedOnLogin(String loginName);

	List<?> escalatedNotifications(String loginName);

	List<?> getlocationWiseDetails(Set<String> projectIds);

	List<?> getAllEscalatedTickets(String loginName);

	List<?> showAllEscalatedNotificationsForCCCD();

	List<?> getAllEscalatedTicketsForCCCD();

	void updateDocketAsInvalidated(int docketNumber);

	List<?> searchEsclation(String sitecode, Date date1, Date date2);

	List<?> getEscalationAnalysisDetails(Set<String> sitecodes, Date date1, Date date2,Integer level);

	List<?> getDocketDetailsBasedOnStatusAndProjectId(Set<String> projectIds,int status);


	List<?> getCircleWiseData(Set<String> sitecodes, Date date,Integer categoryId, Integer subCategoryId);

	ProjectHeirarchyEntity getParentProjectHeirarchyEntityBasedOnSiteCode(String siteCode);

	List<?> getComplaintAgingStatistics();

	Long getComplaintsCountBasedOnDayWise(int status, int day,int month,int year);

	Long getEscalatedComplaintsCountBasedOnDayWise(int day, int month,int year);

	List<?> getComplaintAgingForPendingResov();


	List<?> getSupprtTeamDetails();

	List<?> getSupportBasedDateDetails(Date fdate, Date tdate, String userName);

	List<?> readMyComplaints(String loginName);

	List<?> totalComplaints();

	List<?> escalatedComplaints();

	List<?> getteamDetails(Date fdate, Date tdate, Set<Integer> statusid,String uemail);

	List<?> getSupportTeamMonthyWise(Set<Integer> statusid, String uEmail);

	List<TicketEscalationEntity> getAllLevelDesignation(Integer integer);

	List<?> pendingForRegistrationTicketsBasedOnSectionWise(Set<String> projectIds, int status);

	List<?> getTodayComplaints(Integer status, int day, int month, int year);

	List<?> getTodayPendingComplaints(Integer status, int day, int month,int year);

	List<?> getTodayEscalatedComplaints(Integer status, int day, int month,int year);

	List<?> searchDocketRRNumber(String rrNumber);

	List<?> getAllTodayComplaints(Integer status, int day, int month, int year);

	List<?> readInAllMyComplaintDetails(Set<Integer> statusid, String uEmail);

	List<?> getInvalidPendingForSupportTeam(String uEmail);

	List<?> getInvalidTeamDetails(Date fdate, Date tdate, Set<Integer> statusid,String uemail);

	List<?> getMyComplaintsDetails(Date fdate, Date tdate,Set<Integer> statusid, String uEmail);

	List<?> getSupportBasedDateDetails(Date fdate, Date tdate);

	String getLocationClassificationTypeBasedOnSiteCode(String string);

	List<?> getTicketDataBasedOnStatus(int status);

	List<?> getTicketDataBasedOnStatusMobile(int status, String sitecode);
	
	List<?> getTicketDataBasedOnStatusMobile2(String sitecode);

}
