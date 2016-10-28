package com.pgrs.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.novell.ldap.LDAPAttribute;
import com.novell.ldap.LDAPAttributeSet;
import com.novell.ldap.LDAPConnection;
import com.novell.ldap.LDAPEntry;
import com.novell.ldap.LDAPException;
import com.novell.ldap.LDAPModification;
import com.novell.ldap.LDAPSearchResults;
import com.novell.ldap.util.Base64;
import com.pgrs.ldap.Ldap;
import com.pgrs.ldap.LdapBusinessModel;
import com.pgrs.util.MyBootstrapCacheLoaderFactory;
import com.pgrs.util.TreeViewItem;

/**
 * @author Ravi Shankar Reddy
 *
 */

@Controller
public class ProductAccessController {
	private static final Log logger = LogFactory.getLog(ProductAccessController.class);
	static String str;

	@Value("${Unique.ldap.ldapHost}")
	private String ldapHost;
	
	@Value("${Unique.ldap.ldapPort}")
	private int ldapPort;
	
	@Value("${Unique.ldap.loginDN}")
	private String loginDN;
	
	@Value("${Unique.ldap.password}")
	private String password;
	
	@Value("${Unique.ldap.domain1}")
	private String dc1;
	
	@Value("${Unique.ldap.domain2}")
	private String dc2;

	
	static int ldapVersion = LDAPConnection.LDAP_V3;

	//@Autowired
	//private BreadCrumbTreeService breadCrumbService;

	@Autowired
	private MyBootstrapCacheLoaderFactory myBootstrapCacheLoaderFactory;
	
	@Autowired
	private LdapBusinessModel ldapBusinessModel;
	

	 /** 
    * To Retrieve Ldap Connection 
    * static variable ldapHost 
    * static variable ldapPort 
    * static variable loginDN 
    * static variable password 
    */	
	private LDAPConnection connectLdap() throws LDAPException, Exception {
		LDAPConnection lc = new LDAPConnection();
		lc.connect(ldapHost, ldapPort);
		lc.bind(ldapVersion, loginDN, password.getBytes("UTF8"));
		return lc;
	}


	@RequestMapping(value = "/productaccess", method = RequestMethod.GET)
	public String index(Model model, HttpServletRequest request) {

		model.addAttribute("ViewName", "Product Management");
		model.addAttribute("modulename","Product Management");		
		model.addAttribute("items",getRoles(request));
		model.addAttribute("projects",getProjects());
		return "usermanagement/productaccess";
	}

	@RequestMapping(value = "/ldaptree/flush", method = RequestMethod.POST)
	public @ResponseBody String flushChages(HttpServletResponse response) throws IOException, JSONException, LDAPException {
		PrintWriter out;
		myBootstrapCacheLoaderFactory.setMemberWithDesciption(ldapBusinessModel.getMemberAttributes());
		myBootstrapCacheLoaderFactory.setMemberWithDesciptionMenu(ldapBusinessModel.getMemberAttributesForMenu());
	    out = response.getWriter();
		out.write("Changes Saved Successfully");
		
		
		return null;
	}
	
