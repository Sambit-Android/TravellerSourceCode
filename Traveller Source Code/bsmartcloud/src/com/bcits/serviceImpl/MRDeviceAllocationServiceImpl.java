package com.bcits.serviceImpl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

import com.bcits.entity.MRDeviceAllocationEntity;
import com.bcits.service.MRDeviceAllocationService;
import com.bcits.utility.BCITSLogger;

@Repository
public class MRDeviceAllocationServiceImpl extends
		GenericServiceImpl<MRDeviceAllocationEntity>
		implements MRDeviceAllocationService {

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<MRDeviceAllocationEntity> findAllMRDeviceAllocations(HttpServletRequest request,ModelMap model) 
	{
		List<MRDeviceAllocationEntity> list=null;
         String sdoCode=(String) request.getSession().getAttribute("sdocodeval");
         String billMonth=(String) request.getSession().getAttribute("currentMonth");
         List countList=new ArrayList();
         BCITSLogger.logger.info("=fff======sdoCode--"+sdoCode);
         if(sdoCode==null)
         {
        	 sdoCode="%";
        	 list=entityManager.createNamedQuery("MRDeviceAllocationEntity.findAll",MRDeviceAllocationEntity.class).setParameter("sdoCode", sdoCode).getResultList();
         }
         else
         {
        	 BCITSLogger.logger.info("=======sdoCode--"+sdoCode);
        	 list=entityManager.createNamedQuery("MRDeviceAllocationEntity.findAll",MRDeviceAllocationEntity.class).setParameter("sdoCode", sdoCode).getResultList();
        	 BigInteger count=null;
             for (MRDeviceAllocationEntity mrDeviceAllocationEntity : list)
             {
           	  String sql="SELECT count(*) FROM photobilling.hhbm_consumers WHERE bill_month='"+billMonth+"' AND sdo_code like '"+mrDeviceAllocationEntity.getSdoCode()+"' AND bmd_reading like '"+mrDeviceAllocationEntity.getMrCode()+"'";
           	  count= (BigInteger) entityManager.createNativeQuery(sql).getSingleResult();
           	  countList.add(count);
             }
             model.put("consumersCount", countList);
         }
          
          return list;
	}
	
	
	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<MRDeviceAllocationEntity> findAllMRDeviceAllocations1(HttpServletRequest request,String sdocode,ModelMap model) 
	{
		return entityManager.createNamedQuery("MRDeviceAllocationEntity.findAll",MRDeviceAllocationEntity.class).setParameter("sdoCode", sdocode).getResultList();
	}
	
	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public boolean checkAllocated(HttpServletRequest request,String mrcode,String deviceid) {

		long res=(Long)entityManager.createNamedQuery("MRDeviceAllocationEntity.checkAllocated").setParameter("deviceid",deviceid).setParameter("mrcode",mrcode).getSingleResult();
	    
		if(res>0)
	    {
	    	return true;
	    }
	    else {
			return false;
		}
		
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<MRDeviceAllocationEntity> findSdoCodeByMrcode(String mrCode) 
	{
	//	return entityManager.createNamedQuery("MRCreditMasterEntity.findAllByMrcode").setParameter("mrCode", mrCode).setParameter("sdoCode", sdoCode).getResultList();

		return entityManager.createNamedQuery(
				"MRDeviceAllocationEntity.findSdoCodeByMrcode").setParameter("mrCode", mrCode).getResultList();
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Object> findBySdoCode(String sdoCode) 
	{
		List<Object> list=null;
		try 
		{
			list=entityManager.createNamedQuery("MRDeviceAllocationEntity.findBySdoCode").setParameter("sdoCode", Integer.parseInt(sdoCode)).getResultList();
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		return list;
	} 
	
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public int deleteDevice(String deviceid)
	{
		int val=0;
		try 
		{
			BCITSLogger.logger.info("=======================>deviceid"+deviceid);
			 val=entityManager.createQuery("DELETE FROM MRDeviceAllocationEntity m where m.deviceid=:deviceid").setParameter("deviceid", deviceid.trim()).executeUpdate();
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		return val;
	} 
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<Object[]> getDeviceWiseReport(ModelMap model, String currentMonth)
	{

		List list=null;
		
			String sql="SELECT ROW_NUMBER() OVER (ORDER BY AA.tBillIssued),(SELECT section FROM vcloudengine.location WHERE cast(sitecode as varchar)=AA.sitecode),AA.devid,AA.tBillIssued AS NoofBillsIssued,AA.noofdays as NoOfDaysBilled"+" "
					+ ",(AA.tBillIssued/AA.noofdays)as AvgBillPerDay , AA.sitecode"+" " 
 +"FROM(SELECT hhd.device_id as devid ,count(DISTINCT hhd.consumer_sc_no)as tBillIssued, count(DISTINCT (to_date(hhd.billdate, 'dd/MM/yyyy')))as noofdays,"+" " 
+"hhd.sdo_code as sitecode,ROW_NUMBER() OVER (ORDER BY count(DISTINCT hhd.consumer_sc_no))"+" "
+"FROM  photobilling.hhbm_download hhd, vcloudengine.location lct "+" "
+"WHERE  cast(lct.sitecode as varchar)=hhd.sdo_code AND  hhd.sdo_code not like '2000' and hhd.bill_month='"+currentMonth+"'"+" "
 +" GROUP BY hhd.device_id,hhd.sdo_code ORDER BY count(DISTINCT hhd.consumer_sc_no))AA";
			BCITSLogger.logger.info("==================>sql"+sql);
			list=entityManager.createNativeQuery(sql).getResultList();
			model.put("deviceWiseReport",list);
			model.put("currentMonth",currentMonth);
			if(list.size()==0)
			{
				model.put("results","No Data is Available for the selected month.");
			}
			else
			{
				model.put("results","notDisplay");
			}
		
	
		return list;
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<MRDeviceAllocationEntity> findByDeviceId(String deviceid)
	{

		List<MRDeviceAllocationEntity> list=null;
		try 
		{
			list=entityManager.createQuery("Select m from MRDeviceAllocationEntity m where m.deviceid=:deviceid").
			setParameter("deviceid", deviceid).getResultList();
		} 
		catch (Exception e)
		{
			BCITSLogger.logger.info("Error while processing............."+e.toString());
		}
		return list;
	}
}
