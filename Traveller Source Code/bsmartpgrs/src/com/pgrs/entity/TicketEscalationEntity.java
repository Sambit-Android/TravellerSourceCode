package com.pgrs.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.QueryHint;
import javax.persistence.Table;

import com.pgrs.util.PgrsSchemaName;

@Entity
@Table(name = "PGRS_ESCALATION",schema=PgrsSchemaName.cescPgrsSchema)
@NamedQueries({
	@NamedQuery(name="TicketEscalationEntity.findAllEscalationLevels",query="SELECT te.escId,te.subCategoryId,te.level,te.dnId,dn.dnName,te.notificationType,te.assignmentType,te.urbanTimeLines,te.ruralTimeLines FROM TicketEscalationEntity te INNER JOIN te.designation dn WHERE te.subCategoryId=:subCategoryId ORDER BY te.level ASC,te.assignmentType ASC",hints={@QueryHint(name="org.hibernate.cacheable",value="true")}),
})
public class TicketEscalationEntity extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false, precision = 10, scale = 0)
	private int escId;
	
	@Column(name="SUBCATEGORY_ID")
	private int subCategoryId;
	
	@ManyToOne
	@JoinColumn(name="SUBCATEGORY_ID",insertable=false,updatable=false)
	private TicketSubCategoryEntity subCategoryEntity;
	
	@Column(name="LEVEL")
	private int level;
	
	@Column(name="DN_ID")
	private int dnId;
	
	@OneToOne
	@JoinColumn(name="DN_ID",insertable=false,updatable=false)
	private Designation designation;
	
	@Column(name="NOTIFICATION_TYPE")
	private String notificationType;
	
	@Column(name="ASSIGNMENT_TYPE")
	private String assignmentType;
	
	@Column(name="UR_TIME_LINES")
	private int urbanTimeLines;
	
	@Column(name="RL_TIME_LINES")
	private int ruralTimeLines;

	public int getEscId() {
		return escId;
	}

	public void setEscId(int escId) {
		this.escId = escId;
	}

	public int getSubCategoryId() {
		return subCategoryId;
	}

	public void setSubCategoryId(int subCategoryId) {
		this.subCategoryId = subCategoryId;
	}

	public TicketSubCategoryEntity getSubCategoryEntity() {
		return subCategoryEntity;
	}

	public void setSubCategoryEntity(TicketSubCategoryEntity subCategoryEntity) {
		this.subCategoryEntity = subCategoryEntity;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getDnId() {
		return dnId;
	}

	public void setDnId(int dnId) {
		this.dnId = dnId;
	}

	public Designation getDesignation() {
		return designation;
	}

	public void setDesignation(Designation designation) {
		this.designation = designation;
	}

	public String getNotificationType() {
		return notificationType;
	}

	public void setNotificationType(String notificationType) {
		this.notificationType = notificationType;
	}

	public String getAssignmentType() {
		return assignmentType;
	}

	public void setAssignmentType(String assignmentType) {
		this.assignmentType = assignmentType;
	}

	public int getUrbanTimeLines() {
		return urbanTimeLines;
	}

	public void setUrbanTimeLines(int urbanTimeLines) {
		this.urbanTimeLines = urbanTimeLines;
	}

	public int getRuralTimeLines() {
		return ruralTimeLines;
	}

	public void setRuralTimeLines(int ruralTimeLines) {
		this.ruralTimeLines = ruralTimeLines;
	}
	
}
