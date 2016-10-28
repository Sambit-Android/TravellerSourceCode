package com.pgrs.service;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.MessageSource;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import com.novell.ldap.LDAPException;
import com.pgrs.beans.UserProfileBean;
import com.pgrs.entity.Company;
import com.pgrs.entity.Project;
import com.pgrs.entity.Users;


public interface UserService {
	
	/*public Users getUserObject( String type,
			Users users,HttpServletRequest req) throws LDAPException, Exception;
	*/
	/*public Users saveUserObject(String type,
			Users users,HttpServletRequest req) throws LDAPException, Exception;*/
	
	List<?> readUserObject();
	
	/*public String getImage(int urId,HttpServletResponse response) throws SQLException, IOException;*/
	
	public Blob getImageformobile(int urId) throws SQLException;
	
	void uploadImageOnId(int urId,MultipartFile blob);
	
	void changeUserStatus(int urId, String operation, HttpServletResponse response, MessageSource messageSource, Locale locale) throws Exception;

	Users saveUserObject(String type, Users users,HttpServletRequest req, SessionStatus sessionStatus) throws LDAPException, Exception;

	public List<Project> getProjects(String name);

	public List<Company> getCompany(String name);
	
	List<?> readAllLoginNames();

	int getUserIdbasedonUsername(String username);
	
	String getusername(int user_id);

	UserProfileBean getUserDetailsByLoginName(String username);

	void updateuserDetailsFromProfile(int userId,String userName,String contactNo,String emailId,String urLoginName);

	String updateChangedUserPassword(int userId,String newPwd,String oldPwd,String urLoginName,String contactNo,String emailId);

	String getThemeNameByLoginName(String userName);

	void updateTheme(String username, String themeName);

	Users updateUserObject(Users users,HttpServletRequest req,SessionStatus sessionStatus);

	List<?> readAllContactNumbers();

	List<?> readAllEmailAddress();

	void deleteUser(int urId);

	String getUserDesignationOnLoginName(String userId);

	String getSiteCodeOnUserId(String userId);

	List<Integer> getAllInActiveUsres(int status);

	List<?> getUserDetailsBySiteCodeAndRole(String sitecode, String phaserole);

	List<?> getEmployeeListBasedOnSiteCode();
	
}
