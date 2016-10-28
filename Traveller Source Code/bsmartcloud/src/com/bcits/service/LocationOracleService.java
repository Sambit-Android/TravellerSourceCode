package com.bcits.service;

import java.util.List;

import org.springframework.ui.ModelMap;

import com.bcits.entity.LocationOracleEntity;


public interface LocationOracleService  extends GenericServiceOracle<LocationOracleEntity> {
	public List<LocationOracleEntity> getAllLocations();

	public List<LocationOracleEntity> getLocationData(String sdocode,ModelMap model);
}
