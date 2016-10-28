package com.pgrs.daoimpl;
// default package

import java.io.IOException;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.pgrs.dao.IUsersDAO;
import com.pgrs.entity.Company;
import com.pgrs.entity.Project;
import com.pgrs.entity.Users;

/**
 * A data access object (DAO) providing persistence and search support for Users
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see .Users
 * @author MyEclipse Persistence Tools
 */
@Repository

public class UsersDAO extends GenericDAOImpl<Users> implements IUsersDAO {
	
	@Override
	public List<?> executeReadObjectQuery() {		
		return entityManager.createNamedQuery("Users.readAllUsers").getResultList();
	}

	@Override
	public Blob executeGetImageQuery(int urId) {
		
		return (Blob) entityManager.createNamedQuery("Users.getImage", Blob.class).setParameter("urId", urId)
				.getSingleResult();
	}

	@Override
	public void executeUploadImageOnId(int urId, MultipartFile files){
		
	 	/*FileInputStream fis = null;
	 	Blob data = null;*/
	 	byte[] imsgeBytes = null;
	 	
	 	try {
			imsgeBytes = files.getBytes();
		} catch (IOException e) {
			e.printStackTrace();
		}
		/*try {
			File convFile = new File( files.getOriginalFilename());
			files.transferTo(convFile);
			fis = new FileInputStream(convFile);
			
			bytes = IOUtils.toByteArray(fis);
			data = entityManager.unwrap(Session.class).getLobHelper().createBlob(fis, convFile.length());
		} catch (Exception e) {
			e.printStackTrace();
		}*/	
		entityManager.createNamedQuery("Users.uploadImageOnId").setParameter("urId", urId).setParameter("personImage", imsgeBytes).executeUpdate();
	}

	@Override
	public void executeStatusChangeQuery(int status, int urId) {
		entityManager.createNamedQuery("Users.UpdateUserStatus").setParameter("userStatus", status).setParameter("urId", urId).executeUpdate();
	}

	@Override
	public Users getUserBasedOnId(int urId) {
		
		return entityManager.createNamedQuery("Users.getUserBasedOnId",Users.class)
				.setParameter("urId", urId)
				.getSingleResult();
	}

	@Override
	public List<Project> getProjects(String name) {
		return entityManager.createNamedQuery("Users.getprojects",Project.class).setParameter("urLoginName", name).getResultList();
	}

	@Override
	public List<Company> getCompany(String name) {
		return entityManager.createNamedQuery("Users.getcompanies",Company.class).setParameter("urLoginName", name).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<String> getUserRoles( int dnId ) {
		return new HashSet<String>(entityManager.createNamedQuery("Users.getUserRoles").setParameter("dnId", dnId).getResultList());
	}

	@Override
	public List<String> getLoginNames() {
		/*return  entityManager.createNamedQuery("Users.getLoginNames",String.class)
				.getResultList();*/
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Users> getActiveUsers() {
		return  entityManager.createNamedQuery("Users.getActiveUsers").setParameter("userStatus", 1).getResultList();
	}

	@Override
	public List<?> readAllLoginNames() {
		
		return  entityManager.createNamedQuery("Users.readLoginNames").getResultList();
	}

	
	public int readuserid(String username) {
			int tm= (Integer) entityManager.createNamedQuery("Users.userid").setParameter("username",username).getResultList().get(0);
			return tm;
	}
	
	@SuppressWarnings("rawtypes")
	private List readDepartment_two(List<?> recoopment)
	{
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		Map<String, Object> userData = null;
		for (Iterator<?> iterator =  recoopment.iterator(); iterator.hasNext();)
		{
			 final Users values =  (Users) iterator.next();
			 userData = new HashMap<String, Object>();
			 userData.put("UrName", (String)values.getUrName());
			 userData.put("LoginName", (String)values.getUrLoginName());
			 userData.put("ContactNo", (String)values.getUrContactNo());
			 userData.put("EmailId", (String)values.getUrEmailId());
			 userData.put("Permanent", (String)values.getUrPermanent());
			 userData.put("DeptId", (Integer)values.getDeptId());
			 userData.put("DnId", (Integer)values.getDnId());
			 userData.put("CreatedDate", (Date)values.getCreatedDate());
		     result.add(userData); 
		 }
		 return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Users> readuserdetailsforprofile(int user_id) {
		return  readDepartment_two( entityManager.createNamedQuery("Users.getUserDetailsforprofile").setParameter("user_id", user_id).getResultList());
	}

	@Override
	public String readuser(int user_id) {
		String tm= (String) entityManager.createNamedQuery("Users.getLoggeduser").setParameter("user_id",user_id).getResultList().get(0);
		return tm;
	}

	@Override
	public Object[] getUserDetailsByLoginName(String loginName) {
		return (Object[]) entityManager.createNamedQuery("Users.getUserDetailsByLoginName").setParameter("loginName",loginName).getSingleResult();
	}

	@Override
	public void updateuserDetailsFromProfile(int userId,String userName,String contactNo,String emailId) {
		entityManager.createNamedQuery("Users.updateuserDetailsFromProfile").setParameter("userId", userId).setParameter("userName", userName).setParameter("contactNo", contactNo).setParameter("emailId", emailId).executeUpdate();
	}

	@Override
	public String getThemeNameByLoginName(String userLoginName) {
		return (String) entityManager.createNamedQuery("Users.getThemeNameByLoginName").setParameter("userLoginName",userLoginName).getSingleResult();
	}

	@Override
	public void updateTheme(String urLoginName, String themeName) {
		entityManager.createNamedQuery("Users.updateTheme").setParameter("urLoginName", urLoginName).setParameter("themeName", themeName).executeUpdate();
	}

	@Override
	public List<String> userNameValidation(String urLoginName,String urEmail, String mobileNo) {
		return entityManager.createNamedQuery("Users.userNameValidation",String.class).setParameter("urLoginName",urLoginName).setParameter("urEmail", urEmail).setParameter("mobileNo", mobileNo).getResultList();
	}
	
	@Override
	public List<?> readAllContactNumbers() {
		return  entityManager.createNamedQuery("Users.readAllContactNumbers").getResultList();	}

	@Override
	public List<?> readAllEmailAddress() {
		return  entityManager.createNamedQuery("Users.readAllEmailAddress").getResultList();
	}

	@Override
	public String getUserDesignationOnLoginName(String urLoginName) {
		return  entityManager.createNamedQuery("Users.UserDesignationOnLoginName",String.class).setParameter("urLoginName", urLoginName).getSingleResult();
	}

	@Override
	public String getSiteCodeOnLoginName(String urLoginName) {
		return entityManager.createNamedQuery("Users.UserSiteCodeOnLoginName",String.class).setParameter("urLoginName", urLoginName).getSingleResult();
	}

	@Override
	public List<Integer> getAllInActiveUsres(int status) {
		return entityManager.createNamedQuery("Users.getAllInActiveUsres",Integer.class).setParameter("status", status).getResultList();
	}

	@Override
	public List<?> getUserDetailsBySiteCodeAndRole(String sitecode,String pahseCompletedCode) {
		return entityManager.createNamedQuery("Users.getUserOnSiteCodeAndRole").setParameter("sitecode", sitecode+"%").setParameter("pahseCompletedCode", pahseCompletedCode).getResultList();
	}

	@Override
	public List<?> getEmployeeListBasedOnSiteCode() {
		return entityManager.createNamedQuery("Users.getEmployeeListBasedOnSiteCode").getResultList();
	}
	
}