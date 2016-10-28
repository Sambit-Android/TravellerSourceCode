package com.pgrs.util;

import java.util.List;


public class CompanyMenu {
	private String companyName;
	private List<ProjectMenu> projects;
	
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public List<ProjectMenu> getProjects() {
		return projects;
	}
	public void setProjects(List<ProjectMenu> projects) {
		this.projects = projects;
	}
	
}
