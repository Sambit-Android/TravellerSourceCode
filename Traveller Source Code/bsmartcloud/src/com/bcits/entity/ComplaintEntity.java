package com.bcits.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="COMPLAINTS",schema="VCLOUDENGINE")
@NamedQueries
({
	@NamedQuery(name="ComplaintEntity.findAll", query="SELECT ct FROM ComplaintEntity ct"),
})
public class ComplaintEntity 
{

	@Id
	@Column(name="COMPLAINTID")
	private int complaintId;
	
	@Column(name="CONSUMERNO")
	private String consumerNo;
	
	@Column(name="COMPIANTDETAILS")
	private String complaintDetails;
	
	@Column(name="STATUS")
	private String status;

	@Column(name="COMPLAINTDATE")
	private Date complaintDate;
	
	@Column(name="REMARKS")
	private String remarks;
	
	@Column(name="SDOCODE")
	private String sdoCode;

	
	public ComplaintEntity() 
	{
		
	}

	public int getComplaintId() {
		return complaintId;
	}

	public void setComplaintId(int complaintId) {
		this.complaintId = complaintId;
	}

	public String getConsumerNo() {
		return consumerNo;
	}

	public void setConsumerNo(String consumerNo) {
		this.consumerNo = consumerNo;
	}

	public String getComplaintDetails() {
		return complaintDetails;
	}

	public void setComplaintDetails(String complaintDetails) {
		this.complaintDetails = complaintDetails;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getComplaintDate() {
		return complaintDate;
	}

	public void setComplaintDate(Date complaintDate) {
		this.complaintDate = complaintDate;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getSdoCode() {
		return sdoCode;
	}

	public void setSdoCode(String sdoCode) {
		this.sdoCode = sdoCode;
	}
}
