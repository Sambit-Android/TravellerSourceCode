package com.pgrs.serviceimpl;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import navigation.Example;
import navigation.ThirdLevel;
import navigation.Widget;
import net.sf.ehcache.CacheManager;

import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pgrs.core.dao.MasterGenericDAO;
import com.pgrs.dao.IndexDao;
import com.pgrs.entity.Company;
import com.pgrs.entity.Project;
import com.pgrs.entity.Users;
import com.pgrs.ldap.LdapBusinessModel;
import com.pgrs.ldap.UserCompany;
import com.pgrs.ldap.UserProjects;
import com.pgrs.service.IndexService;
import com.pgrs.service.UserService;
import com.pgrs.util.CompanyMenu;
import com.pgrs.util.ItemDetails;
import com.pgrs.util.ModuleDetails;
import com.pgrs.util.MyBootstrapCacheLoaderFactory;
import com.pgrs.util.ProjectMenu;
import com.pgrs.util.ThirdLevelDetails;

@Service
public class IndexServiceImpl implements IndexService{

	@Autowired
	private MyBootstrapCacheLoaderFactory myBootstrapCacheLoaderFactory;
	
	@Autowired
	CacheManager cacheManager;
	
	@Autowired
	private LdapBusinessModel ldapBusinessModel;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private MasterGenericDAO genericDAO; 
	
	@Autowired
	private IndexDao indexDao;
	
