package com.bcits.service;

import java.util.List;

import com.bcits.entity.MRDepositEntity;

public interface MRDepositService extends GenericService<MRDepositEntity> {


	public List<MRDepositEntity> getAllMRDeposits();

	public List<MRDepositEntity> getAllMRDepositsByDate(String reqDate);

	public List<Object> getAllMRDepositesByTwoDates(String fromDate,
			String toDate, String sdoCode, String mrCode);

	public List<Object[]> getMRDepositsAndPayments(String fromDate,
			String toDate,
			String sdoCode, String mrCode);
}
