package com.bcits.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bcits.entity.NotificationsEntity;
import com.bcits.service.NotificationsService;

@Repository
public class NotificationsServiceImpl extends GenericServiceImpl<NotificationsEntity> implements NotificationsService
{

	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<NotificationsEntity> findAll() 
	{
		return entityManager.createNamedQuery("NotificationsEntity.findAll").getResultList();
	}
	
}
