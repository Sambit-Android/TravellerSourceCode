package com.pgrs.dao;
// default package

import java.util.List;

import com.pgrs.entity.Company;

/**
 * Interface for CompanyDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface ICompanyDAO extends GenericDAO<Company> {
	
	String getCompanyNameBasedOnId( int companyId );

	void companyStatusUpdate(int status, int id);

	List<Integer> getActiveUsersBasedOnCompanyId(int id);
	
}