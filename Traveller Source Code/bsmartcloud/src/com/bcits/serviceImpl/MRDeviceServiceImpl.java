package com.bcits.serviceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

import com.bcits.entity.MRDeviceEntity;
import com.bcits.service.MRDeviceService;
import com.bcits.service.ModuleMasterService;
import com.bcits.utility.BCITSLogger;

@Repository
public class MRDeviceServiceImpl extends GenericServiceImpl<MRDeviceEntity>
		implements
		MRDeviceService {

	@Autowired
	private ModuleMasterService moduleMasterService;
	
	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<MRDeviceEntity> findAllMRDevices(HttpServletRequest request)
	{
		List<MRDeviceEntity> list=null;
        try 
        {
        	String sdoCode=(String) request.getSession().getAttribute("sdocodeval");
        	BCITSLogger.logger.info("============================>sdoCode"+sdoCode);
        	if(sdoCode!=null)
        	{
        		list=entityManager.createNamedQuery("MRDeviceEntity.findAll").setParameter("sdoCode",sdoCode).getResultList();
        	}
        	else
        	{
        		sdoCode="%";
        		list=entityManager.createNamedQuery("MRDeviceEntity.findAll").setParameter("sdoCode", sdoCode).getResultList();
        	}
		} 
         catch (Exception e) 
		{
			e.printStackTrace();
		}
		return list;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Map<String,Object>> findAllMRDevicesMobile(String sdocode)
	{
		List<Object[]> list=null;
		Map<String,Object> map=null;
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        try 
        {
        	String sdoCode=sdocode;
        	if(sdoCode!=null)
        	{
        		//String query = "SELECT A.deviceid,A.make,A.gprssimno,A.allocatedflag,A.approvalstatus,B.mrcode FROM vcloudengine.devicemaster as A JOIN vcloudengine.deviceallocation as B ON A.sdocode = B.sdocode";
        		//String query="SELECT AA.deviceid ,AA.make,(SELECT mrcode FROM vcloudengine.deviceallocation WHERE deviceid=AA.deviceid) FROM(SELECT * FROM vcloudengine.devicemaster WHERE sdocode='"+sdocode+"' AND allocatedflag IN ('ALLOCATED' ,'NOT ALLOCATED'))AA";
        		String query="SELECT AA.deviceid ,AA.make,AA.allocatedflag,(SELECT mrcode FROM vcloudengine.deviceallocation WHERE deviceid=AA.deviceid) FROM(SELECT * FROM vcloudengine.devicemaster WHERE sdocode='"+sdocode+"' AND allocatedflag IN ('ALLOCATED' ,'NOT ALLOCATED'))AA ORDER BY mrcode";
        		BCITSLogger.logger.info("=============================>query"+query);
       		 	list=entityManager.createNativeQuery(query).getResultList();
        		
        		for(Object[] obj:list)
        		{
					map=new HashMap<String, Object>();
					map.put("deviceid", obj[0]);
					map.put("make", obj[1]);
					//map.put("mrcode", obj[2]);
					map.put("allocatedflag", obj[2]);
					map.put("mrcode", obj[3]);
					result.add(map);
        		}
        		
        		
        	}
		} 
         catch (Exception e) 
		{
			e.printStackTrace();
		}
		return result;
	}
	
	
	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<String> getMatchedDeviceIds(Integer sdoCode) {
		return entityManager
				.createNamedQuery("MRDeviceEntity.findMatchedDeviceIds")
				.setParameter("sdoCode", sdoCode).getResultList();

	}

	@Override
	public List<String> getMatchedDeviceIdsForAllocation(Integer sdoCode) {

		/*return entityManager
				.createNamedQuery(
						"MRDeviceEntity.getMatchedDeviceIdsForAllocation")
				.setParameter("sdoCode", sdoCode).getResultList();*/
		return entityManager
				.createNamedQuery(
						"MRDeviceEntity.getMatchedDeviceIdsForAllocation")
				.getResultList();
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public int approveDevice(String deviceid,HttpServletRequest request,ModelMap model) {
		int res= entityManager.createNamedQuery("MRDeviceEntity.ApproveDevice").setParameter("deviceid", deviceid).executeUpdate();
		if(res>0)
		{
			model.put("results", "Device Approved succesfully");
		}
		else {
			model.put("results", "Device not Approved");
		}
		model.put("mrdList",findAllMRDevices(request));		
		model.put("makeConstrts",getCheckConstraints(
				"devicemaster", "make_check", request));
		
		model.put("activeModules",
				moduleMasterService.findAllActivatedModuleMasters());
		return res;

	}
	

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public String findByDevice(String deviceid ) {
		Object object = null ;
try {
	 object = entityManager.createNamedQuery("MRDeviceEntity.findByDevice").setParameter("deviceid", deviceid).getSingleResult();
	//BCITSLogger.logger.info(" >>>>>>>>>>>>>>>> "+object.toString());
} catch (Exception e) {
	e.printStackTrace();
	//BCITSLogger.logger.info(" >>>>>>>>>>>>>>>> "+object.toString());
}
		
		return object.toString();
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void updateStatus(String deviceid, String status) {
		entityManager.createNamedQuery("MRDeviceEntity.updateStatus").setParameter("deviceid", deviceid).setParameter("status",status).executeUpdate();
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public int updateDeviceSdocode(String deviceid, Integer sdoCode,
			String allostatus)
	{
		int val=entityManager.createNamedQuery("MRDeviceEntity.updateDeviceSdocode").setParameter("deviceid", deviceid).setParameter("allostatus",allostatus).executeUpdate();
		return val;
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public int updateDeviceMaster(String deviceidPk)
	{
		BCITSLogger.logger.info("==============>COMINFG INSIDE----------"+deviceidPk);
		int val=entityManager.createNamedQuery("MRDeviceEntity.updateDeviceMaster").setParameter("deviceid", deviceidPk.trim()).setParameter("allostatus","NOT ALLOCATED").executeUpdate();
		return val;
	}
	
	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public int findByPk(String deviceidPk)
	{
		int val=0;
		try
		{
			 val=(Integer) entityManager.createNamedQuery("MRDeviceEntity.findByPk").setParameter("deviceid", deviceidPk.trim()).getSingleResult();
			 BCITSLogger.logger.info("==============>valvalvalvalval----------"+val);
		}
		catch(Exception e)
		{
			return val;
		}
		return val;
	}
	
	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<MRDeviceEntity> getNotAllocatedDevice(int sdocode) 
	{
		BCITSLogger.logger.info("==============>COMINFG sdocode----------"+sdocode);
		return entityManager.createNamedQuery("MRDeviceEntity.GetNotAllocatedDevice").setParameter("sdoCode", sdocode).getResultList();
		
	}
	
	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Object[]> getDivisionMobCount(ModelMap model)
	{
		 List<Object[]> list=null;
		 String query="SELECT AA.DIVISIONNAME, COALESCE(BB.devcount,0) FROM"
+"(SELECT DISTINCT division as DIVISIONNAME FROM vcloudengine.location)AA"+" "
+"FULL JOIN"
+"(SELECT  lc.division as devdiv,  count(dms.deviceid) as devcount FROM vcloudengine.location lc, vcloudengine.devicemaster dms"+" "
+"WHERE lc.sitecode=dms.sdocode AND lc.sitecode!=2000 GROUP BY lc.division"
+")BB ON AA.DIVISIONNAME=BB.devdiv ORDER BY AA.DIVISIONNAME";
		 BCITSLogger.logger.info("=============================>query"+query);
		 list=entityManager.createNativeQuery(query).getResultList();
		 for (int i = 0; i < list.size(); i++)
		 {
			Object[] data=list.get(i);
			for (int j = 0; j < data.length; j++) 
			{
				if(i==0)
				{
					if(j==1)
					{
						model.put("bellary", data[j]);
					}
				}
				if(i==1)
				{
					if(j==1)
					{
						model.put("gangavathi", data[j]);
					}
				}
				if(i==2)
				{
					if(j==1)
					{
						model.put("hospet", data[j]);
					}
				}
				
				if(i==3)
				{
					if(j==1)
					{
						model.put("koppal", data[j]);
					}
				}
				if(i==4)
				{
					if(j==1)
					{
						model.put("raichur", data[j]);
					}
				}
				if(i==5)
				{
					if(j==1)
					{
						model.put("sindhanoor", data[j]);
					}
				}
				if(i==6)
				{
					if(j==1)
					{
						model.put("testLocation1", data[j]);
					}
				}
				if(i==7)
				{
					if(j==1)
					{
						model.put("testLocation2", data[j]);
					}
				}
				
			}
		 }
		return list;
	}
	
	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Object[]> getDivisionMobiles(String division, ModelMap model)
	{
		 List<Object[]> list=null;
		 String query="SELECT lc.section, lc.sitecode,count(dms.deviceid)FROM vcloudengine.location lc,vcloudengine.devicemaster dms where"+" "
      +"lc.sitecode=dms.sdocode AND upper(lc.division) Like '"+division.toUpperCase()+"' GROUP BY lc.section ,lc.sitecode";
		 try
		 {
			 list=entityManager.createNativeQuery(query).getResultList();
		} 
		 catch (Exception e)
		{
			e.printStackTrace();
		}
		 return list;
	}
	
	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Object[]> getSectionMobiles(String sitecode, ModelMap model)
	{
		 List<Object[]> list=null;
		 String query="SELECT dms.deviceid,dms.gprssimno,dms.make FROM vcloudengine.location lc,vcloudengine.devicemaster dms where"+" "
+"lc.sitecode=dms.sdocode AND cast(lc.newsitecode as varchar) like '"+sitecode+"'";
		 try
		 {
			 list=entityManager.createNativeQuery(query).getResultList();
		} 
		 catch (Exception e)
		{
			e.printStackTrace();
		}
		 return list;
	}
	
	
	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Object[]> getDivisionMobCountCesc(ModelMap model)
	{
		 List<Object[]> list=null;
		 List<String> colourList=new ArrayList<String>();
		 List<Object[]> list1=new ArrayList<Object[]>();
		 String[] color={"yellow","green","purple","red","blue"};
		 /*String query="SELECT AA.DIVISIONNAME, AA.sitecode1,COALESCE(BB.devcount,0) FROM"+" "
+"(SELECT division as DIVISIONNAME,substr(cast(sitecode as varchar),0,3)as sitecode1 FROM vcloudengine.location"+" " 
+"GROUP BY substr(cast(sitecode as varchar),0,3),division ORDER BY substr(cast(sitecode as varchar),0,3))"+" "
+"AA "+" "
+"FULL JOIN"+" "
+"("+" "
+"SELECT  lc.division as devdiv, substr(cast(lc.sitecode as varchar),0,3)as sitecode2, count(dms.deviceid) as devcount FROM vcloudengine.location lc,"+" " 
+"vcloudengine.devicemaster dms WHERE lc.sitecode=dms.sdocode GROUP BY lc.division,substr(cast(lc.sitecode as varchar),0,3) "+" "
+"ORDER BY substr(cast(lc.sitecode as varchar),0,3) "+" "
+")BB "+" "
+"ON AA.DIVISIONNAME=BB.devdiv AND AA.sitecode1=BB.sitecode2 ORDER BY AA.sitecode1";*/
		 String query="SELECT  lc.division as devdiv, " +
				 "substr(cast(lc.newsitecode as varchar),0,3)as sitecode1,  " +
				 "count(dms.deviceid) as devcount FROM vcloudengine.location lc,  " +
				 "vcloudengine.devicemaster dms WHERE lc.sitecode=dms.sdocode  " +
				 "GROUP BY lc.division,substr(cast(lc.newsitecode as varchar),0,3) " +
				 " ORDER BY substr(cast(lc.newsitecode as varchar),0,3)";
		 
		 BCITSLogger.logger.info("=============================>query"+query);
		 list=entityManager.createNativeQuery(query).getResultList();
		 model.put("cescdashList", list);
		 colourList.add("green");
		 colourList.add("purple");
		 colourList.add("red");
		 colourList.add("yellow");
		 colourList.add("blue");
		 model.put("colourList", colourList);
		 int countColor=0;
		 int count1=0;
		 int totMobile=0;
		 for (int i = 0; i < list.size(); i++)
		 {
			    Object[] data=list.get(i);
				Object[] newArr = Arrays.copyOf(data, data.length+1);
			          for (int j = 0; j < newArr.length; j++) 
			            {
							 if(j==0)
							 {
								 totMobile=totMobile+Integer.parseInt(newArr[2].toString());
							 }
							 if(j==3)
							 {
									 newArr[j]=color[countColor];
									 if(countColor<color.length)
									 {
										 countColor=countColor+1;
									 }
							 }
				        }
			          if(countColor==color.length)
			          {
			        	  countColor=0;  
			          }
			          
				list1.add(newArr);
		}
		 
		 model.put("cescdashList", list1);
		 model.put("totMobile", totMobile);
		 
		return list1;
	}
	
	
	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Object[]> getDivisionMobilesCesc(String division, String sitecode,ModelMap model)
	{
		 List<Object[]> list=null;
		 String query="SELECT lc.section, lc.newsitecode,count(dms.deviceid)FROM vcloudengine.location lc,vcloudengine.devicemaster dms where"+" "
      +"lc.sitecode=dms.sdocode AND upper(lc.division) Like '"+division.toUpperCase()+"' and substr(cast(lc.newsitecode as varchar),0,3)like '"+sitecode+"' GROUP BY lc.section ,lc.newsitecode";
		BCITSLogger.logger.info("queryqueryquery"+query);
		 try
		 {
			 list=entityManager.createNativeQuery(query).getResultList();
		} 
		 catch (Exception e)
		{
			e.printStackTrace();
		}
		 return list;
	}
	
	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public String findByDeviceId(String deviceid ) 
	{
		Object object = null ;
try {
	 object = entityManager.createNamedQuery("MRDeviceEntity.findByDevice").setParameter("deviceid", deviceid).getSingleResult();
	//BCITSLogger.logger.info(" >>>>>>>>>>>>>>>> "+object.toString());
} catch (Exception e)
{
	//e.printStackTrace();
	return "noData";
	//BCITSLogger.logger.info(" >>>>>>>>>>>>>>>> "+object.toString());
}
		
		return "DataExists";
	}
	
	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<String> getDeviceFromMobileMaster(String provider)
	{
		List<String> list=null;
	/*	String sql="SELECT deviceid1 FROM vcloudengine.mobilemaster WHERE deviceid1 NOT IN " +
				"( " +
				"SELECT deviceid FROM vcloudengine.devicemaster " +
				") AND deviceid2 NOT IN " +
				"( " +
				"SELECT deviceid FROM vcloudengine.devicemaster " +
				") AND provider='"+provider+"' ORDER BY deviceid1;";*/
		String sql="SELECT deviceid1 FROM vcloudengine.mobilemaster WHERE  provider='"+provider+"' ORDER BY deviceid1";
		 list=entityManager.createNativeQuery(sql).getResultList();
		return list;
	}
	
	
	
}
