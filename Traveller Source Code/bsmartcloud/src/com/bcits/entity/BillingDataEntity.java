package com.bcits.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Entity
@Table(name="BILLINGDATA",schema="CONSUMERAPP")
@NamedQueries
({
	@NamedQuery(name = "BillingDataEntity.findByAccNo", 	query = "SELECT a.sdoCode, a.accNo, a.billMonth, a.billAmountBeforeDueDate, a.billAmountAfterDueDate, a.billIssueDate, a.billNo, a.dueDateCash, a.unitsConsumed, a.vendor, a.notifiDateTime FROM BillingDataEntity a WHERE a.accNo=:searchData ORDER BY a.billIssueDate DESC"),
	@NamedQuery(name = "BillingDataEntity.findByBillNo", 	query = "SELECT a.sdoCode, a.accNo, a.billMonth, a.billAmountBeforeDueDate, a.billAmountAfterDueDate, a.billIssueDate, a.billNo, a.dueDateCash, a.unitsConsumed, a.vendor, a.notifiDateTime FROM BillingDataEntity a WHERE a.billNo=:searchData ORDER BY a.billIssueDate DESC"),
	@NamedQuery(name = "BillingDataEntity.findByBillAmnt", 	query = "SELECT a.sdoCode, a.accNo, a.billMonth, a.billAmountBeforeDueDate, a.billAmountAfterDueDate, a.billIssueDate, a.billNo, a.dueDateCash, a.unitsConsumed, a.vendor, a.notifiDateTime FROM BillingDataEntity a WHERE a.billAmountBeforeDueDate=:searchData ORDER BY a.billIssueDate DESC"),
	@NamedQuery(name = "BillingDataEntity.findBillData", 	query = "SELECT a.sdoCode, a.accNo, a.billMonth, a.billAmountBeforeDueDate, a.billAmountAfterDueDate, to_char(a.billIssueDate,'dd-MM-yyyy'), a.billNo, a.dueDateCash, a.unitsConsumed, a.vendor, a.notifiDateTime FROM BillingDataEntity a WHERE a.sdoCode=:sdocode AND a.accNo=:accNo AND a.billIssueDate IN (SELECT MAX(a.billIssueDate) FROM BillingDataEntity a WHERE a.sdoCode=:sdocode AND a.accNo=:accNo AND a.billNo NOT IN('0','1'))ORDER BY a.billIssueDate DESC"),
	@NamedQuery(name = "BillingDataEntity.updateDatetime", 	query = "UPDATE BillingDataEntity a  SET a.notifiDateTime=TO_TIMESTAMP(:dateString,'dd-MM-yyyy hh:mi:ss') WHERE a.sdoCode=:sdocode AND a.accNo=:accNo AND a.billMonth=:billmonth AND to_char(a.billIssueDate,'dd-MM-yyyy')=:billissuedate"),

	@NamedQuery(name = "BillingDataEntity.findAcSdo", 	    query = "SELECT a.sdoCode, a.accNo, a.billMonth, a.billAmountBeforeDueDate, a.billAmountAfterDueDate, a.billIssueDate, a.billNo, a.dueDateCash, a.unitsConsumed, a.vendor, a.notifiDateTime FROM BillingDataEntity a WHERE a.accNo=:searchData AND a.sdoCode=:sdoCode ORDER BY a.billIssueDate DESC"),
	@NamedQuery(name = "BillingDataEntity.findByBnoSdo", 	query = "SELECT a.sdoCode, a.accNo, a.billMonth, a.billAmountBeforeDueDate, a.billAmountAfterDueDate, a.billIssueDate, a.billNo, a.dueDateCash, a.unitsConsumed, a.vendor, a.notifiDateTime FROM BillingDataEntity a WHERE a.billNo=:searchData AND a.sdoCode=:sdoCode ORDER BY a.billIssueDate DESC"),
	@NamedQuery(name = "BillingDataEntity.findByAmntSdo", 	query = "SELECT a.sdoCode, a.accNo, a.billMonth, a.billAmountBeforeDueDate, a.billAmountAfterDueDate, a.billIssueDate, a.billNo, a.dueDateCash, a.unitsConsumed, a.vendor, a.notifiDateTime FROM BillingDataEntity a WHERE a.billAmountBeforeDueDate=:searchData AND a.sdoCode=:sdoCode ORDER BY a.billIssueDate DESC"),


})
public class BillingDataEntity 
{
	@Id
	@Column(name="SDOCODE")
	private int sdoCode;
	
	@Column(name="ACCNO")
	private String accNo;
	
	@Column(name="BILLMONTH")
	private int billMonth;
	
	@Column(name="BILLAMOUNTBEFOREDUEDATE")
	private double billAmountBeforeDueDate;
	
	@Column(name="BILLAMOUNTAFTERDUEDATE")
	private double billAmountAfterDueDate;
	
	@Column(name="BILLISSUEDATE")
	private Date billIssueDate;
	
	@Column(name="BILLNO")
	private String billNo;
	
	@Column(name="DUEDATECASH")
	private Date dueDateCash;
	
	@Column(name="UNITSCONSUMED")
	private double unitsConsumed;
	
	@Column(name="VENDOR")
	private String vendor;
	
	@Column(name="NOTIFIDATEANDTIME")
	private Date notifiDateTime;
	
	public BillingDataEntity() 
	{
		
	}

	public Date getNotifiDateTime() {
		return notifiDateTime;
	}

	public void setNotifiDateTime(Date notifiDateTime) {
		this.notifiDateTime = notifiDateTime;
	}



	public int getSdoCode() {
		return sdoCode;
	}

	public void setSdoCode(int sdoCode) {
		this.sdoCode = sdoCode;
	}

	public String getAccNo() {
		return accNo;
	}

	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}

	public int getBillMonth() {
		return billMonth;
	}

	public void setBillMonth(int billMonth) {
		this.billMonth = billMonth;
	}

	public double getBillAmountBeforeDueDate() {
		return billAmountBeforeDueDate;
	}

	public void setBillAmountBeforeDueDate(double billAmountBeforeDueDate) {
		this.billAmountBeforeDueDate = billAmountBeforeDueDate;
	}

	public double getBillAmountAfterDueDate() {
		return billAmountAfterDueDate;
	}

	public void setBillAmountAfterDueDate(double billAmountAfterDueDate) {
		this.billAmountAfterDueDate = billAmountAfterDueDate;
	}


	public Date getBillIssueDate() {
		return billIssueDate;
	}

	public void setBillIssueDate(Date billIssueDate) {
		this.billIssueDate = billIssueDate;
	}

	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	public Date getDueDateCash() {
		return dueDateCash;
	}

	public void setDueDateCash(Date dueDateCash) {
		this.dueDateCash = dueDateCash;
	}

	public double getUnitsConsumed() {
		return unitsConsumed;
	}

	public void setUnitsConsumed(double unitsConsumed) {
		this.unitsConsumed = unitsConsumed;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

}
