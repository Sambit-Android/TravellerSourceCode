package com.pgrs.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.pgrs.dao.GenericDAO;
import com.pgrs.entity.Common;

public interface CommonService extends GenericDAO<Common> {
	
	List<?> selectQuery(String className, List<String> attributesList,Map<String, Object> params);
	
	List<?> getPersonNamesFilterList(String className, String personFieldName);

	List<?> selectDistinctQuery(String className, List<String> attributesList,Map<String, Object> object);

	List<?> selectQueryOrderBy(String className, List<String> attributesList,Map<String, Object> params,Map<String, Object> orderByList);

	java.util.Date getMaxDate(String className, List<String> attributesList,Map<String, Object> params);
	
	public List<?> getNewConnectionDetail();

	Set<String> getCheckConstraintsVals(String tableName,String constraintName, String schemaName);
}