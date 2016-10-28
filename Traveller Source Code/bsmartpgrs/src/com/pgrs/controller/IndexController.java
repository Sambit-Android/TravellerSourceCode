package com.pgrs.controller;

import java.io.IOException;
import java.security.Principal;
import java.text.ParseException;
import java.util.HashMap;
import java.util.HashSet;
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
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pgrs.beans.UserProfileBean;
import com.pgrs.dao.ICompanyDAO;
import com.pgrs.dao.IProjectDAO;
import com.pgrs.entity.HelpDeskTicketEntity;
import com.pgrs.service.HelpDeskTicketService;
import com.pgrs.service.IndexService;
import com.pgrs.service.TicketCategoryService;
import com.pgrs.service.UserService;
import com.pgrs.util.FilterUnit;
import com.pgrs.util.SessionData;

/**
 * Controller which includes all the common business logic concerned to all the modules
 * Module: All
 * 
 * @author Ravi Shankar Reddy
 * @version %I%, %G%
 * @since 0.1
 */

@Controller
public class IndexController  extends FilterUnit{
	
	private static final Log logger = LogFactory.getLog(IndexController.class);
		
	@Autowired
	private IndexService indexService;
	
	@Autowired
	private IProjectDAO projectDAO;
	
	@Autowired
	private ICompanyDAO companyDAO;
	
	@Autowired
	private TicketCategoryService ticketCategoryService;
	
	@Autowired
	private HelpDeskTicketService helpDeskTicketService;
	
	@Autowired
	UserService userService;
	
	public static int projectId = 0;
	public static int officeId = 0;
	public static int companyId = 0;
	public static String userId=null;
	public static String projectName="";
	public static String companyName="";
	
	double total=0.0d;
	double recieve=0.0d;
	
	int requestMasterNotification=0;
	int  recoupmentNotification=0;
	int notificationCountVar=0;
	
	
	@RequestMapping(value = "/registerTicket", method ={ RequestMethod.POST,RequestMethod.GET})
	public String registerTicket(@RequestParam String siteCode,@RequestParam String officerName,@RequestParam long mobileNo,@RequestParam String emailId,Model model, HttpServletRequest request,HttpServletResponse response,Principal principal) throws IOException, Exception {
		
		if(siteCode!= null && !siteCode.equals("")){
			
			HelpDeskTicketEntity ticketEntity = new HelpDeskTicketEntity();
			
			ticketEntity.setSiteCode(siteCode);
			ticketEntity.setUserName(officerName);
			ticketEntity.setConsumerName(officerName);
			ticketEntity.setConsumerMobileNo(mobileNo);
			ticketEntity.setConsumerEmailId(emailId);
			
			List<?> categoryList = ticketCategoryService.getAllActiveCategories();
			List<?> sectionsList = ticketCategoryService.getAllSections();
			
			model.addAttribute("helpdeskBean",ticketEntity);
			model.addAttribute("sectionsList",sectionsList);
			model.addAttribute("categoryList",categoryList);
			
			return "pgrs/registerTicket";
		} else{
			return "redirect:/siteCodeError";
		}
	}
	
	@RequestMapping(value = "/siteCodeError", method ={ RequestMethod.POST,RequestMethod.GET})
	public String siteCodeError(Model model, HttpServletRequest request,HttpServletResponse response,Principal principal) throws IOException, Exception {
		return "pgrs/siteCodeError";
	}
	
