package com.pgrs.beans;

import java.io.Serializable;

public class UserProfileBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2891224984009241779L;
	
	private int userId;
	
	private String userName;
	
	private String urLoginName;
	
	private String contactNo;
	
	private String emailId;
	
	private String deptName;
	
	private String desgName;
	
	private String officeName;
	
	private String address;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUrLoginName() {
		return urLoginName;
	}

	public void setUrLoginName(String urLoginName) {
		this.urLoginName = urLoginName;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getDesgName() {
		return desgName;
	}

	public void setDesgName(String desgName) {
		this.desgName = desgName;
	}

	public String getOfficeName() {
		return officeName;
	}

	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
}
