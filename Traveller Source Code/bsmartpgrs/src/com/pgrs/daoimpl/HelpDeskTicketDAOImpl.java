package com.pgrs.daoimpl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pgrs.core.dao.MasterGenericDAO;
import com.pgrs.dao.HelpDeskTicketDAO;
import com.pgrs.entity.HelpDeskTicketEntity;
import com.pgrs.entity.ProjectHeirarchyEntity;
import com.pgrs.entity.TicketEscalationEntity;
import com.pgrs.util.PgrsSchemaName;

@Repository
public class HelpDeskTicketDAOImpl extends GenericDAOImpl<HelpDeskTicketEntity> implements HelpDeskTicketDAO {

	@Autowired
	private MasterGenericDAO masterGenericDAO;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> searchDocketNumber(int docketNumber,String sitecode) {
		return entityManager.createNamedQuery("HelpDeskTicketEntity.searchDocketNumber").setParameter("docketNumber", docketNumber).setParameter("sitecode", sitecode).getResultList();
	}

	@Override
	public List<TicketEscalationEntity> getIntialLevelDesignation(int subCategoryId,Set<String> assignmentTypeSet) {
		return entityManager.createNamedQuery("HelpDeskTicketEntity.getIntialLevelDesignation",TicketEscalationEntity.class).setParameter("subCategoryId", subCategoryId).setParameter("assignmentTypeSet", assignmentTypeSet).getResultList();
	}

	@Override
	public List<?> getUserLoginNamesBasedOnDesignation(Integer dnId) {
		return entityManager.createNamedQuery("HelpDeskTicketEntity.getUserLoginNamesBasedOnDesignation").setParameter("dnId", dnId).getResultList();
	}

	@Override
	public Long getTicketsCountBasedOnStatus(Set<Integer> statusSet,String urLoginName) {
		return (Long)entityManager.createNamedQuery("HelpDeskTicketEntity.getTicketsCountBasedOnStatus").setParameter("statusSet", statusSet).setParameter("urLoginName", urLoginName).getSingleResult();
	}

	@Override
	public Object[] getOfficialDetailsBasedOnLoginName(String urLoginName) {
		return (Object[])entityManager.createNamedQuery("HelpDeskTicketEntity.getOfficialDetailsBasedOnLoginName").setParameter("urLoginName", urLoginName).getSingleResult();
	}

	@Override
	public List<?> docketDetailsRead(String urLoginName,Set<Integer> statusSet) {
		return entityManager.createNamedQuery("HelpDeskTicketEntity.docketDetailsRead").setParameter("urLoginName", urLoginName).setParameter("statusSet", statusSet).getResultList();
	}

	@Override
	public List<Integer> getCategoryList(int docketNumber) {
		return entityManager.createNamedQuery("HelpDeskTicketEntity.getCategoryList",Integer.class).setParameter("docketNumber", docketNumber).getResultList();
	}

	@Override
	public List<?> searchDocketNumberSourceSms(int docketNumber) {
		return entityManager.createNamedQuery("HelpDeskTicketEntity.searchDocketNumberSourceSms").setParameter("docketNumber", docketNumber).getResultList();
	}

	@Override
	public List<Integer> getCategoryListFromTransactionHistory(int docketNumber) {
		return entityManager.createNamedQuery("TicketTransactionEntity.getCategoryListFromTransactionHistory",Integer.class).setParameter("docketNumber", docketNumber).getResultList();
	}

	@Override
	public List<?> searchDocketHistorySourceSms(int docketNumber) {
		return entityManager.createNamedQuery("TicketTransactionEntity.searchDocketHistorySourceSms").setParameter("docketNumber", docketNumber).getResultList();
	}

	@Override
	public List<?> searchDocketHistory(int docketNumber) {
		return entityManager.createNamedQuery("TicketTransactionEntity.searchDocketHistory").setParameter("docketNumber", docketNumber).getResultList();
	}

	@Override
	public List<?> searchDocketEscHistory(int docketNumber) {
		return entityManager.createNamedQuery("TicketTransactionEntity.searchDocketEscHistory").setParameter("docketNumber", docketNumber).getResultList();
	}

	@Override
	public Long getPendingTicketsCount(int status) {
		return (Long)entityManager.createNamedQuery("HelpDeskTicketEntity.getPendingTicketsCount").setParameter("status", status).getSingleResult();
	}

