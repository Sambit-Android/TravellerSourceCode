package com.bcits.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;

import com.bcits.entity.EmployeeOracleEntity;

public interface EmployeeOracleService extends GenericServiceOracle<EmployeeOracleEntity> {
	public List<?> getEmployeData(String userName, String passWord, String dbSchema);

	public List<Object[]> mobileCommonLogin(String userName, String passWord);

	String showSecurityPwd(ModelMap model, HttpServletRequest request);

	public int updateSecurityLauncherPassword(String newPwd);

	List secRealTimeStatus(ModelMap model);
}
