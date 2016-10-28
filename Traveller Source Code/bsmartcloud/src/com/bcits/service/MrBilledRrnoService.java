package com.bcits.service;

import java.util.List;

import com.bcits.entity.MatrixEntity;


public interface MrBilledRrnoService  extends GenericServiceOracle<MatrixEntity> {
	public List<?> getMrBilledRrnosMonth(String sdocode,String schema,String rdngmonth,String mrcode) ;
}






