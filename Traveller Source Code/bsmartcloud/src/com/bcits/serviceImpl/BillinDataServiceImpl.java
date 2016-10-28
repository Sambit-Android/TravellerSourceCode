package com.bcits.serviceImpl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bcits.entity.BillingDataEntity;
import com.bcits.service.BillingDataService;
import com.bcits.utility.BCITSLogger;

@Repository
public class BillinDataServiceImpl extends GenericServiceImpl<BillingDataEntity> implements BillingDataService
{

	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<BillingDataEntity> getBillingData(String searchData,String colValue,String sdoCode) 
	{
		if(sdoCode!="")
		{
			int sdocode=Integer.parseInt(sdoCode);
			if(colValue.equals("1"))
			   {
				   return entityManager.createNamedQuery("BillingDataEntity.findAcSdo").setParameter("searchData", searchData).setParameter("sdoCode", sdocode).getResultList();
			   }
			else if(colValue.equals("2"))
			   {
				   return entityManager.createNamedQuery("BillingDataEntity.findByBnoSdo").setParameter("searchData", searchData).setParameter("sdoCode", sdocode).getResultList();
			   }
			else if(colValue.equals("3"))
			   {
				   double amount=Double.parseDouble(searchData);
				   return entityManager.createNamedQuery("BillingDataEntity.findByAmntSdo").setParameter("searchData", amount).setParameter("sdoCode", sdocode).getResultList();
			   }
		}
		else
		{
			   if(colValue.equals("1"))
			   {
				   return entityManager.createNamedQuery("BillingDataEntity.findByAccNo").setParameter("searchData", searchData).getResultList();
			   }
			   else if(colValue.equals("2"))
			   {
				   return entityManager.createNamedQuery("BillingDataEntity.findByBillNo").setParameter("searchData", searchData).getResultList();
			   }
			   else if(colValue.equals("3"))
			   {
				   double amount=Double.parseDouble(searchData);
				   return entityManager.createNamedQuery("BillingDataEntity.findByBillAmnt").setParameter("searchData", amount).getResultList();
			   }
		}
		return null;
	}

	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<BillingDataEntity> getBillingDataAlert(String sdoCode,String consumerNo) 
	{
		int sdocode=Integer.parseInt(sdoCode);
		return entityManager.createNamedQuery("BillingDataEntity.findBillData").setParameter("sdocode", sdocode).setParameter("accNo", consumerNo).getResultList();
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void updateDateTime(String sdoCode,String consumerNo,String billMonth,String billissuedate,String dateString) 
	{
		int sdocode=Integer.parseInt(sdoCode);
		int billmonth=Integer.parseInt(billMonth);
		Query query=entityManager.createNamedQuery("BillingDataEntity.updateDatetime").setParameter("sdocode", sdocode).setParameter("accNo", consumerNo).setParameter("billmonth", billmonth).setParameter("billissuedate", billissuedate).setParameter("dateString", dateString);
	    int updateData=query.executeUpdate();
	    BCITSLogger.logger.info("No. of records updated is"+"\t"+updateData);
	}
}
