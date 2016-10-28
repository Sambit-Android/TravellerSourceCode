package com.pgrs.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pgrs.dao.IProjectDAO;
import com.pgrs.entity.Project;
import com.pgrs.service.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private IProjectDAO projectDao;
	
	@Override
	public List<?> readhealthNames(int id) {
		
		return projectDao.readHealthName(id);
	}

	@Override
	public List<Project> getProjects() {
		return projectDao.readuserdetails();
	}
	
}

