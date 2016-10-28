package com.bcits.serviceImpl;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import javax.imageio.ImageIO;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

import com.bcits.entity.HHBM_ConsumerEntity;
import com.bcits.entity.HHBM_DownloadEntity;
import com.bcits.service.HHBM_ConsumerService;
import com.bcits.service.HHBM_DownloadService;
import com.bcits.service.MrMasterOracleService;
import com.bcits.utility.BCITSLogger;
import com.bcits.utility.ConnectionClass;


@Repository
public class HHBM_DownloadServiceImpl extends GenericServiceImpl<HHBM_DownloadEntity> implements HHBM_DownloadService
{
	
	@Autowired
    private DataSource dataSource;
	
	@Autowired
	private HHBM_ConsumerService consumerService;
	
	@Autowired
	private MrMasterOracleService masterOracleService;
	
	DecimalFormat formatDemand = new DecimalFormat("##");//("##.0000")    
    
	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<HHBM_DownloadEntity> findImage(ModelMap model,HttpServletRequest request,HttpServletResponse response) 
	{
		return entityManager.createNamedQuery("HHBM_DownloadEntity.findImage").getResultList();
		
		/*for (int i = 0; i < list.size(); i++) 
		{
		    Object[] str= (Object[]) list.get(i);
		    for (int j = 1; j < str.length; j++) 
		    {
		    	consumerNo=(String) str[0];
		    	image=(byte[]) str[1];
		    }
		}*/
	}
	
	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public byte[] findOnlyImage(ModelMap model,HttpServletRequest request,HttpServletResponse response,String consumerScNo,String billMonth,String sdoCode) throws IOException 
	{
		int k=0;

		int t=0;
		byte image[]=null;
		try
		{
		List<HHBM_DownloadEntity> hh=  entityManager.createNamedQuery("HHBM_DownloadEntity.findOnlyImage").setParameter("consNo",consumerScNo).setParameter("billMonth",billMonth).setParameter("sdoCode",sdoCode).getResultList();

		//model.put("imageees", hh);
		//System.out.println(hh.size());
		
		response.setContentType("image/jpeg/png");
    	byte bt[] = null;
    	OutputStream ot = null;
    	ot = response.getOutputStream();	
    	if(hh.size()>0)
    	{
    		bt=hh.get(0).getImage();
    		if(bt.length==0)
    		{
    			//GESCOM
    			//String pathname="/home/bcits/GESCOM_LIVE/PHOTOBILLING_GESCOM/apache-tomcat-7.0.12/bin/noImage.jpg";
    			//CESCOM
    			String pathname="F:\\BsmartCloud\\apache-tomcat-7.0.12\\bin\\noImage.png";
    			//String pathname="E:\\CESC_LIVE_SERVER\\apache-tomcat-7.0.12\\bin\\noImage.jpg";
    			BufferedImage originalImage = ImageIO.read(new File(pathname));
    			ByteArrayOutputStream baos = new ByteArrayOutputStream();
    			ImageIO.write( originalImage, "png", baos );
    			baos.flush();
    			byte[] imageInByte = baos.toByteArray();
    			ot.write(imageInByte);
    			baos.close();
    			
    		}
    		else
    		{
    			ot.write(bt);
            	ot.close();
    		}
    			
    		
    	}
    	else
    	{
    		
    	}
       Base64 b=new Base64();
       BCITSLogger.logger.info("================>BT"+bt.length);
       image=b.encodeBase64(bt);
		/*return b.encodeBase64(bt);*/
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return image;    	
    }

	/*@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public List findLatestData(ModelMap model,HttpServletRequest request) 
	{
		String mainData[] = new String[6];
		String sdoCode=null;
		String bmdReading=null;
		String billdate=null;
		String count=null;
		String consumerNo=null;
		String takenTime=null;
		List imgList=null;
		List iList=null;
		List dataList=new ArrayList();
		String sql="";
		
SELECT  DISTINCT SDO_CODE, UPPER(BMD_READING), max(BILLDATE), COUNT(*)FROM PHOTOBILLING.HHBM_DOWNLOAD WHERE BILLDATE LIKE '08-09-2014' GROUP BY SDO_CODE,UPPER(BMD_READING) ORDER BY SDO_CODE,UPPER(BMD_READING);
SELECT CONSUMER_SC_NO, TAKENTIME,IMAGE FROM PHOTOBILLING.HHBM_DOWNLOAD WHERE SDO_CODE='2426' AND upper(BMD_READING)='A' AND BILLDATE LIKE '08-09-2014' ORDER BY TAKENTIME DESC

		sql="SELECT DISTINCT h.sdoCode,UPPER(h.bmd_Reading),MAX(h.billDate),COUNT(*)FROM HHBM_DownloadEntity h WHERE h.billDate=to_char(sysdate,'dd-MM-yyyy') GROUP BY h.sdoCode, UPPER(h.bmd_Reading)ORDER BY h.sdoCode, UPPER(h.bmd_Reading)";
		final String queryString=sql;
		List alist = entityManager.createQuery(queryString).getResultList();
		
		System.out.println("ALIST SIZE------------------------->"+alist.size());
		imgList=findLatestImg(sdoCode, bmdReading);
		
		
		
		for (int i = 0; i < alist.size(); i++)
		{
			Object[] data=(Object[])alist.get(i);
			for (int j = 0; j < data.length; j++) 
			{
				mainData[0]=(String) data[0];//sdocode
				mainData[1]=(String) data[1];//bmr 
				mainData[2]=(String) data[2];//billdaate
				 count= data[3]+"";//count
				mainData[3]=count;
				//System.out.println("SELECTED DATA---------------------->"+data[j]);
                if(j==0)
                {
                	sdoCode=(String) data[j];
                }
                if(j==1)
                {
                	bmdReading=(String) data[j];
                }
			}
				imgList=findLatestImg(sdoCode, bmdReading);
			for (int k = 0; k < imgList.size(); k++)
			{
				Object[] data1=(Object[])imgList.get(k);
				for (int m = 0; m < data1.length; m++) 
				{
					mainData[4]=(String)data1[0];//consumerNo.
					mainData[5]=(String)data1[1];//timetaken
				}
				
			}
			dataList.add(mainData);
		}
		
		model.put("dataList", alist);
		for (int i = 0; i < dataList.size(); i++) 
		{
			System.out.println("DATA LIST VALUES---------------->"+dataList.get(i));
		}
		
		request.setAttribute("dataList", dataList);
		return dataList;
	}*/	

	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public List findLatestData(String date,ModelMap model,HttpServletRequest request) 
	{
		List list=null;
		try
		{
			String yearMonth=new SimpleDateFormat("yyyyMM").format(new SimpleDateFormat("dd/MM/yyyy").parse(date));
			BCITSLogger.logger.info("todays date is  "+date);
			//list= entityManager.createNamedQuery("HHBM_DownloadEntity.findLatestData").setParameter("date", date).getResultList();
			//String sql="SELECT * FROM(SELECT cast(BMD_READING as VARCHAR(6)),cast(SDO_CODE as VARCHAR(4)),cast(consumer_sc_no as VARCHAR(15)),cast(billdate as VARCHAR(10)),cast(takentime as VARCHAR(30)),cast(BILL_MONTH as VARCHAR(6)),ROW_NUMBER() OVER (PARTITION BY BMD_READING,SDO_CODE ORDER BY SDO_CODE,BMD_READING,billdate,to_timestamp(takentime, 'HH12:MI PM') DESC) AS rownumber FROM PHOTOBILLING.HHBM_DOWNLOAD WHERE SUBSTR(BILLDATE,0,11)=:billdate ORDER BY to_timestamp(takentime, 'HH12:MI PM') DESC,SDO_CODE,BMD_READING ASC) p WHERE (rownumber =1)";
			//String sql="SELECT * FROM(SELECT cast(BMD_READING as VARCHAR(6)),cast(SDO_CODE as VARCHAR(4)),cast(consumer_sc_no as VARCHAR(15)),cast(billdate as VARCHAR(10)),cast(takentime as VARCHAR(30)),cast(BILL_MONTH as VARCHAR(6)),ROW_NUMBER() OVER (PARTITION BY BMD_READING,SDO_CODE ORDER BY SDO_CODE,BMD_READING,billdate DESC) AS rownumber FROM PHOTOBILLING.HHBM_DOWNLOAD WHERE SUBSTR(BILLDATE,0,11)=:billdate ORDER BY to_timestamp(takentime, 'HH12:MI PM') DESC,SDO_CODE,BMD_READING ASC) p WHERE (rownumber =1)";
			String sql="SELECT A.*, (SELECT SECTION FROM vcloudengine.location WHERE cast(sitecode as varchar) like A.sitecode) FROM(SELECT * FROM(SELECT cast(BMD_READING as VARCHAR(6)) ,cast(SDO_CODE as VARCHAR(4)) as sitecode,cast(consumer_sc_no as VARCHAR(15)),cast(billdate as VARCHAR(10)),cast(takentime as VARCHAR(30)),cast(BILL_MONTH as VARCHAR(6)),extra6,ROW_NUMBER() OVER (PARTITION BY BMD_READING,SDO_CODE ORDER BY SDO_CODE,BMD_READING,billdate DESC) AS rownumber FROM PHOTOBILLING.HHBM_DOWNLOAD WHERE BILLDATE LIKE :date AND bill_month='"+yearMonth+"' ORDER BY to_timestamp(takentime, 'HH12:MI PM') DESC,SDO_CODE,BMD_READING ASC) p WHERE (rownumber =1))AS A";
			BCITSLogger.logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+sql);
			Query query=entityManager.createNativeQuery(sql).setParameter("date",date+"%");
		    list=query.getResultList();	
		    
		    String query1="SELECT sitecode,section FROM vcloudengine.location WHERE sitecode||'' NOT IN"+" "
+"("+" "
+"SELECT DISTINCT sdo_code FROM photobilling.hhbm_download WHERE billdate LIKE '"+date+"%'"+" "
+")AND sitecode||''NOT LIKE '90%' AND sitecode||''NOT LIKE '24%' ORDER BY section";
		    List<Object[]> notLiveSections=entityManager.createNativeQuery(query1).getResultList();
		    model.put("notLiveSections", notLiveSections);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<HHBM_DownloadEntity> findNewRecords(ModelMap model,String date,String time,HttpServletRequest request) 
	{
		List<HHBM_DownloadEntity> list=null;
		try
		{
			list= entityManager.createNamedQuery("HHBM_DownloadEntity.findNewRecords").setParameter("date", date).setParameter("time", time).getResultList();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public List findLatestImg(String sdoCode,String bmdReading) 
	{
		List list=null;
		try
		{
			list= entityManager.createNamedQuery("HHBM_DownloadEntity.findLatestImage").setParameter("sdoCode", sdoCode).setParameter("bmdReading", bmdReading.toLowerCase()).getResultList();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return list;
	}

	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public String getBmdReadersAndBilled(String date,ModelMap model) 
	{	
		 List list=null;
		 try
		 {
			// list=entityManager.createNamedQuery("HHBM_DownloadEntity.FindBmdReadersAndBilled").setParameter("date", date).getResultList();
			 list=getTotalbilledAndDemand(date,model);
			 model.put("countBmd", list.size());
			 int billed=0;
			 double totaLDemand=0;
			 List list1=new ArrayList();
			 for (int i = 0; i <list.size(); i++) 
			 {
				 Object[] obj=(Object[]) list.get(i);
				 billed=billed+(Integer) obj[2];// for total billed
				 totaLDemand=totaLDemand+(Double) obj[3];// for total Demand
				 list1.add((Integer) obj[2]);// to set every mrBilled 
			 }
			 model.put("mrBilled", list1);
			 model.put("billed", billed);
			 model.put("totalSdo",(Long)entityManager.createNamedQuery("HHBM_DownloadEntity.GetDistinctSdoCount").setParameter("date", date+"%").getSingleResult());
			 model.put("totalDeamnd",formatDemand.format(totaLDemand/100000));
			 
		 }
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		 return "";
	}

	
			@Override
			@Transactional(propagation=Propagation.SUPPORTS)
			public List getTotalbilledAndDemand(String date,ModelMap model) 
			{
				List list=null;
				Integer deviceCount=0;
				try
				{
					String yearMonth=new SimpleDateFormat("yyyyMM").format(new SimpleDateFormat("dd/MM/yyyy").parse(date));
					String sql="SELECT CAST(h.SDO_CODE as VARCHAR),CAST(h.BMD_READING as VARCHAR),CAST(COUNT(*) as INTEGER) AS cnt,SUM(CAST(ec AS FLOAT)+CAST(fixed_charges AS FLOAT)+CAST(tax AS FLOAT)) AS demand FROM PHOTOBILLING.HHBM_DOWNLOAD h WHERE h.BILLDATE LIKE :date AND h.bill_month='"+yearMonth+"' GROUP BY h.SDO_CODE,h.BMD_READING ORDER BY max(to_timestamp(h.TAKENTIME, 'HH12:MI PM')) DESC,h.SDO_CODE,h.BMD_READING ASC";
					Query query= entityManager.createNativeQuery(sql).setParameter("date", date+"%");
					String sql1="SELECT cast(count(DISTINCT device_id)as INTEGER) FROM photobilling.hhbm_download WHERE BILLDATE LIKE :date AND bill_month='"+yearMonth+"'";
					try
					{
						 deviceCount= (Integer) entityManager.createNativeQuery(sql1).setParameter("date", date+"%").getSingleResult();
						list=query.getResultList();
						model.put("deviceCount", deviceCount);
					}
					catch(NoResultException e)
					{
						model.put("deviceCount", deviceCount);
					}
					
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				return list;
			}
			
			
    @Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public List getBmdReadersAndBilledForAjax(String date,ModelMap model) 
	{	
		 List list=null;
		 try
		 {
			 //list=entityManager.createNamedQuery("HHBM_DownloadEntity.FindBmdReadersAndBilled").setParameter("date", date).getResultList();
			 list=getTotalbilledAndDemand(date,model);
			 int billed=0;
			 double totaLDemand=0;			
			 for (int i = 0; i <list.size(); i++) 
			 {
				 Object[] obj=(Object[]) list.get(i);
				 billed=billed+(Integer) obj[2];
				 totaLDemand=totaLDemand+(Double) obj[3];// for total Demand				 
			 }				
			 list.add(billed);
			 list.add((Long)entityManager.createNamedQuery("HHBM_DownloadEntity.GetDistinctSdoCount").setParameter("date", date+"%").getSingleResult());
			 list.add(formatDemand.format(totaLDemand/100000));
			 list.add(model.get("deviceCount"));
		 }
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		 return list;
	}
	
	
	
	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public String getTotalBilled(String date,ModelMap model) 
	{
		String value="";
		 Object count=null;
		 try
		 {
			 count=entityManager.createNamedQuery("HHBM_DownloadEntity.findBilled").setParameter("date", date).getSingleResult();
			 model.put("billed", count);
			 value=value+count;
		 }
		 catch(Exception e)
		{
			 e.printStackTrace();
		}
		 
		 return value;
	}

	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<HHBM_DownloadEntity> getRealStatusBilledData(HttpServletRequest request,String sdoCode,String mrcode,String billdate,ModelMap model) 
	{		
		return entityManager.createNamedQuery("HHBM_DownloadEntity.GetRealStatusBilledData").setParameter("sdocode", sdoCode).setParameter("mrCode", mrcode).setParameter("billDate", billdate).getResultList();
	}
	
	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<HHBM_DownloadEntity> getRealStatusBilledData1(HttpServletRequest request,String sdoCode,String mrcode,String billdate,ModelMap model) 
	{		
		return entityManager.createNamedQuery("HHBM_DownloadEntity.GetRealStatusBilledData1").setParameter("sdocode", sdoCode).setParameter("mrCode", mrcode).setParameter("billDate", billdate).getResultList();
	}

	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<HHBM_DownloadEntity> getDistinctSdo()
	{
		List<HHBM_DownloadEntity> list=null;
		try
		{
			String currentMonth=new SimpleDateFormat("yyyyMM").format(new Date());
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.MONTH, -1);
			String lastMonth=new SimpleDateFormat("yyyyMM").format(cal.getTime());
			//list= entityManager.createNamedQuery("HHBM_DownloadEntity.findDistinctSdo").getResultList();
			String sql="SELECT ll.sitecode,ll.section FROM photobilling.hhbm_download HH, vcloudengine.location ll  WHERE  HH.sdo_code=CAST(ll.sitecode AS varchar) AND HH.bill_month IN ('"+lastMonth+"','"+currentMonth+"') AND HH.sdo_code IS NOT NULL GROUP BY ll.sitecode,ll.section";
			list=entityManager.createNativeQuery(sql).getResultList();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return list;
	}
	
	/*@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<HHBM_DownloadEntity> getDistinctSdoCodes()
	{
		List<HHBM_DownloadEntity> list=null;
		
		try
		{
			//final String sql="select distinct(h1.sdoCode) from HHBM_DownloadEntity h1,HHBM_ConsumerEntity h2 where h1.sdoCode=h2.sdoCode";
			final String sql="select distinct(sdoCode) from  HHBM_ConsumerEntity ";
			list= entityManager.createQuery(sql).getResultList();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return list;
	}*/

	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<HHBM_DownloadEntity> getDistinctBmd(String sdoCode,String billDate,ModelMap model,HttpServletRequest request) 
	{
		List<HHBM_DownloadEntity> bmdList=null;
		try
		{
			model.put("sCode", sdoCode);
			model.put("bDate", billDate);
			bmdList= entityManager.createNamedQuery("HHBM_DownloadEntity.findDistinctBmd").setParameter("sdocode", sdoCode).setParameter("billDate", billDate).getResultList();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		/*List<HHBM_DownloadEntity> bmdList=*/ 
    	//model.put("bmdList", bmdList);
		return bmdList;
	}

	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<HHBM_DownloadEntity> getAllBmdData(String sdoCode, String billDate,String bmdReader, ModelMap model, HttpServletRequest request) 
	{
		List<HHBM_DownloadEntity> dataList=null;
		try
		{
			 dataList= entityManager.createNamedQuery("HHBM_DownloadEntity.findDistinctBmdData").setParameter("sdocode", sdoCode).setParameter("billDate", billDate).setParameter("bmdReader", bmdReader).getResultList();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	    return dataList;
	}

	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<HHBM_DownloadEntity> getDistinctBmdList(String sdoCode,ModelMap model,HttpServletRequest request) 
	{
		List<HHBM_DownloadEntity> listBmd=null;
		String currentMonth=new SimpleDateFormat("yyyyMM").format(new Date());
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		String lastMonth=new SimpleDateFormat("yyyyMM").format(cal.getTime());
		System.out.println("ddddd"+currentMonth+"=="+lastMonth);
		try
		{
			listBmd= entityManager.createNamedQuery("HHBM_DownloadEntity.findDistinctBmds").setParameter("sdocode", sdoCode.trim()).setParameter("currentMonth", currentMonth).setParameter("lastMonth", lastMonth).getResultList();
		}
		catch(Exception e)
		{
			//e.printStackTrace();
			return listBmd;
		}
		return listBmd;
		
	}

	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<HHBM_DownloadEntity> getBmdDataList(String sdoCode,String bmdReader,String date, ModelMap model, HttpServletRequest request)
    {
		List<HHBM_DownloadEntity> list=null;
		try
		{
			model.put("sCode", sdoCode);
			  model.put("bmdReader", bmdReader);
			  list=entityManager.createNamedQuery("HHBM_DownloadEntity.findBmd30DaysData").setParameter("sdocode", sdoCode).setParameter("bmdReader", bmdReader.trim()).setParameter("date", date).getResultList();
		}
		catch(Exception e)
		{
			//e.printStackTrace();
			return list;
		}
		return list;
		  
    }

	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<HHBM_DownloadEntity> getDistinctBinder(String sdoCode) 
	{
		List<HHBM_DownloadEntity> binderLidt=null;
		try
		{
			binderLidt=entityManager.createNamedQuery("HHBM_DownloadEntity.findDistinctBinder").setParameter("sdoCode", sdoCode.trim()).getResultList();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return binderLidt;
	}
	
	/*@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<String> getLastSixMonths() 
	{ 
		//select (current_date + interval '1 months')::date

		String sql="";
		List<String> billMonth=new ArrayList<String>();
		for (int i = 1; i <=6; i++) 
		{
			int k=i;
			//sql="SELECT TO_CHAR(ADD_MONTHS(SYSDATE,"+-i+"),'YYYYMM') FROM HHBM_DownloadEntity h";
			sql="SELECT TO_CHAR(current_date + interval '1month'),'YYYYMM')";
			String bMonthList= (String) entityManager.createNamedQuery("HHBM_DownloadEntity.lastSixMonths").setParameter("k", k).getSingleResult();
			billMonth.add(bMonthList);
		}
		return billMonth;
	}*/
	
	@Override
	public List<String> getLastSixMonths() 
	{
		int k=0;;
		   int i;
		   List<String> bMonthList=new ArrayList<String>();
		 try
		 {
			 Calendar cal =  Calendar.getInstance();
			   for (i = 0; i < 6; i++) 
			   {
				   if(k==i)
				   {
					   cal.add(Calendar.MONTH ,0);
					   String  month= new SimpleDateFormat("YYYYMM").format(cal.getTime());
					   bMonthList.add(month);
				   }
				   else
				   {
					   cal.add(Calendar.MONTH ,-1);
					   String months  = new SimpleDateFormat("YYYYMM").format(cal.getTime());
					   bMonthList.add(months);
				   }
			   }
		 }
		 catch(Exception e)
		 {
			 e.printStackTrace();
		 }
		return bMonthList;
	}
	
	
	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public void getBinderWiseBill(String sdoCode,String binderNo,String billMonth,ModelMap model,HttpServletRequest request) 
	{
		List binderBillList=null;
		try
		{
			model.put("bndNo", binderNo);
			model.put("bllMon", billMonth);
			model.put("sdocode", sdoCode);
	    	 binderBillList= entityManager.createNamedQuery("HHBM_DownloadEntity.findBinderBillData").setParameter("binderNo", binderNo+"%").setParameter("billMonth", billMonth).setParameter("sdoCode", sdoCode).getResultList();
	    	model.put("binderBillList", binderBillList);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<HHBM_DownloadEntity> getAllBinderWiseData(String binderNo,String billMonth, String billDate, ModelMap model,HttpServletRequest request)
	{
		List<HHBM_DownloadEntity> binderList=null;
		try 
		{
			entityManager.createNamedQuery("HHBM_DownloadEntity.findAllBinderWiseBill").setParameter("binderNo", binderNo+"%").setParameter("billMonth", billMonth).setParameter("billDate", billDate).getResultList();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return  binderList;
	}

	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<HHBM_DownloadEntity> getDistinctConsList(String sdoCode,ModelMap model, HttpServletRequest request) 
	{
		List<HHBM_DownloadEntity> consumerList=null;
		try 
		{
			consumerList= entityManager.createNamedQuery("HHBM_DownloadEntity.findDistinctCons").setParameter("sdocode", sdoCode).getResultList();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return consumerList;  
	}

	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<HHBM_DownloadEntity> getConsumerDetails(String sdoCode,String consumerNo,String eBillMonth,String sBillMonth, ModelMap model, HttpServletRequest request) 
	{
		List<HHBM_DownloadEntity> consumerDataList=null;
		try 
		{
			consumerDataList= entityManager.createNamedQuery("HHBM_DownloadEntity.findConsDetails").setParameter("sdocode", sdoCode).setParameter("consumerNo", consumerNo).setParameter("sBillMonth", sBillMonth).setParameter("eBillMonth", eBillMonth).getResultList();
			
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return consumerDataList;
	}
	
	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<HHBM_DownloadEntity> getMeterNoWiseDetails(String sdoCode,String meterNo,String eBillMonth,String sBillMonth, ModelMap model, HttpServletRequest request) 
	{
		List<HHBM_DownloadEntity> consumerDataList=null;
		try 
		{
			consumerDataList= entityManager.createNamedQuery("HHBM_DownloadEntity.findmeterNoWiseDetails").setParameter("sdocode", sdoCode).setParameter("meterNo",meterNo).setParameter("sBillMonth", sBillMonth).setParameter("eBillMonth", eBillMonth).getResultList();
			
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return consumerDataList;
	}

	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<HHBM_DownloadEntity> getDeviceDataList(String deviceId,String date,ModelMap model, HttpServletRequest request) 
	{
		List<HHBM_DownloadEntity> deviceDataList=null;
		try 
		{
			model.put("deviceId", deviceId);
			deviceDataList= entityManager.createNamedQuery("HHBM_DownloadEntity.findDevice30DaysData").setParameter("deviceId", deviceId).setParameter("date", date).getResultList();
		
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return deviceDataList; 
	}

	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<HHBM_DownloadEntity> getDeviceAllData(String billDate,String deviceId, ModelMap model, HttpServletRequest request) 
	{
		List<HHBM_DownloadEntity> dataList=null;
		try
		{
			dataList= entityManager.createNamedQuery("HHBM_DownloadEntity.findDistinctDeviceData").setParameter("billDate", billDate).setParameter("deviceId", deviceId).getResultList();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return dataList;
	}

	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<HHBM_DownloadEntity> getAllData(String sdoCode,
			String bmdReader, String billDate, String deviceId, String value,String billMonth,String binderNo,
			ModelMap model, HttpServletRequest request) 
	{
		List<HHBM_DownloadEntity> hhbmList=null;
		try
		{
			if(value.equals("meterWise"))
			{
				String bill_Date=new SimpleDateFormat("dd/MM/yyyy").format(new SimpleDateFormat("yyyy-MM-dd").parse(billDate));
				hhbmList= entityManager.createNamedQuery("HHBM_DownloadEntity.findDistinctBmdData").setParameter("sdocode", sdoCode).setParameter("billDate", bill_Date+"%").setParameter("bmdReader", bmdReader).setParameter("billMonth", billMonth).getResultList();
			}
			if(value.equals("deviceWise"))
			{
				 hhbmList= entityManager.createNamedQuery("HHBM_DownloadEntity.findDistinctDeviceData").setParameter("billDate", billDate).setParameter("deviceId", deviceId).getResultList();
			}
			if(value.equals("binderWise"))
			{
				 //System.out.println("DATA COMING------------>"+binderNo+"  "+billMonth+"  "+billDate);
				 hhbmList= entityManager.createNamedQuery("HHBM_DownloadEntity.findAllBinderWiseBill").setParameter("binderNo", binderNo+"%").setParameter("billMonth", billMonth).setParameter("billDate", billDate).setParameter("sdoCode", sdoCode).getResultList();
			}
			if(value.equals("readingDayWise"))
			{
				String bill_Date=new SimpleDateFormat("dd/MM/yyyy").format(new SimpleDateFormat("yyyy-MM-dd").parse(billDate));
				 hhbmList= entityManager.createNamedQuery("HHBM_DownloadEntity.findDistinctBmdData").setParameter("sdocode", sdoCode).setParameter("billDate", bill_Date+"%").setParameter("bmdReader", bmdReader).setParameter("billMonth", billMonth).getResultList();
			}
			
			model.put("hhbmList", hhbmList);
		}
		catch(Exception e)
		{
			//e.printStackTrace();
			return hhbmList;
		}
		
		return hhbmList;
	}

	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<String> getBillMonths() 
	{
		List<String> billMonths=null;
		try
		{
			billMonths=entityManager.createNamedQuery("HHBM_DownloadEntity.findBillMonth").getResultList();
			//billMonths=entityManager.createNamedQuery("HHBM_ConsumerEntity.findBillMonth").getResultList();
			//String sql="SELECT DISTINCT(h.billMonth) FROM  HHBM_DownloadEntity h INTERSECT (SELECT DISTINCT(s.billMonth) FROM  HHBM_ConsumerEntity s) order by h.billMonth desc";
		    //billMonths=entityManager.createQuery(sql).getResultList();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return billMonths;
	}

	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public List getReportDetails(String sdocode, String billMonth,String reportName,ModelMap model) 
	{
		List reportList=null;
		try
		{
			if(reportName.toUpperCase().equals("BILLDATEWISE"))
			{
			   reportList= entityManager.createNamedQuery("HHBM_DownloadEntity.findBillDateWise").setParameter("billMonth", billMonth).setParameter("sdocode", sdocode).getResultList();
			   model.put("sdocode", sdocode);
			   model.put("billMonth", billMonth);
			   model.put("reportName", reportName);
			}
			if(reportName.toUpperCase().equals("MRBILLDATEWISE"))
			{
			   reportList= entityManager.createNamedQuery("HHBM_DownloadEntity.findMrBillDateWise").setParameter("billMonth", billMonth).setParameter("sdocode", sdocode).getResultList();
			   model.put("sdocode", sdocode);
			   model.put("billMonth", billMonth);
			   model.put("reportName", reportName);
			}
			if(reportName.toUpperCase().equals("BINDERBILLDATEWISE"))
			{
			   reportList= entityManager.createNamedQuery("HHBM_DownloadEntity.findBinderBillDateWise").setParameter("billMonth", billMonth).setParameter("sdocode", sdocode).getResultList();
			   model.put("sdocode", sdocode);
			   model.put("billMonth", billMonth);
			   model.put("reportName", reportName);
			}
			if(reportName.toUpperCase().equals("BINDERMRBILLDATEWISE"))
			{
			   reportList= entityManager.createNamedQuery("HHBM_DownloadEntity.findBinderMrBillDateWise").setParameter("billMonth", billMonth).setParameter("sdocode", sdocode).getResultList();
			   model.put("sdocode", sdocode);
			   model.put("billMonth", billMonth);
			   model.put("reportName", reportName);
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return reportList;
	}

	


	@Override
	public String currBillMonth()
	{
		String billMonth=null;
		try
		{
			billMonth=(String)entityManager.createNamedQuery("HHBM_DownloadEntity.getCurrBillMonth").getSingleResult();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			billMonth=null;
		}
		return billMonth;
	}

	@Override
	public List serachCriteriaTwo(HHBM_DownloadEntity hhbm_DownloadEntity,
			String group, ModelMap model) {
		 SimpleDateFormat formatter = new SimpleDateFormat("yyyyMM");
		 String month1=(String)hhbm_DownloadEntity.getBillMonth();
		 String month=null;
		List<HHBM_DownloadEntity> criteriaTwo=null;
		try
		{
			 Date date1 = formatter.parse(month1);
			 SimpleDateFormat dateFormate = new SimpleDateFormat("MM-YYYY");
			 String dates=dateFormate.format(date1);
		     System.out.println("Today  date is here  : " + dateFormate.format(date1));
		     
			
			
			
		   
			Query query=entityManager.createNativeQuery("SELECT DISTINCT  cast(billdate as varchar)  from photobilling.HHBM_download WHERE trim(bill_month) like ? and TRIM(consumer_sc_no) like ?   ORDER BY billdate");
			query.setParameter(1,hhbm_DownloadEntity.getBillMonth());
			//query.setParameter(2, "%"+dateFormate.format(date1));
		    query.setParameter(2,group+"%");
		    criteriaTwo=query.getResultList();
		    
		   
		  
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return criteriaTwo;
	}

	@Override
	public List serachCriteriaThree(HHBM_DownloadEntity hhbm_DownloadEntity,String group,
			ModelMap model) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMM");
		 String month1=(String)hhbm_DownloadEntity.getBillMonth();
		 String month=null;
		List<HHBM_DownloadEntity> criteriaThree=null;
		try
		{
			
		   Query query1=entityManager.createNativeQuery("select  distinct cast(sdo_code as varchar),bmd_reading,cast(billdate as varchar),count(*) FROM photobilling.HHBM_download   WHERE     trim(bill_month) like ?  and trim(consumer_sc_no)  like ? group by sdo_code,bmd_reading ,billdate ORDER BY billdate");
		    query1.setParameter(1,hhbm_DownloadEntity.getBillMonth());
			//query1.setParameter(2, "%"+dateFormate.format(date1));
		    query1.setParameter(2,group+"%");
		    criteriaThree=query1.getResultList();
		    
		   
		  
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return criteriaThree;
	}

	
	/*@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public String checkSdoCode(String parameter) 
	{
		return entityManager.createNamedQuery("HHBM_DownloadEntity.checkSdoCode").getSingleResult();
	}*/
	//DistinctDates
	
	public void makeReportList(HHBM_DownloadEntity hhDownloadEntity,HHBM_ConsumerEntity hhbm_consumerentity,String group,ModelMap model)
	{		
		List consumerlist=consumerService.serachCriteriaOne(hhbm_consumerentity, group, model);
		List downloadlist=serachCriteriaThree(hhDownloadEntity,group,model);
		model.put("serachOne",consumerlist);
		model.put("serachTwo",downloadlist);
		List billDateList=entityManager.createNamedQuery("HHBM_DownloadEntity.DistinctDates").setParameter("billMonth", hhDownloadEntity.getBillMonth()).setParameter("cons_sc_no", group+"%").getResultList();
		
		List<String> newList=new ArrayList<String>();
		String days="";
		
		for (int i = 0; i < consumerlist.size(); i++) 
		{
			
			for (int j = 0; j < downloadlist.size(); j++)
			{
				
					Object[] consumerData=(Object[]) consumerlist.get(i);
					Object[] downloadData=(Object[]) downloadlist.get(j);
					
					if(consumerData[0].toString().trim().equals(downloadData[0]) && consumerData[3].equals(downloadData[1]))
					{	
						int k=0;
							for (int j2 =0 ; j2 < billDateList.size(); j2++) 
							{
								
								System.out.println("hellol "+consumerData[0]+" "+downloadData[0]+"      "+consumerData[3]+" "+downloadData[1]+"     "+downloadData[2]+" "+billDateList.get(j2)+" count "+downloadData[3]);
							if(downloadData[2].equals(billDateList.get(j2).toString()))
							{
								days=days+downloadData[3]+"@"+billDateList.get(j2).toString()+"$";
								
							}							
							}
						
					}				
			}
			newList.add(days);
			days="";
			
		}
		model.put("newList", newList);
		model.put("dayss", billDateList);
		System.out.println("sizeeeee "+newList.size()+"\n"+newList);
		
	}
	
	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public List divisionWiseMobiles(ModelMap model, String cuurentDate)
	{
		
		 List list = null;
	        Connection c = null;
	        int count = 0;
	        BCITSLogger.logger.info("In User DashBoard");
	        try {
	            c = dataSource.getConnection();
	            BCITSLogger.logger.info("dataSource.getConnection()" + c.getMetaData().getURL().contains("gescom"));
	            count = c.getMetaData().getURL().contains("gescom") ? 100 : 500;
	        }
	        catch (SQLException e) {
	            e.printStackTrace();
	        }
	        try {
	            String yearMonth = new SimpleDateFormat("yyyyMM").format(new SimpleDateFormat("dd-MM-yyyy").parse(cuurentDate));
	            String dateValue = cuurentDate.replace("-", "/");
	            String sql = "SELECT (SELECT division FROM vcloudengine.location WHERE substr(newsitecode||'',0,3)=T2.newdivcode LIMIT 1 ), T2.newdivcode, (SELECT count(*) FROM vcloudengine.location l, vcloudengine.devicemaster dev WHERE l.sitecode=dev.sdocode AND substr(l.newsitecode||'',0,3)=T2.newdivcode) as totaldevices,COALESCE(T4.livedevices,0)as livedevices,COALESCE(T3.lessused,0)as lessbilled  FROM ( SELECT DISTINCT substr(newsitecode||'',0,3)as newdivcode FROM vcloudengine.location WHERE substr(newsitecode||'',0,3) not like '9%' ORDER BY substr(newsitecode||'',0,3) )T2 LEFT JOIN ( \tSELECT T1.newDivcode,count(T1.lt500)as lessused FROM \t( \tSELECT substr(l.newsitecode||'',0,3)as newDivcode,device_id,count(*)as lt500 FROM photobilling.hhbm_download h, vcloudengine.location l  \tWHERE l.sitecode||''=h.sdo_code  \tAND h.bill_month='" + yearMonth + "' " + "\tGROUP BY substr(l.newsitecode||'',0,3),device_id " + "\tHAVING count(*)<" + count + " " + "\t)T1 GROUP BY T1.newDivcode ORDER BY T1.newDivcode " + ")T3 ON T2.newdivcode=T3.newDivcode  " + "LEFT JOIN " + "( " + "SELECT substr(l.newsitecode||'',0,3)as newDivcode,count(DISTINCT device_id)as livedevices FROM photobilling.hhbm_download h, vcloudengine.location l  " + "\tWHERE l.sitecode||''=h.sdo_code  " + "\tAND h.bill_month='" + yearMonth + "' AND billdate LIKE '" + dateValue + "%' " + "\tGROUP BY substr(l.newsitecode||'',0,3) " + ")T4 ON T2.newdivcode=T4.newDivcode ORDER BY T4.newDivcode ";
	            BCITSLogger.logger.info("=-=================================sql>" + sql);
	            list = this.entityManager.createNativeQuery(sql).getResultList();
	            int totaDevices = 0;
	            int liveDdevices = 0;
	            int spareDevices = 0;
	            if (list.size() > 0) {
	                int i = 0;
	                while (i < list.size()) {
	                    Object[] dat = (Object[])list.get(i);
	                    int j = 0;
	                    while (j < dat.length) {
	                        if (j == 2) {
	                            totaDevices += Integer.parseInt(dat[j]+"");
	                        }
	                        if (j == 3) {
	                            liveDdevices += Integer.parseInt(dat[j]+"");
	                        }
	                        if (j == 4) {
	                            spareDevices += Integer.parseInt(dat[j]+"");
	                        }
	                        ++j;
	                    }
	                    ++i;
	                }
	            }
	            model.put("spareDevices", spareDevices);
	            model.put( "liveDdevices",  liveDdevices);
	            model.put( "totaDevices",  totaDevices);
	            model.put( "mobileLiveList",  list);
	            model.put( "cuurentDate",  cuurentDate);
	            if (list.size() == 0) {
	                model.put("results", "No Data is Available for the selected date.");
	            } else {
	                model.put("results", "notDisplay");
	            }
	        }
	        catch (Exception e) {
	        	e.printStackTrace();
	            model.put("results","Error while processing.");
	        }
	        return list;
		
		
	}
	
	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public List showMobileDevicesLiveBySection(String divSdo, String date,
			ModelMap model)
	{

        List list = null;
        try {
            String yearMonth = new SimpleDateFormat("yyyyMM").format(new SimpleDateFormat("dd-MM-yyyy").parse(date));
            String dateValue = date.replace("-", "/");
            int count = 0;
            Connection c = null;
            try {
                c = this.dataSource.getConnection();
                BCITSLogger.logger.info("dataSource.getConnection()" + c.getMetaData().getURL().contains("gescom"));
                count = c.getMetaData().getURL().contains("gescom") ? 100 : 500;
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
            String sql = "SELECT (SELECT section FROM vcloudengine.location WHERE newsitecode=T2.newsitecode LIMIT 1 ), T2.newsitecode, (SELECT count(*) FROM vcloudengine.location l, vcloudengine.devicemaster dev WHERE l.sitecode=dev.sdocode AND newsitecode=T2.newsitecode) as totaldevices,COALESCE(T4.livedevices,0)as livedevices,COALESCE(T5.billoftoday,0)as billoftoday,COALESCE(T3.lessused,0)as lessbilled  FROM ( SELECT DISTINCT newsitecode as newsitecode FROM vcloudengine.location WHERE substr(newsitecode||'',0,3) not like '9%' AND substr(newsitecode||'',0,3) like '" + divSdo + "%'" + "ORDER BY newsitecode )T2 LEFT JOIN ( \tSELECT T1.newsitecode,count(T1.lt500)as lessused " + "FROM \t" + "( " + "SELECT l.newsitecode as newsitecode,device_id,count(*)as lt500 FROM photobilling.hhbm_download h, vcloudengine.location l " + " \tWHERE l.sitecode||''=h.sdo_code  \tAND h.bill_month='" + yearMonth + "' AND substr(l.newsitecode||'',0,3) like '" + divSdo + "%'\tGROUP BY l.newsitecode,device_id \t" + "HAVING count(*)<" + count + " )T1 GROUP BY T1.newsitecode ORDER BY T1.newsitecode )T3 ON T2.newsitecode=T3.newsitecode  LEFT JOIN " + "(" + " SELECT l.newsitecode as newsitecode," + "count(DISTINCT device_id)as livedevices FROM photobilling.hhbm_download h, vcloudengine.location l " + " \tWHERE l.sitecode||''=h.sdo_code  \t" + "AND h.bill_month='" + yearMonth + "' AND billdate LIKE '" + dateValue + "%' " + "AND l.newsitecode||'' like '" + divSdo + "%' \tGROUP BY l.newsitecode" + ")T4 ON T2.newsitecode=T4.newsitecode " + "LEFT JOIN" + "(" + "SELECT l.newsitecode,count(DISTINCT h.consumer_sc_no)as billoftoday FROM photobilling.hhbm_download h ," + " vcloudengine.location l WHERE l.sitecode||''=h.sdo_code AND l.newsitecode||'' LIKE '" + divSdo + "%' AND h.bill_month='" + yearMonth + "' AND h.billdate LIKE '" + dateValue + "%' " + "GROUP BY l.newsitecode" + ")T5 ON  T2.newsitecode=T5.newsitecode ";
            BCITSLogger.logger.info("=-=================================sql>" + sql);
            list = this.entityManager.createNativeQuery(sql).getResultList();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return list;
		
	}
	
	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<Object[]> getDeviceMrBilled(String sectioncode, String date, ModelMap model)
	{
        List list = null;
        try {
            String yearMonth = new SimpleDateFormat("yyyyMM").format(new SimpleDateFormat("dd-MM-yyyy").parse(date));
            String dateValue = date.replace("-", "/");
            String sql = "SELECT  AA.deviceidAA,AA.bmd_readingAA,  AA.consumer_sc_noAA AS Today_Billed,BB.consumer_sc_noAB  as total_billed,AA.bill_monthAA FROM  (  SELECT  hhm.device_id as deviceidAA ,  hhm.bmd_reading as bmd_readingAA,  count(DISTINCT hhm.consumer_sc_no)as consumer_sc_noAA , bill_month as bill_monthAA  FROM vcloudengine.location lct,  photobilling.hhbm_download hhm ,  vcloudengine.devicemaster dev,vcloudengine.deviceallocation dev1  WHERE cast(lct.sitecode as varchar)=hhm.sdo_code   and dev1.deviceid=dev.deviceid and lct.sitecode=dev.sdocode and  hhm.billdate like '" + dateValue + "%' AND lct.sitecode||''=hhm.sdo_code AND lct.newsitecode||'' like '" + sectioncode + "%'   " + "AND hhm.bill_month='" + yearMonth + "' GROUP BY hhm.device_id, hhm.bmd_reading,bill_month  " + "ORDER BY hhm.bmd_reading )AA  LEFT JOIN  ( SELECT  hhm.bmd_reading as bmd_readingAB,  " + "count(DISTINCT hhm.consumer_sc_no)as consumer_sc_noAB ,bill_month as bill_monthAB   " + "FROM vcloudengine.location lct, photobilling.hhbm_download hhm , vcloudengine.devicemaster dev,  " + "vcloudengine.deviceallocation dev1 WHERE cast(lct.sitecode as varchar)=hhm.sdo_code  " + " and dev1.deviceid=dev.deviceid and lct.sitecode=dev.sdocode AND lct.sitecode||''=hhm.sdo_code AND lct.newsitecode||'' like'" + sectioncode + "%'   " + "AND bill_month='" + yearMonth + "'  " + " GROUP BY  hhm.bmd_reading,bill_month ORDER BY hhm.bmd_reading ) " + " BB ON  AA.bmd_readingAA=BB.bmd_readingAB ORDER BY AA.bmd_readingAA";
            BCITSLogger.logger.info("=====================>sql" + sql);
            list = this.entityManager.createNativeQuery(sql).getResultList();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return list;
	
	}
	
	
	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<Object[]> getDeviceMrNotBilled(String sectioncode, String date, ModelMap model)
	{

        List list = null;
        String sql = "";
        int count = 0;
        Connection c = null;
        try {
            c = this.dataSource.getConnection();
            BCITSLogger.logger.info("dataSource.getConnection()" + c.getMetaData().getURL().contains("gescom"));
            count = c.getMetaData().getURL().contains("gescom") ? 100 : 500;
            String yearMonth = new SimpleDateFormat("yyyyMM").format(new SimpleDateFormat("dd-MM-yyyy").parse(date));
            String dateValue = date.replace("-", "/");
            sql = "SELECT AAA.* ,   (  SELECT count(DISTINCT hhm.consumer_sc_no) FROM photobilling.hhbm_download hhm,vcloudengine.location l WHERE  hhm.bill_month='" + yearMonth + "' AND hhm.billdate like '" + dateValue + "%' AND  l.sitecode||''=hhm.sdo_code " + "AND l.newsitecode='" + sectioncode + "'   " + "AND hhm.device_id=AAA.deviceid " + ") as xyz FROM   " + "( " + "SELECT hhmd.device_id as deviceid,hhmd.sdo_code as sdocode, " + "count(DISTINCT hhmd.consumer_sc_no) FROM photobilling.hhbm_download hhmd,vcloudengine.location l  WHERE  " + "hhmd.bill_month='" + yearMonth + "' AND l.sitecode||''=hhmd.sdo_code " + "AND l.newsitecode='" + sectioncode + "'  " + "GROUP BY hhmd.device_id,hhmd.sdo_code   " + "HAVING count(DISTINCT hhmd.consumer_sc_no)<" + count + "  " + ")AAA";
            BCITSLogger.logger.info("=====================>sql" + sql);
            list = this.entityManager.createNativeQuery(sql).getResultList();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return list;
	}
	
	
	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<Object[]> divisionWiseMrCount(ModelMap model, String currentDate)
	{
		List list=null;
		try 
		{
			String sql="SELECT p.divisionname,p.sdocodeval, sum(dataval), sum(ddd) FROM("+" "
+"SELECT lct.division as divisionname,substr(cast(hhm.sdo_code as varchar), 0,3) as sdocodeval,count(DISTINCT hhm.bmd_reading)as dataval,count(DISTINCT hhm.device_id)as ddd,ROW_NUMBER() OVER"+" " 
+"(PARTITION BY SDO_CODE ORDER BY SDO_CODE DESC"+" "
+")AS rownumber FROM PHOTOBILLING.HHBM_DOWNLOAD hhm, vcloudengine.location lct WHERE to_date(SUBSTR(BILLDATE,0,11),'dd/MM/yyyy')=to_date('"+currentDate+"','dd/MM/yyyy') AND"+" "
  +"cast(lct.sitecode as varchar)=hhm.sdo_code GROUP BY lct.division,hhm.sdo_code) p WHERE (rownumber =1) GROUP BY p.divisionname,p.sdocodeval ORDER BY p.sdocodeval";
			list=entityManager.createNativeQuery(sql).getResultList();
			model.put("mrLiveList",list);
			model.put("cuurentDate",currentDate);
			if(list.size()==0)
			{
				model.put("results","No Data is Available for the selected date.");
			}
			else
			{
				model.put("results","notDisplay");
			}
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return list;
	}
	
	
	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<Object[]> showMrTrackLive(String divSdo, String date, ModelMap model)
	{
        List list = null;
        ArrayList<Object[]> list1 = new ArrayList<Object[]>();
        try {
            String dateVal = new SimpleDateFormat("dd/MM/yyyy").format(new SimpleDateFormat("dd-MM-yyyy").parse(date));
            String month = new SimpleDateFormat("yyyyMM").format(new SimpleDateFormat("dd-MM-yyyy").parse(date));
            String sql = "SELECT ABC.*,BCC.billedToday,CDF.billedInMonth FROM ( SELECT aa.*,(SELECT SECTION FROM vcloudengine.location WHERE cast(sitecode as varchar) like AA.sitecode) FROM ( SELECT BMD_READING as mrcode  ,cast(SDO_CODE as VARCHAR) as sitecode,consumer_sc_no,billdate as hBilldate ,takentime, BILL_MONTH as yearmonth, longitude,lattitude, device_id,ROW_NUMBER() OVER ( PARTITION BY BMD_READING,SDO_CODE ORDER BY SDO_CODE,BMD_READING,billdate DESC ) AS rownumber FROM   PHOTOBILLING.HHBM_DOWNLOAD WHERE BILLDATE like '" + dateVal + "%' AND bill_month = '" + month + "' " + " " + "AND SDO_CODE like '" + divSdo + "%'" + " " + ")AA WHERE AA.rownumber=1 ORDER BY AA.sitecode" + " " + ")ABC" + " " + "LEFT JOIN" + " " + "(SELECT AA.mrcode,AA.sitecode,count(CASE WHEN AA.hBilldate like '" + dateVal + "%' THEN 0 END)as billedToday,count(CASE WHEN AA.yearmonth like '" + month + "' THEN 0 END)as billedInMonth FROM" + " " + "(" + " " + "SELECT BMD_READING as mrcode  ,cast(SDO_CODE as VARCHAR)" + " " + "as sitecode,consumer_sc_no,billdate as hBilldate,bill_month as yearmonth   FROM  " + " " + "PHOTOBILLING.HHBM_DOWNLOAD WHERE BILLDATE like '" + dateVal + "%' AND bill_month = '" + month + "' " + " " + "AND SDO_CODE like '" + divSdo + "%'" + " " + ")AA GROUP BY AA.mrcode,AA.sitecode ORDER BY AA.sitecode" + " " + ")BCC ON  ABC.mrcode=BCC.mrcode AND ABC.sitecode=BCC.sitecode" + " " + "LEFT JOIN" + " " + "(" + " " + "SELECT BMD_READING as mrcode ,cast(SDO_CODE as VARCHAR) as sitecode,count(consumer_sc_no)as billedInMonth " + " " + "FROM   PHOTOBILLING.HHBM_DOWNLOAD WHERE  bill_month ='" + month + "'  AND SDO_CODE like '" + divSdo + "%' GROUP BY BMD_READING,SDO_CODE" + " " + ")CDF ON  ABC.mrcode=CDF.mrcode AND ABC.sitecode=CDF.sitecode";
            BCITSLogger.logger.info("=-=================================sql>" + sql);
            String mrCodeVal = "(";
            list = this.entityManager.createNativeQuery(sql).getResultList();
            int i = 0;
            while (i < list.size()) {
                Object[] data = (Object[])list.get(i);
                int j = 0;
                while (j < data.length) {
                    if (j == 0) {
                        mrCodeVal = String.valueOf(mrCodeVal) + "'" + data[j].toString() + "'" + ",";
                    }
                    ++j;
                }
                ++i;
            }
            mrCodeVal = String.valueOf(mrCodeVal) + ")";
            BCITSLogger.logger.info("===============================>mrCodeVal" + mrCodeVal.replace(",)", ")"));
            ConnectionClass con = new ConnectionClass();
            con.getconnection();
            List mrDataList = this.masterOracleService.showMrMobileDetails(String.valueOf(divSdo) + "%", mrCodeVal.replace(",)", ")"));
            int i2 = 0;
            while (i2 < list.size()) {
                Object[] data = (Object[])list.get(i2);
                Object[] newArr = Arrays.copyOf(data, data.length + 2);
                int j = 0;
                while (j < newArr.length) {
                    int m = 0;
                    while (m < mrDataList.size()) {
                        Object[] mrData = (Object[])mrDataList.get(m);
                        int k = 0;
                        while (k < mrData.length) {
                            if (mrData[0].toString().equalsIgnoreCase(newArr[0].toString())) {
                                if (j == newArr.length - 2) {
                                    newArr[j] = mrData[1];
                                }
                                if (j == newArr.length - 1) {
                                    newArr[j] = mrData[2];
                                }
                            }
                            ++k;
                        }
                        ++m;
                    }
                    ++j;
                }
                list1.add(newArr);
                ++i2;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return list1;}
	
	


    @Override
    public List<Map<String, Object>> showMrTrackLiveMobile(String divSdo, String date) {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        List<?> list =null;
        try
        {
            String sql="SELECT A.*,(SELECT SECTION FROM vcloudengine.location WHERE cast(sitecode as varchar) like A.sitecode) FROM("+" "
+"SELECT * FROM(SELECT cast(BMD_READING as VARCHAR) ,cast(SDO_CODE as VARCHAR) as sitecode,cast(consumer_sc_no as VARCHAR),cast(billdate as VARCHAR),cast(takentime as VARCHAR),cast(BILL_MONTH as VARCHAR),"+" "
+"cast(longitude as VARCHAR),cast(lattitude as VARCHAR),device_id,ROW_NUMBER() OVER ("+" "
+"PARTITION BY BMD_READING,SDO_CODE ORDER BY SDO_CODE,BMD_READING,billdate DESC"+" "
+") AS rownumber FROM PHOTOBILLING.HHBM_DOWNLOAD WHERE to_date(SUBSTR(BILLDATE,0,11),'dd/MM/yyyy')=to_date('"+date+"','dd/MM/yyyy') AND SDO_CODE like '"+divSdo+"%'"+" "
+"ORDER BY to_timestamp(takentime, 'HH12:MI PM') DESC,SDO_CODE,BMD_READING ASC) p WHERE (rownumber =1)"+" "
+")AS A";

BCITSLogger.logger.info("=-=================================sql>"+sql);
 list=entityManager.createNativeQuery(sql).getResultList();
                for(Iterator<?> iterator=list.iterator();iterator.hasNext();){
                    Object[] obj=(Object[]) iterator.next();
                    Map<String, Object> map=new HashMap<String, Object>();
                    map.put("mrcode", obj[0]);
                    map.put("SDO_CODE", obj[1]);
                    map.put("consumer_sc_no", obj[2]);
                    map.put("billdate", obj[3]);
                    map.put("takentime", obj[4]);
                    map.put("billmonth", obj[5]);
                    map.put("longitude", obj[6]);
                    map.put("lattitude", obj[7]);
                    result.add(map);
                }


        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return result;
    } 
    
    @Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<Object[]> getMrPerfromance(String sdocode, String date)
	{
		List list=null;
		try 
		{
			String sql="";
			list=entityManager.createNativeQuery(sql).setMaxResults(10).getResultList();
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<HHBM_DownloadEntity> viewConsumerOnMap(String rrno, String billmonth, String sdocode, ModelMap model)
	{
		List<HHBM_DownloadEntity> list=null;
		try 
		{
			String sql="SELECT hd FROM HHBM_DownloadEntity hd, HHBM_ConsumerEntity hc WHERE hc.consumer_sc_no=hd.consumer_Sc_No and hc.billMonth=hd.billMonth and TRIM(hd.consumer_Sc_No) LIKE '"+rrno.trim()+"' and TRIM(hd.billMonth) like '"+billmonth.trim()+"' and TRIM(hd.sdoCode) like '"+sdocode.trim()+"'";
			list=entityManager.createQuery(sql).getResultList();
			
			BCITSLogger.logger.info("========================>list"+list.size());
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<HHBM_DownloadEntity> viewConsumerOnMap1(String rrno, String billmonth, String sdocode, String date,ModelMap model)
	{
		List<HHBM_DownloadEntity> list=null;
		try 
		{
			String sql="SELECT hd FROM HHBM_DownloadEntity hd WHERE  TRIM(hd.consumer_Sc_No) LIKE '"+rrno.trim()+"' and TRIM(hd.billMonth) like '"+billmonth.trim()+"' and TRIM(hd.sdoCode) like '"+sdocode.trim()+"' and "
					+ "TRIM(SUBSTR(hd.billDate,0,11))='"+date+"'";
			list=entityManager.createQuery(sql).getResultList();
			
			BCITSLogger.logger.info("========================>list"+list.size());
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return list;
	}
	
	
	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public Object getDeviceHealth(String mrCode, String sdoCode, String date)
	{
		List<Object[]> list=null;
		Object startTime="";
		Object endTime="";
		String newTime="";
		List<String> Battery=new ArrayList<String>();
		List<String> Signal=new ArrayList<String>();
		List<String> totMemory=new ArrayList<String>();
		List<String> MemoryAvl=new ArrayList<String>();
		TreeSet <String> takeTimeVal=new TreeSet<String>();
		TreeSet<Object[]> countBilled1=new TreeSet<Object[]>();
		List<Object[]> countBilled=null;
		Date  startTimeNew=null;
		Date  endTimeNew=null;
		Map<String, Object> data=null;
		//SimpleDateFormat df = new SimpleDateFormat("HH:mm");
		List<String> takenTime=new ArrayList<String>();
		List<Object[]> mobileData=null;
		String billedTime="(";
		try 
		{
			/*
			String sql="SELECT to_char(to_timestamp(takentime, 'HH24:MI PM'),'HH24:MI') FROM photobilling.hhbm_download WHERE bmd_reading='"+mrCode+"' AND sdo_code='"+sdoCode+"' AND to_date(billdate,'dd/MM/yyyy')=to_date('"+date+"','dd/MM/yyyy') ORDER BY to_timestamp(takentime, 'HH24:MI PM')";
			list=entityManager.createNativeQuery(sql).getResultList();
			for (int i = 0; i < list.size(); i++)
			{
				if(i==0)
				{
					startTime=list.get(0);
					endTime=list.get(list.size()-1);
					BCITSLogger.logger.info("=======================>startTime"+startTime+""+endTime);
					    startTimeNew = df.parse((String) startTime); 
					    endTimeNew = df.parse((String) endTime); 
					  Calendar cal = Calendar.getInstance();
					  cal.setTime(startTimeNew);
					  cal.add(Calendar.MINUTE, 30);
					   newTime = df.format(cal.getTime());
					   timeVal.add(newTime);
				}
				  BCITSLogger.logger.info("df.parse((String) newTime).before(endTimeNew)"+df.parse((String) newTime).before(endTimeNew));
				  if(df.parse((String) newTime).before(endTimeNew)||df.parse((String) newTime).equals(endTimeNew))
				  {
					  Calendar cal = Calendar.getInstance();
					  cal.setTime(df.parse((String) newTime));
					  cal.add(Calendar.MINUTE, 30);
					   newTime = df.format(cal.getTime());
					   timeVal.add(newTime);
				  }
				  
			}
			 data=new HashMap<String, Object>();
			data.put("timeVal", timeVal);
			for (String string : timeVal) 
			{
				  BCITSLogger.logger.info("=======================>string"+string);
			}
		*/
			String cuurentDate=new SimpleDateFormat("dd/MM/yyyy").format(new SimpleDateFormat("dd-MM-yyyy").parse(date));
			String yearMonth=new SimpleDateFormat("yyyyMM").format(new SimpleDateFormat("dd-MM-yyyy").parse(date));
			String sql="SELECT (substr(to_char(to_timestamp(takentime, 'HH24:MI PM'),'HH24:MI'),0,3))as timetaken,"+" "
+"min( to_char(to_timestamp(takentime, 'HH24:MI PM'),'HH24:MI')) mintime,"+" "
+"max( to_char(to_timestamp(takentime, 'HH24:MI PM'),'HH24:MI')) maxtime"+" "
+"FROM photobilling.hhbm_download WHERE bmd_reading='"+mrCode+"' AND sdo_code='"+sdoCode+"'"+" "
+" AND billdate like '"+cuurentDate+"%' AND bill_month='"+yearMonth+"'"+" "
+"group by timetaken"+" "
+"order by timetaken";	
			list=entityManager.createNativeQuery(sql).getResultList();
			for (int i = 0; i < list.size(); i++)
			{
				Object[] str=list.get(i);
				for (int j = 0; j < str.length; j++)
				{
					if(j==1||j==2)
					{
						takenTime.add(str[j].toString());
					}
				}
			}
			Collections.sort(takenTime);
			
			for (int i = 0; i < takenTime.size(); i++)
			{
				//BCITSLogger.logger.info("==================>takenTime"+takenTime.get(i));
				takeTimeVal.add(takenTime.get(i));
				if(i!=takenTime.size()-1)
                {
					billedTime=billedTime+"'"+takenTime.get(i)+"',";
                }
                else
                {
                	billedTime=billedTime+"'"+takenTime.get(i)+"'";
                } 
			}
			billedTime=billedTime+")";
            //BCITSLogger.logger.info("============>billedTime"+billedTime); 
            
            String sql2="SELECT ROW_NUMBER()OVER (ORDER BY  takentime) as slno,to_char(to_timestamp(takentime, 'HH24:MI PM'),'HH24:MI')as dd,cast(MAX(extra6)as varchar) FROM photobilling.hhbm_download WHERE bmd_reading='"+mrCode+"' AND sdo_code='"+sdoCode+"'"+" "
 +"AND  billdate like '"+cuurentDate+"%' AND to_char(to_timestamp(takentime, 'HH24:MI PM'),'HH24:MI') IN "+billedTime+" AND bill_month='"+yearMonth+"' GROUP BY takentime ORDER BY dd";
            mobileData=entityManager.createNativeQuery(sql2).getResultList();
            for (int i = 0; i < mobileData.size(); i++)
            {
            	Object[] str=mobileData.get(i);
            	for (int j = 0; j < str.length; j++) 
            	{
					if(j==2)
					{
						String[] deviceValues=str[j].toString().split(",");
						 if(deviceValues.length==4)
						 {
							   Battery.add(deviceValues[0]);
								Signal.add(deviceValues[1]);
								MemoryAvl.add(deviceValues[3]);
						 }
						
					}
				}
			}
            
            String sql3="select max(slno) as slno, takentimeval, max(substr) as substr, noofinst from"+" " 
+"(SELECT ROW_NUMBER()OVER (ORDER BY  deviceData) as slno,takenTimeVal,replace(substr(deviceData ,0,4),',','')as substr,("+" "  
+"SELECT  count(*) as noofinst FROM photobilling.hhbm_download WHERE bmd_reading='"+mrCode+"' AND sdo_code='"+sdoCode+"' AND billdate like '"+cuurentDate+"%'"+" " 
+" and to_timestamp(takentime, 'HH24:MI PM')<=to_timestamp(takenTimeVal, 'HH24:MI PM') and bill_month='"+yearMonth+"'"+" " 
+")FROM "+" " 
+"( SELECT to_char(to_timestamp(takentime, 'HH24:MI PM'),'HH24:MI')as takenTimeVal,extra6 as deviceData FROM photobilling.hhbm_download WHERE bmd_reading='"+mrCode+"' AND sdo_code='"+sdoCode+"' AND billdate like '"+cuurentDate+"%' AND  bill_month='"+yearMonth+"' and"+" "   
+"to_char(to_timestamp(takentime, 'HH24:MI PM'),'HH24:MI') IN "+" " 
+""+billedTime+")AA)ab"+" " 
+"group by takentimeval, noofinst "+" " 
+"order by takentimeval";
            countBilled=entityManager.createNativeQuery(sql3).getResultList();
            
            /*for (Iterator iterator = takeTimeVal.iterator(); iterator.hasNext();)
            {
				Object objects =  iterator.next();
					BCITSLogger.logger.info("===================>objects"+objects);
				
			}*/
            
            data=new HashMap<String, Object>();
            data.put("takeTimeVal", takeTimeVal);
            data.put("Battery", Battery);
            data.put("Signal", Signal);
            data.put("MemoryAvl", MemoryAvl);
            data.put("countBilled", countBilled);
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return data;
	}
	
	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public List showDevicesNotLiveToday(String month, String currentDate,
			ModelMap model)
	{
		List list=null;
		try 
		{
			String sql="SELECT BB.deviceid,BB.astat, BB.aflag,BB.sitecode, BB.sectionval, COALESCE(BB.mrcodes,'') FROM "+" "
					+"("+" "
					+"SELECT AA.*,"+" "
					+"("+" "
					+"SELECT section FROM vcloudengine.location WHERE sitecode=AA.sitecode"+" "
					+") as sectionval,("+" "
					+"SELECT "+" "
					+"mrcode"+" "
					+" FROM vcloudengine.deviceallocation WHERE deviceid=AA.deviceid "+" "
					+") as mrcodes FROM"+" "
					+"("+" "
					+" SELECT  devm.deviceid as deviceid, devm.approvalstatus as astat, devm.allocatedflag as aflag,devm.sdocode as sitecode FROM vcloudengine.devicemaster devm"+" "
					+" WHERE  devm.deviceid NOT IN"+" "
					+" (SELECT DISTINCT hhmd.device_id FROM photobilling.hhbm_download hhmd WHERE   hhmd.bill_month='"+month+"')"+" "
					+")AA ORDER BY AA.sitecode"+" "
					+")BB";

			list=entityManager.createNativeQuery(sql).getResultList();
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<Object[]> getDataForDeviceHealth(String deviceid, String billdate,ModelMap model)
	{
		
		List<Object[]> list=null;
		try 
		{
			String billDate=new SimpleDateFormat("dd/MM/yyyy").format(new SimpleDateFormat("yyyy-MM-dd").parse(billdate));
			String billmonth=new SimpleDateFormat("yyyyMM").format(new SimpleDateFormat("yyyy-MM-dd").parse(billdate));
			String sql="SELECT AA.*,(SELECT section FROM vcloudengine.location WHERE sitecode=AA.sitecode) FROM"+" "
+"("+" "
+"SELECT DISTINCT bmd_reading, cast(sdo_code as int) as sitecode FROM photobilling.hhbm_download WHERE device_id like '"+deviceid+"%' AND bill_month='"+billmonth+"'"+" "
+" AND billdate LIKE '"+billDate+"%'"+" "
+")AA";
			BCITSLogger.logger.info("=-=================================sql>"+billdate+""+sql);
			list=entityManager.createNativeQuery(sql).getResultList();
			for (int i = 0; i < list.size(); i++) 
			{
				Object[] data=list.get(i);
				model.put("DeviceSiteCode", data[1]);
				model.put("DeviceBmr", data[0]);
				model.put("dateOfBill",new SimpleDateFormat("dd-MM-yyyy").format(new SimpleDateFormat("yyyy-MM-dd").parse(billdate)));
				model.put("DeviceSection", data[2]);
			}
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return list;
	}
	
	/*@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public List secRealTimeStatus(ModelMap model)
	{
		List list=null;
		
		try 
		{
			
			String sql="select SITECODE as SECTION_CODE, SECTION as SECTION_CODE, sum(INST) AS TOTAL_NO_CONSUMER,"
					    + "sum(BILLED)as TOTAL_BILLED,sum(TOBEBILLED) as TOTAL_TOBE_BILLED from BSMART.DASHBOARDTREE_NEW"
					    + " where SECTION is not null group by SITECODE,SECTION order by SITECODE,SECTION;";
			BCITSLogger.logger.info("=-===========secRealTimeStatus query=====================>>>"+sql);
			list=entityManager.createNativeQuery(sql).getResultList();
			model.put("secRealTimeList",list);
			if(list.size()==0)
			{
				model.put("results","No Data is Available");
			}
			else
			{
				model.put("results","notDisplay");
			}
		} 
		catch (Exception e)
		{
			model.put("results","Error while processing.");
		}
		return list;
	}*/

	@Override
	public BigInteger getMrWiseBilledTodaySectionWise(String mrcode,String sdocode) {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String formattedDate = sdf.format(date);
		String query="SELECT COUNT(*) AS BILLEDDAY FROM photobilling.hhbm_download WHERE sdo_code = '"+sdocode+"' and bmd_reading = '"+mrcode+"' and billdate like '"+formattedDate+"%"+"'";
		System.out.println(" QUERY MR WISE BILLED TODAY " + query);
		return (BigInteger) entityManager.createNativeQuery(query).getSingleResult();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void deviceUsageRpt(ModelMap model, String currentMonth)
	{

		List list1=null;
		List list2=null;
		
		String sql1="SELECT DD.SDOCODE,SUm(DD.TOtaDevice)totdevices,cast(SUm(DD.lt500) as int) as sum1,cast(SUM(DD.bt500100) as int) as sum2,cast(SUM(DD.bt10002500) as int) as sum3,cast(SUM(DD.gt2500) as int) as sum4," +
				"(SELECT division FROM vcloudengine.location WHERE substr(newsitecode||'',0,3)=DD.SDOCODE||'' LIMIT 1)AS DIVSION," +
				"(SELECT count(dev.deviceid) FROM vcloudengine.location lc, vcloudengine.devicemaster dev WHERE lc.sitecode=dev.sdocode" +
				" AND substr(newsitecode||'',0,3)=DD.SDOCODE||'')as TOtaDevice" +
				" FROM" +
				"(" +
				"SELECT substr(CC.SDOCODE||'',0,3)as sdocode,CC.TOtaDevice,(CC.lt500)lt500,(CC.bt500100)bt500100,(CC.bt10002500)bt10002500,(CC.gt2500)gt2500 FROM" +
				"(" +
				"SELECT" +
				"(SELECT section FROM vcloudengine.location WHERE newsitecode||''=BB.SDOCODE||'')AS SECTION," +
				"(SELECT count(dev.deviceid) FROM vcloudengine.location lc, vcloudengine.devicemaster dev WHERE lc.sitecode=dev.sdocode" +
				" AND newsitecode||''=BB.SDOCODE||'')as TOtaDevice," +
				"BB.* FROM" +
				"(" +
				"SELECT AA.divcode as SDOCODE," +
				"count(CASE WHEN aa.bills BETWEEN 1 AND 500 THEN 0 END)as lt500," +
				"count(CASE WHEN aa.bills BETWEEN 500 AND 1000 THEN 0 END)bt500100," +
				"count(CASE WHEN aa.bills BETWEEN 1000 AND 2500 THEN 0 END)bt10002500," +
				"count(CASE WHEN aa.bills>2500 THEN 0 END)as gt2500 " +
				" FROM " +
				"(" +
				"SELECT lc.newsitecode as divcode ,h.sdo_code  ,h.device_id as deviceid,count(*)as bills FROM " +
				"photobilling.hhbm_download h ,vcloudengine.location lc WHERE lc.sitecode||''=h.sdo_code AND h.bill_month='"+currentMonth+"' AND lc.newsitecode||'' like '%'" +
				"GROUP BY lc.newsitecode,h.sdo_code,h.device_id  " +
				")AA GROUP BY AA.divcode" +
				")BB" +
				")CC " +
				")DD GROUP BY DD.SDOCODE";
		
		BCITSLogger.logger.info("Divisionwise DevUsage Report Query====> "+sql1);
		list1=entityManager.createNativeQuery(sql1).getResultList();
		model.put("divisionDivUsageRptList",list1);
		model.put("currentMonth",currentMonth);
		
		if(list1.size()==0)
		{
			model.put("results","No Data is Available for the selected month.");
		}
		else
		{
			model.put("results","notDisplay");
		}
	

			String sql2="SELECT" +
					"(SELECT section FROM vcloudengine.location WHERE newsitecode||''=BB.SDOCODE||'')AS SECTION," +
					"(SELECT count(dev.deviceid) FROM vcloudengine.location lc, vcloudengine.devicemaster dev WHERE lc.sitecode=dev.sdocode" +
					" AND newsitecode||''=BB.SDOCODE||'')as TOtaDevice," +
					"BB.* FROM" +
					"(" +
					"SELECT AA.divcode as SDOCODE," +
					"count(CASE WHEN aa.bills BETWEEN 1 AND 500 THEN 0 END)as lt500," +
					"count(CASE WHEN aa.bills BETWEEN 500 AND 1000 THEN 0 END)bt500100," +
					"count(CASE WHEN aa.bills BETWEEN 1000 AND 2500 THEN 0 END)bt10002500," +
					"count(CASE WHEN aa.bills>2500 THEN 0 END)as gt2500 " +
					" FROM " +
					"(" +
					"SELECT lc.newsitecode as divcode ,h.sdo_code  ,h.device_id as deviceid,count(*)as bills FROM " +
					"photobilling.hhbm_download h ,vcloudengine.location lc WHERE lc.sitecode||''=h.sdo_code AND h.bill_month='"+currentMonth+"' AND lc.newsitecode||'' like '%'" +
					"GROUP BY lc.newsitecode,h.sdo_code,h.device_id  " +
					")AA GROUP BY AA.divcode" +
					")BB";
			
			BCITSLogger.logger.info("Sectionwise DevUsage Report Query====> "+sql2);
			list2=entityManager.createNativeQuery(sql2).getResultList();
			model.put("divUsageRptList",list2);
			model.put("currentMonth",currentMonth);
			
			if(list2.size()==0)
			{
				model.put("results","No Data is Available for the selected month.");
			}
			else
			{
				model.put("results","notDisplay");
			}
			
		
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List deviceProviderRpt(ModelMap model, String currentMonth)
	{

		List list1=null;
				
		String sql1="SELECT RPT.*, " +
				"(SELECT count(dev.deviceid) FROM vcloudengine.location lc, vcloudengine.devicemaster dev WHERE lc.sitecode=dev.sdocode" +
				" AND substr(newsitecode||'', 0 ,3)=RPT.sitecode AND dev.providedby IN ('BCITS','CESC','OTHERS'))as TOtaDevice," +
				"(SELECT division FROM vcloudengine.location WHERE substr(newsitecode||'', 0 ,3)=RPT.sitecode LIMIT 1) FROM" +
				"(" +
				"SELECT AA.sitecode,count(case WHEN providedby='CESC' THEN AA.deviceid END )as CESC,count(case WHEN providedby='BCITS' THEN AA.deviceid END )as BCITS" +
				",count(case WHEN providedby='OTHERS' THEN AA.deviceid END )as OTHER FROM" +
				"(" +
				"   SELECT substr(l.newsitecode||'', 0 ,3)as sitecode,substr(d.sdocode||'', 0 ,3),providedby,deviceid FROM vcloudengine.devicemaster d, vcloudengine.location l" +
				" WHERE d.sdocode=l.sitecode" +
				")" +
				"AA GROUP BY AA.sitecode ORDER BY AA.sitecode" +
				")RPT";
		
		BCITSLogger.logger.info("deviceProviderRpt  Report Query====> "+sql1);
		list1=entityManager.createNativeQuery(sql1).getResultList();
		model.put("devProviderRptList",list1);
		model.put("currentMonth",currentMonth);
		
		if(list1.size()==0)
		{
			model.put("results","No Data is Available for the selected month.");
		}
		else
		{
			model.put("results","notDisplay");
		}
	
		return list1;
	}
	
	/*@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List mrTypeRpt(ModelMap model, String currentMonth)
	{
		
		List list1=null;
		
		String sql1="SELECT SECTION, 0 AS TOTALDEVICES ," +
					"NVL(SUM(CASE WHEN MRTYPE LIKE 'MR' THEN 1 END),0) AS MR," +
					"NVL(SUM(CASE WHEN MRTYPE LIKE 'GVP' THEN 1 END),0) AS GVP," +
					"NVL(SUM(CASE WHEN MRTYPE LIKE 'OTHERS' THEN 1 END),0) AS OTHERS" +
					" FROM MRMASTER_BILLING" +
					" GROUP BY SECTION";
		
		BCITSLogger.logger.info("mrTypeRpt  Report Query====> "+sql1);
		list1=entityManager.createNativeQuery(sql1).getResultList();
		model.put("mrTypeRptList",list1);
		model.put("currentMonth",currentMonth);
		
		if(list1.size()==0)
		{
			model.put("results","No Data is Available for the selected month.");
		}
		else
		{
			model.put("results","notDisplay");
		}
		
		return list1;
	}*/

	@Override
	public List secRealTimeStatus(ModelMap model) {
		// TODO Auto-generated method stub
		return null;
	}
}

