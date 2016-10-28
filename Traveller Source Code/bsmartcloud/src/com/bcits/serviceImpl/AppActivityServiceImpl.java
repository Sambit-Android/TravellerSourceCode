package com.bcits.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bcits.entity.AppActivityEntity;
import com.bcits.service.AppActivityService;

@Repository
public class AppActivityServiceImpl extends GenericServiceImpl<AppActivityEntity> implements AppActivityService
{

	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<AppActivityEntity> findAll() 
	{
		return entityManager.createNamedQuery("AppActivityEntity.findAll").getResultList();	
	}

}
