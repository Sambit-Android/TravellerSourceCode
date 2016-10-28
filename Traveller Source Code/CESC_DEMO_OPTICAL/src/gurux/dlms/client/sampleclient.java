//
// --------------------------------------------------------------------------
//  Gurux Ltd
// 
//
//
// Filename:        $HeadURL$
//
// Version:         $Revision$,
//                  $Date$
//                  $Author$
//
// Copyright (c) Gurux Ltd
//
//---------------------------------------------------------------------------
//
//  DESCRIPTION
//
// This file is a part of Gurux Device Framework.
//
// Gurux Device Framework is Open Source software; you can redistribute it
// and/or modify it under the terms of the GNU General Public License 
// as published by the Free Software Foundation; version 2 of the License.
// Gurux Device Framework is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of 
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. 
// See the GNU General Public License for more details.
//
// More information of Gurux products: http://www.gurux.org
//
// This code is licensed under the GNU General Public License v2. 
// Full text may be retrieved at http://www.gnu.org/licenses/gpl-2.0.txt
//---------------------------------------------------------------------------

package gurux.dlms.client;

import gurux.common.IGXMedia;
import gurux.dlms.GXDLMSClient;
import gurux.dlms.enums.Authentication;
import gurux.dlms.internal.GXCommon;
import gurux.dlms.manufacturersettings.GXManufacturer;
import gurux.dlms.manufacturersettings.GXManufacturerCollection;
import gurux.dlms.objects.GXDLMSObject;
import gurux.dlms.objects.GXDLMSObjectCollection;
import gurux.dlms.objects.GXDLMSProfileGeneric;
import gurux.dlms.objects.IGXDLMSBase;
import gurux.io.Parity;
import gurux.io.StopBits;
import gurux.serial.java.GXSerial;
import gurux.terminal.GXTerminal;

import java.io.File;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import amr.utils.HelperClass;
import amr.utils.Utils;
import android.app.Activity;
import android.net.ParseException;
import android.os.Environment;

public class sampleclient 
{
	
	public static  GXManufacturer man;
	public static GXDLMSClient dlms;
	public static String errorMessage="";
    /**
    *
    * Show help.
    */
    static void ShowHelp()
    {
       Utils.dirctLogcat("GuruxDlmsSample reads data from the DLMS/COSEM device.");
       Utils.dirctLogcat("");
       Utils.dirctLogcat("GuruxDlmsSample /m=lgz /h=www.gurux.org /p=1000 [/s=] [/u]");        
       Utils.dirctLogcat(" /m=\t manufacturer identifier.");
       Utils.dirctLogcat(" /sp=\t serial port. (Example: COM1)");
       Utils.dirctLogcat(" /n=\t Phone number.");
       Utils.dirctLogcat(" /b=\t Serial port baud rate. 9600 is default.");
       Utils.dirctLogcat(" /h=\t host name.");
       Utils.dirctLogcat(" /p=\t port number or name (Example: 1000).");
       Utils.dirctLogcat(" /s=\t start protocol (IEC or DLMS).");        
       Utils.dirctLogcat(" /a=\t Authentication (None, Low, High).");
       Utils.dirctLogcat(" /pw=\t Password for authentication.");                
       Utils.dirctLogcat(" /t\t Trace messages.");                
       Utils.dirctLogcat(" /u\t Update meter settings from Gurux web portal.");
       Utils.dirctLogcat("Example:");
       Utils.dirctLogcat("Read LG device using TCP/IP connection.");
       Utils.dirctLogcat("GuruxDlmsSample /m=lgz /h=www.gurux.org /p=1000");
       Utils.dirctLogcat("Read LG device using serial port connection.");
       Utils.dirctLogcat("GuruxDlmsSample /m=lgz /sp=COM1 /s=DLMS");
    }
    
    static void trace(PrintWriter logFile, String text)
    {
//        logFile.write(text);
//        System.out.print(text);
    }

