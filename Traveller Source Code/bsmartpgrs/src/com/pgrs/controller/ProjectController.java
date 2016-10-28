package com.pgrs.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pgrs.core.dao.MasterGenericDAO;
import com.pgrs.dao.IProjectDAO;
import com.pgrs.dao.IProjectHeirarchyDAO;
import com.pgrs.dao.IProjectTreeLevelDAO;
import com.pgrs.entity.Company;
import com.pgrs.entity.Project;
import com.pgrs.entity.ProjectHeirarchyEntity;
import com.pgrs.entity.ProjectTreeTemplateLevels;

/**
 * @author Ravi Shankar Reddy
 *
 */

@Controller
public class ProjectController {
	static Logger logger = LoggerFactory.getLogger(ProjectController.class);


	@Autowired
	private MasterGenericDAO genericDAO;
	
	@Autowired
	private IProjectDAO projectDAO;
	
	@Autowired
	private ProductAccessController productAccessController;
	
	@Autowired
	private IProjectTreeLevelDAO iProjectTreeLevelDAO;	
	
	@Autowired
	private IProjectHeirarchyDAO projectHeirarchyDAO;
	
	@RequestMapping("/project")
	public String projectIndex(HttpServletRequest request,Model model){
		model.addAttribute("ViewName", "Projects");
		return "usermanagement/project";
	}
	
