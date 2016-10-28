package com.pgrs.dao;

import javax.servlet.http.HttpServletRequest;

public interface MasterDao {

	String getAllOfficeDetailsOfLoggedInUser(HttpServletRequest request);
	
	String getAllOfficeDetailsOfLoggedInUsermobile(HttpServletRequest request,int id, int projectId);

	String getOfficeChildDetails(int id, int projectId);

}
