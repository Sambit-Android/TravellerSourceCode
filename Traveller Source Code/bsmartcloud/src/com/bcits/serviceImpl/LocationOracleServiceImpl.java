package com.bcits.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

import com.bcits.entity.LocationOracleEntity;
import com.bcits.service.LocationOracleService;

@Repository
public class LocationOracleServiceImpl extends GenericServiceImplOracle<LocationOracleEntity> implements LocationOracleService
{

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<LocationOracleEntity> getAllLocations() {
		
		return oracleEntityManager.createNamedQuery("LocationOracleEntity.getAllLocations",LocationOracleEntity.class).getResultList();
	}
	
	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public  List<LocationOracleEntity> getLocationData(String sdocode,ModelMap model) 
	{
		return oracleEntityManager.createNamedQuery("LocationOracleEntity.getLocationBySdocode").setParameter("sitecode", Integer.parseInt(sdocode)).getResultList();
	}
}
