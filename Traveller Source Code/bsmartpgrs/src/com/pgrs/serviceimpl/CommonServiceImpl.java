package com.pgrs.serviceimpl;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.pgrs.daoimpl.GenericDAOImpl;
import com.pgrs.entity.Common;
import com.pgrs.service.CommonService;

@Repository
public class CommonServiceImpl extends GenericDAOImpl<Common> implements CommonService
{
	@Transactional(propagation = Propagation.SUPPORTS)
	@Override
	public List<?> selectQueryOrderBy(final String className, final List<String> attributesList, final Map<String, Object> params,Map<String, Object> orderByList) 
	{
		final StringBuffer queryString = new StringBuffer(
	        		"SELECT ");

		if((attributesList == null) || (attributesList.size() == 0))
		{
			queryString.append("o");
		}
		
		else if(attributesList.size() == 1)
		{
			queryString.append("o.");
			queryString.append(attributesList.get(0));
		}
		
		else if(attributesList.size() > 1)
		{
			for (Iterator<String> iterator = attributesList.iterator(); iterator.hasNext();)
			{
				String string = (String) iterator.next();
				
				queryString.append("o.");
				queryString.append(string);
				queryString.append(", ");
			}
			
			queryString.replace(0, queryString.length() - 1, queryString.substring(0, queryString.length() - 2));
		}
		else
			queryString.append("o");
		
		queryString.append(" from ");
	    queryString.append(className).append(" o ");
	    queryString.append(this.getQueryClauses(params, orderByList));

	    final Query query = this.entityManager.createQuery(queryString.toString());
	    return (List<?>) query.getResultList();
	}
	
	
	@Transactional(propagation = Propagation.SUPPORTS)
	@Override
	public List<?> selectQuery(final String className, final List<String> attributesList, final Map<String, Object> params) 
	{
		final StringBuffer queryString = new StringBuffer(
	        		"SELECT ");

		if((attributesList == null) || (attributesList.size() == 0))
		{
			queryString.append("o");
		}
		
		else if(attributesList.size() == 1)
		{
			queryString.append("o.");
			queryString.append(attributesList.get(0));
		}
		
		else if(attributesList.size() > 1)
		{
			for (Iterator<String> iterator = attributesList.iterator(); iterator.hasNext();)
			{
				String string = (String) iterator.next();
				
				queryString.append("o.");
				queryString.append(string);
				queryString.append(", ");
			}
			
			queryString.replace(0, queryString.length() - 1, queryString.substring(0, queryString.length() - 2));
		}
		else
			queryString.append("o");
		
		queryString.append(" from ");
	    queryString.append(className).append(" o ");
	    queryString.append(this.getQueryClauses(params, null));

	    final Query query = this.entityManager.createQuery(queryString.toString());

	    return (List<?>) query.getResultList();
	}
	
	@Transactional(propagation = Propagation.SUPPORTS)
	@Override
	public Date getMaxDate(final String className, final List<String> attributesList, final Map<String, Object> params) 
	{
		final StringBuffer queryString = new StringBuffer(
	        		"SELECT ");

		if((attributesList == null) || (attributesList.size() == 0))
		{
			queryString.append("o");
		}
		
		else if(attributesList.size() == 1)
		{
			queryString.append(" Max (o.");
			queryString.append(attributesList.get(0));
			queryString.append(")");
		}
		
		else if(attributesList.size() > 1)
		{
			for (Iterator<String> iterator = attributesList.iterator(); iterator.hasNext();)
			{
				String string = (String) iterator.next();
				
				queryString.append("o.");
				queryString.append(string);
				queryString.append(", ");
			}
			
			queryString.replace(0, queryString.length() - 1, queryString.substring(0, queryString.length() - 2));
		}
		else
			queryString.append("o");
		
		queryString.append(" from ");
	    queryString.append(className).append(" o ");
	    queryString.append(this.getQueryClauses(params, null));

	    final Query query = this.entityManager.createQuery(queryString.toString());

	    return (Date) query.getSingleResult();
	}
	
	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<?> selectDistinctQuery(String className,
			List<String> attributesList, Map<String, Object> params)
	{
		final StringBuffer queryString = new StringBuffer(
        		"SELECT DISTINCT ");

		if((attributesList == null) || (attributesList.size() == 0))
		{
			queryString.append("o");
		}
		
		else if(attributesList.size() == 1)
		{
			queryString.append("o.");
			queryString.append(attributesList.get(0));
		}
		
		else if(attributesList.size() > 1)
		{
			for (Iterator<String> iterator = attributesList.iterator(); iterator.hasNext();)
			{
				String string = (String) iterator.next();
				
				queryString.append("o.");
				queryString.append(string);
				queryString.append(", ");
			}
			
			queryString.replace(0, queryString.length() - 1, queryString.substring(0, queryString.length() - 2));
		}
		else
			queryString.append("o");
		
		queryString.append(" from ");
	    queryString.append(className).append(" o ");
	    queryString.append(this.getQueryClauses(params, null));
	
	    final Query query = this.entityManager.createQuery(queryString.toString());
	
	    return (List<?>) query.getResultList();
	}
	
