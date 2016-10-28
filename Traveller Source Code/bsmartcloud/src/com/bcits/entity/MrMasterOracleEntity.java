package com.bcits.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "MRMASTER", schema="VARU_1115")

@NamedQueries
({	
	@NamedQuery(name = "MrMasterOracleEntity.findMrCode", query = "SELECT a.mrCode FROM MrMasterOracleEntity a")
})


public class MrMasterOracleEntity  implements Serializable
{

	@Id
	@Column(name = "ID")
	private int id;


	@Column(name = "MRCODE")
	private String mrCode;

	@Column(name = "MRNAME")
	private String mrName;


	@Column(name = "MRADDRESS")
	private String mrAddress;

	@Column(name = "MRQUALIFICATION")
	private Timestamp mrQualification;

	
	@Column(name="USERNAME")
	private String userName;


	@Column(name = "DATESTAMP")
	private String dateStamp;
	

	public MrMasterOracleEntity(int id, String mrCode, String mrName,
			String mrAddress, Timestamp mrQualification, String userName,
			String dateStamp) {
		super();
		this.id = id;
		this.mrCode = mrCode;
		this.mrName = mrName;
		this.mrAddress = mrAddress;
		this.mrQualification = mrQualification;
		this.userName = userName;
		this.dateStamp = dateStamp;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getMrCode() {
		return mrCode;
	}


	public void setMrCode(String mrCode) {
		this.mrCode = mrCode;
	}


	public String getMrName() {
		return mrName;
	}


	public void setMrName(String mrName) {
		this.mrName = mrName;
	}


	public String getMrAddress() {
		return mrAddress;
	}


	public void setMrAddress(String mrAddress) {
		this.mrAddress = mrAddress;
	}


	public Timestamp getMrQualification() {
		return mrQualification;
	}


	public void setMrQualification(Timestamp mrQualification) {
		this.mrQualification = mrQualification;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getDateStamp() {
		return dateStamp;
	}


	public void setDateStamp(String dateStamp) {
		this.dateStamp = dateStamp;
	}
	
	
	
}


