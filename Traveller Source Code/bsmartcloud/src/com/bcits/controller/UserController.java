package com.bcits.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.commons.codec.binary.Base64;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bcits.entity.AllowedDeviceVersionsEntity;
import com.bcits.entity.AppActivityEntity;
import com.bcits.entity.BillingDataEntity;
import com.bcits.entity.CircleDataUpload;
import com.bcits.entity.HHBM_ConsumerEntity;
import com.bcits.entity.HHBM_DownloadEntity;
import com.bcits.entity.LocationOracleEntity;
import com.bcits.entity.MRCreditMasterEntity;
import com.bcits.entity.MRDepositEntity;
import com.bcits.entity.ManualSubDivDetails;
import com.bcits.entity.NotificationsEntity;
import com.bcits.entity.OnAirVersionUpdationEntity;
import com.bcits.entity.OnAirVersionUpdationEntityNew;
import com.bcits.entity.Payments;
import com.bcits.entity.PaymentsDataEntity;
import com.bcits.entity.ResultDisconnection;
import com.bcits.entity.Resultspaymets;
import com.bcits.entity.SiteLocationEntity;
import com.bcits.entity.VigilanceComplaintEntity;
import com.bcits.entity.VigilanceTheftEntity;
import com.bcits.service.AllowedDeviceVersionService;
import com.bcits.service.AppActivityService;
import com.bcits.service.BillingDataService;
import com.bcits.service.CircleDetailsService;
import com.bcits.service.DisConnectionReConnectionService;
import com.bcits.service.HHBM_ConsumerService;
import com.bcits.service.HHBM_DownloadService;
import com.bcits.service.LocationOracleService;
import com.bcits.service.LocationService;
import com.bcits.service.MRCreditMasterService;
import com.bcits.service.MRDepositService;
import com.bcits.service.MRDeviceAllocationService;
import com.bcits.service.MRDeviceService;
import com.bcits.service.MRMasterService;
import com.bcits.service.ManualEntrySubDivService;
import com.bcits.service.ModuleMasterService;
import com.bcits.service.NotificationsService;
import com.bcits.service.OnAirVersionUpdationService;
import com.bcits.service.OnAirVersionUpdationServiceNew;
import com.bcits.service.PaymentService;
import com.bcits.service.PaymentsDataService;
import com.bcits.service.SiteLocationService;
import com.bcits.service.UserService;
import com.bcits.service.VigilanceComplaintService;
import com.bcits.service.VigilanceTheftService;
import com.bcits.utility.BCITSLogger;
import com.bcits.utility.EncoderUtility;
import com.bcits.utility.Encryption;
import com.bcits.utility.Link;
import com.bcits.utility.PayRollLogger;



@Controller
public class UserController 
{
	@Autowired
    private UserService userService;
	
	@Autowired
	private ManualEntrySubDivService manualEntrySubDivService;

	
	@Autowired
    private CircleDetailsService circleDetailsService;
	
	@Autowired
	private LocationService locationService; 
	
	@Autowired
	private AppActivityService appActivityService;
	
	@Autowired
	private AllowedDeviceVersionService allowedDeviceVersionService;
	
	@Autowired
	private BillingDataService billingDataService;
	
	@Autowired
	private PaymentsDataService paymentsDataService;
	
	@Autowired
	private NotificationsService notificationsService;
	
	/*@Autowired
	private ComplaintService complaintService;*/
	
	@Autowired
	private HHBM_DownloadService hhbm_DownloadService;
	
	@Autowired
	private HHBM_ConsumerService hhbm_ConsumerService;
	
	@Autowired
	private VigilanceComplaintService vigilanceCompService;
	
	@Autowired
	private VigilanceTheftService vigilanceTheftService;
	
	
	@Autowired
	private PaymentService paymentService;

	@Autowired
	private SiteLocationService siteLocationService;

	@Autowired
	private MRMasterService mrMasterService;

	@Autowired
	private MRDeviceService mrDeviceService;

	@Autowired
	private MRDeviceAllocationService mrDeviceAllocationService;
	
	@Autowired
	private MRCreditMasterService mrCreditMasterService;

	@Autowired
	private MRDepositService mrDepositService;
	
	@Autowired
	private ModuleMasterService moduleMasterService;
	
	@Autowired
	private DisConnectionReConnectionService disConnectionReConnectionService;
	
	@Autowired
	private EncoderUtility encoderUtility;
	
	@Autowired
	private Encryption encryption;
	
	@Autowired
	 private OnAirVersionUpdationService onAirVersionUpdationService;
	
	@Autowired
	private OnAirVersionUpdationServiceNew onAirVersionUpdationServiceCesc;
	
	@Autowired
	private DataSource dataSource;
	
	
	@Autowired
	 private LocationOracleService locationOracleService;
	
	
	
//	ResultDisconnection disconnection;
	//Date current_date=new Date();
	 
	@Link(label="Home", family="UserController", parent = "" )
	@RequestMapping(value="/userDashboard",method=RequestMethod.GET)
	public String dashBoard(ModelMap model, HttpServletRequest request) throws SQLException
	{
		Connection c=null;
		userService.getRecentPath(request.getRequestURI().substring(4,request.getRequestURI().length()), request);
		/*List<HHBM_DownloadEntity> sdoList=hhbm_DownloadService.getDistinctSdo();
		request.getSession().setAttribute("sdoCodeList",sdoList);*/
		c=dataSource.getConnection();
		 BCITSLogger.logger.info("dataSource.getConnection()"+c.getMetaData().getURL().contains("cescnew"));
		 if(c.getMetaData().getURL().contains("cescnew")==true)
		 {
			 mrDeviceService.getDivisionMobCountCesc(model);
			 return "cescUserDashBoard";
		 }
		 else
		 {
			 mrDeviceService.getDivisionMobCount(model);
			 return "userDashboard";
		 }
		
	}  
	
