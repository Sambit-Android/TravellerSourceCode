package com.pgrs.ldap;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.novell.ldap.LDAPAttribute;
import com.novell.ldap.LDAPAttributeSet;
import com.novell.ldap.LDAPConnection;
import com.novell.ldap.LDAPEntry;
import com.novell.ldap.LDAPException;
import com.novell.ldap.LDAPModification;
import com.novell.ldap.LDAPSearchResults;
import com.novell.ldap.util.Base64;
import com.pgrs.util.CompanyMenu;
import com.pgrs.util.ItemDetails;
import com.pgrs.util.ModuleDetails;
import com.pgrs.util.ProjectMenu;
import com.pgrs.util.ThirdLevelDetails;
import com.pgrs.util.TreeViewItem;

/**
 * @author Ravi Shankar Reddy
 *
 */
@Component
public class LdapBusinessModel {	

	@Autowired
	private LdapHelper ldapHelper;
	private final int ldapVersion = LDAPConnection.LDAP_V3;
	
	private static final Log logger = LogFactory.getLog(LdapBusinessModel.class);

	/**
	 * To Retrieve User Password
	 * 
	 * @param userIdInput : User Login name
	 * @param emailIdInput : User Email-id
	 * @param mobileNumberInput : User Mobile Number
	 * 
	 * Returns Map with Key value Pairs (UserId and Password) Or (Error)
	 * @throws LDAPException 
	 */
	public Map<String, String> getPassword(String userIdInput,
			String emailIdInput, String mobileNumberInput) throws LDAPException {
		Map<String, String> credential = new HashMap<String, String>();
		String encodedPassword = "";
		String userId = "";
		String mail = "";
		String telephoneNumber = "";
		byte[] decoded = null;
		String userDN = " cn=" + userIdInput + ",ou=Users,dc="+ldapHelper.dc1+",dc="+ldapHelper.dc2;
		LDAPConnection lc=null;
		try {
			lc = ldapHelper.connectLdap();
			JSONObject obj = ldapHelper.getLdapAttributes(lc, userDN);
			userId = obj.getString("cn");
			mail = obj.getString("mail");
			telephoneNumber = obj.getString("telephoneNumber");
			encodedPassword = obj.getString("userPassword");
			encodedPassword = encodedPassword.replace("{BASE64}", "");
			decoded = Base64.decode(encodedPassword);
			
			if (userId.trim().equalsIgnoreCase(userIdInput.trim()) && telephoneNumber.trim().equals(mobileNumberInput.trim())) {//&& mail.equals(emailIdInput)
				credential.put("UserId", userId);
				credential.put("Password", new String(decoded));
				credential.put("telephoneNumber", telephoneNumber);

			} else {
				credential.put("Error",
						"UserId,Email-id or ContactNumber Not Matched");
			}
			

		} catch (JSONException e) {
			credential.put("Error", "Please Provide Correct Details");
		} catch (LDAPException e) {
			credential.put("Error", "Please Provide Correct Details");
		} catch (Exception e) {
			credential.put("Error", "Please Provide Correct Details");
		}
		finally{
			if(lc!=null)
				lc.disconnect();
		}
		return credential;

	}

	/**
	 * Reset User Password
	 * 
	 * @param userName : User Login Name
	 * @param newPassword : User Password
	 * @throws LDAPException 
	 */
	public String resetPassword(String userName, String newPassword) throws LDAPException {

		String userDn = "cn=" + userName + ",ou=Users,dc="+ldapHelper.dc1+",dc="+ldapHelper.dc2;
		LDAPConnection lc=null;
		try {

			lc= ldapHelper.connectLdap();
			JSONObject obj = ldapHelper.getLdapAttributes(lc, userDn);
			String userPassword = obj.getString("userPassword");
			String response = changePassword(userName, userPassword,newPassword);
			logger.info(response);
			return response;
		} catch (Exception e) {
				return "";
		}
		finally{
			if(lc!=null)
				lc.disconnect();
		}

	}

	

	/**
	 * To Change User Password
	 * 
	 * @param userid : User Login Name
	 * @param oldPassword : User Old Password
	 * @param newPassword : User New Password
	 * 
	 *Returns String Message (Password Modified or Error Message)
	 * @throws LDAPException 
	 */
	public String changePassword(String userid, String oldPassword,	String newPassword) throws LDAPException {
		String modifyDN = "cn=" + userid + ",ou=Users,dc="+ldapHelper.dc1+",dc="+ldapHelper.dc2;
		
		LDAPConnection lc=null;
		try {
			lc = ldapHelper.connectLdap();

			LDAPModification[] modifications = new LDAPModification[2];

			LDAPAttribute deletePassword = new LDAPAttribute("userPassword",
					oldPassword);
			modifications[0] = new LDAPModification(LDAPModification.DELETE,
					deletePassword);
			LDAPAttribute addPassword = new LDAPAttribute("userPassword",
					newPassword);
			modifications[1] = new LDAPModification(LDAPModification.ADD,
					addPassword);
			lc.modify(modifyDN, modifications);			
			return "trueYour Password is Modified";
		}

		catch (LDAPException e) {
			if (e.getResultCode() == LDAPException.NO_SUCH_OBJECT) {
				return "false"+e.getLDAPErrorMessage();
			} else if (e.getResultCode() == LDAPException.INSUFFICIENT_ACCESS_RIGHTS) {
				return "false"+e.getLDAPErrorMessage();
			} else {
				return "false"+e.getLDAPErrorMessage();
			}
		} catch (Exception e) {
			return "false"+e.getMessage();
		}
		finally{
			if(lc!=null)
				lc.disconnect();
		}
	
	}

	/**
	 * Developer Reference
	 */
	/*public static void main(String[] args) throws Exception {
		LdapBusinessModel lbm=new LdapBusinessModel();
		List<CompanyMenu> ll=lbm.getMemberAttributesForMenu();
		for(CompanyMenu cm:ll){
			System.out.println(cm.getCompanyName());
			for(ProjectMenu pm:cm.getProjects()){
				System.out.println(pm.getProjectName());
				for(ModuleDetails md:pm.getModuleDetails()){
					System.out.println(md.getDescription());
					System.out.println(md.getRole());
					System.out.println(md.getImage());
					for(ItemDetails id:md.getItems()){
						System.out.println(id.getText());
						System.out.println(id.getRole());
						System.out.println(id.getUrl());
					}
				}
				
			}
		}
		
	}*/	
	
