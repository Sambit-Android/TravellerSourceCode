/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amr.rf.utils;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import amr.rf.catcher.MasterActivity;
import android.os.Environment;

/**
 *
 * @author REMITH
 */
public class FileManager {
	
    static File dir = new File(Environment.getExternalStorageDirectory() + "");
    
    public static String rootFolder=dir+ "/AMR_BSMART";
    static String pathRadio = dir+ "/AMR_BSMART/RADIO";
    static String pathLog = dir+ "/AMR_BSMART/RADIO/LOG";
    
    public static String logFileLocation=pathLog+"/LogFile_";
    public static String DATABASE_NAME = dir+ "/AMR_BSMART/RADIO/DATABASE/METER_CONFIGURATION.db";
    static PrintWriter fileWriter = null;

    public static void createPath(String filePath) {
        File theDir = new File(filePath);

        if (!theDir.exists()) {
            System.out.println("creating directory: " + filePath); 
            boolean result = false;

            try {
                theDir.mkdir();
                result = true;
            } catch (SecurityException e) {
               e.printStackTrace();
            }
            if (result) {
                System.out.println("DIR created");
            }
        }
    }
    
   
     
     public static  void writeLog(String line)
    {
        try 
        {
//    		String time = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss",Locale.getDefault()).format(Calendar.getInstance().getTime());//GET CURRENT READING TIME
    		line=/*time+"  -  "+*/line+ "\r\n"; //GIVING NEXT LINE
            fileWriter = new PrintWriter(new BufferedWriter(new FileWriter(FileManager.logFileLocation+MasterActivity.logNameTime+".txt", true)));
            fileWriter.print(line);            
        }
        catch (IOException ex) 
        {
            throw new RuntimeException(ex.getMessage());
        }
        finally
        {
            if (fileWriter != null)
            {
                fileWriter.close();
            }
        }
    }
     
     
    public static void createAllFolders() {
    	
        createPath(rootFolder);
        createPath(pathRadio);
        createPath(pathLog);

    }
}
