package com.bcits.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.bcits.entity.HHBM_DownloadEntity;
import com.bcits.entity.SiteLocationEntity;
import com.bcits.service.BillingDataService;
import com.bcits.service.HHBM_ConsumerService;
import com.bcits.service.HHBM_DownloadService;
import com.bcits.service.MasterDeviceStatusService;
import com.bcits.service.ModuleMasterService;
import com.bcits.service.SiteLocationService;
import com.bcits.service.UserService;
import com.bcits.utility.BCITSLogger;
import com.bcits.utility.Link;

@Controller
public class PhotoBilling {

	@Autowired
	private HHBM_DownloadService hhbm_DownloadService;
	
	@Autowired
	private HHBM_ConsumerService hhbm_ConsumerService;
	
	@Autowired
    private UserService userService;
	
	@Autowired
	private ModuleMasterService moduleMasterService;
	
	@Autowired
	private BillingDataService billingDataService;
	
	@Autowired
	private MasterDeviceStatusService deviceStatusService;
	
	@Autowired
	private SiteLocationService siteLocationService;
	
	@Link(label="Real-Time status", family="UserController", parent = "Home" )
	@RequestMapping(value="/realTimeStatus",method=RequestMethod.GET)
	public String realTimeStatus(@ModelAttribute("hhbmDownLoad") HHBM_DownloadEntity hhbmDownLoad,ModelMap model, HttpServletRequest request,HHBM_DownloadEntity downloadEntity,BindingResult bindingResult)
	{
		BCITSLogger.logger.info("In User DashBoard-Real Time Status------------->");
		String date=hhbm_DownloadService.getDate4(new Date());
		userService.getRecentPath(request.getRequestURI().substring(4,request.getRequestURI().length()), request);	
		List list=hhbm_DownloadService.findLatestData(date,model,request);
		hhbm_DownloadService.getBmdReadersAndBilled(date,model);			
		model.put("allvalues", list);		
		return "realTimeStatus";
	
	
	}
    