	@RequestMapping(value = "/index", method ={ RequestMethod.POST,RequestMethod.GET})
	public String index(Model model, HttpServletRequest request,HttpServletResponse response,Principal principal) throws IOException, Exception {
		
		logger.info("In side index method");
		
		try{
			indexService.setMenu(request,principal);
			indexService.setIndexDetails(model,request);
			
		}catch(Exception e){
			e.printStackTrace();
			return "redirect:/logout";
		}
		HttpSession httpSession =  request.getSession(false);
		userId=(String) SessionData.getUserDetails().get("userID");
		
		UserProfileBean userBean = userService.getUserDetailsByLoginName(userId);
		
		httpSession.setAttribute("userContactNo",userBean.getContactNo());
		httpSession.setAttribute("userDeptName",userBean.getDeptName());
		httpSession.setAttribute("userOfficeName",userBean.getOfficeName());
		httpSession.setAttribute("userEmailId",userBean.getEmailId());
		httpSession.setAttribute("userName",userBean.getUserName());
		httpSession.setAttribute("designation", userBean.getDesgName());
		
		officeId = (Integer)httpSession.getAttribute("officeId");
		projectId = (Integer)httpSession.getAttribute("projectId");
		companyId = (Integer)httpSession.getAttribute("companyId");
		projectName = projectDAO.getProjectNameBasedOnId(projectId);
		companyName = companyDAO.getCompanyNameBasedOnId(companyId);
		String sitecode=userService.getSiteCodeOnUserId(userId);
		if(sitecode != null && !sitecode.isEmpty()){
			request.getSession(false).setAttribute("sitecode",sitecode);	
		}
		
		if(userBean.getDeptName().equals("CCCD") || userBean.getDeptName().equals("Administration") || userBean.getDeptName().equalsIgnoreCase("Service Station")){
			
			List<?> categoryList = ticketCategoryService.getAllActiveCategories();
			List<?> sectionsList = ticketCategoryService.getAllSections();
			
			int registrationPendingCount = helpDeskTicketService.getPendingTicketsCount(3);
			int reopenedCount = helpDeskTicketService.getPendingTicketsCount(4);
			httpSession.setAttribute("notificationName1","Resolved");
			httpSession.setAttribute("notificationCount1",registrationPendingCount);
			httpSession.setAttribute("notificationUrl1","pendingForRegistrationNotifications()");
			
			int escalationCount = helpDeskTicketService.getescalatedTicketsCount();
			httpSession.setAttribute("notificationName2","Escalated");
			httpSession.setAttribute("notificationCount2",escalationCount);
			httpSession.setAttribute("notificationUrl2","showAllEscalatedNotificationsForCCCD()");
			
			int totalCount = registrationPendingCount+escalationCount;
			httpSession.setAttribute("totalCount",totalCount);
			
			Map<String,String> complaintAgingList = helpDeskTicketService.getComplaintAgingStatistics();
			Map<String,Integer> dayCounter = helpDeskTicketService.getDayWiseComplaints();
			
			Map<String,String> pendingForReslAging=helpDeskTicketService.getComplaintAgingForPendingResovtion();
			
			model.addAttribute("comPlaintAgingForPending", pendingForReslAging);
			model.addAttribute("complaintAgingList",complaintAgingList);
			model.addAttribute("dayCounter",dayCounter);
			model.addAttribute("helpdeskBean",new HelpDeskTicketEntity());
			model.addAttribute("sectionsList",sectionsList);
			model.addAttribute("categoryList",categoryList);
			model.addAttribute("reopenedCount", reopenedCount);
			return "usermanagement/index";	
			
		}else {
			
			HttpSession session = request.getSession(false);
	   		String loginName = (String)session.getAttribute("userId");
			
			List<?> categoryList = ticketCategoryService.getAllActiveCategories();
			
			Set<Integer> registeredSetNotification = new HashSet<Integer>();
			registeredSetNotification.add(1);
			registeredSetNotification.add(2);
			registeredSetNotification.add(4);
			
			int registeredTicketsCount = helpDeskTicketService.getTicketsCountBasedOnStatus(registeredSetNotification,userId);
			
			Set<Integer> resolvedSetNotification = new HashSet<Integer>();
			resolvedSetNotification.add(3);
			int resolvedTicketsCount = helpDeskTicketService.getTicketsCountBasedOnStatus(resolvedSetNotification,userId);
			int escalationCount = helpDeskTicketService.getescalatedTicketsCountBasedOnLogin(loginName);
			
			httpSession.setAttribute("notificationName1","Assigned");
			httpSession.setAttribute("notificationCount1",registeredTicketsCount);
			httpSession.setAttribute("notificationUrl1","registeredAssignedNotifications()");
			
			httpSession.setAttribute("notificationName2","Escalated");
			httpSession.setAttribute("notificationCount2",escalationCount);
			httpSession.setAttribute("notificationUrl2","escalatedNotifications()");
			
			httpSession.setAttribute("notificationName3","Resolved");
			httpSession.setAttribute("notificationCount3",resolvedTicketsCount);
			httpSession.setAttribute("notificationUrl3","showAllResolvedTickets()");
			
			int totalCount = registeredTicketsCount+escalationCount;
			
			httpSession.setAttribute("totalCount",totalCount);
			model.addAttribute("helpdeskBean",new HelpDeskTicketEntity());
			model.addAttribute("categoryList",categoryList);
			Set<Integer> statusSet = new HashSet<Integer>();
			statusSet.add(1);
			statusSet.add(2);
			statusSet.add(4);
			model.addAttribute("allTickets",helpDeskTicketService.docketDetailsRead(loginName,statusSet));
			
			return "pgrs/myDockets";
		}	
			
			
		/*}else {
			
			String getdesignation=getUserDesignation(request);
			logger.info("########"+getdesignation);
			Long appReceived		=	applicationService.getApplicationCountByStatus(0);
			Long appInProgress 		=	applicationService.getApplicationCountByStatus(1);
			Long appCompleted		=	applicationService.getApplicationCountByStatus(2);
			Long appPending			=	applicationService.getApplicationCountByStatus(3);
			
			
			List<?> divisionsList = ticketCategoryService.getAllDivisions(projectId);
			model.addAttribute("divisionsList",divisionsList);
			
			model.addAttribute("applicationReceived", appReceived);
			model.addAttribute("applicationInProgress", appInProgress);
			model.addAttribute("applicationCompleted", appCompleted);
			model.addAttribute("applicationPending", appPending);
			 sitecode=sitecode+"%";
			for    Installation service
			    Long serviceInProgress=	applicationService.getApplicationCountByFinalStatusWOSCode(StaticProperties.WCR_Code);
				Long serviceCompleted=	applicationService.getApplicationCountByFinalStatusWOSCode(StaticProperties.SER_Code);
				Long servicePending	=	applicationService.getApplicationCountByFinalStatusWOSCode(StaticProperties.SER_Code_Pend);
				
				model.addAttribute("serviceInProgress", serviceInProgress);
				model.addAttribute("serviceCompleted", serviceCompleted);
				model.addAttribute("servicePending", servicePending);
		        
				for    wcr Po
				    Long wcrInProgress=	applicationService.getApplicationCountByFinalStatusWOSCode(StaticProperties.MPO_Code);	
					Long wcrPending	=	applicationService.getApplicationCountByFinalStatusWOSCode(StaticProperties.WCR_Code_Pend);				
					model.addAttribute("wcrInProgress", wcrInProgress);					
					model.addAttribute("wcrPending", wcrPending);
					
					
					for    Meter Po
					
					 Long meterPOInProgress=	applicationService.getApplicationCountByFinalStatusWOSCode(StaticProperties.WO_Code);				
						Long meterPOPending	=	applicationService.getApplicationCountByFinalStatusWOSCode(StaticProperties.MPO_Code_Pend);
						
						model.addAttribute("meterPOInProgress", meterPOInProgress);						
						model.addAttribute("meterPOPending", meterPOPending);
						
						for    Work Order Po
						 Long workOrderInProgress=	applicationService.getApplicationCountByFinalStatusWOSCode(StaticProperties.DEP_Code);
							
							Long workOrderPending	=	applicationService.getApplicationCountByFinalStatusWOSCode(StaticProperties.WO_Code_Pend);
							
							model.addAttribute("workOrderInProgress", workOrderInProgress);							
							model.addAttribute("workOrderPending", workOrderPending);
							
							Long depInProgress=	applicationService.getApplicationCountByFinalStatusWOSCode(StaticProperties.PS_Code);						
							Long depPending	=	applicationService.getApplicationCountByFinalStatusWOSCode(StaticProperties.DEP_Code_Pend);
							
							model.addAttribute("depInProgress", depInProgress);
							model.addAttribute("depPending", depPending);
							
							
							Long powerSancInProgress=	applicationService.getApplicationCountByFinalStatusWOSCode(StaticProperties.EST_Code);					
							Long powerSancpding	=	applicationService.getApplicationCountByFinalStatusWOSCode(StaticProperties.PS_Code_Pend);
							
							model.addAttribute("powerSancInProgress", powerSancInProgress);							
							model.addAttribute("powerSancpding", powerSancpding);
							
							 Long estInProgress=	applicationService.getApplicationCountByFinalStatusWOSCode(StaticProperties.FV_Code);							
							 Long estPending	=	applicationService.getApplicationCountByFinalStatusWOSCode(StaticProperties.EST_Code_Pend);							
								model.addAttribute("estInProgress", estInProgress);								
								model.addAttribute("estPending", estPending);
								
								 Long fvProgress=	applicationService.getApplicationCountByFinalStatusWOSCode(StaticProperties.APPACC_Code);									
								 Long fvPending	=	applicationService.getApplicationCountByFinalStatusWOSCode(StaticProperties.FV_Code_Pend);
									
									model.addAttribute("fvProgress", fvProgress);
									model.addAttribute("fvPending", fvPending);
									
									
									Long appAccInProgress=	applicationService.getApplicationCountByFinalStatusWOSCode(StaticProperties.APPREG_Code);
									
									Long appAccPending	=	applicationService.getApplicationCountByFinalStatusWOSCode(StaticProperties.APPACC_Code_Pend);
									
									model.addAttribute("appAccInProgress",appAccInProgress);
									model.addAttribute("appAccPending", appAccPending);
									
			
			return "newConnection/empOnBoardDashboard";
		}*/
	}
	
