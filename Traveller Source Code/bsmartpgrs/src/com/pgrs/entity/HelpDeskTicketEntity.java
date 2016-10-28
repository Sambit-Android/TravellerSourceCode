package com.pgrs.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PreUpdate;
import javax.persistence.QueryHint;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.pgrs.util.PgrsSchemaName;

@Entity
@Table(name = "PGRS_COMPLAINTS",schema=PgrsSchemaName.cescPgrsSchema)
@NamedQueries({
	@NamedQuery(name = "HelpDeskTicketEntity.searchDocketNumber", query = "SELECT ht.docketNumber,ht.docketStatus,tc.categoryName,tsc.subCategoryName,ur.urName,ht.userName,ht.docketCreatedDt,ht.resolvedDate,ht.docketSummary,ht.estResolveTime,dn.dnName,ur.urContactNo,ht.docketUpdatedDt,ht.assignedTo,ht.invalidFlag,ht.docketSource,ht.consumerName,ht.consumerMobileNo,ht.consumerEmailId,lc.section FROM TicketCategoryEntity tc,TicketSubCategoryEntity tsc,HelpDeskTicketEntity ht,LocationsEntity lc,Users ur INNER JOIN ur.designationEntity dn WHERE lc.newSitecode=ht.siteCode AND ht.siteCode=:sitecode AND ht.categoryId=tc.categoryId AND ht.subCategoryId=tsc.subCategoryId AND ur.urLoginName=ht.assignedTo AND ht.docketNumber=:docketNumber",hints={@QueryHint(name="org.hibernate.cacheable",value="true")}),
	@NamedQuery(name = "HelpDeskTicketEntity.getIntialLevelDesignation", query = "SELECT te FROM TicketEscalationEntity te WHERE te.subCategoryId=:subCategoryId AND te.level=1 AND te.assignmentType IN (:assignmentTypeSet) ORDER BY te.assignmentType ASC",hints={@QueryHint(name="org.hibernate.cacheable",value="true")}),
	@NamedQuery(name = "HelpDeskTicketEntity.getUserLoginNamesBasedOnDesignation", query = "SELECT ur.urLoginName,ur.urName FROM Users ur WHERE ur.dnId=:dnId",hints={@QueryHint(name="org.hibernate.cacheable",value="true")}),
	@NamedQuery(name = "HelpDeskTicketEntity.getTicketsCountBasedOnStatus", query = "SELECT COUNT(*) FROM HelpDeskTicketEntity ht WHERE ht.assignedTo=:urLoginName AND ht.docketStatus IN(:statusSet) AND ht.invalidFlag='No'"),
	@NamedQuery(name = "HelpDeskTicketEntity.getOfficialDetailsBasedOnLoginName", query = "SELECT ur.urName,ur.urContactNo,ur.urEmailId,dn.dnName,ur.urLoginName FROM Users ur INNER JOIN ur.designationEntity dn WHERE ur.urLoginName=:urLoginName"),
	@NamedQuery(name = "HelpDeskTicketEntity.docketDetailsRead", query = "SELECT ht.docketNumber,ht.docketStatus,ht.docketSource,ht.consumerName,ht.consumerMobileNo,ht.rrNumber,ht.address,tc.categoryName,tsc.subCategoryName,ht.remarks,ht.userName,ht.docketCreatedDt,ht.resolvedDate,ht.docketSummary,ph.parent.text,ph.text,ht.estResolveTime FROM ProjectHeirarchyEntity ph,TicketCategoryEntity tc,TicketSubCategoryEntity tsc,HelpDeskTicketEntity ht,Users ur WHERE ht.categoryId=tc.categoryId AND ht.subCategoryId=tsc.subCategoryId AND ph.siteCode=ht.siteCode AND ur.urLoginName=ht.assignedTo AND ht.assignedTo=:urLoginName AND ht.docketStatus IN(:statusSet) AND ht.invalidFlag='No'",hints={@QueryHint(name="org.hibernate.cacheable",value="true")}),
	@NamedQuery(name = "HelpDeskTicketEntity.getCategoryList", query = "SELECT ht.categoryId FROM HelpDeskTicketEntity ht WHERE ht.docketNumber=:docketNumber AND ht.invalidFlag='No'"),
	@NamedQuery(name = "HelpDeskTicketEntity.searchDocketNumberSourceSms", query = "SELECT ht.docketNumber,ht.consumerMobileNo,ht.docketSource,ht.docketStatus,ht.docketCreatedDt FROM HelpDeskTicketEntity ht WHERE ht.docketNumber=:docketNumber AND ht.invalidFlag='No' ORDER BY ht.docketNumber DESC"),
	@NamedQuery(name = "HelpDeskTicketEntity.getPendingTicketsCount", query = "SELECT COUNT(*) FROM HelpDeskTicketEntity ht WHERE ht.docketStatus=:status AND ht.invalidFlag='No'"),
	@NamedQuery(name = "HelpDeskTicketEntity.getescalatedTicketsCount", query = "SELECT COUNT(*) FROM EscalatedComplaintsEntity ec INNER JOIN ec.helpDeskTicketEntity ht WHERE ht.docketStatus NOT IN(3) AND ht.invalidFlag='No' AND ec.level=ht.escLevel"),
	@NamedQuery(name = "HelpDeskTicketEntity.pendingForRegistrationTickets", query = "SELECT ht.docketNumber,ht.docketStatus,tc.categoryName,tsc.subCategoryName,ur.urName,ht.userName,ht.docketCreatedDt,ht.resolvedDate,ht.docketSummary,ht.estResolveTime,dn.dnName,ur.urContactNo,ht.docketUpdatedDt,ht.assignedTo,ht.invalidFlag FROM TicketCategoryEntity tc,TicketSubCategoryEntity tsc,HelpDeskTicketEntity ht,Users ur INNER JOIN ur.designationEntity dn WHERE ht.categoryId=tc.categoryId AND ht.subCategoryId=tsc.subCategoryId AND ur.urLoginName=ht.assignedTo AND ht.docketStatus=3",hints={@QueryHint(name="org.hibernate.cacheable",value="true")}),
	@NamedQuery(name = "HelpDeskTicketEntity.searchMobileNumber", query = "SELECT ht.docketNumber,ht.docketStatus,ht.docketSource,ht.consumerName,ht.consumerMobileNo,ht.rrNumber,ht.address,tc.categoryName,tsc.subCategoryName,ur.urName,ht.userName,ht.docketCreatedDt,ht.resolvedDate,ht.docketSummary,ph.parent.text,ph.text,ht.alternativeMobileNo,ht.consumerEmailId,ht.facebookId,ht.landMark,ht.estResolveTime,dn.dnName,ur.urName,ur.urContactNo,ht.docketUpdatedDt,ht.remarks,ht.invalidFlag FROM ProjectHeirarchyEntity ph,TicketCategoryEntity tc,TicketSubCategoryEntity tsc,HelpDeskTicketEntity ht,Users ur INNER JOIN ur.designationEntity dn WHERE ht.categoryId=tc.categoryId AND ht.subCategoryId=tsc.subCategoryId AND ph.siteCode=ht.siteCode AND ur.urLoginName=ht.assignedTo AND ht.consumerMobileNo=:mobileNo",hints={@QueryHint(name="org.hibernate.cacheable",value="true")}),
	@NamedQuery(name = "HelpDeskTicketEntity.searchDocketRRNumber", query = "SELECT ht.docketNumber,ht.docketStatus,ht.docketSource,ht.consumerName,ht.consumerMobileNo,ht.rrNumber,ht.address,tc.categoryName,tsc.subCategoryName,ur.urName,ht.userName,ht.docketCreatedDt,ht.resolvedDate,ht.docketSummary,ph.parent.text,ph.text,ht.alternativeMobileNo,ht.consumerEmailId,ht.facebookId,ht.landMark,ht.estResolveTime,dn.dnName,ur.urName,ur.urContactNo,ht.docketUpdatedDt,ht.remarks,ht.invalidFlag FROM ProjectHeirarchyEntity ph,TicketCategoryEntity tc,TicketSubCategoryEntity tsc,HelpDeskTicketEntity ht,Users ur INNER JOIN ur.designationEntity dn WHERE ht.categoryId=tc.categoryId AND ht.subCategoryId=tsc.subCategoryId AND ph.siteCode=ht.siteCode AND ur.urLoginName=ht.assignedTo AND ht.rrNumber=:rrNumber",hints={@QueryHint(name="org.hibernate.cacheable",value="true")}),
	@NamedQuery(name = "HelpDeskTicketEntity.getTodaysComplaints", query = "SELECT ht.docketNumber,ht.docketStatus,ht.docketCreatedDt,ht.resolvedDate,tc.categoryName,tsc.subCategoryName,ht.consumerName,ht.consumerMobileNo,ht.assignedTo,ht.estResolveTime FROM ProjectHeirarchyEntity ph,TicketCategoryEntity tc,TicketSubCategoryEntity tsc,HelpDeskTicketEntity ht,Users ur INNER JOIN ur.designationEntity dn WHERE ht.categoryId=tc.categoryId AND ht.subCategoryId=tsc.subCategoryId AND ph.siteCode=ht.siteCode AND ur.urLoginName=ht.assignedTo AND date(ht.docketCreatedDt)=:date AND ht.invalidFlag='No'",hints={@QueryHint(name="org.hibernate.cacheable",value="true")}),
	@NamedQuery(name = "HelpDeskTicketEntity.getNotificationDataBasedOnStatus", query = "SELECT ht.docketStatus,ht.docketCreatedDt,ht.docketNumber,ht.estResolveTime FROM HelpDeskTicketEntity ht WHERE ht.docketStatus IN(:statusSet) AND ht.assignedTo=:loginName AND ht.invalidFlag='No' ORDER BY ht.docketNumber DESC"),
	@NamedQuery(name = "HelpDeskTicketEntity.getPendingNotificationDataBasedOnStatus", query = "SELECT ht.docketStatus,ht.docketCreatedDt,ht.docketNumber,ht.consumerMobileNo FROM HelpDeskTicketEntity ht WHERE ht.docketStatus IN(:statusSet) AND ht.invalidFlag='No'"),
	@NamedQuery(name = "HelpDeskTicketEntity.getAllResolvedComplaints", query = "SELECT ht.docketNumber,ht.docketStatus,ht.docketSource,ht.consumerName,ht.consumerMobileNo,ht.rrNumber,ht.address,tc.categoryName,tsc.subCategoryName,ht.remarks,ht.userName,ht.docketCreatedDt,ht.resolvedDate,ht.docketSummary,ph.parent.text,ph.text,ht.estResolveTime,ht.feedBackRating,ht.feedBackComments FROM ProjectHeirarchyEntity ph,TicketCategoryEntity tc,TicketSubCategoryEntity tsc,HelpDeskTicketEntity ht,Users ur WHERE ht.categoryId=tc.categoryId AND ht.subCategoryId=tsc.subCategoryId AND ph.siteCode=ht.siteCode AND ur.urLoginName=ht.assignedTo AND ht.docketStatus=3 AND ht.invalidFlag='No' ORDER BY ht.docketNumber DESC",hints={@QueryHint(name="org.hibernate.cacheable",value="true")}),
	@NamedQuery(name = "HelpDeskTicketEntity.getAllTicketsForEscalationProcess", query = "SELECT ht.docketNumber,ht.docketCreatedDt,ht.subCategoryId,ht.assignedTo,ht.siteCode FROM HelpDeskTicketEntity ht WHERE ht.docketStatus IN(1,4) AND ht.invalidFlag='No'"),
	@NamedQuery(name = "HelpDeskTicketEntity.getescalatedTicketsCountBasedOnLogin", query = "SELECT COUNT(*) FROM EscalatedComplaintsEntity ec INNER JOIN ec.helpDeskTicketEntity ht WHERE (ec.fromUser=:loginName OR ec.toUser=:loginName) AND ht.invalidFlag='No' AND ht.docketStatus!=3 AND ht.escLevel=ec.level"),
	@NamedQuery(name = "HelpDeskTicketEntity.escalatedNotifications", query = "SELECT ec.docketNumber,ec.escalatedDate FROM EscalatedComplaintsEntity ec INNER JOIN ec.helpDeskTicketEntity ht WHERE ht.docketStatus NOT IN(3) AND (ec.fromUser=:loginName OR ec.toUser=:loginName) AND ht.invalidFlag='No' AND ht.escLevel=ec.level ORDER BY ht.docketNumber DESC",hints={@QueryHint(name="org.hibernate.cacheable",value="true")}),
	@NamedQuery(name = "HelpDeskTicketEntity.getAllEscalatedTickets", query = "SELECT ec.docketNumber,ht.docketStatus,ec.escalatedDate,ec.level,ec.fromUser,ec.toUser FROM EscalatedComplaintsEntity ec INNER JOIN ec.helpDeskTicketEntity ht WHERE (ec.fromUser=:loginName OR ec.toUser=:loginName) AND ht.invalidFlag='No' AND ht.docketStatus!=3 AND ht.escLevel=ec.level ORDER BY ht.docketNumber DESC",hints={@QueryHint(name="org.hibernate.cacheable",value="true")}),
	@NamedQuery(name = "HelpDeskTicketEntity.showAllEscalatedNotificationsForCCCD", query = "SELECT ec.docketNumber,ec.escalatedDate FROM EscalatedComplaintsEntity ec INNER JOIN ec.helpDeskTicketEntity ht WHERE ht.docketStatus NOT IN(3) AND ht.invalidFlag='No' ORDER BY ec.docketNumber DESC",hints={@QueryHint(name="org.hibernate.cacheable",value="true")}),
	@NamedQuery(name = "HelpDeskTicketEntity.getAllEscalatedTicketsForCCCD", query = "SELECT ec.docketNumber,ht.docketStatus,ht.docketCreatedDt,ec.escalatedDate,ht.estResolveTime,ec.level,ec.fromUser,ec.toUser,ec.officerName,ur.urContactNo,ph.text,tc.categoryName FROM Users ur,EscalatedComplaintsEntity ec,TicketCategoryEntity tc INNER JOIN ur.projectHeirarchyEntity ph INNER JOIN ec.helpDeskTicketEntity ht WHERE ur.urLoginName = ec.fromUser AND ht.invalidFlag='No' AND ht.docketStatus!=3 AND ec.level=ht.escLevel AND ht.categoryId=tc.categoryId ORDER BY ec.escalatedDate DESC"),	
	
	@NamedQuery(name = "HelpDeskTicketEntity.getlocationWiseCount", query = "SELECT count(*),p.docketStatus FROM HelpDeskTicketEntity p where p.siteCode IN (:projectIds) AND p.invalidFlag='No' GROUP BY p.docketStatus ORDER BY p.docketStatus ASC",hints={@QueryHint(name="org.hibernate.cacheable",value="true")}),
	@NamedQuery(name = "HelpDeskTicketEntity.updateDocketAsInvalidated", query = "UPDATE HelpDeskTicketEntity ht SET ht.invalidFlag='Yes' WHERE ht.docketNumber=:docketNumber"),
	@NamedQuery(name = "HelpDeskTicketEntity.getDocketDetailsBasedOnStaus", query="SELECT ht.docketNumber,ht.docketStatus,ht.docketCreatedDt,ht.resolvedDate,tc.categoryName,tsc.subCategoryName,ht.consumerName,ht.consumerMobileNo,ht.assignedTo,ht.estResolveTime FROM TicketCategoryEntity tc,TicketSubCategoryEntity tsc,HelpDeskTicketEntity ht,Users ur  WHERE ht.categoryId=tc.categoryId AND ht.subCategoryId=tsc.subCategoryId AND ht.siteCode IN (:projecids) AND ur.urLoginName=ht.assignedTo AND ht.docketStatus=:status"),
	@NamedQuery(name = "HelpDeskTicketEntity.getParentProjectHeirarchyEntityBasedOnSiteCode", query = "SELECT ph.parent FROM ProjectHeirarchyEntity ph WHERE ph.siteCode=:siteCode",hints={@QueryHint(name="org.hibernate.cacheable",value="true")}),
	@NamedQuery(name = "HelpDeskTicketEntity.getComplaintAgingStatistics", query = "SELECT ht.estResolveTime,ht.docketNumber FROM HelpDeskTicketEntity ht WHERE ht.docketStatus IN (1,2,4) AND ht.escFlag='Yes' AND ht.invalidFlag='No'",hints={@QueryHint(name="org.hibernate.cacheable",value="true")}),
	@NamedQuery(name = "HelpDeskTicketEntity.getComplaintsCountBasedOnDayWise", query = "SELECT COUNT(*) FROM HelpDeskTicketEntity ht WHERE ht.docketStatus=:status AND ht.invalidFlag='No' AND EXTRACT(day FROM ht.docketCreatedDt) =:day AND EXTRACT(month FROM ht.docketCreatedDt) =:month AND EXTRACT(year FROM ht.docketCreatedDt)=:year",hints={@QueryHint(name="org.hibernate.cacheable",value="true")}),
	@NamedQuery(name = "HelpDeskTicketEntity.getCircleWiseData1", query="SELECT count(*),(SELECT COUNT(*) FROM HelpDeskTicketEntity p1 WHERE extract(MONTH from p1.docketCreatedDt)=:month AND extract(YEAR from p1.docketCreatedDt)=:year AND p1.escLevel=1 AND p1.docketStatus=3 AND p1.siteCode IN (:siteCodes)),(SELECT COUNT(*) FROM HelpDeskTicketEntity p2 WHERE extract(MONTH from p2.docketCreatedDt)=:month AND extract(YEAR from p2.docketCreatedDt)=:year AND p2.escLevel!=1 AND p2.docketStatus=3 AND p2.siteCode IN (:siteCodes)) FROM HelpDeskTicketEntity p WHERE extract(MONTH from p.docketCreatedDt)=:month AND extract(YEAR from p.docketCreatedDt)=:year AND p.siteCode IN (:siteCodes)"),
	@NamedQuery(name = "HelpDeskTicketEntity.getCircleWiseData2", query="SELECT count(*),(SELECT COUNT(*) FROM HelpDeskTicketEntity p1 WHERE extract(MONTH from p1.docketCreatedDt)=:month AND extract(YEAR from p1.docketCreatedDt)=:year AND p1.escLevel=1 AND p1.docketStatus=3 AND p1.siteCode IN (:siteCodes) AND p1.categoryId=:categoryId),(SELECT COUNT(*) FROM HelpDeskTicketEntity p2 WHERE extract(MONTH from p2.docketCreatedDt)=:month AND extract(YEAR from p2.docketCreatedDt)=:year AND p2.escLevel!=1 AND p2.docketStatus=3 AND p2.siteCode IN (:siteCodes) AND p2.categoryId=:categoryId) FROM HelpDeskTicketEntity p WHERE extract(MONTH from p.docketCreatedDt)=:month AND extract(YEAR from p.docketCreatedDt)=:year AND p.siteCode IN (:siteCodes) AND p.categoryId=:categoryId"),
	@NamedQuery(name = "HelpDeskTicketEntity.getCircleWiseData3", query="SELECT count(*),(SELECT COUNT(*) FROM HelpDeskTicketEntity p1 WHERE extract(MONTH from p1.docketCreatedDt)=:month AND extract(YEAR from p1.docketCreatedDt)=:year AND p1.escLevel=1 AND p1.docketStatus=3 AND p1.siteCode IN (:siteCodes) AND p1.categoryId=:categoryId AND p1.subCategoryId=:subCategoryId),(SELECT COUNT(*) FROM HelpDeskTicketEntity p2 WHERE extract(MONTH from p2.docketCreatedDt)=:month AND extract(YEAR from p2.docketCreatedDt)=:year AND p2.escLevel!=1 AND p2.docketStatus=3 AND p2.siteCode IN (:siteCodes) AND p2.categoryId=:categoryId AND p2.subCategoryId=:subCategoryId) FROM HelpDeskTicketEntity p WHERE extract(MONTH from p.docketCreatedDt)=:month AND extract(YEAR from p.docketCreatedDt)=:year AND p.siteCode IN (:siteCodes) AND p.categoryId=:categoryId AND p.subCategoryId=:subCategoryId"),
	@NamedQuery(name = "HelpDeskTicketEntity.getComplaintStatus", query="SELECT COUNT(*),ht.docketStatus,tc.categoryName,ht.categoryId FROM HelpDeskTicketEntity ht,TicketCategoryEntity tc WHERE ht.categoryId=tc.categoryId AND date(ht.docketCreatedDt)>=:fromdate AND date(ht.docketCreatedDt)<=:todate AND ht.siteCode IN(:sitecodes) GROUP BY ht.docketStatus,tc.categoryName,ht.categoryId ORDER BY ht.docketStatus,tc.categoryName,ht.categoryId ASC"),
	@NamedQuery(name = "HelpDeskTicketEntity.getEscalatedComplaintsCountBasedOnDayWise", query = "SELECT COUNT(*) FROM EscalatedComplaintsEntity ece WHERE EXTRACT(day FROM ece.escalatedDate) =:day AND EXTRACT(month FROM ece.escalatedDate) =:month AND EXTRACT(year FROM ece.escalatedDate)=:year",hints={@QueryHint(name="org.hibernate.cacheable",value="true")}),
    @NamedQuery(name = "HelpDeskTicketEntity.getCompaintsForAgingPendingforResovation", query="select  ht.estResolveTime,ht.docketNumber FROM HelpDeskTicketEntity ht WHERE ht.docketStatus IN(1,2,4) AND ht.escFlag='No' AND ht.escLevel=1"),
    @NamedQuery(name = "HelpDeskTicketEntity.monthWiseDetails", query="SELECT COUNT(case when ht.docketStatus=0 then 1 END) AS ZERO,"
				+"COUNT(case when ht.docketStatus=1 then 1 END) AS ONE,"
				+"COUNT(case when ht.docketStatus=2 then 1 END) AS TWO,"
				+"COUNT(case when ht.docketStatus=3 then 1 END) AS THREE,"
				+"COUNT(case when ht.docketStatus=4 then 1 END) AS FOUR,"
				+"extract(MONTH from ht.docketCreatedDt) FROM HelpDeskTicketEntity ht WHERE extract(YEAR from ht.docketCreatedDt)=extract(YEAR from CURRENT_DATE) GROUP BY extract(MONTH from ht.docketCreatedDt) ORDER BY extract(MONTH from ht.docketCreatedDt) ASC"),
	
				
	@NamedQuery(name="HelpDeskTicketEntity.getTeamDetails",query="SELECT ht.docketNumber,ht.docketStatus,ht.docketCreatedDt,ht.resolvedDate,tc.categoryName,tsc.subCategoryName,ht.consumerName,ht.consumerMobileNo,ht.assignedTo,ht.estResolveTime,dn.dnName,ur.urName,ur.urContactNo, dn1.dnName,ur1.urName,ur1.urContactNo FROM TicketCategoryEntity tc,TicketSubCategoryEntity tsc,TicketTransactionEntity ht,Department dp,Users ur INNER JOIN ur.designationEntity dn,Users ur1 INNER JOIN ur1.designationEntity dn1 WHERE ht.categoryId=tc.categoryId AND ht.subCategoryId=tsc.subCategoryId AND  ur.urLoginName=:uemail AND ht.userName=:uemail AND ur1.urLoginName=ht.assignedTo AND ht.docketStatus IN(:statusId) AND date(ht.docketCreatedDt)>=to_date(:fDate,'dd/MM/YYYY') AND date(ht.docketCreatedDt)<=to_date(:tDate,'dd/MM/YYYY') AND dp.deptId=ur.deptId AND dp.deptName='CCCD' AND ht.invalidFlag='No' AND ht.escFlag!='Yes'"),
	@NamedQuery(name="HelpDeskTicketEntity.getInvalidOtherThanPending",query="SELECT ht.docketNumber,ht.docketStatus,ht.docketCreatedDt,ht.resolvedDate,tc.categoryName,tsc.subCategoryName,ht.consumerName,ht.consumerMobileNo,ht.assignedTo,ht.estResolveTime,dn.dnName,ur.urName,ur.urContactNo, dn1.dnName,ur1.urName,ur1.urContactNo FROM TicketCategoryEntity tc,TicketSubCategoryEntity tsc,TicketTransactionEntity ht,Department dp,Users ur INNER JOIN ur.designationEntity dn,Users ur1 INNER JOIN ur1.designationEntity dn1 WHERE ht.categoryId=tc.categoryId AND ht.subCategoryId=tsc.subCategoryId  AND ur.urLoginName=:uemail AND ur1.urLoginName=ht.assignedTo  AND ht.userName=:uemail AND date(ht.docketCreatedDt)>=to_date(:fDate,'dd/MM/YYYY') AND date(ht.docketCreatedDt)<=to_date(:tDate,'dd/MM/YYYY') AND dp.deptId=ur.deptId AND dp.deptName='CCCD' AND ht.invalidFlag='Yes' AND ht.escFlag!='Yes'"),
	@NamedQuery(name="HelpDeskTicketEntity.getInvalidTeamDetails",query="SELECT ht.docketNumber,ht.docketStatus,ht.docketCreatedDt,ht.consumerName,ht.consumerMobileNo,ht.consumerEmailId,ht.facebookId FROM TicketTransactionEntity ht,Department dp,Users ur INNER JOIN ur.designationEntity dn WHERE ur.urLoginName=:uemail AND ht.userName=:uemail AND date(ht.docketCreatedDt)>=to_date(:fDate,'dd/MM/YYYY') AND date(ht.docketCreatedDt)<=to_date(:tDate,'dd/MM/YYYY') AND dp.deptId=ur.deptId AND dp.deptName='CCCD' AND ht.categoryId=0 AND ht.subCategoryId=0 AND ht.invalidFlag='Yes' AND ht.escFlag!='Yes'"),
	
	
	@NamedQuery(name="HelpDeskTicketEntity.readAllMyComplaintDetails",query="SELECT DISTINCT(ht.docketNumber),ht.docketStatus,ht.docketCreatedDt,ht.resolvedDate,tc.categoryName,tsc.subCategoryName,ht.consumerName,ht.consumerMobileNo,ht.assignedTo,ht.estResolveTime,dn.dnName,ur.urName,ur.urContactNo, dn1.dnName,ur1.urName,ur1.urContactNo FROM TicketCategoryEntity tc,TicketSubCategoryEntity tsc,TicketTransactionEntity ht,Users ur INNER JOIN ur.designationEntity dn,Users ur1 INNER JOIN ur1.designationEntity dn1 WHERE ht.categoryId=tc.categoryId AND ht.subCategoryId=tsc.subCategoryId  AND ur.urLoginName=:uemail AND ht.userName=:uemail AND ur1.urLoginName=ht.assignedTo AND ht.docketStatus IN(:statusId) AND ht.invalidFlag='No'"),
	@NamedQuery(name="HelpDeskTicketEntity.readAllMyComplaintDetails1",query="SELECT DISTINCT(ht.docketNumber),ht.docketStatus,ht.docketCreatedDt,ht.resolvedDate,tc.categoryName,tsc.subCategoryName,ht.consumerName,ht.consumerMobileNo,ht.assignedTo,ht.estResolveTime,dn.dnName,ur.urName,ur.urContactNo, dn1.dnName,ur1.urName,ur1.urContactNo FROM TicketCategoryEntity tc,TicketSubCategoryEntity tsc,TicketTransactionEntity ht,Users ur INNER JOIN ur.designationEntity dn,Users ur1 INNER JOIN ur1.designationEntity dn1 WHERE ht.categoryId=tc.categoryId AND ht.subCategoryId=tsc.subCategoryId  AND ur.urLoginName=:uemail AND ht.userName=:uemail AND ur1.urLoginName=ht.assignedTo AND ht.docketStatus IN(:statusId) AND ht.invalidFlag='No' AND date(ht.docketCreatedDt)>=to_date(:fDate,'dd/MM/YYYY') AND date(ht.docketCreatedDt)<=to_date(:tDate,'dd/MM/YYYY')"),
	
	
	@NamedQuery(name="HelpDeskTicketEntity.readMonthySupportIWise",query="SELECT ht.docketNumber,ht.docketStatus,ht.docketCreatedDt,ht.resolvedDate,tc.categoryName,tsc.subCategoryName,ht.consumerName,ht.consumerMobileNo,ht.assignedTo,ht.estResolveTime,dn.dnName,ur.urName,ur.urContactNo, dn1.dnName,ur1.urName,ur1.urContactNo FROM TicketCategoryEntity tc,TicketSubCategoryEntity tsc,TicketTransactionEntity ht,Department dp,Users ur INNER JOIN ur.designationEntity dn,Users ur1 INNER JOIN ur1.designationEntity dn1 WHERE ht.categoryId=tc.categoryId AND ht.subCategoryId=tsc.subCategoryId  AND ur.urLoginName=:uemail AND ur1.urLoginName=ht.assignedTo  AND ht.userName=:uemail  AND ht.docketStatus IN(:statusId)  AND dp.deptId=ur.deptId AND ht.invalidFlag='No' AND ht.escFlag!='Yes'"),
	@NamedQuery(name="HelpDeskTicketEntity.readMonthySupportIWiseInvalid",query="SELECT ht.docketNumber,ht.docketStatus,ht.docketCreatedDt,ht.resolvedDate,tc.categoryName,tsc.subCategoryName,ht.consumerName,ht.consumerMobileNo,ht.assignedTo,ht.estResolveTime,dn.dnName,ur.urName,ur.urContactNo, dn1.dnName,ur1.urName,ur1.urContactNo FROM TicketCategoryEntity tc,TicketSubCategoryEntity tsc,TicketTransactionEntity ht,Department dp,Users ur INNER JOIN ur.designationEntity dn,Users ur1 INNER JOIN ur1.designationEntity dn1 WHERE ht.categoryId=tc.categoryId AND ht.subCategoryId=tsc.subCategoryId  AND ur.urLoginName=:uemail AND ur1.urLoginName=ht.assignedTo  AND ht.userName=:uemail AND dp.deptId=ur.deptId AND ht.invalidFlag='Yes' AND ht.escFlag!='Yes'"),
	@NamedQuery(name="HelpDeskTicketEntity.readMonthySupportIWiseInvalidPending",query="SELECT ht.docketNumber,ht.docketStatus,ht.docketCreatedDt,ht.consumerName,ht.consumerMobileNo,ht.consumerEmailId,ht.facebookId FROM TicketTransactionEntity ht,Department dp,Users ur INNER JOIN ur.designationEntity dn WHERE ur.urLoginName=:uemail AND ht.userName=:uemail AND dp.deptId=ur.deptId AND dp.deptName='CCCD' AND ht.categoryId=0 AND ht.subCategoryId=0 AND ht.invalidFlag='Yes' AND ht.escFlag!='Yes'"),
	
	
	@NamedQuery(name = "HelpDeskTicketEntity.getAllLevelDesignation", query = "SELECT te FROM TicketEscalationEntity te WHERE te.subCategoryId=:subCategoryId ORDER BY te.level ASC ",hints={@QueryHint(name="org.hibernate.cacheable",value="true")}),
    @NamedQuery(name="HelpDeskTicketEntity.getPendingForRegstrnBasedSection", query="SELECT ht.docketNumber,ht.docketSource,ht.docketCreatedDt,ht.consumerName,ht.consumerMobileNo,ht.consumerEmailId,ht.facebookId FROM HelpDeskTicketEntity ht WHERE ht.docketStatus=0 AND ht.invalidFlag='No' AND ht.siteCode IN(:projectId)  ORDER BY ht.docketCreatedDt DESC"),
   
    @NamedQuery(name = "HelpDeskTicketEntity.getTodayComplaints", query = "SELECT c.docketNumber,c.docketStatus,c.docketCreatedDt,c.resolvedDate,tc.categoryName,tsc.subCategoryName,c.consumerName,c.consumerMobileNo,c.assignedTo,c.estResolveTime,dn.dnName,ur.urName,ur.urContactNo FROM HelpDeskTicketEntity c,TicketCategoryEntity tc,TicketSubCategoryEntity tsc, Users ur INNER JOIN ur.designationEntity dn WHERE c.categoryId=tc.categoryId AND c.invalidFlag='No' AND c.subCategoryId=tsc.subCategoryId AND ur.urLoginName=c.assignedTo AND c.docketStatus=:status AND EXTRACT(day FROM c.docketCreatedDt) =:day1 AND EXTRACT(month FROM c.docketCreatedDt) =:month1 AND EXTRACT(year FROM c.docketCreatedDt)=:year1",hints={@QueryHint(name="org.hibernate.cacheable",value="true")}),
    @NamedQuery(name = "HelpDeskTicketEntity.getAllTodayComplaints", query = "SELECT c.docketNumber,c.docketStatus,c.docketSource,c.consumerName,c.consumerMobileNo,c.rrNumber,c.address,c.remarks,c.userName,c.docketCreatedDt,c.resolvedDate,c.docketSummary,c.assignedTo FROM HelpDeskTicketEntity c  WHERE c.invalidFlag='No' AND EXTRACT(day FROM c.docketCreatedDt) =:day1 AND EXTRACT(month FROM c.docketCreatedDt) =:month1 AND EXTRACT(year FROM c.docketCreatedDt)=:year1",hints={@QueryHint(name="org.hibernate.cacheable",value="true")}),
    @NamedQuery(name = "HelpDeskTicketEntity.getTodayPendingComplaints", query = "SELECT ht.docketNumber,ht.docketSource,ht.docketCreatedDt,ht.consumerName,ht.consumerMobileNo,ht.consumerEmailId,ht.facebookId FROM HelpDeskTicketEntity ht WHERE ht.docketStatus=:status AND ht.invalidFlag='No' AND EXTRACT(day FROM ht.docketCreatedDt) =:day AND EXTRACT(month FROM ht.docketCreatedDt) =:month AND EXTRACT(year FROM ht.docketCreatedDt)=:year",hints={@QueryHint(name="org.hibernate.cacheable",value="true")}),
    @NamedQuery(name = "HelpDeskTicketEntity.getTodayEscalatedComplaints", query = "SELECT ec.docketNumber,ht.docketStatus,ht.docketCreatedDt,ec.escalatedDate,ht.estResolveTime,ec.level,ec.fromUser,ec.toUser,ec.officerName,ur.urContactNo,ph.text FROM Users ur,EscalatedComplaintsEntity ec INNER JOIN ur.projectHeirarchyEntity ph INNER JOIN ec.helpDeskTicketEntity ht WHERE ur.urLoginName = ec.fromUser AND ht.invalidFlag='No' AND EXTRACT(day FROM ec.escalatedDate) =:day AND EXTRACT(month FROM ec.escalatedDate) =:month AND EXTRACT(year FROM ec.escalatedDate)=:year ORDER BY ec.escalatedDate DESC"),
    @NamedQuery(name="HelpDeskTicketEntity.getMyComplaintsDetails",query="SELECT ht.docketNumber,ht.docketStatus,ht.docketCreatedDt,ht.resolvedDate,tc.categoryName,tsc.subCategoryName,ht.consumerName,ht.consumerMobileNo,ht.assignedTo,ht.estResolveTime,dn.dnName,ur.urName,ur.urContactNo, dn1.dnName,ur1.urName,ur1.urContactNo FROM TicketCategoryEntity tc,TicketSubCategoryEntity tsc,HelpDeskTicketEntity ht,Department dp,Users ur INNER JOIN ur.designationEntity dn,Users ur1 INNER JOIN ur1.designationEntity dn1 WHERE ht.categoryId=tc.categoryId AND ht.subCategoryId=tsc.subCategoryId AND  ur.urLoginName=:uemail AND ht.userName=:uemail AND ur1.urLoginName=ht.assignedTo AND ht.docketStatus IN(:statusId) AND date(ht.docketCreatedDt)>=to_date(:fDate,'dd/MM/YYYY') AND date(ht.docketCreatedDt)<=to_date(:tDate,'dd/MM/YYYY') AND dp.deptId=ur.deptId AND dp.deptName='CCCD' AND ht.invalidFlag='No'"),
    @NamedQuery(name = "HelpDeskTicketEntity.getLocationClassificationTypeBasedOnSiteCode", query = "SELECT ph.classificationType FROM ProjectHeirarchyEntity ph WHERE ph.siteCode=:siteCode",hints={@QueryHint(name="org.hibernate.cacheable",value="true")}),
    @NamedQuery(name = "HelpDeskTicketEntity.getTicketDataBasedOnStatus", query = "SELECT ht.docketNumber,ht.docketStatus,tc.categoryName,tsc.subCategoryName,ur.urName,ht.userName,ht.docketCreatedDt,ht.resolvedDate,ht.docketSummary,ht.estResolveTime,dn.dnName,ur.urContactNo,ht.docketUpdatedDt,ht.assignedTo,ht.invalidFlag FROM TicketCategoryEntity tc,TicketSubCategoryEntity tsc,HelpDeskTicketEntity ht,Users ur INNER JOIN ur.designationEntity dn WHERE ht.categoryId=tc.categoryId AND ht.subCategoryId=tsc.subCategoryId AND ur.urLoginName=ht.assignedTo AND ht.docketStatus=:status",hints={@QueryHint(name="org.hibernate.cacheable",value="true")}),
    @NamedQuery(name = "HelpDeskTicketEntity.getTicketDataBasedOnStatusMobile", query ="SELECT ht.docketNumber,ht.docketStatus,tc.categoryName,tsc.subCategoryName,ur.urName,ht.userName,ht.docketCreatedDt,ht.resolvedDate,ht.docketSummary,ht.estResolveTime,dn.dnName,ur.urContactNo,ht.docketUpdatedDt,ht.assignedTo,ht.invalidFlag,ht.docketSource,ht.consumerName,ht.consumerMobileNo,ht.consumerEmailId,lc.section FROM TicketCategoryEntity tc,TicketSubCategoryEntity tsc,HelpDeskTicketEntity ht,LocationsEntity lc,Users ur INNER JOIN ur.designationEntity dn WHERE lc.newSitecode=ht.siteCode AND ht.siteCode=:sitecode AND ht.categoryId=tc.categoryId AND ht.subCategoryId=tsc.subCategoryId AND ur.urLoginName=ht.assignedTo AND ht.docketStatus=:status ORDER BY ht.docketNumber desc",hints={@QueryHint(name="org.hibernate.cacheable",value="true")}),
    @NamedQuery(name = "HelpDeskTicketEntity.getTicketDataBasedOnStatusMobile2", query ="SELECT ht.docketNumber,ht.docketStatus,tc.categoryName,tsc.subCategoryName,ur.urName,ht.userName,ht.docketCreatedDt,ht.resolvedDate,ht.docketSummary,ht.estResolveTime,dn.dnName,ur.urContactNo,ht.docketUpdatedDt,ht.assignedTo,ht.invalidFlag,ht.docketSource,ht.consumerName,ht.consumerMobileNo,ht.consumerEmailId,lc.section FROM TicketCategoryEntity tc,TicketSubCategoryEntity tsc,HelpDeskTicketEntity ht,LocationsEntity lc,Users ur INNER JOIN ur.designationEntity dn WHERE lc.newSitecode=ht.siteCode AND ht.siteCode=:sitecode AND ht.categoryId=tc.categoryId AND ht.subCategoryId=tsc.subCategoryId AND ur.urLoginName=ht.assignedTo ORDER BY ht.docketNumber desc",hints={@QueryHint(name="org.hibernate.cacheable",value="true")})
})
@Cacheable(value=true)
public class HelpDeskTicketEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "DOCKETNUMBER", unique = true, nullable = false, precision = 10, scale = 0)
	private int docketNumber;
	
	@Transient
	private int divisionSiteCode;

	@Transient
	private int circleSiteCode;

	@Transient
	private int subDivisionSiteCode;
	
	@Transient
	private Date fromdate;
	
	@Transient
	private Date todate;
	
	/**
	 * Added By Raju
	 */
	@Transient
	private String transDocNum;
	
	
	public String getTransDocNum() {
		return transDocNum;
	}

	public void setTransDocNum(String transDocNum) {
		this.transDocNum = transDocNum;
	}

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private int ticketId;
	
	@Column(name = "CONSUMERNAME", length = 50)
	private String consumerName;
	
	@Column(name = "DOCKETSOURCE", length = 100)
	private String docketSource;
	
	@Column(name = "DOCKETCREATEDDATETIME")
	private Timestamp docketCreatedDt;
	
	@Column(name = "CONSUMERMOBILENUMBER")
	private long consumerMobileNo;
	
	@Column(name = "CATEGORYID")
	private int categoryId;
	
	/*@OneToOne
	@JoinColumn(name="CATEGORYID",insertable=false,updatable=false)
	private TicketCategoryEntity categoryEntity;*/
	
	@Column(name = "SUBCATEGORYID")
	private int subCategoryId;
	
	/*@OneToOne
	@JoinColumn(name="SUBCATEGORYID",insertable=false,updatable=false)
	private TicketSubCategoryEntity subCategoryEntity;*/
	
	@Column(name = "CONSUMEREMAILID")
	private String consumerEmailId;
	
	@Column(name = "FACEBOOKID")
	private String facebookId;
	
	@Column(name = "USERNAME")
	private String userName;
	
	@Column(name = "DOCKETUPDATE_DATE")
	private Timestamp docketUpdatedDt;

	@Column(name = "LANDMARK")
	private String landMark;
	
	@Column(name = "SITECODE")
	private String siteCode;
	
	@Column(name = "RRNUMBER")
	private String rrNumber;

	@Column(name = "ESTIMATEDRESOLVEDATETIME")
	private Timestamp estResolveTime;
	
	@Column(name = "FEEDBACKRATING")
	private int feedBackRating;
	
	@Column(name = "FEEDBACKCOMMENTS")
	private String feedBackComments;

	@Column(name = "RESOLVEDDATEANDTIME")
	private Timestamp resolvedDate;
	
	@Column(name = "DOCKETSUMMARY")
	private String docketSummary;
	
	@Column(name = "ASSIGNEDTO")
	private String assignedTo;
	
	@Column(name = "DOCKETSTATUS")
	private int docketStatus;

	@Column(name = "ALTERNATIVE_NUMBER")
	private long alternativeMobileNo;
	
	@Column(name = "ADDRESS")
	private String address;
	
	@Column(name = "ESC_FLAG")
	private String escFlag;
	
	@Column(name = "ESC_LEVEL")
	private int escLevel;
	
	@Column(name="REMARKS")
	private String remarks;
	
	@Column(name="OFFICER_NAME")
	private String officerName;
	
	@Column(name="INVALID_FLAG")
	private String invalidFlag;
	
	@Column(name="FEEDBACK_GIVEN_BY")
	private String feedbackGivenBy;

	public int getDocketNumber() {
		return docketNumber;
	}

	public void setDocketNumber(int docketNumber) {
		this.docketNumber = docketNumber;
	}

	public int getTicketId() {
		return ticketId;
	}

	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}

	public String getConsumerName() {
		return consumerName;
	}

	public void setConsumerName(String consumerName) {
		this.consumerName = consumerName;
	}

	public String getDocketSource() {
		return docketSource;
	}

	public void setDocketSource(String docketSource) {
		this.docketSource = docketSource;
	}

	public Timestamp getDocketCreatedDt() {
		return docketCreatedDt;
	}

	public void setDocketCreatedDt(Timestamp docketCreatedDt) {
		this.docketCreatedDt = docketCreatedDt;
	}

	public long getConsumerMobileNo() {
		return consumerMobileNo;
	}

	public void setConsumerMobileNo(long consumerMobileNo) {
		this.consumerMobileNo = consumerMobileNo;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public int getSubCategoryId() {
		return subCategoryId;
	}

	public void setSubCategoryId(int subCategoryId) {
		this.subCategoryId = subCategoryId;
	}

	public String getConsumerEmailId() {
		return consumerEmailId;
	}

	public void setConsumerEmailId(String consumerEmailId) {
		this.consumerEmailId = consumerEmailId;
	}

	public String getFacebookId() {
		return facebookId;
	}

	public void setFacebookId(String facebookId) {
		this.facebookId = facebookId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Timestamp getDocketUpdatedDt() {
		return docketUpdatedDt;
	}

	public void setDocketUpdatedDt(Timestamp docketUpdatedDt) {
		this.docketUpdatedDt = docketUpdatedDt;
	}

	public String getLandMark() {
		return landMark;
	}

	public void setLandMark(String landMark) {
		this.landMark = landMark;
	}

	public String getSiteCode() {
		return siteCode;
	}

	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}

	public String getRrNumber() {
		return rrNumber;
	}

	public void setRrNumber(String rrNumber) {
		this.rrNumber = rrNumber;
	}

	public Timestamp getEstResolveTime() {
		return estResolveTime;
	}

	public void setEstResolveTime(Timestamp estResolveTime) {
		this.estResolveTime = estResolveTime;
	}

	public int getFeedBackRating() {
		return feedBackRating;
	}

	public void setFeedBackRating(int feedBackRating) {
		this.feedBackRating = feedBackRating;
	}

	public String getFeedBackComments() {
		return feedBackComments;
	}

	public void setFeedBackComments(String feedBackComments) {
		this.feedBackComments = feedBackComments;
	}

	public Timestamp getResolvedDate() {
		return resolvedDate;
	}

	public void setResolvedDate(Timestamp resolvedDate) {
		this.resolvedDate = resolvedDate;
	}

	public String getDocketSummary() {
		return docketSummary;
	}

	public void setDocketSummary(String docketSummary) {
		this.docketSummary = docketSummary;
	}

	public String getAssignedTo() {
		return assignedTo;
	}

	public void setAssignedTo(String assignedTo) {
		this.assignedTo = assignedTo;
	}

	public int getDocketStatus() {
		return docketStatus;
	}

	public void setDocketStatus(int docketStatus) {
		this.docketStatus = docketStatus;
	}

	public long getAlternativeMobileNo() {
		return alternativeMobileNo;
	}

	public void setAlternativeMobileNo(long alternativeMobileNo) {
		this.alternativeMobileNo = alternativeMobileNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getDivisionSiteCode() {
		return divisionSiteCode;
	}

	public void setDivisionSiteCode(int divisionSiteCode) {
		this.divisionSiteCode = divisionSiteCode;
	}

	public String getEscFlag() {
		return escFlag;
	}

	public void setEscFlag(String escFlag) {
		this.escFlag = escFlag;
	}

	public int getEscLevel() {
		return escLevel;
	}

	public void setEscLevel(int escLevel) {
		this.escLevel = escLevel;
	}

	/*public TicketCategoryEntity getCategoryEntity() {
		return categoryEntity;
	}

	public void setCategoryEntity(TicketCategoryEntity categoryEntity) {
		this.categoryEntity = categoryEntity;
	}

	public TicketSubCategoryEntity getSubCategoryEntity() {
		return subCategoryEntity;
	}

	public void setSubCategoryEntity(TicketSubCategoryEntity subCategoryEntity) {
		this.subCategoryEntity = subCategoryEntity;
	}*/

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public int getCircleSiteCode() {
		return circleSiteCode;
	}

	public void setCircleSiteCode(int circleSiteCode) {
		this.circleSiteCode = circleSiteCode;
	}

	public Date getFromdate() {
		return fromdate;
	}

	public void setFromdate(Date fromdate) {
		this.fromdate = fromdate;
	}

	public Date getTodate() {
		return todate;
	}

	public void setTodate(Date todate) {
		this.todate = todate;
	}
	
	@PreUpdate
	public void setChangeDate() { 
		this.docketUpdatedDt = new Timestamp(new java.util.Date().getTime());
	}

	public String getOfficerName() {
		return officerName;
	}

	public void setOfficerName(String officerName) {
		this.officerName = officerName;
	}

	public String getInvalidFlag() {
		return invalidFlag;
	}

	public void setInvalidFlag(String invalidFlag) {
		this.invalidFlag = invalidFlag;
	}


	public int getSubDivisionSiteCode() {
		return subDivisionSiteCode;
	}

	public void setSubDivisionSiteCode(int subDivisionSiteCode) {
		this.subDivisionSiteCode = subDivisionSiteCode;
	}


	public String getFeedbackGivenBy() {
		return feedbackGivenBy;
	}

	public void setFeedbackGivenBy(String feedbackGivenBy) {
		this.feedbackGivenBy = feedbackGivenBy;
	}

	
}
