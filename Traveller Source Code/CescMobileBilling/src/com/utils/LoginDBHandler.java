package com.utils;

import java.io.File;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.os.Environment;

public class LoginDBHandler {

	private SQLiteHelper sqLiteHelper;
	private SQLiteDatabase sqLiteDatabase;
	private Context context;
	
	static File dir = new File(Environment.getExternalStorageDirectory()+"" );
	private static String MYDATABASE_NAME =  dir + "/CESCTRM/LOGIN.db";
	public static final String MYDATABASE_TABLE = "LOGIN";
	public static final int MYDATABASE_VERSION = 1;
	
	public static final String  KEY_MRNAME ="MRNAME";
	public static final String  KEY_SITECODE ="SITECODE";
	public static final String  KEY_LOGINSTATUS ="LOGINSTATUS";
	public static final String  KEY_UPDATEDSTATUS ="UPDATEDSTATUS";
	
	
	 static String SCRIPT_CREATE_LOGIN_DATABASE = "CREATE TABLE IF NOT EXISTS "
			 				+MYDATABASE_TABLE+ " " +"( "+KEY_MRNAME+"	TEXT  ," +
			 									     KEY_SITECODE+"	TEXT	," +
			 									    KEY_LOGINSTATUS+ " 	TEXT," +
			 									   KEY_UPDATEDSTATUS+" TEXT ); ";
	
	 
	 public LoginDBHandler(Context c) {
			context = c;
		}

		/**
		 * @return
		 * @throws android.database.SQLException
		 */
		public LoginDBHandler openToRead() throws android.database.SQLException {
			sqLiteHelper = new SQLiteHelper(context, MYDATABASE_NAME, null,
					MYDATABASE_VERSION);
			sqLiteDatabase = sqLiteHelper.getReadableDatabase();
			return this;
		}

		/**
		 * @return
		 * @throws android.database.SQLException
		 */
		public LoginDBHandler openToWrite() throws android.database.SQLException {
			sqLiteHelper = new SQLiteHelper(context, MYDATABASE_NAME, null,
					MYDATABASE_VERSION);
			sqLiteDatabase = sqLiteHelper.getWritableDatabase();
			return this;
		}

		public void close() {
			sqLiteHelper.close();
		}
		
		
		 public boolean isOpen() {
	            return sqLiteDatabase!=null && sqLiteDatabase.isOpen();
	        }
		
		
		
		 /***************HELPER CLASS******* */
		
		public class SQLiteHelper extends SQLiteOpenHelper {

			public SQLiteHelper(Context context, String name,
					CursorFactory factory, int version) {

				super(context, name, factory, version);
			}

			@Override
			public void onCreate(SQLiteDatabase db) {
				
				// TODO Auto-generated method stub
				db.execSQL(SCRIPT_CREATE_LOGIN_DATABASE);
				System.out.println("******************************");
			}

			@Override
			public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
				// TODO Auto-generated method stub
				db.execSQL("DROP TABLE IF EXISTS " + MYDATABASE_TABLE);
			}

		}
		/***************END HELPER CLASS******* */
		
		
		
		 /*REQUIRED*/
        public void updateupdatedstatus(String sitecode,String mrCode)
        {
        	try{
        	ContentValues values =  new ContentValues();
      	  	values.put(KEY_UPDATEDSTATUS,"1");
      	  	sqLiteDatabase.update(MYDATABASE_TABLE, values, KEY_SITECODE+" = '"+sitecode+"'  AND  "+KEY_MRNAME+" = '"+mrCode+"'" , null);
        	}catch (Exception e) {
        		e.printStackTrace();
			}
        }
        
        /*REQUIRED*/
        public boolean checkUsername(String mrname,String sdocode)
        {
        	boolean result = false;
        	Cursor cursor= null;
        	try{
		 String countQuery = "SELECT * FROM  "+ MYDATABASE_TABLE +"  WHERE upper("+KEY_MRNAME+") = upper('"+mrname+"') and  "+KEY_SITECODE+" = '"+sdocode+"'  ";
	         cursor = sqLiteDatabase.rawQuery(countQuery, null);
	        if(cursor.moveToFirst())
	        	result=true;
	        cursor.close();
        	}catch (Exception e) {
				// TODO: handle exception
        		if(cursor!=null)
        			cursor.close();
        		e.printStackTrace();
			}
		return result;
        }
        
        
        /*REQUIRED*/
        public boolean checkwhthereempty()
        {
        	boolean result = false;
        	Cursor cursor  = null;
        	try{
        	String login1 = "SELECT * FROM "+MYDATABASE_TABLE;
        	
        	if(sqLiteDatabase!=null)
        	{
	         cursor = sqLiteDatabase.rawQuery(login1, null);
	        if(cursor.moveToFirst())
	        	result=true;
        	}
        	if(cursor!=null)
	        cursor.close();
        	}catch (Exception e) {
				// TODO: handle exception
        		if(cursor!=null)
        			cursor.close();
        		e.printStackTrace();
			}
		return result;
        }
        
        
        public boolean searchForMrname(String mrname,String siteCode) throws SQLException {
	    	 int i=0;
	    	 Cursor cursor =  null;
	    	 try{
	         cursor =  sqLiteDatabase.query(MYDATABASE_TABLE, null,KEY_MRNAME+" ='" + mrname+"' and "+KEY_SITECODE+" = '"+siteCode+"'", null, null, null, null);
          
           if (cursor.getCount() > 0){
           	 i=1;
           }
           cursor.close();
           
	    	 }catch (Exception e) {
				// TODO: handle exception
	    		 if(cursor!=null)
	    			 e.printStackTrace();
	    		 e.printStackTrace();
	    		
			}
           if(i==1)
           {
           	return true;
           }else
           {
           	return false;
           }
          
       }
		
        
        public boolean checklogin(String mrname,String sdocode) throws SQLException {
	    	System.out.println("Checking in login table");
	    	System.out.println(mrname);
	    	System.out.println(sdocode);
	        Cursor cursor =  sqLiteDatabase.query(MYDATABASE_TABLE, null,KEY_MRNAME+" ='"+mrname+"'   AND  "+KEY_SITECODE+" ='"+sdocode+"'  AND  "+KEY_LOGINSTATUS+" = '1' ", null, null, null, null);
            int i=0;
            if (cursor.getCount() > 0){
            	 i=1;
                //do nothing everything's as it should be
            }
            cursor.close();
            if(i==1)
            {
            	return true;
            }else
            {
            	return false;
            }
           
        }
	    public long insertLogin(String[] values ) {
	    	  ContentValues values1 = new ContentValues();
	    	  values1.put(KEY_MRNAME, values[0]);
	    	  values1.put(KEY_SITECODE, values[1]);
	    	  values1.put(KEY_LOGINSTATUS, values[2]);
	    	long login =sqLiteDatabase.insert(MYDATABASE_TABLE, null, values1);
	    	return login;
	    }
        
		
}
