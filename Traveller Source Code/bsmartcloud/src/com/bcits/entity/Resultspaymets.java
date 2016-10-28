package com.bcits.entity;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Resultspaymets {
	
	private String rrno;
	private String status;
	private String id;
	public String getRrno() {
		return rrno;
	}
	public void setRrno(String rrno) {
		this.rrno = rrno;
	}
	public String getStatus() {
		return status;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
