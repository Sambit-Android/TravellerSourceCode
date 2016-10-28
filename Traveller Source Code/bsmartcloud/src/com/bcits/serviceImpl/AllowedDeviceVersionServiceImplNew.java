package com.bcits.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bcits.entity.AllowedDeviceVersionsEntity;
import com.bcits.entity.AllowedDeviceVersionsEntityNew;
import com.bcits.service.AllowedDeviceVersionService;
import com.bcits.service.AllowedDeviceVersionServiceNew;
@Repository
public class AllowedDeviceVersionServiceImplNew extends GenericServiceImpl<AllowedDeviceVersionsEntityNew> implements AllowedDeviceVersionServiceNew
{

	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<AllowedDeviceVersionsEntityNew> findAll() 
	{
		return entityManager.createNamedQuery("AllowedDeviceVersionsEntityNew.findAll").getResultList();	
	}

	@Override
	public Integer updateAllowedFlagStatus(Integer id, String status) {
		return entityManager
				.createNamedQuery(
						"AllowedDeviceVersionsEntityNew.updateAllowedStatus")
				.setParameter("id", id).setParameter("status", status)
				.executeUpdate();
	}

	@Override
	public List<AllowedDeviceVersionsEntityNew> findAllVersionsToAllow() {
		return entityManager.createNamedQuery(
				"AllowedDeviceVersionsEntityNew.findAllVersionsToAllow",
				AllowedDeviceVersionsEntityNew.class).getResultList();
	}

}
