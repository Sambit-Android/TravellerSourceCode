package com.bcits.serviceImpl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bcits.entity.DisconnectionMasterEntity;
import com.bcits.entity.ResultDisconnection;
import com.bcits.service.DisConnectionReConnectionService;
import com.bcits.utility.BCITSLogger;

@Repository
public class DisConnectionReConnectionServiceImpl extends GenericServiceImplOracle<DisconnectionMasterEntity> implements DisConnectionReConnectionService 
{
	 
	 @Override
	 @Transactional(propagation=Propagation.REQUIRED)
	public ResultDisconnection updateReConnectionListMobile(int serverid,String remarks,Timestamp redate,String longitude,String latitude,byte[] image) {
		 ResultDisconnection disconnection=new ResultDisconnection();
		
		try {
			DisconnectionMasterEntity obj=find(serverid);
			obj.setRemarks(remarks);
			obj.setImage_recon(image);
			obj.setLongitude_recon(longitude);
			obj.setLatitude_recon(latitude);
			obj.setRedate(redate);
		
			DisconnectionMasterEntity a=update(obj);
			BCITSLogger.logger.info("updated status is" + "\t"
					+ a);
							disconnection.setServerid(serverid);
							disconnection.setStatus("updated");
							//list.add(disconnection);
							
					    } 
				catch (Exception e) {
					// TODO: handle exception
					disconnection.setServerid(serverid);
					disconnection.setStatus("notupdated");
					e.printStackTrace();
				}
					return disconnection;

				}
	 