	@Cacheable(value="menuAttributes")
	public List<CompanyMenu> getMemberAttributesForMenu() throws JSONException, LDAPException {
		String searchIn="ou=Company,dc="+ldapHelper.dc1+",dc="+ldapHelper.dc2;
		List<String> listOfMembers = new ArrayList<String>();		
		List<CompanyMenu> companyDetails = new ArrayList<CompanyMenu>();		
		
		LDAPConnection lc =null;
		try {
			
			lc = ldapHelper.connectLdap();

			LDAPSearchResults searchResults = lc.search(searchIn,LDAPConnection.SCOPE_ONE, "(objectclass=*)", null, false);

			LDAPEntry nextEntry = null;

			while (searchResults.hasMore()) {
				nextEntry = searchResults.next();
				listOfMembers.add(nextEntry.getDN());
			}		
			
			for (final String record : listOfMembers) {
				CompanyMenu company=new CompanyMenu();
				company.setCompanyName(ldapHelper.memberDescriptionforMenu(record));
				company.setProjects(getProjects(record));
				companyDetails.add(company);
			}
		}catch(Exception e){
			
		}
		finally{
			if(lc!=null)
				lc.disconnect();
		}
		return companyDetails;
	}
	
	private List<ProjectMenu> getProjects(String searchIn) throws LDAPException {
		List<String> listOfMembers = new ArrayList<String>();		
		List<ProjectMenu> projectDetails = new ArrayList<ProjectMenu>();		
		
		LDAPConnection lc =null;
		try {
			
			lc = ldapHelper.connectLdap();

			LDAPSearchResults searchResults = lc.search(searchIn,LDAPConnection.SCOPE_ONE, "(objectclass=*)", null, false);

			LDAPEntry nextEntry = null;

			while (searchResults.hasMore()) {
				nextEntry = searchResults.next();
				listOfMembers.add(nextEntry.getDN());
			}		
			
			for (final String record : listOfMembers) {
				ProjectMenu projectMenu=new ProjectMenu();
				projectMenu.setProjectName(ldapHelper.memberDescriptionforMenu(record));
				projectMenu.setModuleDetails(getNewModules(record));
				projectDetails.add(projectMenu);
			}
		}catch(Exception e){
			
		}finally{
			if(lc!=null)
				lc.disconnect();
		}
		return projectDetails;
	}
	
	public List<ModuleDetails> getNewModules(String userDN)throws LDAPException {
		List<ModuleDetails> moduleDetails = new ArrayList<ModuleDetails>();
		LDAPConnection lc=null;
		try {

			lc= ldapHelper.connectLdap();
			LDAPSearchResults searchResults = lc.search(userDN,LDAPConnection.SCOPE_BASE, "(objectclass=*)", null, false);

			while (searchResults.hasMore()) {
				LDAPEntry nextEntry = null;
				nextEntry = searchResults.next();

				LDAPAttribute atributes = nextEntry.getAttribute("uniqueMember");

				String[] arr = atributes.getStringValueArray();
				for (int i = 0; i < arr.length; i++) {
					String string = arr[i];
					String arryString[]=string.split(",");
					for (int j = 1; j < arryString.length; j=j+7) {
						String string1 = arryString[j];
						if(string1.equals("ou=Products")){
							ModuleDetails module=new ModuleDetails();
							module.setDescription(ldapHelper.memberDescriptionforMenu(string));
							module.setImage(ldapHelper.memberImage(string));
							List<String> li=ldapHelper.uniqueMembers(string);
							String role="";
							for(String str:li){
								role+=str+",";
							}
							module.setRole(role);
							module.setItems(getNewItems(arr,module.getDescription()));
							moduleDetails.add(module);
						}
					}
					
				}
				
			}
		}catch (LDAPException e) {
			e.getLDAPErrorMessage();
		} catch (JSONException e) {

		} catch (Exception e) {

		}finally{
			if(lc!=null)
				lc.disconnect();
		}
		return moduleDetails;
	}
	
	public List<ItemDetails> getNewItems(String [] userDN,String moduleName)throws LDAPException {
		
		List<ItemDetails> itemDetails = new ArrayList<ItemDetails>();
		for (int i = 0; i < userDN.length; i++) {
			String string = userDN[i];
			String arryString[]=string.split(",");
			for (int j = 1; j < arryString.length; j=j+7) {
				String string1 = arryString[j];
				if(string1.equals("cn="+moduleName)){
					ItemDetails id =new ItemDetails();
					id.setText(ldapHelper.memberDescriptionforMenu(string));
					id.setUrl(ldapHelper.memberImage(string));
					List<String> li=ldapHelper.uniqueMembers(string);
					String role="";
					for(String str:li){
						role+=str+",";
					}
					id.setRole(role);
					//id.setThirdLevel(getNewThirhLevelItems(userDN,id.getText()));
					itemDetails.add(id);
				}
				
			}
		}
		return itemDetails;
	}
	
	/*public List<ThirdLevelDetails> getNewThirhLevelItems(String [] userDN,String moduleName)throws LDAPException {
		
		List<ThirdLevelDetails> thirdLevelDetails = new ArrayList<ThirdLevelDetails>();
		for (int i = 0; i < userDN.length; i++) {
			String string = userDN[i];
			String arryString[]=string.split(",");
			for (int j = 1; j < arryString.length; j=j+7) {
				String string1 = arryString[j];
				if(string1.equals("cn="+moduleName)){
					if(ldapHelper.memberImage(string)!=""){
						ThirdLevelDetails tl=new ThirdLevelDetails();
						tl.setText(ldapHelper.memberDescriptionforMenu(string));
						List<String> li2=ldapHelper.uniqueMembers(string);
						String role2="";
						for(String str:li2){
							role2+=str+",";
						}
						tl.setRole(role2);
						tl.setUrl(ldapHelper.memberImage(string));
						thirdLevelDetails.add(tl);
					}
					
				}
				
			}
		}
		return thirdLevelDetails;
	}*/
	
