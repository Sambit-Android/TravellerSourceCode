package com.pgrs.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pgrs.core.dao.MasterGenericDAO;
import com.pgrs.dao.IProjectHeirarchyDAO;
import com.pgrs.entity.Office;
import com.pgrs.entity.ProjectHeirarchyEntity;
import com.pgrs.entity.ProjectTreeTemplateLevels;

/**
 * @author Ravi Shankar Reddy
 *
 */

@Controller
public class ProjectsManagementController {
	
	private static final Log logger = LogFactory.getLog(ProjectsManagementController.class);

	@Autowired
	private IProjectHeirarchyDAO projectHeirarchyDAO; 

	@Autowired
	private MasterGenericDAO genericDAO;

	@RequestMapping(value = "/projecttree", method ={ RequestMethod.POST,RequestMethod.GET})
	public String loginpage(Model model, HttpServletRequest request,HttpServletResponse response) throws IOException {
		return "usermanagement/projecttree";
	}

	// Tree for Map request 
	@RequestMapping(value = "/projecttree/map/read", method = RequestMethod.POST)
	public @ResponseBody
	List<ProjectHeirarchyEntity> readTree(@RequestBody Map<String, Object> model,HttpServletRequest request) {
		//HttpSession httpSession =  request.getSession(true);
		int projectId = 0;
		int companyId = 0;
		return projectHeirarchyDAO.read((Integer) model.get("id"),projectId,companyId);
	}
	@RequestMapping(value = "/projecttree/map/read1", method = RequestMethod.POST)
	public @ResponseBody
	List<ProjectHeirarchyEntity> readTree1(@RequestBody Map<String, Object> model,HttpServletRequest request) {
		//HttpSession httpSession =  request.getSession(true);
		int projectId = 0;
		int companyId = 0;
		return projectHeirarchyDAO.read1((Integer) model.get("id"),projectId,companyId);
	}

	//Tree with JSON response 
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/projecttree/json/read", method = RequestMethod.GET)
	public @ResponseBody
	List<ProjectHeirarchyEntity> readTree(HttpServletRequest req) {
		
		Integer setId=null;
		int projectId = 0;
		int companyId = 0;
		HttpSession httpSession =  req.getSession(true);
		if(req.getParameter("id")!=null){
			setId = Integer.valueOf(req.getParameter("id"));
		}
		Map criterias = new HashMap();
		if(req.getParameter("id")!="2" && req.getParameter("id")!=null){

			projectId = (int)httpSession.getAttribute("projectId");
			companyId = (int)httpSession.getAttribute("companyId");
		}
		if(setId == null){
			setId = (int)httpSession.getAttribute("officeId");
			if(projectId>0){
				criterias.put("project.id", projectId);
				criterias.put("project.company.id", companyId);
			}
			criterias.put("id",setId);
			List<ProjectHeirarchyEntity> list = genericDAO.findByCriteria(ProjectHeirarchyEntity.class, criterias);
			
			return list;
		}

		return projectHeirarchyDAO.read(setId,projectId,companyId);
	}
	