	/*private Object getCN(String record) throws LDAPException, Exception {
		  LDAPConnection lc = connectLdap();
		  JSONObject obj = getLdapAttributes(lc, record);
		  String text = obj.getString("cn");     
		  lc.disconnect();
		  return text;
	}*/
	

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/ldaptree/update", method = RequestMethod.POST)
	public String updateNode(@RequestParam("values") String directoryStructure,
			@RequestParam("mselect") String mselect,
			HttpServletResponse response) {

		StringBuffer sb = new StringBuffer();		
		String remBrace = mselect.substring(1, mselect.length() - 1);
		List<Ldap> tempList = ldapBusinessModel.getLdapRoles();
		String[] temp = remBrace.split(",");
		/* Matching roles id to roles name */
		for (int i = 0; i < temp.length; i++) {
			for (Iterator iterator = tempList.iterator(); iterator.hasNext();) {
				Ldap ldap = (Ldap) iterator.next();
				String checkText = ldap.getText();
				String arrayText = temp[i];
				int id = ldap.getId();
				String ldapid = Integer.toString(id);

				if (arrayText.trim().equalsIgnoreCase(ldapid.trim())) {
					sb.append(checkText + ",");
				}
			}
		}
		// var rolesName: Text of Roles, format: User,Admin,Approver....
		String rolesName = sb.toString();
		String splitval[] = directoryStructure.split(",");
		String userDN = "";
		
		for(int i = splitval.length-1 ;i> 0;i--){	
			String mod[] = splitval[i].split("=");
			userDN+="cn="+mod[1]+",";
		}
		userDN+="ou=Products,dc="+dc1+",dc="+dc2;		
		PrintWriter out;
		try {
			List<String> um = uniqueMembers(userDN);
			for(int i=0;i<um.size();i++)
			{
				removeMemberFromTask(userDN,um.get(i));				
			}
			String uniquemem[] = rolesName.split(",");

			for(int i=0;i<uniquemem.length;i++)
			{
				addMemberToTask(userDN,uniquemem[i]);				
			}
			//addMemberToTask(userDN,"Admin");
			try {
				out = response.getWriter();
				out.write(rolesName);
				// out.write(responsechar);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/ldaptree/taskroles", method = RequestMethod.POST)
	public @ResponseBody
	List<Ldap> taskRoles(@RequestParam("values") String directoryStructure,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {

		List<Ldap> data = new ArrayList<Ldap>();
		String[] splitval = directoryStructure.split(",");		
		String userDN = "";	
		for(int i = splitval.length-1 ;i> 0;i--){	
			String mod[] = splitval[i].split("=");

			userDN+="cn="+mod[1]+",";
		}
		userDN+="ou=Products,dc="+dc1+",dc="+dc2;

		List<String> list = uniqueMembers(userDN);
			String strlist = list.toString();
			String listval = strlist.substring(1, strlist.length() - 1);

			List<Ldap> tempList = ldapBusinessModel.getLdapRoles();
			
			Ldap obj = null;
			String[] temp = listval.split(",");

			for (int i = 0; i < temp.length; i++) {
				obj = new Ldap();
				for (Iterator iterator = tempList.iterator(); iterator
						.hasNext();) {
					Ldap ldap = (Ldap) iterator.next();
					String checkText = ldap.getText();
					String arrayText = temp[i];
					int id = ldap.getId();

					if (arrayText.trim().equalsIgnoreCase(checkText.trim())) {
						obj.setId(id);
						obj.setText(arrayText);
					}
				}
				data.add(obj);
			}
		
		return data;
	}

	
	/** 
     * Retrieving All the roles
     *  
     * Returns List of Roles
     */
	public List<Ldap> getRoles(HttpServletRequest request) {
		List<Ldap> data = new ArrayList<Ldap>();

		List<String> listOfMembers = new ArrayList<String>();
		try {

			LDAPConnection lc = connectLdap();

			LDAPSearchResults searchResults = lc.search(
					"ou=Roles,dc="+dc1+",dc="+dc2, LDAPConnection.SCOPE_ONE,
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

			int i = 0;	
			for (String list : listOfMembers) {
				String[] parts = list.split(",");
				if (parts.length == 4) {
					String parts2[] = parts[0].split("=");

					data.add(new Ldap(i, parts2[1]));
				}
				i++;
			}
			lc.disconnect();

		} catch (Exception e) {
			e.printStackTrace();
		}
		List<Ldap> resData = new ArrayList<Ldap>();
		//HttpSession session = request.getSession(true);
		//List<String> roleList = uniqueMembers("cn="+session.getAttribute("projectName")+",cn="+session.getAttribute("companyName")+",ou=Company,dc="+dc1+",dc="+dc2);
		for (Ldap data1: data) {
			/*for (String projRoles : roleList) {
				if(projRoles.equalsIgnoreCase(data1.getText()) && !projRoles.equalsIgnoreCase("Admin"))
			*/		resData.add(new Ldap(data1.getId(),data1.getText()));
			/*}*/
		}
		
		return resData;

	}
	
	public List<Ldap> getProjects() {
		List<Ldap> data = new ArrayList<Ldap>();

		List<String> listOfMembers = new ArrayList<String>();

		try {

			LDAPConnection lc = connectLdap();

			LDAPSearchResults searchResults = lc.search(
					"ou=Projects,dc="+dc1+",dc="+dc2, LDAPConnection.SCOPE_ONE,
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

	/** 
     * 	Returns List of Users for Particular Role
     *	@param String userDN   :Directory name
     *  
     * 	Returns List of Users 
     */
	public List<String> uniqueMembers(String userDN) {

		List<String> al = new ArrayList<String>();
		JSONObject obj;
		try {

			
			LDAPConnection lc = connectLdap();
			LDAPSearchResults searchResults = lc.search(userDN,
					LDAPConnection.SCOPE_BASE, "(objectclass=*)", null, false);

			while (searchResults.hasMore()) {
				LDAPEntry nextEntry = null;
				nextEntry = searchResults.next();

				LDAPAttribute atributes = nextEntry
						.getAttribute("uniqueMember");

				
				String[] arr = atributes.getStringValueArray();
				
				for (int i = 0; i < arr.length; i++) {

					//if (arr[i].contains("ou=Roles")) {
						obj = getLdapAttributes(lc, arr[i]);
						al.add(obj.getString("cn"));
					//}
				}
			}
			lc.disconnect();
		} catch (LDAPException e) {
			e.getLDAPErrorMessage();
		} catch (JSONException e) {

		} catch (Exception e) {

		}
		
		return al;
	}

	
public List<String> nonRoleUniqueMembers(String userDN) {

		List<String> al = new ArrayList<String>();
		//JSONObject obj;
		try {
			LDAPConnection lc = connectLdap();
			LDAPSearchResults searchResults = lc.search(userDN,	LDAPConnection.SCOPE_BASE, "(objectclass=*)", null, false);

			while (searchResults.hasMore()) {
				LDAPEntry nextEntry = null;
				nextEntry = searchResults.next();

				LDAPAttribute atributes = nextEntry
						.getAttribute("uniqueMember");

				String[] arr = atributes.getStringValueArray();
				for (int i = 0; i < arr.length; i++) {
					if (!arr[i].contains("ou=Roles")) {
						al.add(arr[i]);
					}
				}
			}
			lc.disconnect();
		} catch (LDAPException e) {
			e.getLDAPErrorMessage();
		} catch (JSONException e) {

		} catch (Exception e) {

		}
		return al;
	}
	
	/** 
     * Internal Supporting Method for getRoles,getLoginRoles,uniqueMembers,memberDescription,getGroups,getPassword
     * @param lc      	:Ldap Connection Object
     * @param userDN	:User Directory Name
     * 
     * Returns LDAP Attributes for Specified DN
     */
	private JSONObject getLdapAttributes(LDAPConnection lc, String userDN)
			throws JSONException, LDAPException, Exception {

		JSONObject obj = new JSONObject();
		LDAPEntry nextEntry = null;

		LDAPSearchResults searchResults = lc.search(userDN,
				LDAPConnection.SCOPE_BASE, "(objectclass=*)", null, false);
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
     * Internal Supporting Method for deleting the roles assign to the particular task
     * @param compUrl      	:User Directory Name
     * @param role	:   role to delete
     * 
     */
	public void removeMemberFromTask(String compUrl, String role) {

		String memtoDelete = " cn="+role+",ou=Roles,dc="+dc1+",dc="+dc2;
		System.out.println("-------------------memtoDelete----------------"+memtoDelete);
		// Add modifications to modRoles
		LDAPAttribute uniqueMember = new LDAPAttribute("uniqueMember", memtoDelete);
		LDAPModification modRole = new LDAPModification(
				LDAPModification.DELETE, uniqueMember);
	   System.out.println("-------------------removeMemberFromTask----------------");
		// Modify the Role's attributes
		try {
			LDAPConnection lc = connectLdap();
			lc.modify(compUrl, modRole);
			lc.disconnect();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/** 
     * Internal Supporting Method for Adding the roles assign to the particular task
     * @param compUrl      	:User Directory Name
     * @param role	:   role to Add
     * 
     */
	public void addMemberToTask(String compUrl, String role) {

		String memtoAdd = "cn="+role+",ou=Roles,dc="+dc1+",dc="+dc2;

		// Add modifications to modRoles
		LDAPAttribute uniqueMember = new LDAPAttribute("uniqueMember", memtoAdd);
		LDAPModification modRole = new LDAPModification(LDAPModification.ADD,
				uniqueMember);

		try {
			// Modify the Role's attributes
			LDAPConnection lc = connectLdap();
			lc.modify(compUrl, modRole);
			lc.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/setting/producttree", method = RequestMethod.POST)
	public @ResponseBody
	List<TreeViewItem> getProduct(HttpServletRequest request) throws Exception {
		
		List<TreeViewItem> data = new ArrayList<TreeViewItem>();
		TreeViewItem staticroot = new TreeViewItem();
		TreeViewItem root = new TreeViewItem();
		HttpSession httpSession =  request.getSession(true);
		List<String> umList =  nonRoleUniqueMembers("cn="+httpSession.getAttribute("projectName")+",cn="+httpSession.getAttribute("companyName")+",ou=Company,dc="+dc1+",dc="+dc2);
		
		staticroot.setFields("Product Hierarchy","", false);
		root.setFields("Product","", true);
		
		for (String uniqueMembers1 : umList) {
			String[] umArrMod = uniqueMembers1.split(",");
			if(umArrMod.length==4){
				String moduleArr[] = uniqueMembers1.split(",");
				String moduleName[] = moduleArr[0].split("=");
				TreeViewItem level1 = root.AddSubItem();
				level1.setFields("Module = " + moduleName[1],"", true);
				for (String uniqueMembers2 : umList) {
					String[] umArr2 = uniqueMembers2.split(",");
					if(umArr2.length==5 && uniqueMembers2.contains(uniqueMembers1)){
						String form1Arr[] = uniqueMembers2.split(",");
						String form1Name[] = form1Arr[0].split("=");
						TreeViewItem level2 = level1.AddSubItem();
						level2.setFields("Forms = " + form1Name[1],"", true);
						for (String uniqueMembers3 : umList) {
							String[] umArr3 = uniqueMembers3.split(",");
							if(umArr3.length==6 && uniqueMembers3.contains(uniqueMembers2)){
								String form2Arr[] = uniqueMembers3.split(",");
								String form2Name[] = form2Arr[0].split("=");
								TreeViewItem level3 = level2.AddSubItem();
								String formOrTask ="Tasks";
								for (String uniqueMembers4 : umList) {
									String[] umArr4 = uniqueMembers4.split(",");
									if(umArr4.length==7 && uniqueMembers4.contains(uniqueMembers3)){
										formOrTask = "Forms";
										String form3Arr[] = uniqueMembers4.split(",");
										String form3Name[] = form3Arr[0].split("=");
										TreeViewItem level4 = level3.AddSubItem();
										level4.setFields("Task = " + form3Name[1],"", true);
									}
								}
								level3.setFields(formOrTask+" = " + form2Name[1],"", true);
							}
						}
					}
				}
				
			}
			
		}

		data.add(staticroot);
		data.add(root);
		return data;
	
	}
	
	
	public void addGroup(String url,String name, String description) {
		String containerName = url+",dc="+dc1+",dc="+dc2;
		LDAPAttributeSet attributeSet = new LDAPAttributeSet();

		attributeSet.add(new LDAPAttribute("objectclass", new String("groupOfUniqueNames")));
		attributeSet.add(new LDAPAttribute("objectclass", new String("top")));
		//attributeSet.add(new LDAPAttribute("uniqueMember", new String("cn=Admin,ou=Roles,dc="+dc1+",dc="+dc2)));
		attributeSet.add(new LDAPAttribute("cn", new String[] { name }));
		//attributeSet.add(new LDAPAttribute("description", new String(description)));
		String dn = "cn=" + name + "," + containerName;
		LDAPEntry newEntry = new LDAPEntry(dn, attributeSet);
		LDAPConnection lc = null;
		try {
			lc = connectLdap();
			lc.add(newEntry);
		}
		catch (LDAPException e) {
			logger.info("Got Exception in ProductAccessController.addGroup");
		} catch (Exception e) {
			logger.info("Got Exception in ProductAccessController.addGroup");
		} finally {
			try {
				lc.disconnect();
			} catch (LDAPException e) {
				logger.info("Got Exception in ProductAccessController.addGroup");
			}
		}
	}
	
	
	public void deleteGroup(String url,String name) {

		String containerName = url+",dc="+dc1+",dc="+dc2;
		String dn = "cn=" + name + "," + containerName;

		LDAPConnection lc = null;
		try {
			lc = connectLdap();
			lc.delete(dn);
		}
		catch (LDAPException e) {
		} catch (Exception e) {
			logger.info("Got Exception in ProductAccessController.deleteGroup");
		} finally {
			try {
				lc.disconnect();
			} catch (LDAPException e) {
				logger.info("Got Exception in ProductAccessController.deleteGroup");
			}
		}
	}
	

}
