package com.bcits.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "DEVICESTATUS", schema = "VCLOUDENGINE")
@NamedQueries({
	@NamedQuery(name = "MasterDeviceStatusEntity.findAll", query = "SELECT mdse FROM MasterDeviceStatusEntity mdse "),
	@NamedQuery(name = "MasterDeviceStatusEntity.getDeviceDetails", query = "SELECT m FROM MasterDeviceStatusEntity m WHERE m.deviceid=:deviceId"),
	@NamedQuery(name = "MasterDeviceStatusEntity.DeviceHealth", query = "SELECT s FROM MasterDeviceStatusEntity s,MRDeviceAllocationEntity d WHERE d.deviceid=s.deviceid  AND d.mrCode =:mrcode AND d.sdoCode=:sdoCode AND TO_CHAR(s.datetimestamp,'dd-mm-yyyy')=:date")

})
public class MasterDeviceStatusEntity {
	

	   @Id
       @GeneratedValue(strategy = GenerationType.IDENTITY)
	   
	   @Column(name = "id")
	private Integer id;
	
	   @Column(name = "deviceid")
	private String deviceid;
	
	   @Column(name = "batterylevel")
	private int batterylevel;
	
	   @Column(name = "signalstrength")
	private int signalstrength;
	
	   @Column(name = "memoryinternalaltotal")
	private float memoryinternalaltotal;
	
	   @Column(name = "appversion")
	private String appversion;
	
	   @Column(name = "reporteddateandtime")
	private Timestamp reporteddateandtime;
	
	   @Column(name = "remarks")
	private String remarks;
	
	   @Column(name = "memoryinternalavailable")
	private float memoryinternalavailable;
	
	   @Column(name = "memoryexternaltotal")
	private float memoryexternaltotal;
	
	   @Column(name = "memoryexternalavailable")
	private float memoryexternalavailable;
	   
	   @Column(name = "datetimestamp")
		private Timestamp datetimestamp;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDeviceid() {
		return deviceid;
	}

	public void setDeviceid(String deviceid) {
		this.deviceid = deviceid;
	}

	public int getBatterylevel() {
		return batterylevel;
	}

	public void setBatterylevel(int batterylevel) {
		this.batterylevel = batterylevel;
	}

	public int getSignalstrength() {
		return signalstrength;
	}

	public void setSignalstrength(int signalstrength) {
		this.signalstrength = signalstrength;
	}

	public float getMemoryinternalaltotal() {
		return memoryinternalaltotal;
	}

	public void setMemoryinternalaltotal(float memoryinternalaltotal) {
		this.memoryinternalaltotal = memoryinternalaltotal;
	}

	public String getAppversion() {
		return appversion;
	}

	public void setAppversion(String appversion) {
		this.appversion = appversion;
	}

	public Timestamp getReporteddateandtime() {
		return reporteddateandtime;
	}

	public void setReporteddateandtime(Timestamp reporteddateandtime) {
		this.reporteddateandtime = reporteddateandtime;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public float getMemoryinternalavailable() {
		return memoryinternalavailable;
	}

	public void setMemoryinternalavailable(float memoryinternalavailable) {
		this.memoryinternalavailable = memoryinternalavailable;
	}

	public float getMemoryexternaltotal() {
		return memoryexternaltotal;
	}

	public void setMemoryexternaltotal(float memoryexternaltotal) {
		this.memoryexternaltotal = memoryexternaltotal;
	}

	public float getMemoryexternalavailable() {
		return memoryexternalavailable;
	}

	public void setMemoryexternalavailable(float memoryexternalavailable) {
		this.memoryexternalavailable = memoryexternalavailable;
	}

	public Timestamp getDatetimestamp() {
		return datetimestamp;
	}

	public void setDatetimestamp(Timestamp datetimestamp) {
		this.datetimestamp = datetimestamp;
	}	
	
}
