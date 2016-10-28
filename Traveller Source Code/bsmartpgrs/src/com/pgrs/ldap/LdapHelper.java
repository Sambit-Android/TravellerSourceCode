package com.pgrs.ldap;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.novell.ldap.LDAPAttribute;
import com.novell.ldap.LDAPAttributeSet;
import com.novell.ldap.LDAPConnection;
import com.novell.ldap.LDAPEntry;
import com.novell.ldap.LDAPException;
import com.novell.ldap.LDAPModification;
import com.novell.ldap.LDAPSearchResults;
import com.novell.ldap.util.Base64;
@Component
public class LdapHelper {
	@Value("${Unique.ldap.ldapHost}")
	private String ldapHost;

	@Value("${Unique.ldap.ldapPort}")
	private int ldapPort;

	@Value("${Unique.ldap.loginDN}")
	private String loginDN;

	@Value("${Unique.ldap.password}")
	private String password;
	
	@Value("${Unique.ldap.domain1}")
	public String dc1;
	 
	@Value("${Unique.ldap.domain2}")
	public String dc2;

	 private final int ldapVersion = LDAPConnection.LDAP_V3;
	 
	 private static final Log logger = LogFactory.getLog(LdapHelper.class);
	 
	 /**
		 * Load Ldap connection details from properties file, Establish Ldap connection
		 * 
		 * @param
		 * @throws LDAPException
		 */
		LDAPConnection connectLdap() throws LDAPException, Exception {
			LDAPConnection lc = new LDAPConnection();
			lc.connect(ldapHost, ldapPort);
			lc.bind(ldapVersion, loginDN, password.getBytes("UTF8"));
			return lc;
		}
		
		public String getUserLoginName(String userID) {
			String userDN = " cn=" + userID + ",ou=Users,dc="+dc1+",dc="+dc2;
			String userName = "";
			try {
				LDAPConnection lc = connectLdap();
				JSONObject obj = getLdapAttributes(lc, userDN);
				userName = obj.getString("cn");
				lc.disconnect();
			} catch (JSONException e) {
				logger.info(e.getMessage());
			} catch (LDAPException e) {
				logger.info(e.getLDAPErrorMessage());
			} catch (Exception e) {
				logger.info(e.getMessage());
			}

			return userName;
		}
		
		/**
		 * Internal Supporting Method for
		 * getRoles,getLoginRoles,uniqueMembers,memberDescription,getGroups,getPassword
		 * 
		 * @param lc : Ldap Connection Object
		 * @param userDN : User Directory Name
		 * 
		 * Returns LDAP Attributes for Specified DN
		 */
		JSONObject getLdapAttributes(LDAPConnection lc, String userDN)
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
		
		/**
		 * Internal Supporting Method for getMemberAttributes
		 * 
		 * @param string : Modules Name
		 * 
		 * Returns the tasks
		 * @throws LDAPException 
		 */
		String memberDescriptionforMenu(String string) throws LDAPException {
			String description = "";
			LDAPConnection lc =null;
			try {
				 lc= connectLdap();
				JSONObject obj = getLdapAttributes(lc, string);
				description = obj.getString("cn");
				
			} catch (LDAPException e) {
				
			} catch (Exception e) {
				
			}finally{
				if(lc!=null)
				lc.disconnect();
			}
			
			return description;
			
		}
		
		public String getCN(String record) throws LDAPException, Exception {
			LDAPConnection lc = connectLdap();
			JSONObject obj = getLdapAttributes(lc, record);
			String text = obj.getString("cn");					
			lc.disconnect();
			return text;
		}
		
		/**
		 * Internal Supporting Method for getMemberAttributes
		 * 
		 * @param string : Modules Name
		 * 
		 * Returns the tasks
		 * @throws LDAPException 
		 */
		String memberDescription(String string) throws LDAPException {
			String description = "";
			LDAPConnection lc = null;
			try {
				lc = connectLdap();
				JSONObject obj = getLdapAttributes(lc, string);
				description = obj.getString("businessCategory");
				lc.disconnect();
			} catch (LDAPException e) {
				
			} catch (Exception e) {
				
			}finally{
				if(lc!=null)
				lc.disconnect();
			}

			return description;

		}
		
