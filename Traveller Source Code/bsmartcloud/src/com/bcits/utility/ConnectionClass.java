package com.bcits.utility;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionClass
{
	public ConnectionClass() {

    }
	public Connection getconnection() {
        Connection conn = null;
        InputStream inputStream;
        String username="";
        String password="";
        String driver="";
        String url="";
        try {
           
        	Properties prop = new Properties();
			String propFileName = "application.properties";
 
			inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
 
			if (inputStream != null)
			{
				prop.load(inputStream);
			} else
			{
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}
			 driver = prop.getProperty("datasource1.driverClassName");
			 url = prop.getProperty("datasource1.url");
			 username = prop.getProperty("datasource1.username");
			 password = prop.getProperty("datasource1.password");
			 
            Class.forName(driver);
        } catch (Exception e) {
            System.out.println("Unable to load the driver class!===" + e);
        }
        try {
        	//LOCAL
          //conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.2.81:1522:orcl", ""+sdocode+"" , "bcits");
            //TEST
        	//conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.10.250:1521:GESCOM", ""+sdocode+"" , "bcits");
        	//LIVE
        	 conn = DriverManager.getConnection(url, username , password);
        	System.out.println("Connection Success====");
          
        } catch (SQLException e) {
            System.out.println("Could't get connection! " +e);
        }
        return conn;
    }
}
