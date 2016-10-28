package com.bcits.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bcits.entity.GroupEntity;
import com.bcits.entity.LocationEntity;
import com.bcits.entity.MRDeviceAllocationEntity;
import com.bcits.entity.MRDeviceEntity;
import com.bcits.entity.MRMasterEntity;
import com.bcits.entity.User;
import com.bcits.service.AssetTransactionService;
import com.bcits.service.EmployeeOracleService;
import com.bcits.service.GroupService;
import com.bcits.service.HHBM_ConsumerService;
import com.bcits.service.HHBM_DownloadService;
import com.bcits.service.LocationService;
import com.bcits.service.MRDeviceAllocationService;
import com.bcits.service.MRDeviceService;
import com.bcits.service.MRMasterService;
import com.bcits.service.ModuleMasterService;
import com.bcits.service.SiteLocationService;
import com.bcits.service.UserService;
import com.bcits.utility.BCITSLogger;
import com.bcits.utility.Encryption;
import com.bcits.utility.Link;

@Controller
public class AdminController {
	@Autowired
	private UserService userService;

	@Autowired
	private LocationService locationService;

	@Autowired
	private GroupService groupService;

	@Autowired
	private MRMasterService mrMasterService;

	@Autowired
	private SiteLocationService siteLocationService;

	@Autowired
	private MRDeviceService mrDeviceService;

	@Autowired
	private MRDeviceAllocationService mrDeviceAllocationService;

	@Autowired
	private Encryption encryption;

	@Autowired
	private ModuleMasterService moduleMasterService;
	// Date current_date=new Date();

	@Autowired
	private AssetTransactionService assetTransactionService;

	@Autowired
	private HHBM_ConsumerService consumerService;

	@Autowired
	private HHBM_DownloadService downloadService;

	@Autowired
	private EmployeeOracleService employeeOracleService;

	@Link(label = "Home", family = "AdminController", parent = "")
	@RequestMapping(value = "/adminDashboard", method = RequestMethod.GET)
	public String dashBoard(ModelMap model, HttpServletRequest request) {
		BCITSLogger.logger.info("In DashBoard");
		userService.getRecentPath(
				request.getRequestURI().substring(4,
						request.getRequestURI().length()), request);
		// locationService.getTopMostLevelData(request, model);
		model.put("results", "notDisplay");
		return "adminDashboard";
	}

