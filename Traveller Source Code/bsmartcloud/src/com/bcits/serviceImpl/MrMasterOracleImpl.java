package com.bcits.serviceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bcits.entity.MrMasterOracleEntity;
import com.bcits.service.MrMasterOracleService;
import com.bcits.utility.BCITSLogger;

@Repository
public class MrMasterOracleImpl extends GenericServiceImplOracle<MrMasterOracleEntity> implements MrMasterOracleService
{

	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<Object[]> getMrmaster(String sdocode)
	{
		String sdocode1="\""+sdocode+"\"";
		List<Object[]> list=null;
		try 
		{
			String sql="SELECT MRCODE FROM "+sdocode1+".MRMASTER";
			BCITSLogger.logger.info("=================>"+sql);
			list=oracleEntityManager.createNativeQuery(sql).getResultList();
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<Object[]> getOracleMrmaster(String sdocode)
	{
		List<Object[]> list=null;
		try 
		{
			String sql="SELECT MRCODE FROM BSMART.MRMASTER_BILLING WHERE TRIM(SITECODE)='"+sdocode+"'";
			BCITSLogger.logger.info("=================>"+sql);
			list=oracleEntityManager.createNativeQuery(sql).getResultList();
			BCITSLogger.logger.info("=================>list.size()"+list.size());
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public Map<String, Object> showMrOracleDetails(String mrcode, String sdocode)
	{
		List<Object[]> list=null;
		Map<String, Object> data=null;
		try 
		{
			String sql="SELECT * FROM BSMART.MRMASTER_BILLING WHERE TRIM(SITECODE)='"+sdocode+"' AND TRIM(MRCODE)='"+mrcode+"'" ;
			BCITSLogger.logger.info("=================>"+sql);
			list=oracleEntityManager.createNativeQuery(sql).getResultList();
			data=new HashMap<String, Object>();
			for (int i = 0; i < list.size(); i++) 
			{
				Object[] str=list.get(i);
				for (int j = 0; j < str.length; j++) 
				{
					if(j==6)
					{
						data.put("MrName", str[j]);
					}
					if(j==7)
					{
						data.put("mobileNo", str[j]);
					}
				}
			}
			
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return data;
	}
	
	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<String[]> showMrMobileDetails(String sitecode, String mrCodeVal)
	{
		List<Object[]> list=null;
		String[] mrData=null;
		List<String[]> mrDataList=new ArrayList<String[]>();
		try 
		{
			String sql="SELECT * FROM BSMART.MRMASTER_BILLING WHERE TRIM(SITECODE) LIKE '"+sitecode+"' AND TRIM(MRCODE)IN "+mrCodeVal+"" ;
			BCITSLogger.logger.info("=================>"+sql);
			list=oracleEntityManager.createNativeQuery(sql).getResultList();
			for (int i = 0; i < list.size(); i++) 
			{
				Object[] str=list.get(i);
				mrData=new String[3];
				for (int j = 0; j < str.length; j++) 
				{
					if(j==5)
					{
						mrData[0]=str[j]==null?"":str[j].toString();
					}
					if(j==6)
					{
						mrData[1]=str[j]==null?"":str[j].toString();
					}
					if(j==7)
					{
							mrData[2]=str[j]==null?"":str[j].toString();
						
					}
				}
				mrDataList.add(mrData);
			}
			
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return mrDataList;
	}

}
