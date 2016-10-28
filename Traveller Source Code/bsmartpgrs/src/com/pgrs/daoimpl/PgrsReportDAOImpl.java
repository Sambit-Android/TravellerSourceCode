package com.pgrs.daoimpl;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Repository;

import com.pgrs.dao.PgrsReportDAO;
import com.pgrs.entity.HelpDeskTicketEntity;
import com.pgrs.util.PgrsSchemaName;

@Repository
public class PgrsReportDAOImpl extends GenericDAOImpl<HelpDeskTicketEntity> implements PgrsReportDAO{
	String schemaname=PgrsSchemaName.cescPgrsSchema;
	@Override
	public List<?> getComplaintStatus(Date fromdate, Date todate,String siteCodes) 
	{
		
		String queryString = "SELECT A.id, A.category_name, COUNT(case when B.DOCKETSTATUS=0 then 1 END) AS ZERO, COUNT(case when B.DOCKETSTATUS=1 then 1 END) AS ONE, "
				+"COUNT(case when B.DOCKETSTATUS=2 then 1 END) AS TWO, COUNT(case when B.DOCKETSTATUS=3 then 1 END) AS THREE, COUNT(case when B.DOCKETSTATUS=4 then 1 END) AS FOUR "
				+"FROM "
				+ "(SELECT id, category_name FROM "+schemaname+".pgrs_category )a "
				+ "left join "
				+ "(select categoryid, docketcreateddatetime, docketstatus, sitecode  from "+schemaname+".pgrs_complaints WHERE invalid_flag='No' )b "
				+"ON a.ID=B.categoryid WHERE  B.SITECODE IN"+siteCodes +" AND date(B.docketcreateddatetime)>=to_date('"+new SimpleDateFormat("dd/MM/yyyy").format(fromdate)+"','dd/MM/yyyy') AND date(B.docketcreateddatetime)<=to_date('"+new SimpleDateFormat("dd/MM/yyyy").format(todate)+"','dd/MM/yyyy') "
				+" group by A.id, A.category_name order by A.id, A.category_name ASC";
		final Query query = this.entityManager.createNativeQuery(queryString);
		return query.getResultList();
	}
	@Override
	public List<?> getaccidentStatusReport(Date fromdate, Date todate,String siteCodes) {
		
		String queryString = "SELECT A.id, A.category_name, COUNT(case when B.DOCKETSTATUS=0 then 1 END) AS ZERO, COUNT(case when B.DOCKETSTATUS=1 then 1 END) AS ONE, "
				+"COUNT(case when B.DOCKETSTATUS=2 then 1 END) AS TWO, COUNT(case when B.DOCKETSTATUS=3 then 1 END) AS THREE, COUNT(case when B.DOCKETSTATUS=4 then 1 END) AS FOUR "
				+"FROM "
				+ "(SELECT id, category_name FROM "+schemaname+".pgrs_category )a "
				+ "left join "
				+ "(select categoryid, docketcreateddatetime, docketstatus, sitecode  from "+schemaname+".pgrs_complaints WHERE subcategoryid in(19,20,24,26,27,28,36) AND sitecode IN"+siteCodes +" AND date(docketcreateddatetime)>=to_date('"+new SimpleDateFormat("dd/MM/yyyy").format(fromdate)+"','dd/MM/yyyy') AND date(docketcreateddatetime)<=to_date('"+new SimpleDateFormat("dd/MM/yyyy").format(todate)+"','dd/MM/yyyy'))b "
				+"ON a.ID=B.categoryid"
				+" group by A.id, A.category_name order by A.id, A.category_name ASC";
		final Query query = this.entityManager.createNativeQuery(queryString);
		return query.getResultList();
	}
	@Override
	public List<?> esclatedStatusReport(Date fromdate, Date todate,String siteCodes) {
		System.out.println(siteCodes);
		String queryString="SELECT m.consumername, m.consumermobilenumber,m.docketnumber,m.docketcreateddatetime,m.address,m.categoryid, m.docketsummary,m.assignedto,m.subcategoryid,"
				+"m.category_name  , m.subcategory_name, m.esc_level,m.name,m.dn_name,m.ur_login_name,m.ur_contact_no,m.sitecode,"			
				+"case when m.esc_level!=1 then (SELECT u.name||' - '||u.ur_contact_no FROM "+schemaname+".pgrs_escalation e,"+schemaname+".core_users u WHERE e.dn_id=u.dn_id AND e.level=m.esc_level AND e.subcategory_id=m.subcategoryid AND u.office_id=(SELECT ph.id FROM "+schemaname+".core_project_hierarchy ph WHERE ph.site_code=m.sitecode) LIMIT 1) END AS one,"
				+"case when m.esc_level!=1 then (SELECT u.name||' - '||u.ur_contact_no FROM "+schemaname+".pgrs_escalation e,"+schemaname+".core_users u WHERE e.dn_id=u.dn_id AND e.level=m.esc_level AND e.subcategory_id=m.subcategoryid AND u.office_id=(SELECT ph.parent_id FROM "+schemaname+".core_project_hierarchy ph WHERE ph.site_code=m.sitecode) LIMIT 1) END AS TWO,"
				+"case when m.esc_level!=1 then (SELECT u.name||' - '||u.ur_contact_no FROM "+schemaname+".pgrs_escalation e,"+schemaname+".core_users u WHERE e.dn_id=u.dn_id AND e.level=m.esc_level AND e.subcategory_id=m.subcategoryid AND u.office_id=(SELECT parent_id FROM "+schemaname+".core_project_hierarchy WHERE id=(SELECT ph.parent_id FROM "+schemaname+".core_project_hierarchy ph WHERE ph.site_code=m.sitecode)) LIMIT 1) END AS THREE,"
				+"case when m.esc_level!=1 then (SELECT u.name||' - '||u.ur_contact_no FROM "+schemaname+".pgrs_escalation e,"+schemaname+".core_users u WHERE e.dn_id=u.dn_id AND e.level=m.esc_level AND e.subcategory_id=m.subcategoryid AND u.office_id=(SELECT parent_id FROM "+schemaname+".core_project_hierarchy WHERE id=(SELECT parent_id FROM "+schemaname+".core_project_hierarchy WHERE id=(SELECT ph.parent_id FROM "+schemaname+".core_project_hierarchy ph WHERE ph.site_code=m.sitecode))) LIMIT 1) END AS FOUR,"
				+"case when m.esc_level!=1 then (SELECT u.name||' - '||u.ur_contact_no FROM "+schemaname+".pgrs_escalation e,"+schemaname+".core_users u WHERE e.dn_id=u.dn_id AND e.level=m.esc_level AND e.subcategory_id=m.subcategoryid AND u.office_id=(SELECT parent_id FROM "+schemaname+".core_project_hierarchy WHERE id=(SELECT parent_id FROM "+schemaname+".core_project_hierarchy WHERE id=(SELECT parent_id FROM "+schemaname+".core_project_hierarchy WHERE id=(SELECT ph.parent_id FROM "+schemaname+".core_project_hierarchy ph WHERE ph.site_code=m.sitecode)))) LIMIT 1) END AS FIVE,"
				+"case when m.esc_level!=1 then (SELECT u.name||' - '||u.ur_contact_no FROM "+schemaname+".pgrs_escalation e,"+schemaname+".core_users u WHERE e.dn_id=u.dn_id AND e.level=m.esc_level AND e.subcategory_id=m.subcategoryid AND u.office_id=(SELECT parent_id FROM "+schemaname+".core_project_hierarchy WHERE id=(SELECT parent_id FROM "+schemaname+".core_project_hierarchy WHERE id=(SELECT parent_id FROM "+schemaname+".core_project_hierarchy WHERE id=(SELECT parent_id FROM "+schemaname+".core_project_hierarchy WHERE id=(SELECT ph.parent_id FROM "+schemaname+".core_project_hierarchy ph WHERE ph.site_code=m.sitecode))))) LIMIT 1) END AS SIX"
				+" FROM"  
				+"(SELECT z.consumername, z.consumermobilenumber,z.docketnumber,z.docketcreateddatetime,z.address,z.categoryid, z.docketsummary,z.assignedto,z.subcategoryid," 
				+" z.category_name  , z.subcategory_name, z.esc_level,y.name,y.dn_name,y.ur_login_name,y.ur_contact_no,z.sitecode from " 
				+"(select " 
				+"bb.consumername, bb.consumermobilenumber,bb.docketnumber,bb.docketcreateddatetime,bb.address,bb.categoryid, bb.docketsummary,bb.assignedto,bb.subcategoryid," 
				+"bb.category_name  , bb.subcategory_name, d.esc_level,bb.sitecode  from"
				+"(SELECT DISTINCT(docketnumber),id, esc_level FROM "+schemaname+".pgrs_escalated_complaints)d " 
				+"left join "
				+"(select " 
				+"aa.consumername, aa.consumermobilenumber,aa.docketnumber,aa.docketcreateddatetime,aa.address,aa.categoryid, aa.docketsummary,aa.assignedto,aa.subcategoryid," 
				+"aa.category_name  , c.subcategory_name,AA.sitecode,AA.esc_level from " 
				+"(select A.consumername, A.consumermobilenumber,A.docketnumber,A.docketcreateddatetime,A.address,A.categoryid, A.docketsummary,A.assignedto,a.subcategoryid," 
				+"B.category_name,a.sitecode,a.esc_level  FROM " 
				+"(SELECT consumername, consumermobilenumber,docketnumber,docketcreateddatetime,address,categoryid, docketsummary,assignedto, subcategoryid, sitecode,esc_level FROM "+schemaname+".pgrs_complaints WHERE sitecode IN"+siteCodes +" AND date(docketcreateddatetime)>=to_date('"+new SimpleDateFormat("dd/MM/yyyy").format(fromdate)+"','dd/MM/yyyy') AND date(docketcreateddatetime)<=to_date('"+new SimpleDateFormat("dd/MM/yyyy").format(todate)+"','dd/MM/yyyy') )a " 
				+" left join " 
				+"(SELECT id, category_name   FROM "+schemaname+".pgrs_category)b " 
				+"on a.categoryid=b.id)AA "
				+"left join " 
				+"(SELECT id, subcategory_name   FROM "+schemaname+".pgrs_subcategory)c " 
				+"ON AA.subcategoryid=c.id)bb "
				+"ON d.docketnumber=bb.docketnumber AND d.esc_level= bb.esc_level)z, " 
				+"(SELECT cu.name,dn.dn_name,cu.ur_login_name,cu.ur_contact_no FROM "+schemaname+".core_users cu,"+schemaname+".core_designation dn WHERE dn.id=cu.dn_id)y WHERE z.assignedto=y.ur_login_name)m " 
				+"group by m.consumername, m.consumermobilenumber,m.docketnumber,m.docketcreateddatetime,m.address,m.categoryid, m.docketsummary,m.assignedto,m.subcategoryid, "
				+"m.category_name  , m.subcategory_name, m.esc_level,m.name,m.dn_name,m.ur_login_name,m.ur_contact_no,m.sitecode";
		
		final Query query = this.entityManager.createNativeQuery(queryString);
		return query.getResultList();
	}