	@Link(label="Home", family="UserController", parent = "" )
	@RequestMapping(value="/cescUserDashBoard",method=RequestMethod.GET)
	public String cescUserDashBoard(ModelMap model, HttpServletRequest request)
	{
		//userService.getRecentPath(request.getRequestURI().substring(4,request.getRequestURI().length()), request);
		mrDeviceService.getDivisionMobCountCesc(model);
		return "cescUserDashBoard";
	} 
	
	
	    @Link(label="On Air version Update", family="UserController", parent = "" )
		@RequestMapping(value="/onAirVersionUpdate",method=RequestMethod.GET)
	    public String onAirVersionUpdate(@ModelAttribute("onairverupdt") OnAirVersionUpdationEntity onairverupdt,BindingResult result,ModelMap model, HttpServletRequest request)
		{
			BCITSLogger.logger.info("In User DashBoard-On Air Version Update");
			userService.getRecentPath(request.getRequestURI().substring(4,request.getRequestURI().length()), request);
			model.put("onAirVerList", onAirVersionUpdationService.findAll());
			model.addAttribute("allDevices", mrDeviceService.findAllMRDevices(request));
			return "onAirVersionUpdate";
		}
	    
	    
	    @Link(label="On Air version Update", family="UserController", parent = "" )
	    @RequestMapping(value = "/addNewVersionUpdate", method = {RequestMethod.POST, RequestMethod.GET })
		public String addNewVersionUpdate(@ModelAttribute("allowedVersion") AllowedDeviceVersionsEntity allowedNew,	@ModelAttribute("onairverupdt") OnAirVersionUpdationEntity onairverupdt,@RequestParam("uploadedapk") MultipartFile apkFile,BindingResult result, ModelMap model, HttpServletRequest request)
	    {
	    	long res=onAirVersionUpdationService.checkVersionExist(onairverupdt.getApkVersion());
	    	if(res>0)
	    	{
	    		model.put("results","Entered version already exist");
	    	}
	    	else
	    	{
			onairverupdt.setReleaseFlag("not released");
			onairverupdt.setTimestamp(new Timestamp(new Date().getTime()));
			onairverupdt.setUploadedyBy((String) request.getSession(false).getAttribute("username"));
			onairverupdt.setApkName(onairverupdt.getApkName()+onairverupdt.getApkVersion()+".apk");
			try {
			List<OnAirVersionUpdationEntity> onair = onAirVersionUpdationService.findAll();
			String pathname ="";			
			if(onair.size()>0)
			{
				pathname =onair.get(0).getApkPath();//D:\\JVVNLAPKFILES\
				BCITSLogger.logger.info("=========================>"+pathname);
			}
			else
			{
				//pathname ="E:\\JVVNLAPKFILES\\";
				//pathname ="E:\\CESCFILES\\";
				pathname="/home/bcits/GESCOM_LIVE/GESC_LIVE_APK/";
			}
			if (!apkFile.isEmpty()) {
				try {					
					File directory = new File(pathname);
					if (!directory.exists()) {
						directory.mkdirs();
					}
					String apkPath = pathname + onairverupdt.getApkName();
					BCITSLogger.logger.info("==================apkPath=======>"+apkPath);
					onairverupdt.setApkPath(pathname);
			
					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(apkPath)));
					 stream.write(apkFile.getBytes());
					stream.close();
				} catch (Exception e) {
					e.getMessage();
				}

				onAirVersionUpdationService.save(onairverupdt);
				model.put("results", "APK Files stored successfully..");
				/*if (onAirVersionUpdationService.update(onairverupdt) != null) {
					if (onair.get(0).getReleaseFlag().equals("not released")) {

						allowedNew.setAppName(onair.get(0).getApkName());
						allowedNew.setDateTimeStamp(new Timestamp(new Date().getTime()));

						allowedNew.setRemarks(onair.get(0).getRemarks());
						allowedNew.setVersion_name(onair.get(0).getApkVersion());
						allowedNew.setId(onair.get(0).getId());
					}
					
					//onAirVersionUpdationService.delete(onair.get(0).getId());

					model.put("results", "APK Files stored successfully..");
				} else {
					model.put("results","Error while uploading APK Files");
				}*/


			}
			} catch (Exception e) {
				model.put("results","Error while uploading APK Files");
				e.printStackTrace();
			}
			
	    	}
			BCITSLogger.logger.info("In User DashBoard-On Air Version Update");

			model.put("onAirVerList", onAirVersionUpdationService.findAll());
			model.addAttribute("allDevices", mrDeviceService.findAllMRDevices(request));
			model.put("onairverupdt", new OnAirVersionUpdationEntity());
			return "onAirVersionUpdate";
		}

	    @Link(label="On Air version Update", family="UserController", parent = "" )
		@RequestMapping(value = "/releaseAPKVersion", method = RequestMethod.POST)
		public String releaseAPKVersion(@ModelAttribute("allowedVersion") AllowedDeviceVersionsEntity allowedVersionsEntity,ModelMap model,	BindingResult bindingResult,@ModelAttribute("onairverupdt") OnAirVersionUpdationEntity onairverupdt1,HttpServletRequest request,HttpServletResponse response, @RequestParam("apkid") Integer apkid)
	    {
			OnAirVersionUpdationEntity onairverupdt = onAirVersionUpdationService.find(apkid);
			AllowedDeviceVersionsEntity allowedNew = new AllowedDeviceVersionsEntity();
			allowedNew.setAppName(onairverupdt.getApkName());
			allowedNew.setDateTimeStamp(new Timestamp(new Date().getTime()));
			allowedNew.setRemarks(onairverupdt.getRemarks());
			allowedNew.setVersion_name(onairverupdt.getApkVersion());
			allowedNew.setId(onairverupdt.getId());
			allowedNew.setAllowedFlag("allowed");
			if (allowedDeviceVersionService.update(allowedNew) != null) {
				onairverupdt.setReleaseFlag("released");
				onAirVersionUpdationService.update(onairverupdt);
				model.put("results",onairverupdt.getApkName() + "-"+ onairverupdt.getApkVersion()+ " is released successfully...");
			} else {
				model.put("results",onairverupdt.getApkName() + "-"	+ onairverupdt.getApkVersion()+ " is Not released...");
			}

			model.put("onAirVerList", onAirVersionUpdationService.findAll());
			model.put("onairverupdt", new OnAirVersionUpdationEntity());
			return "onAirVersionUpdate";
		}
		

		
		  @RequestMapping(value = "/downloadAPKFile", method = { RequestMethod.GET,	RequestMethod.POST })
		 public @ResponseBody void downloadAPKFile(@ModelAttribute("onairverupdt") OnAirVersionUpdationEntity onairverupdt1,HttpServletRequest request,HttpServletResponse response, @RequestParam("apkid") Integer apkid) {

			OnAirVersionUpdationEntity onairverupdt = onAirVersionUpdationService.find(apkid);
			if(onairverupdt != null){
				String fullPath = onairverupdt.getApkPath()
						+ onairverupdt.getApkName();
						
				BCITSLogger.logger.info("-=-=-=-=-=-=-=fullPath:::::::::::::::::::--->"+fullPath);
				File downloadFile = new File(fullPath);
				try {
					FileInputStream inputStream = new FileInputStream(downloadFile);
					// get MIME type of the file
					String mimeType = request.getServletContext().getMimeType(
							fullPath);
					if (mimeType == null) {
						// set to binary type if MIME mapping not found
						mimeType = "application/octet-stream";
					}
			/* System.out.println("MIME type: " + mimeType); */

					// set content attributes for the response
					response.setContentType(mimeType);
					response.setContentLength((int) downloadFile.length());

					// set headers for the response
					String headerKey = "Content-Disposition";
					String headerValue = String.format(
							"attachment; filename=\"%s\"", downloadFile.getName());
					response.setHeader(headerKey, headerValue);

					 // get output stream of the response
			        OutputStream outStream = response.getOutputStream();
			 
			        byte[] buffer = new byte[4096];
			        int bytesRead = -1;
			 
			        // write bytes read from the input stream into the output stream
			        while ((bytesRead = inputStream.read(buffer)) != -1) {
			            outStream.write(buffer, 0, bytesRead);
			        }
			 
			        inputStream.close();
			        outStream.close();
				} catch (Exception e) {

					e.printStackTrace();
				}
			  
			}

		   }
	
	    @Link(label="Allowed Version", family="UserController", parent = "Home" )
		@RequestMapping(value="/allowedVersions",method=RequestMethod.GET)
		public String allowedVersions(@ModelAttribute("allowedVersion") AllowedDeviceVersionsEntity allowedVersionsEntity, ModelMap model,BindingResult bindingResult,HttpServletRequest request)
		{
			BCITSLogger.logger.info("In User DashBoard-Allowed Versions");
			userService.getRecentPath(request.getRequestURI().substring(4,request.getRequestURI().length()), request);
			List<AllowedDeviceVersionsEntity> allowedVersionList=allowedDeviceVersionService.findAll();
			 model.addAttribute("results", "notDisplay");
			model.put("allowedVersionList",allowedVersionList);
			return "allowedVersions";
		}
	
	  
	    @Link(label="Allowed Version", family="UserController", parent = "Home" )
		@RequestMapping(value="/updateAllowedVersion",method=RequestMethod.POST)
		public String updateAppActivityLog(@ModelAttribute("allowedVersion") AllowedDeviceVersionsEntity allowedVersionsEntity,BindingResult bindingResult, ModelMap model,HttpServletRequest request)
		{		
			if(allowedVersionsEntity.getId()==0)
			{
				allowedVersionsEntity.setDateTimeStamp(new Timestamp(new Date().getTime()));
				allowedDeviceVersionService.update(allowedVersionsEntity);
				model.put("results", "Allowed Device version Details Added Successfully");
			}
			else
			{ 
				allowedVersionsEntity.setDateTimeStamp(allowedDeviceVersionService.getTimestamp(request.getParameter("dateTimeStamp")));
				allowedDeviceVersionService.update(allowedVersionsEntity);
				model.put("results", "Allowed Device version Details Updated Successfully");
			}
			 List<AllowedDeviceVersionsEntity> allowedVersionList=allowedDeviceVersionService.findAll();
			 model.put("allowedVersion", new AllowedDeviceVersionsEntity());
			 model.put("allowedVersionList",allowedVersionList);
			 return "allowedVersions";
		}
	    
	    
	    @RequestMapping(value="/editAllowedVersion/{operation}",method={RequestMethod.POST,RequestMethod.GET})
		public @ResponseBody Object editAddress(@PathVariable int operation,HttpServletResponse response,HttpServletRequest request,ModelMap model) throws JsonParseException, JsonMappingException, IOException
		{
	    	AllowedDeviceVersionsEntity allowedVersionsEntity=allowedDeviceVersionService.find(operation);
			return allowedVersionsEntity;
		}
	    
	    
	    
	    
	    //APP ACTIVITY LOG
	    @Link(label="App Activity Log", family="UserController", parent = "Home" )
		@RequestMapping(value="/appActivityLog",method=RequestMethod.GET)
		public String appActivityLog(@ModelAttribute("appAcivityLog") AppActivityEntity appActivityEntity, ModelMap model,BindingResult bindingResult,HttpServletRequest request)
		{
			BCITSLogger.logger.info("In User DashBoard-App Activity Log");
			userService.getRecentPath(request.getRequestURI().substring(4,request.getRequestURI().length()), request);
			List<AppActivityEntity> appActivityList=appActivityService.findAll();
			model.addAttribute("results", "notDisplay");
			model.put("appActitvityList",appActivityList);
			return "appActivityLog";
		}

	    //BILLING DATA
	    @Link(label="", family="UserController", parent = "Home" )
		@RequestMapping(value="/billingData",method={RequestMethod.GET,RequestMethod.POST})
		public String billingData(ModelMap model, HttpServletRequest request)
		{
			BCITSLogger.logger.info("In User DashBoard-Billing Data");
			userService.getRecentPath(request.getRequestURI().substring(4,request.getRequestURI().length()), request);
			model.put("results", "notDisplay");
			return "billingData";
		}
	    
	    @Link(label="Home", family="UserController", parent = "" )
		@RequestMapping(value="/getBillingData",method=RequestMethod.POST)
		public String getBillingData(ModelMap model, HttpServletRequest request)
		{
			BCITSLogger.logger.info("In User DashBoard-Payments Data");
			userService.getRecentPath(request.getRequestURI().substring(4,request.getRequestURI().length()), request);	
			List<BillingDataEntity> BillingDataList=billingDataService.getBillingData(request.getParameter("searchData").toUpperCase(),request.getParameter("colValue"),request.getParameter("sdoCode"));
			//System.out.println("BILLING DATA LIST---------------------->"+BillingDataList.size());
			model.put("results", "notDisplay");
			if(BillingDataList.size()>0)
			{
				model.put("BillingDataList", BillingDataList);
			}
			else
			{
				model.put("results", "Records are not available.");
			}
			 
			 return "billingData";
		}
	    
	    @Link(label="Home", family="UserController", parent = "" )
		@RequestMapping(value="/paymentsData",method={RequestMethod.GET,RequestMethod.POST})
		public String paymentsData(ModelMap model, HttpServletRequest request)
		{
			BCITSLogger.logger.info("In User DashBoard-Payments Data");
			userService.getRecentPath(request.getRequestURI().substring(4,request.getRequestURI().length()), request);		
			model.put("results", "notDisplay");
			return "paymentsData";
		}
	    
	    @Link(label="Home", family="UserController", parent = "" )
		@RequestMapping(value="/getPaymentsData",method=RequestMethod.POST)
		public String getPaymentsData(ModelMap model, HttpServletRequest request)
		{
			BCITSLogger.logger.info("In User DashBoard-Payments Data---->"+request.getParameter("paymentMode"));
			userService.getRecentPath(request.getRequestURI().substring(4,request.getRequestURI().length()), request);
			model.put("results", "notDisplay");
			List<PaymentsDataEntity> paymentsList=null;
			
			paymentsList=paymentsDataService.getPaymentsData(request.getParameter("searchData").toUpperCase(),request.getParameter("colValue"),request.getParameter("sdoCode"),request.getParameter("paymentMode").toUpperCase());
			
		
			if(paymentsList.size()==0)
			{
				model.put("results", "Records are not available.");
			}
			else
			{
				model.put("paymentsList", paymentsList);
			}
			
			 return "paymentsData";
		}
	    
	    
	    @Link(label="Home", family="UserController", parent = "" )
		@RequestMapping(value="/billingAlerts",method=RequestMethod.GET)
		public String billingAlerts(ModelMap model, HttpServletRequest request)
		{
			BCITSLogger.logger.info("In User DashBoard-Billing Alerts");
			userService.getRecentPath(request.getRequestURI().substring(4,request.getRequestURI().length()), request);	
			model.addAttribute("results", "notDisplay");
			return "billingAlerts";
		}
	    
	    @Link(label="Home", family="UserController", parent = "" )
		@RequestMapping(value="/getBillAlert",method=RequestMethod.POST)
		public String getBillAlert(ModelMap model, HttpServletRequest request)
		{
			BCITSLogger.logger.info("In User DashBoard-billing alert");
			userService.getRecentPath(request.getRequestURI().substring(4,request.getRequestURI().length()), request);	
			List<BillingDataEntity> billingDataAlert=billingDataService.getBillingDataAlert(request.getParameter("sdoCode").toUpperCase(),request.getParameter("consumerNo"));
			model.addAttribute("results", "notDisplay");
			model.put("results", "notDisplay");
			if(billingDataAlert.size()>0)
			{
				model.put("billingDataAlert", billingDataAlert);
			}
			else
			{
				model.put("results", "Records are not available.");
			}
			
			 return "billingAlerts";
		}
	    
	    
	    @Link(label="Billing Alert", family="UserController", parent = "" )
		@RequestMapping(value="/updateBillAlertDate",method=RequestMethod.POST)
		public String updateBillAlertDate(ModelMap model, HttpServletRequest request,BillingDataEntity billingDataEntity,BindingResult bindingResult)
		{
			BCITSLogger.logger.info("In User DashBoard-billing alert");
			userService.getRecentPath(request.getRequestURI().substring(4,request.getRequestURI().length()), request);
			String date=billingDataService.getDatenTime(new Date());
			BCITSLogger.logger.info("In User DashBoard-billing alert"+date);
			billingDataService.updateDateTime(request.getParameter("sdoCode"),request.getParameter("accNo"),request.getParameter("billMonth"),request.getParameter("billIssueDate"),date);
			model.put("results", "Notification Alert Sent Successfully");
			return "billingAlerts";
		}
	    
	    
	    @Link(label="Announcements", family="UserController", parent = "Home" )
		@RequestMapping(value="/notifications",method=RequestMethod.GET)
		public String notifications(@ModelAttribute("notifications") NotificationsEntity notificationsEntity,BindingResult bindingResult, ModelMap model,HttpServletRequest request)
		{
			BCITSLogger.logger.info("In User DashBoard-Notifications");
			userService.getRecentPath(request.getRequestURI().substring(4,request.getRequestURI().length()), request);	
			List<NotificationsEntity> notificList=notificationsService.findAll();
			model.addAttribute("results", "notDisplay");
			model.put("notificList", notificList);
			return "notifications";
		}
	    
	    @Link(label="", family="UserController", parent = "Home" )
		@RequestMapping(value="/searchListItem",method={RequestMethod.GET,RequestMethod.POST})
		public String searchListItem(ModelMap model, HttpServletRequest request)
		{
			BCITSLogger.logger.info("In User DashBoard-searchListItem");
			userService.getRecentPath(request.getRequestURI().substring(4,request.getRequestURI().length()), request);
			model.put("results", "notDisplay");
			return "searchListItem";
		}
	    
	    
	    @Link(label="Home", family="UserController", parent = "" )
		@RequestMapping(value="/getSearchListData",method=RequestMethod.POST)
		public String getSearchListData(ModelMap model, HttpServletRequest request)
		{
	    	try
	    	{
	    		BCITSLogger.logger.info("In User DashBoard-Payments Data");
				BCITSLogger.logger.info("In User DashBoard-Payments Data:::::::::::::::::::::::::::::->"+request.getParameter("colValue"));
				userService.getRecentPath(request.getRequestURI().substring(4,request.getRequestURI().length()), request);	
				List<BillingDataEntity> BillingDataList=null;
				List<PaymentsDataEntity> paymentsList=null;
				if(request.getParameter("searchFrom").trim().equalsIgnoreCase("billeddata"))
				{
					 BillingDataList=billingDataService.getBillingData(request.getParameter("searchData").toUpperCase(),request.getParameter("colValue1"),request.getParameter("sdoCode"));
					//System.out.println("BILLING DATA LIST---------------------->"+BillingDataList.size());
					model.put("results", "notDisplay");
					if(BillingDataList.size()>0)
					{
						model.put("BillingDataList", BillingDataList);
					}
					else
					{
						model.put("results", "Bill details are not available.");
					}
				}
				
				if(request.getParameter("searchFrom").trim().equalsIgnoreCase("paymentdata"))
				{
					 paymentsList=paymentsDataService.getPaymentsData(request.getParameter("searchData").toUpperCase(),request.getParameter("colValue2"),request.getParameter("sdoCode"),request.getParameter("paymentMode").toUpperCase());
					model.put("results", "notDisplay");
					if(paymentsList.size()>0)
					{
						model.put("paymentsList", paymentsList);
					}
					else
					{
						model.put("results", "Payment details are not available.");
					}
				}
				if(request.getParameter("searchFrom").trim().equalsIgnoreCase("billedAndPaid"))
				{
					PayRollLogger.logger.info("--------------request.getParameter()--------request.getParameter()----------------->"+request.getParameter("paymentMode"));
					String payMode=request.getParameter("paymentMode");
					if(request.getParameter("paymentMode").equalsIgnoreCase("select"))
					{
						payMode="%";
					}
					BillingDataList=billingDataService.getBillingData(request.getParameter("searchData").toUpperCase(),request.getParameter("colValue3"),request.getParameter("sdoCode"));
					 paymentsList=paymentsDataService.getPaymentsData(request.getParameter("searchData").toUpperCase(),request.getParameter("colValue3"),request.getParameter("sdoCode"),payMode);
					model.put("results", "notDisplay");
					PayRollLogger.logger.info("--------------BillingDataList--------paymentsList----------------->"+paymentsList.size()+"---"+BillingDataList.size());
					if(paymentsList.size()!=0 && BillingDataList.size()!=0)
					{
						model.put("paymentsList", paymentsList);
						model.put("BillingDataList", BillingDataList);
					}
					else 
					{
						model.put("results", "Both Billed and Payment details are not available.");
					}
				}
	    	}
	    	catch(Exception e)
	    	{
	    		e.printStackTrace();
	    	}
			 
			 return "searchListItem";
		}
	    
	    
	    @Link(label="Announcements", family="UserConroller", parent = "Home" )
		@RequestMapping(value="/updateNotifications",method=RequestMethod.POST)
		public String updateNotifications(@ModelAttribute("notifications") NotificationsEntity notificationsEntity,BindingResult bindingResult, ModelMap model,HttpServletRequest request)
		{		
	    	    Calendar c = Calendar.getInstance();  
	            c.add(Calendar.DATE, 30);  
	            Date d = c.getTime();
				notificationsEntity.setExpireDate(d);
				notificationsEntity.setDatePublished(new Date());
				notificationsService.update(notificationsEntity);
				model.put("results", "Notification sent Successfully");
				List<NotificationsEntity> notificList=notificationsService.findAll();
				model.put("notificList", notificList);
				model.put("notifications", new NotificationsEntity());
				return "notifications";
		}
	    
	    
	    
	    
	    
	    @Link(label="Energy wise Reports", family="UserController", parent = "" )
		@RequestMapping(value="/energyWiseReports",method=RequestMethod.GET)
		public String energyWiseReports(ModelMap model, HttpServletRequest request)
		{
			BCITSLogger.logger.info("In User DashBoard-Energywise Reports");
			userService.getRecentPath(request.getRequestURI().substring(4,request.getRequestURI().length()), request);		
			return "energyWiseReports";
		}
	    
	    
	    
	    /*@Link(label="Real-Time status", family="UserController", parent = "Home" )
		@RequestMapping(value="/realTimeStatus/getImage/{value1}",method=RequestMethod.GET)
		public void realTimeStatusGetImage(ModelMap model, HttpServletRequest request,HttpServletResponse response,@PathVariable String value1) throws IOException
		{
			BCITSLogger.logger.info("In User DashBoard-Consumerwise view");
			userService.getRecentPath(request.getRequestURI().substring(4,request.getRequestURI().length()), request);	
			hhbm_DownloadService.findImage(model,request,response);
			return "consumerWiseView";
	    	response.setContentType("image/jpeg");
	    	byte bt[] = null;
	    	OutputStream ot = null;
	    	ot = response.getOutputStream();	    	
	    	bt = hhbm_DownloadService.findOnlyImage(model,request,response,value1);
	    	ot.write(bt);
	    	ot.close();
	    	 hhbm_DownloadService.findOnlyImage(model,request,response,value1);
		}*/
	    
	    
	    /*<------------------------------------------------METHOD TO DISPLAY IMAGE----------------------------------------------->*/
	    
	    
		@RequestMapping(value="/theftimageDisplay/getImage/{consumerNo}/{value}",method=RequestMethod.GET)
		public void theftRecordingGetImage(ModelMap model, HttpServletRequest request,HttpServletResponse response,@PathVariable String consumerNo,@PathVariable String value) throws IOException
		{
			//System.out.println("VALUE---------------------->"+value);
			BCITSLogger.logger.info("In User DashBoard-Image display(Theft Recording)");
			vigilanceTheftService.findOnlyImage(model,request,response,consumerNo,value);
		}
		
