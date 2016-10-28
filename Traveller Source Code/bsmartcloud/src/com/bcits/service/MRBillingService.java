package com.bcits.service;

import java.util.List;

import com.bcits.entity.BillingProgressEntity;
import com.bcits.entity.LocationOracleEntity;


public interface MRBillingService  extends GenericServiceOracle<BillingProgressEntity> {
	public List<?> getMrBillingProgress(String sitecode);
	
}