	/**
	 * Internal Supporting Method for Manage Permission
	 * 
	 * @param searchIn : Directory Name Where User Modules Exist in LDAP
	 * 
	 *Returns the Roles and tasks for that role
	 */
	@Cacheable(value = "messageCache", key = "#word")
	public List<JSONObject> getMemberAttributes()throws LDAPException {
		String searchIn="ou=Products,dc="+ldapHelper.dc1+",dc="+ldapHelper.dc2;
		List<String> listOfMembers = new ArrayList<String>();
		
		List<JSONObject> map = new ArrayList<JSONObject>();	
		LDAPConnection lc=null;
		try {

			lc = ldapHelper.connectLdap();

			LDAPSearchResults searchResults = lc.search(searchIn,LDAPConnection.SCOPE_SUB, "(objectclass=*)", null, false);

			LDAPEntry nextEntry = null;

			while (searchResults.hasMore()) {
				nextEntry = searchResults.next();
				listOfMembers.add(nextEntry.getDN());
			}
			for (int i = 1; i < listOfMembers.size(); i++) {
				JSONObject jo=new JSONObject();
				jo.put("url",ldapHelper.memberDescription(listOfMembers.get(i)));
				jo.put("role",ldapHelper.uniqueMembers(listOfMembers.get(i)));
				jo.put("project",ldapHelper.getProject(listOfMembers.get(i)));
				jo.put("company",ldapHelper.getCompany(listOfMembers.get(i)));
				map.add(jo);
			}			
			lc.disconnect();			

		} catch (LDAPException e) {
			e.getLDAPErrorMessage();
		} catch (Exception e) {
			e.getMessage();
		}finally{
			if(lc!=null)
				lc.disconnect();
		}
		return map;
	}

	
	public List<String> getAllRoles() throws LDAPException, Exception {
		
		String modifyDN = "ou=Roles,dc="+ldapHelper.dc1+",dc="+ldapHelper.dc2;
		
		List<String> listOfMembers = new ArrayList<String>();
		
		LDAPConnection lc = ldapHelper.connectLdap();
		
		LDAPSearchResults searchResults = lc.search(modifyDN,LDAPConnection.SCOPE_ONE, "(objectclass=*)", null, false);
		
		LDAPEntry nextEntry = null;
		
		while (searchResults.hasMore()) {
			nextEntry = searchResults.next();
			listOfMembers.add(nextEntry.getDN());
		}
		
		List<String> roles=new ArrayList<String>();
		
		for (final String record : listOfMembers) {				
			String str=(String) ldapHelper.getCN(record);
			roles.add(str);
		}	
		
		lc.disconnect();
		
		return roles;
	}
	
	

	/**
	 * Remove User From Role
	 * 
	 * @param role : role Name
	 * @param userdn : userName
	 */
	public void removeMemberFromRolesAfterRoleDeactivation(String role,	String userdn) throws LDAPException{

		String roledn = "cn=" + role + ",ou=Roles,dc="+ldapHelper.dc1+",dc="+ldapHelper.dc2;
		// Add modifications to modRoles
		LDAPAttribute uniqueMember = new LDAPAttribute("uniqueMember", userdn);
		LDAPModification modRole = new LDAPModification(LDAPModification.DELETE, uniqueMember);
		LDAPConnection lc=null;
		// Modify the Role's attributes
		try {
			lc = ldapHelper.connectLdap();
			lc.modify(roledn, modRole);
			lc.disconnect();

		} catch (Exception e) {
		}finally{
			if(lc!=null)
				lc.disconnect();
		}
		logger.info("user Removed from roles");
	}

	
	
	/**
	 * Internal Supporting Method for getMemberAttributes
	 * 
	 * Returns the Roles
	 * @throws LDAPException 
	 */
	public List<String> getRoles() throws LDAPException {

		String searchIn = "ou=Roles,dc="+ldapHelper.dc1+",dc="+ldapHelper.dc2;
		List<String> listOfRoles = new ArrayList<String>();
		List<String> listOfMembers = new ArrayList<String>();
		LDAPConnection lc =null;
		try {

			lc = ldapHelper.connectLdap();

			LDAPSearchResults searchResults = lc.search(searchIn,
					LDAPConnection.SCOPE_SUB, "(objectclass=*)", null, false);

			LDAPEntry nextEntry = null;

			while (searchResults.hasMore()) {
				nextEntry = searchResults.next();
				listOfMembers.add(nextEntry.getDN());
			}
			JSONObject obj;
			for (int i = 1; i < listOfMembers.size(); i++) {
				obj = ldapHelper.getLdapAttributes(lc, listOfMembers.get(i));
				listOfRoles.add(obj.getString("cn"));
			}
			

		} catch (LDAPException e) {
			e.getLDAPErrorMessage();
		} catch (Exception e) {
			e.getMessage();
		}finally{
			if(lc!=null){
				lc.disconnect();
			}
		}

		return listOfRoles;
	}

	/**
	 * Adding User Data to LDAP
	 * 
	 * @param userName : User Login Name
	 * @param userPassword : User Password
	 * @param email : User email_Id
	 * @param telNumber : User Contact Number
	 * @param roles : List Of Roles or single Role
	 * @param groups : List Of Groups or single Group
	 * 
	 * Returns String Message (Success or Error)
	 */
	public String addUsers(String userName, String userPassword,String email,String telNumber,Set<String> roles){
		String containerName = "ou=Users,dc="+ldapHelper.dc1+",dc="+ldapHelper.dc2;
		
		File file = new File(".\\WebContent\\resources\\img\\personImage.jpg");
	    MultipartFile multiPartFile=new MockMultipartFile(file.getName(),new byte[(int) file.length()]);
	    byte[] imsgeBytes = null;
	 	try {
			imsgeBytes = multiPartFile.getBytes();
		} catch (IOException e) {
			e.printStackTrace();
		}

		LDAPAttributeSet attributeSet = new LDAPAttributeSet();

		attributeSet.add(new LDAPAttribute("objectclass", new String("inetOrgPerson")));

		attributeSet.add(new LDAPAttribute("cn", new String[] { userName }));

		attributeSet.add(new LDAPAttribute("sn", new String(userName)));

		attributeSet.add(new LDAPAttribute("telephonenumber", new String(telNumber)));

		attributeSet.add(new LDAPAttribute("mail", new String(email)));

		attributeSet.add(new LDAPAttribute("userpassword", new String(userPassword)));
		
		attributeSet.add(new LDAPAttribute("photo", imsgeBytes));

		String dn = "cn=" + userName + "," + containerName;

		LDAPEntry newEntry = new LDAPEntry(dn, attributeSet);
		LDAPConnection lc = null;

		try {

			// connect to the server
			lc = ldapHelper.connectLdap();
			lc.add(newEntry);

			@SuppressWarnings("rawtypes")
			Iterator roleIterator = roles.iterator();

			while (roleIterator.hasNext()) {
				memberToRoles((String) roleIterator.next(), userName);
			}
/*
			@SuppressWarnings("rawtypes")
			Iterator groupIterator = groups.iterator();

			while (groupIterator.hasNext()) {
				memberToGorups((String) groupIterator.next(), userName);
			}*/
			logger.info("successfully added");
		}

		catch (LDAPException e) {
			logger.info("Got Exception in LdapBusinessModel.addUsers");
			return e.getLDAPErrorMessage();
		} catch (Exception e) {
			logger.info("Got Exception in LdapBusinessModel.addUsers");
		} finally {
			try {
				lc.disconnect();
			} catch (LDAPException e) {
				logger.info("Got Exception in LdapBusinessModel.addUsers");
			}
		}
		return "User is Successfully Added to the Entry";
	}

