package Utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.bcits.bsmartbilling.Login;
import com.bcits.bsmartbilling.UtilMaster;

import android.os.Environment;

public class DefaultClass {
	// 0 for debug mode === stacttrace prints in console
	//1 for run mode==== stacktrace goes to the log file
	static int debugRunMode = 1;
	public static String mainDir = "CESCTRM/log";
	public static String logfile;
	static SimpleDateFormat dff= new SimpleDateFormat("ddMMyyyy");
	static Date d = new Date();
	public static String currentdate = dff.format(d);
	static String logfilename = currentdate+"_LogFile.log";
	static PrintStream pw = null;
	 PrintStream pw1 = null;
	
	public DefaultClass() 
	 {
		
		if(UtilMaster.mGLogin_SiteCode.trim()!="" && UtilMaster.mGLogin_MRCode.trim()!="")
		{
			logfile = Environment.getExternalStorageDirectory() +"/"+mainDir+"/"+UtilMaster.mGLogin_SiteCode+"_"+UtilMaster.mGLogin_MRCode+"_"+currentdate+"_LogFile.log";
		}else{
		logfile = Environment.getExternalStorageDirectory() +"/"+mainDir+"/"+currentdate+"_LogFile.log";
		}
		  if(DefaultClass.debugRunMode == 1)
			{
			  File f = new File(DefaultClass.logfile);
			  if(!f.exists())
			  {
				  try {
					f.createNewFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			  }
			  System.out.println("File length()========================== " + f.length());
				try {
					pw = new PrintStream(new FileOutputStream(f, true));
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  
				System.setErr(pw);
			}
	 }
}
