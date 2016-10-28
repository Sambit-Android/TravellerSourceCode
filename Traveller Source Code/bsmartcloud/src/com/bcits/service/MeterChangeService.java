package com.bcits.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jettison.json.JSONArray;
import org.springframework.ui.ModelMap;

import com.bcits.entity.MeterChangeEntity;
import com.bcits.utility.Resultupdated;

public interface MeterChangeService extends GenericService<MeterChangeEntity> {
	
	public List<MeterChangeEntity> getMobileBillingData(String listno, HttpServletRequest request);
	public String getListNoValidationResult(String listno, HttpServletRequest request);
	public ArrayList<Resultupdated> updateMobileDataToMeterChangeTable(HttpServletRequest request, JSONArray array);
	public List<Object[]> showMeterReplacementOnMap(String divSdo, String date,
			ModelMap model);

}