/*===========================================================================================================================================*/    
	    
	    
	    
	    
	    /*@RequestMapping(value="/getBmdBillData/{sdoCode}/{billDate}/{bmdReader}" , method={RequestMethod.POST,RequestMethod.GET})
		public @ResponseBody Object getBmdBillData(@PathVariable("sdoCode") String sdoCode,@PathVariable ("billDate") String billDate,@PathVariable ("bmdReader") String bmdReader,HttpServletRequest request,ModelMap model)throws JsonParseException, JsonMappingException, IOException 
		{
	    	System.out.println("COMING--------------->");
	    	List<HHBM_DownloadEntity> dataList=hhbm_DownloadService.getAllBmdData(sdoCode,billDate,bmdReader,model,request);
	    	System.out.println("LIST SIZE--------------->"+dataList.size());
	    	return dataList;
		}*/
	    
	    
	    /*@RequestMapping(value="/getDistinctBmd/{date}/{sdocode}" ,method={RequestMethod.POST,RequestMethod.GET})
		public @ResponseBody Object getDistinctBmd(@PathVariable("date") String date,@PathVariable("sdocode") String sdoCode, HttpServletResponse response,HttpServletRequest request,ModelMap model) throws JsonParseException, JsonMappingException, IOException
		{
	    	System.out.println("COMING DATE-------------->"+date);
	    	System.out.println("COMING SDOCODE----------->"+sdoCode);
			BCITSLogger.logger.info("In  realTimeStatusBilled ");
			hhbm_DownloadService.getDistinctBmd(sdoCode,date,model);
			Object[] str=null;
			String[] bmdArray=null;
			List bmdList1=new ArrayList();
			System.out.println("SIZE OF BMDLIST---------------->"+bmdList.size());
			for (int i = 0; i < bmdList.size(); i++) 
			{
			     str= (Object[]) bmdList.get(i);
			    System.out.println("LENGTH OF STR---------------->"+str.length);
			    for (int j = 0; j < str.length-1; j++) 
			    {
			    	System.out.println("DATA0-------------------->"+str[0]);
			    	System.out.println("DATA1-------------------->"+str[1]);
			    	bmdList1.add(str[0]+"#"+str[1]);
			    }
			}
			return "";
		}*/
	    
	    
	    
	    
	    /*@Link(label="Consumerwise view", family="UserController", parent = "Home" )
		@RequestMapping(value="/consumerWiseView/getImage/{value1}",method=RequestMethod.GET)
		public void consumerWiseViewgetImage(ModelMap model, HttpServletRequest request,HttpServletResponse response,@PathVariable String value1) throws IOException
		{
			BCITSLogger.logger.info("In User DashBoard-Consumerwise view");
			userService.getRecentPath(request.getRequestURI().substring(4,request.getRequestURI().length()), request);	
			hhbm_DownloadService.findImage(model,request,response);
			return "consumerWiseView";
	    	response.setContentType("image/jpeg");
	    	byte bt[] = null;
	    	OutputStream ot = null;
	    	ot = response.getOutputStream();	    	
	    	bt = hhbm_DownloadService.findOnlyImage(model,request,response,value1);
	    	ot.write(bt);
	    	ot.close();
	    	 hhbm_DownloadService.findOnlyImage(model,request,response,value1);
	    	
		}
	    
	    @Link(label="Consumerwise view", family="UserController", parent = "Home" )
	    @RequestMapping(value="/consumerWiseView",method=RequestMethod.GET)
	    public String consumerWiseView(ModelMap model, HttpServletRequest request,HttpServletResponse response) throws IOException
	    {
	    	BCITSLogger.logger.info("In User DashBoard-Consumerwise view");
			userService.getRecentPath(request.getRequestURI().substring(4,request.getRequestURI().length()), request);	
			model.addAttribute("obj",hhbm_DownloadService.findImage(model, request, response));
			return "consumerWiseView";
	    	
	    }*/
	    
	    
	    
	    
	    /**
		 * Billing Summary Reports::::::
		 * 
		 */
	    /*@Link(label="Billing Summary Reports", family="UserController", parent = "Home" )
		@RequestMapping(value="/billingSummaryReport",method={RequestMethod.POST,RequestMethod.GET})
		public String billingSummaryReports(ModelMap model, HttpServletRequest request,HttpServletResponse response) 
		{
			BCITSLogger.logger.info("In User DashBoard-Billing Summary Reports");
			userService.getRecentPath(request.getRequestURI().substring(4,request.getRequestURI().length()), request);	
			//model.put("sdoCodeval", request.getSession().getAttribute("sdoVal"));
			List<HHBM_DownloadEntity> sdoList=hhbm_DownloadService.getDistinctSdoCodes();
			List<String> billMonthList=hhbm_DownloadService.getBillMonths();
			model.put("billMonthList", billMonthList);
			model.put("sdoList", sdoList);
			model.put("results", "notDisplay");
			return "billingSummaryReport";
		}
	    
	    *//**
		 * Billing Summary Reports Displaying method::::::
		 * 
		 *//*
	    @Link(label="Billing Summary Reports", family="UserController", parent = "Home" )
		@RequestMapping(value="/getReportDetails",method=RequestMethod.POST)
		public String showReportDetails(ModelMap model, HttpServletRequest request) 
		{
	    	
	    	String sdocode=request.getParameter("sdoCode");
	    	String bMonth=request.getParameter("billMonth");
	    	String reportName=request.getParameter("reportName");
	    	BCITSLogger.logger.info("Coming Data------------------->"+reportName);
	    	if(reportName.toUpperCase().equals("BINDERWISE")||reportName.toUpperCase().equals("MRWISE")
	    			||reportName.toUpperCase().equals("BINDERMRWISE"))
	    	{
	    		BCITSLogger.logger.info("Coming Data------------------->"+reportName);
	    		hhbm_ConsumerService.getReportDetails(sdocode, bMonth, reportName,model);
	    	}
	    	else
	    	{
	    		hhbm_DownloadService.getReportDetails(sdocode,bMonth,reportName,model);
	    	}
	    	List<HHBM_DownloadEntity> sdoList=hhbm_DownloadService.getDistinctSdo();
			List<String> billMonthList=hhbm_DownloadService.getBillMonths();
			model.put("billMonthList", billMonthList);
			model.put("sdoList", sdoList);
	    	model.put("results", "notDisplay");
			return "billingSummaryReport";
		}*/
	    
	    
	  
	    
	    @Link(label="Home", family="UserController", parent = "" )
	    @RequestMapping(value="/setSdoCodeVal" ,method=RequestMethod.POST)
		public String setSdoCodeVal(HttpServletRequest request)
		{
			BCITSLogger.logger.info("In User dash Boards-Set Sdo Code");
			if(request.getParameter("sdoId")!=null ||request.getParameter("sdoId")!="")
			{
				request.getSession().setAttribute("sdoVal", request.getParameter("sdoId"));
			}
			//List<HHBM_DownloadEntity> sdoList=hhbm_DownloadService.checkSdoCode(request.getParameter("sdoId"));
			return "userDashboard";
		}
	    
	    @Link(label="Home", family="UserController", parent = "" )
	    @RequestMapping(value="/reSetSdoCodeVal" ,method=RequestMethod.POST)
		public String reSetSdoCodeVal(HttpServletRequest request)
		{
	    	if(request.getSession().getAttribute("sdoVal")!=null ||request.getSession().getAttribute("sdoVal")!="")
	    	{
	    		request.getSession().removeAttribute("sdoVal");
	    	}
			return "userDashboard";
		}
	    
	    
	    
	    
