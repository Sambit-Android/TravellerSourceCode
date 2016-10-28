package com.bcits.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bcits.entity.MRCreditMasterEntity;
import com.bcits.entity.MRDeviceAllocationEntity;
import com.bcits.entity.SchemaValue;
import com.bcits.entity.User;
import com.bcits.service.MRCreditMasterService;
import com.bcits.service.MRDeviceAllocationService;
import com.bcits.service.MRMasterService;
import com.bcits.service.ModuleMasterService;
import com.bcits.service.UserService;
import com.bcits.utility.BCITSLogger;
import com.bcits.utility.EncoderUtility;
import com.bcits.utility.Encryption;

@Controller
//@ImportResource("classpath:applicationContext.xml")
//@PropertySource("classpath:application.properties")
public class LoginController
{

	@Autowired
	private UserService userService;
	
	@Autowired
	private ModuleMasterService moduleMasterService;
	
	@Autowired
	private MRMasterService mrMasterService;

	/*@Autowired
	private DataSource dataSource;*/
	
	@Autowired
	private MRCreditMasterService mrCreditMasterService;
	
	/*@Resource
    private Environment environment;*/
	
	@Autowired
	private EncoderUtility encoderUtility;
	
	
	@Autowired
	private MRDeviceAllocationService deviceAllocationService;
	
	@Autowired
	private Encryption encryption; 
	
	@RequestMapping(value = "/", method = { RequestMethod.POST,
			RequestMethod.GET })
	public String index(ModelMap model,HttpServletRequest request)

	{
		//System.out.println(environment.getRequiredProperty("url")+" hhhhhoi");
		HttpSession session=request.getSession(false);
		session.removeAttribute("userLogout");
		model.put("user", new User());
		model.put("msg", "notDisplay");
		String sessionValue=request.getParameter("sessionVal");
		if(sessionValue!=null)
		{
			model.addAttribute("msg", "Your session has been Expired Please login");
		}
		return "login";
	}
	
	@RequestMapping(value="/login",method={RequestMethod.POST,RequestMethod.GET})
	public String login(@ModelAttribute("user") User user,ModelMap model,HttpServletRequest request,HttpSession session)
	{
		System.out.println("username : "+user.getUserMailId());
		String afterLoginPage = userService.afterLogin(user.getUserMailId(), encryption.encrypt(user.getUserPassword()),session,model);
		model.put("user", new User());
		return afterLoginPage;
	}
	
	
	@RequestMapping(value="/loginBySdocode",method={RequestMethod.POST,RequestMethod.GET})
	public String loginBySdo(@ModelAttribute("user") User user,ModelMap model,HttpServletRequest request,HttpSession session)
	{
		BCITSLogger.logger.info("dbUserName-----------------> : "+request.getParameter("dbUserName"));
		String userMailId=request.getParameter("dbUserName")+"@bcits.co.in";
		String userPassword="bcits";
		String afterLoginPage="";
		List<User> list = userService.findAll(userMailId, encryption.encrypt(userPassword));
		if(list.size() > 0)
		{
			 afterLoginPage = userService.afterLogin(userMailId, encryption.encrypt(userPassword),session,model);
		}
		else
		{
			User userObject=new User();
			userObject.setUserMailId(userMailId);
			userObject.setUserPassword(encryption.encrypt(userPassword));
			userObject.setUserCreatedDate(new Date());
			userObject.setUserType("ADMIN_SUBDIVISION");
			userObject.setUsername(request.getParameter("dbUserName"));
			userObject.setUserStatus("ACTIVE");
			userObject.setUserLevel("NONE");
			byte[] bFile = userService.defaultImage(request);
			userObject.setImage(bFile);
	        userService.save(userObject);
	        afterLoginPage = userService.afterLogin(userMailId, encryption.encrypt(userPassword),session,model);
			
		}
		model.put("user", new User());
		return afterLoginPage;
	}
	
	@RequestMapping(value="/logout",method=RequestMethod.GET)
	public String logout(ModelMap model,HttpServletRequest request)
	{
		
		model.put("user", new User());		
	
		HttpSession session=request.getSession();
		String usertype=(String)session.getAttribute("userType");
		if(usertype!=null)
		{
			if(usertype.equalsIgnoreCase("ADMIN_SUBDIVISION"))
			{
				session.setAttribute("userLogout", "userLogout");
				model.put("msg", "You are successfully logged out.Please close the browser.");
			}
			if(usertype.equalsIgnoreCase("ADMIN")||usertype.equalsIgnoreCase("USER"))
			{
				model.put("msg", "notDisplay");
			}
		}
		session.removeAttribute("userName");
		session.removeAttribute("userType");
		session.removeAttribute("path");
		session.removeAttribute("userId");
		session.removeAttribute("userNumber");	
		session.removeAttribute("userLevel");
		session.removeAttribute("userMailId");		
		session.removeAttribute("sdoVal");
		session.removeAttribute("sdocodeval");
		session.removeAttribute("sectionVal");
		session.removeAttribute("projectName");
		//session.invalidate();	
		//session.removeAttribute("userLogout");
		
		return "login";	
	}
	@RequestMapping(value="/logoutnew",method=RequestMethod.GET)
	public String logoutnew(ModelMap model,HttpServletRequest request)
	{
		return "logoutnew";
	}
	
