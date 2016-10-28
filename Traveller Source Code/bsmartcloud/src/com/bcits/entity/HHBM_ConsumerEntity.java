package com.bcits.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
/*@NamedQuery(name = "InvoicesEntity.getInvoiceItemsForLineItems", query = "SELECT DISTINCT im.itemId,im.itemName FROM InvoiceItemMasterEntity im WHERE im.itemName NOT IN('Service Tax','S&H Educational Cess On ST','Education Cess','Round Off') AND itemId NOT IN (SELECT im.itemId FROM InvoiceLineItemEntity it INNER JOIN it.invoiceItemMasterEntity im WHERE it.invoicesEntity.invoiceId=:invoiceId)"),*/

@Entity
@Table(name="HHBM_CONSUMERS",schema="PHOTOBILLING")
@NamedQueries
({
		@NamedQuery(name = "HHBM_ConsumerEntity.findAllSdoCodes", query = "SELECT DISTINCT(h.sdoCode) from HHBM_ConsumerEntity h"),
		@NamedQuery(name = "HHBM_ConsumerEntity.findAll", query = "SELECT h FROM HHBM_ConsumerEntity h"),
		@NamedQuery(name="HHBM_ConsumerEntity.findBillMonth" , query="SELECT DISTINCT(h.billMonth) FROM HHBM_ConsumerEntity h WHERE h.billMonth!='' ORDER BY h.billMonth DESC"),
		// @NamedQuery(name = "HHBM_ConsumerEntity.getTotalConnections", query =
		// "SELECT COUNT(h) AS totalconns FROM HHBM_ConsumerEntity h WHERE h.sdoCode LIKE(:sdoCode)  AND h.billMonth LIKE(:billMonth) AND h.bmdReading LIKE(TRIM(:bmdReading))"),

		@NamedQuery(name = "HHBM_ConsumerEntity.getTotalConnections", query = "SELECT COUNT(h) AS totalconns FROM HHBM_ConsumerEntity h WHERE h.sdoCode =:sdoCode AND h.billMonth=:billMonth AND h.bmdReading =:bmdReading"),

		@NamedQuery(name = "HHBM_ConsumerEntity.getTotalBilled", query = "SELECT COUNT(h) AS totalbilled FROM HHBM_ConsumerEntity h WHERE h.sdoCode=:sdoCode  AND h.billMonth=:billMonth AND h.bmdReading=:bmdReading AND h.bill_status='1'"),
		@NamedQuery(name = "HHBM_ConsumerEntity.getTotalDownloaded", query = "SELECT COUNT(h) AS totaldownloaded FROM HHBM_ConsumerEntity h WHERE h.sdoCode=:sdoCode  AND h.billMonth=:billMonth AND h.bmdReading=:bmdReading AND h.downStatus='1' AND h.bill_status <>'1'"),
		@NamedQuery(name = "HHBM_ConsumerEntity.findMatchedData", query = "SELECT h FROM HHBM_ConsumerEntity h WHERE h.sdoCode=:sdoCode AND h.billMonth=:billMonth AND h.bmdReading=:bmdReading"),
		@NamedQuery(name = "HHBM_ConsumerEntity.findMatchedBillMonths", query = "SELECT DISTINCT(h.billMonth) FROM HHBM_ConsumerEntity h WHERE h.sdoCode=:sdoCode"),
		@NamedQuery(name = "HHBM_ConsumerEntity.findMatchedBmdReading", query = "SELECT DISTINCT(h.bmdReading) FROM HHBM_ConsumerEntity h WHERE h.sdoCode=:sdoCode AND h.billMonth=:billMonth"),

		@NamedQuery(name = "HHBM_ConsumerEntity.updateDownStatus", query = "UPDATE HHBM_ConsumerEntity h SET h.downStatus=:downStatus WHERE h.sdoCode=:sdoCode AND h.bmdReading=:bmdReading AND h.billMonth=:billMonth AND h.bill_status <>'1'"),

/*
 * @NamedQuery(name="HHBM_ConsumerEntity.BinderWiseReport", query=
 * "SELECT A.SDOCODE AS SDOCODE,A.BINDER as BINDER ,A.TOTAL as TOTAL,COALESCE(B.BILLED,0) AS BILLED,COALESCE(C.BILLED,0) AS UNBILLED FROM"
 * + "(" +
 * "SELECT DISTINCT  SUBSTR(TRIM(h.consumer_sc_no),1,4) AS BINDER,h.sdoCode AS SDOCODE,COUNT(*) AS TOTAL  FROM HHBM_ConsumerEntity h WHERE h.sdoCode=:sdoCode AND h.billMonth=:billMonth GROUP BY SUBSTR(TRIM(h.consumer_sc_no),1,4),h.sdoCode ORDER BY SUBSTR(TRIM(h.consumer_sc_no),1,4)"
 * + ") AS A " + "LEFT JOIN " + "(" +
 * "SELECT DISTINCT  SUBSTR(TRIM(h.consumer_sc_no),1,4) AS BINDER,COUNT(*) AS BILLED   FROM HHBM_ConsumerEntity h WHERE h.sdoCode=:sdoCode AND h.billMonth=:billMonth AND h.bill_status  IN ('1') GROUP BY SUBSTR(TRIM(h.consumer_sc_no),1,4) ORDER BY SUBSTR(TRIM(h.consumer_sc_no),1,4)"
 * + ") AS B ON A.BINDER=B.BINDER " + "LEFT JOIN " + "(" +
 * "SELECT DISTINCT  SUBSTR(TRIM(h.consumer_sc_no),1,4) AS BINDER,COUNT(*) AS BILLED   FROM HHBM_ConsumerEntity h WHERE h.sdoCode=:sdoCode AND h.billMonth=:billMonth AND h.bill_status  NOT IN ('1') GROUP BY SUBSTR(TRIM(h.consumer_sc_no),1,4) ORDER BY SUBSTR(TRIM(h.consumer_sc_no),1,4)"
 * + ")  C ON A.BINDER=C.BINDER "),
 */
	
	
/* SELECT A.BINDER as BINDER ,A.TOTAL as TOTAL,COALESCE(B.BILLED,0) AS BILLED,COALESCE(C.BILLED,0) AS UNBILLED FROM 
(
SELECT DISTINCT  SUBSTR(TRIM(CONSUMER_SC_NO),1,4) AS BINDER,COUNT(*) AS TOTAL    FROM HHBM_CONSUMERS WHERE BILL_MONTH='201410'GROUP BY SUBSTR(TRIM(CONSUMER_SC_NO),1,4) ORDER BY SUBSTR(TRIM(CONSUMER_SC_NO),1,4)
) AS A
LEFT JOIN
(
SELECT DISTINCT  SUBSTR(TRIM(CONSUMER_SC_NO),1,4) AS BINDER,COUNT(*) AS BILLED   FROM HHBM_CONSUMERS WHERE BILL_MONTH='201410' AND BILL_STATUS   IN ('1') GROUP BY SUBSTR(TRIM(CONSUMER_SC_NO),1,4) ORDER BY SUBSTR(TRIM(CONSUMER_SC_NO),1,4)
) AS B ON A.BINDER=B.BINDER
LEFT JOIN
(
SELECT DISTINCT  SUBSTR(TRIM(CONSUMER_SC_NO),1,4) AS BINDER,COUNT(*) AS BILLED   FROM HHBM_CONSUMERS WHERE BILL_MONTH='201410' AND BILL_STATUS  NOT IN ('1') GROUP BY SUBSTR(TRIM(CONSUMER_SC_NO),1,4) ORDER BY SUBSTR(TRIM(CONSUMER_SC_NO),1,4)
)  C ON A.BINDER=C.BINDER 	
 */
/*@NamedQuery(name = "HHBM_ConsumerEntity.SearchCriteriaOne", query = "SELECT DISTINCT model.sdoCode,model.bmdReading,COUNT(*) FROM HHBM_ConsumerEntity model WHERE TRIM(model.billMonth) LIKE :billMonth AND TRIM(model.consumer_sc_no)  LIKE '1%' GROUP BY model.sdoCode,model.bmdReading")*/
		/*@NamedQuery(name = "HHBM_ConsumerEntity.SearchCriteriaOne", query = "SELECT DISTINCT model.sdoCode,loc.locationtype,loc.company,model.bmdReading,COUNT(*),model.billMonth FROM HHBM_ConsumerEntity model,LocationEntity loc WHERE model.sdoCode=loc.sitecode AND TRIM(model.billMonth) LIKE :billMonth AND TRIM(model.consumer_sc_no)  LIKE '1%' GROUP BY model.sdoCode,model.bmdReading,loc.locationtype,loc.company,model.billMonth ORDER BY model.sdoCode"),*/
		
		/*@NamedQuery(name = "HHBM_ConsumerEntity.SearchCriteriaOne", query = "SELECT C.SDOCODE,C.BMDREADING,COALESCE(D.locationtype ,'0') AS LOCATIONTYPE , COALESCE(D.company,'0') AS COMPANY,C.BINDER,C.BILLMONTH,C.NOOFINST, C.DOWNLOADED, COALESCE(D.sitecode,'0') AS SITECODE  FROM (select a.billMonth AS BILLMONTH, a.sdoCode AS SDOCODE, a.bmdReading AS BMDREADING,a.consumer_sc_no as BINDER, a.noofinst AS NOOFINST, COALESCE(b.download,0) AS DOWNLOADED from  (SELECT billMonth, sdoCode, bmdReading,  count(*) as noofinst, trim(substr(consumer_sc_no,0,5)) as consumer_sc_no  FROM HHBM_ConsumerEntity   group by billMonth, sdoCode, bmdReading , trim(substr(consumer_sc_no,0,5)))a left join(SELECT billMonth, sdoCode, bmd_Reading,  count(*) as download, trim(substr(consumer_sc_no,0,5)) as consumer_sc_no FROM hhbm_download group by billMonth, sdoCode, bmd_Reading , trim(substr(consumer_sc_no,0,5)))b on a.billMonth= b.billMonth and a.sdoCode=b.sdoCode and a.consumer_sc_no=b. consumer_sc_no WHERE a.billMonth =:billMonth)C left join (select company, locationtype, sitecode from LocationEntity)D ON C.SDOCODE=D.sitecode")*/
		
				
})
public class HHBM_ConsumerEntity implements Serializable
{
	
