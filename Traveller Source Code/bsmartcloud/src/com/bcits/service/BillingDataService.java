package com.bcits.service;


import java.util.Date;
import java.util.List;

import com.bcits.entity.BillingDataEntity;


public interface BillingDataService extends GenericService<BillingDataEntity>
{
	List<BillingDataEntity> getBillingData(String searchData,String colvalue,String sdoCode);	
	List<BillingDataEntity> getBillingDataAlert(String sdoCode,String consumerNo);	
	void updateDateTime(String sdocode,String accno,String billMonth,String billissueDate,String dateTime);
}
