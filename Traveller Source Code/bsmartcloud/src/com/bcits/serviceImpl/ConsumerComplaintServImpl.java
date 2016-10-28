package com.bcits.serviceImpl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

import com.bcits.entity.ComplaintEntity;
import com.bcits.entity.ConsumerComplaintEntity;
import com.bcits.service.ConsumerComplaintService;

@Repository
public class ConsumerComplaintServImpl extends GenericServiceImpl<ConsumerComplaintEntity> implements ConsumerComplaintService 
{

	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public void getConsComplData(ModelMap model,HttpServletRequest request)
	{
		List<ConsumerComplaintEntity> complaintList=findAll();
		model.put("complaintList", complaintList);
		List<String> statusList=getCheckConstraints("CONSUMER_COMPLAINTS", "STATUS_CHECK",request);
		model.put("statusList", statusList);
	}
	
	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<ConsumerComplaintEntity> findAll() 
	{
		return entityManager.createNamedQuery("ConsumerComplaintEntity.findAll").getResultList();
	}
	
	
}
