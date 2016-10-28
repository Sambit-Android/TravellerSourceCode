package com.bcits.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bcits.entity.DefectiveMeterEntity;
import com.bcits.entity.LocationOracleEntity;
import com.bcits.entity.SiteLocationEntity;
import com.bcits.service.DefectiveMeterService;
import com.bcits.service.HHBM_ConsumerService;
import com.bcits.service.LocationOracleService;
import com.bcits.service.MeterChangeService;
import com.bcits.service.MrMasterOracleService;
import com.bcits.service.PaymentService;
import com.bcits.service.SiteLocationService;
import com.bcits.utility.BCITSLogger;
import com.bcits.utility.ConnectionClass;
import com.bcits.utility.Resultupdated;



@Controller
public class MRMController {
	
	
	@Autowired
	private MeterChangeService meterChangeService;
	
	
	@Autowired
	private DefectiveMeterService defectiveMeterService;
	
	@Autowired
	private PaymentService paymentService;
	
	@Autowired
	private MrMasterOracleService masterOracleService;
	
	@Autowired
	private SiteLocationService siteLocationService;
	
	@Autowired
	private HHBM_ConsumerService hhbm_ConsumerService;
	
	@Autowired
	private LocationOracleService locationOracleService;
	
	@PersistenceContext(unitName="ORACLEDataSource")
	protected EntityManager oracleEntityManager;

	
	
	
	
