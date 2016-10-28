package com.bcits.serviceImpl;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartFile;

import com.bcits.entity.CircleDataUpload;
import com.bcits.entity.CircleDetails;
import com.bcits.service.CircleDetailsService;
import com.bcits.utility.BCITSLogger;

@Repository
public class CircleDetailsServiceImpl extends GenericServiceImpl<CircleDetails> implements CircleDetailsService 
{

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public String circleDetailsUpload(CircleDataUpload circleDataUpload, ModelMap model,
			HttpServletRequest request) {
		
		
		
		
		MultipartFile multipartFile=circleDataUpload.getFile();
		String fileName="";
		if(multipartFile!=null)
        {
  			fileName = multipartFile.getOriginalFilename();
  			try 
  			{
  				InputStream stream = multipartFile.getInputStream();
  				//HSSFWorkbook workbook = new HSSFWorkbook(stream); 
  				HSSFWorkbook workbook = new HSSFWorkbook(stream); 
  				HSSFSheet sheet = workbook.getSheetAt(0); 
  				int noOfSheets=workbook.getNumberOfSheets();
  			    int notRead=0;
  				int alreadyUploaded=0;
  				int uploaded=0;
  				String SheetNameNotUpdate="";
  				String SheetNameAlreadyUpdated="";
  				int sheetUpdated=0;
  				String SheetName="";
  				int record=0;
  				int lastRows=0;
  				int lastColumn=0;
  				int  notInserted=0;
  				String cluster="",clientid="";
  				int len=0;
  				String notreadColumnsDetails ="";
  				
  				for(int i=0;i<noOfSheets;i++)
  				{
  						try 
  						{
  								SheetName=workbook.getSheetName(i);	  	
  								lastRows=workbook.getSheetAt(i).getLastRowNum();
  								
  								if(lastRows==0)
  								{
  									model.put("results", "Records are not avaliable in excel to upload");
  									return "null";
  								}
  								//String[] slno=new String[lastRows];
  								String[] sitecode=new String[lastRows];
  								String[] locationname=new String[lastRows];
  								String[] locationtype=new String[lastRows];
  								String[] company=new String[lastRows];
  								String[] zone=new String[lastRows];
  					
  								String[] circle=new String[lastRows];
  								
  							
  								
  								
  								//System.out.println("SDO coe=========="+circle[j1]);
  								
  								if(workbook.getSheetAt(i).getRow(0)!=null )
  								{
	  								lastColumn=workbook.getSheetAt(i).getRow(0).getLastCellNum();
	  								
	  								
	  								int cell_type1=0;
	  								String cellGotValue="";
	  								HSSFCell cell_1=null;	  										
	  										
	  								//int slnoCol=0;
	  								int sitecodeCol=0;
	  								int locationnameCol=0;
	  								int locationtypeCol=0;
	  								int circlCol=0;
	  								int companyCol=0;
	  								int zoneCol=0;
  								
  								
  								for (int j=0;j<=lastRows;j++)
  								{  
  									
  									if(j==0)// To get Column Names First row in Excel
  									{
  										for (int k=j;k<lastColumn;k++)
		  								{	  								         	
		  										//HSSFCell cellNull= workbook.getSheetAt(i).getRow(j).getCell((short)k);
  											HSSFCell cellNull= workbook.getSheetAt(i).getRow(j).getCell(k);
  											if(cellNull!=null)
	  										{
  												cellGotValue=cellNull.getStringCellValue();											
  												cellGotValue=cellGotValue.trim();
	  										}
		  										int check=0;									
		  										
		  									
		  										if(cellGotValue.equalsIgnoreCase("SDOCODE "))
		  										{
		  											sitecodeCol=k;	  											
		  											check++;
		  										}
		  										if(cellGotValue.equalsIgnoreCase("SDODESC"))
		  										{
		  											locationnameCol=k;	  											
		  											check++;
		  										}
		  										if(cellGotValue.equalsIgnoreCase("DIVISION"))
		  										{
		  											locationtypeCol=k;	  											
		  											check++;
		  										}
		  										if(cellGotValue.equalsIgnoreCase("DISCOM"))
		  										{
		  											companyCol=k;
		  											check++;
		  										}
		  										if(cellGotValue.equalsIgnoreCase("ZONE"))
		  										{
		  											zoneCol=k;
		  											check++;
		  										}
		  									
		  										if(cellGotValue.equalsIgnoreCase("CIRCLE"))
		  										{
		  											circlCol=k;	  											
		  											check++;
		  										}
		  										
		  							     }
  									}
  									else
  									{
  										
  									 for (int k=0;k<lastColumn;k++)
			  						 {
  										
  										    HSSFCell cellNull= workbook.getSheetAt(i).getRow(j).getCell(k);
													
			  									if(cellNull!=null)
			  										{	
			  										    
				  										if(cellNull.getCellType()==cellNull.CELL_TYPE_NUMERIC)
			  						                    {
			  						                        if (HSSFDateUtil.isCellDateFormatted(cellNull)) 
			  						                        {
			  						                            Date date=cellNull.getDateCellValue();
			  						                            //SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			  						                           SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
			  						                            cellGotValue = sdf.format(date);
			  						                            cellGotValue=cellGotValue.trim();
			  						                           // PayRollLogger.logger.info("DATA OD TYPE DATE----------------------------->"+cellGotValue);
			  						                        } 
			  						                        else
			  						                        {
			  						                        	cellGotValue=cellNull.getNumericCellValue()+"";
			  						                        	cellGotValue=cellGotValue.trim();
			  						                        	//BCITSLogger.logger.info("NUMERIC VALUES IN STRING---------------------------->"+cellGotValue);
			  						                        }
			  						                   }
			  											else 
			  						                    {
			  												cellGotValue=cellNull.getStringCellValue();
			  												if(cellGotValue.contains("'"))
			  					                            {
			  													cellGotValue=cellGotValue.replaceAll("'","''");	  
			  													//PayRollLogger.logger.info("CELL GOT VALUE==============="+cellGotValue);
			  					                            }
			  												cellGotValue=cellGotValue.trim();	  
			  												//PayRollLogger.logger.info("TEXT VALUES---------------------------->"+cellGotValue);
			  											}
			  										}
			  									else
			  									{
			  										cellGotValue="0.0";
			  									}
			  											

				  								  if(k==sitecodeCol)
			  										{
				  									 sitecode[j-1]=cellGotValue.trim();
				  									
			  										}									
			  										 if(k==locationnameCol)
			  										{
			  											locationname[j-1]=cellGotValue.trim();
			  										}
			  										else if(k==locationtypeCol)
			  										{
			  											locationtype[j-1]=cellGotValue.trim();
			  										}
			  										else if(k==circlCol)
			  										{
			  											circle[j-1]=cellGotValue.trim();
			  										}
			  										else if(k==companyCol)
			  										{
			  											company[j-1]=cellGotValue.trim();
			  										}
			  										else if(k==zoneCol)
			  										{
			  											zone[j-1]=cellGotValue.trim();
			  										}
			  										else
			  										{
			  											notreadColumnsDetails="COLUMNS NOT READ AT"+k;
			  										}
				  													
			  						 }
		  									
  									  
									 //CREATE NEW OBJECT HERE AND SET THE VALUES
  									CircleDetails circleDetailsEntity=new CircleDetails();
  									
  									
  									if(company[j-1]== null)
 									 {
 										model.put("results", "SDOCode is missing,"+"\t"+"see row No."+"  "+(j+1));
 										return "showCircleDetails";
 									 }
 									
 									else
 									{
 										int sdoCode=(int)Double.parseDouble(sitecode[j-1]);
 										circleDetailsEntity.setSitecode(sdoCode+"");
 									}
 										
 									 
  									if(locationname[j-1].equals("0.0"))
 									 {
 										model.put("results", "SDODesc is missing,"+"\t"+"see row No."+"  "+(j+1));
 										return "showCircleDetails";
 									 }
 									 else
 									 {
 										
 										circleDetailsEntity.setLocationname(locationname[j-1]);
 									 }
  									
  									 
  									if(locationtype[j-1].equals("0.0"))
 									 {
 										model.put("results", "Division is missing,"+"\t"+"see row No."+"  "+(j+1));
 										return "showCircleDetails";
 									 }
 									 else
 									 {
 										circleDetailsEntity.setLocationtype(locationtype[j-1]);;
 									 }
  									
  									 
  									if(circle[j-1].equals("0.0"))
 									 {
 										model.put("results", "Circle is missing,"+"\t"+"see row No."+"  "+(j+1));
 										return "showCircleDetails";
 									 }
 									 else
 									 {
 										circleDetailsEntity.setCircle(circle[j-1]);
 									 }
  									
  									if(company[j-1].equals("0.0"))
									 {
										model.put("results", "Discom is missing,"+"\t"+"see row No."+"  "+(j+1));
										return "showCircleDetails";
									 }
									 else
									 {
										circleDetailsEntity.setCompany(company[j-1]);
									 }
  									
  									if(zone[j-1].equals("0.0"))
									 {
										model.put("results", "Discom is missing,"+"\t"+"see row No."+"  "+(j+1));
										return "showCircleDetails";
									 }
									 else
									 {
										circleDetailsEntity.setZone(zone[j-1]);
									 }
  									
  										//String loggedUser=(String) request.getSession().getAttribute("username");
  										//holidayEntity.setLoggedUser(loggedUser);
  										//Date date= new Date();
  										//holidayEntity.setLoggedDateStamp(new Timestamp(date.getTime()));
  										save(circleDetailsEntity);
  										//getHolidayData(model);
										model.put("results", "Circle details uploaded successfully.");
  									 
  									 
  							}

  							//BCITSLogger.logger.info("UPLOADED::::::::::::::::::"+"   "+uploaded++);
  								}
  						}
  					}
  						
  						catch (Exception e) 
  						{
  							if(lastRows!=0)
  							{
  								model.put("results", "Error while uploading the file");
  								notRead++;
  								SheetNameNotUpdate=SheetNameNotUpdate+" "+SheetName;	
  							}
  								e.printStackTrace();
  							continue;
  						}
  						
  			} 
  		}
  			catch (IOException e) 
  			{
  				e.printStackTrace();
  			}
  			catch (Exception e) 
  			{
  				e.printStackTrace();
  			} 
        }
		return "";
	}

}