	/**
	 * Deleting User Data from LDAP
	 * 
	 * @param userName : User Login Name
	 * 
	 * Returns String Message (Success or Error)
	 * @param groupNames : groupNames
	 * @param roleNames : roleNames
	 * @throws LDAPException 
	 */
	public String deleteUsers(String userName, Set<String> roleNames) throws Exception {
		logger.info("inside delete Users");
		String containerName = "ou=Users,dc="+ldapHelper.dc1+",dc="+ldapHelper.dc2;

		String dn = "cn=" + userName + "," + containerName;
		
		for (String roleName : roleNames) {
			removeMemberFromRoles(roleName, userName);
		}

		// String newEntry = new LDAPEntry(dn);
		LDAPConnection lc = null;
		try {

			// connect to the server
			lc = ldapHelper.connectLdap();
			logger.info("connection success");
			// lc.add(newEntry);
			lc.delete(dn);
			logger.info("successfully deleted");
		}

		catch (LDAPException e) {
			return e.getLDAPErrorMessage();
		} catch (Exception e) {
			logger.info("Got Exception in LdapBusinessModel.deleteUsers");
		} finally {
			try {
				// disconnect with the server
				lc.disconnect();
			} catch (LDAPException e) {
				logger.info("Got Exception in LdapBusinessModel.deleteUsers");
			}
		}
		return "User is Successfully deleted to the Entry";
	}

	/**
	 * Adding Members to Roles
	 * 
	 * @param role : Role Name
	 * @param userdn : Member for that Role
	 * 
	 * Returns String Message (Success or Error)
	 * @throws LDAPException 
	 */
	public void memberToRoles(String role, String userdn) throws LDAPException {

		String roledn = "cn=" + role + ",ou=Roles,dc="+ldapHelper.dc1+",dc="+ldapHelper.dc2;
		userdn = "cn=" + userdn + ",ou=Users,dc="+ldapHelper.dc1+",dc="+ldapHelper.dc2;

		// Add modifications to modRoles
		LDAPAttribute uniqueMember = new LDAPAttribute("uniqueMember", userdn);
		LDAPModification modRole = new LDAPModification(LDAPModification.ADD,uniqueMember);
		LDAPConnection lc =null;
		try {
			// Modify the Role's attributes
			lc= ldapHelper.connectLdap();
			lc.modify(roledn, modRole);
		} catch (Exception e) {
		}
		finally{
			if(lc!=null){
				lc.disconnect();
			}
		}
		logger.info("member added to roles");

	}

	/**
	 * Removing Members from Roles
	 * 
	 * @param role : Role Name
	 * @param userdn : Member for that Role
	 * 
	 * Returns String Message (Success or Error)
	 * @throws LDAPException 
	 */
	public void removeMemberFromRoles(String role, String username) throws LDAPException {

		String roledn = "cn=" + role + ",ou=Roles,dc="+ldapHelper.dc1+",dc="+ldapHelper.dc2;
		String userdn = "cn=" + username + ",ou=Users,dc="+ldapHelper.dc1+",dc="+ldapHelper.dc2;
		LDAPConnection lc =null;
		// Add modifications to modRoles
		
		LDAPAttribute uniqueMember = new LDAPAttribute("uniqueMember", userdn);
		LDAPModification modRole = new LDAPModification(LDAPModification.DELETE, uniqueMember);

		// Modify the Role's attributes
		try {			
			lc = ldapHelper.connectLdap();
			lc.modify(roledn, modRole);
			lc.disconnect();

		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			if(lc!=null){
				lc.disconnect();
			}
		}
		logger.info("user Removed from roles");
	}

	/**
	 * Adding Members to Groups
	 * 
	 * @param group : Group Name
	 * @param userdn : Member for that Group
	 * 
	 * Returns String Message (Success or Error)
	 * @throws LDAPException 
	 */
	public void memberToGorups(String group, String userdn) throws LDAPException {
		String groupdn = "cn=" + group + ",ou=GeneralGroups,dc="+ldapHelper.dc1+",dc="+ldapHelper.dc2;
		userdn = "cn=" + userdn + ",ou=Users,dc="+ldapHelper.dc1+",dc="+ldapHelper.dc2;
		LDAPConnection lc =null;
		try {
			 lc = ldapHelper.connectLdap();

			// Add modifications to modRoles
			LDAPAttribute uniqueMember = new LDAPAttribute("uniqueMember",
					userdn);
			LDAPModification modGroup = new LDAPModification(
					LDAPModification.ADD, uniqueMember);

			// Modify the Role's attributes
			lc.modify(groupdn, modGroup);

		} catch (Exception e) {
		}finally{
			if(lc!=null){
				lc.disconnect();
			}
		}
		logger.info("member added to groups");

	}

	/**
	 * Removing Members from Groups
	 * 
	 * @param group : Group Name
	 * @param userdn : Member for that Group
	 * 
	 * Returns String Message (Success or Error)
	 * @throws LDAPException 
	 */
	public void removeMemberFromGorups(String group, String userdn) throws LDAPException {

		String groupdn = "cn=" + group + ",ou=GeneralGroups,dc="+ldapHelper.dc1+",dc="+ldapHelper.dc2;
		userdn = "cn=" + userdn + ",ou=Users,dc="+ldapHelper.dc1+",dc="+ldapHelper.dc2;

		logger.info(userdn + "" + group);
		// Add modifications to modRoles
		LDAPAttribute uniqueMember = new LDAPAttribute("uniqueMember", userdn);
		LDAPModification modGroup = new LDAPModification(
				LDAPModification.DELETE, uniqueMember);

		// Modify the Role's attributes
		LDAPConnection lc = null;
		try {
			lc = ldapHelper.connectLdap();
			lc.modify(groupdn, modGroup);
		} catch (Exception e) {
		}finally{
			if(lc!=null){
				lc.disconnect();
			}
		}
		logger.info("member removed from groups");

	}
	

