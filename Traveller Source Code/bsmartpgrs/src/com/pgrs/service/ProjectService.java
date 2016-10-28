package com.pgrs.service;

import java.util.List;

import com.pgrs.entity.Project;

public interface ProjectService {
	
	List<?> readhealthNames(int id);

	List<Project> getProjects();
}
