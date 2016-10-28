package com.pgrs.serviceimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.WordUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.novell.ldap.LDAPException;
import com.pgrs.core.dao.MasterGenericDAO;
import com.pgrs.dao.IRoleDAO;
import com.pgrs.entity.Designation;
import com.pgrs.entity.Role;
import com.pgrs.ldap.LdapBusinessModel;
import com.pgrs.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService
{	
	static Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);

	@Autowired
	private IRoleDAO iRoleDAO;
	@Autowired
	private MasterGenericDAO genericDAO;
	@Autowired
	private LdapBusinessModel ldapBusinessModel;

	@Override
	public List<Role> findAllRoles()
	{
		return iRoleDAO.findAllRoles();
	}

	@Override
	public List<Role> getAllRoles() 
	{			
		return iRoleDAO.findAll();
	}

	@Override
	public void save(Role role)
	{	
		role.setRlName(WordUtils.capitalizeFully((String) role.getRlName()));
		iRoleDAO.save(role);	
	}

	@Override
	public void update(Role role) throws LDAPException 
	{
		Role roles=genericDAO.getById(Role.class, role.getId());	
		ldapBusinessModel.editRoles(role.getRlName(),roles.getRlName());		
		iRoleDAO.update(role);
	}

	@Override
	public List<?> readRoleNames(int dnId) 
	{			
		return iRoleDAO.readRoleNamesForUniqueness(dnId);
	}

	@Override
	public void setRoleStatus(int rlId, String operation,HttpServletResponse response, MessageSource messageSource,Locale locale) 
	{	
		iRoleDAO.updateRoleStatus(rlId,operation,response,messageSource);
	}
	@SuppressWarnings({ "rawtypes", "unchecked", "serial" })
	@Override
	public List<?> getRolesBasedOnOfficeId(HttpServletRequest request) throws LDAPException
	{
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>(); 
		//List<String> roleNames = ldapBusinessModel.getRolesBaseddonProject(projectName,companyName);
		Map criterias= new HashMap();
		criterias.put("roleStatus","Active");
		List<Role> roles = genericDAO.findByCriteria(Role.class, criterias);
		for (final Role role : roles) {
			result.add(new HashMap<String, Object>() 
					{{
						put("rlName", role.getRlName());
						put("rlId", role.getId());
						put("dnId", role.getDnId());
					}});

		}
		return result;

	}

	@Override
	public void deleteRole(int rlId) {
		iRoleDAO.delete(rlId);
	}

	@Override
	public int getActiveUserBasedOnUserId(int urId) {
		return iRoleDAO.getActiveUserBasedOnUserId(urId);
	}

	@SuppressWarnings({ "rawtypes", "serial", "unchecked" })
	@Override
	public List<?> getDesignationNames() {
		
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>(); 
		Map criterias= new HashMap();
		criterias.put("designationStatus","Active");
		List<Designation> designationList = genericDAO.findByCriteria(Designation.class, criterias);
		for (final Designation designation : designationList) {
			result.add(new HashMap<String, Object>() 
					{{
						put("dnId", designation.getDnId());
						put("dnName", designation.getDnName());
					}});

		}
		return result;
	}
}