    @RequestMapping(value="/GetAllrealTimeStatusData",method={RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody Object getRealTimeStatus(HttpServletResponse response,HttpServletRequest request,ModelMap model) throws JsonParseException, JsonMappingException, IOException
	{
		BCITSLogger.logger.info("In  real time status ajax ");
		String date=hhbm_DownloadService.getDate4(new Date());
		List list=hhbm_DownloadService.findLatestData(date,model,request);			
		list.add(hhbm_DownloadService.getBmdReadersAndBilledForAjax(date, model));//Always it contains in last list
		//List<Object[]> data=(List<Object[]>) model.get("notLiveSections");			
		//BCITSLogger.logger.info("=========================>data"+data.size());
		//list.add(data);
		return list;
	}
    
    @RequestMapping(value="/realTimeStatusBilled",method={RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody Object getRealTimeStatusBilled(HttpServletResponse response,HttpServletRequest request,ModelMap model) throws JsonParseException, JsonMappingException, IOException
	{
		BCITSLogger.logger.info("In  realTimeStatusBilled ");
		String date=hhbm_DownloadService.getDate4(new Date());
		String count=hhbm_DownloadService.getTotalBilled(date,model);
		return count;
	}
    
    @RequestMapping(value="/getRealStatusBilledData/{mrName}/{sdoCode}",method={RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody Object getRealStatusBilledData(@PathVariable String mrName,@PathVariable String sdoCode,HttpServletRequest request,ModelMap model)
	{
		BCITSLogger.logger.info("In get real status total billed data ");
		String date=hhbm_DownloadService.getDate4(new Date());
		return hhbm_DownloadService.getRealStatusBilledData(request, sdoCode, mrName, date, model);
		
	}
    @RequestMapping(value="/getRealStatusBilledData1/{mrName}/{sdoCode}/{date}",method={RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody Object getRealStatusBilledData1(@PathVariable String mrName,@PathVariable String sdoCode,@PathVariable String date,HttpServletRequest request,ModelMap model)
	{
		BCITSLogger.logger.info("In get real status total billed data ");
		return hhbm_DownloadService.getRealStatusBilledData1(request, sdoCode, mrName, date, model);
	}
    
    @RequestMapping(value="/realTimeBmdReaders",method={RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody Object getRealTimeBmdReaders(HttpServletResponse response,HttpServletRequest request,ModelMap model) throws JsonParseException, JsonMappingException, IOException
	{
		BCITSLogger.logger.info("In  realTimeBmdReaders ");
		String date=hhbm_DownloadService.getDate4(new Date());
		//String count=hhbm_DownloadService.getBmdReaders(date,model); // by manjunath
		return 0;
	}
    
    @RequestMapping(value="/realTimeStatusDeviceHealth/{mrCode}/{sdoCode}",method={RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody Object realTimeStatusDeviceHealth(@PathVariable String mrCode,@PathVariable int sdoCode,HttpServletResponse response,HttpServletRequest request,ModelMap model) throws JsonParseException, JsonMappingException, IOException
	{
		BCITSLogger.logger.info("In  realTimeStatusDeviceHealth "+mrCode+" "+sdoCode);
		String date=hhbm_DownloadService.getDate4(new Date());
		return deviceStatusService.getDeviceHealth(mrCode, sdoCode, date);		
	}
    
    @Link(label="Binder Wise view", family="UserController", parent = "Home" )
	@RequestMapping(value="/binderWiseView",method={RequestMethod.GET,RequestMethod.POST})
	public String binderWiseView(ModelMap model, HttpServletRequest request)
	{
		BCITSLogger.logger.info("In User DashBoard-BinderWise view");
		userService.getRecentPath(request.getRequestURI().substring(4,request.getRequestURI().length()), request);
		if(request.getSession().getAttribute("sdoVal")!=null)
		{
			List<HHBM_DownloadEntity> binderList=hhbm_DownloadService.getDistinctBinder((String)request.getSession().getAttribute("sdoVal"));
			model.put("binderList", binderList);
		}
		List<HHBM_DownloadEntity> sdoList=hhbm_DownloadService.getDistinctSdo();
		//List<String> billMonthList=hhbm_DownloadService.getLastSixMonths();
		List<String> billMonthList=hhbm_DownloadService.getBillMonths();
		model.put("sdoCodeval", request.getSession().getAttribute("sdoVal"));
		model.put("billMonthList", billMonthList);
		model.put("sdoList", sdoList);
		model.put("results", "notDisplay");		
		return "binderWiseView";
	}
    
    @RequestMapping(value="/getDistinctBinder/{sdoCode}" ,method={RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody Object getDistinctBinder(@PathVariable("sdoCode") String sdoCode, HttpServletResponse response,HttpServletRequest request,ModelMap model) throws JsonParseException, JsonMappingException, IOException
	{
		BCITSLogger.logger.info("In User dash Boards-Binder wise view");
		List<HHBM_DownloadEntity> binderList=hhbm_DownloadService.getDistinctBinder(sdoCode);
		return binderList;
	}
    
    @Link(label="Binder Wise view", family="UserController", parent = "Home" )
	@RequestMapping(value="/getBinderBillData",method=RequestMethod.POST)
	public String showReportForBinderNumber(ModelMap model, HttpServletRequest request)
	{
		BCITSLogger.logger.info("In User DashBoard-BinderWise view"+request.getParameter("sdoCode"));
		userService.getRecentPath(request.getRequestURI().substring(4,request.getRequestURI().length()), request);
		hhbm_DownloadService.getBinderWiseBill(request.getParameter("sdoCode"),request.getParameter("binderNo"),request.getParameter("billMonth"),model,request);
		List<HHBM_DownloadEntity> binderList=hhbm_DownloadService.getDistinctBinder(request.getParameter("sdoCode"));
		List<HHBM_DownloadEntity> sdoList=hhbm_DownloadService.getDistinctSdo();
		List<HHBM_DownloadEntity> bndrList=(List<HHBM_DownloadEntity>) model.get("binderBillList");
		model.put("results", "notDisplay");
		
		if(bndrList.size()>0)
		{
			model.put("bndrList", bndrList);
			model.put("binderNo", request.getParameter("binderNo"));
			model.put("billMonth", request.getParameter("billMonth"));
			model.put("sdCd", request.getParameter("sdoCode"));
			model.put("binderList", binderList);
		}
		else
		{
			//model.put("results", "Records are not available for the BINDER NO-"+"\t"+request.getParameter("binderNo")+"\t"+"and BILL MONTH-"+"\t"+request.getParameter("billMonth"));
			model.put("results", "Records are not available.");
		}
		//List<String> billMonthList=hhbm_DownloadService.getLastSixMonths();
		List<String> billMonthList=hhbm_DownloadService.getBillMonths();
		model.put("billMonthList", billMonthList);
		
		model.put("sdoList", sdoList);
		return "binderWiseView";
	}
    
    @RequestMapping(value="/getBndrWiseBillData" , method=RequestMethod.POST)
	public String showReportForBinderNumber(HttpServletRequest request,ModelMap model) 
	{
    	BCITSLogger.logger.info("In User DashBoard-BinderWise view");
    	//List<HHBM_DownloadEntity> bndrBilldetails=hhbm_DownloadService.getAllBinderWiseData(request.getParameter("binderNo"),request.getParameter("billMonth"),request.getParameter("billDate"),model,request);
    	hhbm_DownloadService.getAllData(request.getParameter("sdocode"), "", request.getParameter("billDate"), "", request.getParameter("value"),request.getParameter("billMonth"),request.getParameter("binderNo"), model, request);
    	hhbm_DownloadService.getBinderWiseBill(request.getParameter("sdocode"),request.getParameter("binderNo"),request.getParameter("billMonth"),model,request);
    	List<HHBM_DownloadEntity> bndrList=(List<HHBM_DownloadEntity>) model.get("binderBillList");
    	List<HHBM_DownloadEntity> binderList=hhbm_DownloadService.getDistinctBinder(request.getParameter("sdocode"));
		//List<String> billMonthList=hhbm_DownloadService.getLastSixMonths();
    	List<String> billMonthList=hhbm_DownloadService.getBillMonths();
		List<HHBM_DownloadEntity> sdoList=hhbm_DownloadService.getDistinctSdo();
		model.put("results", "notDisplay");
		
			model.put("bndrList", bndrList);
			model.put("binderNo", request.getParameter("binderNo"));
			model.put("billMonth", request.getParameter("billMonth"));
			model.put("sdCd", request.getParameter("sdocode"));
		
		model.put("sdoList", sdoList);
		model.put("billMonthList", billMonthList);
		model.put("binderList", binderList);
    	//model.put("bndrBilldetails", bndrBilldetails);
		
    	return "binderWiseView";
	}
    
    @RequestMapping(value="/imageDisplay/getImage/{consumerNo}/{sdoCode}/{billMonth}",method=RequestMethod.GET)
	public void realTimeStatusGetImage(ModelMap model, HttpServletRequest request,HttpServletResponse response,@PathVariable String consumerNo,@PathVariable String sdoCode,@PathVariable String billMonth) throws IOException
	{
		//BCITSLogger.logger.info("In User DashBoard-Image display"+consumerNo+" "+sdoCode+" "+billMonth);
		 hhbm_DownloadService.findOnlyImage(model,request,response,consumerNo,billMonth,sdoCode);
    	
	}
    
    @Link(label="Meter Reader wise view", family="UserController", parent = "Home" )
	@RequestMapping(value={"/meterReaderwiseView","/gpsWiseView"},method={RequestMethod.GET,RequestMethod.POST})
	public String meterReaderwiseView(ModelMap model, HttpServletRequest request,@ModelAttribute("hhbmDownLoad") HHBM_DownloadEntity hhbmDownLoad)
	{
		BCITSLogger.logger.info("In User DashBoard-Meter Reader wise view "+request.getServletPath());
		userService.getRecentPath(request.getRequestURI().substring(4,request.getRequestURI().length()), request);	
		List<HHBM_DownloadEntity> sdoList=hhbm_DownloadService.getDistinctSdo();
		//String sdoCode=(String) request.getSession().getAttribute("sdoVal");
		if(request.getSession().getAttribute("sdoVal")!=null)
		{
			List<HHBM_DownloadEntity> bmdList=hhbm_DownloadService.getDistinctBmdList((String)request.getSession().getAttribute("sdoVal"),model,request);
			model.put("bmdList", bmdList);
		}
		//BCITSLogger.logger.info("BMD READERS LIST DATA................>"+bmdList);
		
		model.put("sdoCodeval", request.getSession().getAttribute("sdoVal"));
		model.put("sdoList", sdoList);
		model.put("results","notDisplay");
	   
	    if(request.getServletPath().equals("/meterReaderwiseView"))
	    {
	    	return "meterReaderwiseView";
	    }
		else
		{
			return "gpsWiseView";
		}
	}
    	  
    @RequestMapping(value="/getDistinctBmds/{sdoCode}" ,method={RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody Object getDistinctBmd(@PathVariable("sdoCode") String sdoCode, HttpServletResponse response,HttpServletRequest request,ModelMap model) throws JsonParseException, JsonMappingException, IOException
	{
		BCITSLogger.logger.info("In User dash Boards-Meter Reader wise view");
		List<HHBM_DownloadEntity> bmdList=hhbm_DownloadService.getDistinctBmdList(sdoCode,model,request);
		return bmdList;
	}
    
    @RequestMapping(value={"/getBmdReadersData","/getGPSReadersData"} ,method={RequestMethod.POST,RequestMethod.GET})
	public  String getBmdReadersData(HttpServletResponse response,HttpServletRequest request,ModelMap model,@ModelAttribute("hhbmDownLoad") HHBM_DownloadEntity hhbmDownLoad) throws JsonParseException, JsonMappingException, IOException 
	{
    	try 
    	{
    		String d1=hhbm_DownloadService.getDate3(new Date());
    		BCITSLogger.logger.info("In User dash Boards-Meter Reader wise view");
    		List<HHBM_DownloadEntity> bmdDataList=hhbm_DownloadService.getBmdDataList(request.getParameter("sdoCode"),request.getParameter("bmdReader"),d1,model,request);
    		List<HHBM_DownloadEntity> bmdList=hhbm_DownloadService.getDistinctBmdList(request.getParameter("sdoCode"),model,request);
    		//BCITSLogger.logger.info("BMD READERS LIST DATA................>"+bmdDataList.size());
    		model.put("results", "notDisplay");
    		if(bmdDataList.size()>0)
    		{
    			model.put("bmdDataList", bmdDataList);
    			model.put("sdcd", request.getParameter("sdoCode"));
    			model.put("bmdRdr", request.getParameter("bmdReader"));
    			model.put("bmdList", bmdList);
    		}
    		else
    		{
    			model.put("results", "Records are not available.");
    			model.put("sdoCodeval", request.getSession().getAttribute("sdoVal"));
    		}
    		
    		List<HHBM_DownloadEntity> sdoList=hhbm_DownloadService.getDistinctSdo();
    		 model.put("sdoList", sdoList);
    		 
    		
		} 
    	catch (Exception e)
    	{
    		List<HHBM_DownloadEntity> sdoList=hhbm_DownloadService.getDistinctSdo();
   		 model.put("sdoList", sdoList);
			e.printStackTrace();
		}
    	if(request.getServletPath().equals("/getBmdReadersData"))
	    {
	    	return "meterReaderwiseView";
	    }
		else
		{
			return "gpsWiseView";
		}
    	
	}
    
    @RequestMapping(value="/getBmdAllData" , method={RequestMethod.POST,RequestMethod.GET})
	public String getBmdReaderData(HttpServletRequest request,ModelMap model,@ModelAttribute("hhbmDownLoad") HHBM_DownloadEntity hhbmDownLoad) 
	{
		try 
		{
			String d1=hhbm_DownloadService.getDate3(new Date());
	    	BCITSLogger.logger.info("In User dash Boards-Meter Reader wise view ===== "+request.getParameter("sCode")+" "+request.getParameter("bmdRr")+" "+ request.getParameter("bDate")+" "+request.getParameter("value")+" "+request.getParameter("bMonth"));
	    	//System.out.println("LIST SIZE--------------->"+request.getParameter("sCode"));
	    	System.out.println("LIST SIZE--------------->"+request.getParameter("bDate"));
	    	//System.out.println("LIST SIZE--------------->"+request.getParameter("value"));
	    	//System.out.println("LIST SIZE--------------->"+request.getParameter("bmdRr"));
	    	//List<HHBM_DownloadEntity> bmdBillList=hhbm_DownloadService.getAllBmdData(request.getParameter("sCode"),request.getParameter("bDate"),request.getParameter("bmdRdr"),model,request);
	    	hhbm_DownloadService.getAllData(request.getParameter("sCode"), request.getParameter("bmdRr"), request.getParameter("bDate"), "", request.getParameter("value"),request.getParameter("bMonth"),"",model, request);
	    	List<HHBM_DownloadEntity> bmdDataList=hhbm_DownloadService.getBmdDataList(request.getParameter("sCode"),request.getParameter("bmdRr"),d1,model,request);
	    	//System.out.println("LIST SIZE--------------->"+bmdDataList.size());
	    	List<HHBM_DownloadEntity> sdoList=hhbm_DownloadService.getDistinctSdo();
			List<HHBM_DownloadEntity> bmdList=hhbm_DownloadService.getDistinctBmdList(request.getParameter("sCode"),model,request);
			model.put("sdcd", request.getParameter("sCode"));
			model.put("bmdRdr", request.getParameter("bmdRr"));
			model.put("bmdList", bmdList);
			model.put("sdoList", sdoList);
			model.put("DeviceSiteCode", request.getParameter("sCode"));
			model.put("DeviceBmr", request.getParameter("bmdRr"));
			model.put("dateOfBill",new SimpleDateFormat("dd-MM-yyyy").format(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("bDate"))));
			List<SiteLocationEntity> list=siteLocationService.findBySdocode(request.getParameter("sCode"));
			String sectionName="";
			for (SiteLocationEntity siteLocationEntity : list) 
			{
				sectionName=siteLocationEntity.getSection();
			}
			model.put("DeviceSection", sectionName);
			model.put("bmdDataList", bmdDataList);
		} catch (Exception e) 
		{
			List<HHBM_DownloadEntity> sdoList=hhbm_DownloadService.getDistinctSdo();
			model.put("sdoList", sdoList);
			//e.printStackTrace();
			model.put("results", "notDisplay");
			return "meterReaderwiseView";
		}
    	//model.put("bmdBillList", bmdBillList);
		model.put("results", "notDisplay");
		model.put("sdoCodeval", request.getSession().getAttribute("sdoVal"));
    	return "meterReaderwiseView";
	}
    
    @RequestMapping(value="/getGPSBmdAllData" , method={RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody Object getGPSBmdAllData(HttpServletRequest request,ModelMap model,@ModelAttribute("hhbmDownLoad") HHBM_DownloadEntity hhbmDownLoad) 
	{
    	 List<HHBM_DownloadEntity> list=null;
    	try 
    	{
    		BCITSLogger.logger.info("coming= "+hhbmDownLoad.getBillMonth()+" "+hhbmDownLoad.getSdoCode()+" "+hhbmDownLoad.getBmd_Reading()+" "+hhbmDownLoad.getBillDate()+" "+request.getParameter("value"));
            list= hhbm_DownloadService.getAllData(hhbmDownLoad.getSdoCode(), hhbmDownLoad.getBmd_Reading(),hhbmDownLoad.getBillDate(), "", request.getParameter("value"),hhbmDownLoad.getBillMonth(),"",model, request);
		}
    	catch (Exception e) 
    	{
			e.printStackTrace();
		}
    	return list;
	}
    
    
    @RequestMapping(value="/getDeviceAllData" , method=RequestMethod.POST)
	public String showDeviceData(HttpServletRequest request,ModelMap model) 
	{
    	try 
    	{
    		BCITSLogger.logger.info("In User dash Boards-DeviceWise view");
        	String date=hhbm_DownloadService.getDate3(new Date());
        	//System.out.println("LIST SIZE--------------->"+request.getParameter("value"));
        	//List<HHBM_DownloadEntity> deviceBillList=hhbm_DownloadService.getDeviceAllData(request.getParameter("bDate"),request.getParameter("dvceId"),model,request);
        	hhbm_DownloadService.getAllData("", "", request.getParameter("bDate"), request.getParameter("dvceId"), request.getParameter("value"),"","", model, request);
        	List<HHBM_DownloadEntity> deviceData=hhbm_DownloadService.getDeviceDataList(request.getParameter("dvceId"),date,model,request);
        	model.put("deviceData", deviceData);
        	//model.put("deviceBillList", deviceBillList);
        	model.put("results", "notDisplay");
        	hhbm_DownloadService.getDataForDeviceHealth(request.getParameter("dvceId"),request.getParameter("bDate"),model);
        	
		}
    	catch (Exception e) 
    	{
			e.printStackTrace();
		}
    	
    	return "deviceWiseView";
	}
    
	/*public String hhbmDowload(HttpServletRequest request,ModelMap model) 
	{
    	List<HHBM_DownloadEntity> deviceBillList=hhbm_DownloadService.getAllData(request.getParameter("sCode"),request.getParameter("bmdRdr"),request.getParameter("bDate"),request.getParameter("dvceId"),request.getParameter("value"),model,request);
    	return "deviceWiseView";
	}*/
    
    
    @Link(label="DeviceWise view", family="UserController", parent = "Home" )
	@RequestMapping(value="/deviceWiseView",method={RequestMethod.GET,RequestMethod.POST})
	public String deviceWiseView(ModelMap model, HttpServletRequest request)
	{
		BCITSLogger.logger.info("In User DashBoard-DeviceWise view");
		userService.getRecentPath(request.getRequestURI().substring(4,request.getRequestURI().length()), request);		
		//List<HHBM_DownloadEntity> sdoList=hhbm_DownloadService.getDistinctSdo();
		//model.put("sdoList", sdoList);
		model.put("results", "notDisplay");
		return "deviceWiseView";
	}
    
    @RequestMapping(value="/getDeviceWiseData" ,method=RequestMethod.POST)
	public  String getDeviceWiseData(HttpServletResponse response,HttpServletRequest request,ModelMap model) 
	{
		BCITSLogger.logger.info("In User dash Boards-DeviceWise view");
    	String date=hhbm_DownloadService.getDate3(new Date());
		List<HHBM_DownloadEntity> deviceData=hhbm_DownloadService.getDeviceDataList(request.getParameter("deviceId").trim(),date,model,request);
		//List<HHBM_DownloadEntity> sdoList=hhbm_DownloadService.getDistinctSdo();
		//model.put("sdoList", sdoList);
		model.put("results", "notDisplay");
		if(deviceData.size()>0)
		{
			model.put("deviceId", request.getParameter("deviceId"));
			model.put("deviceData", deviceData);
		}
		else
		{
			model.put("results", "Records are not available.");
		}
		
		return "deviceWiseView";
	}
    
/*===========================================================================================================================================*/	    
    
    
    @Link(label="Reading Daywise view", family="UserController", parent = "Home" )
	@RequestMapping(value="/readingDayWiseView",method={RequestMethod.GET,RequestMethod.POST})
	public String readingDayWiseView(ModelMap model, HttpServletRequest request)
	{
		BCITSLogger.logger.info("In User DashBoard-Reading Daywise view");
		userService.getRecentPath(request.getRequestURI().substring(4,request.getRequestURI().length()), request);
		List<HHBM_DownloadEntity> sdoList=hhbm_DownloadService.getDistinctSdo();
		model.put("sdoList", sdoList);
		model.put("sdoCodeval", request.getSession().getAttribute("sdoVal"));
		model.put("results", "notDisplay");
		
		return "readingDayWiseView";
	}
    
    @Link(label="Home", family="UserController", parent = "" )
	@RequestMapping(value="/getBmdList",method=RequestMethod.POST)
	public String getBmdList(ModelMap model, HttpServletRequest request)
	{
		BCITSLogger.logger.info("In User DashBoard-Reading Daywise view");
		userService.getRecentPath(request.getRequestURI().substring(4,request.getRequestURI().length()), request);
		List<HHBM_DownloadEntity> bmdList=null;
		if(request.getParameter("billDt")=="")
		{
			String currentdate=billingDataService.getDate3(new Date());
			 bmdList=hhbm_DownloadService.getDistinctBmd(request.getParameter("sdoCode"),currentdate,model,request);
		}
		else
		{
			bmdList=hhbm_DownloadService.getDistinctBmd(request.getParameter("sdoCode"),request.getParameter("billDt"),model,request);
		}
		 
		model.put("results", "notDisplay");
		if(bmdList.size()>0)
		{
			model.put("sdcd", request.getParameter("sdoCode"));
			model.put("bmdList", bmdList);
		}
		else
		{
			model.put("results", "Records are not available.");
		}
		List<HHBM_DownloadEntity> sdoList=hhbm_DownloadService.getDistinctSdo();
		model.put("sdoList", sdoList);
		
		return "readingDayWiseView";
	}
    
    @RequestMapping(value="/getBmdBillData" , method=RequestMethod.POST)
	public String getBmdBillData(HttpServletRequest request,ModelMap model) 
	{
    	BCITSLogger.logger.info("In User DashBoard-Reading Daywise view");
    	//List<HHBM_DownloadEntity> dataList=hhbm_DownloadService.getAllBmdData(request.getParameter("sdoCode"),request.getParameter("billDate"),request.getParameter("bmdReader"),model,request);
    	hhbm_DownloadService.getAllData(request.getParameter("sdoCode"), request.getParameter("bmdReader"), request.getParameter("billDate"), "", request.getParameter("value"),request.getParameter("billMonth"),"", model, request);
    	List<HHBM_DownloadEntity> bmdList=hhbm_DownloadService.getDistinctBmd(request.getParameter("sdoCode"),request.getParameter("billDate"),model,request);
    	List<HHBM_DownloadEntity> sdoList=hhbm_DownloadService.getDistinctSdo();
		model.put("sdoList", sdoList);
    	model.put("bmdList", bmdList);
		model.put("results", "notDisplay");
		model.put("sdcd", request.getParameter("sdoCode"));
		try 
		{
			model.put("DeviceSiteCode", request.getParameter("sdoCode"));
			model.put("DeviceBmr", request.getParameter("bmdReader"));
			model.put("dateOfBill",new SimpleDateFormat("dd-MM-yyyy").format(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("billDate"))));
			List<SiteLocationEntity> list=siteLocationService.findBySdocode(request.getParameter("sdoCode"));
			String sectionName="";
			for (SiteLocationEntity siteLocationEntity : list) 
			{
				sectionName=siteLocationEntity.getSection();
			}
			model.put("DeviceSection", sectionName);
		} catch (ParseException e) 
		{
			e.printStackTrace();
		}
    	return "readingDayWiseView";
	}
    @Link(label="Consumerwise view", family="UserController", parent = "Home" )
    @RequestMapping(value={"/consumerWiseView","/meterWiseView"},method={RequestMethod.GET,RequestMethod.POST})
    public String consumerWiseView(ModelMap model, HttpServletRequest request,HttpServletResponse response) throws IOException
    {
    	BCITSLogger.logger.info("In Consumerwise/meterNo view"+ request.getSession().getAttribute("sdoVal"));
		userService.getRecentPath(request.getRequestURI().substring(4,request.getRequestURI().length()), request);	
		List<HHBM_DownloadEntity> sdoList=hhbm_DownloadService.getDistinctSdo();
		model.put("sdoList", sdoList);
		model.put("sdoCodeval", request.getSession().getAttribute("sdoVal"));
		model.put("results", "notDisplay");
		
		if(request.getServletPath().equals("/consumerWiseView"))
		{
			return "consumerWiseView";
		}
		else {
			return "meterNoWiseView";
		}
		
    }
    
    @RequestMapping(value="/getDistinctConsumer/{sdoCode}" ,method={RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody Object showDistinctConsumer(@PathVariable("sdoCode") String sdoCode, HttpServletResponse response,HttpServletRequest request,ModelMap model) throws JsonParseException, JsonMappingException, IOException
	{
		BCITSLogger.logger.info("In User dash Boards-Consumerwise view");
		List<HHBM_DownloadEntity> consList=hhbm_DownloadService.getDistinctConsList(sdoCode,model,request);
		return consList;
	}
    
    @RequestMapping(value={"/getConsumerDetails","/getMeterDetails"} ,method=RequestMethod.POST)
	public  String showConsumerDetails(HttpServletResponse response,HttpServletRequest request,ModelMap model) 
	{
    	BCITSLogger.logger.info("In User dash Boards-Consumer wise view");
    	String eBillMonth=null;
    	String sBillMonth=null;		
    	 List<String> blMonth=hhbm_DownloadService.getBillMonths();
    	 if(blMonth.size()>5)
    	 {
    		    sBillMonth=blMonth.get(5);
				eBillMonth=blMonth.get(0);
    	 }
		else 
		{
			eBillMonth=blMonth.get(0);
			sBillMonth=blMonth.get(blMonth.size()-1);
		}
    	 List<HHBM_DownloadEntity> list=null;
    	 
    	 if(request.getServletPath().equals("/getConsumerDetails"))
 		{
    		 list=hhbm_DownloadService.getConsumerDetails(request.getParameter("sdoCode"),request.getParameter("consumerNo"),eBillMonth,sBillMonth,model,request);
 		}
    	 else {
    		 list=hhbm_DownloadService.getMeterNoWiseDetails(request.getParameter("sdoCode"),request.getParameter("consumerNo"),eBillMonth,sBillMonth,model,request);
		}
		
		List<HHBM_DownloadEntity> sdoList=hhbm_DownloadService.getDistinctSdo();
		model.put("results", "notDisplay");
		if(list.size()>0)
		{	
			model.put("sdoCd", request.getParameter("sdoCode"));
			model.put("consNo", request.getParameter("consumerNo"));
			model.put("hhbmList", list);
		}
		else
		{
			//model.put("results", "Records are not available for the SDOCODE-"+"\t"+request.getParameter("sdoCode")+"\t"+"and CONSUMER NO-"+"\t"+""+"\t"+request.getParameter("consumerNo"));
			model.put("results", "Records are not available");
		}
		model.put("sdoList", sdoList);
		
		if(request.getServletPath().equals("/getConsumerDetails"))
		{
			return "consumerWiseView";
		}
		else {
			return "meterNoWiseView";
		}
	}
    
    
    /**
	 * Billing Summary Reports::::::
	 * 
	 */
    //@Link(label="Billing Summary Reports", family="UserController", parent = "Home" )
	@RequestMapping(value="/billingSummaryReport",method={RequestMethod.POST,RequestMethod.GET})
	public String billingSummaryReports(ModelMap model, HttpServletRequest request,HttpServletResponse response) 
	{
		BCITSLogger.logger.info("In User DashBoard-Billing Summary Reports");
		userService.getRecentPath(request.getRequestURI().substring(4,request.getRequestURI().length()), request);	
		//model.put("sdoCodeval", request.getSession().getAttribute("sdoVal"));
		List<HHBM_DownloadEntity> sdoList=hhbm_DownloadService.getDistinctSdo();
		List<String> billMonthList=hhbm_DownloadService.getBillMonths();
		model.put("billMonthList", billMonthList);
		model.put("sdoList", sdoList);
		model.put("results", "notDisplay");	
		return "billingSummaryReport";
	}
    
    /**
	 * Billing Summary Reports Displaying method::::::
	 * 
	 */
    @Link(label="Billing Summary Reports", family="UserController", parent = "Home" )
	@RequestMapping(value="/getReportDetails",method=RequestMethod.POST)
	public String showReportDetails(ModelMap model, HttpServletRequest request) 
	{
    	String sdocode=request.getParameter("sdoCode");
    	String bMonth=request.getParameter("billMonth");
    	String reportName=request.getParameter("reportName");
    	BCITSLogger.logger.info("Coming Data------------------->"+reportName);
    	model.put("results", "notDisplay");
    	if(reportName.toUpperCase().equals("BINDERWISE")||reportName.toUpperCase().equals("MRWISE")
    			||reportName.toUpperCase().equals("BINDERMRWISE"))
    	{
    		BCITSLogger.logger.info("Coming Data------------------->"+reportName);
    		List<Object> reportList=hhbm_ConsumerService.getReportDetails(sdocode, bMonth, reportName,model);
    		 if(reportList.size()>0)
    		 {
    			 if(request.getSession().getAttribute("sdoVal")!=null)
    				{
    				 	model.put("reportList", reportList);
    				 	model.put("sdoCodeval", request.getSession().getAttribute("sdoVal"));
    				}
    			 else
    			 	{
    				 	model.put("reportList", reportList);
    			 	}
    		 }
    		 else
    		 {
    			 model.put("results", "Records are not available.");
    		 }
    		 
    	}
    	else if(reportName.toUpperCase().equals("BILLDATEWISE")||reportName.toUpperCase().equals("MRBILLDATEWISE")
    			||reportName.toUpperCase().equals("BINDERBILLDATEWISE")||reportName.toUpperCase().equals("BINDERMRBILLDATEWISE"))
    	{
    		List reportList=hhbm_DownloadService.getReportDetails(sdocode,bMonth,reportName,model);
    		if(reportList.size()>0)
    		 {
    			if(request.getSession().getAttribute("sdoVal")!=null)
				{
				 	model.put("reportList", reportList);
				 	model.put("sdoCodeval", request.getSession().getAttribute("sdoVal"));
				}
    			 else
    			 	{
    				 	model.put("reportList", reportList);
    			 	}
    		 }
    		 else
    		 {
    			 model.put("results", "Records are not available");
    		 }
    		
    	}
    	
    	List<HHBM_DownloadEntity> sdoList=hhbm_DownloadService.getDistinctSdo();
		List<String> billMonthList=hhbm_DownloadService.getBillMonths();
		model.put("billMonthList", billMonthList);
		model.put("sdoList", sdoList);
		return "billingSummaryReport";
	}
    
	@RequestMapping(value={"/showArrearswise"},method={RequestMethod.GET,RequestMethod.POST})
	public String showArrearswise(ModelMap model, HttpServletRequest request,@ModelAttribute("hhbmDownLoad") HHBM_DownloadEntity hhbmDownLoad)
	{
		List<HHBM_DownloadEntity> sdoList=hhbm_DownloadService.getDistinctSdo();
		model.put("sdoList", sdoList);
		model.put("results","notDisplay");
		model.put("sdoCodeval", request.getSession().getAttribute("sdoVal"));
		List<String> billMonthList=hhbm_DownloadService.getBillMonths();
		model.put("billMonthList", billMonthList);
		String cuurentDate=new SimpleDateFormat("yyyyMM").format(new Date());
		model.put("cuurentDate", cuurentDate);
		return "arrearsWiseView";
	}
	
	@RequestMapping(value = "/getConsumersArrear/{sdocode}/{billmonth}/{arrearrange}", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody  Object getConsumersArrear(@PathVariable String sdocode,@PathVariable String billmonth,@PathVariable String arrearrange,HttpServletResponse response,HttpServletRequest request,ModelMap model) throws JsonParseException, JsonMappingException, IOException
	{
		BCITSLogger.logger.info("In add sub div manually  :::::::::Controller "+arrearrange+"---"+billmonth+"---"+sdocode);
		return hhbm_ConsumerService.getConsumersArrear(sdocode,billmonth, arrearrange,model);
	}
	
	@RequestMapping(value = "/getConsumersArrearMobile", method = {RequestMethod.GET,RequestMethod.POST}, headers = "Accept=application/json")
    public @ResponseBody  List<?> getMrTrackLiveMobile(@RequestBody String user_List) throws JsonParseException, JsonMappingException, IOException
    {
        JSONArray arr;
        String sdocode;
        String billmonth;
        String arrearrange;
        try {
            arr = new JSONArray(user_List);
            JSONObject Asst_json_obj = arr.getJSONObject(0);
            sdocode = Asst_json_obj.getString("sdocode");
            billmonth = Asst_json_obj.getString("billmonth");
            arrearrange = Asst_json_obj.getString("arrearrange");
            BCITSLogger.logger.info("In add sub div manually :::::::::Controller "+sdocode+"---"+billmonth+"---"+arrearrange);
            return hhbm_ConsumerService.getConsumersArrearMobile(sdocode,billmonth, arrearrange);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
} 
    
	
	@RequestMapping(value = "/viewConsumerOnMap/{rrno}/{billmonth}/{sdocode}", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody  Object viewConsumerOnMap(@PathVariable String rrno,@PathVariable String billmonth,@PathVariable String sdocode,HttpServletResponse response,HttpServletRequest request,ModelMap model) throws JsonParseException, JsonMappingException, IOException
	{
		BCITSLogger.logger.info("In add sub div manually  :::::::::Controller "+rrno+"---"+sdocode);
		return hhbm_DownloadService.viewConsumerOnMap(rrno,billmonth,sdocode,model);
	}
	
	@RequestMapping(value = "/viewConsumerOnMap/{rrno}/{billmonth}/{sdocode}/{date}", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody  Object viewConsumerOnMap1(@PathVariable String rrno,@PathVariable String billmonth,@PathVariable String sdocode,@PathVariable String date,HttpServletResponse response,HttpServletRequest request,ModelMap model) throws JsonParseException, JsonMappingException, IOException
	{
		BCITSLogger.logger.info("In add sub div manually  :::::::::Controller "+rrno+"---"+sdocode);
		return hhbm_DownloadService.viewConsumerOnMap1(rrno,billmonth,sdocode,date.replaceAll("-", "/"),model);
	}
	
	@RequestMapping(value="/getMobileHealth/{mrCode}/{sdoCode}/{date}",method={RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody Object getMobileHealth(@PathVariable String mrCode,@PathVariable String sdoCode,@PathVariable String date,HttpServletResponse response,HttpServletRequest request,ModelMap model) throws JsonParseException, JsonMappingException, IOException
	{
		BCITSLogger.logger.info("In  realTimeStatusDeviceHealth====="+mrCode+"==="+sdoCode+"==="+date);
		return hhbm_DownloadService.getDeviceHealth(mrCode, sdoCode, date);		
	}
	
}
