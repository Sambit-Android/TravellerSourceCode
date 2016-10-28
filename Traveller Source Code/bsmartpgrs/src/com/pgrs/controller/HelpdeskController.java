package com.pgrs.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.Principal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import com.pgrs.core.dao.MasterGenericDAO;
import com.pgrs.dao.HelpDeskTicketDAO;
import com.pgrs.entity.HelpDeskTicketEntity;
import com.pgrs.entity.ProjectHeirarchyEntity;
import com.pgrs.entity.TicketCategoryEntity;
import com.pgrs.entity.TicketDocumentsEntity;
import com.pgrs.entity.TicketEscalationEntity;
import com.pgrs.entity.TicketSubCategoryEntity;
import com.pgrs.service.CommonService;
import com.pgrs.service.HelpDeskTicketService;
import com.pgrs.service.TicketCategoryService;
import com.pgrs.service.TicketDocumentService;
import com.pgrs.service.TicketEscalationService;
import com.pgrs.service.TicketSubCategoryService;
import com.pgrs.util.DateTimeCalender;
import com.pgrs.util.JsonResponse;
import com.pgrs.util.SessionData;

@Controller
public class HelpdeskController {

	private static final Log logger = LogFactory.getLog(HelpdeskController.class);

	DateTimeCalender dateTimeCalender = new DateTimeCalender();

	@Autowired
	private TicketSubCategoryService ticketSubCategoryService;

	@Autowired
	private TicketCategoryService ticketCategoryService;

	@Autowired
	private MasterGenericDAO genericDao;

	@Autowired
	private CommonService commonService;

	@Autowired
	private HelpDeskTicketService helpDeskTicketService;

	@Autowired
	private TicketEscalationService ticketEscalationService;

	@Autowired
	private HelpDeskTicketDAO helpDeskTicketDAO;

	@Autowired
	private TicketDocumentService ticketDocumentService;
	
	@RequestMapping(value = "/createTicketMobile", method = {RequestMethod.GET, RequestMethod.POST })
	public String createTicketMobile(@RequestBody String params,HttpServletRequest request, HttpServletResponse response,SessionStatus sessionStatus,final Locale locale)  {
		
		try{
			
			HelpDeskTicketEntity helpdeskBean = new HelpDeskTicketEntity();		
			JSONArray arr = new JSONArray(params);
			JSONObject Asst_json_obj = arr.getJSONObject(0);
			
			helpdeskBean.setSiteCode(Asst_json_obj.getString("sitecode"));
			helpdeskBean.setUserName(Asst_json_obj.getString("officerName"));
			helpdeskBean.setConsumerName(Asst_json_obj.getString("officerName"));
			helpdeskBean.setConsumerMobileNo(Asst_json_obj.getLong("mobileNo"));
			helpdeskBean.setConsumerEmailId(Asst_json_obj.getString("emailId"));
			helpdeskBean.setCategoryId(Asst_json_obj.getInt("category"));
			helpdeskBean.setSubCategoryId(Asst_json_obj.getInt("subcategory"));
			helpdeskBean.setDocketSummary(Asst_json_obj.getString("description"));
			
			helpdeskBean.setDocketSource("Mobile");			
			helpdeskBean.setUserName(helpdeskBean.getConsumerName());
			String result = helpDeskTicketService.saveTicketInfo(helpdeskBean);		
			return result+"--------"+helpdeskBean.getDocketNumber();

		}catch(Exception e){
			return "novalue";
		}
		
		
	}