	@RequestMapping(value="/lockScreen",method=RequestMethod.GET)
	public String lockScreen(ModelMap model,HttpServletRequest request)
	{
		BCITSLogger.logger.info("In Lock Screen");
		model.put("userLock",new User());		
		return "lockScreen";
	}
	
	
	@RequestMapping(value="/unlock",method=RequestMethod.POST)
	public String unlock(@RequestParam("password") String password,ModelMap model,HttpServletRequest request) 
	{
		BCITSLogger.logger.info("in unLock method "+password);
		String lockResult=userService.unLockScreen(password, request,model);		
		return lockResult;
	}
	
	
/*	//MOBLIE MVS LOGIN
	@RequestMapping(value="/loginMobileUser",method=RequestMethod.POST)
    public @ResponseBody Object loginMobileUser(@RequestBody String user_List,HttpServletRequest request) throws JSONException
    {
    	Connection c=null;
		try 
		{
			 c=dataSource.getConnection();
			  request.getSession().setAttribute("dbmsName",c.getMetaData().getDatabaseProductName());
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
    	
    	String  userName=null;
		String  passWord=null;
		String  sdoCode=null;
		
		List<MRMasterEntity> list=null;
		JSONArray arr = new JSONArray(user_List);
		for (int i = 0; i < arr.length(); i++)
    	{
			JSONObject Asst_json_obj = arr.getJSONObject(i);
			userName =	Asst_json_obj.getString("USERNAME");  
			passWord =	Asst_json_obj.getString("PASSWORD");
			sdoCode = Asst_json_obj.getString("SDOCODE");					
    	}
		
		if(request.getSession().getAttribute("dbmsName")!=null)
		{
		    list= mrMasterService.findMobileUser(userName, passWord, Integer.parseInt(sdoCode));		
		    if(list.size()>0)
		    {
		    	return list;
		    }
		    else{
		    	return "no_user";
		    }
		}
		else
		{
			
			return "networkError";
		}
		
    }
	*/
	
	/*@RequestMapping(value="/loginMobilePaymentUser",method=RequestMethod.POST)
	public String loginMobilePaymentUser(@RequestParam("MRCODE") String mrcode,@RequestParam("PASSWORD") String password,@RequestParam("SDOCODE") String sdocode,ModelMap model,HttpServletRequest request) 
	{

		
		return null;
	}*/
	
	//Old Login Method For Login Mobile
	/*@RequestMapping(value = "/loginMobileMvsUser", method = RequestMethod.POST)
	public @ResponseBody
	Object loginMobileUser(@RequestBody String user_List) throws JSONException {

		JSONArray arr = new JSONArray(user_List);

		JSONObject Asst_json_obj = arr.getJSONObject(0);
		String mrcode = Asst_json_obj.getString("MRCODE");
		String password = Asst_json_obj.getString("PASSWORD");
		String sdocode = Asst_json_obj.getString("SDOCODE");
		String status = mrMasterService.validateForMrMaster(mrcode, password);
		BCITSLogger.logger.info("in unLock method password >>" + password
				+ "mrcode >> " + mrcode + " sdocode >>" + sdocode
				+ "status ..." + status);
		String availableCredit = "NotValid";
		if (status.equalsIgnoreCase("1")) {
			availableCredit="valid";
			
		}  

		return availableCredit;
	}*/
	
	
	@RequestMapping(value = "/loginMobileMvsUser", method = {RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody
	Object loginMobileUser(@RequestBody String user_List) throws JSONException {

		JSONArray arr = new JSONArray(user_List);

		JSONObject Asst_json_obj = arr.getJSONObject(0);
		String mrcode = Asst_json_obj.getString("MRCODE");
		String password = Asst_json_obj.getString("PASSWORD");
		String sdocode = Asst_json_obj.getString("SDOCODE");
		String status = mrMasterService.validateForMrMaster(mrcode, password);
		BCITSLogger.logger.info("in unLock method password >>" + password
				+ "mrcode >> " + mrcode + " sdocode >>" + sdocode
				+ "status ..." + status);
		String availableCredit = "NotValid";
		if (status.equalsIgnoreCase("1")) {
			List<MRDeviceAllocationEntity> list=deviceAllocationService.findSdoCodeByMrcode(mrcode);
			//BCITSLogger.logger.info("loginMobileUser"+sdo);
			if(list.size()>0)
			{ 
				BCITSLogger.logger.info("loginMobileUser"+list.get(0).getSdoCode());
				if(list.get(0).getSdoCode()==Integer.parseInt(sdocode)){
				availableCredit="valid";
				}
			}
		
			
		}  

		return availableCredit;
	}
	@RequestMapping(value = "/getCreditLimitMobilePaymentUser", method = RequestMethod.POST)
	public @ResponseBody
	Object getCreditLimitMobilePaymentUser(@RequestBody String user_List) throws JSONException {

		JSONArray arr = new JSONArray(user_List);

		JSONObject Asst_json_obj = arr.getJSONObject(0);
		String mrcode = Asst_json_obj.getString("MRCODE");
		String sdocode = Asst_json_obj.getString("SDOCODE");
		BCITSLogger.logger.info("sdocode" + sdocode+" mrcode"+mrcode);
		String availableCredit="No Credit limit";
			List<MRCreditMasterEntity> list = mrCreditMasterService
					.findAllByMrcode(mrcode,sdocode);
			BCITSLogger.logger.info("list" + list.size());
			if (list.size() > 0) {
				availableCredit = list.get(0).getAvailCredit() + "";
				BCITSLogger.logger.info("availableCredit" + availableCredit);

				int status1=mrCreditMasterService.updateStatusMobSynchMobile(mrcode,sdocode); 

			} 
		
			

		return availableCredit;
	}
}
