package com.bcits.entity;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@IdClass(HHBM_DownloadID.class)
@Table(name="HHBM_DOWNLOAD",schema="PHOTOBILLING")
@NamedQueries
({
	@NamedQuery(name="HHBM_DownloadEntity.findImage", 		query="SELECT h FROM HHBM_DownloadEntity h"),
	@NamedQuery(name="HHBM_DownloadEntity.findOnlyImage", 	query="SELECT h FROM HHBM_DownloadEntity h WHERE TRIM(h.consumer_Sc_No)=TRIM(:consNo) AND h.billMonth=:billMonth AND TRIM(h.sdoCode)=TRIM(:sdoCode)"),
    //@NamedQuery(name="HHBM_DownloadEntity.findLatest" , query="SELECT DISTINCT h.sdoCode,UPPER(h.bmd_Reading),MAX(h.billDate),COUNT(*) FROM HHBM_DownloadEntity h WHERE h.billDate=to_char(sysdate,'dd-MM-yyyy') GROUP BY h.sdoCode, h.UPPER(h.bmd_Reading)"),
    //@NamedQuery(name="HHBM_DownloadEntity.findLatestImage" , query="SELECT h FROM HHBM_DownloadEntity h WHERE h.sdoCode=:sdoCode AND h.bmd_Reading=:bmdReading AND h.billDate=to_char(sysdate,'dd-MM-yyyy') ORDER BY h.takenTime DESC"),
	//@NamedQuery(name="HHBM_DownloadEntity.findLatestData", query="SELECT h FROM HHBM_DownloadEntity h WHERE TO_DATE(h.billDate,'dd-MM-yyyy')=TO_DATE(:date,'dd-MM-yyyy') ORDER BY TO_DATE(h.billDate,'dd-MM-yyyy') DESC"),
	//@NamedQuery(name="HHBM_DownloadEntity.findLatestData", query=" SELECT p.bmd_Reading  FROM(SELECT h.bmd_Reading,h.sdoCode,h.consumer_Sc_No,h.billDate,h.takenTime ,ROW_NUMBER() OVER (PARTITION BY h.bmd_Reading,h.sdoCode ORDER BY h.bmd_Reading,h.sdoCode,h.billDate,to_timestamp(h.takenTime, 'HH12:MI PM') DESC) AS rownumber FROM HHBM_DownloadEntity h WHERE h.billDate='04-12-2014') p WHERE (rownumber =1)"),
	
	@NamedQuery(name="HHBM_DownloadEntity.findNewRecords", query="SELECT h FROM HHBM_DownloadEntity h WHERE TO_DATE(h.billDate,'dd-MM-yyyy')= TO_DATE(:date,'dd-MM-yyyy')AND TRIM(h.takenTime)LIKE TO_DATE(:time,'HH:MM AM') "),
	/*SELECT TAKENTIME,BILLDATE FROM HHBM_DOWNLOAD  WHERE BILLDATE=to_char(sysdate,'dd-MM-yyyy') AND TRIM('0'|| TAKENTIME)=TO_CHAR(SYSDATE,'HH:MI AM') ORDER BY TO_DATE(TAKENTIME,'HH:MI AM') DESC;*/
	@NamedQuery(name="HHBM_DownloadEntity.FindBmdReadersAndBilled",	query="SELECT h.sdoCode,h.bmd_Reading,COUNT(*) AS cnt FROM HHBM_DownloadEntity h WHERE h.billDate=:date GROUP BY h.sdoCode,h.bmd_Reading ORDER BY max(to_timestamp(h.takenTime, 'HH12:MI PM')) DESC,h.sdoCode,h.bmd_Reading ASC"),
	@NamedQuery(name="HHBM_DownloadEntity.findBilled",		query="SELECT COUNT(*) FROM HHBM_DownloadEntity h WHERE SUBSTR(h.billDate,0,11)=:date"),  //WHERE TO_DATE(h.billDate,'dd-MM-yyy')=TO_DATE(:date,'dd-MM-yyyy')"),

	@NamedQuery(name="HHBM_DownloadEntity.findDistinctSdo" , query="SELECT DISTINCT h.sdoCode FROM HHBM_DownloadEntity h where h.sdoCode!=' ' ORDER BY h.sdoCode"),
	@NamedQuery(name="HHBM_DownloadEntity.GetDistinctSdoCount" , query="SELECT COUNT(DISTINCT h.sdoCode) FROM HHBM_DownloadEntity h where h.sdoCode is not null AND h.billMonth='201610' and h.billDate like :date"),
	
	//Reading day Wise
	@NamedQuery(name="HHBM_DownloadEntity.findDistinctBmd" ,     query="SELECT DISTINCT TRIM(h.bmd_Reading), COUNT(*),h.billMonth,TO_DATE(h.billDate,'dd-MM-yyyy') FROM HHBM_DownloadEntity h WHERE h.sdoCode=:sdocode AND TO_DATE(h.billDate,'dd-MM-yyyy')=TO_DATE(:billDate,'yyyy-MM-dd') GROUP BY h.bmd_Reading,h.billMonth,TO_DATE(h.billDate,'dd-MM-yyyy') ORDER BY TRIM(h.bmd_Reading)"),
	@NamedQuery(name="HHBM_DownloadEntity.findDistinctBmdData" , query="SELECT h FROM HHBM_DownloadEntity h WHERE h.sdoCode=:sdocode AND h.billDate like :billDate AND h.billMonth=:billMonth AND h.bmd_Reading like :bmdReader order by h.billDate"),
	
	//Meter Reader Wise
	//@NamedQuery(name="HHBM_DownloadEntity.findDistinctBmds",  query="SELECT DISTINCT TRIM(h.bmd_Reading) FROM HHBM_DownloadEntity h WHERE h.sdoCode=:sdocode ORDER BY TRIM(h.bmd_Reading)"),
	@NamedQuery(name="HHBM_DownloadEntity.findDistinctBmds",  query="SELECT DISTINCT TRIM(h.bmd_Reading) FROM HHBM_DownloadEntity h WHERE h.sdoCode=:sdocode AND h.billMonth BETWEEN :lastMonth AND :currentMonth ORDER BY TRIM(h.bmd_Reading)"),
	//@NamedQuery(name="HHBM_DownloadEntity.findBmd30DaysData", query="SELECT COUNT(*),TO_DATE(h.billDate,'dd-MM-yyyy'),h.billMonth FROM HHBM_DownloadEntity h WHERE h.sdoCode=:sdocode AND TRIM(h.bmd_Reading)=:bmdReader AND TO_DATE(h.billDate, 'dd-MM-yyyy')>(TO_DATE(:date,'dd-MM-yyyy')-30)AND TO_DATE(h.billDate, 'dd-MM-yyyy')<=(TO_DATE(:date,'dd-MM-yyyy'))GROUP BY h.bmd_Reading,TO_DATE(h.billDate,'dd-MM-yyyy'),h.billMonth ORDER BY TO_DATE(h.billDate,'dd-MM-yyyy') DESC"),
	@NamedQuery(name="HHBM_DownloadEntity.findBmd30DaysData", query="SELECT COUNT(*),TO_DATE(h.billDate,'dd-MM-yyyy'),h.billMonth FROM HHBM_DownloadEntity h WHERE h.sdoCode=:sdocode AND h.bmd_Reading like :bmdReader AND TO_DATE(h.billDate, 'dd-MM-yyyy')>(TO_DATE(:date,'dd-MM-yyyy')-30)AND TO_DATE(h.billDate, 'dd-MM-yyyy')<=(TO_DATE(:date,'dd-MM-yyyy'))GROUP BY h.bmd_Reading,TO_DATE(h.billDate,'dd-MM-yyyy'),h.billMonth ORDER BY TO_DATE(h.billDate,'dd-MM-yyyy') DESC"),

	//@NamedQuery(name="HHBM_DownloadEntity.findDistinctBmdData" , query="SELECT h FROM HHBM_DownloadEntity h WHERE h.sdoCode=:sdocode AND h.billDate=:billDate AND TRIM(h.bmd_Reading)=:bmdReader"),
	
	//Binder Wise View
	@NamedQuery(name="HHBM_DownloadEntity.findDistinctBinder",     query="SELECT DISTINCT SUBSTR(TRIM(h.consumer_Sc_No),1,4) FROM HHBM_DownloadEntity h WHERE h.consumer_Sc_No!=' ' AND TRIM(h.sdoCode)=:sdoCode"),
	@NamedQuery(name="HHBM_DownloadEntity.findBinderBillData",     query="SELECT TO_DATE(h.billDate,'dd-MM-yyyy'), COUNT(*),h.billMonth FROM HHBM_DownloadEntity h WHERE SUBSTR(TRIM(h.consumer_Sc_No),1,4)LIKE :binderNo AND h.billMonth=:billMonth AND h.sdoCode=:sdoCode GROUP BY TO_DATE(h.billDate,'dd-MM-yyyy'), h.billMonth ORDER BY TO_DATE(h.billDate,'dd-MM-yyyy') DESC"),
	@NamedQuery(name="HHBM_DownloadEntity.findAllBinderWiseBill" , query="SELECT h FROM HHBM_DownloadEntity h WHERE  SUBSTR(TRIM(h.consumer_Sc_No),1,4)LIKE:binderNo AND h.billMonth=:billMonth AND TO_DATE(h.billDate,'dd-MM-yyyy')=TO_DATE(:billDate,'yyyy-MM-dd') AND h.sdoCode=:sdoCode ORDER BY TO_DATE(h.billDate,'dd-MM-yyyy')"),

	//Consumer Wise View
	@NamedQuery(name="HHBM_DownloadEntity.findDistinctCons", query="SELECT DISTINCT TRIM(h.consumer_Sc_No) FROM HHBM_DownloadEntity h WHERE h.sdoCode=:sdocode ORDER BY TRIM(h.consumer_Sc_No)"),
	//@NamedQuery(name="HHBM_DownloadEntity.findConsDetails",  query="SELECT h.billMonth,h.billDate,h.consumer_Sc_No,h.readin_Date,h.previous_reading,h.present_Reading,h.units,h.consrStatus,h.meterNo,h.meterReadingType,h.bmd_Reading,h.longitude,h.lattitude,h.takenTime FROM HHBM_DownloadEntity h WHERE h.sdoCode=:sdocode AND TRIM(h.consumer_Sc_No)=TRIM(:consumerNo) AND h.billMonth BETWEEN :eBillMonth AND :sBillMonth ORDER BY h.billMonth DESC"),
	@NamedQuery(name="HHBM_DownloadEntity.findConsDetails",  query="SELECT h FROM HHBM_DownloadEntity h WHERE h.sdoCode=:sdocode AND TRIM(h.consumer_Sc_No)=TRIM(:consumerNo) AND h.billMonth BETWEEN :sBillMonth AND :eBillMonth ORDER BY h.billMonth DESC"),
	
	//meterNoWise
	@NamedQuery(name="HHBM_DownloadEntity.findmeterNoWiseDetails",  query="SELECT h FROM HHBM_DownloadEntity h WHERE h.sdoCode=:sdocode AND TRIM(h.meterNo)=TRIM(:meterNo) AND h.billMonth BETWEEN :sBillMonth AND :eBillMonth ORDER BY h.billMonth DESC"),
	
	//Device wise View
	@NamedQuery(name="HHBM_DownloadEntity.findDevice30DaysData", query="SELECT COUNT(*),TO_DATE(h.billDate,'dd-MM-yyyy') FROM HHBM_DownloadEntity h WHERE  TRIM(h.deviceId)=TRIM(:deviceId) AND TO_DATE(h.billDate, 'dd-MM-yyyy')>(TO_DATE(:date,'dd-MM-yyyy')-30)AND TO_DATE(h.billDate, 'dd-MM-yyyy')<=(TO_DATE(:date,'dd-MM-yyyy'))GROUP BY TO_DATE(h.billDate,'dd-MM-yyyy') ORDER BY TO_DATE(h.billDate,'dd-MM-yyyy') DESC"),
	@NamedQuery(name="HHBM_DownloadEntity.findDistinctDeviceData" , query="SELECT h FROM HHBM_DownloadEntity h WHERE TO_DATE(h.billDate,'dd-MM-yyyy')=TO_DATE(:billDate,'yyyy-MM-dd') AND TRIM(h.deviceId)=TRIM(:deviceId)"),
	
	//@NamedQuery(name="HHBM_DownloadEntity.lastSixMonths" , query="SELECT TO_CHAR(current_date + interval '1 months'),'YYYYMM')FROM HHBM_DownloadEntity h"),
	@NamedQuery(name="HHBM_DownloadEntity.findBillMonth" , query="SELECT DISTINCT(h.billMonth) FROM HHBM_DownloadEntity h ORDER BY h.billMonth DESC"),
	@NamedQuery(name="HHBM_DownloadEntity.findBillDateWise" , query="SELECT DISTINCT TO_DATE(h.billDate,'dd-MM-yyyy'),h.billMonth ,COUNT(*)FROM HHBM_DownloadEntity h WHERE h.billMonth=:billMonth AND h.sdoCode=:sdocode AND h.billStatus='1' GROUP BY TO_DATE(h.billDate,'dd-MM-yyyy'),h.billMonth ORDER BY TO_DATE(h.billDate,'dd-MM-yyyy') DESC"),
	@NamedQuery(name="HHBM_DownloadEntity.findMrBillDateWise" , query="SELECT DISTINCT h.bmd_Reading, TO_DATE(h.billDate,'dd-MM-yyyy'),h.billMonth ,COUNT(*)FROM HHBM_DownloadEntity h WHERE h.billMonth=:billMonth AND h.sdoCode=:sdocode AND h.billStatus='1' GROUP BY h.bmd_Reading,TO_DATE(h.billDate,'dd-MM-yyyy'),h.billMonth ORDER BY TO_DATE(h.billDate,'dd-MM-yyyy') DESC"),
	@NamedQuery(name="HHBM_DownloadEntity.findBinderBillDateWise" , query="SELECT DISTINCT SUBSTR(TRIM(h.consumer_Sc_No),1,4),h.billMonth, TO_DATE(h.billDate,'dd-MM-yyyy') ,COUNT(*)FROM HHBM_DownloadEntity h WHERE h.billMonth=:billMonth AND h.sdoCode=:sdocode AND h.billStatus='1' GROUP BY  SUBSTR(TRIM(h.consumer_Sc_No),1,4),TO_DATE(h.billDate,'dd-MM-yyyy'),h.billMonth ORDER BY TO_DATE(h.billDate,'dd-MM-yyyy') DESC"),
	@NamedQuery(name="HHBM_DownloadEntity.findBinderMrBillDateWise" , query="SELECT DISTINCT SUBSTR(TRIM(h.consumer_Sc_No),1,4),h.bmd_Reading,h.billMonth, TO_DATE(h.billDate,'dd-MM-yyyy') ,COUNT(*)FROM HHBM_DownloadEntity h WHERE h.billMonth=:billMonth AND h.sdoCode=:sdocode AND h.billStatus='1' GROUP BY  SUBSTR(TRIM(h.consumer_Sc_No),1,4),TO_DATE(h.billDate,'dd-MM-yyyy'),h.billMonth,h.bmd_Reading ORDER BY TO_DATE(h.billDate,'dd-MM-yyyy')DESC"),
	
	@NamedQuery(name="HHBM_DownloadEntity.GetRealStatusBilledData" , query="SELECT h FROM HHBM_DownloadEntity h WHERE TRIM(h.sdoCode)=TRIM(:sdocode) AND TRIM(h.bmd_Reading)=TRIM(:mrCode) AND SUBSTR(h.billDate,0,11)=:billDate"),
	@NamedQuery(name="HHBM_DownloadEntity.GetRealStatusBilledData1" , query="SELECT h FROM HHBM_DownloadEntity h WHERE TRIM(h.sdoCode)=TRIM(:sdocode) AND TRIM(h.bmd_Reading)=TRIM(:mrCode) AND replace(cast(to_date(substr(h.billDate,0,11),'dd-MM-yyyy') as string),'-','')=:billDate and TRIM(h.billMonth)=SUBSTR(:billDate,0,7)"),
	/*@NamedQuery(name="HHBM_DownloadEntity.SearchCriteriaTwo" , query="SELECT  DISTINCT model.sdoCode,model.bmd_Reading,model.billDate,count(*) FROM HHBM_DownloadEntity model WHERE TRIM(model.billMonth) LIKE :billMonth AND TRIM(model.consumer_Sc_No)  LIKE '1%'  group by sdoCode,bmd_Reading,billdate,billMonth order by sdoCode ")*/
	/*@NamedQuery(name="HHBM_DownloadEntity.SearchCriteriaTwo" , query="select  distinct model.sdoCode,model.bmd_Reading,count(*) FROM HHBM_DownloadEntity model  WHERE     trim(model.billMonth) like :billMonth  and trim(model.consumer_Sc_No)  like '1%' group by sdoCode,bmd_Reading "),
	@NamedQuery(name="HHBM_DownloadEntity.SearchCriteriaThree" ,query="select  distinct model.sdoCode,model.bmd_Reading,model.billDate,count(*) FROM HHBM_DownloadEntity model  WHERE trim(model.billMonth) like :billMonth  and trim(model.consumer_Sc_No)  like '1%' group by sdoCode,bmd_Reading,billDate ORDER BY sdoCode,billDate ")*/
	
	@NamedQuery(name="HHBM_DownloadEntity.SearchCriteriaThree" ,query="select  distinct model.sdoCode,model.bmd_Reading,model.billDate,count(*) FROM HHBM_DownloadEntity model  WHERE trim(model.billMonth) like :billMonth  and trim(model.consumer_Sc_No)  like '1%' group by sdoCode,bmd_Reading,billDate ORDER BY sdoCode"),
	@NamedQuery(name="HHBM_DownloadEntity.getCurrBillMonth" ,query="SELECT MAX(model.billMonth) FROM HHBM_DownloadEntity model "),
	
	@NamedQuery(name="HHBM_DownloadEntity.DistinctDates" ,query="SELECT distinct model.billDate FROM HHBM_DownloadEntity model WHERE TRIM(model.billMonth) LIKE :billMonth AND TRIM(model.consumer_Sc_No) LIKE :cons_sc_no ORDER BY model.billDate")
	
	
	/*SELECT DISTINCT SUBSTR(TRIM(consumer_Sc_No),1,4) AS BINDER ,BMD_READING as MRCODE ,TO_DATE(BILLDATE,'DD/MM/YYYY'),COUNT(*) AS BILLED 
						FROM HHBM_DOWNLOAD 
						WHERE BILL_MONTH='201410' AND BILL_STATUS='1' 
						GROUP BY SUBSTR(TRIM(consumer_Sc_No),1,4) ,BMD_READING,BILLDATE 
						ORDER BY SUBSTR(TRIM(consumer_Sc_No),1,4) ,BMD_READING,TO_DATE(BILLDATE,'DD/MM/YYYY')*/

})

