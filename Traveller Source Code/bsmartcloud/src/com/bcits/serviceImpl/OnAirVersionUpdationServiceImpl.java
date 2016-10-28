package com.bcits.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bcits.entity.OnAirVersionUpdationEntity;
import com.bcits.service.OnAirVersionUpdationService;

@Repository
public class OnAirVersionUpdationServiceImpl extends GenericServiceImpl<OnAirVersionUpdationEntity>	implements OnAirVersionUpdationService {

	@Override
	public List<OnAirVersionUpdationEntity> findAll() {
		return entityManager.createNamedQuery("OnAirVersionUpdationEntity.findAll",	OnAirVersionUpdationEntity.class).getResultList();
	}

	@Override
	public long checkVersionExist(String version) {
		return (Long)entityManager.createNamedQuery("OnAirVersionUpdationEntity.checkVersionExist").setParameter("version", version).getSingleResult();
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<OnAirVersionUpdationEntity> getlatestVersion() {
		return entityManager.createNamedQuery(
				"OnAirVersionUpdationEntity.latestVersion",
				OnAirVersionUpdationEntity.class).getResultList();
	}
}
