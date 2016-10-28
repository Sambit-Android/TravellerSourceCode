package com.bcits.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bcits.entity.Device_Logs_Entity;
import com.bcits.entity.MRDeviceAllocationEntity;
import com.bcits.entity.MRDeviceEntity;
import com.bcits.entity.MasterDeviceStatusEntity;
import com.bcits.service.DeviceLogService;
import com.bcits.service.MRDeviceAllocationService;
import com.bcits.service.MRDeviceService;
import com.bcits.service.MasterDeviceStatusService;
import com.bcits.service.MrMasterOracleService;
import com.bcits.service.SiteLocationService;
import com.bcits.utility.BCITSLogger;
import com.bcits.utility.ConnectionClass;


@Controller
public class CoreModuleController {
	
 @Autowired
 private MasterDeviceStatusService masterDeviceStatusService;
 
	@Autowired
	private MRDeviceService mrDeviceService;
	  
	@Autowired
	private DeviceLogService deviceLogService;
	
	@Autowired
	private MrMasterOracleService masterOracleService;
	
	@Autowired
	private MRDeviceAllocationService mrDeviceAllocationService;
	
	@Autowired
	private SiteLocationService siteLocationService;
	
	@ResponseBody
	@RequestMapping(value = "/insertDeviceInformation", method = RequestMethod.POST)
	public String getinsertDeviceInformation(@RequestBody String body) throws IOException, JSONException {
		
		BCITSLogger.logger.info("COMING INSIDE---------------->insertDeviceInformation");
		
		String status="notinserted";
		 try{
		JSONObject json = new JSONObject(body);
          
		MasterDeviceStatusEntity deviceStatusEntity = new MasterDeviceStatusEntity();
		BCITSLogger.logger.info("COMING INSIDE---------------->insertDeviceInformation"+json.getString("deviceid"));
		deviceStatusEntity.setDeviceid(json.getString("deviceid"));
		deviceStatusEntity.setBatterylevel(Integer.parseInt(json.getString("batterylevel")));
		deviceStatusEntity.setSignalstrength(Integer.parseInt(json.getString("signalstrength")));
		deviceStatusEntity.setMemoryexternalavailable(Float.parseFloat(json.getString("memoryexternalavailable")));
		deviceStatusEntity.setMemoryexternaltotal(Float.parseFloat(json.getString("memoryexternaltotal")));
		deviceStatusEntity.setMemoryinternalaltotal(Float.parseFloat(json.getString("memoryinternalaltotal")));
		deviceStatusEntity.setMemoryinternalavailable(Float.parseFloat(json.getString("memoryinternalavailable")));
		deviceStatusEntity.setRemarks(json.getString("remarks"));
		deviceStatusEntity.setAppversion(json.getString("appversion"));
		deviceStatusEntity.setReporteddateandtime(new Timestamp(Long.parseLong(json.getString("reporteddateandtime"))));		
		deviceStatusEntity.setDatetimestamp(new Timestamp(new Date().getTime()));		

		masterDeviceStatusService.save(deviceStatusEntity);
		 status="inserted";
       
        	
        }
        catch(Exception e){
        	e.printStackTrace();
        	status="notinserted";
        }
		return status;
	}
	
