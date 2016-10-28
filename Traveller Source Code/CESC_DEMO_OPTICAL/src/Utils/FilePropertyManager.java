package Utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Properties;
import java.util.PropertyResourceBundle;

import android.os.Environment;

public class FilePropertyManager 
{
	private static Hashtable htFiles = new java.util.Hashtable();

	public FilePropertyManager()
	{
		super();
	}

	private static synchronized PropertyResourceBundle createBundle(
			String fileName) throws Exception 
			{
				FileInputStream propStream;
				PropertyResourceBundle propBundle;
				try {
						propStream = new FileInputStream(Environment.getExternalStorageDirectory()+"/"+fileName);
					}
				catch (java.io.FileNotFoundException e) 
				{
					throw new Exception("Cannot initialise the property bundle. File "+ fileName + "is not found", e);
				}
				try {
						propBundle = new PropertyResourceBundle(propStream);
						propStream.close();
					}
				catch (java.io.IOException e)
				{
					throw new Exception("Cannot create a property bundle for file + "
					+ fileName, e);
				}
					htFiles.put(Environment.getExternalStorageDirectory()+"/"+fileName, propBundle);
					return (propBundle);

			}

	public static String getProperty(String param, String fileName)
			throws Exception {
		Properties properties;
		properties = new Properties();
		InputStream input;
		properties.load(input = new FileInputStream(Environment.getExternalStorageDirectory()+"/"+fileName));
		return properties.getProperty(param);
	}

	public static java.io.InputStream getStream(String fileName)
			throws Exception {
		FileInputStream propStream;
		String file = fileName;
		try {
			propStream = new FileInputStream(Environment.getExternalStorageDirectory()+"/"+file);
		} catch (java.io.FileNotFoundException e) {
			throw new Exception("Cannot initialise the property page. file "
					+ file + "is not found", e);
		}
		return propStream;
	}

	public static String setProperty(String key, String value, String fileName)
			throws Exception
			{
				String prop;
				Properties properties;
				InputStream input;
				OutputStream output;
				String file = fileName;
				properties = new Properties();
				properties.load(input = new FileInputStream(Environment.getExternalStorageDirectory()+"/"+file));
				input.close();
				properties.setProperty(key, value); // .handleGetObject(param);
				properties.store(output = new FileOutputStream(Environment.getExternalStorageDirectory()+"/"+file), null);
				output.close();
				return null;
			}
	
	public static void appendLog(String text)
	{       
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		System.out.println(dateFormat.format(date));
	   File logFile = new File(Environment.getExternalStorageDirectory()+"/CESCTRM/log/log_"+dateFormat.format(date)+".txt");
	   if (!logFile.exists())
	   {
	      try
	      {
	         logFile.createNewFile();
	      } 
	      catch (IOException e)
	      {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      }
	   }
	   try
	   {
	      //BufferedWriter for performance, true to set append to file flag
	      BufferedWriter buf = new BufferedWriter(new FileWriter(logFile, true)); 
	      buf.append(text);
	      buf.newLine();
	      buf.close();
	   }
	   catch (IOException e)
	   {
	      // TODO Auto-generated catch block
	      e.printStackTrace();
	   }
	}

}
