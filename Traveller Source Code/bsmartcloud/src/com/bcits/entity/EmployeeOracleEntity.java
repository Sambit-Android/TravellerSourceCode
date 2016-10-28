package com.bcits.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.Table;

@Entity
@Table(name="EMPLOYEE",schema="BSMART")
@NamedQueries({
	       /*    @NamedQuery(name="LocationOracleEntity.getAllLocations",query="SELECT l FROM LocationOracleEntity l ")*/
})
public class EmployeeOracleEntity {

	
	@Id
	@Column(name="USERNAME")
	private String username;
	
	@Column(name="PASSWORD")
	private String password;

	@Column(name="LOGINNAME")
	private int loginname;
	
	@Column(name="DESIGNATION")
	private String designation;
	
	@Column(name="USERTYPE")
	private String usertype;
	
	@Column(name="ID")
	private String id;
	
	@Column(name="USERACTIVE")
	private String useractive;
	
	@Column(name="EMAIL")
	private String email;

	@Column(name="MOBILE")
	private String mobile;
	
	@Column(name="EMPNAME")
	private String empname;
	
	@Column(name="SITECODE")
	private String sitecode;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getLoginname() {
		return loginname;
	}

	public void setLoginname(int loginname) {
		this.loginname = loginname;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getUsertype() {
		return usertype;
	}

	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUseractive() {
		return useractive;
	}

	public void setUseractive(String useractive) {
		this.useractive = useractive;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmpname() {
		return empname;
	}

	public void setEmpname(String empname) {
		this.empname = empname;
	}

	public String getSitecode() {
		return sitecode;
	}

	public void setSitecode(String sitecode) {
		this.sitecode = sitecode;
	}

	
}
