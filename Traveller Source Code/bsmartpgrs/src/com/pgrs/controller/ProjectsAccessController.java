package com.pgrs.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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
import com.pgrs.ldap.LdapBusinessModel;
import com.pgrs.util.MyBootstrapCacheLoaderFactory;
import com.pgrs.util.TreeViewItem;

/**
 * @author Ravi Shankar Reddy
 *
 */

@Controller
public class ProjectsAccessController {

	
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
	
	@Autowired
	private LdapBusinessModel ldapBusinessModel;
	
	@Autowired
	private MyBootstrapCacheLoaderFactory myBootstrapCacheLoaderFactory;
	
	
	
	@RequestMapping(value = "/projectaccess", method ={ RequestMethod.POST,RequestMethod.GET})
	public String loginpage(Model model, HttpServletRequest request,HttpServletResponse response) throws IOException {
		//model.addAttribute("roles",productAccessController.getRoles());
		return "usermanagement/projectaccess";
	}
	
	
	
	
	
	@RequestMapping(value = "/projectaccess/readcompanytree/{ou}", method = RequestMethod.POST)
	public @ResponseBody
	List<TreeViewItem> getProduct(HttpServletRequest request,@PathVariable String ou) throws Exception {
		
		List<String> listOfMembers = new ArrayList<String>();
		List<TreeViewItem> data = new ArrayList<TreeViewItem>();
		TreeViewItem staticroot = new TreeViewItem();
		TreeViewItem root = new TreeViewItem();
		//String ldapUrl = "ou="+ou+",dc="+dc1+",dc="+dc2;
		try {
			LDAPConnection lc = connectLdap();

			LDAPSearchResults searchResults = lc.search("ou="+ou+",dc="+dc1+",dc="+dc2, LDAPConnection.SCOPE_ONE,"(objectclass=*)", null, false);
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

				staticroot.setFields(ou+"  Hierarchy","removethis", false);
				root.setFields(ou,"", true);

				if (parts.length == 4) {
					String parts2[] = parts[0].split("=");
					TreeViewItem level1 = null;
					if(!StringUtils.equalsIgnoreCase(parts2[1], "Test Admin"))	{
						level1 = root.AddSubItem();
						level1.setFields(parts2[1],parts[0]+","+"", true);
					}
					List<String> listOfchild = new ArrayList<String>();
					LDAPSearchResults searchChild = lc.search(parts[0] + ",ou="+ou+",dc="+dc1+",dc="+dc2,LDAPConnection.SCOPE_ONE, "(objectclass=*)", null,	false);

					while (searchChild.hasMore()) {
						LDAPEntry nextEntryChild = null;
						try {
							nextEntryChild = searchChild.next();
						} catch (LDAPException e) {
							if (e.getResultCode() == LDAPException.LDAP_TIMEOUT	|| e.getResultCode() == LDAPException.CONNECT_ERROR) {
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
								level2.setFields(child11[1], child1[0]+ "," + child1[1]+","+"",true);

							List<String> listOfchildofchild = new ArrayList<String>();
							LDAPSearchResults searchChildofchild = lc.search(child1[0] + "," + child1[1]	+ ",ou="+ou+",dc="+dc1+",dc="+dc2,LDAPConnection.SCOPE_ONE,"(objectclass=*)", null, false);

							while (searchChildofchild.hasMore()) {
								LDAPEntry nextEntryChildofChild = null;
								try {
									nextEntryChildofChild = searchChildofchild
											.next();
								} catch (LDAPException e) {
									if (e.getResultCode() == LDAPException.LDAP_TIMEOUT	|| e.getResultCode() == LDAPException.CONNECT_ERROR) {
										break;
									} else {
										continue;
									}
								}
								listOfchildofchild.add(nextEntryChildofChild.getDN());
							}
							Iterator<String> itChildofchild = listOfchildofchild.iterator();

							int k = 0;
							while (itChildofchild.hasNext()) {
								itChildofchild.next();
								String loc1 = listOfchildofchild.get(k);
								String[] child2 = loc1.split(",");
								if (child2.length == 6) {
									TreeViewItem level3 = level2.AddSubItem();
									String child12[] = child2[0].split("=");
									String usrlDn = "cn="+child12[1]+","+child1[0] +","+child1[1]+",ou="+ou+",dc="+dc1+",dc="+dc2; 									
									List<String> seventhLevelList = new ArrayList<String>();
									LDAPSearchResults searchSixthLevel = lc.search(usrlDn,LDAPConnection.SCOPE_ONE,"(objectclass=*)", null, false);
									
									level3.setFields(child12[1],"cn="+child12[1]+","+child1[0] +","+child1[1]+",",true);
									
									while(searchSixthLevel.hasMore()){	
										LDAPEntry getSeventhLevel = null;
										try {
											getSeventhLevel = searchSixthLevel.next();
										} catch (LDAPException e) {
											if (e.getResultCode() == LDAPException.LDAP_TIMEOUT	|| e.getResultCode() == LDAPException.CONNECT_ERROR) {
												break;
											} else {
												continue;
											}
										}
										seventhLevelList.add(getSeventhLevel.getDN());
									}									
									Iterator<String> iterarteSeventhLevel = seventhLevelList.iterator();
									
									int l=0;
									while (iterarteSeventhLevel.hasNext()) {
										iterarteSeventhLevel.next();
										String seven = seventhLevelList.get(l);									
										TreeViewItem level4 = level3.AddSubItem();					
										String usrlDnEight = "cn="+(String)getCN(seven)+","+usrlDn;										
										List<String> eighthLevelList = new ArrayList<String>();
										LDAPSearchResults searchEighthLevel = lc.search(usrlDnEight,LDAPConnection.SCOPE_ONE,"(objectclass=*)", null, false);
										
										level4.setFields((String)getCN(seven),"cn="+(String)getCN(seven)+",cn="+child12[1]+","+child1[0] +","+child1[1]+",", true);
										
										while(searchEighthLevel.hasMore()){
		
											LDAPEntry getEighthLevel = null;
											try {
												getEighthLevel = searchEighthLevel.next();
											} catch (LDAPException e) {
												if (e.getResultCode() == LDAPException.LDAP_TIMEOUT	|| e.getResultCode() == LDAPException.CONNECT_ERROR) {
													break;
												} else {
													continue;
												}
											}
											eighthLevelList.add(getEighthLevel.getDN());
										}
										
										Iterator<String> iterarteEightthLevel = eighthLevelList.iterator();
										
										int m=0;
										while (iterarteEightthLevel.hasNext()) {
											iterarteEightthLevel.next();
											String eight = eighthLevelList.get(m);
											
											level4.AddSubItem().setFields("Tasks = "+(String)getCN(eight), "",true);
											m++;
										}
										l++;
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
	
	private LDAPConnection connectLdap() throws LDAPException, Exception {
		LDAPConnection lc = new LDAPConnection();
		lc.connect(ldapHost, ldapPort);
		lc.bind(ldapVersion, loginDN, password.getBytes("UTF8"));
		return lc;
	}
	
	private String getCN(String record) throws LDAPException, Exception {
		  LDAPConnection lc = connectLdap();
		  JSONObject obj = getLdapAttributes(lc, record);
		  String text = obj.getString("cn");     
		  lc.disconnect();
		  return text;
	}
	
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
	
	/*@RequestMapping(value = "/projectaccess/readroles", method = RequestMethod.POST)
	public List<Ldap> getRoles() {
		return productAccessController.getRoles();
	}
*/
	
	@RequestMapping(value = "/projectaccess/commitchanges", method = {RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody
	String commitChanges(HttpServletRequest request,@RequestParam("projects") String projects,@RequestParam("products") String products, @RequestParam("roles") String roles) throws Exception {
		
		String projectsArr[] = projects.split(":");
		String productArr[] = products.split(":");
		String rolesArr[] = roles.split(":");
		for (String projectName : projectsArr) {
			String targetUrl = projectName+"ou=Company,dc="+dc1+",dc="+dc2;;
			
			List<String> um = fullUniqueMembers(targetUrl);
			for(int i=0;i<um.size();i++){
				removeAttribute("uniqueMember",targetUrl,um.get(i));
				System.out.println("@@@@@@@@@@@@@#"+um.get(i));
			}
			for (String rolesName : rolesArr) {
				addAttribute("uniqueMember",targetUrl, rolesName+"ou=Roles,dc="+dc1+",dc="+dc2);
				System.out.println("#######################"+rolesName);
			}
			for (String productName : productArr) {
				addAttribute("uniqueMember",targetUrl, productName+"ou=Products,dc="+dc1+",dc="+dc2);
				System.out.println("########$$$$$$$$$$$$$$$$$$##############"+productName);
			}
			addAttribute("uniqueMember",targetUrl,"cn=Admin,ou=Roles,dc="+dc1+",dc="+dc2);
		}
		String o = "";
		String ou = "";
		for (String productName : productArr) {
			String targetUrl = productName+"ou=Products,dc="+dc1+",dc="+dc2;;
			String oMembers= getMembers("o",targetUrl);	
			String ouMembers= getMembers("ou",targetUrl);
			o=oMembers;
			ou=ouMembers;
			/*removeAttribute("o",targetUrl,oMembers);	
			removeAttribute("ou",targetUrl,ouMembers);	 */
			for (String projectName : projectsArr) {
				String projArr[] = (projectName+"ou=Company,dc="+dc1+",dc="+dc2).split(",");
				if(projArr.length > 4){
					if(!o.contains(getCN((projectName+"ou=Company,dc="+dc1+",dc="+dc2)))){
						o+=getCN((projectName+"ou=Company,dc="+dc1+",dc="+dc2))+",";
					}			
					if(projArr[1].contains("cn")){
						String comp[] = projArr[1].split("=");
						if(!ou.contains(comp[1])){
							ou+=comp[1]+",";
						}
					}
				}		
			}
			/*addAttribute("ou",targetUrl, ou);
			addAttribute("o",targetUrl, o);*/
		}
		
		myBootstrapCacheLoaderFactory.setMemberWithDesciption(ldapBusinessModel.getMemberAttributes());
		myBootstrapCacheLoaderFactory.setMemberWithDesciptionMenu(ldapBusinessModel.getMemberAttributesForMenu());
		
		return "Done";
	}
	
	public void addAttribute(String attribute,String targetUrl, String uniqueMem) {

		String memtoAdd = uniqueMem;
		// Add modifications to modRoles
		LDAPAttribute uniqueMember = new LDAPAttribute(attribute, memtoAdd);
		LDAPModification modRole = new LDAPModification(LDAPModification.ADD,
				uniqueMember);
		try {
			// Modify the Role's attributes
			LDAPConnection lc = connectLdap();
			lc.modify(targetUrl, modRole);
			lc.disconnect();
		} catch (Exception e) {
		}
	}
	
	
	public void removeAttribute(String attribute, String compUrl, String uniqueMem) {

		String memtoDelete = uniqueMem;

		// Add modifications to modRoles
		LDAPAttribute uniqueMember = new LDAPAttribute(attribute,memtoDelete);
		LDAPModification modRole = new LDAPModification(
				LDAPModification.DELETE, uniqueMember);

		// Modify the Role's attributes
		try {
			LDAPConnection lc = connectLdap();
			lc.modify(compUrl, modRole);
			lc.disconnect();

		} catch (Exception e) {
		}
	}
	
	
	public List<String> uniqueMembers(String userDN) {

		List<String> al = new ArrayList<String>();
		//JSONObject obj;
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
				for (String uniquemembers : arr) {
					
					String reponseSplitter[] = uniquemembers.split("ou=");
					al.add(reponseSplitter[0]);
				}
				
				/*for (int i = 0; i < arr.length; i++) {
						obj = getLdapAttributes(lc, arr[i]);
						al.add(obj.getString("cn"));
				}*/
			}
			lc.disconnect();
		} catch (LDAPException e) {
			e.getLDAPErrorMessage();
		} catch (JSONException e) {

		} catch (Exception e) {

		}
		return al;
	}
	
	public List<String> fullUniqueMembers(String userDN) {

		List<String> al = new ArrayList<String>();
		//JSONObject obj;
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
				for (String uniquemembers : arr) {
					al.add(uniquemembers);
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
	
	public String getMembers(String attribute,String userDN) {

		String al = "";
		try {
			LDAPConnection lc = connectLdap();
			LDAPSearchResults searchResults = lc.search(userDN,
					LDAPConnection.SCOPE_BASE, "(objectclass=*)", null, false);

			while (searchResults.hasMore()) {
				LDAPEntry nextEntry = null;
				nextEntry = searchResults.next();

				LDAPAttribute atributes = nextEntry
						.getAttribute(attribute);
				String[] arr = atributes.getStringValueArray();
				for (String uniquemembers : arr) {
					al+=uniquemembers;
				}
				/*for (int i = 0; i < arr.length; i++) {
						obj = getLdapAttributes(lc, arr[i]);
						al.add(obj.getString("cn"));
				}*/
			}
			lc.disconnect();
		} catch (LDAPException e) {
			e.getLDAPErrorMessage();
		} catch (JSONException e) {

		} catch (Exception e) {

		}
		return al;
	}
	
	@RequestMapping(value = "/projectaccess/getproductandroles", method={RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody List<String> 
	getProductAndRoles(HttpServletRequest request, @RequestParam("project") String project)
	{
		return uniqueMembers(project+"ou=Company,dc="+dc1+",dc="+dc2);
	}
	
}
