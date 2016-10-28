package com.pgrs.util;

import java.util.List;

public class ProjectMenu {
	private String projectName;
	private List<ModuleDetails> moduleDetails;
	
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public List<ModuleDetails> getModuleDetails() {
		return moduleDetails;
	}
	public void setModuleDetails(List<ModuleDetails> moduleDetails) {
		this.moduleDetails = moduleDetails;
	}
	
}
