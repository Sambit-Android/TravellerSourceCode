package com.bcits.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;



@Entity
@Table(name="test_defective",schema="MVS")
@NamedQueries
({
	@NamedQuery(name="DefectiveMeterEntity.findallbylistdefective", query="SELECT cti FROM DefectiveMeterEntity cti where cti.listno =:listno and cti.status =0"),
	@NamedQuery(name="DefectiveMeterEntity.findcountdefective", query="SELECT count(ct) FROM DefectiveMeterEntity ct where ct.listno =:listno"),
	@NamedQuery(name="DefectiveMeterEntity.findavailabledownloadsdefective", query="SELECT count(ct) FROM DefectiveMeterEntity ct where ct.listno =:listno and ct.status =0"),
	@NamedQuery(name="DefectiveMeterEntity.updatestatus", query="UPDATE DefectiveMeterEntity ct  SET ct.status = 1 where ct.rrno =:rrno"),
})

public class DefectiveMeterEntity {

	
	@Id
	@Column(name="ID")
	private int id;
	
	@Column(name="RRNO")
	private String rrno;
	
	@Column(name="CONSUMERNAME")
	private String consumername;
	
	@Column(name="CONSUMERADDRESS")
	private String consumeraddress;
	
	@Column(name="METERSTATUS")
	private String meterstatus;
	
	@Column(name="POLENO")
	private String poleno;
	
	@Column(name="FEEDERCODE")
	private String federcode;
	
	@Column(name="TCCODE")
	private String tccode;
	
	@Column(name="SOCODE")
	private String socode;
	
	@Column(name="PRESENTREADING")
	private int presentreading;
	
	@Column(name="SANCTIONKW")
	private int sactionkw;
	
	@Column(name="SANCTIONHP")
	private int sactionhp;
	
	@Column(name="TARIFFDCB")
	private String tariffdcb;
	
	@Column(name="STATUS")
	private int status;
	
	@Column(name="LISTNO")
	private int listno;

	
	public DefectiveMeterEntity() {
		// TODO Auto-generated constructor stub
	
	
	}
	
	
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

	public String getConsumername() {
		return consumername;
	}

	public void setConsumername(String consumername) {
		this.consumername = consumername;
	}

	public String getConsumeraddress() {
		return consumeraddress;
	}

	public void setConsumeraddress(String consumeraddress) {
		this.consumeraddress = consumeraddress;
	}

	public String getMeterstatus() {
		return meterstatus;
	}

	public void setMeterstatus(String meterstatus) {
		this.meterstatus = meterstatus;
	}

	public String getPoleno() {
		return poleno;
	}

	public void setPoleno(String poleno) {
		this.poleno = poleno;
	}

	public String getFedercode() {
		return federcode;
	}

	public void setFedercode(String federcode) {
		this.federcode = federcode;
	}

	public String getTccode() {
		return tccode;
	}

	public void setTccode(String tccode) {
		this.tccode = tccode;
	}

	public String getSocode() {
		return socode;
	}

	public void setSocode(String socode) {
		this.socode = socode;
	}

	public int getPresentreading() {
		return presentreading;
	}

	public void setPresentreading(int presentreading) {
		this.presentreading = presentreading;
	}

	public int getSactionkw() {
		return sactionkw;
	}

	public void setSactionkw(int sactionkw) {
		this.sactionkw = sactionkw;
	}

	public int getSactionhp() {
		return sactionhp;
	}

	public void setSactionhp(int sactionhp) {
		this.sactionhp = sactionhp;
	}

	public String getTariffdcb() {
		return tariffdcb;
	}

	public void setTariffdcb(String tariffdcb) {
		this.tariffdcb = tariffdcb;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getListno() {
		return listno;
	}

	public void setListno(int listno) {
		this.listno = listno;
	}

	
}
