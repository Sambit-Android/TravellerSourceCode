package com.bcits.serviceImpl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bcits.service.GenericServiceOracle;
import com.bcits.utility.BCITSLogger;

@Transactional(propagation = Propagation.REQUIRED)
public class GenericServiceImplOracle<T>   implements GenericServiceOracle<T> 
{

	@PersistenceContext(unitName="ORACLEDataSource")
	protected EntityManager oracleEntityManager;

	private Class<T> type;

	@SuppressWarnings("unchecked")
	public GenericServiceImplOracle() {
		Type t = getClass().getGenericSuperclass();
		ParameterizedType pt = (ParameterizedType) t;
		type = (Class) pt.getActualTypeArguments()[0];
	}

	/**
	 * Perform an initial save of a previously unsaved Entity. All subsequent
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
	 * UsersDAO.save(entity);
	 * txManager.commit(txn);
	 * </pre>
	 * 
	 * @param t
	 *            Entity to persist
	 * @return Entity instance
	 * @since 0.1
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public T save(final T t) {		
		BCITSLogger.logger.info("saving "+t+" instance");
		try {
			this.oracleEntityManager.persist(t);
			BCITSLogger.logger.info(t+"saved successful");
			return t;
			
		} catch (RuntimeException re) {
			BCITSLogger.logger.error("saving "+t+" failed", re);
			throw re;
		}
	}

	/**
	 * Delete a persistent Entity. This operation must be performed within the a
	 * database transaction context for the entity's data to be permanently
	 * deleted from the persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * <p>
	 * User-managed Spring transaction example:
	 * 
	 * <pre>
	 * TransactionStatus txn = txManager
	 * 		.getTransaction(new DefaultTransactionDefinition());
	 * UsersDAO.delete(entity);
	 * txManager.commit(txn);
	 * entity = null;
	 * </pre>
	 * 
	 * @param id
	 *            Entity property
	 * @since 0.1
	 */
	@Override
	public void delete(final Object id) {
		
		BCITSLogger.logger.info("deleting instance");
		try {
			this.oracleEntityManager.remove(this.oracleEntityManager.getReference(type, id));
			BCITSLogger.logger.info("delete successful");
		} catch (RuntimeException re) {
			BCITSLogger.logger.error("delete failed", re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved Entity and return it or a copy of it to the
	 * sender. A copy of the Users entity parameter is returned when the JPA
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
	 * entity = UsersDAO.update(entity);
	 * txManager.commit(txn);
	 * </pre>
	 * 
	 * @param t
	 *            Entity instance to update
	 * @return Updated entity instance
	 * @since 0.1
	 */
	@Override
	public T update(final T t) {
		
		BCITSLogger.logger.info("updating "+t+" instance");
		try {
			T result = this.oracleEntityManager.merge(t);
			BCITSLogger.logger.info("update successful");
			return result;
		} catch (RuntimeException re) {
			BCITSLogger.logger.error("update failed", re);
			throw re;
		}
	}

	/**
	 * Find all Entity instances with a specific property value.

	 * @param id
	 *            property to query
	 * @return T found by query
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	@Override
	public T find(final Object id) {		
		BCITSLogger.logger.info("finding instance with id: " + id);
		try {
			return (T) this.oracleEntityManager.find(type, id);
		} catch (RuntimeException re) {
			BCITSLogger.logger.error("find failed", re);
			throw re;
		}
	}
	
	public void getRecentPath(String recentPath,HttpServletRequest request)
	{
		HttpSession session= request.getSession();
		session.setAttribute("path", recentPath);
	}
	
	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	 public Date getDate2(String dateString)
	 {
		  DateFormat formatter = null;
	        Date convertedDate = null;
	        try{
	        formatter = new SimpleDateFormat("dd-MM-yyyy");
	        convertedDate =  formatter.parse(dateString);
	        }
	        catch(Exception e) {
	     	   e.printStackTrace();
	 			// TODO: handle exception
	 		}
		 return convertedDate;
	 }
	
	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	 public Date yyyyddStringToyyyyddDate(String dateString)
	 {
		  DateFormat formatter = null;
	        Date convertedDate = null;
	        try{
	        formatter = new SimpleDateFormat("yyyydd");
	        convertedDate =  formatter.parse(dateString);
	        }
	        catch(Exception e) 
	        {
	     	   e.printStackTrace();
	 		}
		 return convertedDate;
	 }
	 
	 public String getDate3(Date date)
	 {
		 SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
		 String datevalue=sdf.format(date);		 
		 return datevalue;
	 }
	 
	 public String getDate4(Date date)
	 {
		 SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		 String datevalue=sdf.format(date);		 
		 return datevalue;
	 }
	 
	 public float doubRound(double val, int places) 
	 {
		  long factor = (long)Math.pow(10,places);
		  val = val * factor;
		  long tmp = Math.round(val);
		  return (float)tmp / factor;
	 }
	 
	 public  LocalDate getNthSundayOfMonth(final int n, final int month, final int year) {
	     final LocalDate firstSunday = new LocalDate(year, month, 1).withDayOfWeek(DateTimeConstants.SUNDAY);
	     
	     if (n > 1) {
	       final LocalDate nThSunday = firstSunday.plusWeeks(n-1);
	       final LocalDate lastDayInMonth = firstSunday.dayOfMonth().withMaximumValue();
	       if (nThSunday.isAfter(lastDayInMonth)) {
	         throw new IllegalArgumentException("There is no " + n + "th Sunday in this month!");
	       }
	       return nThSunday;
	     }
	     return firstSunday;
	   }
	 
	 public  LocalDate getNthSaturdayOfMonth(final int n, final int month, final int year) 
	 {
		 if(new LocalDate(year, month, 01).dayOfWeek().getAsShortText().equals("Sun"))
			{
			 final LocalDate firstSat = new LocalDate(year, month, 02).withDayOfWeek(DateTimeConstants.SATURDAY);
		     if (n > 1) 
		     {
		       final LocalDate nThSat = firstSat.plusWeeks(n-1);
		       final LocalDate lastDayInMonth = firstSat.dayOfMonth().withMaximumValue();
		       if (nThSat.isAfter(lastDayInMonth))
		       {  
		         throw new IllegalArgumentException("There is no " + n + "th saturday in this month!");
		       }
		       return nThSat;
		     }
		     return firstSat;
			}
		 else
		 {
			 final LocalDate firstSat = new LocalDate(year, month, 01).withDayOfWeek(DateTimeConstants.SATURDAY);
		     if (n > 1) 
		     {
		       final LocalDate nThSat = firstSat.plusWeeks(n-1);
		       final LocalDate lastDayInMonth = firstSat.dayOfMonth().withMaximumValue();
		       if (nThSat.isAfter(lastDayInMonth))
		       {  
		         throw new IllegalArgumentException("There is no " + n + "th saturday in this month!");
		       }
		       return nThSat;
		     }
		     return firstSat;
		 }
	     
	   }
	 
	 public  int countOfSaturdays(int year, int month) {
		    Calendar calendar = Calendar.getInstance();
		    // Note that month is 0-based in calendar, bizarrely.
		    calendar.set(year, month - 1, 1);
		    int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		    int count = 0;
		    for (int day = 1; day <= daysInMonth; day++) {
		        calendar.set(year, month - 1, day);
		        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
		        if (dayOfWeek == Calendar.SATURDAY ) {
		            count++;
		            // Or do whatever you need to with the result.
		        }
		    }
		    return count;
		}
	 
	 public  int countOfSundays(int year, int month) {
		    Calendar calendar = Calendar.getInstance();
		    // Note that month is 0-based in calendar, bizarrely.
		    calendar.set(year, month - 1, 1);
		    int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

		    int count = 0;
		    for (int day = 1; day <= daysInMonth; day++) {
		        calendar.set(year, month - 1, day);
		        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
		        if (dayOfWeek == Calendar.SUNDAY ) {
		            count++;
		            // Or do whatever you need to with the result.
		        }
		    }
		    return count;
		}
	 
	 public  int getNoOfDaysofMonth(int year, int month) {
		    Calendar calendar = Calendar.getInstance();
		    // Note that month is 0-based in calendar, bizarrely.
		    calendar.set(year, month - 1, 1);

		    int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

		    return daysInMonth;
		}
	 
	 public Date getDatenTime(String dateString)
	 {
		 DateFormat formatter = null;
	        Date convertedDate = null;
	        try{
	        formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
	        convertedDate =  formatter.parse(dateString);
	        }
	        catch(Exception e) {
	     	   e.printStackTrace();
	 			// TODO: handle exception
	 		}
		 
		 return convertedDate;
	 }
	 
	 public Date getDateTime(String dateString)
	 {
		 DateFormat formatter = null;
	        Date convertedDate = null;
	        try{
	        formatter = new SimpleDateFormat("dd-MM-yyyy hh : mm : ss");
	        convertedDate =  formatter.parse(dateString);
	        }
	        catch(Exception e) {
	     	   e.printStackTrace();
	 			// TODO: handle exception
	 		}
		 
		 return convertedDate;
	 }

}
