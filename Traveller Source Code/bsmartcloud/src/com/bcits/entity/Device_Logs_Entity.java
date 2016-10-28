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
@Table(name = "DEVICELOGS", schema = "VCLOUDENGINE")
@NamedQueries({ @NamedQuery(name = "Device_Logs_Entity.findAll", query = "SELECT mrde FROM Device_Logs_Entity mrde")

})
public class Device_Logs_Entity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "DEVICEID")
	private String deviceid;

	@Column(name = "REPORTDATE")
	private Timestamp reportdate;

	@Column(name = "LOG")
	private byte[] log;

	@Column(name = "dbbackup")
	private byte[] dbbackup;

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

	public byte[] getLog() {
		return log;
	}

	public void setLog(byte[] log) {
		this.log = log;
	}

	public Timestamp getReportdate() {
		return reportdate;
	}

	public void setReportdate(Timestamp reportdate) {
		this.reportdate = reportdate;
	}

	public byte[] getDbbackup() {
		return dbbackup;
	}

	public void setDbbackup(byte[] dbbackup) {
		this.dbbackup = dbbackup;
	}

}
