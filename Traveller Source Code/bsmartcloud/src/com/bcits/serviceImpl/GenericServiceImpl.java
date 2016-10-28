package com.bcits.serviceImpl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bcits.service.GenericService;
import com.bcits.utility.BCITSLogger;


/**
 * A data access object (DAO) providing persistence and search support for Users
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @author Manjunath Krishnappa
 * @version %I%, %G%
 */
@Transactional(propagation = Propagation.REQUIRED)
public abstract class GenericServiceImpl<T> implements GenericService<T> {

	@PersistenceContext(unitName="POSTGREDataSource")
	protected EntityManager entityManager;
	

	private Class<T> type;

	@SuppressWarnings("unchecked")
	public GenericServiceImpl() {
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
			this.entityManager.persist(t);
			//payRollLogger.logger.info(t+" saved successful");
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
			this.entityManager.remove(this.entityManager.getReference(type, id));
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
			T result = this.entityManager.merge(t);
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
			return (T) this.entityManager.find(type, id);
		} catch (RuntimeException re) {
			BCITSLogger.logger.error("find failed", re);
			throw re;
		}
	}
	
	/*@Transactional(propagation = Propagation.SUPPORTS)
	@Override
	public List<String> getCheckConstraints(String tableName,String constraintName,HttpServletRequest request)
	{		
		String dbName=(String) request.getSession().getAttribute("dbmsName");
		if(dbName.equalsIgnoreCase("ORACLE"))
		{
			BCITSLogger.logger.info("ORACLE COMING------------->"+dbName);
			String res= (String) entityManager.createNamedQuery("constraint.getChecks").setParameter("tableName", tableName.toUpperCase()).setParameter("constraintName", constraintName.toUpperCase()).getSingleResult();
			BCITSLogger.logger.info(" value is  "+res);		
			String[] splitRes=res.split(" OR ");
			int subLength = 0;
			String[] test = null;
			List<String> list=new ArrayList<String>();
			if(splitRes.length > 0)
			{
				test = splitRes[0].split("'");
				subLength = test[0].length()+1;
			}
			String finalRes="";
			String result = "";
			//List list = new ArrayList();
			for(int i=0;i<splitRes.length;i++)
			{
				finalRes=splitRes[i].trim();
				 	
				result = result + (finalRes.substring(subLength, finalRes.length()-1)) + ",";
			    
			}
			result = result.substring(0,result.length()-1);
			String[] finalres=result.split(",");
			for (int i = 0; i < finalres.length; i++) 
			{
				list.add(finalres[i]);
			}
			BCITSLogger.logger.info("value"+result);	
					return list;
		}
		else if(dbName.equalsIgnoreCase("POSTGRESQL"))
		{
			BCITSLogger.logger.info("POSTGRE COMING------------->"+dbName);
			int j=1;
			int oid=getOid(tableName);
			String sql="select c.consrc from Pg_Constraint_Entity c  where conrelid ='"+oid+"' and conname='"+constraintName.toLowerCase()+"'";
			Query query=entityManager.createQuery(sql);
			String  s=(String)query.getSingleResult();
			String s1=s.replace("(", "");
			String[] res1=s1.split(" OR ");
			String[] res2=null;
			List<String> list=new ArrayList<String>();
			for (int i = 0; i < res1.length; i++) 
			{
				 res2=res1[i].split("'");
				 list.add(res2[j]);
			}
			return list;
		}
		return null;
	}*/
	
	@Transactional(propagation = Propagation.SUPPORTS)
	@Override
	public List<String> getCheckConstraints(String tableName,String constraintName,HttpServletRequest request)
	{		
			int j=1;
			int oid=getOid(tableName);
			String sql="select c.consrc from Pg_Constraint_Entity c  where conrelid ='"+oid+"' and conname='"+constraintName.toLowerCase()+"'";
			Query query=entityManager.createQuery(sql);
			String  s=(String)query.getSingleResult();
			String s1=s.replace("(", "");
			String[] res1=s1.split(" OR ");
			String[] res2=null;
			List<String> list=new ArrayList<String>();
			for (int i = 0; i < res1.length; i++) 
			{
				 res2=res1[i].split("'");
				 list.add(res2[j]);
			}
			return list;
	}
	
	@Transactional(propagation = Propagation.SUPPORTS)
	@Override
	public List<String> getCheckConstraints(String tableName,String constraintName)
	{		
			int j=1;
			int oid=getOid(tableName);
			String sql="select c.consrc from Pg_Constraint_Entity c  where conrelid ='"+oid+"' and conname='"+constraintName.toLowerCase()+"'";
			Query query=entityManager.createQuery(sql);
			String  s=(String)query.getSingleResult();
			String s1=s.replace("(", "");
			String[] res1=s1.split(" OR ");
			String[] res2=null;
			List<String> list=new ArrayList<String>();
			for (int i = 0; i < res1.length; i++) 
			{
				 res2=res1[i].split("'");
				 list.add(res2[j]);
			}
			return list;
	}
	
	@Transactional(propagation = Propagation.SUPPORTS)
	@Override
	public int getOid(String tableName)
	{
		String sql="select p.oid from Pg_Class_Entity p where p.relname='"+tableName.toLowerCase()+"'";
		Query query=entityManager.createQuery(sql);
		int  s=(Integer)query.getSingleResult();
		return s;		
	}
	
	/*@Transactional(propagation = Propagation.SUPPORTS)
	@Override
	public List<String> getPostgreChecks(String tableName,String constraintName)
	{
		int oid=getOid(tableName);
		String sql="select c.consrc from AllConstraintPostgre c  where conrelid ='"+oid+"' and conname='"+constraintName+"'";
		Query query=entityManager.createQuery(sql);
		String  s=(String)query.getSingleResult();
		//System.out.println("DATA1---------------------------------->"+s);
		String s1=s.replace("(", "");
		//System.out.println("DATA2---------------------------------->"+s1);
		String[] res1=s1.split(" OR ");
		List<String> list=new ArrayList<String>();
		for (int i = 0; i < res1.length; i++) 
		{
			int j=1;
			//System.out.println("DATA3---------------------------------->"+res1.length);
			//System.out.println("DATA3---------------------------------->"+res1[i]);
			String[] res2=res1[i].split("'");
			for (int j = 0; j < res2.length; j++) 
			{
				//System.out.println("DATA3---------------------------------->"+res2[j]);
			}
			list.add(res2[j]);
		}
		return list;		
	}*/
	
	public void getRecentPath(String recentPath,HttpServletRequest request)
	{

		HttpSession session= request.getSession();
		session.setAttribute("path", recentPath);
	}
	
	
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
	 		}
		 
		 return convertedDate;
	  
	 }
	 
	 public String getDate3(Date date)
	 {
		 SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
		 String datevalue=sdf.format(date);		 
		 return datevalue;
	 }
	 
	 public String getDatenTime(Date date)
	 {
		 SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
		 String datevalue=sdf.format(date);		 
		 return datevalue;
	 }
	 
	 public Timestamp getTimestamp(String dateTimeString)
	 {
		  DateFormat formatter = null;
		  Timestamp timestamp=null;
	        try{
	        formatter = new SimpleDateFormat("dd-MM-yyyy HH : mm : ss : SSS");
	        Date convertedDate =  formatter.parse(dateTimeString);
	        timestamp=new Timestamp(convertedDate.getTime());
	        }
	        catch(Exception e) 
	        {
	     	   e.printStackTrace();
	 		}
		 
		 return timestamp;
	  
	 }
	 //29 July 2014 - 05:5005 August 2014 - 12:25
	 public Timestamp getTimeStamp(String dateTimeString)
	 {
		  DateFormat formatter = null;
		  Timestamp timestamp=null;
	        try{
	        formatter = new SimpleDateFormat("dd-mm-yyyy - HH:mm ");
	        Date convertedDate =  formatter.parse(dateTimeString);
	        timestamp=new Timestamp(convertedDate.getTime());
	        }
	        catch(Exception e) 
	        {
	     	   e.printStackTrace();
	 		}
		 
		 return timestamp;
	 }
	 
	 
	public String getTime(Date date)
	{
		DateFormat time=new SimpleDateFormat("h:mm a");
		 return time.format(date);
	}
	 
	 
	public String getDate4(Date date)
	 {
		 SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
		 String datevalue=sdf.format(date);		 
		 return datevalue;
	 }
	
	@Override
	public List<String> getAllDatesInMonth(int month,int year)
	{
		List<String> dates=new ArrayList<String>();
		Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.YEAR, year);
        int maxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        dates.add(df.format(cal.getTime()));
        for (int i = 1; i < maxDay; i++) 
        {
         cal.set(Calendar.DAY_OF_MONTH, i + 1);
         dates.add(df.format(cal.getTime()));
        }
		return dates;
	}
	
	 @Override
		public  int getNoOfDaysofMonth(int year, int month) {
			    Calendar calendar = Calendar.getInstance();
			    // Note that month is 0-based in calendar, bizarrely.
			    calendar.set(year, month - 1, 1);

			    int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

			    return daysInMonth;
			}
	 
}