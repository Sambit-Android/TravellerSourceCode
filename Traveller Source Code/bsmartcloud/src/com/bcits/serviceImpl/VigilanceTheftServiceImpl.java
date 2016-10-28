package com.bcits.serviceImpl;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

import com.bcits.entity.VigilanceTheftEntity;
import com.bcits.service.VigilanceTheftService;
import com.bcits.utility.BCITSLogger;

@Repository
public class VigilanceTheftServiceImpl extends GenericServiceImpl<VigilanceTheftEntity> implements VigilanceTheftService
{

	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public void getTheftData(ModelMap model,HttpServletRequest request)
	{
		List<VigilanceTheftEntity> theftList=findAll();
		model.put("theftList", theftList);
		List<String> statusList=getCheckConstraints("vigilancetheft", "STATUS_CHECK",request);
		model.put("statusList", statusList);
	}
	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<VigilanceTheftEntity> findAll() 
	{
		return entityManager.createNamedQuery("VigilanceTheftEntity.findAll").getResultList();
	}
	
	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public byte[] findOnlyImage(ModelMap model,HttpServletRequest request,HttpServletResponse response,String consumer_Sc_No,String value) throws IOException 
	{
		int k=0;
		int t=0;
		List<VigilanceTheftEntity> hh=  entityManager.createNamedQuery("VigilanceTheftEntity.findOnlyImage").setParameter("consNo",consumer_Sc_No).getResultList();
		//model.put("imageees", hh);
		response.setContentType("image/jpeg/png");
    	byte bt[] = null;
    	OutputStream ot = null;
    	ot = response.getOutputStream();	
    	if(hh.size()>0)
    	{
    		if(value.equals("1"))
    		{
    			if(hh.get(0).getImage()!=null)
    			{
    				System.out.println("IMAGE IS NULL");
    			}
    			bt=hh.get(0).getImage();
    		}
    		if(value.equals("2"))
    		{
    			bt=hh.get(0).getImage_two();
    		}
    		if(value.equals("3"))
    		{
    			bt=hh.get(0).getImage_three();
    		}
        		ot.write(bt);
            	ot.close();
    	}
       Base64 b=new Base64();
       b.encodeBase64(bt);
		return b.encodeBase64(bt);
			    	
    }

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public int updateStatus(int id, String status) 
	{
		Query query=entityManager.createNamedQuery("VigilanceTheftEntity.updateStatus").setParameter("id", id).setParameter("status", status);
		int updateData=query.executeUpdate();
		BCITSLogger.logger.info("No. of records updated is"+"\t"+updateData);
		return updateData;
	}

	
	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<VigilanceTheftEntity> findByUser(String username) 
	{
		return entityManager.createNamedQuery("VigilanceTheftEntity.findByUser").setParameter("username", username).getResultList();
	}
	
	/*@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public void updateStatus() 
	{
		int sdocode=Integer.parseInt(sdoCode);
		int billmonth=Integer.parseInt(billMonth);
		Query query=entityManager.createNamedQuery("BillingDataEntity.updateDatetime").setParameter("sdocode", sdocode).setParameter("accNo", consumerNo).setParameter("billmonth", billmonth).setParameter("billissuedate", billissuedate);
	    int updateData=query.executeUpdate();
	    BCITSLogger.logger.info("No. of records updated is"+"\t"+updateData);
	}*/
}
