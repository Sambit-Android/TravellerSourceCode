package com.bcits.service;


import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.ModelMap;

import com.bcits.entity.HHBM_ConsumerEntity;
import com.bcits.entity.HHBM_DownloadEntity;

public interface HHBM_DownloadService extends GenericService<HHBM_DownloadEntity>
{

	//For Real Time status view
	List<HHBM_DownloadEntity> findImage(ModelMap model,HttpServletRequest request,HttpServletResponse response) throws IOException;
	byte[] findOnlyImage(ModelMap model, HttpServletRequest request,HttpServletResponse response, String consumer_Sc_No,String billMonth,String sdoCode) throws IOException;
	//List findLatestData(ModelMap model);
	List findLatestImg(String sdoCode, String bmdReading);
	List findLatestData(String date,ModelMap model, HttpServletRequest request);
	List<HHBM_DownloadEntity> findNewRecords(ModelMap model, String date,String time,HttpServletRequest request);
	String getBmdReadersAndBilled(String date,ModelMap model);
	String getTotalBilled(String date,ModelMap model);
	
	//public List<HHBM_DownloadEntity> getDistinctSdoCodes();
	
	//For Reading day wise screen
	List<HHBM_DownloadEntity> getDistinctSdo();
	List<HHBM_DownloadEntity> getDistinctBmd(String sdoCode, String billDate, ModelMap model,HttpServletRequest request);
	List<HHBM_DownloadEntity> getAllBmdData(String sdoCode, String billDate,String bmdReader, ModelMap model,HttpServletRequest request);
	
	//For Meter Reader Wise
	List<HHBM_DownloadEntity> getDistinctBmdList(String sdoCode,ModelMap model,HttpServletRequest request);
	List<HHBM_DownloadEntity> getBmdDataList(String sdoCode,String bmdReader,String date, ModelMap model, HttpServletRequest request);
	
	//For Binder Wise view
	List<HHBM_DownloadEntity> getDistinctBinder(String sdoCode);
	List<String> getLastSixMonths();
	void getBinderWiseBill(String sdoCode,String binderNo, String billMonth, ModelMap model,HttpServletRequest request);
	List<HHBM_DownloadEntity> getAllBinderWiseData(String binderNo,String billMonth, String parameter3, ModelMap model,HttpServletRequest request);
	
	//For Consumer Wise view	
	List<HHBM_DownloadEntity> getDistinctConsList(String sdoCode,ModelMap model,HttpServletRequest request);
	List<HHBM_DownloadEntity> getConsumerDetails(String sdoCode,String consumerNo,String eBillMonth,String sBillMonth, ModelMap model, HttpServletRequest request);
	
	//For Device Wise view	
	//List<HHBM_DownloadEntity> getDeviceDataList(String deviceId,ModelMap model, HttpServletRequest request);
	List<HHBM_DownloadEntity> getDeviceDataList(String deviceId,String date,ModelMap model, HttpServletRequest request);
	List<HHBM_DownloadEntity> getDeviceAllData(String billDate,String deviceId, ModelMap model, HttpServletRequest request);
	
	
	//GENEARL METHOD
	List<HHBM_DownloadEntity> getAllData(String sdoCode, String bmdReader, String billDate,String deviceId,   String value,   String billMonth,  String binderNo,  ModelMap model, HttpServletRequest request);
	
	//List<HHBM_DownloadEntity> checkSdoCode(String parameter);
	
	List<String> getBillMonths();
	List getReportDetails(String sdocode,String billMonth, String reportName,ModelMap model);
	
	List getBmdReadersAndBilledForAjax(String date,ModelMap model);
	
	List getTotalbilledAndDemand(String date,ModelMap model) ;
	
	List<HHBM_DownloadEntity> getMeterNoWiseDetails(String sdoCode,String meterNo,String eBillMonth,String sBillMonth, ModelMap model, HttpServletRequest request);
	
	List<HHBM_DownloadEntity> getRealStatusBilledData(HttpServletRequest request,String sdoCode,String mrcode,String billdate,ModelMap model);
   
	public List  serachCriteriaTwo(HHBM_DownloadEntity hhbm_DownloadEntity,String group,ModelMap model);
	public List  serachCriteriaThree(HHBM_DownloadEntity hhbm_DownloadEntity,String group,ModelMap model);
    public String currBillMonth();
    void makeReportList(HHBM_DownloadEntity hhDownloadEntity,HHBM_ConsumerEntity hhbm_consumerentity,String group,ModelMap model);
	public List divisionWiseMobiles(ModelMap model, String cuurentDate);
	public List showMobileDevicesLiveBySection(String divSdo, String date,
			ModelMap model);
	public List<Object[]> getDeviceMrBilled(String sectioncode, String date, ModelMap model);
	public List<Object[]> getDeviceMrNotBilled(String sectioncode, String date, ModelMap model);
	public List<Object[]> divisionWiseMrCount(ModelMap model, String currentDate);
	public List<Object[]> showMrTrackLive(String divSdo, String date, ModelMap model);
	List<Map<String, Object>> showMrTrackLiveMobile(String divSdo, String date);
	public List<Object[]> getMrPerfromance(String sdocode, String date);
	public List<HHBM_DownloadEntity> viewConsumerOnMap(String rrno, String billmonth, String sdocode, ModelMap model);
	public Object getDeviceHealth(String mrCode, String sdoCode, String date);
	 List showDevicesNotLiveToday(String month, String currentDate,
			ModelMap model);
	List<HHBM_DownloadEntity> getRealStatusBilledData1(
			HttpServletRequest request, String sdoCode, String mrcode,
			String billdate, ModelMap model);
	List<HHBM_DownloadEntity> viewConsumerOnMap1(String rrno, String billmonth,
			String sdocode, String date,ModelMap model);
	List<Object[]> getDataForDeviceHealth(String deviceid, String billdate,ModelMap model);
	//public List<?> getMrWiseBilledTodaySectionWise(String mrcode,String sdocode);
	public BigInteger getMrWiseBilledTodaySectionWise(String mrcode,String sdocode);
	public void deviceUsageRpt(ModelMap model, String parameter);

	List<Object[]> deviceProviderRpt(ModelMap model, String parameter);
	/*List<Object[]> mrTypeRpt(ModelMap model, String currentMonth);*/

	List secRealTimeStatus(ModelMap model);
}

