package com.bcits.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.Table;

@Entity
@Table(name="MASTER")
@NamedQueries({
	       /*    @NamedQuery(name="LocationOracleEntity.getAllLocations",query="SELECT l FROM LocationOracleEntity l ")*/
})
public class RrnoSearchEntity {
	
	@Id
	@Column(name="RRNO")
	private String rrno;
	
	@Column(name="CONSUMERNAME")
	private String consumername;

	@Column(name="CONSUMERADDRESS")
	private int consumeraddress;
	
	@Column(name="CONSUMERADDRESS1")
	private String consumeraddress1;
	
	@Column(name="PLACE")
	private String place;
	
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

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getLcno() {
		return lcno;
	}

	public void setLcno(String lcno) {
		this.lcno = lcno;
	}

	public String getFoliono() {
		return foliono;
	}

	public void setFoliono(String foliono) {
		this.foliono = foliono;
	}

	public String getFc() {
		return fc;
	}

	public void setFc(String fc) {
		this.fc = fc;
	}

	public String getCtration() {
		return ctration;
	}

	public void setCtration(String ctration) {
		this.ctration = ctration;
	}

	public String getCtratiod() {
		return ctratiod;
	}

	public void setCtratiod(String ctratiod) {
		this.ctratiod = ctratiod;
	}

	public String getCorporation() {
		return corporation;
	}

	public void setCorporation(String corporation) {
		this.corporation = corporation;
	}

	public String getAreaname() {
		return areaname;
	}

	public void setAreaname(String areaname) {
		this.areaname = areaname;
	}

	public int getSocode() {
		return socode;
	}

	public void setSocode(int socode) {
		this.socode = socode;
	}

	public String getFeedercode() {
		return feedercode;
	}

	public void setFeedercode(String feedercode) {
		this.feedercode = feedercode;
	}

	public String getTccode() {
		return tccode;
	}

	public void setTccode(String tccode) {
		this.tccode = tccode;
	}

	public String getPoleno() {
		return poleno;
	}

	public void setPoleno(String poleno) {
		this.poleno = poleno;
	}

	public String getMrcode() {
		return mrcode;
	}

	public void setMrcode(String mrcode) {
		this.mrcode = mrcode;
	}

	public String getTod() {
		return tod;
	}

	public void setTod(String tod) {
		this.tod = tod;
	}

	public String getLddate() {
		return lddate;
	}

	public void setLddate(String lddate) {
		this.lddate = lddate;
	}

	public String getWard() {
		return ward;
	}

	public void setWard(String ward) {
		this.ward = ward;
	}

	public String getBkwh() {
		return bkwh;
	}

	public void setBkwh(String bkwh) {
		this.bkwh = bkwh;
	}

	public String getTariffdcb() {
		return tariffdcb;
	}

	public void setTariffdcb(String tariffdcb) {
		this.tariffdcb = tariffdcb;
	}

	public int getTariffcode() {
		return tariffcode;
	}

	public void setTariffcode(int tariffcode) {
		this.tariffcode = tariffcode;
	}

	public String getDateofservice() {
		return dateofservice;
	}

	public void setDateofservice(String dateofservice) {
		this.dateofservice = dateofservice;
	}

	public String getSanctionkw() {
		return sanctionkw;
	}

	public void setSanctionkw(String sanctionkw) {
		this.sanctionkw = sanctionkw;
	}

	public String getSanctionhp() {
		return sanctionhp;
	}

	public void setSanctionhp(String sanctionhp) {
		this.sanctionhp = sanctionhp;
	}

	public String getContractdmd() {
		return contractdmd;
	}

	public void setContractdmd(String contractdmd) {
		this.contractdmd = contractdmd;
	}

	public String getPddate() {
		return pddate;
	}

	public void setPddate(String pddate) {
		this.pddate = pddate;
	}

	public String getTaluk() {
		return taluk;
	}

	public void setTaluk(String taluk) {
		this.taluk = taluk;
	}

	public String getNoofflats() {
		return noofflats;
	}

	public void setNoofflats(String noofflats) {
		this.noofflats = noofflats;
	}

	public String getInststatus() {
		return inststatus;
	}

	public void setInststatus(String inststatus) {
		this.inststatus = inststatus;
	}

	public String getStatusdate() {
		return statusdate;
	}

	public void setStatusdate(String statusdate) {
		this.statusdate = statusdate;
	}

	public String getAverage() {
		return average;
	}

	public void setAverage(String average) {
		this.average = average;
	}

	public int getReadingday() {
		return readingday;
	}

	public void setReadingday(int readingday) {
		this.readingday = readingday;
	}

	public String getSiderrno() {
		return siderrno;
	}

	public void setSiderrno(String siderrno) {
		this.siderrno = siderrno;
	}

	public String getPf() {
		return pf;
	}

	public void setPf(String pf) {
		this.pf = pf;
	}

	public String getTelephoneno() {
		return telephoneno;
	}

	public void setTelephoneno(String telephoneno) {
		this.telephoneno = telephoneno;
	}

	public String getPanchayath() {
		return panchayath;
	}

	public void setPanchayath(String panchayath) {
		this.panchayath = panchayath;
	}

	public String getNoofmeters() {
		return noofmeters;
	}

	public void setNoofmeters(String noofmeters) {
		this.noofmeters = noofmeters;
	}

	public String getVillagename() {
		return villagename;
	}

	public void setVillagename(String villagename) {
		this.villagename = villagename;
	}

	@Column(name="LCNO")
	private String lcno;
	
	@Column(name="FOLIONO")
	private String foliono;
	
	@Column(name="FC")
	private String fc;

	@Column(name="CTRATION")
	private String ctration;
	
	@Column(name="CTRATIOD")
	private String ctratiod;
	
	@Column(name="CORPORATION")
	private String corporation;
		
	@Column(name="AREANAME")
	private String areaname;

	@Column(name="SOCODE")
	private int socode;
	
	@Column(name="FEEDERCODE")
	private String feedercode;
	
	@Column(name="TCCODE")
	private String tccode;
	
	@Column(name="POLENO")
	private String poleno;
	
	@Column(name="MRCODE")
	private String mrcode;
	
	@Column(name="TOD")
	private String tod;

	@Column(name="LDDATE")
	private String lddate;
	
	@Column(name="WARD")
	private String ward;
	
	@Column(name="BKWH")
	private String bkwh;
	
	@Column(name="TARIFFDCB")
	private String tariffdcb;

	@Column(name="TARIFFCODE")
	private int tariffcode;
	
	@Column(name="DATEOFSERVICE")
	private String dateofservice;
	
	@Column(name="SANCTIONKW")
	private String sanctionkw;
	
	@Column(name="SANCTIONHP")
	private String sanctionhp;
	
	@Column(name="CONTRACTDMD")
	private String contractdmd;
	
	@Column(name="PDDATE")
	private String pddate;
	
	@Column(name="TALUK")
	private String taluk;

	@Column(name="NOOFFLATS")
	private String noofflats;
	
	@Column(name="INSTSTATUS")
	private String inststatus;
	
	@Column(name="STATUSDATE")
	private String statusdate;
	
	@Column(name="AVERAGE")
	private String average;

	@Column(name="READINGDAY")
	private int readingday;
	
	@Column(name="SIDERRNO")
	private String siderrno;
	
	@Column(name="PF")
	private String pf;
	
	@Column(name="TELEPHONENO")
	private String telephoneno;
	
	@Column(name="PANCHAYATH")
	private String panchayath;
	
	@Column(name="NOOFMETERS")
	private String noofmeters;
	
	@Column(name="VILLAGENAME")
	private String villagename;
}