	@Override
	public List<?> viewComplaintStatusReport(String fromMonth, String toMonth,String sitecode,String status ,HttpServletRequest req) 
	{
		String sql="";
		if(status.equalsIgnoreCase("resolved"))
		{
			 sql="SELECT A.id, A.category_name, COUNT(*) AS TOTAL,COUNT(case when B.DOCKETSTATUS=3 then 1 END) AS RESOLVED FROM (SELECT id, category_name FROM "+schemaname+".pgrs_category )a left join (select categoryid, docketcreateddatetime, docketstatus, sitecode  from "+schemaname+".pgrs_complaints )b ON a.ID=B.categoryid WHERE "
						+ " SITECODE IN "+sitecode+" AND to_char(B.docketcreateddatetime,'yyyyMM')BETWEEN '"+fromMonth+"' AND '"+toMonth+"' group by A.id, A.category_name order by A.id, A.category_name";
				
		}
		else if(status.equalsIgnoreCase("unresolved"))
		{
			 sql="SELECT A.id, A.category_name, COUNT(*) AS TOTAL,COUNT(case when B.DOCKETSTATUS<>3 then 1 END) AS RESOLVED FROM (SELECT id, category_name FROM "+schemaname+".pgrs_category )a left join (select categoryid, docketcreateddatetime, docketstatus, sitecode  from "+schemaname+".pgrs_complaints )b ON a.ID=B.categoryid WHERE "
						+ " SITECODE IN "+sitecode+" AND to_char(B.docketcreateddatetime,'yyyyMM')BETWEEN '"+fromMonth+"' AND '"+toMonth+"' group by A.id, A.category_name order by A.id, A.category_name";
		}
		System.out.println("-=-=-"+sql);
		return entityManager.createNativeQuery(sql).getResultList();
	}

