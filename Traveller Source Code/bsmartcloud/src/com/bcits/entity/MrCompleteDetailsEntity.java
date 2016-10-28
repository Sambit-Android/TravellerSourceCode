package com.bcits.entity;



import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
@Entity
@Table(name="MRMASTER_DETAILS",schema="BSMART")
@NamedQueries
({
	           @NamedQuery(name="MrCompleteDetailsEntity.getMrBillingProgress",query="SELECT b FROM BillingProgressEntity b where b.sitecode=:sitecode")
})

public class MrCompleteDetailsEntity implements Serializable
{
	
	@Column(name="CIRCLE")
	private String circle;
	
	@Column(name="DIVISION")
	private String division;
	
	@Column(name="SUBDIVISION")
	private String subdivision;
	
	@Column(name="SECTION")
	private int section;
	
	@Id
	@Column(name="SITECODE")
	private String sitecode;
	
	@Id
	@Column(name="MRCODE")
	private String mrcode;
	
	@Column(name="MRNAME")
	private String mrname;
	
	@Column(name="ZONE")
	private String zone;
	
	@Column(name="MRQUALIFICATION")
	private String mrqualification;

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

	public String getSubdivision() {
		return subdivision;
	}

	public void setSubdivision(String subdivision) {
		this.subdivision = subdivision;
	}

	public int getSection() {
		return section;
	}

	public void setSection(int section) {
		this.section = section;
	}

	public String getSitecode() {
		return sitecode;
	}

	public void setSitecode(String sitecode) {
		this.sitecode = sitecode;
	}

	public String getMrcode() {
		return mrcode;
	}

	public void setMrcode(String mrcode) {
		this.mrcode = mrcode;
	}

	public String getMrname() {
		return mrname;
	}

	public void setMrname(String mrname) {
		this.mrname = mrname;
	}

	public String getZone() {
		return zone;
	}

	public void setZone(String zone) {
		this.zone = zone;
	}

	public String getMrqualification() {
		return mrqualification;
	}

	public void setMrqualification(String mrqualification) {
		this.mrqualification = mrqualification;
	}
	
	
	
}