	@Override
	public Long getescalatedTicketsCount() {
		return (Long)entityManager.createNamedQuery("HelpDeskTicketEntity.getescalatedTicketsCount").getSingleResult();
		/*String queryString="SELECT count(DISTINCT(ht.docketnumber)) FROM "+schemaname+".pgrs_escalated_complaints ec,"+schemaname+".pgrs_complaints ht WHERE ec.docketnumber=ht.docketnumber AND ht.docketstatus!=3 And ht.invalid_flag='No'";
		final Query query = this.entityManager.createNativeQuery(queryString);
		return ((BigInteger) query.getSingleResult()).longValue();*/
	}

	@Override
	public List<?> pendingForRegistrationTickets() {
		return entityManager.createNamedQuery("HelpDeskTicketEntity.pendingForRegistrationTickets").getResultList();
	}

	@Override
	public List<?> searchMobileNumber(long mobileNo) {
		return entityManager.createNamedQuery("HelpDeskTicketEntity.searchMobileNumber").setParameter("mobileNo", mobileNo).getResultList();
	}


	@Override
	public List<?> getTodaysComplaints(Date date) {
		return entityManager.createNamedQuery("HelpDeskTicketEntity.getTodaysComplaints").setParameter("date",date).getResultList(); 
	}

	public List<?> getNotificationDataBasedOnStatus(Set<Integer> statusSet,String loginName) {
		return entityManager.createNamedQuery("HelpDeskTicketEntity.getNotificationDataBasedOnStatus").setParameter("statusSet", statusSet).setParameter("loginName", loginName).getResultList();

	}

	@Override
	public List<?> getPendingNotificationDataBasedOnStatus(Set<Integer> statusSet) {
		return entityManager.createNamedQuery("HelpDeskTicketEntity.getPendingNotificationDataBasedOnStatus").setParameter("statusSet", statusSet).getResultList();
	}

	@Override
	public List<?> getAllResolvedComplaints() {
		return entityManager.createNamedQuery("HelpDeskTicketEntity.getAllResolvedComplaints").getResultList();
	}

	@Override
	public List<?> searchEsclation(String sitecode) {
		return entityManager.createNamedQuery("EscalatedComplaintsEntity.searchEsclatedComplaints").setParameter("sitecode", sitecode+"%").getResultList();
	}

	@Override
	public List<?> getAllTicketsForEscalationProcess() {
		return entityManager.createNamedQuery("HelpDeskTicketEntity.getAllTicketsForEscalationProcess").getResultList();
	}

	@Override
	public Long getescalatedTicketsCountBasedOnLogin(String loginName) {
		return (Long)entityManager.createNamedQuery("HelpDeskTicketEntity.getescalatedTicketsCountBasedOnLogin").setParameter("loginName", loginName).getSingleResult();
	}

	@Override
	public List<?> escalatedNotifications(String loginName) {
		return entityManager.createNamedQuery("HelpDeskTicketEntity.escalatedNotifications").setParameter("loginName", loginName).getResultList();
	}

	@Override
	public List<?> getlocationWiseDetails(Set<String> projectIds) {
		return entityManager.createNamedQuery("HelpDeskTicketEntity.getlocationWiseCount").setParameter("projectIds", projectIds).getResultList();
	}

	@Override
	public List<?> getAllEscalatedTickets(String loginName) {
		return entityManager.createNamedQuery("HelpDeskTicketEntity.getAllEscalatedTickets").setParameter("loginName", loginName).getResultList();
	}

	@Override
	public List<?> showAllEscalatedNotificationsForCCCD() {
		return entityManager.createNamedQuery("HelpDeskTicketEntity.showAllEscalatedNotificationsForCCCD").getResultList();
	}

	@Override
	public List<?> getAllEscalatedTicketsForCCCD() {
		return entityManager.createNamedQuery("HelpDeskTicketEntity.getAllEscalatedTicketsForCCCD").getResultList();
	}

	@Override
	public void updateDocketAsInvalidated(int docketNumber) {
		entityManager.createNamedQuery("HelpDeskTicketEntity.updateDocketAsInvalidated").setParameter("docketNumber", docketNumber).executeUpdate();
	}