	/**
	 * Editing User Data to LDAP
	 * 
	 * @param oldName : User's Old Login Name
	 * @param userName : User's New Login Name
	 * @param emailID : User email_Id
	 * @param phoneNumber : User Contact Number
	 * @param roleNames : List Of Roles or single Role
	 * @param groupNames : List Of Groups or single Group
	 * 
	 * Returns String Message (Success or Error)
	 * @throws LDAPException 
	 */
	public void editUsers(String oldName, String loginName, String emailID,
			String phoneNumber, Set<String> roleNames) throws LDAPException {

		String userDn = "cn=" + loginName + ",ou=Users,dc="+ldapHelper.dc1+",dc="+ldapHelper.dc2;

		List<String> allRoles = getRoles();
		logger.info("list of roles " + allRoles);

		@SuppressWarnings("rawtypes")
		Iterator allRole = allRoles.iterator();
		while (allRole.hasNext()) {
			removeMemberFromRoles((String) allRole.next(), oldName);
		}
		
		// Add modifications to modRoles
		LDAPModification[] modifications = new LDAPModification[2];

		LDAPAttribute emailId = new LDAPAttribute("mail", emailID);
		modifications[0] = new LDAPModification(LDAPModification.REPLACE,emailId);

		LDAPAttribute telNumber = new LDAPAttribute("telephonenumber",phoneNumber);
		modifications[1] = new LDAPModification(LDAPModification.REPLACE,telNumber);
		LDAPConnection lc=null;
		try {

			ldapHelper.renameDn(oldName, loginName);

			lc= ldapHelper.connectLdap();
			lc.modify(userDn, modifications);
			logger.info("Users data Modified");

			// Modify the Role's attributes

			@SuppressWarnings("rawtypes")
			Iterator rolesIterator = roleNames.iterator();
			while (rolesIterator.hasNext()) {
				memberToRoles((String) rolesIterator.next(), loginName);
			}

			@SuppressWarnings("rawtypes")
			Iterator groupsIterator = roleNames.iterator();
			while (groupsIterator.hasNext()) {
				memberToGorups((String) groupsIterator.next(), loginName);
			}

			logger.info(" all members modified");
		} catch (Exception e) {
			logger.info("Exception " + e.getMessage());
		}finally{
			if(lc!=null){
				lc.disconnect();
			}
		}
	}
	
	/**
	 * Editing User Data to LDAP
	 * 
	 * @param userName : User's New Login Name
	 * @param emailID : User email_Id
	 * @param phoneNumber : User Contact Number
	 * 
	 * Returns String Message (Success or Error)
	 * @throws LDAPException 
	 */
	public void editUsersMobileAndEmail(String loginName,String emailID,String phoneNumber) throws LDAPException {

		String userDn = "cn=" + loginName + ",ou=Users,dc="+ldapHelper.dc1+",dc="+ldapHelper.dc2;

		// Add modifications to modRoles
		LDAPModification[] modifications = new LDAPModification[2];

		LDAPAttribute emailId = new LDAPAttribute("mail", emailID);
		modifications[0] = new LDAPModification(LDAPModification.REPLACE,emailId);

		LDAPAttribute telNumber = new LDAPAttribute("telephonenumber",phoneNumber);
		modifications[1] = new LDAPModification(LDAPModification.REPLACE,telNumber);
		LDAPConnection lc=null;
		try {

			lc= ldapHelper.connectLdap();
			lc.modify(userDn, modifications);
			logger.info("Users data Modified");

			logger.info(" all members modified");
		} catch (Exception e) {
			logger.info("Exception " + e.getMessage());
		}finally{
			if(lc!=null){
				lc.disconnect();
			}
		}
	}

	/**
	 * Adding Roles
	 * 
	 * @param roleName : Role Name
	 * @param description : Role Description
	 * 
	 * Returns String Message (Success or Error)
	 */
	public String addRoles(String roleName, String description) {
		logger.info("inside add groups");
		String containerName = "ou=Roles,dc="+ldapHelper.dc1+",dc="+ldapHelper.dc2;

		LDAPAttributeSet attributeSet = new LDAPAttributeSet();

		attributeSet.add(new LDAPAttribute("objectclass", new String("groupOfUniqueNames")));
		attributeSet.add(new LDAPAttribute("objectclass", new String("top")));
		attributeSet.add(new LDAPAttribute("cn", new String[] { roleName }));
		attributeSet.add(new LDAPAttribute("description", new String(description)));

		String dn = "cn=" + roleName + "," + containerName;

		LDAPEntry newEntry = new LDAPEntry(dn, attributeSet);
		LDAPConnection lc = null;
		try {

			// connect to the server
			lc = ldapHelper.connectLdap();
			logger.info("connection success");
			lc.add(newEntry);
			logger.info("successfully added");
		}

		catch (LDAPException e) {
			logger.info("exception " + e.getLDAPErrorMessage());
			return e.getLDAPErrorMessage();
		} catch (Exception e) {
			e.getMessage();
		} finally {
			try {
				lc.disconnect();
			} catch (LDAPException e) {
				e.printStackTrace();
			}
		}
		return "Role is Successfully Added to the Entry";
	}

	/**
	 * Deleting Roles
	 * 
	 * @param roleName : Role Name
	 * 
	 * Returns String Message (Success or Error)
	 */
	public String deleteRoles(String roleName) {

		String containerName = "ou=Roles,dc="+ldapHelper.dc1+",dc="+ldapHelper.dc2;
		String dn = "cn=" + roleName + "," + containerName;

		// String newEntry = new LDAPEntry(dn);
		LDAPConnection lc = null;
		try {

			// connect to the server
			lc = ldapHelper.connectLdap();
			logger.info("connection success");
			// lc.add(newEntry);
			lc.delete(dn);
			logger.info("successfully deleted");
		}

		catch (LDAPException e) {
			logger.info("Got Exception in LdapBusinessModel.deleteRoles");
			return e.getLDAPErrorMessage();
		} catch (Exception e) {
			logger.info("Got Exception in LdapBusinessModel.deleteRoles");
		} finally {
			try {
				// disconnect with the server
				lc.disconnect();
			} catch (LDAPException e) {
				logger.info("Got Exception in LdapBusinessModel.deleteRoles");
			}
		}
		return "Roles is Successfully deleted to the Entry";
	}

