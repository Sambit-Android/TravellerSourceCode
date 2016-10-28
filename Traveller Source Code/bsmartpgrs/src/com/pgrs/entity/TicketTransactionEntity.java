package com.pgrs.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.QueryHint;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.pgrs.util.PgrsSchemaName;

@Entity
@Table(name = "PGRS_COMPLAINT_TRANSACTIONS",schema=PgrsSchemaName.cescPgrsSchema)
@NamedQueries({
	@NamedQuery(name = "TicketTransactionEntity.getCategoryListFromTransactionHistory", query = "SELECT ht.categoryId FROM TicketTransactionEntity ht WHERE ht.docketNumber=:docketNumber AND ht.invalidFlag='No'"),
	@NamedQuery(name = "TicketTransactionEntity.searchDocketNumberSourceSms", query = "SELECT ht.docketCreatedDt,ht.docketStatus,ht.action,ht.consumerMobileNo,ht.consumerEmailId FROM TicketTransactionEntity ht WHERE ht.docketNumber=:docketNumber AND ht.invalidFlag='No'",hints={@QueryHint(name="org.hibernate.cacheable",value="true")}),
	@NamedQuery(name = "TicketTransactionEntity.searchDocketHistory", query = "SELECT ht.docketCreatedDt,ht.docketStatus,ht.action,ht.consumerName,ht.consumerMobileNo,ht.consumerEmailId,ht.userName,ht.assignedTo,ht.remarks,ur.urName,dn.dnName,ur.urName,ur.urContactNo,ht.docketUpdatedDt,ht.transactionId FROM TicketTransactionEntity ht,Users ur INNER JOIN ur.designationEntity dn INNER JOIN ht.categoryEntity tc INNER JOIN ht.subCategoryEntity tsc WHERE ur.urLoginName=ht.assignedTo AND ht.escFlag='No' AND ht.docketNumber=:docketNumber AND ht.invalidFlag='No' ORDER BY ht.transactionId ASC",hints={@QueryHint(name="org.hibernate.cacheable",value="true")}),
	@NamedQuery(name = "TicketTransactionEntity.searchDocketEscHistory", query = "SELECT ht.docketCreatedDt,ec.escalatedDate,ec.level,ur.urName,ur.urLoginName,dn.dnName,ur.urContactNo,ph.text,u.urName,u.urLoginName,d.dnName,u.urContactNo,prh.text FROM Users ur,Users u,EscalatedComplaintsEntity ec INNER JOIN ec.helpDeskTicketEntity ht INNER JOIN ur.projectHeirarchyEntity ph INNER JOIN ur.designationEntity dn INNER JOIN u.projectHeirarchyEntity prh INNER JOIN u.designationEntity d WHERE ec.fromUser=ur.urLoginName AND ec.toUser=u.urLoginName AND ec.docketNumber=:docketNumber ORDER BY ec.escId ASC",hints={@QueryHint(name="org.hibernate.cacheable",value="true")}),
})
@Cacheable(value=true)
public class TicketTransactionEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(name="DOCKETNUMBER")
	private int docketNumber;
	
	@OneToOne
	@JoinColumn(name="DOCKETNUMBER",insertable=false,updatable=false)
	private HelpDeskTicketEntity ticketEntity;
	
	@Transient
	private int divisionSiteCode;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false, precision = 10, scale = 0)
	private int transactionId;
	
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
	
	@OneToOne
	@JoinColumn(name="CATEGORYID",insertable=false,updatable=false)
	private TicketCategoryEntity categoryEntity;
	
	@Column(name = "SUBCATEGORYID")
	private int subCategoryId;
	
	@OneToOne
	@JoinColumn(name="SUBCATEGORYID",insertable=false,updatable=false)
	private TicketSubCategoryEntity subCategoryEntity;
	
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
	
	@Column(name="ACTION")
	private String action;
	
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

/*	public TicketCategoryEntity getCategoryEntity() {
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

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
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

	public String getFeedbackGivenBy() {
		return feedbackGivenBy;
	}

	public void setFeedbackGivenBy(String feedbackGivenBy) {
		this.feedbackGivenBy = feedbackGivenBy;
	}

	public HelpDeskTicketEntity getTicketEntity() {
		return ticketEntity;
	}

	public void setTicketEntity(HelpDeskTicketEntity ticketEntity) {
		this.ticketEntity = ticketEntity;
	}

	public TicketCategoryEntity getCategoryEntity() {
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
	} 
	
}
