package com.pgrs.controller;

import java.io.IOException;
import java.security.Principal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Hibernate;
import org.joda.time.DateTime;
import org.joda.time.Period;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pgrs.core.dao.MasterGenericDAO;
import com.pgrs.entity.ProjectHeirarchyEntity;
import com.pgrs.service.PgrsReportService;
import com.pgrs.service.TicketCategoryService;
import com.pgrs.util.DateTimeCalender;
import com.pgrs.util.PgrsSchemaName;

@Controller
public class DashBoardController {
	
	@Autowired
	private MasterGenericDAO masterGenericDAO;
	
	@Autowired
	private TicketCategoryService ticketCategoryService;
	
	@Autowired
	private PgrsReportService pgrsReportService;
	
	DateTimeCalender dateTimeCalender = new DateTimeCalender(); 
	
	@PersistenceContext
	EntityManager entityManager;
	
	@RequestMapping(value ="/dashboard" , method ={ RequestMethod.POST,RequestMethod.GET})
	public String dashboard(Model model, HttpServletRequest request,HttpServletResponse response,Principal principal) throws IOException, Exception {
		HttpSession session=request.getSession(false);
		 String tab=(String) session.getAttribute("tabdata");
			if(tab==null){
				session.setAttribute("tabdata","1");
			}
		model.addAttribute("modulename","Dashboard");
		return "usermanagement/dashboard";
		
	}
	
