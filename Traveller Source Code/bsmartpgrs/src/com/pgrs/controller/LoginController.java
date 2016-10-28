package com.pgrs.controller;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.novell.ldap.LDAPException;
import com.pgrs.core.dao.MasterGenericDAO;
import com.pgrs.dao.IUsersDAO;
import com.pgrs.entity.Company;
import com.pgrs.entity.Project;
import com.pgrs.ldap.LdapBusinessModel;
import com.pgrs.service.DashboardService;
import com.pgrs.service.ProjectService;
import com.pgrs.service.UserService;
import com.pgrs.util.EmailCredentialsDetailsBean;
import com.pgrs.util.FilterUnit;
import com.pgrs.util.ForgotPasswordSendMailThread;
import com.pgrs.util.ForgotPasswordSendThroughSMS;
import com.pgrs.util.SmsCredentialsDetailsBean;

/**
 * @author Ravi Shankar Reddy
 *
 */
@Controller
public class LoginController extends FilterUnit{
	
	@Autowired
	private LdapBusinessModel ldapBusinessModel;
	
	@Autowired
	private DashboardService dashboardService;
	
	@Autowired
	private MasterGenericDAO genericDAO; 
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private IUsersDAO iUsersDAO;
	
	private static final Log logger = LogFactory.getLog(LoginController.class);

