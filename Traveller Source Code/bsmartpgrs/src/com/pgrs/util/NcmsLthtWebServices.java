package com.pgrs.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public class NcmsLthtWebServices {

	private static final Log logger = LogFactory.getLog(NcmsLthtWebServices.class);
	
	public static List<Map<String,Object>>  sendRequestNCMSPayment (String sitecode,String rrno) 
	{
		
		HttpURLConnection conn 		= 	null;
		String response 			= 	"error: " + "GET" + " failed ";
	    String url 					=   "http://182.72.76.246/jaxrs/payments/NCMS/"+sitecode+"/"+rrno;
	    String type 				=   "application/json";
	    String method 				=   "GET";
	    
		try
		{
			URL u = new URL(url);
	        conn = (HttpURLConnection)u.openConnection();
			conn.setRequestProperty("Content-Type", type);
			conn.setRequestProperty("Accept", type);
			conn.setRequestMethod(method);
			String line = "";
			StringBuffer sb = new StringBuffer();
			BufferedReader input = new BufferedReader(new InputStreamReader(conn.getInputStream()) );
			while((line = input.readLine()) != null)
			    sb.append(line);
			input.close();
			conn.disconnect();
			response=sb.toString();
			String responseFromServer=response;
			JSONObject obj 				=	null;
			obj = new JSONObject(responseFromServer);
			System.out.println("obj.length()"+obj.length());
			String ncmsPaymentInfo 	 =  obj.getString("ncmsPaymentInfo");
			logger.info("ncmsPaymentInfo------------------"+ncmsPaymentInfo);
			
			if(ncmsPaymentInfo.contains("norecords")){
				return null;
			}else{
			
			List<Map<String, Object>> payments = new ArrayList<Map<String, Object>>();
			String firstChar = String.valueOf(ncmsPaymentInfo.charAt(0));	
			
			if (firstChar.equalsIgnoreCase("[")) {
				
				JSONArray array 	=  new JSONArray(ncmsPaymentInfo);
				for (int i = 0; i < array.length(); i++) 
				{
					obj =  array.getJSONObject(i);
					Map<String, Object> data = getPaymentsByRRNo(obj);
					payments.add(data);
				}
			
			}else{
				JSONObject obj1 = new JSONObject(ncmsPaymentInfo);
				
				Map<String, Object> data = getPaymentsByRRNo(obj1);
				payments.add(data);
			}
			
			return payments;
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}
		finally 
		{
			try 
			{
				if (conn != null)
				conn.disconnect();
			}
			catch (Exception e) 
			{
				return null;
			}
		}
	}

	private static Map<String, Object> getPaymentsByRRNo(JSONObject obj) throws JSONException {
		Map<String, Object> data=new HashMap<>();
		data.put("rrno", obj.getString("rrno"));
		data.put("amount", obj.getString("amount"));
		data.put("recdate", obj.getString("recdate"));
		data.put("recno", obj.getString("recno"));
		data.put("towards", obj.getString("towards"));
		data.put("paymentmode", obj.getString("paymentmode"));
		data.put("entrydate", obj.getString("entrydate"));
		return data;
	}
	
	public static List<Map<String,Object>>  sendRequestNCMSBills (String sitecode,String rrno) 
	{
		
		HttpURLConnection conn 		= 	null;
		String response 			= 	"error: " + "GET" + " failed ";
	    String url 					=   "http://182.72.76.246/jaxrs/bills/NCMS/"+sitecode+"/"+rrno;
	    String type 				=   "application/json";
	    String method 				=   "GET";
	    
		try
		{
			URL u = new URL(url);
	        conn = (HttpURLConnection)u.openConnection();
			conn.setRequestProperty("Content-Type", type);
			conn.setRequestProperty("Accept", type);
			conn.setRequestMethod(method);
			String line = "";
			StringBuffer sb = new StringBuffer();
			BufferedReader input = new BufferedReader(new InputStreamReader(conn.getInputStream()) );
			while((line = input.readLine()) != null)
			    sb.append(line);
			input.close();
			conn.disconnect();
			response=sb.toString();
			String responseFromServer=response;
			JSONObject obj 				=	null;
			obj = new JSONObject(responseFromServer);
			System.out.println("obj.length()"+obj.length());
			String ncmsBillInfo 	 =  obj.getString("ncmsBillInfo");
			logger.info("ncmsBillInfo------------------"+ncmsBillInfo);
			
			if(ncmsBillInfo.contains("norecords")){
				return null;
			}else{
			
				List<Map<String, Object>> bills = new ArrayList<Map<String, Object>>();
				String firstChar = String.valueOf(ncmsBillInfo.charAt(0));	
				if (firstChar.equalsIgnoreCase("[")) {
					
					    JSONArray array =  new JSONArray(ncmsBillInfo);
						for (int i = 0; i < array.length(); i++) 
						{
							obj =  array.getJSONObject(i);
							Map<String, Object> data = getBillsByRRNumber(obj);
							bills.add(data);
						}
				
				}else{
					
					JSONObject obj1 = new JSONObject(ncmsBillInfo);
					
					Map<String, Object> data = getBillsByRRNumber(obj1);
					bills.add(data);
					
				}
			return bills;
		 }
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}
		finally 
		{
			try 
			{
				if (conn != null)
				conn.disconnect();
			}
			catch (Exception e) 
			{
				return null;
			}
		}
	}

	private static Map<String, Object> getBillsByRRNumber(JSONObject obj) throws JSONException {
		Map<String, Object> data=new HashMap<>();
		data.put("accountid", obj.getString("accountid"));
		data.put("rrno", obj.getString("rrno"));
		data.put("name", obj.getString("name"));
		data.put("address", obj.getString("address"));
		data.put("tariffdesc", obj.getString("tariffdesc"));
		data.put("sanctionkw", obj.getString("sanctionkw"));
		data.put("sanctionhp", obj.getString("sanctionhp"));
		data.put("sanctionkva", obj.getString("sanctionkva"));
		data.put("average", obj.getString("average"));
		data.put("rdngdate", obj.getString("rdngdate"));
		data.put("presentreading", obj.getString("presentreading"));
		data.put("previousreading", obj.getString("previousreading"));
		data.put("units", obj.getString("units"));
		data.put("mcunits", obj.getString("mcunits"));
		data.put("bm", obj.getString("bm"));
		data.put("meterstatus", obj.getString("meterstatus"));
		data.put("fcamt", obj.getString("fcamt"));
		data.put("ecamt", obj.getString("ecamt"));
		data.put("fac", obj.getString("fac"));
		data.put("tax", obj.getString("tax"));
		data.put("pfpen", obj.getString("pfpen"));
		data.put("penamt", obj.getString("penamt"));
		data.put("dramt", obj.getString("dramt"));
		data.put("auditarr", obj.getString("auditarr"));
		data.put("backbillarr", obj.getString("backbillarr"));
		data.put("others", obj.getString("others"));
		data.put("appamt", obj.getString("appamt"));
		data.put("auditmmd", obj.getString("auditmmd"));
		data.put("creditrebate", obj.getString("creditrebate"));
		data.put("arrears", obj.getString("arrears"));
		data.put("interest", obj.getString("interest"));
		data.put("dladjgiven", obj.getString("dladjgiven"));
		data.put("netamtdue", obj.getString("netamtdue"));
		data.put("pf", obj.getString("pf"));
		data.put("billingdemand", obj.getString("billingdemand"));
		data.put("penload", obj.getString("penload"));
		data.put("penunits", obj.getString("penunits"));
		data.put("bdf", obj.getString("bdf"));
		data.put("billdate", obj.getString("billdate"));
		data.put("billno", obj.getString("billno"));
		return data;
	}
	
	public static Map<String,Object>  sendRequestNCMSConsumer (String sitecode,String rrno) 
	{
		
		HttpURLConnection conn 		= 	null;
		String response 			= 	"error: " + "GET" + " failed ";
	    String url 					=   "http://182.72.76.246/jaxrs/consumer/NCMS/"+sitecode+"/"+rrno;
	    String type 				=   "application/json";
	    String method 				=   "GET";
	    
		try
		{
			URL u = new URL(url);
	        conn = (HttpURLConnection)u.openConnection();
			conn.setRequestProperty("Content-Type", type);
			conn.setRequestProperty("Accept", type);
			conn.setRequestMethod(method);
			String line = "";
			StringBuffer sb = new StringBuffer();
			BufferedReader input = new BufferedReader(new InputStreamReader(conn.getInputStream()) );
			while((line = input.readLine()) != null)
			    sb.append(line);
			input.close();
			conn.disconnect();
			response=sb.toString();
			String responseFromServer=response;
			JSONObject obj 				=	null;
			try 
			{
				obj = new JSONObject(responseFromServer);
				System.out.println("obj.length()"+obj.length());
				String ncmsConnectionInfo 	 =  obj.getString("ncmsConnectionInfo");
				
				if(ncmsConnectionInfo.contains("norecords")){
					return null;
				}else{
				
				
				System.out.println("ncmsConnectionInfo------------------"+ncmsConnectionInfo);
				
				JSONObject obj1 = new JSONObject(ncmsConnectionInfo);
				Map<String, Object> data=new HashMap<>();
				data.put("corporation", obj1.getString("corporation"));
				data.put("ward", obj1.getString("ward"));
				data.put("mrcode", obj1.getString("mrcode"));
				data.put("taluk", obj1.getString("taluk"));
				data.put("panchayath", obj1.getString("panchayath"));
				data.put("noofhouses", obj1.getString("noofhouses"));
				data.put("bkwh", obj1.getString("bkwh"));
				data.put("fc", obj1.getString("fc"));
				data.put("lddate", obj1.getString("lddate"));
				data.put("pddate", obj1.getString("pddate"));
				data.put("tod", obj1.getString("tod"));
				data.put("email", obj1.getString("email"));
				data.put("linemancode", obj1.getString("linemancode"));
				data.put("socode", obj1.getString("socode"));
				data.put("rrno", obj1.getString("rrno"));
				data.put("name", obj1.getString("name"));
				data.put("address", obj1.getString("address"));
				data.put("place", obj1.getString("place"));
				data.put("pincode", obj1.getString("pincode"));
				data.put("readindday", obj1.getString("readindday"));
				data.put("average", obj1.getString("average"));
				data.put("dateofservice", obj1.getString("dateofservice"));
				data.put("tariffdescription", obj1.getString("tariffdescription"));
				data.put("sanctionedkw", obj1.getString("sanctionedkw"));
				data.put("ctration", obj1.getString("ctration"));
				data.put("sanctionedhp", obj1.getString("sanctionedhp"));
				data.put("ctratiod", obj1.getString("ctratiod"));
				data.put("contractdemand", obj1.getString("contractdemand"));
				data.put("omcode", obj1.getString("omcode"));
				data.put("stationcode", obj1.getString("stationcode"));
				data.put("tccode", obj1.getString("tccode"));
				data.put("feedercode", obj1.getString("feedercode"));
				data.put("poleno", obj1.getString("poleno"));
				data.put("villagename", obj1.getString("villagename"));
				data.put("telephoneno", obj1.getString("telephoneno"));
				
				return data;
				
			}
				
			} 
			catch (JSONException e) 
			{
				e.printStackTrace();
			}
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			
		}
		finally 
		{
			try 
			{
				if (conn != null)
				conn.disconnect();
			}
			catch (Exception e) 
			{
				
			}
		}
		return null;
	}
	
	public static List<Map<String,Object>>  sendRequestNCMSDeposits (String sitecode,String rrno) 
	{
		
		HttpURLConnection conn 		= 	null;
		String response 			= 	"error: " + "GET" + " failed ";
	    String url 					=   "http://182.72.76.246/jaxrs/deposits/NCMS/"+sitecode+"/"+rrno;
	    String type 				=   "application/json";
	    String method 				=   "GET";
	    
		try
		{
			URL u = new URL(url);
	        conn = (HttpURLConnection)u.openConnection();
			conn.setRequestProperty("Content-Type", type);
			conn.setRequestProperty("Accept", type);
			conn.setRequestMethod(method);
			String line = "";
			StringBuffer sb = new StringBuffer();
			BufferedReader input = new BufferedReader(new InputStreamReader(conn.getInputStream()) );
			while((line = input.readLine()) != null)
			    sb.append(line);
			input.close();
			conn.disconnect();
			response=sb.toString();
			String responseFromServer=response;
			JSONObject obj 				=	null;
			obj = new JSONObject(responseFromServer);
			
			System.out.println("obj.length()"+obj.length());
			
			String ncmsDepositInfo 	 =  obj.getString("ncmsDepositInfo");
			logger.info("ncmsDepositInfo------------------"+ncmsDepositInfo);
			
			if(ncmsDepositInfo.contains("norecords")){
				return null;
			}else{
				List<Map<String, Object>> deposits = new ArrayList<Map<String, Object>>();
				String firstChar = String.valueOf(ncmsDepositInfo.charAt(0));	
			
				if (firstChar.equalsIgnoreCase("[")) {
					
					JSONArray array =  new JSONArray(ncmsDepositInfo);
					for (int i = 0; i < array.length(); i++) 
					{
						obj =  array.getJSONObject(i);
						Map<String, Object> data = getDepositsByRRNumber(obj);
						deposits.add(data);
					}
				
				}else{
					JSONObject obj1 = new JSONObject(ncmsDepositInfo);
					Map<String, Object> data = getDepositsByRRNumber(obj1);
					deposits.add(data);
				}
				
								
		
			
			return deposits;
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}
		finally 
		{
			try 
			{
				if (conn != null)
				conn.disconnect();
			}
			catch (Exception e) 
			{
				return null;
			}
		}
	}

	private static Map<String, Object> getDepositsByRRNumber(JSONObject obj1) throws JSONException {
		Map<String, Object> data=new HashMap<>();
		data.put("rrno", obj1.getString("rrno"));
		data.put("amount", obj1.getString("amount"));
		data.put("recdate", obj1.getString("recdate"));
		data.put("recno", obj1.getString("recno"));
		/*data.put("remarks", obj1.getString("remarks"));*/
		data.put("status", obj1.getString("status"));
		return data;
	}
	
}