	@RequestMapping(value = "/users/indexdetails/{username}/{officeid}/{projectid}", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> getIndexamount(@PathVariable String username,@PathVariable int officeid,@PathVariable int projectid) {
		Map<String, Object>  indexlist = indexService.getindexmobile(username,officeid,projectid);
	    return  indexlist;
	}
	
	
	
/*	@RequestMapping(value = "/index", method ={ RequestMethod.POST,RequestMethod.GET})
	public String index(Model model, HttpServletRequest request,HttpServletResponse response,Principal principal) throws IOException, Exception {
		
		logger.info("In side index method");
		
		try{
			indexService.setMenu(request,principal);
			indexService.setIndexDetails(model,request);
			
		}catch(Exception e){
			e.printStackTrace();
			return "redirect:/logout";
		}
		HttpSession httpSession =  request.getSession();
		officeId = (Integer)httpSession.getAttribute("officeId");
		projectId = (Integer)httpSession.getAttribute("projectId");
		companyId = (Integer)httpSession.getAttribute("companyId");
		
		projectName = projectDAO.getProjectNameBasedOnId(projectId);
		companyName = companyDAO.getCompanyNameBasedOnId(companyId);		
		
		return "customerManagement/index";
	}
	*/
	
	
	@RequestMapping(value = "/timeLine", method ={ RequestMethod.POST,RequestMethod.GET})
	public String timelineSubmit(Model model, HttpServletRequest request,HttpServletResponse response,Principal principal) throws IOException, Exception {
		
		logger.info("Inside timelineSubmit method");
		model.addAttribute("modulename","Manage TimeLine");	
		return "redirect:/timeLinePage";
	}
	
	@RequestMapping(value = "/timeLinePage", method ={ RequestMethod.POST,RequestMethod.GET})
	public String timeLinePage(Model model, HttpServletRequest request,HttpServletResponse response,Principal principal) throws IOException, Exception {
		
		logger.info("Inside timeLinePage method");
		model.addAttribute("modulename","Manage TimeLine");	
		return "customerManagement/timeLine";
	}
	public String getUserDesignation(HttpServletRequest request){
		
		String userId=(String) SessionData.getUserDetails().get("userID");
		String userDesignation=userService.getUserDesignationOnLoginName(userId);
		request.getSession(false).setAttribute("userDesignation",userDesignation);
		logger.info("::::::::::::userDesignation:::::::::::"+userDesignation);
		
		
	return userDesignation;
	}
	
	/*@RequestMapping(value = "/projectSelected", method ={ RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public List<?> timeline(Model model,ModelMap map, HttpServletRequest request,HttpServletResponse response) throws IOException, Exception {
		
		String project=request.getParameter("project");
		logger.info("project Selected:::::::::::::"+project);
		request.getSession(false).setAttribute("userLoggedIn",project);
	
	return null;
	}*/
	
	/**
	 * Added By raju For Notifications
	 * 
	 */
	
	@RequestMapping(value="/readNotifyEveryMinute",method={RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody Map<String,Object> getEveryminNotify(HttpServletRequest request,ModelMap model){
		
		String userIdNew = (String)SessionData.getUserDetails().get("userID");
		Map<String,Object> getData=new HashMap<String, Object>();
		
		try
		{
		

		UserProfileBean userBean = userService.getUserDetailsByLoginName(userIdNew);				
		if(userBean.getDeptName().equals("CCCD") || userBean.getDeptName().equals("Administration")){
			
			int registrationPendingCount = helpDeskTicketService.getPendingTicketsCount(3);
			int reopenedCount = helpDeskTicketService.getPendingTicketsCount(4);
			getData.put("notificationName1","Resolved");
			getData.put("notificationCount1",registrationPendingCount);
			getData.put("notificationUrl1","pendingForRegistrationNotifications()");
			
			int escalationCount = helpDeskTicketService.getescalatedTicketsCount();
			getData.put("notificationName2","Escalated");
			getData.put("notificationCount2",escalationCount);
			getData.put("notificationUrl2","showAllEscalatedNotificationsForCCCD()");
			
			int totalCount = registrationPendingCount+escalationCount;
			getData.put("totalCount",totalCount);
		
			getData.put("reopenedCount", reopenedCount);
		}else {
			
			HttpSession session = request.getSession(false);
	   		String loginName = (String)session.getAttribute("userId");
			
			Set<Integer> registeredSetNotification = new HashSet<Integer>();
			registeredSetNotification.add(1);
			registeredSetNotification.add(4);
			
			int registeredTicketsCount = helpDeskTicketService.getTicketsCountBasedOnStatus(registeredSetNotification,userId);
			
			Set<Integer> resolvedSetNotification = new HashSet<Integer>();
			resolvedSetNotification.add(3);
			int resolvedTicketsCount = helpDeskTicketService.getTicketsCountBasedOnStatus(resolvedSetNotification,userId);
			int escalationCount = helpDeskTicketService.getescalatedTicketsCountBasedOnLogin(loginName);
			
			getData.put("notificationName1","Assigned");
			getData.put("notificationCount1",registeredTicketsCount);
			getData.put("notificationUrl1","registeredAssignedNotifications()");
			
			getData.put("notificationName2","Escalated");
			getData.put("notificationCount2",escalationCount);
			getData.put("notificationUrl2","escalatedNotifications()");
			
			getData.put("notificationName3","Resolved");
			getData.put("notificationCount3",resolvedTicketsCount);
			getData.put("notificationUrl3","showAllResolvedTickets()");
			
			int totalCount = registeredTicketsCount+escalationCount;
			
			getData.put("totalCount",totalCount);
		}
	
		return getData;
		}catch(Exception e){
			return null;
		}
	}
	@RequestMapping(value = "/index/showTodayComplaints", method = RequestMethod.GET)
	public @ResponseBody List<?> showTodayComplaints(HttpServletRequest req) throws ParseException
	{
		Integer status=Integer.parseInt(req.getParameter("status"));
		List<?> result=helpDeskTicketService.getTodayComplaints(status);
		return result;
	}
}
