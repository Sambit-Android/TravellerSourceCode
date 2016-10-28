package com.bcits.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="sitedetails",schema="vcloudengine")
@NamedQueries
({
	@NamedQuery(name="SiteDetails.findAll" , query="SELECT s FROM SiteDetails s")
})
public class SiteDetails 
{

	@Id
	@Column(name="sitecode")
	private int siteCode;
	
	@Column(name="company")
	private String company;
	
	@Column(name="circle")
	private String circle;
	
	@Column(name="division")
	private String division;
	
	@Column(name="subdivision")
	private String subDivision;
	
	@Column(name="emailids")
	private String emailIds;
	
	@Column(name="phonenos")
	private String phoneNos;

	public SiteDetails() 
	{
		
	}

	public int getSiteCode() {
		return siteCode;
	}

	public void setSiteCode(int siteCode) {
		this.siteCode = siteCode;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getCircle() {
		return circle;
	}

	public void setCircle(String circle) {
		this.circle = circle;
	}

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public String getSubDivision() {
		return subDivision;
	}

	public void setSubDivision(String subDivision) {
		this.subDivision = subDivision;
	}

	public String getEmailIds() {
		return emailIds;
	}

	public void setEmailIds(String emailIds) {
		this.emailIds = emailIds;
	}

	public String getPhoneNos() {
		return phoneNos;
	}

	public void setPhoneNos(String phoneNos) {
		this.phoneNos = phoneNos;
	}
	
	
}
