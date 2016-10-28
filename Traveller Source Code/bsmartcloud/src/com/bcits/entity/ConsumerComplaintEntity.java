package com.bcits.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="CONSUMER_COMPLAINTS",schema="MVS")
@NamedQueries
({
	@NamedQuery(name="ConsumerComplaintEntity.findAll", query="SELECT ct FROM ConsumerComplaintEntity ct"),
})
public class ConsumerComplaintEntity 
{
	@Id
	@SequenceGenerator(name = "conscomplseq", sequenceName = "CONSUMER_COMPLAINTS_ID")
	@GeneratedValue(generator = "conscomplseq")	
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

	
	public ConsumerComplaintEntity() 
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
	
}
