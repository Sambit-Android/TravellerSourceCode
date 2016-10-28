package com.bcits.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bcits.entity.LocationOracleEntity;
import com.bcits.entity.OnAirVersionUpdationEntityNew;
import com.bcits.service.AllowedDeviceVersionServiceNew;
import com.bcits.service.ConsumerDetailsSearchService;
import com.bcits.service.EmployeeOracleService;
import com.bcits.service.HHBM_DownloadService;
import com.bcits.service.LocationOracleService;
import com.bcits.service.MRBillingService;
import com.bcits.service.MrBilledRrnoService;
import com.bcits.service.OnAirVersionUpdationServiceNew;
import com.bcits.service.RrnoSearchService;
import com.bcits.utility.BCITSLogger;

@Controller
public class AndroidAppController {

	@Autowired
	private LocationOracleService locationOracleService;

	@Autowired
	private EmployeeOracleService employeeOracleService;

	@Autowired
	private MRBillingService mrBillingService;

	@Autowired
	private MrBilledRrnoService mrBilledRrService;

	@Autowired
	private OnAirVersionUpdationServiceNew onAirVersionUpdationServiceNew;

	@Autowired
	private AllowedDeviceVersionServiceNew allowedDeviceVersionServiceNew;

	@Autowired
	private RrnoSearchService rrnoSearchService;

	@Autowired
	private ConsumerDetailsSearchService consumerDetailsSearchService;

	@Autowired
	private HHBM_DownloadService hhbm_downloadservice;

	/*
	 * @Autowired private LocationOracleEntity locationEntity;
	 * 
	 * @Autowired private LocationEntityService locationEntityService;
	 */

	/*
	 * @PersistenceContext protected EntityManager entityManager;
	 */

