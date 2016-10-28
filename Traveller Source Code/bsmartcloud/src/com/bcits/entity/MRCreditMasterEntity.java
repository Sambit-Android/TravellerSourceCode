package com.bcits.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "MRCREDITMASTER", schema = "MVS")
@NamedQueries({
		@NamedQuery(name = "MRCreditMasterEntity.findAll", query = "SELECT mrme FROM MRCreditMasterEntity mrme "),
		@NamedQuery(name = "MRCreditMasterEntity.findAllByMrcode", query = "SELECT m FROM MRCreditMasterEntity m where m.mrCode Like :mrCode AND m.sdoCode Like :sdoCode"),
		@NamedQuery(name = "MRCreditMasterEntity.findMRCreditMaster", query = "SELECT mrcm FROM MRCreditMasterEntity mrcm WHERE UPPER(mrcm.mrCode) LIKE UPPER(:mrCode) AND mrcm.sdoCode LIKE :sdoCode"),
		@NamedQuery(name = "MRCreditMasterEntity.updateAvailableCreditMobile", query = "UPDATE MRCreditMasterEntity m SET m.availCredit =:amount where m.mrCode Like :mrCode AND m.sdoCode Like :sdoCode"),
		@NamedQuery(name = "MRCreditMasterEntity.updateStatusMobSynchMobile", query = "UPDATE MRCreditMasterEntity m SET m.localMobSynched ='1' where m.mrCode Like :mrCode AND m.sdoCode Like :sdoCode"),
		@NamedQuery(name = "MRCreditMasterEntity.updateAvailCredt", query = "UPDATE MRCreditMasterEntity mrcme SET mrcme.availCredit = :availCredit,mrcme.localMobSynched=:localMobSynched WHERE UPPER(mrcme.mrCode) LIKE UPPER(:mrCode) AND mrcme.sdoCode LIKE :sdoCode"),
		@NamedQuery(name = "MRCreditMasterEntity.findMRCreditMasterByTwoDates", query = "SELECT mrcme FROM MRCreditMasterEntity mrcme WHERE mrcme.timestamp BETWEEN TO_DATE(:fromDate, 'dd-MM-yyyy') AND TO_DATE(:toDate, 'dd-MM-yyyy') AND UPPER(mrcme.mrCode) LIKE UPPER(:mrCode) AND mrcme.sdoCode LIKE :sdoCode order by mrcme.timestamp DESC")

})
public class MRCreditMasterEntity {

	@Id
	// @SequenceGenerator(name = "mrcMaster_id", sequenceName =
	// "mrcredit_id_seq")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "SDOCODE")
	private String sdoCode;

	@Column(name = "MRCODE")
	private String mrCode;

	@Column(name = "AVAILABLECREDIT")
	private Double availCredit;

	@Column(name = "REMARKS")
	private String remarks;

	@Column(name = "TOTALCREDIT")
	private Double totalCredit;

	@Column(name = "ADDEDDATEANDTIME")
	private Timestamp timestamp;


	@Column(name = "LOCALMOBSYNCHED")
	private String localMobSynched;
	
	@Column(name = "CASHCOUNTERID")
	private String cashcounterid;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSdoCode() {
		return sdoCode;
	}

	public void setSdoCode(String sdoCode) {
		this.sdoCode = sdoCode;
	}

	public String getMrCode() {
		return mrCode;
	}

	public void setMrCode(String mrCode) {
		this.mrCode = mrCode;
	}

	public Double getAvailCredit() {
		return availCredit;
	}

	public void setAvailCredit(Double availCredit) {
		this.availCredit = availCredit;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Double getTotalCredit() {
		return totalCredit;
	}

	public void setTotalCredit(Double totalCredit) {
		this.totalCredit = totalCredit;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public MRCreditMasterEntity() {

	}

	public MRCreditMasterEntity(Integer id, String sdoCode, String mrCode,
			Double availCredit, String remarks, Double totalCredit,
			Timestamp timestamp, String localMobSynched) {

		this.id = id;
		this.availCredit = availCredit;
		this.mrCode = mrCode;
		this.remarks = remarks;
		this.sdoCode = sdoCode;
		this.timestamp = timestamp;
		this.totalCredit = totalCredit;
		this.localMobSynched = localMobSynched;

	}

	public String getLocalMobSynched() {
		return localMobSynched;
	}

	public void setLocalMobSynched(String localMobSynched) {
		this.localMobSynched = localMobSynched;
	}

	public String getCashcounterid() {
		return cashcounterid;
	}

	public void setCashcounterid(String cashcounterid) {
		this.cashcounterid = cashcounterid;
	}
	

}
