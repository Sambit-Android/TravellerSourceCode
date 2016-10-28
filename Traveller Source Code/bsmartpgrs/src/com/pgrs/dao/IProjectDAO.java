package com.pgrs.dao;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.pgrs.entity.Project;
import com.pgrs.entity.ProjectTreeTemplateLevels;


public interface IProjectDAO extends GenericDAO<Project> {

	List<?> read();

	void saveEntity(Project project);

	String getProjectNameBasedOnId( int projectId );
	
	@Transactional(propagation= Propagation.SUPPORTS)
	void saveLevelDetails(ProjectTreeTemplateLevels projectTreeTemplateLevels);

	List<?> readTemplateLevels(int id);

	List<?> readHealthName(int id);

	List<Project> readuserdetails();

	void projectStatusUpdate(int status, int id);

	List<Integer> getActiveUsersBasedOnProjectId(int id);

}