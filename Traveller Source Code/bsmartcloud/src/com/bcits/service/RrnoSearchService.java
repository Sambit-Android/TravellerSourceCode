package com.bcits.service;

import java.util.List;
import java.util.Map;

import org.springframework.ui.ModelMap;

import com.bcits.entity.RrnoSearchEntity;

public interface RrnoSearchService extends GenericServiceOracle<RrnoSearchEntity> {

	List<Map<String, Object>> getRRnumberlist(String inputtype, String inputVariable, String schemaname, String year);
		
	}

	
