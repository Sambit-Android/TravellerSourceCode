package com.pgrs.serviceimpl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pgrs.core.dao.MasterGenericDAO;
import com.pgrs.dao.HelpDeskTicketDAO;
import com.pgrs.dao.PgrsReportDAO;
import com.pgrs.entity.TicketCategoryEntity;
import com.pgrs.service.PgrsReportService;

@Service
public class PgrsReportServiceImpl implements PgrsReportService{
	
	@Autowired
	private PgrsReportDAO pgrsReportDAO;
	
	@Autowired
	private HelpDeskTicketDAO helpDeskTicketDAO;
	
	@Autowired
	private MasterGenericDAO masterGenericDAO;
	
	@Override
	public List<?> getComplaintStatus(Date fromdate, Date todate,Set<String> siteCodes) {
		String sitecode="";
		Iterator<String> iter=siteCodes.iterator();
		while(iter.hasNext()){
			sitecode+="'"+iter.next()+"',";
		}
		sitecode="("+sitecode.substring(0,sitecode.length()-1)+")";
		List<?> res=pgrsReportDAO.getComplaintStatus(fromdate,todate,sitecode);
		List<Map<String, Object>> result=new ArrayList<>();
		long total=0;
		long pending=0;
		long assigned=0;
		long onhold=0;
		long resolved=0;
		long reopened=0;
		for(Iterator<?> iterator1=res.iterator();iterator1.hasNext();){
			Object[] obj1=(Object[]) iterator1.next();
			Map<String, Object> data=new HashMap<>();
			data.put("category", obj1[1]);
			data.put("pending", obj1[2]);
			data.put("assigned", obj1[3]);
			data.put("onhold", obj1[4]);
			data.put("resolved", obj1[5]);
			data.put("reopened", obj1[6]);
			long sum =((BigInteger)obj1[2]).longValue() +((BigInteger)obj1[3]).longValue()+((BigInteger)obj1[4]).longValue()+((BigInteger)obj1[5]).longValue()+((BigInteger)obj1[6]).longValue();
			total+=sum;
			pending=((BigInteger)obj1[2]).longValue()+pending;
			assigned=((BigInteger)obj1[3]).longValue()+assigned;
			onhold=((BigInteger)obj1[4]).longValue()+onhold;
			resolved=((BigInteger)obj1[5]).longValue()+resolved;
			reopened=((BigInteger)obj1[6]).longValue()+reopened;
			data.put("total", sum);
			result.add(data);
		}
		if(total!=0){
		Map<String, Object> data=new HashMap<>();
		data.put("category", "Grand Total");
		data.put("pending", pending);
		data.put("assigned",assigned);
		data.put("onhold",onhold);
		data.put("resolved", resolved);
		data.put("reopened", reopened);
		data.put("total",total);
		result.add(data);
		}
		return result;
	}
	@Override
	public List<?> viewComplaintStatusReport(String fromMonth, String toMonth,Set<String> siteCodes,String status, HttpServletRequest req) 
	{
		String sitecode="";
		Iterator<String> iter=siteCodes.iterator();
		while(iter.hasNext()){
			sitecode+="'"+iter.next()+"',";
		}
		java.text.NumberFormat df = new DecimalFormat("#0.00");
		sitecode="("+sitecode.substring(0,sitecode.length()-1)+")";
		List<?> res=pgrsReportDAO.viewComplaintStatusReport(fromMonth, toMonth, sitecode,status, req);
		List<Map<String, Object>> result=new ArrayList<>();
		for(Iterator<?> iterator1=res.iterator();iterator1.hasNext();){
			Object[] obj1=(Object[]) iterator1.next();
			Map<String, Object> data=new HashMap<>();
			data.put("category", obj1[1]);
			data.put("total", obj1[2]);
			data.put("resolved", obj1[3]);
			
			data.put("resPercentage", df.format(((Double.parseDouble(obj1[3].toString()))/(Double.parseDouble(obj1[2].toString()))*100.00)));
			result.add(data);
		}
		return result;
	}
	@Override
	public List<?> viewComplaintStatusGraphicalReport(String fromMonth, String toMonth,Set<String> siteCodes,String status, HttpServletRequest req) 
	{
		String sitecode="";
		Iterator<String> iter=siteCodes.iterator();
		while(iter.hasNext()){
			sitecode+="'"+iter.next()+"',";
		}
		sitecode="("+sitecode.substring(0,sitecode.length()-1)+")";
		return pgrsReportDAO.viewComplaintStatusReport(fromMonth, toMonth, sitecode,status, req);
	}
	@Override
	public List<?> showCusSatisfactionResults(String fromMonth, String toMonth,Set<String> siteCodes, HttpServletRequest req)
	{
		String sitecode="";
		Iterator<String> iter=siteCodes.iterator();
		while(iter.hasNext()){
			sitecode+="'"+iter.next()+"',";
		}
		sitecode="("+sitecode.substring(0,sitecode.length()-1)+")";
		List<?> res=pgrsReportDAO.showCusSatisfactionResults(fromMonth,toMonth,sitecode,req);
		List<Map<String, Object>> result=new ArrayList<>();
		for(Iterator<?> iterator1=res.iterator();iterator1.hasNext();){
			Object[] obj1=(Object[]) iterator1.next();
			Map<String, Object> data=new HashMap<>();
			data.put("category", obj1[1]);
			data.put("resolved", Math.round((Double.parseDouble(obj1[2].toString())+Double.parseDouble(obj1[3].toString())+Double.parseDouble(obj1[4].toString()))));
			data.put("good", obj1[2]);
			data.put("poor",obj1[3]);
			data.put("excellent",obj1[4]);
			result.add(data);
		}
		return result;
	}
	@Override
	public List<?> customerSatisfactionGraphicalReport(String fromMonth, String toMonth,Set<String> siteCodes, HttpServletRequest req)
	{
		String sitecode="";
		Iterator<String> iter=siteCodes.iterator();
		while(iter.hasNext()){
			sitecode+="'"+iter.next()+"',";
		}
		sitecode="("+sitecode.substring(0,sitecode.length()-1)+")";
		List<?> res=pgrsReportDAO.showCusSatisfactionResults(fromMonth,toMonth,sitecode,req);
		List<Object> result=new ArrayList<>();
		
		List<String> category=new ArrayList<>();
		List<Long> good=new ArrayList<>();
		List<Long> poor=new ArrayList<>();
		List<Long> excellent=new ArrayList<>();
		
		for(Iterator<?> iterator1=res.iterator();iterator1.hasNext();){
			Object[] obj1=(Object[]) iterator1.next();
			category.add((String)obj1[1]);
			good.add(((BigInteger)obj1[2]).longValue());
			poor.add(((BigInteger)obj1[3]).longValue());
			excellent.add(((BigInteger)obj1[4]).longValue());
			
		}
		result.add(category);
		List<Object> result1=new ArrayList<>();
		Map<String, Object> data=new HashMap<>();
		data.put("name","Good");
		data.put("data", good);
		result1.add(data);
		
		data=new HashMap<>();
		data.put("name","Poor");
		data.put("data", poor);
		result1.add(data);
		
		data=new HashMap<>();
		data.put("name","Excellent");
		data.put("data", excellent);
		result1.add(data);
		
		result.add(result1);
		return result;
	}
	
