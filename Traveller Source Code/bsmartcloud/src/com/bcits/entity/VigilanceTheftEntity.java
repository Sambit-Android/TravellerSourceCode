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
@Table(name="vigilancetheft",schema="MVS")
@NamedQueries
({
	@NamedQuery(name="VigilanceTheftEntity.findAll", query="SELECT tr FROM VigilanceTheftEntity tr"),
	@NamedQuery(name="VigilanceTheftEntity.findOnlyImage", 	query="SELECT tr FROM VigilanceTheftEntity tr WHERE TRIM(tr.rr_Number)=TRIM(:consNo)"),
	@NamedQuery(name = "VigilanceTheftEntity.updateStatus", 	query = "UPDATE VigilanceTheftEntity tr  SET tr.status=:status WHERE tr.id=:id"),
	@NamedQuery(name="VigilanceTheftEntity.findByUser", query="SELECT tr FROM VigilanceTheftEntity tr where trim(tr.createdBy)=:username order by tr.id desc"),

})
public class VigilanceTheftEntity 
{
	@Id
	/*@SequenceGenerator(name = "theftseq", sequenceName = "THEFT_ID")
	@GeneratedValue(generator = "theftseq")*/
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private int id;
	
	@Column(name="SDOCODE")
	private String sdoCode;
	
	@Column(name="RR_NUMBER")
	private String rr_Number;
	
	@Column(name="ADDRESS")
	private String address;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	@Column(name="IMAGE")
	private byte[] image;
	
	@Column(name="LATITUDE")
	private String lattitude;
	
	@Column(name="LONGITUDE")
	private String longitude;
	
	@Column(name="CREATEDBY")
	private String createdBy;
	
	@Column(name="IMAGE_TWO")
	private byte[] image_two;
	
	@Column(name="IMAGE_THREE")
	private byte[] image_three;
	
	@Column(name="STATUS")
	private String status;
	
	@Column(name="DATE")
	private Date date;
	
	public VigilanceTheftEntity() 
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


	public String getRr_Number() {
		return rr_Number;
	}


	public void setRr_Number(String rr_Number) {
		this.rr_Number = rr_Number;
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


	public byte[] getImage() {
		return image;
	}


	public void setImage(byte[] image) {
		this.image = image;
	}


	public String getLattitude() {
		return lattitude;
	}


	public void setLattitude(String lattitude) {
		this.lattitude = lattitude;
	}


	public String getLongitude() {
		return longitude;
	}


	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}


	public String getCreatedBy() {
		return createdBy;
	}


	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}


	public byte[] getImage_two() {
		return image_two;
	}


	public void setImage_two(byte[] image_two) {
		this.image_two = image_two;
	}


	public byte[] getImage_three() {
		return image_three;
	}

	public void setImage_three(byte[] image_three) {
		this.image_three = image_three;
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