	@RequestMapping(value = "/getAllLocationNamesInTree/{locationCode}/{region}/{requiredLevel}", method = {
			RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody
	Object getLocationNames(@PathVariable String locationCode,
			@PathVariable String region, @PathVariable int requiredLevel,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap model) throws JsonParseException, JsonMappingException,
			IOException {
		BCITSLogger.logger.info("In getLocationNames  data " + locationCode
				+ "  " + region + " " + requiredLevel);
		List list = new ArrayList();
		if (requiredLevel == 1) {
			locationCode = "10";
		}
		list = locationService.getChildNodes(locationCode, region,
				requiredLevel);
		return list;
	}

	@Link(label = "Location Management", family = "AdminController", parent = "Home")
	@RequestMapping(value = "/adminLocation", method = RequestMethod.GET)
	public String addLocation(
			@ModelAttribute("locationManagement") LocationEntity location,
			ModelMap model, HttpServletRequest request) {
		BCITSLogger.logger.info("In Add Location-Admin Dash Board");
		int value = 0;
		if (request.getParameter("flag") != null) {
			value = Integer.parseInt(request.getParameter("flag"));
			if (value == 0) {
				model.addAttribute("results", "Location Deleted Successfully.");
			} else if (value == 1) {
				model.addAttribute("results",
						"Location is not Deleted Successfully. Please try again.");
			}
		} else {
			model.addAttribute("results", "notDisplay");
		}

		userService.getRecentPath(
				request.getRequestURI().substring(4,
						request.getRequestURI().length()), request);
		locationService.getLocationData(model, request);
		return "adminLocationInfo";
	}

	/*------For Adding Location Information------*/
	@Link(label = "Location Management", family = "AdminController", parent = "Home")
	@RequestMapping(value = "/addLocationData", method = RequestMethod.POST)
	public String GetAddLocationData(
			@ModelAttribute("locationManagement") LocationEntity location,
			BindingResult result, ModelMap model, HttpServletRequest request) {
		BCITSLogger.logger.info("LOCATION NAME      "
				+ location.getLocationname());
		// String
		// valLocName=locationService.findLocName(location.getLocationname().toUpperCase());

		BCITSLogger.logger.info("getAddLocationData Data"
				+ request.getParameter("codeVal"));
		try {
			int codevaluee = Integer.parseInt(request.getParameter("codeVal"));
			int levell = locationService.getLocationCode(location.getLevel(),
					codevaluee);
			if (levell == 0) {
				String twoDigits = "00";
				levell = Integer.parseInt(request.getParameter("codeVal")
						+ twoDigits);
				BCITSLogger.logger.info("levell is " + levell);
			}
			BCITSLogger.logger.info("level values " + levell);
			location.setLocationcode(levell + 1);
			location.setDatestamp(new Timestamp(new Date().getTime()));
			location.setUsername((String) request.getSession().getAttribute(
					"username"));
			BCITSLogger.logger.info("LOCATION ENTITY " + location.toString());
			locationService.save(location);
			model.put("results", "Location added successfully.");
		} catch (Exception e) {
			model.put("results", "Error while adding Location.");
			e.printStackTrace();
		}
		locationService.getLocationData(model, request);
		model.put("locationManagement", new LocationEntity());
		return "adminLocationInfo";
	}

	@RequestMapping(value = "/getAllLocationNames/{operation}/{level}/{digits}", method = RequestMethod.GET)
	public @ResponseBody
	Object addLocationData(
			@ModelAttribute("locationManagement") LocationEntity locationEntity,
			BindingResult result, @PathVariable String operation,
			@PathVariable int level, @PathVariable int digits,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap model) throws JsonParseException, JsonMappingException,
			IOException {
		BCITSLogger.logger.info("In Location names data " + level + "   "
				+ digits);
		List<LocationEntity> locEntity = locationService
				.getLocationNamesForLevels(operation, level, digits);
		return locEntity;
	}

	@RequestMapping(value = "/editLocationManagement/{operation}", method = {
			RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody
	Object editLocationManagement(@PathVariable String operation,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap model) throws JsonParseException, JsonMappingException,
			IOException {
		BCITSLogger.logger.info("In Location edit data ");
		// int id=Integer.parseInt(operation);
		LocationEntity loc = locationService.find(operation);
		return loc;
	}

	@RequestMapping(value = "/deleteLocation/{locCode}", method = {
			RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public Object deleteLocation(@PathVariable String locCode,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap model) throws JsonParseException, JsonMappingException,
			IOException {
		String val1 = "\"deleted\"";
		;
		String val2 = "\"notDeleted\"";
		;
		BCITSLogger.logger.info("In Delete location-User Dash Board" + locCode);
		int locationcode = Integer.parseInt(locCode);
		/*
		 * List<LocationEntity>
		 * loc=locationService.checkLevelLocation(locationcode); for (int i = 0;
		 * i < loc.size(); i++) {
		 * BCITSLogger.logger.info("DATA COMING------------------>"+loc); }
		 */
		// locationService.delete(locationcode);
		int deleteCount = locationService.deleteLocation(locCode);
		// List<LocationEntity>
		// list=locationService.getLocationData(locationcode,model);
		if (deleteCount > 0) {
			return val1;
		} else {
			return val2;
		}
		/*
		 * if(list.size()==0) { return val1; } else { return val2; }
		 */
	}

	@Link(label = "Location Management", family = "AdminController", parent = "Home")
	@RequestMapping(value = "/updateLocationData", method = RequestMethod.POST)
	public String updateLocationData(
			@ModelAttribute("locationManagement") LocationEntity location,
			BindingResult bingingResult, ModelMap model,
			HttpServletRequest request) {
		BCITSLogger.logger.info("updateLocationData");
		try {
			BCITSLogger.logger.info("LOCATION NAME             "
					+ location.getLocationname());
			location.setDatestamp(new Timestamp(new Date().getTime()));
			location.setUsername((String) request.getSession().getAttribute(
					"username"));
			locationService.update(location);
			model.put("results", "Location Updated Successfully.");
		} catch (Exception e) {
			model.put("results", "Error while updating location");
			e.printStackTrace();
		}
		locationService.getLocationData(model, request);
		model.put("locationManagement", new LocationEntity());
		return "adminLocationInfo";
	}

	/*
	 * //FOR FINDING DUPLICATE LOCATION NAME
	 * 
	 * @RequestMapping(value="/valLocationName/{operation}/{level}",method={
	 * RequestMethod.POST,RequestMethod.GET}) public @ResponseBody Object
	 * valLocationName(@PathVariable String operation,@PathVariable int
	 * level,HttpServletResponse response,HttpServletRequest request,ModelMap
	 * model) throws JsonParseException, JsonMappingException, IOException {
	 * BCITSLogger.logger.info("In Locations"); String locName=null;
	 * List<LocationEntity>
	 * loc=locationService.findLocName(operation.toUpperCase(),level);
	 * if(loc.size()>0) { locName=loc.get(0).getLocationname(); } return
	 * locName; }
	 */

	// FOR FINDING DUPLICATE LOCATION NAME
	@RequestMapping(value = "/valLocationName/{operation}/{loccode}/{level}", method = { RequestMethod.GET })
	public @ResponseBody
	Object valLocationName(@PathVariable String operation,
			@PathVariable int loccode, @PathVariable int level,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap model) throws JsonParseException, JsonMappingException,
			IOException {
		BCITSLogger.logger.info("In Locations=>" + operation + "   " + level);
		String locName = null;
		List<LocationEntity> loc = locationService.findLocName(
				operation.toUpperCase(), loccode, level);
		if (loc.size() > 0) {
			locName = loc.get(0).getLocationname();
			BCITSLogger.logger.info(locName
					+ "=================================>");
		}
		return locName;
	}

	@RequestMapping(value = "/valSiteCode/{operation}/{level}", method = {
			RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody
	Object valLocationName(@PathVariable int operation,
			@PathVariable int level, HttpServletResponse response,
			HttpServletRequest request, ModelMap model)
			throws JsonParseException, JsonMappingException, IOException {
		BCITSLogger.logger.info("In Locations");

		String siteCode = null;

		// String siteCode=null;

		List<LocationEntity> loc = locationService.findSiteCode(operation,
				level);
		if (loc.size() > 0) {
			siteCode = loc.get(0).getSitecode();
		}
		return siteCode;
	}

	@RequestMapping(value = "/getLocationTypes/{level}", method = {
			RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody
	Object getLocationTypes(@PathVariable int level,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap model) throws JsonParseException, JsonMappingException,
			IOException {
		BCITSLogger.logger.info("In Locations");
		List<String> locTypes = locationService.getLocationTypes(request);
		return locTypes;
	}

	@Link(label = "Group", family = "AdminController", parent = "")
	@RequestMapping(value = "/getGroup", method = RequestMethod.GET)
	public String groups(@ModelAttribute("group") GroupEntity group1,
			HttpServletRequest request, ModelMap model) {
		BCITSLogger.logger.info("In group");
		model.put("allGroup", groupService.findAll());
		return "group";
	}

	@Link(label = "Group", family = "AdminController", parent = "")
	@RequestMapping(value = "/updateGroup", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String updateGroup(@ModelAttribute("group") GroupEntity group1,
			HttpServletRequest request, ModelMap model) {
		BCITSLogger.logger.info("In group DML");
		groupService.updateGroupData(request, model, group1);
		return "group";
	}

	@Link(label = "Group", family = "AdminController", parent = "")
	@RequestMapping(value = "/editGroup/{id}", method = { RequestMethod.GET,
			RequestMethod.POST })
	public @ResponseBody
	Object editGroup(HttpServletRequest request, ModelMap model,
			@PathVariable int id) {
		BCITSLogger.logger.info("In Edit group ");
		return groupService.find(id);
	}

	@Link(label = "Device Operator Master Management", family = "AdminController", parent = "")
	@RequestMapping(value = "/meterReaderAccessManagment", method = RequestMethod.GET)
	public String meterReaderAccessManagment(
			@ModelAttribute("mrMasters") MRMasterEntity mrMasters,
			HttpServletRequest request, ModelMap model,
			BindingResult bindingResult) {
		model.put("mrmList", mrMasterService.findAllMRMasters());
		// model.put("sdoCodes", siteLocationService.getAllSiteCodes());
		// model.put("sdoCodes", locationService.getAllSdoLevel4(request));
		// model.put("groupData", groupService.findAll());
		return "meterReaderAccessManagment";
	}

	/**
	 * Adding and Updating the MR Reader Details.......
	 */

	@Link(label = "Device Operator Master Management", family = "AdminController", parent = "")
	@RequestMapping(value = "/updateMRMaster", method = RequestMethod.POST)
	public String updateMRMaster(
			@ModelAttribute("mrMasters") MRMasterEntity mrMasters,
			HttpServletRequest request, ModelMap model,
			BindingResult bindingResult, @RequestParam String operation,
			@RequestParam String groupsData) {
		mrMasterService.updateMRMasterData(mrMasters, request, new Date(),
				model, groupsData, operation);
		// model.put("groupData", groupService.findAll());
		return "meterReaderAccessManagment";
	}

	@Link(label = "Device Operator Master Management", family = "AdminController", parent = "")
	@RequestMapping(value = "/deleteMRMaster", method = RequestMethod.POST)
	public String deleteMRMaster(
			@ModelAttribute("mrMasters") MRMasterEntity mrMasters,
			@RequestParam("mrCode") String mrCode, HttpServletRequest request,
			ModelMap model) {
		try {
			if (mrMasterService.deleteMRMaster(mrCode) > 0) {
				model.put("results", "MR Reader Deleted Successfully....");
			} else {
				model.put("results", "MR Reader Cannot Be Deleted....");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		model.put("mrmList", mrMasterService.findAllMRMasters());
		// model.put("sdoCodes", siteLocationService.getAllSiteCodes());
		// model.put("sdoCodes", locationService.getAllSdoLevel4(request));
		// model.put("groupData", groupService.findAll());
		model.put("mrMasters", new MRMasterEntity());
		return "meterReaderAccessManagment";
	}

	@RequestMapping(value = "/checkMRCodeAvailability", method = RequestMethod.POST)
	public @ResponseBody
	MRMasterEntity checkMRCodeAvailability(
			@ModelAttribute("mrMasters") MRMasterEntity mrMasters,
			@RequestParam("mrCode") String mrCode,
			@RequestParam("sdoCode") Integer sdoCode,
			HttpServletRequest request, ModelMap model) {
		return mrMasterService.findMRMaster(mrCode);
	}

	/**
	 * The Response of the method is going in the form of JSON OBject.....
	 * 
	 * 
	 */

	@Link(label = "Device Operator Master Management", family = "AdminController", parent = "")
	@RequestMapping(value = "/editMRMaster", method = RequestMethod.POST)
	public @ResponseBody
	MRMasterEntity editMRMaster(@RequestParam("mrCode") String mrCode,
			ModelMap model) {
		return mrMasterService.findMRMaster(mrCode);

	}

	/**
	 * Meter Reader Device Registration Management::::::::
	 * 
	 */

	@Link(label = "Devices Management", family = "AdminController", parent = "")
	@RequestMapping(value = "/mrDeviceAccessManagment", method = RequestMethod.GET)
	public String mrDeviceAccessManagment(
			@ModelAttribute("mrDevices") MRDeviceEntity mrDevices,
			HttpServletRequest request, ModelMap model,
			BindingResult bindingResult) {
		System.out
				.println(" *********************** mrDeviceAccessManagment  *********************");
		model.put("mrdList", mrDeviceService.findAllMRDevices(request));
		model.put("makeConstrts", mrDeviceService.getCheckConstraints(
				"devicemaster", "make_check", request));
		model.put("sdoCodes", siteLocationService.getAllSiteCodes());
		model.put("results", "notDisplay");
		
		return "mrDeviceAccessManagment";
	}

	@RequestMapping(value = "/mrDeviceAccessManagmentMob/{sdocode}", method = {
			RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody
	List<Map<String, Object>> mrDeviceAccessManagmentMob(
			@PathVariable String sdocode) 
	{
		System.out
				.println(" *********************** mrDeviceAccessManagmentMobile  *********************");
		List<Map<String, Object>> result = mrDeviceService
				.findAllMRDevicesMobile(sdocode);
		return result;
	}

	@Link(label = "Devices Management", family = "AdminController", parent = "")
	@RequestMapping(value = "/editMRDevice/{id}", method = {
			RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody
	MRDeviceEntity editMRDevice(
			@ModelAttribute("mrDevices") MRDeviceEntity mrDevices,
			@PathVariable int id, HttpServletRequest request, ModelMap model,
			BindingResult bindingResult) {
		System.out.println("ID dev " + id);
		MRDeviceEntity dev = mrDeviceService.find(id);
		// System.out.println("obj=== "+dev.getApprovalStatus());
		return dev;
	}

	@Link(label = "Devices Management", family = "AdminController", parent = "")
	@RequestMapping(value = "/getDeviceDetails/{deviceid}", method = {
			RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody
	MRDeviceEntity getDeviceDetails(
			@ModelAttribute("mrDevices") MRDeviceEntity mrDevices,
			@PathVariable String deviceid, HttpServletRequest request,
			ModelMap model, BindingResult bindingResult) {
		System.out.println("ID dev " + deviceid);
		MRDeviceEntity dev = mrDeviceService.find(deviceid);
		System.out.println("obj=== " + dev.getApprovalStatus());
		return dev;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Link(label = "Devices Management", family = "AdminController", parent = "")
	@RequestMapping(value = "/updateMRDevice", method = RequestMethod.POST)
	public String updateMRDevice(
			@ModelAttribute("mrDevices") MRDeviceEntity mrDevices,
			HttpServletRequest request, ModelMap model,
			BindingResult bindingResult) {
		mrDevices.setUsername((String) request.getSession().getAttribute(
				"username"));
		mrDevices.setTimestamp(new Timestamp(new Date().getTime()));
		/*
		 * MRDeviceEntity
		 * deviceDetails=mrDeviceService.find(mrDevices.getDeviceid());
		 * BCITSLogger.logger.info("================>"); if (deviceDetails !=
		 * null) { mrDevices.setAllocatedflag(deviceDetails.getAllocatedflag());
		 * mrDevices.setApprovalStatus(deviceDetails.getApprovalStatus());\
		 * mrDevices.set //mrDevices.setStatus("Registered");
		 * mrDeviceService.update(mrDevices); model.put("results",
		 * "Device Added Successfully"); AssetTransactionEntity
		 * assetTransactionEntity=new AssetTransactionEntity();
		 * assetTransactionEntity.setDeviceId(mrDevices.getDeviceid());
		 * assetTransactionEntity.setSiteCode(mrDevices.getSdoCode());
		 * assetTransactionEntity.setTransDate(new Timestamp(new
		 * Date().getTime())); assetTransactionEntity.setTransType("Updated");
		 * assetTransactionService.save(assetTransactionEntity); } else {
		 * mrDevices.setApprovalStatus("NOT APPROVED");
		 * //mrDevices.setStatus("Created"); mrDeviceService.update(mrDevices);
		 * model.put("results", "Device Added Successfully");
		 * 
		 * AssetTransactionEntity assetTransactionEntity=new
		 * AssetTransactionEntity();
		 * assetTransactionEntity.setDeviceId(mrDevices.getDeviceid());
		 * assetTransactionEntity.setSiteCode(mrDevices.getSdoCode());
		 * assetTransactionEntity.setTransDate(new Timestamp(new
		 * Date().getTime())); assetTransactionEntity.setTransType("Created");
		 * assetTransactionService.save(assetTransactionEntity); }
		 */
		//mrDevices.setDeviceid(request.getParameter("deviceid"));
		BCITSLogger.logger.info("============mrDevices.getDeviceid()====>" + mrDevices.getDeviceid());
		MRDeviceEntity mrDev = mrDeviceService.find(mrDevices.getId());
		if (mrDev != null) 
		{
			if (!mrDevices.getDeviceid().equalsIgnoreCase(mrDev.getDeviceid())) 
			{
				String value = mrDeviceService.findByDeviceId(mrDevices.getDeviceid());
				if (value.equalsIgnoreCase("DataExists")) {
					model.put("results", "Device id " + mrDevices.getDeviceid()
							+ " already registered");
				} else {
					mrDeviceService.update(mrDevices);
					model.put("results", "Device updated Successfully");
				}
				model.put("mrdList", mrDeviceService.findAllMRDevices(request));
				model.put("makeConstrts", mrDeviceService.getCheckConstraints(
						"devicemaster", "make_check", request));
				model.put("mrDevices", new MRDeviceEntity());
				model.put("sdoCodes", siteLocationService.getAllSiteCodes());
				return "mrDeviceAccessManagment";
			}
		}
		
		try {
			BCITSLogger.logger.info("================>" + mrDevices.getId());
			if (mrDevices.getId() == 0) {
				mrDevices.setAllocatedflag("NOT ALLOCATED");
				mrDevices.setApprovalStatus("NOT APPROVED");
				mrDevices.setStatus("WORKING");
				mrDevices.setDeviceType("MOBILE");
				String value = mrDeviceService.findByDeviceId(mrDevices.getDeviceid());
				if (value.equalsIgnoreCase("DataExists"))
				{
					model.put("results", "Device id " + mrDevices.getDeviceid()
							+ " already registered");
				} else {
					mrDeviceService.update(mrDevices);
					model.put("results", "Device saved Successfully");
				}
				
				//mrDeviceService.save(mrDevices);
				/*model.put("results", "Device Added Successfully");*/
			} else {
				mrDeviceService.update(mrDevices);
				model.put("results", "Device updated Successfully");
			}
			model.put("mrdList", mrDeviceService.findAllMRDevices(request));
			model.put("makeConstrts", mrDeviceService.getCheckConstraints(
					"devicemaster", "make_check", request));
			model.put("mrDevices", new MRDeviceEntity());
			model.put("sdoCodes", siteLocationService.getAllSiteCodes());
		} catch (Exception e) {
			e.printStackTrace();
			model.put("results", "error while processing");
		}
		return "mrDeviceAccessManagment";
	}

	@Link(label = "Devices Management", family = "AdminController", parent = "")
	@RequestMapping(value = "/deleteMRDevice", method = RequestMethod.POST)
	public String deleteMRDevice(@RequestParam("deviceid") int deviceid,
			@ModelAttribute("mrDevices") MRDeviceEntity mrDevices,
			HttpServletRequest request, ModelMap model) {
		try {
			mrDeviceService.delete(deviceid);
			model.put("results", "Device is Deleted Successfully...");
		} catch (Exception e) {
			model.put("results", "Device not Deleted");
			e.printStackTrace();
		}
		model.put("mrdList", mrDeviceService.findAllMRDevices(request));
		model.put("makeConstrts", mrDeviceService.getCheckConstraints(
				"devicemaster", "make_check", request));
		model.put("sdoCodes", siteLocationService.getAllSiteCodes());
		return "mrDeviceAccessManagment";
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Link(label = "Devices Management", family = "AdminController", parent = "")
	@RequestMapping(value = "/approveDevice", method = RequestMethod.GET)
	public String approveDevice(
			@ModelAttribute("mrDevices") MRDeviceEntity mrDevices,
			HttpServletRequest request, @RequestParam String deviceid,
			ModelMap model, BindingResult bindingResult) {
		mrDeviceService.approveDevice(deviceid, request, model);
		// String status="Registered";
		// mrDeviceService.updateStatus(deviceid,status);
		/*
		 * AssetTransactionEntity assetTransactionEntity=new
		 * AssetTransactionEntity();
		 * assetTransactionEntity.setDeviceId(mrDevices.getDeviceid());
		 * assetTransactionEntity.setSiteCode(mrDevices.getSdoCode());
		 * assetTransactionEntity.setTransDate(new Timestamp(new
		 * Date().getTime()));
		 * assetTransactionEntity.setTransType("Registered");
		 * assetTransactionService.save(assetTransactionEntity);
		 */
		model.put("sdoCodes", siteLocationService.getAllSiteCodes());
		return "mrDeviceAccessManagment";
	}

	@Link(label = "MR Device Access Management", family = "AdminController", parent = "")
	@RequestMapping(value = "/checkDeviceAvailability", method = RequestMethod.POST)
	public @ResponseBody
	Boolean checkDeviceAvailability(@RequestParam("deviceid") String deviceid,
			@ModelAttribute("mrDevices") MRDeviceEntity mrDevices,
			HttpServletRequest request, ModelMap model) {
		int pk = mrDeviceService.findByPk(deviceid);
		BCITSLogger.logger.info("==============>COMINFG pk----------" + pk);
		if (mrDeviceService.find(pk) != null) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Device Allocation management is started
	 */
	@Link(label = "Devices Allocation and DeAllocation", family = "AdminController", parent = "")
	@RequestMapping(value = "/mrDeviceAllocationManagment", method = {
			RequestMethod.GET, RequestMethod.POST })
	public String mrDeviceAllocationManagment(
			@ModelAttribute("mrDevAllocs") MRDeviceAllocationEntity mrDevAllocs,
			HttpServletRequest request, ModelMap model,
			BindingResult bindingResult) {
		model.put("mrdaList", mrDeviceAllocationService
				.findAllMRDeviceAllocations(request, model));
		model.put("sdoCodes", siteLocationService.getAllSiteCodes());
		return "mrDeviceAllocationManagment";
	}

	@Link(label = "Devices Allocation and DeAllocation", family = "AdminController", parent = "")
	@RequestMapping(value = "/allocateNewDevice", method = { RequestMethod.POST })
	public String allocateNewDevice(
			@ModelAttribute("mrDevAllocs") MRDeviceAllocationEntity mrDevAllocs,
			HttpServletRequest request, ModelMap model) {
		mrDevAllocs.setUsername((String) request.getSession().getAttribute(
				"username"));
		mrDevAllocs.setTimestamp(new Timestamp(new Date().getTime()));
		String mrcode = mrDevAllocs.getMrCode().trim().replaceAll("\\s", "");
		mrDevAllocs.setMrCode(mrcode);
		try {
			List<MRDeviceAllocationEntity> list = mrDeviceAllocationService
					.findByDeviceId(mrDevAllocs.getDeviceid());
			if (list.size() > 0) {
				model.put("results", "Device Id " + mrDevAllocs.getDeviceid()
						+ " already Allocated");
				model.put("mrDevAllocs", new MRDeviceAllocationEntity());
				model.put("sdoCodes", siteLocationService.getAllSiteCodes());
				model.put("mrdaList", mrDeviceAllocationService
						.findAllMRDeviceAllocations(request, model));
				return "mrDeviceAllocationManagment";
			}
			mrDeviceAllocationService.save(mrDevAllocs);
			int updatestatus = mrDeviceService.updateDeviceSdocode(
					mrDevAllocs.getDeviceid(), mrDevAllocs.getSdoCode(),
					"ALLOCATED");
			if (updatestatus > 0) {
				model.put("results", "Device Allocated Successfully");
			} else {
				model.put("results", "Device not Allocated");
			}

		} catch (Exception e) {
			model.put("results", "Device not Allocated");
			e.printStackTrace();
		}
		model.put("mrdaList", mrDeviceAllocationService
				.findAllMRDeviceAllocations(request, model));
		/*
		 * for (MRDeviceAllocationEntity iterable_element :
		 * mrDeviceAllocationService.findAllMRDeviceAllocations()) {
		 * BCITSLogger.
		 * logger.info("=======================>iterable_element"+iterable_element
		 * .getMrMaster().getMrName()); }
		 */
		model.put("sdoCodes", siteLocationService.getAllSiteCodes());
		// model.put("sdoCodes", locationService.getAllSdoLevel4(request));
		// model.put("operators", mrMasterService.getNotAllocatedOperators());
		model.put("mrDevAllocs", new MRDeviceAllocationEntity());
		return "mrDeviceAllocationManagment";
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Link(label = "Devices Allocation and DeAllocation", family = "AdminController", parent = "")
	@RequestMapping(value = "/deAllocateDevice", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String deAllocateDevice(
			@ModelAttribute("mrDevAllocs") MRDeviceAllocationEntity mrDevAllocs,
			@RequestParam String deviceidPk, HttpServletRequest request,
			ModelMap model) {
		try {
			BCITSLogger.logger.info("=======================>iterable_element"
					+ deviceidPk);
			int val = mrDeviceAllocationService.deleteDevice(deviceidPk);
			int val1 = mrDeviceService.updateDeviceMaster(deviceidPk + "");
			// mrDeviceService.updateStatus(mrDevAllocs.getDeviceid(),"DeAllocated");
			if (val > 0 && val1 > 0) {
				model.put("results", "Device DeAllocated successfully");
			} else {
				model.put("results", "Device not DeAllocated");
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		model.put("mrdaList", mrDeviceAllocationService
				.findAllMRDeviceAllocations(request, model));
		model.put("sdoCodes", siteLocationService.getAllSiteCodes());
		// model.put("sdoCodes", locationService.getAllSdoLevel4(request));
		// model.put("operators", mrMasterService.getNotAllocatedOperators());
		return "mrDeviceAllocationManagment";
	}

	@Link(label = "MR Device Access Management", family = "AdminController", parent = "")
	@RequestMapping(value = "/checkAllocated/{operatorDevice}/{id}", method = RequestMethod.POST)
	public @ResponseBody
	Boolean checkAllocated(@PathVariable String operatorDevice,
			@PathVariable String id, HttpServletRequest request, ModelMap model) {
		if (operatorDevice.equals("device"))// check for device allocated
		{
			return mrDeviceAllocationService.checkAllocated(request, "%", id);
		}
		if (operatorDevice.equals("operator"))// check for mr allocated
		{
			return mrDeviceAllocationService.checkAllocated(request, id, "%");
		}
		return true;
	}

	@Link(label = "User Access management", family = "AdminController", parent = "")
	@RequestMapping(value = "/userAccessManagment", method = RequestMethod.GET)
	public String userAccessManagment(@ModelAttribute("users") User user,
			ModelMap model, BindingResult bindingResult,
			HttpServletRequest request) {
		BCITSLogger.logger.info("In User DashBoard-User Access Mangement");
		userService.getRecentPath(
				request.getRequestURI().substring(4,
						request.getRequestURI().length()), request);
		userService.getUserData(model, request);
		model.addAttribute("results", "notDisplay");

		return "userAccessManagment";
	}

	@RequestMapping(value = "/getProfileImage/getImage/{userId}", method = RequestMethod.GET)
	public void getUseImage(ModelMap model, HttpServletRequest request,
			HttpServletResponse response, @PathVariable int userId)
			throws IOException {
		BCITSLogger.logger.info("In profile photo for user Accessment");
		userService.getImage(request, response, userId);

	}

	@RequestMapping(value = "/getProfilePic", method = RequestMethod.GET)
	public void getprofilePic(ModelMap model, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		BCITSLogger.logger.info("In profile pic ");
		userService.displayProfilePic(request, response);
	}

	@Link(label = "User Access management", family = "AdminController", parent = "")
	@RequestMapping(value = "/updateUser", method = RequestMethod.POST)
	public String updateUserData(@ModelAttribute("users") User user,
			BindingResult bindingResult, ModelMap model,
			HttpServletRequest request) {
		userService.updateuserDetails(request, user, new Date(), model);
		userService.getUserData(model, request);
		model.put("users", new User());
		return "userAccessManagment";
	}

	@RequestMapping(value = "/editUser/{operation}", method = {
			RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody
	Object editUser(@PathVariable int operation, HttpServletResponse response,
			HttpServletRequest request, ModelMap model)
			throws JsonParseException, JsonMappingException, IOException {
		User user = userService.find(operation);
		user.setUserPassword(encryption.decrypt(user.getUserPassword()));
		return user;
	}

	@Link(label = "Manage Menu List", family = "UserController", parent = "Home")
	@RequestMapping(value = "/modulesConfiguration", method = RequestMethod.GET)
	public String modulesConfiguration(ModelMap model,
			HttpServletRequest request) {
		BCITSLogger.logger.info("In User DashBoard-Modules Configuration");
		model.put("mdmasters", moduleMasterService.findAllModuleMasters());
		userService.getRecentPath(
				request.getRequestURI().substring(4,
						request.getRequestURI().length()), request);

		return "modulesConfiguration";
	}

	@Link(label = "Manage Menu List", family = "UserController", parent = "Home")
	@RequestMapping(value = "/activeSelectedModules", method = {
			RequestMethod.POST, RequestMethod.GET })
	public String activeSelectedModules(@RequestParam String activateMdls,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap model) {
		BCITSLogger.logger.info("all modules " + activateMdls);
		moduleMasterService.updateActiveDeactiveStatus(activateMdls);
		model.put("mdmasters", moduleMasterService.findAllModuleMasters());

		return "modulesConfiguration";
	}

	@RequestMapping(value = "/showMobileDevicesLive", method = {RequestMethod.GET, RequestMethod.POST })
	public String showMobileDevicesLivef(HttpServletRequest request,ModelMap model) 
	{
		String cuurentDate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		if (request.getParameter("fromDate") != null)
		{
			downloadService.divisionWiseMobiles(model,
					request.getParameter("fromDate"));
		} else {
			downloadService.divisionWiseMobiles(model, cuurentDate);
		}
		return "mobileDevicesLive";
	}

	@RequestMapping(value = "/showMobileDevicesLiveBySection/{divSdo}/{date}", method = {
			RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	Object getSectionMobiles(@PathVariable String divSdo,
			@PathVariable String date, HttpServletResponse response,
			HttpServletRequest request, ModelMap model)
			throws JsonParseException, JsonMappingException, IOException {
		BCITSLogger.logger.info("In add sub div manually  :::::::::Controller "
				+ date + "---" + divSdo);
		return downloadService.showMobileDevicesLiveBySection(divSdo, date,
				model);
	}

	@RequestMapping(value = "/showDeviceMrBilled/{sectioncode}/{date}", method = {
			RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	Object showDeviceMrBilled(@PathVariable String sectioncode,
			@PathVariable String date, HttpServletResponse response,
			HttpServletRequest request, ModelMap model)
			throws JsonParseException, JsonMappingException, IOException {
		BCITSLogger.logger.info("In add sub div manually  :::::::::Controller "
				+ date + "---" + sectioncode);
		return downloadService.getDeviceMrBilled(sectioncode, date, model);
	}

	@RequestMapping(value = "/showDeviceMrNotBilled/{sectioncode}/{date}", method = {
			RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	Object showDeviceMrNotBilled(@PathVariable String sectioncode,
			@PathVariable String date, HttpServletResponse response,
			HttpServletRequest request, ModelMap model)
			throws JsonParseException, JsonMappingException, IOException {
		BCITSLogger.logger.info("In add sub div manually  :::::::::Controller "
				+ date + "---" + sectioncode);
		return downloadService.getDeviceMrNotBilled(sectioncode, date, model);
	}

	@RequestMapping(value = "/showMeterReadersLive", method = {RequestMethod.GET, RequestMethod.POST })
	public String showMeterReadersLive(HttpServletRequest request,ModelMap model) {
		/*
		 * String cuurentDate=new SimpleDateFormat("dd-MM-yyyy").format(new
		 * Date()); BCITSLogger.logger.info("===================."+cuurentDate);
		 * BCITSLogger
		 * .logger.info("===================."+request.getParameter("fromDate"
		 * )); if(request.getParameter("fromDate")!=null) {
		 * downloadService.divisionWiseMrCount
		 * (model,request.getParameter("fromDate")); } else {
		 * downloadService.divisionWiseMrCount(model,cuurentDate); }
		 */
		String cuurentDate = new SimpleDateFormat("dd-MM-yyyy")
				.format(new Date());
		model.put("cuurentDate", cuurentDate);
		return "meterReaderTrack";
	}

	@RequestMapping(value = "/showMrTrackLive/{divSdo}/{date}", method = {
			RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	Object getMrTrackLive(@PathVariable String divSdo,
			@PathVariable String date, HttpServletResponse response,
			HttpServletRequest request, ModelMap model)
			throws JsonParseException, JsonMappingException, IOException {
		BCITSLogger.logger.info("In add sub div manually  :::::::::Controller "
				+ date + "---" + divSdo);
		return downloadService.showMrTrackLive(divSdo, date, model);
	}

	@RequestMapping(value = "/getSubdivisionList/{divSdo}", method = {
			RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	Object getSubdivisionList(@PathVariable String divSdo,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap model) throws JsonParseException, JsonMappingException,
			IOException {
		BCITSLogger.logger.info("In add sub div manually  :::::::::Controller"
				+ divSdo);
		return siteLocationService.findDistinctSubDivision(divSdo, model,
				request);
	}

	@RequestMapping(value = "/getLocationTypeVal/{locType}", method = {
			RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	Object getLocationTypeVal(@PathVariable String locType,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap model) throws JsonParseException, JsonMappingException,
			IOException {
		BCITSLogger.logger.info("In add sub div manually  :::::::::Controller"
				+ locType);
		return siteLocationService.findByLocType(locType, model, request);
	}

	@RequestMapping(value = "/showMrTrackLiveMobile", method = {
			RequestMethod.GET, RequestMethod.POST }, headers = "Accept=application/json")
	public @ResponseBody
	List<?> getMrTrackLiveMobile(@RequestBody String user_List)
			throws JsonParseException, JsonMappingException, IOException {
		JSONArray arr;
		String date;
		String divSdo;
		try {
			arr = new JSONArray(user_List);
			JSONObject Asst_json_obj = arr.getJSONObject(0);
			date = Asst_json_obj.getString("date");
			divSdo = Asst_json_obj.getString("value");
			BCITSLogger.logger
					.info("In add sub div manually :::::::::Controller " + date
							+ "---" + divSdo);
			return downloadService.showMrTrackLiveMobile(divSdo, date);
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
	}

	@RequestMapping(value = "/trackMrPerformance", method = {
			RequestMethod.GET, RequestMethod.POST })
	public String trackMrPerformance(HttpServletRequest request, ModelMap model) {
		String cuurentDate = new SimpleDateFormat("dd-MM-yyyy")
				.format(new Date());
		model.put("cuurentDate", cuurentDate);
		return "trackMrPerformance";
	}

	@RequestMapping(value = { "/showMrPerformance" }, method = RequestMethod.POST)
	public String showMrPerformance(ModelMap model, HttpServletRequest request) {
		BCITSLogger.logger.info("In add sub div manually  :::::::::Controller"
				+ request.getParameter("locCode") + "dfdf"
				+ request.getParameter("fromDate"));
		downloadService.getMrPerfromance(request.getParameter("locCode"),
				request.getParameter("fromDate"));
		model.put("cuurentDate", request.getParameter("fromDate"));
		return "trackMrPerformance";
	}

	@RequestMapping(value = "/deviceWiseReport", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String deviceWiseReport(HttpServletRequest request, ModelMap model) {
		String currentMonth = new SimpleDateFormat("yyyyMM").format(new Date());
		model.put("currentMonth", currentMonth);
		BCITSLogger.logger.info("===================."
				+ request.getParameter("fromDate"));
		try {
			if (request.getParameter("fromDate") != null) {
				mrDeviceAllocationService.getDeviceWiseReport(model,
						request.getParameter("fromDate"));
			} else {
				mrDeviceAllocationService.getDeviceWiseReport(model,
						currentMonth);
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.put("results", "Error while processing.");
			return "deviceWiseReport";
		}
		return "deviceWiseReport";
	}

	@RequestMapping(value = "/showDevicesNotLiveToday/{month}", method = {
			RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	Object showDevicesNotLiveToday(@PathVariable String month,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap model) throws JsonParseException, JsonMappingException,
			IOException {
		String currentDate = new SimpleDateFormat("dd/MM/yyyy")
				.format(new Date());
		BCITSLogger.logger.info("In add sub div manually  :::::::::Controller "
				+ currentDate + "---" + month);
		return downloadService.showDevicesNotLiveToday(month, currentDate,
				model);
	}

	@Link(label = "Devices Management", family = "AdminController", parent = "")
	@RequestMapping(value = "/updateDeviceStatus", method = RequestMethod.GET)
	public String updateDeviceStatus(
			@ModelAttribute("mrDevices") MRDeviceEntity mrDevices,
			HttpServletRequest request, ModelMap model,
			BindingResult bindingResult) {
		model.put("mrdList", mrDeviceService.findAllMRDevices(request));
		model.put("statusList", mrDeviceService.getCheckConstraints(
				"devicemaster", "device_status", request));
		model.put("sdoCodes", siteLocationService.getAllSiteCodes());
		model.put("results", "notDisplay");
		return "updateDeviceStatus";
	}

	@RequestMapping(value = "/updateMobileStatus", method = {
			RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	Object updateMobileStatus(@RequestParam String sitecodeVal,
			@RequestParam String mobileid, @RequestParam String status,
			@RequestParam String remark, HttpServletRequest request,
			ModelMap model) throws JsonParseException, JsonMappingException,
			IOException {
		BCITSLogger.logger.info("In add sub div manually  :::::::::Controller "
				+ sitecodeVal + "===" + mobileid + "===" + status + "-==-=-"
				+ remark);
		return null;
	}

	@Link(label = "Security Launcher", family = "AdminController", parent = "")
	@RequestMapping(value = "/viewSecurityLauncher", method = RequestMethod.GET)
	public String viewSecurityLauncher(ModelMap model,
			HttpServletRequest request) {
		BCITSLogger.logger.info("In Security Launcher");
		userService.getRecentPath(
				request.getRequestURI().substring(4,
						request.getRequestURI().length()), request);
		model.addAttribute("results", "notDisplay");
		model.addAttribute("securityPwd",
				employeeOracleService.showSecurityPwd(model, request));
		return "securityLauncher";
	}

	@RequestMapping(value = "/checkSecurityLauncherPassword", method = {
			RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody
	Object getLocationDetails(@RequestBody String jasonArray)
			throws JSONException {
		BCITSLogger.logger.info("in checkSecurityLauncherPassword...........");
		String value = "";
		try {
			JSONArray arr = new JSONArray(jasonArray);
			for (int i = 0; i < arr.length(); i++) {
				JSONObject Asst_json_obj = arr.getJSONObject(i);
				String pwd = Asst_json_obj.getString("PASSWORD");
				BCITSLogger.logger
						.info("in checkSecurityLauncherPassword...........pwd"
								+ pwd);
				String securityPwd = employeeOracleService.showSecurityPwd(
						null, null);
				if (securityPwd.equals(pwd)) {
					return "SUCCESS";
				} else {
					return "FAILED";
				}
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return value;
	}

	@RequestMapping(value = "/changeSecurityLauncherPassword", method = RequestMethod.GET)
	public @ResponseBody
	String changeSecurityLauncherPassword() throws JSONException {
		String newPwd = "";
		String securityPwd = "";
		try {
			Random rand = new Random();
			newPwd = (long) (rand.nextDouble() * 10000000000L) + "";
			BCITSLogger.logger.info("newPwd" + newPwd);
			int value = employeeOracleService
					.updateSecurityLauncherPassword(newPwd);
			if (value == 1) {
				securityPwd = employeeOracleService.showSecurityPwd(null, null);
			} else {
				securityPwd = "error";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return securityPwd;
	}

	@RequestMapping(value = "/sectionRealTimeStatus", method = {
			RequestMethod.GET, RequestMethod.POST })
	public String sectionRealTimeStatus(HttpServletRequest request,
			ModelMap model) {
		employeeOracleService.secRealTimeStatus(model);
		return "sectionWiseLive";
	}

	@RequestMapping(value = "/deviceUsageRpt", method = { RequestMethod.GET,RequestMethod.POST })
	public String deviceUsageRpt(HttpServletRequest request, ModelMap model) 
	{
		String currentMonth = new SimpleDateFormat("yyyyMM").format(new Date());
		model.put("currentMonth", currentMonth);
		try {
			if (request.getParameter("fromDate") != null)
			{
				downloadService.deviceUsageRpt(model,request.getParameter("fromDate"));
			} 
			else 
			{
				downloadService.deviceUsageRpt(model,currentMonth);
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.put("results", "Error while processing.");
			return "deviceWiseUsageRpt";
		}
		return "deviceWiseUsageRpt";
	}
	
	
	@RequestMapping(value = "/getDeviceFromMobileMaster/{provider}", method = {RequestMethod.GET })
	public @ResponseBody Object getDeviceFromMobileMaster(@PathVariable String provider) 
	{
		return mrDeviceService.getDeviceFromMobileMaster(provider);
	}
	
	@RequestMapping(value = "/deviceProviderRpt", method = { RequestMethod.GET,RequestMethod.POST })
	public String deviceProviderRpt(HttpServletRequest request, ModelMap model) 
	{
		String currentMonth = new SimpleDateFormat("yyyyMM").format(new Date());
		model.put("currentMonth", currentMonth);
		try {
			if (request.getParameter("fromDate") != null)
			{
				downloadService.deviceProviderRpt(model,request.getParameter("fromDate"));
			} 
			else 
			{
				downloadService.deviceProviderRpt(model,currentMonth);
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			model.put("results", "Error while processing.");
			
			return "deviceProviderwiseRpt";
		}
		return "deviceProviderwiseRpt";
	}
	
	/*@RequestMapping(value = "/mrTypeRpt", method = { RequestMethod.GET,RequestMethod.POST })
	public String mrTypeRpt(HttpServletRequest request, ModelMap model) 
	{
		String currentMonth = new SimpleDateFormat("yyyyMM").format(new Date());
		model.put("currentMonth", currentMonth);
		try {
			if (request.getParameter("fromDate") != null)
			{
				downloadService.mrTypeRpt(model,request.getParameter("fromDate"));
			} 
			else 
			{
				downloadService.mrTypeRpt(model,currentMonth);
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			model.put("results", "Error while processing.");
			
			return "mrTypeRpt";
		}
		return "mrTypeRpt";
	}*/
	
}
