package com.bcits.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jettison.json.JSONArray;

import com.bcits.entity.DefectiveMeterEntity;
import com.bcits.entity.MeterChangeEntity;

public interface DefectiveMeterService extends GenericServiceOracle<DefectiveMeterEntity>{
	
	/*public List<DefectiveMeterEntity> getMobileBillingDataDefective(String listno, HttpServletRequest request);
	public String getListNoValidationResultDefective(String listno, HttpServletRequest request);*/
	JSONArray getAllDisConnectionListDataNew(int listno, String schema, int readingmth);

}