	 @Override
		@Transactional(propagation = Propagation.SUPPORTS)
		public List<Map<String, Object>> getAllDisConnectionListData(final int listno, final String schema) 
		{
			List<Object[]> list=null;
			
			List<Map<String, Object>> approvalMap =new ArrayList<Map<String,Object>>() ;
			String query="SELECT id,rrno,listno,listdate,tariff,DISDATE,disfr,socode,remarks,dramt,rdngdate,mtrcode,arrears,username,datestamp,reclistno,recdate,ageing,"
					+ "status,consumername,consumeraddress,feedercode,tccode,poleno,latitude,longtituted"
					+ " FROM "+schema+".DISCONNECTIONMATRIX WHERE LISTNO = '"+listno+"' ";
			list=oracleEntityManager.createNativeQuery(query).getResultList();
	        for (Iterator iterator = list.iterator(); iterator.hasNext();)
	        {
				final Object[] objects = (Object[]) iterator.next();
					approvalMap.add(new HashMap<String, Object>()
				 {{
						    put("id",(objects[0]!=null)?objects[0].toString():"NA");
							put("rrno",(objects[1]!=null)?objects[1].toString():"NA");
							put("listno",(objects[2]!=null)?objects[2].toString():"NA");
							put("listdate",(objects[3]!=null)?objects[3].toString():"NA");
							put("tariff",(objects[4]!=null)?objects[4].toString():"NA");
							put("disdate",(objects[5]!=null)?objects[5].toString():"NA");
							put("disfr",(objects[6]!=null)?objects[6].toString():"NA");
							put("sdocode",(objects[7]!=null)?objects[7].toString():"NA");
							put("remarks",(objects[8]!=null)?objects[8].toString():"NA");
							put("dramt",(objects[9]!=null)?objects[9].toString():"NA");
							put("rdngdate",(objects[10]!=null)?objects[10].toString():"NA");
							put("mtrcode",(objects[11]!=null)?objects[11].toString():"NA");
							put("arrears",(objects[12]!=null)?objects[12].toString():"NA");
							put("username",(objects[13]!=null)?objects[13].toString():"NA");
							put("datestamp",(objects[14]!=null)?objects[14].toString():"NA");
							put("reclistno",(objects[15]!=null)?objects[15].toString():"NA");
							put("redate",(objects[16]!=null)?objects[16].toString():"NA");
							put("ageing",(objects[17]!=null)?objects[17].toString():"NA");
							put("status",(objects[18]!=null)?objects[18].toString():"NA");
							put("consumer_name",(objects[19]!=null)?objects[19].toString():"NA");
							put("address",(objects[20]!=null)?objects[20].toString():"NA");
							put("fdrcode",(objects[21]!=null)?objects[21].toString():"NA");
							put("tccode",(objects[22]!=null)?objects[22].toString():"NA");
							put("poleno",(objects[23]!=null)?objects[23].toString():"NA");
							put("latitude_dis",(objects[24]!=null)?objects[24].toString():"NA");
							put("longitude_dis",(objects[25]!=null)?objects[25].toString():"NA");
					 
							
							String   amountLast="NA",recDate="NA",recNo="",mode="NA";
							try {
							 String queryLastPayment="SELECT  AMOUNT, RECDATE, RECNO, PAYMENTMODE FROM "+schema+".LATESTPAYMENTS_MV WHERE RRNO = '"+objects[1].toString().trim()+"' ";
//							 System.out.println(queryLastPayment);
							 List<Object[]> listPayment =oracleEntityManager.createNativeQuery(queryLastPayment).getResultList();
							
							 if(listPayment.size()>0){
						        amountLast =listPayment.get(0)[0].toString();
							 	recDate=listPayment.get(0)[1].toString();
							 	recNo=listPayment.get(0)[2].toString();
							 	mode=listPayment.get(0)[3].toString();
								/*
								System.out.println("amount  "+amountLast);
								System.out.println("recDate  "+recDate);
								System.out.println("recNo  "+recNo);
								System.out.println("mode  "+mode);*/
							 }
							 
							} catch (Exception e) {
								e.printStackTrace();
							}
							
							 put("PAY_AMOUNT",amountLast); 
							 put("PAY_RECDATE",recDate); 
							 put("PAY_RECNO",recNo); 
							 put("PAY_MODE",mode); 
							
				 }}
					);
			}
			return approvalMap;
		}
	 @Override
	 @Transactional(propagation=Propagation.REQUIRED,value="txManageroracle")
	public ResultDisconnection updateDisConnectionListMobile(String schema,String type,int serverid, String remarks,String date, int disfr,int status,byte[] image) {
		 ResultDisconnection disconnection=new ResultDisconnection();

	try {
		String query;
		if(type.equals("DISCONNECTION")){ // DISCONNECTION and RECONNECTION
			 query="UPDATE "+schema+".DISCONNECTIONMASTER SET remarks='"+remarks+"',disdate=to_date('"+date+"','YYYY-MM-DD HH24:MI:SS'),disfr='"+disfr+"',status='"+status+"' WHERE ID='"+serverid+"'";
		}else{
			 query="UPDATE "+schema+".DISCONNECTIONMASTER SET remarks='"+remarks+"',recdate=to_date('"+date+"','YYYY-MM-DD HH24:MI:SS'),status='"+status+"' WHERE ID='"+serverid+"'";
		}


		System.out.println(query);

		int result = oracleEntityManager.createNativeQuery(query).executeUpdate();
		
		System.err.println("RESULT ============= "+result);
		
		if(result>0){ 
			disconnection.setServerid(serverid);
			disconnection.setStatus("updated");
			System.out.println("UPDATEDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD  serverid   "+ serverid);
		}else{
			System.err.println("FAILEEEEEEEEDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD  serverid   "+serverid);
		}
		
	} 
	catch (Exception e) {
		e.printStackTrace();
		disconnection.setServerid(serverid);
		disconnection.setStatus("notupdated");
	}
		return disconnection;

	}

	@Override
	public List<Map<String, Object>> getPaymentStatus(int listno,	String schema, String rrNo) {

		List<Object[]> list=null;
		List<Map<String, Object>> approvalMap =new ArrayList<Map<String,Object>>() ;
		String query="SELECT arrears,status "
				+ " FROM "+schema+".DISCONNECTIONMATRIX WHERE LISTNO = '"+listno+"' and rrno='"+rrNo+"' ";
		list=oracleEntityManager.createNativeQuery(query).getResultList();
        for (Iterator iterator = list.iterator(); iterator.hasNext();)
        {
			final Object[] objects = (Object[]) iterator.next();
				approvalMap.add(new HashMap<String, Object>()
			 {{
				 
					    put("arrears",(objects[0]!=null)?objects[0].toString():"NA");
						put("status",(objects[1]!=null)?objects[1].toString():"NA");
				 
			 }}
				);
		}
        
        System.err.println(approvalMap.toString());
		return approvalMap;
	
	}
}