	@Override
	public List<?> showCusSatisfactionResults(String fromMonth, String toMonth,String sitecode, HttpServletRequest req)
	{
		String sql="SELECT A.id, A.category_name, COUNT(case when B.feedbackrating=3 then 1 END) AS GOOD,COUNT(case when B.feedbackrating=1 then 1 END) AS POOR,COUNT(case when B.feedbackrating=4 then 1 END) AS EXE FROM (SELECT id, category_name FROM "+schemaname+".pgrs_category )a left join (select categoryid, docketcreateddatetime, docketstatus, sitecode,feedbackrating  from "+schemaname+".pgrs_complaints )b ON a.ID=B.categoryid WHERE  SITECODE IN "+sitecode+" AND to_char(B.docketcreateddatetime,'yyyyMM')BETWEEN '"+fromMonth+"' AND '"+toMonth+"'group by A.id, A.category_name order by A.id, A.category_name ASC";
		return entityManager.createNativeQuery(sql).getResultList();
	}
	@Override
	public List<?> showKERCStatusReport(Date date1, Date date2, String sitecode)
	{
		String sql="SELECT A.category_name,count(case when b.resolveddateandtime<=b.estimatedresolvedatetime then 1 end) resolvedwithintimeline,count(case when b.resolveddateandtime>b.estimatedresolvedatetime then 1 end) resolvedtimeline,count(*) as total,count(case when b.docketstatus=3 then 1 end) resolved,count(case when b.docketstatus<>3 then 1 end) unresolved FROM(SELECT id, category_name FROM "+schemaname+".pgrs_category )a left join (select categoryid, docketcreateddatetime, docketstatus, sitecode,estimatedresolvedatetime, resolveddateandtime   from "+schemaname+".pgrs_complaints )b ON a.ID=B.categoryid WHERE  SITECODE IN "+sitecode+" AND date(docketcreateddatetime)>=to_date('"+new SimpleDateFormat("dd/MM/yyyy").format(date1)+"','dd/MM/yyyy')AND date(docketcreateddatetime)<=to_date('"+new SimpleDateFormat("dd/MM/yyyy").format(date2)+"','dd/MM/yyyy') group by  A.id,A.category_name order by A.id, A.category_name ASC";
		System.out.println("-=-=-"+sql);
		return entityManager.createNativeQuery(sql).getResultList();
	}
	@Override
	public List<?> categoryWiseDetails() {
		String queryString = "SELECT A.id, A.category_name, COUNT(case when B.DOCKETSTATUS=0 then 1 END) AS ZERO, COUNT(case when B.DOCKETSTATUS=1 then 1 END) AS ONE, "
				+"COUNT(case when B.DOCKETSTATUS=2 then 1 END) AS TWO, COUNT(case when B.DOCKETSTATUS=3 then 1 END) AS THREE, COUNT(case when B.DOCKETSTATUS=4 then 1 END) AS FOUR "
				+"FROM "
				+ "(SELECT id, category_name FROM "+schemaname+".pgrs_category )a "
				+ "left join "
				+ "(select categoryid, docketcreateddatetime, docketstatus, sitecode  from "+schemaname+".pgrs_complaints )b "
				+"ON a.ID=B.categoryid"
				+" group by A.id, A.category_name order by A.id, A.category_name ASC";
		final Query query = this.entityManager.createNativeQuery(queryString);
		return query.getResultList();
	}
	@Override
	public List<?> monthWiseDetails() {
		return entityManager.createNamedQuery("HelpDeskTicketEntity.monthWiseDetails").getResultList();
	}
	@Override
	public List<?> showAverageTimeBreached(String fromMonth, String toMonth,String sitecode, String status, HttpServletRequest req) {
		String sql="";
		if(status.equalsIgnoreCase("resolved"))
		{
			sql="SELECT A.id, A.category_name, COUNT(*) AS TOTAL,COUNT(case when B.DOCKETSTATUS=3 then 1 END) AS RESOLVED,"
					+"avg(extract ( DAY from  B.resolveddateandtime - B.docketcreateddatetime)*24 + extract ( HOUR   from  B.resolveddateandtime - B.docketcreateddatetime)) as AVG1,"
					+"avg(extract ( DAY from  B.estimatedresolvedatetime - B.docketcreateddatetime)*24 + extract ( HOUR   from   B.estimatedresolvedatetime- B.docketcreateddatetime))AS AVG2 FROM" 
					+"(SELECT id, category_name FROM "+schemaname+".pgrs_category )a left join (select categoryid, docketcreateddatetime, docketstatus, "
					+"sitecode,resolveddateandtime,estimatedresolvedatetime  from "+schemaname+".pgrs_complaints )b ON a.ID=B.categoryid WHERE "
					+"SITECODE IN "+sitecode+" AND to_char(B.docketcreateddatetime,'yyyyMM')BETWEEN '"+fromMonth+"' AND '"+toMonth+"' group by A.id, A.category_name order by A.id, A.category_name";
		}
		else if(status.equalsIgnoreCase("unresolved"))
		{
			sql="SELECT A.id, A.category_name, COUNT(*) AS TOTAL,COUNT(case when B.DOCKETSTATUS<>3 then 1 END) AS UNRESOLVED,"
					+"avg(extract ( DAY from  CURRENT_TIMESTAMP - docketcreateddatetime)*24 + extract ( HOUR   from  CURRENT_TIMESTAMP - docketcreateddatetime))AS AVG1 ,"
					+"avg(extract ( DAY from  estimatedresolvedatetime - docketcreateddatetime)*24 + extract ( HOUR   from   estimatedresolvedatetime- docketcreateddatetime))AS AVG2 FROM" 
					+"(SELECT id, category_name FROM "+schemaname+".pgrs_category )a left join (select categoryid, docketcreateddatetime, docketstatus, "
					+"sitecode,resolveddateandtime,estimatedresolvedatetime  from "+schemaname+".pgrs_complaints )b ON a.ID=B.categoryid WHERE "
					+"SITECODE IN "+sitecode+" AND to_char(B.docketcreateddatetime,'yyyyMM')BETWEEN '"+fromMonth+"' AND '"+toMonth+"' group by A.id, A.category_name order by A.id, A.category_name";
		}else if(status.equalsIgnoreCase("escalated")){
			
		}
		System.out.println("-=-=-"+sql);
		return entityManager.createNativeQuery(sql).getResultList();
	}
	@Override
	public List<?> dayWiseReport(int selectedMonth,int year) {
		String queryString = "SELECT to_char(A.DATE1,'dd-MM-yyyy'),A.CATAGORYCOUNT,A.categoryid,B.category_name  FROM"
				+" (SELECT id,category_name FROM "+schemaname+".pgrs_category)B RIGHT JOIN"
				+" (SELECT DISTINCT date(docketcreateddatetime) AS DATE1,count(*) AS CATAGORYCOUNT,categoryid FROM "+schemaname+".pgrs_complaints WHERE" 
				+" extract(MONTH from date(docketcreateddatetime))="+selectedMonth+" AND extract(YEAR from date(docketcreateddatetime))="+year+" AND categoryid !=0 GROUP BY date(docketcreateddatetime),categoryid "
				+" ORDER BY date(docketcreateddatetime),categoryid ASC)A ON B.id =A.categoryid"
				+" ORDER BY A.DATE1,A.CATAGORYCOUNT,A.categoryid,B.category_name ASC";
		System.out.println(queryString);
		final Query query = this.entityManager.createNativeQuery(queryString);
		return query.getResultList();
	}
	
