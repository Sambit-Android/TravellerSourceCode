package com.bcits.service;

import java.util.Date;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.joda.time.LocalDate;

public interface GenericServiceOracle<T>
{


	/**
	 * Perform an initial save of a previously unsaved entity. All subsequent
	 * persist actions of this entity should use the #update() method. This
	 * operation must be performed within the a database transaction context for
	 * the entity's data to be permanently saved to the persistence store, i.e.,
	 * database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * <p>
	 * User-managed Spring transaction example:
	 * 
	 * <pre>
	 * TransactionStatus txn = txManager
	 * 		.getTransaction(new DefaultTransactionDefinition());
	 * GenericServiceImpl.save(entity);
	 * txManager.commit(txn);
	 * </pre>
	 * 
	 * @param t
	 *            Entity to persist
	 * @return Entity instance
	 * @since 0.1
	 */
	T save(T t);

	/**
	 * Delete a persistent Entity instance. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * <p>
	 * User-managed Spring transaction example:
	 * 
	 * <pre>
	 * TransactionStatus txn = txManager
	 * 		.getTransaction(new DefaultTransactionDefinition());
	 * IUsersDAO.delete(entity);
	 * txManager.commit(txn);
	 * entity = null;
	 * </pre>
	 * 
	 * @param id
	 *            Entity property
	 * @since 0.1
	 */
	void delete(Object id);

	/**
	 * Find all Entity instances with a specific property value.
	 * 
	 * @param id
	 *            The name of the Entity property to query
	 * @return found by query
	 * @since 0.1
	 */
	T find(Object id);

	/**
	 * Persist a previously saved Entity and return it or a copy of it to the
	 * sender. A copy of the Entity parameter is returned when the JPA
	 * persistence mechanism has not previously been tracking the updated
	 * entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * <p>
	 * User-managed Spring transaction example:
	 * 
	 * <pre>
	 * TransactionStatus txn = txManager
	 * 		.getTransaction(new DefaultTransactionDefinition());
	 * entity = IUsersDAO.update(entity);
	 * txManager.commit(txn);
	 * </pre>
	 * 
	 * @param t
	 *            Entity to update
	 * @return Updated entity instance
	 * @since 0.1
	 */
	T update(T t);

	
	//To get recent path
	void getRecentPath(String path,HttpServletRequest request);
	Date getDate2(String dateString);
	String getDate3(Date date);
	String getDate4(Date date);
	 public Date getDatenTime(String date);
	 public Date getDateTime(String dateString);
	 
	public float doubRound(double val, int places); 
	public  LocalDate getNthSundayOfMonth(final int n, final int month, final int year);
	public  LocalDate getNthSaturdayOfMonth(final int n, final int month, final int year);
	public  int countOfSaturdays(int year, int month);
	public  int countOfSundays(int year, int month);
	public  int getNoOfDaysofMonth(int year, int month);

	Date yyyyddStringToyyyyddDate(String dateString);
	
	

	
}