	@Override
	public List<?> searchEsclation(String sitecode, Date date1, Date date2) {
		
		if(!(sitecode.equalsIgnoreCase("888888"))){
		  return entityManager.createNamedQuery("EscalatedComplaintsEntity.searchEsclatedComplaints1").setParameter("sitecode", sitecode+"%").setParameter("date1", date1).setParameter("date2", date2).getResultList();
		}else{
		  return entityManager.createQuery("SELECT COUNT(pc.docketNumber),COUNT(ec.level) as level,ec.level FROM EscalatedComplaintsEntity ec,HelpDeskTicketEntity pc WHERE pc.docketNumber=ec.docketNumber AND pc.siteCode!='"+sitecode+"%"+"' AND date(ec.escalatedDate)>=to_date('"+new SimpleDateFormat("dd/MM/yyyy").format(date1)+"','dd/MM/yyyy') AND date(ec.escalatedDate)<=to_date('"+new SimpleDateFormat("dd/MM/yyyy").format(date2)+"','dd/MM/yyyy') GROUP BY ec.level ORDER BY ec.level ASC").getResultList();
		}
	}

	@Override
	public List<?> getEscalationAnalysisDetails(Set<String> siteCodes, Date date1,Date date2, Integer level) {
		
		String sitecode="";
		Iterator<String> iter=siteCodes.iterator();
		while(iter.hasNext()){
			String str=iter.next();
			sitecode+="'"+str+"',";
		}
		sitecode="("+sitecode.substring(0,sitecode.length()-1)+")";
		
		if(level!=0){
		return masterGenericDAO.executeSimpleObjectQuery("SELECT t.docketNumber,t.docketStatus,t.docketCreatedDt,ec.escalatedDate,t.estResolveTime,ec.level,tc.categoryName,tsc.subCategoryName FROM EscalatedComplaintsEntity ec,HelpDeskTicketEntity t,TicketCategoryEntity tc,TicketSubCategoryEntity tsc WHERE t.docketNumber=ec.docketNumber AND t.categoryId=tc.categoryId AND t.subCategoryId=tsc.subCategoryId AND t.siteCode IN "+sitecode+" AND ec.level="+level+" AND date(ec.escalatedDate)>=to_date('"+new SimpleDateFormat("dd/MM/yyyy").format(date1)+"' ,'dd/MM/yyyy') AND date(ec.escalatedDate)<=to_date('"+new SimpleDateFormat("dd/MM/yyyy").format(date2)+"' ,'dd/MM/yyyy')");
		}else{
			return masterGenericDAO.executeSimpleObjectQuery("SELECT t.docketNumber,t.docketStatus,t.docketCreatedDt,ec.escalatedDate,t.estResolveTime,ec.level,tc.categoryName,tsc.subCategoryName FROM EscalatedComplaintsEntity ec,HelpDeskTicketEntity t,TicketCategoryEntity tc,TicketSubCategoryEntity tsc WHERE t.docketNumber=ec.docketNumber AND t.categoryId=tc.categoryId AND t.subCategoryId=tsc.subCategoryId AND t.siteCode IN "+sitecode+" AND ec.level!="+level+" AND date(ec.escalatedDate)>=to_date('"+new SimpleDateFormat("dd/MM/yyyy").format(date1)+"' ,'dd/MM/yyyy') AND date(ec.escalatedDate)<=to_date('"+new SimpleDateFormat("dd/MM/yyyy").format(date2)+"' ,'dd/MM/yyyy')");
		}
	}
	
	@Override
	public List<?> getDocketDetailsBasedOnStatusAndProjectId(Set<String> projectIds, int status) {
		return entityManager.createNamedQuery("HelpDeskTicketEntity.getDocketDetailsBasedOnStaus").setParameter("projecids", projectIds).setParameter("status", status).getResultList();
	}