	@RequestMapping(value = "/createTicketTrmMobile", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody String createTicketTrmMobile(@RequestBody String params) throws IOException, Exception {
		
		String sitecode , officername , email , desc;
		long mobile;
		int subcat;
		int cat;
		
		   JSONArray arr = new JSONArray(params);
		   JSONObject Asst_json_obj = arr.getJSONObject(0);
		   sitecode = Asst_json_obj.getString("sitecode");
		   officername = Asst_json_obj.getString("officerName");
		   mobile = Long.parseLong(Asst_json_obj.getString("mobileNo"));
		   email = Asst_json_obj.getString("emailId");
		   desc = Asst_json_obj.getString("description");
		   cat = Asst_json_obj.getInt("category");
		   subcat = Asst_json_obj.getInt("subcategory");
		   
		   System.out.println("====================================DATA   "+Asst_json_obj.toString());
		
		if (sitecode != null && !sitecode.equals("")) {

			HelpDeskTicketEntity ticketEntity = new HelpDeskTicketEntity();

			ticketEntity.setSiteCode(sitecode);
			ticketEntity.setUserName(officername);
			ticketEntity.setConsumerName(officername);
			ticketEntity.setConsumerMobileNo(mobile);
			ticketEntity.setConsumerEmailId(email);
			ticketEntity.setDocketSummary(desc);
			ticketEntity.setCategoryId(cat);
			ticketEntity.setSubCategoryId(subcat);

			ticketEntity.setDocketSource("Mobile");
			ticketEntity.setUserName(ticketEntity.getConsumerName());
			helpDeskTicketService.saveTicketInfo(ticketEntity);
			return "Ticket No. is " + ticketEntity.getDocketNumber();
		} else {
			return "Ticket not created. Please Try Again.";
		}
	}

	@RequestMapping(value = "/createTicketTrm", method = { RequestMethod.GET, RequestMethod.POST })
	public String createTicketTrm(@ModelAttribute("helpdeskBean") HelpDeskTicketEntity helpdeskBean,
			BindingResult bindingResult, ModelMap model, @RequestParam("ticketDoc") MultipartFile file,
			HttpServletRequest req, SessionStatus sessionStatus, final Locale locale) {

		HttpSession httpSession = req.getSession();

		try {
			helpdeskBean.setDocketSource("Web");
			helpdeskBean.setUserName(helpdeskBean.getConsumerName());
			helpDeskTicketService.saveTicketInfo(helpdeskBean);
			httpSession.setAttribute("helpDeskSaveResult",
					"Ticket created Successfully and Ticket No. is " + helpdeskBean.getDocketNumber());
			if (file.getSize() > 0 && file != null) {
				TicketDocumentsEntity document = new TicketDocumentsEntity();

				document.setDocname(file.getOriginalFilename());
				document.setDocketNo(helpdeskBean.getDocketNumber());
				document.setDocument(file.getBytes());

				ticketDocumentService.save(document);
			}
		} catch (Exception e) {
			e.printStackTrace();
			httpSession.setAttribute("helpDeskSaveResult", "Ticket Not Created");
		}

		return "redirect:/registerTicket?siteCode=4444&officerName=Rav&mobileNo=525522&emailId=45";
	}

	// Feedback comments Use Case
	@RequestMapping(value = "/feedbackComments", method = { RequestMethod.POST, RequestMethod.GET })
	public String feedbackComments(Model model, HttpServletRequest request, final Locale locale) {

		logger.info("Inside Pgrs feed back comments method");
		model.addAttribute("modulename", "Feedback Comments");

		List<?> resolvedComplaints = helpDeskTicketService.getAllResolvedComplaints();

		model.addAttribute("helpdeskEntity", new HelpDeskTicketEntity());
		model.addAttribute("resolvedComplaints", resolvedComplaints);

		return "pgrs/feedbackComments";
	}

	// Register Complaints Use Case
	@RequestMapping(value = "/registerComplaint", method = { RequestMethod.POST, RequestMethod.GET })
	public String registerComplaint(Model model, HttpServletRequest request, final Locale locale) {

		logger.info("Inside Pgrs register complaints method");
		model.addAttribute("modulename", "Register Complaint");

		HttpSession httpSession = request.getSession(false);
		int projectId = (Integer) httpSession.getAttribute("projectId");

		List<?> categoryList = ticketCategoryService.getAllActiveCategories();
		List<?> divisionsList = ticketCategoryService.getAllDivisions(projectId);

		model.addAttribute("helpdeskBean", new HelpDeskTicketEntity());
		model.addAttribute("divisionsList", divisionsList);
		model.addAttribute("categoryList", categoryList);

		return "pgrs/registerComplaint";
	}

	// Escalation Analysis Use Case
	@RequestMapping(value = "/escalationAnalysis", method = { RequestMethod.POST, RequestMethod.GET })
	public String escalationAnalysis(Model model, HttpServletRequest request, final Locale locale) {

		logger.info("Inside Pgrs escalation analysis method");
		model.addAttribute("modulename", "Escalation Analysis");

		HttpSession httpSession = request.getSession(false);
		int projectId = (Integer) httpSession.getAttribute("projectId");
		List<?> circleList = ticketCategoryService.getAllCircles(projectId);
		model.addAttribute("helpdeskBean", new HelpDeskTicketEntity());
		model.addAttribute("circleList", circleList);
		return "pgrs/escalationAnalysis";
	}

	@RequestMapping(value = "/searchEscalation", method = { RequestMethod.GET, RequestMethod.POST })
	public String serachEscalationInfo(@ModelAttribute("helpdeskBean") HelpDeskTicketEntity helpdeskBean,
			BindingResult bindingResult, ModelMap model, HttpServletRequest req, SessionStatus sessionStatus,
			final Locale locale) {
		logger.info("Inside Pgrs view complaints method");
		model.addAttribute("modulename", "Search Escalation");
		String sitecode = "";
		String filter = "";
		if (helpdeskBean.getSiteCode() != null && !helpdeskBean.getSiteCode().equalsIgnoreCase("0")) {
			ProjectHeirarchyEntity projectHeirarchyEntity = genericDao
					.getByUniqueAttribute(ProjectHeirarchyEntity.class, "siteCode", helpdeskBean.getSiteCode());
			filter += " Circle: " + projectHeirarchyEntity.getParent().getParent().getParent().getText();
			filter += " Division: " + projectHeirarchyEntity.getParent().getText();
			filter += " Sub Division :" + projectHeirarchyEntity.getText();
			sitecode = helpdeskBean.getSiteCode();
		} else if (helpdeskBean.getDivisionSiteCode() != 0 && helpdeskBean.getDivisionSiteCode() != 1) {
			ProjectHeirarchyEntity entity = genericDao.getById(ProjectHeirarchyEntity.class,
					helpdeskBean.getDivisionSiteCode());
			sitecode = entity.getSiteCode();
			filter += " Circle: " + entity.getParent().getText();
			filter += " Division: " + entity.getText();
		} else if (helpdeskBean.getCircleSiteCode() != 0) {
			ProjectHeirarchyEntity entity = genericDao.getById(ProjectHeirarchyEntity.class,
					helpdeskBean.getCircleSiteCode());
			sitecode = entity.getSiteCode();
			if (entity.getText().equalsIgnoreCase("CESC")) {
				filter += " Circle: All";
			} else {
				filter += " Circle: " + entity.getText();
			}
		}
		if (helpdeskBean.getDivisionSiteCode() == 0 || helpdeskBean.getDivisionSiteCode() == 1) {
			filter += " Division : All";
		}
		if (helpdeskBean.getSiteCode() == null || helpdeskBean.getSiteCode().equalsIgnoreCase("0")) {
			filter += " Sub Division : All";
		}
		Date date1 = null;
		Date date2 = null;
		if (helpdeskBean.getFromdate() != null && helpdeskBean.getTodate() != null) {
			date1 = helpdeskBean.getFromdate();
			date2 = helpdeskBean.getTodate();
			int temp = date1.compareTo(date2);
			if (temp > 0) {
				date1 = helpdeskBean.getTodate();
				date2 = helpdeskBean.getFromdate();
			}
			List<?> escalation = helpDeskTicketService.searchEsclation(sitecode, date1, date2);
			model.addAttribute("escalation", escalation);
		} else if (!sitecode.equalsIgnoreCase("")) {
			List<?> escalation = helpDeskTicketService.searchEsclation(sitecode);
			model.addAttribute("escalation", escalation);
		}
		HttpSession httpSession = req.getSession(false);
		int projectId = (Integer) httpSession.getAttribute("projectId");
		List<?> circleList = ticketCategoryService.getAllCircles(projectId);
		model.addAttribute("helpdeskBean", new HelpDeskTicketEntity());
		model.addAttribute("circleList", circleList);
		model.addAttribute("sitecode", sitecode);
		model.addAttribute("CirDivSub", filter);
		if (date1 != null && date2 != null) {
			filter = "From Date : " + new SimpleDateFormat("dd/MM/yyyy").format(date1) + " To Date:"
					+ new SimpleDateFormat("dd/MM/yyyy").format(date2) + " " + filter;
			model.addAttribute("fromdate", new SimpleDateFormat("dd/MM/yyyy").format(date1));
			model.addAttribute("todate", new SimpleDateFormat("dd/MM/yyyy").format(date2));
		} else {
			model.addAttribute("fromdate", "empty");
			model.addAttribute("todate", "empty");
		}
		model.addAttribute("filter", filter);
		return "pgrs/escalationAnalysis";

	}

	// View Complaints Use Case
	@RequestMapping(value = "/viewComplaints", method = { RequestMethod.POST, RequestMethod.GET })
	public String viewComplaints(Model model, HttpServletRequest request, final Locale locale) {

		logger.info("Inside Pgrs view complaints method");
		model.addAttribute("modulename", "Search Complaints");

		HttpSession httpSession = request.getSession(false);
		int projectId = (Integer) httpSession.getAttribute("projectId");
		List<?> divisionsList = ticketCategoryService.getAllDivisions(projectId);
		List<?> circleList = ticketCategoryService.getAllCircles(projectId);
		List<?> categoryList = ticketCategoryService.getAllActiveCategories();
		// List<?> viewComplaints=helpDeskTicketService.getTodaysComplaints(new
		// Date());
		model.addAttribute("helpdeskBean", new HelpDeskTicketEntity());
		model.addAttribute("divisionsList", divisionsList);
		model.addAttribute("circleList", circleList);
		model.addAttribute("categoryList", categoryList);
		// model.addAttribute("search",viewComplaints);
		return "pgrs/viewComplaints";
	}

	// Reports Use Case
	@RequestMapping(value = "/pgrsReports", method = { RequestMethod.POST, RequestMethod.GET })
	public String pgrsReports(Model model, HttpServletRequest request, final Locale locale) {

		logger.info("Inside Pgrs Reports method");
		model.addAttribute("modulename", "Reports");

		return "pgrs/pgrsReports";
	}

	// Support Team Use Case
	@RequestMapping(value = "/pgrsSupportTeam", method = { RequestMethod.POST, RequestMethod.GET })
	public String pgrsSupportTeam(Model model, HttpServletRequest request, final Locale locale) {

		logger.info("Inside Pgrs support team method");
		List<?> getSupprtDetails = helpDeskTicketService.getSupportTeamDetails();
		model.addAttribute("supprtTeam", getSupprtDetails);
		model.addAttribute("modulename", "Support Team");

		return "pgrs/pgrsSupportTeam";
	}

	// Location Analysis Use Case
	@RequestMapping(value = "/locationWiseAnalysis", method = { RequestMethod.POST, RequestMethod.GET })
	public String locationWiseAnalysis(Model model, HttpServletRequest request, final Locale locale) {

		logger.info("Inside Pgrs location wise analysis method");
		model.addAttribute("modulename", "Location Wise Analysis");
		List<?> categoryList = ticketCategoryService.getAllActiveCategories();
		model.addAttribute("categoryList", categoryList);
		return "pgrs/locationWiseAnalysis";
	}

	@RequestMapping(value = "/location/locationWiseAnalysis", method = RequestMethod.POST)
	public @ResponseBody List<?> getDetailOnId(@RequestParam("id") int id) {
		Set<String> projectIds = new HashSet<String>();
		ProjectHeirarchyEntity projectHeirarchyEntity = genericDao.getById(ProjectHeirarchyEntity.class, id);
		if (!(projectHeirarchyEntity.getType().equalsIgnoreCase("Zone")
				|| projectHeirarchyEntity.getType().equalsIgnoreCase("Projects"))) {
			projectIds.add(projectHeirarchyEntity.getSiteCode());
		}
		fetchChildren1(genericDao.getById(ProjectHeirarchyEntity.class, id), projectIds);
		return helpDeskTicketService.getlocationWiseDetails(projectIds);
	}

	// Category Use Case
	@RequestMapping(value = "/complaintCategory", method = { RequestMethod.POST, RequestMethod.GET })
	public String complaintCategory(Model model, HttpServletRequest request, final Locale locale) {

		logger.info("Inside complaintCategory method");
		model.addAttribute("modulename", "Category");

		return "pgrs/complaintCategory";
	}

	@RequestMapping(value = "/helpDesk/readCategoryUrl", method = RequestMethod.POST)
	public @ResponseBody List<?> readCategory() {
		return ticketCategoryService.findAllCategories();
	}

	@RequestMapping(value = "/helpDesk/createCategoryUrl", method = RequestMethod.GET)
	public @ResponseBody Object createCategory(@ModelAttribute("categoryEntity") TicketCategoryEntity categoryEntity,
			ModelMap model, SessionStatus sessionStatus, final Locale locale, HttpServletRequest req) {
		ticketCategoryService.save(categoryEntity);
		return ticketCategoryService.findAllCategories();
	}

	@RequestMapping(value = "/helpDesk/destroyCategoryUrl", method = RequestMethod.GET)
	public @ResponseBody Object destroyCategory(@ModelAttribute("categoryEntity") TicketCategoryEntity categoryEntity,
			SessionStatus sessionStatus, HttpServletRequest req) throws Exception {

		logger.info("In destroy category method");
		JsonResponse errorResponse = new JsonResponse();
		if (categoryEntity.getStatus() == 1) {
			errorResponse.setStatus("AciveCategoryDestroyError");
			return errorResponse;
		} else {
			try {
				ticketCategoryService.delete(categoryEntity.getCategoryId());
			} catch (Exception e) {
				errorResponse.setStatus("CHILD");
				return errorResponse;
			}
			sessionStatus.setComplete();
			return ticketCategoryService.findAllCategories();
		}
	}

	@RequestMapping(value = "/helpDesk/updateCategoryUrl", method = RequestMethod.GET)
	public @ResponseBody Object updateCategory(@ModelAttribute("categoryEntity") TicketCategoryEntity categoryEntity,
			ModelMap model, SessionStatus sessionStatus, final Locale locale, HttpServletRequest req) {
		ticketCategoryService.update(categoryEntity);
		return ticketCategoryService.findAllCategories();
	}

	@RequestMapping(value = "/category/filter/{feild}", method = RequestMethod.GET)
	public @ResponseBody Set<?> getCategoryContentsForFilter(@PathVariable String feild) {
		List<String> attributeList = new ArrayList<String>();
		attributeList.add(feild);
		HashSet<Object> set = new HashSet<Object>(
				commonService.selectQuery("TicketCategoryEntity", attributeList, null));

		return set;
	}

	@RequestMapping(value = "/helpDesk/categoryStatusFilter", method = RequestMethod.GET)
	public @ResponseBody List<?> categoryStatusFilter() {

		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", "Active");
		map.put("statusId", 1);
		result.add(map);

		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("status", "In Active");
		map2.put("statusId", 0);
		result.add(map2);

		return result;
	}

	@RequestMapping(value = "/helpDesk/readCategoryNamesForUniqueness", method = RequestMethod.GET)
	public @ResponseBody List<String> getAllCategoryNames() {
		return ticketCategoryService.getAllCategoryNames();
	}

	@RequestMapping(value = "/helpDesk/categoryStatus/{categoryId}/{operation}", method = RequestMethod.POST)
	public void categoryStatus(@PathVariable int categoryId, @PathVariable String operation,
			HttpServletResponse response) {
		if (operation.equalsIgnoreCase("activate")) {
			ticketCategoryService.setCategoryStatus(categoryId, operation, response);
		} else {
			ticketCategoryService.setCategoryStatus(categoryId, operation, response);
		}
	}

	// Sub Category Use Case
	@RequestMapping(value = "/complaintSubCategory", method = { RequestMethod.POST, RequestMethod.GET })
	public String complaintSubCategory(Model model, HttpServletRequest request, final Locale locale) {

		logger.info("Inside complaintSubCategory method");
		model.addAttribute("modulename", "Sub Category");

		return "pgrs/complaintSubCategory";
	}

	@RequestMapping(value = "/helpDesk/readSubCategoryUrl", method = RequestMethod.POST)
	public @ResponseBody List<?> readSubCategory() {
		return ticketSubCategoryService.findAllSubCategories();
	}

	@RequestMapping(value = "/helpDesk/createSubCategoryUrl", method = RequestMethod.GET)
	public @ResponseBody Object createSubCategory(
			@ModelAttribute("subCategoryEntity") TicketSubCategoryEntity subCategoryEntity, ModelMap model,
			SessionStatus sessionStatus, final Locale locale, HttpServletRequest req) {
		ticketSubCategoryService.save(subCategoryEntity);
		return ticketSubCategoryService.findAllSubCategories();
	}

	@RequestMapping(value = "/helpDesk/destroySubCategoryUrl", method = RequestMethod.GET)
	public @ResponseBody Object destroySubCategory(
			@ModelAttribute("subCategoryEntity") TicketSubCategoryEntity subCategoryEntity, SessionStatus sessionStatus,
			HttpServletRequest req) throws Exception {

		logger.info("In destroy category method");
		JsonResponse errorResponse = new JsonResponse();
		if (subCategoryEntity.getStatus() == 1) {
			errorResponse.setStatus("AciveSubCategoryDestroyError");
			return errorResponse;
		} else {
			try {
				ticketSubCategoryService.delete(subCategoryEntity.getSubCategoryId());
			} catch (Exception e) {
				errorResponse.setStatus("CHILD");
				return errorResponse;
			}
			sessionStatus.setComplete();
			return ticketSubCategoryService.findAllSubCategories();
		}
	}

	@RequestMapping(value = "/helpDesk/updateSubCategoryUrl", method = RequestMethod.GET)
	public @ResponseBody Object updateSubCategory(
			@ModelAttribute("subCategoryEntity") TicketSubCategoryEntity subCategoryEntity, ModelMap model,
			SessionStatus sessionStatus, final Locale locale, HttpServletRequest req) {
		ticketSubCategoryService.update(subCategoryEntity);
		return ticketSubCategoryService.findAllSubCategories();
	}

	@RequestMapping(value = "/subCategory/filter/{feild}", method = RequestMethod.GET)
	public @ResponseBody Set<?> getSubCategoryContentsForFilter(@PathVariable String feild) {
		List<String> attributeList = new ArrayList<String>();
		attributeList.add(feild);
		HashSet<Object> set = new HashSet<Object>(
				commonService.selectQuery("TicketSubCategoryEntity", attributeList, null));

		return set;
	}

	@RequestMapping(value = "/helpDesk/categorySubStatusFilter", method = RequestMethod.GET)
	public @ResponseBody List<?> categorySubStatusFilter() {

		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", "Active");
		map.put("statusId", 1);
		result.add(map);

		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("status", "In Active");
		map2.put("statusId", 0);
		result.add(map2);

		return result;
	}

	@RequestMapping(value = "/helpDesk/getAllActiveCategoryNamesUrl", method = RequestMethod.GET)
	public @ResponseBody List<?> getAllActiveCategoryNamesUrl() {
		return ticketCategoryService.getAllActiveCategories();
	}

	@RequestMapping(value = "/helpDesk/readSubCategoryNamesForUniqueness", method = RequestMethod.GET)
	public @ResponseBody List<String> getAllSubCategoryNames() {
		return ticketSubCategoryService.getAllSubCategoryNames();
	}

	@RequestMapping(value = "/helpDesk/subCategoryStatus/{subCategoryId}/{operation}", method = RequestMethod.POST)
	public void subCategoryStatus(@PathVariable int subCategoryId, @PathVariable String operation,
			HttpServletResponse response) {
		if (operation.equalsIgnoreCase("activate")) {
			ticketSubCategoryService.setSubCategoryStatus(subCategoryId, operation, response);
		} else {
			ticketSubCategoryService.setSubCategoryStatus(subCategoryId, operation, response);
		}
	}

	// Escalation level Use Case
	@RequestMapping(value = "/helpDesk/readLevelUrl/{subCategoryId}", method = RequestMethod.POST)
	public @ResponseBody List<?> readEscalationLevel(@PathVariable int subCategoryId) {
		return ticketEscalationService.findAllEscalationLevels(subCategoryId);
	}

	@RequestMapping(value = "/helpDesk/createLevelUrl/{subCategoryId}", method = RequestMethod.GET)
	public @ResponseBody Object createEscalationLevel(
			@ModelAttribute("escalationEntity") TicketEscalationEntity escalationEntity,
			@PathVariable int subCategoryId, ModelMap model, SessionStatus sessionStatus, final Locale locale,
			HttpServletRequest req) {
		escalationEntity.setSubCategoryId(subCategoryId);
		ticketEscalationService.save(escalationEntity);
		return ticketEscalationService.findAllEscalationLevels(subCategoryId);
	}

	@RequestMapping(value = "/helpDesk/updateLevelUrl/{subCategoryId}", method = RequestMethod.GET)
	public @ResponseBody Object updateEscalationLevel(
			@ModelAttribute("escalationEntity") TicketEscalationEntity escalationEntity,
			@PathVariable int subCategoryId, ModelMap model, SessionStatus sessionStatus, final Locale locale,
			HttpServletRequest req) {
		escalationEntity.setSubCategoryId(subCategoryId);
		ticketEscalationService.update(escalationEntity);
		return ticketEscalationService.findAllEscalationLevels(subCategoryId);
	}

	@RequestMapping(value = "/helpDesk/destroyLevelUrl/{subCategoryId}", method = RequestMethod.GET)
	public @ResponseBody Object destroyEscalationLevel(
			@ModelAttribute("escalationEntity") TicketEscalationEntity escalationEntity,
			@PathVariable int subCategoryId, SessionStatus sessionStatus, HttpServletRequest req) throws Exception {

		logger.info("In destroy escalation level method");
		JsonResponse errorResponse = new JsonResponse();
		try {
			ticketEscalationService.delete(escalationEntity.getEscId());
		} catch (Exception e) {
			errorResponse.setStatus("CHILD");
			return errorResponse;
		}
		return ticketEscalationService.findAllEscalationLevels(subCategoryId);
	}

	// Register Ticket Use Case
	@RequestMapping(value = "/helpDesk/getAllSubCategories/{categoryId}", method = { RequestMethod.POST,
			RequestMethod.GET })
	public @ResponseBody List<?> getAllActiveSubCategories(@PathVariable int categoryId) {
		List<?> subCategoryList = ticketSubCategoryService.getAllActiveSubCategories(categoryId);
		return subCategoryList;
	}

	@RequestMapping(value = "/helpDesk/getAllSubDivisions/{divisionId}", method = { RequestMethod.POST,
			RequestMethod.GET })
	public @ResponseBody List<?> getAllSubDivisions(@PathVariable int divisionId) {

		return ticketSubCategoryService.getAllSubDivisions(divisionId);
	}

	@RequestMapping(value = "/helpDesk/getAllSections/{subDivisionId}", method = { RequestMethod.POST,
			RequestMethod.GET })
	public @ResponseBody List<?> getAllSections(@PathVariable int subDivisionId) {

		return ticketSubCategoryService.getAllSections(subDivisionId);
	}

	@RequestMapping(value = "/helpDesk/getAllSubDivisions1/{divisionId}", method = { RequestMethod.POST,
			RequestMethod.GET })
	public @ResponseBody List<?> getAllSubDivisions1(@PathVariable int divisionId) {

		Set<Integer> divisionIds = new HashSet<Integer>();
		divisionIds.add(divisionId);
		fetchChildren(genericDao.getById(ProjectHeirarchyEntity.class, divisionId), divisionIds);

		List<?> subCategoryList = ticketSubCategoryService.getAllSubDivisions1(divisionIds);
		return subCategoryList;
	}

	@RequestMapping(value = "/helpDesk/getAllSection/{divisionId}", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody List<?> getAllSection(@PathVariable int divisionId) {

		Set<Integer> subDivisionIds = new HashSet<Integer>();
		subDivisionIds.add(divisionId);
		fetchChildren(genericDao.getById(ProjectHeirarchyEntity.class, divisionId), subDivisionIds);

		List<?> subCategoryList = ticketSubCategoryService.getAllSection(subDivisionIds);
		return subCategoryList;
	}

	@RequestMapping(value = "/helpDesk/getAllDivisions/{circleId}", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody List<?> getAllDivisions(@PathVariable int circleId) {

		List<?> subCategoryList = ticketSubCategoryService.getAllDivisions(circleId);
		return subCategoryList;
	}

	private ProjectHeirarchyEntity fetchChildren(ProjectHeirarchyEntity parent, Set<Integer> divisionIds) {
		Hibernate.initialize(parent.getChilds());
		for (ProjectHeirarchyEntity child : parent.getChilds()) {
			fetchChildren(child, divisionIds);
			divisionIds.add(child.getId());
		}
		return parent;
	}

	private ProjectHeirarchyEntity fetchChildren1(ProjectHeirarchyEntity parent, Set<String> divisionIds) {
		Hibernate.initialize(parent.getChilds());
		for (ProjectHeirarchyEntity child : parent.getChilds()) {
			fetchChildren1(child, divisionIds);
			divisionIds.add(child.getSiteCode());
		}
		return parent;
	}

	@RequestMapping(value = "/createTicket", method = { RequestMethod.GET, RequestMethod.POST })
	public String saveTicketInfo(@ModelAttribute("helpdeskBean") HelpDeskTicketEntity helpdeskBean,
			BindingResult bindingResult, ModelMap model, @RequestParam("ticketDoc") MultipartFile file,
			HttpServletRequest req, SessionStatus sessionStatus, final Locale locale) {

		HttpSession session = req.getSession(false);
		String actionType = req.getParameter("actionType");
		if (actionType.equals("Save")) {

			try {
				String userName = (String) SessionData.getUserDetails().get("userID");
				helpdeskBean.setDocketSource("Phone");
				helpdeskBean.setUserName(userName);
				helpDeskTicketService.saveTicketInfo(helpdeskBean);
				session.setAttribute("helpDeskSaveResult",
						"Ticket created Successfully and Ticket No. is " + helpdeskBean.getDocketNumber());
				if (file.getSize() > 0 && file != null) {
					TicketDocumentsEntity document = new TicketDocumentsEntity();

					document.setDocname(file.getOriginalFilename());
					document.setDocketNo(helpdeskBean.getDocketNumber());
					document.setDocument(file.getBytes());

					ticketDocumentService.save(document);
				}
			} catch (Exception e) {
				e.printStackTrace();
				session.setAttribute("helpDeskSaveResult", "Ticket Not Created");
			}

			return "redirect:/index";

		} else {

			if (helpdeskBean.getDocketSource() == null) {
				helpdeskBean.setDocketSource(req.getParameter("docSourceUpdate"));

			}
			String alternativeMobileNo = req.getParameter("alternativeMobileNo");

			if (alternativeMobileNo != null && !alternativeMobileNo.equals("")) {
				long actualAlternativeMobileNo = Long.parseLong(alternativeMobileNo);
				helpdeskBean.setConsumerMobileNo(actualAlternativeMobileNo);
			}

			String docketNo = req.getParameter("selectedDocket");
			HelpDeskTicketEntity entity = helpDeskTicketDAO.find(Integer.parseInt(docketNo));

			helpdeskBean.setConsumerMobileNo(entity.getConsumerMobileNo());
			// helpdeskBean.setDocketCreatedDt(entity.getDocketCreatedDt());
			helpdeskBean.setDocketNumber(Integer.parseInt(docketNo));
			helpdeskBean.setDocketStatus(1);

			try {
				helpDeskTicketService.update(helpdeskBean);
				session.setAttribute("helpDeskUpdateResult", docketNo + " docket details updated Successfully");
			} catch (Exception e) {
				e.printStackTrace();
				session.setAttribute("helpDeskUpdateResult", "Ticket Not Updated");
			}
			return "redirect:/index";
		}
	}

	@RequestMapping(value = "/searchTicket", method = { RequestMethod.GET, RequestMethod.POST })
	public String searchTicketInfo(@ModelAttribute("helpdeskBean") HelpDeskTicketEntity helpdeskBean,
			BindingResult bindingResult, ModelMap model, HttpServletRequest req, SessionStatus sessionStatus,
			final Locale locale) {
		logger.info("Inside Pgrs view complaints method");
		model.addAttribute("modulename", "Search Complaints");
		String query = "";
		String title = "";
		System.out.println("From Date " + new SimpleDateFormat("dd/MM/yyyy").format(helpdeskBean.getFromdate())
				+ " To Date " + new SimpleDateFormat("dd/MM/yyyy").format(helpdeskBean.getTodate()));
		if (helpdeskBean.getFromdate() != null && helpdeskBean.getTodate() != null) {
			title = "From Date : " + new SimpleDateFormat("dd/MM/yyyy").format(helpdeskBean.getFromdate())
					+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
			title += " To Date : " + new SimpleDateFormat("dd/MM/yyyy").format(helpdeskBean.getTodate()) + "<br>";
			query = "date(ht.docketCreatedDt)>=to_date('"
					+ new SimpleDateFormat("dd/MM/yyyy").format(helpdeskBean.getFromdate()) + "','dd/MM/yyyy') AND ";
			query += "date(ht.docketCreatedDt)<=to_date('"
					+ new SimpleDateFormat("dd/MM/yyyy").format(helpdeskBean.getTodate()) + "','dd/MM/yyyy')";
		}
		if (helpdeskBean.getSiteCode() != null && !helpdeskBean.getSiteCode().equalsIgnoreCase("0")) {
			ProjectHeirarchyEntity projectHeirarchyEntity = genericDao
					.getByUniqueAttribute(ProjectHeirarchyEntity.class, "siteCode", helpdeskBean.getSiteCode());
			title += " Circle : " + projectHeirarchyEntity.getParent().getParent().getText();
			title += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Division : " + projectHeirarchyEntity.getParent().getText();
			title += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Sub Division : " + projectHeirarchyEntity.getText();
			if (query.equalsIgnoreCase("")) {
				query = "ht.siteCode like '" + helpdeskBean.getSiteCode() + "%'";
			} else {
				query += " AND ht.siteCode like '" + helpdeskBean.getSiteCode() + "%'";
			}
		} else if (helpdeskBean.getDivisionSiteCode() != 0) {
			ProjectHeirarchyEntity entity = genericDao.getById(ProjectHeirarchyEntity.class,
					helpdeskBean.getDivisionSiteCode());
			title += " Circle : " + entity.getParent().getText();
			title += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Division : " + entity.getText()
					+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Sub Division : All <br>";
			if (query.equalsIgnoreCase("")) {
				query = "ht.siteCode like '" + entity.getSiteCode() + "%'";
			} else {
				query += " AND ht.siteCode like '" + entity.getSiteCode() + "%'";
			}
		} else if (helpdeskBean.getCircleSiteCode() != 0) {
			ProjectHeirarchyEntity entity = genericDao.getById(ProjectHeirarchyEntity.class,
					helpdeskBean.getCircleSiteCode());
			title += " Circle : " + entity.getText()
					+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Division: All &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Sub Division : All <br>";
			if (query.equalsIgnoreCase("")) {
				query = "ht.siteCode like '" + entity.getSiteCode() + "%'";
			} else {
				query += " AND ht.siteCode like '" + entity.getSiteCode() + "%'";
			}
		} else {
			title += " Circle : All&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Division: All &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Sub Division : All <br>";
		}
		if (helpdeskBean.getDocketStatus() != 0) {
			String[] str = { "Pending For Registration", "Assigned & Registered", "On Hold", "Resolved", "Reopened" };
			title += " Docket Status : " + str[helpdeskBean.getDocketStatus()];
			if (query.equalsIgnoreCase("")) {
				query = "ht.docketStatus=" + (helpdeskBean.getDocketStatus());
			} else {
				query += " AND ht.docketStatus=" + (helpdeskBean.getDocketStatus());
			}
		} else {
			title += " Docket Status : All";
		}
		if (helpdeskBean.getDocketSource() != null) {
			title += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Docket Source : " + helpdeskBean.getDocketSource();
			if (query.equalsIgnoreCase("")) {
				query = "ht.docketSource='" + helpdeskBean.getDocketSource() + "'";
			} else {
				query += " AND ht.docketSource='" + helpdeskBean.getDocketSource() + "'";
			}
		} else {
			title += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Docket Source : All ";
		}
		if (helpdeskBean.getCategoryId() != 0) {
			TicketCategoryEntity ticketCategoryEntity = genericDao.getById(TicketCategoryEntity.class,
					helpdeskBean.getCategoryId());
			title += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Docket Category : " + ticketCategoryEntity.getCategoryName();
			if (query.equalsIgnoreCase("")) {
				query = "ht.categoryId=" + helpdeskBean.getCategoryId();
			} else {
				query += " AND ht.categoryId=" + helpdeskBean.getCategoryId();
			}
		} else {
			title += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Docket Category :All ";
		}
		if (helpdeskBean.getSubCategoryId() != 0) {
			TicketSubCategoryEntity ticketSubCategoryEntity = genericDao.getById(TicketSubCategoryEntity.class,
					helpdeskBean.getSubCategoryId());
			title += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Docket Sub Category : "
					+ ticketSubCategoryEntity.getSubCategoryName();
			if (query.equalsIgnoreCase("")) {
				query = "ht.subCategoryId=" + helpdeskBean.getSubCategoryId();
			} else {
				query += " AND ht.subCategoryId=" + helpdeskBean.getSubCategoryId();
			}
		} else {
			title += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Docket Sub Category : All";
		}

		System.out.println(query);
		if (query != "") {
			List<?> list = genericDao.executeSimpleObjectQuery(
					"SELECT ht.docketNumber,ht.docketStatus,ht.docketCreatedDt,ht.resolvedDate,tc.categoryName,tsc.subCategoryName,ht.consumerName,ht.consumerMobileNo,ht.assignedTo,ht.estResolveTime,dn.dnName,ur.urName,ur.urContactNo FROM ProjectHeirarchyEntity ph,TicketCategoryEntity tc,TicketSubCategoryEntity tsc,HelpDeskTicketEntity ht,Users ur INNER JOIN ur.designationEntity dn WHERE ht.categoryId=tc.categoryId AND ht.subCategoryId=tsc.subCategoryId AND ph.siteCode=ht.siteCode AND ht.docketStatus!=0 AND ur.urLoginName=ht.assignedTo AND "
							+ query);
			List<?> viewComplaints = helpDeskTicketService.getComplaints(list);
			model.addAttribute("search", viewComplaints);
		}
		HttpSession httpSession = req.getSession(false);
		int projectId = (Integer) httpSession.getAttribute("projectId");
		List<?> divisionsList = ticketCategoryService.getAllDivisions(projectId);
		List<?> circleList = ticketCategoryService.getAllCircles(projectId);
		List<?> categoryList = ticketCategoryService.getAllActiveCategories();

		model.addAttribute("helpdeskBean", new HelpDeskTicketEntity());
		model.addAttribute("divisionsList", divisionsList);
		model.addAttribute("circleList", circleList);
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("title", title);

		return "pgrs/viewComplaints";

	}

	@RequestMapping(value = "/helpDesk/getNotifications", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Set<String> getNotifications(@PathVariable int categoryId) {
		return commonService.getCheckConstraintsVals("pgrs_escalation", "pgrs_escalation_notification_type_check",
				"PGRS");
	}

	@RequestMapping(value = "/helpDesk/searchDocketNumber/{docketNumber}/{sitecode}", method = { RequestMethod.GET,
			RequestMethod.POST })
	public @ResponseBody List<Map<String, Object>> searchDocketNumber(@PathVariable int docketNumber,@PathVariable String sitecode,
			HttpServletRequest req) {
		logger.info("In read search docket number");
		List<Map<String, Object>> docketList = helpDeskTicketService.searchDocketNumber(docketNumber,sitecode);
		return docketList;
	}

	@RequestMapping(value = "/helpDesk/getTicketDataBasedOnStatus/{status}", method = { RequestMethod.GET,
			RequestMethod.POST })
	public @ResponseBody List<Map<String, Object>> getTicketDataBasedOnStatus(@PathVariable int status,
			HttpServletRequest req) {
		logger.info("In read search docket number");
		List<Map<String, Object>> docketList = helpDeskTicketService.getTicketDataBasedOnStatus(status);
		return docketList;
	}
	
	@RequestMapping(value = "/helpDesk/getTicketDataBasedOnStatusMobile/{status}/{sitecode}", method = { RequestMethod.GET,
			RequestMethod.POST })
	public @ResponseBody List<Map<String, Object>> getTicketDataBasedOnStatusMobile(@PathVariable int status,@PathVariable String sitecode,
			HttpServletRequest req) {
		logger.info("In read search docket number");
		List<Map<String, Object>> docketList = helpDeskTicketService.getTicketDataBasedOnStatusMobile(status,sitecode);
		return docketList;
	}
	@RequestMapping(value = "/helpDesk/getTicketDataBasedOnStatusMobile2/{sitecode}", method = { RequestMethod.GET,
			RequestMethod.POST })
	public @ResponseBody List<Map<String, Object>> getTicketDataBasedOnStatusMobile2(@PathVariable String sitecode,
			HttpServletRequest req) {
		logger.info("In read search docket number");
		List<Map<String, Object>> docketList = helpDeskTicketService.getTicketDataBasedOnStatusMobile2(sitecode);
		return docketList;
	}

	@RequestMapping(value = "/helpDesk/searchMobileNumber/{mobileNo}", method = { RequestMethod.GET,
			RequestMethod.POST })
	public @ResponseBody List<Map<String, Object>> searchMobileNumber(@PathVariable long mobileNo,
			HttpServletRequest req) {
		logger.info("In read search docket number");
		List<Map<String, Object>> docketList = helpDeskTicketService.searchMobileNumber(mobileNo);
		return docketList;
	}

	@RequestMapping(value = "/helpDesk/searchDocketRRNumber/{rrNumber}", method = { RequestMethod.GET,
			RequestMethod.POST })
	public @ResponseBody List<Map<String, Object>> searchDocketRRNumber(@PathVariable String rrNumber,
			HttpServletRequest req) {
		logger.info("In read search docket number");
		List<Map<String, Object>> docketList = helpDeskTicketService.searchDocketRRNumber(rrNumber);
		return docketList;
	}

	@RequestMapping(value = "/helpDesk/updateDocket", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody String updateDocketDetails(HttpServletRequest request) {

		HttpSession session = request.getSession(false);

		String resultmessage = "";
		String dockNo = request.getParameter("docketNo");
		String docNum[] = new String[1];
		if (dockNo.contains(",")) {
			docNum = dockNo.split(",");
		} else {
			docNum[0] = dockNo;

		}
		for (int i = 0; i < docNum.length; i++) {
			HelpDeskTicketEntity ticketEntity = helpDeskTicketDAO.find(Integer.parseInt(docNum[i]));
			String status = request.getParameter("docketStatus");
			if (!status.equals("")) {
				int docketStatus = Integer.parseInt(request.getParameter("docketStatus"));
				ticketEntity.setDocketStatus(docketStatus);
				if (ticketEntity.getDocketStatus() == 3) {
					ticketEntity.setResolvedDate(new Timestamp(new Date().getTime()));
				}
				if (ticketEntity.getDocketStatus() == 4) {
					ticketEntity.setResolvedDate(null);
					ticketEntity.setDocketCreatedDt(new Timestamp(new Date().getTime()));
					Set<String> assignmentTypeSet = new HashSet<>();
					assignmentTypeSet.add("Assigned");
					List<TicketEscalationEntity> escList = helpDeskTicketDAO
							.getIntialLevelDesignation(ticketEntity.getSubCategoryId(), assignmentTypeSet);

					// List<?> userNamesList =
					// helpDeskTicketDAO.getUserLoginNamesBasedOnDesignation(escList.get(0).getDnId());
					/*
					 * if(userNamesList.isEmpty()){ ProjectHeirarchyEntity
					 * parentEntitySubDivision = helpDeskTicketDAO.
					 * getParentProjectHeirarchyEntityBasedOnSiteCode(
					 * ticketEntity.getSiteCode()); userNamesList =
					 * helpDeskTicketDAO.getUserLoginNamesBasedOnDesignation(
					 * escList.get(0).getDnId(),parentEntitySubDivision.
					 * getSiteCode()); if(userNamesList.isEmpty()){
					 * ProjectHeirarchyEntity parentEntityDivision =
					 * helpDeskTicketDAO.
					 * getParentProjectHeirarchyEntityBasedOnSiteCode(
					 * parentEntitySubDivision.getSiteCode()); userNamesList =
					 * helpDeskTicketDAO.getUserLoginNamesBasedOnDesignation(
					 * escList.get(0).getDnId(),parentEntityDivision.getSiteCode
					 * ()); if(userNamesList.isEmpty()){ ProjectHeirarchyEntity
					 * parentEntityCircle = helpDeskTicketDAO.
					 * getParentProjectHeirarchyEntityBasedOnSiteCode(
					 * parentEntityDivision.getSiteCode()); userNamesList =
					 * helpDeskTicketDAO.getUserLoginNamesBasedOnDesignation(
					 * escList.get(0).getDnId(),parentEntityCircle.getSiteCode()
					 * ); if(userNamesList.isEmpty()){ ProjectHeirarchyEntity
					 * parentEntityZone = helpDeskTicketDAO.
					 * getParentProjectHeirarchyEntityBasedOnSiteCode(
					 * parentEntityCircle.getSiteCode()); userNamesList =
					 * helpDeskTicketDAO.getUserLoginNamesBasedOnDesignation(
					 * escList.get(0).getDnId(),parentEntityZone.getSiteCode());
					 * if(userNamesList.isEmpty()){ ProjectHeirarchyEntity
					 * parentEntityProject = helpDeskTicketDAO.
					 * getParentProjectHeirarchyEntityBasedOnSiteCode(
					 * parentEntityZone.getSiteCode()); userNamesList =
					 * helpDeskTicketDAO.getUserLoginNamesBasedOnDesignation(
					 * escList.get(0).getDnId(),parentEntityProject.getSiteCode(
					 * )); } } } }
					 * 
					 * }
					 */
					int escTimeLines;
					String classificationType = helpDeskTicketDAO
							.getLocationClassificationTypeBasedOnSiteCode(ticketEntity.getSiteCode());
					if (classificationType.equalsIgnoreCase("Urban")) {
						escTimeLines = escList.get(0).getUrbanTimeLines();
					} else {
						escTimeLines = escList.get(0).getRuralTimeLines();
					}

					Calendar calender = Calendar.getInstance();
					calender.setTime(new Date());
					calender.add(Calendar.HOUR, escTimeLines);
					Timestamp actualEstDate = new Timestamp(calender.getTime().getTime());

					ticketEntity.setEstResolveTime(actualEstDate);
				}
			}
			String remarks = request.getParameter("remarks");
			String commentValue = request.getParameter("commentValue");
			if (commentValue != null && commentValue.equals("Invalid")) {
				ticketEntity.setInvalidFlag("Yes");
			}
			ticketEntity.setRemarks(remarks);
			try {
				helpDeskTicketService.updateDocket(ticketEntity, session);
				resultmessage = dockNo + " Docket details updated successfully";
			} catch (Exception e) {
				resultmessage = dockNo + " Docket details not updated";
			}
		}
		return resultmessage;
	}

	@RequestMapping(value = "/helpDesk/searchDocketHistory/{docketNumber}", method = { RequestMethod.GET,
			RequestMethod.POST })
	public @ResponseBody List<Map<String, Object>> searchDocketHistory(@PathVariable int docketNumber,
			HttpServletRequest req) {
		logger.info("In read search docket history");
		List<Map<String, Object>> docketList = helpDeskTicketService.searchDocketHistory(docketNumber);
		return docketList;
	}

	@RequestMapping(value = "/helpDesk/searchDocketEscHistory/{docketNumber}", method = { RequestMethod.GET,
			RequestMethod.POST })
	public @ResponseBody List<Map<String, Object>> searchDocketEscHistory(@PathVariable int docketNumber,
			HttpServletRequest req) {
		logger.info("In read search docket escalation history");
		List<Map<String, Object>> docketList = helpDeskTicketService.searchDocketEscHistory(docketNumber);
		return docketList;
	}

	@RequestMapping(value = "/helpDesk/pendingForRegistrationTickets", method = { RequestMethod.GET,
			RequestMethod.POST })
	public @ResponseBody List<Map<String, Object>> pendingForRegistrationTickets(HttpServletRequest req) {
		logger.info("In read pending for registration");
		List<Map<String, Object>> docketList = helpDeskTicketService.pendingForRegistrationTickets();
		return docketList;
	}

	@RequestMapping(value = "/header/getNotificationDataBasedOnStatus/{docketStatus}", method = { RequestMethod.GET,
			RequestMethod.POST })
	public @ResponseBody List<?> getNotificationDataBasedOnStatus(@PathVariable int docketStatus,
			HttpServletRequest req) {

		HttpSession session = req.getSession(false);
		String loginName = (String) session.getAttribute("userId");
		List<?> docketList = null;

		Set<Integer> statusSet = new HashSet<Integer>();

		if (docketStatus == 3) {
			statusSet.add(3);
			docketList = helpDeskTicketService.getPendingNotificationDataBasedOnStatus(statusSet);
		} else {
			statusSet.add(1);
			statusSet.add(2);
			statusSet.add(4);
			docketList = helpDeskTicketService.getNotificationDataBasedOnStatus(statusSet, loginName);
		}
		return docketList;
	}

	@RequestMapping(value = "/pendingForRegistrationUrl", method = { RequestMethod.GET, RequestMethod.POST })
	public String pendingForRegistrationUrl(HttpServletRequest req) {

		logger.info("DocNum-----------" + req.getParameter("docNum"));
		HttpSession session = req.getSession(false);
		session.setAttribute("docNumForNotify", req.getParameter("docNum"));
		session.setAttribute("pendingForRegistrationNotification", "Notification");

		return "redirect:/index";
	}

	@RequestMapping(value = "/escalatedComplaintsUrl", method = { RequestMethod.GET, RequestMethod.POST })
	public String escalatedComplaintsUrl(HttpServletRequest req) {

		logger.info("inside Esc For Cc--" + req.getParameter("escDocForCCNum"));
		HttpSession session = req.getSession(false);
		session.setAttribute("escDocForCCNum", req.getParameter("escDocForCCNum"));
		session.setAttribute("escalatedComplaintsNotification", "Notification");

		return "redirect:/index";
	}

	@RequestMapping(value = "/escalatedComplaintsForCCCDUrl", method = { RequestMethod.GET, RequestMethod.POST })
	public String escalatedComplaintsForCCCDUrl(HttpServletRequest req) {
		logger.info("inside Escalted Docket Number--" + req.getParameter("escDocNum"));
		HttpSession session = req.getSession(false);
		session.setAttribute("escDocNum", req.getParameter("escDocNum"));
		session.setAttribute("escalatedComplaintsForCCCDNotifications", "Notification");

		return "redirect:/index";
	}

	@RequestMapping(value = "/getNotifyForAssignedDockets", method = { RequestMethod.GET, RequestMethod.POST })
	public String getAssignedDocFromNotify(HttpServletRequest req) {
		logger.info("inside Escalted Docket Number--" + req.getParameter("escAssignDocNum"));
		HttpSession session = req.getSession(false);
		session.setAttribute("escAssignDocNum", req.getParameter("escAssignDocNum"));
		session.setAttribute("reqEscAssignedTick", "Notification");

		return "redirect:/index";
	}

	@RequestMapping(value = "/helpDesk/getAllAssignedTicketsInMyDockets", method = { RequestMethod.GET,
			RequestMethod.POST })
	public @ResponseBody List<?> getAllEscaulatyeTickets(HttpServletRequest req) {
		logger.info("In read all getAllAssignedTicketsInMyDockets tickets");
		HttpSession session = req.getSession(false);
		String loginName = (String) session.getAttribute("userId");
		Set<Integer> statusSet = new HashSet<Integer>();
		statusSet.add(1);
		statusSet.add(4);
		statusSet.add(2);
		List<?> assigndTick = helpDeskTicketService.docketDetailsRead(loginName, statusSet);
		return assigndTick;
	}

	@RequestMapping(value = "/helpDesk/getAllResolvedTickets", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody List<?> getAllResolvedTickets(HttpServletRequest req) {
		logger.info("In read all resolved tickets");
		HttpSession session = req.getSession(false);
		String loginName = (String) session.getAttribute("userId");
		Set<Integer> statusSet = new HashSet<Integer>();
		statusSet.add(3);
		List<?> docketList = helpDeskTicketService.docketDetailsRead(loginName, statusSet);
		return docketList;
	}

	@RequestMapping(value = "/submitFeedbackComments", method = { RequestMethod.GET, RequestMethod.POST })
	public String submitFeedbackComments(@ModelAttribute("helpdeskEntity") HelpDeskTicketEntity helpdeskEntity,
			BindingResult bindingResult, ModelMap model, HttpServletRequest req, SessionStatus sessionStatus,
			final Locale locale) {

		String docNum[] = new String[1];
		if (helpdeskEntity.getDocketNumber() != 0) {
			docNum[0] = String.valueOf(helpdeskEntity.getDocketNumber());
		} else {
			if (helpdeskEntity.getTransDocNum().contains(",")) {
				docNum = helpdeskEntity.getTransDocNum().split(",");
			} else {
				docNum[0] = helpdeskEntity.getTransDocNum();

			}
		}
		for (int i = 0; i < docNum.length; i++) {
			HelpDeskTicketEntity ticketEntity = helpDeskTicketDAO.find(Integer.parseInt(docNum[i]));

			ticketEntity.setFeedBackRating(helpdeskEntity.getFeedBackRating());
			ticketEntity.setFeedBackComments(helpdeskEntity.getFeedBackComments());
			String resultFeedbackComments = "";
			HttpSession session = req.getSession(false);

			try {
				helpDeskTicketService.submitFeedbackcomments(ticketEntity);
				resultFeedbackComments = "Thank you for your feedback";
				session.setAttribute("resultFeedbackComments", resultFeedbackComments);
			} catch (Exception e) {
				resultFeedbackComments = "Feedback comments are not updated";
				session.setAttribute("resultFeedbackComments", resultFeedbackComments);
			}
		}
		return "redirect:/feedbackComments";
	}

	@RequestMapping(value = "/header/escalatedNotifications", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody List<?> escalatedNotifications(HttpServletRequest req) {

		HttpSession session = req.getSession(false);
		String loginName = (String) session.getAttribute("userId");
		List<?> docketList = null;
		docketList = helpDeskTicketService.escalatedNotifications(loginName);
		return docketList;
	}

	@RequestMapping(value = "/header/showAllEscalatedNotificationsForCCCD", method = { RequestMethod.GET,
			RequestMethod.POST })
	public @ResponseBody List<?> showAllEscalatedNotificationsForCCCD(HttpServletRequest req) {

		List<?> docketList = null;
		docketList = helpDeskTicketService.showAllEscalatedNotificationsForCCCD();
		return docketList;
	}

	@Scheduled(cron = "${scheduling.job.helpDeskEscalationCron}")
	public void escalationSheduler() throws Exception {

		logger.info("In escalation method cron sheduler code");
		synchronized (this) {
			helpDeskTicketService.escalationSheduler();
		}
		return;
	}

	@RequestMapping(value = "/helpDesk/getAllEscalatedTickets", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody List<?> getAllEscalatedTickets(HttpServletRequest req) {
		logger.info("In read all escalated tickets");
		HttpSession session = req.getSession(false);
		String loginName = (String) session.getAttribute("userId");
		List<?> docketList = helpDeskTicketService.getAllEscalatedTickets(loginName);
		return docketList;
	}

	@RequestMapping(value = "/helpDesk/getAllEscalatedTicketsForCCCD", method = { RequestMethod.GET,
			RequestMethod.POST })
	public @ResponseBody List<?> getAllEscalatedTicketsForCCCD(HttpServletRequest req) {
		logger.info("In read all escalated tickets for cccd");
		List<?> docketList = helpDeskTicketService.getAllEscalatedTicketsForCCCD();
		return docketList;
	}

	@RequestMapping(value = "/esclation/escalationAnalysis", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody List<?> escalationAnalysis(HttpServletRequest req) throws ParseException {
		String siteCode = ((String) req.getParameter("sitecode"));
		String fromDate = ((String) req.getParameter("fromdate"));
		String toDate = ((String) req.getParameter("todate"));
		Integer level = (Integer.parseInt(req.getParameter("level")));
		return helpDeskTicketService.getEscalationAnalysisDetails(siteCode, fromDate, toDate, level);
	}

	@RequestMapping(value = "/helpDesk/invalidateDocketDetails/{docketNumber}", method = { RequestMethod.GET,
			RequestMethod.POST })
	public @ResponseBody String invalidateDocketDetails(@PathVariable int docketNumber, HttpServletRequest request) {

		String invalidResponse = "";
		try {
			helpDeskTicketService.updateDocketAsInvalidated(docketNumber);
			invalidResponse = docketNumber + " Docket details are invalidated successfully";
		} catch (Exception e) {
			invalidResponse = docketNumber + " Docket details are not invalidated";
		}

		return invalidResponse;
	}

	@RequestMapping(value = "/readDocketAllDetails", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody List<?> getDocketDetailss(HttpServletRequest req) throws ParseException {
		int status = Integer.parseInt(req.getParameter("status"));
		int id = Integer.parseInt(req.getParameter("id"));
		System.out.println("project Id--" + id + "---status--" + status);
		Set<String> projectIds = new HashSet<String>();
		ProjectHeirarchyEntity projectHeirarchyEntity = genericDao.getById(ProjectHeirarchyEntity.class, id);
		if (!(projectHeirarchyEntity.getType().equalsIgnoreCase("Zone")
				|| projectHeirarchyEntity.getType().equalsIgnoreCase("Projects"))) {
			projectIds.add(projectHeirarchyEntity.getSiteCode());
		}
		fetchChildren1(genericDao.getById(ProjectHeirarchyEntity.class, id), projectIds);

		return helpDeskTicketService.getDocketDetailsBasedOnStatus(projectIds, status);
	}

	@RequestMapping(value = "/readAllDetailsBasedOnSearchWise", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody List<?> getAllDocketDetailsBasedOnSearch(HttpServletRequest req) {
		try {
			String fromDate = req.getParameter("fromDate");
			String toDate = req.getParameter("toDate");
			int catgryId = 0;
			int subcatgryId = 0;
			int id = 0;
			int status = 0;
			System.out.println("1" + req.getParameter("catagoryId") + " 2" + req.getParameter("subCatId"));
			if (req.getParameter("catagoryId") != null && !req.getParameter("catagoryId").trim().equals("")) {
				catgryId = Integer.parseInt(req.getParameter("catagoryId"));
			}
			if (req.getParameter("subCatId") != null && !req.getParameter("subCatId").trim().equals("")) {
				subcatgryId = Integer.parseInt(req.getParameter("subCatId"));
			}

			if (req.getParameter("id") != null && !req.getParameter("id").trim().equals("")) {
				id = Integer.parseInt(req.getParameter("id"));
			}
			if (req.getParameter("statd") != null && !req.getParameter("statd").trim().equals("")) {
				status = Integer.parseInt(req.getParameter("statd"));
			}

			Set<String> projectIds = new HashSet<String>();
			ProjectHeirarchyEntity projectHeirarchyEntity = genericDao.getById(ProjectHeirarchyEntity.class, id);
			if (!(projectHeirarchyEntity.getType().equalsIgnoreCase("Zone")
					|| projectHeirarchyEntity.getType().equalsIgnoreCase("Projects"))) {
				projectIds.add(projectHeirarchyEntity.getSiteCode());
			}
			fetchChildren1(genericDao.getById(ProjectHeirarchyEntity.class, id), projectIds);

			String query = "";
			if (fromDate != null && toDate != null && !fromDate.trim().equals("") && !toDate.trim().equals("")) {
				query = "date(ht.docketCreatedDt)>=to_date('" + fromDate + "','dd/MM/yyyy') AND ";
				query += "date(ht.docketCreatedDt)<=to_date('" + toDate + "','dd/MM/yyyy')";
			}
			if (catgryId != 0) {
				if (query.equalsIgnoreCase("")) {
					query = "ht.categoryId=" + catgryId;
				} else {
					query += " AND ht.categoryId=" + catgryId;
				}
			}
			if (subcatgryId != 0) {
				if (query.equalsIgnoreCase("")) {
					query = "ht.subCategoryId=" + subcatgryId;
				} else {
					query += " AND ht.subCategoryId=" + subcatgryId;
				}
			}
			String sitecode = "";
			Iterator<String> iter = projectIds.iterator();
			while (iter.hasNext()) {
				String projectHeirarchyEntity2 = iter.next();
				sitecode += "'" + projectHeirarchyEntity2 + "',";
			}
			sitecode = "(" + sitecode.substring(0, sitecode.length() - 1) + ")";
			logger.info("Query---" + query);
			List<?> viewComplaints = new ArrayList<>();
			if (query != "") {
				System.out.println("Final Query-"
						+ "SELECT ht.docketNumber,ht.docketStatus,ht.docketCreatedDt,ht.resolvedDate,tc.categoryName,tsc.subCategoryName,ht.consumerName,ht.consumerMobileNo,ht.assignedTo,ht.estResolveTime FROM TicketCategoryEntity tc,TicketSubCategoryEntity tsc,HelpDeskTicketEntity ht,Users ur INNER JOIN ur.designationEntity dn WHERE ht.categoryId=tc.categoryId AND ht.subCategoryId=tsc.subCategoryId AND ht.siteCode IN "
						+ sitecode + " AND ur.urLoginName=ht.assignedTo AND ht.docketStatus=" + status + " AND "
						+ query);
				List<?> list = genericDao.executeSimpleObjectQuery(
						"SELECT ht.docketNumber,ht.docketStatus,ht.docketCreatedDt,ht.resolvedDate,tc.categoryName,tsc.subCategoryName,ht.consumerName,ht.consumerMobileNo,ht.assignedTo,ht.estResolveTime FROM TicketCategoryEntity tc,TicketSubCategoryEntity tsc,HelpDeskTicketEntity ht,Users ur INNER JOIN ur.designationEntity dn WHERE ht.categoryId=tc.categoryId AND ht.subCategoryId=tsc.subCategoryId AND ht.siteCode IN "
								+ sitecode + " AND ur.urLoginName=ht.assignedTo AND ht.docketStatus=" + status + " AND "
								+ query);
				viewComplaints = helpDeskTicketService.readDetailsInMap(list);
			}
			return viewComplaints;
		}

		catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	@RequestMapping(value = "/getDetailsOnSearchBasedWeb/{docketNumber}", method = { RequestMethod.GET,
			RequestMethod.POST })
	public @ResponseBody List<?> getAllDocketDetailsBasedOnSearchNew(@PathVariable int docketNumber,
			HttpServletRequest req) {
		try {
			logger.info("inside Docket--" + docketNumber);
			List<Object[]> grt = genericDao.executeSimpleObjectQuery(
					"SELECT ht.docketNumber,ht.docketStatus,ht.docketSource,ht.consumerName,ht.consumerMobileNo,ht.rrNumber,ht.address,tc.categoryId,tsc.subCategoryId,ur.urName,ht.userName,ht.docketCreatedDt,ht.resolvedDate,ht.docketSummary,ph.parentId,ph.siteCode,ht.alternativeMobileNo,ht.consumerEmailId,ht.facebookId,ht.landMark,ht.estResolveTime,dn.dnName,ur.urName,ur.urContactNo,ht.docketUpdatedDt,ht.remarks,ph.parent.parentId FROM ProjectHeirarchyEntity ph,TicketCategoryEntity tc,TicketSubCategoryEntity tsc,HelpDeskTicketEntity ht,Users ur INNER JOIN ur.designationEntity dn WHERE ht.categoryId=tc.categoryId AND ht.subCategoryId=tsc.subCategoryId AND ph.siteCode=ht.siteCode AND ur.urLoginName=ht.assignedTo AND ht.docketNumber="
							+ docketNumber);

			List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
			Map<String, Object> docketMap = null;
			for (Iterator<?> iterator = grt.iterator(); iterator.hasNext();) {
				final Object[] values = (Object[]) iterator.next();
				docketMap = new HashMap<String, Object>();

				docketMap.put("docketNumber", (int) values[0]);
				if (((int) values[1]) == 0) {
					docketMap.put("docketStatus", "Pending for Registration");
				} else if (((int) values[1]) == 1) {
					docketMap.put("docketStatus", "Registered&Assigned");
				} else if (((int) values[1]) == 2) {
					docketMap.put("docketStatus", "On Hold");
				} else if (((int) values[1]) == 3) {
					docketMap.put("docketStatus", "Resolved");
				} else if (((int) values[1]) == 4) {
					docketMap.put("docketStatus", "Reopened");
				}
				docketMap.put("docketSource", (String) values[2]);
				docketMap.put("consumerName", (String) values[3]);
				docketMap.put("consumerMobileNo", (Long) values[4]);
				docketMap.put("rrNumber", (String) values[5]);
				docketMap.put("address", (String) values[6]);
				docketMap.put("categoryId", (int) values[7]);
				docketMap.put("subCategoryId", (int) values[8]);
				docketMap.put("urName", (String) values[9]);
				docketMap.put("userName", (String) values[10]);
				docketMap.put("docketCreatedDt",
						new SimpleDateFormat("dd/MM/yyyy hh:mm a").format((Timestamp) values[11]));
				try {
					docketMap.put("resolvedDate",
							new SimpleDateFormat("dd/MM/yyyy hh:mm a").format((Timestamp) values[12]));
				} catch (Exception e) {
					docketMap.put("resolvedDate", "");
				}

				docketMap.put("docketSummary", (String) values[13]);
				docketMap.put("divisionId", (Integer) values[26]);
				docketMap.put("subDivisionId", (Integer) values[14]);
				docketMap.put("sectionId", (String) values[15]);

				if ((Long) values[16] == null || (Long) values[16] == 0) {
					docketMap.put("alternativeMobileNo", "");
				} else {
					docketMap.put("alternativeMobileNo", (Long) values[16]);
				}

				if ((String) values[17] == null || ((String) values[17]).equals("")) {
					docketMap.put("consumerEmailId", "");
				} else {
					docketMap.put("consumerEmailId", (String) values[17]);
				}

				if ((String) values[18] == null || ((String) values[18]).equals("")) {
					docketMap.put("facebookId", "");
				} else {
					docketMap.put("facebookId", (String) values[18]);
				}

				if ((String) values[19] == null || ((String) values[19]).equals("")) {
					docketMap.put("landMark", "");
				} else {
					docketMap.put("landMark", (String) values[19]);
				}

				try {
					docketMap.put("estResolveTime",
							new SimpleDateFormat("dd/MM/yyyy hh:mm a").format((Timestamp) values[20]));
				} catch (Exception e) {
					docketMap.put("estResolveTime", "");
				}

				if ((String) values[21] == null || ((String) values[21]).equals("")) {
					docketMap.put("designation", "");
				} else {
					docketMap.put("designation", (String) values[21]);
				}

				if ((String) values[22] == null || ((String) values[22]).equals("")) {
					docketMap.put("assinedName", "");
				} else {
					docketMap.put("assinedName", (String) values[22]);
				}

				if ((String) values[23] == null || ((String) values[23]).equals("")) {
					docketMap.put("officialMobileNo", "");
				} else {
					docketMap.put("officialMobileNo", (String) values[23]);
				}

				docketMap.put("docketUpdatedDt",
						new SimpleDateFormat("dd/MM/yyyy hh:mm a").format((Timestamp) values[24]));

				if ((String) values[25] == null || ((String) values[25]).equals("")) {
					docketMap.put("remarks", "");
				} else {
					docketMap.put("remarks", (String) values[25]);
				}

				result.add(docketMap);
			}
			return result;

		} catch (Exception e) {
			e.printStackTrace();

		}

		return null;
	}

	@RequestMapping(value = "/getRemarksBaseOnModeAndMobileNo/{docketNumber}/{mobileNo}", method = { RequestMethod.GET,
			RequestMethod.POST })
	public @ResponseBody String getAllDocketDetailsBasedOnSearchNew(@PathVariable int docketNumber,
			@PathVariable long mobileNo, HttpServletRequest req, HttpServletResponse response) {
		try {
			Object[] remarks = genericDao.executeSingleObjectQuery(
					"SELECT ht.remarks,ht.address FROM HelpDeskTicketEntity ht WHERE ht.docketNumber=" + docketNumber
							+ " AND ht.consumerMobileNo=" + mobileNo + " AND ht.docketSource='Sms'");
			return (String) remarks[0];
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// ----------------------------------------------------Mobile part Code Dont
	// Delete Added By
	// Raju------------------------------------------------------------

	@RequestMapping(value = "/getListOfCategory", method = RequestMethod.POST)
	public @ResponseBody Object updateComplaints() throws JSONException {

		List<?> categoryList = ticketCategoryService.getAllActiveCategories();
		return categoryList;
	}

	@SuppressWarnings("serial")
	@RequestMapping(value = "/getListOfSubcategory", method = RequestMethod.POST)
	public @ResponseBody Object subcateg() throws JSONException {

		List<Object[]> subcategoryList = genericDao.executeSimpleObjectQuery(
				"SELECT tsc.subCategoryId,tsc.categoryId,tsc.subCategoryName FROM TicketSubCategoryEntity tsc WHERE tsc.status = 1");

		List<Map<String, Object>> subList = new ArrayList<>();
		for (final Object[] obj : subcategoryList) {
			subList.add(new HashMap<String, Object>() {
				{
					put("subCategoryid", (int) obj[0]);
					put("Categoryid", (int) obj[1]);
					put("subCategoryName", (String) obj[2]);

				}
			});

		}

		return subList;
	}

	@RequestMapping(value = "/getListOfDivistionCode", method = RequestMethod.POST)
	public @ResponseBody Object divistion() throws JSONException {

		List<?> divisionsList = ticketCategoryService.getAllDivisions(1);
		return divisionsList;
	}

	@RequestMapping(value = "/getListOfSiteCode", method = RequestMethod.POST)
	public @ResponseBody Object siteCode() throws JSONException {

		List<?> subCategoryList = ticketSubCategoryService.getAllSubDivisionsForAndroid();
		logger.info("Site Cote---" + subCategoryList.size());
		return subCategoryList;
	}

	/*
	 * @RequestMapping(value="/createTicketAndroid",method
	 * ={RequestMethod.POST,RequestMethod.GET})//JAMAL'S public @ResponseBody
	 * String RegisterComplaints(HttpServletRequest request,HttpServletResponse
	 * response, @RequestBody String complaints_list) throws JSONException {
	 */

	/*
	 * @RequestMapping(value="/createTicketAndroid",method
	 * ={RequestMethod.POST,RequestMethod.GET},consumes =
	 * MediaType.APPLICATION_JSON_VALUE) public @ResponseBody Object
	 * RegisterComplaints(@RequestBody String complaints_list) throws
	 * JSONException {
	 */

	@RequestMapping(value = "/createTicketAndroid", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody String RegisterComplaints(HttpServletRequest request, HttpServletResponse response,
			@RequestBody String complaints_list) throws JSONException {

		System.out.println(complaints_list + "Hitting on create ticket Android "
				+ "###########@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");

		try {

			HelpDeskTicketEntity complEntity = new HelpDeskTicketEntity();

			JSONArray arr = new JSONArray(complaints_list);
			System.out.println("\nArray length "+arr.length());
			JSONObject Asst_json_obj = arr.getJSONObject(0);
			System.out.println("\nObject length "+arr.getJSONObject(0).length());

			complEntity.setConsumerMobileNo(Long.parseLong(Asst_json_obj.getString("MobileNo")));
			complEntity.setDocketCreatedDt(new Timestamp(new Date().getTime()));
			complEntity.setUserName(Asst_json_obj.getString("ConsumerName"));
			complEntity.setDocketUpdatedDt(new Timestamp(new Date().getTime()));

			complEntity.setConsumerName(Asst_json_obj.getString("ConsumerName"));
			complEntity.setConsumerEmailId(Asst_json_obj.getString("Emailid"));
			complEntity.setCategoryId(Integer.parseInt(Asst_json_obj.getString("CategoryId")));
			complEntity.setSubCategoryId(Integer.parseInt(Asst_json_obj.getString("SubCategoryId")));
			complEntity.setSiteCode(Asst_json_obj.getString("SiteCode"));
			complEntity.setAddress("");
			complEntity.setLandMark("");
			complEntity.setDocketSummary(Asst_json_obj.getString("DocketSummary"));
			complEntity.setRrNumber(Asst_json_obj.getString("RRNO"));
			complEntity.setDocketSource(Asst_json_obj.getString("DocketSource"));

			Set<String> assignmentTypeSet = new HashSet<>();
			assignmentTypeSet.add("Assigned");

			List<TicketEscalationEntity> escList = helpDeskTicketDAO
					.getIntialLevelDesignation(complEntity.getSubCategoryId(), assignmentTypeSet);

			List<?> userNamesList = helpDeskTicketDAO.getUserLoginNamesBasedOnDesignation(escList.get(0).getDnId());

			Object[] values = (Object[]) userNamesList.get(0);
			complEntity.setAssignedTo((String) values[0]);
			complEntity.setOfficerName((String) values[1]);
			complEntity.setDocketStatus(0);
			complEntity.setEscLevel(escList.get(0).getLevel());
			complEntity.setEscFlag("No");
			complEntity.setInvalidFlag("No");

			int escTimeLines;
			String classificationType = helpDeskTicketDAO
					.getLocationClassificationTypeBasedOnSiteCode(complEntity.getSiteCode());
			if (classificationType.equalsIgnoreCase("Urban")) {
				escTimeLines = escList.get(0).getUrbanTimeLines();
			} else {
				escTimeLines = escList.get(0).getRuralTimeLines();
			}

			Calendar calender = Calendar.getInstance();
			calender.setTime(new Date());
			calender.add(Calendar.HOUR, escTimeLines);
			Timestamp actualEstDate = new Timestamp(calender.getTime().getTime());

			complEntity.setEstResolveTime(actualEstDate);

			helpDeskTicketService.saveTicketFromMobil(complEntity);
			return "Ticket Created Successfully And Docket Number :" + complEntity.getDocketNumber();
		} catch (Exception e) {
			e.printStackTrace();
			return "Not Success! Please Contact Adminstration";
		}

	}


	/*
	 * @RequestMapping(value="/createTicketAndroid",method
	 * ={RequestMethod.POST,RequestMethod.GET})//JAMAL'S public @ResponseBody
	 * String RegisterComplaints(HttpServletRequest request,HttpServletResponse
	 * response, @RequestBody String complaints_list) throws JSONException {
	 */

	@RequestMapping(value = "/getRegisteredComplaintsMobile", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object viewComplaints(HttpServletRequest request, HttpServletResponse response,
			@RequestBody String complaints_list) throws JSONException {

		System.out.println( "Inside getRegisteredComplaintsMobile" );
		
		JSONArray arr = new JSONArray(complaints_list);

		JSONObject Asst_json_obj = arr.getJSONObject(0);
		
		System.out.println( "Getting Mobile Number " + Long.parseLong(Asst_json_obj.getString("MobileNo")) );
		
		List<Map<String, Object>> docketList = helpDeskTicketService
				.searchMobileNumber(Long.parseLong(Asst_json_obj.getString("MobileNo")));
		return docketList;

	}

	@RequestMapping(value = "/consumerLoginFromMobile", method = { RequestMethod.POST,
			RequestMethod.GET }, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Object consumerLogin(@RequestBody String complaints_list) throws JSONException {
		try {

			JSONArray arr = new JSONArray(complaints_list);
			JSONObject Asst_json_obj = arr.getJSONObject(0);
			String userName = Asst_json_obj.getString("UserName");
			String pwd = Asst_json_obj.getString("Password");

			List<?> checkValidUser = genericDao
					.executeSimpleQuery("select u from AndroidUserDeatailsEntity u where u.userName='" + userName
							+ "' AND u.password='" + pwd + "'");
			if (checkValidUser.size() > 0) {

				return checkValidUser;

			} else {
				return "Invalid Credentials";
			}

		} catch (Exception e) {
			e.printStackTrace();
			return "Invalid Credentials";
		}
	}

	@RequestMapping(value = "/consumerLoginDetailsUpdateMobile", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object consumerLoginDetailsUpdate(@RequestBody String complaints_list) throws JSONException {
		try {

			JSONArray arr = new JSONArray(complaints_list);
			JSONObject Asst_json_obj = arr.getJSONObject(0);
			String emailId = Asst_json_obj.getString("EmailId");
			String phoneNumber = Asst_json_obj.getString("PhoneNumber");
			String userName = Asst_json_obj.getString("UserName");

			long count = genericDao.executeSimpleUpdateQuery1("update AndroidUserDeatailsEntity u SET u.emailid='"
					+ emailId + "' , u.phoneNumber='" + phoneNumber + "' where u.userName='" + userName + "'");

			if (count > 0) {
				return "Successfully Updated";
			} else {
				return "Not Updated Please Again Try Later";
			}

		} catch (Exception e) {
			e.printStackTrace();
			return "Not Updated Please Again Try Later";
		}
	}

	@RequestMapping(value = "/recoverPasswordAndroid", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object recoverPassword(HttpServletRequest request, HttpServletResponse response,
			@RequestBody String complaints_list) throws JSONException {

		JSONArray arr = new JSONArray(complaints_list);

		JSONObject Asst_json_obj = arr.getJSONObject(0);

		String phonenumber = Asst_json_obj.getString("MobileNo");

		List<Object[]> checkValidUser = genericDao.executeSimpleObjectQuery(
				"select u.fullname,u.password,u.userName from AndroidUserDeatailsEntity u where  u.phoneNumber='"
						+ phonenumber + "'");
		if (checkValidUser.size() > 0) {
			// Object[] obj=checkValidUser.get(0);
			// String fNam=(String)obj[0];
			// String pwd=(String)obj[1];
			// String userName=(String)obj[2];
			// Send Password SMS To Mobile
			return "Password Sent to Registered Mobile Number";

		} else {
			return "User Doesn't Exist";
		}

	}

	@RequestMapping(value = "/changePasswordFromMobile", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object changePwdFromMobile(@RequestBody String complaints_list) throws JSONException {
		try {
			JSONArray arr = new JSONArray(complaints_list);
			JSONObject Asst_json_obj = arr.getJSONObject(0);
			String oldpwd = Asst_json_obj.getString("OldPassword");
			String newpwd = Asst_json_obj.getString("NewPassword");
			String userName = Asst_json_obj.getString("UserName");

			System.out.println(
					oldpwd + "++++++++++++++++++++++++++++Getting the values+++++++++++++++++++++++++++++++" + newpwd);

			long count = genericDao.executeSimpleUpdateQuery1("update AndroidUserDeatailsEntity u SET u.password='"
					+ newpwd + "' where u.userName='" + userName + "'");

			if (count > 0) {
				return "Successfully Updated";
			} else {
				return "Not Updated Please Again Try Later";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "Not Updated Please Again Try Later";
		}

	}

	@RequestMapping(value = "/enableSmsAlertAndroid", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object enableSmsAlertAndroid(@RequestBody String registerList) throws JSONException {
		String smsAlert = null;
		try {
			JSONArray arr = new JSONArray(registerList);
			JSONObject obj = arr.getJSONObject(0);

			String userName = obj.getString("userName");
			String purpose = obj.getString("purpose");

			if (purpose.equals("REGISTER")) // CHECKING THE PURPOSE OF CALLING
											// THIS METHOD
			{

				String currentStatus = obj.getString("currentStatus");

				if (currentStatus.equals("ON")) {
					smsAlert = "OFF";
				} else {
					smsAlert = "ON";
				}

				// ACTIVE, NOT_ACTIVE,ACTIVATED,DE_ACTIVATED.FAILED

				long count = genericDao.executeSimpleUpdateQuery1("update AndroidUserDeatailsEntity u SET u.smsAlert='"
						+ smsAlert + "'  where u.userName='" + userName + "'");

				if (count > 0) {
					if (smsAlert.equals("ON"))
						return "ACTIVATED";
					else
						return "DE_ACTIVATED";
				} else {

					return "FAILED";
				}

			} else { // CHECKING THE STATUS
				System.out.println(userName);
				List<Object[]> res = genericDao.executeSimpleObjectQuery(
						"select u.smsAlert,u.phoneNumber from AndroidUserDeatailsEntity u  where u.userName='"
								+ userName + "'");
				System.out.println("usnam--" + userName + "-----" + res.size());
				Object[] s = res.get(0);
				String smsStatus = (String) s[0];

				if (smsStatus.equals("ON")) {
					return "ACTIVE";
				} else {
					return "NOT_ACTIVE";
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
			return "FAILED";
		}
	}

	public String getTimeFormat(long val) {
		Date date = new Date(val);
		SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String dateText = df2.format(date);
		return dateText;

	}

	// ------------------------------End Android
	// part------------------------------------------------------------

	@RequestMapping(value = "/helpDesk/loadDetailsBasedOnRRNum/{sitCode}/{rrNum}", method = { RequestMethod.GET,
			RequestMethod.POST })
	public @ResponseBody List<?> getAllCustomersDetailsBasedOnRRNum(@PathVariable int sitCode,
			@PathVariable String rrNum, HttpServletRequest req) {
		logger.info("Inside RR Num Fetch Custmers Details--sitecode " + sitCode + "-RRNUM--" + rrNum);

		String sitcod = String.valueOf(sitCode);
		List<?> getDetails = genericDao.executeSimpleObjectQuery(
				"select p.consumerName,p.custMobileNo,p.consumerAddress,p.tariff,p.poleno from PgrsCustomers p where p.rrnumber='"
						+ rrNum + "' and p.sitecode='" + sitcod + "'");
		List<Map<String, Object>> readData = new ArrayList<>();
		for (Iterator<?> iterator = getDetails.iterator(); iterator.hasNext();) {
			Object[] obj = (Object[]) iterator.next();
			Map<String, Object> map = new HashMap<>();
			map.put("name", (String) obj[0]);
			map.put("mobile", (String) obj[1]);
			map.put("address", (String) obj[2]);
			map.put("tarriff", (String) obj[3]);
			map.put("poleNum", (String) obj[4]);
			readData.add(map);

		}

		return readData;

	}

	@RequestMapping(value = "/SlaComplaints", method = { RequestMethod.POST, RequestMethod.GET })
	public String ComplaintsForSlaAging(Model model, HttpServletRequest request, final Locale locale) {

		logger.info("Inside Pgrs view SlaComplaints method");
		model.addAttribute("modulename", "SLA Complaints");

		HttpSession httpSession = request.getSession(false);
		int projectId = (Integer) httpSession.getAttribute("projectId");
		List<?> divisionsList = ticketCategoryService.getAllDivisions(projectId);
		List<?> circleList = ticketCategoryService.getAllCircles(projectId);
		List<?> categoryList = ticketCategoryService.getAllActiveCategories();
		// List<?> viewComplaints=helpDeskTicketService.getTodaysComplaints(new
		// Date());
		model.addAttribute("helpdeskBean", new HelpDeskTicketEntity());
		model.addAttribute("divisionsList", divisionsList);
		model.addAttribute("circleList", circleList);
		model.addAttribute("categoryList", categoryList);

		model.addAttribute("agingEscPending", helpDeskTicketService.getComplaintAgingForPendingResovtion());
		model.addAttribute("complaintsAging", helpDeskTicketService.getComplaintAgingStatistics());
		// model.addAttribute("search",viewComplaints);
		return "pgrs/slaComplaints";
	}

	@RequestMapping(value = "/slaComplaintSearch", method = { RequestMethod.GET, RequestMethod.POST })
	public String searchTicketSlaComplaintsInfo(@ModelAttribute("helpdeskBean") HelpDeskTicketEntity helpdeskBean,
			BindingResult bindingResult, ModelMap model, HttpServletRequest req, SessionStatus sessionStatus,
			final Locale locale) {
		logger.info("Inside Pgrs SLA complaints method");
		model.addAttribute("modulename", "SLA Complaints");
		System.out.println(helpdeskBean.getSiteCode());
		System.out.println(helpdeskBean.getDivisionSiteCode());
		System.out.println(helpdeskBean.getCircleSiteCode());
		String query = "";
		if (helpdeskBean.getSiteCode() != null && !helpdeskBean.getSiteCode().equalsIgnoreCase("0")) {
			if (query.equalsIgnoreCase("")) {
				query = "ht.siteCode like '" + helpdeskBean.getSiteCode() + "%'";
			} else {
				query += " AND ht.siteCode like '" + helpdeskBean.getSiteCode() + "%'";
			}
		} else if (helpdeskBean.getDivisionSiteCode() != 0) {
			ProjectHeirarchyEntity entity = genericDao.getById(ProjectHeirarchyEntity.class,
					helpdeskBean.getDivisionSiteCode());
			if (query.equalsIgnoreCase("")) {
				query = "ht.siteCode like '" + entity.getSiteCode() + "%'";
			} else {
				query += " AND ht.siteCode like '" + entity.getSiteCode() + "%'";
			}
		} else if (helpdeskBean.getCircleSiteCode() != 0) {
			ProjectHeirarchyEntity entity = genericDao.getById(ProjectHeirarchyEntity.class,
					helpdeskBean.getCircleSiteCode());
			if (query.equalsIgnoreCase("")) {
				query = "ht.siteCode like '" + entity.getSiteCode() + "%'";
			} else {
				query += " AND ht.siteCode like '" + entity.getSiteCode() + "%'";
			}
		}
		/*
		 * if(helpdeskBean.getDocketStatus()!=0){
		 * if(query.equalsIgnoreCase("")){
		 * query="ht.docketStatus="+(helpdeskBean.getDocketStatus()-1); }else{
		 * query+=" AND ht.docketStatus="+(helpdeskBean.getDocketStatus()-1); }
		 * }
		 */
		if (helpdeskBean.getDocketSource() != null) {
			if (query.equalsIgnoreCase("")) {
				query = "ht.docketSource='" + helpdeskBean.getDocketSource() + "'";
			} else {
				query += " AND ht.docketSource=" + helpdeskBean.getDocketSource();
			}
		}
		if (helpdeskBean.getCategoryId() != 0) {
			if (query.equalsIgnoreCase("")) {
				query = "ht.categoryId=" + helpdeskBean.getCategoryId();
			} else {
				query += " AND ht.categoryId=" + helpdeskBean.getCategoryId();
			}
		}
		if (helpdeskBean.getSubCategoryId() != 0) {
			if (query.equalsIgnoreCase("")) {
				query = "ht.subCategoryId=" + helpdeskBean.getSubCategoryId();
			} else {
				query += " AND ht.subCategoryId=" + helpdeskBean.getSubCategoryId();
			}
		}

		logger.info("Based On As per Aging--" + req.getParameter("agingAsPerSlaPending"));
		if (req.getParameter("agingAsPerSlaPending") != null && req.getParameter("agingAsPerSlaPending") != "0"
				&& req.getParameter("agingAsPerSlaPending") != "") {
			logger.info("Inside Aging If ");
			String docNum = req.getParameter("agingAsPerSlaPending");
			if (docNum.contains(",")) {
				if (query.equals("")) {
					query += "ht.docketNumber IN(" + docNum.substring(0, docNum.length() - 1) + ")";
				} else {
					query += "AND ht.docketNumber IN(" + docNum.substring(0, docNum.length() - 1) + ")";
				}
			}

		}

		if (req.getParameter("complaintsAgingSla") != null && req.getParameter("complaintsAgingSla") != "0"
				&& req.getParameter("complaintsAgingSla") != "") {
			logger.info("Inside complaintsAgingSla If ");
			String docNumNew = req.getParameter("complaintsAgingSla");
			if (docNumNew.contains(",")) {
				if (query.equals("")) {
					query += "ht.docketNumber IN(" + docNumNew.substring(0, docNumNew.length() - 1) + ")";
				} else {
					query += "AND ht.docketNumber IN(" + docNumNew.substring(0, docNumNew.length() - 1) + ")";
				}
			}

		}

		logger.info(query);
		if (query != "") {
			List<?> list = genericDao.executeSimpleObjectQuery(
					"SELECT ht.docketNumber,ht.docketStatus,ht.docketCreatedDt,ht.resolvedDate,tc.categoryName,tsc.subCategoryName,ht.consumerName,ht.consumerMobileNo,ht.assignedTo,ht.estResolveTime,dn.dnName,ur.urName,ur.urContactNo FROM ProjectHeirarchyEntity ph,TicketCategoryEntity tc,TicketSubCategoryEntity tsc,HelpDeskTicketEntity ht,Users ur INNER JOIN ur.designationEntity dn WHERE ht.categoryId=tc.categoryId AND ht.subCategoryId=tsc.subCategoryId AND ph.siteCode=ht.siteCode AND ur.urLoginName=ht.assignedTo AND "
							+ query);
			logger.info(
					"SELECT ht.docketNumber,ht.docketStatus,ht.docketCreatedDt,ht.resolvedDate,tc.categoryName,tsc.subCategoryName,ht.consumerName,ht.consumerMobileNo,ht.assignedTo,ht.estResolveTime,dn.dnName,ur.urName,ur.urContactNo FROM ProjectHeirarchyEntity ph,TicketCategoryEntity tc,TicketSubCategoryEntity tsc,HelpDeskTicketEntity ht,Users ur INNER JOIN ur.designationEntity dn WHERE ht.categoryId=tc.categoryId AND ht.subCategoryId=tsc.subCategoryId AND ph.siteCode=ht.siteCode AND ur.urLoginName=ht.assignedTo AND "
							+ query);
			List<?> viewComplaints = helpDeskTicketService.getComplaints(list);
			model.addAttribute("search", viewComplaints);
		}
		HttpSession httpSession = req.getSession(false);
		int projectId = (Integer) httpSession.getAttribute("projectId");
		List<?> divisionsList = ticketCategoryService.getAllDivisions(projectId);
		List<?> circleList = ticketCategoryService.getAllCircles(projectId);
		List<?> categoryList = ticketCategoryService.getAllActiveCategories();

		model.addAttribute("helpdeskBean", new HelpDeskTicketEntity());
		model.addAttribute("divisionsList", divisionsList);
		model.addAttribute("circleList", circleList);
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("agingEscPending", helpDeskTicketService.getComplaintAgingForPendingResovtion());
		model.addAttribute("complaintsAging", helpDeskTicketService.getComplaintAgingStatistics());

		return "pgrs/slaComplaints";

	}

	@RequestMapping(value = "/readSupportTeamDateWiseDetails", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody List<?> readSupportTeamDateWiseDetails(HttpServletRequest req) throws ParseException {
		Date fdate = new SimpleDateFormat("dd/MM/yyyy").parse(req.getParameter("fromDate"));
		Date tdate = new SimpleDateFormat("dd/MM/yyyy").parse(req.getParameter("toDate"));
		return helpDeskTicketService.getSupportTeamBaseOnDate(fdate, tdate);
	}

	@RequestMapping(value = "/readMyComplaintDetails", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody List<?> getAllCustomersSupportteam(HttpServletRequest req) throws ParseException {

		HttpSession session = req.getSession(false);
		String userName = (String) session.getAttribute("userId");
		Date fdate = new SimpleDateFormat("dd/MM/yyyy").parse(req.getParameter("fromDate"));
		Date tdate = new SimpleDateFormat("dd/MM/yyyy").parse(req.getParameter("toDate"));
		return helpDeskTicketService.getSupportTeamBaseOnDate(fdate, tdate, userName);
	}

	@RequestMapping(value = "/myComplaints")
	public String indexMycomplaints(HttpServletRequest req, ModelMap model) {
		HttpSession session = req.getSession(false);
		String loginName = (String) session.getAttribute("userId");

		model.addAttribute("supprtTeam", helpDeskTicketService.getMycomplaints(loginName));

		return "pgrs/myComplaints";
	}

	@RequestMapping(value = "/readInAllDetailsWise", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody List<?> getAllCustomersSupportteamInDetailsWise(HttpServletRequest req) throws ParseException {
		Date fdate = null;
		Date tdate = null;
		Set<Integer> statusid = new HashSet<>();
		String uEmail = req.getParameter("uemail");
		String status = req.getParameter("uStatus");
		if (status.equalsIgnoreCase("total")) {
			statusid.add(1);
			statusid.add(2);
			statusid.add(3);
			statusid.add(4);
		} else {
			statusid.add(Integer.parseInt(status));
		}

		if (req.getParameter("fromDate") != null && req.getParameter("fromDate") != ""
				&& req.getParameter("toDate") != null && req.getParameter("toDate") != "") {
			fdate = new SimpleDateFormat("dd/MM/yyyy").parse(req.getParameter("fromDate"));
			tdate = new SimpleDateFormat("dd/MM/yyyy").parse(req.getParameter("toDate"));
		}

		if (fdate != null && tdate != null) {
			logger.info("Inside F and T date " + statusid + "----" + uEmail);
			return helpDeskTicketService.getteamDetails(fdate, tdate, statusid, uEmail);

		} else {
			logger.info("Inside Else Condtion----" + statusid + "----" + uEmail);
			return helpDeskTicketService.getTeamWiseMothlyWise(statusid, uEmail);

		}

	}

	@RequestMapping(value = "/readInAllMyComplaintDetails", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody List<?> readInAllMyComplaintDetails(HttpServletRequest req) throws ParseException {
		Date fdate = null;
		Date tdate = null;
		Set<Integer> statusid = new HashSet<>();
		String uEmail = req.getParameter("uemail");
		String status = req.getParameter("uStatus");
		if (status.equalsIgnoreCase("total")) {
			statusid.add(1);
			statusid.add(2);
			statusid.add(3);
			statusid.add(4);
		} else {
			statusid.add(Integer.parseInt(status));
		}

		if (req.getParameter("fromDate") != null && req.getParameter("fromDate") != ""
				&& req.getParameter("toDate") != null && req.getParameter("toDate") != "") {
			fdate = new SimpleDateFormat("dd/MM/yyyy").parse(req.getParameter("fromDate"));
			tdate = new SimpleDateFormat("dd/MM/yyyy").parse(req.getParameter("toDate"));
		}

		if (fdate != null && tdate != null) {
			logger.info("Inside F and T date " + statusid + "----" + uEmail);
			return helpDeskTicketService.getMyComplaintsDetails(fdate, tdate, statusid, uEmail);

		} else {
			logger.info("Inside Else Condtion----" + statusid + "----" + uEmail);
			return helpDeskTicketService.readInAllMyComplaintDetails(statusid, uEmail);

		}

	}

	@RequestMapping(value = "/helpDesk/pendingForRegistrationTicketsBasedOnSectionCode", method = { RequestMethod.GET,
			RequestMethod.POST })
	public @ResponseBody List<?> getAllPendingForSectionWise(HttpServletRequest req) {

		int status = 0;
		int id = Integer.parseInt(req.getParameter("id"));
		Set<String> projectIds = new HashSet<String>();
		ProjectHeirarchyEntity projectHeirarchyEntity = genericDao.getById(ProjectHeirarchyEntity.class, id);
		if (!(projectHeirarchyEntity.getType().equalsIgnoreCase("Zone")
				|| projectHeirarchyEntity.getType().equalsIgnoreCase("Projects"))) {
			projectIds.add(projectHeirarchyEntity.getSiteCode());
		}
		fetchChildren1(genericDao.getById(ProjectHeirarchyEntity.class, id), projectIds);

		return helpDeskTicketService.getDocketDetailsBasedOnStatusForPending(projectIds, status);
	}

	@RequestMapping("/helpDesk/download/{docketNo}")
	public String customerDocDownload(@PathVariable("docketNo") int docketNo, HttpServletResponse response)
			throws Exception {

		TicketDocumentsEntity documentsEntity = ticketDocumentService.findDocumentByDocketNo(docketNo);
		try {
			if (documentsEntity != null && documentsEntity.getDocument() != null) {

				response.setHeader("Content-Disposition", "inline;filename=\"" + documentsEntity.getDocname() + "\"");
				OutputStream out = response.getOutputStream();
				InputStream is = new ByteArrayInputStream(documentsEntity.getDocument());
				IOUtils.copy(is, out);
				out.flush();
				out.close();
			} else {
				OutputStream out = response.getOutputStream();
				IOUtils.write("File Not Found", out);
				out.flush();
				out.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
