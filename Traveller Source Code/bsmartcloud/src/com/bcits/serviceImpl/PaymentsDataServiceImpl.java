package com.bcits.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bcits.entity.PaymentsDataEntity;
import com.bcits.service.PaymentsDataService;
import com.bcits.utility.BCITSLogger;

@Repository
public class PaymentsDataServiceImpl extends GenericServiceImpl<PaymentsDataEntity> implements PaymentsDataService
{

	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<PaymentsDataEntity> getPaymentsData(String searchData,String colValue,String sdoCode,String paymentMode) 
	{
		
		if(paymentMode.equalsIgnoreCase("ALL"))
		{
			paymentMode="%";
		}
		if(sdoCode!="")
		{
			int sdocode=Integer.parseInt(sdoCode);
			if(colValue.equals("1"))
			   {
				   return entityManager.createNamedQuery("PaymentsDataEntity.findByCnoSdo").setParameter("searchData", searchData).setParameter("sdoCode", sdocode).setParameter("modeOfPayment", paymentMode).getResultList();
			   }
			   else if(colValue.equals("2"))
			   {
				   return entityManager.createNamedQuery("PaymentsDataEntity.findByRnoSdo").setParameter("searchData", searchData).setParameter("sdoCode", sdocode).setParameter("modeOfPayment", paymentMode).getResultList();
			   }
			   else if(colValue.equals("3"))
			   {
				   double amount=Double.parseDouble(searchData);
				   return entityManager.createNamedQuery("PaymentsDataEntity.findByAmntSdo").setParameter("searchData", amount).setParameter("sdoCode", sdocode).setParameter("modeOfPayment", paymentMode).getResultList();
			   }
		}
		else
		{
			if(colValue.equals("1"))
			   {
				   return entityManager.createNamedQuery("PaymentsDataEntity.findByConsumerNo").setParameter("searchData", searchData).setParameter("modeOfPayment", paymentMode).getResultList();
			   }
			   else if(colValue.equals("2"))
			   {
				   return entityManager.createNamedQuery("PaymentsDataEntity.findByReceiptNo").setParameter("searchData", searchData).setParameter("modeOfPayment", paymentMode).getResultList();
			   }
			   else if(colValue.equals("3"))
			   {
				   double amount=Double.parseDouble(searchData);
				   return entityManager.createNamedQuery("PaymentsDataEntity.findByAmount").setParameter("searchData", amount).setParameter("modeOfPayment", paymentMode).getResultList();
			   }
/*			   else if(colValue.equals("4"))
			   {
				   BCITSLogger.logger.info("-=-=-=colValue-=-=-=-=-=-=-=-=>"+colValue+"----"+searchData);
				   return entityManager.createNamedQuery("PaymentsDataEntity.findBypaymentMode").setParameter("modeOfPayment", searchData).getResultList();
			   }
*/		}
		return null;
	}
}
