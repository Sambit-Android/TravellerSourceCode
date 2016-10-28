package com.bcits.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bcits.entity.ComplaintEntity;
import com.bcits.service.ComplaintService;

@Repository
public class ComplaintsServiceImpl extends GenericServiceImpl<ComplaintEntity> implements ComplaintService
{

	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<ComplaintEntity> findAll() 
	{
		return entityManager.createNamedQuery("ComplaintEntity.findAll").getResultList();
	}

}
