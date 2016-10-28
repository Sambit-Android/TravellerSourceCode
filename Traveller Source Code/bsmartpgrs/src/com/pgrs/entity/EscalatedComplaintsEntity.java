package com.pgrs.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.QueryHint;
import javax.persistence.Table;

import com.pgrs.util.PgrsSchemaName;

@Entity
@Table(name = "PGRS_ESCALATED_COMPLAINTS",schema=PgrsSchemaName.cescPgrsSchema)
@NamedQueries({
	//@NamedQuery(name="TicketEscalationEntity.findAllEscalationLevels",query="SELECT te.escId,te.subCategoryId,te.level,te.dnId,dn.dnName,te.notificationType,te.assignmentType,te.urbanTimeLines,te.ruralTimeLines FROM TicketEscalationEntity te INNER JOIN te.designation dn WHERE te.subCategoryId=:subCategoryId ORDER BY te.escId ASC"),
	@NamedQuery(name="EscalatedComplaintsEntity.searchEsclatedComplaints",query="SELECT COUNT(pc.docketNumber),COUNT(ec.level) as level,ec.level FROM EscalatedComplaintsEntity ec,HelpDeskTicketEntity pc WHERE pc.docketNumber=ec.docketNumber AND pc.siteCode like :sitecode GROUP BY ec.level ORDER BY ec.level ASC",hints={@QueryHint(name="org.hibernate.cacheable",value="true")}),
	@NamedQuery(name="EscalatedComplaintsEntity.searchEsclatedComplaints1",query="SELECT COUNT(pc.docketNumber),COUNT(ec.level) as level,ec.level FROM EscalatedComplaintsEntity ec,HelpDeskTicketEntity pc WHERE pc.docketNumber=ec.docketNumber AND pc.siteCode like :sitecode AND  ec.escalatedDate<=:date2 AND ec.escalatedDate>=:date1 GROUP BY ec.level ORDER BY ec.level ASC",hints={@QueryHint(name="org.hibernate.cacheable",value="true")}),

})
public class EscalatedComplaintsEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false, precision = 10, scale = 0)
	private int escId;
	
	@Column(name="ESC_DATE")
	private Timestamp escalatedDate;
	
	@Column(name="ESC_LEVEL")
	private int level;
	
	@Column(name="DOCKETNUMBER")
	private int docketNumber;
	
	@ManyToOne
	@JoinColumn(name="DOCKETNUMBER",insertable=false,updatable=false)
	private HelpDeskTicketEntity helpDeskTicketEntity;
	
	@Column(name="FROM_USER")
	private String fromUser;
	
	@Column(name="TO_USER")
	private String toUser;
	
	@Column(name="REMARKS")
	private String remarks;
	
	@Column(name="OFFICER_NAME")
	private String officerName;

	public int getEscId() {
		return escId;
	}

	public void setEscId(int escId) {
		this.escId = escId;
	}

	public Timestamp getEscalatedDate() {
		return escalatedDate;
	}

	public void setEscalatedDate(Timestamp escalatedDate) {
		this.escalatedDate = escalatedDate;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getDocketNumber() {
		return docketNumber;
	}

	public void setDocketNumber(int docketNumber) {
		this.docketNumber = docketNumber;
	}

	public HelpDeskTicketEntity getHelpDeskTicketEntity() {
		return helpDeskTicketEntity;
	}

	public void setHelpDeskTicketEntity(HelpDeskTicketEntity helpDeskTicketEntity) {
		this.helpDeskTicketEntity = helpDeskTicketEntity;
	}

	public String getFromUser() {
		return fromUser;
	}

	public void setFromUser(String fromUser) {
		this.fromUser = fromUser;
	}

	public String getToUser() {
		return toUser;
	}

	public void setToUser(String toUser) {
		this.toUser = toUser;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getOfficerName() {
		return officerName;
	}

	public void setOfficerName(String officerName) {
		this.officerName = officerName;
	}
	
}