	@RequestMapping(value="/dashBoard/readDashBoardCircle", method = RequestMethod.GET)
	public @ResponseBody Object readDashBoardCircle(HttpServletRequest request){
		HttpSession session=request.getSession(false);
		Date date1=(Date) session.getAttribute("date1");
		 Date date2=(Date) session.getAttribute("date2");
		 String date11=""; String date22="";
		 if(date1 !=null && date2!=null){
			date11=dateTimeCalender.getDate3(date1); 
			date22=dateTimeCalender.getDate3(date2); 
		 }
		 Integer categoryId=(Integer) session.getAttribute("categoryId");
		 String mode=(String) session.getAttribute("mode");
		String titel="";
		List<?> list=masterGenericDAO.executeSimpleObjectQuery("SELECT ph.text,ph.siteCode,ph.id FROM ProjectHeirarchyEntity ph WHERE ph.type='Circle'");
		
		List<Map<String, Object>> result=new ArrayList<>();
		for(Iterator<?> itr=list.iterator();itr.hasNext();){
			Object[] obj=(Object[]) itr.next();
			
			List<?> complaints=null;
			if(date1 !=null && date2 !=null || mode!=null || categoryId!=null){
				if(date1 !=null && date2 !=null && mode!=null && categoryId!=null){
				
					titel = "From Date :"+date11+ " To Date:"+date22+" Cateory :"+session.getAttribute("categoryName")+" Mode:"+mode;
					complaints=masterGenericDAO.executeSimpleObjectQuery("SELECT count(*),pc.docketStatus FROM HelpDeskTicketEntity pc WHERE pc.siteCode LIKE '"+(String)obj[1]+"%' "
							+ "AND date(pc.docketCreatedDt)>=TO_DATE('"+new SimpleDateFormat("dd/MM/yyyy").format(date1)+"','dd/MM/yyyy') AND "
							+ "date(pc.docketCreatedDt)<=TO_DATE('"+new SimpleDateFormat("dd/MM/yyyy").format(date2)+"','dd/MM/yyyy') AND pc.categoryId="+categoryId+" AND pc.docketSource='"+mode+"' AND pc.invalidFlag='No' GROUP BY pc.docketStatus ORDER BY pc.docketStatus ASC");
				}else if(mode==null && categoryId==null){
					titel="From Date :"+date11+ "To Date:"+date22;
				complaints=masterGenericDAO.executeSimpleObjectQuery("SELECT count(*),pc.docketStatus FROM HelpDeskTicketEntity pc WHERE pc.siteCode LIKE '"+(String)obj[1]+"%' "
						+ "AND date(pc.docketCreatedDt)>=TO_DATE('"+new SimpleDateFormat("dd/MM/yyyy").format(date1)+"','dd/MM/yyyy') AND "
								+ "date(pc.docketCreatedDt)<=TO_DATE('"+new SimpleDateFormat("dd/MM/yyyy").format(date2)+"','dd/MM/yyyy') AND pc.invalidFlag='No' GROUP BY pc.docketStatus ORDER BY pc.docketStatus ASC");
				}else if(date1==null && mode==null){
					titel="Cateory :"+session.getAttribute("categoryName");
				complaints=masterGenericDAO.executeSimpleObjectQuery("SELECT count(*),pc.docketStatus FROM HelpDeskTicketEntity pc WHERE pc.siteCode LIKE '"+(String)obj[1]+"%' "
						+ "AND pc.categoryId="+categoryId+" AND pc.invalidFlag='No' GROUP BY pc.docketStatus ORDER BY pc.docketStatus ASC");
				}else if(date1==null && categoryId==null){	
					titel="Mode :"+mode;
					complaints=masterGenericDAO.executeSimpleObjectQuery("SELECT count(*),pc.docketStatus FROM HelpDeskTicketEntity pc WHERE pc.siteCode LIKE '"+(String)obj[1]+"%' "
							+ "AND pc.docketSource='"+mode+"' AND pc.invalidFlag='No' GROUP BY pc.docketStatus ORDER BY pc.docketStatus ASC");
				}else if(date1!=null && categoryId!=null){
					titel= "From Date :"+date11+ "To Date:"+date22+" Cateory :"+session.getAttribute("categoryName");
					complaints=masterGenericDAO.executeSimpleObjectQuery("SELECT count(*),pc.docketStatus FROM HelpDeskTicketEntity pc WHERE pc.siteCode LIKE '"+(String)obj[1]+"%' "
							+ "AND date(pc.docketCreatedDt)>=TO_DATE('"+new SimpleDateFormat("dd/MM/yyyy").format(date1)+"','dd/MM/yyyy') AND "
							+ "date(pc.docketCreatedDt)<=TO_DATE('"+new SimpleDateFormat("dd/MM/yyyy").format(date2)+"','dd/MM/yyyy') AND pc.categoryId="+categoryId+" AND pc.invalidFlag='No' GROUP BY pc.docketStatus ORDER BY pc.docketStatus ASC");
				}else if(date1!=null && mode!=null){
					titel="From Date :"+date11+ "To Date:"+date22+" Mode :"+mode;
					complaints=masterGenericDAO.executeSimpleObjectQuery("SELECT count(*),pc.docketStatus FROM HelpDeskTicketEntity pc WHERE pc.siteCode LIKE '"+(String)obj[1]+"%' "
							+ "AND date(pc.docketCreatedDt)>=TO_DATE('"+new SimpleDateFormat("dd/MM/yyyy").format(date1)+"','dd/MM/yyyy') AND "
							+ "date(pc.docketCreatedDt)<=TO_DATE('"+new SimpleDateFormat("dd/MM/yyyy").format(date2)+"','dd/MM/yyyy') AND pc.docketSource='"+mode+"' AND pc.invalidFlag='No' GROUP BY pc.docketStatus ORDER BY pc.docketStatus ASC");
				}else if(categoryId!=null && mode!=null){
					titel="Cateory :"+session.getAttribute("categoryName")+" Mode :"+mode;
					complaints=masterGenericDAO.executeSimpleObjectQuery("SELECT count(*),pc.docketStatus FROM HelpDeskTicketEntity pc WHERE pc.siteCode LIKE '"+(String)obj[1]+"%' "
							+ "AND pc.categoryId="+categoryId+" AND pc.docketSource='"+mode+"' AND pc.invalidFlag='No' GROUP BY pc.docketStatus ORDER BY pc.docketStatus ASC");
				}
			}else{
			complaints=masterGenericDAO.executeSimpleObjectQuery("SELECT count(*),pc.docketStatus FROM HelpDeskTicketEntity pc WHERE pc.siteCode LIKE '"+(String)obj[1]+"%' "
					+ "AND pc.invalidFlag='No' GROUP BY pc.docketStatus ORDER BY pc.docketStatus ASC");
			}
			int flag=0;
			Map<Integer,Long> status=new HashMap<>();
			for(int i=0;i<5;i++){
				status.put(i,(long)0);
			}
			for(Iterator<?> iterator=complaints.iterator();iterator.hasNext();){
				Object[] complaintList=(Object[]) iterator.next();
				flag=1;
				status.put((Integer)complaintList[1], (long)complaintList[0]);	
			}
			
			if(flag==1){
				Map<String, Object> data=new HashMap<>();
				data.put("circle", (String)obj[0]);
				data.put("circleId", (Integer)obj[2]);
				data.put("sitecode", (String)obj[1]);
				Set<Integer> keyset=status.keySet();
				long total=0;
				for(Integer key: keyset){
					total+=status.get(key);
					if(key==0){
						data.put("pending",status.get(key));
					}else if(key==1){
						data.put("assigned",status.get(key));
					}else if(key==2){
						data.put("onhold",status.get(key));
					}else if(key==3){
						data.put("resolved",status.get(key));
					}else if(key==4){
						data.put("reopen",status.get(key));
					}
				}
				data.put("total",total);
			result.add(data);
			}
		}
		List<Object> resultList=new ArrayList<>();
		resultList.add(result);
		resultList.add(titel!=""?"(Filter "+titel+")":"");
		return resultList;
	}
	@RequestMapping(value="/dashBoard/readDashBoardDivision", method = RequestMethod.GET)
	public @ResponseBody Object readDashBoardDivision(HttpServletRequest request){
		HttpSession session=request.getSession(false);
		Date date1=(Date) session.getAttribute("date1");
		 Date date2=(Date) session.getAttribute("date2");
		 Integer categoryId=(Integer) session.getAttribute("categoryId");
		 String mode=(String) session.getAttribute("mode");
		
		List<?> list=masterGenericDAO.executeSimpleObjectQuery("SELECT ph.text,ph.siteCode,ph.id FROM ProjectHeirarchyEntity ph WHERE ph.type='Division'");
		List<Map<String, Object>> result=new ArrayList<>();
		for(Iterator<?> itr=list.iterator();itr.hasNext();){
			Object[] obj=(Object[]) itr.next();
			
			List<?> complaints=null;
			if(date1 !=null && date2 !=null || mode!=null || categoryId!=null){
				if(date1 !=null && date2 !=null && mode!=null && categoryId!=null){
				
					complaints=masterGenericDAO.executeSimpleObjectQuery("SELECT count(*),pc.docketStatus FROM HelpDeskTicketEntity pc WHERE pc.siteCode LIKE '"+(String)obj[1]+"%' "
							+ "AND date(pc.docketCreatedDt)>=TO_DATE('"+new SimpleDateFormat("dd/MM/yyyy").format(date1)+"','dd/MM/yyyy') AND "
							+ "date(pc.docketCreatedDt)<=TO_DATE('"+new SimpleDateFormat("dd/MM/yyyy").format(date2)+"','dd/MM/yyyy') AND pc.categoryId="+categoryId+" AND pc.docketSource='"+mode+"' AND pc.invalidFlag='No' GROUP BY pc.docketStatus ORDER BY pc.docketStatus ASC");
				}else if(mode==null && categoryId==null){
				complaints=masterGenericDAO.executeSimpleObjectQuery("SELECT count(*),pc.docketStatus FROM HelpDeskTicketEntity pc WHERE pc.siteCode LIKE '"+(String)obj[1]+"%' "
						+ "AND date(pc.docketCreatedDt)>=TO_DATE('"+new SimpleDateFormat("dd/MM/yyyy").format(date1)+"','dd/MM/yyyy') AND "
								+ "date(pc.docketCreatedDt)<=TO_DATE('"+new SimpleDateFormat("dd/MM/yyyy").format(date2)+"','dd/MM/yyyy') AND pc.invalidFlag='No' GROUP BY pc.docketStatus ORDER BY pc.docketStatus ASC");
				}else if(date1==null && mode==null){
				complaints=masterGenericDAO.executeSimpleObjectQuery("SELECT count(*),pc.docketStatus FROM HelpDeskTicketEntity pc WHERE pc.siteCode LIKE '"+(String)obj[1]+"%' "
						+ "AND pc.categoryId="+categoryId+" AND pc.invalidFlag='No' GROUP BY pc.docketStatus ORDER BY pc.docketStatus ASC");
				}else if(date1==null && categoryId==null){	
					complaints=masterGenericDAO.executeSimpleObjectQuery("SELECT count(*),pc.docketStatus FROM HelpDeskTicketEntity pc WHERE pc.siteCode LIKE '"+(String)obj[1]+"%' "
							+ "AND pc.docketSource='"+mode+"' AND pc.invalidFlag='No' GROUP BY pc.docketStatus ORDER BY pc.docketStatus ASC");
				}else if(date1!=null && categoryId!=null){
					complaints=masterGenericDAO.executeSimpleObjectQuery("SELECT count(*),pc.docketStatus FROM HelpDeskTicketEntity pc WHERE pc.siteCode LIKE '"+(String)obj[1]+"%' "
							+ "AND date(pc.docketCreatedDt)>=TO_DATE('"+new SimpleDateFormat("dd/MM/yyyy").format(date1)+"','dd/MM/yyyy') AND "
							+ "date(pc.docketCreatedDt)<=TO_DATE('"+new SimpleDateFormat("dd/MM/yyyy").format(date2)+"','dd/MM/yyyy') AND pc.categoryId="+categoryId+" AND pc.invalidFlag='No' GROUP BY pc.docketStatus ORDER BY pc.docketStatus ASC");
				}else if(date1!=null && mode!=null){
					complaints=masterGenericDAO.executeSimpleObjectQuery("SELECT count(*),pc.docketStatus FROM HelpDeskTicketEntity pc WHERE pc.siteCode LIKE '"+(String)obj[1]+"%' "
							+ "AND date(pc.docketCreatedDt)>=TO_DATE('"+new SimpleDateFormat("dd/MM/yyyy").format(date1)+"','dd/MM/yyyy') AND "
							+ "date(pc.docketCreatedDt)<=TO_DATE('"+new SimpleDateFormat("dd/MM/yyyy").format(date2)+"','dd/MM/yyyy') AND pc.docketSource='"+mode+"' AND pc.invalidFlag='No' GROUP BY pc.docketStatus ORDER BY pc.docketStatus ASC");
				}else if(categoryId!=null && mode!=null){
					complaints=masterGenericDAO.executeSimpleObjectQuery("SELECT count(*),pc.docketStatus FROM HelpDeskTicketEntity pc WHERE pc.siteCode LIKE '"+(String)obj[1]+"%' "
							+ "AND pc.categoryId="+categoryId+" AND pc.docketSource='"+mode+"' AND pc.invalidFlag='No' GROUP BY pc.docketStatus ORDER BY pc.docketStatus ASC");
				}
			}else{
			complaints=masterGenericDAO.executeSimpleObjectQuery("SELECT count(*),pc.docketStatus FROM HelpDeskTicketEntity pc WHERE pc.siteCode LIKE '"+(String)obj[1]+"%' "
					+ "AND pc.invalidFlag='No' GROUP BY pc.docketStatus ORDER BY pc.docketStatus ASC");
			}
			int flag=0;
			Map<Integer,Long> status=new HashMap<>();
			for(int i=0;i<5;i++){
				status.put(i,(long)0);
			}
			for(Iterator<?> iterator=complaints.iterator();iterator.hasNext();){
				Object[] complaintList=(Object[]) iterator.next();
				flag=1;
				status.put((Integer)complaintList[1], (long)complaintList[0]);	
			}
			
			if(flag==1){
				Map<String, Object> data=new HashMap<>();
				data.put("circle", (String)obj[0]);
				data.put("circleId", (Integer)obj[2]);
				data.put("sitecode", (String)obj[1]);
				Set<Integer> keyset=status.keySet();
				long total=0;
				for(Integer key: keyset){
					total+=status.get(key);
					if(key==0){
						data.put("pending",status.get(key));
					}else if(key==1){
						data.put("assigned",status.get(key));
					}else if(key==2){
						data.put("onhold",status.get(key));
					}else if(key==3){
						data.put("resolved",status.get(key));
					}else if(key==4){
						data.put("reopen",status.get(key));
					}
				}
				data.put("total",total);
			result.add(data);
			}
		}
		return result;
	}
	@RequestMapping(value="/dashBoard/readDashBoardSubDivision", method = RequestMethod.GET)
	public @ResponseBody Object readDashBoardSubDivision(HttpServletRequest request){
		HttpSession session=request.getSession(false);
		Date date1=(Date) session.getAttribute("date1");
		Date date2=(Date) session.getAttribute("date2");
		String date11=""; String date22="";
		if(date1 !=null && date2!=null){
			date11=dateTimeCalender.getDate3(date1); 
			date22=dateTimeCalender.getDate3(date2); 
		}
		Integer categoryId=(Integer) session.getAttribute("categoryId");
		String mode=(String) session.getAttribute("mode");
		String titel="";
		List<?> complaints=null;
		if(date1 !=null && date2 !=null || mode!=null || categoryId!=null){
			if(date1 !=null && date2 !=null && mode!=null && categoryId!=null){
				
				titel = "From Date :"+date11+ " To Date:"+date22+" Cateory :"+session.getAttribute("categoryName")+" Mode:"+mode;
				
				complaints=masterGenericDAO.executeSimpleObjectQuery("SELECT count(c.docketStatus),p.parent.text,c.docketStatus,p.parent.siteCode,p.parent.id FROM HelpDeskTicketEntity c,"
						+ "ProjectHeirarchyEntity p WHERE  p.siteCode=c.siteCode AND date(c.docketCreatedDt)>=TO_DATE('"+new SimpleDateFormat("dd/MM/yyyy").format(date1)+"','dd/MM/yyyy') AND "
						+ "date(c.docketCreatedDt)<=TO_DATE('"+new SimpleDateFormat("dd/MM/yyyy").format(date2)+"','dd/MM/yyyy') AND "
						+ "c.categoryId="+categoryId+"  AND c.docketSource='"+mode+"' AND c.invalidFlag='No' GROUP BY p.parent.text,c.docketStatus,p.parent.siteCode,p.parent.id ORDER BY "
						+ "p.parent.text,c.docketStatus,p.parent.siteCode,p.parent.id");
			}else if(mode==null && categoryId==null){
				titel="From Date :"+date11+ "To Date:"+date22;
				complaints=masterGenericDAO.executeSimpleObjectQuery("SELECT count(c.docketStatus),p.parent.text,c.docketStatus,p.parent.siteCode,p.parent.id FROM HelpDeskTicketEntity c,"
						+ "ProjectHeirarchyEntity p WHERE  p.siteCode=c.siteCode AND date(c.docketCreatedDt)>=TO_DATE('"+new SimpleDateFormat("dd/MM/yyyy").format(date1)+"','dd/MM/yyyy') AND "
						+ "date(c.docketCreatedDt)<=TO_DATE('"+new SimpleDateFormat("dd/MM/yyyy").format(date2)+"','dd/MM/yyyy') "
						+ "AND c.invalidFlag='No' GROUP BY p.parent.text,c.docketStatus,p.parent.siteCode,p.parent.id ORDER BY "
						+ "p.parent.text,c.docketStatus,p.parent.siteCode,p.parent.id");
			}else if(date1==null && mode==null){
				titel="Cateory :"+session.getAttribute("categoryName");
				complaints=masterGenericDAO.executeSimpleObjectQuery("SELECT count(c.docketStatus),p.parent.text,c.docketStatus,p.parent.siteCode,p.parent.id FROM HelpDeskTicketEntity c,"
						+ "ProjectHeirarchyEntity p WHERE  p.siteCode=c.siteCode AND "
						+ "c.categoryId="+categoryId+" AND c.invalidFlag='No' GROUP BY p.parent.text,c.docketStatus,p.parent.siteCode,p.parent.id ORDER BY "
						+ "p.parent.text,c.docketStatus,p.parent.siteCode,p.parent.id");
			}else if(date1==null && categoryId==null){	
				titel="Mode :"+mode;
				
				complaints=masterGenericDAO.executeSimpleObjectQuery("SELECT count(c.docketStatus),p.parent.text,c.docketStatus,p.parent.siteCode,p.parent.id FROM HelpDeskTicketEntity c,"
						+ "ProjectHeirarchyEntity p WHERE  p.siteCode=c.siteCode AND "
						+ "c.docketSource='"+mode+"' AND c.invalidFlag='No' GROUP BY p.parent.text,c.docketStatus,p.parent.siteCode,p.parent.id ORDER BY "
						+ "p.parent.text,c.docketStatus,p.parent.siteCode,p.parent.id");
			}else if(date1!=null && categoryId!=null){
				titel= "From Date :"+date11+ "To Date:"+date22+" Cateory :"+session.getAttribute("categoryName");
				complaints=masterGenericDAO.executeSimpleObjectQuery("SELECT count(c.docketStatus),p.parent.text,c.docketStatus,p.parent.siteCode,p.parent.id FROM HelpDeskTicketEntity c,"
						+ "ProjectHeirarchyEntity p WHERE  p.siteCode=c.siteCode AND date(c.docketCreatedDt)>=TO_DATE('"+new SimpleDateFormat("dd/MM/yyyy").format(date1)+"','dd/MM/yyyy') AND "
						+ "date(c.docketCreatedDt)<=TO_DATE('"+new SimpleDateFormat("dd/MM/yyyy").format(date2)+"','dd/MM/yyyy') AND "
						+ "c.categoryId="+categoryId+" AND c.invalidFlag='No' GROUP BY p.parent.text,c.docketStatus,p.parent.siteCode,p.parent.id ORDER BY "
						+ "p.parent.text,c.docketStatus,p.parent.siteCode,p.parent.id");
			}else if(date1!=null && mode!=null){
				titel="From Date :"+date11+ "To Date:"+date22+" Mode :"+mode;
				
				complaints=masterGenericDAO.executeSimpleObjectQuery("SELECT count(c.docketStatus),p.parent.text,c.docketStatus,p.parent.siteCode,p.parent.id FROM HelpDeskTicketEntity c,"
						+ "ProjectHeirarchyEntity p WHERE  p.siteCode=c.siteCode AND date(c.docketCreatedDt)>=TO_DATE('"+new SimpleDateFormat("dd/MM/yyyy").format(date1)+"','dd/MM/yyyy') AND "
						+ "date(c.docketCreatedDt)<=TO_DATE('"+new SimpleDateFormat("dd/MM/yyyy").format(date2)+"','dd/MM/yyyy') AND "
						+ "c.docketSource='"+mode+"' AND c.invalidFlag='No' GROUP BY p.parent.text,c.docketStatus,p.parent.siteCode,p.parent.id ORDER BY "
						+ "p.parent.text,c.docketStatus,p.parent.siteCode,p.parent.id");
			}else if(categoryId!=null && mode!=null){
				titel="Cateory :"+session.getAttribute("categoryName")+" Mode :"+mode;
				complaints=masterGenericDAO.executeSimpleObjectQuery("SELECT count(c.docketStatus),p.parent.text,c.docketStatus,p.parent.siteCode,p.parent.id FROM HelpDeskTicketEntity c,"
						+ "ProjectHeirarchyEntity p WHERE p.siteCode=c.siteCode AND c.categoryId="+categoryId+"  AND c.docketSource='"+mode+"' AND c.invalidFlag='No' GROUP BY p.parent.text,c.docketStatus,p.parent.siteCode,p.parent.id ORDER BY "
						+ "p.parent.text,c.docketStatus,p.parent.siteCode,p.parent.id");
			}
		}else{
			complaints=masterGenericDAO.executeSimpleObjectQuery("SELECT count(c.docketStatus),p.parent.text,c.docketStatus,p.parent.siteCode,p.parent.id FROM HelpDeskTicketEntity c,"
					+ "ProjectHeirarchyEntity p WHERE p.siteCode=c.siteCode AND c.invalidFlag='No' GROUP BY p.parent.text,c.docketStatus,p.parent.siteCode,p.parent.id ORDER BY "
					+ "p.parent.text,c.docketStatus,p.parent.siteCode,p.parent.id");
		}
		CopyOnWriteArrayList<?> res= new CopyOnWriteArrayList<>(complaints);
		List<Map<String,Object>> resulList=new ArrayList<>();
		for(Iterator<?> itr=res.iterator();itr.hasNext();){
			long sum=0;
			Object[] obj=(Object[]) itr.next();
			Map<String, Object> data=new HashMap<>();
			data.put("subDivision", (String)obj[1]);
			data.put("sitecode", (String)obj[3]);
			data.put("parentid", (Integer)obj[4]);
			for(Iterator<?> iterator=res.iterator();iterator.hasNext();){
				Object[] obj1=(Object[]) iterator.next();
				if(((String)obj[3]).equalsIgnoreCase((String)obj1[3])){
					sum=sum+(long)obj1[0];
					if((Integer)obj1[2]==0){
						data.put("pending",(long)obj1[0]);
						res.remove(obj1);
					}else if((Integer)obj1[2]==1){
						data.put("assigned",(long)obj1[0]);
						res.remove(obj1);
					}else if((Integer)obj1[2]==2){
						data.put("onhold",(long)obj1[0]);
						res.remove(obj1);
					}else if((Integer)obj1[2]==3){
						data.put("resolved",(long)obj1[0]);
						res.remove(obj1);
					}else if((Integer)obj1[2]==4){
						data.put("reopen",(long)obj1[0]);
						res.remove(obj1);
					}
				}
				
			}
			data.put("total",sum);
			resulList.add(data);
		}		
		List<Object> resultList=new ArrayList<>();
		resultList.add(resulList);
		resultList.add(titel!=""?"(Filter "+titel+")":"");
		return resultList;
	}
	@SuppressWarnings("unused")
	@RequestMapping(value="/dashboard/getSubDivisionBasedOnSiteCode/{parentId}",method ={ RequestMethod.POST,RequestMethod.GET} )
	public @ResponseBody Object getSubDivisionBasedOnSiteCode(@PathVariable Integer parentId,HttpServletRequest request){
		HttpSession session=request.getSession(false);
		Date date1=(Date) session.getAttribute("date1");
		Date date2=(Date) session.getAttribute("date2");
		String date11=""; String date22="";
		if(date1 !=null && date2!=null){
			date11=dateTimeCalender.getDate3(date1); 
			date22=dateTimeCalender.getDate3(date2); 
		}
		ProjectHeirarchyEntity projectHeirarchyEntity=masterGenericDAO.getById(ProjectHeirarchyEntity.class,parentId);
		Set<String> ids=new HashSet<>();
		ids.add(projectHeirarchyEntity.getSiteCode());
		fetchChildren1(projectHeirarchyEntity,ids);		
		
		String sitecode="";
		Iterator<String> iter=ids.iterator();
		while(iter.hasNext()){
			sitecode+="'"+iter.next()+"',";
		}
		sitecode="("+sitecode.substring(0,sitecode.length()-1)+")";
		
		
		Integer categoryId=(Integer) session.getAttribute("categoryId");
		String mode=(String) session.getAttribute("mode");
		String titel="";
		List<?> complaints=null;
		if(date1 !=null && date2 !=null || mode!=null || categoryId!=null){
			if(date1 !=null && date2 !=null && mode!=null && categoryId!=null){
				//SELECT ht.docketNumber,ht.docketStatus,ht.docketSource,ht.consumerName,ht.consumerMobileNo,ht.rrNumber,ht.address,
				titel = "From Date :"+date11+ " To Date:"+date22+" Cateory :"+session.getAttribute("categoryName")+" Mode:"+mode;
				complaints=masterGenericDAO.executeSimpleObjectQuery("SELECT c.docketNumber,c.docketStatus,c.docketSource,c.consumerName,c.consumerMobileNo,"
						+ "c.rrNumber,c.address,tc.categoryName,tsc.subCategoryName,c.remarks,c.userName,c.docketCreatedDt,c.resolvedDate,c.docketSummary,p.parent.text,"
						+ "p.text FROM ProjectHeirarchyEntity p,HelpDeskTicketEntity c,TicketCategoryEntity tc,TicketSubCategoryEntity tsc WHERE "
						+ "c.categoryId=tc.categoryId AND c.subCategoryId=tsc.subCategoryId AND c.siteCode IN "+sitecode+" AND p.siteCode=c.siteCode AND date(c.docketCreatedDt)>=TO_DATE('"+new SimpleDateFormat("dd/MM/yyyy").format(date1)+"','dd/MM/yyyy') AND "
						+ "date(c.docketCreatedDt)<=TO_DATE('"+new SimpleDateFormat("dd/MM/yyyy").format(date2)+"','dd/MM/yyyy') AND "
						+ "c.categoryId="+categoryId+"  AND c.docketSource='"+mode+"' AND c.invalidFlag='No' "); 
			}else if(mode==null && categoryId==null){
				titel="From Date :"+date11+ "To Date:"+date22;
				complaints=masterGenericDAO.executeSimpleObjectQuery("SELECT c.docketNumber,c.docketStatus,c.docketSource,c.consumerName,c.consumerMobileNo,"
						+ "c.rrNumber,c.address,tc.categoryName,tsc.subCategoryName,c.remarks,c.userName,c.docketCreatedDt,c.resolvedDate,c.docketSummary,p.parent.text,"
						+ "p.text FROM ProjectHeirarchyEntity p,HelpDeskTicketEntity c,TicketCategoryEntity tc,TicketSubCategoryEntity tsc WHERE "
						+ "c.categoryId=tc.categoryId AND c.subCategoryId=tsc.subCategoryId AND c.siteCode IN "+sitecode+" AND p.siteCode=c.siteCode AND date(c.docketCreatedDt)>=TO_DATE('"+new SimpleDateFormat("dd/MM/yyyy").format(date1)+"','dd/MM/yyyy') AND "
						+ "date(c.docketCreatedDt)<=TO_DATE('"+new SimpleDateFormat("dd/MM/yyyy").format(date2)+"','dd/MM/yyyy') AND c.invalidFlag='No' "); 
			}else if(date1==null && mode==null){
				titel="Cateory :"+session.getAttribute("categoryName");
				complaints=masterGenericDAO.executeSimpleObjectQuery("SELECT c.docketNumber,c.docketStatus,c.docketSource,c.consumerName,c.consumerMobileNo,"
						+ "c.rrNumber,c.address,tc.categoryName,tsc.subCategoryName,c.remarks,c.userName,c.docketCreatedDt,c.resolvedDate,c.docketSummary,p.parent.text,"
						+ "p.text FROM ProjectHeirarchyEntity p,HelpDeskTicketEntity c,TicketCategoryEntity tc,TicketSubCategoryEntity tsc WHERE "
						+ "c.categoryId=tc.categoryId AND c.subCategoryId=tsc.subCategoryId AND c.invalidFlag='No' AND c.siteCode IN "+sitecode+" AND p.siteCode=c.siteCode AND c.categoryId="+categoryId); 
			}else if(date1==null && categoryId==null){	
				titel="Mode :"+mode;
				
				complaints=masterGenericDAO.executeSimpleObjectQuery("SELECT c.docketNumber,c.docketStatus,c.docketSource,c.consumerName,c.consumerMobileNo,"
						+ "c.rrNumber,c.address,tc.categoryName,tsc.subCategoryName,c.remarks,c.userName,c.docketCreatedDt,c.resolvedDate,c.docketSummary,p.parent.text,"
						+ "p.text FROM ProjectHeirarchyEntity p,HelpDeskTicketEntity c,TicketCategoryEntity tc,TicketSubCategoryEntity tsc WHERE "
						+ "c.categoryId=tc.categoryId AND c.subCategoryId=tsc.subCategoryId AND c.invalidFlag='No' AND c.siteCode IN "+sitecode+" AND p.siteCode=c.siteCode AND c.docketSource='"+mode+"'"); 
			}else if(date1!=null && categoryId!=null){
				titel= "From Date :"+date11+ "To Date:"+date22+" Cateory :"+session.getAttribute("categoryName");
				complaints=masterGenericDAO.executeSimpleObjectQuery("SELECT c.docketNumber,c.docketStatus,c.docketSource,c.consumerName,c.consumerMobileNo,"
						+ "c.rrNumber,c.address,tc.categoryName,tsc.subCategoryName,c.remarks,c.userName,c.docketCreatedDt,c.resolvedDate,c.docketSummary,p.parent.text,"
						+ "p.text FROM ProjectHeirarchyEntity p,HelpDeskTicketEntity c,TicketCategoryEntity tc,TicketSubCategoryEntity tsc WHERE "
						+ "c.categoryId=tc.categoryId AND c.subCategoryId=tsc.subCategoryId AND c.invalidFlag='No' AND c.siteCode IN "+sitecode+" AND p.siteCode=c.siteCode AND date(c.docketCreatedDt)>=TO_DATE('"+new SimpleDateFormat("dd/MM/yyyy").format(date1)+"','dd/MM/yyyy') AND "
						+ "date(c.docketCreatedDt)<=TO_DATE('"+new SimpleDateFormat("dd/MM/yyyy").format(date2)+"','dd/MM/yyyy') AND "
						+ "c.categoryId="+categoryId); 
			}else if(date1!=null && mode!=null){
				titel="From Date :"+date11+ "To Date:"+date22+" Mode :"+mode;
				
				complaints=masterGenericDAO.executeSimpleObjectQuery("SELECT c.docketNumber,c.docketStatus,c.docketSource,c.consumerName,c.consumerMobileNo,"
						+ "c.rrNumber,c.address,tc.categoryName,tsc.subCategoryName,c.remarks,c.userName,c.docketCreatedDt,c.resolvedDate,c.docketSummary,p.parent.text,"
						+ "p.text FROM ProjectHeirarchyEntity p,HelpDeskTicketEntity c,TicketCategoryEntity tc,TicketSubCategoryEntity tsc WHERE "
						+ "c.categoryId=tc.categoryId AND c.subCategoryId=tsc.subCategoryId AND c.siteCode IN "+sitecode+" AND p.siteCode=c.siteCode AND date(c.docketCreatedDt)>=TO_DATE('"+new SimpleDateFormat("dd/MM/yyyy").format(date1)+"','dd/MM/yyyy') AND "
						+ "date(c.docketCreatedDt)<=TO_DATE('"+new SimpleDateFormat("dd/MM/yyyy").format(date2)+"','dd/MM/yyyy') AND "
						+ "c.docketSource='"+mode+"' AND c.invalidFlag='No'"); 
			}else if(categoryId!=null && mode!=null){
				titel="Cateory :"+session.getAttribute("categoryName")+" Mode :"+mode;
				complaints=masterGenericDAO.executeSimpleObjectQuery("SELECT c.docketNumber,c.docketStatus,c.docketSource,c.consumerName,c.consumerMobileNo,"
						+ "c.rrNumber,c.address,tc.categoryName,tsc.subCategoryName,c.remarks,c.userName,c.docketCreatedDt,c.resolvedDate,c.docketSummary,p.parent.text,"
						+ "p.text FROM ProjectHeirarchyEntity p,HelpDeskTicketEntity c,TicketCategoryEntity tc,TicketSubCategoryEntity tsc WHERE "
						+ "c.categoryId=tc.categoryId AND c.subCategoryId=tsc.subCategoryId AND c.siteCode IN "+sitecode+" AND p.siteCode=c.siteCode AND "
						+ "c.categoryId="+categoryId+"  AND c.docketSource='"+mode+"' AND c.invalidFlag='No'"); 
			}
		}else{
			
			complaints=masterGenericDAO.executeSimpleObjectQuery("SELECT c.docketNumber,c.docketStatus,c.docketSource,c.consumerName,c.consumerMobileNo,"
					+ "c.rrNumber,c.address,tc.categoryName,tsc.subCategoryName,c.remarks,c.userName,c.docketCreatedDt,c.resolvedDate,c.docketSummary,p.parent.text,"
					+ "p.text FROM ProjectHeirarchyEntity p,HelpDeskTicketEntity c,TicketCategoryEntity tc,TicketSubCategoryEntity tsc WHERE "
					+ "c.categoryId=tc.categoryId AND c.subCategoryId=tsc.subCategoryId AND c.siteCode IN "+sitecode+" AND p.siteCode=c.siteCode AND c.invalidFlag='No'"); 
		}
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		 Map<String, Object> docketMap = null;
		 for (Iterator<?> iterator = complaints.iterator(); iterator.hasNext();)
			{
				final Object[] values = (Object[]) iterator.next();
				docketMap = new HashMap<String, Object>();
				
				docketMap.put("docketNumber", (int)values[0]);
				if(((int)values[1])==0){
					docketMap.put("docketStatus", "Pending for Registration");
				}else if(((int)values[1])==1){
					docketMap.put("docketStatus", "Registered/Assigned");
				}else if(((int)values[1])==2){
					docketMap.put("docketStatus", "On Hold");
				}else if(((int)values[1])==3){
					docketMap.put("docketStatus", "Resolved");
				}else if(((int)values[1])==4){
					docketMap.put("docketStatus", "Reopen");
				}
				docketMap.put("docketSource", (String)values[2]);
				docketMap.put("consumerName", (String)values[3]);
				docketMap.put("consumerMobileNo", (Long)values[4]);
				docketMap.put("rrNumber", (String)values[5]);
				docketMap.put("address", (String)values[6]);
				docketMap.put("categoryName", (String)values[7]);
				docketMap.put("subCategoryName", (String)values[8]);
				docketMap.put("remarks", (String)values[9]);
				docketMap.put("userName", (String)values[10]);
				docketMap.put("docketCreatedDt", new SimpleDateFormat("MM/dd/yyyy HH:mm:ss aa").format((Timestamp)values[11]));
				try{
					docketMap.put("resolvedDate", new SimpleDateFormat("MM/dd/yyyy HH:mm:ss aa").format((Timestamp)values[12]));
				}catch(Exception e){
					docketMap.put("resolvedDate", "");
				}
				
				docketMap.put("docketSummary", (String)values[13]);
				docketMap.put("divisionName", (String)values[14]);
				docketMap.put("subDivisionName", (String)values[15]);
			
			result.add(docketMap);
	     }
		 return result;
	}
	@SuppressWarnings("unused")
	@RequestMapping(value="/dashboard/getSubDivisionBasedOnCategory/{parentId}/{docketStatus}",method ={ RequestMethod.POST,RequestMethod.GET} )
	public @ResponseBody Object getSubDivisionBasedOnCategory(@PathVariable Integer parentId,@PathVariable int docketStatus,HttpServletRequest request){
		HttpSession session=request.getSession(false);
		Date date1=(Date) session.getAttribute("date1");
		Date date2=(Date) session.getAttribute("date2");
		String date11=""; String date22="";
		if(date1 !=null && date2!=null){
			date11=dateTimeCalender.getDate3(date1); 
			date22=dateTimeCalender.getDate3(date2); 
		}
		
		ProjectHeirarchyEntity projectHeirarchyEntity=masterGenericDAO.getById(ProjectHeirarchyEntity.class,parentId);
		Set<String> ids=new HashSet<>();
		ids.add(projectHeirarchyEntity.getSiteCode());
		fetchChildren1(projectHeirarchyEntity,ids);		
		
		String sitecode="";
		Iterator<String> iter=ids.iterator();
		while(iter.hasNext()){
			sitecode+="'"+iter.next()+"',";
		}
		sitecode="("+sitecode.substring(0,sitecode.length()-1)+")";
		System.out.println(sitecode);
		Integer categoryId=(Integer) session.getAttribute("categoryId");
		String mode=(String) session.getAttribute("mode");
		String titel="";
		List<?> complaints=null;
		if(date1 !=null && date2 !=null || mode!=null || categoryId!=null){
			if(date1 !=null && date2 !=null && mode!=null && categoryId!=null){
				//SELECT ht.docketNumber,ht.docketStatus,ht.docketSource,ht.consumerName,ht.consumerMobileNo,ht.rrNumber,ht.address,
				titel = "From Date :"+date11+ " To Date:"+date22+" Cateory :"+session.getAttribute("categoryName")+" Mode:"+mode;
				complaints=masterGenericDAO.executeSimpleObjectQuery("SELECT c.docketNumber,c.docketStatus,c.docketSource,c.consumerName,c.consumerMobileNo,"
						+ "c.rrNumber,c.address,tc.categoryName,tsc.subCategoryName,c.remarks,c.userName,c.docketCreatedDt,c.resolvedDate,c.docketSummary,p.parent.text,"
						+ "p.text,dn.dnName,ur.urName,ur.urContactNo,c.assignedTo FROM ProjectHeirarchyEntity p,HelpDeskTicketEntity c,TicketCategoryEntity tc,TicketSubCategoryEntity tsc, Users ur INNER JOIN ur.designationEntity dn WHERE "
						+ "c.categoryId=tc.categoryId AND c.subCategoryId=tsc.subCategoryId AND c.siteCode IN "+sitecode+" AND c.docketStatus="+docketStatus+" AND p.siteCode=c.siteCode AND date(c.docketCreatedDt)>=TO_DATE('"+new SimpleDateFormat("dd/MM/yyyy").format(date1)+"','dd/MM/yyyy') AND "
						+ "date(c.docketCreatedDt)<=TO_DATE('"+new SimpleDateFormat("dd/MM/yyyy").format(date2)+"','dd/MM/yyyy') AND "
						+ "c.categoryId="+categoryId+"  AND c.docketSource='"+mode+"' AND c.invalidFlag='No' AND ur.urLoginName=c.assignedTo"); 
			}else if(mode==null && categoryId==null){
				titel="From Date :"+date11+ "To Date:"+date22;
				complaints=masterGenericDAO.executeSimpleObjectQuery("SELECT c.docketNumber,c.docketStatus,c.docketSource,c.consumerName,c.consumerMobileNo,"
						+ "c.rrNumber,c.address,tc.categoryName,tsc.subCategoryName,c.remarks,c.userName,c.docketCreatedDt,c.resolvedDate,c.docketSummary,p.parent.text,"
						+ "p.text,dn.dnName,ur.urName,ur.urContactNo,c.assignedTo FROM ProjectHeirarchyEntity p,HelpDeskTicketEntity c,TicketCategoryEntity tc,TicketSubCategoryEntity tsc, Users ur INNER JOIN ur.designationEntity dn WHERE "
						+ "c.categoryId=tc.categoryId AND c.subCategoryId=tsc.subCategoryId AND c.siteCode IN "+sitecode+" AND c.docketStatus="+docketStatus+" AND p.siteCode=c.siteCode AND date(c.docketCreatedDt)>=TO_DATE('"+new SimpleDateFormat("dd/MM/yyyy").format(date1)+"','dd/MM/yyyy') AND "
						+ "date(c.docketCreatedDt)<=TO_DATE('"+new SimpleDateFormat("dd/MM/yyyy").format(date2)+"','dd/MM/yyyy') AND c.invalidFlag='No' AND ur.urLoginName=c.assignedTo"); 
			}else if(date1==null && mode==null){
				titel="Cateory :"+session.getAttribute("categoryName");
				complaints=masterGenericDAO.executeSimpleObjectQuery("SELECT c.docketNumber,c.docketStatus,c.docketSource,c.consumerName,c.consumerMobileNo,"
						+ "c.rrNumber,c.address,tc.categoryName,tsc.subCategoryName,c.remarks,c.userName,c.docketCreatedDt,c.resolvedDate,c.docketSummary,p.parent.text,"
						+ "p.text,dn.dnName,ur.urName,ur.urContactNo,c.assignedTo FROM ProjectHeirarchyEntity p,HelpDeskTicketEntity c,TicketCategoryEntity tc,TicketSubCategoryEntity tsc, Users ur INNER JOIN ur.designationEntity dn WHERE "
						+ "c.categoryId=tc.categoryId AND c.subCategoryId=tsc.subCategoryId AND c.siteCode IN "+sitecode+" AND c.docketStatus="+docketStatus+" AND p.siteCode=c.siteCode AND c.invalidFlag='No' AND c.categoryId="+categoryId+"AND ur.urLoginName=c.assignedTo"); 
			}else if(date1==null && categoryId==null){	
				titel="Mode :"+mode;
				
				complaints=masterGenericDAO.executeSimpleObjectQuery("SELECT c.docketNumber,c.docketStatus,c.docketSource,c.consumerName,c.consumerMobileNo,"
						+ "c.rrNumber,c.address,tc.categoryName,tsc.subCategoryName,c.remarks,c.userName,c.docketCreatedDt,c.resolvedDate,c.docketSummary,p.parent.text,"
						+ "p.text,dn.dnName,ur.urName,ur.urContactNo,c.assignedTo FROM ProjectHeirarchyEntity p,HelpDeskTicketEntity c,TicketCategoryEntity tc,TicketSubCategoryEntity tsc, Users ur INNER JOIN ur.designationEntity dn WHERE "
						+ "c.categoryId=tc.categoryId AND c.subCategoryId=tsc.subCategoryId AND c.siteCode IN "+sitecode+" AND c.docketStatus="+docketStatus+" AND p.siteCode=c.siteCode AND c.docketSource='"+mode+"' AND c.invalidFlag='No' AND ur.urLoginName=c.assignedTo"); 
			}else if(date1!=null && categoryId!=null){
				titel= "From Date :"+date11+ "To Date:"+date22+" Cateory :"+session.getAttribute("categoryName");
				complaints=masterGenericDAO.executeSimpleObjectQuery("SELECT c.docketNumber,c.docketStatus,c.docketSource,c.consumerName,c.consumerMobileNo,"
						+ "c.rrNumber,c.address,tc.categoryName,tsc.subCategoryName,c.remarks,c.userName,c.docketCreatedDt,c.resolvedDate,c.docketSummary,p.parent.text,"
						+ "p.text,dn.dnName,ur.urName,ur.urContactNo,c.assignedTo FROM ProjectHeirarchyEntity p,HelpDeskTicketEntity c,TicketCategoryEntity tc,TicketSubCategoryEntity tsc, Users ur INNER JOIN ur.designationEntity dn WHERE "
						+ "c.categoryId=tc.categoryId AND c.subCategoryId=tsc.subCategoryId AND c.siteCode IN "+sitecode+" AND c.docketStatus="+docketStatus+" AND p.siteCode=c.siteCode AND date(c.docketCreatedDt)>=TO_DATE('"+new SimpleDateFormat("dd/MM/yyyy").format(date1)+"','dd/MM/yyyy') AND "
						+ "date(c.docketCreatedDt)<=TO_DATE('"+new SimpleDateFormat("dd/MM/yyyy").format(date2)+"','dd/MM/yyyy') AND c.invalidFlag='No' AND "
						+ "c.categoryId="+categoryId+" AND ur.urLoginName=c.assignedTo"); 
			}else if(date1!=null && mode!=null){
				titel="From Date :"+date11+ "To Date:"+date22+" Mode :"+mode;
				
				complaints=masterGenericDAO.executeSimpleObjectQuery("SELECT c.docketNumber,c.docketStatus,c.docketSource,c.consumerName,c.consumerMobileNo,"
						+ "c.rrNumber,c.address,tc.categoryName,tsc.subCategoryName,c.remarks,c.userName,c.docketCreatedDt,c.resolvedDate,c.docketSummary,p.parent.text,"
						+ "p.text,dn.dnName,ur.urName,ur.urContactNo,c.assignedTo FROM ProjectHeirarchyEntity p,HelpDeskTicketEntity c,TicketCategoryEntity tc,TicketSubCategoryEntity tsc, Users ur INNER JOIN ur.designationEntity dn WHERE "
						+ "c.categoryId=tc.categoryId AND c.subCategoryId=tsc.subCategoryId AND c.siteCode IN "+sitecode+" AND c.docketStatus="+docketStatus+" AND p.siteCode=c.siteCode AND date(c.docketCreatedDt)>=TO_DATE('"+new SimpleDateFormat("dd/MM/yyyy").format(date1)+"','dd/MM/yyyy') AND "
						+ "date(c.docketCreatedDt)<=TO_DATE('"+new SimpleDateFormat("dd/MM/yyyy").format(date2)+"','dd/MM/yyyy') AND "
						+ "c.docketSource='"+mode+"' AND c.invalidFlag='No' AND ur.urLoginName=c.assignedTo"); 
			}else if(categoryId!=null && mode!=null){
				titel="Cateory :"+session.getAttribute("categoryName")+" Mode :"+mode;
				complaints=masterGenericDAO.executeSimpleObjectQuery("SELECT c.docketNumber,c.docketStatus,c.docketSource,c.consumerName,c.consumerMobileNo,"
						+ "c.rrNumber,c.address,tc.categoryName,tsc.subCategoryName,c.remarks,c.userName,c.docketCreatedDt,c.resolvedDate,c.docketSummary,p.parent.text,"
						+ "p.text,dn.dnName,ur.urName,ur.urContactNo,c.assignedTo FROM ProjectHeirarchyEntity p,HelpDeskTicketEntity c,TicketCategoryEntity tc,TicketSubCategoryEntity tsc, Users ur INNER JOIN ur.designationEntity dn WHERE "
						+ "c.categoryId=tc.categoryId AND c.subCategoryId=tsc.subCategoryId AND c.siteCode IN "+sitecode+" AND c.docketStatus="+docketStatus+" AND p.siteCode=c.siteCode AND "
						+ "c.categoryId="+categoryId+"  AND c.docketSource='"+mode+"' AND c.invalidFlag='No' AND ur.urLoginName=c.assignedTo"); 
			}
		}else{
			
			complaints=masterGenericDAO.executeSimpleObjectQuery("SELECT c.docketNumber,c.docketStatus,c.docketSource,c.consumerName,c.consumerMobileNo,"
					+ "c.rrNumber,c.address,tc.categoryName,tsc.subCategoryName,c.remarks,c.userName,c.docketCreatedDt,c.resolvedDate,c.docketSummary,p.parent.text,"
					+ "p.text,dn.dnName,ur.urName,ur.urContactNo,c.assignedTo FROM ProjectHeirarchyEntity p,HelpDeskTicketEntity c,TicketCategoryEntity tc,TicketSubCategoryEntity tsc, Users ur INNER JOIN ur.designationEntity dn WHERE "
					+ "c.categoryId=tc.categoryId AND c.invalidFlag='No' AND c.subCategoryId=tsc.subCategoryId AND c.siteCode IN "+sitecode+" AND c.docketStatus="+docketStatus+" AND p.siteCode=c.siteCode AND ur.urLoginName=c.assignedTo"); 
		}
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		Map<String, Object> docketMap = null;
		for (Iterator<?> iterator = complaints.iterator(); iterator.hasNext();)
		{
			final Object[] values = (Object[]) iterator.next();
			docketMap = new HashMap<String, Object>();
			
			docketMap.put("docketNumber", (int)values[0]);
			if(((int)values[1])==0){
				docketMap.put("docketStatus", "Pending for Registration");
			}else if(((int)values[1])==1){
				docketMap.put("docketStatus", "Registered/Assigned");
			}else if(((int)values[1])==2){
				docketMap.put("docketStatus", "On Hold");
			}else if(((int)values[1])==3){
				docketMap.put("docketStatus", "Resolved");
			}else if(((int)values[1])==4){
				docketMap.put("docketStatus", "Reopen");
			}
			docketMap.put("docketSource", (String)values[2]);
			docketMap.put("consumerName", (String)values[3]);
			docketMap.put("consumerMobileNo", (Long)values[4]);
			docketMap.put("rrNumber", (String)values[5]);
			docketMap.put("address", (String)values[6]);
			docketMap.put("categoryName", (String)values[7]);
			docketMap.put("subCategoryName", (String)values[8]);
			docketMap.put("remarks", (String)values[9]);
			docketMap.put("userName", (String)values[10]);
			docketMap.put("docketCreatedDt", new SimpleDateFormat("MM/dd/yyyy HH:mm:ss aa").format((Timestamp)values[11]));
			try{
				docketMap.put("resolvedDate", new SimpleDateFormat("MM/dd/yyyy HH:mm:ss aa").format((Timestamp)values[12]));
			}catch(Exception e){
				docketMap.put("resolvedDate", "");
			}
			
			docketMap.put("docketSummary", (String)values[13]);
			docketMap.put("divisionName", (String)values[14]);
			docketMap.put("subDivisionName", (String)values[15]);
			

			if((String)values[16]==null ||((String)values[16]).equals("")){
				System.out.println("Desg null");
				docketMap.put("designation", "");
			}else{
				docketMap.put("designation", (String)values[16]);
			}
			
			if((String)values[17]==null ||((String)values[17]).equals("")){
				
				docketMap.put("assinedName", "");
			}else{
				docketMap.put("assinedName", (String)values[17]);
			}	
			
			if((String)values[18]==null ||((String)values[18]).equals("")){
				
				docketMap.put("officialMobileNo", "");
			}else{
				docketMap.put("officialMobileNo", (String)values[18]);
			}
			
       if((String)values[19]==null ||((String)values[19]).equals("")){
				
				docketMap.put("officialEmaill", "");
			}else{
				docketMap.put("officialEmaill", (String)values[19]);
			}
			

			
			
			result.add(docketMap);
		}
		return result;
	}
	@SuppressWarnings("unused")
	@RequestMapping(value="/dashboard/getSubDivisionBasedOnCategory1/{parentId}/{docketStatus}",method ={ RequestMethod.POST,RequestMethod.GET} )
	public @ResponseBody Object getSubDivisionBasedOnCategory1(@PathVariable Integer parentId,@PathVariable int docketStatus,HttpServletRequest request){
		HttpSession session=request.getSession(false);
		Date date1=(Date) session.getAttribute("date1");
		Date date2=(Date) session.getAttribute("date2");
		String date11=""; String date22="";
		if(date1 !=null && date2!=null){
			date11=dateTimeCalender.getDate3(date1); 
			date22=dateTimeCalender.getDate3(date2); 
		}
		
		ProjectHeirarchyEntity projectHeirarchyEntity=masterGenericDAO.getById(ProjectHeirarchyEntity.class,parentId);
		Set<String> ids=new HashSet<>();
		ids.add(projectHeirarchyEntity.getSiteCode());
		fetchChildren1(projectHeirarchyEntity,ids);		
		
		String sitecode="";
		Iterator<String> iter=ids.iterator();
		while(iter.hasNext()){
			sitecode+="'"+iter.next()+"',";
		}
		sitecode="("+sitecode.substring(0,sitecode.length()-1)+")";
		Integer categoryId=(Integer) session.getAttribute("categoryId");
		String mode=(String) session.getAttribute("mode");
		String titel="";
		List<?> complaints=null;
		if(date1 !=null && date2 !=null || mode!=null || categoryId!=null){
			if(date1 !=null && date2 !=null && mode!=null && categoryId!=null){
				//SELECT ht.docketNumber,ht.docketStatus,ht.docketSource,ht.consumerName,ht.consumerMobileNo,ht.rrNumber,ht.address,
				//
				titel = "From Date :"+date11+ " To Date:"+date22+" Cateory :"+session.getAttribute("categoryName")+" Mode:"+mode;
				complaints=masterGenericDAO.executeSimpleObjectQuery("SELECT ht.docketNumber,ht.docketSource,ht.docketCreatedDt,ht.consumerName,"
						+ "ht.consumerMobileNo,ht.consumerEmailId,ht.facebookId FROM HelpDeskTicketEntity ht WHERE "
						+ "date(ht.docketCreatedDt)>=TO_DATE('"+new SimpleDateFormat("dd/MM/yyyy").format(date1)+"','dd/MM/yyyy') AND "
						+ "date(ht.docketCreatedDt)<=TO_DATE('"+new SimpleDateFormat("dd/MM/yyyy").format(date2)+"','dd/MM/yyyy') AND "
						+ "ht.siteCode IN "+sitecode+" AND ht.invalidFlag='No' AND ht.docketSource='"+mode+"' AND ht.categoryId="+categoryId+" AND ht.docketStatus=0 ORDER BY ht.docketNumber DESC");
				
			}else if(mode==null && categoryId==null){
				titel="From Date :"+date11+ "To Date:"+date22;
				complaints=masterGenericDAO.executeSimpleObjectQuery("SELECT ht.docketNumber,ht.docketSource,ht.docketCreatedDt,ht.consumerName,"
						+ "ht.consumerMobileNo,ht.consumerEmailId,ht.facebookId FROM HelpDeskTicketEntity ht WHERE "
						+ "date(ht.docketCreatedDt)>=TO_DATE('"+new SimpleDateFormat("dd/MM/yyyy").format(date1)+"','dd/MM/yyyy') AND "
						+ "date(ht.docketCreatedDt)<=TO_DATE('"+new SimpleDateFormat("dd/MM/yyyy").format(date2)+"','dd/MM/yyyy') AND "
						+ "ht.siteCode IN "+sitecode+" AND ht.invalidFlag='No' AND ht.docketStatus=0 ORDER BY ht.docketNumber DESC");
			}else if(date1==null && mode==null){
				titel="Cateory :"+session.getAttribute("categoryName");
				complaints=masterGenericDAO.executeSimpleObjectQuery("SELECT ht.docketNumber,ht.docketSource,ht.docketCreatedDt,ht.consumerName,"
						+ "ht.consumerMobileNo,ht.consumerEmailId,ht.facebookId FROM HelpDeskTicketEntity ht WHERE "
						+ "ht.siteCode IN "+sitecode+" AND ht.invalidFlag='No' AND ht.categoryId="+categoryId+" AND ht.docketStatus=0 ORDER BY ht.docketNumber DESC");
			}else if(date1==null && categoryId==null){	
				titel="Mode :"+mode;
				complaints=masterGenericDAO.executeSimpleObjectQuery("SELECT ht.docketNumber,ht.docketSource,ht.docketCreatedDt,ht.consumerName,"
						+ "ht.consumerMobileNo,ht.consumerEmailId,ht.facebookId FROM HelpDeskTicketEntity ht WHERE "
						+ "ht.siteCode IN "+sitecode+" AND ht.invalidFlag='No' AND ht.docketSource='"+mode+"' AND ht.docketStatus=0 ORDER BY ht.docketNumber DESC");
			}else if(date1!=null && categoryId!=null){
				titel= "From Date :"+date11+ "To Date:"+date22+" Cateory :"+session.getAttribute("categoryName");
				complaints=masterGenericDAO.executeSimpleObjectQuery("SELECT ht.docketNumber,ht.docketSource,ht.docketCreatedDt,ht.consumerName,"
						+ "ht.consumerMobileNo,ht.consumerEmailId,ht.facebookId FROM HelpDeskTicketEntity ht WHERE "
						+ "date(ht.docketCreatedDt)>=TO_DATE('"+new SimpleDateFormat("dd/MM/yyyy").format(date1)+"','dd/MM/yyyy') AND "
						+ "date(ht.docketCreatedDt)<=TO_DATE('"+new SimpleDateFormat("dd/MM/yyyy").format(date2)+"','dd/MM/yyyy') AND "
						+ "ht.siteCode IN "+sitecode+" AND ht.invalidFlag='No' AND ht.categoryId="+categoryId+" AND ht.docketStatus=0 ORDER BY ht.docketNumber DESC");
			}else if(date1!=null && mode!=null){
				titel="From Date :"+date11+ "To Date:"+date22+" Mode :"+mode;
				complaints=masterGenericDAO.executeSimpleObjectQuery("SELECT ht.docketNumber,ht.docketSource,ht.docketCreatedDt,ht.consumerName,"
						+ "ht.consumerMobileNo,ht.consumerEmailId,ht.facebookId FROM HelpDeskTicketEntity ht WHERE "
						+ "date(ht.docketCreatedDt)>=TO_DATE('"+new SimpleDateFormat("dd/MM/yyyy").format(date1)+"','dd/MM/yyyy') AND "
						+ "date(ht.docketCreatedDt)<=TO_DATE('"+new SimpleDateFormat("dd/MM/yyyy").format(date2)+"','dd/MM/yyyy') AND "
						+ "ht.siteCode IN "+sitecode+" AND ht.invalidFlag='No' AND ht.docketSource='"+mode+"' AND ht.docketStatus=0 ORDER BY ht.docketNumber DESC");
			}else if(categoryId!=null && mode!=null){
				titel="Cateory :"+session.getAttribute("categoryName")+" Mode :"+mode;
				complaints=masterGenericDAO.executeSimpleObjectQuery("SELECT ht.docketNumber,ht.docketSource,ht.docketCreatedDt,ht.consumerName,"
						+ "ht.consumerMobileNo,ht.consumerEmailId,ht.facebookId FROM HelpDeskTicketEntity ht WHERE "
						+ "ht.siteCode IN "+sitecode+" AND ht.invalidFlag='No' AND ht.docketSource='"+mode+"' AND ht.categoryId="+categoryId+" AND"
								+ " ht.docketStatus=0 ORDER BY ht.docketNumber DESC");
			}
		}else{
			complaints=masterGenericDAO.executeSimpleObjectQuery("SELECT ht.docketNumber,ht.docketSource,ht.docketCreatedDt,ht.consumerName,"
					+ "ht.consumerMobileNo,ht.consumerEmailId,ht.facebookId FROM HelpDeskTicketEntity ht WHERE "
					+ "ht.siteCode IN "+sitecode+" AND ht.docketStatus=0 AND ht.invalidFlag='No' ORDER BY ht.docketNumber DESC");
		}
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		 Map<String, Object> docketMap = null;
		 for (Iterator<?> iterator = complaints.iterator(); iterator.hasNext();)
			{
				final Object[] values = (Object[]) iterator.next();
				docketMap = new HashMap<String, Object>();
				
				docketMap.put("docketNumber", (int)values[0]);
				docketMap.put("docketSource", (String)values[1]);
				docketMap.put("docketCreatedDt", new SimpleDateFormat("MM/dd/yyyy hh:mm a").format((Timestamp)values[2]));
				if(((String)values[3])==null || ((String)values[3]).equals("")){
					docketMap.put("consumerName", "");
				}else{
					docketMap.put("consumerName", (String)values[3]);
				}
				if((Long)values[4]==0){
					docketMap.put("consumerMobileNo", "");
				}else{
					docketMap.put("consumerMobileNo", (Long)values[4]);
				}
				if(((String)values[5])==null || ((String)values[5]).equals("")){
					docketMap.put("consumerEmailId", "");
				}else{
					docketMap.put("consumerEmailId", (String)values[5]);
				}
				if(((String)values[6])==null || ((String)values[6]).equals("")){
					docketMap.put("facebookId", "");
				}else{
					docketMap.put("facebookId", (String)values[6]);
				}
//Added By Raju
				
				java.util.Date currentDate=new java.util.Date();
				Date creatdt =  (Timestamp) values[2];
				DateTime dt1 = null;
				DateTime dt2 =null;
				if(currentDate.after(creatdt)){
					dt2 = new DateTime(creatdt);
					dt1 = new DateTime(currentDate);
				}else{
					dt1= new DateTime(creatdt);
					dt2 = new DateTime(currentDate);
				}
				
				final Period period = new Period(dt2,dt1);
				String noOfMonths="";
				if(period.getYears()!=0){
					if(period.getYears()==1){
						noOfMonths=period.getYears()+" Year, ";
					}else{
					noOfMonths=period.getYears()+" Years, ";
					}
				}
				if(period.getMonths()!=0){
					if(period.getMonths()==1){
						noOfMonths=noOfMonths+""+period.getMonths()+" Month, ";
					}else{
					noOfMonths=noOfMonths+""+period.getMonths()+" Months, ";
					}
				}
				if(period.getWeeks()!=0){
					if(period.getWeeks()==1){
						noOfMonths=noOfMonths+""+period.getWeeks()+" Week, ";
					}else{
						noOfMonths=noOfMonths+""+period.getWeeks()+" Weeks, ";
					}
				}
				noOfMonths=noOfMonths+" "+period.getDays()+" Days, "+period.getHours()+" Hours";
				docketMap.put("elapsedTime", noOfMonths);
			
			result.add(docketMap);
			}
		 return result;
	}
	@RequestMapping(value = "/registerMaster/readRegisterBasedOnDate", method = RequestMethod.GET)
	public @ResponseBody String readRegisterBasedOnDate(HttpServletRequest req) throws ParseException
	{
		Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(req.getParameter("fromDate"));
		Date date2 = new SimpleDateFormat("dd/MM/yyyy").parse(req.getParameter("toDate"));
		int temp=date1.compareTo(date2);
		if(temp>0){
			date1=new SimpleDateFormat("dd/MM/yyyy").parse(req.getParameter("toDate"));
			date2=new SimpleDateFormat("dd/MM/yyyy").parse(req.getParameter("fromDate"));
		}
		HttpSession session=req.getSession(false);
		session.setAttribute("date1",date1);
		session.setAttribute("date2",date2);
		session.setAttribute("tabdata", (String)req.getParameter("tab"));
		return "success";
	}
	@RequestMapping(value = "/dashboard/category", method = RequestMethod.GET)
	public @ResponseBody List<?> setCategory(HttpServletRequest req) throws ParseException
	{
		HttpSession session=req.getSession(false);
		session.setAttribute("categoryId", Integer.parseInt(req.getParameter("category")));
		session.setAttribute("categoryName", req.getParameter("categoryName"));
		session.setAttribute("tabdata", (String)req.getParameter("tab"));
		return null;
	}
	@RequestMapping(value = "/dashboard/mode", method = RequestMethod.GET)
	public @ResponseBody List<?> setMode(HttpServletRequest req) throws ParseException
	{
		HttpSession session=req.getSession(false);
		session.setAttribute("mode", (String)req.getParameter("category"));
		session.setAttribute("tabdata", (String)req.getParameter("tab"));
		System.out.println((String)req.getParameter("tab"));
		return null;
	}
	@RequestMapping(value = "/dashboard/clearFilter", method = RequestMethod.GET)
	public @ResponseBody Object clearFilter(HttpServletRequest req) throws ParseException
	{
		HttpSession session=req.getSession(false);
		session.removeAttribute("date1");
		session.removeAttribute("date2");
		session.removeAttribute("categoryId");
		session.removeAttribute("mode");
		session.removeAttribute("titleName");
		session.removeAttribute("tabdata");
		session.removeAttribute("title");
		return null;
	}
	//ticketCategoryService.getAllActiveCategories();
	@RequestMapping(value = "/dashboard/categoryList",method={RequestMethod.POST,RequestMethod.GET}) 
	public @ResponseBody List<?> categoryList(HttpServletRequest request) {
		return ticketCategoryService.getAllActiveCategories();
	}
	private ProjectHeirarchyEntity fetchChildren1(ProjectHeirarchyEntity parent,Set<String> divisionIds) {
		Hibernate.initialize(parent.getChilds());
		for (ProjectHeirarchyEntity child : parent.getChilds()) {
			fetchChildren1(child,divisionIds);
			divisionIds.add(child.getSiteCode());
		}
		return parent;
	}
	