public class HHBM_DownloadEntity implements Serializable
{
	
	
	@Column(name="BILL_NO")
	private String billNo;
	
	@Id
	@Column(name="consumer_sc_no")
	private String consumer_Sc_No;
	
	@Column(name="IMAGE")
	private byte[] image;

	@Id
	@Column(name="SDO_CODE")
	private String sdoCode;
	
	@Column(name="TAKENTIME")
	private String takenTime;
	
	@Column(name="BILLDATE")
	private String billDate;
	
	@Id
	@Column(name="BILL_MONTH")
	private String billMonth;
	
	@Column(name="DEVICE_ID")
	private String deviceId;
	
	@Column(name="BMD_READING")
	private String bmd_Reading;
	
	@Column(name="READING_DATE")
	private String readin_Date;
	
	@Column(name="PREVIOUS_READING")
	private String previous_reading;
	
	@Column(name="PRESENT_READING")
	private String present_Reading;
	
	@Column(name="UNITS")
	private String units;
	
	@Column(name="C_STATUS")
	private String cStatus;
	
	@Column(name="consumerstatus")
	private String consrStatus;
	
	@Column(name="METER_NO")
	private String meterNo;
	
	@Column(name="LATTITUDE")
	private String lattitude;
	
	@Column(name="LONGITUDE")
	private String longitude;
	