	@Id
	@Column(name="CONSUMER_SC_NO")
	private String consumer_sc_no;
 
	@Column(name="BILL_STATUS")
	private String bill_status;
	
	
	@Column(name="BILL_MONTH")
	private String billMonth;
	
	@Id
	@Column(name="SDO_CODE")
	private String sdoCode;
	
	@Column(name="BMD_READING")
	private String bmdReading;

	@Column(name = "DOWN_STATUS")
	private Integer downStatus;
	
	@Column(name="address1")
	private String address1;
	
	@Column(name="address2")
	private String address2;
	
	/*@OneToOne
	@JoinColumn(name="SDO_CODE",insertable=false,updatable=false)
	private LocationEntity locationEntity;*/

	/*@OneToOne
	@JoinColumns( {
        @JoinColumn(name = "SDO_CODE",insertable=false,updatable=false ),
        @JoinColumn(name = "BILL_MONTH",insertable=false,updatable=false),
        @JoinColumn(name = "CONSUMER_SC_NO",insertable=false,updatable=false)
    })
	
	private HHBM_DownloadEntity downloadEntity;*/
	
	
	/*@Column(name="TAKENTIME")
	private String takenTime;
	
	@Column(name="BILLDATE")
	private String billDate;
	
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
	private String meterReadingType;*/
	
