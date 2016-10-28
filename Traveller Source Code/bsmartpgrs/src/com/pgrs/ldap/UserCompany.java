package com.pgrs.ldap;

import java.util.List;

public class UserCompany implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String companyName;
	private List<UserProjects> userProject;
	
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public List<UserProjects> getUserProject() {
		return userProject;
	}
	public void setUserProject(List<UserProjects> userProject) {
		this.userProject = userProject;
	}
}
