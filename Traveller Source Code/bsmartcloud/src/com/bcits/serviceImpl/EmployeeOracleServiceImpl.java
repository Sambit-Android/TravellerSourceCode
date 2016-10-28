package com.bcits.serviceImpl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

import com.bcits.entity.EmployeeOracleEntity;
import com.bcits.service.EmployeeOracleService;
import com.bcits.utility.BCITSLogger;
import com.bcits.utility.Encryption_MrLocator;

@Repository
public class EmployeeOracleServiceImpl  extends GenericServiceImplOracle<EmployeeOracleEntity> implements EmployeeOracleService {

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<?> getEmployeData(String userName,	String passWord, String dbSchema) {
	
       //String query = "SELECT t.DESIGNATION,t.USERTYPE,t.EMAIL,t.MOBILE,t.EMPNAME,t.SITECODE from "+dbSchema+".EMPLOYEE t where (t.username = '"+userName+"' and t.password = '"+passWord+"') ";

		String query = "SELECT DESIGNATION,USERTYPE from BSMART.EMPLOYEE  where (EMAIL='jayamma@gmail.com')";
		List<?> 	list = oracleEntityManager.createNativeQuery(query).getResultList();


		return list;
	}
	
	public List<Object[]> mobileCommonLogin(String userName, String passWord)
	{
		Encryption_MrLocator e = new Encryption_MrLocator();
		String query = "SELECT  DESIGNATION, USERTYPE, EMAIL, MOBILE, EMPNAME, SITECODE from BSMART.EMPLOYEE   where (UPPER(username) like'"+userName.toUpperCase()+"' and password = '"+e.encrypt(passWord)+"' and USERACTIVE='ACTIVE') ";
		List<Object[]> list = oracleEntityManager.createNativeQuery(query).getResultList();
		return list;
	}

	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public String showSecurityPwd(ModelMap model,HttpServletRequest request)
	{
		String pwd="";
		try 
		{
			String sql="SELECT PASSWORD FROM BSMART.SECURITY_LAUNCHER";
			 pwd=(String) oracleEntityManager.createNativeQuery(sql).getSingleResult();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return pwd;
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,value="txManageroracle")
	public int updateSecurityLauncherPassword(String newPwd)
	{
		int val=0;
		try 
		{
			 String sql="UPDATE BSMART.SECURITY_LAUNCHER SET PASSWORD='"+newPwd+"'";
			 val=oracleEntityManager.createNativeQuery(sql).executeUpdate();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return val;
	}
	
	
	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public List secRealTimeStatus(ModelMap model)
	{
		List list=null;
		
		try 
		{
			
			String sql="select cast(SITECODE as int), SECTION as SECTION, sum(INST) AS TOTAL_NO_CONSUMER,"
					    + "sum(BILLED)as TOTAL_BILLED,sum(TOBEBILLED) as TOTAL_TOBE_BILLED from BSMART.DASHBOARDTREE_NEW"
					    + " where SECTION is not null AND MONTHYEAR='2016-10' group by SITECODE,SECTION order by SITECODE,SECTION";
			BCITSLogger.logger.info("=-===========secRealTimeStatus query=====================>>>"+sql);
			list=oracleEntityManager.createNativeQuery(sql).getResultList();
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
	}
}