	String schemaname=PgrsSchemaName.cescPgrsSchema;
	
	/* ----------------------------------- dashboardsMD------------------------------------------------------------------ ------------*/
	@RequestMapping(value ="/dashboardsMD" , method ={ RequestMethod.POST,RequestMethod.GET})
	public String dashboardsMD(Model model, HttpServletRequest request,HttpServletResponse response,Principal principal) throws IOException, Exception {
		model.addAttribute("modulename","Dashboard");
		return "pgrs/dashboardsMD";
		
	}
	@RequestMapping(value="/dashboardsMD/circleWiseData", method = RequestMethod.GET)
	public @ResponseBody Object circleWiseData(HttpServletRequest request){
		List<?> list=masterGenericDAO.executeSimpleObjectQuery("SELECT ph.text,ph.siteCode,ph.id FROM ProjectHeirarchyEntity ph WHERE ph.type='Circle' ORDER BY ph.id ASC");
		List<String> category=new ArrayList<>();
		List<Long> pending=new ArrayList<>();
		List<Long> assigned=new ArrayList<>();
		List<Long> onhold=new ArrayList<>();
		List<Long> resolved=new ArrayList<>();
		List<Long> reopned=new ArrayList<>();
		List<Long> total=new ArrayList<>();
		
		for(Iterator<?> itr=list.iterator();itr.hasNext();){
			Object[] obj=(Object[]) itr.next();
			String query1="SELECT count(*),COUNT(case when ht.docketStatus=0 then 0 END) as ZERO,COUNT(case when ht.docketStatus=1 then 1 END) AS ONE,"
					+"COUNT(case when ht.docketStatus=2 then 2 END) AS TWO,COUNT(case when ht.docketStatus=3 then 3 END) AS THREE,"
					+"COUNT(case when ht.docketStatus=4 then 4 END)AS FOUR FROM HelpDeskTicketEntity ht WHERE ht.invalidFlag='No' AND ht.siteCode LIKE '"+(String)obj[1]+"%' ";
			
			List<?> complaints = masterGenericDAO.executeSimpleObjectQuery(query1);
			for(Iterator<?> iterator=complaints.iterator();iterator.hasNext();){
				Object[] objects=(Object[]) iterator.next();
				category.add((String)obj[0]);
				total.add((Long)objects[0]);
				pending.add((Long)objects[1]);
				assigned.add((Long)objects[2]);
				onhold.add((Long)objects[3]);
				resolved.add((Long)objects[4]);
				reopned.add((Long)objects[5]);
			}
		}
		List<Object> resultList=new ArrayList<>();
		resultList.add(category);
		resultList.add(total);
		resultList.add(pending);
		resultList.add(assigned);
		resultList.add(onhold);
		resultList.add(resolved);
		resultList.add(reopned);
		return resultList;	
	}
	@RequestMapping(value = "/dashboard/categoryWiseDetails", method = RequestMethod.GET)
	public @ResponseBody List<?> categoryWiseDetails(HttpServletRequest req) throws ParseException
	{
		List<Object> resultList=pgrsReportService.categoryWiseDetails();
		return resultList;
	}
	@RequestMapping(value = "/dashboard/monthWiseDetails", method = RequestMethod.GET)
	public @ResponseBody List<?> monthWiseDetails(HttpServletRequest req) throws ParseException
	{
		List<Object> resultList=pgrsReportService.monthWiseDetails();
		return resultList;
	}
	@RequestMapping(value = "/dashboard/totalComplaints", method = RequestMethod.GET)
	public @ResponseBody List<?> totalComplaints(HttpServletRequest req) throws ParseException
	{
		List<Map<String,Object>> result=new ArrayList<>();
		List<Map<String,Object>> result1=new ArrayList<>();
		/*List<?> circle = masterGenericDAO.executeSimpleObjectQuery("SELECT p.newSitecode,p.section,p.newSitecode FROM LocationsEntity p ORDER BY p.section ASC");
		for(Iterator<?> iterator=circle.iterator();iterator.hasNext();){
			Object[] circleObject=(Object[]) iterator.next();
			int circleId = 0;
			String text=(String)circleObject[1]; 
			String sitecode=(String)circleObject[2]; 
			
		}*/
		
		Map<String,Object> data=pgrsReportService.totalComplaints();
		result.add(data);	
		
		Map<String,Object> data1=pgrsReportService.escalatedComplaints();
		result1.add(data1);
		
		List<Object> res=new ArrayList<>();
		res.add(result);
		res.add(result1);
		return res;
	}
}