/*------------------------------------------------------SERVER SIDE WEB SERVICE CODE-------------------------------------------------------------*/
	    @RequestMapping(value="/updateComplaints",method=RequestMethod.POST)
	    public @ResponseBody Object updateComplaints(@RequestBody  String complaints_list) throws JSONException
	    {
	    	VigilanceComplaintEntity complEntity=new VigilanceComplaintEntity();
	    	JSONArray arr = new JSONArray(complaints_list);
	    
	    	for (int i = 0; i < arr.length(); i++) 
	    	{
	    		JSONObject Asst_json_obj = arr.getJSONObject(i);
	    	
	    		complEntity.setSdoCode(Asst_json_obj.getString("SDOCODE"));
	    		complEntity.setConsumerNo(Asst_json_obj.getString("C_NUMBER"));
	    		complEntity.setAddress(Asst_json_obj.getString("ADDRESS"));
	    		complEntity.setDescription(Asst_json_obj.getString("DESCRIPTION"));
	    		complEntity.setIssue(Asst_json_obj.getString("ISSUE"));
	    		complEntity.setCreatedBy(Asst_json_obj.getString("CREATEDBY"));
	    		complEntity.setStatus(Asst_json_obj.getString("STATUS"));
	    		
	    		if(Asst_json_obj.getString("DATE").equals("online_date")){
	    			 java.util.Date date= new java.util.Date();
	    			   complEntity.setDate(date);
	    		}else{
	    			Timestamp disdate = Timestamp.valueOf(Asst_json_obj.getString("DATE"));
		    		complEntity.setDate(disdate);
	    		}
	    		
	    	}
	    	vigilanceCompService.update(complEntity);
			return "Success";
	    }
	    
	    @RequestMapping(value="/updateTheftRecording",method=RequestMethod.POST)
	    public @ResponseBody Object updateTheftRecording(@RequestBody String theft_list) throws JSONException
	    {
	    	String  sdocode;
			String  rrnumber ;
			String  address ;
			String  description ;
			String  image,image2,image3;
			String  latitude ;
			String  longitude ;
			String  user ;
			String status;
			VigilanceTheftEntity theftEntity=new VigilanceTheftEntity();
	    	JSONArray arr = new JSONArray(theft_list);
	    	for (int i = 0; i < arr.length(); i++)
	    	{
				JSONObject Asst_json_obj = arr.getJSONObject(i);
				
				sdocode =	Asst_json_obj.getString("SDOCODE");  
				rrnumber =	Asst_json_obj.getString("rr_number");  
				description =	Asst_json_obj.getString("description");  
				address =	Asst_json_obj.getString("address");  
				image =Asst_json_obj.getString("image") ; 
				latitude =	Asst_json_obj.getString("latitude");  
				longitude =	Asst_json_obj.getString("longitude");  
				user =	Asst_json_obj.getString("createdby");  
				image2 =Asst_json_obj.getString("image2") ;
				image3 =Asst_json_obj.getString("image3") ;
				byte[] IMAGE = Base64.decodeBase64(image) ;
				byte[] IMAGE2 = Base64.decodeBase64(image2) ;
				byte[] IMAGE3 = Base64.decodeBase64(image3) ;
				status=Asst_json_obj.getString("STATUS") ;
				theftEntity.setSdoCode(sdocode);
				theftEntity.setRr_Number(rrnumber);
				theftEntity.setDescription(description);
				theftEntity.setAddress(address);
				theftEntity.setImage(IMAGE);
				theftEntity.setLattitude(latitude);
				theftEntity.setLongitude(longitude);
				theftEntity.setCreatedBy(user);
				theftEntity.setImage_two(IMAGE2);
				theftEntity.setImage_three(IMAGE3);
				theftEntity.setStatus(status);
				
				if(Asst_json_obj.getString("DATE").equals("online_date")){
	    			 java.util.Date date= new java.util.Date();
	    			 theftEntity.setDate(date);
	    		}else{
	    			Timestamp disdate = Timestamp.valueOf(Asst_json_obj.getString("DATE"));
	    			theftEntity.setDate(disdate);
	    		}
				
				
				
	    	}
	    	vigilanceTheftService.update(theftEntity);
			return "Success";
	    }
	    
	 
	    
	    ModelMap model=new ModelMap();
	    @RequestMapping(value="/showConsComplaints/{username}",method=RequestMethod.GET)
	    public @ResponseBody Object showConsComplaint(@PathVariable String username) throws JSONException
	    {
	    	List<VigilanceComplaintEntity> complList=vigilanceCompService.findByUser(username);
			return complList;
	    }
	    
	    @RequestMapping(value="/showTheftRecords/{username}",method=RequestMethod.GET)
	    public @ResponseBody Object showTheftRecords(@PathVariable String username) throws JSONException
	    {
	    
	    	List<Map<String, Object>> approvalMap = null ;
			try{
		    	List<VigilanceTheftEntity> theftList=vigilanceTheftService.findByUser(username);

				BCITSLogger.logger.info("list" + theftList.size());
				approvalMap = new ArrayList<Map<String,Object>>();
				
				for(final VigilanceTheftEntity record : theftList)
				{				
					approvalMap.add(new HashMap<String, Object>() {{
							put("id",record.getId());
							put("rr_Number",record.getRr_Number());
							put("address",record.getAddress());
							put("description",record.getDescription());
							put("createdBy",record.getCreatedBy());
							put("status",record.getStatus());
							put("date",record.getDate());
							put("sdoCode",record.getSdoCode());
							
							
					 }});	
			}
				
			}
				catch (Exception exception)
				{
					BCITSLogger.logger.info("Error while saving JSON object to DB");
					exception.printStackTrace();
					
				}
			
	    	
			return approvalMap;
	    }

	    @RequestMapping(value="/getSectionForTheftReport",method=RequestMethod.GET)
	     public @ResponseBody Object getSection() throws JSONException
	     {
	      System.out.println("coming inside get location");
	      List<String> siteCodeList=siteLocationService.getAllSdoCodes();
	      return siteCodeList;
	     }
	    
	    //TODO
	    @RequestMapping(value="/getServerTimeForMobile",method=RequestMethod.GET)
	     public @ResponseBody Object getServerTimeForMobile() throws JSONException
	     {
	    	 java.util.Date date= new java.util.Date();
	   return new Timestamp(date.getTime());
	     }
	    
	    
	/**
	 * Site Locations Info Module is started from here::::::::::
	 * 
	 */
	@RequestMapping(value = "/siteLocations", method = RequestMethod.GET)
	public String siteLocationsAccessManagement(
			@ModelAttribute("siteLocations") SiteLocationEntity siteLocations,
			HttpServletRequest request, ModelMap model,
			BindingResult bindingResult) {

		model.put("sitelocs", siteLocationService.getAllSiteLocations(model,request));
		model.put("results", "notDisplay");
		return "siteLocationsAccessManagement";
	}

	@RequestMapping(value = "/updateSiteLocation", method = RequestMethod.POST)
	public String updateSiteLocation(
			@ModelAttribute("siteLocations") SiteLocationEntity siteLocations,
			HttpServletRequest request, ModelMap model,
			BindingResult bindingResult) {


		if (siteLocationService.getAllSiteCodes().contains(
				siteLocations.getSiteCode())) {
			siteLocationService.update(siteLocations);
			model.put("results", "Site Location Updated Successfully");

		} else {

			siteLocationService.update(siteLocations);
			model.put("results", "Site Location Added Successfully");
		}

		model.put("sitelocs", siteLocationService.getAllSiteLocations(model,request));
		model.put("siteLocations", new SiteLocationEntity());
		return "siteLocationsAccessManagement";
	}

	@RequestMapping(value = "/editSiteLocation", method = RequestMethod.POST)
	public @ResponseBody
	SiteLocationEntity editSiteLocation(
			@RequestParam("siteCode") Integer sdoCode, ModelMap model) {

		return siteLocationService.find(sdoCode);

	}

	/**
	 * Meter Reader Access Management starting here:::::::::::::
	 * 
	 */

    
    @RequestMapping(value="/getOperatorImage/getImage/{mrCode}",method=RequestMethod.GET)
	public void getProfileImage(ModelMap model, HttpServletRequest request,HttpServletResponse response,@PathVariable String mrCode) throws IOException
	{
		BCITSLogger.logger.info("In photo display");		
		mrMasterService.getPhoto(model, request, response, mrCode);
	   
	}

	
	
	@Link(label="Collection Details", family="UserController", parent = "Home" )
  	@RequestMapping(value="/collectionDetails",method = { RequestMethod.GET,RequestMethod.POST })
  	public String getCollectionDetails( ModelMap model,HttpServletResponse response,HttpServletRequest request) throws IOException
  	{
    	BCITSLogger.logger.info("In getCollectionDetails method");
		userService.getRecentPath(request.getRequestURI().substring(5,request.getRequestURI().length()), request);
		model.put("currentDate", new Date());
		model.put("sdoCodes", siteLocationService.getAllSiteCodes());
		return "collectionDetails";
  	}
	@Link(label = "Collection Details", family = "UserController", parent = "Home")
	 @RequestMapping(value="/searchPaymentsByDates",method = { RequestMethod.GET,RequestMethod.POST })
	   public String searchPaymentsByDates(ModelMap model,HttpServletResponse response,HttpServletRequest request) throws IOException
	   {
	  BCITSLogger.logger.info("In searchPaymentsByDates method");
	  userService.getRecentPath(request.getRequestURI().substring(5,request.getRequestURI().length()), request); 
	  
	  String fromDate = request.getParameter("fromDate");
	  String toDate = request.getParameter("toDate");
	  String sdoCode = request.getParameter("sdoCode");
	  String mrCode = request.getParameter("mrCode");
	  System.out.println("fromDate ==" + fromDate + "toDate===" + toDate
	    + "sdoCode===" + sdoCode + "mrCode====" + mrCode);
	  List<Payments> paymentsList = new ArrayList<Payments>();
	  paymentsList = paymentService.findByDate(fromDate, toDate, sdoCode,
	    mrCode);
	  model.put("paymentsList", paymentsList);
	  model.put("currentDate", new Date());
	  model.put("sdoCodes", siteLocationService.getAllSiteCodes());
	  return "collectionDetails";
	   }
	
	
	//AUDIT SPRING WEB SERVICE---------------------------------//
	//@Consumes("application/json")
	//@Produces("application/json")
	@ResponseBody
	@RequestMapping(value = "/audit", method = { RequestMethod.GET,RequestMethod.POST })
	public ArrayList<Resultspaymets> getAudit(@RequestBody String body) throws IOException, JSONException {
		
		BCITSLogger.logger.info("COMING INSIDE---------------->audit");
		Resultspaymets res = new Resultspaymets();
		ArrayList<Resultspaymets> list = new ArrayList<Resultspaymets>();
		
		try {
			
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
			JSONArray json = new JSONArray(body);
			for (int i = 0; i < json.length(); i++)
			{
				Payments payments = new Payments();
				JSONObject json1 = json.getJSONObject(i);
				BCITSLogger.logger.info("COMING INSIDE---------------->audit"+json1.get("CDDATE"));

				if (json1.isNull("RRNO") || json1.equals(""))
					json1.put("RRNO", "");
				else {
					payments.setRrno(json1.getString("RRNO").trim());
				}

				if (json1.isNull("MRCREDIT"))
					json1.put("MRCREDIT", "");
				else {
					payments.setMrCredit(json1.getString("MRCREDIT").trim());
				}

				if (json1.isNull("MRCODE"))
					json1.put("MRCODE", "");
				else {
					payments.setMrcode(json1.getString("MRCODE").trim());
				}

				if(json1.isNull("RDATE") || json1.get("RDATE") == null || json1.get("RDATE") == "") json1.put("RDATE", "");
				else {
					payments.setRdate(formatter.parse(json1.getString("RDATE").trim()));
				}

				if (json1.isNull("RECNO") || json1.equals("")  )json1.put("RECNO", "");
				else {
					payments.setRecno(json1.getString("RECNO").trim());
				}

				if(json1.isNull("AMOUNT") || json1.get("AMOUNT") == null || json1.get("AMOUNT") == "") json1.put("AMOUNT", "");
				else {
					payments.setAmount(Double.parseDouble(json1
							.getString("AMOUNT").trim()));
				}

				if (json1.isNull("TRANSACTIONID"))
					json1.put("TRANSACTIONID", "");
				else {
					payments.setTransactionid(json1.getString("TRANSACTIONID").trim());
				}

				if (json1.isNull("DEVICEID"))
					json1.put("DEVICEID", "");
				else {
					payments.setDeviceId(json1.getString("DEVICEID").trim());
				}

				if (json1.isNull("CDNO"))
					json1.put("CDNO", "");
				else {
					payments.setCdno(json1.getString("CDNO").trim());
				}

				if(json1.isNull("CDDATE") || json1.get("CDDATE") == null || json1.get("CDDATE") == "" || json1.get("CDDATE").equals("")) 
					json1.put("CDDATE", "");
				else
				{
					payments.setCddate(formatter1.parse(json1.getString("CDDATE").trim()));
				}

				if (json1.isNull("BANKNAME"))
					json1.put("BANKNAME", "");
				else {
					payments.setBankname(json1.getString("BANKNAME").trim());
				}

				if (json1.isNull("REMARKS"))
					json1.put("REMARKS", "");
				else {
					payments.setRemarks(json1.getString("REMARKS").trim());
				}
				if (json1.isNull("SDOCODE"))
					json1.put("SDOCODE", "");
				else {
					payments.setSdoCode(json1.getString("SDOCODE").trim());
				}

				if (json1.isNull("BRANCH"))
					json1.put("BRANCH", "");
				else {
					payments.setBranch(json1.getString("BRANCH").trim());
				}

				if (json1.isNull("PAYMODE"))
					json1.put("PAYMODE", "");
				else {
					payments.setPaymode(json1.getString("PAYMODE").trim());
				}


				if (json1.isNull("DATESTAMP"))json1.put("DATESTAMP", "");
				else 
				{
					payments.setDatestamp(new Timestamp(new Date().getTime()) );
				}
				
			 //   payments.setCcid("CC"+payments.getId());

				Resultspaymets resultspaymets = paymentService.saveJSONObject(payments);
				
				if(resultspaymets == null)
				{
					res.setRrno(payments.getRrno());
					res.setStatus("NOTUPDATED");
					res.setId(json1.getString("SQLITEID"));
					list.add(res);
				}
				else
				{
					resultspaymets.setId(json1.getString("SQLITEID"));
					list.add(resultspaymets);
				}
			}

		} 
		catch (Exception exception)
		{
			BCITSLogger.logger.info("Error while saving JSON object to DB");
			exception.printStackTrace();
		}
		return list;
	}

	@RequestMapping(value = "/updateAvailableCreditLimitMobilePaymentUser", method = RequestMethod.POST)
	public @ResponseBody
	Object getCreditLimitMobilePaymentUser(@RequestBody String user_List) throws JSONException {
		BCITSLogger.logger.info("in updateAvailableCreditLimitMobilePaymentUser...........");

		JSONArray arr = new JSONArray(user_List);

		JSONObject Asst_json_obj = arr.getJSONObject(0);
		String mrcode = Asst_json_obj.getString("MRCODE");
		String sdocode = Asst_json_obj.getString("SDOCODE");
		String amount = Asst_json_obj.getString("AMOUNT");
		String serverbalanceBefore = Asst_json_obj.getString("SERVERAVAILABLEBALANCE");
		BCITSLogger.logger.info("amount "+amount+" serverbalanceBefore "+serverbalanceBefore);
		
		String availableCredit="No Credit limit";
		String statusOfMobSynched="1";
			int status;
		//	availableCredit	=status+"";
			///BCITSLogger.logger.info("status" + status);
		
			List<MRCreditMasterEntity> list = mrCreditMasterService
					.findAllByMrcode(mrcode,sdocode);
			BCITSLogger.logger.info("list" + list.size());
			if (list.size() > 0) {
				availableCredit = list.get(0).getAvailCredit() + "";
				statusOfMobSynched=list.get(0).getLocalMobSynched().trim();
				BCITSLogger.logger.info("statusOfMobSynched   >>>>" + statusOfMobSynched+" ....."+availableCredit);
				if(statusOfMobSynched .equals("0"))
				{
					
					
				 amount=Double.parseDouble(amount)+(Double.parseDouble(availableCredit)-Double.parseDouble(serverbalanceBefore))+"";
					BCITSLogger.logger.info("statusOfMobSynched   >>>>" + statusOfMobSynched);

					status=mrCreditMasterService.updateAvailableCreditMobile(mrcode, sdocode, amount);
						int status1=mrCreditMasterService.updateStatusMobSynchMobile(mrcode,sdocode); 
						BCITSLogger.logger.info("list" + status1);

				}
				else
				{
					BCITSLogger.logger.info("statusOfMobSynched  else >>>>" );

					 status=mrCreditMasterService.updateAvailableCreditMobile(mrcode, sdocode, amount);
				}
				availableCredit=amount;
			} 
			

		return availableCredit;
	}

	
	/**
	 * MR Credit Master Access Management. ./mrCreditMasters
	 */
	@Link(label = "MR Credit Masters", family = "UserController", parent = "")
	@RequestMapping(value = "/mrCreditMasters", method = RequestMethod.GET)
	public String mrCreditMasterAccessManagement(
			@ModelAttribute("mrCreditMasters") MRCreditMasterEntity mrCreditMasters,
			HttpServletRequest request, ModelMap model, BindingResult errors) {

		//model.put("sdoCodes", locationService.findSdoAndSubdiv());
		model.put("sdoCodes", siteLocationService.getAllSiteCodes());
		model.put("mrcmasters", mrCreditMasterService.getAllCreditMasters());
		List<MRCreditMasterEntity> list=mrCreditMasterService.getAllCreditMasters();
		for (MRCreditMasterEntity mrCreditMasterEntity : list) 
		{
			
			String value=mrCreditMasterEntity.getSdoCode()+mrCreditMasterEntity.getMrCode();
			String cashCounterId="";
			if(value.length()<10)
			{
				cashCounterId=value;
				for (int k = 0; k<10; k++) 
				{
					if(cashCounterId.length()!=10)
					{
						cashCounterId="1"+cashCounterId;
					}
				}
			}
			BCITSLogger.logger.info("================>cashCounterId"+cashCounterId);
		}
		model.put("results", "notDisplay");
		return "mrCreditMasters";
	}

	
	@RequestMapping(value = "/getMrCodeData", method = RequestMethod.POST)
	public @ResponseBody Object getMrCodeData(@ModelAttribute("mrCreditMasters") MRCreditMasterEntity mrCreditMasters,@RequestParam("sdoCode") String sdoCode, HttpServletRequest request,ModelMap model)
	{
		//return mrDeviceAllocationService.findBySdoCode(sdoCode);
	    return null;
	}
	@RequestMapping(value = "/updateMRCreditMaster", method = RequestMethod.POST)
	public String updateMRCreditMaster(
			@ModelAttribute("mrCreditMasters") MRCreditMasterEntity mrCreditMasters,
			HttpServletRequest request, ModelMap model) {

		String value=mrCreditMasters.getSdoCode()+mrCreditMasters.getMrCode();
		String cashCounterId="";
		if(value.length()<10)
		{
			cashCounterId=value;
			for (int k = 0; k<10; k++) 
			{
				if(cashCounterId.length()<9)
				{
					cashCounterId="10"+cashCounterId;
				}
				if(cashCounterId.length()==9)
				{
					cashCounterId="1"+cashCounterId;
				}
			}
		}
		BCITSLogger.logger.info("================>cashCounterId"+cashCounterId);
		mrCreditMasters.setCashcounterid(cashCounterId);
		mrCreditMasters.setAvailCredit(new Double(0));
		mrCreditMasters.setTimestamp(new Timestamp(new Date().getTime()));

		if (mrCreditMasterService.checkMRCMasterAvail( mrCreditMasters.getSdoCode(), mrCreditMasters.getMrCode()) == null) 
		{
			mrCreditMasters.setLocalMobSynched("0");
			if (mrCreditMasterService.update(mrCreditMasters) != null) 
			{
				model.put("results", "MR Credit Master Added Successfully.");
			} else 
			{
				model.put("results", "MR Credit Master Cannot Added ");
			}
		} else {
			model.put("results", "MR Credit Master Already Added ");
		}
		model.put("sdoCodes", siteLocationService.getAllSiteCodes());
		model.put("mrcmasters", mrCreditMasterService.getAllCreditMasters());

		model.put("mrCreditMasters", new MRCreditMasterEntity());
		return "mrCreditMasters";
	}

	@RequestMapping(value = "/checkMRCAvail", method = RequestMethod.POST)
	public @ResponseBody
	Boolean checkMRCreditMasterAvailability(
			@ModelAttribute("mrCreditMasters") MRCreditMasterEntity mrCreditMasters,
			@RequestParam("sdoCode") String sdoCode,
			@RequestParam("mrCode") String mrCode, HttpServletRequest request,
			ModelMap model) {

		if (mrCreditMasterService.checkMRCMasterAvail(sdoCode, mrCode) != null) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * MR Deposit Access Management.........
	 */
	@RequestMapping(value = "/mrDepositMasters", method = RequestMethod.GET)
	public String mrDepositAccessManagement(
			@ModelAttribute("mrDeposits") MRDepositEntity mrDeposits,
			HttpServletRequest request, ModelMap model, BindingResult errors) {

		//model.put("sdoCodes", locationService.findSdoAndSubdiv());
		model.put("sdoCodes", siteLocationService.getAllSiteCodes());

		/*BCITSLogger.logger.info("Date Format:::::::::"
				+ new SimpleDateFormat("YYYY-MM-dd").format(new Date()));*/

		model.put("mrdepositsList", mrDepositService
				.getAllMRDepositsByDate(new SimpleDateFormat("yyyy-MM-dd")
						.format(new Date())));
		model.put("currentDate", new Date());
		model.put("results", "notDisplay");
		model.put("mrDeposits", new MRDepositEntity());
		return "mrDepositMasters";
	}

	@RequestMapping(value = "/makeDeposit", method = RequestMethod.POST)
	 public String mrMasterDeposit(@ModelAttribute("mrCreditMasters") MRCreditMasterEntity mrCreditMasters,@ModelAttribute("mrDeposits") MRDepositEntity mrDeposits,HttpServletRequest request, ModelMap model) 
	{
		String mrcode=request.getParameter("mrCode");
		System.out.println("mrcodemrcodemrcodemrcodemrcode"+mrcode);
		mrDeposits.setMrCode(mrcode);
	  mrDeposits.setTimestamp(new Timestamp(new Date().getTime()));
	  mrCreditMasters.setLocalMobSynched("0");
	  MRCreditMasterEntity mrc = mrCreditMasterService.checkMRCMasterAvail(mrDeposits.getSdoCode(), mrDeposits.getMrCode());
	  if (mrc != null) {
	   Double availCredit = mrc.getAvailCredit() + mrDeposits.getAmount();
	   if (mrDepositService.update(mrDeposits) != null) {

	    mrCreditMasterService.updateAvailCredit(availCredit,
	      mrDeposits.getMrCode(), mrDeposits.getSdoCode(),
	      mrCreditMasters.getLocalMobSynched());

	    model.put("results", " Deposited to Master Successfully.");
	   } else {

	    model.put("results", "Unable to Deposit to Master Account ");
	   }

	  } else {
	   model.put("results", "MR Credit Master is not available");

	  }
	  model.put("sdoCodes", siteLocationService.getAllSiteCodes());
	  model.put("mrdepositsList", mrDepositService
	    .getAllMRDepositsByDate(new SimpleDateFormat("yyyy-MM-dd")
	      .format(new Date())));
	  model.put("currentDate", new Date());
	  model.put("mrDeposits", new MRDepositEntity());	 
	  return "mrDepositMasters";
	 }
	/**
	 * To get All the deposits based on the selected date.
	 */

	@RequestMapping(value = "/getAllDepositsByDate", method = {
			RequestMethod.GET, RequestMethod.POST })
	public String getAllDepositsByDate(@RequestParam String reqDate,
			@ModelAttribute("mrDeposits") MRDepositEntity mrDeposits,
			HttpServletRequest request, ModelMap model) {

		List<MRDepositEntity> deplist = mrDepositService
				.getAllMRDepositsByDate(reqDate);

		if (deplist.size() > 0) {
			model.put("results", deplist.size() + "  Deposits on " + reqDate);
		} else {
			model.put("results", "No Deposits on " + reqDate);
		}
		model.put("sdoCodes", siteLocationService.getAllSiteCodes());
		model.put("mrdepositsList", deplist);

		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date d = formatter.parse(reqDate);
			model.put("currentDate", d);
			BCITSLogger.logger.info("date=" + d + "formatter class==========="
					+ d.getClass());
		} catch (ParseException e) {

			e.printStackTrace();
		}

		return "mrDepositMasters";
	}
	

	@Link(label = "MR Audit Details", family = "UserController", parent = "Cash Collection")
	@RequestMapping(value = "/mrAuditDetails", method = RequestMethod.GET)
	public String mrAuditDetails(
			@ModelAttribute("siteLocations") SiteLocationEntity siteLocations,
			HttpServletRequest request, ModelMap model,
			BindingResult bindingResult) {
		model.put("sdoCodes", siteLocationService.getAllSiteCodes());
		model.put("fromDate", new Date());
		model.put("toDate", new Date());
		model.put("results", "notDisplay");

		return "mrAuditDetails";
	}

	@Link(label = "MR Audit Details", family = "UserController", parent = "Cash Collection")
	@RequestMapping(value = "/mrAuditByDates", method = RequestMethod.POST)
	public String mrAuditByDates(@RequestParam("fromDate") String fromDate,
			@RequestParam("sdoCode") String sdoCode,
			@RequestParam("mrCode") String mrCode,
			@RequestParam("toDate") String toDate, ModelMap model) {

		try {

			Date begin = new Date(new SimpleDateFormat("dd-MM-yyyy").parse(
					fromDate).getTime());


			Date endDate = new Date(new SimpleDateFormat("dd-MM-yyyy").parse(
					toDate).getTime());
			model.put("frodmDate", begin);
			model.put("toDate", endDate);
			List<Date> list = new ArrayList<Date>();
			list.add(new Date(begin.getTime()));
			while (begin.compareTo(endDate) < 0) {
				 
				  begin = new Date(begin.getTime() + 86400000);
				list.add(new Date(begin.getTime()));

				System.out.println("dates=========" + begin);
				  }
			model.put("dates", list);
		} catch (ParseException e) {

			e.printStackTrace();
		}

		/*
		 * java.util.Date begin = new Date(startDate.getTime());
		 * java.util.LinkedList list = new java.util.LinkedList(); list.add(new
		 * Date(begin.getTime()));
		 * 
		 * while(begin.compareTo(endDate)<0){
		 * 
		 * begin = new Date(begin.getTime() + 86400000); list.add(new
		 * Date(begin.getTime())); }
		 */
		model.put("mrcmasters", mrCreditMasterService
				.getAllMRCreditMastersByTwoDates(fromDate, toDate, mrCode,
						sdoCode));
		model.put("mrdeposits", mrDepositService.getAllMRDepositesByTwoDates(
				fromDate, toDate, sdoCode, mrCode));

		BCITSLogger.logger.info("mrdeposits====={} \n and mrCmasters======{}",
				model.get("mrdeposits"), model.get("mrcmasters"));

		model.put("paylist", paymentService.findByTwoDates(fromDate, toDate,
				sdoCode, mrCode));

		model.put("sdoCodes", siteLocationService.getAllSiteCodes());
		model.put("results", "notDisplay");

		return "mrAuditDetails";
	}
	
	
	@Link(label = "Reset Cosumer Data", family = "UserController", parent = "")
	@RequestMapping(value = "/resetConsumerData", method = {RequestMethod.POST, RequestMethod.GET })
	public String resetConsumerData(@ModelAttribute("hhbmcons") HHBM_ConsumerEntity hhbmcons,HttpServletRequest request, BindingResult bindingResult,ModelMap model) 
	{	
		model.put("sdoCodesList", hhbm_ConsumerService.findAllSdoCodes());
		model.put("results", "notDisplay");
		return "resetConsumerData";
	}

	@Link(label = "Reset Cosumer Data", family = "UserController", parent = "")
	@RequestMapping(value = "/getMatchedBillMonths", method = RequestMethod.POST)
	public @ResponseBody
	List<String> getMatchedBillMonths(
			@ModelAttribute("hhbmcons") HHBM_ConsumerEntity hhbmcons,
			@RequestParam("sdoCode") String sdoCode,
			HttpServletRequest request, BindingResult bindingResult,
			ModelMap model) {

		return hhbm_ConsumerService.findMatchedBillMonths(sdoCode);
	}

	@Link(label = "Reset Cosumer Data", family = "UserController", parent = "")
	@RequestMapping(value = "/getMatchedBmdReadings", method = RequestMethod.POST)
	public @ResponseBody
	List<String> getMatchedBmdReadings(
			@ModelAttribute("hhbmcons") HHBM_ConsumerEntity hhbmcons,
			@RequestParam("sdoCode") String sdoCode,
			@RequestParam("billMonth") String billMonth,
			HttpServletRequest request, BindingResult bindingResult,
			ModelMap model) {

		return hhbm_ConsumerService.findMatchedBmdReading(sdoCode, billMonth);
	}

	@Link(label = "Reset Cosumer Data", family = "UserController", parent = "")
	@RequestMapping(value = "/getConsumerData", method = { RequestMethod.POST,
			RequestMethod.GET })
	public String getConsumerData(
			@ModelAttribute("hhbmcons") HHBM_ConsumerEntity hhbmcons,
			@RequestParam("sdoCode") String sdoCode,
			@RequestParam("bmdReading") String bmdReading,
			@RequestParam("billMonths") String billMonth, ModelMap model) {

		model.put("totalConnections", hhbm_ConsumerService.getTotalConnections(
				sdoCode, billMonth, bmdReading));

		model.put("billedConns", hhbm_ConsumerService.getTotalBilled(sdoCode,
				billMonth, bmdReading));
		model.put("totalDownloaded", hhbm_ConsumerService.getTotalDownloaded(
				sdoCode, billMonth, bmdReading));
		model.put("billMonth", billMonth);		
		model.put("sdoCodesList", hhbm_ConsumerService.findAllSdoCodes());
		model.put("results", "notDisplay");
		model.put("sdoCodesList", hhbm_ConsumerService.findAllSdoCodes());
		return "resetConsumerData";
	}

	@Link(label = "Reset Cosumer Data", family = "UserController", parent = "")
	@RequestMapping(value = "/updateDownStatus", method = RequestMethod.POST)
	public String updateDownStatus(
			@ModelAttribute("hhbmcons") HHBM_ConsumerEntity hhbmcons,
			BindingResult binding,
			ModelMap model) {

		if (hhbm_ConsumerService.updateDownStatus(hhbmcons) > 0) {
			model.put("results", "Down Status Updated Successfully");
		} else {

			model.put("results", "Down Status Cannot be Updated");
		}

		model.put("totalConnections", hhbm_ConsumerService.getTotalConnections(
				hhbmcons.getSdoCode(), hhbmcons.getBillMonth(),
				hhbmcons.getBmdReading()));

		model.put("billedConns", hhbm_ConsumerService.getTotalBilled(
				hhbmcons.getSdoCode(), hhbmcons.getBillMonth(),
				hhbmcons.getBmdReading()));
		model.put("totalDownloaded", hhbm_ConsumerService.getTotalDownloaded(
				hhbmcons.getSdoCode(), hhbmcons.getBillMonth(),
				hhbmcons.getBmdReading()));
		model.put("billMonth", hhbmcons.getBillMonth());
		model.put("sdoCodesList", hhbm_ConsumerService.findAllSdoCodes());

		return "resetConsumerData";
	}

	@Link(label = "Circle Details ", family = "UserController", parent = "")
	@RequestMapping(value = "/circlDetailsImport", method = RequestMethod.GET)
	public String circleDetails(HttpServletRequest request)
	{
		BCITSLogger.logger.info("In circle details controller ");
		return "showCircleDetails";
	}
	
	@Link(label = "Circle Details ", family = "UserController", parent = "")
	@RequestMapping(value = "/circleDetailsUpload", method ={RequestMethod.GET,RequestMethod.POST})
	public String addCircleDetails(CircleDataUpload circleDataUpload,ModelMap model,HttpServletRequest request)
	{
		BCITSLogger.logger.info("In add circle details controller ");
		circleDetailsService.circleDetailsUpload(circleDataUpload, model, request);
		/*if(errmsg.equalsIgnoreCase("error"))
		{
		 model.put("msg","Circle Details Added Succesfully ");
		}
		else
		{
		 model.put("msg","Circle Details Added Succesfully ");
		}*/
		 model.put("msg","Circle Details Added Succesfully ");
		 return "showCircleDetails";
		
	}
	
	@Link(label = "Sub Division Wise Report", family = "UserController", parent = "")
	@RequestMapping(value = "/subDevisionDetails", method = RequestMethod.GET)
	public String subDevisionDetails(@ModelAttribute("manualsubDivDetails")ManualSubDivDetails manualSubDivDetails,BindingResult result,HttpServletRequest request,ModelMap map)
	{
		BCITSLogger.logger.info("In SubdivisionDetails:::::::::Controller ");
		map.put("details",locationService.findSdoAndSubdiv());
		map.put("allDetails",manualEntrySubDivService.getAllDetails());
		return "subdivisionDetails";
	}
	
	
	@Link(label = "Reports ", family = "UserController", parent = "")
	@RequestMapping(value = "/meterReaderWiseReports", method = RequestMethod.GET)
	public String meterReaderWiseReports(@ModelAttribute("hhbmEntity") HHBM_ConsumerEntity hhbmEntity,@ModelAttribute("hhbmEntity1") HHBM_DownloadEntity hhbm_DownloadEntity,BindingResult result,HttpServletRequest request,ModelMap map)
	{
		BCITSLogger.logger.info("In Meter reader wise reports :::::::::Controller ");
		map.put("month",hhbm_DownloadService.currBillMonth());
		return "meterReaderWiseReports";
	}
	
	
	@Link(label = "Reports ", family = "UserController", parent = "")
	@RequestMapping(value = "/meterReaderWiseSearchReport", method = {RequestMethod.GET,RequestMethod.POST})
	public String meterReaderWiseSearch(@ModelAttribute("hhbmEntity") HHBM_ConsumerEntity hHBM_ConsumerEntity,@ModelAttribute("hhbmEntity1") HHBM_DownloadEntity hhbm_DownloadEntity,BindingResult result,HttpServletRequest request,ModelMap map)
	{
		BCITSLogger.logger.info("In serach Meter reader wise reports :::::::::Controller ");
		String group=request.getParameter("group");
		System.out.println("groupe============"+group);
		hhbm_DownloadService.makeReportList(hhbm_DownloadEntity, hHBM_ConsumerEntity, group, map);
		//map.put("serachOne",hhbm_ConsumerService.serachCriteriaOne(hHBM_ConsumerEntity,group,map));
		//map.put("serachTwo",hhbm_DownloadService.serachCriteriaTwo(hhbm_DownloadEntity, group, map));
	    //map.put("searchThree",hhbm_DownloadService.serachCriteriaThree(hhbm_DownloadEntity, group, map));
		map.put("month",hhbm_DownloadService.currBillMonth());
		//map.put("searchTwo",hhbm_DownloadService.serachCriteriaTwo(hhbm_DownloadEntity,map));
		//map.put("searchThree",hhbm_DownloadService.serachCriteriaThree(hhbm_DownloadEntity,map));
		//map.put("searchFour",hhbm_DownloadService.serachCriteriaThree(hhbm_DownloadEntity,map));
		
		return "meterReaderWiseReports";
	}
	
	
	@Link(label = "AddSubDiv ", family = "UserController", parent = "")
	@RequestMapping(value = "/addSudDivDivDetailsManualy", method = {RequestMethod.GET,RequestMethod.POST})
	public String addSudDivDivDetailsManualy(@ModelAttribute("manualsubDivDetails")ManualSubDivDetails manualSubDivDetails,BindingResult result,HttpServletRequest request,ModelMap map)
	{
		BCITSLogger.logger.info("In add sub div manually  :::::::::Controller ");
		manualEntrySubDivService.save(manualSubDivDetails);
		map.put("msg","Details added succsesfully");
		map.put("allDetails",manualEntrySubDivService.getAllDetails());
		map.put("details",locationService.findSdoAndSubdiv());
        map.put("manualSubDivDetails",new ManualSubDivDetails());
 
		return "subdivisionDetails";
	}
	
	@Link(label = "SubDivUpdate ", family = "UserController", parent = "")
	@RequestMapping(value = "/getSelectedDetails/{operation}", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody  Object getSelectedDetails(@PathVariable Integer operation,HttpServletResponse response,HttpServletRequest request,ModelMap model) throws JsonParseException, JsonMappingException, IOException
	{
		BCITSLogger.logger.info("In add sub div manually  :::::::::Controller ");
		return manualEntrySubDivService.find(operation);
		
		//return manualEntrySubDivService.getSelectedDetails(manualSubDivDetails).toString();
	}
	
	
	
	@Link(label = "SubDivUpdate ", family = "UserController", parent = "")
    @RequestMapping(value="/updateSubDivDetails",method={RequestMethod.POST,RequestMethod.GET})
  	public String updateSubDivDetails(@ModelAttribute("manualsubDivDetails")ManualSubDivDetails manualSubDivDetails,BindingResult result,HttpServletRequest request,ModelMap map)
  	{
  		
    	BCITSLogger.logger.info("In update sub div manually  :::::::::Controller ");
  		manualEntrySubDivService.update(manualSubDivDetails);
  		map.put("allDetails",manualEntrySubDivService.getAllDetails());
		map.put("details",locationService.findSdoAndSubdiv());
		map.put("manualSubDivDetails",new ManualSubDivDetails());
		map.put("msg","Sub Div details updated succsessfully");
  	    return "subdivisionDetails";
  	}
	
	
	@RequestMapping(value="/getMeterData",method=RequestMethod.GET)
    public @ResponseBody Object getMeterData(HttpServletRequest request) 
    {
		BCITSLogger.logger.info("======getMeterData====MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM=======>"+request.getParameter("data"));
		return request.getParameter("data");
    }
	
	@RequestMapping(value="/getMeterData1",method=RequestMethod.POST)
    public @ResponseBody Object getMeterData1(@RequestBody String theft_list) throws JSONException
    {
		BCITSLogger.logger.info("======getMeterData1==MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM=================>"+theft_list);
		JSONArray arr = new JSONArray(theft_list);
		BCITSLogger.logger.info("======getMeterData1==MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM=================>"+arr.length());
		return arr.length();
    }
	
	@RequestMapping(value = "/getDivisionMobiles/{division}", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody  Object getSelectedDetails(@PathVariable String division,HttpServletResponse response,HttpServletRequest request,ModelMap model) throws JsonParseException, JsonMappingException, IOException
	{
		BCITSLogger.logger.info("In add sub div manually  :::::::::Controller "+division);
		return mrDeviceService.getDivisionMobiles(division, model);
	}
	
	@RequestMapping(value = "/getSectionMobiles/{sitecode}", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody  Object getSectionMobiles(@PathVariable String sitecode,HttpServletResponse response,HttpServletRequest request,ModelMap model) throws JsonParseException, JsonMappingException, IOException
	{
		BCITSLogger.logger.info("In add sub div manually  :::::::::Controller "+sitecode);
		return mrDeviceService.getSectionMobiles(sitecode, model);
	}
	
	@RequestMapping(value = "/getDivisionMobilesCesc/{division}/{sitecode}", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody  Object getSelectedDetails(@PathVariable String division,@PathVariable String sitecode,HttpServletResponse response,HttpServletRequest request,ModelMap model) throws JsonParseException, JsonMappingException, IOException
	{
		BCITSLogger.logger.info("In add sub div manually  :::::::::Controller "+division);
		return mrDeviceService.getDivisionMobilesCesc(division,sitecode, model);
	}
	
	
	
	 //TODO MOBILE PART
		@RequestMapping(value = "/getDisReConnectionMobileUser", method = RequestMethod.POST)
		public @ResponseBody
		Object getDisReConnectionList(@RequestBody String user_List) throws JSONException
		{
			BCITSLogger.logger.info("In getDisReConnectionMobileUser...........");
			JSONArray arr = new JSONArray(user_List);
			JSONObject obj = arr.getJSONObject(0);
			String sdocode = obj.getString("SDOCODE");
			int listno = Integer.parseInt(obj.getString("LISTNO"));
			
			BCITSLogger.logger.info("Sdocode   = "+sdocode+"   List no  = "+listno);
			
			List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();;
			
				try 
				{
					List<LocationOracleEntity> locList=locationOracleService.getLocationData(sdocode, model);
					BCITSLogger.logger.info("Location List Size " + locList.size());
					String schema=locList.get(0).getDbuser();
					BCITSLogger.logger.info("schema name = " + schema);
					list = disConnectionReConnectionService.getAllDisConnectionListData(listno, schema);
					BCITSLogger.logger.info("list" + list.size());
				} catch (Exception exception) {
					BCITSLogger.logger.info("getDisReConnectionMobileUser Main Try Catch  "+exception.getMessage());
					exception.printStackTrace();

				}

			return list;
		}


		@RequestMapping(value = "/UpdateDisReConnectionMobileUser", method = RequestMethod.POST)
		public @ResponseBody
		Object UpdateDisReConnectionList(@RequestBody String user_List)	throws JSONException {
			BCITSLogger.logger				.info("in UpdateDisReConnectionMobileUser...........");
			ArrayList<ResultDisconnection> list = new ArrayList<ResultDisconnection>();
			JSONArray arr = new JSONArray(user_List);
			int serverid = 0;
			BCITSLogger.logger.info("in UpdateDisReConnectionMobileUser..........."+arr);
			
			for (int i = 0; i < arr.length(); i++) {
				
				try {
					JSONObject obj = arr.getJSONObject(i);
					
					String type =obj.getString("TYPE"); // DISCONNECTION OR RECONNECTION
					
					String sdocode = obj.getString("SDOCODE");
					String remarks = obj.getString("REMARKS");
					serverid = Integer.parseInt(obj.getString("SERVERID"));
					int statusdis = Integer.parseInt(obj.getString("STATUS"));
					
					int disfr = Integer.parseInt(obj.getString("DISFR"));
					
					String date = obj.getString("ACTION_DATE");
					String image = obj.getString("IMAGE");
					
					byte[] imageByte =Base64.decodeBase64(image);
					BCITSLogger.logger.info("image length..........."+imageByte.length);

					
					List<LocationOracleEntity> locList=locationOracleService.getLocationData(sdocode, model);
					BCITSLogger.logger.info("Location List Size " + locList.size());
					String schema=locList.get(0).getDbuser();
					BCITSLogger.logger.info("schema name = " + schema);
					
					ResultDisconnection disconnection = disConnectionReConnectionService.updateDisConnectionListMobile(schema,type,serverid, remarks,date, disfr, statusdis, imageByte);
					list.add(disconnection);

				} catch (Exception exception) {
					
				}
			}

			return list;

		}
	
		@RequestMapping(value = "/getDisconnectionPaymentStatus", method = RequestMethod.POST)
		public @ResponseBody
		Object getDisconnectionPaymentStatus(@RequestBody String user_List) throws JSONException
		{
			BCITSLogger.logger.info("In getDisconnectionPaymentStatus...........");
			JSONArray arr = new JSONArray(user_List);
			JSONObject obj = arr.getJSONObject(0);
			String sdocode = obj.getString("SDOCODE");
			int listno = Integer.parseInt(obj.getString("LISTNO"));
			String rrNo = obj.getString("ACCOUNTNO");
			BCITSLogger.logger.info("Sdocode   = "+sdocode+"   List no  = "+listno+"  rrNo "+rrNo);
			List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();;
				try 
				{
					List<LocationOracleEntity> locList=locationOracleService.getLocationData(sdocode, model);
					BCITSLogger.logger.info("Location List Size " + locList.size());
					String schema=locList.get(0).getDbuser();
					BCITSLogger.logger.info("schema name = " + schema);
					list = disConnectionReConnectionService.getPaymentStatus(listno, schema,rrNo);
					BCITSLogger.logger.info("list" + list.size());
				} catch (Exception exception) {
					BCITSLogger.logger.info("getDisReConnectionMobileUser Main Try Catch  "+exception.getMessage());
					exception.printStackTrace();
				}
			return list;
		}
		
		
		@Link(label="On Air version Update", family="UserController", parent = "" )
		@RequestMapping(value="/onAirVersionUpdateCesc",method=RequestMethod.GET)
	    public String onAirVersionUpdateCesc(@ModelAttribute("onairverupdtCesc") OnAirVersionUpdationEntityNew onairverupdtCesc,BindingResult result,ModelMap model, HttpServletRequest request)
		{
			try 
			{
				BCITSLogger.logger.info("In User DashBoard-On Air Version Update");
				userService.getRecentPath(request.getRequestURI().substring(4,request.getRequestURI().length()), request);
				model.put("onAirVerList", onAirVersionUpdationServiceCesc.findAll(model,request));
			}
			catch (Exception e)
			{
				e.printStackTrace();
				model.put("results","Error while loading");
				return "onAirVersionUpdateCesc";
			}
			return "onAirVersionUpdateCesc";
		}
		
		@Link(label="On Air version Update", family="UserController", parent = "" )
	    @RequestMapping(value = "/addNewVersionUpdateCesc", method = {RequestMethod.POST, RequestMethod.GET })
		public String addNewVersionUpdateCesc(@ModelAttribute("onairverupdtCesc") OnAirVersionUpdationEntityNew onairverupdt,@RequestParam("uploadedapk") MultipartFile apkFile,BindingResult result, ModelMap model, HttpServletRequest request)
	    {
	    	long res=onAirVersionUpdationServiceCesc.checkVersionExist(onairverupdt.getApkVersion(),onairverupdt.getRemarks());
	    	if(res>0)
	    	{
	    		model.put("results","Entered version "+onairverupdt.getApkVersion()+" already exist");
	    	}
	    	else
	    	{
			/*onairverupdt.setReleaseFlag("not released");
			onairverupdt.setTimestamp(new Timestamp(new Date().getTime()));
			onairverupdt.setUploadedyBy((String) request.getSession(false).getAttribute("username"));
			onairverupdt.setApkName(onairverupdt.getApkName()+onairverupdt.getApkVersion()+".apk");*/
			try 
			{
			OnAirVersionUpdationEntityNew onair = onAirVersionUpdationServiceCesc.findByRemarks(onairverupdt.getApkVersion(),onairverupdt.getRemarks());
			String pathname ="";			
			if(onair!=null)
			{
				pathname =onair.getApkPath();
			}
			else
			{
				if(onairverupdt.getRemarks().equalsIgnoreCase("TRMCESC"))
				{
					pathname ="E:\\TRMAPPCESC\\";
				}
				else if(onairverupdt.getRemarks().equalsIgnoreCase("CESCBILLING"))
				{
					pathname ="E:\\CESCLIVE_APK\\";
				}
				else if(onairverupdt.getRemarks().equalsIgnoreCase("METERREPLACE"))
				{
					pathname ="E:\\METERREPLACEAPP\\";
				}
				else if(onairverupdt.getRemarks().equalsIgnoreCase("DISCONNECTION_CESC"))
				{
					pathname ="E:\\DISCONNECTIONAPP\\";
				}
				//E:\TRMAPPCESC\
				//E:\METERREPLACEAPP\
				//E:\DISCONNECTIONAPP\
				//E:\CESCLIVE_APK\
				
			}
			String apkPath="";
			if (!apkFile.isEmpty()) {
				try {					
					File directory = new File(pathname);
					if (!directory.exists()) 
					{
						directory.mkdirs();
					}
					 apkPath = pathname + onairverupdt.getApkName()+onairverupdt.getApkVersion()+".apk";
					BCITSLogger.logger.info("==================apkPath=======>"+apkPath);
				//	onairverupdt.setApkPath(pathname);
					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(apkPath)));
					 stream.write(apkFile.getBytes());
					stream.close();
				} catch (Exception e) {
					e.getMessage();
				}
				onair.setApkName(onairverupdt.getApkName()+onairverupdt.getApkVersion()+".apk");
				onair.setApkVersion(onairverupdt.getApkVersion());
				onair.setUploadedyBy((String) request.getSession(false).getAttribute("username"));
				onair.setTimestamp(new Timestamp(new Date().getTime()));
				onair.setRemarks(onairverupdt.getRemarks());
				onair.setApkPath(pathname);
				//onair.setReleaseFlag("not released");
				onair.setReleaseFlag("released");
				onair.setAppRemarks(onairverupdt.getAppRemarks());
				onAirVersionUpdationServiceCesc.update(onair);
				
				model.put("results", "APK Files stored successfully..");
			}
			} catch (Exception e) {
				model.put("results","Error while uploading APK Files");
				e.printStackTrace();
			}
			
	    	}
			BCITSLogger.logger.info("In User DashBoard-On Air Version Update");

			model.put("onAirVerList", onAirVersionUpdationServiceCesc.findAll(model,request));
			model.put("onairverupdtCesc", new OnAirVersionUpdationEntityNew());
			return "onAirVersionUpdateCesc";
		}
		
		@Link(label="On Air version Update", family="UserController", parent = "" )
		@RequestMapping(value = "/releaseAPKVersionCesc", method = RequestMethod.POST)
		public String releaseAPKVersionCesc(@ModelAttribute("allowedVersion") AllowedDeviceVersionsEntity allowedVersionsEntity,ModelMap model,	BindingResult bindingResult,@ModelAttribute("onairverupdtCesc") OnAirVersionUpdationEntityNew onairverupdt1,HttpServletRequest request,HttpServletResponse response, @RequestParam("apkid") Integer apkid)
	    {
			try 
			{
				OnAirVersionUpdationEntityNew onairverupdt = onAirVersionUpdationServiceCesc.find(apkid);
				AllowedDeviceVersionsEntity allowedNew = new AllowedDeviceVersionsEntity();
				allowedNew.setAppName(onairverupdt.getApkName());
				allowedNew.setDateTimeStamp(new Timestamp(new Date().getTime()));
				allowedNew.setRemarks(onairverupdt.getRemarks());
				allowedNew.setVersion_name(onairverupdt.getApkVersion());
				allowedNew.setId(onairverupdt.getId());
				allowedNew.setAllowedFlag("allowed");
				if (allowedDeviceVersionService.update(allowedNew) != null) {
					onairverupdt.setReleaseFlag("released");
					onAirVersionUpdationServiceCesc.update(onairverupdt);
					model.put("results",onairverupdt.getApkName() + "-"+ onairverupdt.getApkVersion()+ " is released successfully...");
				} else {
					model.put("results",onairverupdt.getApkName() + "-"	+ onairverupdt.getApkVersion()+ " is Not released...");
				}

				model.put("onAirVerList", onAirVersionUpdationServiceCesc.findAll(model,request));
				model.put("onairverupdtCesc", new OnAirVersionUpdationEntityNew());
			} 
			catch (Exception e) 
			{
				model.put("onAirVerList", onAirVersionUpdationServiceCesc.findAll(model,request));
				model.put("onairverupdtCesc", new OnAirVersionUpdationEntityNew());
				return "onAirVersionUpdate";
			}
			return "onAirVersionUpdateCesc";
		}
		
		@RequestMapping(value = "/downloadAPKFileCesc", method = { RequestMethod.GET,	RequestMethod.POST })
		 public @ResponseBody void downloadAPKFileCesc(@ModelAttribute("onairverupdtCesc") OnAirVersionUpdationEntityNew onairverupdt1,HttpServletRequest request,HttpServletResponse response, @RequestParam("apkid") Integer apkid) {

			OnAirVersionUpdationEntityNew onairverupdt = onAirVersionUpdationServiceCesc.find(apkid);
			if(onairverupdt != null){
				String fullPath = onairverupdt.getApkPath()
						+ onairverupdt.getApkName();
						
				BCITSLogger.logger.info("-=-=-=-=-=-=-=fullPath:::::::::::::::::::--->"+fullPath);
				File downloadFile = new File(fullPath);
				try {
					FileInputStream inputStream = new FileInputStream(downloadFile);
					// get MIME type of the file
					String mimeType = request.getServletContext().getMimeType(
							fullPath);
					if (mimeType == null) {
						// set to binary type if MIME mapping not found
						mimeType = "application/octet-stream";
					}
			/* System.out.println("MIME type: " + mimeType); */

					// set content attributes for the response
					response.setContentType(mimeType);
					response.setContentLength((int) downloadFile.length());

					// set headers for the response
					String headerKey = "Content-Disposition";
					String headerValue = String.format(
							"attachment; filename=\"%s\"", downloadFile.getName());
					response.setHeader(headerKey, headerValue);

					 // get output stream of the response
			        OutputStream outStream = response.getOutputStream();
			 
			        byte[] buffer = new byte[4096];
			        int bytesRead = -1;
			 
			        // write bytes read from the input stream into the output stream
			        while ((bytesRead = inputStream.read(buffer)) != -1) {
			            outStream.write(buffer, 0, bytesRead);
			        }
			 
			        inputStream.close();
			        outStream.close();
				} catch (Exception e) {

					e.printStackTrace();
				}
			  
			}

		   }
	
		
		
		
		
		
}

