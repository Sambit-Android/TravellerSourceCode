package com.pgrs.core.dao;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.pgrs.entity.BaseEntity;


/**
 * A data access object (DAO) providing persistence and search support for Users
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @author Ravi Shankar Reddy 
 * @version %I%, %G%
 */
@Transactional(readOnly = true)
@SuppressWarnings({ "unchecked" })
public class MasterGenericJpaDAO implements MasterGenericDAO {

	static Logger logger = LoggerFactory.getLogger(MasterGenericJpaDAO.class);
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public <T extends BaseEntity> T getById(Class<T> clazz, int id) {
		return entityManager.find(clazz, id);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public <T extends BaseEntity> T getByCriteria(Class<T> clazz, Map criterias) {
		List<T> items = findByCriteria(clazz, criterias, null, false);
		if (items != null && items.size() > 0)
			return items.get(0);
		return null;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public <T extends BaseEntity> T getByUniqueAttribute(Class<T> clazz,
			String attributeName, Object value) {
		Map criterias = new HashMap();
		criterias.put(attributeName, value);
		List<T> items = findByCriteria(clazz, criterias);
		if (items != null && items.size() > 0)
			return items.get(0);
		return null;
	}

	@Override
	public <T extends BaseEntity> T getByNamedQuery(String namedQuery,
			Map<String, Object> map) {
		Query q = entityManager.createNamedQuery(namedQuery);
		Iterator<String> it = map.keySet().iterator();
		while (it.hasNext()) {
			String key = it.next();
			q.setParameter(key, map.get(key));
		}
		List<T> data = q.getResultList();
		if (data != null && data.size() > 0)
			return data.get(0);
		else
			return null;
	}

	@Override
	public <T extends BaseEntity> List<T> executeSimpleQuery(String query) {
		Query q = entityManager.createQuery(query);
		return q.getResultList();
	}
	
	@Override
	public List<Object[]> executeSimpleObjectQuery(String query) {
		Query q = entityManager.createQuery(query);
		return q.getResultList();
	}
	
	@Override
	public Object[] executeSingleObjectQuery(String query) {
		Query q = entityManager.createQuery(query);
		try{
			Object[] obj= (Object[]) q.getSingleResult();
			return obj;
		}catch(Exception e){
			return null;
		}
		

	}
	
	@Override
	public <T extends BaseEntity> void executeSimpleUpdateQuery(String query) {
		Query q = entityManager.createQuery(query);
		q.executeUpdate();
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public int executeSimpleUpdateQuery1(String query) {
		Query q = entityManager.createQuery(query);
		return q.executeUpdate();
	}

	@Override
	public <T extends BaseEntity> List<T> findAll(Class<T> clazz) {
		return findByCriteria(clazz, null);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public <T extends BaseEntity> List<T> findAll(Class<T> clazz,
			boolean activeOnly) {
		Map criterias = new HashMap();
		criterias.put("status", 1);
		return findByCriteria(clazz, criterias);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public <T extends BaseEntity> List<T> findByCriteria(Class<T> clazz,
			Map criterias) {
		return findByCriteria(clazz, criterias, null, false);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public <T extends BaseEntity> List<T> findByCriteria(Class<T> clazz,
			Map criterias, String sortField, boolean desc) {
		// Build the Query String with Search Criteria
		int count = 0;
		int counter = 0;
		StringBuilder query = new StringBuilder("Select obj from ").append(
				clazz.getSimpleName()).append(" obj where 1=1");
		if (criterias != null && criterias.size() > 0) {
			Object[] keyArray = criterias.keySet().toArray();
			for (int i = 0; i < keyArray.length; i++) {
				if (keyArray[i].toString().startsWith("||")) {
					counter++;
				}
			}
			if (counter == 1)
				query = new StringBuilder("Select obj from ").append(
						clazz.getSimpleName()).append(" obj where 1!=1");
			for (int i = 0; i < keyArray.length; i++) {
				if (keyArray[i].toString().startsWith("||")) {
					if (count == 0 && counter > 1)
						query.append(" and ( ");
					else
						query.append(" or ");
					if (criterias.get(keyArray[i]).toString().contains("null")) {
						if (criterias.get(keyArray[i]).toString().contains("!"))
							query.append(" obj.").append(keyArray[i])
									.append(" IS NOT NULL");
						else
							query.append(" obj.").append(keyArray[i])
									.append(" IS NULL");

					} else if (criterias.get(keyArray[i]).toString()
							.contains("<>")
							|| criterias.get(keyArray[i]).toString()
									.contains("!=")) {
						query.append(" obj.").append(keyArray[i]).append("!=:")
								.append("p" + i);
					} else if (criterias.get(keyArray[i]).toString()
							.contains(",")) {
						StringBuilder valBuilder = new StringBuilder();
						String[] values = criterias.get(keyArray[i]).toString()
								.trim().split(",");
						for (int j = 0; j < values.length; j++) {
							values[j] = "'" + values[j] + "',";
							valBuilder.append(values[j]);
						}
						if (valBuilder.length() > 1) {
							valBuilder = valBuilder.deleteCharAt(valBuilder
									.length() - 1);
							query.append(" obj.")
									.append(keyArray[i])
									.append(" IN (" + valBuilder.toString()
											+ ")");
						}
					} else
						query.append(" obj.")
								.append(keyArray[i].toString()
										.substring(
												keyArray[i].toString().indexOf(
														"|") + 2)).append("=:")
								.append("p" + i);
					count++;
				}

			}
			if (count > 1)
				query.append(" )");
			for (int i = 0; i < keyArray.length; i++) {
				if (keyArray[i].toString().startsWith("||")) {
					continue;
				} else if (criterias.get(keyArray[i]).toString()
						.contains("null")) {
					if (criterias.get(keyArray[i]).toString().contains("!"))
						query.append(" and obj.").append(keyArray[i])
								.append(" IS NOT NULL");
					else
						query.append(" and obj.").append(keyArray[i])
								.append(" IS NULL");
				} else if (criterias.get(keyArray[i]).toString().contains("<>")
						|| criterias.get(keyArray[i]).toString().contains("!=")) {
					query.append(" and obj.").append(keyArray[i]).append("!=:")
							.append("p" + i);
				} else if (criterias.get(keyArray[i]).toString().contains(">")) {
					SimpleDateFormat dateFormat = new SimpleDateFormat(
							"dd-MM-yyyy");
					String modifiedValue = criterias.get(keyArray[i])
							.toString().replace(">", "");
					try {
						criterias.put(keyArray[i],
								dateFormat.parse(modifiedValue));
					} catch (Exception e) {

						e.printStackTrace();
					}
					query.append(" and obj.").append(keyArray[i]).append(">:")
							.append("p" + i);
				} else if (criterias.get(keyArray[i]).toString().contains(",")) {
					StringBuilder valBuilder = new StringBuilder();
					String[] values = criterias.get(keyArray[i]).toString()
							.trim().split(",");
					for (int j = 0; j < values.length; j++) {
						values[j] = "'" + values[j] + "',";
						valBuilder.append(values[j]);
					}
					if (valBuilder.length() > 1) {
						valBuilder = valBuilder.deleteCharAt(valBuilder
								.length() - 1);
						query.append(" and obj.").append(keyArray[i])
								.append(" IN (" + valBuilder.toString() + ")");
					}
				} else if (keyArray[i].toString().equalsIgnoreCase("dateField")
						&& criterias.get("from") != null
						&& criterias.get("to") != null) {
					query.append(" and obj.")
							.append(criterias.get("dateField"));
					Timestamp firstDate = new Timestamp(
							((Date) criterias.get("from")).getTime());
					Timestamp secondDate = new Timestamp(
							((Date) criterias.get("to")).getTime() + 86399999);
					query.append(" Between '" + firstDate + "' AND '"
							+ secondDate + "'");
				} else {
					if (keyArray[i].toString().equalsIgnoreCase("dateField")) {
						// Do nothing
					} else if (keyArray[i].toString().equalsIgnoreCase("from")) {
						// Do nothing
					} else if (keyArray[i].toString().equalsIgnoreCase("to")) {
						// Do nothing
					} else
						query.append(" and obj.").append(keyArray[i])
								.append("=:").append("p" + i);
				}
			}
		}
		if (sortField != null) {
			query.append("  ORDER BY obj.").append(sortField);
			if (!desc)
				query.append(" asc");
			else
				query.append(" desc");

		}
		// Build the query Object
		Query jpaQuery = entityManager.createQuery(query.toString());
		// Set the search Parameters for the query
		if (criterias != null && criterias.size() > 0) {
			Object[] keyArray = criterias.keySet().toArray();
			for (int i = 0; i < keyArray.length; i++) {
				if (criterias.get(keyArray[i]).toString().contains("!="))
					jpaQuery.setParameter(
							"p" + i,
							criterias
									.get(keyArray[i])
									.toString()
									.substring(
											criterias.get(keyArray[i])
													.toString()
													.lastIndexOf("=") + 1));
				else if (criterias.get(keyArray[i]).toString().contains("null")) {
					// Do nothing
				} else if (criterias.get(keyArray[i]).toString().contains(",")) {
					// Do nothing
				} else if (keyArray[i].toString().equalsIgnoreCase("dateField")) {
					// Do nothing
				} else if (keyArray[i].toString().equalsIgnoreCase("from")) {
					// Do nothing
				} else if (keyArray[i].toString().equalsIgnoreCase("to")) {
					// Do nothing
				} else
					jpaQuery.setParameter("p" + i, criterias.get(keyArray[i]));
			}

		}
		// Execute the query and return List of objects
		return jpaQuery.getResultList();
	}

	@Override
	public <T extends BaseEntity> List<T> findByNamedQuery(String namedQuery,
			Map<String, Object> map) {
		Query q = entityManager.createNamedQuery(namedQuery);
		Iterator<String> it = map.keySet().iterator();
		while (it.hasNext()) {
			String key = it.next();
			q.setParameter(key, map.get(key));
		}
		return q.getResultList();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public <T extends BaseEntity> List<T> findLimitedByCount(Class<T> clazz,
			Map criterias, Integer maxCount) {
		Boolean containsOr = false;
		int count = 0;
		StringBuffer queryCount = new StringBuffer("select count(p) from "
				+ clazz.getSimpleName() + " p where 1=1 ");
		StringBuffer searchString = new StringBuffer("");
		if (criterias != null) {
			searchString = createQueryFromCriterias(clazz, criterias,
					containsOr, count);
		}
		if (containsOr && count == 1)
			queryCount = new StringBuffer("select count(p) from "
					+ clazz.getSimpleName() + " p where 1!=1 ");
		queryCount.append(searchString.toString());
		StringBuffer queryStmt = new StringBuffer(" from "
				+ clazz.getSimpleName() + " p where 1=1 ");
		if (containsOr && count == 1)
			queryStmt = new StringBuffer(" from " + clazz.getSimpleName()
					+ " p where 1!=1 ");
		queryStmt.append(searchString.toString());
		return entityManager.createQuery(queryStmt.toString())
				.setMaxResults(maxCount).getResultList();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public <T extends BaseEntity> void save(T entity) {
		entityManager.persist(entity);
		// writeEntityLog("ADD", entity, null);
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public <T extends BaseEntity> void update(T entity) {
		entityManager.merge(entity);
		// writeEntityLog("ADD", entity, null);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public <T extends BaseEntity> void saveOrUpdate(T entity,Integer id) {
		if (id == null || id == 0) {
			entityManager.persist(entity);
			// writeEntityLog("ADD", entity, null);
		} else {
			entityManager.merge(entity);
			
			// writeEntityLog("UPDATE", entity, oldEntity);
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public <T extends BaseEntity> void delete(T entity) {
		entityManager.remove(entity);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public <T extends BaseEntity> void deleteById(Class<T> clazz, int id) {
		logger.info("deleting instance");
		entityManager.remove(getById(clazz, id));
		logger.info("deleted Successfully");
		
	}



	

	@SuppressWarnings({ "unused", "rawtypes" })
	@Override
	public <T extends BaseEntity> List<T> findByCriteria(Class<T> clazz,
			Map criterias, boolean orderResult, String sortField, boolean desc,
			String groupField) {
		// Build the Query String with Search Criteria
		StringBuilder query = new StringBuilder("Select obj from ").append(
				clazz.getSimpleName()).append(" obj where 1=1");
		if (criterias != null && criterias.size() > 0) {
			Object[] keyArray = criterias.keySet().toArray();
			for (int i = 0; i < keyArray.length; i++) {
				if (criterias.get(keyArray[i]).toString().contains("null")) {
					if (criterias.get(keyArray[i]).toString().contains("!"))
						query.append(" and obj.").append(keyArray[i])
								.append(" IS NOT NULL");
					else
						query.append(" and obj.").append(keyArray[i])
								.append(" IS NULL");

				} else {
					query.append(" and obj.").append(keyArray[i]).append("=:")
							.append("p" + i);
				}

			}
		}
		if (orderResult) {
			if (sortField != null) {
				query.append("  ORDER BY obj.").append(sortField);
				if (!desc)
					query.append(" asc");
				else
					query.append(" desc");

			}
		}
		if (groupField != null) {
			query.append("  GROUP BY obj.").append(groupField);
		}
		// Build the query Object
		int index = 0;
		if (criterias != null && criterias.size() > 0) {
			Object[] keyArray = criterias.keySet().toArray();
			for (int i = 0; i < keyArray.length; i++) {
				if (!criterias.get(keyArray[i]).toString().contains("null")) {
					index++;
				}
			}
		}
		Query jpaQuery = entityManager.createQuery(query.toString());
		// Set the search Parameters for the query
		if (criterias != null && criterias.size() > 0) {
			Object[] keyArray = criterias.keySet().toArray();
			for (int i = 0; i < keyArray.length; i++) {
				if (criterias.get(keyArray[i]).toString().contains("!="))
					jpaQuery.setParameter(
							"p" + i,
							criterias
									.get(keyArray[i])
									.toString()
									.substring(
											criterias.get(keyArray[i])
													.toString()
													.lastIndexOf("=") + 1));
				else if (criterias.get(keyArray[i]).toString().contains("null")) {
					// Do nothing
				} else if (criterias.get(keyArray[i]).toString().contains(",")) {
					// Do nothing
				} else if (keyArray[i].toString().equalsIgnoreCase("dateField")) {
					// Do nothing
				} else if (keyArray[i].toString().equalsIgnoreCase("from")) {
					// Do nothing
				} else if (keyArray[i].toString().equalsIgnoreCase("to")) {
					// Do nothing
				} else
					jpaQuery.setParameter("p" + i, criterias.get(keyArray[i]));
			}

		}
		// Execute the query and return List of objects
		return jpaQuery.getResultList();
	}

	@Override
	public <T extends BaseEntity> List<T> findByCommaSeparatedIds(
			Class<T> clazz, String ids) {
		StringBuilder query = new StringBuilder("Select obj from ").append(
				clazz.getSimpleName()).append(" obj where ");
		query.append(" obj.id").append(" IN (" + ids + ")");
		return entityManager.createQuery(query.toString()).getResultList();
	}

	@SuppressWarnings("rawtypes")
	private <T extends BaseEntity> StringBuffer createQueryFromCriterias(
			Class<T> clazz, Map criterias, Boolean containsOr, int count) {
		int counter = 0;
		StringBuffer searchString = new StringBuffer("");
		if (criterias != null) {
			for (Object param : criterias.keySet()) {
				if (param.toString().startsWith("||"))
					counter++;
			}
			for (Object param : criterias.keySet()) {
				if (criterias.get(param.toString())!=null) {
					if (param.toString().startsWith("||")) {
						containsOr = true;
						if (count == 0 && counter > 1)
							searchString.append(" and ( ");
						else
							searchString.append(" or ");
						String criteriaKey = param.toString().replace("||", "");
						if (criterias.get(param.toString()) instanceof Integer
								|| criterias.get(param.toString()) instanceof Long) {
							searchString.append(" p." + criteriaKey + "="
									+ criterias.get(param.toString()));
						}
						else if (!criterias.get(param.toString()).toString()
								.contains("null")) {
							if (criterias.get(param.toString()).toString()
									.contains("!"))
								searchString.append(" p." + criteriaKey
										+ " IS NOT NULL");
							else
								searchString.append(" p." + criteriaKey
										+ " IS NULL");
						} else if (criterias.get(param.toString()).toString()
								.contains(",")) {
							StringBuilder valBuilder = new StringBuilder();
							String[] values = criterias.get(param.toString())
									.toString().trim().split(",");
							for (int i = 0; i < values.length; i++) {
								values[i] = "'" + values[i] + "',";
								valBuilder.append(values[i]);
							}
							if (valBuilder.length() > 1) {
								valBuilder = valBuilder.deleteCharAt(valBuilder
										.length() - 1);
								searchString.append(" UPPER(p." + criteriaKey
										+ ") IN (" + valBuilder.toString()
										+ ")");
							}
						} else if (criterias.get(param.toString()).toString()
								.startsWith("!=")
								|| criterias.get(param.toString()).toString()
										.startsWith("<>")) {
							searchString.append(" p."
									+ criteriaKey
									+ " <> '"
									+ criterias.get(param.toString())
											.toString().trim().substring(2)
									+ "'");

						} else if (criterias.get(param.toString()) instanceof Date) {
							if ("from".equalsIgnoreCase(param.toString())) {
								Timestamp firstDate = new Timestamp(
										((Date) criterias.get(param.toString()))
												.getTime());
								Timestamp secondDate = null;
								secondDate = new Timestamp(
										((Date) criterias.get("to")).getTime() + 86399999);
								String fieldName = (String) criterias
										.get("dateField");
								searchString.append(" UPPER(p." + fieldName
										+ ") Between '" + firstDate + "' AND '"
										+ secondDate + "'");
							}
						} else {
							if (!"dateField".equalsIgnoreCase(param.toString()))
								searchString.append(" UPPER(p." + criteriaKey
										+ ") like UPPER('"
										+ criterias.get(param.toString())
										+ "%') ");
						}
						count++;
					}
				}
			}
			if (count > 1)
				searchString.append(" )");
			for (Object param : criterias.keySet()) {
				if (param.toString().startsWith("||"))
					continue;
				else {
					if (criterias.get(param.toString()) instanceof Integer
							|| criterias.get(param.toString()) instanceof Long) {
						searchString.append(" and p." + param + "="
								+ criterias.get(param.toString()));
					}
					else if (StringUtils.isEmpty(criterias.get(param.toString()).toString())) {
						searchString.append("");
					}
					else if (criterias.get(param.toString()).toString()
							.contains("null")) {
						if (criterias.get(param.toString()).toString()
								.contains("!"))
							searchString.append(" and p." + param
									+ " IS NOT NULL");
						else
							searchString.append(" and p." + param + " IS NULL");
					} else if (criterias.get(param.toString()).toString()
							.contains(",")) {
						StringBuilder valBuilder = new StringBuilder();
						String[] values = criterias.get(param.toString())
								.toString().trim().split(",");
						for (int i = 0; i < values.length; i++) {
							values[i] = "'" + values[i] + "',";
							valBuilder.append(values[i]);
						}
						if (valBuilder.length() > 1) {
							valBuilder = valBuilder.deleteCharAt(valBuilder
									.length() - 1);
							searchString.append(" and UPPER(p." + param
									+ ") IN (" + valBuilder.toString() + ")");
						}
					} else if (criterias.get(param.toString()).toString()
							.startsWith("!=")
							|| criterias.get(param.toString()).toString()
									.startsWith("<>")) {
						searchString.append(" and p."
								+ param
								+ " <> '"
								+ criterias.get(param.toString()).toString()
										.trim().substring(2) + "'");

					} else if (criterias.get(param.toString()) instanceof Date) {
						if ("from".equalsIgnoreCase(param.toString())) {
							Timestamp firstDate = new Timestamp(
									((Date) criterias.get(param.toString()))
											.getTime());
							Timestamp secondDate = null;
							secondDate = new Timestamp(
									((Date) criterias.get("to")).getTime() + 86399999);
							String fieldName = (String) criterias
									.get("dateField");
							searchString.append(" and UPPER(p." + fieldName
									+ ") Between '" + firstDate + "' AND '"
									+ secondDate + "'");
						}
					} else {
						if (!"dateField".equalsIgnoreCase(param.toString()))
						{
							if (criterias.get(param).toString().contains("-") )
							{
								searchString.append(" and UPPER(p." + param
									+ ") like UPPER('"
									+ criterias.get(param.toString()) + "%') ");
									// + criterias.get(param.toString()) + "') ");
							}
							else{
								searchString.append(" and UPPER(p." + param
										+ ") like UPPER('"
										//+ criterias.get(param.toString()) + "%') ");
										 + criterias.get(param.toString()) + "') ");
								}
						}
					}
				}
				
			}
		}
		return searchString;
	}

	
	public EntityManager getEntityManager() {
		return entityManager;
	}

	
	

	
	
}
