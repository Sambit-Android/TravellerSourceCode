package com.pgrs.daoimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.pgrs.core.dao.MasterGenericDAO;
import com.pgrs.dao.IProjectDAO;
import com.pgrs.dao.IProjectHeirarchyDAO;
import com.pgrs.entity.Project;
import com.pgrs.entity.ProjectTreeTemplateLevels;

/**
 * A data access object (DAO) providing persistence and search support for
 * Project entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see .Project
 * @author Ravi Shankar Reddy
 */
@Repository
public class ProjectDAO extends GenericDAOImpl<Project> implements IProjectDAO {


	@Autowired
	private MasterGenericDAO genericDAO;
	
	@Autowired
	private IProjectHeirarchyDAO heirarchyDAO;
	
	@SuppressWarnings("serial")
	@Override
	public List<?> read() {
		
		List<Map<String, Object>> response = new ArrayList<Map<String, Object>>();    
		for (final Project record :genericDAO.findAll(Project.class)) {
			response.add(new HashMap<String, Object>() {{
				put("id", record.getId());
				put("name", record.getName());
				put("companyId", record.getCompany().getId());
				put("companyName", record.getCompany().getCompanyName());
				put("createdBy", record.getCreatedBy());
				put("updatedBy", record.getUpdatedBy());
				put("salesLedger",record.getSalesLedger());
				put("status", record.getStatus());
				put("enclosuresDetails", record.getEnclosuresDetails());
				put("metric",record.getMetric());
			}});
		}
		return response;
	}

	@Override
	 public String getProjectNameBasedOnId(int projectId) {
	  
	  List<String> projectNames = entityManager.createNamedQuery("Project.getProjectNameBasedOnId",String.class)
	    .setParameter("projectId", projectId)
	    .getResultList(); 
	  Iterator<String> it=projectNames.iterator();
	  while(it.hasNext()){

	   return (String) it.next();
	  }
	  return null;
	 }


	@Override
	public void saveEntity(Project project) {
		Project entity = new Project();
		entity.setName(project.getName());
		entity.setCompanyId(project.getCompanyId());
		entity.setSalesLedger(project.getSalesLedger());
		genericDAO.save(entity);
	}

	@Override
	@Transactional(propagation= Propagation.SUPPORTS)
	public void saveLevelDetails(
			ProjectTreeTemplateLevels projectTreeTemplateLevels) {
		
		genericDAO.save(projectTreeTemplateLevels);
		
	}

	@SuppressWarnings({ "rawtypes", "unchecked", "serial" })
	@Override
	public List<?> readTemplateLevels(int id) {
		
		Map criterias= new HashMap();
		criterias.put("projectId", id);
		
		List<Map<String, Object>> response = new ArrayList<Map<String, Object>>();    
		for (final ProjectTreeTemplateLevels record :genericDAO.findByCriteria(ProjectTreeTemplateLevels.class, criterias,"level",false)) {
			response.add(new HashMap<String, Object>() {{
				put("phlId", record.getPhlId());
				put("name", record.getName());
				put("level", record.getLevel());
				put("projectId", record.getProjectId());
				put("createdBy", record.getCreatedBy());
				put("updatedBy", record.getUpdatedBy());
				put("status", record.getStatus());
			}});
		}
		return response;
	}
	
	public Integer getOfficeIds(int id){
		Integer phId =  heirarchyDAO.getParentIdOnId(id);
		if(phId!=null){
			return phId;
		}
		else
			return null;
	}

	@Override
	public List<?> readHealthName(int id) {
		
		return entityManager.createNamedQuery("ProjectMetrices.readHealthNames").setParameter("id", id).getResultList();
	}

	@Override
	public List<Project> readuserdetails() {
		return entityManager.createNamedQuery("Project.getProjectName",Project.class).getResultList();
	}

	@Override
	public void projectStatusUpdate(int status, int projectId) {
		entityManager.createNamedQuery("Project.projectStatusUpdate").setParameter("status", status).setParameter("projectId", projectId).executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> getActiveUsersBasedOnProjectId(int projectId) {
		return entityManager.createNamedQuery("Project.getActiveUsersBasedOnProjectId").setParameter("projectId", projectId).getResultList();
	}


	
}