	@RequestMapping(value="/getdatenew",method={RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String getServerDateToUpload() 
	{
		System.out.println("Coming to getdatenew method=======");
		String serverDate = null ;
		try
		{
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Calendar cal = Calendar.getInstance();
			serverDate = dateFormat.format(cal.getTime());
		}
		catch (Exception e){}
		return serverDate;
	}
	
	
	
	@RequestMapping(value = "/getconsumerdataformobilefromoracledb/{socode}", method = {RequestMethod.GET,RequestMethod.POST})
    public @ResponseBody
    String getConsumerDataForMobileFromOracleDB(@PathVariable("socode") String  socode, HttpServletRequest request) throws JSONException
    {
        BCITSLogger.logger.info("In getConsumerDataForMobileFromOracleDB...........");
        String sdocode = socode;
        int originalSDoCode = Integer.parseInt(socode);


        JSONArray list = new JSONArray();
            try {
                List<LocationOracleEntity> locList=locationOracleService.getLocationData(sdocode, null);
                
                BCITSLogger.logger.info("Location List Size " + locList.size());
                String schema=locList.get(0).getDbuser();
                BCITSLogger.logger.info("schema name = " + schema);
                
                // listno not required
                
                int billmonth = new Date().getMonth();// It will give the previous month which we needed
                
                
                list = defectiveMeterService.getAllDisConnectionListDataNew(originalSDoCode, schema, billmonth);
                
                
             //   list =  getAllDisConnectionListDataNewDirect(listno, schema, 7);
                
                
            } catch (Exception exception) {
            	BCITSLogger.logger.info("getConsumerDataForMobileFromOracleDB Main Try Catch  "+exception.getMessage());
                exception.printStackTrace();

            }

        return list.toString();
    } 
	
	
	
	
	
	
	@RequestMapping(value="/mobiledownloaddatavalidation/{listno}",method={RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String getMobileDownloadDataValidation(@PathVariable("listno") String  listno, HttpServletRequest request) 
	{
		String listnovalidation =null;
		
		//listnovalidation = defectiveMeterService.getListNoValidationResultDefective(listno, request);
		
		listnovalidation = "LISTISVALID#DOWNLOADAVAILABLE";
		

System.out.println("listnovalidation :"+listnovalidation);		

		return listnovalidation;
	}
	
	
	
	
	
	/*@RequestMapping(value = "/getconsumerdataformobile/{listno}", method = {RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody List getCosnumerDataForMobile(@PathVariable("listno") String  listno,HttpServletRequest request) throws IOException {
System.out.println("Coming to method===========");
	   List list = new ArrayList<DefectiveMeterEntity>();

		try{
			list = defectiveMeterService.getMobileBillingDataDefective(listno, request);
			//return list;
			}
		catch(Exception e){
			e.printStackTrace();
		}
		
		
		System.out.println(list);
		
		return list;
	}*/


	@RequestMapping(value = "/insertdatatometerchangetable", method = RequestMethod.POST)
	public @ResponseBody ArrayList<Resultupdated>  getinsertDeviceInformation(@RequestBody String body,HttpServletRequest request) throws IOException, JSONException {

		ArrayList<Resultupdated> list = new ArrayList<Resultupdated>();

		try{

			JSONArray array =new JSONArray(body); 
			
			list = meterChangeService.updateMobileDataToMeterChangeTable(request, array);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
	@RequestMapping(value="/showCollectionViewMap", method = {RequestMethod.GET,RequestMethod.POST})
	public String showCollectionViewMap(HttpServletRequest request, ModelMap model) 
    {
		String cuurentDate=new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		model.put("cuurentDate", cuurentDate);
		return "collectionViewMap";
	}
	
	@RequestMapping(value = "/showCollectionOnMap/{divSdo}/{date}", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody  Object showCollectionOnMap(@PathVariable String divSdo,@PathVariable String date,HttpServletResponse response,HttpServletRequest request,ModelMap model) throws JsonParseException, JsonMappingException, IOException
	{
		BCITSLogger.logger.info("In add sub div manually  :::::::::Controller "+date+"---"+divSdo);
		return paymentService.showCollectionOnMap(divSdo,date, model);
	}
	
	
	@RequestMapping(value="/showenrgyLosses", method = {RequestMethod.GET,RequestMethod.POST})
	public String showenrgyLosses(HttpServletRequest request, ModelMap model) 
    {
		String cuurentDate=new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		model.put("cuurentDate", cuurentDate);
		return "energyLossesView";
	}

	
	@RequestMapping(value="/showMeterReplacement", method = {RequestMethod.GET,RequestMethod.POST})
	public String showMeterReplacement(HttpServletRequest request, ModelMap model) 
    {
		String cuurentDate=new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		model.put("cuurentDate", cuurentDate);
		return "meterReplacementgmap";
	}
	
	@RequestMapping(value = "/showMeterReplacementOnMap/{divSdo}/{date}", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody  Object showMeterReplacementOnMap(@PathVariable String divSdo,@PathVariable String date,HttpServletResponse response,HttpServletRequest request,ModelMap model) throws JsonParseException, JsonMappingException, IOException
	{
		BCITSLogger.logger.info("In add sub div manually  :::::::::Controller "+date+"---"+divSdo);
		return meterChangeService.showMeterReplacementOnMap(divSdo,date, model);
	}
	
	
	@RequestMapping(value="/showMrOracleDetails/{mrcode}/{sdocode}",method={RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody Object showMrOracleDetails(@PathVariable String mrcode,@PathVariable String sdocode, HttpServletRequest request) 
	{
		BCITSLogger.logger.info("========================>mrcodesdocode"+mrcode+" "+sdocode);
		ConnectionClass con=new ConnectionClass();
		con.getconnection();
		return masterOracleService.showMrOracleDetails(mrcode,sdocode);
	}
	
	@RequestMapping(value="/showMrDaywiseReport", method = {RequestMethod.GET,RequestMethod.POST})
	public String showMrDaywiseReport(HttpServletRequest request, ModelMap model) 
    {
		String cuurentDate=new SimpleDateFormat("yyyyMM").format(new Date());
		model.put("cuurentDate", cuurentDate);
		List<Object[]> list=siteLocationService.findByLocType("section",model,request);
		model.put("sectionList", list);
		model.put("daysOfMonth", siteLocationService.getNoOfDaysofMonth(Integer.parseInt(cuurentDate.substring(0, 4)),Integer.parseInt(cuurentDate.substring(4, 6))));
		model.put("results", "notDisplay");
		model.put("dispalyTH", "No");
		return "mrDaywiseReport";
	}
	
	/*@RequestMapping(value="/showMrDaywiseBySection/{siteCode}/{billMonth}",method={RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody Object showMrDaywiseBySection(@PathVariable String siteCode,@PathVariable String billMonth, HttpServletRequest request,ModelMap model) 
	{
		BCITSLogger.logger.info("========================>mrcodesdocode"+siteCode+" "+billMonth);
		List<Object[]> list=hhbm_ConsumerService.showMrDaywiseBySection(siteCode, billMonth,model);
		return list;
	}*/
	
	@RequestMapping(value="/showMrDaywiseBySection",method={RequestMethod.POST,RequestMethod.GET})
	public String showMrDaywiseBySection(HttpServletRequest request,ModelMap model) 
	{
		try 
		{
			List<Object[]> list=hhbm_ConsumerService.showMrDaywiseBySection(request.getParameter("siteCodeval"), request.getParameter("fromDate"),model);
			
			if(list!=null)
			{
				if(list.size()>0)
				{
					model.put("results", "notDisplay");
					model.put("daysOfMonth", siteLocationService.getNoOfDaysofMonth(Integer.parseInt(request.getParameter("fromDate").substring(0, 4)),Integer.parseInt(request.getParameter("fromDate").substring(4, 6))));
					model.put("mrDaywiseList", list);
					model.put("dispalyTH", "Yes");
				}
				else
				{
					model.put("results", "No Data Available.");
				}
			}
			model.put("cuurentDate", request.getParameter("fromDate"));
			List<Object[]> sectionlist=siteLocationService.findByLocType("section",model,request);
			model.put("sectionList", sectionlist);
			String section="";
			List<SiteLocationEntity> listVal=siteLocationService.findBySdocode(request.getParameter("siteCodeval"));
			for (SiteLocationEntity siteLocationEntity : listVal)
			{
				section=siteLocationEntity.getSection();
			}
			model.put("sectionname", section.replace(" ", "_"));
			model.put("sdocodeVal", request.getParameter("siteCodeval"));
		} 
		catch (Exception e)
		{
			model.put("results", "No Data Available");
		}
		
		return "mrDaywiseReport";
	}
	
	
	@RequestMapping(value="/showMrPerformanceView", method = {RequestMethod.GET,RequestMethod.POST})
	public String showMrPerformanceView(HttpServletRequest request, ModelMap model) 
    {
		String cuurentDate=new SimpleDateFormat("yyyyMM").format(new Date());
		model.put("cuurentDate", cuurentDate);
		List<Object[]> list=siteLocationService.findByLocType("section",model,request);
		model.put("sectionList", list);
		model.put("results", "notDisplay");
		return "mrPerformanceView";
	}
	
	@RequestMapping(value="/displayMrPerformance",method={RequestMethod.POST,RequestMethod.GET})
	public String showMrPerformance(HttpServletRequest request,ModelMap model) 
	{
		try 
		{
			List<Object[]> list=hhbm_ConsumerService.mrPerformanceView(request.getParameter("siteCodeval"), request.getParameter("fromDate"),model);
			if(list.size()>0)
			{
				model.put("results", "notDisplay");
				model.put("mrPerformanceList", list);
			}
			else
			{
				model.put("results", "No Data Available.");
			}
			model.put("cuurentDate", request.getParameter("fromDate"));
			List<Object[]> sectionlist=siteLocationService.findByLocType("section",model,request);
			model.put("sectionList", sectionlist);
			String section="";
			List<SiteLocationEntity> listVal=siteLocationService.findBySdocode(request.getParameter("siteCodeval"));
			for (SiteLocationEntity siteLocationEntity : listVal)
			{
				section=siteLocationEntity.getSection();
			}
			model.put("sectionname", section);
			model.put("sdocodeVal", request.getParameter("siteCodeval"));
		} 
		catch (Exception e)
		{
			model.put("results", "Error while processing.");
		}
		
		return "mrPerformanceView";
	}
	
	
	
	
     @RequestMapping(value = "/displayMrPerformanceForMobile", method = {RequestMethod.GET,RequestMethod.POST}, headers = "Accept=application/json")
     public Object   displayMrPerformanceForMobile(@RequestBody String user_List,HttpServletRequest request,ModelMap model) throws JsonParseException, JsonMappingException, IOException
     {

          JSONArray arr;
         String date;
         String divSdo;
         List<Object[]> list=null;


         try {
             arr = new JSONArray(user_List);
             JSONObject Asst_json_obj = arr.getJSONObject(0);
             date = Asst_json_obj.getString("fromDate");
             divSdo = Asst_json_obj.getString("siteCodeval");
             BCITSLogger.logger.info("In method manually :::::::::Controller "+date+"---"+divSdo);
             List<Map<String, Object>> approvalMap = null ;

             try
             {
               list=hhbm_ConsumerService.mrPerformanceView(divSdo,date,model);
                 BCITSLogger.logger.info("listno "+list);
                 approvalMap = new ArrayList<Map<String,Object>>();

                     for(final Object[] record : list)
                     {
                     approvalMap.add(new HashMap<String, Object>() {{

                         put("id",record[0]);
                        put("mrcode",record[1]);
                        put("totBills",record[2]);
                        put("noofDaysBilled",record[3]);
                        put("avgBillperday",record[4]);
                        put("normal",record[5]);
                        put("dl",record[6]);
                        put("mnr",record[7]);
                        put("other",record[8]);
                        put("expectedBills",record[9]);
                        put("devEff",record[10]);
                        put("mrname",record[11]);
                        put("contactNo",record[12]);


                      }});
                     }

             }
             catch (Exception e)
             {
                 BCITSLogger.logger.info("Error while saving JSON object to DB");
             }
for (Iterator iterator = approvalMap.iterator(); iterator.hasNext();)
{
	Map<String, Object> map = (Map<String, Object>) iterator.next();
	BCITSLogger.logger.info("======================================>map"+map.get("mrcode"));
	BCITSLogger.logger.info("======================================>map"+map.get("noofDaysBilled"));
}
             return approvalMap;
         } catch (JSONException e) 
         {
             e.printStackTrace();
             return "NoData";
         }


}


     @RequestMapping(value = "/getMobileMRPerformance", method = {RequestMethod.GET,RequestMethod.POST}, headers = "Accept=application/json")
     public @ResponseBody  List<?> getMrTrackLiveMobile(@RequestBody String user_List,ModelMap model) throws JsonParseException, JsonMappingException, IOException
     {
         JSONArray arr;
         String date;
          String divSdo;


         try {
              arr = new JSONArray(user_List);
              JSONObject Asst_json_obj = arr.getJSONObject(0);
              date = Asst_json_obj.getString("fromDate");
              divSdo = Asst_json_obj.getString("siteCodeval");
              BCITSLogger.logger.info("In method manually :::::::::Controller "+date+"---"+divSdo);


              return hhbm_ConsumerService.mrPerformanceView(divSdo,date,model);
         } catch (JSONException e) {
             e.printStackTrace();
             return null;
         }

 }


     @RequestMapping(value = "/getMobileMRPerformance1", method = {RequestMethod.GET,RequestMethod.POST}, headers = "Accept=application/json")
     public @ResponseBody  List<?> getMobileMRPerformance1(@RequestBody String user_List,ModelMap model) throws JsonParseException, JsonMappingException, IOException
     {
         JSONArray arr;
         String date;
          String divSdo;


         try {
              arr = new JSONArray(user_List);
              JSONObject Asst_json_obj = arr.getJSONObject(0);
              date = Asst_json_obj.getString("fromDate");
              divSdo = Asst_json_obj.getString("siteCodeval");
              BCITSLogger.logger.info("In method manually :::::::::Controller "+date+"---"+divSdo);

              List<Map<String, Object>> approvalMap = new ArrayList<Map<String,Object>>();
             List<Object[]> list= (List<Object[]>) hhbm_ConsumerService.mrPerformanceViewForMobile(divSdo,date,model);
             for(Iterator<?> iterator=list.iterator();iterator.hasNext();){
                 Object[] record=(Object[]) iterator.next();
                 Map<String, Object> data=new HashMap<String, Object>();
                 data.put("id",record[0]);
                 data.put("mrcode",record[1]);
                 data.put("totBills",record[2]);
                 data.put("noofDaysBilled",record[3]);
                 data.put("avgBillperday",record[4]);
                 data.put("normal",record[5]);
                 data.put("dl",record[6]);
                 data.put("mnr",record[7]);
                 data.put("other",record[8]);
                 data.put("expectedBills",record[9]);
                 data.put("devEff",record[10]);
                 data.put("mrname",record[11]);
                 data.put("contactNo",record[12]);
                 approvalMap.add(data);
             }
             return approvalMap;
         } catch (JSONException e) {
             e.printStackTrace();
             return null;
         }

 } 
	
     
	
	
	
	
}
