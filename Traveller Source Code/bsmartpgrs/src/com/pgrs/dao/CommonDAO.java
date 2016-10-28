package com.pgrs.dao;

import java.util.List;
import java.util.Map;

public interface CommonDAO extends GenericDAO<com.pgrs.entity.Common>
{
	List<?> selectQuery(String className, List<String> attributesList,
			Map<String, Object> params);
	
	List<?> getPersonNamesFilterList(String className, String personFieldName);

	List<?> selectDistinctQuery(String className, List<String> attributesList,
			Map<String, Object> object);

	List<?> selectQueryOrderBy(String className, List<String> attributesList,Map<String, Object> params,Map<String, Object> orderByList);

	java.util.Date getMaxDate(String className, List<String> attributesList,Map<String, Object> params);
}