	@Override
	public List<?> accidentStatusReport(Date fromdate, Date todate,Set<String> siteCodes) {
		String sitecode="";
		Iterator<String> iter=siteCodes.iterator();
		while(iter.hasNext()){
			sitecode+="'"+iter.next()+"',";
		}
		sitecode="("+sitecode.substring(0,sitecode.length()-1)+")";
		List<?> res=pgrsReportDAO.getaccidentStatusReport(fromdate,todate,sitecode);
		List<Map<String, Object>> result=new ArrayList<>();
		long total=0;
		long pending=0;
		long assigned=0;
		long onhold=0;
		long resolved=0;
		long reopened=0;
		for(Iterator<?> iterator1=res.iterator();iterator1.hasNext();){
			Object[] obj1=(Object[]) iterator1.next();
			Map<String, Object> data=new HashMap<>();
			data.put("category", obj1[1]);
			data.put("pending", obj1[2]);
			data.put("assigned", obj1[3]);
			data.put("onhold", obj1[4]);
			data.put("resolved", obj1[5]);
			data.put("reopened", obj1[6]);
			long sum =((BigInteger)obj1[2]).longValue() +((BigInteger)obj1[3]).longValue()+((BigInteger)obj1[4]).longValue()+((BigInteger)obj1[5]).longValue()+((BigInteger)obj1[6]).longValue();
			total+=sum;
			pending=((BigInteger)obj1[2]).longValue()+pending;
			assigned=((BigInteger)obj1[3]).longValue()+assigned;
			onhold=((BigInteger)obj1[4]).longValue()+onhold;
			resolved=((BigInteger)obj1[5]).longValue()+resolved;
			reopened=((BigInteger)obj1[6]).longValue()+reopened;
			data.put("total", sum);
			result.add(data);
		}
		Map<String, Object> data=new HashMap<>();
		data.put("category", "Grand Total");
		data.put("pending", pending);
		data.put("assigned",assigned);
		data.put("onhold",onhold);
		data.put("resolved", resolved);
		data.put("reopened", reopened);
		data.put("total",total);
		result.add(data);
		return result;
	}
	@Override
	public List<?> esclatedStatusReport(Date fromdate, Date todate,Set<String> siteCodes) {
		String sitecode="";
		Iterator<String> iter=siteCodes.iterator();
		while(iter.hasNext()){
			sitecode+="'"+iter.next()+"',";
		}
		sitecode="("+sitecode.substring(0,sitecode.length()-1)+")";
		List<?> res=pgrsReportDAO.esclatedStatusReport(fromdate,todate,sitecode);
		List<Map<String, Object>> result=new ArrayList<>();
		for(Iterator<?> iterator1=res.iterator();iterator1.hasNext();){
			Object[] obj1=(Object[]) iterator1.next();
			Map<String, Object> data=new HashMap<>();
			//z.consumername, z.consumermobilenumber,z.docketnumber,z.docketcreateddatetime,z.address,z.categoryid, z.docketsummary,z.assignedto,z.subcategoryid, "
			//z.category_name  , z.subcategory_name, z.esc_level,y.name,y.dn_name,y.ur_login_name
			data.put("consumername", obj1[0]);
			data.put("consumermobilenumber", obj1[1]);
			data.put("docketnumber", obj1[2]);
			data.put("createdDate", new SimpleDateFormat("dd/MM/yyyy").format((Timestamp)obj1[3]));
			data.put("createdtime", new SimpleDateFormat("hh:mm a").format((Timestamp)obj1[3]));
			data.put("docketcreateddatetime", obj1[3]);
			data.put("address", obj1[4]);
			data.put("docketsummary", obj1[6]);
			data.put("category_name", obj1[9]);
			data.put("subcategory_name", obj1[10]);
			data.put("esc_level", obj1[11]);
			data.put("name", obj1[12]);
			data.put("dn_name", obj1[13]);
			data.put("ur_login_name", obj1[14]);
			data.put("ur_contact", obj1[15]);
			if(obj1[17]!=null){
				data.put("level1", (String)obj1[17]);
				data.put("level2", "");
				data.put("level3", "");
				data.put("level4", "");
				data.put("level5", "");
				data.put("level6", "");
			}else if(obj1[18]!=null){
				data.put("level2", (String)obj1[18]);
				data.put("level1", "");
				data.put("level3", "");
				data.put("level4", "");
				data.put("level5", "");
				data.put("level6", "");
			}else if(obj1[19]!=null){
				data.put("level3", (String)obj1[19]);
				data.put("level2", "");
				data.put("level1", "");
				data.put("level4", "");
				data.put("level5", "");
				data.put("level6", "");
			}else if(obj1[20]!=null){
				data.put("level4", (String)obj1[20]);
				data.put("level2", "");
				data.put("level3", "");
				data.put("level1", "");
				data.put("level5", "");
				data.put("level6", "");
			}else if(obj1[21]!=null){
				data.put("level5", (String)obj1[21]);
				data.put("level2", "");
				data.put("level3", "");
				data.put("level4", "");
				data.put("level1", "");
				data.put("level6", "");
			}else if(obj1[22]!=null){
				data.put("level6", (String)obj1[22]);
				data.put("level2", "");
				data.put("level3", "");
				data.put("level4", "");
				data.put("level5", "");
				data.put("level1", "");
			}
			
			result.add(data);
		}
		return result;
	}
	@Override
	public List<?> showKERCStatusReport(Date date1, Date date2,Set<String> siteCodes, HttpServletRequest req)
	{
		String sitecode="";
		Iterator<String> iter=siteCodes.iterator();
		while(iter.hasNext()){
			sitecode+="'"+iter.next()+"',";
		}
		sitecode="("+sitecode.substring(0,sitecode.length()-1)+")";
		List<?> res=pgrsReportDAO.showKERCStatusReport(date1,date2,sitecode);
		List<Map<String, Object>> result=new ArrayList<>();
		for(Iterator<?> iterator1=res.iterator();iterator1.hasNext();){
			Object[] obj1=(Object[]) iterator1.next();
			Map<String, Object> data=new HashMap<>();
			data.put("category", obj1[0]);
			data.put("totalComplaints",obj1[3]);
			data.put("totalResolved", obj1[4]);
			data.put("resolvedwithintime",obj1[1]);
			data.put("resolvedtimeline",obj1[2]);
			result.add(data);
		}
		return result;
	}
	@Override
	public List<Object> compliantstatusGraphicalReport(Date fromdate, Date todate,Set<String> siteCodes) {
		String sitecode="";
		Iterator<String> iter=siteCodes.iterator();
		while(iter.hasNext()){
			sitecode+="'"+iter.next()+"',";
		}
		sitecode="("+sitecode.substring(0,sitecode.length()-1)+")";
		List<?> res=pgrsReportDAO.getComplaintStatus(fromdate,todate,sitecode);
		List<Object> result=new ArrayList<>();
		List<String> category=new ArrayList<>();
		List<Long> pending1=new ArrayList<>();
		List<Long> assigned1=new ArrayList<>();
		List<Long> onhold1=new ArrayList<>();
		List<Long> resolved1=new ArrayList<>();
		List<Long> reopened1=new ArrayList<>();
		for(Iterator<?> iterator1=res.iterator();iterator1.hasNext();){
			Object[] obj1=(Object[]) iterator1.next();
			category.add((String)obj1[1]);
			pending1.add(((BigInteger)obj1[2]).longValue());
			assigned1.add(((BigInteger)obj1[3]).longValue());
			onhold1.add(((BigInteger)obj1[4]).longValue());
			resolved1.add(((BigInteger)obj1[5]).longValue());
			reopened1.add(((BigInteger)obj1[6]).longValue());
		}
		result.add(category);
		List<Object> result1=new ArrayList<>();
		Map<String, Object> data=new HashMap<>();
		data.put("name","Pending for registration");
		data.put("data", pending1);
		result1.add(data);
		
		data=new HashMap<>();
		data.put("name","Registered and assigned ");
		data.put("data", assigned1);
		result1.add(data);
		
		data=new HashMap<>();
		data.put("name","On Hold");
		data.put("data", onhold1);
		result1.add(data);
		
		data=new HashMap<>();
		data.put("name","Resloved");
		data.put("data", resolved1);
		result1.add(data);
		
		data=new HashMap<>();
		data.put("name","Reopened");
		data.put("data", reopened1);
		result1.add(data);
		
		result.add(result1);
		return result;
	}
	@Override
	public List<Object> categoryWiseDetails() {
		List<?> res=pgrsReportDAO.categoryWiseDetails();
		List<Object> result=new ArrayList<>();
		List<String> category=new ArrayList<>();
		List<Long> pending1=new ArrayList<>();
		List<Long> assigned1=new ArrayList<>();
		List<Long> onhold1=new ArrayList<>();
		List<Long> resolved1=new ArrayList<>();
		List<Long> reopened1=new ArrayList<>();
		for(Iterator<?> iterator1=res.iterator();iterator1.hasNext();){
			Object[] obj1=(Object[]) iterator1.next();
			category.add((String)obj1[1]);
			pending1.add(((BigInteger)obj1[2]).longValue());
			assigned1.add(((BigInteger)obj1[3]).longValue());
			onhold1.add(((BigInteger)obj1[4]).longValue());
			resolved1.add(((BigInteger)obj1[5]).longValue());
			reopened1.add(((BigInteger)obj1[6]).longValue());
		}
		result.add(category);
		List<Object> result1=new ArrayList<>();
		Map<String, Object> data=new HashMap<>();
		data.put("name","Pending for registration");
		data.put("data", pending1);
		result1.add(data);
		
		data=new HashMap<>();
		data.put("name","Registered and assigned ");
		data.put("data", assigned1);
		result1.add(data);
		
		data=new HashMap<>();
		data.put("name","On Hold");
		data.put("data", onhold1);
		result1.add(data);
		
		data=new HashMap<>();
		data.put("name","Resloved");
		data.put("data", resolved1);
		result1.add(data);
		
		data=new HashMap<>();
		data.put("name","Reopened");
		data.put("data", reopened1);
		result1.add(data);
		
		result.add(result1);
		return result;
	}
	@Override
	public List<Object> monthWiseDetails() {
		
		List<Long> pending=new ArrayList<>();
		List<Long> assigned=new ArrayList<>();
		List<Long> onhold=new ArrayList<>();
		List<Long> resolved=new ArrayList<>();
		List<Long> reopened=new ArrayList<>();
		for(int i=0;i<=11;i++){
			pending.add((long)0);
			assigned.add((long)0);
			onhold.add((long)0);
			resolved.add((long)0);
			reopened.add((long)0);
		}
		List<?> res=pgrsReportDAO.monthWiseDetails();
		for(Iterator<?> iterator=res.iterator();iterator.hasNext();){
			Object[] obj=(Object[]) iterator.next();
			pending.set(((Integer)obj[5])-1,(long)obj[0]);
			assigned.set(((Integer)obj[5])-1,(long)obj[1]);
			onhold.set(((Integer)obj[5])-1,(long)obj[2]);
			resolved.set(((Integer)obj[5])-1,(long)obj[3]);
			reopened.set(((Integer)obj[5])-1,(long)obj[4]);
		}
		List<Object> result=new ArrayList<>();
		result.add(pending);
		result.add(assigned);
		result.add(onhold);
		result.add(resolved);
		result.add(reopened);
		return result;
	}
	@Override
	public Map<String, Object> totalComplaints() {
		List<?> data=helpDeskTicketDAO.totalComplaints();
		Map<String, Object> result=new HashMap<>();
		for(Iterator<?> iterator=data.iterator();iterator.hasNext();){
			Object[] obj=(Object[]) iterator.next();
			if((Long)obj[0]!=0){
			result.put("total",(Long)obj[0]);
			result.put("asPerSLA",(Long)obj[1]);
			result.put("beyondSLA",(Long)obj[2]);
			result.put("resolved",(Long)obj[3]);
			result.put("text","");
			result.put("circleId",0);
			return result;
			}
		}
		return null;
	}
	@Override
	public Map<String, Object> escalatedComplaints() {
		List<?> data=helpDeskTicketDAO.escalatedComplaints();
		Map<String, Object> result=new HashMap<>();
		for(Iterator<?> iterator=data.iterator();iterator.hasNext();){
			Object[] obj=(Object[]) iterator.next();
			if((Long)obj[0]!=0){
			result.put("total",(Long)obj[0]);
			result.put("level1",(Long)obj[1]);
			result.put("level2",(Long)obj[2]);
			result.put("level3",(Long)obj[3]);
			result.put("level4",(Long)obj[4]);
			result.put("level5",(Long)obj[5]);
			result.put("level6",(Long)obj[6]);
			result.put("pending",(Long)obj[7]);
			result.put("onhold",(Long)obj[8]);
			result.put("text","");
			result.put("circleId",0);
			return result;
			}
		}
		return null;
	}
	
	
	@SuppressWarnings("static-access")
	@Override
	public List<?> showAverageTimeBreached(String fromMonth, String toMonth,Set<String> siteCodes, String status, HttpServletRequest req) {
		String sitecode="";
		Iterator<String> iter=siteCodes.iterator();
		while(iter.hasNext()){
			sitecode+="'"+iter.next()+"',";
		}
		sitecode="("+sitecode.substring(0,sitecode.length()-1)+")";
		List<?> res=pgrsReportDAO.showAverageTimeBreached(fromMonth, toMonth, sitecode,status, req);
		List<Map<String, Object>> result=new ArrayList<>();
		if(status.equalsIgnoreCase("resolved"))
		{
		for(Iterator<?> iterator1=res.iterator();iterator1.hasNext();){
			Object[] obj1=(Object[]) iterator1.next();
			if(((BigInteger)obj1[3]).longValue()!=0){
			Map<String, Object> data=new HashMap<>();
			
			data.put("category", obj1[1]);
			data.put("total", obj1[2]);
			data.put("resolved", obj1[3]);
			
			if(obj1[4]!=null){
				final Double diffInHours = Double.parseDouble(obj1[4].toString());
				String s = String.format("%.2f", diffInHours);
				final double diffInHours1 = (double) diffInHours.parseDouble(s);
				int hours=(int)diffInHours1;
				double minutesDecimal = ((diffInHours1 - hours) * 60);
			data.put("avgresolved",   hours+" hr : "+((int)minutesDecimal<0?-(int)minutesDecimal:(int)minutesDecimal) +" min");
			}else{
				data.put("avgresolved", 0);
			}
			if(obj1[5]!=null){
				final Double diffInHours = Double.parseDouble(obj1[5].toString());
				String s = String.format("%.2f", diffInHours);
				final double diffInHours1 = (double) diffInHours.parseDouble(s);
				int hours=(int)diffInHours1;
				double minutesDecimal = ((diffInHours1 - hours) * 60);
			data.put("estavgresolved", hours+" hr : "+((int)minutesDecimal<0?-(int)minutesDecimal:(int)minutesDecimal) +" min");
			}else{
				data.put("estavgresolved", 0);
			}
			if(obj1[4]!=null && obj1[5]!=null){
				final Double diffInHours = (Double.parseDouble(obj1[4].toString()))-(Double.parseDouble(obj1[5].toString()));
				String s = String.format("%.2f", diffInHours);
				final double diffInHours1 = (double) diffInHours.parseDouble(s);
				int hours=(int)diffInHours1;
				double minutesDecimal = ((diffInHours1 - hours) * 60);
				data.put("resPercentage",  (hours<0?0:hours)+" hr : "+((int)minutesDecimal<0?0:(int)minutesDecimal) +" min");
			}else{
				data.put("resPercentage", 0+" hr : "+0 +" min");
			}
			result.add(data);
			}
		}
		}else if(status.equalsIgnoreCase("unresolved"))
		{
			for(Iterator<?> iterator1=res.iterator();iterator1.hasNext();){
				Object[] obj1=(Object[]) iterator1.next();
				if(((BigInteger)obj1[3]).longValue()!=0){
				Map<String, Object> data=new HashMap<>();
				
				data.put("category", obj1[1]);
				data.put("total", obj1[2]);
				data.put("resolved", obj1[3]);
				
				if(obj1[4]!=null){
					final Double diffInHours = Double.parseDouble(obj1[4].toString());
					String s = String.format("%.2f", diffInHours);
					final double diffInHours1 = (double) diffInHours.parseDouble(s);
					int hours=(int)diffInHours1;
					double minutesDecimal = ((diffInHours1 - hours) * 60);
				data.put("avgresolved",   hours+" hr : "+((int)minutesDecimal<0?-(int)minutesDecimal:(int)minutesDecimal) +" min");
				}else{
					data.put("avgresolved", 0);
				}
				if(obj1[5]!=null){
					final Double diffInHours = Double.parseDouble(obj1[5].toString());
					String s = String.format("%.2f", diffInHours);
					final double diffInHours1 = (double) diffInHours.parseDouble(s);
					int hours=(int)diffInHours1;
					double minutesDecimal = ((diffInHours1 - hours) * 60);
				data.put("estavgresolved", hours+" hr : "+((int)minutesDecimal<0?-(int)minutesDecimal:(int)minutesDecimal) +" min");
				}else{
					data.put("estavgresolved", 0);
				}
				if(obj1[4]!=null && obj1[5]!=null){
					final Double diffInHours = (Double.parseDouble(obj1[4].toString()))-(Double.parseDouble(obj1[5].toString()));
					String s = String.format("%.2f", diffInHours);
					final double diffInHours1 = (double) diffInHours.parseDouble(s);
					int hours=(int)diffInHours1;
					double minutesDecimal = ((diffInHours1 - hours) * 60);
				data.put("resPercentage",  (hours<0?0:hours) +" hr : "+((int)minutesDecimal<0?-(int)minutesDecimal:(int)minutesDecimal) +" min");
				}else{
					data.put("resPercentage", 0+" hr : "+0 +" min");
				}
				result.add(data);
				}
			}
		}
		return result;
	}
	@SuppressWarnings("static-access")
	@Override
	public List<?> showAverageTimeBreachedGraohicalReport(String fromMonth, String toMonth,Set<String> siteCodes, String status, HttpServletRequest req) {
		String sitecode="";
		Iterator<String> iter=siteCodes.iterator();
		while(iter.hasNext()){
			sitecode+="'"+iter.next()+"',";
		}
		sitecode="("+sitecode.substring(0,sitecode.length()-1)+")";
		List<?> res=pgrsReportDAO.showAverageTimeBreached(fromMonth, toMonth, sitecode,status, req);
		List<String> category=new ArrayList<>();
		List<Integer> breach=new ArrayList<>();
		if(status.equalsIgnoreCase("resolved"))
		{
			for(Iterator<?> iterator1=res.iterator();iterator1.hasNext();){
				Object[] obj1=(Object[]) iterator1.next();
				if(((BigInteger)obj1[3]).longValue()!=0){
					category.add((String)obj1[1]);
					if(obj1[4]!=null && obj1[5]!=null){
						final Double diffInHours = (Double.parseDouble(obj1[4].toString()))-(Double.parseDouble(obj1[5].toString()));
						String s = String.format("%.2f", diffInHours);
						final double diffInHours1 = (double) diffInHours.parseDouble(s);
						int hours=(int)diffInHours1;
						breach.add(hours<0?0:hours);
					}else{
						breach.add(0);
					}
				}
			}
			List<Object> result=new ArrayList<>();
			result.add(category);
			result.add(breach);
			return result;
		}else if(status.equalsIgnoreCase("unresolved"))
		{
			for(Iterator<?> iterator1=res.iterator();iterator1.hasNext();){
				Object[] obj1=(Object[]) iterator1.next();
				if(((BigInteger)obj1[3]).longValue()!=0){
					category.add((String)obj1[1]);
					if(obj1[4]!=null && obj1[5]!=null){
						final Double diffInHours = (Double.parseDouble(obj1[4].toString()))-(Double.parseDouble(obj1[5].toString()));
						String s = String.format("%.2f", diffInHours);
						final double diffInHours1 = (double) diffInHours.parseDouble(s);
						int hours=(int)diffInHours1;
						breach.add(hours<0?0:hours);
					}else{
						breach.add(0);
					}
				}
			}
			List<Object> result=new ArrayList<>();
			result.add(category);
			result.add(breach);
			return result;
			
		}
		return null;
	}
	@Override
	public List<?> dayWiseReport(int selectedMonth,int year) {
		List<?> resultList=pgrsReportDAO.dayWiseReport(selectedMonth,year);
		List<Map<String,Object>> result=new ArrayList<>();
		if(resultList==null){
			return null;
		}
		List<TicketCategoryEntity> catResult=masterGenericDAO.findAll(TicketCategoryEntity.class,true);
		
		//totalcount map
		CopyOnWriteArrayList<?> res= new CopyOnWriteArrayList<>(resultList);
		
		Long total=(long)0;
		
		  for(Iterator<?> iterator=res.iterator();iterator.hasNext();){
			Object[] obj1=(Object[]) iterator.next();
			Map<String,Object> jspData=new HashMap<>();
			jspData.put("date", obj1[0]);
			Map<Integer,Long> categoryCount=new HashMap<>();
			for(TicketCategoryEntity ticketCategoryEntity:catResult){
				categoryCount.put(ticketCategoryEntity.getCategoryId(), (long)0);
			}
			//map data
			for(Iterator<?> iterator2=res.iterator();iterator2.hasNext();){
				Object[] obj2=(Object[]) iterator2.next();
				if(((String)obj1[0]).equalsIgnoreCase((String)obj2[0])){
				categoryCount.put(((BigDecimal)obj2[2]).intValue(),((BigInteger)obj2[1]).longValue());	
				res.remove(obj2);
				total+=((BigInteger)obj2[1]).longValue();
				}
				
			}
			
			List<Long> data=new ArrayList<>();
			Set<Integer> keyset=categoryCount.keySet();
			
			for(Integer key:keyset){
				data.add(categoryCount.get(key));
				
			}
			jspData.put("category",data);		
			jspData.put("total",total);
			result.add(jspData);
			total=(long)0;
		  }
		  List<String> category=new ArrayList<>();
		  Map<Integer,String> categoryCount=new HashMap<>();
		  for(TicketCategoryEntity ticketCategoryEntity:catResult){
				categoryCount.put(ticketCategoryEntity.getCategoryId(), ticketCategoryEntity.getCategoryName());
			}
		  
		  Set<Integer> keyset=categoryCount.keySet();
			for(Integer key:keyset){
				category.add(categoryCount.get(key));
				
			}
		  List<Object> finalresult=new ArrayList<>();
		 finalresult.add(category);
		 finalresult.add(result);
		return finalresult;
	}
	@SuppressWarnings({ "serial", "static-access" })
	@Override
	public List<?> KERCTimeLimitReport(final int month, final int year, String type,final int typeId) {
		
		List<?> resultList=pgrsReportDAO.KERCTimeLimitReport(month,year,type,typeId);
		
		List<Map<String, Object>> KERCData = new ArrayList<Map<String, Object>>();
		
		for (final Iterator<?> i = resultList.iterator(); i.hasNext();) {
			KERCData.add(new HashMap<String, Object>() {
				{
					
					final Object[] values = (Object[])i.next();
					put("townName", (String)values[8]);
					put("siteCode", (String)values[7]);
					put("month", month);
					put("year", year);
					put("typeId", typeId);
					put("pendingFromPrev", ((BigInteger)values[1]).longValue());
					put("currentRegistered", ((BigInteger)values[0]).longValue());
					put("totalPending", (((BigInteger)values[0]).longValue()+((BigInteger)values[1]).longValue()));
					put("resovledComplaints",((BigInteger)values[2]).longValue());
					put("complaintsYetToClosed", ((BigInteger)values[5]));
					put("withinKERC",((BigInteger)values[3]).longValue());
					put("beyondKERC",((BigInteger)values[4]).longValue());
					long within=((BigInteger)values[3]).longValue();
					long without=((BigInteger)values[4]).longValue();
					 if(values[6]!=null){
						final Double diffInHours = Double.parseDouble(values[6].toString());
						String s = String.format("%.2f", diffInHours);
						final double diffInHours1 = (double) diffInHours.parseDouble(s);
						int hours=(int)diffInHours1;
						double minutesDecimal = ((diffInHours1 - hours) * 60);
						put("pendingPeriod",   hours+" hr : "+((int)minutesDecimal<0?-(int)minutesDecimal:(int)minutesDecimal) +" min");
						}else{
						put("pendingPeriod", 0);
				    }
					if(within!=without){ 
					   put("percentWitninKERC",((within) * 100/ (without+within)));
					}else{
						 put("percentWitninKERC",0);
					}
			
				}
			});
		}
		return KERCData;
	}
	@Override
	public List<?> getComplaintClosed(String sitecode, int month, int year) {
		List<?> data=pgrsReportDAO.getComplaintClosed(sitecode, month, year);
		//P.docketnumber,P.docketstatus,P.docketcreateddatetime,C.category_name,S.subcategory_name,P.docketsummary,P.docketsource,P.consumername,P.consumermobilenumber
		List<Map<String, Object>> result=new ArrayList<>();
		for(Iterator<?> iterator=data.iterator();iterator.hasNext();){
			Object[] obj=(Object[]) iterator.next();
			Map<String,Object> resData=new HashMap<>();
			resData.put("docketNumber", obj[0]);
			String[] str={"Pending For Registration","Assigned & Registered","On Hold","Resolved","Reopened"};
			resData.put("docketStatus", str[((BigDecimal)obj[1]).intValue()]);
			resData.put("docketCreatedDt",new SimpleDateFormat("MM/dd/yyyy HH:mm:ss aa").format((Timestamp)obj[2]));
			resData.put("categoryName", (String)obj[3]);
			resData.put("subCategoryName", (String)obj[4]);
			resData.put("docketSummary", (String)obj[5]);
			resData.put("docketSource", (String)obj[6]);
			resData.put("consumerName", ((String)obj[7]).substring(0).toUpperCase()+""+((String)obj[7]).substring(1,((String)obj[7]).length()-1).toUpperCase());
			resData.put("consumerMobileNo", ((BigDecimal)obj[8]).longValue());
			result.add(resData);
		}
		return result;
	}
	
}