	@ResponseBody
	@RequestMapping(value = "/postDeviceInformation", method = RequestMethod.POST)
	public String postDeviceInformation(@RequestBody String body) throws IOException, JSONException {
		
		BCITSLogger.logger.info("COMING INSIDE---------------->insertDeviceInformation");
		
		String status="notinserted";
		 try{
		JSONObject json = new JSONObject(body);
          
		MasterDeviceStatusEntity deviceStatusEntity = new MasterDeviceStatusEntity();
		BCITSLogger.logger.info("COMING INSIDE---------------->insertDeviceInformation"+json.getString("deviceid"));
		deviceStatusEntity.setDeviceid(json.getString("deviceid"));
		deviceStatusEntity.setBatterylevel(Integer.parseInt(json.getString("batterylevel")));
		deviceStatusEntity.setSignalstrength(Integer.parseInt(json.getString("signalstrength")));
		deviceStatusEntity.setMemoryexternalavailable(Float.parseFloat(json.getString("memoryexternalavailable")));
		deviceStatusEntity.setMemoryexternaltotal(Float.parseFloat(json.getString("memoryexternaltotal")));
		deviceStatusEntity.setMemoryinternalaltotal(Float.parseFloat(json.getString("memoryinternalaltotal")));
		deviceStatusEntity.setMemoryinternalavailable(Float.parseFloat(json.getString("memoryinternalavailable")));
		deviceStatusEntity.setRemarks(json.getString("remarks"));
		deviceStatusEntity.setAppversion(json.getString("appversion"));
		deviceStatusEntity.setReporteddateandtime(new Timestamp(Long.parseLong(json.getString("reporteddateandtime"))));		
		deviceStatusEntity.setDatetimestamp(new Timestamp(new Date().getTime()));		

		masterDeviceStatusService.save(deviceStatusEntity);
		 status="inserted";
       
        	
        }
        catch(Exception e){
        	e.printStackTrace();
        	status="notinserted";
        }
		return status;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/getDeviceInformation/{deviceId}", method = {RequestMethod.POST,RequestMethod.GET})
	public Object getDeviceInformation(@PathVariable String deviceId) throws IOException, JSONException {
		
		BCITSLogger.logger.info("COMING INSIDE---------------->DeviceInformation");
		
		return masterDeviceStatusService.getDeviceDetails(deviceId);
	}
	
	@ResponseBody
	@RequestMapping(value = "/deviceregistration", method = RequestMethod.POST)
	public String deviceRegistration(@RequestBody String body,HttpServletRequest request) throws IOException, JSONException {
		
		BCITSLogger.logger.info("COMING INSIDE---------------->insertDeviceInformation");
		
		String status="notinserted";
		 try{
		 
		JSONObject json = new JSONObject(body);
          List<String> list=new ArrayList<String>();
		MRDeviceEntity  deviceEntity= new MRDeviceEntity();
		BCITSLogger.logger.info("COMING INSIDE---------------->insertDeviceInformation"+json.getString("deviceid"));
		deviceEntity.setDeviceid(json.getString("deviceid"));
		deviceEntity.setMake(json.getString("make"));
		deviceEntity.setGprsSimNum(json.getString("gprsSimNum"));
		deviceEntity.setAllocatedflag("not allocated");
		deviceEntity.setApprovalStatus("NOT APPROVED");

		HttpSession httpSession=request.getSession();
		httpSession.setAttribute("dbmsName", json.getString("dbmsName"));
		deviceEntity.setTimestamp(new Timestamp(new Date().getTime()));		
		list=mrDeviceService.getCheckConstraints(
				"devicemaster", "make_check", request);
			BCITSLogger.logger.info("COMING INSIDE---------------->insertDeviceInformation"+list.size());
       if(list.size()==0)
       {
    	   status="devicenotsupport"; 
       }
       else{
    	   if (mrDeviceService.find(deviceEntity.getDeviceid()) != null) {
   			mrDeviceService.update(deviceEntity);
   			status="updated";
   			//st
   			//model.put("results", "MR Device Updated Successfully");
   		}else{
    	   for (int i = 0; i < list.size(); i++) {
    		   status="devicenotsupport"; 
    		    String model = list.get(i);
    		    if(model.equalsIgnoreCase(json.getString("make"))){
    		    	mrDeviceService.save(deviceEntity);
    		    	status="inserted"; 
    		    	break;
    		    	
    		    }
    		    
    			BCITSLogger.logger.info("COMING INSIDE---------------->insertDeviceInformation"+model);
    		}
   		}
       }
        }
        catch(Exception e){
        	e.printStackTrace();
        	status="notinserted";
        }
		return status;
	}
	
	@ResponseBody
	@RequestMapping(value = "/deviceLogs", method = RequestMethod.POST)
	public String deviceLogs(@RequestBody String body,HttpServletRequest request) throws IOException, JSONException {
		
		BCITSLogger.logger.info("COMING INSIDE---------------->deviceLogs"+body);
		
		String status="notinserted";
		 try{
		 
		JSONObject json = new JSONObject(body);
		Device_Logs_Entity  deviceEntity= new Device_Logs_Entity();
		BCITSLogger.logger.info("COMING INSIDE---------------->deviceLogs"+json.getString("deviceid"));
		deviceEntity.setDeviceid(json.getString("deviceid"));
		
		String zipfile = json.getString("log_db");
		byte[] zipfile1 =Base64.decodeBase64(zipfile);
		deviceEntity.setLog(zipfile1);
		deviceEntity.setReportdate(new Timestamp(new Date().getTime()));		
		
    
    		    deviceLogService.save(deviceEntity);
    		    	status="inserted"; 
    		   
      
        }
        catch(Exception e){
        	e.printStackTrace();
        	status="notinserted";
        }
		return status;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/deviceDbBackUp", method = RequestMethod.POST)
	public String deviceDbBackUp(@RequestBody String body,HttpServletRequest request) throws IOException, JSONException {
		
		BCITSLogger.logger.info("COMING INSIDE---------------->deviceLogs"+body);
		
		String status="notinserted";
		 try{
		 
		JSONObject json = new JSONObject(body);
		Device_Logs_Entity  deviceEntity= new Device_Logs_Entity();
		BCITSLogger.logger.info("COMING INSIDE---------------->deviceLogs"+json.getString("deviceid"));
		deviceEntity.setDeviceid(json.getString("deviceid"));
		
		String zipfile = json.getString("log_db");
		byte[] zipfile1 =Base64.decodeBase64(zipfile);
		deviceEntity.setDbbackup(zipfile1);
		deviceEntity.setReportdate(new Timestamp(new Date().getTime()));		
		
    
    		    deviceLogService.save(deviceEntity);
    		    	status="inserted"; 
    		   
      
        }
        catch(Exception e){
        	e.printStackTrace();
        	status="notinserted";
        }
		return status;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/getMrmaster/{sdocode}", method = {RequestMethod.POST,RequestMethod.GET})
	public Object getMrmaster(@PathVariable String sdocode,HttpServletRequest request,ModelMap model) throws IOException, JSONException, Exception
	{
		List<Object[]> list=null;
		List<MRDeviceAllocationEntity> allList=null;
		try 
		{
			//String dbUser=siteLocationService.getDbuser(sdocode);
			//BCITSLogger.logger.info("COMING INSIDE---------------->dbUser"+dbUser);
			ConnectionClass con=new ConnectionClass();
			con.getconnection();
			 //list=masterOracleService.getMrmaster(dbUser);
			list=masterOracleService.getOracleMrmaster(sdocode);
			 allList=mrDeviceAllocationService.findAllMRDeviceAllocations1(request,sdocode,model);
			 for (MRDeviceAllocationEntity mrDeviceAllocationEntity : allList)
				{
				 
			for (int i = 0; i < list.size(); i++) 
			{
					if(mrDeviceAllocationEntity.getMrCode().trim().equals(list.get(i)))
					{
						list.remove(list.get(i));
					}
				}
			}
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		
		return list;
	}
	
	@ResponseBody
	@RequestMapping(value = "/getDevicesBySdoCode/{sdocode}", method = {RequestMethod.POST,RequestMethod.GET})
	public Object getDevicesBySdoCode(@PathVariable String sdocode) throws IOException, JSONException, Exception
	{
		BCITSLogger.logger.info("COMING INSIDE---------------->sdocode"+sdocode);
		return mrDeviceService.getNotAllocatedDevice(Integer.parseInt(sdocode));
	}
}
