package com.pgrs.daoimpl;
// default package

import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.pgrs.dao.ICompanyDAO;
import com.pgrs.entity.Company;

/**
 * A data access object (DAO) providing persistence and search support for
 * Company entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see .Company
 * @author Ravi Shankar Reddy
 */
@Repository
public class CompanyDAO extends GenericDAOImpl<Company> implements ICompanyDAO {

	@Override
	public String getCompanyNameBasedOnId(int companyId) {
		
		List<String> companyNames = entityManager.createNamedQuery("Company.getCompanyNameBasedOnId",String.class)
				.setParameter("companyId", companyId)
				.getResultList();	
		Iterator<String> it=companyNames.iterator();
		while(it.hasNext()){

			return (String) it.next();
		}
		return null;
	}

	@Override
	public void companyStatusUpdate(int status, int companyId) {
		entityManager.createNamedQuery("Company.companyStatusUpdate").setParameter("status", status).setParameter("companyId", companyId).executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> getActiveUsersBasedOnCompanyId(int companyId) {
		return entityManager.createNamedQuery("Company.getActiveUsersBasedOnCompanyId").setParameter("companyId", companyId).getResultList();
	}
	
}