	@RequestMapping(value="/project/read", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody List<?> read(){		
		return projectDAO.read();
	}
	
	@RequestMapping(value = "/project/getMetricsChoice", method = RequestMethod.GET)
	public @ResponseBody List<Map<String, String>> getAllMetricsChoice() {
       List<String> actionNames=new ArrayList<String>();
		
		actionNames.add("Yes");
		actionNames.add("No");
		Collections.sort(actionNames);
		
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		Map<String, String> map = null;
		for (Iterator<String> iterator = actionNames.iterator(); iterator.hasNext();)
		{
			String string = (String) iterator.next();
			map = new HashMap<String, String>();
			map.put("metric", string);
			result.add(map);
		}
		return result;
	}
	
	@RequestMapping(value="/project/save", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody List<?> save(@ModelAttribute("project") Project project,HttpServletRequest request,Model model,BindingResult bindingResult){
		project.setCompanyId(Integer.parseInt(request.getParameter("companyId")));
		projectDAO.save(project);	 
		return read();
	}
	
	@RequestMapping(value="/project/update", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody List<?> update(@ModelAttribute("project") Project project,HttpServletRequest request,Model model){
		project.setCompanyId(Integer.parseInt(request.getParameter("companyId")));
		project.setId(Integer.parseInt(request.getParameter("id")));
		projectDAO.update(project);
		return read();
	}
	
	@RequestMapping(value="/project/delete", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody List<?> delete(@ModelAttribute Project project,HttpServletRequest request,Model model){
		
		//genericDAO.deleteById(Project.class, Integer.parseInt(request.getParameter("id")));
		projectDAO.delete(Integer.parseInt(request.getParameter("id")));
		return read();
	}
	
	@RequestMapping(value = "/project/status/{status}/{id}", method ={ RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String status(@PathVariable int status,@PathVariable int id,HttpServletRequest request,HttpServletResponse response){
		
		Project project = genericDAO.getById(Project.class, id);
		String projectName = project.getName();
		String companyName = project.getCompany().getCompanyName();
		if(status==0){
			List<Integer> usersList = projectDAO.getActiveUsersBasedOnProjectId(id);
			if(usersList.isEmpty()){
				projectDAO.projectStatusUpdate(status,id);
				productAccessController.deleteGroup("cn="+companyName+",ou=Company",projectName);
				projectHeirarchyDAO.deleteProjectHeirarchyBasedOnProjectId(id);
				return "Deactivated";
			}else{
				return "Cannot be Deactivate,because this project assigned user is active";
			}
		}else{
			projectDAO.projectStatusUpdate(status,id);
			productAccessController.addGroup("cn="+companyName+",ou=Company",projectName, projectName);
			ProjectHeirarchyEntity projectHeirarchyEntity = new ProjectHeirarchyEntity();
			projectHeirarchyEntity.setText(project.getName());
			projectHeirarchyEntity.setType("Projects");
			projectHeirarchyEntity.setTreeHierarchy("Project > "+project.getName());
			projectHeirarchyEntity.setParentId(2);
			projectHeirarchyEntity.setProject(genericDAO.getById(Project.class, project.getId()));
			projectHeirarchyDAO.save(projectHeirarchyEntity);
			return "Activated";
		}
	}
	
	
	@SuppressWarnings({ "unchecked", "serial" })
	@RequestMapping(value="/project/company", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody List<?> getComapny(HttpServletRequest request,Model model){
		
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		@SuppressWarnings("rawtypes")
		Map criterias =  new HashMap();
		criterias.clear();
		criterias.put("status", 1);
		for (final Company company : genericDAO.findByCriteria(Company.class,criterias)) {
			result.add(new HashMap<String, Object>() {{
				put("companyId",company.getId());
				put("companyName", company.getCompanyName());
			}});
		}
		return result;
	}	
	
	@RequestMapping(value = "/projecttreetemplate", method ={ RequestMethod.POST,RequestMethod.GET})
	public String projectTemplateIndex(Model model, HttpServletRequest request,HttpServletResponse response) throws IOException {
		return "usermanagement/projecttreetemplate";
	}
		
	@RequestMapping(value = "/project/template/status/{status}/{id}", method ={ RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String projectTemplateStatus(@PathVariable int status,@PathVariable int id,HttpServletRequest request,HttpServletResponse response){
		genericDAO.executeSimpleUpdateQuery("update ProjectTreeTemplate model set model.status="+status+" where model.id="+id);
		if(status==0)
			return "Deactivated";
		else
			return "Activated";
	}
	
	
	
	@RequestMapping(value="/project/template/level/read/{id}", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody List<?> projectTemplateLevelRead(@PathVariable int id){

		return projectDAO.readTemplateLevels(id);
	}

	
	@RequestMapping(value="/project/template/level/save/{id}", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody Object projectTemplateLevelSave( @ModelAttribute("projectTreeTemplateLevels") ProjectTreeTemplateLevels projectTreeTemplateLevels,@PathVariable int id,HttpServletRequest request,Model model,BindingResult bindingResult){
		
		projectTreeTemplateLevels.setProjectId(id);;
		List<Object[]> obj = genericDAO.executeSimpleObjectQuery("select MAX(o.level), MAX(o.level) from ProjectTreeTemplateLevels o where o.projectId="+id);
		int level =0;
		if(obj.size()==0)
			level=1;
		else
		{	
			Object[] ob = obj.get(0);
			if(ob[0]==null)
				level =1;
			else
				level =(Integer)ob[0]+1;
		}
		String names=request.getParameter("name");
		projectTreeTemplateLevels.setLevel(level);
		/*@SuppressWarnings("unchecked")
		//List<Map<String, Object>> listAssets = (ArrayList<Map<String, Object>>) map.get("text");// this is what you have already
		for (Map<String, Object> map1 :listAssets) {
		    for (Map.Entry<String, Object> entry : map1.entrySet()) {		    	
		    	if(entry.getKey().equalsIgnoreCase("value")){
		    		names+=entry.getValue()+",";
		    	}
		    }
		}*/
		projectTreeTemplateLevels.setName(names);
		genericDAO.save(projectTreeTemplateLevels);
		return projectDAO.readTemplateLevels(id);
	}
	
	@RequestMapping(value="/project/template/level/update/{id}", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody List<?> projectTemplateLevelUpdate(@ModelAttribute ProjectTreeTemplateLevels projectTreeTemplateLevels,@PathVariable int id,HttpServletRequest request,Model model){
		projectTreeTemplateLevels.setProjectId(id);
		genericDAO.update(projectTreeTemplateLevels);
		return projectDAO.readTemplateLevels(id);
	}
	
	@RequestMapping(value="/project/template/level/delete/{id}", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody List<?> projectTemplateLevelDelete(@ModelAttribute ProjectTreeTemplateLevels projectTreeTemplateLevels,@PathVariable int id,HttpServletRequest request,Model model){
		
		iProjectTreeLevelDAO.delete(Integer.parseInt(request.getParameter("phlId")));
		return projectDAO.readTemplateLevels(id);
	}	
}