	/**
	 * Editing Roles
	 * 
	 * @param roleName : Role Name
	 * 
	 * Returns String Message (Success or Error)
	 * @throws LDAPException 
	 */
	public void editRoles(String roleName,String oldName) throws LDAPException {
		String groupdn = "cn=" + oldName + ",ou=Roles,dc="+ldapHelper.dc1+",dc="+ldapHelper.dc2;

		// Add modifications to modRoles
		LDAPModification[] modifications = new LDAPModification[1];

		LDAPAttribute cn = new LDAPAttribute("cn", roleName);
		modifications[0] = new LDAPModification(LDAPModification.REPLACE, cn);

		// Modify the Role's attributes
		LDAPConnection lc = null;
		try {
			lc = ldapHelper.connectLdap();
			lc.modify(groupdn, modifications);
			logger.info("Role Modified");
		} catch (Exception e) {
		}finally{
			if(lc!=null){
				lc.disconnect();
			}
		}
	}

	/**
	 * Retrieving Login Roles
	 * 
	 * @param userId : User Login Name
	 * 
	 * Returns List Of Roles
	 * @throws LDAPException 
	 */
	public List<String> getLoginRoles(String userId) throws LDAPException {

		String userDN = "cn=" + userId + ",ou=Users,dc="+ldapHelper.dc1+",dc="+ldapHelper.dc2;
		String returnAttrs[] = { "isMemberOf" };
		String member = "";
		List<String> roles = new ArrayList<String>();
		String role = "";
		LDAPConnection lc=null;
		try {
			lc= ldapHelper.connectLdap();
			LDAPEntry entry = lc.read(userDN, returnAttrs);
			LDAPAttribute atributes = entry.getAttribute("isMemberOf");

			try {

				String[] arr = atributes.getStringValueArray();
				for (int i = 0; i < arr.length; i++) {
					if ((arr[i].contains("ou=Roles"))) {
						member = arr[i];
						JSONObject obj = ldapHelper.getLdapAttributes(lc, member);
						role = obj.getString("cn");
						roles.add(role);

					}

				}

			} catch (Exception e) {
				// /e.printStackTrace();
				// logger.info("Role " + e.getMessage());
			}
		}
		catch (LDAPException e) {
			e.getMessage();
		} catch (Exception e1) {
			e1.getMessage();
		}finally{
			if(lc!=null){
				lc.disconnect();
			}
		}
		return roles;

	}
	
	/**
	 * Retrieving User Image
	 * 
	 * @param userName : User Login Name
	 * 
	 * Returns Image in Bytes
	 */
	public byte[] getImage(String userName) throws LDAPException, Exception {
		byte[] imageInBytes = null;
		String containerName = "cn=" + userName
				+ ",ou=Users,dc="+ldapHelper.dc1+",dc="+ldapHelper.dc2;
		String returnAttrs[] = { "photo" };

		LDAPConnection lc = ldapHelper.connectLdap();
		LDAPEntry entry = lc.read(containerName, returnAttrs);
		LDAPAttribute atributes = entry.getAttribute("photo");
		imageInBytes = atributes.getByteValue();
		lc.disconnect();

		return imageInBytes;
	}

	public byte[] getImageForFemale(String userName) throws LDAPException,
			Exception {
		byte[] imageInBytes = null;
		String containerName = "cn=" + userName
				+ ",ou=Users,dc="+ldapHelper.dc1+",dc="+ldapHelper.dc2;
		String returnAttrs[] = { "jpegPhoto" };

		LDAPConnection lc = ldapHelper.connectLdap();
		LDAPEntry entry = lc.read(containerName, returnAttrs);
		LDAPAttribute atributes = entry.getAttribute("jpegPhoto");
		imageInBytes = atributes.getByteValue();
		lc.disconnect();

		return imageInBytes;
	}

	/**
	 * Adding User Image
	 * 
	 * @param userName : User Login Name
	 * @param imsgeBytes : Image in bytes
	 * @throws LDAPException 
	 */
	public void setImage(String username, byte[] imsgeBytes) throws LDAPException {
		String containerName = "cn=" + username
				+ ",ou=Users,dc="+ldapHelper.dc1+",dc="+ldapHelper.dc2;
		LDAPConnection lc = null;
		try {
			lc = ldapHelper.connectLdap();
			logger.info("image bytes " + imsgeBytes);
			LDAPModification[] modifications = new LDAPModification[1];
			LDAPAttribute imageToLDAP = new LDAPAttribute("photo", imsgeBytes);
			modifications[0] = new LDAPModification(LDAPModification.REPLACE,imageToLDAP);
			lc.modify(containerName, modifications);
			logger.info("User image Modified");
			

		} catch (LDAPException e) {
			e.getLDAPErrorMessage();
		} catch (Exception e) {
			e.getMessage();
		}finally{
			if(lc!=null){
				lc.disconnect();
			}
		}
	}

	