	@Override
	public List<?> getCircleWiseData(Set<String> siteCodes, Date date,Integer categoryId, Integer subCategoryId) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int month=cal.get(Calendar.MONTH)+1;
		System.out.println(month);
		int year=cal.get(Calendar.YEAR);
		if(categoryId==0 && subCategoryId==0){
			return entityManager.createNamedQuery("HelpDeskTicketEntity.getCircleWiseData1").setParameter("month",month).setParameter("year",year).setParameter("siteCodes",siteCodes).getResultList();
		}else if(categoryId!=0 && subCategoryId==0){
			return entityManager.createNamedQuery("HelpDeskTicketEntity.getCircleWiseData2").setParameter("month",month).setParameter("year",year).setParameter("siteCodes",siteCodes).setParameter("categoryId",categoryId).getResultList();
		}else if(categoryId!=0 && subCategoryId!=0){
			return entityManager.createNamedQuery("HelpDeskTicketEntity.getCircleWiseData3").setParameter("month",month).setParameter("year",year).setParameter("siteCodes",siteCodes).setParameter("categoryId",categoryId).setParameter("subCategoryId",subCategoryId).getResultList();
		}
		return null;
	}

	@Override
	public ProjectHeirarchyEntity getParentProjectHeirarchyEntityBasedOnSiteCode(String siteCode) {
		return entityManager.createNamedQuery("HelpDeskTicketEntity.getParentProjectHeirarchyEntityBasedOnSiteCode",ProjectHeirarchyEntity.class).setParameter("siteCode", siteCode).getSingleResult();
	}

	@Override
	public List<?> getComplaintAgingStatistics() {
		return entityManager.createNamedQuery("HelpDeskTicketEntity.getComplaintAgingStatistics").getResultList();
	}

	@Override
	public Long getComplaintsCountBasedOnDayWise(int status, int day,int month,int year) {
		return (Long)entityManager.createNamedQuery("HelpDeskTicketEntity.getComplaintsCountBasedOnDayWise").setParameter("status", status).setParameter("day", day).setParameter("month", month).setParameter("year", year).getSingleResult();
		
	}

	@Override
	public Long getEscalatedComplaintsCountBasedOnDayWise(int day, int month, int year) {
		return (Long)entityManager.createNamedQuery("HelpDeskTicketEntity.getEscalatedComplaintsCountBasedOnDayWise").setParameter("day", day).setParameter("month", month).setParameter("year", year).getSingleResult();
	}

	@Override
	public List<?> getComplaintAgingForPendingResov() {
		return entityManager.createNamedQuery("HelpDeskTicketEntity.getCompaintsForAgingPendingforResovation").getResultList();
	}


	@Override
	public List<?> getSupprtTeamDetails() {
		String schemaname=PgrsSchemaName.cescPgrsSchema;
		/*String query="SELECT u.name,COUNT(case when c.docketStatus=0 then 1 END) AS ZERO,"
				+" COUNT(case when c.docketStatus=1 then 1 END) AS ONE,COUNT(case when c.docketStatus=2 then 1 END)" 
				+" AS TWO,COUNT(case when c.docketStatus=3 then 1 END) AS THREE,COUNT(case when c.docketstatus=4 then 1 END) AS" 
				+" FOUR,count(*)AS TOTAL,c.username FROM "+schemaname+".pgrs_complaint_transactions c,"+schemaname+".core_users u,"+schemaname+".core_department d WHERE c.username=u.ur_login_name " 
				+" AND u.dept_id=d.id AND d.dept_name='CCCD' AND extract(YEAR from docketcreateddatetime)=extract(YEAR from CURRENT_DATE)AND "
				+" extract(MONTH from docketcreateddatetime)=extract(MONTH from CURRENT_DATE)"
				+" GROUP BY u.name,c.username";*/
		String query="SELECT u.name,COUNT(case when c.docketStatus=0 then 1 END) AS ZERO,"
				+" COUNT(case when (c.docketStatus=1 AND c.invalid_flag='No') then 1 END) AS ONE,COUNT(case when (c.docketStatus=2AND c.invalid_flag='No') then 1 END)"
				+" AS TWO,COUNT(case when (c.docketStatus=3 AND c.invalid_flag='No') then 1 END) AS THREE,COUNT(case when (c.docketstatus=4 AND c.invalid_flag='No') then 1 END) AS"
				+" FOUR,count(*)AS TOTAL,c.username,COUNT(case when c.invalid_flag='Yes' then 1 END) AS Invalid FROM "+schemaname+".pgrs_complaint_transactions c,"+schemaname+".core_users u,"+schemaname+".core_department d WHERE c.username=u.ur_login_name"
				+" AND u.dept_id=d.id AND c.esc_flag!='Yes'" 
				+" GROUP BY u.name,c.username";
		return entityManager.createNativeQuery(query).getResultList();
	}

	@Override
	public List<?> getSupportBasedDateDetails(Date fdate, Date tdate,String userName) {
		String schemaname=PgrsSchemaName.cescPgrsSchema;
		String query="SELECT u.name,"
					 + "COUNT(case when (docketStatus=0 AND c.invalid_flag='No') then 1 END) AS ZERO,"
                     +"COUNT(case when (c.docketStatus=1 AND c.invalid_flag='No') then 1 END) AS ONE,"
                     +"COUNT(case when (c.docketStatus=2 AND c.invalid_flag='No') then 1 END) AS TWO,"
                     +"COUNT(case when (c.docketStatus=3 AND c.invalid_flag='No') then 1 END) AS THREE,"
                     +"COUNT(case when (c.docketStatus=4 AND c.invalid_flag='No') then 1 END) AS FOUR,count(*),c.username,"
                     + "COUNT(case when (c.invalid_flag='Yes') then 1 END) AS INVALID "
                     +"FROM "+schemaname+".pgrs_complaints c,"+schemaname+".core_users u,"+schemaname+".core_department d WHERE c.username=u.ur_login_name AND c.username=:loginName AND u.dept_id=d.id AND d.dept_name='CCCD' and c.esc_flag!='Yes'"
                     +"AND date(docketcreateddatetime)>=to_date('"+new SimpleDateFormat("dd/MM/yyyy").format(fdate)+"','dd/MM/yyyy') AND date(docketcreateddatetime)<=to_date('"+new SimpleDateFormat("dd/MM/yyyy").format(tdate)+"','dd/MM/yyyy')"
                     +"GROUP BY u.name,c.username";
		return entityManager.createNativeQuery(query).setParameter("loginName", userName).getResultList();
	}

	@Override
	public List<?> readMyComplaints(String loginName) {
		String schemaname=PgrsSchemaName.cescPgrsSchema;
		String query="SELECT u.name,"
				+ "COUNT(case when (docketStatus=0 AND c.invalid_flag='No') then 1 END) AS ZERO,"
                +"COUNT(case when (c.docketStatus=1 AND c.invalid_flag='No') then 1 END) AS ONE,"
                +"COUNT(case when (c.docketStatus=2 AND c.invalid_flag='No') then 1 END) AS TWO,"
                +"COUNT(case when (c.docketStatus=3 AND c.invalid_flag='No') then 1 END) AS THREE,"
                +"COUNT(case when (c.docketStatus=4 AND c.invalid_flag='No') then 1 END) AS FOUR,count(*),c.username,"
                + "COUNT(case when (c.invalid_flag='Yes') then 1 END) AS INVALID "
                +"FROM "+schemaname+".pgrs_complaint_transactions c,"+schemaname+".core_users u,"+schemaname+".core_department d WHERE c.username=u.ur_login_name AND u.ur_login_name=:loginName AND u.dept_id=d.id AND c.esc_flag!='Yes'"
                +"GROUP BY u.name,c.username";
		System.out.println(query);
		return entityManager.createNativeQuery(query).setParameter("loginName", loginName).getResultList();
	}


	@Override
	public List<?> totalComplaints() {
		return masterGenericDAO.executeSimpleObjectQuery("SELECT count(*),(SELECT COUNT(*) FROM HelpDeskTicketEntity p1 WHERE p1.escLevel=1 AND p1.docketStatus=3),(SELECT COUNT(*) FROM HelpDeskTicketEntity p2 WHERE p2.escLevel!=1 AND p2.docketStatus=3),COUNT(CASE WHEN p.docketStatus=3 THEN 1 END) FROM HelpDeskTicketEntity p");
	}

	@Override
	public List<?> escalatedComplaints() {
		return masterGenericDAO.executeSimpleObjectQuery("SELECT count(*),count(CASE when p.escLevel=1 then 1 END)as ONE,count(CASE when p.escLevel=2 then 2 END)as TWO,count(CASE when p.escLevel=3 then 3 END)as THREE,count(CASE when p.escLevel=4 then 4 END)as FOUR,count(CASE when p.escLevel=5 then 5 END)as FIVE,count(CASE when p.escLevel=6 then 6 END)as SIX,count(CASE when p.docketStatus=4 then 7 END)as SEVEN,count(CASE when p.docketStatus=2 then 8 END)as EIGHT FROM HelpDeskTicketEntity p");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<?> getteamDetails(Date fdate, Date tdate, Set<Integer> statusid,String uemail) {
		if(statusid.size()==1){
			Iterator<Integer> itr=statusid.iterator();itr.hasNext();
			int status=itr.next();
			if(status==5){
				return entityManager.createNamedQuery("HelpDeskTicketEntity.getInvalidOtherThanPending").setParameter("fDate", new SimpleDateFormat("dd/MM/yyyy").format(fdate)).setParameter("tDate", new SimpleDateFormat("dd/MM/yyyy").format(tdate)).setParameter("uemail", uemail).getResultList();
			}
		}else if(statusid.size()>2){
			List<Object> result1= entityManager.createNamedQuery("HelpDeskTicketEntity.getInvalidOtherThanPending").setParameter("fDate", new SimpleDateFormat("dd/MM/yyyy").format(fdate)).setParameter("tDate", new SimpleDateFormat("dd/MM/yyyy").format(tdate)).setParameter("uemail", uemail).getResultList();
			List<Object> result2= entityManager.createNamedQuery("HelpDeskTicketEntity.getTeamDetails").setParameter("fDate", new SimpleDateFormat("dd/MM/yyyy").format(fdate)).setParameter("tDate", new SimpleDateFormat("dd/MM/yyyy").format(tdate)).setParameter("statusId", statusid).setParameter("uemail", uemail).getResultList();
			result2.addAll(result1);
			return result2;
		}
		return entityManager.createNamedQuery("HelpDeskTicketEntity.getTeamDetails").setParameter("fDate", new SimpleDateFormat("dd/MM/yyyy").format(fdate)).setParameter("tDate", new SimpleDateFormat("dd/MM/yyyy").format(tdate)).setParameter("statusId", statusid).setParameter("uemail", uemail).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<?> getSupportTeamMonthyWise(Set<Integer> statusid, String uEmail) {
		if(statusid.size()==1){
			Iterator<Integer> itr=statusid.iterator();itr.hasNext();
			int status=itr.next();
			if(status==5){
				return entityManager.createNamedQuery("HelpDeskTicketEntity.readMonthySupportIWiseInvalid").setParameter("uemail", uEmail).getResultList();
			}
		}else if(statusid.size()>2){
			List<Object> result1= entityManager.createNamedQuery("HelpDeskTicketEntity.readMonthySupportIWiseInvalid").setParameter("uemail", uEmail).getResultList();
			List<Object> result2=entityManager.createNamedQuery("HelpDeskTicketEntity.readMonthySupportIWise").setParameter("statusId", statusid).setParameter("uemail", uEmail).getResultList();
			result2.addAll(result1);
			return result2;
		}
		return entityManager.createNamedQuery("HelpDeskTicketEntity.readMonthySupportIWise").setParameter("statusId", statusid).setParameter("uemail", uEmail).getResultList();
	}

	@Override
	public List<TicketEscalationEntity> getAllLevelDesignation(Integer subCategoryId) {
		return entityManager.createNamedQuery("HelpDeskTicketEntity.getAllLevelDesignation",TicketEscalationEntity.class).setParameter("subCategoryId", subCategoryId).getResultList();
	}

	@Override
	public List<?> pendingForRegistrationTicketsBasedOnSectionWise(
			Set<String> projectIds, int status) {
		return entityManager.createNamedQuery("HelpDeskTicketEntity.getPendingForRegstrnBasedSection").setParameter("projectId", projectIds).getResultList();
	}

	@Override
	public List<?> getTodayComplaints(Integer status, int day, int month,int year) {
		return entityManager.createNamedQuery("HelpDeskTicketEntity.getTodayComplaints").setParameter("status", status).setParameter("day1", day).setParameter("month1", month).setParameter("year1", year).getResultList();
	}

	@Override
	public List<?> getTodayPendingComplaints(Integer status,int day,int month,int year) {
		return entityManager.createNamedQuery("HelpDeskTicketEntity.getTodayPendingComplaints").setParameter("status", status).setParameter("day", day).setParameter("month", month).setParameter("year", year).getResultList();
	}

	@Override
	public List<?> getTodayEscalatedComplaints(Integer status, int day,int month, int year) {
		return entityManager.createNamedQuery("HelpDeskTicketEntity.getTodayEscalatedComplaints").setParameter("day", day).setParameter("month", month).setParameter("year", year).getResultList();
	}

	@Override
	public List<?> searchDocketRRNumber(String rrNumber) {
		return entityManager.createNamedQuery("HelpDeskTicketEntity.searchDocketRRNumber").setParameter("rrNumber", rrNumber).getResultList();
	}

	@Override
	public List<?> getAllTodayComplaints(Integer status, int day, int month,int year) {
		return entityManager.createNamedQuery("HelpDeskTicketEntity.getAllTodayComplaints").setParameter("day1", day).setParameter("month1", month).setParameter("year1", year).getResultList();
	}

	@Override
	public List<?> readInAllMyComplaintDetails(Set<Integer> statusid,String uEmail) {
		return entityManager.createNamedQuery("HelpDeskTicketEntity.readAllMyComplaintDetails").setParameter("statusId", statusid).setParameter("uemail", uEmail).getResultList();
	}

	@Override
	public List<?> getInvalidPendingForSupportTeam(String uEmail) {
		return entityManager.createNamedQuery("HelpDeskTicketEntity.readMonthySupportIWiseInvalidPending").setParameter("uemail", uEmail).getResultList();
	}

	@Override
	public List<?> getInvalidTeamDetails(Date fdate, Date tdate,Set<Integer> statusid, String uemail) {
		return entityManager.createNamedQuery("HelpDeskTicketEntity.getInvalidTeamDetails").setParameter("fDate", new SimpleDateFormat("dd/MM/yyyy").format(fdate)).setParameter("tDate", new SimpleDateFormat("dd/MM/yyyy").format(tdate)).setParameter("uemail", uemail).getResultList();
		
	}

	@Override
	public List<?> getMyComplaintsDetails(Date fdate, Date tdate,Set<Integer> statusid, String uEmail) {
		return entityManager.createNamedQuery("HelpDeskTicketEntity.readAllMyComplaintDetails1").setParameter("fDate", new SimpleDateFormat("dd/MM/yyyy").format(fdate)).setParameter("tDate", new SimpleDateFormat("dd/MM/yyyy").format(tdate)).setParameter("statusId", statusid).setParameter("uemail", uEmail).getResultList();
	}

	@Override
	public List<?> getSupportBasedDateDetails(Date fdate, Date tdate) {
		String schemaname=PgrsSchemaName.cescPgrsSchema;
		String query="SELECT u.name,"
					 + "COUNT(case when (docketStatus=0 AND c.invalid_flag='No') then 1 END) AS ZERO,"
                     +"COUNT(case when (c.docketStatus=1 AND c.invalid_flag='No') then 1 END) AS ONE,"
                     +"COUNT(case when (c.docketStatus=2 AND c.invalid_flag='No') then 1 END) AS TWO,"
                     +"COUNT(case when (c.docketStatus=3 AND c.invalid_flag='No') then 1 END) AS THREE,"
                     +"COUNT(case when (c.docketStatus=4 AND c.invalid_flag='No') then 1 END) AS FOUR,count(*),c.username,"
                     + "COUNT(case when (c.invalid_flag='Yes') then 1 END) AS INVALID "
                     +"FROM "+schemaname+".pgrs_complaint_transactions c,"+schemaname+".core_users u,"+schemaname+".core_department d WHERE c.username=u.ur_login_name AND u.dept_id=d.id AND d.dept_name='CCCD' and c.esc_flag!='Yes'"
                     +"AND date(docketcreateddatetime)>=to_date('"+new SimpleDateFormat("dd/MM/yyyy").format(fdate)+"','dd/MM/yyyy') AND date(docketcreateddatetime)<=to_date('"+new SimpleDateFormat("dd/MM/yyyy").format(tdate)+"','dd/MM/yyyy')"
                     +"GROUP BY u.name,c.username";
		return entityManager.createNativeQuery(query).getResultList();
	}

	@Override
	public String getLocationClassificationTypeBasedOnSiteCode(String siteCode) {
		try{
			return (String)entityManager.createNamedQuery("HelpDeskTicketEntity.getLocationClassificationTypeBasedOnSiteCode").setParameter("siteCode", siteCode).getSingleResult();
		}catch(Exception e){
			return "";
		}
	}

	@Override
	public List<?> getTicketDataBasedOnStatus(int status) {
		return entityManager.createNamedQuery("HelpDeskTicketEntity.getTicketDataBasedOnStatus").setParameter("status", status).getResultList();
	}
	@Override
	public List<?> getTicketDataBasedOnStatusMobile(int status,String sitecode) {
		return entityManager.createNamedQuery("HelpDeskTicketEntity.getTicketDataBasedOnStatusMobile").setParameter("status", status).setParameter("sitecode", sitecode).getResultList();
	}
	@Override
	public List<?> getTicketDataBasedOnStatusMobile2(String sitecode) {
		return entityManager.createNamedQuery("HelpDeskTicketEntity.getTicketDataBasedOnStatusMobile2").setParameter("sitecode", sitecode).getResultList();
	}

}
