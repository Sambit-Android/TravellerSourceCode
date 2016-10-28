package com.bcits.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bcits.entity.MatrixEntity;
import com.bcits.service.MrBilledRrnoService;

@Repository
public class MrBilledRrnoServiceImpl extends GenericServiceImplOracle<MatrixEntity> implements MrBilledRrnoService {

	
	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<?> getMrBilledRrnosMonth(String sdocode,String schema,String rdngmonth,String mrcode) 
	{
		String query="SELECT RDNGMONTH,RRNO,TARIFFDESC,CONSUMERNAME,CONSUMERADDRESS,CONSUMERADDRESS1,LATITUDE,LONGTITUTED,to_char(RDNGDATE,'DD/MM/YYYY') AS RDNGDATE,SOCODE,FEEDERCODE,TCCODE,METERSTATUS,BILLNO,to_char(BILLDATE,'DD/MM/YYYY HH:MM:SS') AS BILLDATE,NETAMTDUE" +
				" FROM "+schema+".MATRIX WHERE RDNGMONTH ='"+ rdngmonth +"'AND MRCODE ='"+ mrcode +"'";
		/*return oracleEntityManager.createNamedQuery("BillingProgressEntity.getMrBillingProgress").setParameter("sitecode", Integer.parseInt(sdocode)).getResultList();*/
		System.out.println(" QUERY FOR GETTING RRNOS " + query);
		return oracleEntityManager.createNativeQuery(query).getResultList();
	}

}
