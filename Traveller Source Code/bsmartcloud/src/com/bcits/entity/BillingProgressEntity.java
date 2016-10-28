package com.bcits.entity;



import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
@Entity
@Table(name="SMSBILLEDDEATAILS_MR",schema="BSMART")
@NamedQueries
({
	           @NamedQuery(name="BillingProgressEntity.getMrBillingProgress",query="SELECT b FROM BillingProgressEntity b where b.sitecode=:sitecode")
})

public class BillingProgressEntity implements Serializable
{
	
	@Column(name="SECTION")
	private String section;
	
	@Id
	@Column(name="SITECODE")
	private int sitecode;
	
	@Column(name="TOBEBILLED")
	private String tobebilled;
	
	@Column(name="BILLED")
	private String billed;
	
	@Column(name="UNBILLED")
	private String unbilled;
	
	@Column(name="TOBEBILLED_DAY")
	private String tobebilledday;
	
	@Column(name="BILLED_DAY")
	private String billedday;
	
	@Column(name="UNBILLED_DAY")
	private String unbilledday;
	
	@Id
	@Column(name="MRCODE")
	private String mrcode;

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public int getSitecode() {
		return sitecode;
	}

	public void setSitecode(int sitecode) {
		this.sitecode = sitecode;
	}

	public String getTobebilled() {
		return tobebilled;
	}

	public void setTobebilled(String tobebilled) {
		this.tobebilled = tobebilled;
	}

	public String getBilled() {
		return billed;
	}

	public void setBilled(String billed) {
		this.billed = billed;
	}

	public String getUnbilled() {
		return unbilled;
	}

	public void setUnbilled(String unbilled) {
		this.unbilled = unbilled;
	}

	public String getTobebilledday() {
		return tobebilledday;
	}

	public void setTobebilledday(String tobebilledday) {
		this.tobebilledday = tobebilledday;
	}

	public String getBilledday() {
		return billedday;
	}

	public void setBilledday(String billedday) {
		this.billedday = billedday;
	}

	public String getUnbilledday() {
		return unbilledday;
	}

	public void setUnbilledday(String unbilledday) {
		this.unbilledday = unbilledday;
	}

	public String getMrcode() {
		return mrcode;
	}

	public void setMrcode(String mrcode) {
		this.mrcode = mrcode;
	}
	
}
