package com.bcits.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

import com.bcits.entity.LocationEntity;
import com.bcits.service.LocationService;
import com.bcits.utility.BCITSLogger;


@Repository
public class LocationServiceImpl extends GenericServiceImpl<LocationEntity> implements LocationService 
{
	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<LocationEntity> findAll() 
	{
		List<LocationEntity> list=null;
		try
		{
			list= entityManager.createNamedQuery("LocationEntity.All").getResultList();
			System.out.println("data is coming for admin"+list.size());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
			return list;
	}
	
	/*@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<LocationEntity> getAllSiteCodes() 
	{
		List<LocationEntity> list=null;
		try 
		{
			list= entityManager.createNamedQuery("LocationEntity.findAllSDOCodes").getResultList();
			
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return list;
		
	}*/
	
	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public void getLocationData(ModelMap model,HttpServletRequest request) 
	{
		try
		{
			  List<LocationEntity> locationList=findAll();		  
			  model.addAttribute("locationList",locationList);
			  List<String>locationLevel =getCheckConstraints("LOCATIONS", "levels_check", request);	
			  model.put("levelSize", locationLevel.size());
			  List<String> locationList2=getCheckConstraints("LOCATIONS", "locationtype_check", request);
			  List<String> zone =getCheckConstraints("LOCATIONS", "zone_check", request);
			  List<String> company =getCheckConstraints("LOCATIONS", "company_check", request);
			  model.addAttribute("LocationTypes", locationList2);
			  model.addAttribute("LocationLevel", locationLevel);
			  model.addAttribute("companyList", company);
			  model.addAttribute("zoneList", zone);
			  
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		  
	}
	
	@Transactional(propagation=Propagation.SUPPORTS)
	public int getLocationCode(int level,int digitsCode)
	{
		/*List codeValue=entityManager.createNamedQuery("LocationEntity.code").setParameter("level", level).getResultList();
		int code=0;
		if(codeValue.size()>0)
		{
			 code=(int) codeValue.get(0);
		}		
		return code;*/
		BCITSLogger.logger.info("DATA COMING------------------------>"+level+"  "+digitsCode);
		try {			
			String sql="select locationcode FROM vcloudengine.Locations l where levels='"+level+"' AND cast(locationcode as varchar) LIKE '"+digitsCode+"%' ORDER BY locationcode DESC";
			List codeValue = entityManager.createNativeQuery(sql).getResultList();
			BCITSLogger.logger.info("CODE VALUE---------------------> "+codeValue);

			int code=0;
			if(codeValue.size()>0)
			{
				code=(Integer) codeValue.get(0);
			}		
			return code;			
			
		} catch (RuntimeException re) 
		{
			re.printStackTrace();
			throw re;
		}
	}
	
	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<LocationEntity> getLocationData(int locationcode,ModelMap model) 
	{
		List<LocationEntity> entityList=null;
		try
		{
			entityList= entityManager.createNamedQuery("LocationEntity.findByLocationCode").setParameter("locationcode", locationcode+"%").getResultList();
			/*for (int i = 0; i < entityList.size(); i++)
		     {
		    	  model.put("place", entityList.get(i).getLocationname());
		     }*/
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return entityList;
	}
	
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<LocationEntity> getLocationNamesForLevels(String locType,int level,int digitCode)
	{	
		int locCode=(level)*2;
		List<LocationEntity> list=null;
    try {
    	Query query = entityManager.createNamedQuery("LocationEntity.locationNames").setParameter("locCode", locCode).setParameter("level", level-1+"").setParameter("digits", digitCode+"%");
    	 list=query.getResultList();
		} catch (RuntimeException re) 
		{
			re.printStackTrace();
			throw re;
		}
	return list;
	}
	
	
	@Transactional(propagation=Propagation.SUPPORTS)
	public void getTopMostLevelData(HttpServletRequest request,ModelMap model) 
	{
		 //String locationList3 =getCheckConstraints("LOCATIONS", "LOCCATEGORY_CHECK");
		 //model.addAttribute("LocationCategy", locationList3);
		 List list=entityManager.createNamedQuery("LocationEntity.topMost").getResultList();	
		 model.put("topMost", list.get(0));
		 //BCITSLogger.logger.info("values are "+list.get(0));
		 
	}
	
	@Transactional(propagation=Propagation.SUPPORTS)
	public List getChildNodes(String locationCode,String region,int level) 
	{	
		
		final String queryStr = "SELECT l.locationcategory,l.locationcode,l.level,l.locationname FROM LocationEntity l WHERE l.locationcode LIKE '"+locationCode+"__' AND l.locationcategory LIKE '"+region+"'" ;
		Query query = entityManager.createQuery(queryStr);
		List alist=query.getResultList();
		return alist;
		
	}
	
	@Transactional(propagation=Propagation.SUPPORTS)
	public List getZerothNodeAndRegion(String locationCode) 
	{
		
		final String queryStr = "SELECT DISTINCT(l.locationcategory) FROM LocationEntity l WHERE l.locationcode LIKE '"+locationCode+"__'";
		Query query = entityManager.createQuery(queryStr);
		List alist=query.getResultList();
		return alist;
		
	}
	
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<LocationEntity> getLocationNamest(int digitsCode,String category)
	{
		digitsCode=digitsCode+1;
		return entityManager.createNamedQuery("LocationEntity.locationNames").setParameter("level",digitsCode).setParameter("category",category).getResultList();
		
	}

	
	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public void getLocationCategory(ModelMap model) 
	{
		//String locationList3 =getCheckConstraints("LOCATIONS", "LOCCATEGORY_CHECK");
		 //model.addAttribute("LocationCategory", locationList3);
	}

	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<LocationEntity> getLocationNames(String locationCategory,ModelMap model) 
	{
		BCITSLogger.logger.info("valuee "+locationCategory);
		if(locationCategory.equals("0"))
		{
			locationCategory="%";
		}
		List<LocationEntity> locationList= entityManager.createNamedQuery("LocationEntity.getRegions").setParameter("locationCategory",locationCategory).getResultList();		
		return locationList;
	}

	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<String> getLocationTypes(HttpServletRequest request) 
	{
		List<String> locationList2=null;
		try
		{
			 locationList2=getCheckConstraints("LOCATIONS", "locationtype_check", request);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return locationList2;
	}

	
	
	/*@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<LocationEntity> findLocName(String locationName,int level) 
	{
		List<LocationEntity> entityList=null;
		try
		{
			if(level==1)
			{
				entityList= entityManager.createNamedQuery("LocationEntity.findLocName").setParameter("locationName", locationName).getResultList();
			}
			else if(level==2)
			{
				entityList= entityManager.createNamedQuery("LocationEntity.findLevel2Location").setParameter("locationName", locationName).setParameter("level2", level).getResultList();
			}
			else if(level==3)
			{
				//entityList= entityManager.createNamedQuery("LocationEntity.findLocName").setParameter("locationName", locationName).getResultList();
				entityList= entityManager.createNamedQuery("LocationEntity.findLevel3Location").setParameter("locationName", locationName).setParameter("level3", level).getResultList();
			}
			else if(level==4)
			{
				//entityList= entityManager.createNamedQuery("LocationEntity.findLocName").setParameter("locationName", locationName).getResultList();
				entityList= entityManager.createNamedQuery("LocationEntity.findLevel4Location").setParameter("locationName", locationName).setParameter("level4", level).getResultList();
			//}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return entityList;
	}*/
	
	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<LocationEntity> findLocName(String locationName,int loccode,int level) 
	{
		List<LocationEntity> entityList=null;
		 String queryStr =null;
		try
		{     if(level==1)
			{
			     queryStr = "SELECT e FROM LocationEntity e WHERE UPPER(e.locationname)='"+locationName+"' AND e.level='"+level+"'";
			}
			else
			{
				BCITSLogger.logger.info("DATA COMING--------->"+locationName+" "+loccode+"  "+level);
				  queryStr = "SELECT e FROM LocationEntity e WHERE UPPER(e.locationname)='"+locationName+"' AND cast(e.locationcode as string) LIKE '"+loccode+"%' AND e.level="+level+"";
			}
				
				Query query = entityManager.createQuery(queryStr);
				List alist=query.getResultList();
				return alist;
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return entityList;
	}

	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<LocationEntity> findSiteCode(int operation,int level) 
	{
		
		List<LocationEntity> entityList=null;
		try
		{
			entityList= entityManager.createNamedQuery("LocationEntity.findSiteCode").setParameter("siteCode", operation).setParameter("level4", level).getResultList();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return entityList;
	}
	
	
	
	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<LocationEntity> getAllSdoLevel4(HttpServletRequest request) 
	{
		List<LocationEntity> locationList2=null;
		try
		{
			locationList2=entityManager.createNamedQuery("LocationEntity.GetAllSdoLevel4").getResultList();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return locationList2;
	}

	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<LocationEntity> checkLevelLocation(int locationcode) 
	{
		List<LocationEntity> locationList2=null;
		try
		{
			locationList2=entityManager.createNamedQuery("LocationEntity.findLevelLocation").setParameter("locationcode", locationcode+"%").getResultList();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return locationList2;
		
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public int deleteLocation(String locCode) 
	{
		int deleteCount=0;
		try
		{
			Query query=entityManager.createQuery("DELETE from LocationEntity l WHERE  cast(l.locationcode as string)LIKE :locationCode");
			 deleteCount=query.setParameter("locationCode", locCode+"%").executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return deleteCount;
	}

	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<LocationEntity> findSdoAndSubdiv() {
		
		List<LocationEntity> locationList2=null;
		try
		{
			locationList2=entityManager.createNamedQuery("LocationEntity.findSdoAndSubDiv").getResultList();
	  
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return locationList2;
	}

	@Override
	public List<LocationEntity> findSubdiv() {
		

		List<LocationEntity> locationList2=null;
		try
		{
			locationList2=entityManager.createNamedQuery("LocationEntity.findSubDiv").getResultList();
		    System.out.println("data twi"+locationList2);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return locationList2;
	}
	
}