	@RequestMapping(value = "/getLocationDetails", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object getLocationDetails() throws JSONException {
		BCITSLogger.logger.info("in getLocationDetails...........");
		Object locationList = null;
		try {

			locationList = locationOracleService.getAllLocations();

		} catch (Exception exception) {
			BCITSLogger.logger.info("Error while saving JSON object to DB");
			exception.printStackTrace();
		}
		return locationList;
	}

	@RequestMapping(value = "/location", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody List<LocationOracleEntity> mobileLocation() throws JSONException {
		BCITSLogger.logger.info("In Mobile Location...........");
		List<LocationOracleEntity> response = null;
		Map<String, String> map = null;
		try {

			response = locationOracleService.getAllLocations();
			if (response.size() > 0) {
				return response;

			} else {
				return null;
			}

		} catch (Exception exception) {
			exception.printStackTrace();
			return null;
		}
	}

	/**
	 * @category GET LATEST VERSION Based On deviceID
	 * @return version
	 * @throws JSONException
	 * @author Shivanand
	 */

	@Transactional(propagation = Propagation.REQUIRED)
	@RequestMapping(value = "/apk/getapkversion/{deviceid}/{appname}", method = { RequestMethod.POST,
			RequestMethod.GET })
	public @ResponseBody String getVersion_deviceid(@PathVariable("deviceid") String deviceid,
			@PathVariable("appname") String appname) throws JSONException {
		BCITSLogger.logger.info("in getVersion_...........");
		String version = null;

		System.out.println(" appname " + appname);

		try {
			List<OnAirVersionUpdationEntityNew> entities = onAirVersionUpdationServiceNew.getlatestVersion(appname);
			if (entities.size() > 0) {
				version = entities.get(0).getApkVersion();
			}

			System.out.println("Coming 2nd loop :" + version);

		} catch (Exception exception) {
			BCITSLogger.logger.info("Error while saving JSON object to DB");
			exception.printStackTrace();
		}

		return version;
	}

	/**
	 * @category DOWNLOAD APK FILE
	 * @param request
	 * @param response
	 * @return APK FILE
	 * @author shivanand
	 */

	@Transactional(propagation = Propagation.REQUIRED)
	@RequestMapping(value = "/apk/downloadApk/{deviceid}/{appname}", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody void getApkFileDeviceid(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("deviceid") String deviceid, @PathVariable("appname") String appname) {
		BCITSLogger.logger.info("in getApkFile...........");
		try {

			List<OnAirVersionUpdationEntityNew> entities = onAirVersionUpdationServiceNew.getlatestVersion(appname);
			// String filePathToBeServed = "D:\\JVVNLAPKFILES\\JVVNL_V3.44.apk";
			final String FILE_PATH = entities.get(0).getApkPath() + entities.get(0).getApkName();
			String filePathToBeServed = FILE_PATH;

			File fileToDownload = new File(filePathToBeServed);

			System.out.println("filePathToBeServed :" + filePathToBeServed);

			long length = fileToDownload.length();

			if (length <= Integer.MAX_VALUE) {
				response.setContentLength((int) length);
			} else {/*
					 * response.addHeader("Content-Length",
					 * Long.toString(length));
					 */
			}
			InputStream inputStream = new FileInputStream(fileToDownload);
			response.setContentType("application/force-download");
			response.setHeader("Content-Disposition", "attachment; filename=" + entities.get(0).getApkName());
			IOUtils.copy(inputStream, response.getOutputStream());
			response.flushBuffer();

		} catch (Exception e) {
			BCITSLogger.logger.info("Request could not be completed at this moment. Please try again.");
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/getBillingProgress", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody List<Map<String, Object>> billingProgress(@RequestBody String user_List) throws JSONException {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		BCITSLogger.logger.info("In Mobile BillingProgress...........");
		List<Object[]> response = null;
		List<Object[]> response2 = null;
		Map<String, Object> map = null;
		String sdocode;
		String billedday = "";
		try {
			JSONArray arr = new JSONArray(user_List);
			JSONObject Asst_json_obj = arr.getJSONObject(0);
			sdocode = Asst_json_obj.getString("sdocode");
			System.out.println("SDO CODE being passed " + sdocode);

			response = (List<Object[]>) mrBillingService.getMrBillingProgress(sdocode);

			if (response.size() > 0) {
				for (Object[] obj : response) {

					int unbilled = Integer.parseInt(obj[4].toString()) - Integer.parseInt(obj[5].toString());
					int unbilled_day = Integer.parseInt(obj[6].toString()) - Integer.parseInt(obj[7].toString());

					map = new HashMap<String, Object>();
					map.put("mrname", obj[0]);
					map.put("mrqualification", obj[1]);
					map.put("section", obj[2]);
					map.put("sitecode", obj[3]);
					map.put("tobebilled", obj[4]);
					map.put("billed", obj[5]);
					map.put("unbilled", String.valueOf(unbilled));
					map.put("tobebilledday", obj[6]);
					map.put("billedday", obj[7]);
					map.put("unbilledday", String.valueOf(unbilled_day));
					map.put("mrcode", obj[8]);
					map.put("billsissueday", obj[9]);
					result.add(map);
				}

				return result;

			} else {
				return null;
			}

		} catch (Exception exception) {
			exception.printStackTrace();
			return null;
		}
	}

	@RequestMapping(value = "/getBilledRrnos", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody List<Map<String, Object>> rrnobilled(@RequestBody String user_List) throws JSONException {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		BCITSLogger.logger.info("In Mobile GETTING RRNOS...........");
		List<Object[]> response = null;
		Map<String, Object> map = null;
		String sdocode, schema, rdngmonth, mrcode, rdngdate, responseType;
		try {
			JSONArray arr = new JSONArray(user_List);
			JSONObject Asst_json_obj = arr.getJSONObject(0);
			sdocode = Asst_json_obj.getString("sdocode");
			schema = Asst_json_obj.getString("schema");
			rdngmonth = Asst_json_obj.getString("rdngmonth");
			mrcode = Asst_json_obj.getString("mrcode");
			rdngdate = Asst_json_obj.getString("rdngdate");
			// responseType = Asst_json_obj.getString("responsetype");

			// BCITSLogger.logger.info("GETTING DETAILS " + sdocode );

			response = (List<Object[]>) mrBilledRrService.getMrBilledRrnosMonth(sdocode, schema, rdngmonth, mrcode);

			if (response.size() > 0) {
				for (Object[] obj : response) {
					map = new HashMap<String, Object>();
					map.put("rdngmonth", obj[0]);
					map.put("rrno", obj[1]);
					map.put("tariffdesc", obj[2]);
					map.put("consumername", obj[3]);
					map.put("consumeraddress", obj[4]);
					map.put("consumeraddress1", obj[5]);
					map.put("latitude", obj[6]);
					map.put("longitude", obj[7]);
					map.put("rdngdate", obj[8]);
					map.put("socode", obj[9]);
					map.put("feedercode", obj[10]);
					map.put("tccode", obj[11]);
					map.put("meterstatus", obj[12]);
					map.put("billno", obj[13]);
					map.put("billdate", obj[14]);
					map.put("netamtdue", obj[15]);
					result.add(map);
				}

				return result;
			}

			else {

			}

			/*
			 * response = (List<Object[]>)
			 * mrBilledRrService.getMrBilledRrnos(sdocode);
			 * 
			 * if(response.size()>0) { for(Object[] obj:response){ map=new
			 * HashMap<String, Object>(); map.put("mrname", obj[0]);
			 * map.put("mrqualification", obj[1]); map.put("section", obj[2]);
			 * map.put("sitecode", obj[3]); map.put("tobebilled", obj[4]);
			 * map.put("billed", obj[5]); map.put("unbilled", obj[6]);
			 * map.put("tobebilledday", obj[7]); map.put("billedday", obj[8]);
			 * map.put("unbilledday", obj[9]); map.put("mrcode", obj[10]);
			 * result.add(map); }
			 * 
			 * return result;
			 * 
			 * } else { return null; }
			 */

		} catch (Exception exception) {
			exception.printStackTrace();
			return null;
		}
		return result;
	}

	@RequestMapping(value = "/mobileCommonLogin", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object mobileCommonLogin(@RequestBody String loginData) throws JSONException {
		BCITSLogger.logger.info("in mobileCommonLogin...........");
		List<Object[]> response = null;
		Map<String, String> map = null;
		try {
			JSONArray arr = new JSONArray(loginData);
			JSONObject obj = arr.getJSONObject(0);

			String userName = obj.getString("userName");
			String passWord = obj.getString("passWord");
			String dbSchema = obj.getString("dbSchema");

			System.out.println(userName + " " + passWord + " " + dbSchema);

			map = new HashMap<String, String>();
			response = employeeOracleService.mobileCommonLogin(userName, passWord);
			if (response.size() > 0) {
				Object[] res = response.get(0);

				map.put("DESIGNATION", (res[0] == null) ? "NA" : res[0].toString());
				map.put("USERTYPE", (res[1] == null) ? "NA" : res[1].toString());
				map.put("EMAIL", (res[2] == null) ? "NA" : res[2].toString());
				map.put("MOBILE", (res[3] == null) ? "NA" : res[3].toString());
				map.put("EMPNAME", (res[4] == null) ? "NA" : res[4].toString());
				map.put("SITECODE", (res[5] == null) ? "NA" : res[5].toString());
				return map;
			} else {
				return "NoData";
			}

		} catch (Exception exception) {
			exception.printStackTrace();
			return exception.getMessage();
		}
	}

	@RequestMapping(value = "/consumerHistoryGetRrnos/{inputVariable}/{inputtype}/{year}/{schemaname}", method = {
			RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object consumerHistoryGetRrnos(@PathVariable("inputVariable") String inputVariable,
			@PathVariable("inputtype") String inputtype, @PathVariable("year") String year,
			@PathVariable("schemaname") String schemaname) throws JSONException {
		BCITSLogger.logger.info("in consumerHistoryGetRrnos...........");
		List<Map<String, Object>> response = null;
		try {

			response = rrnoSearchService.getRRnumberlist(inputtype, inputVariable, schemaname, year);
			if (response.size() > 0) {
				return response;
			} else {
				return "NoData";
			}

		} catch (Exception exception) {
			exception.printStackTrace();
			return exception.getMessage();
		}
	}

	@RequestMapping(value = "/consumerHistoryGetConsumerDetails/{rrno}/{schemaname}", method = { RequestMethod.POST,
			RequestMethod.GET })
	public @ResponseBody Object consumerHistoryGetConsumerDetails(@PathVariable("rrno") String rrno,
			@PathVariable("schemaname") String schemaname) throws JSONException {
		BCITSLogger.logger.info("in consumerHistoryGetConsumerDetails...........");
		List<Map<String, Object>> response = null;
		try {

			response = consumerDetailsSearchService.getConsumerlistforrrno(rrno, schemaname);
			if (response.size() > 0) {

				return response;
			} else {
				return "NoData";
			}

		} catch (Exception exception) {
			exception.printStackTrace();
			return exception.getMessage();
		}
	}

	@RequestMapping(value = "/consumerHistoryGetBillingDetails/{rrno}/{schemaname}", method = { RequestMethod.POST,
			RequestMethod.GET })
	public @ResponseBody Object consumerHistoryGetBillingDetails(@PathVariable("rrno") String rrno,
			@PathVariable("schemaname") String schemaname) throws JSONException {
		BCITSLogger.logger.info("in consumerHistoryGetBillingDetails...........");
		List<Map<String, Object>> response = null;
		try {

			response = consumerDetailsSearchService.getBillingDetailsforrrno(rrno, schemaname);
			if (response.size() > 0) {

				return response;
			} else {
				return "NoData";
			}

		} catch (Exception exception) {
			exception.printStackTrace();
			return exception.getMessage();
		}
	}

	@RequestMapping(value = "/consumerHistoryGetPaymentDetails/{rrno}/{schemaname}", method = { RequestMethod.POST,
			RequestMethod.GET })
	public @ResponseBody Object consumerHistoryGetPaymentDetails(@PathVariable("rrno") String rrno,
			@PathVariable("schemaname") String schemaname) throws JSONException {
		BCITSLogger.logger.info("in consumerHistoryGetPaymentDetails...........");
		List<Map<String, Object>> response = null;
		try {

			response = consumerDetailsSearchService.getPaymentDetailsforrrno(rrno, schemaname);
			if (response.size() > 0) {

				return response;
			} else {
				return "NoData";
			}

		} catch (Exception exception) {
			exception.printStackTrace();
			return exception.getMessage();
		}
	}

	@RequestMapping(value = "/consumerHistoryGetDepositDetails/{rrno}/{schemaname}", method = { RequestMethod.POST,
			RequestMethod.GET })
	public @ResponseBody Object consumerHistoryGetDepositDetails(@PathVariable("rrno") String rrno,
			@PathVariable("schemaname") String schemaname) throws JSONException {
		BCITSLogger.logger.info("in consumerHistoryGetDepositDetails...........");
		List<Map<String, Object>> response = null;
		try {

			response = consumerDetailsSearchService.getDepositDetailsforrrno(rrno, schemaname);
			if (response.size() > 0) {

				return response;
			} else {
				return "NoData";
			}

		} catch (Exception exception) {
			exception.printStackTrace();
			return exception.getMessage();
		}
	}

	@RequestMapping(value = "/consumerHistoryGetMeterDetails/{rrno}/{schemaname}", method = { RequestMethod.POST,
			RequestMethod.GET })
	public @ResponseBody Object consumerHistoryGetMeterDetails(@PathVariable("rrno") String rrno,
			@PathVariable("schemaname") String schemaname) throws JSONException {
		BCITSLogger.logger.info("in consumerHistoryGetMeterDetails...........");
		List<Map<String, Object>> response = null;
		try {

			response = consumerDetailsSearchService.getMeterDetailsforrrno(rrno, schemaname);
			if (response.size() > 0) {

				return response;
			} else {
				return "NoData";
			}

		} catch (Exception exception) {
			exception.printStackTrace();
			return exception.getMessage();
		}
	}

	@RequestMapping(value = "/consumerHistoryGetMeterChangeDetails/{rrno}/{schemaname}", method = { RequestMethod.POST,
			RequestMethod.GET })
	public @ResponseBody Object consumerHistoryGetMeterChangeDetails(@PathVariable("rrno") String rrno,
			@PathVariable("schemaname") String schemaname) throws JSONException {
		BCITSLogger.logger.info("in consumerHistoryGetMeterChangeDetails...........");
		List<Map<String, Object>> response = null;
		try {

			response = consumerDetailsSearchService.getMeterChangeDetailsforrrno(rrno, schemaname);
			if (response.size() > 0) {

				return response;
			} else {
				return "NoData";
			}

		} catch (Exception exception) {
			exception.printStackTrace();
			return exception.getMessage();
		}
	}

}
