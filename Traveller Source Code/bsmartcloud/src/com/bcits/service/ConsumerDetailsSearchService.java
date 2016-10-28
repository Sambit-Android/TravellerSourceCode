package com.bcits.service;

import java.util.List;
import java.util.Map;

import com.bcits.entity.MatrixEntity;
import com.bcits.entity.RrnoSearchEntity;

public interface ConsumerDetailsSearchService extends GenericServiceOracle<MatrixEntity> {

	List<Map<String, Object>> getConsumerlistforrrno(String rrno, String schemaname);

	List<Map<String, Object>> getBillingDetailsforrrno(String rrno, String schemaname);

	List<Map<String, Object>> getPaymentDetailsforrrno(String rrno, String schemaname);

	List<Map<String, Object>> getDepositDetailsforrrno(String rrno, String schemaname);

	List<Map<String, Object>> getMeterDetailsforrrno(String rrno, String schemaname);

	List<Map<String, Object>> getMeterChangeDetailsforrrno(String rrno, String schemaname);

	
		
}
