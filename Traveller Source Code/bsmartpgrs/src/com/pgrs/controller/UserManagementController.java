package com.pgrs.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import com.novell.ldap.LDAPException;
import com.pgrs.beans.UserProfileBean;
import com.pgrs.core.dao.MasterGenericDAO;
import com.pgrs.dao.IProjectHeirarchyDAO;
import com.pgrs.dao.IUsersDAO;
import com.pgrs.entity.Company;
import com.pgrs.entity.Department;
import com.pgrs.entity.Designation;
import com.pgrs.entity.Project;
import com.pgrs.entity.ProjectHeirarchyEntity;
import com.pgrs.entity.Role;
import com.pgrs.entity.Users;
import com.pgrs.ldap.LdapBusinessModel;
import com.pgrs.service.CommonService;
import com.pgrs.service.DepartmentService;
import com.pgrs.service.DesignationService;
import com.pgrs.service.RoleService;
import com.pgrs.service.UserService;
import com.pgrs.util.JsonResponse;

/**
 * @author Ravi Shankar Reddy
 *
 */

@Controller
public class UserManagementController
{	

	private static final Log logger = LogFactory.getLog(UserManagementController.class);

	@Autowired 
	private LdapBusinessModel ldapBusinessModel;

	@Autowired
	private DesignationService designationService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private DepartmentService departmentService;

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private UserService userService;

	@Autowired
	private MasterGenericDAO genericDAO;

	@Autowired
	private IUsersDAO iUsersDAO;
	
	@Autowired
	private IProjectHeirarchyDAO projectHeirarchyDAO; 
	
	@Autowired
	private IProjectHeirarchyDAO heirarchyDAO;	
	
	@Autowired
	private CommonService commonService;

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public String index(Model model, HttpServletRequest request,final Locale locale) {
		model.addAttribute("modulename","User Management");		
		return "usermanagement/users";
	}
	
	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public String profile(Model model, HttpServletRequest request,final Locale locale) {
		
		logger.info("Inside user profile method");
		
		HttpSession session = request.getSession(false); 
		String username= (String) session.getAttribute("userId");
	
		UserProfileBean usersDetails = userService.getUserDetailsByLoginName(username);
		
		model.addAttribute("usersDetails",usersDetails);
		model.addAttribute("modulename","User Profile");	
		
		return "usermanagement/userProfile";
	}	
	