    static void traceLn(PrintWriter logFile, String text)
    {        
//        logFile.write(text + "\r\n");
//        System.out.print(text + "\r\n");
    }

    /**
     * @param args the command line arguments
     * @return 
     */
    public static HashMap<String, String> hashMap;
    public  HashMap<String, String> getMeterReading(String[] args,Activity activity) 
    {   
    	 errorMessage="";
         hashMap = new HashMap<String, String>();
        // Gurux example server parameters.
        // /m=lgz /h=localhost /p=4060 
        // /m=grx /h=localhost /p=4061    
        GXCommunicate com = null;
        try
        {
            IGXMedia media = null;
            
            /*File dir = new File(Environment.getExternalStorageDirectory() + "");
            String path=dir+ "/Manufacturer_Settings";*/
            
            
            /*String[] f = MainActivity.context.getAssets().list("");
            for(String f1 : f){
            	Utils.dirctLogcat("FILE NAME ==== "+f1);
            }*/
            
            
            
            //Get manufacturer settings from Gurux web service if not installed yet.
            //This is something that you do not nesessary seed. You can 
            //hard code the settings. This is only for demostration.
            //Use hard coded settings like this:
            // GXDLMSClient cl = new GXDLMSClient(true,             
            // (byte) 0x21,            
            // (byte) 0x3,            
            // Authentication.None,             
            // null,
            // InterfaceType.General);

         /*   try
            {
                if (GXManufacturerCollection.isFirstRun(path))
                { 
                	//TODO
                   GXManufacturerCollection.updateManufactureSettings(path);
                }
            }
            catch(Exception ex)
            {
               Utils.dirctLogcat(ex.toString());
            }*/
            //4059 is Official DLMS port.
            String id = "", host = "", port = "4059", pw = "";
            boolean trace = false, iec = true;
            Authentication auth = Authentication.NONE;  
            int startBaudRate = 9600;
            String number = null;
            for (String it : args)
            {
                String item = it.trim();
                if (item.compareToIgnoreCase("/u") == 0)//Update
                {
                	//TODO//Get latest manufacturer settings from Gurux web server.
                   // GXManufacturerCollection.updateManufactureSettings(path);
                }
                else if(item.startsWith("/m="))//Manufacturer
                {
                    id = item.replaceFirst("/m=", "");
                }
                else if(item.startsWith("/h=")) //Host
                {
                    host = item.replaceFirst("/h=", "");              
                }
                else if (item.startsWith("/p="))// TCP/IP Port
                {
                    media = new gurux.net.GXNet();
                    port = item.replaceFirst("/p=", "");
                }
                else if (item.startsWith("/sp="))//Serial Port
                {
                    if (media == null)
                    {
                    	try {
                    	    media = new gurux.serial.java.GXSerial();		
						} catch (Exception e) {
							Utils.dirctLogcat(e.toString()); 						}
                    
                    }
                    port = item.replaceFirst("/sp=", "");                    
                }
                else if (item.startsWith("/n="))//Phone number for terminal.
                {               
                    media = new GXTerminal();
                    number = item.replaceFirst("/n=", "");                    
                }
                else if(item.startsWith("/b="))//Baud rate
                {
                    startBaudRate = Integer.parseInt(item.replaceFirst("/b=", ""));
                }
                else if (item.startsWith("/t"))//Are messages traced.
                {                    
                    trace = true;
                }
                else if (item.startsWith("/s="))//Start
                {
                    String tmp = item.replaceFirst("/s=", "");
                    iec = !tmp.toLowerCase().equals("dlms");
                }
                else if (item.startsWith("/a="))//Authentication
                {
                    auth = Authentication.valueOf(it.trim().replaceFirst("/a=", "").toUpperCase());                    
                }
                else if (item.startsWith("/pw="))//Password
                {
                    pw = it.trim().replaceFirst("/pw=", "");
                }
                else
                {
                    ShowHelp();
                    return hashMap;
                }
            }
            if (id.isEmpty() || port.isEmpty() || (media instanceof gurux.net.GXNet && host.isEmpty()))
            {
                ShowHelp();
                return hashMap;
            }            
       
            //Initialize connection settings.
            if (media instanceof GXSerial)
            {
                GXSerial serial = (GXSerial) media;
                serial.setPortName(port);
                if (iec)
                {
                    serial.setBaudRate(300);
                    serial.setDataBits(7);
                    serial.setParity(Parity.EVEN);
                    serial.setStopBits(StopBits.ONE);                    
                    Utils.dirctLogcat( "Connection Serttings initialized===========1");	
                }
                else
                {
                    serial.setBaudRate(startBaudRate);
                    serial.setDataBits(8);
                    serial.setParity(Parity.NONE);
                    serial.setStopBits(StopBits.ONE);                    
                    Utils.dirctLogcat( "Connection Serttings initialized===========2");	
                }
            }
         
            else
            {
            	Utils.dirctLogcat( "Unknown media type.");	
            	 return hashMap;
            }
            dlms = new GXDLMSClient(); 
      /*      GXManufacturerCollection items = new GXManufacturerCollection();
            GXManufacturerCollection.readManufacturerSettings(items, path,activity);
             man = items.findByIdentification(id);*/
            
            
            if (man == null)
            {
            	Utils.dirctLogcat( "Invalid manufacturer.");	
            	 return hashMap;
            }
            else{
            	 Utils.dirctLogcat(man.getName());
            	 Utils.dirctLogcat(man.getUseLogicalNameReferencing()+"");
            	 Utils.dirctLogcat(man.getServerSettings()+"");
            	 Utils.dirctLogcat(man.getSettings()+"");
            	 Utils.dirctLogcat(man.getIdentification()+"");
                
            	 Utils.dirctLogcat( "Manufacturer okey --");	
            	
            }
            
            
            dlms.setObisCodes(man.getObisCodes());
            Utils.dirctLogcat( "After reading xml");

            com = new GXCommunicate(1500, dlms, man, iec, auth, pw, media);                        
            com.Trace = trace;
            
            com.initializeConnection();     
            
            Utils.dirctLogcat( "Reading association view");                          
            byte[] reply = com.readDataBlock(dlms.getObjectsRequest());
            GXDLMSObjectCollection objects = dlms.parseObjects(reply, true);                                                
            //Read Profile Generic columns first.
         /*   GXDLMSObjectCollection profileGenerics = objects.getObjects(ObjectType.PROFILE_GENERIC);
            for(GXDLMSObject it : profileGenerics)
            {      
            	Utils.dirctLogcat(  "Profile Generic " + it.getName() + " Columns:");           
            	
                GXDLMSProfileGeneric pg = (GXDLMSProfileGeneric) it;
                //Read columns.
                try
                {
                    GXDLMSObject[] columns = (GXDLMSObject[]) com.readObject(pg, 3);
                    //Read and update columns scalers.
                    for (GXDLMSObject it2 : columns)
                    {
                        if (it2 instanceof GXDLMSRegister)
                        {
                            com.readObject(it2, 3);
                        }
                        if (it2 instanceof GXDLMSDemandRegister)
                        {
                            com.readObject(it2, 4);
                        }
                    }
                    boolean First = true;
                    StringBuilder sb = new StringBuilder();
                    for(GXDLMSObject col : columns)
                    {
                        if (!First)
                        {
                            sb.append(" | ");
                        }                        
                        sb.append(col.getName());
                        sb.append(" ");
                        String desc = col.getDescription();
                        if (desc != null)
                        {
                            sb.append(desc);
                        }
                        First = false;
                    }
                  Utils.dirctLogcat( sb.toString());  
                }
                catch(Exception ex)
                {
                   Utils.dirctLogcat(  "Err! Failed to read columns:" + ex.getMessage()); 
                }
              // break; //ADDED EXTRA
            }*/
            
            //Read all attributes from all objects.
            
            for(GXDLMSObject it : objects)
            {
            	
                if (!(it instanceof IGXDLMSBase))
                {
                    //If interface is not implemented.
                	Utils.dirctLogcat( "Unknown Interface: " + it.getObjectType().toString()); 
                    continue;
                }
                
                if (it instanceof GXDLMSProfileGeneric)
                {
                    //Profile generic are read later 
                    // because it might take so long time
                    // and this is only a example.
                    continue;
                }      
                
                
               if(it.getName().toString().trim().equals("1.0.1.8.0.255")/* || it.getName().toString().trim().equals("0.0.1.0.0.255")||it.getName().toString().trim().equals("0.0.96.1.0.255")*/)
               {
                   Utils.dirctLogcat(  "-------- Reading " +  it.getClass().getSimpleName() + " " +  it.getName().toString() + " " +it.getDescription());
               
                   for(int pos : ((IGXDLMSBase) it).getAttributeIndexToRead())
                {
                    try
                    {                        
                        Object val = com.readObject(it, pos);
                        if (val instanceof byte[])
                        {
                            val = GXCommon.toHex((byte[]) val);
                        }
                        else if (val instanceof Double)
                        {
                          /*  NumberFormat formatter = NumberFormat.getNumberInstance();
                            val = formatter.format(val);*/
                        }
                        else if (val != null && val.getClass().isArray())
                        {
                            String str = "";
                            for(int pos2 = 0; pos2 != Array.getLength(val); ++pos2)
                            {
                                if (!str.equals(""))
                                {
                                    str += ", ";
                                }
                                Object tmp = Array.get(val, pos2);
                                if (tmp instanceof byte[])
                                {
                                    str += GXCommon.toHex((byte[]) tmp);
                                }
                                else
                                {
                                    str += String.valueOf(tmp);
                                }
                            }
                            val = str;
                        }         
                      //TODO
//                        Utils.dirctLogcat(String.valueOf(val)); 
                        
                       /* if(it.getName().toString().trim().equals("0.0.96.1.0.255")){
                            
                            hashMap.put("meterNumber", HelperClass.hexStringToString(String.valueOf(val)).trim());
//                            Utils.dirctLogcat(HelperClass.hexStringToString(String.valueOf(val))+"  !!!!!!!!!!!!!!!!!!!!!!METER  NUMBER"); 
                        }*/
                        if(it.getName().toString().trim().equals("1.0.1.8.0.255")&& pos==2){
                             hashMap.put("meterReading", HelperClass.roundToKwh(String.valueOf(val)));
//                             Utils.dirctLogcat(String.valueOf(val)+"  !!!!!!!!!!!!!!!!!!!!!!METER  READING"); 
                            return hashMap;
                        }
                    }
                    catch(Exception ex)
                    {
                      Utils.dirctLogcat("Error! Index: " + pos + " " + ex.getMessage());
                        //Continue reading.
                    }
                }  
                
            }
            }            
  
            ///////////////////////////////////////////////////////////////////
            //Get data of profile generics.
         /*   Object[] cells;
            for(GXDLMSObject it : profileGenerics)
            {      
                
                //TODO
              Utils.dirctLogcat( "-------- Reading " + 
                        it.getClass().getSimpleName() + " " + 
                        it.getName().toString() + " " + 
                        it.getDescription());
                
                long entriesInUse = ((Number)com.readObject(it, 7)).longValue();
                long entries = ((Number)com.readObject(it, 8)).longValue();
              Utils.dirctLogcat( "Entries: " + String.valueOf(entriesInUse) + "/" + String.valueOf(entries));
                GXDLMSProfileGeneric pg = (GXDLMSProfileGeneric) it;
                //If there are no columns.
                if (entriesInUse == 0 || pg.getCaptureObjects().length == 0)
                {
                    continue;
                }
                ///////////////////////////////////////////////////////////////////
                //Read first item.                
                try
                {
                    cells = com.readRowsByEntry(pg, 1, 1);
                    for(Object rows : cells)
                    {
                        for(Object cell : (Object[]) rows)
                        {
                            if (cell instanceof byte[])
                            {
                               Utils.dirctLogcat( GXDLMSClient.toHex((byte[]) cell) + " | ");
                            }
                            else
                            {
                               Utils.dirctLogcat( cell + " | ");
                            }
                        }
                      Utils.dirctLogcat( "");
                    }
                }
                catch(Exception ex)
                {
                   Utils.dirctLogcat( "Error! Failed to read first row: " + ex.getMessage());                                      
                }                                    
                ///////////////////////////////////////////////////////////////////
                //Read last day.
                try
                {
                    java.util.Calendar start = java.util.Calendar.getInstance(java.util.TimeZone.getTimeZone("UTC"));
                    start.set(java.util.Calendar.HOUR_OF_DAY, 0); // set hour to midnight 
                    start.set(java.util.Calendar.MINUTE, 0); // set minute in hour 
                    start.set(java.util.Calendar.SECOND, 0); // set second in minute 
                    start.set(java.util.Calendar.MILLISECOND, 0); 
                    start.add(java.util.Calendar.DATE, -1);    
                    
                    java.util.Calendar end = java.util.Calendar.getInstance(java.util.TimeZone.getTimeZone("UTC"));                    
                    end.set(java.util.Calendar.MINUTE, 0); // set minute in hour 
                    end.set(java.util.Calendar.SECOND, 0); // set second in minute 
                    end.set(java.util.Calendar.MILLISECOND, 0); 

                    GXDLMSObject sorted = pg.getSortObject();
                    if (sorted == null)
                    {
                        sorted = pg.getCaptureObjects()[0];
                    }
                    cells = com.readRowsByRange(it, sorted, start.getTime(), end.getTime());
                    for(Object rows : cells)
                    {
                        for(Object cell : (Object[]) rows)
                        {
                            if (cell instanceof byte[])
                            {
                                System.out.print(GXDLMSClient.toHex((byte[]) cell) + " | ");
                            }
                            else
                            {
                               Utils.dirctLogcat( cell + " | ");
                            }
                        }
                      Utils.dirctLogcat( "");
                    }
                }
                catch(Exception ex)
                {
                  Utils.dirctLogcat( "Error! Failed to read last day: " + ex.getMessage());
                    //Continue reading if device returns access denied error.                                                
                } 
            }   */    
        }
        catch(Exception ex)
        {           
        	errorMessage=ex.getMessage();
        	Utils.dirctLogcat("ERROR 1"+ex.getMessage());
        	 if (com != null)
             {                    
                 try {
					com.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
             }
        }
        finally
        {
                  
            try
            {
                ///////////////////////////////////////////////////////////////
                //Disconnect.
                if (com != null)
                {                    
                    com.close();
                }
            }
            catch(Exception ex)
            {
            	 errorMessage=ex.getMessage();
            	Utils.dirctLogcat("ERROR 2"+ex.getMessage());
            }
        }
        Utils.dirctLogcat("DONE  !!!!!!!!!!!!!!!!!!!!!!METER  READING  "+hashMap); 
        return hashMap;
    }
         	private static String dateConverter(String dbDate) throws java.text.ParseException {// Convert DB date to
												// dd/MMM/yyyy format.

		String convertedDate;

		String[] date2 = dbDate.split(" ");
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy",Locale.getDefault());
		Date dates = null;
		try {
			dates = sdf.parse(date2[0]);
		} catch (ParseException e) {

			e.printStackTrace();

		}

		sdf = new SimpleDateFormat("dd-MM-yy");
		convertedDate = sdf.format(dates);

		return convertedDate;

	}
         	
         	public void readManufacturer() {
         		File dir = new File(Environment.getExternalStorageDirectory() + "");
                String path=dir+ "/Manufacturer_Settings";
         	      GXManufacturerCollection items = new GXManufacturerCollection();
                  GXManufacturerCollection.readManufacturerSettings(items, path,null);
                   man = items.findByIdentification("gil");
         	}
         	
}