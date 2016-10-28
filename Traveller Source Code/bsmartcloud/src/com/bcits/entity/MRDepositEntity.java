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

import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

@Entity
@Table(name = "MRDEPOSITS", schema = "MVS")
@NamedQueries({
		@NamedQuery(name = "MRDepositEntity.findAll", query = "SELECT mrde FROM MRDepositEntity mrde"),
		@NamedQuery(name = "MRDepositEntity.getAllDepositsByDate", query = "SELECT mrde FROM MRDepositEntity mrde WHERE pg_catalog.date(mrde.timestamp)=to_date(:reqDate,'yyyy-MM-dd')"),
		@NamedQuery(name = "MRDepositEntity.findAllDepositsByTwoDates", query = "SELECT mrde.amount,to_char(mrde.timestamp,'dd-MM-yyyy') FROM MRDepositEntity mrde WHERE UPPER(mrde.mrCode) LIKE UPPER(:mrCode) AND mrde.sdoCode LIKE :sdoCode AND pg_catalog.date(mrde.timestamp) BETWEEN TO_DATE(:fromDate,'dd-MM-yyyy') AND TO_DATE(:toDate,'dd-MM-yyyy') order by mrde.timestamp DESC"),
/*
 * @NamedQuery(name = "MRDepositEntity.findMRDepositsAndPayments", query =
 * "SELECT p.amount,to_char(p.rdate,'dd-MM-yyyy'),d.amount,to_char(d.timestamp, 'dd-MM-yyyy') FROM Payments p,MRDepositEntity d WHERE p.sdoCode LIKE d.sdoCode AND d.sdoCode LIKE :sdoCode "
 * +
 * "AND UPPER(p.mrcode) LIKE UPPER(d.mrCode) AND UPPER(d.mrCode) LIKE UPPER(:mrCode) AND  pg_catalog.date(d.timestamp) BETWEEN to_date(:fromDate, 'dd-mm-yyyy') AND to_date(:toDate, 'dd-mm-yyyy') AND pg_catalog.date(p.rdate) BETWEEN to_date(:fromDate, 'dd-mm-yyyy') AND to_date(:toDate, 'dd-mm-yyyy')"
 * )
 */
})
public class MRDepositEntity {

	@Id
	// @SequenceGenerator(name = "mrdep_id", sequenceName = "mrdeposit_id_seq")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "SDOCODE")
	private String sdoCode;

	@Column(name = "MRCODE")
	private String mrCode;

	@Column(name = "AMOUNT")
	@NumberFormat(style = Style.CURRENCY)
	// @NumberFormat(style = Style.NUMBER, pattern = "#,###.##")
	private Double amount;

	@Column(name = "DEPOSITDATETIME")
	private Timestamp timestamp;

	@Column(name = "REMARKS")
	private String remarks;

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

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	/*
	 * public String getTimestamp() { SimpleDateFormat formatter = new
	 * SimpleDateFormat("dd-MM-yyyy"); return formatter.format(timestamp); }
	 */

	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public MRDepositEntity(Integer id, String sdoCode, String mrCode,
			Double amount, Timestamp timestamp, String remarks) {

		this.amount = amount;
		this.id = id;
		this.mrCode = mrCode;
		this.remarks = remarks;
		this.sdoCode = sdoCode;
		this.timestamp = timestamp;

	}

	public MRDepositEntity() {

	}


}