	public static String projectName="";
	public static String companyName="";
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void setMenu(HttpServletRequest request,Principal principal) throws Exception{
		
		List<JSONObject> memberWithDesciption=myBootstrapCacheLoaderFactory.getMemberWithDesciption();	
		List<CompanyMenu> memberWithDesciptionForMenu=myBootstrapCacheLoaderFactory.getMemberWithDesciptionMenu();	

		HttpSession session = request.getSession(false);
		String userLoginName = principal.getName().toLowerCase();
		session.setAttribute("userId", userLoginName);
		session.setAttribute("memberWithDesciption", memberWithDesciption); 
		
		String themeName = userService.getThemeNameByLoginName(userLoginName);
		if(themeName==null){
			session.setAttribute("themeName", "default");
			session.setAttribute("theme", "Default");
		}else{
			String theme = themeName.replace(" ", "").toLowerCase();			
			session.setAttribute("themeName", theme);
			session.setAttribute("theme", themeName);
		}
		
		List<String> roles = ldapBusinessModel.getLoginRoles(userLoginName);

		session.setAttribute("roles", roles);

		ObjectMapper mapper = new ObjectMapper(new JsonFactory());  		
		List<JSONObject> modules=new ArrayList<JSONObject>();
		List<String> mod=new ArrayList<String>();

		List<String> companyList=new ArrayList<>();
		List<String> projectList=new ArrayList<>();

		List<Project> projectobjects=userService.getProjects(principal.getName().toLowerCase());
		List<UserCompany> userComapies=new ArrayList<>();
		List<String> userComapiesUnique=new ArrayList<>();
		List<String> userprojectsUnique=new ArrayList<>();
		for(Project pr:projectobjects){
			UserCompany uc=new UserCompany();
			List<UserProjects> up=new ArrayList<>();
			uc.setCompanyName(pr.getCompany().getCompanyName());
			for(Project pro:projectobjects){

				if(pro.getCompany().getCompanyName().equals(uc.getCompanyName())){

					UserProjects upro=new UserProjects();
					upro.setProjectName(pro.getName());
					if(!(userprojectsUnique.contains(pr.getCompany().getCompanyName()+pro.getName()))){
						projectList.add(pro.getName());
						up.add(upro);
					}
					userprojectsUnique.add(pr.getCompany().getCompanyName()+pro.getName());
				}
			}
			uc.setUserProject(up);
			if(!(userComapiesUnique.contains(pr.getCompany().getCompanyName()))){
				companyList.add(pr.getCompany().getCompanyName());
				userComapies.add(uc);
			}
			userComapiesUnique.add(pr.getCompany().getCompanyName());
		}

		session.setAttribute("companylist", userComapies);

		if(companyList.size()>0){
			companyName=companyList.get(0);
			if(session.getAttribute("companyName")==null){
				session.setAttribute("companyName", companyName);
				String str=(String) session.getAttribute("companyName");
				List<Company> Company=genericDAO.executeSimpleQuery("Select model from Company model where model.companyName='"+str+"'");
				int comapnyId=Company.get(0).getId();
				session.setAttribute("companyId", comapnyId);
			}else{
				String str=(String) session.getAttribute("companyName");
				List<Company> pro=genericDAO.executeSimpleQuery("Select model from Company model where model.companyName='"+str+"'");
				int companyId=pro.get(0).getId();
				session.setAttribute("companyId", companyId);
			}
		}

		if(projectList.size()>0){
			projectName=projectList.get(0);
			if(session.getAttribute("projectName")==null){
				session.setAttribute("projectName", projectName);
				String str=(String) session.getAttribute("projectName");
				List<Project> pro=genericDAO.executeSimpleQuery("Select model from Project model where model.name='"+str+"'");
				int projectId=pro.get(0).getId();
				session.setAttribute("projectId", projectId);
			}else{
				String str=(String) session.getAttribute("projectName");
				List<Project> pro=genericDAO.executeSimpleQuery("Select model from Project model where model.name='"+str+"'");
				int projectId=pro.get(0).getId();
				session.setAttribute("projectId", projectId);
			}
		}
		Map criterias = new HashMap();
		criterias.put("urLoginName", principal.getName().toLowerCase());
		criterias.put("projectId", (int) session.getAttribute("projectId"));
		Users users = genericDAO.getByCriteria(Users.class, criterias);
		session.setAttribute("officeId", users.getOfficeId());
		session.setAttribute("urId", users.getUrId());
		session.setAttribute("urName", users.getUrName());
		//session.setAttribute("userId", (String)SessionData.getUserDetails().get("userID"));


		/*for(CompanyMenu cm:memberWithDesciptionForMenu){
			for(ProjectMenu pm:cm.getProjects()){
				for(ModuleDetails md:pm.getModuleDetails()){
					for(ItemDetails id:md.getItems()){
						
					}
				}
			}
		}*/

		List<ThirdLevel> thirdlevel=new ArrayList<ThirdLevel>();
		List<String> thirdList=new ArrayList<>();

		for(CompanyMenu companyMenu:memberWithDesciptionForMenu){
			if(companyMenu.getCompanyName().equals(companyName)){
				for(ProjectMenu projectMenu:companyMenu.getProjects()){
					for(String str:projectList){
						if(str.equals(projectMenu.getProjectName())){
							for(ModuleDetails mdetails:projectMenu.getModuleDetails()){
								for(String rol:roles){
									String strRole[]=mdetails.getRole().split(",");
									for(int i=0;i<strRole.length;i++){
										if(strRole[i].equals(rol)){
											JSONObject module=new JSONObject();
											module.put("text", mdetails.getDescription());					
											module.put("role", strRole[i]);
											module.put("image", mdetails.getImage());
											List<JSONObject> items=new ArrayList<JSONObject>();
											List<String> im=new ArrayList<String>();					
											for(ItemDetails idetails:mdetails.getItems()){	
												for(String rolItem:roles){
													String itemRole[]=idetails.getRole().split(",");
													for(int j=0;j<itemRole.length;j++){
														if(itemRole[j].equals(rolItem)){
															JSONObject item=new JSONObject();
															item.put("text", idetails.getText());						
															item.put("url", idetails.getUrl());
															if(idetails.getUrl().equals("")){
																List<ThirdLevelDetails> thirdLevelDetails=ldapBusinessModel.thirdlevelMenu(mdetails.getDescription(),idetails.getText());
																for(ThirdLevelDetails tmdetails:thirdLevelDetails){
																	String thirdLevelRole[] = tmdetails.getRole().split(",");
																	for(int k=0;k<thirdLevelRole.length;k++){
																		if(itemRole[k].equals(rolItem)){
																			ThirdLevel json=new ThirdLevel();
																			json.setModule(idetails.getText());
																			json.setUrl(tmdetails.getUrl());
																			json.setName(tmdetails.getText());
																			if(!(thirdList.contains(tmdetails.getText()))){
																				thirdlevel.add(json);	
																			}
																			thirdList.add(tmdetails.getText());
																		    }
																		}
																	}
															}
															item.put("role", itemRole[j]);	
															if(!(im.contains(idetails.getText()))){
																items.add(item);
															}
															im.add(idetails.getText());
														}
													}
												}
											}

											module.put("items", items);					
											if(!(mod.contains(mdetails.getDescription()))){
												modules.add(module);
											}
											mod.add(mdetails.getDescription());
										}
									}

								}		

							}		
						}
					}
				}
			}
		}

		JSONObject jo = new JSONObject();	
		jo.put("Navigation", modules);  

		HashMap<String, Widget[]> navigation = mapper.readValue(jo.toString(), new TypeReference<HashMap<String,Widget[]>>() {});    

		for (Map.Entry<String, Widget[]> entry : navigation.entrySet()) 
		{
			for (Widget widget : entry.getValue()) 
			{
				List<Example> examples = new ArrayList<Example>();   
				for (Example example : widget.getItems()) 
				{	
					examples.add(example);
				}

				widget.setItems(examples.toArray(new Example[examples.size()]));
			}
		}     

		session.setAttribute("navigation", navigation);
		session.setAttribute("thirdlevel", thirdlevel);
		cacheManager.removalAll();
	}
	
	
	@Override
	public void setIndexDetails(Model model,HttpServletRequest request) {
		indexDao.setIndexDetails(model,request);
	}

	@Override
	public Map<String, Object> getindexmobile(String username, int officeid,int projectid) {
		return indexDao.setIndexDetailsmobile(username, null, null, officeid, projectid);
	}
}
