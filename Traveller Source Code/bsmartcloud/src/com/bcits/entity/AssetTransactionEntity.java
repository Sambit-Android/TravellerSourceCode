package com.bcits.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="ASSET_TRANSACTION", schema="vcloudengine")
@NamedQueries({
	//@NamedQuery(name="",query="")
})
public class AssetTransactionEntity implements Serializable{

	  @Id
	  @GeneratedValue(strategy=GenerationType.IDENTITY)
	  @Column(name = "tid")
	  private int transId;
	  
	  @Column(name = "deviceid")
	  private String deviceId;
	
	 @Column(name = "transtype")
	  private String transType;
	
	 @Column(name = "transopid")
	  private String operatorCode;
	
	 @Column(name = "translocid")
	  private Integer siteCode;
	
	 @Column(name = "transdate")
	  private Timestamp transDate;
	
	 @Column(name = "trepdes")
	  private String repairDescription;
	
	 @Column(name = "trepdate")
	  private Timestamp repairDate;
	
	 @Column(name = "trepvendor")
	  private String repVendor;
	
	 @Column(name = "trepvenmob")
	  private String vendorMob;
	
	 @Column(name = "trepbill")
	 private byte[] trepbill;
	
	 @Column(name = "trnw")
	  private String resonForNotWorking;

	public int getTransId() {
		return transId;
	}

	public void setTransId(int transId) {
		this.transId = transId;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getTransType() {
		return transType;
	}

	public void setTransType(String transType) {
		this.transType = transType;
	}

	public String getOperatorCode() {
		return operatorCode;
	}

	public void setOperatorCode(String operatorCode) {
		this.operatorCode = operatorCode;
	}

	public Integer getSiteCode() {
		return siteCode;
	}

	public void setSiteCode(Integer siteCode) {
		this.siteCode = siteCode;
	}

	public Timestamp getTransDate() {
		return transDate;
	}

	public void setTransDate(Timestamp transDate) {
		this.transDate = transDate;
	}

	public String getRepairDescription() {
		return repairDescription;
	}

	public void setRepairDescription(String repairDescription) {
		this.repairDescription = repairDescription;
	}

	public Timestamp getRepairDate() {
		return repairDate;
	}

	public void setRepairDate(Timestamp repairDate) {
		this.repairDate = repairDate;
	}

	public String getRepVendor() {
		return repVendor;
	}

	public void setRepVendor(String repVendor) {
		this.repVendor = repVendor;
	}

	public String getVendorMob() {
		return vendorMob;
	}

	public void setVendorMob(String vendorMob) {
		this.vendorMob = vendorMob;
	}

	public byte[] getTrepbill() {
		return trepbill;
	}

	public void setTrepbill(byte[] trepbill) {
		this.trepbill = trepbill;
	}

	public String getResonForNotWorking() {
		return resonForNotWorking;
	}

	public void setResonForNotWorking(String resonForNotWorking) {
		this.resonForNotWorking = resonForNotWorking;
	}
	
}