	 private String getQueryClauses(final Map<String, Object> params, final Map<String, Object> orderParams) 
	 {
	     final StringBuffer queryString = new StringBuffer();
	     if ((params != null) && !params.isEmpty()) 
	     {
	             queryString.append(" where ");
	             for (final Iterator<Map.Entry<String, Object>> it = params.entrySet().iterator(); it.hasNext();) 
	             {
	                     final Map.Entry<String, Object> entry = it.next();
	                     if (entry.getValue() instanceof Boolean) 
	                     {
	                             queryString.append(entry.getKey()).append(" is ").append(entry.getValue()).append(" ");
	                     } 
	                     else 
	                     {
	                            if (entry.getValue() instanceof Number) 
	                            {
	                                    queryString.append(entry.getKey()).append(" = ")
	                                                   .append(entry.getValue());
	                            }
	                            else 
	                            {
	                                    // string equality
	                                    queryString.append(entry.getKey()).append(" = '")
	                                                   .append(entry.getValue()).append("'");
	                            }
	                     }
	                     if (it.hasNext()) 
	                     {
	                            queryString.append(" and ");
	                     }
	             }
	     }
	     if ((orderParams != null) && !orderParams.isEmpty()) 
	     {
	             queryString.append(" order by ");
	             for (final Iterator<Map.Entry<String, Object>> it = orderParams
	                            .entrySet().iterator(); it.hasNext();) 
	             {
	                     final Map.Entry<String, Object> entry = it.next();
	                     queryString.append(entry.getKey()).append(" ");
	                     if (entry.getValue() != null) 
	                     {
	                             queryString.append(entry.getValue());
	                     }
	                     if (it.hasNext()) 
	                     {
	                            queryString.append(", ");
	                     }
	             }
	     }
	     return queryString.toString();
	}
	
	 @Override
		//select p.personId, p.firstName, p.lastName, p.personType, p.personStyle from Account a Inner join a.person p order by p.firstName
		@Transactional(propagation = Propagation.SUPPORTS)
		public List<?> getPersonNamesFilterList(final String className, final String personFieldName) 
		{
			final StringBuffer queryString = new StringBuffer(
	        		"SELECT DISTINCT p.personId, p.firstName, p.lastName, p.personType, p.personStyle FROM ");

		    queryString.append(className).append(" o ");
		    queryString.append("INNER JOIN o.");
		    queryString.append(personFieldName).append(" x ");
		    queryString.append("INNER JOIN x.person p ");
 		    queryString.append("ORDER BY p.firstName");
		
		    final Query query = this.entityManager.createQuery(queryString.toString());
		
		    return (List<?>) query.getResultList();
		}


	@Override
	public List<?> getNewConnectionDetail() {
		
		String queryString = "select np.applicationid,np.name,np.appregdate,np.citypres,np.loadkw,np.loadhp,np.loadkva,np.tariffdesc,np.locality,np.natureofinst FROM pgrs.ncpt_application np limit 10";
		final Query query = this.entityManager.createNativeQuery(queryString);
		return query.getResultList();
	}
	
	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public Set<String> getCheckConstraintsVals(String tableName,String constraintName,String schemaName)
	{		
		String res= (String) entityManager.createNamedQuery("oracleconstraint.getChecks").setParameter("tableName", tableName).setParameter("schema", schemaName).setParameter("constraintName", constraintName).getSingleResult();
		String[] splitRes=res.split(" OR ");
		Set<String> set=new TreeSet<String>();
		int subLength = 0;
		String[] test = null;
		if(splitRes.length > 0)
		{
			test = splitRes[0].split("'");
			subLength = test[0].length()+1;
		}
		String finalRes="";
		String result = "";
		for(int i=0;i<splitRes.length;i++)
		{
			finalRes=splitRes[i].trim();
			result = result + (finalRes.substring(subLength, finalRes.length()-1)) + ",";
		}
		result = result.substring(0,result.length()-1);
		String[] finalres=result.split(",");
		for (int i = 0; i < finalres.length; i++) 
		{
			set.add(finalres[i]);
		}
				return set;
	}

}