	//Tree with JSON response 
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/projecttree/json/customRead", method = RequestMethod.GET)
	public @ResponseBody
	List<ProjectHeirarchyEntity> customReadTree(HttpServletRequest req) {
		
		Integer setId=null;
		int projectId = 0;
		int companyId = 0;
		HttpSession httpSession =  req.getSession(true);
		if(req.getParameter("id")!=null){
			setId = Integer.valueOf(req.getParameter("id"));
		}
		Map criterias = new HashMap();
		if(req.getParameter("id")!="2" && req.getParameter("id")!=null){
			ProjectHeirarchyEntity projectHeirarchyEntity=genericDAO.getById(ProjectHeirarchyEntity.class, Integer.valueOf(req.getParameter("id")));
			projectId = projectHeirarchyEntity.getProject().getId();
			companyId = (int)httpSession.getAttribute("companyId");
		}
		if(setId == null){
			companyId = (int)httpSession.getAttribute("companyId");
			criterias.put("type","Projects");
			criterias.put("project.company.id", companyId);
			List<?> list=genericDAO.executeSimpleQuery("Select o.id,o.text from ProjectHeirarchyEntity o WHERE o.type='Projects' AND o.project.company.id="+companyId);
			//List<ProjectHeirarchyEntity> list=genericDAO.executeSimpleQuery("Select o from ProjectHeirarchyEntity o WHERE o.type='Projects' AND o.project.company.id="+companyId);
			List<ProjectHeirarchyEntity> entity=new ArrayList<ProjectHeirarchyEntity>();
			for (Iterator<?> iterator2=list.iterator();iterator2.hasNext();) {
				final Object[] values = (Object[]) iterator2.next();
				ProjectHeirarchyEntity projectHeirarchyEntity=new ProjectHeirarchyEntity();
				projectHeirarchyEntity.setId((int)values[0]);
				projectHeirarchyEntity.setText((String)values[1]);
				Set<ProjectHeirarchyEntity> childs=new HashSet<ProjectHeirarchyEntity>();
				childs.add(projectHeirarchyEntity);
				projectHeirarchyEntity.setChilds(childs);
				entity.add(projectHeirarchyEntity);
			}
			return entity;
		}

		return projectHeirarchyDAO.readCustom(setId,projectId,companyId);
	}


