package com.pgrs.service;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.MessageSource;

import com.novell.ldap.LDAPException;
import com.pgrs.entity.Role;

public interface RoleService
{
	List<Role> findAllRoles();
	
	List<Role> getAllRoles();
	
	public void save(Role role);
	
	public void update(Role role) throws LDAPException;
	
	public List<?> readRoleNames(int dnId);
	
	public void setRoleStatus(int rlId, String operation, HttpServletResponse response, MessageSource messageSource, Locale locale);
	
	List<?> getRolesBasedOnOfficeId(HttpServletRequest request) throws LDAPException;
	
	void deleteRole(int rlId);

	int getActiveUserBasedOnUserId(int urId);

	List<?> getDesignationNames();
}
