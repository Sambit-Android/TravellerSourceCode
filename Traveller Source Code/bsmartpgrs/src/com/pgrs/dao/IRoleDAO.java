package com.pgrs.dao;
// default package

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.MessageSource;

import com.pgrs.entity.Role;

public interface IRoleDAO extends GenericDAO<Role>
{
	public List<Role> findAll();
	
	public List<?> readRoleNamesForUniqueness(int dnId);
	
	public void updateRoleStatus(int rlId, String operation,HttpServletResponse response, MessageSource messageSource);
	
	List<Role> executeGetRolesBasedOnOfficeId( int officeId );
	
	String getRoleNameBasedOnId(int roleId);
	
	int getRoleIdBasedOnName(String roleName);
	
	public List<Role> findAllRoles();

	public int getActiveUserBasedOnUserId(int urId);

}