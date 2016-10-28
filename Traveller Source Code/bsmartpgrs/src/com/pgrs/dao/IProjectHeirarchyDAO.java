package com.pgrs.dao;
// default package

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.pgrs.entity.ProjectHeirarchyEntity;

/**
 * Interface for RoleDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IProjectHeirarchyDAO extends GenericDAO<ProjectHeirarchyEntity>  {
	
	public List<ProjectHeirarchyEntity> read(Integer id, int projectId, int companyId);
	
	public ProjectHeirarchyEntity getDetailsOnId(int id);
	
	public int getIdByparentIdAndText(Integer id, String text);
	
	String getOfficeNameBasedOnId( int officeId );

	public Integer getParentIdOnId(int id);

	List<Object[]> getZones(int id);

	List<Object[]> getDivisions(int id);

	List<Object[]> getOffices(int id);
	
	public List<ProjectHeirarchyEntity> readCustom(Integer setId,int projectId, int companyId);

	public List<?> getAllOffice();

	List<?> getCircles(HttpServletRequest request);

	public String getCountBasedOnLevelAndProjectId(int level, int projectId,String siteCode);

	public String getDivisionSiteCode(int level, int projectId, Integer id);

	void delete(int id);

	public void deleteProjectHeirarchyBasedOnProjectId(int id);

	public List<ProjectHeirarchyEntity> read1(Integer integer, int projectId,
			int companyId);

}