	/*
	public LocationEntity getLocationEntity() {
		return locationEntity;
	}*/

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	/*public void setLocationEntity(LocationEntity locationEntity) {
		this.locationEntity = locationEntity;
	}*/

	public String getBmdReading() {
		return bmdReading;
	}

	public void setBmdReading(String bmdReading) {
		this.bmdReading = bmdReading;
	}

	public Integer getDownStatus() {
		return downStatus;
	}

	public void setDownStatus(Integer downStatus) {
		this.downStatus = downStatus;
	}

	public HHBM_ConsumerEntity()
	{
		
	}


	public HHBM_ConsumerEntity(String consumer_sc_no, String bill_status,
			String billMonth, String sdoCode) {
		super();
		this.consumer_sc_no = consumer_sc_no;
		this.bill_status = bill_status;
		this.billMonth = billMonth;
		this.sdoCode = sdoCode;
	}


	public String getConsumer_sc_no() {
		return consumer_sc_no;
	}

	public void setConsumer_sc_no(String consumer_sc_no) {
		this.consumer_sc_no = consumer_sc_no;
	}

	public String getBill_status() {
		return bill_status;
	}

	public void setBill_status(String bill_status) {
		this.bill_status = bill_status;
	}

	public String getBillMonth() {
		return billMonth;
	}

	public void setBillMonth(String billMonth) {
		this.billMonth = billMonth;
	}


	public String getSdoCode() {
		return sdoCode;
	}


	public void setSdoCode(String sdoCode) {
		this.sdoCode = sdoCode;
	}

}



