package com.bcits.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/*@Entity
@Table(name="MATRIX")
@NamedQueries
({
	           @NamedQuery(name="MatrixEntity.getMrBillingProgress",query="SELECT b FROM BillingProgressEntity b where b.sitecode=:sitecode")
})*/


public class MatrixEntity implements Serializable
{
	@Column(name="RRNO")
	private String rrno;
	
	@Column(name="RDNGMONTH")
	private String rdngmonth;
	
	@Column(name="TARIFFDESC")
	private String tariffdesc;
	
	@Column(name="CONSUMERNAME")
	private String consumername;	
	
	@Column(name="CONSUMERADDRESS")
	private int consumeraddress;	
	
	@Column(name="CONSUMERADDRESS1")
	private String consumeraddress1;
	
	
	@Column(name="LATITUDE")
	private String latitude;
	
	@Column(name="LONGTITUTED")
	private String longitude;
	
	@Column(name="RDNGDATE")
	private String rdngdate;
	
	@Column(name="SOCODE")
	private String socode;	
	
	@Column(name="FEEDERCODE")
	private int feedercode;	
	
	@Column(name="TCCODE")
	private String tccode;
	
	@Column(name="METERSTATUS")
	private String meterstatus;
	
	@Column(name="BILLNO")
	private String billno;
	
	@Column(name="BILLDATE")
	private String billdate;
	
	@Column(name="NETAMTDUE")
	private String netamtdue;
	

	public String getBillno() {
		return billno;
	}

	public void setBillno(String billno) {
		this.billno = billno;
	}

	public String getRrno() {
		return rrno;
	}

	public void setRrno(String rrno) {
		this.rrno = rrno;
	}

	public String getRdngmonth() {
		return rdngmonth;
	}

	public void setRdngmonth(String rdngmonth) {
		this.rdngmonth = rdngmonth;
	}

	public String getTariffdesc() {
		return tariffdesc;
	}

	public void setTariffdesc(String tariffdesc) {
		this.tariffdesc = tariffdesc;
	}

	public String getConsumername() {
		return consumername;
	}

	public void setConsumername(String consumername) {
		this.consumername = consumername;
	}

	public int getConsumeraddress() {
		return consumeraddress;
	}

	public void setConsumeraddress(int consumeraddress) {
		this.consumeraddress = consumeraddress;
	}

	public String getConsumeraddress1() {
		return consumeraddress1;
	}

	public void setConsumeraddress1(String consumeraddress1) {
		this.consumeraddress1 = consumeraddress1;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getRdngdate() {
		return rdngdate;
	}

	public void setRdngdate(String rdngdate) {
		this.rdngdate = rdngdate;
	}

	public String getSocode() {
		return socode;
	}

	public void setSocode(String socode) {
		this.socode = socode;
	}

	public int getFeedercode() {
		return feedercode;
	}

	public void setFeedercode(int feedercode) {
		this.feedercode = feedercode;
	}

	public String getTccode() {
		return tccode;
	}

	public void setTccode(String tccode) {
		this.tccode = tccode;
	}

	public String getMeterstatus() {
		return meterstatus;
	}

	public void setMeterstatus(String meterstatus) {
		this.meterstatus = meterstatus;
	}
	
	
}