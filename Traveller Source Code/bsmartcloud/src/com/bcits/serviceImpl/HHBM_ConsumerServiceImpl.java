package com.bcits.serviceImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

import com.bcits.entity.HHBM_ConsumerEntity;
import com.bcits.service.HHBM_ConsumerService;
import com.bcits.service.MrMasterOracleService;
import com.bcits.utility.BCITSLogger;
import com.bcits.utility.ConnectionClass;

@Repository
public class HHBM_ConsumerServiceImpl extends GenericServiceImpl<HHBM_ConsumerEntity> implements HHBM_ConsumerService
{
	
	@Autowired
	private MrMasterOracleService masterOracleService;
	

	@Autowired
	private DataSource dataSource;
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<Object> getReportDetails(String sdocode, String billmonth,String reportname,ModelMap model) 
	{
		BCITSLogger.logger.info("REPORT NAME======================>"+reportname);
		List<Object> reportList=null;
		try
		{
			if(reportname.toUpperCase().equals("BINDERWISE"))
			{
				   String sql="select  a.binder ,a.total ,coalesce(b.billed,0),coalesce(c.unbilled,0)as values  from (select distinct  substr(trim(consumer_sc_no),1,4) as binder ,count(*) as total   from photobilling.hhbm_consumers where sdo_code='"+sdocode+"' and bill_month='"+billmonth+"' group by substr(trim(consumer_sc_no),1,4) order by substr(trim(consumer_sc_no),1,4)) as a left join(select distinct  substr(trim(consumer_sc_no),1,4) as binder,count(*) as billed   from photobilling.hhbm_consumers where sdo_code='"+sdocode+"' and bill_month='"+billmonth+"' and bill_status   in ('1') group by substr(trim(consumer_sc_no),1,4) order by substr(trim(consumer_sc_no),1,4)) as b on a.binder=b.binder left join(select distinct  substr(trim(consumer_sc_no),1,4) as binder,count(*) as unbilled   from photobilling.hhbm_consumers where  sdo_code='"+sdocode+"' and bill_month='"+billmonth+"' and bill_status  not in ('1') group by substr(trim(consumer_sc_no),1,4) order by substr(trim(consumer_sc_no),1,4))  c on a.binder=c.binder";
				   reportList = entityManager.createNativeQuery(sql).getResultList();
				   /*for (int i = 0; i < reportList.size(); i++) 
				   {
					   Object[] s=(Object[])reportList.get(i);
					   for (int j = 0; j < s.length; j++) 
					   {
						   BCITSLogger.logger.info("REPORT VALUES======================>"+s[j]);
					   }
				   }*/
				   model.put("sdocode", sdocode);
				   model.put("billMonth", billmonth);
				   model.put("reportName", reportname);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		try
		{
			if(reportname.toUpperCase().equals("MRWISE"))
			{
				String sql="select  a.bmd_reading,a.total,coalesce(b.billed,0) ,coalesce(c.unbilled,0) as values  from (select distinct  bmd_reading as bmd_reading ,count(*) as total from photobilling.hhbm_consumers where sdo_code='"+sdocode+"' and bill_month='"+billmonth+"'group by bmd_reading  order by bmd_reading) as a left join(select distinct  bmd_reading as bmd_reading,count(*) as billed   from photobilling.hhbm_consumers where sdo_code='"+sdocode+"' and bill_month='"+billmonth+"' and bill_status   in ('1') group by bmd_reading,sdo_code   order by bmd_reading) as b on a.bmd_reading=b.bmd_reading left join(select distinct  bmd_reading as bmd_reading,count(*) as unbilled   from photobilling.hhbm_consumers where  sdo_code='"+sdocode+"' and bill_month='"+billmonth+"' and bill_status  not in ('1') group by bmd_reading ,sdo_code  order by bmd_reading)  c on a.bmd_reading=c.bmd_reading";
				BCITSLogger.logger.info("REPORT VALUES===sql===================>"+sql); 
				reportList = entityManager.createNativeQuery(sql).getResultList();
				 /*for (int i = 0; i < reportList.size(); i++) 
				   {
					   Object[] s=(Object[])reportList.get(i);
					   for (int j = 0; j < s.length; j++) 
					   {
						   BCITSLogger.logger.info("REPORT VALUES======================>"+s[j]);
					   }
				   }*/
				   model.put("sdocode", sdocode);
				   model.put("billMonth", billmonth);
				   model.put("reportName", reportname);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		try
		{
			if(reportname.toUpperCase().equals("BINDERMRWISE"))
			{
				BCITSLogger.logger.info("BINDERWISE REPORTS");
				String sql="select  a.binder, a.mrcode, a.total,coalesce(b.billed,0) ,coalesce(c.unbilled,0) as values  from (select distinct  substr(trim(consumer_sc_no),1,4) as binder,bmd_reading as mrcode ,count(*) as total from photobilling.hhbm_consumers where sdo_code='"+sdocode+"' and bill_month='"+billmonth+"' group by   substr(trim(consumer_sc_no),1,4),bmd_reading  order by  substr(trim(consumer_sc_no),1,4),bmd_reading ) as a left join(select distinct  substr(trim(consumer_sc_no),1,4) as binder,bmd_reading as mrcode,count(*) as billed   from photobilling.hhbm_consumers where sdo_code='"+sdocode+"' and bill_month='"+billmonth+"' and bill_status   in ('1') group by   substr(trim(consumer_sc_no),1,4),bmd_reading  order by substr(trim(consumer_sc_no),1,4),bmd_reading ) as b on a.binder=b.binder left join(select distinct  substr(trim(consumer_sc_no),1,4) as binder,bmd_reading as mrcode,count(*) as unbilled   from photobilling.hhbm_consumers where  sdo_code='"+sdocode+"' and bill_month='"+billmonth+"' and bill_status  not in ('1') group by substr(trim(consumer_sc_no),1,4),bmd_reading  order by  substr(trim(consumer_sc_no),1,4),bmd_reading )  c on a.binder=c.binder";
				 reportList = entityManager.createNativeQuery(sql).getResultList();
				 /*for (int i = 0; i < reportList.size(); i++) 
				   {
					   Object[] s=(Object[])reportList.get(i);
					   for (int j = 0; j < s.length; j++) 
					   {
						   BCITSLogger.logger.info("REPORT VALUES======================>"+s[j]);
					   }
				   }*/
				   model.put("sdocode", sdocode);
				   model.put("billMonth", billmonth);
				   model.put("reportName", reportname);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return reportList;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<String> findAllSdoCodes() {

		return entityManager.createNamedQuery(
				"HHBM_ConsumerEntity.findAllSdoCodes", String.class)
				.getResultList();
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<HHBM_ConsumerEntity> findAll() {
		return entityManager.createNamedQuery("HHBM_ConsumerEntity.findAll",
				HHBM_ConsumerEntity.class).getResultList();
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<HHBM_ConsumerEntity> findMatchedData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<String> findMatchedBillMonths(String sdoCode) {

		return entityManager
				.createNamedQuery("HHBM_ConsumerEntity.findMatchedBillMonths",
						String.class).setParameter("sdoCode", sdoCode)
				.getResultList();
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<String> findMatchedBmdReading(String sdoCode, String billMonth) {

		return entityManager
				.createNamedQuery("HHBM_ConsumerEntity.findMatchedBmdReading",
						String.class).setParameter("sdoCode", sdoCode)
				.setParameter("billMonth", billMonth).getResultList();
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public String getTotalConnections(String sdoCode, String billMonth,
			String bmdReading) {

		/*
		 * Integer count = null; try {
		 * 
		 * //entityManager.createQuery(
		 * "select count(*) from hhbm_consumers where sdo_code LIKE(:sdoCode) and bill_month LIKE (:billMonth) bmd_reading LIKE (:bmdReading) "
		 * ).setParameter("sdoCode", sdoCode).setParameter("billMonth",
		 * billMonth).setParameter("bmdReading", bmdReading).getSingleResult();
		 * Long obj = (Long)entityManager
		 * .createNamedQuery("HHBM_ConsumerEntity.getTotalConnections")
		 * .setParameter("sdoCode", sdoCode) .setParameter("billMonth",
		 * billMonth) .setParameter("bmdReading", bmdReading).getSingleResult()
		 * ; if (obj != null) { String coun = ""+obj;
		 * System.out.println("string count === " + coun); } } catch
		 * (NoResultException nre) { nre.getMessage(); } return count;
		 */

		Long count = (Long) entityManager.createNamedQuery(
"HHBM_ConsumerEntity.getTotalConnections")
				.setParameter("sdoCode", sdoCode.trim())
				.setParameter("billMonth", billMonth.trim())
				.setParameter("bmdReading", bmdReading.trim())
				.getSingleResult();
		System.out.println("conns count===================" + count.toString());
		return count.toString();
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public String getTotalBilled(String sdoCode, String billMonth,
			String bmdReading) {

		Long count = (Long) entityManager
				.createNamedQuery("HHBM_ConsumerEntity.getTotalBilled")
				.setParameter("sdoCode", sdoCode.trim())
				.setParameter("billMonth", billMonth.trim())
				.setParameter("bmdReading", bmdReading.trim())
				.getSingleResult();
		System.out
				.println("billed count===================" + count.toString());
		return count.toString();

	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public String getTotalDownloaded(String sdoCode,
			String billMonth, String bmdReading) {

		Long count = (Long) entityManager
				.createNamedQuery("HHBM_ConsumerEntity.getTotalDownloaded")
				.setParameter("sdoCode", sdoCode.trim())
				.setParameter("billMonth", billMonth.trim())
				.setParameter("bmdReading", bmdReading.trim())
				.getSingleResult();
		System.out.println("down count===================" + count.toString());
		return count.toString();


	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Integer updateDownStatus(HHBM_ConsumerEntity hhbm)
	{

		return entityManager
				.createNamedQuery("HHBM_ConsumerEntity.updateDownStatus")
				.setParameter("sdoCode", hhbm.getSdoCode().trim())
				.setParameter("billMonth", hhbm.getBillMonth().trim())
				.setParameter("bmdReading", hhbm.getBmdReading().trim())
				.setParameter("downStatus", hhbm.getDownStatus())
				.executeUpdate();
	}

	@Override
	public List serachCriteriaOne(HHBM_ConsumerEntity hhbm_consumerentity,String group,ModelMap model) 
	{
		 
		 SimpleDateFormat formatter = new SimpleDateFormat("yyyyMM");
		
		
		 
		 String month1=(String)hhbm_consumerentity.getBillMonth();
		 String month=null;
		 List<Object[]> searchDetailsOne=null;
		 System.out.println("date is comimg "+hhbm_consumerentity.getBillMonth());
		try
		{

			 Date date1 = formatter.parse(month1);
			 SimpleDateFormat dateFormate = new SimpleDateFormat("MM-YYYY");
			 String dates=dateFormate.format(date1);
		     System.out.println("Today  date is here           : " + dateFormate.format(date1));
		     
		     System.out.println("group========="+group);
		     /*Query query=entityManager.createNativeQuery("SELECT distinct cast(A.SDO_CODE as varchar),SUBDIV,COMPANY,A.MRCODE,A.Tobebilled , B.BILLED FROM ( select distinct c.sdo_code  as SDO_CODE,l.locationtype SUBDIV,l.company as COMPANY,c.bmd_reading AS MRCODE,c.bill_month,count(*) as Tobebilled FROM photobilling.HHBM_consumers c,  vcloudengine.locations l WHERE trim(bill_month) like ?  and trim(consumer_sc_no)  like ? and c.sdo_code=l.sitecode  group by c.sdo_code,c.bmd_reading,c.bill_month,l.locationtype,l.company )A LEFT JOIN (select distinct sdo_code, bmd_reading,bill_month  ,count(*) as Billed from photobilling.hhbm_download  WHERE trim(bill_month) like ?  and trim(consumer_sc_no)like ? group by sdo_code, bmd_reading, bill_month)B on A.sdo_code=B.sdo_code AND  A.bill_month =B.bill_month WHERE A.bill_month=? AND A.MRCODE=B.bmd_reading group by A.SDO_CODE, A.MRCODE,A.Tobebilled , B.BILLED,SUBDIV,COMPANY ORDER BY A.SDO_CODE ");*/

			 Query query=entityManager.createNativeQuery("SELECT  CAST(A.SDO_CODE as NUMERIC),SUBDIV,COMPANY,A.MRCODE,A.Tobebilled , B.BILLED FROM ( select distinct c.sdo_code  as SDO_CODE,l.locationtype SUBDIV,l.company as COMPANY,c.bmd_reading AS MRCODE,c.bill_month,count(*) as Tobebilled FROM photobilling.HHBM_consumers c,  vcloudengine.locations l WHERE trim(bill_month) like ?  and trim(consumer_sc_no)   like ? and cast(c.sdo_code as numeric)=l.sitecode  group by c.sdo_code,c.bmd_reading,c.bill_month,l.locationtype,l.company )A LEFT JOIN (select distinct sdo_code, bmd_reading,bill_month  ,count(*)as Billed from photobilling.hhbm_download  WHERE trim(bill_month) like ?  and trim(consumer_sc_no)like ? group by sdo_code, bmd_reading, bill_month)B on A.sdo_code=B.sdo_code AND  A.bill_month =B.bill_month AND A.MRCODE=B.bmd_reading WHERE A.bill_month= ? group by A.SDO_CODE, A.MRCODE,A.Tobebilled , B.BILLED,SUBDIV,COMPANY ORDER BY A.SDO_CODE");
		
		    // Query query=entityManager.createNativeQuery("SELECT DISTINCT CAST(SDO_CODE AS VARCHAR), MRCODE, SUM(TOBEBILLED) AS TOBEBILLED, SUM(BILLED) AS BILLED, COMPANY, LOCATIONTYPE, COUNT(BINDER) AS BINDER, SUM(A01) as day1,SUM(A02)as day2,SUM(A03) as day3,SUM(A04) as day4,SUM(A05) as day5,SUM(A06) as day6,SUM(A07) as day7,SUM(A08) as day8,SUM(A09) as day9,SUM(A10) as day10,SUM(A11) day11,SUM(A12) day12,SUM(A13)as day13,SUM(A14) as day14,SUM(A15) as day15,SUM(A16) as day16,SUM(A17) as day17,SUM(A18) as day18,SUM(A19) as day19,SUM(A20) as day20,SUM(A21) as day21,SUM(A22) as day22,SUM(A23) as day23,SUM(A24) as Day24, SUM(A25) as Day25,SUM(A26) as day26,sum(A27) as day27,sum(A28) as day28,sum(A29) as day29,sum(A30) as day30,sum(A31) as day31  FROM (SELECT DISTINCT SDO_CODE, MRCODE, sum(TOBEBILLED) as TOBEBILLED, COALESCE(SUM(BILLED),0) AS BILLED, COMPANY, LOCATIONTYPE,  count(binder) as binder, COALESCE(SUM(CASE WHEN DAYS=01 THEN BILLED END),0) AS A01,COALESCE(SUM(CASE WHEN DAYS=02 THEN BILLED END),0) AS A02,COALESCE(SUM(CASE WHEN DAYS=03 THEN BILLED END),0) AS A03,COALESCE(SUM(CASE WHEN DAYS=04 THEN BILLED END),0) AS A04,COALESCE(SUM(CASE WHEN DAYS=05 THEN BILLED END),0) AS A05,COALESCE(SUM(CASE WHEN DAYS=06 THEN BILLED END),0) AS A06,COALESCE(SUM(CASE WHEN DAYS=07 THEN BILLED END),0) AS A07,COALESCE(SUM(CASE WHEN DAYS=08 THEN BILLED END),0) AS A08,COALESCE(SUM(CASE WHEN DAYS=09 THEN BILLED END),0) AS A09,COALESCE(SUM(CASE WHEN DAYS=10 THEN BILLED END),0) AS A10,COALESCE(SUM(CASE WHEN DAYS=11 THEN BILLED END),0) AS A11,COALESCE(SUM(CASE WHEN DAYS=12 THEN BILLED END),0) AS A12,COALESCE(SUM(CASE WHEN DAYS=13 THEN BILLED END),0) AS A13,COALESCE(SUM(CASE WHEN DAYS=14 THEN BILLED END),0) AS A14,COALESCE(SUM(CASE WHEN DAYS=15 THEN BILLED END),0) AS A15,COALESCE(SUM(CASE WHEN DAYS=16 THEN BILLED END),0) AS A16, COALESCE(SUM(CASE WHEN DAYS=17 THEN BILLED END),0) AS A17,COALESCE(SUM(CASE WHEN DAYS=18 THEN BILLED END),0) AS A18,COALESCE(SUM(CASE WHEN DAYS=19 THEN BILLED END),0) AS A19,COALESCE(SUM(CASE WHEN DAYS=20 THEN BILLED END),0) AS A20,COALESCE(SUM(CASE WHEN DAYS=21 THEN BILLED END),0) AS A21,COALESCE(SUM(CASE WHEN DAYS=22 THEN BILLED END),0) AS A22,COALESCE(SUM(CASE WHEN DAYS=23 THEN BILLED END),0) AS A23,COALESCE(SUM(CASE WHEN DAYS=24 THEN BILLED END),0) AS A24,COALESCE(SUM(CASE WHEN DAYS=25 THEN BILLED END),0) AS A25,COALESCE(SUM(CASE WHEN DAYS=26 THEN BILLED END),0) AS A26,COALESCE(SUM(CASE WHEN DAYS=27 THEN BILLED END),0) AS A27,COALESCE(SUM(CASE WHEN DAYS=28 THEN BILLED END),0) AS A28, COALESCE(SUM(CASE WHEN DAYS=29 THEN BILLED END),0) AS A29,COALESCE(SUM(CASE WHEN DAYS=30 THEN BILLED END),0) AS A30, COALESCE(SUM(CASE WHEN DAYS=31 THEN BILLED END),0) AS A31 FROM   (SELECT A.SDO_CODE, A.MRCODE,A.Tobebilled , B.BILLED , B.DAYS, a.binder  FROM  (select  sdo_code as SDO_CODE, bmd_reading AS MRCODE, (substr(consumer_sc_no,0,5)) as binder,  bill_month , count(*) as Tobebilled  from photobilling.hhbm_consumers WHERE bill_month=?    group by sdo_code, bmd_reading ,bill_month,(substr(consumer_sc_no,0,5))  )A LEFT JOIN (select sdo_code, bmd_reading, (substr(consumer_sc_no,0,5)) as binder,  bill_month , count(*) as Billed,  EXTRACT (DAY FROM to_date(billdate,'DD-MM-YYYY') ) AS DAYS from photobilling.hhbm_download WHERE billdate like ? group by sdo_code, bmd_reading, bill_month , to_date(billdate,'DD-MM-YYYY'),(substr(consumer_sc_no,0,5)))B on A.sdo_code=B.sdo_code AND  A.bill_month =B.bill_month  AND  A.binder =B.binder WHERE A.bill_month=? group by A.SDO_CODE, A.MRCODE,A.Tobebilled , B.BILLED , B.DAYS,a.binder)BI left join (select COMPANY, LOCATIONTYPE, SITECODE from vcloudengine.locations)L ON BI.sdo_code=L.SITECODE GROUP BY SDO_CODE, MRCODE, COMPANY, LOCATIONTYPE, binder)Z GROUP BY SDO_CODE, MRCODE, COMPANY, LOCATIONTYPE "); 

			//Query query=entityManager.createNativeQuery("SELECT DISTINCT CAST(SDO_CODE as varchar), MRCODE, SUM(TOBEBILLED) AS TOBEBILLED, SUM(BILLED) AS BILLED, COMPANY, LOCATIONTYPE, COUNT(BINDER) AS BINDER, SUM(A01) as day1,SUM(A02)as day2,SUM(A03) as day3,SUM(A04) as day4,SUM(A05) as day5,SUM(A06) as day6,SUM(A07) as day7,SUM(A08) as day8,SUM(A09) as day9,SUM(A10) as day10,SUM(A11) day11,SUM(A12) day12,SUM(A13)as day13,SUM(A14) as day14,SUM(A15) as day15,SUM(A16) as day16,SUM(A17) as day17,SUM(A18) as day18,SUM(A19) as day19,SUM(A20) as day20,SUM(A21) as day21,SUM(A22) as day22,SUM(A23) as day23,SUM(A24) as Day24, SUM(A25) as Day25,SUM(A26) as day26,sum(A27) as day27,sum(A28) as day28,sum(A29) as day29,sum(A30) as day30,sum(A31) as day31  FROM (SELECT DISTINCT SDO_CODE, MRCODE, sum(TOBEBILLED) as TOBEBILLED, COALESCE(SUM(BILLED),0) AS BILLED, COMPANY, LOCATIONTYPE,  count(binder) as binder, COALESCE(SUM(CASE WHEN DAYS=01 THEN BILLED END),0) AS A01,COALESCE(SUM(CASE WHEN DAYS=02 THEN BILLED END),0) AS A02,COALESCE(SUM(CASE WHEN DAYS=03 THEN BILLED END),0) AS A03,COALESCE(SUM(CASE WHEN DAYS=04 THEN BILLED END),0) AS A04,COALESCE(SUM(CASE WHEN DAYS=05 THEN BILLED END),0) AS A05,COALESCE(SUM(CASE WHEN DAYS=06 THEN BILLED END),0) AS A06,COALESCE(SUM(CASE WHEN DAYS=07 THEN BILLED END),0) AS A07,COALESCE(SUM(CASE WHEN DAYS=08 THEN BILLED END),0) AS A08,COALESCE(SUM(CASE WHEN DAYS=09 THEN BILLED END),0) AS A09,COALESCE(SUM(CASE WHEN DAYS=10 THEN BILLED END),0) AS A10,COALESCE(SUM(CASE WHEN DAYS=11 THEN BILLED END),0) AS A11,COALESCE(SUM(CASE WHEN DAYS=12 THEN BILLED END),0) AS A12,COALESCE(SUM(CASE WHEN DAYS=13 THEN BILLED END),0) AS A13,COALESCE(SUM(CASE WHEN DAYS=14 THEN BILLED END),0) AS A14,COALESCE(SUM(CASE WHEN DAYS=15 THEN BILLED END),0) AS A15,COALESCE(SUM(CASE WHEN DAYS=16 THEN BILLED END),0) AS A16, COALESCE(SUM(CASE WHEN DAYS=17 THEN BILLED END),0) AS A17,COALESCE(SUM(CASE WHEN DAYS=18 THEN BILLED END),0) AS A18,COALESCE(SUM(CASE WHEN DAYS=19 THEN BILLED END),0) AS A19,COALESCE(SUM(CASE WHEN DAYS=20 THEN BILLED END),0) AS A20,COALESCE(SUM(CASE WHEN DAYS=21 THEN BILLED END),0) AS A21,COALESCE(SUM(CASE WHEN DAYS=22 THEN BILLED END),0) AS A22,COALESCE(SUM(CASE WHEN DAYS=23 THEN BILLED END),0) AS A23,COALESCE(SUM(CASE WHEN DAYS=24 THEN BILLED END),0) AS A24,COALESCE(SUM(CASE WHEN DAYS=25 THEN BILLED END),0) AS A25,COALESCE(SUM(CASE WHEN DAYS=26 THEN BILLED END),0) AS A26,COALESCE(SUM(CASE WHEN DAYS=27 THEN BILLED END),0) AS A27,COALESCE(SUM(CASE WHEN DAYS=28 THEN BILLED END),0) AS A28,COALESCE(SUM(CASE WHEN DAYS=29 THEN BILLED END),0) AS A29,COALESCE(SUM(CASE WHEN DAYS=30 THEN BILLED END),0) AS A30,COALESCE(SUM(CASE WHEN DAYS=31 THEN BILLED END),0) AS A31 FROM (SELECT A.SDO_CODE, A.MRCODE,A.Tobebilled , B.BILLED , B.DAYS, a.binder FROM (select  sdo_code as SDO_CODE, bmd_reading AS MRCODE, (substr(consumer_sc_no,0,5)) as binder,  bill_month , count(*) as Tobebilled  from photobilling.hhbm_consumers   group by sdo_code, bmd_reading ,bill_month,(substr(consumer_sc_no,0,5))  )A LEFT JOIN (select sdo_code, bmd_reading, (substr(consumer_sc_no,0,5)) as binder,  bill_month , count(*) as Billed, EXTRACT (DAY FROM to_date(billdate,'DD-MM-YYYY') ) AS DAYS from photobilling.hhbm_download group by sdo_code, bmd_reading, bill_month , to_date(billdate,'DD-MM-YYYY'),(substr(consumer_sc_no,0,5)))B on A.sdo_code=B.sdo_code AND  A.bill_month =B.bill_month AND  A.binder =B.binder WHERE A.bill_month=? group by A.SDO_CODE, A.MRCODE,A.Tobebilled , B.BILLED , B.DAYS,a.binder)BI left join (select COMPANY, LOCATIONTYPE, SITECODE from vcloudengine.locations)L ON CAST(BI.sdo_code as NUMERIC)=L.SITECODE GROUP BY SDO_CODE, MRCODE, COMPANY, LOCATIONTYPE, binder)Z GROUP BY SDO_CODE, MRCODE, COMPANY, LOCATIONTYPE ");
			//query.setParameter(1,hhbm_consumerentity.getBillMonth());

			
		     query.setParameter(1,hhbm_consumerentity.getBillMonth()); 
		     query.setParameter(2,group+"%");
			 query.setParameter(3,hhbm_consumerentity.getBillMonth());
			 query.setParameter(4,group+"%");
			 query.setParameter(5,hhbm_consumerentity.getBillMonth());
			
			
			 searchDetailsOne = query.getResultList();
			 System.out.println("date size"+searchDetailsOne.size());
			// Iterator iter=searchDetailsOne.iterator();
			 
			 
		     if(searchDetailsOne.size()>0)
			    {
			     
			      System.out.println("month====================="+hhbm_consumerentity.getBillMonth());
			      month=hhbm_consumerentity.getBillMonth();
			
			    System.out.println("month "+month);
			    Date date = formatter.parse(month);
			    Calendar cal = Calendar.getInstance();
		        cal.setTime(date);
		        int weekInMonth=cal.getActualMaximum(Calendar.WEEK_OF_MONTH);
		        int daysInMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		        model.put("dom",daysInMonth);
		        System.out.println("Week of the month "+weekInMonth);
		       
		        
		        cal.set(Calendar.DAY_OF_MONTH, 1);

		        Date firstDayOfMonth = cal.getTime();

		        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		        System.out.println("Today             : " + sdf.format(date));
		        System.out.println("First Day of Month: " + sdf.format(firstDayOfMonth));
		        
		        sdf.applyPattern( "EEE" );  
	            String day= sdf.format( date ); 
		        System.out.println("first day of a month"+day);
		        model.put("count",searchDetailsOne.size());
		        model.put("day",day);
		        ///searchDetailsOne.add(daysInMonth);
		      /*  
		        Locale usersLocale = Locale.getDefault();

			    DateFormatSymbols dfs = new DateFormatSymbols(usersLocale);
			    //String weekdays[] = dfs.getWeekdays();
			     String weekdays[]={"Sun","Mon","Tue","Wed","Thurs","Fri","Sat"};
			    //List<Object> weekdays = Arrays.asList(new Object[] {"Sun","Mon","Tue","Wed","Thurs","Fri","Sat"});
			    cal = Calendar.getInstance(usersLocale);
			    int indexOfYellow = ArrayUtils.indexOf(weekdays, "Yellow");
			   // int strdate=weekdays.indexOf(day);
			    
			    
			   // int firstDayOfWeek = cal.getFirstDayOfWeek();
			    int dayOfWeek;

			    for (dayOfWeek = firstDayOfWeek; dayOfWeek < weekdays.length; dayOfWeek++)
			       {
			    	System.out.println(weekdays[dayOfWeek].indexOf("Fri"));
			    	
			       }
			    
			    for(int i=0;i<weekInMonth;i++)
			    {
			      for (dayOfWeek = strdate; dayOfWeek < weekdays.size(); dayOfWeek++)
			       {
			    	
			    	 
			    	//System.out.println("days"+weekdays);
			    	Iterator it=weekdays.iterator();
			    	while (it.hasNext()) 
			    	{
			    		// List<Object[]> days=(Object)it.next();
						System.out.println("days"+it.next());
					}*/
			    	
			       }
			    
			    	    
			    //}
			      
		        
		        
			    //}
			    
			  	    
			    
			   
			    
			}
		catch (ParseException e) 
		      {
				e.printStackTrace();
			 }
		  
		catch(EntityNotFoundException enfe)
		{
			enfe.printStackTrace();
		}
		return searchDetailsOne;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Object[]>  getConsumersArrear(String sdocode, String billmonth,String arrearrange, ModelMap model)
			{
		             List<Object[]> list=null;
		             String val="";
		             String sql="";
		             if(arrearrange.equalsIgnoreCase("5000 and 49999")||arrearrange.equalsIgnoreCase("50000 and 99999"))
		             {
		            	 val="BETWEEN"+" "+arrearrange;
		             }
		             else
		             {
		            	 val=">"+" "+arrearrange;
		             }
		             
		             try 
		            {
						sql="SELECT AA.*,(SELECT section FROM vcloudengine.location WHERE cast(sitecode as VARCHAR) LIKE hhdSitecode) FROM (SELECT hhd.lattitude, hhd.longitude,hhd.consumer_sc_no, hhc.arrears,hhd.bill_month,hhd.sdo_code as hhdsitecode, hhc.consumer_name,hhc.cmobile_no,hhd.bmd_reading,hhd.tariff,hhc.address1, hhc.address2 FROM photobilling.hhbm_consumers hhc, photobilling.hhbm_download hhd"+" "
+"WHERE hhc.sdo_code=hhd.sdo_code AND hhc.consumer_sc_no=hhd.consumer_sc_no and "+" "
+"hhc.sdo_code like '"+sdocode+"%' and hhd.sdo_code like'"+sdocode+"%' AND hhd.bill_month='"+billmonth+"'"+" "
+"AND cast(hhc.arrears as NUMERIC)"+val+")AA";
						BCITSLogger.logger.info("sqlsqlsqlsql"+sql);
						list=entityManager.createNativeQuery(sql).getResultList();
					}
		             catch (Exception e) 
					{
						e.printStackTrace();
					}
					return list;
			}

	
	@Override
    public List<Map<String, Object>> getConsumersArrearMobile(String sdocode, String billmonth,String arrearrange) {
         List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
         List<?> list =null;
        String val="";
        String sql="";
        if(arrearrange.equalsIgnoreCase("500 and 1000")
        		||arrearrange.equalsIgnoreCase("1000 and 5000")
        		||arrearrange.equalsIgnoreCase("5000 and 10000")
        		||arrearrange.equalsIgnoreCase("10000 and 25000")
        		||arrearrange.equalsIgnoreCase("25000 and 50000")
        		||arrearrange.equalsIgnoreCase("50000 and 100000")
        		)
        {
            val="BETWEEN"+" "+arrearrange;
        }
        else
        {
            val=">"+" "+arrearrange;
        }

        try
       {
            /*sql="SELECT hhd.lattitude, hhd.longitude,hhd.consumer_sc_no, hhc.arrears,hhd.bill_month,hhd.sdo_code,hhc.consumer_name,hhc.reading_date,hhc.address1,hhc.address2,trfinal.tariffdesc,hhc.bmd_reading as mrcode FROM photobilling.hhbm_consumers hhc, photobilling.hhbm_download hhd ,photobilling.tariff_final trfinal "+" "
            		+"WHERE hhc.sdo_code=hhd.sdo_code AND hhc.consumer_sc_no=hhd.consumer_sc_no and trim(hhc.tariff)=CAST( trfinal.tariffcode AS text ) and "+" "
            		+"hhc.sdo_code like '"+sdocode+"%' and hhd.sdo_code like'"+sdocode+"%' AND hhd.bill_month='"+billmonth+"'"+" "
            		+"AND cast(hhc.arrears as NUMERIC)"+val+"";*/
            /*sql="select DISTINCT ap.lattitude, ap.longitude,ap.consumer_sc_no, ap.arrears,ap.bill_month,ap.sdo_code,ap.consumer_name, "
            		+ "ap.reading_date,ap.address1,ap.address2,ap.tariffdesc,ap.mrcode, aq.billdate as mrbilldate, aq.lattitude "
            		+ "AS mrlat, aq.longitude as mrlong from (SELECT DISTINCT hhd.lattitude, hhd.longitude,hhd.consumer_sc_no, "
            		+ "hhc.arrears,hhd.bill_month,hhd.sdo_code,hhc.consumer_name,hhc.reading_date,hhc.address1,hhc.address2,"
            		+ "hhc.bmd_reading as mrcode, trfinal.tariffdesc as tariffdesc FROM photobilling.hhbm_consumers hhc, "
            		+ "photobilling.hhbm_download hhd,photobilling.tariff_final trfinal WHERE hhc.sdo_code=hhd.sdo_code AND "
            		+ "hhc.consumer_sc_no=hhd.consumer_sc_no and trim(hhc.tariff)=CAST( trfinal.tariffcode AS text ) and "
            		+ "hhc.sdo_code like '"+sdocode+"%' AND hhd.bill_month='"+billmonth+"' AND cast(hhc.arrears as NUMERIC)"+val+")ap LEFT JOIN "
            		+ "(select a.bmd_reading, a.billdate, b.lattitude  , b.longitude from ((select bmd_reading, max(billdate) "
            		+ "billdate from PHOTOBILLING.HHBM_DOWNLOAD where bill_month='"+billmonth+"' group by bmd_reading)a LEFT JOIN "
            		+ "(select bmd_reading, billdate, cast(longitude as VARCHAR) longitude,cast(lattitude as VARCHAR)lattitude "
            		+ "from PHOTOBILLING.HHBM_DOWNLOAD )b ON A.bmd_reading=b.bmd_reading and a.billdate=b.billdate))aq "
            		+ "on ap.mrcode = aq.bmd_reading";*/
        	sql="select ap.lattitude, ap.longitude,ap.consumer_sc_no, ap.arrears,ap.bill_month,ap.sdo_code,ap.consumer_name,"
        			+ "ap.reading_date,ap.address1,ap.address2, ap.tariffdesc,ap.mrcode,aq.billdate as mrbilldate, aq.lattitude "
        			+ "AS mrlat, aq.longitude as mrlong from (SELECT DISTINCT hhd.lattitude, hhd.longitude,hhd.consumer_sc_no, "
        			+ "hhc.arrears,hhd.bill_month,hhd.sdo_code,hhc.consumer_name,hhc.reading_date,hhc.address1,hhc.address2,"
        			+ "hhc.bmd_reading as mrcode, substr(trfinal.tariffdesc,0,4) as tariffdesc FROM photobilling.hhbm_consumers hhc, "
        			+ "photobilling.hhbm_download hhd,photobilling.tariff_final trfinal WHERE hhc.sdo_code=hhd.sdo_code "
        			+ "AND hhc.consumer_sc_no=hhd.consumer_sc_no and trim(hhc.tariff)=CAST( trfinal.tariffcode AS text ) "
        			+ "and trfinal.tdate='01-apr-2016' and hhc.sdo_code like '"+sdocode+"%' AND hhd.bill_month='"+billmonth+"' "
        					+ "AND cast(hhc.arrears as NUMERIC)"+val+")ap LEFT JOIN (select a.bmd_reading ,a.sdo_code ,a.billdate, "
        							+ "b.lattitude  , b.longitude from ((select bmd_reading, sdo_code, max(billdate) billdate "
        							+ "from PHOTOBILLING.HHBM_DOWNLOAD where bill_month='"+billmonth+"' and sdo_code "
        									+ "like '"+sdocode+"%' group by bmd_reading,sdo_code )a LEFT JOIN "
        											+ "(select bmd_reading, billdate,sdo_code, cast(longitude as VARCHAR) "
        											+ "longitude,cast(lattitude as VARCHAR)lattitude   from PHOTOBILLING.HHBM_DOWNLOAD )b "
        											+ "ON a.bmd_reading=b.bmd_reading and a.billdate=b.billdate and a.sdo_code=b.sdo_code))aq "
        											+ "on ap.mrcode = aq.bmd_reading and ap.sdo_code=aq.sdo_code";
            list=entityManager.createNativeQuery(sql).getResultList();
               for(Iterator<?> iterator=list.iterator();iterator.hasNext();){
                   Object[] obj=(Object[]) iterator.next();
                   Map<String, Object> map=new HashMap<String, Object>();
                   map.put("lattitude", obj[0]);
                   map.put("longitude", obj[1]);
                   map.put("consumer_sc_no", obj[2]);
                   map.put("arrears", obj[3]);
                   map.put("bill_month", obj[4]);
                   map.put("sdo_code", obj[5]);
                   map.put("consumer_name", obj[6]);
                   map.put("reading_date", obj[7]);
                   map.put("address", obj[8]+" "+obj[9]);
                   map.put("tariff", obj[10]);
                   map.put("mrcode", obj[11]);
                   map.put("mrlastbilldate", obj[12]);
                   map.put("mrlat", obj[13]);
                   map.put("mrlong", obj[14]);
                   result.add(map);
               }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return result;

    } 
	
	/*@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<Object[]> showMrDaywiseBySection(String siteCode, String billmonth,ModelMap model)
	{
		List<Object[]> list=null;
		List<Object[]> list1=new ArrayList<Object[]>();
		try 
		 {
			model.put("daysOfMonth", getNoOfDaysofMonth(Integer.parseInt(billmonth.substring(0, 4)),Integer.parseInt(billmonth.substring(4, 6))));
			 List<String> dateList=getAllDatesInMonth(Integer.parseInt(billmonth.substring(4, 6))-1,Integer.parseInt(billmonth.substring(0, 4)));
			 String sql="";
			 String sql1="";
			 String sumString1="SUM(";
			 String sumString="";
			 String groupString="";
			for (int k = 0; k <dateList.size(); k++) 
			{
				sql="(SELECT count(DISTINCT consumer_sc_no) FROM  photobilling.hhbm_download WHERE sdo_code='"+siteCode+"' AND bill_month='"+billmonth+"' AND billdate like '"+dateList.get(k)+"%' AND bmd_reading=mrcode)as day"+(k+1)+"";
				sumString="BB"+"."+"day"+(k+1);
				if(k!=dateList.size()-1)
               {
					sql1=sql1+sql+",";
					sumString1=sumString1+sumString+"+";
					groupString=groupString+sumString+",";
               }
               else
               {
               	sql1=sql1+sql;
               	sumString1=sumString1+sumString+")";
               	groupString=groupString+sumString;
               }
			}
			
			//String query1="SELECT BB.*,"+sumString1+" FROM(SELECT AA.*,"+sql1+" "+"FROM(SELECT DISTINCT bmd_reading  as mrcode FROM  photobilling.hhbm_download WHERE sdo_code='"+siteCode+"' AND bill_month='"+billmonth+"')AA)BB GROUP BY BB.mrcode,"+groupString+"";
			String query1="SELECT BB.* FROM(SELECT AA.*,"+sql1+" "+"FROM(SELECT DISTINCT bmd_reading  as mrcode FROM  photobilling.hhbm_download WHERE sdo_code='"+siteCode+"' AND bill_month='"+billmonth+"')AA)BB GROUP BY BB.mrcode,"+groupString+"";
			list=entityManager.createNativeQuery(query1).getResultList();
			BCITSLogger.logger.info("=============================>showMrDaywiseBySection==="+query1);
			String mrCodeVal="(";
			for (int i = 0; i < list.size(); i++)
			{
				Object[] data=list.get(i);
				for (int j = 0; j <data.length; j++)
				{
					if(j==0)
					{
							mrCodeVal=mrCodeVal+"'"+data[j].toString()+"'"+",";
					}
					
				}
				
			}
			 mrCodeVal=mrCodeVal+")";
			 ConnectionClass con=new ConnectionClass();
				con.getconnection();
				List<String[]> mrDataList=masterOracleService.showMrMobileDetails(siteCode,mrCodeVal.replace(",)", ")"));
				BCITSLogger.logger.info("===========================>mrDataList"+mrDataList.size());
				Object[] newArr=null;
				if(mrDataList.size()>0)
				{
					for (int i = 0; i < list.size(); i++)
			       {
				Object[] data=list.get(i);
				
					newArr = Arrays.copyOf(data, data.length+2);
					for (int j = 0; j <newArr.length; j++)
					{
						for (int m = 0; m < mrDataList.size(); m++)
						{
							Object[] mrData=mrDataList.get(m);
							for (int k = 0; k <mrData.length; k++)
							{
								if(mrData[0].toString().equalsIgnoreCase(newArr[0].toString()))
								{
									if(j==newArr.length-2)
									{
										newArr[j]=mrData[1];
									}
									if(j==newArr.length-1)
									{
										newArr[j]=mrData[2];
									}
								}
							}
						}
						
					}
					list1.add(newArr);
					model.put("newArrLength", newArr.length);
				}
					return list1;
			}
				else
				{
					return list;
				}
			
		} 
		 catch (Exception e)
		{
			 model.put("results","Error while processing.");
		}
		BCITSLogger.logger.info("===========================>+list1++list1list1"+list1.size());
		return null;
		
	}*/
	
	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<Object[]> showMrDaywiseBySection(String siteCode, String billmonth,ModelMap model)
	{
		List<Object[]> list=null;
		List<Object[]> list2=null;
		List<Object[]> list1=new ArrayList<Object[]>();
		try 
		 {
			model.put("daysOfMonth", getNoOfDaysofMonth(Integer.parseInt(billmonth.substring(0, 4)),Integer.parseInt(billmonth.substring(4, 6))));
			 List<String> dateList=getAllDatesInMonth(Integer.parseInt(billmonth.substring(4, 6))-1,Integer.parseInt(billmonth.substring(0, 4)));
			 String sql="";
			 String sql1="";
			 String sumString1="SUM(";
			 String sumString="";
			 String groupString="";
			 String totalSum="";
			for (int k = 0; k <dateList.size(); k++) 
			{
				sql="count(CASE WHEN billdate like '"+dateList.get(k)+"%' THEN 0 END)as day"+(k+1)+"";
				sumString="AA"+"."+"day"+(k+1);
				if(k!=dateList.size()-1)
               {
					sql1=sql1+sql+",";
					totalSum=totalSum+"SUM(day"+(k+1)+")as sum"+(k+1)+" ,";
					sumString1=sumString1+sumString+"+";
					groupString=groupString+sumString+",";
               }
               else
               {
            	   totalSum=totalSum+"SUM(day"+(k+1)+")as sum"+(k+1)+"";
               	sql1=sql1+sql;
            	sumString1=sumString1+sumString+")";
            	groupString=groupString+sumString;
               }
			}
			
			String query1="SELECT AA.*,"+sumString1+" FROM( (SELECT DISTINCT bmd_reading as mrcode,"+sql1+" FROM  photobilling.hhbm_download WHERE sdo_code='"+siteCode+"' AND bill_month='"+billmonth+"' GROUP BY bmd_reading))AA GROUP BY AA.mrcode,"+groupString+"";
			totalSum="Select "+totalSum+" from("+""+query1+" )AA";
			BCITSLogger.logger.info("===========================>query1 "+query1);
			list=entityManager.createNativeQuery(query1).getResultList();
			list2=entityManager.createNativeQuery(totalSum).getResultList();
			int finalSum=0;
			for (int i = 0; i < list2.size(); i++)
			{
				Object[] data=list2.get(i);
				for (int j = 0; j < data.length; j++) 
				{
					finalSum=finalSum+Integer.parseInt(data[j]+"");
				}
			}
			
			String mrCodeVal="(";
			for (int i = 0; i < list.size(); i++)
			{
				Object[] data=list.get(i);
				for (int j = 0; j <data.length; j++)
				{
					if(j==0)
					{
							mrCodeVal=mrCodeVal+"'"+data[j].toString()+"'"+",";
					}
					
				}
				
			}
			 mrCodeVal=mrCodeVal+")";
			 ConnectionClass con=new ConnectionClass();
				con.getconnection();
				List<String[]> mrDataList=masterOracleService.showMrMobileDetails(siteCode,mrCodeVal.replace(",)", ")"));
				Object[] newArr=null;
				if(mrDataList.size()>0)
				{
					for (int i = 0; i < list.size(); i++)
			       {
				Object[] data=list.get(i);
				
					newArr = Arrays.copyOf(data, data.length+2);
					for (int j = 0; j <newArr.length; j++)
					{
						for (int m = 0; m < mrDataList.size(); m++)
						{
							Object[] mrData=mrDataList.get(m);
							for (int k = 0; k <mrData.length; k++)
							{
								if(mrData[0].toString().equalsIgnoreCase(newArr[0].toString()))
								{
									if(j==newArr.length-2)
									{
										newArr[j]=mrData[1];
									}
									if(j==newArr.length-1)
									{
										newArr[j]=mrData[2];
									}
								}
							}
						}
						
					}
					list1.add(newArr);
					model.put("newArrLength", newArr.length);
					model.put("totalSum", list2);
					model.put("finalSum", finalSum);
				}
					return list1;
			}
				else
				{
					return list;
				}
			
		} 
		 catch (Exception e)
		{
			 return null;
		}
		//return null;
		
	}
	
	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<Object[]> mrPerformanceView(String sitecode, String billmonth,ModelMap model)
	{
		List<Object[]> list=null;
		String query="";
		int count=0;
		Connection c=null;
		List<Object[]> list1=new ArrayList<Object[]>();
		try 
		 {
				 c=dataSource.getConnection();
				 BCITSLogger.logger.info("dataSource.getConnection()"+c.getMetaData().getURL().contains("gescom"));
				 if(c.getMetaData().getURL().contains("gescom")==true)
				 {
					 count=175;
				 }
				 else
				 {
					 count=350;
				 }
			query="SELECT ROW_NUMBER() OVER (ORDER BY AA.tBillIssued),AA.mrcode,AA.tBillIssued AS TotalBills,AA.noofdays as NoOfDaysBilled ,"+" "
					+"(AA.tBillIssued/AA.noofdays)as AvgBillPerDay, BB.normal,BB.dl,BB.mnr,BB.other,(AA.noofdays*"+count+") as actualBills,"+" "
					+"round(cast(((AA.tBillIssued*100)/cast((AA.noofdays*"+count+")as float))AS numeric),2)as DeviceEfficiency FROM ("+" "
					+"SELECT DISTINCT hhd.bmd_reading as mrcode ,count(hhd.consumer_sc_no)as tBillIssued,"+" "
					 +"count(DISTINCT (to_date(hhd.billdate, 'dd/MM/yyyy')))as noofdays,ROW_NUMBER() OVER (ORDER BY count(DISTINCT hhd.consumer_sc_no))"+" "
					+" FROM  photobilling.hhbm_download hhd, vcloudengine.location lct  WHERE  cast(lct.sitecode as varchar)=hhd.sdo_code AND hhd.bill_month='"+billmonth+"'"+" "
					 +"AND hhd.sdo_code='"+sitecode+"'"+" "
					+" GROUP BY hhd.bmd_reading,hhd.sdo_code ORDER BY count(hhd.consumer_sc_no))AA "+" "
					+"FULL JOIN"+" "
					+"(SELECT hhd.bmd_reading as mrcode, count(CASE WHEN hhd.meter_reading_type='1' THEN  '-' END)as normal,"+" "
					+"count(CASE WHEN hhd.meter_reading_type='2' THEN  '-' END)as dl,"+" "
					+"count(CASE WHEN hhd.meter_reading_type='3' THEN  '-' END)as mnr,count(CASE WHEN hhd.meter_reading_type NOT IN ('1','2','3') THEN  '-' END) as other"+" "
					 +"FROM  photobilling.hhbm_download hhd, vcloudengine.location lct  WHERE  cast(lct.sitecode as varchar)=hhd.sdo_code AND hhd.bill_month='"+billmonth+"'"+" "
					 +"AND hhd.sdo_code='"+sitecode+"' "+" "
					 +"GROUP BY hhd.bmd_reading ORDER BY hhd.bmd_reading)BB ON AA.mrcode=BB.mrcode";
			System.out.println(query);
			list=entityManager.createNativeQuery(query).getResultList();
			String mrCodeVal="(";
			for (int i = 0; i < list.size(); i++)
			{
				Object[] data=list.get(i);
				for (int j = 0; j <data.length; j++)
				{
					if(j==1)
					{
							mrCodeVal=mrCodeVal+"'"+data[j].toString()+"'"+",";
					}
					
				}
				
			}
			 mrCodeVal=mrCodeVal+")";
			 ConnectionClass con=new ConnectionClass();
				con.getconnection();
				List<String[]> mrDataList=masterOracleService.showMrMobileDetails(sitecode,mrCodeVal.replace(",)", ")"));
			for (int i = 0; i < list.size(); i++)
			{
				Object[] data=list.get(i);
				Object[] newArr = Arrays.copyOf(data, data.length+2);
				for (int j = 0; j <newArr.length; j++)
				{
					for (int m = 0; m < mrDataList.size(); m++)
					{
						Object[] mrData=mrDataList.get(m);
						for (int k = 0; k <mrData.length; k++)
						{
							if(mrData[0].toString().equalsIgnoreCase(newArr[1].toString()))
							{
								if(j==newArr.length-2)
								{
									newArr[j]=mrData[1];
								}
								if(j==newArr.length-1)
								{
									newArr[j]=mrData[2];
								}
							}
						}
					}
					
				}
				list1.add(newArr);
			}
		 }
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		return list1;
	}
	
	@Override
    @Transactional(propagation=Propagation.SUPPORTS)
    public List<Object[]> mrPerformanceViewForMobile(String sitecode, String billmonth,ModelMap model)
    {
        List<Object[]> list=null;
        String query="";
        List<Object[]> list1=new ArrayList<Object[]>();
        try
         {
            query="SELECT ROW_NUMBER() OVER (ORDER BY AA.tBillIssued),AA.mrcode,AA.tBillIssued AS TotalBills,AA.noofdays as NoOfDaysBilled ,"+" "
                    +"(AA.tBillIssued/AA.noofdays)as AvgBillPerDay, BB.normal,BB.dl,BB.mnr,BB.other,(AA.noofdays*175) as actualBills,"+" "
+"round(cast(((AA.tBillIssued*100)/cast((AA.noofdays*175)as float))AS numeric),2)as DeviceEfficiency FROM ("+" "
                    +"SELECT DISTINCT hhd.bmd_reading as mrcode ,count(DISTINCT hhd.consumer_sc_no)as tBillIssued,"+" "
                     +"count(DISTINCT (to_date(hhd.billdate, 'dd/MM/yyyy')))as noofdays,ROW_NUMBER() OVER (ORDER BY count(DISTINCT hhd.consumer_sc_no))"+" "
                    +" FROM  photobilling.hhbm_download hhd, vcloudengine.location lct  WHERE  cast(lct.sitecode as varchar)=hhd.sdo_code AND hhd.bill_month='"+billmonth+"'"+" "
                     +"AND hhd.sdo_code='"+sitecode+"'"+" "
                    +" GROUP BY hhd.bmd_reading,hhd.sdo_code ORDER BY count(DISTINCT hhd.consumer_sc_no))AA "+" "
                    +"FULL JOIN"+" "
                    +"(SELECT hhd.bmd_reading as mrcode, count(CASE WHEN hhd.meter_reading_type='1' THEN  '-' END)as normal,"+" "
                    +"count(CASE WHEN hhd.meter_reading_type='2' THEN  '-' END)as dl,"+" "
                    +"count(CASE WHEN hhd.meter_reading_type='3' THEN  '-' END)as mnr,count(CASE WHEN hhd.meter_reading_type NOT IN ('1','2','3') THEN  '-' END) as other"+" "
                     +"FROM  photobilling.hhbm_download hhd, vcloudengine.location lct  WHERE  cast(lct.sitecode as varchar)=hhd.sdo_code AND hhd.bill_month='"+billmonth+"'"+" "
                     +"AND hhd.sdo_code='"+sitecode+"' "+" "
                     +"GROUP BY hhd.bmd_reading ORDER BY hhd.bmd_reading)BB ON AA.mrcode=BB.mrcode";

list=entityManager.createNativeQuery(query).getResultList();
            String mrCodeVal="(";
            for (int i = 0; i < list.size(); i++)
            {
                Object[] data=list.get(i);
                for (int j = 0; j <data.length; j++)
                {
                    if(j==1)
                    {
mrCodeVal=mrCodeVal+"'"+data[j].toString()+"'"+",";
                    }

                }

            }
             mrCodeVal=mrCodeVal+")";
 BCITSLogger.logger.info("===============================>mrCodeVal"+mrCodeVal.replace(",)", ")"));
             ConnectionClass con=new ConnectionClass();
                con.getconnection();
                List<String[]> mrDataList=masterOracleService.showMrMobileDetails(sitecode,mrCodeVal.replace(",)", ")"));
            for (int i = 0; i < list.size(); i++)
            {
                Object[] data=list.get(i);
                Object[] newArr = Arrays.copyOf(data, 13);
                for (int j = 0; j <newArr.length; j++)
                {
                    for (int m = 0; m < mrDataList.size(); m++)
                    {
                        Object[] mrData=mrDataList.get(m);
                        for (int k = 0; k <mrData.length; k++)
                        {
if(mrData[0].toString().equalsIgnoreCase(newArr[1].toString()))
                            {
                                if(j==newArr.length-2)
                                {
                                    newArr[j]=mrData[1];
                                }
                                if(j==newArr.length-1)
                                {
                                    newArr[j]=mrData[2];
                                }
                            }
                        }
                    }

                }
                list1.add(newArr);
            }
         }
        catch(Exception e)
        {
            e.printStackTrace();
        }


        return list1;
    } 
	
}
