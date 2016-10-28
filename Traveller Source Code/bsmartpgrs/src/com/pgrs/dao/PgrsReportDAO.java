package com.pgrs.dao;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import com.pgrs.entity.HelpDeskTicketEntity;

public interface PgrsReportDAO extends GenericDAO<HelpDeskTicketEntity> {

	List<?> getComplaintStatus(Date fromdate, Date todate, String sitecode);

	List<?> viewComplaintStatusReport(String fromMonth, String toMonth,String sitecode,String status,HttpServletRequest req);

	List<?> showCusSatisfactionResults(String fromMonth, String toMonth,String sitecode, HttpServletRequest req);

	List<?> getaccidentStatusReport(Date fromdate, Date todate, String sitecode);

	List<?> esclatedStatusReport(Date fromdate, Date todate, String sitecode);

	List<?> showKERCStatusReport(Date date1, Date date2, String sitecode);

	List<?> categoryWiseDetails();

	List<?> monthWiseDetails();

	List<?> showAverageTimeBreached(String fromMonth, String toMonth,String sitecode, String status, HttpServletRequest req);

	List<?> dayWiseReport(int selectedMonth,int year);

	List<?> KERCTimeLimitReport(int month, int year, String type,int typeId);

	List<?> getComplaintClosed(String sitecode, int month, int year);

	List<?> KERCRAPDRPReport(int month, int year, String type, int typeId, Set<String> actualSitCodes);

	List<?> KERCRAPDRPReport1(int month, int year, String type, int typeId,
			Set<String> actualSitCodes);

	
}
