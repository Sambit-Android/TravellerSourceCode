package com.bcits.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bcits.entity.AllowedDeviceVersionsEntity;
import com.bcits.service.AllowedDeviceVersionService;
@Repository
public class AllowedDeviceVersionServiceImpl extends GenericServiceImpl<AllowedDeviceVersionsEntity> implements AllowedDeviceVersionService
{

	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<AllowedDeviceVersionsEntity> findAll() 
	{
		return entityManager.createNamedQuery("AllowedDeviceVersionsEntity.findAll").getResultList();	
	}

	@Override
	public Integer updateAllowedFlagStatus(Integer id, String status) {
		return entityManager
				.createNamedQuery(
						"AllowedDeviceVersionsEntity.updateAllowedStatus")
				.setParameter("id", id).setParameter("status", status)
				.executeUpdate();
	}

	@Override
	public List<AllowedDeviceVersionsEntity> findAllVersionsToAllow() {
		return entityManager.createNamedQuery(
				"AllowedDeviceVersionsEntity.findAllVersionsToAllow",
				AllowedDeviceVersionsEntity.class).getResultList();
	}

}
