package com.bcits.serviceImpl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

import com.bcits.entity.DefectiveMeterEntity;
import com.bcits.entity.MeterChangeEntity;
import com.bcits.service.MeterChangeService;
import com.bcits.utility.BCITSLogger;
import com.bcits.utility.Resultupdated;


@Repository
public class MeterChangeServiceImpl extends GenericServiceImpl<MeterChangeEntity> implements MeterChangeService  {

	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<MeterChangeEntity> getMobileBillingData(String listno, HttpServletRequest request) 
	{
		List<MeterChangeEntity> data = entityManager.createNamedQuery("MeterChangeEntity.findallbylist").setParameter("lcno",listno).getResultList();

		if(data.size()>0){

			System.out.println(">>>>>>>>>>>>>>"+data);
			return data;

		}
		else{
			return null;
		}
	}


	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public String getListNoValidationResult(String listno, HttpServletRequest request) 
	{
		String listnovalidation = "LISTISNOTVALID";
		String downloadavailability = "DOWNLOADNOTAVAILABLE";
		Long a=  (Long) entityManager.createNamedQuery("MeterChangeEntity.findcount").setParameter("lcno",listno).getSingleResult();

		if(a> 0){

			listnovalidation = "LISTISVALID";

		}


		Long b = (Long) entityManager.createNamedQuery("MeterChangeEntity.findavailabledownloads").setParameter("lcno",listno).getSingleResult();


		if(b>0){

			downloadavailability = "DOWNLOADAVAILABLE";
		}

		System.out.println("b :"+b);

		String result = listnovalidation + "#"+  downloadavailability;

		return result;

	}




	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public ArrayList<Resultupdated> updateMobileDataToMeterChangeTable(HttpServletRequest request, JSONArray array) {

		Resultupdated res;
		ArrayList<Resultupdated> list = new ArrayList<Resultupdated>();

		try {

			MeterChangeEntity meterChangeEntity ;
			DefectiveMeterEntity defectivemeterentity;

			
			
			

			for (int i = 0; i < array.length(); i++)
			{

				res = new Resultupdated();
				meterChangeEntity = new MeterChangeEntity();
				defectivemeterentity = new DefectiveMeterEntity();

				int updateResult = 0;


				try{

					JSONObject json =array.getJSONObject(i);

					/*meterChangeEntity=find(Integer.parseInt(json.getString("ID")));*/



					if (json.getString("RRNO") == null
							|| json.getString("RRNO") == ""
							|| json.isNull("RRNO")) {
						json.put("RRNO", "-");
						meterChangeEntity.setRrno(json.getString("RRNO"));
					} else {
						meterChangeEntity.setRrno(json.getString("RRNO"));
					}

					
					
					if (json.getString("LISTNO") == null
							|| json.getString("LISTNO") == ""
							|| json.isNull("LISTNO")) {
						json.put("LISTNO", "-");
						meterChangeEntity.setLcno(json.getString("LISTNO"));
					} else {
						meterChangeEntity.setLcno(json.getString("LISTNO"));
					}
					
					
					
					if (json.getString("CONSUMER_NAME") == null
							|| json.getString("CONSUMER_NAME") == ""
							|| json.isNull("CONSUMER_NAME")) {
						json.put("CONSUMER_NAME", "-");
						meterChangeEntity.setUsername(json.getString("CONSUMER_NAME"));
					} else {
						meterChangeEntity.setUsername(json.getString("CONSUMER_NAME"));
					}
					
					
					if (json.getString("ADDRESS") == null
							|| json.getString("ADDRESS") == ""
							|| json.isNull("ADDRESS")) {
						json.put("ADDRESS", "-");
						meterChangeEntity.setAddress(json.getString("ADDRESS"));
					} else {
						meterChangeEntity.setAddress(json.getString("ADDRESS"));
					}
					
					
					if (json.getString("FDRCODE") == null
							|| json.getString("FDRCODE") == ""
							|| json.isNull("FDRCODE")) {
						json.put("FDRCODE", "-");
						meterChangeEntity.setFdrcode(json.getString("FDRCODE"));
					} else {
						meterChangeEntity.setFdrcode(json.getString("FDRCODE"));
					}
					
					
					if (json.getString("POLENO") == null
							|| json.getString("POLENO") == ""
							|| json.isNull("POLENO")) {
						json.put("POLENO", "-");
						meterChangeEntity.setPoleno(json.getString("POLENO"));
					} else {
						meterChangeEntity.setPoleno(json.getString("POLENO"));
					}
					
					
					if (json.getString("TCCODE") == null
							|| json.getString("TCCODE") == ""
							|| json.isNull("TCCODE")) {
						json.put("TCCODE", "-");
						meterChangeEntity.setTccode(json.getString("TCCODE"));
					} else {
						meterChangeEntity.setTccode(json.getString("TCCODE"));
					}
					
					
					

					if (json.getString("OMDATEOFRELEASE") == null
								|| json.getString("OMDATEOFRELEASE") == ""
								|| json.isNull("OMDATEOFRELEASE")) {

							json.put("OMDATEOFRELEASE", "00-00-0000");
							meterChangeEntity.setReleasedate(getDateFormat(json.getString("OMDATEOFRELEASE")));

						} else {

							
							System.out.println(getDateFormat(json.getString("OMDATEOFRELEASE")));
							
							meterChangeEntity.setReleasedate(getDateFormat(json.getString("OMDATEOFRELEASE")));
						}

					 

					if (json.getString("OMSERIALNO") == null
							|| json.getString("OMSERIALNO") == ""
							|| json.isNull("OMSERIALNO")) {

						json.put("OMSERIALNO", "-");
						meterChangeEntity.setOlderserialno(json.getString("OMSERIALNO"));

					} else {

						meterChangeEntity.setOlderserialno(json.getString("OMSERIALNO"));
					}





					if (json.getString("OMMAKE") == null
							|| json.getString("OMMAKE") == ""
							|| json.isNull("OMMAKE")) {

						json.put("OMMAKE", "-");
						meterChangeEntity.setOldmake(json.getString("OMMAKE"));

					} else {

						meterChangeEntity.setOldmake(json.getString("OMMAKE"));
					}


					if (json.getString("OMCAPACITY") == null
							|| json.getString("OMCAPACITY") == ""
							|| json.isNull("OMCAPACITY")) {

						json.put("OMCAPACITY", "-");
						meterChangeEntity.setOldcapacity(json.getString("OMCAPACITY"));

					} else {

						meterChangeEntity.setOldcapacity(json.getString("OMCAPACITY"));
					}


					if (json.getString("OMCOVER") == null
							|| json.getString("OMCOVER") == ""
							|| json.isNull("OMCOVER")) {

						json.put("OMCOVER", "Having Cover");

						if(json.getString("OMCOVER").equals("Having Cover")){


							meterChangeEntity.setOldcover(0);
						}
						else{
							meterChangeEntity.setOldcover(1);
						}



					} else {

						if(json.getString("OMCOVER").equals("Having Cover")){
							meterChangeEntity.setOldcover(0);
						}
						else{
							meterChangeEntity.setOldcover(1);
						}

					}



					if (json.getString("OMPOSITION") == null
							|| json.getString("OMPOSITION") == ""
							|| json.isNull("OMPOSITION")) {

						json.put("OMPOSITION", "Inside Premisis");

						if(json.getString("OMPOSITION").equals("Inside Premisis")){

							meterChangeEntity.setOldposition(0);
						}else{
							meterChangeEntity.setOldposition(1);
						}



					} else {

						if(json.getString("OMPOSITION").equals("Inside Premisis")){
							meterChangeEntity.setOldposition(0);
						}else{
							meterChangeEntity.setOldposition(1);
						}

					}


					if (json.getString("OMTYPE") == null
							|| json.getString("OMTYPE") == ""
							|| json.isNull("OMTYPE")) {

						json.put("OMTYPE", "STATIC");
						if(json.getString("OMTYPE").equals("STATIC"))
						{
							meterChangeEntity.setOldtype(0);
						}
						else if(json.getString("OMTYPE").equals("Electro Machanical")){
							meterChangeEntity.setOldtype(1);
						}
						else if(json.getString("OMTYPE").equals("High Precision")){
							meterChangeEntity.setOldtype(2);
						}
						else{
							meterChangeEntity.setOldtype(0);
						}

					} else {

						if(json.getString("OMTYPE").equals("STATIC"))
						{
							meterChangeEntity.setOldtype(0);
						}
						else if(json.getString("OMTYPE").equals("Electro Machanical")){
							meterChangeEntity.setOldtype(1);
						}
						else if(json.getString("OMTYPE").equals("High Precision")){
							meterChangeEntity.setOldtype(2);
						}
						else{
							meterChangeEntity.setOldtype(0);
						}
					}



					if (json.getString("OMFR") == null
							|| json.getString("OMFR") == ""
							|| json.isNull("OMFR")) {

						json.put("OMFR", "0");
						meterChangeEntity.setFinalreading(Integer.parseInt(json.getString("OMFR")));

					} else {

						meterChangeEntity.setFinalreading(Integer.parseInt(json.getString("OMFR")));
					}


					if (json.getString("NMDATEOFINSTALL") == null
								|| json.getString("NMDATEOFINSTALL") == ""
								|| json.isNull("NMDATEOFINSTALL")) {

							json.put("NMDATEOFINSTALL", "00-00-0000");
							meterChangeEntity.setGivendate(getDateFormat(json.getString("NMDATEOFINSTALL")));

						} else {

							meterChangeEntity.setGivendate(getDateFormat(json.getString("NMDATEOFINSTALL")));
						}



					if (json.getString("NMSERIALNO") == null
							|| json.getString("NMSERIALNO") == ""
							|| json.isNull("NMSERIALNO")) {

						json.put("NMSERIALNO", "-");
						meterChangeEntity.setNewserialno(json.getString("NMSERIALNO"));

					} else {

						meterChangeEntity.setNewserialno(json.getString("NMSERIALNO"));
					}



					if (json.getString("NMMAKE") == null
							|| json.getString("NMMAKE") == ""
							|| json.isNull("NMMAKE")) {

						json.put("NMMAKE", "-");
						meterChangeEntity.setNewmake(json.getString("NMMAKE"));

					} else {

						meterChangeEntity.setNewmake(json.getString("NMMAKE"));
					}


					if (json.getString("NMCAPACITY") == null
							|| json.getString("NMCAPACITY") == ""
							|| json.isNull("NMCAPACITY")) {

						json.put("NMCAPACITY", "-");
						meterChangeEntity.setNewcapacity(json.getString("NMCAPACITY"));

					} else {

						meterChangeEntity.setNewcapacity(json.getString("NMCAPACITY"));
					}



					if (json.getString("NMCOVER") == null
							|| json.getString("NMCOVER") == ""
							|| json.isNull("NMCOVER")) {

						json.put("NMCOVER", "Having Cover");

						if(json.getString("NMCOVER").equals("Having Cover")){

							meterChangeEntity.setCover(0);
						}else{
							meterChangeEntity.setCover(1);
						}



					} else {

						if(json.getString("NMCOVER").equals("Having Cover")){
							meterChangeEntity.setCover(0);
						}else{
							meterChangeEntity.setCover(1);
						}
					}


					if (json.getString("NMPOSITION") == null
							|| json.getString("NMPOSITION") == ""
							|| json.isNull("NMPOSITION")) {

						json.put("NMPOSITION", "Inside Premisis");

						if(json.getString("NMPOSITION").equals("Inside Premisis"))
						{
							meterChangeEntity.setPosition(0);
						}else{
							meterChangeEntity.setPosition(1);
						}



					} else {

						if(json.getString("NMPOSITION").equals("Inside Premisis"))
						{
							meterChangeEntity.setPosition(0);
						}else{
							meterChangeEntity.setPosition(1);
						}
					}


					if (json.getString("NMTYPE") == null
							|| json.getString("NMTYPE") == ""
							|| json.isNull("NMTYPE")) {

						json.put("NMTYPE", "STATIC");

						if(json.getString("NMTYPE").equals("STATIC"))
						{
							meterChangeEntity.setType(0);
						}
						else if(json.getString("NMTYPE").equals("Electro Machanical")){
							meterChangeEntity.setType(1);
						}
						else if(json.getString("NMTYPE").equals("High Precision")){
							meterChangeEntity.setType(2);
						}
						else{
							meterChangeEntity.setType(0);
						}


					} else {

						if(json.getString("NMTYPE").equals("STATIC"))
						{
							meterChangeEntity.setType(0);
						}
						else if(json.getString("NMTYPE").equals("Electro Machanical")){
							meterChangeEntity.setType(1);
						}
						else if(json.getString("NMTYPE").equals("High Precision")){
							meterChangeEntity.setType(2);
						}
						else{
							meterChangeEntity.setType(0);
						}
					}


					if (json.getString("NMIR") == null
							|| json.getString("NMIR") == ""
							|| json.isNull("NMIR")) {

						json.put("NMIR", "0");
						meterChangeEntity.setInitialreading(Integer.parseInt(json.getString("NMIR")));

					} else {

						meterChangeEntity.setInitialreading(Integer.parseInt(json.getString("NMIR")));
					}


					if (json.getString("METERREPLACEDATE") == null
							|| json.getString("METERREPLACEDATE") == ""
							|| json.isNull("METERREPLACEDATE")) {

						json.put("METERREPLACEDATE", "-");
						meterChangeEntity.setMcdate(json.getString("METERREPLACEDATE"));

					} else {

						meterChangeEntity.setMcdate(json.getString("METERREPLACEDATE"));
					}



					meterChangeEntity.setDatestamp(new Date());


					if (json.getString("OMLATTITUDE") == null
							|| json.getString("OMLATTITUDE") == ""
							|| json.isNull("OMLATTITUDE")) {

						json.put("OMLATTITUDE", "-");
						meterChangeEntity.setOmlattitude(json.getString("OMLATTITUDE"));

					} else {

						meterChangeEntity.setOmlattitude(json.getString("OMLATTITUDE"));
					}


					if (json.getString("OMLONGITUDE") == null
							|| json.getString("OMLONGITUDE") == ""
							|| json.isNull("OMLONGITUDE")) {

						json.put("OMLONGITUDE", "-");
						meterChangeEntity.setOmlongitude(json.getString("OMLONGITUDE"));

					} else {

						meterChangeEntity.setOmlongitude(json.getString("OMLONGITUDE"));
					}




					if (json.getString("NMLATTITUDE") == null
							|| json.getString("NMLATTITUDE") == ""
							|| json.isNull("NMLATTITUDE")) {

						json.put("NMLATTITUDE", "-");
						meterChangeEntity.setNmlattitude(json.getString("NMLATTITUDE"));

					} else {

						meterChangeEntity.setNmlattitude(json.getString("NMLATTITUDE"));
					}




					if (json.getString("NMLONGITUDE") == null
							|| json.getString("NMLONGITUDE") == ""
							|| json.isNull("NMLONGITUDE")) {

						json.put("NMLONGITUDE", "-");
						meterChangeEntity.setNmlongitude(json.getString("NMLONGITUDE"));

					} else {

						meterChangeEntity.setNmlongitude(json.getString("NMLONGITUDE"));
					}


					if (json.getString("OMIMAGE") == null
							|| json.getString("OMIMAGE") == ""
							|| json.isNull("OMIMAGE")) {

						json.put("OMIMAGE", "-");
						byte[] image1 =Base64.decodeBase64(json.getString("OMIMAGE"));

						meterChangeEntity.setOmimage(image1);

					} else {

						byte[] image1 =Base64.decodeBase64(json.getString("OMIMAGE"));

						meterChangeEntity.setOmimage(image1);
					}


					if (json.getString("NMIMAGE") == null
							|| json.getString("NMIMAGE") == ""
							|| json.isNull("NMIMAGE")) {

						json.put("NMIMAGE", "-");
						byte[] image2 =Base64.decodeBase64(json.getString("NMIMAGE"));

						meterChangeEntity.setNmimage(image2);

					} else {

						byte[] image2 =Base64.decodeBase64(json.getString("NMIMAGE"));

						meterChangeEntity.setNmimage(image2);
					}


					
					if (json.getString("DEVICEID") == null
							|| json.getString("DEVICEID") == ""
							|| json.isNull("DEVICEID")) {

						json.put("DEVICEID", "-");
						long deviceid = Long.parseLong(json.getString("DEVICEID"));

						meterChangeEntity.setUpdated(deviceid);

					} else {

						long deviceid = Long.parseLong(json.getString("DEVICEID"));

						meterChangeEntity.setUpdated(deviceid);

					}
					
					
					
					
					if (json.getString("SOCODE") == null
							|| json.getString("SOCODE") == ""
							|| json.isNull("SOCODE")) {

						json.put("SOCODE", "-");
						String sOCODE= json.getString("SOCODE");

						meterChangeEntity.setSdocode(sOCODE);

					} else {

						String sOCODE= json.getString("SOCODE");
						meterChangeEntity.setSdocode(sOCODE);

					}
					
					
					


					
					
					save(meterChangeEntity);

					
					
					
/*
					int updated = 	entityManager.createNamedQuery("DefectiveMeterEntity.updatestatus")
							.setParameter("rrno", meterChangeEntity.getRrno()).executeUpdate();*/
					
					
					
					int updated = 1;
					
					/*MeterChangeEntity a=update(meterChangeEntity);*/



					/*BCITSLogger.logger.info("updated status is" + "\t"
							+ a);
					System.out.println("aaaaa :"+a);
*/

					
					
					
					if(updated >0){
						res.status="UPDATED";
						res.rrno = meterChangeEntity.getRrno();
					}
					else{
						
						res.status="NOTUPDATED";
						res.rrno = meterChangeEntity.getRrno();
						
					}
					
				





				}					
				catch(Exception e){
					e.printStackTrace();

					res.status="NOTUPDATED";
					res.rrno = meterChangeEntity.getRrno();

				}


				list.add(res);

			} 






		}catch (Exception e) {
			e.printStackTrace();
		}





		return list;

	}


	
	