	@RequestMapping(value = "/project/template/tree/{projectId}", method = RequestMethod.GET)
	public @ResponseBody
	List<ProjectHeirarchyEntity> getTreeProjectTemplates(@PathVariable int projectId, HttpServletRequest req) {
		HttpSession httpSession =  req.getSession(true);
		int companyId = (int)httpSession.getAttribute("companyId");
		Integer setId=null;
		if(req.getParameter("id") == null){
			return projectHeirarchyDAO.read(setId,projectId,companyId);
		}else{
			return projectHeirarchyDAO.read(Integer.parseInt(req.getParameter("id")),projectId,companyId);
		}

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/projecttree/getdetailonid",method=RequestMethod.POST) 
	public @ResponseBody
	Map getDetailOnId(@RequestParam("id") int id) {
		Map<String, Object> result = new HashMap<String, Object>();
		Map criterias = new HashMap();
		criterias.put("id", id);
		List<ProjectHeirarchyEntity> entity = new ArrayList<ProjectHeirarchyEntity>();
		entity.add(projectHeirarchyDAO.getDetailsOnId(id));
		ProjectHeirarchyEntity  projectHeirarchyEntity = projectHeirarchyDAO.getDetailsOnId(id);
		result.put("id",projectHeirarchyEntity.getId());
		result.put("text", projectHeirarchyEntity.getText());
		result.put("type", projectHeirarchyEntity.getType());
		return result;
	}

	@RequestMapping(value = "/projecttree/delete", method = RequestMethod.POST)
	public @ResponseBody String  delete(@RequestParam("id") int id,HttpServletRequest request, HttpServletResponse response) throws IOException {
		String strresponse = "";
		int projectId = 0;
		int companyId = 0;
		HttpSession httpSession =  request.getSession(true);
		if(request.getParameter("id")!="2"){
			projectId = (int)httpSession.getAttribute("projectId");
			companyId = (int)httpSession.getAttribute("companyId");
		}
		List<ProjectHeirarchyEntity> list = projectHeirarchyDAO.read(id,projectId,companyId);
		if(list.size() == 0){
			projectHeirarchyDAO.delete(id);
			strresponse = "Deleted Successfully";
		}else{
			strresponse = "Only Leaf nodes can be deleted";
		}
		return strresponse;
	}


	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/projecttree/save", method = RequestMethod.POST)
	public @ResponseBody String save(
			@RequestParam("text") String text,
			@RequestParam("id") Integer id, 
			@RequestParam("treeHierarchy") String treeHierarchy,	
			HttpServletRequest request) throws ParseException {
		
		String strresponse = "";
		String[] treeSoplitter = treeHierarchy.split(">");
		logger.info("----------------------id      "+id);
		Map criterias = new HashMap();
		criterias.put("id", id);
		ProjectHeirarchyEntity projectHeirarchyEntity = new ProjectHeirarchyEntity();
		ProjectHeirarchyEntity entity = genericDAO.getByCriteria(ProjectHeirarchyEntity.class, criterias);
		logger.info("----------------------Type      "+entity.getType());
		int flag = 0;
		HttpSession httpSession =  request.getSession(true);
		int	projectId = (int)httpSession.getAttribute("projectId");
		
		Set<ProjectTreeTemplateLevels> projectLevelsList = entity.getProject().getProjectTreeTemplateLevels();

		for (ProjectTreeTemplateLevels projectTreeTemplateLevels : projectLevelsList) {

			if(projectTreeTemplateLevels.getLevel() == treeSoplitter.length){
				flag=1;
				if(projectTreeTemplateLevels.getLevel()>=2){
					logger.info("----------------------Level    "+projectTreeTemplateLevels.getLevel());
					logger.info("---------------------- projectId   "+projectId);
					logger.info("---------------------- SITE CODE   "+entity.getSiteCode());
					String siteCode = projectHeirarchyDAO.getCountBasedOnLevelAndProjectId(projectTreeTemplateLevels.getLevel(),projectId,entity.getSiteCode());
					
					String parentSiteCode = "";
					if(entity.getSiteCode()!=null){
						parentSiteCode = entity.getSiteCode();
					}
					
					if("Zone".equalsIgnoreCase(entity.getType())){
					
					
					logger.info("-------------parentSiteCode----------"+parentSiteCode);
					if(siteCode==null){ 
					logger.info("---------------------inside  CategoryCode NULL  "+siteCode);
					projectHeirarchyEntity.setSiteCode(parentSiteCode+""+1);
					//projectHeirarchyEntity.setIndividualCatCode((categoryCode+1));
					
			       }    else{
					logger.info("---------------------inside  CategoryCode NOT NULL  "+siteCode);
			    	projectHeirarchyEntity.setSiteCode(parentSiteCode+""+((Integer.parseInt(siteCode.replace(parentSiteCode, "")))+1));
					//projectHeirarchyEntity.setIndividualCatCode(categoryCode+1);
					
						}
					}
					
					if("Circle".equalsIgnoreCase(entity.getType())){
						
						String divisionSiteCode=projectHeirarchyDAO.getDivisionSiteCode(projectTreeTemplateLevels.getLevel(),projectId,id);
						
						logger.info("---------------------divisionSiteCode----"+divisionSiteCode);
						String parentSiteCodeforDivision = "";
						if(divisionSiteCode!=null){
							parentSiteCodeforDivision = divisionSiteCode;
						}
						
						logger.info("-------------parentSiteCode----------"+parentSiteCode);
						logger.info("-------------inside Circle siteCode----------"+siteCode);
						if(divisionSiteCode==null){ 
						logger.info("---------------------inside  CategoryCode NULL  "+siteCode);
						projectHeirarchyEntity.setSiteCode(parentSiteCode+""+1);
						//projectHeirarchyEntity.setIndividualCatCode((categoryCode+1));
						
				       }    else{
						logger.info("---------------------inside  CategoryCode NOT NULL  "+siteCode);
				    	projectHeirarchyEntity.setSiteCode(""+((Integer.parseInt(parentSiteCodeforDivision)+1)));
						//projectHeirarchyEntity.setIndividualCatCode(categoryCode+1);

						}
					}
					
					if("Division".equalsIgnoreCase(entity.getType())){
						
						String subDivisionSiteCode=projectHeirarchyDAO.getDivisionSiteCode(projectTreeTemplateLevels.getLevel(),projectId,id);
						
						logger.info("---------------------subDivisionSiteCode----"+subDivisionSiteCode);
						String parentSiteCodeforSubDivision = "";
						if(subDivisionSiteCode!=null){
							parentSiteCodeforSubDivision = subDivisionSiteCode;
						}
						
						logger.info("-------------parentSiteCode----------"+parentSiteCode);
						logger.info("-------------inside Division siteCode----------"+siteCode);
						if(subDivisionSiteCode==null){ 
						logger.info("---------------------inside  CategoryCode NULL  "+siteCode);
						projectHeirarchyEntity.setSiteCode(parentSiteCode+""+0);
						//projectHeirarchyEntity.setIndividualCatCode((categoryCode+1));
						
				       }    else{
						logger.info("---------------------inside  CategoryCode NOT NULL  "+siteCode);
				    	projectHeirarchyEntity.setSiteCode(""+((Integer.parseInt(parentSiteCodeforSubDivision)+1)));
						//projectHeirarchyEntity.setIndividualCatCode(categoryCode+1);

						}
					}
					
					if("Subdivision".equalsIgnoreCase(entity.getType())){
						
						String sectionSiteCode=projectHeirarchyDAO.getDivisionSiteCode(projectTreeTemplateLevels.getLevel(),projectId,id);
						
						logger.info("---------------------sectionSiteCode----"+sectionSiteCode);
						String parentSiteCodeforSection = "";
						if(sectionSiteCode!=null){
							parentSiteCodeforSection = sectionSiteCode;
						}
						
						logger.info("-------------parentSiteCode----------"+parentSiteCode);
						logger.info("-------------inside Circle siteCode----------"+siteCode);
						if(sectionSiteCode==null){ 
						logger.info("---------------------inside  CategoryCode NULL  "+siteCode);
						projectHeirarchyEntity.setSiteCode(parentSiteCode+""+0);
						//projectHeirarchyEntity.setIndividualCatCode((categoryCode+1));
						
				       }    else{
						logger.info("---------------------inside  CategoryCode NOT NULL  "+siteCode);
				    	projectHeirarchyEntity.setSiteCode(""+((Integer.parseInt(parentSiteCodeforSection)+1)));
						//projectHeirarchyEntity.setIndividualCatCode(categoryCode+1);

						}
					}

				}

				projectHeirarchyEntity.setProjectTreeTemplateLevels(projectTreeTemplateLevels);
				projectHeirarchyEntity.setType(projectTreeTemplateLevels.getName());
				projectHeirarchyEntity.setTreeHierarchy(treeHierarchy);
				projectHeirarchyEntity.setText(text);
				projectHeirarchyEntity.setStatus(1);
				projectHeirarchyEntity.setParentId(id);
				projectHeirarchyEntity.setProject(entity.getProject());
				projectHeirarchyDAO.save(projectHeirarchyEntity);
				int Value = projectHeirarchyDAO.getIdByparentIdAndText(id,text);
				strresponse = Value + "/" +projectHeirarchyEntity.getType();
		}

		}
		
		if(flag==0){
			return "no";
		}

		return strresponse;
	}

	
    @RequestMapping(value = "/projecttree/update", method = RequestMethod.POST)
	public @ResponseBody String update(
			@RequestParam("text") String text,
			@RequestParam("id") int id,
			@RequestParam("treeHierarchy") String treeHierarchy,
			HttpServletRequest request) throws ParseException {

		String strresponse = "";
		/*ProjectHeirarchyEntity projectHeirarchyEntity = new ProjectHeirarchyEntity();
		projectHeirarchyEntity.setId(id);*/
		ProjectHeirarchyEntity projectHeirarchyEntity = projectHeirarchyDAO.getDetailsOnId(id);
		projectHeirarchyEntity.setText(text);
		//projectHeirarchyEntity.setParentId(entity.getParentId());
		/*projectHeirarchyEntity.setTreeHierarchy(treeHierarchy);
		Map criterias = new HashMap();
		criterias.put("id", id);
		projectHeirarchyEntity.setProject(genericDAO.getByCriteria(ProjectHeirarchyEntity.class, criterias).getProject());*/

		projectHeirarchyDAO.update(projectHeirarchyEntity);
		strresponse="Updated Successfully";
		return strresponse;
	}

	@RequestMapping(value = "/projecttree/getoffices",method={RequestMethod.POST,RequestMethod.GET}) 
	public @ResponseBody
	List<Office> getAllOffice() {

		List<Office> list =  new ArrayList<Office>();
		for (Office entity : genericDAO.findAll(Office.class)) {
			Office office = new Office();
			office.setId(entity.getId());
			office.setOfficeName(entity.getOfficeName());
			list.add(office);
		}
		return list;
	}

	@RequestMapping(value = "/projecttree/getonlyoffices",method={RequestMethod.POST,RequestMethod.GET}) 
	public @ResponseBody
	List<Map<String, Object>> getOnlyOffice(HttpServletRequest request) {
		HttpSession httpSession =  request.getSession(true);
		int	id = (int)httpSession.getAttribute("officeId");
		return common(id,request);
	}

	@RequestMapping(value = "/projecttree/getonlyofficesonid/{id}",method={RequestMethod.POST,RequestMethod.GET}) 
	public @ResponseBody
	List<Map<String, Object>> getOnlyOfficeOnIdProject(@PathVariable int id,HttpServletRequest request) {
		return common(id,request);
	}


	@RequestMapping(value = "/project/template/getoffices/{projectId}",method={RequestMethod.POST,RequestMethod.GET}) 
	public @ResponseBody
	List<Map<String, Object>> getOnlyOfficeProject(@PathVariable int projectId,HttpServletRequest request) {
		HttpSession httpSession =  request.getSession(true);
		int	id = (int)httpSession.getAttribute("officeId");
		return commonForProject(id, projectId);
	}


	@RequestMapping(value = "/project/template/getofficesonid/{id}/{projectId}",method={RequestMethod.POST,RequestMethod.GET}) 
	public @ResponseBody
	List<Map<String, Object>> getOnlyOfficeOnId(@PathVariable int id,@PathVariable int projectId) {
		return commonForProject(id, projectId);
	}
	Map<Integer,ProjectHeirarchyEntity> officeMap;
	@SuppressWarnings("serial")
	public List<Map<String, Object>> common(int id,HttpServletRequest request){
		HttpSession session= request.getSession(false);
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>(); 
		String officeIds = id+",";
		/*Map criterias = new HashMap();
		criterias.put("id", id);
		criterias.put("project.id",(int)session.getAttribute("projectId"));

		ProjectHeirarchyEntity projectHeirarchyEntity = genericDAO.getByCriteria(ProjectHeirarchyEntity.class, criterias);

		for(ProjectHeirarchyEntity entity : projectHeirarchyEntity.getChilds()){
			officeIds+=entity.getId()+",";
			for (ProjectHeirarchyEntity entity2 : entity.getChilds()) {
				officeIds+=entity2.getId()+",";
				for (ProjectHeirarchyEntity entity3 : entity2.getChilds()) {
					officeIds+=entity3.getId()+",";
					for (ProjectHeirarchyEntity entity4 : entity3.getChilds()) {
						officeIds+=entity4.getId()+",";
						for (ProjectHeirarchyEntity entity5 : entity4.getChilds()) {
							officeIds+=entity5.getId()+",";
						}
					}
				}
			}
		}*/
		if(officeMap==null){
		List<?> bankNames = projectHeirarchyDAO.getAllOffice();
		officeMap=new HashMap<Integer,ProjectHeirarchyEntity>();
		for (Iterator<?> iterator = bankNames.iterator(); iterator.hasNext();)
		{
			final Object[] values = (Object[]) iterator.next();
			ProjectHeirarchyEntity projectHeirarchyEntity=new ProjectHeirarchyEntity();
			projectHeirarchyEntity.setId((int)values[0]);
			projectHeirarchyEntity.setText((String)values[1]);
			if(!((String)values[1]).equalsIgnoreCase("root")){
				projectHeirarchyEntity.setParentId((int)values[2]);
			}
			officeMap.put((Integer)values[0],projectHeirarchyEntity);
		}
		}
		Set<Integer> office=new HashSet<Integer>();
		office.add((int)session.getAttribute("officeId"));
		getAllOfficeUnderThisId(officeMap,id,office);
		if(true){
		Iterator<Integer> iterator=office.iterator();
		while(iterator.hasNext()){
			officeIds+=iterator.next()+",";
		}
		}
		//List<ProjectHeirarchyEntity> list =  genericDAO.executeSimpleQuery("select o from ProjectHeirarchyEntity o where (o.type='Office' OR o.type='Sub Division Office') and o.id IN("+officeIds.substring(0,officeIds.length()-1)+")  order by o.text");
		List<?> list =  genericDAO.executeSimpleQuery("select o.id,o.text,o.project.id,o.project.name from ProjectHeirarchyEntity o where (o.type='Office' OR o.type='Sub Division Office') and o.id IN("+officeIds.substring(0,officeIds.length()-1)+")  order by o.text");
		/*for (final ProjectHeirarchyEntity entity : list) {
			result.add(new HashMap<String, Object>() {{
				put("text", entity.getText());
				put("id", entity.getId());
				put("projectId", entity.getProject().getId());
				put("projectName", entity.getProject().getName());
			}});
		}*/
		for (Iterator<?> iterator2=list.iterator();iterator2.hasNext();) {
			final Object[] values = (Object[]) iterator2.next();
			result.add(new HashMap<String, Object>() {{
				put("text",(String)values[1]);
				put("id",(int)values[0]);
				put("projectId", (int)values[2]);
				put("projectName", (String)values[3]);
			}});
		}
		return result;

	}
	private void getAllOfficeUnderThisId(
			Map<Integer,ProjectHeirarchyEntity> officeMap, int officeId,Set<Integer> office) {

		Set<Integer> keys = officeMap.keySet();
		for(Integer key: keys){
			if(key !=0 && key!=1 && key>=officeId){
				ProjectHeirarchyEntity projectHeirarchyEntity=officeMap.get(key);
				if(projectHeirarchyEntity.getParentId()==officeId){
					office.add(projectHeirarchyEntity.getId());
					getAllOfficeUnderThisId(officeMap,projectHeirarchyEntity.getId(),office);

				}
			}
		}

	}

	@SuppressWarnings("serial")
	public List<Map<String, Object>> commonForProject(int id,int projectId){
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>(); 
		String officeIds = id+",";

		ProjectHeirarchyEntity projectHeirarchyEntity = genericDAO.getById(ProjectHeirarchyEntity.class, id);

		for(ProjectHeirarchyEntity entity : projectHeirarchyEntity.getChilds()){
			officeIds+=entity.getId()+",";
			for (ProjectHeirarchyEntity entity2 : entity.getChilds()) {
				officeIds+=entity2.getId()+",";
				for (ProjectHeirarchyEntity entity3 : entity2.getChilds()) {
					officeIds+=entity3.getId()+",";
					for (ProjectHeirarchyEntity entity4 : entity3.getChilds()) {
						officeIds+=entity4.getId()+",";
						for (ProjectHeirarchyEntity entity5 : entity4.getChilds()) {
							officeIds+=entity5.getId()+",";
						}
					}
				}
			}
		}
		List<?> list =  genericDAO.executeSimpleQuery("select o.id,o.text from ProjectHeirarchyEntity o where o.type='Office' and o.id IN("+officeIds.substring(0,officeIds.length()-1)+") and o.project.id="+projectId+" order by o.text");


		/*for (final ProjectHeirarchyEntity entity : list) {
			result.add(new HashMap<String, Object>() {{
				put("text", entity.getText());
				put("id", entity.getId());
			}});
		}*/
		for (Iterator<?> iterator2=list.iterator();iterator2.hasNext();) {
			final Object[] values = (Object[]) iterator2.next();
			result.add(new HashMap<String, Object>() {{
				put("text",(String)values[1]);
				put("id",(int)values[0]);
				
			}});
		}
		return result;

	}
	
	@RequestMapping(value="/project/office/tdscalc/{phId}/{paymentAmount}",method={RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody double tdsCalculation(@PathVariable int phId, @PathVariable double paymentAmount) throws Exception{
		
		Object[] obj = genericDAO.executeSingleObjectQuery("select o.tdsRate,o.tdsRate from ProjectHeirarchyEntity o where o.id="+phId);
		return paymentAmount*(Double)obj[0]/100; 
	}
	
}