	/**
	 * Retrieving the directory structure from the LDAP in a TreeView
	 * 
	 * Returns List of LDAP Hierarchy in a tree format
	 */
	@Cacheable(value = "getData", key = "#word1")
	public List<TreeViewItem> getData() {

		List<String> listOfMembers = new ArrayList<String>();
		List<TreeViewItem> data = new ArrayList<TreeViewItem>();
		TreeViewItem staticroot = new TreeViewItem();
		TreeViewItem root = new TreeViewItem();

		try {

			LDAPConnection lc = ldapHelper.connectLdap();

			LDAPSearchResults searchResults = lc.search(
					"ou=Products,dc="+ldapHelper.dc1+",dc="+ldapHelper.dc2, LDAPConnection.SCOPE_ONE,
					"(objectclass=*)", null, false);
			while (searchResults.hasMore()) {
				LDAPEntry nextEntry = null;
				try {
					nextEntry = searchResults.next();
				} catch (LDAPException e) {
					if (e.getResultCode() == LDAPException.LDAP_TIMEOUT
							|| e.getResultCode() == LDAPException.CONNECT_ERROR) {
						break;
					} else {
						continue;
					}
				}
				listOfMembers.add(nextEntry.getDN());
			}
			Iterator<String> it = listOfMembers.iterator();

			int i = 0;
			while (it.hasNext()) {

				String list = listOfMembers.get(i);
				String[] parts = list.split(",");

				staticroot.setFields("IREO_BFM","", false);
				root.setFields("Product = BFM","", true);

				if (parts.length == 4) {
					String parts2[] = parts[0].split("=");
					TreeViewItem level1 = root.AddSubItem();
					level1.setFields("Module = " + parts2[1],"", true);
					List<String> listOfchild = new ArrayList<String>();
					LDAPSearchResults searchChild = lc.search(parts[0]
							+ ",ou=Products,dc="+ldapHelper.dc1+",dc="+ldapHelper.dc2,
							LDAPConnection.SCOPE_ONE, "(objectclass=*)", null,
							false);

					while (searchChild.hasMore()) {
						LDAPEntry nextEntryChild = null;
						try {
							nextEntryChild = searchChild.next();
						} catch (LDAPException e) {
							if (e.getResultCode() == LDAPException.LDAP_TIMEOUT
									|| e.getResultCode() == LDAPException.CONNECT_ERROR) {
								break;
							} else {
								continue;
							}
						}
						listOfchild.add(nextEntryChild.getDN());
					}

					Iterator<String> itChilds = listOfchild.iterator();
					int j = 0;
					while (itChilds.hasNext()) {
						itChilds.next();
						String loc = listOfchild.get(j);
						String[] child1 = loc.split(",");
						if (child1.length == 5) {
							TreeViewItem level2 = level1.AddSubItem();
							String child11[] = child1[0].split("=");
							level2.setFields("Forms = " + child11[1],"", true);

							List<String> listOfchildofchild = new ArrayList<String>();
							LDAPSearchResults searchChildofchild = lc
									.search(child1[0] + "," + child1[1]
											+ ",ou=Products,dc="+ldapHelper.dc1+",dc="+ldapHelper.dc2,
											LDAPConnection.SCOPE_ONE,
											"(objectclass=*)", null, false);

							while (searchChildofchild.hasMore()) {
								LDAPEntry nextEntryChildofChild = null;
								try {
									nextEntryChildofChild = searchChildofchild
											.next();
								} catch (LDAPException e) {
									if (e.getResultCode() == LDAPException.LDAP_TIMEOUT
											|| e.getResultCode() == LDAPException.CONNECT_ERROR) {
										break;
									} else {
										continue;
									}
								}
								listOfchildofchild.add(nextEntryChildofChild
										.getDN());
							}
							Iterator<String> itChildofchild = listOfchildofchild
									.iterator();

							int k = 0;
							while (itChildofchild.hasNext()) {
								itChildofchild.next();
								String loc1 = listOfchildofchild.get(k);
								String[] child2 = loc1.split(",");
								if (child2.length == 6) {
									TreeViewItem level3 = level2.AddSubItem();
									String child12[] = child2[0].split("=");
									level3.setFields("Tasks = " + child12[1],"",
											true);

									String userDN = "cn=" + child12[1] + ",ou="
											+ child11[1] + ",ou=" + parts2[1]
											+ ",ou=Products,dc="+ldapHelper.dc1+",dc="+ldapHelper.dc2;

									List<String> listofroles = ldapHelper.uniqueMembers(userDN);
									
									if (listofroles.size() > 0) {
										String roles = listofroles.toString();
										String finalroles = roles.substring(1,
												roles.length() - 1);

										level3.AddSubItem().setFields(
												"Roles = " + finalroles,"", true);
									}
								}
								k++;
							}
						}
						j++;
					}
				}
				i++;
			}
			lc.disconnect();

		} catch (LDAPException e) {
			e.getLDAPErrorMessage();
		} catch (Exception e) {
			e.getMessage();
		}
		data.add(staticroot);
		data.add(root);
		return data;
	}

	/**
	 * Retrieving All the roles
	 * 
	 * Returns List of Roles
	 */
	@Cacheable(value = "getLdapRoles", key = "#word2")
	public List<Ldap> getLdapRoles() {
		List<Ldap> data = new ArrayList<Ldap>();

		List<String> listOfMembers = new ArrayList<String>();

		try {

			LDAPConnection lc = ldapHelper.connectLdap();
			LDAPSearchResults searchResults = lc.search("ou=Roles,dc="+ldapHelper.dc1+",dc="+ldapHelper.dc2, LDAPConnection.SCOPE_ONE,"(objectclass=*)", null, false);
			while (searchResults.hasMore()) {
				LDAPEntry nextEntry = null;
				try {
					nextEntry = searchResults.next();
				} catch (LDAPException e) {
					if (e.getResultCode() == LDAPException.LDAP_TIMEOUT	|| e.getResultCode() == LDAPException.CONNECT_ERROR) {
						break;
					} else {
						continue;
					}
				}
				listOfMembers.add(nextEntry.getDN());
			}

			Iterator<String> it = listOfMembers.iterator();
			int i = 0;
			while (it.hasNext()) {

				String list = listOfMembers.get(i);
				String[] parts = list.split(",");
				if (parts.length == 4) {
					String parts2[] = parts[0].split("=");
					data.add(new Ldap(i, parts2[1]));
				}
				i++;
			}
			lc.disconnect();

		} catch (Exception e) {

		}

		return data;

	}
	
	@SuppressWarnings("unused")
	public String loginCheck(String userName, String userPWD) {
		String userDN="cn="+userName+",ou=Users,dc="+ldapHelper.dc1+",dc="+ldapHelper.dc2+"";
		String status = "";
		LDAPAttribute attribute;

		try {
			LDAPConnection lc = ldapHelper.connectLdap();
			JSONObject obj = ldapHelper.getLdapAttributes(lc, userDN);
			
			String encodedPassword = obj.getString("userPassword");
			encodedPassword = encodedPassword.replace("{BASE64}", "");
			byte[] decoded = Base64.decode(encodedPassword);
			String str=new String(decoded);	
			if (str.equals(userPWD)) {
				status += "Successfully Logged in";
			} else {
				status += "Password is incorrect";
			}
		} catch (LDAPException e) {
			status += e.getLDAPErrorMessage();
		} catch (Exception e) {
			status += e.getMessage();
		}

		return status;
	}
	
