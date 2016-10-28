package com.bcits.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="APPACTIVITY",schema="VCLOUDENGINE")
@NamedQueries
({
	@NamedQuery(name = "AppActivityEntity.findAll", query = "SELECT a FROM AppActivityEntity a")
})
public class AppActivityEntity 
{
	@Id
	//@SequenceGenerator(name="appActivityId",sequenceName="APPACTIVITY_ID")
	//@GeneratedValue(generator="appActivityId")
	@Column(name="ID")
	private int id;
	
	@Column(name="APPNAME")
	private String appName;
	
	@Column(name="TRANSTIME")
	private Timestamp transTime;
	
	@Column(name="ACTIVITY")
	private String activity;

	public AppActivityEntity() 
	{
		
	}

	
	public AppActivityEntity(int id, String appName, Timestamp transTime,
			String activity) {
		super();
		this.id = id;
		this.appName = appName;
		this.transTime = transTime;
		this.activity = activity;
	}




	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public Timestamp getTransTime() {
		return transTime;
	}

	public void setTransTime(Timestamp transTime) {
		this.transTime = transTime;
	}

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}
	
	
}
