package com.pgrs.dao;
// default package

import java.sql.Blob;
import java.util.List;
import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

import com.pgrs.entity.Company;
import com.pgrs.entity.Project;
import com.pgrs.entity.Users;

/**
 * Interface for UsersDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IUsersDAO extends GenericDAO<Users>{
	
	List<?> executeReadObjectQuery();
	
	Blob executeGetImageQuery(int urId);
	
	void executeUploadImageOnId(int urId,MultipartFile files);
	
	void executeStatusChangeQuery(int status,int urId);
	
	Users getUserBasedOnId(int urId);

	List<Project> getProjects(String name);

	List<Company> getCompany(String name);

	Set<String> getUserRoles(int urId);
	
	List<String> getLoginNames();

	List<Users> getActiveUsers();

	List<?> readAllLoginNames();
	int readuserid(String username);

	List<Users> readuserdetailsforprofile(int user_id);

	String readuser(int user_id);

	Object[] getUserDetailsByLoginName(String loginName);

	void updateuserDetailsFromProfile(int userId,String userName,String contactNo,String emailId);

	String getThemeNameByLoginName(String userName);

	void updateTheme(String urLoginName, String themeName);

	List<String> userNameValidation(String urLoginName,String urEmail, String mobileNo);

	List<?> readAllContactNumbers();

	List<?> readAllEmailAddress();

	String getUserDesignationOnLoginName(String urLoginName);

	String getSiteCodeOnLoginName(String urLoginName);

	List<Integer> getAllInActiveUsres(int status);

	List<?> getUserDetailsBySiteCodeAndRole(String sitecode, String pahseCompletedCode);

	List<?> getEmployeeListBasedOnSiteCode();
	
}