	public List<ThirdLevelDetails> thirdlevelMenu(String searchIn, String string) throws LDAPException, Exception {
			
		String modifyDN = "cn=" + string + ",cn="+searchIn+",ou=Products,dc="+ldapHelper.dc1+",dc="+ldapHelper.dc2;

		List<String> listOfMembers = new ArrayList<String>();
		
		LDAPConnection lc = null;
		
		List<ThirdLevelDetails> obj = new ArrayList<ThirdLevelDetails>();	
		
		try{
			
			lc = ldapHelper.connectLdap();
	
			LDAPSearchResults searchResults = lc.search(modifyDN,LDAPConnection.SCOPE_ONE, "(objectclass=*)", null, false);
	
			LDAPEntry nextEntry = null;
	
			while (searchResults.hasMore()) {
				nextEntry = searchResults.next();
				listOfMembers.add(nextEntry.getDN());
			}
			
			
			for (final String record : listOfMembers) {			
				ThirdLevelDetails td=new ThirdLevelDetails();
				td.setText(ldapHelper.getCN(record).toString());
				List<String> li=ldapHelper.uniqueMembers(record);
				
				String role="";
				for(String str:li){
						role+=str+",";
				}
				td.setRole(role);
				td.setUrl(ldapHelper.memberDescription(record));
				obj.add(td);
			}	
		
		}catch(Exception e){
			
		}finally{
			if(lc!=null)
				lc.disconnect();
		}		
		
		return obj;
	}

	public List<String> getRolesBaseddonProject(String projectName, String companyName) throws LDAPException {
		List<String> roles = new ArrayList<String>();
		String userDN="cn="+projectName+",cn="+companyName+",ou=Company,dc="+ldapHelper.dc1+",dc="+ldapHelper.dc2;
		logger.info("UserDn---------? "+userDN);
		JSONObject obj;
		LDAPConnection lc =null;
		try {

			lc = ldapHelper.connectLdap();
			LDAPSearchResults searchResults = lc.search(userDN,LDAPConnection.SCOPE_BASE, "(objectclass=*)", null, false);

			while (searchResults.hasMore()) {
				LDAPEntry nextEntry = null;
				nextEntry = searchResults.next();

				LDAPAttribute atributes = nextEntry.getAttribute("uniqueMember");

				String[] arr = atributes.getStringValueArray();

				for (int i = 0; i < arr.length; i++) {
					if(arr[i].contains("ou=Roles")){
						obj = ldapHelper.getLdapAttributes(lc, arr[i]);
						roles.add(obj.getString("cn"));
					}
					
				}
			}
			
		} catch (LDAPException e) {
			e.getLDAPErrorMessage();
		} catch (JSONException e) {

		} catch (Exception e) {

		}finally{
			if(lc!=null)
			lc.disconnect();
		}

		return roles;
	}

	@SuppressWarnings("unused")
	public String loginCheckformobile(String userid, String upassword) {
		
		logger.info("inside login check >>>>>>>>>>>> userid " + userid +" upassword " +  upassword);
	    
	    String  ldapHost = ResourceBundle.getBundle("application").getString("ldapHostAjax");
		 
	    int ldapPort = Integer.parseInt(ResourceBundle.getBundle("application").getString("ldapPortAjax"));
	    
	    String  dc1 = ResourceBundle.getBundle("application").getString("ldap.Ajax.domain1");
	    String  dc2 = ResourceBundle.getBundle("application").getString("ldap.Ajax.domain2");
	    
	   

	    String status = "";   
	    Map<String, String> credential = new HashMap<String, String>();

	    try {
	    
	     LDAPConnection lc = new LDAPConnection();
	     logger.info("connecting...........");
	     lc.connect(ldapHost, ldapPort);
	    
	     String userDN = "cn=" + userid + ",ou=Users,dc="+dc1+",dc="+dc2; 
	     
	   
	     JSONObject obj = getLdapAttributes(lc, userDN);
	     String nam=obj.getString("cn");
	   
	     
	    lc.bind(ldapVersion, userDN, upassword.getBytes("UTF8"));

	    
	     status = loginCheck(lc, userDN, "{BASE64}" + Base64.encode(upassword.getBytes())); 
	  
	     
	     
	    } catch (LDAPException e) {
	     logger.info(e.getLDAPErrorMessage());
	     credential.put("status", e.getMessage());
	    } catch (Exception e) {
	     logger.info(e.getMessage());
	     credential.put("status", e.getMessage());
	    }
	    
	    
	    return status;
	   }
	 public String loginCheck(LDAPConnection lc, String userDN, String userPWD) {
		 logger.info("loginCheck>>>>>>>>>>>>>>>>>>");
		  String status = "";
		  LDAPAttribute attribute;

		  try {
		   // check user's password
		   attribute = new LDAPAttribute("userPassword", userPWD);
		  
		   if (lc.compare(userDN, attribute)) {
		    status += "success";
		    
		   } else {
		    status += "Password is incorrect";
		   }
		  } 
		  catch (LDAPException e) {
			  logger.info("first catch");      
		   return "User Not Registered";
		  }
		  catch (Exception e) {
			  logger.info("second catch");   
		   status += e.getMessage();
		  }
		  logger.info("status : "+ status);
		     
		  return status;
		 }
		private JSONObject getLdapAttributes(LDAPConnection lc, String userDN)
				throws JSONException, LDAPException, Exception {

			JSONObject obj = new JSONObject();
			LDAPEntry nextEntry = null;

			LDAPSearchResults searchResults = lc.search(userDN,	LDAPConnection.SCOPE_BASE, "(objectclass=*)", null, false);
			while (searchResults.hasMore()) {
				nextEntry = searchResults.next();
				LDAPAttributeSet attributeSet = nextEntry.getAttributeSet();
				@SuppressWarnings("rawtypes")
				Iterator allAttributes = attributeSet.iterator();
				while (allAttributes.hasNext()) {
					LDAPAttribute attribute = (LDAPAttribute) allAttributes.next();
					String attributeName = attribute.getName();
					@SuppressWarnings("rawtypes")
					Enumeration allValues = attribute.getStringValues();
					if (allValues != null) {
						String value;
						while (allValues.hasMoreElements()) {
							value = (String) allValues.nextElement();
							if (Base64.isLDIFSafe(value)) {
							} else {
								value = Base64.encode(value.getBytes());
							}
							obj.put(attributeName, value);
						}
					}
				}
			}
			return obj;
		}
	
}
