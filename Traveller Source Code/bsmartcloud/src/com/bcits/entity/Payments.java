package com.bcits.entity;

import java.sql.Timestamp;
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
@Table(name="PAYMENTS",schema="MVS")
@NamedQueries({@NamedQuery(name="Find.Payments.ByRRNO",query="SELECT p FROM Payments p WHERE p.rrno=:rrno"),
	           @NamedQuery(name="Payment.findAll",query="SELECT p from Payments p order by p.datestamp DESC"),
		@NamedQuery(name = "Find.Payments.Bydate", query = "SELECT P FROM Payments p WHERE pg_catalog.date(p.rdate) BETWEEN TO_DATE(:fromDate, 'dd-MM-yyyy') AND TO_DATE(:toDate, 'dd-MM-yyyy') order by p.datestamp DESC"),
		@NamedQuery(name = "Find.Payments.BydateSdoCode", query = "SELECT P FROM Payments p WHERE pg_catalog.date(p.rdate) BETWEEN TO_DATE(:fromDate, 'dd-MM-yyyy') AND TO_DATE(:toDate, 'dd-MM-yyyy') AND p.sdoCode LIKE :sdoCode order by p.datestamp DESC"),
		@NamedQuery(name = "Find.Payments.BydateSdoCodeMrCode", query = "SELECT P FROM Payments p WHERE pg_catalog.date(p.rdate) BETWEEN TO_DATE(:fromDate, 'dd-MM-yyyy') AND TO_DATE(:toDate, 'dd-MM-yyyy') AND p.sdoCode LIKE :sdoCode AND UPPER(p.mrcode) LIKE :mrCode order by p.datestamp DESC"),
		@NamedQuery(name = "Payments.getByTwoDates", query = "SELECT p.amount,to_char(p.rdate,'dd-MM-yyyy') FROM Payments p WHERE UPPER(p.mrcode) LIKE UPPER(:mrCode) AND p.sdoCode LIKE :sdoCode AND pg_catalog.date(p.rdate) BETWEEN TO_DATE(:fromDate, 'dd-MM-yyyy') AND TO_DATE(:toDate, 'dd-MM-yyyy')"),
// @NamedQuery(name = "Payments.paymentDeposit", query =
// "SELECT p FROM Payments p,MRDepositEntity d WHERE UPPER(p.mrcode) LIKE UPPER(:mrCode) AND p.sdoCode LIKE :sdoCode AND p.rdate BETWEEN TO_DATE(:fromDate, 'dd-MM-yyyy') AND TO_DATE(:toDate, 'dd-MM-yyyy')")
    })
public class Payments
{
	@Id
	//@SequenceGenerator(name = "paymentSeq", sequenceName = "PAYMENTS_ID_SEQ")
	//@GeneratedValue(strategy =GenerationType.SEQUENCE,generator = "paymentSeq")	
	
	/*@SequenceGenerator(name = "USER_GENERATOR", sequenceName = "resource_id_seq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_GENERATOR")*/
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private int id;
	
	@Column(name="RRNO")
	private String rrno;
	/*@Column(name="LEDGNO")
	private String ledgno;
	@Column(name="FOLIONO")
	private String foliono;*/
	
	@Column(name="RDATE")
	private Date rdate;
	
	@Column(name="RECNO")
	private String recno;
	
	@Column(name="AMOUNT")
	private Double amount;
	
	@Column(name="DEVICEID")
	private String deviceId;
	
	@Column(name="CDNO")
	private String cdno;
	
	@Column(name="CDDATE")
	private Date cddate;
	
	@Column(name="BANKNAME")
	private String bankname;
	
	@Column(name="BRANCH")
	private String branch;
	
	@Column(name="PAYMODE")
	private String paymode;

	@Column(name="DATESTAMP")
	private Timestamp datestamp;
	
	@Column(name="TRANSACTIONID")
	private String transactionid;
	
	/*@Column(name="CCID")
	private String ccid;*/
	
	@Column(name="MRCODE")
	private String mrcode;
	
	@Column(name="SDOCODE")
	private String sdoCode;
	
	@Column(name="MRCREDIT")
	private String mrCredit;
	
	@Column(name="REMARKS")
	private String remarks;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRrno() {
		return rrno;
	}

	public void setRrno(String rrno) {
		this.rrno = rrno;
	}

	public Date getRdate() {
		return rdate;
	}

	/*
	 * public String getRdate() { SimpleDateFormat formatter = new
	 * SimpleDateFormat("dd-MM-yyyy"); return formatter.format(rdate); }
	 */
	public void setRdate(Date rdate) {
		this.rdate = rdate;
	}

	public String getRecno() {
		return recno;
	}

	public void setRecno(String recno) {
		this.recno = recno;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getCdno() {
		return cdno;
	}

	public void setCdno(String cdno) {
		this.cdno = cdno;
	}

	public Date getCddate() {
		return cddate;
	}

	public void setCddate(Date cddate) {
		this.cddate = cddate;
	}

	public String getBankname() {
		return bankname;
	}

	public void setBankname(String bankname) {
		this.bankname = bankname;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getPaymode() {
		return paymode;
	}

	public void setPaymode(String paymode) {
		this.paymode = paymode;
	}

	public Timestamp getDatestamp() {
		return datestamp;
	}

	/*
	 * public String getDatestamp() { SimpleDateFormat formatter = new
	 * SimpleDateFormat("dd-MM-yyyy"); return formatter.format(datestamp); }
	 */
	public void setDatestamp(Timestamp datestamp) {
		this.datestamp = datestamp;
	}

	public String getTransactionid() {
		return transactionid;
	}

	public void setTransactionid(String transactionid) {
		this.transactionid = transactionid;
	}

	/*public String getCcid() {
		return ccid;
	}

	public void setCcid(String ccid) {
		this.ccid = ccid;
	}*/

	public String getMrcode() {
		return mrcode;
	}

	public void setMrcode(String mrcode) {
		this.mrcode = mrcode;
	}

	public String getSdoCode() {
		return sdoCode;
	}

	public void setSdoCode(String sdoCode) {
		this.sdoCode = sdoCode;
	}

	public String getMrCredit() {
		return mrCredit;
	}

	public void setMrCredit(String mrCredit) {
		this.mrCredit = mrCredit;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	

}