	@RequestMapping(value = "/users/read", method = {RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody List<?> readUser() {
		logger.info("Inside user management read method");
		return userService.readUserObject();
	}
	
	@RequestMapping(value = "/users/updateUserDetails", method = RequestMethod.POST)
	public @ResponseBody String updateUserDetailsFromProfile(@RequestParam("userId") int userId,@RequestParam("userName") String userName,@RequestParam("contactNo") String contactNo,@RequestParam("emailId") String emailId,@RequestParam("urLoginName") String urLoginName,HttpServletResponse response) throws IOException {
		userService.updateuserDetailsFromProfile(userId,userName,contactNo,emailId,urLoginName);
	 	 return "Hello "+userName+" your details have been successfully updated";
	}	
	
	@RequestMapping(value = "/users/updateChangedUserPassword", method = RequestMethod.POST)
	public @ResponseBody String updateChangedUserPassword(@RequestParam("userId") int userId,@RequestParam("newPwd") String newPwd,@RequestParam("oldPwd") String oldPwd,@RequestParam("urLoginName") String urLoginName,@RequestParam("contactNo") String contactNo,@RequestParam("emailId") String emailId,HttpServletResponse response) throws IOException {
	 	 return userService.updateChangedUserPassword(userId,newPwd,oldPwd,urLoginName,contactNo,emailId);
	}
	
	 /*@RequestMapping(value = "/person/upload/personImage/{id}", method = RequestMethod.POST)
	 @Transactional(propagation = Propagation.REQUIRED)
	 public @ResponseBody void save(@PathVariable int id,@RequestParam MultipartFile files, HttpServletRequest request,HttpServletResponse response) throws IOException, SQLException
	 {
	 	 userService.uploadImageOnId(id, files);
	 	 Users com = iUsersDAO.find(id);
	 	 byte[] imsgeBytes = files.getBytes();
	 	 try {
			ldapBusinessModel.setImage(com.getUrLoginName(), imsgeBytes);
		} catch (LDAPException e) {
			e.printStackTrace();
		}
	 }*/
	
	@RequestMapping(value = "/users/create", method = { RequestMethod.GET,RequestMethod.POST })
	public @ResponseBody Object createUser(@ModelAttribute("users") Users users,HttpServletRequest req,HttpServletResponse response,ModelMap model,	BindingResult bindingResult,final Locale locale,SessionStatus sessionStatus) throws LDAPException, Exception{
		users.setThemeName("Default");
		return userService.saveUserObject("save",users,req,sessionStatus);
	}

	@RequestMapping(value = "/users/update", method = { RequestMethod.GET,RequestMethod.POST })
	public @ResponseBody Object updateUser(@ModelAttribute("users") Users users,ModelMap model,BindingResult bindingResult,HttpServletRequest req,HttpServletResponse response,final Locale locale,SessionStatus sessionStatus) throws LDAPException, Exception{
		users = userService.updateUserObject(users,req,sessionStatus);
		return readUser();
	}
	
	@RequestMapping(value = "/users/delete", method = RequestMethod.GET)
	public @ResponseBody Object deleteUser(@ModelAttribute("users") Users users,SessionStatus sessionStatus,HttpServletRequest req) throws Exception{
     logger.info("In destroy Users details method");
	    
	    JsonResponse errorResponse = new JsonResponse();
		
	    if( (Integer.parseInt(req.getParameter("userStatus"))==1)) {
			errorResponse.setStatus("AciveUserMasterDestroyError");
			return errorResponse;				
		}else{
			int urId = (Integer.parseInt(req.getParameter("urId")));
		try {			
			userService.deleteUser(urId);
		} catch (Exception e) {
			errorResponse.setStatus("CHILD");
			return errorResponse;
		}
		sessionStatus.setComplete();
		return users;
		}
	}
	
	
	@RequestMapping(value = "/users/userStatus/{urId}/{operation}", method = RequestMethod.POST)
	public void updateStatus(@PathVariable int urId,@PathVariable String operation,ModelMap model,HttpServletRequest request,HttpServletResponse response, final Locale locale) throws Exception {		
		userService.changeUserStatus(urId, operation, response, messageSource, locale);
	}
	
	@RequestMapping(value = "/users/filter/{feild}", method = RequestMethod.GET)
	public @ResponseBody Set<?> getUsersContentsForFilter(@PathVariable String feild) {
		List<String> attributeList = new ArrayList<String>();
		attributeList.add(feild);
		HashSet<Object> set = new HashSet<Object>(commonService.selectQuery("Users",attributeList, null));
		
		return set;
	}
	
	@RequestMapping(value = "/users/userStatusFilter", method = RequestMethod.GET)
	public @ResponseBody List<?> employeeStatusFilter(){	

		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>(); 
	   
		Map<String, Object> map=new HashMap<String, Object>() ;
		map.put("userStatus", "Active");
		map.put("statusId", 1);
		result.add(map); 
	   
		Map<String, Object> map2=new HashMap<String, Object>() ;
		map2.put("userStatus", "In Active");
		map2.put("statusId", 0);
		result.add(map2); 
		
	    return result;
	}
	
	@RequestMapping(value="/users/commonFilterForDepartmentNameUrl",method=RequestMethod.GET)
	public @ResponseBody Set<?> commonFilterForDepartmentNameUrl()	{ 
	   Set<String> result = new HashSet<String>();
	   for (Users users : genericDAO.findAll(Users.class))
	   {	
		  result.add(users.getDepartmentEntity().getDeptName());		    	
	   }
	   return result;
	}	
	
	@RequestMapping(value="/users/commonFilterForDesignationNameUrl",method=RequestMethod.GET)
	public @ResponseBody Set<?> commonFilterForDesignationNameUrl()	{ 
	   Set<String> result = new HashSet<String>();
	   for (Users users: genericDAO.findAll(Users.class))
	   {	
		  result.add(users.getDesignationEntity().getDnName());		    	
	   }
	   return result;
	}
	
	@RequestMapping(value="/users/commonFilterForCompanyUrl",method=RequestMethod.GET)
	public @ResponseBody Set<?> commonFilterForCompanyUrl()
	{ 
	   Set<String> result = new HashSet<String>();
	   for (Company company : genericDAO.findAll(Company.class))
	   {	
		  result.add(company.getCompanyName());		    	
	   }
	   return result;
	}
	
	@RequestMapping(value="/users/commonFilterForProjectUrl",method=RequestMethod.GET)
	public @ResponseBody Set<?> commonFilterForProjectUrl(){ 
	   Set<String> result = new HashSet<String>();
	   for (Project project : genericDAO.findAll(Project.class))
	   {	
		  result.add(project.getName());		    	
	   }
	   return result;
	}
	
	@RequestMapping(value="/users/commonFilterForOfficeUrl",method=RequestMethod.GET)
	public @ResponseBody Set<?> commonFilterForOfficeUrl(){ 
	   Set<String> result = new HashSet<String>();
	   for (ProjectHeirarchyEntity heirarchyEntity : genericDAO.findAll(ProjectHeirarchyEntity.class))
	   {	
		  result.add(heirarchyEntity.getText());		    	
	   }
	   return result;
	}
	
	/*@RequestMapping("/users/getUserimage/{urId}")
	public String getImage(Model userName, HttpServletRequest request,HttpServletResponse response, @PathVariable int urId)	throws LDAPException, Exception {

		response.setContentType("image/jpeg");
		return userService.getImage(urId,response);
	}*/
	
	@RequestMapping(value = "/users/upload/userImage", method = RequestMethod.POST)
	public @ResponseBody
	void save(@RequestParam MultipartFile files, HttpServletRequest request,HttpServletResponse response) throws IOException, SQLException, LDAPException {

		int urId = Integer.parseInt(request.getParameter("urId"));
		Users users=genericDAO.getById(Users.class, urId);
		userService.uploadImageOnId(urId, files);
		ldapBusinessModel.setImage(users.getUrLoginName(), files.getBytes());

	}
	
	@RequestMapping(value = "/users/getDesignation", method = RequestMethod.GET)
	public @ResponseBody List<?> getDesignation() 
	{
		return designationService.getAllActiveDesignations();

	}
	
	@RequestMapping(value = "/users/getRoles", method = RequestMethod.GET)
	public @ResponseBody List<?> getRolesBasedOnOfficeId(HttpServletRequest request) throws LDAPException 
	{
		return roleService.getRolesBasedOnOfficeId(request);

	}

	@RequestMapping(value = "/department/readDepartmentNamesForUniqueness", method = RequestMethod.GET)
	public @ResponseBody List<?> readDepartmentNames()
	{		
		return departmentService.readDepartmentNames();
	}

	//Contact Number Uniqueness
	@RequestMapping(value = "/users/readAllContactNumbers", method = RequestMethod.GET)
	public @ResponseBody List<?> readAllContactNumbers(HttpServletRequest req) {		
		return userService.readAllContactNumbers();
	}
	
	//Email Address Uniqueness
	@RequestMapping(value = "/users/readAllEmailAddress", method = RequestMethod.GET)
	public @ResponseBody List<?> readAllEmailAddress(HttpServletRequest req) {		
		return userService.readAllEmailAddress();
	}
	
	// ------------------------------------Role Use Case---------------------------------------------- //
	@RequestMapping("/roles")
	public String roleIndexPage(Model model, HttpServletRequest request) 
	{
		logger.info("Reading role index page");
		model.addAttribute("modulename","User Management");
		return "usermanagement/role";
	}

	//Read All Roles
	@RequestMapping(value = "/roles/readRoleUrl", method = RequestMethod.POST)
	public @ResponseBody List<?> getRoleMasterData()
	{
		return roleService.getAllRoles();
	}

	//Read RoleNames For RoleNameUniqueness
	@RequestMapping(value = "/roles/readRoleNamesForUniqueness/{dnId}", method = RequestMethod.GET)
	public @ResponseBody List<?> getAllRoleNames(@PathVariable int dnId) 
	{		
		return roleService.readRoleNames(dnId);
	}

	//Create Role
	@RequestMapping(value = "/roles/createRoleUrl", method = RequestMethod.GET)
	public @ResponseBody Object createRole(@ModelAttribute("role") Role role, ModelMap model, SessionStatus sessionStatus, final Locale locale,HttpServletRequest req) throws InterruptedException 
	{
		roleService.save(role);
		return roleService.getAllRoles();   	
	}

	//Update Role
	@RequestMapping(value = "/roles/updateRoleUrl", method = RequestMethod.GET)
	public @ResponseBody Object updateRole(@ModelAttribute("role") Role role,ModelMap model,SessionStatus sessionStatus,final Locale locale,HttpServletRequest req) throws InterruptedException, LDAPException 
	{
		role.setId(Integer.parseInt(req.getParameter("rlId")));
		role.setRlName(req.getParameter("rlName"));
		role.setRlDescription(req.getParameter("rlDescription"));
		roleService.update(role);
		return roleService.getAllRoles();   	
	}
	
	@RequestMapping(value = "/roles/destroyRoleUrl", method = RequestMethod.GET)
	public @ResponseBody Object destroyRoleUrl(@ModelAttribute("role") Role role,SessionStatus sessionStatus,HttpServletRequest req) throws Exception{
     logger.info("In destroy role details method");
	    
	    JsonResponse errorResponse = new JsonResponse();
		
	    if( req.getParameter("roleStatus").toString().equalsIgnoreCase("Active")) {
			errorResponse.setStatus("AciveRoleDestroyError");
			return errorResponse;				
		}else{
		try {
			int rlId = (Integer.parseInt(req.getParameter("rlId")));
			roleService.deleteRole(rlId);			
			
		} catch (Exception e) {
			errorResponse.setStatus("CHILD");
			return errorResponse;
		}
		sessionStatus.setComplete();
		return role;
		}
	}
	
	@RequestMapping(value = "/roles/getDesignationNamesUrl", method = RequestMethod.GET)
	public @ResponseBody List<?> getDesignationNames() 
	{
		return roleService.getDesignationNames();
	}

	//Activate & De-activating of role
	@RequestMapping(value = "/role/RoleStatus/{rlId}/{operation}", method = RequestMethod.POST)
	public String updtadeRoleStatus(@PathVariable int rlId,@PathVariable String operation,ModelMap model, HttpServletRequest request,HttpServletResponse response, final Locale locale) throws IOException{
		
		if(operation.equalsIgnoreCase("activate")){
			roleService.setRoleStatus(rlId, operation, response, messageSource, locale);
		}else{
			roleService.setRoleStatus(rlId, operation, response, messageSource, locale);			
		}	

		return null;
	}

	//Role Filter URL'S
	@RequestMapping(value = "/roles/getRoleNameFilter", method = RequestMethod.GET)
	public @ResponseBody Set<?> getRoleNameFilter()
	{ 
		Set<String> result = new HashSet<String>();
		for (Role role : roleService.findAllRoles())
		{	
			result.add(role.getRlName());		    	
		}
		return result;
	}

	@RequestMapping(value = "/roles/getRoleDescriptionFilter", method = RequestMethod.GET)
	public @ResponseBody Set<?> getRoleDescriptionFilter()
	{ 
		Set<String> result = new HashSet<String>();
		for (Role role : roleService.findAllRoles())
		{	
			result.add(role.getRlDescription());		    	
		}
		return result;
	}

	@RequestMapping(value = "/roles/getRoleStatusFilter", method = RequestMethod.GET)
	public @ResponseBody Set<?> getRoleStatusFilter()
	{ 
		Set<String> result = new HashSet<String>();
		for (Role role : roleService.findAllRoles())
		{	
			result.add(role.getRoleStatus());		    	
		}
		return result;
	}

	// ------------------------------------Department Use Case---------------------------------------------- //
	@RequestMapping("/department")
	public String departmentIndexPage(Model model, HttpServletRequest request) 
	{
		logger.info("Reading Department index page");
		model.addAttribute("modulename","User Management");
		return "usermanagement/department";
	}

	//Read All Department
	@RequestMapping(value = "/department/readDepartmentUrl", method = RequestMethod.POST)
	public @ResponseBody List<?> getDepartmentMasterData()
	{
		return departmentService.findAll();
	}

	//Create Department
	@RequestMapping(value = "/department/createDepartmentUrl", method = RequestMethod.GET)
	public @ResponseBody Object createDepartment(@ModelAttribute("department") Department department, ModelMap model, SessionStatus sessionStatus, final Locale locale,HttpServletRequest req) throws InterruptedException 
	{
		departmentService.save(department);
		return department;   	
	}

	//Update Department	
	@RequestMapping(value = "/department/updateDepartmentUrl", method = RequestMethod.GET)
	public @ResponseBody Object updateDepartment(@ModelAttribute("department") Department department, ModelMap model, SessionStatus sessionStatus, final Locale locale,HttpServletRequest req) throws InterruptedException 
	{
		departmentService.update(department);
		return departmentService.findAll();   	
	}
	
	@RequestMapping(value = "/department/deleteDepartmentUrl", method = RequestMethod.GET)
	public @ResponseBody Object deleteDepartmentUrl(@ModelAttribute("department") Department department,SessionStatus sessionStatus,HttpServletRequest req) throws Exception{
     logger.info("In destroy Department details method");
	    
	    JsonResponse errorResponse = new JsonResponse();
		
	    if( req.getParameter("departmentStatus").toString().equalsIgnoreCase("Active")) {
			errorResponse.setStatus("AciveDepartmentMasterDestroyError");
			return errorResponse;				
		}else{
		try {
			int deptId = (Integer.parseInt(req.getParameter("deptId")));
		
			departmentService.deleteDepartment(deptId);			
			
		} catch (Exception e) {
			errorResponse.setStatus("CHILD");
			return errorResponse;
		}
		sessionStatus.setComplete();
		return department;
		}
	}
	
	@RequestMapping(value = "/department/filter/{feild}", method = RequestMethod.GET)
	public @ResponseBody Set<?> getDepartmentsContentsForFilter(@PathVariable String feild) {
		List<String> attributeList = new ArrayList<String>();
		attributeList.add(feild);
		HashSet<Object> set = new HashSet<Object>(commonService.selectQuery("Department",attributeList, null));
		
		return set;
	}

	//Activate & De-activating of role
	@RequestMapping(value = "/department/DepartmentStatus/{deptId}/{operation}", method = RequestMethod.POST)
	public String updtadeDepartmentStatus(@PathVariable int deptId,@PathVariable String operation,ModelMap model, HttpServletRequest request,HttpServletResponse response, final Locale locale)
	{
		departmentService.setDepartmentStatus(deptId, operation, response, messageSource, locale);
		return null;
	}

	// ------------------------------------Designation Use Case---------------------------------------------- //
	@RequestMapping("/designation")
	public String designationIndexPage(Model model, HttpServletRequest request) 
	{
		logger.info("Reading Designation index page");
		model.addAttribute("modulename","User Management");
		return "usermanagement/designation";
	}

	//Read All Designations
	@RequestMapping(value = "/designation/readDesignationUrl", method = RequestMethod.POST)
	public @ResponseBody List<?> getDesignationMasterData()
	{
		return designationService.findAll();
	}

	//Read All Departments to dropdown
	@RequestMapping(value = "/department/getDepartmentNameFilter", method = RequestMethod.GET)
	public @ResponseBody List<?> departmentReadUrl() 
	{
		return departmentService.getDepartmentNameFilter();
	} 
	
	//Read DesigNameUniqueness
	@RequestMapping(value = "/designation/readDesignationNamesForUniqueness/{deptId}", method = RequestMethod.GET)
	public @ResponseBody List<?> readDesignationNamesForUniqueness(@PathVariable int deptId) 
	{		
		return designationService.readDesignationNamesForUniqueness(deptId);
	}

	//Create Designation
	@RequestMapping(value = "/designation/createDesignationUrl", method = RequestMethod.GET)
	public @ResponseBody Object createDesignation(@ModelAttribute("designation") Designation designation, ModelMap model, SessionStatus sessionStatus, final Locale locale,HttpServletRequest req) throws InterruptedException 
	{
		designationService.save(designation);
		return designationService.findAll();   	
	}

	//Update Designation
	@RequestMapping(value = "/designation/updateDesignationUrl", method = RequestMethod.GET)
	public @ResponseBody Object updateDesignation(@ModelAttribute("designation") Designation designation, ModelMap model, SessionStatus sessionStatus, final Locale locale,HttpServletRequest req) throws InterruptedException 
	{
		designationService.update(designation);
		return designationService.findAll();
	}
	
	@RequestMapping(value = "/designation/deleteDesignationUrl", method = RequestMethod.GET)
	public @ResponseBody Object deleteDesignationUrl(@ModelAttribute("designation") Designation designation,SessionStatus sessionStatus,HttpServletRequest req) throws Exception{
     logger.info("In destroy designation details method");
	    
	    JsonResponse errorResponse = new JsonResponse();
		
	    if( req.getParameter("designationStatus").toString().equalsIgnoreCase("Active")) {
			errorResponse.setStatus("AciveDesignationMasterDestroyError");
			return errorResponse;				
		}else{
		try {
			int dnId = (Integer.parseInt(req.getParameter("dnId")));
			designationService.deleteDesignation(dnId);			
			
		} catch (Exception e) {
			errorResponse.setStatus("CHILD");
			return errorResponse;
		}
		sessionStatus.setComplete();
		return designation;
		}
	}

	//Activate & De-activating of department
	@RequestMapping(value = "/designation/designationStatus/{dnId}/{operation}", method = RequestMethod.POST)
	public String updtadeDesignationStatus(@PathVariable int dnId,@PathVariable String operation,ModelMap model, HttpServletRequest request,HttpServletResponse response, final Locale locale)
	{
		designationService.setDesignationStatus(dnId, operation, response, messageSource, locale);
		return null;
	}

	//Designation Filter URL'S
		@RequestMapping(value = "/designation/getDesignationNameFilter", method = RequestMethod.GET)
		public @ResponseBody Set<?> getDesignationNameFilter()
		{ 						
			Set<String> result = new HashSet<String>();		
			for(Iterator<?> i = designationService.findAllDesignation().iterator(); i.hasNext();){
				final Object[] values = (Object[]) i.next();
				result.add((String)values[1]);		
			}
			return result;
		}

		@RequestMapping(value = "/designation/getDesignationDescriptionFilter", method = RequestMethod.GET)
		public @ResponseBody Set<?> getDesignationDescriptionFilter()
		{ 
			Set<String> result = new HashSet<String>();		
			for(Iterator<?> i = designationService.findAllDesignation().iterator(); i.hasNext();){
				final Object[] values = (Object[]) i.next();
				result.add((String)values[2]);		
			}
			return result;
		}

		@RequestMapping(value = "/designation/getDesignationStatusFilter", method = RequestMethod.GET)
		public @ResponseBody Set<?> getDesignationStatusFilter()
		{ 
			Set<String> result = new HashSet<String>();		
			for(Iterator<?> i = designationService.findAllDesignation().iterator(); i.hasNext();){
				final Object[] values = (Object[]) i.next();
				result.add((String)values[3]);		
			}
			return result;
		}
		

		@RequestMapping(value = "/designation/getDepartmentForDesignationFilter", method = RequestMethod.GET)
		public @ResponseBody Set<?> getDepartmentForDesignationFilter()
		{ 
			Set<String> result = new HashSet<String>();		
			for(Iterator<?> i = designationService.findAllDesignation().iterator(); i.hasNext();){
				final Object[] values = (Object[]) i.next();
				result.add((String)values[4]);		
			}
			return result;
		}
	
	@RequestMapping(value = "/users/readAllLoginNames", method = RequestMethod.GET)
	public @ResponseBody List<?> getAllTransactionNames(HttpServletRequest req) {		
		return userService.readAllLoginNames();
	}
	
	//-------------------------------------------------------------
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/projecttree/users/json/read", method = RequestMethod.GET)
	public @ResponseBody List<ProjectHeirarchyEntity> readTree(HttpServletRequest req) {
		
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
	
	
	@SuppressWarnings({ "rawtypes", "unchecked", "serial" })
	public List<Map<String, Object>> common(int id,HttpServletRequest request){
		
		
		HttpSession session= request.getSession(false);
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>(); 
		String officeIds = id+",";
		Map criterias = new HashMap();
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
		}
		List<ProjectHeirarchyEntity> list =  genericDAO.executeSimpleQuery("select o from ProjectHeirarchyEntity o where (o.type='Office' OR o.type='Sub Division Office') and o.id IN("+officeIds.substring(0,officeIds.length()-1)+")  order by o.text");


		for (final ProjectHeirarchyEntity entity : list) {
			result.add(new HashMap<String, Object>() {{
				put("text", entity.getText());
				put("id", entity.getId());
			}});
		}
		return result;

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
		List<ProjectHeirarchyEntity> list =  genericDAO.executeSimpleQuery("select o from ProjectHeirarchyEntity o where o.type='Office' and o.id IN("+officeIds.substring(0,officeIds.length()-1)+") and o.project.id="+projectId+" order by o.text");


		for (final ProjectHeirarchyEntity entity : list) {
			result.add(new HashMap<String, Object>() {{
				put("text", entity.getText());
				put("id", entity.getId());
			}});
		}
		return result;

	}	
	
	
	
	
	
	@RequestMapping(value = "/users/getonlyoffices/{officeId}",method={RequestMethod.POST,RequestMethod.GET}) 
	public @ResponseBody List<Map<String, Object>> getOnlyOffice(@PathVariable int tId,HttpServletRequest request) {
		HttpSession httpSession =  request.getSession(true);
		int	id = (int)httpSession.getAttribute("officeId");
		return common(id,request);
	}	
	public Integer getOfficeIds(int id){
		Integer phId =  heirarchyDAO.getParentIdOnId(id);
		if(phId!=null){
			return phId;
		}
		else
			return null;
	}	
	
	
	/**
	 * @author Raju
	 * 
	 * To Change The Status To Activate All & Vise-versa
	 * 
	 */
	
	@RequestMapping(value = "/users/StatusChangeAll/{operation}", method = RequestMethod.POST)
	public @ResponseBody String updateStatusToActivateOrDeactivate(@PathVariable String operation,ModelMap model,HttpServletRequest request,HttpServletResponse response, final Locale locale) throws Exception {		
		if(operation.trim().equalsIgnoreCase("Activate"))
		{
			logger.info("Inside Activate Method");
			List<Integer> urIdList = userService.getAllInActiveUsres(0);
			for (Integer urId : urIdList) {
				userService.changeUserStatus(urId, operation, response, messageSource, locale);
			}
			return "Activated";
		}
		else
		{
			logger.info("Inside De-Activate Method");
			List<Integer> urIdList = userService.getAllInActiveUsres(1);
			for (Integer urId : urIdList) {
				userService.changeUserStatus(urId, operation, response, messageSource, locale);
			}
			return "De-Activated";
		}
	}
}