	@Override
	public List<?> KERCTimeLimitReport(int month, int year, String type, int typeId) {
		
		String queryString ="SELECT COUNT(CASE WHEN B.dockdate="+month+" AND B.currenrYear="+year+" then 1 END)AS registered,"
				+" COUNT(CASE WHEN (B.dockdate<"+month+" AND B.docketstatus!=3) then 1 END)AS previouspending,"
				+" COUNT(CASE WHEN (B.dockdate="+month+" AND B.resdate="+month+" AND B.docketstatus=3) then 1 END)AS resolved,"
				+" COUNT(CASE WHEN (B.dockdate="+month+" AND resolveddateandtime<=estimatedresolvedatetime) then 1 END)AS resolvedwithintimelines,"
				+" COUNT(CASE WHEN (B.dockdate="+month+" AND resolveddateandtime>estimatedresolvedatetime) then 1 END)AS resolvedbeyondtimelines,"
				+" COUNT(CASE WHEN (B.dockdate<="+month+" AND B.docketstatus!=3) then 1 END)AS currentpending,"
				+" (SELECT avg(extract ( DAY from  CURRENT_TIMESTAMP - docketcreateddatetime)*24 + extract ( HOUR   from  CURRENT_TIMESTAMP - docketcreateddatetime))"
				+"  FROM "+schemaname+".pgrs_complaints where invalid_flag='No' AND docketstatus!=0 AND substring(sitecode from 1 for "+typeId+")=B.sitecode AND ((extract(MONTH from docketcreateddatetime)<"+month+" AND docketstatus!=3) OR (extract(MONTH from docketcreateddatetime)="+month+" AND docketstatus!=3)) AND extract(YEAR from docketcreateddatetime)="+year+" AND sitecode IS NOT NULL),"
				+" B.sitecode,A.text"  
				+" FROM (SELECT site_code,text FROM "+schemaname+".core_project_hierarchy WHERE type='"+type+"')A INNER JOIN"
				+" (SELECT substring(sitecode from 1 for "+typeId+")as sitecode,extract(MONTH from docketcreateddatetime)as DockDate,extract(YEAR from docketcreateddatetime) as currenrYear,"
				+" extract(MONTH from resolveddateandtime)as ResDate,docketstatus,docketcreateddatetime,"
				+" resolveddateandtime,estimatedresolvedatetime FROM "+schemaname+".pgrs_complaints WHERE sitecode is not NULL AND invalid_flag='No')B"
				+" ON A.site_code=B.sitecode and B.currenrYear="+year+" GROUP BY B.sitecode,A.text";
		System.out.println(queryString);
		final Query query = this.entityManager.createNativeQuery(queryString);
		return query.getResultList();
	}
	@Override
	public List<?> getComplaintClosed(String sitecode, int month, int year) {
		
		if(sitecode.contains(",")){
			Set<String> actualSitCodes = new HashSet<String>();
			
			String[] subdivisionIdsArray = sitecode.split(","); 
			List<String> statusSet = Arrays.asList(subdivisionIdsArray);
			
			for (String string : statusSet) {
				actualSitCodes.add(string);
			}
			
			String sitecode1="";
			Iterator<String> iter=actualSitCodes.iterator();
			while(iter.hasNext()){
				sitecode1+="sitecode LIKE'"+iter.next()+"%' OR ";
			}
			
			sitecode1="("+sitecode1.substring(0,sitecode1.length()-3)+")";
			System.out.println("************************Sitecode Values*********************"+sitecode1);
			String queryString="SELECT P.docketnumber,P.docketstatus,P.docketcreateddatetime,C.category_name,S.subcategory_name,P.docketsummary,P.docketsource,P.consumername,P.consumermobilenumber FROM "+schemaname+".pgrs_complaints p INNER JOIN "+schemaname+".pgrs_category C on p.categoryid=C.id INNER JOIN" 
					+" "+schemaname+".pgrs_subcategory S on p.subcategoryid=S.id WHERE "+sitecode1+" "
					+" AND extract(MONTH from docketcreateddatetime)<="+month+" AND extract(YEAR from docketcreateddatetime)="+year
					+" AND docketstatus!=3 AND p.invalid_flag='No'";
			System.out.println(queryString);
			final Query query = this.entityManager.createNativeQuery(queryString);
			return query.getResultList();
		}else{
			
			String queryString="SELECT P.docketnumber,P.docketstatus,P.docketcreateddatetime,C.category_name,S.subcategory_name,P.docketsummary,P.docketsource,P.consumername,P.consumermobilenumber FROM "+schemaname+".pgrs_complaints p INNER JOIN "+schemaname+".pgrs_category C on p.categoryid=C.id INNER JOIN" 
					+" "+schemaname+".pgrs_subcategory S on p.subcategoryid=S.id WHERE sitecode like '"+sitecode+"%' "
					+" AND extract(MONTH from docketcreateddatetime)<="+month+" AND extract(YEAR from docketcreateddatetime)="+year
					+" AND docketstatus!=3 AND p.invalid_flag='No'";
			System.out.println(queryString);
			final Query query = this.entityManager.createNativeQuery(queryString);
			return query.getResultList();
		}
		
		
		
		
		
		
	}
	
	
	@Override
	public List<?> KERCRAPDRPReport(int month, int year, String type, int typeId, Set<String> actualSitCodes) {
		String sitecode="";
		Iterator<String> iter=actualSitCodes.iterator();
		while(iter.hasNext()){
			sitecode+="'"+iter.next()+"',";
		}
		sitecode="("+sitecode.substring(0,sitecode.length()-1)+")";
		
		String queryString ="SELECT COUNT(CASE WHEN B.dockdate="+month+" AND B.currenrYear="+year+" then 1 END)AS registered,"
				+" COUNT(CASE WHEN (B.dockdate<"+month+" AND B.docketstatus!=3) then 1 END)AS previouspending,"
				+" COUNT(CASE WHEN (B.dockdate="+month+" AND B.resdate="+month+" AND B.docketstatus=3) then 1 END)AS resolved,"
				+" COUNT(CASE WHEN (B.dockdate="+month+" AND resolveddateandtime<=estimatedresolvedatetime) then 1 END)AS resolvedwithintimelines,"
				+" COUNT(CASE WHEN (B.dockdate="+month+" AND resolveddateandtime>estimatedresolvedatetime) then 1 END)AS resolvedbeyondtimelines,"
				+" COUNT(CASE WHEN (B.dockdate<="+month+" AND B.docketstatus!=3) then 1 END)AS currentpending,"
				+" (SELECT avg(extract ( DAY from  CURRENT_TIMESTAMP - docketcreateddatetime)*24 + extract ( HOUR   from  CURRENT_TIMESTAMP - docketcreateddatetime))"
				+"  FROM "+schemaname+".pgrs_complaints where invalid_flag='No' AND docketstatus!=0 AND substring(sitecode from 1 for "+typeId+")=B.sitecode AND ((extract(MONTH from docketcreateddatetime)<"+month+" AND docketstatus!=3) OR (extract(MONTH from docketcreateddatetime)="+month+" AND docketstatus!=3)) AND extract(YEAR from docketcreateddatetime)="+year+" AND sitecode IS NOT NULL),"
				+" B.sitecode,A.text"  
				+" FROM (SELECT site_code,text FROM "+schemaname+".core_project_hierarchy WHERE type='"+type+"' AND site_code IN"+sitecode+")A INNER JOIN"
				+" (SELECT substring(sitecode from 1 for "+typeId+")as sitecode,extract(MONTH from docketcreateddatetime)as DockDate,extract(YEAR from docketcreateddatetime) as currenrYear,"
				+" extract(MONTH from resolveddateandtime)as ResDate,docketstatus,docketcreateddatetime,"
				+" resolveddateandtime,estimatedresolvedatetime FROM "+schemaname+".pgrs_complaints WHERE sitecode is not NULL AND invalid_flag='No')B"
				+" ON A.site_code=B.sitecode and B.currenrYear="+year+" GROUP BY B.sitecode,A.text";
		System.out.println(queryString);
		final Query query = this.entityManager.createNativeQuery(queryString);
		return query.getResultList();
	
	}
	@Override
	public List<?> KERCRAPDRPReport1(int month, int year, String type, int typeId, Set<String> actualSitCodes) {
		String sitecode="";
		Iterator<String> iter=actualSitCodes.iterator();
		while(iter.hasNext()){
			sitecode+="'"+iter.next()+"',";
		}
		sitecode="("+sitecode.substring(0,sitecode.length()-1)+")";
		
		String queryString ="SELECT COUNT(CASE WHEN B.dockdate="+month+" AND B.currenrYear="+year+" then 1 END)AS registered,"
				+" COUNT(CASE WHEN (B.dockdate<"+month+" AND B.docketstatus!=3) then 1 END)AS previouspending,"
				+" COUNT(CASE WHEN (B.dockdate="+month+" AND B.resdate="+month+" AND B.docketstatus=3) then 1 END)AS resolved,"
				+" COUNT(CASE WHEN (B.dockdate="+month+" AND resolveddateandtime<=estimatedresolvedatetime) then 1 END)AS resolvedwithintimelines,"
				+" COUNT(CASE WHEN (B.dockdate="+month+" AND resolveddateandtime>estimatedresolvedatetime) then 1 END)AS resolvedbeyondtimelines,"
				+" COUNT(CASE WHEN (B.dockdate<="+month+" AND B.docketstatus!=3) then 1 END)AS currentpending,"
				+" (SELECT avg(extract ( DAY from  CURRENT_TIMESTAMP - docketcreateddatetime)*24 + extract ( HOUR   from  CURRENT_TIMESTAMP - docketcreateddatetime))"
				+"  FROM "+schemaname+".pgrs_complaints where invalid_flag='No' AND docketstatus!=0 AND substring(sitecode from 1 for "+typeId+")=B.sitecode AND ((extract(MONTH from docketcreateddatetime)<"+month+" AND docketstatus!=3) OR (extract(MONTH from docketcreateddatetime)="+month+" AND docketstatus!=3)) AND extract(YEAR from docketcreateddatetime)="+year+" AND sitecode IS NOT NULL),"
				+" B.sitecode,A.townname"  
				+" FROM (SELECT site_code,townname FROM "+schemaname+".core_project_hierarchy WHERE townname is not NULL AND site_code IN"+sitecode+")A INNER JOIN"
				+" (SELECT substring(sitecode from 1 for "+typeId+")as sitecode,extract(MONTH from docketcreateddatetime)as DockDate,extract(YEAR from docketcreateddatetime) as currenrYear,"
				+" extract(MONTH from resolveddateandtime)as ResDate,docketstatus,docketcreateddatetime,"
				+" resolveddateandtime,estimatedresolvedatetime FROM "+schemaname+".pgrs_complaints WHERE sitecode is not NULL AND invalid_flag='No')B"
				+" ON A.site_code=B.sitecode and B.currenrYear="+year+" GROUP BY B.sitecode,A.townname";
		System.out.println(queryString);
		final Query query = this.entityManager.createNativeQuery(queryString);
		return query.getResultList();
	
	}
}