		/**
		 * Returns List of Users for Particular Role
		 * 
		 * @param String userDN : Directory name
		 * Returns List of Users
		 * @throws LDAPException 
		 */
		public List<String> uniqueMembers(String userDN) throws LDAPException {
			List<String> al = new ArrayList<String>();
			JSONObject obj;
			LDAPConnection lc =null;
			try {

				lc = connectLdap();
				LDAPSearchResults searchResults = lc.search(userDN,
						LDAPConnection.SCOPE_BASE, "(objectclass=*)", null, false);

				while (searchResults.hasMore()) {
					LDAPEntry nextEntry = null;
					nextEntry = searchResults.next();

					LDAPAttribute atributes = nextEntry.getAttribute("uniqueMember");

					String[] arr = atributes.getStringValueArray();

					for (int i = 0; i < arr.length; i++) {
						obj = getLdapAttributes(lc, arr[i]);
						al.add(obj.getString("cn"));
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

			return al;

		}
		
		/**
		 * Internal Supporting Method for editUsers
		 * 
		 * @param oldName : Old DirectoryName
		 * @param newName : New Directory Name
		 * 
		 * Returns Nothing
		 * @throws LDAPException 
		 */
		public void renameDn(String oldName, String newName) throws LDAPException {
			String dn = "cn=" + oldName + ",ou=Users,dc="+dc1+",dc="+dc2;

			LDAPConnection lc=null;
			try {
				lc = connectLdap();
				lc.rename(dn, "cn=" + newName, true);
			} catch (Exception e) {
			}
			finally{
				if(lc!=null)
					lc.disconnect();
			}
			logger.info("Entry " + dn + " has been renamed.");

		}
		
		String memberImage(String string) throws LDAPException{
			String description = "";
			LDAPConnection lc=null;
			try {
				 lc= connectLdap();
				JSONObject obj = getLdapAttributes(lc, string);
				description = obj.getString("description");
				lc.disconnect();
			} catch (LDAPException e) {
				
			} catch (Exception e) {
				
			}finally{
				if(lc!=null)
					lc.disconnect();
			}
			
			return description;
			
		}

		public String getProject(String string) throws LDAPException {
			String description = "";
			LDAPConnection lc=null;
			try {
				 lc= connectLdap();
				JSONObject obj = getLdapAttributes(lc, string);
				description = obj.getString("o");
				lc.disconnect();
			} catch (LDAPException e) {
				
			} catch (Exception e) {
				
			}finally{
				if(lc!=null)
					lc.disconnect();
			}
			
			return description;
		}

		public String getCompany(String string) throws LDAPException {
			String description = "";
			LDAPConnection lc=null;
			try {
				 lc= connectLdap();
				JSONObject obj = getLdapAttributes(lc, string);
				description = obj.getString("ou");
				lc.disconnect();
			} catch (LDAPException e) {
				
			} catch (Exception e) {
				
			}finally{
				if(lc!=null)
					lc.disconnect();
			}
			
			return description;
		}
		
		@SuppressWarnings("unused")
		public static void main(String[] args) throws Exception {
			LdapHelper lbm=new LdapHelper();
		}	
		
		public void addOToTask(String targetUrl, String uniqueMem) throws LDAPException, Exception {

			  LDAPAttribute ou = null;
			  String companyArr[] = uniqueMem.split(",");
			  if(companyArr.length>4){
			   if(companyArr[1].contains("cn")){
			    String comp[] = companyArr[1].split("=");
			    ou = new LDAPAttribute("ou", comp[1]);
			   }
			  }
			  
			  String memtoAdd = getCN(uniqueMem);
			  
			  LDAPAttribute o = new LDAPAttribute("o", memtoAdd);
			  LDAPModification modLdapO = new LDAPModification(LDAPModification.ADD,o);
			  LDAPModification modLdapOu = new LDAPModification(LDAPModification.ADD,ou);
			  try {

			   LDAPConnection lc = connectLdap();
			   lc.modify(targetUrl, modLdapO);
			   lc.modify(targetUrl, modLdapOu);
			   lc.disconnect();
			  } catch (Exception e) {
				  e.printStackTrace();
			  }

			 }
		
		
}
