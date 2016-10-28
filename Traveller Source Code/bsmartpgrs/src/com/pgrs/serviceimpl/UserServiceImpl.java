package com.pgrs.serviceimpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import com.novell.ldap.LDAPException;
import com.pgrs.beans.UserProfileBean;
import com.pgrs.core.dao.MasterGenericDAO;
import com.pgrs.dao.IUsersDAO;
import com.pgrs.entity.Company;
import com.pgrs.entity.Project;
import com.pgrs.entity.Users;
import com.pgrs.ldap.LdapBusinessModel;
import com.pgrs.service.UserService;
import com.pgrs.util.EmailCredentialsDetailsBean;
import com.pgrs.util.SmsCredentialsDetailsBean;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private IUsersDAO iUsersDAO;

	@Autowired
	private LdapBusinessModel ldapBusinessModel;

	@Autowired
	private MasterGenericDAO genericDAO;

	@PersistenceContext
	private EntityManager em;
	
	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<?> readUserObject(){

		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		List<?> objectList = iUsersDAO.executeReadObjectQuery();
		Map<String, Object> userMap = null;
		for (Iterator<?> iterator = objectList.iterator(); iterator.hasNext();){
			
				final Object[] values = (Object[]) iterator.next();
				userMap = new HashMap<String, Object>();
				
				userMap.put("urId", (Integer)values[0]);
				userMap.put("urName", (String)values[1]);
				userMap.put("urLoginName", (String)values[2]);
				userMap.put("urContactNo", (String)values[3]);
				userMap.put("urEmailId", (String)values[4]);
				userMap.put("urPermanent", (String)values[5]);
				userMap.put("deptName", (String)values[8]);
				userMap.put("deptId", (Integer)values[7]);
				userMap.put("dnName", (String)values[10]);
				userMap.put("dnId", (Integer)values[9]);
				userMap.put("userStatus", (Integer)values[6]);
				userMap.put("companyName", (String)values[11]);
				userMap.put("projectName", (String)values[12]);
				userMap.put("officeName", (String)values[13]);
				userMap.put("OfficeId", (Integer)values[14]);
				
				result.add(userMap);
			}
		return result;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public Users saveUserObject(String type, Users users,HttpServletRequest req ,SessionStatus sessionStatus) throws LDAPException, Exception {

		int officeId = 3;
		int projectId = 1;
		int companyId = 1;
		
		users.setProjectId(projectId);
		users.setOfficeId(officeId);
		users.setCompanyId(companyId);
		users.setUrLoginName(users.getUrLoginName().toLowerCase());
		
		genericDAO.save(users);

		return users;	
	}
	
	@Transactional(propagation = Propagation.SUPPORTS)
	public Users updateUserObject(Users users, HttpServletRequest req,SessionStatus sessionStatus) {
		
		int projectId = 1;
		int companyId = 1;
		
		users.setOfficeId(3);
		users.setProjectId(projectId);
		users.setCompanyId(companyId);
		
		iUsersDAO.update(users);
		return users;
	}

	/*@Override
	public String getImage(int urId,HttpServletResponse response) throws SQLException, IOException {

		Blob blobImage = iUsersDAO.executeGetImageQuery(urId);
		int blobLength = 0;
		byte[] blobAsBytes = null;
		if (blobImage != null) {
			blobLength = (int) blobImage.length();
			blobAsBytes = blobImage.getBytes(1, blobLength);
		}
		OutputStream ot = response.getOutputStream();
		try {
			ot.write(blobAsBytes);
			ot.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}*/

	@Override
	public void uploadImageOnId(int urId, MultipartFile files) {
		iUsersDAO.executeUploadImageOnId(urId,files);
	}
	
	@Override
	public void changeUserStatus(int urId,String operation,HttpServletResponse response,MessageSource messageSource,Locale locale) {

		try {
			PrintWriter out = response.getWriter();
			Users user = iUsersDAO.getUserBasedOnId(urId);
			if(operation.equalsIgnoreCase("activate")){
				
				iUsersDAO.executeStatusChangeQuery(1,urId);
				userCredentialsLdapEmailCode(user);
				
				out.write("User Activated.Your Account Details are send to your mail and to your mobile number.Please use those details for login. ");
			} else{
				iUsersDAO.executeStatusChangeQuery(0,urId);
				Set<String> roleNames = iUsersDAO.getUserRoles(user.getDnId());
				/*Set<String> roleNames = new HashSet<String>();
				for (String roleId : roleIds) {
					Role role = roleDAO.find(Integer.parseInt(roleId));
					roleNames.add(role.getRlName());
				}*/				
				try {
					ldapBusinessModel.deleteUsers(user.getUrLoginName(), roleNames);
				} catch (Exception e) {
				}
				out.write("User Deactivated");
			}
		} catch (IOException e){
		}
	}
	
	@Async
	private void userCredentialsLdapEmailCode(Users user){
		
		//String pass = RandomStringUtils.randomAlphanumeric(7);
		String pass = "bcits";
		
		SmsCredentialsDetailsBean smsCredentialsDetailsBean = new SmsCredentialsDetailsBean();
		EmailCredentialsDetailsBean emailCredentialsDetailsBean = null;
		try {
			emailCredentialsDetailsBean = new EmailCredentialsDetailsBean();
		} catch (Exception e) {
			e.printStackTrace();
		}				
		emailCredentialsDetailsBean.setToAddressEmail(user.getUrEmailId());
		//new Thread(new UserCredentialSendMailThread(emailCredentialsDetailsBean,user.getUrLoginName(),pass,user.getUrName())).start();
		
		String userMobileNo = user.getUrContactNo().substring(user.getUrContactNo().length()-10, user.getUrContactNo().length());
		smsCredentialsDetailsBean.setNumber(""+userMobileNo);
		smsCredentialsDetailsBean.setUserName(user.getUrName());
		//new Thread(new UserCredentialThroughSMS(smsCredentialsDetailsBean,"Activated",user.getUrLoginName(),pass)).start();
		
		/*Set<String> roles=new HashSet<String>();*/
		Set<String> userRoles = iUsersDAO.getUserRoles(user.getDnId());
		/*for (Iterator iterator = userRoles.iterator(); iterator.hasNext();) {
			String string = (String) iterator.next();
			String roleArray[]=string.split(",");

			for (int i = 0; i < roleArray.length; i++) {
				String roleId = roleArray[i];
				roles.add(genericDAO.getById(Role.class, Integer.parseInt(roleId)).getRlName());
			}
		}*/

		ldapBusinessModel.addUsers(user.getUrLoginName(), pass, user.getUrEmailId(), userMobileNo, userRoles);
	}
	
	@Override
	public List<Project> getProjects(String name) {
		return iUsersDAO.getProjects(name);
	}
	
	@Override
	public List<Company> getCompany(String name) {
		return iUsersDAO.getCompany(name);
	}
	
	@Override
	public List<?> readAllLoginNames() {
		
		return iUsersDAO.readAllLoginNames();
	}
	
	@Override
	public int getUserIdbasedonUsername(String username) {
		return iUsersDAO.readuserid(username);
	}
		
	@Override
	public String getusername(int user_id) {
		return iUsersDAO.readuser(user_id);
	}
	
	@SuppressWarnings("unused")
	@Override
	public Blob getImageformobile(int urId) throws SQLException {
		Blob blobImage = iUsersDAO.executeGetImageQuery(urId);
		int blobLength = 0;
		byte[] blobAsBytes = null;
		if (blobImage != null) {
			blobLength = (int) blobImage.length();
			blobAsBytes = blobImage.getBytes(1, blobLength);
		}
		
		return blobImage;
	}

	@Override
	public UserProfileBean getUserDetailsByLoginName(String username) {
		
		UserProfileBean profileBean = new UserProfileBean();
		
		Object[] usersData = iUsersDAO.getUserDetailsByLoginName(username.trim());
		
		profileBean.setUserId((Integer)usersData[0]);
		profileBean.setUserName((String)usersData[1]);
		profileBean.setUrLoginName((String)usersData[2]);
		profileBean.setContactNo((String)usersData[3]);
		profileBean.setEmailId((String)usersData[4]);
		profileBean.setAddress((String)usersData[5]);
		profileBean.setDeptName((String)usersData[6]);
		profileBean.setDesgName((String)usersData[7]);
		profileBean.setOfficeName((String)usersData[8]);
		
		return profileBean;
	}

	@Override
	public void updateuserDetailsFromProfile(int userId,String userName,String contactNo,String emailId,String urLoginName) {
		iUsersDAO.updateuserDetailsFromProfile(userId,userName,contactNo,emailId);
		userDetailsUpdateInLdapCode(urLoginName,emailId,contactNo);
	}
	
	@Async
	private void userDetailsUpdateInLdapCode(String urLoginName,String emailId,String contactNo){
		try {
			ldapBusinessModel.editUsersMobileAndEmail(urLoginName,emailId,contactNo);
		} catch (LDAPException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String updateChangedUserPassword(int userId,String newPwd,String oldPwd,String urLoginName,String contactNo,String emailId) {
		
		String response = "";
		
		try {
			
			System.out.println("Login Name----"+urLoginName);
			System.out.println("emailId----"+emailId);
			System.out.println("contactNo----"+contactNo);
			
			Map<String, String> data = ldapBusinessModel.getPassword(urLoginName,emailId,contactNo);
			String password = data.get("Password");
			
			System.out.println("Old Password ------:"+oldPwd);
			System.out.println("New Password ------:"+password);
			
			if(oldPwd.equalsIgnoreCase(password)){
				response = ldapBusinessModel.resetPassword(urLoginName,newPwd);
			}else{
				response = "falseEntered old password wrongly";
			}
		} catch (LDAPException e) {
			e.printStackTrace();
			response = "falseSomething went wrong in server.Please try again later";
		}
		
		return response;
	}
	
	@Async
	private void resetPwdInLdapCode(String urLoginName,String newPwd){
		try {
			ldapBusinessModel.resetPassword(urLoginName,newPwd);
		} catch (LDAPException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getThemeNameByLoginName(String userName) {
		return iUsersDAO.getThemeNameByLoginName(userName);
	}

	@Override
	public void updateTheme(String urLoginName, String themeName) {
		iUsersDAO.updateTheme(urLoginName,themeName);
	}

	@Override
	public List<?> readAllContactNumbers() {
		return iUsersDAO.readAllContactNumbers();
	}

	@Override
	public List<?> readAllEmailAddress() {
		return iUsersDAO.readAllEmailAddress();
	}

	@Override
	public void deleteUser(int urId) {
		iUsersDAO.delete(urId);
		
	}

	@Override
	public String getUserDesignationOnLoginName(String urLoginName) {
		return iUsersDAO.getUserDesignationOnLoginName( urLoginName);
	}

	@Override
	public String getSiteCodeOnUserId(String urLoginName) {
		return iUsersDAO.getSiteCodeOnLoginName( urLoginName);
	}

	@Override
	public List<Integer> getAllInActiveUsres(int status) {
		return iUsersDAO.getAllInActiveUsres(status);
	}

	@Override
	public List<?> getUserDetailsBySiteCodeAndRole(String sitecode,String pahseCompletedCode) {
		return iUsersDAO.getUserDetailsBySiteCodeAndRole( sitecode,pahseCompletedCode);
	}

	@SuppressWarnings("serial")
	@Override
	public List<?> getEmployeeListBasedOnSiteCode() {
		List<?> employeeListList= iUsersDAO.getEmployeeListBasedOnSiteCode();
		List<Map<String, Object>> employees = new ArrayList<Map<String, Object>>();
		for (final Iterator<?> i = employeeListList.iterator(); i.hasNext();) {
			employees.add(new HashMap<String, Object>() {
				{
					final Object[] values = (Object[])i.next();
					put("urName", (String)values[0]);
					put("urLoginName", (String)values[1]);
					put("urContactNo", (String)values[2]);
					put("urEmailId", (String)values[3]);
					if((Integer)values[4]==0){
					put("status", "Inactive");
					}
					if((Integer)values[4]==1){
					put("status", "Active");
					}
					put("locationText", (String)values[5]);
					put("dnName", (String)values[6]);
					
				}
			});
		}
		return employees;
	}
}
