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
@Table(name="PAYMENTSDATA",schema="CONSUMERAPP")
@NamedQueries
({
	@NamedQuery(name = "PaymentsDataEntity.findByConsumerNo", query = "SELECT a.sdoCode, a.consumerNo, a.paymentDate,a.amount, a.receiptNo, a.modeOfPayment, a.vendor FROM PaymentsDataEntity a WHERE a.consumerNo=:searchData and a.modeOfPayment like:modeOfPayment ORDER BY a.paymentDate DESC"),
	@NamedQuery(name = "PaymentsDataEntity.findBypaymentMode", query = "SELECT a.sdoCode, a.consumerNo, a.paymentDate,a.amount, a.receiptNo, a.modeOfPayment, a.vendor FROM PaymentsDataEntity a WHERE a.modeOfPayment=:modeOfPayment ORDER BY a.paymentDate DESC"),
	@NamedQuery(name = "PaymentsDataEntity.findByReceiptNo", query = "SELECT a.sdoCode, a.consumerNo, a.paymentDate,a.amount, a.receiptNo, a.modeOfPayment, a.vendor FROM PaymentsDataEntity a WHERE a.receiptNo=:searchData and a.modeOfPayment like:modeOfPayment ORDER BY a.paymentDate DESC"),
	@NamedQuery(name = "PaymentsDataEntity.findByAmount", query = "SELECT a.sdoCode, a.consumerNo, a.paymentDate, a.amount, a.receiptNo, a.modeOfPayment, a.vendor FROM PaymentsDataEntity a WHERE a.amount=:searchData and a.modeOfPayment like:modeOfPayment ORDER BY a.paymentDate DESC"),
  
	@NamedQuery(name = "PaymentsDataEntity.findByCnoSdo", query = "SELECT a.sdoCode, a.consumerNo, a.paymentDate,a.amount, a.receiptNo, a.modeOfPayment, a.vendor FROM PaymentsDataEntity a WHERE a.consumerNo=:searchData AND a.sdoCode=:sdoCode and a.modeOfPayment like:modeOfPayment ORDER BY a.paymentDate DESC"),
	@NamedQuery(name = "PaymentsDataEntity.findByRnoSdo", query = "SELECT a.sdoCode, a.consumerNo, a.paymentDate,a.amount, a.receiptNo, a.modeOfPayment, a.vendor FROM PaymentsDataEntity a WHERE a.receiptNo=:searchData AND a.sdoCode=:sdoCode and a.modeOfPayment like:modeOfPayment ORDER BY a.paymentDate DESC"),
	@NamedQuery(name = "PaymentsDataEntity.findByAmntSdo", query = "SELECT a.sdoCode, a.consumerNo, a.paymentDate, a.amount, a.receiptNo, a.modeOfPayment, a.vendor FROM PaymentsDataEntity a WHERE a.amount=:searchData AND a.sdoCode=:sdoCode and a.modeOfPayment like:modeOfPayment ORDER BY a.paymentDate DESC"),
	@NamedQuery(name = "PaymentsDataEntity.findBySdoPayMode", query = "SELECT a.sdoCode, a.consumerNo, a.paymentDate,a.amount, a.receiptNo, a.modeOfPayment, a.vendor FROM PaymentsDataEntity a WHERE a.modeOfPayment=:modeOfPayment AND a.sdoCode=:sdoCode and a.modeOfPayment like:modeOfPayment ORDER BY a.paymentDate DESC"),

})
public class PaymentsDataEntity 
{

	@Id
	//@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="PAYMENTID")
	private int paymentId;
	
	@Column(name="SDOCODE")
	private int sdoCode;
	
	@Column(name="CONSUMERNO")
	private String consumerNo;
	
	@Column(name="PAYMENTDATE")
	private Date paymentDate;
	
	@Column(name="AMOUNT")
	private double amount;
	
	@Column(name="RECEIPTNO")
	private String receiptNo;
	
	@Column(name="MODEOFPAYMENT")
	private String modeOfPayment;
	
	@Column(name="VENDOR")
	private String vendor;

	public PaymentsDataEntity() 
	{
		
	}

	public PaymentsDataEntity(int paymentId, int sdoCode, String consumerNo,
			Date paymentDate, double amount, String receiptNo,
			String modeOfPayment, String vendor) {
		super();
		this.paymentId = paymentId;
		this.sdoCode = sdoCode;
		this.consumerNo = consumerNo;
		this.paymentDate = paymentDate;
		this.amount = amount;
		this.receiptNo = receiptNo;
		this.modeOfPayment = modeOfPayment;
		this.vendor = vendor;
	}

	public int getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}

	public int getSdoCode() {
		return sdoCode;
	}

	public void setSdoCode(int sdoCode) {
		this.sdoCode = sdoCode;
	}

	public String getConsumerNo() {
		return consumerNo;
	}

	public void setConsumerNo(String consumerNo) {
		this.consumerNo = consumerNo;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getReceiptNo() {
		return receiptNo;
	}

	public void setReceiptNo(String receiptNo) {
		this.receiptNo = receiptNo;
	}

	public String getModeOfPayment() {
		return modeOfPayment;
	}

	public void setModeOfPayment(String modeOfPayment) {
		this.modeOfPayment = modeOfPayment;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}
	
}
