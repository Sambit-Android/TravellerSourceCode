package com.bcits.serviceImpl;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.bcits.entity.MRMasterComPK;
import com.bcits.entity.MRMasterEntity;
import com.bcits.entity.MrGroup;
import com.bcits.entity.VigilanceTheftEntity;
import com.bcits.service.MRMasterService;
import com.bcits.service.MrGroupSerive;
import com.bcits.service.SiteLocationService;
import com.bcits.service.UserService;
import com.bcits.utility.BCITSLogger;

@Repository
public class MRMasterServiceImpl extends GenericServiceImpl<MRMasterEntity>	implements	MRMasterService {
	
	@Autowired
	private SiteLocationService siteLocationService;

	@Autowired
	private MrGroupSerive mrGroupSerive;
	
	@Autowired
	private UserService userService;
	
	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<MRMasterEntity> findAllMRMasters() {
		BCITSLogger.logger.info("fetching is started");
		return entityManager.createNamedQuery("MRMasterEntity.findAllMRMasters").getResultList();

	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public MRMasterEntity findMRMaster(String mrCode) {
		
		try {
			
			return (MRMasterEntity) entityManager.createNamedQuery("MRMasterEntity.findMRMaster").setParameter("mrCode", mrCode).getSingleResult();					
			
		} catch (Exception e) {	
			e.printStackTrace();
		}
		return null;

	}

	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public byte[] getPhoto(ModelMap model,HttpServletRequest request,HttpServletResponse response,String mrCode) throws IOException 
	{		
		 response.setContentType("image/jpeg/png");
		/* MRMasterComPK pKey=new MRMasterComPK();
		 pKey.setMrCode(mrCode);
		 pKey.setSdoCode(sdoCode);
		 MRMasterEntity data=find(pKey); */
		 
		 MRMasterEntity data=find(mrCode);
		 if(data.getImage()!=null)
		 {
			 byte photo[] = data.getImage();    	
	    	 OutputStream ot = null;
	    	 ot = response.getOutputStream();
	         ot.write(photo);
	         ot.close();    	
	         Base64 b=new Base64();
	         b.encodeBase64(photo);
			 return b.encodeBase64(photo);
		 }
    	 else {
			return null;
		}
			    	
    }
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Integer updateMRMaster(MRMasterEntity mrMasterEntity) {
		
		/*MRMasterComPK pKey=new MRMasterComPK();
		pKey.setMrCode(mrMasterEntity.getMrCode());
		pKey.setSdoCode(mrMasterEntity.getSdoCode());
		MRMasterEntity oldData= find(pKey);*/
		MRMasterEntity oldData= find(mrMasterEntity.getMrCode());
		mrMasterEntity.setAllocationFlag(oldData.getAllocationFlag());
		if(mrMasterEntity.getImage()==null)
		{
			mrMasterEntity.setImage(oldData.getImage());
		}
		 update(mrMasterEntity);
		 return 0;
		
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<String> getMatchedMRCodes() {

		return entityManager.createNamedQuery("MRMasterEntity.findMatchedMRCodes").getResultList();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Integer deleteMRMaster(String mrCode) {

		return entityManager.createNamedQuery("MRMasterEntity.removeMRMaster").setParameter("mrCode", mrCode).executeUpdate();

	}
	
	public String validateForMrMaster(String mrCode,String password) {
		
		String status = "0";

		List<MRMasterEntity> list =  entityManager.createNamedQuery("MRMasterEntity.validateForMrMaster")
				.setParameter("mrCode", mrCode)
				.setParameter("password", password).getResultList();
				if(list.size() > 0)
				{
					status = "1";
				}
				return status;

	}

	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<MRMasterEntity> findMobileUser(String userName,String password) 
	{
		return entityManager.createNamedQuery("MRMasterEntity.findMobileUser").setParameter("USERNAME", userName).setParameter("PASSWORD", password).getResultList();
	}

	@Override
	public List<String> getMatchedMRCodesForAllocation() {

		return entityManager.createNamedQuery("MRMasterEntity.getMatchedMRCodesForAllocation").getResultList();
	}
	
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void updateMRMasterData(MRMasterEntity mrMasters,HttpServletRequest request,Date current_date,ModelMap model,String groups,String operation)
	{
		
		// for group
		try {
			
		
		
		if (operation.equals("add") && find(mrMasters.getMrCode())!=null)
		{
			System.out.println("yes coming");
			model.put("results", "Entered Device operator code already exist");
		}
		else {
		// end group
		
			mrMasters.setUsername((String) request.getSession().getAttribute("username"));
			mrMasters.setTimestamp(new Timestamp(current_date.getTime()));

			//Added by manjunath
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			MultipartFile multipartFile = multipartRequest.getFile("image1");
			 try {
				 if(multipartFile.getSize()>0)
				 {
					 byte[] image = IOUtils.toByteArray(multipartFile.getInputStream());				
						mrMasters.setImage(image);	
				}			
			} 
			 catch (IOException e)
			{				
				e.printStackTrace();
			}
			//end by manjunath
			 
			//continue for group
			 
			 //List<MrGroup> grpList=new ArrayList<MrGroup>();		
			 //String[] str=groups.split(",");	
			  
			 
				 //delete if there are records related to mrcode and sdocode
				  /*int res= entityManager.createNamedQuery("MrGroup.DeleteData").setParameter("mrcode", mrMasters.getMrCode()).executeUpdate();
				    
					 int id=0;
				 	 try
					 {
						 id=(int)entityManager.createNamedQuery("MrGroup.MaxId").getSingleResult();
					 } 
					 catch (Exception e) 
					 {
						e.printStackTrace();
					 }
				 
				 for (int i = 0; i < str.length; i++)
				 {
					 MrGroup mrGrp= new MrGroup();			
				     mrGrp.setId(id+(i+1));	
					 mrGrp.setMrcode(mrMasters.getMrCode());			
					 mrGrp.setGrpName(str[i]);			
					 grpList.add(mrGrp);
				}
				 
				 mrMasters.setMrgroups(grpList);*/
			 
			 //end group
			
			 
			if (operation.equals("add")) 
			{
				//to set defalut Image
				if(multipartFile.getSize()==0)
				{
				byte[] bFile = 	userService.defaultImage(request);
				mrMasters.setImage(bFile);			
				}
				//End default image
				mrMasters.setPassword("1234");//hardcode PWD
				save(mrMasters);
				model.put("results", "Device Operaor Added Successfully");
			} 
			if(operation.equals("update"))
			{
				updateMRMaster(mrMasters);			
				model.put("results", "Device Operaor Updated Successfully");
			}
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//model.put("activeModules",	moduleMasterService.findAllActivatedModuleMasters());
		model.put("mrmList", findAllMRMasters());
		model.put("sdoCodes", siteLocationService.getAllSiteCodes());
		
		model.put("mrMasters", new MRMasterEntity());
	}
	
	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<MRMasterEntity> getNotAllocatedOperators()
	{
			return entityManager.createNamedQuery("MRMasterEntity.GetNotAllocatedOperators").getResultList();
		
	}
}
