package com.pgrs.daoimpl;
// default package

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Repository;

import com.pgrs.controller.ProductAccessController;
import com.pgrs.core.dao.MasterGenericDAO;
import com.pgrs.dao.IRoleDAO;
import com.pgrs.entity.Role;
import com.pgrs.ldap.LdapBusinessModel;

@Repository
public class RoleDAO extends GenericDAOImpl<Role> implements IRoleDAO {
	
	@Autowired
	private MasterGenericDAO genericDAO;
	
	@Autowired
	private LdapBusinessModel ldapBusinessModel;
	
	@Autowired
	private ProductAccessController productAccessController;
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Role> findAllRoles() {
		return entityManager.createNamedQuery("Role.findAllRoles").getResultList();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Role> findAll() {		
		return readRoles(entityManager.createNamedQuery("Role.findAll").getResultList());
	}
	
	@SuppressWarnings("rawtypes")
	private List readRoles(List<?> roles) {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		 Map<String, Object> rolesData = null;
		 for (Iterator<?> iterator = roles.iterator(); iterator.hasNext();)
		 {
			 final Object[] values = (Object[]) iterator.next();
			 rolesData = new HashMap<String, Object>();
			 
			 rolesData.put("rlId", (Integer)values[0]);
			 rolesData.put("rlName",(String)values[1]);		
			 rolesData.put("rlDescription",(String)values[2]);
			 rolesData.put("roleStatus", (String)values[3]);
			 rolesData.put("dnId", (Integer)values[4]);
			 rolesData.put("dnName", (String)values[5]);
		     result.add(rolesData); 
		 }
		 return result;
	}

	@Override
	public List<?> readRoleNamesForUniqueness(int dnId) {		
	    return entityManager.createNamedQuery("Role.readRoleNames").setParameter("dnId", dnId).getResultList();	
	}

	@Override
	public void updateRoleStatus(int rlId, String operation,HttpServletResponse response, MessageSource messageSource) 
	{	
		try
		{
			PrintWriter out = response.getWriter();	
			if(operation.equalsIgnoreCase("activate"))
			{
				entityManager.createNamedQuery("Role.UpdateStatus").setParameter("rlStatus", "Active").setParameter("rlId", rlId).executeUpdate();
				Role role=genericDAO.getById(Role.class, rlId);
				try{
					productAccessController.addGroup("ou=Roles", role.getRlName(), ""+role.getId());
				}catch(Exception e){
					
				}
				//ldapBusinessModel.addRoles(role.getRlName(),role.getRlDescription());
				out.write("Role Activated");
			}
			else
			{
			   entityManager.createNamedQuery("Role.UpdateStatus").setParameter("rlStatus", "Inactive").setParameter("rlId", rlId).executeUpdate();
			   Role role=genericDAO.getById(Role.class, rlId);
			   ldapBusinessModel.deleteRoles(role.getRlName());
			   productAccessController.deleteGroup("ou=Roles", role.getRlName());
			   out.write("Role Deactivated");				
			}			
		}
		catch(Exception e){
		   e.printStackTrace();
	    }
    }

	@Override
	public List<Role> executeGetRolesBasedOnOfficeId(int officeId) {
		
		return entityManager.createNamedQuery("Role.executeGetRolesBasedOnOfficeId",Role.class)
				.setParameter("officeId", officeId)
				.getResultList();
	}

	@Override
	public String getRoleNameBasedOnId(int roleId) {
		
		List<String> roleNames = entityManager.createNamedQuery("Role.getRoleNameBasedOnId",String.class)
				.setParameter("roleId", roleId)
				.getResultList();	
		Iterator<String> it=roleNames.iterator();
		while(it.hasNext()){

			return (String) it.next();
		}
		return null;
	}
	@Override
	public int getRoleIdBasedOnName(String roleName) {
		
		List<Integer> roleNames = entityManager.createNamedQuery("Role.getRoleIdBasedOnName",Integer.class)
				.setParameter("roleName", roleName)
				.getResultList();	
		Iterator<Integer> it=roleNames.iterator();
		while(it.hasNext()){

			return (int) it.next();
		}
		return 0;
	}

	@Override
	public int getActiveUserBasedOnUserId(int urId) {
		return (int)entityManager.createNamedQuery("Role.getActiveUserBasedOnUserId").setParameter("urId", urId).getSingleResult();
	}	
}