	@Column(name="METER_READING_TYPE")
	private String meterReadingType;
	
	@Column(name="bill_status")
	private String billStatus;
	
	@Column(name="bank")
	private String bank;
	
	@Column(name="tariff")
	private String tariff;
	
	@Column(name="netamountdue")
	private String netamountdue;
	
	
	
	public HHBM_DownloadEntity()
	{
		
	}


	/*public HHBM_ConsumerEntity getConsumerEntity() {
		return consumerEntity;
	}


	public void setConsumerEntity(HHBM_ConsumerEntity consumerEntity) {
		this.consumerEntity = consumerEntity;
	}*/


	public String getNetamountdue() {
		return netamountdue;
	}


	public void setNetamountdue(String netamountdue) {
		this.netamountdue = netamountdue;
	}


	public String getTariff() {
		return tariff;
	}


	public void setTariff(String tariff) {
		this.tariff = tariff;
	}


	public String getBillNo() {
		return billNo;
	}


	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}


	public String getConsumer_Sc_No() {
		return consumer_Sc_No;
	}


	public void setConsumer_Sc_No(String consumer_Sc_No) {
		this.consumer_Sc_No = consumer_Sc_No;
	}


	public byte[] getImage() {
		return image;
	}


	public void setImage(byte[] image) {
		this.image = image;
	}


	public String getSdoCode() {
		return sdoCode;
	}


	public void setSdoCode(String sdoCode) {
		this.sdoCode = sdoCode;
	}


	public String getTakenTime() {
		return takenTime;
	}


	public void setTakenTime(String takenTime) {
		this.takenTime = takenTime;
	}


	public String getBillDate() {
		return billDate;
	}


	public void setBillDate(String billDate) {
		this.billDate = billDate;
	}


	public String getBillMonth() {
		return billMonth;
	}


	public void setBillMonth(String billMonth) {
		this.billMonth = billMonth;
	}


	public String getDeviceId() {
		return deviceId;
	}


	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}


	public String getBmd_Reading() {
		return bmd_Reading;
	}


	public void setBmd_Reading(String bmd_Reading) {
		this.bmd_Reading = bmd_Reading;
	}


	public String getReadin_Date() {
		return readin_Date;
	}


	public void setReadin_Date(String readin_Date) {
		this.readin_Date = readin_Date;
	}


	public String getPrevious_reading() {
		return previous_reading;
	}


	public void setPrevious_reading(String previous_reading) {
		this.previous_reading = previous_reading;
	}


	public String getPresent_Reading() {
		return present_Reading;
	}


	public void setPresent_Reading(String present_Reading) {
		this.present_Reading = present_Reading;
	}


	public String getUnits() {
		return units;
	}


	public void setUnits(String units) {
		this.units = units;
	}


	public String getcStatus() {
		return cStatus;
	}


	public void setcStatus(String cStatus) {
		this.cStatus = cStatus;
	}


	public String getConsrStatus() {
		return consrStatus;
	}


	public void setConsrStatus(String consrStatus) {
		this.consrStatus = consrStatus;
	}


	public String getMeterNo() {
		return meterNo;
	}


	public void setMeterNo(String meterNo) {
		this.meterNo = meterNo;
	}


	public String getLattitude() {
		return lattitude;
	}


	public void setLattitude(String lattitude) {
		this.lattitude = lattitude;
	}


	public String getLongitude() {
		return longitude;
	}


	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}


	public String getMeterReadingType() {
		return meterReadingType;
	}


	public void setMeterReadingType(String meterReadingType) {
		this.meterReadingType = meterReadingType;
	}


	public String getBillStatus() {
		return billStatus;
	}


	public void setBillStatus(String billStatus) {
		this.billStatus = billStatus;
	}


	public String getBank() {
		return bank;
	}


	public void setBank(String bank) {
		this.bank = bank;
	}

	
	
}

class HHBM_DownloadID implements Serializable
{
	private String consumer_Sc_No;
	private String sdoCode;
	private String billMonth;
	
	
}
