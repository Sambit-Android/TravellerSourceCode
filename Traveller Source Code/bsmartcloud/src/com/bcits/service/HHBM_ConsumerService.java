package com.bcits.service;

import java.util.List;

import org.springframework.ui.ModelMap;

import com.bcits.entity.HHBM_ConsumerEntity;

public interface HHBM_ConsumerService extends GenericService<HHBM_ConsumerEntity>
{
	List<Object> getReportDetails(String sdoCode,String billMonth,String reportName,ModelMap model);

	public List<String> findAllSdoCodes();

	public List<HHBM_ConsumerEntity> findAll();

	public List<HHBM_ConsumerEntity> findMatchedData();

	// HHBM_ConsumerEntity.findMatchedBillMonths
	public List<String> findMatchedBillMonths(String sdoCode);

	// HHBM_ConsumerEntity.findMatchedBmdReading
	public List<String> findMatchedBmdReading(String sdoCode, String billMonth);

	// HHBM_ConsumerEntity.getTotalConnections
	public String getTotalConnections(String sdoCode, String billMonth,
			String bmdReading);

	// HHBM_ConsumerEntity.getTotalBilled
	public String getTotalBilled(String sdoCode, String billMonth,
			String bmdReading);

	// HHBM_ConsumerEntity.getTotalDownloaded
	public String getTotalDownloaded(String sdoCode, String billMonth,
			String bmdReading);

	// HHBM_ConsumerEntity.updateDownStatus
	public Integer updateDownStatus(HHBM_ConsumerEntity hhbm_consumerentity);
	
	//search for report
	
	public List  serachCriteriaOne(HHBM_ConsumerEntity hhbm_consumerentity,String group,ModelMap model);

	public List<Object[]>  getConsumersArrear(String sdocode, String billmonth,
			String arrearrange, ModelMap model);
	
	//public List  serachCriteriafour(HHBM_ConsumerEntity hhbm_consumerentity,ModelMap model);
	
	List<?> getConsumersArrearMobile(String sdocode, String billmonth,String arrearrange);

	List<Object[]> showMrDaywiseBySection(String siteCode, String billMonth,ModelMap model);

	List<Object[]> mrPerformanceView(String sitecode, String billmonth,
			ModelMap model);

	List<?> mrPerformanceViewForMobile(String divSdo, String date,
            ModelMap model);
}