        public Date getDateFormat(String dateString){
		
        	DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		/*String dateInString = "07/06/2013";*/
		Date date = null;
		try {
	 
			date =formatter.parse(dateString);
			System.out.println(date);
			/*System.out.println(formatter.format(date));*/
	 
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		
		
		return date;
	}
	
        @Override
		@Transactional(propagation=Propagation.SUPPORTS)
        public List<Object[]> showMeterReplacementOnMap(String divSdo, String date,ModelMap model)
		{
			
			List list=null;
			try 
			{
				Date d=new SimpleDateFormat("dd-MM-yyyy").parse(date);
				String cuurentDate=new SimpleDateFormat("yyyy-MM-dd").format(d);
				String sql="SELECT AA.*,(SELECT section FROM vcloudengine.location WHERE cast(sitecode as VARCHAR) LIKE lococde)FROM "+" "
+"(SELECT pay.rrno,pay.sdocode as lococde, hhc.consumer_name, hhd.lattitude, hhd.longitude , cast(pay.releasedate as VARCHAR(11)),pay.olderserialno, pay.newserialno,pay.oldmake,"+" "
+"pay.newmake, pay.oldcapacity,pay.newcapacity,pay.oldcover,pay.cover,pay.oldposition, pay.position, pay.type, pay.oldtype"+" "
+"FROM mvs.meterchangeoutput pay, photobilling.hhbm_consumers hhc , photobilling.hhbm_download hhd "+" "
+"WHERE "+" "   
+"hhc.consumer_sc_no=hhd.consumer_sc_no AND hhc.sdo_code=hhd.sdo_code AND hhc.bill_month=hhd.bill_month AND pay.rrno=hhc.consumer_sc_no AND pay.sdocode=hhc.sdo_code  and substr(cast(pay.datestamp as VARCHAR),0,11) like '"+cuurentDate+"')AA";
				list=entityManager.createNativeQuery(sql).getResultList();
			} 
			catch (Exception e)
			{
				e.printStackTrace();
			}
			return list;
		}
		

}
