package com.bcits.entity;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="vigilancecomplaints",schema="MVS")
@NamedQueries
({
	@NamedQuery(name="VigilanceComplaintEntity.findAll", query="SELECT ct FROM VigilanceComplaintEntity ct"),
	@NamedQuery(name="VigilanceComplaintEntity.findByUser", query="SELECT ct FROM VigilanceComplaintEntity ct where trim(ct.createdBy)=:username order by ct.id desc"),
	@NamedQuery(name = "VigilanceComplaintEntity.updateStatus", 	query = "UPDATE VigilanceComplaintEntity tr  SET tr.status=:status WHERE tr.id=:id"),
})
public class VigilanceComplaintEntity 
{
	@Id
	/*@SequenceGenerator(name = "conscomplseq", sequenceName = "VIGILANCE_COMPLAINTS_ID")
	@GeneratedValue(generator = "conscomplseq")	*/
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private int id;
	
	@Column(name="SDOCODE")
	private String sdoCode;
	
	@Column(name="C_NUMBER")
	private String consumerNo;
	
	@Column(name="ADDRESS")
	private String address;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	@Column(name="ISSUE")
	private String issue;
	
	@Column(name="CREATEDBY")
	private String createdBy;
	
	@Column(name="STATUS")
	private String status;
	
	@Column(name="DATE")
	private Date date;
	
	public VigilanceComplaintEntity() 
	{
		
	}

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}

	public String getSdoCode() {
		return sdoCode;
	}

	public void setSdoCode(String sdoCode) {
		this.sdoCode = sdoCode;
	}

	public String getConsumerNo() {
		return consumerNo;
	}

	public void setConsumerNo(String consumerNo) {
		this.consumerNo = consumerNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIssue() {
		return issue;
	}

	public void setIssue(String issue) {
		this.issue = issue;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
}
