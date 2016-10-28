package com.pgrs.service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

public interface PgrsReportService {

	List<?> getComplaintStatus(Date fromdate, Date todate, Set<String> siteCodes);

	List<?> accidentStatusReport(Date date1, Date date2, Set<String> siteCodes);

	List<?> esclatedStatusReport(Date date1, Date date2, Set<String> siteCodes);

	List<?> viewComplaintStatusReport(String fromMonth, String toMOnth,Set<String> siteCodes,String status,HttpServletRequest req);

	List<?> showCusSatisfactionResults(String fromMonth, String toMonth,Set<String> siteCodes, HttpServletRequest req);

	List<?> showKERCStatusReport(Date date1, Date date2, Set<String> siteCodes,HttpServletRequest req);

	List<?> viewComplaintStatusGraphicalReport(String fromMonth,String toMonth, Set<String> siteCodes, String status,HttpServletRequest req);

	List<Object> compliantstatusGraphicalReport(Date date1, Date date2,Set<String> siteCodes);

	List<?> customerSatisfactionGraphicalReport(String fromMonth,String toMonth, Set<String> siteCodes, HttpServletRequest req);

	List<Object> categoryWiseDetails();

	List<Object> monthWiseDetails();

	Map<String, Object> totalComplaints();

	Map<String, Object> escalatedComplaints();

	List<?> showAverageTimeBreached(String fromMonth, String toMonth,Set<String> siteCodes, String status, HttpServletRequest req);

	List<?> showAverageTimeBreachedGraohicalReport(String fromMonth,String toMonth, Set<String> siteCodes, String status,HttpServletRequest req);

	List<?> dayWiseReport( int selectedMonth, int year);

	List<?> KERCTimeLimitReport(int month, int year, String type,int typeId);

	List<?> getComplaintClosed(String sitecode, int month, int year);

}