	/* ------------------- Manage Login -----------------------*/	 
	
	
	@RequestMapping(value = { "/", "/login" }, method ={ RequestMethod.POST,RequestMethod.GET})
	public String loginpage(Model model, HttpServletRequest request,HttpServletResponse response) throws IOException {
		logger.info("Accessing Login Page");
		return "usermanagement/login";
	}
	
	
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request,HttpServletResponse response)
	{		
		logger.info("User Logging Out");		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		SecurityContextHolder.getContext().setAuthentication(null);
		
		return "usermanagement/login";		
	}
	
	@RequestMapping("/lock")
	public String lock(Model model,HttpServletRequest request,HttpServletResponse response,ModelMap model1) throws IOException
	{	
		HttpSession session = request.getSession(true);
		String userName = session.getAttribute("userId").toString();
		model1.addAttribute("userName",userName);
		return "usermanagement/lock";		
	}
	
	/**
	 * Display User Image
	 * 
	 * @param response : none
	 * @param request : none
	 * @throws Exception 
	 */
	@RequestMapping("/getprofileimage")
	public void getProfileImage(HttpServletResponse response,HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession(true);
		
		dashboardService.getProfileImage(session.getAttribute("userId").toString(),response);
	}
	
	/**
	 * Display User Image
	 * 
	 * @param response : none
	 * @param request : none
	 * @throws Exception 
	 */
	@RequestMapping("/lockrelease")
	public String lockRelease(Model model,HttpServletResponse response,HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession(true);
		String str=ldapBusinessModel.loginCheck(session.getAttribute("userId").toString(), request.getParameter("password").toString());
		if(str.equals("Successfully Logged in")){
			return "redirect:/index";
		}else{
			model.addAttribute("msg","Password is Wrong");
			return "usermanagement/lock";	
		}
	}
	
	@RequestMapping(value = {"/403","/accessDenied"})
	public String errorpage403(Model model,HttpServletRequest request,HttpServletResponse response) throws IOException
	{		
		return "errorpages/403";		
	}
	@RequestMapping("/500")
	public String errorpage500(Model model,HttpServletRequest request,HttpServletResponse response) throws IOException
	{		
		return "errorpages/500";		
	}
	@RequestMapping("/404")
	public String errorpage404(Model model,HttpServletRequest request,HttpServletResponse response) throws IOException
	{		
		return "errorpages/404";		
	}
	
	@RequestMapping("/setopenstatus")
	public void setopenstatus(HttpServletRequest request,HttpServletResponse response) throws IOException
	{		
		HttpSession session = request.getSession(true);
		session.setAttribute("openstatus", request.getParameter("status"));		
	}
	
	@RequestMapping("/changeProject/{company}/{project}")
	public String changeProject(@PathVariable String company,@PathVariable String project, HttpServletRequest request,HttpServletResponse response) throws IOException
	{		
		HttpSession session = request.getSession(true);
		session.setAttribute("companyName", company);
		session.setAttribute("projectName", project);
		return "redirect:/index";
	}
	
	@RequestMapping("/changeCompany/{company}")
	public String changeCompany(@PathVariable String company, HttpServletRequest request,HttpServletResponse response) throws IOException
	{		
		HttpSession session = request.getSession(true);
		session.setAttribute("companyName", company);
		List<Company> str=genericDAO.executeSimpleQuery("Select model from Company model where model.companyName='"+company+"'");
		int comapnyId=str.get(0).getId();
		session.setAttribute("companyId", comapnyId);
		String userName=(String) session.getAttribute("userId");
		List<Project> project=userService.getProjects(userName);
		session.setAttribute("projectName", project.get(0).getName());
		return "redirect:/index";
	}
	
	/** ------------------- End ----------------------- */
	//***********************************MOBILE LOGIN
		@RequestMapping(value = "/mobileLogin" , method = RequestMethod.POST)
		
			 public @ResponseBody String mobileLogin( HttpServletRequest request,
			 HttpServletResponse response) throws IOException {
			 LdapBusinessModel businessModel = new LdapBusinessModel();
			 String status=businessModel.loginCheckformobile( request.getParameter("userMailId")	, request.getParameter("password"));
			 	if(status == null){
			 		status="invalid";
			 	}
			 	else{
			 		if(status == ""){
					status="invalid";
				    }
				   else{
					   
					status="success";
				  }
			 		}
			  return status;
			 }
		
		@RequestMapping(value = "projects/mobile", method = RequestMethod.GET)
		public @ResponseBody List<?> readProjects(HttpServletRequest req)
		{	
			List<Project> userDetails = projectService.getProjects();
		    return  userDetails;
		}
		
		/*@RequestMapping("/users/getImage/{username}")
				public void getImageForPreRegisteredView(Model userName, HttpServletRequest request,HttpServletResponse response, @PathVariable String username)	throws Exception {
					response.setContentType("image/jpeg");
					int user_id = userService.getUserIdbasedonUsername(username);
					Blob blobImage = userService.getImageformobile(user_id);
					int blobLength = 0;
					byte[] blobAsBytes = null;
					if (blobImage != null) {
						blobLength = (int) blobImage.length();
						blobAsBytes = blobImage.getBytes(1, blobLength);
					}
					OutputStream ot = response.getOutputStream();
					try {
						ot.write(blobAsBytes);
						ot.close();
					} catch (Exception e) {
						//e.printStackTrace();
					}
					//return null;
		}*/
		
		@RequestMapping("/changeGridTheme")
		public String changeGridTheme(HttpServletRequest request,HttpServletResponse response){
			String theme = request.getParameter("theme");
			String themeName = request.getParameter("themeName");
			HttpSession session = request.getSession(false);
			session.setAttribute("themeName", theme);
			session.setAttribute("theme", themeName);
			
			String username= (String) session.getAttribute("userId");			
			userService.updateTheme(username,themeName);
			
			return "redirect:/index";
			
		}
		
		@RequestMapping(value = {"/forgotpassword" }, method ={ RequestMethod.POST,RequestMethod.GET})
		public String forgotpassword(Model model, HttpServletRequest request,HttpServletResponse response) throws IOException {
			logger.info("Accessing Forgotpassword Page");
			return "usermanagement/forgotpassword";
		}
		
		/**
		 * Forgot Password
		 * 
		 * @param map : none
		 * @param model : none 
		 * @param locale : System Local
		 * @return Users Password successful Message
		 * @throws LDAPException 
		 */
		@RequestMapping(value = "/recoverPassword/{urLoginName}/{urEmail}/{mobileNo}", method ={ RequestMethod.POST,RequestMethod.GET})
		public @ResponseBody String recoverPassword(@PathVariable String urLoginName,@PathVariable String urEmail,@PathVariable String mobileNo,Model model,HttpServletRequest req,final Locale locale) throws LDAPException {
			
			logger.info("Accessing recoverPassword method");	
			String userLoginName = urLoginName.trim();
			List<String> testUserName = iUsersDAO.userNameValidation(userLoginName,urEmail,mobileNo);
			
			if(testUserName.isEmpty()){
				return "falseInvalid user,Please enter valid details.";
			}else{
				
				Object[] usersData = iUsersDAO.getUserDetailsByLoginName(userLoginName.trim()); 
				String userName = (String)usersData[1];
				String emailId = (String)usersData[4];
				String contactNo = (String)usersData[3];
				
				Map<String, String> data = ldapBusinessModel.getPassword(userLoginName,emailId,contactNo);
				String password = data.get("Password");
				String errorMessage = data.get("Error");
				if (errorMessage == null || errorMessage.equals("")) {

					logger.info("Forgot Password:Sending Mail To User");
					
					SmsCredentialsDetailsBean smsCredentialsDetailsBean = new SmsCredentialsDetailsBean();
					EmailCredentialsDetailsBean emailCredentialsDetailsBean = null;
					try {
						emailCredentialsDetailsBean = new EmailCredentialsDetailsBean();
					} catch (Exception e) {
						e.printStackTrace();
					}				
					emailCredentialsDetailsBean.setToAddressEmail(emailId);
					new Thread(new ForgotPasswordSendMailThread(emailCredentialsDetailsBean,userLoginName,password,userName)).start();
					
					smsCredentialsDetailsBean.setNumber(""+contactNo);
					smsCredentialsDetailsBean.setUserName(userName);
					new Thread(new ForgotPasswordSendThroughSMS(smsCredentialsDetailsBean,userLoginName,password)).start();
					
					return "trueYour Credentials are Sent to Mail Id : '" + emailId+"'";
					
					} else {
					logger.info("Forgot Password:Printing Error Message");
					return "false"+errorMessage ;
				}
			}
		}

		
		
		
		/** ------------------- End ----------------------- */
		
	}
