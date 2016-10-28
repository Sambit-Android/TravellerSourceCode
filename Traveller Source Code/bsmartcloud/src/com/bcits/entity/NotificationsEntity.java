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
@Table(name="NOTIFICATIONS",schema="CONSUMERAPP")
@NamedQueries
({
	@NamedQuery(name = "NotificationsEntity.findAll", query = "SELECT a FROM NotificationsEntity a"),
})
public class NotificationsEntity 
{
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="NOTIFICATIONDETAILS")
	private String notificationDetails;
	
	@Column(name="EXPIREDDATE")
	private Date expireDate;
	
	@Column(name="DATEPUBLISHED")
	private Date datePublished;

	@Column(name="TITLE")
	private String title;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNotificationDetails() {
		return notificationDetails;
	}

	public void setNotificationDetails(String notificationDetails) {
		this.notificationDetails = notificationDetails;
	}

	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	public Date getDatePublished() {
		return datePublished;
	}

	public void setDatePublished(Date datePublished) {
		this.datePublished = datePublished;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	
	
}
