package com.bcits.serviceImpl;

import java.util.List;

import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

import com.bcits.entity.VigilanceComplaintEntity;
import com.bcits.service.VigilanceComplaintService;
import com.bcits.utility.BCITSLogger;

@Repository
public class VigilanceComplaintServImpl extends GenericServiceImpl<VigilanceComplaintEntity> implements VigilanceComplaintService 
{

	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public void getConsComplData(ModelMap model,HttpServletRequest request)
	{
		List<VigilanceComplaintEntity> complaintList=findAll();
		model.put("complaintList", complaintList);
		List<String> statusList=getCheckConstraints("vigilancecomplaints", "STATUS_CHECK",request);
		model.put("statusList", statusList);
	}
	
	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<VigilanceComplaintEntity> findAll() 
	{
		return entityManager.createNamedQuery("VigilanceComplaintEntity.findAll").getResultList();
	}
	
	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<VigilanceComplaintEntity> findByUser(String username)
	{
		return entityManager.createNamedQuery("VigilanceComplaintEntity.findByUser").setParameter("username", username).getResultList();
	}
	
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public int updateStatus(int id, String status) 
	{
		Query query=entityManager.createNamedQuery("VigilanceComplaintEntity.updateStatus").setParameter("id", id).setParameter("status", status);
		int updateData=query.executeUpdate();
		BCITSLogger.logger.info("No. of records updated is"+"\t"+updateData);
		return updateData;
	}
}
